package in.edelweiss.proposal.form.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.PortletRequestDispatcher;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.common.util.DateFormatterUtil;
import in.edelweiss.common.util.EdelweissAPIUtil;
import in.edelweiss.proposal.form.constants.ProposalFormPortletKeys;
import in.edelweiss.proposal.form.pf.model.CommonData;
import in.edelweiss.proposal.form.pf.model.CommunicationDetails;
import in.edelweiss.proposal.form.pf.model.CovidQuestionDetails;
import in.edelweiss.proposal.form.pf.model.EmploymentDetails;
import in.edelweiss.proposal.form.pf.model.FamilyHistoryDetails;
import in.edelweiss.proposal.form.pf.model.InsuranceHistoryDetails;
import in.edelweiss.proposal.form.pf.model.LifeStyleDetails;
import in.edelweiss.proposal.form.pf.model.MedicalQuestionDetails;
import in.edelweiss.proposal.form.pf.model.PersonalDetails;
import in.edelweiss.proposal.form.pf.model.ProposalJourney;
import in.edelweiss.proposal.form.pf.model.ResponseData;

@Component(property = { "javax.portlet.name=" + ProposalFormPortletKeys.PROPOSALFORM,
		"mvc.command.name=/pf/render_pf_form" }, service = MVCResourceCommand.class)
public class PFMVCResourceCommand extends BaseMVCResourceCommand {

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

		String applicationNumber = "523171104121";

		if (Validator.isNull(applicationNumber) || applicationNumber.isBlank()) {
			applicationNumber = "523171104121";
		}

		logger.debug("applicationNumber >>>>>>>>>>>>>>>>>>>>>>>>> " + applicationNumber);

		String pfURL = ConfigurationUtil.getProposalFormAPIURLConfiguration().getPFDetailsURL() + "?applicationNumber="
				+ applicationNumber;
		logger.debug("pfURL... "+pfURL);
		String pfResponse = EdelweissAPIUtil.callPJGetAPI(pfURL);
		logger.debug("pfResponse... "+pfResponse);

		ProposalJourney proposalJourney = objectMapper.readValue(pfResponse, ProposalJourney.class);

		logger.debug("proposalJourney >>>>>>>>>>>>>>>>>>>>>>>>> " + proposalJourney);

		// CommonData
		CommonData commonDetails = proposalJourney.getResponseData().getCommonData();

		if (commonDetails.getApplicationStage() == 2 && commonDetails.getPaymentStatus().equals("N")) {
			resourceRequest.setAttribute("payment_url",
					proposalJourney.getResponseData().getPaymentDetails().getPaymentUrl());

		} else if (commonDetails.getApplicationStage() == 2 && commonDetails.getPushToLms().equals("Y")) {
			resourceRequest.setAttribute("productDetails", proposalJourney.getResponseData().getProductDetails());
			resourceRequest.setAttribute("commonDetails", commonDetails);

		} else if (commonDetails.getApplicationStage() > 2) {

		}

		resourceRequest.setAttribute("commonDetails", commonDetails);
		resourceRequest.setAttribute("isLongForm", commonDetails.getFormType().equalsIgnoreCase("COM00"));

		// Get PF Details Response for JS Request Creation
		resourceRequest.setAttribute("pfResponse", pfResponse);

		// PersonalDetails
		PersonalDetails personalDetails = proposalJourney.getResponseData().getPersonalDetails();
		resourceRequest.setAttribute("laPersonalDetails", personalDetails.getLaDetails());
		resourceRequest.setAttribute("proposerPersonalDetails", personalDetails.getProposerDetails());
		resourceRequest.setAttribute("spousePersonalDetails", personalDetails.getSpouseDetails());

		// Condition to check related to LA for field population
		boolean isLaMinor = true;
		boolean isInfant = true;
		try {
			if (Validator.isNotNull(personalDetails.getLaDetails())
					&& Validator.isNotNull(personalDetails.getLaDetails().getDob())
					&& !personalDetails.getLaDetails().getDob().isBlank()) {

				int laAge = DateFormatterUtil.calculateAge(personalDetails.getLaDetails().getDob());

				if (laAge == -1) {
					personalDetails.getLaDetails().setDob(StringPool.BLANK);
				}

				isLaMinor = laAge > 18 ? false : true;
				isInfant = laAge > 5 ? false : true;
			}
			resourceRequest.setAttribute("isLaMinor", isLaMinor);
			resourceRequest.setAttribute("isInfant", isInfant);

			if (Validator.isNotNull(personalDetails.getProposerDetails())
					&& Validator.isNotNull(personalDetails.getProposerDetails().getDob())
					&& !personalDetails.getProposerDetails().getDob().isBlank()) {

				int proposerAge = DateFormatterUtil.calculateAge(personalDetails.getProposerDetails().getDob());

				if (proposerAge == -1) {
					personalDetails.getProposerDetails().setDob(StringPool.BLANK);
				}
			}

			if (Validator.isNotNull(personalDetails.getSpouseDetails())
					&& Validator.isNotNull(personalDetails.getSpouseDetails().getDob())
					&& !personalDetails.getSpouseDetails().getDob().isBlank()) {

				int spouseAge = DateFormatterUtil.calculateAge(personalDetails.getSpouseDetails().getDob());

				if (spouseAge == -1) {
					personalDetails.getSpouseDetails().setDob(StringPool.BLANK);
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		// CommunicationDetails
		CommunicationDetails communicationDetails = proposalJourney.getResponseData().getCommunicationDetails();
		resourceRequest.setAttribute("laCommunicationDetails", communicationDetails.getLaDetails());
		resourceRequest.setAttribute("proposerCommunicationDetails", communicationDetails.getProposerDetails());
		resourceRequest.setAttribute("spouseCommunicationDetails", communicationDetails.getSpouseDetails());

		// EmploymentDetails
		EmploymentDetails employmentDetails = proposalJourney.getResponseData().getEmploymentDetails();
		resourceRequest.setAttribute("laEmploymentDetails", employmentDetails.getLaDetails());
		resourceRequest.setAttribute("proposerEmploymentDetails", employmentDetails.getProposerDetails());
		resourceRequest.setAttribute("spouseEmploymentDetails", employmentDetails.getSpouseDetails());
		// LifeStyleDetails
		LifeStyleDetails lifeStyleDetails = proposalJourney.getResponseData().getLifeStyleDetails();
		resourceRequest.setAttribute("lifeStyleDetails", lifeStyleDetails);
		resourceRequest.setAttribute("laLifeStyleDetails", lifeStyleDetails.getLaDetails());
		resourceRequest.setAttribute("ProposerLifeStyleDetails", lifeStyleDetails.getProposerDetails());
		resourceRequest.setAttribute("spouseLifeStyleDetails", lifeStyleDetails.getSpouseDetails());

		if (lifeStyleDetails.getProposerDetails() != null) {
			resourceRequest.setAttribute("insurerDetails", lifeStyleDetails.getProposerDetails());
		} else if (lifeStyleDetails.getSpouseDetails() != null) {
			resourceRequest.setAttribute("insurerDetails", lifeStyleDetails.getSpouseDetails());
		}

		// medical question details
		MedicalQuestionDetails medicalQuestionDetails = proposalJourney.getResponseData().getMedicalQuestionDetails();
		resourceRequest.setAttribute("medicalQuestionDetails", medicalQuestionDetails);
		resourceRequest.setAttribute("laMedicalQuestionDetails", medicalQuestionDetails.getLaDetails());
		resourceRequest.setAttribute("proposerMedicalQuestionDetails", medicalQuestionDetails.getProposerDetails());
		resourceRequest.setAttribute("spouseMedicalQuestionDetails", medicalQuestionDetails.getSpouseDetails());

		// API Endpoint URL
		resourceRequest.setAttribute("getCKycDetailsURL",
				ConfigurationUtil.getProposalFormAPIURLConfiguration().getCKycDetailsURL());
		resourceRequest.setAttribute("getBankDetailsIFSCURL",
				ConfigurationUtil.getProposalFormAPIURLConfiguration().getBankDetailsIFSCURL());
		resourceRequest.setAttribute("postCKycDetailsURL",
				ConfigurationUtil.getProposalFormAPIURLConfiguration().getPostCKycDetailsURL());
		resourceRequest.setAttribute("sendOTPURL",
				ConfigurationUtil.getProposalFormAPIURLConfiguration().getSendOTPURL());
		resourceRequest.setAttribute("verifyOTPURL",
				ConfigurationUtil.getProposalFormAPIURLConfiguration().getValidateOTPURL());
		resourceRequest.setAttribute("createPaymentLinkURL",
				ConfigurationUtil.getProposalFormAPIURLConfiguration().getCreatePaymentLinkURL());
		resourceRequest.setAttribute("savePFDetailsURL",
				ConfigurationUtil.getProposalFormAPIURLConfiguration().getSavePFDetailsURL());
		resourceRequest.setAttribute("savethankyouURL",
				ConfigurationUtil.getProposalFormAPIURLConfiguration().getSavethankyouURL());
		resourceRequest.setAttribute("autoSaveInterval",
				ConfigurationUtil.getProposalFormAPIURLConfiguration().getAutoSaveIntervalURL());
		resourceRequest.setAttribute("totalAutoSaveInterval",
				ConfigurationUtil.getProposalFormAPIURLConfiguration().getAutoSaveTotalIntervalURL());

		/*
		 * Other Details Section
		 */
		getInsuranceHistoryDetails(proposalJourney.getResponseData(), resourceRequest);

		// Covid19 Details
		CovidQuestionDetails covidQuestionDetails = proposalJourney.getResponseData().getCovidQuestionDetails();
		if (Validator.isNotNull(covidQuestionDetails)) {

			resourceRequest.setAttribute("covidQuestionDetails", covidQuestionDetails);
			resourceRequest.setAttribute("laCovidDetails", covidQuestionDetails.getLaDetails());
			if (Validator.isNotNull(covidQuestionDetails.getProposerDetails())
					&& commonDetails.getIsLaPrSameYn().equals("N")) {
				resourceRequest.setAttribute("prSpCovidDetails", covidQuestionDetails.getProposerDetails());
			} else if (Validator.isNotNull(covidQuestionDetails.getSpouseDetails())
					&& commonDetails.getSpouseExistYn().equals("Y")) {
				resourceRequest.setAttribute("prSpCovidDetails", covidQuestionDetails.getSpouseDetails());
			}
		}

		PortletRequestDispatcher portletRequestDispatcher = getPortletRequestDispatcher(resourceRequest,
				"/proposal_form/proposal_form.jsp");

		portletRequestDispatcher.include(resourceRequest, resourceResponse);

	}

	/*
	 * Get LA/PR/SP Insurance History and Other Details
	 */
	private void getInsuranceHistoryDetails(ResponseData responseData, ResourceRequest resourceRequest) {

		try {

			/*
			 * Insurance History Details
			 */
			InsuranceHistoryDetails insuranceHistoryDetails = responseData.getInsuranceHistoryDetails();
			if (Validator.isNotNull(insuranceHistoryDetails)) {
				resourceRequest.setAttribute("laInsuranceHistoryDetails", insuranceHistoryDetails.getLaDetails());

				if (Validator.isNotNull(insuranceHistoryDetails.getProposerDetails())) {
					resourceRequest.setAttribute("prSpInsuranceHistoryDetails",
							insuranceHistoryDetails.getProposerDetails());
					resourceRequest.setAttribute("isProposerApplicable", true);
				} else if (Validator.isNotNull(insuranceHistoryDetails.getSpouseDetails())) {
					resourceRequest.setAttribute("prSpInsuranceHistoryDetails",
							insuranceHistoryDetails.getSpouseDetails());
					resourceRequest.setAttribute("isSpouseApplicable", true);
				}
			}

			/*
			 * Family History Details
			 */
			FamilyHistoryDetails familyHistoryDetails = responseData.getFamilyHistoryDetails();
			if (Validator.isNotNull(familyHistoryDetails)) {
				resourceRequest.setAttribute("laFamilyHistoryDetails", familyHistoryDetails.getLaDetails());

				if (Validator.isNotNull(familyHistoryDetails.getProposerDetails())) {
					resourceRequest.setAttribute("prSpFamilyHistoryDetails", familyHistoryDetails.getProposerDetails());

				} else if (Validator.isNotNull(familyHistoryDetails.getSpouseDetails())) {
					resourceRequest.setAttribute("prSpFamilyHistoryDetails", familyHistoryDetails.getSpouseDetails());

				}
			}
		} catch (Exception exception) {
			logger.error("Exception while fetching LA and Proposer Insurance Details: " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				exception.printStackTrace();
			}
		}
	}

	private static final Log logger = LogFactoryUtil.getLog(ProposalFormMVCRenderCommand.class);
}
