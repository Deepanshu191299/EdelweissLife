package in.edelweiss.personality.quiz.actions;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

import in.edelweiss.common.contants.ParameterConstants;
import in.edelweiss.personality.quiz.constants.PersonalityQuizPortletKeys;
import in.edelweiss.personality.quiz.util.PersonalityQuizUtil;

/**
 * 
 * @author Abhijit AA
 *
 * This Class is used to Submit the Personality Quiz Form Request to Personality API.
 */

@Component(immediate = true,
property = {
		"javax.portlet.name=" + PersonalityQuizPortletKeys.PERSONALITYQUIZ,
		"mvc.command.name=" + PersonalityQuizPortletKeys.SUBMIT_PERSONALITY_QUIZ_FORM_MVC_ACTION_COMMAND 
},
service = MVCActionCommand.class
		)
public class SubmitPersonalityQuizFormMVCActionCommand extends BaseMVCActionCommand {

	/**
	 * This method is used to submit the user entered details to Personality API.
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

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		//User Submitted Form Details.
		List<Integer> quizAnsList = new ArrayList<>();
		quizAnsList.add(ParamUtil.getInteger(actionRequest, "quiz1"));
		quizAnsList.add(ParamUtil.getInteger(actionRequest, "quiz2"));
		quizAnsList.add(ParamUtil.getInteger(actionRequest, "quiz3"));
		quizAnsList.add(ParamUtil.getInteger(actionRequest, "quiz4"));

		String persona = getPersonaFromQuiz(quizAnsList);

		String name = ParamUtil.getString(actionRequest, "name");
		String mobileNo = ParamUtil.getString(actionRequest, "mobileNo");
		String email = ParamUtil.getString(actionRequest, "email");
		String dateOfBirth = ParamUtil.getString(actionRequest, "dateOfBirth");
		String browserDetails = ParamUtil.getString(actionRequest, "browserDetails");

		JSONObject detailsJSON = getLeadGenerationJSON(name, email, mobileNo, dateOfBirth, persona, browserDetails, themeDisplay);
		logger.debug(detailsJSON);

		try 
		{
			if(Validator.isNotNull(detailsJSON)) {
				Map<String, Object> responseMap = PersonalityQuizUtil.personalityQuiz(detailsJSON.toString());

				if(Validator.isNotNull(responseMap)) {
					int status = (Integer)responseMap.get("status");
					JSONObject jsonObject = (JSONObject)responseMap.get("jsonObject");
					if(status == 200) {
						if(Validator.isNotNull(jsonObject) && jsonObject.has("status")) {
							if(jsonObject.getBoolean("status")) {
								isFormSubmittedSuccessfully = true;
								formSubmissionReponse.put("persona", persona);
							}else {
								responseData = jsonObject.getJSONArray("errors").getString(0);
							}
						}
					}else if(Validator.isNotNull(jsonObject) && jsonObject.has("message")) {
						responseData = jsonObject.getString("message");
					}
				}
			}

			formSubmissionReponse.put("isFormSubmittedSuccessfully", isFormSubmittedSuccessfully);
			formSubmissionReponse.put("responseData", responseData);

		}catch (Exception exception) {
			formSubmissionReponse.put("internalError", true);
			logger.error("Exception occured while submitting personality form : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		hideDefaultSuccessMessage(actionRequest);
		JSONPortletResponseUtil.writeJSON(actionRequest, actionResponse, formSubmissionReponse);
	}

	/**
	 * This method is used to find the personality of the user based on quiz answers.
	 * 
	 * @param quizAnsList
	 * 
	 * @return String
	 */
	protected String getPersonaFromQuiz(List<Integer> quizAnsList) {

		int maxChar = 0;
		HashMap<Integer, Integer> ansCount = new HashMap<>();
		for(Integer ans : quizAnsList) {
			if(ansCount.containsKey(ans)) {
				ansCount.put(ans, ansCount.get(ans) + 1);
			}else{
				ansCount.put(ans, 1);
			}
		}

		Integer[] valArray = ansCount.values().toArray(new Integer[0]);
		if(ansCount.size()==4 || valArray.length == 2 
				&& (valArray[0].equals(valArray[1]))) {
			maxChar = quizAnsList.get(3);
		}else {
			Set<Entry<Integer, Integer>> entrySet = ansCount.entrySet();
			int maxCount = 0;
			for (Entry<Integer, Integer> entry : entrySet){
				if (entry.getValue() > maxCount){
					maxCount = entry.getValue();
					maxChar = entry.getKey();
				}	
			}
		}
		
		if(maxChar == 1) {
			return "lion";
		}else if(maxChar == 2) {
			return "elephant";
		}else if(maxChar == 3) {
			return "deer";
		}else {
			return "hawk";
		}
	}

	/**
	 * This method is used to create the JSON required for the Lead Generation API based on the user inputs.
	 * 
	 * @param name
	 * @param email
	 * @param mobileNo
	 * @param dateOfBirth
	 * @param persona
	 * @param browserDetails
	 * 
	 * @return JSONObject
	 */
	protected JSONObject getLeadGenerationJSON(String name, String email, String mobileNo, String dateOfBirth, String persona, String browserDetails, ThemeDisplay themeDisplay) {

		JSONObject interestSessionJSON = JSONFactoryUtil.createJSONObject();
		interestSessionJSON.put("Lead_Form_Product_Category","");
		interestSessionJSON.put("Lead_Form_Product_Code","");
		interestSessionJSON.put("Persona", persona);
		interestSessionJSON.put("Recommended_Annual_Income","");
		interestSessionJSON.put("Recommended_Objective","");
		interestSessionJSON.put("Recommended_Objective_Category","");
		interestSessionJSON.put("Recommended_Product","");
		interestSessionJSON.put("Site_Section","Quiz");
		interestSessionJSON.put("Stage","Get Started");

		JSONObject comboDataJson = JSONFactoryUtil.createJSONObject();
		comboDataJson.put("Product_Code","");
		comboDataJson.put("Product_Name","");
		comboDataJson.put("Quotation_Number","");
		comboDataJson.put("SumAssured",0);

		JSONArray comboDataArray = JSONFactoryUtil.createJSONArray();
		comboDataArray.put(comboDataJson);

		JSONObject quoteJson = JSONFactoryUtil.createJSONObject();
		quoteJson.put("AB_Better_Half_Amount",0);
		quoteJson.put("AB_Better_Half_AmountWoTax",0);
		quoteJson.put("ABBetterHalfBenefit_YN","N");
		quoteJson.put("ABTopupBenefits_YN","N");
		quoteJson.put("ABTopupRate", 0);
		quoteJson.put("ADBRider_YN","N");
		quoteJson.put("ADBRiderAmount", 0);
		quoteJson.put("ADBRiderAmountWoTax",0);
		quoteJson.put("ADBRiderPPT",0);
		quoteJson.put("ADBRiderPT",0);
		quoteJson.put("ADBRiderSA",0);
		quoteJson.put("Additional_Benefits","No");
		quoteJson.put("AllRiderPremium",0);
		quoteJson.put("AllRiderPremiumWoTax",0);
		quoteJson.put("AnnuityOptions","");
		quoteJson.put("ATPDRider_YN","N");
		quoteJson.put("ATPDRiderAmount",0);
		quoteJson.put("ATPDRiderAmountWoTax",0);
		quoteJson.put("ATPDRiderPPT",0);
		quoteJson.put("ATPDRiderPT",0);
		quoteJson.put("ATPDRiderSA",0);
		quoteJson.put("BasePremiumAmount",0);
		quoteJson.put("BasePremiumAmountWoTax",0);
		quoteJson.put("BI_PDF_Path","");
		quoteJson.put("BIQuotationNumber","");
		quoteJson.put("BondFund",0);
		quoteJson.put("Child_DOB","");
		quoteJson.put("Child_Name","");
		quoteJson.put("CIRider_YN","N");
		quoteJson.put("CIRiderAmount",0);
		quoteJson.put("CIRiderAmountWoTax",0);
		quoteJson.put("CIRiderPPT",0);
		quoteJson.put("CIRiderPT",0);
		quoteJson.put("CIRiderSA",0);
		quoteJson.put("ClaimOption","");
		quoteJson.put("Combo_YN","Y");
		quoteJson.put("ComboData",comboDataArray);
		quoteJson.put("DeathBenefitOption","");
		quoteJson.put("EquityBluechipFund",0);
		quoteJson.put("EquityLargeCapFund",0);
		quoteJson.put("EquityMidCapFund",0);
		quoteJson.put("EquityTop250Fund",0);
		quoteJson.put("FamilyIncomeBenefit_YN","N");
		quoteJson.put("GiltFund",0);
		quoteJson.put("GuaranteedIncomeType","");
		quoteJson.put("HCBRider_YN","N");
		quoteJson.put("HCBRiderAmount",0);
		quoteJson.put("HCBRiderAmountWoTax",0);
		quoteJson.put("HCBRiderPPT",0);
		quoteJson.put("HCBRiderPT",0);
		quoteJson.put("HCBRiderSA",0);
		quoteJson.put("IBRider_YN","N");
		quoteJson.put("IBRiderAmount",0);
		quoteJson.put("IBRiderAmountWoTax",0);
		quoteJson.put("IBRiderPPT",0);
		quoteJson.put("IBRiderPT",0);
		quoteJson.put("IBRiderSA",0);
		quoteJson.put("InvestmentStrategy","");
		quoteJson.put("IS_LA_PR_Same_YN","Y");
		quoteJson.put("LA_Smoker_YN","N");
		quoteJson.put("LittleChampBenefit_YN","N");
		quoteJson.put("LongTermBondFund",0);
		quoteJson.put("LumpsumProportion",0);
		quoteJson.put("ManagedFund",0);
		quoteJson.put("MaturityBenefitOption","");
		quoteJson.put("MoneyMaketFund",0);
		quoteJson.put("MonthlyIncomeOptions","");
		quoteJson.put("MonthlyIncomeOptionsMonths",0);
		quoteJson.put("OpportunityPayout","");
		quoteJson.put("PaidUpAdditionsBenefit_YN","N");
		quoteJson.put("PayoutFrequency","");
		quoteJson.put("PayoutOptions","");
		quoteJson.put("PayoutPeriodYears",0);
		quoteJson.put("PebasedFund",0);
		quoteJson.put("PlanOptions","");
		quoteJson.put("PolicyOption","");
		quoteJson.put("PolicyTerm",0);
		quoteJson.put("PR_Smoker_YN","N");
		quoteJson.put("PremiumPaymentFrequency","");
		quoteJson.put("PremiumPaymentTerm",0);
		quoteJson.put("PremiumPaymentTermOption","");
		quoteJson.put("Product_Code","");
		quoteJson.put("ProductName","");
		quoteJson.put("ProductType","");
		quoteJson.put("PWBRider_YN","N");
		quoteJson.put("PWBRiderAmount",0);
		quoteJson.put("PWBRiderAmountWoTax",0);
		quoteJson.put("PWbRiderCIATPD_YN","N");
		quoteJson.put("PWBRiderDeathCIATPD_YN","N");
		quoteJson.put("PWbRiderOnDeath_YN","N");
		quoteJson.put("PWBRiderPPT",0);
		quoteJson.put("PWBRiderPT",0);
		quoteJson.put("PWBRiderSA",0);
		quoteJson.put("RisingStarBenefit_YN","N");
		quoteJson.put("RiskStrategyOptedFor","");
		quoteJson.put("Spouse_DOB","");
		quoteJson.put("Spouse_Name","");
		quoteJson.put("SpouseSmoke_YN","");
		quoteJson.put("Stage","Quiz");
		quoteJson.put("STP_YN","N");
		quoteJson.put("STPType","");
		quoteJson.put("SumAssured",0);
		quoteJson.put("SWP_YN","N");
		quoteJson.put("SWPPayable","");
		quoteJson.put("SWPWithdrawalPerAnnum","");
		quoteJson.put("TermRider_YN","N");
		quoteJson.put("TermRiderAmount",0);
		quoteJson.put("TermRiderAmountWoTax",0);
		quoteJson.put("TermRiderPPT",0);
		quoteJson.put("TermRiderPT",0);
		quoteJson.put("TermRiderSA",0);
		quoteJson.put("TotalPremiumAmount",0);
		quoteJson.put("TotalPremiumAmountWoTax",0);
		quoteJson.put("WOPBenefitAmount",0);
		quoteJson.put("WOPBenefitAmountWoTax",0);
		quoteJson.put("WOPBenefitYN","N");
		quoteJson.put("WOPRider_YN","N");
		quoteJson.put("WOPRiderAmount",0);
		quoteJson.put("WOPRiderAmountWoTax",0);
		quoteJson.put("WOPRiderPPT",0);
		quoteJson.put("WOPRiderPT",0);
		quoteJson.put("WOPRiderSA",0);

		JSONObject lmsLeadJSON = JSONFactoryUtil.createJSONObject();
		lmsLeadJSON.put("Ad_position","");
		lmsLeadJSON.put("Adgroup_id","");
		lmsLeadJSON.put("AlternatePhone","");
		lmsLeadJSON.put("AnnualIncomeSuitability","");
		lmsLeadJSON.put("BrowserVersion","");
		lmsLeadJSON.put("C_id","2");
		lmsLeadJSON.put("Campaign_id","");
		lmsLeadJSON.put("ChildDob","");
		lmsLeadJSON.put("ChildName","");
		lmsLeadJSON.put("Cp_id","13");
		lmsLeadJSON.put("Device","");
		lmsLeadJSON.put("Dob", dateOfBirth);
		lmsLeadJSON.put("Education","");
		lmsLeadJSON.put("Education_Qualification","");
		lmsLeadJSON.put("Email",email);
		lmsLeadJSON.put("FinancialGoalSuitability","");
		lmsLeadJSON.put("Gclid","");
		lmsLeadJSON.put("Gender","");
		lmsLeadJSON.put("Income","");
		lmsLeadJSON.put("Interest_Sessions",interestSessionJSON);
		lmsLeadJSON.put("LAProposerRelation","");
		lmsLeadJSON.put("Lead_Form_Type","Reccomendation");
		lmsLeadJSON.put("Location_id","");
		lmsLeadJSON.put("Married","");
		lmsLeadJSON.put("Match_type","");
		lmsLeadJSON.put("Name", name);
		lmsLeadJSON.put("Nationality","Indian");
		lmsLeadJSON.put("Nature_Of_Duty","");
		lmsLeadJSON.put("Network","");
		lmsLeadJSON.put("Occupation","");
		lmsLeadJSON.put("OtherInsuranceSuitability","");
		lmsLeadJSON.put("ParentLeadID","");
		lmsLeadJSON.put("PermanentPinCode","");
		lmsLeadJSON.put("Phone",mobileNo);
		lmsLeadJSON.put("Product","Discovery");
		lmsLeadJSON.put("ProposerLARelation","");
		lmsLeadJSON.put("Quote",quoteJson);
		lmsLeadJSON.put("Relation","");
		lmsLeadJSON.put("Relationship","");
		lmsLeadJSON.put("RequestURL","");
		lmsLeadJSON.put("RiskAppetiteSuitability","");
		lmsLeadJSON.put("Site_Section","Quiz");
		lmsLeadJSON.put("Source","EdelweissWebsales");
		lmsLeadJSON.put("SpouseDob","");
		lmsLeadJSON.put("SpouseName","");
		lmsLeadJSON.put("SpouseSmoke","");
		lmsLeadJSON.put("Sumassured","0");
		lmsLeadJSON.put("SumAssuredSuitability",0);
		lmsLeadJSON.put("Target_name","");
		lmsLeadJSON.put("Tobacco","");
		lmsLeadJSON.put("Total_Sum_Assured","");
		lmsLeadJSON.put("UserBrowser", browserDetails);
		lmsLeadJSON.put("Utm_campaign","");
		lmsLeadJSON.put("Utm_content","");
		lmsLeadJSON.put("Utm_creative","");
		lmsLeadJSON.put("Utm_medium","");
		lmsLeadJSON.put("UTM_Param1","");
		lmsLeadJSON.put("UTM_Param10","");
		lmsLeadJSON.put("UTM_Param2","");
		lmsLeadJSON.put("UTM_Param3","");
		lmsLeadJSON.put("UTM_Param4","");
		lmsLeadJSON.put("UTM_Param5","");
		lmsLeadJSON.put("UTM_Param6","");
		lmsLeadJSON.put("UTM_Param7","");
		lmsLeadJSON.put("UTM_Param8","");
		lmsLeadJSON.put("UTM_Param9","");
		lmsLeadJSON.put("UTM_Placement","");
		lmsLeadJSON.put("Utm_source","EdelweissWebsales");
		lmsLeadJSON.put("Utm_term","");
		lmsLeadJSON.put("V_id","4");
		lmsLeadJSON.put("Visitor_id","");
		lmsLeadJSON.put("Web_url",themeDisplay.getURLCurrent().substring(0, themeDisplay.getURLCurrent().indexOf("?")));

		JSONObject detailsJSON = JSONFactoryUtil.createJSONObject();
		detailsJSON.put("LMSLead", lmsLeadJSON);

		return detailsJSON;
	}

	private Log logger = LogFactoryUtil.getLog(this.getClass());

}