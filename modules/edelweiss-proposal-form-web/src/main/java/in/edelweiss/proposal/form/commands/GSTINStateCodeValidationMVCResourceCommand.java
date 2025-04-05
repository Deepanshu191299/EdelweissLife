package in.edelweiss.proposal.form.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Map;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.common.util.EdelweissAPIUtil;
import in.edelweiss.proposal.form.constants.ProposalFormPortletKeys;
import in.edelweiss.proposal.form.pf.model.ProposalJourney;
import in.edelweiss.system.configurations.EdelweissGSTINOAuthConfiguration;

@Component(property = { "javax.portlet.name=" + ProposalFormPortletKeys.PROPOSALFORM,
"mvc.command.name=/gstin/state-code/validation" }, service = MVCResourceCommand.class)
public class GSTINStateCodeValidationMVCResourceCommand extends BaseMVCResourceCommand{
	private static final Log logger = LogFactoryUtil.getLog(GSTINStateCodeValidationMVCResourceCommand.class);
	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
		logger.debug("Inside the GSTIN resource command");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		
		String gstinStateCodeValidationURL = StringPool.BLANK;

		String appId = ParamUtil.getString(resourceRequest, "appId");
		String stateName = ParamUtil.getString(resourceRequest, "state");
		String gstinNo = ParamUtil.getString(resourceRequest,"gstinNo" );
		gstinNo = gstinNo.substring(0,2);
		logger.debug(appId +"...." + stateName +"...."+ gstinNo);
		
		String pfURL = ConfigurationUtil.getProposalFormAPIURLConfiguration().getPFDetailsURL()
				+ "?applicationNumber=" + appId;
		String pfResponse = EdelweissAPIUtil.callPJGetAPI(pfURL);
		logger.debug("pf response....."+ pfResponse);
		ProposalJourney proposalJourney = objectMapper.readValue(pfResponse, ProposalJourney.class);
		logger.debug("proposaljourney::"+ proposalJourney);
		
		EdelweissGSTINOAuthConfiguration edelweissGSTINOAuthConfiguration =  ConfigurationUtil.getEdelweissGSTINOAuthConfiguration();
		String accessToken = EdelweissAPIUtil.getD365OAuthToken();
		String stateFlag = "false";
		
		if (Validator.isNotNull(accessToken)) {
			gstinStateCodeValidationURL = edelweissGSTINOAuthConfiguration.getGstinStateCodeAPIValidation();
			Map<String, Object> drcCode = EdelweissAPIUtil.validateGstinStateCodeAPI(gstinStateCodeValidationURL,accessToken,stateName);
			logger.debug("drcCode response" + drcCode);
			
			if(Validator.isNotNull(drcCode)) {
				logger.debug("inside the drcCode notnull:::::");
				int status = (Integer)drcCode.get("status");
				logger.debug("status code::"+ status);
				if(status == 200) {
					JSONObject jsonObject = (JSONObject)drcCode.get("jsonObject");
					logger.debug("JSON Object" + jsonObject);
					if(Validator.isNotNull(jsonObject)) {
						JSONArray jsonArray = jsonObject.getJSONArray("value");
						logger.debug("JSON ARRAY"+ jsonArray);
						JSONObject firstElement = jsonArray.getJSONObject(0);
						logger.debug("First Element" + firstElement);
						 String etliDrccodeValue = firstElement.getString("etli_drccode");
						 logger.debug("etliDrcCode"+ etliDrccodeValue);
						 
						 if(gstinNo.equalsIgnoreCase(etliDrccodeValue)) {
								stateFlag = "true";
								
							}						
						}
					}
				}
			}
		logger.debug("CheckStateFlag123 : "+stateFlag);
		resourceResponse.getWriter().write(stateFlag.toString());
	}
}
