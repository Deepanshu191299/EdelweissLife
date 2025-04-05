package in.edelweiss.rest.api.builder.internal.resource.v1_0;

import static in.edelweiss.tokio.constants.EdelweissObjectConstants.INDIAN;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.RESPONSE_DATA;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.STATUS;

import in.edelweiss.rest.api.builder.dto.v1_0.LMS;
import in.edelweiss.rest.api.builder.resource.v1_0.LMSResource;
import in.edelweiss.system.configurations.EdelweissGenerateLeadConfiguration;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.model.InterestSessions;
import in.edelweiss.tokio.common.model.LMSRequest;
import in.edelweiss.tokio.common.model.Lmslead;
import in.edelweiss.tokio.common.model.Quote;
import in.edelweiss.tokio.common.util.EdelweissConfigurationUtil;

import com.edelweiss.http.core.api.ETIPCoreAPI;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author pramod.dk
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/lms.properties",
	scope = ServiceScope.PROTOTYPE, service = LMSResource.class
)
public class LMSResourceImpl extends BaseLMSResourceImpl {
	
	private static final Log _log = LogFactoryUtil.getLog(LMSResourceImpl.class);
	
	@Override
	public Response addLeadDetails(LMS lms) throws Exception {
		
		_log.info("============= addLeadDetails API called ==============");
		
		_log.info("Request Body ========= " + lms);

		JSONObject apiResponse = JSONFactoryUtil.createJSONObject();
		JSONObject data = JSONFactoryUtil.createJSONObject();
		Response.ResponseBuilder responseBuilder = Response.ok();
		
		String name = GetterUtil.getString(lms.getLead().getName(),StringPool.BLANK);
		String phone = GetterUtil.getString(lms.getLead().getPhone(),StringPool.BLANK);
		String email = GetterUtil.getString(lms.getLead().getEmail(),StringPool.BLANK);
		String channelId = GetterUtil.getString(lms.getLead().getChannelId(),StringPool.BLANK);
		String campaingId = GetterUtil.getString(lms.getLead().getCampaingId(),StringPool.BLANK);
		String vendorId = GetterUtil.getString(lms.getLead().getVendorId(),StringPool.BLANK);
		String source = GetterUtil.getString(lms.getLead().getSource(),StringPool.BLANK);
		String leadFormType = GetterUtil.getString(lms.getLead().getLeadFormType(),StringPool.BLANK);
		
		String mobileRegex = "\\d{10}";
		
		if(phone.equals(StringPool.BLANK)) {
			apiResponse.put("status", false);
			apiResponse.put("message", "Phone number is required");
			return responseBuilder.entity(apiResponse).build();
		}
		
		if(!phone.matches(mobileRegex)) {
			apiResponse.put("status", false);
			apiResponse.put("message", "Phone number is not valid");
			return responseBuilder.entity(apiResponse).build();
		}
		

		try {
			EdelweissGenerateLeadConfiguration edelweissGenerateLeadConfiguration = EdelweissConfigurationUtil
					.getEdelweissGenerateLeadConfiguration();
			String authorization = edelweissTokioCommonApi.getOAuthToken(
					edelweissGenerateLeadConfiguration.getOAuth2QJURL(),
					edelweissGenerateLeadConfiguration.getOAuth2QJUsername(),
					edelweissGenerateLeadConfiguration.getOAuth2QJPassword());

			Lmslead lmsLead = new Lmslead();
			lmsLead.setPhone(phone);
			lmsLead.setName(name);
			lmsLead.setWebUrl("");
			lmsLead.setSource(source);
			lmsLead.setSiteSection("");
			lmsLead.setLeadFormType(leadFormType);
			lmsLead.setcId(channelId);
			lmsLead.setCpId(campaingId);
			lmsLead.setvId(vendorId);
			lmsLead.setEmail(email);
			lmsLead.setDob("");
			lmsLead.setGender("");
			lmsLead.setNationality(INDIAN);
			lmsLead.setProduct("");
			lmsLead.setVisitorId("");
			lmsLead.setOccupation("");

			// Set UTM
			lmsLead.setUtmSource("");
			lmsLead.setUtmMedium("");
			lmsLead.setUtmCampaign("");
			lmsLead.setUtmTerm("");
			lmsLead.setGclid("");
			lmsLead.setUtmContent("");
			lmsLead.setuTMPlacement("");
			lmsLead.setUtmCreative("");
			lmsLead.setAdgroupId("");
			lmsLead.setDevice("");
			lmsLead.setCampaignId("");

			InterestSessions interestSessions = new InterestSessions();
			interestSessions.setRecommendedObjectiveCategory("");
			interestSessions.setRecommendedObjective("");
			interestSessions.setRecommendedAnnualIncome("");
			interestSessions.setRecommendedProduct("");
			interestSessions.setRecommendedProduct("");
			interestSessions.setPersona("");
			interestSessions.setLeadFormProductCode("");
			interestSessions.setStage("");
			interestSessions.setSiteSection("");
			interestSessions.setLeadFormProductCategory("");

			Quote quote = new Quote();
			quote.setPayoutOptions("");
			quote.setStage("");
			
			lmsLead.setQuote(quote);
			lmsLead.setInterestSessions(interestSessions);

			LMSRequest lmsRequest = new LMSRequest();
			lmsRequest.setLmslead(lmsLead);
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(lmsRequest);
			_log.debug("Lead Capture WEB >>>  generateLead >>> request Body ::: " + jsonString);
			JSONObject requestJson = JSONFactoryUtil.createJSONObject(jsonString);
			data = etipCoreAPI.callPostAPI(requestJson,
					edelweissGenerateLeadConfiguration.getGenerateLeadQJURL(), true,
					edelweissGenerateLeadConfiguration.getGenerateLeadQJXAPIKEY(), authorization);
			_log.debug("EdelweissLMSActionCommand >>>  generateLead  inside the Lead Capture >>> responseJSON ::: "
					+ data);

			if (Validator.isNotNull(data) && data.getBoolean(STATUS)) {
				
				if (Validator.isNotNull(data.getJSONObject(RESPONSE_DATA))) {
					apiResponse.put("data", data);
					apiResponse.put("status", true);
					apiResponse.put("message", "");
				}
			} else {
				_log.error("Json Response is null -- Api Error");				
				apiResponse.put("status", false);
				apiResponse.put("message", "Internal server error");
			}
		} catch (Exception e) {
			_log.error("Error :::  "+ e);
			apiResponse.put("status", false);
			apiResponse.put("message", "Internal server error");
		}
		
		log.info("apiResponse : "+apiResponse);
		return responseBuilder.entity(apiResponse).build();
	}
	
	@Reference
	private ETIPCoreAPI etipCoreAPI;

	@Reference
	private EdelweissTokioCommonApi edelweissTokioCommonApi;
	
	private static Log log = LogFactoryUtil.getLog(LMSResourceImpl.class);
}