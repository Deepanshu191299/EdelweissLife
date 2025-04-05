package in.edelweiss.tokio.common.model;

import static in.edelweiss.tokio.constants.EdelweissCommonConstants.PRODUCT_NAME_QUOTE;

import static in.edelweiss.tokio.constants.EdelweissCommonConstants.QUOTE_STAGE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.ANNUAL_INCOME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.ASSURED_DATE_OF_BIRTH;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.ASSURED_FULL_NAME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.ASSURED_RELATION;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.CHILD_DOB;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.CHILD_NAME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.COUNTRY_CODE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.CREATE_DATE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.CREATOR;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.CUST_MARRIED;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.DATE_OF_BIRTH;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.EDUCATION_QUALIFICATION;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.EMAIL;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.EXTERNAL_REFERENCE_CODE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.FAMILY_CHILD;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.FAMILY_CHILD_DOB;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.FAMILY_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.FAMILY_SPOUSE_DOB;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.FAMILY_SPOUSE_NAME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.FAMILY_SPOUSE_SMOKE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.FULL_NAME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.GENDER;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.INCOME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.INVESTMENT_OBJECTIVE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.ISNRI;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_STATUS;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LMS_INTEREST_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LMS_QUOTE_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.MARRIED;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.MOBILE_NUMBER;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.MODIFIED_DATE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.NATURE_OF_DUTY;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.NDNC_CONSENT;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.NRI_MOBILE_NUMBER;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.OCCUPATION;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.OI_SUM_ASSURED;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.OTHER_INSURANCE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.O_PRODUCT_NAME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.O_QUOTE_STAGE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PINCODE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PRODUCT_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.RCUSTOMERFAMILYDETAILSRELCCUSTOMERENQUIRYID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.RISK_APPETITE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.SMOKE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.SMOKER;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.SPOUSE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.SPOUSE_DOB;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.SPOUSE_NAME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.SPOUSE_OCCUPATION;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.SPOUSE_SMOKE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.SPOUSE_SUMASSURED;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.STATUS;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.SUITABILITY_CONSENT;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.TOBACCO;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

import java.util.Map;

public class LiferayObjectModel {

	private LiferayObjectModel() {
	}

	public static JSONObject createLiferayRequestJSON(Map<String, String> paramMap) {

		JSONObject liferayJSON = JSONFactoryUtil.createJSONObject();
		setBasicDetails(paramMap, liferayJSON);
		setLMSDetails(paramMap, liferayJSON);
		setConsentAndSuitabilityDetails(paramMap, liferayJSON);

		liferayJSON.put(MODIFIED_DATE,
				(paramMap.containsKey(MODIFIED_DATE)) ? paramMap.get(MODIFIED_DATE) : StringPool.BLANK);
		liferayJSON.put(PINCODE, (paramMap.containsKey(PINCODE)) ? paramMap.get(PINCODE) : StringPool.BLANK);
		liferayJSON.put(PRODUCT_ID, (paramMap.containsKey(PRODUCT_ID)) ? paramMap.get(PRODUCT_ID) : StringPool.BLANK);
		liferayJSON.put(RISK_APPETITE,
				(paramMap.containsKey(RISK_APPETITE)) ? paramMap.get(RISK_APPETITE) : StringPool.BLANK);
		liferayJSON.put(SMOKER, (paramMap.containsKey(SMOKER)) ? paramMap.get(SMOKER) : StringPool.BLANK);
		
		if(paramMap.containsKey(TOBACCO)) {
			liferayJSON.put(SMOKER,(paramMap.containsKey(TOBACCO)) ? paramMap.get(TOBACCO) : StringPool.BLANK);
		}
		
		liferayJSON.put(SUITABILITY_CONSENT,
				(paramMap.containsKey(SUITABILITY_CONSENT)) ? Boolean.TRUE : StringPool.BLANK);
		liferayJSON.put(FAMILY_ID, (paramMap.containsKey(FAMILY_ID)) ? paramMap.get(FAMILY_ID) : StringPool.BLANK);
		liferayJSON.put(O_PRODUCT_NAME,
				(paramMap.containsKey(PRODUCT_NAME_QUOTE)) ? paramMap.get(PRODUCT_NAME_QUOTE) : StringPool.BLANK);
		liferayJSON.put(O_QUOTE_STAGE,
				(paramMap.containsKey(QUOTE_STAGE)) ? paramMap.get(QUOTE_STAGE) : StringPool.BLANK);
		return liferayJSON;
	}

	private static void setConsentAndSuitabilityDetails(Map<String, String> paramMap, JSONObject liferayJSON) {
		liferayJSON.put(INVESTMENT_OBJECTIVE,
				(paramMap.containsKey(INVESTMENT_OBJECTIVE)) ? paramMap.get(INVESTMENT_OBJECTIVE) : StringPool.BLANK);
		liferayJSON.put(ISNRI, (paramMap.containsKey(ISNRI)) ? Boolean.TRUE : StringPool.BLANK);
		liferayJSON.put(NATURE_OF_DUTY,
				(paramMap.containsKey(NATURE_OF_DUTY)) ? paramMap.get(NATURE_OF_DUTY) : StringPool.BLANK);
		liferayJSON.put(NDNC_CONSENT, (paramMap.containsKey(NDNC_CONSENT)) ? Boolean.TRUE : StringPool.BLANK);
		liferayJSON.put(NRI_MOBILE_NUMBER,
				(paramMap.containsKey(NRI_MOBILE_NUMBER)) ? paramMap.get(NRI_MOBILE_NUMBER) : StringPool.BLANK);
		liferayJSON.put(OCCUPATION, (paramMap.containsKey(OCCUPATION)) ? paramMap.get(OCCUPATION) : StringPool.BLANK);
		liferayJSON.put(OI_SUM_ASSURED,
				(paramMap.containsKey(OI_SUM_ASSURED)) ? paramMap.get(OI_SUM_ASSURED) : StringPool.BLANK);
		liferayJSON.put(OTHER_INSURANCE,
				(paramMap.containsKey(OTHER_INSURANCE)) ? paramMap.get(OTHER_INSURANCE) : StringPool.BLANK);
	}

	private static void setLMSDetails(Map<String, String> paramMap, JSONObject liferayJSON) {
		liferayJSON.put(LEAD_ID, (paramMap.containsKey(LEAD_ID)) ? paramMap.get(LEAD_ID) : StringPool.BLANK);
		liferayJSON.put(LEAD_STATUS,
				(paramMap.containsKey(LEAD_STATUS)) ? paramMap.get(LEAD_STATUS) : StringPool.BLANK);
		liferayJSON.put(LMS_INTEREST_ID,
				(paramMap.containsKey(LMS_INTEREST_ID)) ? paramMap.get(LMS_INTEREST_ID) : StringPool.BLANK);
		liferayJSON.put(LMS_QUOTE_ID,
				(paramMap.containsKey(LMS_QUOTE_ID)) ? paramMap.get(LMS_QUOTE_ID) : StringPool.BLANK);
	}

	private static void setBasicDetails(Map<String, String> paramMap, JSONObject liferayJSON) {
		liferayJSON.put(CREATOR, "Test Test");
		liferayJSON.put(EXTERNAL_REFERENCE_CODE, paramMap.get(EXTERNAL_REFERENCE_CODE));
		liferayJSON.put(ID, "");
		//liferayJSON.put(STATUS, "Approved");
		liferayJSON.put(ANNUAL_INCOME,
				(paramMap.containsKey(ANNUAL_INCOME)) ? paramMap.get(ANNUAL_INCOME) : StringPool.BLANK);
		
		liferayJSON.put(COUNTRY_CODE,
				(paramMap.containsKey(COUNTRY_CODE)) ? paramMap.get(COUNTRY_CODE) : StringPool.BLANK);
		liferayJSON.put(CREATE_DATE,
				(paramMap.containsKey(CREATE_DATE)) ? paramMap.get(CREATE_DATE) : StringPool.BLANK);
		liferayJSON.put(DATE_OF_BIRTH,
				(paramMap.containsKey(DATE_OF_BIRTH)) ? paramMap.get(DATE_OF_BIRTH) : StringPool.BLANK);
		liferayJSON.put(EDUCATION_QUALIFICATION,
				(paramMap.containsKey(EDUCATION_QUALIFICATION)) ? paramMap.get(EDUCATION_QUALIFICATION)
						: StringPool.BLANK);
		liferayJSON.put(EMAIL, (paramMap.containsKey(EMAIL)) ? paramMap.get(EMAIL) : StringPool.BLANK);
		liferayJSON.put(FULL_NAME, (paramMap.containsKey(FULL_NAME)) ? paramMap.get(FULL_NAME) : StringPool.BLANK);
		liferayJSON.put(GENDER, (paramMap.containsKey(GENDER)) ? paramMap.get(GENDER) : StringPool.BLANK);
		liferayJSON.put(MOBILE_NUMBER,
				(paramMap.containsKey(MOBILE_NUMBER)) ? paramMap.get(MOBILE_NUMBER) : StringPool.BLANK);
		liferayJSON.put(CUST_MARRIED,(paramMap.containsKey(MARRIED)) ? paramMap.get(MARRIED) : paramMap.getOrDefault(CUST_MARRIED, StringPool.BLANK));
		liferayJSON.put(FAMILY_CHILD, (paramMap.containsKey(CHILD_NAME)) ? paramMap.get(CHILD_NAME) : paramMap.getOrDefault(FAMILY_CHILD, StringPool.BLANK));
		
		if(paramMap.containsKey(INCOME)) {
			liferayJSON.put(ANNUAL_INCOME,(paramMap.containsKey(INCOME)) ? paramMap.get(INCOME) : StringPool.BLANK);
		}
		
		liferayJSON.put(SPOUSE, (paramMap.containsKey(SPOUSE)) ? paramMap.get(SPOUSE):StringPool.BLANK);
		liferayJSON.put(SPOUSE_OCCUPATION, (paramMap.containsKey(SPOUSE_OCCUPATION)) ? paramMap.get(SPOUSE_OCCUPATION): StringPool.BLANK);
		liferayJSON.put(SPOUSE_SUMASSURED, (paramMap.containsKey(SPOUSE_SUMASSURED)) ? paramMap.get(SPOUSE_SUMASSURED) : StringPool.BLANK);
		
	}

	public static JSONObject createLiferayFamilyJSON(Map<String, String> familyParam) {
		JSONObject liferayFamilyJSON = JSONFactoryUtil.createJSONObject();
		liferayFamilyJSON.put(CREATOR, "Test Test");
		liferayFamilyJSON.put(EXTERNAL_REFERENCE_CODE, familyParam.get(EXTERNAL_REFERENCE_CODE));
		liferayFamilyJSON.put(ID, "");
		//liferayFamilyJSON.put(STATUS, "Approved");
		liferayFamilyJSON.put(CREATE_DATE,
				(familyParam.containsKey(CREATE_DATE)) ? familyParam.get(CREATE_DATE) : StringPool.BLANK);
		liferayFamilyJSON.put(LEAD_ID,
				(familyParam.containsKey(LEAD_ID)) ? familyParam.get(LEAD_ID) : StringPool.BLANK);
		liferayFamilyJSON.put(ASSURED_FULL_NAME,
				(familyParam.containsKey(ASSURED_FULL_NAME)) ? familyParam.get(ASSURED_FULL_NAME) : StringPool.BLANK);
		liferayFamilyJSON.put(ASSURED_DATE_OF_BIRTH,
				(familyParam.containsKey(ASSURED_DATE_OF_BIRTH)) ? familyParam.get(ASSURED_DATE_OF_BIRTH)
						: StringPool.BLANK);
		liferayFamilyJSON.put(GENDER, (familyParam.containsKey(GENDER)) ? familyParam.get(GENDER) : StringPool.BLANK);
		liferayFamilyJSON.put(ASSURED_RELATION,
				(familyParam.containsKey(ASSURED_RELATION)) ? familyParam.get(ASSURED_RELATION) : StringPool.BLANK);
		liferayFamilyJSON.put(SMOKE, (familyParam.containsKey(SMOKE)) ? familyParam.get(SMOKE) : StringPool.BLANK);
		
		liferayFamilyJSON.put(FAMILY_SPOUSE_NAME, (familyParam.containsKey(SPOUSE_NAME)) ? familyParam.get(SPOUSE_NAME) : StringPool.BLANK);
		liferayFamilyJSON.put(FAMILY_SPOUSE_DOB, (familyParam.containsKey(SPOUSE_DOB)) ? familyParam.get(SPOUSE_DOB) : StringPool.BLANK);
		liferayFamilyJSON.put(FAMILY_SPOUSE_SMOKE, (familyParam.containsKey(SPOUSE_SMOKE)) ? familyParam.get(SPOUSE_SMOKE) : StringPool.BLANK);
		liferayFamilyJSON.put(FAMILY_CHILD_DOB, (familyParam.containsKey(CHILD_DOB)) ? familyParam.get(CHILD_DOB) : StringPool.BLANK);
		
		liferayFamilyJSON.put(RCUSTOMERFAMILYDETAILSRELCCUSTOMERENQUIRYID,
				familyParam.containsKey(RCUSTOMERFAMILYDETAILSRELCCUSTOMERENQUIRYID)
						? familyParam.get(RCUSTOMERFAMILYDETAILSRELCCUSTOMERENQUIRYID)
						: StringPool.BLANK);
		return liferayFamilyJSON;

	}
	
}
