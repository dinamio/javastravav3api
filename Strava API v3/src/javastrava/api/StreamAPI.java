package javastrava.api;

import javastrava.api.async.StravaAPICallback;
import javastrava.model.StravaStream;
import javastrava.model.StravaStreamSet;
import javastrava.model.reference.StravaStreamResolutionType;
import javastrava.model.reference.StravaStreamSeriesDownsamplingType;
import javastrava.model.reference.StravaStreamType;
import javastrava.service.StreamService;
import javastrava.service.exception.BadRequestException;
import javastrava.service.exception.NotFoundException;
import javastrava.service.exception.UnauthorizedException;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * <p>
 * API definitions for {@link StreamService} endpoints
 * </p>
 *
 * @author Dan Shannon
 *
 */
public interface StreamAPI {
	/**
	 * Converts an array of StravaStreamType enums to a comma-separated string for API calls
	 * @param types Array of stream types
	 * @return Comma-separated string of stream type values
	 */
	static String typeString(final StravaStreamType[] types) {
		if (types == null || types.length == 0) {
			return null;
		}
		if (types.length == 1) {
			return types[0].getValue();
		}
		StringBuilder typeString = new StringBuilder(types[0].getValue());
		for (int i = 1; i < types.length; i++) {
			typeString.append(",").append(types[i].getValue());
		}
		return typeString.toString();
	}
	/**
	 * @see javastrava.service.StreamService#getActivityStreams(Long, javastrava.model.reference.StravaStreamType...)
	 *
	 * @param activityId
	 *            The id of the activity for which streams are to be retrieved
	 * @param types
	 *            Array of stream types, if the activity does not have that stream it will not be included in the response
	 * @param keyByType
	 *            (Optional) Used to alter the output of the returned streams. If true, the keys of streams will be their types
	 * @return Returns an array of unordered stream objects, or <code>null</code> if the activity doesn't exist
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 * @throws NotFoundException
	 *             If the activity does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/activities/{id}/streams")
	default StravaStream[] getActivityStreams(@Path("id") final Long activityId, final StravaStreamType[] types, @Query("key_by_type") final Boolean keyByType) throws UnauthorizedException, NotFoundException, BadRequestException {
		return getActivityStreams(activityId, typeString(types), keyByType);
	}

	/**
	 * @see javastrava.service.StreamService#getActivityStreams(Long, javastrava.model.reference.StravaStreamType...)
	 *
	 * @param activityId
	 *            The id of the activity for which streams are to be retrieved
	 * @param keys
	 *            Comma separated list of types, if the activity does not have that stream it will not be included in the response
	 * @param keyByType
	 *            (Optional) Used to alter the output of the returned streams. If true, the keys of streams will be their types
	 * @return Returns an array of unordered stream objects, or <code>null</code> if the activity doesn't exist
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 * @throws NotFoundException
	 *             If the activity does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/activities/{id}/streams")
	public StravaStream[] getActivityStreams(@Path("id") final Long activityId, @Query("keys") final String keys, @Query("key_by_type") final Boolean keyByType) throws UnauthorizedException, NotFoundException, BadRequestException;

	/**
	 * @see javastrava.service.StreamService#getActivityStreams(Long, javastrava.model.reference.StravaStreamType...)
	 *
	 * @param activityId
	 *            The id of the activity for which streams are to be retrieved
	 * @param types
	 *            Array of stream types, if the activity does not have that stream it will not be included in the response
	 * @param keyByType
	 *            (Optional) Used to alter the output of the returned streams. If true, the keys of streams will be their types
	 * @param callback
	 *            The callback to execute on completion
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 * @throws NotFoundException
	 *             If the activity does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/activities/{id}/streams")
	default void getActivityStreams(@Path("id") final Long activityId, final StravaStreamType[] types, @Query("key_by_type") final Boolean keyByType, final StravaAPICallback<StravaStream[]> callback)
			throws UnauthorizedException, NotFoundException, BadRequestException {
		getActivityStreams(activityId, typeString(types), keyByType, callback);
	}

	/**
	 * @see javastrava.service.StreamService#getActivityStreams(Long, javastrava.model.reference.StravaStreamType...)
	 *
	 * @param activityId
	 *            The id of the activity for which streams are to be retrieved
	 * @param keys
	 *            Comma separated list of types, if the activity does not have that stream it will not be included in the response
	 * @param keyByType
	 *            (Optional) Used to alter the output of the returned streams. If true, the keys of streams will be their types
	 * @param callback
	 *            The callback to execute on completion
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 * @throws NotFoundException
	 *             If the activity does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/activities/{id}/streams")
	public void getActivityStreams(@Path("id") final Long activityId, @Query("keys") final String keys, @Query("key_by_type") final Boolean keyByType, final StravaAPICallback<StravaStream[]> callback)
			throws UnauthorizedException, NotFoundException, BadRequestException;

	/**
	 * @see javastrava.service.StreamService#getActivityStreams(Long, javastrava.model.reference.StravaStreamType...)
	 *
	 * @param activityId
	 *            The id of the activity for which streams are to be retrieved
	 * @param types
	 *            Array of stream types, if the activity does not have that stream it will not be included in the response
	 * @param keyByType
	 *            (Optional) Used to alter the output of the returned streams. If true, the keys of streams will be their types
	 * @return Returns an array of unordered stream objects, or <code>null</code> if the activity doesn't exist
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 * @throws NotFoundException
	 *             If the activity does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/activities/{id}/streams")
	default Response getActivityStreamsRaw(@Path("id") final Long activityId, final StravaStreamType[] types, @Query("key_by_type") final Boolean keyByType) throws UnauthorizedException, NotFoundException, BadRequestException {
		return getActivityStreamsRaw(activityId, typeString(types), keyByType);
	}

	/**
	 * @see javastrava.service.StreamService#getActivityStreams(Long, javastrava.model.reference.StravaStreamType...)
	 *
	 * @param activityId
	 *            The id of the activity for which streams are to be retrieved
	 * @param keys
	 *            Comma separated list of types, if the activity does not have that stream it will not be included in the response
	 * @param keyByType
	 *            (Optional) Used to alter the output of the returned streams. If true, the keys of streams will be their types
	 * @return Returns an array of unordered stream objects, or <code>null</code> if the activity doesn't exist
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 * @throws NotFoundException
	 *             If the activity does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/activities/{id}/streams")
	public Response getActivityStreamsRaw(@Path("id") final Long activityId, @Query("keys") final String keys, @Query("key_by_type") final Boolean keyByType) throws UnauthorizedException, NotFoundException, BadRequestException;

	/**
	 * Get activity streams as a keyed object (key_by_type=true)
	 *
	 * @param activityId
	 *            The id of the activity for which streams are to be retrieved
	 * @param types
	 *            Array of stream types, if the activity does not have that stream it will not be included in the response
	 * @return Returns a stream set with streams keyed by type, or <code>null</code> if the activity doesn't exist
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 * @throws NotFoundException
	 *             If the activity does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/activities/{id}/streams")
	default StravaStreamSet getActivityStreamSet(@Path("id") final Long activityId, final StravaStreamType[] types) throws UnauthorizedException, NotFoundException, BadRequestException {
		return getActivityStreamSet(activityId, typeString(types), true);
	}

	/**
	 * Get activity streams as a keyed object (key_by_type=true)
	 *
	 * @param activityId
	 *            The id of the activity for which streams are to be retrieved
	 * @param keys
	 *            Comma separated list of types, if the activity does not have that stream it will not be included in the response
	 * @return Returns a stream set with streams keyed by type, or <code>null</code> if the activity doesn't exist
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 * @throws NotFoundException
	 *             If the activity does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/activities/{id}/streams")
	public StravaStreamSet getActivityStreamSet(@Path("id") final Long activityId, @Query("keys") final String keys, @Query("key_by_type") final Boolean keyByType) throws UnauthorizedException, NotFoundException, BadRequestException;

	/**
	 * Get activity streams as a keyed object (key_by_type=true) - Async version
	 *
	 * @param activityId
	 *            The id of the activity for which streams are to be retrieved
	 * @param types
	 *            Array of stream types, if the activity does not have that stream it will not be included in the response
	 * @param callback
	 *            The callback to execute on completion
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 * @throws NotFoundException
	 *             If the activity does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/activities/{id}/streams")
	default void getActivityStreamSet(@Path("id") final Long activityId, final StravaStreamType[] types, final StravaAPICallback<StravaStreamSet> callback) throws UnauthorizedException, NotFoundException, BadRequestException {
		getActivityStreamSet(activityId, typeString(types), true, callback);
	}

	/**
	 * Get activity streams as a keyed object (key_by_type=true) - Async version
	 *
	 * @param activityId
	 *            The id of the activity for which streams are to be retrieved
	 * @param keys
	 *            Comma separated list of types, if the activity does not have that stream it will not be included in the response
	 * @param callback
	 *            The callback to execute on completion
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 * @throws NotFoundException
	 *             If the activity does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/activities/{id}/streams")
	public void getActivityStreamSet(@Path("id") final Long activityId, @Query("keys") final String keys, @Query("key_by_type") final Boolean keyByType, final StravaAPICallback<StravaStreamSet> callback) throws UnauthorizedException, NotFoundException, BadRequestException;

	/**
	 * @see javastrava.service.StreamService#getEffortStreams(Long, StravaStreamResolutionType, StravaStreamSeriesDownsamplingType, javastrava.model.reference.StravaStreamType...)
	 *
	 * @param segmentEffortId
	 *            The id of the segment effort for which streams are to be retrieved
	 * @param types
	 *            List of types, if the effort does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is being reduced
	 * @return Returns an array of unordered stream objects.
	 * @throws UnauthorizedException
	 *             If the security token is not valid or the effort is flagged as private
	 * @throws NotFoundException
	 *             If the effort does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/segment_efforts/{id}/streams/{types}")
	public StravaStream[] getEffortStreams(@Path("id") final Long segmentEffortId, @Path("types") final String types, @Query("resolution") final StravaStreamResolutionType resolution,
			@Query("series_type") final StravaStreamSeriesDownsamplingType seriesType) throws UnauthorizedException, NotFoundException, BadRequestException;

	/**
	 * @see javastrava.service.StreamService#getEffortStreams(Long, StravaStreamResolutionType, StravaStreamSeriesDownsamplingType, javastrava.model.reference.StravaStreamType...)
	 *
	 * @param segmentEffortId
	 *            The id of the segment effort for which streams are to be retrieved
	 * @param types
	 *            List of types, if the effort does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is being reduced
	 * @param callback
	 *            The callback to execute on completion
	 * @throws UnauthorizedException
	 *             If the security token is not valid or the effort is flagged as private
	 * @throws NotFoundException
	 *             If the effort does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/segment_efforts/{id}/streams/{types}")
	public void getEffortStreams(@Path("id") final Long segmentEffortId, @Path("types") final String types, @Query("resolution") final StravaStreamResolutionType resolution,
			@Query("series_type") final StravaStreamSeriesDownsamplingType seriesType, final StravaAPICallback<StravaStream[]> callback)
			throws UnauthorizedException, NotFoundException, BadRequestException;

	/**
	 * @see javastrava.service.StreamService#getEffortStreams(Long, StravaStreamResolutionType, StravaStreamSeriesDownsamplingType, javastrava.model.reference.StravaStreamType...)
	 *
	 * @param segmentEffortId
	 *            The id of the segment effort for which streams are to be retrieved
	 * @param types
	 *            List of types, if the effort does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is being reduced
	 * @return Returns an array of unordered stream objects.
	 * @throws UnauthorizedException
	 *             If the security token is not valid or the effort is flagged as private
	 * @throws NotFoundException
	 *             If the effort does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/segment_efforts/{id}/streams/{types}")
	public Response getEffortStreamsRaw(@Path("id") final Long segmentEffortId, @Path("types") final String types, @Query("resolution") final StravaStreamResolutionType resolution,
			@Query("series_type") final StravaStreamSeriesDownsamplingType seriesType) throws UnauthorizedException, NotFoundException, BadRequestException;

	/**
	 * <p>
	 * distance, altitude and latlng stream types are always returned.
	 * </p>
	 *
	 * @param id
	 *            Id of the route
	 * @return Array of Streams. distance, altitude and latlng stream types are always returned.
	 * @throws UnauthorizedException
	 *             if the route is private and token doesn't have view_private authorisation scope
	 * @throws NotFoundException
	 *             if the route doesn't exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/routes/{id}/streams")
	public StravaStream[] getRouteStreams(@Path("id") final Integer id) throws UnauthorizedException, NotFoundException, BadRequestException;

	/**
	 * <p>
	 * distance, altitude and latlng stream types are always returned.
	 * </p>
	 *
	 * @param id
	 *            Id of the route
	 * @param callback
	 *            The callback to execute on completion
	 * @throws UnauthorizedException
	 *             if the route is private and token doesn't have view_private authorisation scope
	 * @throws NotFoundException
	 *             if the route doesn't exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/routes/{id}/streams")
	public void getRouteStreams(@Path("id") final Integer id, final StravaAPICallback<StravaStream[]> callback) throws UnauthorizedException, NotFoundException, BadRequestException;

	/**
	 * <p>
	 * distance, altitude and latlng stream types are always returned.
	 * </p>
	 *
	 * @param id
	 *            Id of the route
	 * @return Array of Streams. distance, altitude and latlng stream types are always returned.
	 * @throws UnauthorizedException
	 *             if the route is private and token doesn't have view_private authorisation scope
	 * @throws NotFoundException
	 *             if the route doesn't exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/routes/{id}/streams")
	public Response getRouteStreamsRaw(@Path("id") final Integer id) throws UnauthorizedException, NotFoundException, BadRequestException;

	/**
	 * @see javastrava.service.StreamService#getSegmentStreams(Integer, StravaStreamResolutionType, StravaStreamSeriesDownsamplingType, javastrava.model.reference.StravaStreamType...)
	 *
	 * @param segmentId
	 *            The id of the segment for which streams are to be retrieved
	 * @param types
	 *            List of types, if the segment does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is being reduced
	 * @return Returns an array of unordered stream objects.
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 * @throws NotFoundException
	 *             If the segment does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/segments/{id}/streams/{types}")
	public StravaStream[] getSegmentStreams(@Path("id") final Integer segmentId, @Path("types") final String types, @Query("resolution") final StravaStreamResolutionType resolution,
			@Query("series_type") final StravaStreamSeriesDownsamplingType seriesType) throws UnauthorizedException, NotFoundException, BadRequestException;

	/**
	 * @see javastrava.service.StreamService#getSegmentStreams(Integer, StravaStreamResolutionType, StravaStreamSeriesDownsamplingType, javastrava.model.reference.StravaStreamType...)
	 *
	 * @param segmentId
	 *            The id of the segment for which streams are to be retrieved
	 * @param types
	 *            List of types, if the segment does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is being reduced
	 * @param callback
	 *            The callback to execute on completion
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 * @throws NotFoundException
	 *             If the segment does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/segments/{id}/streams/{types}")
	public void getSegmentStreams(@Path("id") final Integer segmentId, @Path("types") final String types, @Query("resolution") final StravaStreamResolutionType resolution,
			@Query("series_type") final StravaStreamSeriesDownsamplingType seriesType, final StravaAPICallback<StravaStream[]> callback)
			throws UnauthorizedException, NotFoundException, BadRequestException;

	/**
	 * @see javastrava.service.StreamService#getSegmentStreams(Integer, StravaStreamResolutionType, StravaStreamSeriesDownsamplingType, javastrava.model.reference.StravaStreamType...)
	 *
	 * @param segmentId
	 *            The id of the segment for which streams are to be retrieved
	 * @param types
	 *            List of types, if the segment does not have that stream it will not be included in the response
	 * @param resolution
	 *            (Optional) low (100), medium (1000) or high (10000), default is all, indicates desired number of data points, streams will only be down sampled
	 * @param seriesType
	 *            (Optional) relevant only if using resolution. Either "time" or "distance", default is "distance", used to index the streams if the stream is being reduced
	 * @return Returns an array of unordered stream objects.
	 * @throws UnauthorizedException
	 *             If there is a security exception
	 * @throws NotFoundException
	 *             If the segment does not exist
	 * @throws BadRequestException
	 *             If the request is malformed
	 */
	@GET("/segments/{id}/streams/{types}")
	public Response getSegmentStreamsRaw(@Path("id") final Integer segmentId, @Path("types") final String types, @Query("resolution") final StravaStreamResolutionType resolution,
			@Query("series_type") final StravaStreamSeriesDownsamplingType seriesType) throws UnauthorizedException, NotFoundException, BadRequestException;
}
