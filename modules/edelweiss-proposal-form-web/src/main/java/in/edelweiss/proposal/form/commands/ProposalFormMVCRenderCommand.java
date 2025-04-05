package in.edelweiss.proposal.form.commands;

import static in.edelweiss.tokio.constants.EdelweissObjectConstants.APP_NUMBER;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.CUSTOMER_INVESTMENT_DETAILS_BASE_URL;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.CUSTOMER_INVESTMENT_DETAILS_ID_PARAM;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.CUSTOMER_POLICY_DETAILS_BASE_URL;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.FILTER_QUERY_PARAM;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PUT_CUSTOMER_INVESTMENT_DETAILS_URL;

import com.edelweiss.http.core.api.ETIPCoreAPI;
import com.edelweiss.http.core.exception.ETIPSystemException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.liferay.petra.string.StringPool;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.webcache.WebCachePoolUtil;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.common.util.DateFormatterUtil;
import in.edelweiss.common.util.EdelweissAPIUtil;
import in.edelweiss.pdf.generator.utility.PDFUtil;
import in.edelweiss.proposal.form.company.model.CompanyList;
import in.edelweiss.proposal.form.constants.ProposalFormConstants;
import in.edelweiss.proposal.form.constants.ProposalFormPortletKeys;
import in.edelweiss.proposal.form.dropdown.model.Data;
import in.edelweiss.proposal.form.dropdown.model.DropdownMaster;
import in.edelweiss.proposal.form.pf.model.CommonData;
import in.edelweiss.proposal.form.pf.model.CommunicationDetails;
import in.edelweiss.proposal.form.pf.model.CovidQuestionDetails;
import in.edelweiss.proposal.form.pf.model.EmploymentDetails;
import in.edelweiss.proposal.form.pf.model.FamilyHistoryDetails;
import in.edelweiss.proposal.form.pf.model.InsuranceHistoryDetails;
import in.edelweiss.proposal.form.pf.model.LifeStyleDetails;
import in.edelweiss.proposal.form.pf.model.MedicalQuestionDetails;
import in.edelweiss.proposal.form.pf.model.PersonalDetails;
import in.edelweiss.proposal.form.pf.model.ProductDetails;
import in.edelweiss.proposal.form.pf.model.ProposalJourney;
import in.edelweiss.proposal.form.pf.model.ResponseData;
import in.edelweiss.proposal.form.util.CISHtmlContentReader;
import in.edelweiss.system.configurations.EdelweissLRBasicAuthConfiguration;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.util.EdelweissConfigurationUtil;

/**
 * @author Abhijit AA
 */

@Component(immediate = true, property = { "javax.portlet.name=" + ProposalFormPortletKeys.PROPOSALFORM,
		"mvc.command.name=/" }, service = MVCRenderCommand.class)
public class ProposalFormMVCRenderCommand implements MVCRenderCommand {

	private static final Log logger = LogFactoryUtil.getLog(ProposalFormMVCRenderCommand.class);
	
	@Reference
	ETIPCoreAPI etipCoreApi;

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		logger.info("Proposal Form Loaded");
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			HttpServletRequest originalRequest = PortalUtil.getOriginalServletRequest(request);
			String applicationNumber = ParamUtil.getString(originalRequest, "appid");
			renderRequest.setAttribute("appid", applicationNumber);

			logger.info("Proposal Form Application ID : "+applicationNumber);
			
			String pfURL = ConfigurationUtil.getProposalFormAPIURLConfiguration().getPFDetailsURL()
					+ "?applicationNumber=" + applicationNumber;
			String pfResponse = EdelweissAPIUtil.callPJGetAPI(pfURL);
			logger.info("pfResponse :::::::::::::: "+pfResponse);
			logger.info("ProposalFormMVCRenderCommand pfResponse : AppId : "+applicationNumber+" " +pfResponse);
			
			ProposalJourney proposalJourney = objectMapper.readValue(pfResponse, ProposalJourney.class);

			if (Validator.isNull(proposalJourney)) {
				logger.info("Proposal Form proposalJourney Is Null");
				renderRequest.setAttribute("title",
						"Error while processing your request. Please contact our support team if the problem persists.");

				return "/proposal_form/error_modal.jsp";
			}

			logger.info("Proposal Form GetPF Status : "+proposalJourney.isStatus());
			if (!proposalJourney.isStatus()) {
				if (Validator.isNotNull(proposalJourney.getErrors()) && proposalJourney.getErrors().size() > 0) {
					logger.info("Proposal Form Get Error : "+proposalJourney.getErrors());
					renderRequest.setAttribute("title", proposalJourney.getErrors().get(0));
				} else {
					logger.info("Proposal Form Custom Error");
					renderRequest.setAttribute("title",
							"Error while processing your request. Please use the id and contact our support team if the problem persists."
									+ proposalJourney.getResponseData2().getId());
				}
				return "/proposal_form/error_modal.jsp";
			}
			
			// CommonData
			CommonData commonDetails = proposalJourney.getResponseData().getCommonData();
			// ProductDetails
			ProductDetails productDetails = proposalJourney.getResponseData().getProductDetails();
			
			logger.info("Proposal Form Application Stage : "+commonDetails.getApplicationStage());
			logger.info("Proposal Form Payment Status : "+commonDetails.getPaymentStatus() != null ? commonDetails.getPaymentStatus() : "");
			logger.info("Proposal Form Push To LMS : "+commonDetails.getPushToLms() != null ? commonDetails.getPushToLms() : "");
			
			JSONObject cusPolicyDetailsRes = getPolicyDetailsByApplicationNumber(applicationNumber, themeDisplay);
			String leadId = cusPolicyDetailsRes.getJSONArray("items").getJSONObject(0).getString("leadId");
			String customerInvestmentDetailsURL = themeDisplay.getPortalURL()  + PUT_CUSTOMER_INVESTMENT_DETAILS_URL;
			customerInvestmentDetailsURL = StringUtil.replace(customerInvestmentDetailsURL, CUSTOMER_INVESTMENT_DETAILS_ID_PARAM, String.valueOf(leadId));
			JSONObject cusInvestmentRes = getCustomerInvestmentDetailsByLead(leadId, themeDisplay);
			JSONArray items = cusInvestmentRes.getJSONArray("items");
			JSONObject obj = items.getJSONObject(0);
			String customerInvestmentDetailsPayementCreateDate = obj.getString("paymentCreatedDate");
			String customerInvestmentDetailsId = obj.getString("id");
			JSONObject customerInvestmentDetailsRequestBody = JSONFactoryUtil.createJSONObject();
			Date currentDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			
			//Capturing Payment date after payment
			if (commonDetails.getPaymentStatus().equals("Y")) {
				if(customerInvestmentDetailsPayementCreateDate.equalsIgnoreCase(StringPool.BLANK)) {
					logger.info("Payment Date Captured :::::::::: " +sdf.format(currentDate));
					customerInvestmentDetailsRequestBody.put("paymentCreatedDate", sdf.format(currentDate));
				}
				updateCustomerInvestmentDetailsByLead(customerInvestmentDetailsId, themeDisplay, customerInvestmentDetailsRequestBody);
			}
			
			//CIS Imlementation
			if (commonDetails.getApplicationStage() == 2 && commonDetails.getPushToLms().equals("Y") && commonDetails.getPaymentStatus().equals("Y")) {
				logger.info("Proposal Form CIS PDF Pushing To D365 "+applicationNumber);
				boolean customerInvestmentDetailsIsCISPdfPushed = obj.getBoolean("isCISPDFPushed");
				
				if(!customerInvestmentDetailsIsCISPdfPushed) {
					logger.info("CIS PDF Pushed For : "+applicationNumber);
					CISHtmlContentReader cisHtmlContentReader = new CISHtmlContentReader();
					JSONObject cusInvestmentAfterPaymentRes = getCustomerInvestmentDetailsByLead(leadId, themeDisplay);
					String htmlContent = cisHtmlContentReader.getHtmlByAppId(applicationNumber,cusInvestmentAfterPaymentRes, themeDisplay, etipCoreApi);
					String cisData = PDFUtil.generatePDFAsBase64String(htmlContent);
					logger.info("CIS PDF Appid : "+applicationNumber+" Base64 : "+cisData);
					
					String pushCISURL = ConfigurationUtil.getProposalFormAPIURLConfiguration().getPostCisDocument();
					logger.info("CIS Push URL : "+pushCISURL);
					//String pushCISURL = "https://buyonlineapiuat.edelweisslife.in/api/v1/proposalform/postcisdocument";
					Map<String, String> requestPushCIS = new HashMap<>();
					requestPushCIS.put("cisdata", cisData);
					requestPushCIS.put("applicationNumber", applicationNumber);
					JSONObject requestPushCISJson = JSONFactoryUtil.createJSONObject(requestPushCIS);
					logger.info("CIS PDF Push Appid : "+applicationNumber+" RequestJson : "+requestPushCISJson);
					JSONObject pushedCISResponse = etipCoreApi.callPostAPI(requestPushCISJson,pushCISURL, false, "", "");
					
					if(pushedCISResponse.getBoolean("status")) {
						customerInvestmentDetailsRequestBody.put("isCISPDFPushed", true);
					}
					logger.info("CIS PDF pushedCISResponse : "+pushedCISResponse);
				}

				updateCustomerInvestmentDetailsByLead(customerInvestmentDetailsId, themeDisplay, customerInvestmentDetailsRequestBody);
			}
			
			if (commonDetails.getApplicationStage() == 2 && commonDetails.getPaymentStatus().equals("N")) {
				logger.info("Application stage 2 And PaymentSatus N "+applicationNumber);
				renderRequest.setAttribute("payment_url",
						proposalJourney.getResponseData().getPaymentDetails().getPaymentUrl());
				return "/proposal_form/payment_redirect.jsp";
			} else if (commonDetails.getApplicationStage() == 2 && commonDetails.getPushToLms().equals("Y")) {
				logger.info("Application stage 2 And PushToLms Y "+applicationNumber);
				renderRequest.setAttribute("productDetails", proposalJourney.getResponseData().getProductDetails());
				//renderRequest.setAttribute("ProductTotalPremiumAmount", proposalJourney.getResponseData().getProductDetails().getTotalPremiumAmount());
				renderRequest.setAttribute("childProductDetails", proposalJourney.getResponseData().getChildProductDetails());
				renderRequest.setAttribute("commonDetails", commonDetails);
				String appTrackerUrl = getAppTrackerUrl(proposalJourney, commonDetails.getPolicyNo());
				logger.info("AppTrackerUrl "+appTrackerUrl);
				renderRequest.setAttribute("savethankyouURL",ConfigurationUtil.getProposalFormAPIURLConfiguration().getSavethankyouURL());
				renderRequest.setAttribute("appTrackerUrl", appTrackerUrl);
				// ChildProductDetails
				ArrayList<ProductDetails> childProductDetails = proposalJourney.getResponseData().getChildProductDetails();
				if(childProductDetails != null) {
					for (ProductDetails childProduct : childProductDetails) {
						if(childProduct.getProductName().equalsIgnoreCase("Guaranteed Savings STAR")) {
							renderRequest.setAttribute("childProduct", childProduct);
							//renderRequest.setAttribute("childProductTotalPremiumAmount",childProduct.getTotalPremiumAmount());
						}
					}
				}
				if(Validator.isNotNull(proposalJourney.getResponseData().getPaymentDetails())) {
					String transactionId=proposalJourney.getResponseData().getPaymentDetails().getTransactionId();
					logger.info("Payment Appid :"+applicationNumber+ " Transaction Id :"+transactionId);
					renderRequest.setAttribute("transactionId", transactionId);
				}
				
				String redirectURL = themeDisplay.getURLCurrent();
				if(redirectURL.contains("/proposal-new")) {
					redirectURL = redirectURL.replace("/proposal-new", "/thankyou");
					logger.info("Redirecting From Proposal-new To thankyou : "+redirectURL);
					HttpServletResponse response = PortalUtil.getHttpServletResponse(renderResponse);					
					response.sendRedirect(redirectURL);
				}
				
				return "/proposal_form/thank_you.jsp";
			} else if (commonDetails.getApplicationStage() > 2) {
				logger.info("Application stage greaterthan 2");
				String appTrackerUrl = getAppTrackerUrl(proposalJourney, commonDetails.getPolicyNo());
				logger.info("AppTrackerUrl "+appTrackerUrl);
				renderRequest.setAttribute("appTrackerUrl", appTrackerUrl);
				return "/application_tracker/application_tracker.jsp";
			}
			
			boolean displayABHANoField = ConfigurationUtil.getProposalFormAPIURLConfiguration().getDisplayABHANoField();
			logger.debug("commonDetails..."+commonDetails);
			renderRequest.setAttribute("commonDetails", commonDetails);
			renderRequest.setAttribute("isLaPrSame", commonDetails.getIsLaPrSameYn().equals("Y"));
			renderRequest.setAttribute("isSpouseExist", commonDetails.getSpouseExistYn().equals("Y"));
			renderRequest.setAttribute("isLongForm", commonDetails.getFormType().equalsIgnoreCase("COM00"));
			renderRequest.setAttribute("isWSP", commonDetails.getProductName().equalsIgnoreCase("Wealth Secure+"));	
			renderRequest.setAttribute("displayABHANoField", displayABHANoField);
			renderRequest.setAttribute("isZP", commonDetails.getProductName().equalsIgnoreCase("Zindagi Protect Plus"));
			renderRequest.setAttribute("isWP", commonDetails.getProductName().equalsIgnoreCase("Wealth Plus"));
			renderRequest.setAttribute("isWRP", commonDetails.getProductName().equalsIgnoreCase("Wealth Rise+"));
			renderRequest.setAttribute("isWGP", commonDetails.getProductName().equalsIgnoreCase("Wealth Gain+"));
			renderRequest.setAttribute("isCSP", commonDetails.getProductName().equalsIgnoreCase("Capital Secure+"));
			renderRequest.setAttribute("isIB", commonDetails.getProductName().equalsIgnoreCase("Income Builder"));
			renderRequest.setAttribute("isAI", commonDetails.getProductName().equalsIgnoreCase("Active Income"));
			renderRequest.setAttribute("isSJB", commonDetails.getProductName().equalsIgnoreCase("Saral Jeevan Bima"));
			
			renderRequest.setAttribute("isWP", commonDetails.getProductName().equalsIgnoreCase("Wealth Plus"));
			renderRequest.setAttribute("isWRP", commonDetails.getProductName().equalsIgnoreCase("Wealth Rise+"));
			renderRequest.setAttribute("isWGP", commonDetails.getProductName().equalsIgnoreCase("Wealth Gain+"));
			renderRequest.setAttribute("isCSP", commonDetails.getProductName().equalsIgnoreCase("Capital Secure+"));
			renderRequest.setAttribute("isIB", commonDetails.getProductName().equalsIgnoreCase("Income Builder"));
			renderRequest.setAttribute("isAI", commonDetails.getProductName().equalsIgnoreCase("Active Income"));
			renderRequest.setAttribute("isSJB", commonDetails.getProductName().equalsIgnoreCase("Saral Jeevan Bima"));
			
			if(commonDetails.getProductName().equalsIgnoreCase("Wealth Secure+")) {
				renderRequest.setAttribute("isChildBenefit", productDetails.getPlanOptions().toLowerCase().contains("child"));	
			} else if(commonDetails.getProductName().equalsIgnoreCase("Zindagi Protect Plus")) {
				renderRequest.setAttribute("isChildBenefit", (null!=productDetails.getChildBenefit())?productDetails.getChildBenefit().equalsIgnoreCase("Y"):"false");	
				
			}
			
			// Get PF Details Response for JS Request Creation
			renderRequest.setAttribute("pfResponse", pfResponse);

			String endpointURL = ConfigurationUtil.getProposalFormAPIURLConfiguration().getMasterDataURL();
			String companyListURL = ConfigurationUtil.getProposalFormAPIURLConfiguration().getCompanyListURL();
			// Cache dropdown
			Map<String, List<Data>> masterMap = DropdownMasterCacheItem.get(endpointURL);
			if (Validator.isNotNull(masterMap)) {
				renderRequest.setAttribute("masterMap", masterMap);
				renderRequest.setAttribute("masterJson", objectMapper.writeValueAsString(masterMap));
			} else {
				WebCachePoolUtil.remove(DropdownMaster.class.getName());
				logger.debug(" DropdownMaster is null >>>>>>>>>>>>>>>>>>>>>>> ");
			}
			// Cache company list
			CompanyList companyList = CompanyListCacheItem.get(companyListURL);
			if (Validator.isNotNull(companyList) && companyList.isStatus()) {
				renderRequest.setAttribute("companyList", companyList.getResponseData());
			} else {
				WebCachePoolUtil.remove(CompanyList.class.getName());
				logger.debug(" CompanyList is null >>>>>>>>>>>>>>>>>>>>>>> ");
			}

			/*
			 * TODO: Update the JSP to handle the invalid Application Number
			 */
			if (Validator.isNull(proposalJourney.getResponseData())) {
				logger.error("No Data found for with the given application id");
				return "/proposal_form/proposal_form.jsp";
			}

			// PersonalDetails
			PersonalDetails personalDetails = proposalJourney.getResponseData().getPersonalDetails();
			logger.info("personalDetails----------->>>><><><><><"+personalDetails.getLaDetails().getAadharNumber());
			renderRequest.setAttribute("laPersonalDetails", personalDetails.getLaDetails());
			renderRequest.setAttribute("proposerPersonalDetails", personalDetails.getProposerDetails());
			renderRequest.setAttribute("spousePersonalDetails", personalDetails.getSpouseDetails());
			logger.info("personalDetails.getLaDetails().getTaxResidenceDeclarationYn()--------testing-----testing------------->"+personalDetails.getLaDetails().getTaxResidenceDeclarationYn());
			renderRequest.setAttribute("laTaxResidency",personalDetails.getLaDetails().getTaxResidenceDeclarationYn());
			
			logger.info(":::::::::laPersonalDetails:::::::::"+personalDetails.getLaDetails());
			long companyId = themeDisplay.getCompanyId();
			Map<String, String>courseDataMap = edelweissTokioCommonApi.
					getPicklistByExternalReferenceCode(ProposalFormConstants.COURSE_DETAIL_EXTREF_CODE, companyId);
			logger.debug("courseDataMap..."+courseDataMap);
			renderRequest.setAttribute("courseDataMap", courseDataMap);

			// Condition to check related to LA for field population
			boolean isLaMinor = true;
			boolean isInfant = true;
			if (Validator.isNotNull(personalDetails.getLaDetails())
					&& Validator.isNotNull(personalDetails.getLaDetails().getDob())
					&& !personalDetails.getLaDetails().getDob().isBlank()) {

				int laAge = DateFormatterUtil.calculateAge(personalDetails.getLaDetails().getDob());

				if (laAge == -1) {
					personalDetails.getLaDetails().setDob(StringPool.BLANK);
				}

				isLaMinor = laAge >= 18 ? false : true;
				isInfant = laAge >= 5 ? false : true;
			}
			renderRequest.setAttribute("isLaMinor", isLaMinor);
			renderRequest.setAttribute("isInfant", isInfant);

			if (Validator.isNotNull(personalDetails.getProposerDetails()) && commonDetails.getIsLaPrSameYn().equals("N")
					&& Validator.isNotNull(personalDetails.getProposerDetails().getDob())
					&& !personalDetails.getProposerDetails().getDob().isBlank()) {

				int proposerAge = DateFormatterUtil.calculateAge(personalDetails.getProposerDetails().getDob());

				if (proposerAge == -1) {
					personalDetails.getProposerDetails().setDob(StringPool.BLANK);
				}
			}

			if (Validator.isNotNull(personalDetails.getSpouseDetails()) && commonDetails.getSpouseExistYn().equals("Y")
					&& Validator.isNotNull(personalDetails.getSpouseDetails().getDob())
					&& !personalDetails.getSpouseDetails().getDob().isBlank()) {

				int spouseAge = DateFormatterUtil.calculateAge(personalDetails.getSpouseDetails().getDob());

				if (spouseAge == -1) {
					personalDetails.getSpouseDetails().setDob(StringPool.BLANK);
				}
			}

			// CommunicationDetails
			CommunicationDetails communicationDetails = proposalJourney.getResponseData().getCommunicationDetails();
			renderRequest.setAttribute("laCommunicationDetails", communicationDetails.getLaDetails());
			renderRequest.setAttribute("proposerCommunicationDetails", communicationDetails.getProposerDetails());
			renderRequest.setAttribute("spouseCommunicationDetails", communicationDetails.getSpouseDetails());

			logger.debug("communicationDetails.getLaDetails() :::::: "+communicationDetails.getLaDetails());
			logger.debug("proposerCommunicationDetails :::::: "+communicationDetails.getProposerDetails());
			// EmploymentDetails
			EmploymentDetails employmentDetails = proposalJourney.getResponseData().getEmploymentDetails();
			renderRequest.setAttribute("laEmploymentDetails", employmentDetails.getLaDetails());
			renderRequest.setAttribute("proposerEmploymentDetails", employmentDetails.getProposerDetails());
			renderRequest.setAttribute("spouseEmploymentDetails", employmentDetails.getSpouseDetails());
			// LifeStyleDetails
			LifeStyleDetails lifeStyleDetails = proposalJourney.getResponseData().getLifeStyleDetails();
			renderRequest.setAttribute("lifeStyleDetails", lifeStyleDetails);
			renderRequest.setAttribute("laLifeStyleDetails", lifeStyleDetails.getLaDetails());
			renderRequest.setAttribute("ProposerLifeStyleDetails", lifeStyleDetails.getProposerDetails());
			renderRequest.setAttribute("spouseLifeStyleDetails", lifeStyleDetails.getSpouseDetails());

			// medical question details
			MedicalQuestionDetails medicalQuestionDetails = proposalJourney.getResponseData()
					.getMedicalQuestionDetails();
			renderRequest.setAttribute("medicalQuestionDetails", medicalQuestionDetails);
			renderRequest.setAttribute("laMedicalQuestionDetails", medicalQuestionDetails.getLaDetails());
			renderRequest.setAttribute("proposerMedicalQuestionDetails", medicalQuestionDetails.getProposerDetails());
			renderRequest.setAttribute("spouseMedicalQuestionDetails", medicalQuestionDetails.getSpouseDetails());

			// API Endpoint URL
			renderRequest.setAttribute("getCKycDetailsURL",
					ConfigurationUtil.getProposalFormAPIURLConfiguration().getCKycDetailsURL());
			renderRequest.setAttribute("getBankDetailsIFSCURL",
					ConfigurationUtil.getProposalFormAPIURLConfiguration().getBankDetailsIFSCURL());
			renderRequest.setAttribute("postCKycDetailsURL",
					ConfigurationUtil.getProposalFormAPIURLConfiguration().getPostCKycDetailsURL());
			renderRequest.setAttribute("sendOTPURL",
					ConfigurationUtil.getProposalFormAPIURLConfiguration().getSendOTPURL());
			renderRequest.setAttribute("verifyOTPURL",
					ConfigurationUtil.getProposalFormAPIURLConfiguration().getValidateOTPURL());
			renderRequest.setAttribute("createPaymentLinkURL",
					ConfigurationUtil.getProposalFormAPIURLConfiguration().getCreatePaymentLinkURL());
			renderRequest.setAttribute("savePFDetailsURL",
					ConfigurationUtil.getProposalFormAPIURLConfiguration().getSavePFDetailsURL());			
			renderRequest.setAttribute("autoSaveInterval",
					ConfigurationUtil.getProposalFormAPIURLConfiguration().getAutoSaveIntervalURL());
			renderRequest.setAttribute("totalAutoSaveInterval",
					ConfigurationUtil.getProposalFormAPIURLConfiguration().getAutoSaveTotalIntervalURL());

			/*
			 * Other Details Section
			 */
			getInsuranceHistoryDetails(proposalJourney.getResponseData(), renderRequest);

			// Covid19 Details
			CovidQuestionDetails covidQuestionDetails = proposalJourney.getResponseData().getCovidQuestionDetails();
			if (Validator.isNotNull(covidQuestionDetails)) {

				renderRequest.setAttribute("covidQuestionDetails", covidQuestionDetails);
				renderRequest.setAttribute("laCovidDetails", covidQuestionDetails.getLaDetails());
				if (Validator.isNotNull(covidQuestionDetails.getProposerDetails())
						&& commonDetails.getIsLaPrSameYn().equals("N")) {
					renderRequest.setAttribute("prSpCovidDetails", covidQuestionDetails.getProposerDetails());
				} else if (Validator.isNotNull(covidQuestionDetails.getSpouseDetails())
						&& commonDetails.getSpouseExistYn().equals("Y")) {
					renderRequest.setAttribute("prSpCovidDetails", covidQuestionDetails.getSpouseDetails());
				}
			}
			// used in health details section
			if(personalDetails.getProposerDetails()!=null){
				renderRequest.setAttribute("insurerHealth", personalDetails.getProposerDetails());
		   }
		   else if(personalDetails.getSpouseDetails()!=null){
			   renderRequest.setAttribute("insurerHealth",personalDetails.getSpouseDetails());
		   }
			
			//EKYC Validation
			PortletSession session = renderRequest.getPortletSession();
			String ekycTxnId = (String) session.getAttribute("txnId", PortletSession.PORTLET_SCOPE);
			String isLaPrSps = (String) session.getAttribute("isLaPrSps", PortletSession.PORTLET_SCOPE);
			JSONObject ekycDetailsObj = getEkycDetailsByTxnId(ekycTxnId, themeDisplay, renderRequest, isLaPrSps);
			String postEkycDetailsStatus = postEkycDetails(ekycDetailsObj, isLaPrSps, applicationNumber);
			logger.debug("postEkycDetailsStatus :  "+postEkycDetailsStatus);
			renderRequest.setAttribute("ekycDetailsByTxnId", ekycDetailsObj);

			String AadharNumberApiRes = ekycDetailsObj.getString("uid");
			String AadharNumber = personalDetails.getLaDetails().getAadharNumber();
			
			logger.debug("ekycTxnId :  "+ekycTxnId);
			logger.debug("AadharNumberApiRes :  "+AadharNumberApiRes);
			logger.debug("Aadhar Number :  "+AadharNumber);
			logger.debug("isLaPrSps :  "+isLaPrSps);
			logger.debug("ekycStatus :  "+ekycDetailsObj.getString("ekycStatus"));
			
			if(Validator.isNotNull(AadharNumberApiRes) && ekycDetailsObj.getString("ekycStatus").equals("Y")) {
				renderRequest.setAttribute("aadharUuidRndr", AadharNumberApiRes);
				session.setAttribute("ekycSuccess_la_session", "true", PortletSession.PORTLET_SCOPE);
			}else if(Validator.isNotNull(AadharNumber)) {
				renderRequest.setAttribute("aadharUuidRndr", AadharNumber);
				session.setAttribute("ekycSuccess_la_session", "true", PortletSession.PORTLET_SCOPE);
			}else {
				session.setAttribute("ekycSuccess_la_session", "false", PortletSession.PORTLET_SCOPE);
			}
			
			//LA Ekyc
			if(null!=isLaPrSps && isLaPrSps.equalsIgnoreCase("la")) {
				if(null!=ekycDetailsObj && ekycDetailsObj.length()!=0 && ekycDetailsObj.getString("ekycStatus").equalsIgnoreCase("Y") && postEkycDetailsStatus.equalsIgnoreCase("true")) {
					//renderRequest.setAttribute("ekycSuccess_la", "true");
					logger.debug("LA Ekyc : ture");
					session.setAttribute("ekycSuccess_la_session", "true", PortletSession.PORTLET_SCOPE);
				} else {
					//renderRequest.setAttribute("ekycSuccess_la", "false");
					logger.debug("LA Ekyc : false");
					session.setAttribute("isEkyc_la_failed_session", "true", PortletSession.PORTLET_SCOPE);
					session.setAttribute("ekycSuccess_la_session", "false", PortletSession.PORTLET_SCOPE);
				}	
			} else if(null!=isLaPrSps && isLaPrSps.equalsIgnoreCase("proposer")) {//Proposer Ekyc
				if(null!=ekycDetailsObj && ekycDetailsObj.length()!=0 && ekycDetailsObj.getString("ekycStatus").equalsIgnoreCase("Y") && postEkycDetailsStatus.equalsIgnoreCase("true")) {
					//renderRequest.setAttribute("ekycSuccess_proposer", "true");
					logger.debug("LA Ekyc : ture");
					session.setAttribute("ekycSuccess_proposer", "true", PortletSession.PORTLET_SCOPE);
				} else {
					//renderRequest.setAttribute("ekycSuccess_proposer", "false");
					logger.debug("LA Ekyc : false");
					session.setAttribute("ekycSuccess_proposer", "false", PortletSession.PORTLET_SCOPE);
				}	
			} 
			
			if(null!=(String) session.getAttribute("ekycSuccess_la_session", PortletSession.PORTLET_SCOPE)) {
				renderRequest.setAttribute("ekycSuccess_la", (String) session.getAttribute("ekycSuccess_la_session", PortletSession.PORTLET_SCOPE));		
			}

			if(null!=(String) session.getAttribute("ekycSuccess_proposer", PortletSession.PORTLET_SCOPE)) {
				renderRequest.setAttribute("ekycSuccess_proposer", (String) session.getAttribute("ekycSuccess_proposer", PortletSession.PORTLET_SCOPE));		
			} 
			
			renderRequest.setAttribute("isEkyc_la_failed", (String) session.getAttribute("isEkyc_la_failed_session", PortletSession.PORTLET_SCOPE));
			session.removeAttribute("txnId");
			session.removeAttribute("isLaPrSps");
			logger.debug("After setAttribute :  "+ekycTxnId);
			logger.debug("End >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");

			return "/proposal_form/proposal_form.jsp";

		} catch (Exception e) {
			renderRequest.setAttribute("title",
					"Error while processing your request. Please contact our support team if the problem persists.");
			logger.error("Error while parsing Get Data Response ::: " + e);
			logger.error("Error while parsing Get Data Response ::: " + e.getMessage());
		}

		return "/proposal_form/error_modal.jsp";
	}

	/*
	 * Get LA/PR/SP Insurance History and Other Details
	 */
	private void getInsuranceHistoryDetails(ResponseData responseData, RenderRequest renderRequest) {

		try {

			/*
			 * Insurance History Details
			 */
			InsuranceHistoryDetails insuranceHistoryDetails = responseData.getInsuranceHistoryDetails();
			if (Validator.isNotNull(insuranceHistoryDetails)) {
				renderRequest.setAttribute("laInsuranceHistoryDetails", insuranceHistoryDetails.getLaDetails());

				if (Validator.isNotNull(insuranceHistoryDetails.getProposerDetails())) {
					renderRequest.setAttribute("prSpInsuranceHistoryDetails",
							insuranceHistoryDetails.getProposerDetails());
					
				} else if (Validator.isNotNull(insuranceHistoryDetails.getSpouseDetails())) {
					renderRequest.setAttribute("prSpInsuranceHistoryDetails",
							insuranceHistoryDetails.getSpouseDetails());
					
				}
			}

			/*
			 * Family History Details
			 */
			FamilyHistoryDetails familyHistoryDetails = responseData.getFamilyHistoryDetails();
			if (Validator.isNotNull(familyHistoryDetails)) {
				renderRequest.setAttribute("laFamilyHistoryDetails", familyHistoryDetails.getLaDetails());

				if (Validator.isNotNull(familyHistoryDetails.getProposerDetails())) {
					renderRequest.setAttribute("prSpFamilyHistoryDetails", familyHistoryDetails.getProposerDetails());

				} else if (Validator.isNotNull(familyHistoryDetails.getSpouseDetails())) {
					renderRequest.setAttribute("prSpFamilyHistoryDetails", familyHistoryDetails.getSpouseDetails());

				}
			}
			
			/*
			 * Check Proposer and Spouse Applicabel
			 */
			renderRequest.setAttribute("isProposerApplicable",
					responseData.getCommonData().getIsLaPrSameYn().equalsIgnoreCase("N"));
			renderRequest.setAttribute("isSpouseApplicable",
					responseData.getCommonData().getSpouseExistYn().equalsIgnoreCase("Y"));
		} catch (Exception exception) {
			logger.error("Exception while fetching LA and Proposer Insurance Details: " + exception);
			if (logger.isDebugEnabled()) {
				exception.printStackTrace();
			}
		}
	}
	
	private String getAppTrackerUrl(ProposalJourney proposalJourney, String policyNumber) {

		String appTrackerUrl = "/application-tracker";

		try {
			logger.debug("App tracker policy no " + policyNumber);
			logger.debug("App tracker dob " + proposalJourney.getResponseData().getPersonalDetails().getLaDetails().getDob());
			
			appTrackerUrl = "/application-tracker?policynumber=" + policyNumber + "&dob="
					+ DateFormatterUtil.parseDateToSpecificFormat(proposalJourney.getResponseData().getPersonalDetails().getLaDetails().getDob(), "ddMMyyyy");
			
			logger.debug(appTrackerUrl);
		} catch (Exception e) {
			logger.error("Exception while generate App tracker Url ::::::::::::::::::::: "+e.getMessage());
		}

		return appTrackerUrl;
	}

	
	private JSONObject getEkycDetailsByTxnId(String txnId, ThemeDisplay themeDisplay, RenderRequest renderRequest, String isLaPrSps) {
		
		JSONObject ekycDetailsJson =  JSONFactoryUtil.createJSONObject();
		
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
		try {
			if(null!=txnId && txnId.length()>5) {
				logger.debug("themeDisplay PortalURL : "+themeDisplay.getPortalURL());
				
				//txnId = "2e6292f0-9843-4b60-aee3-d3dd51a298ce"; //testing
				/*ekycDetailsJson = etipCoreAPI.callGetAPI(new HashMap<>(),
						"https://uat.edelweisslife.in"
								+ ProposalFormConstants.EKYC_DETAILS_BY_TXN_ID+txnId,
						false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());*/
				 
				
				ekycDetailsJson = etipCoreAPI.callGetAPI(new HashMap<>(),
						themeDisplay.getPortalURL()
								+ ProposalFormConstants.EKYC_DETAILS_BY_TXN_ID+txnId,
						false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());
				 
				
				if(null!=ekycDetailsJson && ekycDetailsJson.length()!=0) {
					JSONArray ekycDetailsJsonArray = ekycDetailsJson.getJSONArray("items");
					
					if(ekycDetailsJsonArray.length()!=0) {
						for(int i=0;i<ekycDetailsJsonArray.length();i++) {
							if(null!=ekycDetailsJsonArray.getJSONObject(i) 
									&& ekycDetailsJsonArray.getJSONObject(i).getString("txnId").equalsIgnoreCase(txnId)) {
								
								ekycDetailsJson = ekycDetailsJsonArray.getJSONObject(i);
								ekycDetailsJson.put("isLaPrSps", isLaPrSps);
							}
						}
					} else {
						ekycDetailsJson = JSONFactoryUtil.createJSONObject();
					}					
				}
				
				renderRequest.setAttribute("ekycPerformed", "true");
			}
			
		} catch (ETIPSystemException e) {
			logger.error(e.getMessage());
			if(logger.isDebugEnabled()) {
				e.printStackTrace();
			}
			ekycDetailsJson = JSONFactoryUtil.createJSONObject();
			renderRequest.setAttribute("ekycPerformed", "true");
		} catch (Exception e) {
			logger.error(e.getMessage());
			if(logger.isDebugEnabled()) {
				e.printStackTrace();
			}
			ekycDetailsJson = JSONFactoryUtil.createJSONObject();
			renderRequest.setAttribute("ekycPerformed", "true");
		}
		
		logger.debug("ekycDetailsJson : "+ekycDetailsJson);
		return ekycDetailsJson;
		
	}
	
	public String postEkycDetails(JSONObject ekycDetailsObj, String isLaPrSps, String applicationNumber) {
		
		logger.debug("Inside postEkycDetails() ekycDetailsObj : "+ekycDetailsObj);
		String status = "";
		JSONObject requestObj = JSONFactoryUtil.createJSONObject();
		JSONObject responseObj = JSONFactoryUtil.createJSONObject();
		
		if(Validator.isNotNull(ekycDetailsObj) && ekycDetailsObj.length()!=0) {
		
			if(null!=isLaPrSps && isLaPrSps.equalsIgnoreCase("la")) {
				isLaPrSps = "1";
			} else if(null!=isLaPrSps && isLaPrSps.equalsIgnoreCase("proposer")) {
				isLaPrSps = "2";
			} else if(null!=isLaPrSps && isLaPrSps.equalsIgnoreCase("spouse")) {
				isLaPrSps = "3";
			}
			String gender="";
			
			if(ekycDetailsObj.getString("gender").equalsIgnoreCase("M")) {
				gender = "Male";
			} else if(ekycDetailsObj.getString("gender").equalsIgnoreCase("F")) {
				gender = "Female";
			} else {
				gender = "Others";
			}
			
			requestObj.put("Application_Number", Long.valueOf(applicationNumber));
			requestObj.put("Detail_Type_Of", Long.valueOf(isLaPrSps));
			requestObj.put("EKYC_ID", ekycDetailsObj.getString("txnId"));
			requestObj.put("Cust_EKYC_Link", "");
			requestObj.put("EKYC_Linkvalid_YN", "");
			requestObj.put("Status", ekycDetailsObj.getString("ekycStatus"));
			requestObj.put("EKYC_FailureReason", ekycDetailsObj.getString("ekycStatus").equalsIgnoreCase("N")?ekycDetailsObj.getString("ekycMessage"):"");
			requestObj.put("Dob", ekycDetailsObj.getString("dob"));
			requestObj.put("Gender", gender);
			requestObj.put("Name", ekycDetailsObj.getString("name"));
			requestObj.put("Country", ekycDetailsObj.getString("country"));
			requestObj.put("District", ekycDetailsObj.getString("dist"));
			requestObj.put("House", ekycDetailsObj.getString("house"));
			requestObj.put("Landmark", ekycDetailsObj.getString("lm"));
			requestObj.put("Location", ekycDetailsObj.getString("loc"));
			requestObj.put("Postal_Code", ekycDetailsObj.getString("pc"));
			requestObj.put("Post_Office", ekycDetailsObj.getString("po"));
			requestObj.put("State", ekycDetailsObj.getString("state"));
			requestObj.put("Sub_District", ekycDetailsObj.getString("subdist"));
			requestObj.put("EKYC_Photo", ekycDetailsObj.getString("photo"));
			requestObj.put("UID", ekycDetailsObj.getString("uid"));
			requestObj.put("co", ekycDetailsObj.getString("co"));
			requestObj.put("street", ekycDetailsObj.getString("street"));
			requestObj.put("vtc", ekycDetailsObj.getString("vtc"));
			requestObj.put("EKYC_CompletionDate", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
			requestObj.put("Created_By", "WEBSITE");
			
			logger.debug("requestObj : "+requestObj);
			
			String apiResponse = "";
			try {
				apiResponse = EdelweissAPIUtil.postEkycDetailsApi(requestObj);
				responseObj = JSONFactoryUtil.createJSONObject(apiResponse);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			logger.debug("apiResponse : "+apiResponse);
			logger.debug("responseObj : "+responseObj);
			if(null!=responseObj && responseObj.length()!=0) {
				status = responseObj.getString("status");
			}	
		}
		logger.debug("status : "+status);
		return status;
	}
	
	private JSONObject getCustomerInvestmentDetailsByLead(String leadId, ThemeDisplay themeDisplay) {
	    	logger.info("insdie getCustomerInvestmentDetailsByLead :::::");
			logger.info("applicationNumber :::::::::: "+leadId);
			JSONObject responseObject = JSONFactoryUtil.createJSONObject();
			EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
			StringBuilder paramBuilder = new StringBuilder();
			paramBuilder.append(LEAD_ID);
			paramBuilder.append(StringPool.SPACE);
			paramBuilder.append("eq");
			paramBuilder.append(StringPool.SPACE);
			paramBuilder.append("'" + leadId + "'");
			String parameterURL = URLEncoder.encode(paramBuilder.toString(), StandardCharsets.UTF_8).replace(" ", "%20");
			
			String customerInvestmentDetailsURL = themeDisplay.getPortalURL()  + CUSTOMER_INVESTMENT_DETAILS_BASE_URL + FILTER_QUERY_PARAM + parameterURL;
			logger.info("customerPolicyDetailsURL :::::::::::::: "+customerInvestmentDetailsURL);
			logger.info("liferayUserName :::::::::::::: "+edelweissLRBasicAuthConfiguration.getLRUsername());
			logger.info("liferayPassword :::::::::::::: "+edelweissLRBasicAuthConfiguration.getLRPassword());
			try {
				responseObject = etipCoreAPI.callGetAPI(new HashMap<>(), customerInvestmentDetailsURL, false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());
				logger.info("responseObject getCustomerInvestmentDetailsByLead : "+responseObject);
			} catch (ETIPSystemException e) {
				logger.error("ProposalServices getCustomerInvestmentDetailsByLead :::: "+e.getMessage());
			}
			return responseObject;
	}
	
	private JSONObject updateCustomerInvestmentDetailsByLead(String customerInvestmentDetailsId, ThemeDisplay themeDisplay, JSONObject params) {
    	logger.info("insdie updateCustomerInvestmentDetailsByLead :::::");
		logger.info("customerInvestmentDetailsId :::::::::: "+customerInvestmentDetailsId);
		JSONObject responseObject = JSONFactoryUtil.createJSONObject();
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
		
		String customerInvestmentDetailsUpdateURL = themeDisplay.getPortalURL()  + PUT_CUSTOMER_INVESTMENT_DETAILS_URL ;
		customerInvestmentDetailsUpdateURL = customerInvestmentDetailsUpdateURL.replace(CUSTOMER_INVESTMENT_DETAILS_ID_PARAM, customerInvestmentDetailsId);
		logger.info("customerInvestmentDetailsUpdateURL :::::::::::::: "+customerInvestmentDetailsUpdateURL);
		try {
			responseObject = etipCoreAPI.callPutAPI(params, customerInvestmentDetailsUpdateURL, false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());
			logger.info("responseObject From updateCustomerInvestmentDetailsByLead : "+responseObject);
		} catch (ETIPSystemException e) {
			logger.error("ProposalServices updateCustomerInvestmentDetailsByLead :::: "+e.getMessage());
		}
		return responseObject;
	}
	
	public JSONObject getPolicyDetailsByApplicationNumber(String applicationNumber, ThemeDisplay themeDisplay) {
		logger.info("insdie getPolicyDetailsByApplicationNumber :::::");
		logger.info("applicationNumber :::::::::: "+applicationNumber);
		JSONObject responseObject = JSONFactoryUtil.createJSONObject();
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
		StringBuilder paramBuilder = new StringBuilder();
		paramBuilder.append(APP_NUMBER);
		paramBuilder.append(StringPool.SPACE);
		paramBuilder.append("eq");
		paramBuilder.append(StringPool.SPACE);
		paramBuilder.append("'" + applicationNumber + "'");
		String parameterURL = URLEncoder.encode(paramBuilder.toString(), StandardCharsets.UTF_8).replace(" ", "%20");
		
		String customerPolicyDetailsURL = themeDisplay.getPortalURL()  + CUSTOMER_POLICY_DETAILS_BASE_URL + FILTER_QUERY_PARAM + parameterURL;
		logger.info("customerPolicyDetailsURL :::::::::::::: "+customerPolicyDetailsURL);
		logger.info("liferayUserName :::::::::::::: "+edelweissLRBasicAuthConfiguration.getLRUsername());
		logger.info("liferayPassword :::::::::::::: "+edelweissLRBasicAuthConfiguration.getLRPassword());
		try {
			responseObject = etipCoreAPI.callGetAPI(new HashMap<>(), customerPolicyDetailsURL, false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());
			logger.info("responseObject getPolicyDetailsByApplicationNumber : "+responseObject);
		} catch (ETIPSystemException e) {
			logger.error("ProposalServices getPolicyDetailsByApplicationNumber :::: "+e.getMessage());
		}
		return responseObject;
	}
	
	@Reference(unbind = "-")
	private ETIPCoreAPI etipCoreAPI;
	
	
	@Reference(unbind = "-")
	private EdelweissTokioCommonApi edelweissTokioCommonApi; 
}
