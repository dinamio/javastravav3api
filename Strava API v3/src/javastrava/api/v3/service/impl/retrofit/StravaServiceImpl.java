package javastrava.api.v3.service.impl.retrofit;

import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.service.exception.UnauthorizedException;
import javastrava.config.Messages;
import javastrava.config.StravaConfig;
import lombok.extern.log4j.Log4j2;

/**
 * <p>
 * Base class for all implementations of Strava services
 * </p>
 * 
 * @author Dan Shannon
 * @param <T> The class of retrofit interface to be created
 *
 */
@Log4j2
public abstract class StravaServiceImpl<T> {
//	private final AthleteServices athleteService;
	
	/**
	 * Current request rate over the last 15 minutes
	 */
	public static int requestRate = 0;
	/**
	 * Current request rate over the last day
	 */
	public static int requestRateDaily = 0;
	
	private final Token token;
	protected final T restService;
	
	/**
	 * Calculates the percentage of the per-15-minute request limit that has been used, issues a warning if required
	 * 
	 * @return Percentage used.
	 */
	public static float requestRatePercentage() {
		float percent = (StravaConfig.RATE_LIMIT == 0 ? 0 : 100 * new Float(requestRate).floatValue() / new Float(StravaConfig.RATE_LIMIT).floatValue());
		if (percent > 100) {
			log.error(String.format(Messages.string("StravaServiceImpl.exceededRateLimit"), Integer.valueOf(requestRate), Integer.valueOf(StravaConfig.RATE_LIMIT), Float.valueOf(percent))); //$NON-NLS-1$
		}
		else if (percent > StravaConfig.WARN_AT_REQUEST_LIMIT_PERCENT) {
			log.warn(String.format(Messages.string("StravaServiceImpl.approachingRateLimit"), Integer.valueOf(requestRate), Integer.valueOf(StravaConfig.RATE_LIMIT), Float.valueOf(percent))); //$NON-NLS-1$
		}
		return percent;
	}
	
	/**
	 * Calculates the percentage of the daily request limit that has been used, issues a warning if required
	 * 
	 * @return Percentage used.
	 */
	public static float requestRateDailyPercentage() {
		float percent = (StravaConfig.RATE_LIMIT_DAILY == 0 ? 0 : 100 * new Float(requestRateDaily).floatValue() / new Float(StravaConfig.RATE_LIMIT_DAILY).floatValue());
		if (percent > 100) {
			log.error(String.format(Messages.string("StravaServiceImpl.exceededRateLimitDaily"), Integer.valueOf(requestRateDaily), Integer.valueOf(StravaConfig.RATE_LIMIT_DAILY), Float.valueOf(percent))); //$NON-NLS-1$
		}
		else if (percent > StravaConfig.WARN_AT_REQUEST_LIMIT_PERCENT) {
			log.warn(String.format(Messages.string("StravaServiceImpl.approachingRateLimitDaily"), Integer.valueOf(requestRateDaily), Integer.valueOf(StravaConfig.RATE_LIMIT_DAILY), Float.valueOf(percent))); //$NON-NLS-1$
		}
		return percent;
	}

	/**
	 * <p>
	 * Protected constructor prevents user from getting a service instance without a valid token
	 * </p>
	 * 
	 * @param class1 The class of the service implementation being created
	 * @param token The access token to be used to authenticate to the Strava API
	 */
	protected StravaServiceImpl(final Class<T> class1, final Token token) {
		this.token = token;
		this.restService = Retrofit.retrofit(class1, token);
	}

	/**
	 * <p>
	 * Work out if the access token is valid (i.e. has not been revoked)
	 * </p>
	 * 
	 * @return <code>true</code> if the token can be used to get the authenticated athlete, <code>false</code> otherwise
	 */
	protected boolean accessTokenIsValid() {
		try {
			AthleteServicesImpl.implementation(this.token).getAuthenticatedAthlete();
			return true;
		} catch (UnauthorizedException e) {
			return false;
		}
	}
	
	protected final Token getToken() {
		return this.token;
	}
}
