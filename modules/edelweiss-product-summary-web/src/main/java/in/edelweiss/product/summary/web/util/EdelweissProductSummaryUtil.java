package in.edelweiss.product.summary.web.util;

import static in.edelweiss.tokio.constants.EdelweissObjectConstants.RCUSTOMERFAMILYDETAILSRELCCUSTOMERENQUIRYID;

import com.edelweiss.http.core.api.ETIPCoreAPI;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.portlet.PortletRequest;

import org.osgi.service.component.annotations.Reference;

import in.edelweiss.product.summary.web.constants.EdelweissProductSummaryWebPortletKeys;
import in.edelweiss.tokio.common.model.Application;
import in.edelweiss.tokio.common.model.CustomerFamilyDetailsRel;
import in.edelweiss.tokio.common.model.GenerateApplication;
import in.edelweiss.tokio.common.model.GeneratePolicyNo;
import in.edelweiss.tokio.common.model.LMSApplication;
import in.edelweiss.tokio.common.model.LiferayResponseMessage;
import in.edelweiss.tokio.common.model.Quote;
import in.edelweiss.tokio.common.model.UpdateLMSLeadRequest;
import in.edelweiss.tokio.common.model.UpdateLead;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;
import in.edelweiss.tokio.constants.EdelweissObjectConstants;
public class EdelweissProductSummaryUtil {
	
    @Reference
	 ETIPCoreAPI etipCoreAPI;
    private static final Log log = LogFactoryUtil.getLog(EdelweissProductSummaryUtil.class);
    public static String getPolicyNoRequestBody(ObjectMapper objectMapper, String ipAddress, String leadId, String quotationId) throws JsonProcessingException {
		GeneratePolicyNo generatePolicy = new GeneratePolicyNo();
		generatePolicy.setQuotationID(quotationId);
		generatePolicy.setLeadID(leadId);
		generatePolicy.setSrc("EdelweissWebsales");
		generatePolicy.setAPISource("");
		generatePolicy.setUTMSource("UTM_Source value");
		generatePolicy.setPayFirstYN("Y");
		generatePolicy.setSourceFrom("Website");
		generatePolicy.setIPAddress(ipAddress);
		generatePolicy.setApplicationStatus("1");
		return objectMapper.writeValueAsString(generatePolicy);
	}
    
    public static String getLeadMetaDataURL(String leadId) {
		StringBuilder leadURL = new StringBuilder();
		leadURL.append(EdelweissObjectConstants.LEAD_ID);
		leadURL.append(StringPool.SPACE);
		leadURL.append("eq");
		leadURL.append(StringPool.SPACE);
		leadURL.append("'" + leadId + "'");
		return URLEncoder.encode(leadURL.toString(), StandardCharsets.UTF_8) .replace(" ", "%20");
	}
      public static String getPolicyApplicationRequestBody(ObjectMapper objectMapper, String lmsId,long applicationNumber,String phoneNo,String policyNumber, String quotationID, String lMSQuoteId) throws JsonProcessingException {
    	  GenerateApplication generateApplication = new GenerateApplication();
    	  LMSApplication lmsApplication = new LMSApplication();
    	  Application application = new Application();
    	 
    	  
    	  lmsApplication.setLMSId(lmsId);
    	  lmsApplication.setPhone(phoneNo);
    	  application.setApplicationNumber(applicationNumber);
    	  application.setPaidYN("N");
    	  application.setStage("Data Entry");
    	  application.setStatus("N");
    	  application.setTabId("4");
    	  
    	  try {
			if(quotationID.contains(",")) {
				  quotationID = quotationID.split(",")[0];
			  }
		} catch (Exception e) {
			log.error(" Quotation id error >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+quotationID);
		}
    	  
    	  application.setQuotationID(quotationID);
    	  application.setLMSQuoteId(lMSQuoteId);
    	  application.setPolicyNumber(policyNumber);
    	  lmsApplication.setApplication(application);
    	  generateApplication.setLMSApplication(lmsApplication);
    	 return objectMapper.writeValueAsString(generateApplication);
    	  
      }
    
      	public static String getUpdateLeadRequestBody(ObjectMapper objectMapper, String lMSId, String fullName, String phone, String email, String gender, String dateOfBirth) throws JsonProcessingException {
      		UpdateLMSLeadRequest update  = new UpdateLMSLeadRequest();
      		Quote quote = new Quote();
      		UpdateLead updateLMSLead = new UpdateLead();
      		
      		updateLMSLead.setLMSId(lMSId);
      		updateLMSLead.setName(fullName);
      		updateLMSLead.setDob(dateOfBirth);
      		updateLMSLead.setEmail(email);
      		updateLMSLead.setGender(gender);
      		updateLMSLead.setPhone(phone);
      		updateLMSLead.setLeadFormType(EdelweissCommonConstants.LEAD_FORM_TYPE_RECCOMENDATION);
      		updateLMSLead.setQuote(quote);
      		 
      		update.setupdateLead(updateLMSLead);
			return objectMapper.writeValueAsString(update);
      		
      	}
      	public static CustomerFamilyDetailsRel getRequestCustomerFamilyDetails(Map<String, String> requestParametersMap, String leadId) {
    		CustomerFamilyDetailsRel customerFamilyDetails = new CustomerFamilyDetailsRel();
    		customerFamilyDetails.setAssuranceFullName(requestParametersMap.get(EdelweissObjectConstants.ASSURED_FULL_NAME));
    		customerFamilyDetails.setAssuranceDob(requestParametersMap.get(EdelweissObjectConstants.ASSURED_DATE_OF_BIRTH));
    		customerFamilyDetails.setAssuredRelation(requestParametersMap.get(EdelweissObjectConstants.ASSURED_RELATION));
    		customerFamilyDetails.setLeadId(leadId);
    		customerFamilyDetails.setrCustomerFamilyDetailsRelCCustomerEnquiryId(Long.valueOf(requestParametersMap.get(RCUSTOMERFAMILYDETAILSRELCCUSTOMERENQUIRYID)));
    		boolean removeLittleChamp = GetterUtil.getBoolean(requestParametersMap.get(EdelweissProductSummaryWebPortletKeys.REMOVE_LITTLE_CHAMP), false);
    		boolean removeRisingStar = GetterUtil.getBoolean(requestParametersMap.get(EdelweissProductSummaryWebPortletKeys.RISING_STAR), false);
    		if(Validator.isNotNull(removeLittleChamp) && removeLittleChamp) {
    			customerFamilyDetails.setIsLittleChampBenefitOpted(EdelweissProductSummaryWebPortletKeys.NO);
    		}
    		if(Validator.isNotNull(removeRisingStar) && removeRisingStar) {
    			customerFamilyDetails.setIsRisingStarBenefitOpted(EdelweissProductSummaryWebPortletKeys.NO);
    		}
    		
    		log.debug("EdelweissProductCustomizeHelper >>> getRequestCustomerFamilyDetails >>> Customer Family Details ::: " + customerFamilyDetails.toString());
    		return customerFamilyDetails;
    	}
      	
      	
      	public static LiferayResponseMessage getProductRelationsData(JSONObject productRelObjectResponse) {
    		LiferayResponseMessage productRelationsData = null;
    		try {
    			ObjectMapper objectMapper = new ObjectMapper();
    			objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    			productRelationsData = objectMapper.readValue(productRelObjectResponse.toString(), LiferayResponseMessage.class);
    			
    			log.debug("EdelweissProductCustomizeHelper >>> getProductRelationsData >>> Product Relation Data::: " + productRelationsData);
    			
    		} catch(Exception e) {
    			log.error("EdelweissProductCustomizeHelper >>> getProductRelationsData >>> Error fetching Product Relation Data ::: " + e);
    		}
    		
    		return productRelationsData;
    	}
      	
      	public static void hideSuccessAndErrorMessage(PortletRequest portletRequest) {
    		SessionMessages.add(portletRequest, PortalUtil.getPortletId(portletRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_SUCCESS_MESSAGE);
    		SessionMessages.add(portletRequest, PortalUtil.getPortletId(portletRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
    	}
	}
