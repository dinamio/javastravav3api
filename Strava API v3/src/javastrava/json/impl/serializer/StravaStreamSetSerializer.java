package javastrava.json.impl.serializer;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import javastrava.model.StravaStreamData;
import javastrava.model.StravaStreamSet;

/**
 * JSON serializer/deserializer for StravaStreamSet objects
 * Handles the key_by_type=true response format where streams are keyed by type name
 * 
 * @author Dan Shannon
 */
public class StravaStreamSetSerializer implements JsonSerializer<StravaStreamSet>, JsonDeserializer<StravaStreamSet> {

	/**
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public StravaStreamSet deserialize(final JsonElement element, final Type type, final JsonDeserializationContext context) throws JsonParseException {
		JsonObject json = null;
		try {
			json = (JsonObject) element;
		} catch (final ClassCastException e) {
			throw new JsonParseException(e);
		}

		final StravaStreamSet streamSet = new StravaStreamSet();
		final Map<String, StravaStreamData> streams = new HashMap<>();

		// Iterate through all properties in the JSON object
		// Each property represents a stream type (e.g., "heartrate", "distance")
		for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
			final String streamType = entry.getKey();
			final JsonElement streamElement = entry.getValue();
			
			if (streamElement.isJsonObject()) {
				// Deserialize the stream data
				final StravaStreamData streamData = context.deserialize(streamElement, StravaStreamData.class);
				streams.put(streamType, streamData);
			}
		}

		streamSet.setStreams(streams);
		return streamSet;
	}

	/**
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(final StravaStreamSet streamSet, final Type type, final JsonSerializationContext context) {
		final JsonObject element = new JsonObject();
		
		if (streamSet.getStreams() != null) {
			for (Map.Entry<String, StravaStreamData> entry : streamSet.getStreams().entrySet()) {
				final String streamType = entry.getKey();
				final StravaStreamData streamData = entry.getValue();
				element.add(streamType, context.serialize(streamData));
			}
		}
		
		return element;
	}
}
