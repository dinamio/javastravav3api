package javastrava.json.impl.serializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import javastrava.model.StravaMapPoint;
import javastrava.model.StravaStreamData;
import javastrava.model.reference.StravaStreamResolutionType;
import javastrava.model.reference.StravaStreamSeriesDownsamplingType;

/**
 * JSON serializer/deserializer for StravaStreamData objects
 * 
 * @author Dan Shannon
 */
public class StravaStreamDataSerializer implements JsonSerializer<StravaStreamData>, JsonDeserializer<StravaStreamData> {

	/**
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public StravaStreamData deserialize(final JsonElement element, final Type type, final JsonDeserializationContext context) throws JsonParseException {
		JsonObject json = null;
		try {
			json = (JsonObject) element;
		} catch (final ClassCastException e) {
			throw new JsonParseException(e);
		}

		final StravaStreamData streamData = new StravaStreamData();

		// Parse the data array - could be coordinates, booleans, or floats
		final JsonElement dataElement = json.get("data");
		if (dataElement != null && dataElement.isJsonArray()) {
			final JsonArray array = dataElement.getAsJsonArray();
			
			// Check if this is coordinate data (array of arrays with 2 elements each)
			if (array.size() > 0 && array.get(0).isJsonArray() && array.get(0).getAsJsonArray().size() == 2) {
				// This is latlng data
				List<StravaMapPoint> points = new ArrayList<StravaMapPoint>();
				for (final JsonElement arrayElement : array) {
					final JsonArray coordArray = arrayElement.getAsJsonArray();
					final Float latitude = Float.valueOf(coordArray.get(0).getAsFloat());
					final Float longitude = Float.valueOf(coordArray.get(1).getAsFloat());
					final StravaMapPoint point = new StravaMapPoint(latitude, longitude);
					points.add(point);
				}
				streamData.setMapPoints(points);
			} else if (array.size() > 0 && array.get(0).isJsonPrimitive() && array.get(0).getAsJsonPrimitive().isBoolean()) {
				// This is boolean data (moving stream)
				List<Boolean> moving = new ArrayList<Boolean>();
				for (final JsonElement arrayElement : array) {
					final Boolean bool = Boolean.valueOf(arrayElement.getAsBoolean());
					moving.add(bool);
				}
				streamData.setMoving(moving);
			} else {
				// This is numeric data
				List<Float> data = new ArrayList<Float>();
				for (final JsonElement arrayElement : array) {
					if (arrayElement.isJsonNull()) {
						data.add(null);
					} else {
						final Float dataValue = Float.valueOf(arrayElement.getAsFloat());
						data.add(dataValue);
					}
				}
				streamData.setData(data);
			}
		}

		// Parse other fields
		final JsonElement originalSizeElement = json.get("original_size");
		if (originalSizeElement != null && !originalSizeElement.isJsonNull()) {
			streamData.setOriginalSize(Integer.valueOf(originalSizeElement.getAsInt()));
		}

		streamData.setResolution((StravaStreamResolutionType) context.deserialize(json.get("resolution"), StravaStreamResolutionType.class));
		streamData.setSeriesType((StravaStreamSeriesDownsamplingType) context.deserialize(json.get("series_type"), StravaStreamSeriesDownsamplingType.class));

		return streamData;
	}

	/**
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(final StravaStreamData streamData, final Type type, final JsonSerializationContext context) {
		final JsonObject element = new JsonObject();
		
		element.add("original_size", context.serialize(streamData.getOriginalSize()));
		element.add("resolution", context.serialize(streamData.getResolution()));
		element.add("series_type", context.serialize(streamData.getSeriesType()));
		
		final JsonArray dataArray = new JsonArray();
		element.add("data", dataArray);
		
		if (streamData.getMapPoints() != null) {
			for (final StravaMapPoint point : streamData.getMapPoints()) {
				dataArray.add(context.serialize(point));
			}
		} else if (streamData.getMoving() != null) {
			for (final Boolean moving : streamData.getMoving()) {
				dataArray.add(context.serialize(moving));
			}
		} else if (streamData.getData() != null) {
			for (final Float number : streamData.getData()) {
				dataArray.add(context.serialize(number));
			}
		}
		
		return element;
	}
}
