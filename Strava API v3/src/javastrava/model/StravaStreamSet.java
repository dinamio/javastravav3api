package javastrava.model;

import java.util.Map;
import java.util.HashMap;

import javastrava.model.reference.StravaResourceState;

/**
 * <p>
 * Stream set for key_by_type=true API responses.
 * Contains a map where keys are stream type names (e.g., "heartrate", "distance") 
 * and values are the corresponding stream data.
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaStreamSet implements StravaEntity {
	/**
	 * Map of stream type names to stream data
	 */
	private Map<String, StravaStreamData> streams;

	/**
	 * No args constructor
	 */
	public StravaStreamSet() {
		super();
		this.streams = new HashMap<>();
	}

	/**
	 * Constructor with streams map
	 * @param streams Map of stream type names to stream data
	 */
	public StravaStreamSet(final Map<String, StravaStreamData> streams) {
		this.streams = streams != null ? streams : new HashMap<>();
	}

	/**
	 * @return the streams map
	 */
	public Map<String, StravaStreamData> getStreams() {
		return this.streams;
	}

	/**
	 * @param streams the streams map to set
	 */
	public void setStreams(final Map<String, StravaStreamData> streams) {
		this.streams = streams;
	}

	/**
	 * Get a specific stream by type name
	 * @param streamType The stream type name (e.g., "heartrate", "distance")
	 * @return The stream data for the specified type, or null if not found
	 */
	public StravaStreamData getStream(final String streamType) {
		return this.streams != null ? this.streams.get(streamType) : null;
	}

	/**
	 * Add a stream to the set
	 * @param streamType The stream type name
	 * @param streamData The stream data
	 */
	public void addStream(final String streamType, final StravaStreamData streamData) {
		if (this.streams == null) {
			this.streams = new HashMap<>();
		}
		this.streams.put(streamType, streamData);
	}

	/**
	 * Check if a stream type exists in the set
	 * @param streamType The stream type name to check
	 * @return true if the stream type exists, false otherwise
	 */
	public boolean hasStream(final String streamType) {
		return this.streams != null && this.streams.containsKey(streamType);
	}

	/**
	 * Get the number of streams in the set
	 * @return The number of streams
	 */
	public int size() {
		return this.streams != null ? this.streams.size() : 0;
	}

	/**
	 * Check if the stream set is empty
	 * @return true if no streams are present, false otherwise
	 */
	public boolean isEmpty() {
		return this.streams == null || this.streams.isEmpty();
	}

	/**
	 * @return the resource state (always DETAILED for stream sets)
	 */
	@Override
	public StravaResourceState getResourceState() {
		return StravaResourceState.DETAILED;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof StravaStreamSet)) {
			return false;
		}
		final StravaStreamSet other = (StravaStreamSet) obj;
		if (this.streams == null) {
			if (other.streams != null) {
				return false;
			}
		} else if (!this.streams.equals(other.streams)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.streams == null) ? 0 : this.streams.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "StravaStreamSet [streams=" + this.streams + "]";
	}
}
