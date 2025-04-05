package edelweiss.csb.integration.web.actions;

import com.edelweiss.http.core.api.ETIPCoreAPI;

import com.liferay.portal.kernel.encryptor.EncryptorUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.net.URLEncoder;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import edelweiss.csb.integration.web.constants.EdelweissCsbIntegrationWebPortletKeys;
import edelweiss.csb.integration.web.util.CsbIntegrationUtil;
import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.system.configurations.EdelweissLRBasicAuthConfiguration;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.util.EdelweissConfigurationUtil;
/*import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;*/

import static in.edelweiss.tokio.constants.EdelweissObjectConstants.CSB_INTEGRATION_BASE_URL;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.GET_CSB_INTEGRATION_DETAILS_URL;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.CSB_ACCOUNTNO;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.CSB_EMPLOYEEID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.CSB_EMPLOYEENAME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.CSB_CLIENTNAME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.CLIENT_DOB;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.CUSTOMER_ACCOUNTNO;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.CUSTOMER_OCCUPATION;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.BRANCH_CODE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.BRANCH_NAME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PIN_CODE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.NEO_APPLICATIONNO;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.NOMINEE_NAME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.CUSTOMER_RELATION;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.CUSTOMER_MOBILE_NO;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.EMAIL_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PREMIUM;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.MEDICATION;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.TREATMENT;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.COVID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.RISK_COVER_OPTED;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.CUSTOMER_GENDER;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.CSB_INTEGRATION_STATUS;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.AGE;


@Component(
immediate = true, 
property = { "javax.portlet.name=" + EdelweissCsbIntegrationWebPortletKeys.EDELWEISSCSBINTEGRATIONWEB,
		"mvc.command.name="
				+ EdelweissCsbIntegrationWebPortletKeys.SUBMIT_CSB_INTEGRATION_MVC_ACTION_COMMAND }, 
service = MVCActionCommand.class)

public class SubmitCsbIntegrationActionCommand extends BaseMVCActionCommand {

	@Reference
	ETIPCoreAPI etipCoreApi;
	@Reference
	private EdelweissTokioCommonApi edelweissTokioCommonApi;
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		// TODO Auto-generated method stub
		logger.info("inside doProcessAction123::");
	
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil
				.getEdelweissLRBasicAuthConfiguration();
		String liferayUserName = edelweissLRBasicAuthConfiguration.getLRUsername();
		String liferayPassword = edelweissLRBasicAuthConfiguration.getLRPassword();
		logger.info("liferayUserName:::::::"+ liferayUserName);
		logger.info("liferayPassword::::::::"+ liferayPassword);
		
		String csbAccountNo = ParamUtil.getString(actionRequest, "csbAccountNo");
		String csbEmployeeId = ParamUtil.getString(actionRequest, "csbEmployeeId");
		String csbEmployeeName = ParamUtil.getString(actionRequest, "csbEmployeeName");
		String clientName = ParamUtil.getString(actionRequest, "clientName");
		String dob = ParamUtil.getString(actionRequest, "dob");
		String customerId = ParamUtil.getString(actionRequest, "customerId");
		String occupation = ParamUtil.getString(actionRequest, "occupation");
		String branchName = ParamUtil.getString(actionRequest, "branchName");
		String branchCode = ParamUtil.getString(actionRequest, "branchCode");
		String pinCode = ParamUtil.getString(actionRequest, "pinCode");
		String neoApplicatioNumber = ParamUtil.getString(actionRequest, "neoApplicatioNumber");
		String nomineeName = ParamUtil.getString(actionRequest, "nomineeName");
		String relation = ParamUtil.getString(actionRequest, "relation");
		String mobileNumber = ParamUtil.getString(actionRequest, "mobileNumber");
		String emailId = ParamUtil.getString(actionRequest, "emailId");
		String riskcovered = ParamUtil.getString(actionRequest, "riskcovered");
		String premiumAmount = "";
		String decease = ParamUtil.getString(actionRequest, "decease");
		String treatment = ParamUtil.getString(actionRequest, "treatment");
		String testedPositive = ParamUtil.getString(actionRequest, "testedPositive");
		String gender = ParamUtil.getString(actionRequest, "gender");
		
		int ageValue = calculateAge(dob);

		logger.info("ageValue "+ageValue);
		logger.info("csbAccountNo "+csbAccountNo);
		logger.info("csbEmployeeId "+csbEmployeeId);
		logger.info("csbEmployeeName "+csbEmployeeName);
		logger.info("clientName "+clientName);
		logger.info("age "+dob);
		logger.info("customerId "+customerId);
		logger.info("occupation "+occupation);
		logger.info("branchName "+branchName);
		logger.info("branchCode "+branchCode);
		logger.info("pinCode "+pinCode);
		logger.info("neoApplicatioNumber "+neoApplicatioNumber);
		logger.info("nomineeName "+nomineeName);
		logger.info("relation "+relation);
		logger.info("mobileNumber "+mobileNumber);
		logger.info("emailId "+emailId);
		logger.info("riskcovered "+riskcovered);
		logger.info("decease "+decease);
		logger.info("treatment "+treatment);
		logger.info("testedPositive "+testedPositive);
		logger.info("gender"+ gender);
		
		Map<String, String> mapformMap = new HashMap<>();
		
		mapformMap.put(CSB_ACCOUNTNO, csbAccountNo);
		mapformMap.put(CSB_EMPLOYEEID, csbEmployeeId);
		mapformMap.put(CSB_EMPLOYEENAME, csbEmployeeName);
		mapformMap.put(CSB_CLIENTNAME, clientName);
		mapformMap.put(CLIENT_DOB, dob);
		mapformMap.put(CUSTOMER_ACCOUNTNO, customerId);
		mapformMap.put(CUSTOMER_OCCUPATION, occupation);
		mapformMap.put(BRANCH_NAME, branchName);
		mapformMap.put(BRANCH_CODE, branchCode);
		mapformMap.put(PIN_CODE, pinCode);
		mapformMap.put(NEO_APPLICATIONNO, neoApplicatioNumber);
		mapformMap.put(NOMINEE_NAME, nomineeName);
		mapformMap.put(CUSTOMER_RELATION, relation);
		mapformMap.put(CUSTOMER_MOBILE_NO, mobileNumber);
		mapformMap.put(EMAIL_ID, emailId);
		mapformMap.put(RISK_COVER_OPTED, riskcovered);
		mapformMap.put(MEDICATION, decease);
		mapformMap.put(TREATMENT, treatment);
		mapformMap.put(COVID, testedPositive);
		mapformMap.put(CSB_INTEGRATION_STATUS, "Consent Pending");
		mapformMap.put(AGE, String.valueOf(ageValue));
		
		
		if(Validator.isNotNull(riskcovered) &&  riskcovered.equals("5")){
			premiumAmount = "2349";
			mapformMap.put(PREMIUM, premiumAmount);
		}else {
			premiumAmount = "1409";
			mapformMap.put(PREMIUM, premiumAmount);
		}
		
		
		mapformMap.put(CUSTOMER_GENDER, gender);
			
		String csbIntegrationUrl = themeDisplay.getPortalURL() + CSB_INTEGRATION_BASE_URL;
		logger.info("csbIntergration URL::::"+ csbIntegrationUrl);
		
		JSONObject requestJson = JSONFactoryUtil.createJSONObject(mapformMap);
		logger.info("requestBody::::::::" +requestJson);
		
		JSONObject lrCsbResponse = etipCoreApi.callPostAPI(requestJson,csbIntegrationUrl, false, liferayUserName, liferayPassword);
		//logger.info("csbIntegration >>> doProcessAction >>> Liferay response  ::: "+ lrCsbResponse);
		
		String objectCsbIb = lrCsbResponse.getString("id");
		String clientName1 = lrCsbResponse.getString("clientName");
		String mobileNo = lrCsbResponse.getString("mobileNo");
		String emailAddress = lrCsbResponse.getString("emailId");
		String locationURL = ConfigurationUtil.getProposalFormAPIURLConfiguration().getSendOTPURL();
		
		//For generating the Encoded URL
		String key1 = "ccDL0pxQmjqZEh4HRF3+sQ==";
		logger.info("key1  " + key1 );
		String encryptedText = EncryptorUtil.encrypt(EncryptorUtil.deserializeKey(key1), objectCsbIb);
		encryptedText = URLEncoder.encode(encryptedText,"UTF-8");
		logger.info("Encrypted Text: " + encryptedText);
		
		String previewURL = "https://uat.edelweisslife.in/csb-integration-page?appId="+encryptedText;
		
		Map<String, String> tinyURLRequest = new HashMap<>();
		tinyURLRequest.put("url", previewURL);
		String tinyURLEndpoint = "https://devapi.edelweissfin.com/tinyurl";
		String tinyURLKey = "fEWWLzicH64UEhZne9GH724zSGvDmrzB3yk7KRH0";
		String tinyURLUsername = "13sej7smuiuiov55ecsntgvvp7";
		String tinyURLPassword = "nd51isdlla2bol8tkr62aorte21mug1nchl6c0s0jgij2tojtfq";
		
		
		JSONObject tinyURLRequestJson = JSONFactoryUtil.createJSONObject(tinyURLRequest);
		logger.info("tinyURLRequestJson123 ::::"+ tinyURLRequestJson);
		JSONObject authorizations = JSONFactoryUtil.createJSONObject(etipCoreApi.generateOauthToken("https://devapi.edelweisslife.in/oauth2/token", tinyURLUsername, tinyURLPassword));
		logger.info("tinyURLAuthorization::::"+ authorizations);
		String authorization=authorizations.getString("access_token");
		logger.info("tinyURLAuthorization AccessToken::::"+ authorization);
		
		JSONObject generateTinyUrlResponse = etipCoreApi.callPostAPI(tinyURLRequestJson,tinyURLEndpoint, true, tinyURLKey, authorization);
		logger.info("<<<<<<<<generateTinyUrlResponse:::::::"+ generateTinyUrlResponse.getString("tinyUrl")); 
		String tinyURL ="https://"+generateTinyUrlResponse.getString("tinyUrl");
		
		CsbIntegrationUtil.sendCsbOtp(objectCsbIb, clientName1, "N", "CSBOTP", mobileNo, emailAddress,tinyURL,locationURL);
				
		sendRedirect(actionRequest, actionResponse, previewURL);
		
	}
	
	public int calculateAge(String dateOfBirth) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date dob = null;
		try {
			dob = formatter.parse(dateOfBirth);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Calendar birthDate = Calendar.getInstance();
        birthDate.setTime(dob);
        Calendar currentDate = Calendar.getInstance();
        int age = currentDate.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
        // If birth date is greater than today's date (after today's day and month), then decrement age by 1
        if (currentDate.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        return age;
    }

	private Log logger = LogFactoryUtil.getLog(this.getClass());

}

