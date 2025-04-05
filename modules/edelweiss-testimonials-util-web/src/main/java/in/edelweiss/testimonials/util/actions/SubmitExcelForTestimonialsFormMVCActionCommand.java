package in.edelweiss.testimonials.util.actions;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Date;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

import in.edelweiss.common.contants.DateConstants;
import in.edelweiss.common.contants.ParameterConstants;
import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.common.util.DateFormatterUtil;
import in.edelweiss.system.configurations.TestimonialConfiguration;
import in.edelweiss.testimonials.util.constants.TestimonialsUtilPortletKeys;
import in.edelweiss.testimonials.utility.TestimonialsUtil;

/**
 * 
 * @author Abhijit AA
 *
 * This Class is used to Submit the Excel sheet to call batch upload for testimonials.
 */

@Component(immediate = true,
property = {
		"javax.portlet.name=" + TestimonialsUtilPortletKeys.TESTIMONIALSUTIL,
		"mvc.command.name=" + TestimonialsUtilPortletKeys.SUBMIT_EXCEL_FOR_TESTIMONIALS_FORM_MVC_ACTION_COMMAND 
},
service = MVCActionCommand.class
		)
public class SubmitExcelForTestimonialsFormMVCActionCommand extends BaseMVCActionCommand {

	/**
	 * This method is used to Submit the Excel sheet to call batch upload for testimonials.
	 * 
	 * @param actionRequest
	 * @param actionResponse
	 * @return void
	 */
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		TestimonialConfiguration testimonialConfiguration = ConfigurationUtil.getTestimonialConfiguration();
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		JSONObject formSubmissionReponse = JSONFactoryUtil.createJSONObject();
		
		boolean isFormSubmittedSuccessfully = false;
		String responseData = StringPool.BLANK;
		int responseId = 0;

		//User Submitted Form Details.
		String excelJSONStr = ParamUtil.getString(actionRequest, "excelJSONArray");
		JSONArray excelJSONArray = JSONFactoryUtil.createJSONArray(excelJSONStr);
		JSONArray contentJSONArray = createTestimonialJSONArray(excelJSONArray, themeDisplay.getUserId(), testimonialConfiguration);
		try 
		{
			if(Validator.isNotNull(contentJSONArray)) {
				
				Map<String, Object> responseMap = TestimonialsUtil.batchPostTestimonials(themeDisplay.getPortalURL(), 
						testimonialConfiguration.getStructureFolderId(), contentJSONArray.toString());

				if(Validator.isNotNull(responseMap)) {
					int status = (Integer)responseMap.get("status");
					JSONObject jsonObject = (JSONObject)responseMap.get("jsonObject");
					
					if(status == 202) {
						if(Validator.isNotNull(jsonObject) && jsonObject.has("executeStatus") && jsonObject.has("id")) {
							responseData = jsonObject.getString("executeStatus");
							responseId = jsonObject.getInt("id");
							isFormSubmittedSuccessfully = true;
						}
					}else if(status == 403) {
						responseData = "Forbidden";
					}else if(status == 400 && Validator.isNotNull(jsonObject) && jsonObject.has("status")) {
						responseData = jsonObject.getString("status");
					}
				}
			}
			
			formSubmissionReponse.put("isFormSubmittedSuccessfully", isFormSubmittedSuccessfully);
			formSubmissionReponse.put("responseData", responseData);
			formSubmissionReponse.put("responseId", responseId);
			
		}catch (Exception exception) {
			formSubmissionReponse.put("internalError", true);
			logger.error("Exception occured while submitting excel for testimonials form : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		
		hideDefaultSuccessMessage(actionRequest);
		JSONPortletResponseUtil.writeJSON(actionRequest, actionResponse, formSubmissionReponse);
	}
	
	/**
	 * This method is used to Fetch Through all the JSON Array Objects & Pass it to create Json and add it to array.
	 * 
	 * @param excelJSONArray
	 * @param userId
	 * @param testimonialConfiguration
	 * @return JSONArray
	 */
	private JSONArray createTestimonialJSONArray(JSONArray excelJSONArray, long userId, TestimonialConfiguration testimonialConfiguration) {
		
		JSONArray batchJSONArray = JSONFactoryUtil.createJSONArray();
		
		try {
			if(Validator.isNotNull(excelJSONArray) && excelJSONArray.length() > 0) {
				for(int i = 0; i < excelJSONArray.length(); i++) {
					JSONObject excelJsonObject = (JSONObject) excelJSONArray.get(i);
					if(Validator.isNotNull(excelJsonObject) && excelJsonObject.length() > 3) {
						batchJSONArray.put(createJSONObject(excelJsonObject, userId, testimonialConfiguration));
					}
				}
			}
		}catch(Exception exception) {
			logger.error("Exception occured while traversing through JSONArray : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		
		return batchJSONArray;
	}

	/**
	 * This method is used to Fetch Through all the JSON details & put it to particular place to create Json Object for Structure Content API.
	 * 
	 * @param excelObject
	 * @param userId
	 * @param testimonialConfiguration
	 * @return JSONObject
	 */
	protected JSONObject createJSONObject(JSONObject excelObject, long userId, TestimonialConfiguration testimonialConfiguration) {

		JSONObject contentJSON = JSONFactoryUtil.createJSONObject();
		
		try {
			
			JSONArray contentFieldsArray = JSONFactoryUtil.createJSONArray();

			//Slider JSON
			JSONObject sliderContentFieldValueJson = JSONFactoryUtil.createJSONObject();
			sliderContentFieldValueJson.put("data","No");

			JSONObject sliderContentFieldJson = JSONFactoryUtil.createJSONObject();
			sliderContentFieldJson.put("contentFieldValue", sliderContentFieldValueJson);
			sliderContentFieldJson.put("name", "doYouWantInSlider");
			sliderContentFieldJson.put("nestedContentFields", JSONFactoryUtil.createJSONArray());

			contentFieldsArray.put(sliderContentFieldJson);

			//Image JSON
			JSONObject imageFieldJson = JSONFactoryUtil.createJSONObject();
			imageFieldJson.put("description", "ETLICustomer");
			
			if(excelObject.getString("customer_profile").contains("female")) {
				imageFieldJson.put("id", testimonialConfiguration.getFemaleImageId());
			}else if(excelObject.getString("customer_profile").contains("male")) {
				imageFieldJson.put("id", testimonialConfiguration.getMaleImageId());	
			}else {
				imageFieldJson.put("id", testimonialConfiguration.getDefaultImageId());
			}
			
			imageFieldJson.put("title", "Person Image");

			JSONObject imageContentFieldValueJson = JSONFactoryUtil.createJSONObject();
			imageContentFieldValueJson.put("image", imageFieldJson);

			JSONObject imageContentFieldJson = JSONFactoryUtil.createJSONObject();
			imageContentFieldJson.put("contentFieldValue", imageContentFieldValueJson);
			imageContentFieldJson.put("name", "personImage");
			imageContentFieldJson.put("nestedContentFields", JSONFactoryUtil.createJSONArray());
			
			contentFieldsArray.put(imageContentFieldJson);
			
			//Designation JSON
			JSONObject designationContentFieldValueJson = JSONFactoryUtil.createJSONObject();
			designationContentFieldValueJson.put("data", excelObject.getString("vcposition"));

			JSONObject designationContentFieldJson = JSONFactoryUtil.createJSONObject();
			designationContentFieldJson.put("contentFieldValue", designationContentFieldValueJson);
			designationContentFieldJson.put("name", "designation");
			designationContentFieldJson.put("nestedContentFields", JSONFactoryUtil.createJSONArray());

			contentFieldsArray.put(designationContentFieldJson);

			//Short Note JSON
			JSONObject shortNoteContentFieldValueJson = JSONFactoryUtil.createJSONObject();
			shortNoteContentFieldValueJson.put("data", excelObject.getString("vcdesc"));

			JSONObject shortNoteContentFieldJson = JSONFactoryUtil.createJSONObject();
			shortNoteContentFieldJson.put("contentFieldValue", shortNoteContentFieldValueJson);
			shortNoteContentFieldJson.put("name", "shortNote");
			shortNoteContentFieldJson.put("nestedContentFields", JSONFactoryUtil.createJSONArray());
			
			contentFieldsArray.put(shortNoteContentFieldJson);
			
			JSONObject creatorJson = JSONFactoryUtil.createJSONObject();
			creatorJson.put("id", userId);

			String titleName = excelObject.getString("vcname").trim().replaceAll("\\s+", " ");
			
			contentJSON.put("contentFields", contentFieldsArray);
			contentJSON.put("contentStructureId", testimonialConfiguration.getContentStructureId());
			contentJSON.put("creator", creatorJson);
			contentJSON.put("datePublished", DateFormatterUtil.parseDate(new Date(), DateConstants.HYPHEN_YYYY_MM_DD_T_HH_MM_SS_SSS_Z, "IST"));
			contentJSON.put("friendlyUrlPath", titleName.toLowerCase().replace(" ", "-"));
			contentJSON.put("title", titleName);
			contentJSON.put("viewableBy", "Anyone");

		}catch(Exception exception) {
			logger.error("Exception occured while Creating JSONObject : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		
		return contentJSON;
	}

	private Log logger = LogFactoryUtil.getLog(this.getClass());

}