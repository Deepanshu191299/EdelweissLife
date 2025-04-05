package in.edelweiss.raise.a.claim.actions;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

import in.edelweiss.common.contants.ParameterConstants;
import in.edelweiss.raise.a.claim.constants.RaiseAClaimPortletKeys;
import in.edelweiss.raise.a.claim.util.RaiseAClaimApiUtil;

/**
 * 
 * @author Abhijit Ande
 *
 * This Class is used to Submit the Raise A Claim Request to Claim Intimation API.
 */

@Component(immediate = true,
property = {
		"javax.portlet.name=" + RaiseAClaimPortletKeys.RAISEACLAIM,
		"mvc.command.name=" + RaiseAClaimPortletKeys.SUBMIT_RAISE_A_CLAIM_FORM_MVC_ACTION_COMMAND 
},
service = MVCActionCommand.class
		)
public class SubmitRaiseAClaimFormMVCActionCommand extends BaseMVCActionCommand {

	/**
	 * This method is used to submit the user entered details to Claim Intimation API.
	 * 
	 * @param actionRequest
	 * @param actionResponse
	 * @return void
	 */
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		JSONObject formSubmissionReponse = JSONFactoryUtil.createJSONObject();
		boolean isFormSubmittedSuccessfully = false;
		String responseData = StringPool.BLANK;

		//User Submitted Form Details.
		String name = ParamUtil.getString(actionRequest, ParameterConstants.NAME);
		String mobileNo = ParamUtil.getString(actionRequest, ParameterConstants.MOBILE_NO);
		String policyNo = ParamUtil.getString(actionRequest, ParameterConstants.POLICY_NUMBER);
		String email = ParamUtil.getString(actionRequest, ParameterConstants.EMAIL);
		String addressOne = ParamUtil.getString(actionRequest, ParameterConstants.ADDRESS_1);
		String addressLineTwo = ParamUtil.getString(actionRequest, ParameterConstants.ADDRESS_LINE_2);
		String addressLineThree = ParamUtil.getString(actionRequest, ParameterConstants.ADDRESS_LINE_3);
		String pinCode = ParamUtil.getString(actionRequest, ParameterConstants.PIN_CODE);
		String city = ParamUtil.getString(actionRequest, ParameterConstants.CITY);
		String state = ParamUtil.getString(actionRequest, ParameterConstants.STATE);
		String bankAccountNo = ParamUtil.getString(actionRequest, ParameterConstants.BANK_ACCOUNT_NUMBER);
		String bankName = ParamUtil.getString(actionRequest, ParameterConstants.BANK_NAME);
		String bankAccountIFSC = ParamUtil.getString(actionRequest, ParameterConstants.BANK_ACCOUNT_IFSC);
		
		String deathCertificateId = ParamUtil.getString(actionRequest, ParameterConstants.DEATH_CERTIFICATE_ID);
		String addressProofId = ParamUtil.getString(actionRequest, ParameterConstants.ADDRESS_PROOF_ID);
		String addressProofOneId = ParamUtil.getString(actionRequest, ParameterConstants.ADDRESS_PROOF_ONE_ID);
		String addressProofTwoId = ParamUtil.getString(actionRequest, ParameterConstants.ADDRESS_PROOF_TWO_ID);
		String validIDProofsId = ParamUtil.getString(actionRequest,ParameterConstants.VALID_ID_PROOFS_ID);
		String recentPhotoId = ParamUtil.getString(actionRequest,ParameterConstants.RECENT_PHOTO_ID);
		String cancelledChequeId = ParamUtil.getString(actionRequest, ParameterConstants.CANCELLED_CHEQUE_ID);
		String policyBondId = ParamUtil.getString(actionRequest, ParameterConstants.POLICY_BOND_ID);
		String doctorsCertificateId = ParamUtil.getString(actionRequest, ParameterConstants.DOCTORS_CERTIFICATE_ID);
		String causeOfDeathId = ParamUtil.getString(actionRequest, ParameterConstants.CAUSE_OF_DEATH_ID);
		String policeFirId = ParamUtil.getString(actionRequest, ParameterConstants.POLICE_FIR_ID);
		String postMortemReportId = ParamUtil.getString(actionRequest, ParameterConstants.POST_MORTEM_REPORT_ID);
		String panchnamaId = ParamUtil.getString(actionRequest, ParameterConstants.PANCHNAMA_ID);

		JSONObject detailsJSON = JSONFactoryUtil.createJSONObject();
		detailsJSON.put(ParameterConstants.NOMINEE_NAME_JSON, name);
		detailsJSON.put(ParameterConstants.NOMINEE_NUMBER_JSON, mobileNo);
		detailsJSON.put(ParameterConstants.POLICY_NUMBER_JSON, policyNo);
		detailsJSON.put(ParameterConstants.ACTIVE_JSON, 0);
		detailsJSON.put(ParameterConstants.USER_IP_JSON, "string");
		detailsJSON.put(ParameterConstants.USER_ID_JSON, 0);
		detailsJSON.put(ParameterConstants.EMAIL_ID_JSON, email);
		detailsJSON.put(ParameterConstants.ADDRESS_1_JSON, addressOne);
		detailsJSON.put(ParameterConstants.ADDRESS_2_JSON, addressLineTwo);
		detailsJSON.put(ParameterConstants.ADDRESS_3_JSON, addressLineThree);
		detailsJSON.put(ParameterConstants.CITY_JSON, city);
		detailsJSON.put(ParameterConstants.STATE_JSON, state);
		detailsJSON.put(ParameterConstants.PINCODE_JSON, pinCode);
		detailsJSON.put(ParameterConstants.ACCOUNT_NUMBER_JSON, bankAccountNo);
		detailsJSON.put(ParameterConstants.BANK_NAME_JSON, bankName);
		detailsJSON.put(ParameterConstants.IFSC_CODE_JSON, bankAccountIFSC);
		detailsJSON.put(ParameterConstants.IS_INDIAN_RESIDENT_JSON, 0);
		detailsJSON.put(ParameterConstants.IS_ANOTHER_RESIDENT_JSON, 0);

		String claimType = ParamUtil.getString(actionRequest, ParameterConstants.CLAIM_TYPE);
		
		if(claimType.equalsIgnoreCase(ParameterConstants.DEATH_CLAIM_CATEGORY_NAME)) {
			
			String dateOfDeath = ParamUtil.getString(actionRequest, ParameterConstants.DATE_OF_DEATH);
			String placeOfDeath = ParamUtil.getString(actionRequest, ParameterConstants.PLACE_OF_DEATH);
			String isNaturalDeath = ParamUtil.getString(actionRequest, ParameterConstants.IS_NATURAL_DEATH);
			String causeOfDeath = ParamUtil.getString(actionRequest, ParameterConstants.CAUSE_OF_DEATH_TEXT);
			String policyHolderDrName = ParamUtil.getString(actionRequest, ParameterConstants.POLICY_HOLDER_TREATING_DOCTOR_NAME);
			String policyHolderDrAddress = ParamUtil.getString(actionRequest, ParameterConstants.POLICY_HOLDER_TREATING_DOCTOR_ADDRESS);
			String policyHolderDrMobNo = ParamUtil.getString(actionRequest, ParameterConstants.POLICY_HOLDER_TREATING_DOCTOR_MOB_NO);
			
			detailsJSON.put(ParameterConstants.DATE_OF_DEATH_JSON, dateOfDeath);
			detailsJSON.put(ParameterConstants.CAUSE_OF_DEATH_JSON, causeOfDeath);
			detailsJSON.put(ParameterConstants.PLACE_OF_DEATH_JSON, placeOfDeath);
			detailsJSON.put(ParameterConstants.DOCTOR_NAME_JSON, policyHolderDrName);
			detailsJSON.put(ParameterConstants.DOCTOR_ADDRESS_JSON, policyHolderDrAddress);
			detailsJSON.put(ParameterConstants.DOCTOR_CONTACT_NUMBER_JSON, policyHolderDrMobNo);
			detailsJSON.put(ParameterConstants.DEATH_TYPE_JSON, isNaturalDeath);
		}
		
		detailsJSON.put(ParameterConstants.DEATH_CERTIFICATE_ID_JSON, deathCertificateId);
		detailsJSON.put(ParameterConstants.ADDRESS_PROOF_ID_JSON, addressProofId);
		detailsJSON.put(ParameterConstants.ADDRESS_PROOF_DOC_1_ID_JSON, addressProofOneId);
		detailsJSON.put(ParameterConstants.ADDRESS_PROOF_DOC_2_ID_JSON, addressProofTwoId);
		detailsJSON.put(ParameterConstants.VALID_ID_PROOFS_ID_JSON, validIDProofsId);
		detailsJSON.put(ParameterConstants.RECENT_PHOTO_ID_JSON, recentPhotoId);
		detailsJSON.put(ParameterConstants.CANCELLED_CHEQUE_ID_JSON, cancelledChequeId);
		detailsJSON.put(ParameterConstants.POLICY_BOND_ID_JSON, policyBondId);
		detailsJSON.put(ParameterConstants.DOCTOR_CERTIFICATE_ID_JSON, doctorsCertificateId);
		detailsJSON.put(ParameterConstants.TREATMENT_RECORD_ID_JSON, "string");
		detailsJSON.put(ParameterConstants.CAUSE_OF_DEATH_BY_JSON, causeOfDeathId);
		detailsJSON.put(ParameterConstants.FIR_FILE_ID_JSON, policeFirId);
		detailsJSON.put(ParameterConstants.POST_MORTEM_FILE_ID_JSON, postMortemReportId);
		detailsJSON.put(ParameterConstants.FI_FILE_ID_JSON, "string");
		detailsJSON.put(ParameterConstants.PANCHNAMA_FILE_ID_JSON, panchnamaId);
		detailsJSON.put(ParameterConstants.PAPPER_CUTTING_FILE_ID_JSON, "string");
		logger.debug(detailsJSON);
		
		try 
		{
			if(Validator.isNotNull(detailsJSON)) {
				Map<String, Object> responseMap = RaiseAClaimApiUtil.claimIntimation(detailsJSON.toString());

				if(Validator.isNotNull(responseMap)) {
					int status = (Integer)responseMap.get("status");
					JSONObject jsonObject = (JSONObject)responseMap.get("jsonObject");

					if(status == 200) {
						if(Validator.isNotNull(jsonObject) && jsonObject.has("status")) {
							boolean jsonStatus = jsonObject.getBoolean("status");

							if(jsonStatus == true) {
								responseData = jsonObject.getString("responseData");
								isFormSubmittedSuccessfully = true;
							}
						}
					}else if(Validator.isNotNull(jsonObject) && jsonObject.has("errors")) {
						responseData = jsonObject.getString("errors");
					}else if(Validator.isNotNull(jsonObject) && jsonObject.has("message")) {
						responseData = jsonObject.getString("message");
					}
				}
			}
			
			formSubmissionReponse.put("isFormSubmittedSuccessfully", isFormSubmittedSuccessfully);
			formSubmissionReponse.put("responseData", responseData);
			logger.info("responseData of claims----------------->> "+responseData);
		}catch (Exception exception) {
			formSubmissionReponse.put("internalError", true);
			logger.error("Exception occured while submitting raise a claim form : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		hideDefaultSuccessMessage(actionRequest);
		JSONPortletResponseUtil.writeJSON(actionRequest, actionResponse, formSubmissionReponse);
	}

	private Log logger = LogFactoryUtil.getLog(this.getClass());

}