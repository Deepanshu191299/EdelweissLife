package in.edelweiss.testimonials.util.portlet;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import in.edelweiss.common.contants.ParameterConstants;
import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.testimonials.util.constants.TestimonialsUtilPortletKeys;
import in.edelweiss.testimonials.utility.TestimonialsUtil;

/**
 * This portlet is used to render the Testimonial input with document extensions
 * This portlet is used to give output to check status resource.
 * 
 * @author Abhijit AA
 */
@Component(
		immediate = true,
		property = {
				"com.liferay.portlet.display-category=category.sample",
				"com.liferay.portlet.header-portlet-css=/css/main.css",
				"com.liferay.portlet.instanceable=true",
				"javax.portlet.display-name=TestimonialsUtil",
				"javax.portlet.init-param.template-path=/",
				"javax.portlet.init-param.view-template=/jsp/testimonial-excel.jsp",
				"javax.portlet.name=" + TestimonialsUtilPortletKeys.TESTIMONIALSUTIL,
				"javax.portlet.resource-bundle=content.Language",
				"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
		)
public class TestimonialsUtilPortlet extends MVCPortlet {

	/**
	 * This method is used to render the Testimonial input with document extensions
	 * 
	 * @param renderRequest
	 * @param renderResponse
	 * 
	 * @return void
	 * 
	 */
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		String documentExtensions = ConfigurationUtil.getTestimonialConfiguration().getTestimonialDocumentExtension();
		renderRequest.setAttribute("documentExtensions", documentExtensions);

		super.render(renderRequest, renderResponse);
	}
	
	/**
	 * This method is used to call Batch Engine API for the Status of task.
	 * 
	 * @param resourceRequest
	 * @param resourceResponse
	 * 
	 * @return void
	 * 
	 */
	@Override
		public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
				throws IOException, PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		JSONObject documenUploadReponse = JSONFactoryUtil.createJSONObject();
		String responseData = StringPool.BLANK;
		int responseId = 0;
		boolean isFormSubmittedSuccessfully = false;
		
		int taskId = ParamUtil.getInteger(resourceRequest, "taskId");
		
		try {

			if (Validator.isNotNull(taskId)) {
				
				Map<String, Object> responseMap = TestimonialsUtil.getTaskStatus(themeDisplay.getPortalURL(), taskId);

				if(Validator.isNotNull(responseMap)) {
					int status = (Integer)responseMap.get("status");
					JSONObject jsonObject = JSONFactoryUtil.createJSONObject(responseMap.get("content").toString());
					if(status == 200) {
						if(Validator.isNotNull(jsonObject) && jsonObject.has("executeStatus") && jsonObject.has("id")) {
							responseData = jsonObject.getString("executeStatus");
							responseId = jsonObject.getInt("id");
							isFormSubmittedSuccessfully = true;
						}
					}else if(status == 403) {
						responseData = "Forbidden";
					}else if(status == 400 && Validator.isNotNull(jsonObject) && jsonObject.has("status")) {
						responseData = jsonObject.getString("status");
					}else if(status == 404 && Validator.isNotNull(jsonObject) && jsonObject.has("status")) {
						responseData = jsonObject.getString("title");
					}
				}
			}

			documenUploadReponse.put("isFormSubmittedSuccessfully", isFormSubmittedSuccessfully);
			documenUploadReponse.put("responseData", responseData);
			documenUploadReponse.put("responseId", responseId);

		} catch (Exception exception) {
			documenUploadReponse.put("internalError", true);
			logger.error("Error while calling Batch Engine Task Status Resource Command : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		
		resourceResponse.getWriter().write(documenUploadReponse.toString());
	}
		
	private Log logger = LogFactoryUtil.getLog(this.getClass());
}