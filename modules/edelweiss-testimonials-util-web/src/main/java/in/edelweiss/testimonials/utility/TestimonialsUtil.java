package in.edelweiss.testimonials.utility;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.HashMap;
import java.util.Map;

import in.edelweiss.common.contants.EdelweissAPIConstants;
import in.edelweiss.common.contants.ParameterConstants;
import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.common.util.EdelweissAPIUtil;
import in.edelweiss.system.configurations.TestimonialConfiguration;


/**
 * @author Abhijit AA
 * 
 * The class will contain the API Implementation & Some API Related Constants Methods.
 */
public class TestimonialsUtil {

	/**
	 * private constructor to hide the implicit public one
	 * 
	 */
	private TestimonialsUtil() {

	}

	/**
	 *This method is used to call the Post API of Structured Content to push the batch of testimonials.
	 *
	 *@param detailsJSON
	 *
	 *@return Map<String, Object>
	 */
	public static Map<String, Object> batchPostTestimonials(String baseURL, long structureFolderId, String detailsJSON) {

		Map<String, Object> responseMap = new HashMap<>();

		try {

			TestimonialConfiguration testimonialConfiguration = ConfigurationUtil.getTestimonialConfiguration();
			String endpointURL = baseURL + "/o/headless-delivery/v1.0/structured-content-folders/" + structureFolderId + "/structured-contents/batch";
			String authorizationToken = EdelweissAPIUtil.getEncodedAuthorizationCredentials(testimonialConfiguration.getLRUsername(), testimonialConfiguration.getLRPassword());
			responseMap = EdelweissAPIUtil.callPostAPI(endpointURL, 
					EdelweissAPIConstants.APPLICATION_JSON, false, authorizationToken, StringPool.BLANK, detailsJSON);

		}catch(Exception exception) {
			logger.error("Error while calling Batch Post Testimonials API : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		return responseMap;
	}

	/**
	 *This method is used to call the Get API of Batch Engine to get the status of batch task.
	 *
	 *@param baseURL
	 *@param taskId
	 *
	 *@return Map<String, Object>
	 */
	public static Map<String, Object> getTaskStatus(String baseURL, int taskId) {

		Map<String, Object> responseMap = new HashMap<>();

		try {

			TestimonialConfiguration testimonialConfiguration = ConfigurationUtil.getTestimonialConfiguration();
			String endpointURL = baseURL + "/o/headless-batch-engine/v1.0/import-task/" + taskId;
			String authorizationToken = EdelweissAPIUtil.getEncodedAuthorizationCredentials(testimonialConfiguration.getLRUsername(), testimonialConfiguration.getLRPassword());
			responseMap = EdelweissAPIUtil.callGetAPI(endpointURL, 
					EdelweissAPIConstants.APPLICATION_JSON, false, authorizationToken, StringPool.BLANK, null);

		}catch(Exception exception) {
			logger.error("Error while calling Batch Get Status Testimonials API : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		return responseMap;
	}

	private static Log logger = LogFactoryUtil.getLog(TestimonialsUtil.class);
}
