package in.edelweiss.tokio.common.api;

import com.edelweiss.http.core.exception.ETIPSystemException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.io.Serializable;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletResponse;

import in.edelweiss.tokio.common.model.BIRequest;
import in.edelweiss.tokio.common.model.BIRequest.AgentDetails;
import in.edelweiss.tokio.common.model.BIRequest.ComboDetails;
import in.edelweiss.tokio.common.model.BIRequest.CompanyDetails;
import in.edelweiss.tokio.common.model.BIRequest.CustomerDetails;
import in.edelweiss.tokio.common.model.BIRequest.LiDetails;
import in.edelweiss.tokio.common.model.BIRequest.Product;
import in.edelweiss.tokio.common.model.BIRequest.ProductDetails;
import in.edelweiss.tokio.common.model.BIRequest.ProposerDetails;
import in.edelweiss.tokio.common.model.CustomerEnquiry;
import in.edelweiss.tokio.common.model.CustomerFamilyDetailsRel;
import in.edelweiss.tokio.common.model.CustomerFundAllocationDetailsRel;
import in.edelweiss.tokio.common.model.CustomerInvestmentDetails;
import in.edelweiss.tokio.common.model.CustomerPolicyDetailsRel;
import in.edelweiss.tokio.common.model.CustomizeFeaturesCard;
import in.edelweiss.tokio.common.model.FormView;
import in.edelweiss.tokio.common.model.InterestSessions;
import in.edelweiss.tokio.common.model.LiferayResponseMessage;
import in.edelweiss.tokio.common.model.MultipleLoginBlock;
import in.edelweiss.tokio.common.model.Quote;

/**
 * @author naitik.datta
 */
public interface EdelweissTokioCommonApi {
	
	public FormView getFormData(PortletRequest renderRequest, String productName);
	
	public Map<String, String> getPicklistByExternalReferenceCode(String externalReferenceCode, long companyId);

	public String getCustomFieldValue(long companyId, String className, long classPk, String columnName);
	
	public JSONObject getProductNestedFieldDataByProductId(String serviceURL, String productID);
		
	public BIRequest getBIRequest(ProductDetails productDetails, CustomerDetails customerDetails, CompanyDetails companyDetails);
	
	public ProductDetails getBIRequestProductDetails(Product product, ComboDetails comboDetails);
	
	public CustomerDetails getBIRequestCustomerDetails(LiDetails liDetails, ProposerDetails proposerDetails, AgentDetails agentDetails);
	

	public LiDetails getBIRequestLiDetails(String liName, int liEntryAge, String liDob, String liGender, String liState,
			String liMobileNo, String liEmailId);
	
	
	public ProposerDetails getBIRequestProposerDetails(String sameProposer, String proposerName, int proposerAge, String proposerDob,
			String proposerGender);
	
	public CompanyDetails getBIRequestCompanyDetails(int companyState, String gstin, int gstinNumber);

	public AgentDetails getBIRequestAgentDetails(String agentId, String agentName, String agentLocation, boolean fetchDefault);

	public String getLeadIdFromCookie(PortletRequest portletRequest, String cookieName);
	
	public void createCookie(String name, String values, HttpServletResponse response) throws ETIPSystemException;
	
	public Map<String , String> getEditBasicDetails(PortletRequest portletRequest) throws ETIPSystemException;
	
	public Map<String , String> getFamilyDetails(PortletRequest portletRequest, JSONObject basicDetailsObject) throws ETIPSystemException;

	public JSONObject updateDetails(PortletRequest portletRequest , Map<String,String> updatedFamilyMap) throws ETIPSystemException;
	
	public JSONArray getRecommendedProductList(String productId, Map<String, String> leadData);

	public JSONObject getProductDataWithLinkedPlansAndRiders(String portalURL, String productId);
	
	public JSONObject updateLead(PortletRequest portletRequest ,Map<String, String> updatedFamilyMap, String productName,
			JSONObject metaDataResponse);

	public InterestSessions getLMSRequestInterestSessionsBody(Map<String, String> updateLMSRquestMapData);

	public String getUpdateLMSRequestBody(Map<String, String> updateLMSRquestMapData);

	public Quote getQuoteLMSRequestBody(Map<String, String> updateLMSRquestQuoteMapData);

	public JSONObject updateLMSLead(String requestBody) throws JSONException, ETIPSystemException;

	JSONObject generateLead(Map<String, String> formValues, String productName, JSONObject metaDataResponse,String userAgent);
	
	public String getLeadMetaDataURL(String leadId);
	
	public String getMultipleLoginURL(String proposalNumber);

	public CustomerEnquiry getCustomerEnquiryByLeadId(String portalURL, String leadId);

	public CustomerEnquiry updateCustomerEnquiryById(String portalURL, CustomerEnquiry customerEnquiryRequest, Long customerEnquiryId) throws Exception;

	public CustomerFamilyDetailsRel updateFamilyDetails(String portalURL, CustomerFamilyDetailsRel customerFamilyDetails, String familyId) throws JsonProcessingException, ETIPSystemException, JSONException;

	public Boolean deleteFamilyDetailsById(String portalURL, String familyId);

	public CustomerInvestmentDetails updateInvestmentDetailsById(String portalURL, CustomerInvestmentDetails customerInvestmentDetails, Long customerInvestmentDetailId) throws Exception;

	public MultipleLoginBlock updateMultipleLoginBlock(String portalURL, MultipleLoginBlock multipleLoginBlock, Long multipleLoginBlockId) throws Exception;

	public CustomerInvestmentDetails getInvestmentDetailsLeadId(String portalURL, String leadId);
	
	public JSONObject getCustomerInvestmentDetailsLeadId(String portalURL, String leadId);
	
	public CustomerFundAllocationDetailsRel updateFundAllocationDetailsById(String portalURL, CustomerFundAllocationDetailsRel customerFundAllocationDetails, Long customerFundAllocationDetailsId) throws Exception;

	public CustomerFundAllocationDetailsRel getFundAllocationDetailsByLeadId(String portalURL, String leadId);

	public JSONObject getCAGRData() throws JSONException, ETIPSystemException;

	public JSONObject invokeRetailParnerGenerateBI(String biRequestJsonString) throws JSONException, ETIPSystemException;

	public String getQuoteJourneyOAuthToken() throws JSONException, ETIPSystemException;
	
	public String getQJComboOAuthToken(String url, String userName, String pwd) throws JSONException, ETIPSystemException;

	public CustomerPolicyDetailsRel updatePolicyDetailsById(String portalURL, CustomerPolicyDetailsRel customerPolicyDetailsRel, Long customerPolicyDetailsId) throws Exception;

	public JSONObject invokeBuyJourneyGenerateBi(String biRequestJsonString) throws JSONException, ETIPSystemException;

	public JSONObject getFamilyDetailsByLeadId(String portalURL, String leadId);

	public JSONObject getPolicyDetailsByLeadId(String portalURL, String leadId);
	
	public MultipleLoginBlock getMultipleLoginByPolicyNumber(String portalURL, String policyNumber);
	
	public JSONObject getRestartJourneyData(Map<String, String> restartJourneyDataMap);
	
	public JSONObject updateCustomerDropoutDetails(String portalUrl ,Map<String,String> restartJourneyRequest, String customerDropoutId) throws JsonProcessingException, ETIPSystemException;
	
	public JSONObject getProductAndLinkedRidersData(String portalURL, String productId);
	
	public JSONObject getProductAndLinkedDirectRidersData(String portalURL, String productId);
	
	public JSONObject getProductJsonConfigurationByProductId(String portalURL, String productId);
	
	public JSONObject getProductRidersData(String portalURL, String riderId);
	
	public void renderBasicDetails(PortletRequest portletRequest);

	public String getMetaDataURLByParam(String fieldName, String fieldValue);
	
	public JSONObject getProductMasterByProductCode(String portalURL, String productCode);
	
	public LiferayResponseMessage getProductMasterBeanByProductCode(String portalURL, String productCode);
	
	public CustomerFamilyDetailsRel getRequestCustomerFamilyDetails(Map<String, String> requestParametersMap, String leadId);

	public CustomerPolicyDetailsRel getRequestPolicyDetails(Map<String, String> requestParametersMap, String leadId);
	
	public JSONObject invokeProductList(String productListRequestStr) throws JSONException, ETIPSystemException;

	public JSONObject invokeComboGenerateBi(String biRequestJsonString) throws JSONException, ETIPSystemException;
	
	public JSONObject invokeProductModification(String productModificationRequestStr) throws JSONException, ETIPSystemException;

	public CustomizeFeaturesCard getCustomizeFeatureCardContentByURLTitle(long grpupId, String articleURLTitle);

	public CustomerEnquiry updateCustomerEnquiryLMSResponse(String portalURL, JSONObject leadUpdateResponse, String customerEnquiryId);
	
	public JSONObject validate(PortletRequest portletRequest);
	
	public void updateCustomerQuoteStage(Map<String, Serializable> values, long customerEnquiryId,ServiceContext serviceContext);

	public String getOAuthToken(String url, String username, String password) throws JSONException, ETIPSystemException;
	
	public  JSONObject getPartnerData(PortletRequest portletRequest,String productId,ThemeDisplay themeDisplay,String a); 

	public Map<String, String> addUTMCampaignParams(PortletRequest portletRequest , Map<String, String> mapformMap);
	
	public JSONObject setPartner(JSONObject customerEnquire, JSONObject partnerData, PortletResponse portletResponse);
	
	public void setPartnerCookie(boolean isPartner,PortletRequest portletRequest, PortletResponse portletResponse);
	
	public  JSONObject getPartnerDataByLeadId(PortletRequest portletRequest);

	public String lmsDateFormate(String date);

	public FormView getFormData(PortletRequest renderRequest, long formInstanceId); 
	
	public String getEKycLinkURLByAPI(String requestJsonString) throws JSONException, ETIPSystemException;
}