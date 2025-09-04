package javastrava.model;

import java.util.List;

import javastrava.model.reference.StravaResourceState;
import javastrava.model.reference.StravaStreamResolutionType;
import javastrava.model.reference.StravaStreamSeriesDownsamplingType;

/**
 * <p>
 * Stream data object for key_by_type=true API responses.
 * Contains the actual stream data without the type field (since type is the key).
 * </p>
 *
 * @author Dan Shannon
 *
 */
public class StravaStreamData implements StravaEntity {
	/**
	 * Raw data (either this or {@link #mapPoints} or {@link #moving} will be populated, depending on the stream type).
	 */
	private List<Float> data;
	
	/**
	 * Raw GPS co-ordinates (either this or {@link #data} or {@link #moving} will be populated, depending on the stream type).
	 */
	private List<StravaMapPoint> mapPoints;
	
	/**
	 * Boolean data stream indicating whether athlete was moving or not (either this or {@link #data} or {@link #mapPoints} will be populated, depending on the stream type).
	 */
	private List<Boolean> moving;
	
	/**
	 * Method of downsampling applied by Strava when returning the stream (if appropriate) - either by distance or by time
	 */
	private StravaStreamSeriesDownsamplingType seriesType;
	
	/**
	 * Number of data points in the complete stream
	 */
	private Integer originalSize;
	
	/**
	 * Reduced resolution of this stream representation (if appropriate)
	 */
	private StravaStreamResolutionType resolution;

	/**
	 * No args constructor
	 */
	public StravaStreamData() {
		super();
	}

	/**
	 * @return the data
	 */
	public List<Float> getData() {
		return this.data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(final List<Float> data) {
		this.data = data;
	}

	/**
	 * @return the mapPoints
	 */
	public List<StravaMapPoint> getMapPoints() {
		return this.mapPoints;
	}

	/**
	 * @param mapPoints the mapPoints to set
	 */
	public void setMapPoints(final List<StravaMapPoint> mapPoints) {
		this.mapPoints = mapPoints;
	}

	/**
	 * @return the moving
	 */
	public List<Boolean> getMoving() {
		return this.moving;
	}

	/**
	 * @param moving the moving to set
	 */
	public void setMoving(final List<Boolean> moving) {
		this.moving = moving;
	}

	/**
	 * @return the seriesType
	 */
	public StravaStreamSeriesDownsamplingType getSeriesType() {
		return this.seriesType;
	}

	/**
	 * @param seriesType the seriesType to set
	 */
	public void setSeriesType(final StravaStreamSeriesDownsamplingType seriesType) {
		this.seriesType = seriesType;
	}

	/**
	 * @return the originalSize
	 */
	public Integer getOriginalSize() {
		return this.originalSize;
	}

	/**
	 * @param originalSize the originalSize to set
	 */
	public void setOriginalSize(final Integer originalSize) {
		this.originalSize = originalSize;
	}

	/**
	 * @return the resolution
	 */
	public StravaStreamResolutionType getResolution() {
		return this.resolution;
	}

	/**
	 * @param resolution the resolution to set
	 */
	public void setResolution(final StravaStreamResolutionType resolution) {
		this.resolution = resolution;
	}

	/**
	 * @return the resource state (always DETAILED for stream data)
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
		if (!(obj instanceof StravaStreamData)) {
			return false;
		}
		final StravaStreamData other = (StravaStreamData) obj;
		if (this.data == null) {
			if (other.data != null) {
				return false;
			}
		} else if (!this.data.equals(other.data)) {
			return false;
		}
		if (this.mapPoints == null) {
			if (other.mapPoints != null) {
				return false;
			}
		} else if (!this.mapPoints.equals(other.mapPoints)) {
			return false;
		}
		if (this.moving == null) {
			if (other.moving != null) {
				return false;
			}
		} else if (!this.moving.equals(other.moving)) {
			return false;
		}
		if (this.originalSize == null) {
			if (other.originalSize != null) {
				return false;
			}
		} else if (!this.originalSize.equals(other.originalSize)) {
			return false;
		}
		if (this.resolution != other.resolution) {
			return false;
		}
		if (this.seriesType != other.seriesType) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.data == null) ? 0 : this.data.hashCode());
		result = (prime * result) + ((this.mapPoints == null) ? 0 : this.mapPoints.hashCode());
		result = (prime * result) + ((this.moving == null) ? 0 : this.moving.hashCode());
		result = (prime * result) + ((this.originalSize == null) ? 0 : this.originalSize.hashCode());
		result = (prime * result) + ((this.resolution == null) ? 0 : this.resolution.hashCode());
		result = (prime * result) + ((this.seriesType == null) ? 0 : this.seriesType.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "StravaStreamData [data=" + this.data + ", mapPoints=" + this.mapPoints + ", moving=" + this.moving
				+ ", seriesType=" + this.seriesType + ", originalSize=" + this.originalSize + ", resolution="
				+ this.resolution + "]";
	}
}
