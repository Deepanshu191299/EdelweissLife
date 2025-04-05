package in.edelweiss.tokio.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "actions", "creator", "dateCreated", "dateModified", "externalReferenceCode", "id", "status",
		"leadStatus", "annualIncome", "occupation", "gender", "mobileNumber", "smoker", "countryCode",
		"nRIMobileNumber", "otherInsurance", "lmsQuoteId", "email", "leadId", "pincode", "productId", "isNRI",
		"natureOfDuty", "fullName", "investmentObjective", "suitabilityConsent", "dateOfBirth", "familyId",
		"nDNCConsent", "riskAppetite", "educationQualification", "lmsInterestId", "oISumAssured" })
public class Item {

	@JsonProperty("dateCreated")
	private String dateCreated;
	@JsonProperty("dateModified")
	private String dateModified;
	@JsonProperty("externalReferenceCode")
	private String externalReferenceCode;
	@JsonProperty("id")
	private Integer id;
	@JsonProperty("leadStatus")
	private String leadStatus;
	@JsonProperty("annualIncome")
	private String annualIncome;
	@JsonProperty("occupation")
	private String occupation;
	@JsonProperty("gender")
	private String gender;
	@JsonProperty("mobileNumber")
	private String mobileNumber;
	@JsonProperty("smoker")
	private String smoker;
	@JsonProperty("countryCode")
	private String countryCode;
	@JsonProperty("nRIMobileNumber")
	private String nRIMobileNumber;
	@JsonProperty("otherInsurance")
	private String otherInsurance;
	@JsonProperty("lmsQuoteId")
	private String lmsQuoteId;
	@JsonProperty("email")
	private String email;
	@JsonProperty("leadId")
	private String leadId;
	@JsonProperty("pincode")
	private String pincode;
	@JsonProperty("productId")
	private String productId;
	@JsonProperty("isNRI")
	private Boolean isNRI;
	@JsonProperty("natureOfDuty")
	private String natureOfDuty;
	@JsonProperty("fullName")
	private String fullName;
	@JsonProperty("investmentObjective")
	private String investmentObjective;
	@JsonProperty("suitabilityConsent")
	private Boolean suitabilityConsent;
	@JsonProperty("dateOfBirth")
	private String dateOfBirth;
	@JsonProperty("familyId")
	private String familyId;
	@JsonProperty("nDNCConsent")
	private Boolean nDNCConsent;
	@JsonProperty("riskAppetite")
	private String riskAppetite;
	@JsonProperty("educationQualification")
	private String educationQualification;
	@JsonProperty("lmsInterestId")
	private String lmsInterestId;
	@JsonProperty("oISumAssured")
	private String oISumAssured;
	@JsonProperty("quotationId")
	private String quotationId;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public Item() {
	}

	/**
	 *
	 * @param leadStatus
	 * @param annualIncome
	 * @param occupation
	 * @param gender
	 * @param mobileNumber
	 * @param externalReferenceCode
	 * @param smoker
	 * @param dateCreated
	 * @param countryCode
	 * @param nRIMobileNumber
	 * @param otherInsurance
	 * @param id
	 * @param lmsQuoteId
	 * @param email
	 * @param leadId
	 * @param pincode
	 * @param creator
	 * @param productId
	 * @param isNRI
	 * @param natureOfDuty
	 * @param fullName
	 * @param investmentObjective
	 * @param suitabilityConsent
	 * @param dateModified
	 * @param dateOfBirth
	 * @param familyId
	 * @param nDNCConsent
	 * @param riskAppetite
	 * @param educationQualification
	 * @param actions
	 * @param lmsInterestId
	 * @param oISumAssured
	 * @param status
	 */
	private Item(String dateCreated, String dateModified, String externalReferenceCode, Integer id, String leadStatus,
			String annualIncome, String occupation, String gender, String mobileNumber, String smoker,
			String countryCode, String nRIMobileNumber, String otherInsurance, String lmsQuoteId, String email,
			String leadId, String pincode, String productId, Boolean isNRI, String natureOfDuty, String fullName,
			String investmentObjective, Boolean suitabilityConsent, String dateOfBirth, String familyId,
			Boolean nDNCConsent, String riskAppetite, String educationQualification, String lmsInterestId,
			String oISumAssured, String quotationId) {
		super();
		this.dateCreated = dateCreated;
		this.dateModified = dateModified;
		this.externalReferenceCode = externalReferenceCode;
		this.id = id;
		this.leadStatus = leadStatus;
		this.annualIncome = annualIncome;
		this.occupation = occupation;
		this.gender = gender;
		this.mobileNumber = mobileNumber;
		this.smoker = smoker;
		this.countryCode = countryCode;
		this.nRIMobileNumber = nRIMobileNumber;
		this.otherInsurance = otherInsurance;
		this.lmsQuoteId = lmsQuoteId;
		this.email = email;
		this.leadId = leadId;
		this.pincode = pincode;
		this.productId = productId;
		this.isNRI = isNRI;
		this.natureOfDuty = natureOfDuty;
		this.fullName = fullName;
		this.investmentObjective = investmentObjective;
		this.suitabilityConsent = suitabilityConsent;
		this.dateOfBirth = dateOfBirth;
		this.familyId = familyId;
		this.nDNCConsent = nDNCConsent;
		this.riskAppetite = riskAppetite;
		this.educationQualification = educationQualification;
		this.lmsInterestId = lmsInterestId;
		this.oISumAssured = oISumAssured;
		this.quotationId = quotationId;
	}

	@JsonProperty("dateCreated")
	public String getDateCreated() {
		return dateCreated;
	}

	
	@JsonProperty("dateCreated")
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	@JsonProperty("dateModified")
	public String getDateModified() {
		return dateModified;
	}

	@JsonProperty("dateModified")
	public void setDateModified(String dateModified) {
		this.dateModified = dateModified;
	}

	@JsonProperty("externalReferenceCode")
	public String getExternalReferenceCode() {
		return externalReferenceCode;
	}

	@JsonProperty("externalReferenceCode")
	public void setExternalReferenceCode(String externalReferenceCode) {
		this.externalReferenceCode = externalReferenceCode;
	}

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}

	

	@JsonProperty("leadStatus")
	public String getLeadStatus() {
		return leadStatus;
	}

	@JsonProperty("leadStatus")
	public void setLeadStatus(String leadStatus) {
		this.leadStatus = leadStatus;
	}

	@JsonProperty("annualIncome")
	public String getAnnualIncome() {
		return annualIncome;
	}

	@JsonProperty("annualIncome")
	public void setAnnualIncome(String annualIncome) {
		this.annualIncome = annualIncome;
	}

	@JsonProperty("occupation")
	public String getOccupation() {
		return occupation;
	}

	@JsonProperty("occupation")
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	@JsonProperty("gender")
	public String getGender() {
		return gender;
	}

	@JsonProperty("gender")
	public void setGender(String gender) {
		this.gender = gender;
	}

	@JsonProperty("mobileNumber")
	public String getMobileNumber() {
		return mobileNumber;
	}

	@JsonProperty("mobileNumber")
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@JsonProperty("smoker")
	public String getSmoker() {
		return smoker;
	}

	@JsonProperty("smoker")
	public void setSmoker(String smoker) {
		this.smoker = smoker;
	}

	@JsonProperty("countryCode")
	public String getCountryCode() {
		return countryCode;
	}

	@JsonProperty("countryCode")
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@JsonProperty("nRIMobileNumber")
	public String getnRIMobileNumber() {
		return nRIMobileNumber;
	}

	@JsonProperty("nRIMobileNumber")
	public void setnRIMobileNumber(String nRIMobileNumber) {
		this.nRIMobileNumber = nRIMobileNumber;
	}

	@JsonProperty("otherInsurance")
	public String getOtherInsurance() {
		return otherInsurance;
	}

	@JsonProperty("otherInsurance")
	public void setOtherInsurance(String otherInsurance) {
		this.otherInsurance = otherInsurance;
	}

	@JsonProperty("lmsQuoteId")
	public String getLmsQuoteId() {
		return lmsQuoteId;
	}

	@JsonProperty("lmsQuoteId")
	public void setLmsQuoteId(String lmsQuoteId) {
		this.lmsQuoteId = lmsQuoteId;
	}

	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty("leadId")
	public String getLeadId() {
		return leadId;
	}

	@JsonProperty("leadId")
	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}

	@JsonProperty("pincode")
	public String getPincode() {
		return pincode;
	}

	@JsonProperty("pincode")
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	@JsonProperty("productId")
	public String getProductId() {
		return productId;
	}

	@JsonProperty("productId")
	public void setProductId(String productId) {
		this.productId = productId;
	}

	@JsonProperty("isNRI")
	public Boolean getIsNRI() {
		return isNRI;
	}

	@JsonProperty("isNRI")
	public void setIsNRI(Boolean isNRI) {
		this.isNRI = isNRI;
	}

	@JsonProperty("natureOfDuty")
	public String getNatureOfDuty() {
		return natureOfDuty;
	}

	@JsonProperty("natureOfDuty")
	public void setNatureOfDuty(String natureOfDuty) {
		this.natureOfDuty = natureOfDuty;
	}

	@JsonProperty("fullName")
	public String getFullName() {
		return fullName;
	}

	@JsonProperty("fullName")
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@JsonProperty("investmentObjective")
	public String getInvestmentObjective() {
		return investmentObjective;
	}

	@JsonProperty("investmentObjective")
	public void setInvestmentObjective(String investmentObjective) {
		this.investmentObjective = investmentObjective;
	}

	@JsonProperty("suitabilityConsent")
	public Boolean getSuitabilityConsent() {
		return suitabilityConsent;
	}

	@JsonProperty("suitabilityConsent")
	public void setSuitabilityConsent(Boolean suitabilityConsent) {
		this.suitabilityConsent = suitabilityConsent;
	}

	@JsonProperty("dateOfBirth")
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	@JsonProperty("dateOfBirth")
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@JsonProperty("familyId")
	public String getFamilyId() {
		return familyId;
	}

	@JsonProperty("familyId")
	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}

	@JsonProperty("nDNCConsent")
	public Boolean getnDNCConsent() {
		return nDNCConsent;
	}

	@JsonProperty("nDNCConsent")
	public void setnDNCConsent(Boolean nDNCConsent) {
		this.nDNCConsent = nDNCConsent;
	}

	@JsonProperty("riskAppetite")
	public String getRiskAppetite() {
		return riskAppetite;
	}

	@JsonProperty("riskAppetite")
	public void setRiskAppetite(String riskAppetite) {
		this.riskAppetite = riskAppetite;
	}

	@JsonProperty("educationQualification")
	public String getEducationQualification() {
		return educationQualification;
	}

	@JsonProperty("educationQualification")
	public void setEducationQualification(String educationQualification) {
		this.educationQualification = educationQualification;
	}

	@JsonProperty("lmsInterestId")
	public String getLmsInterestId() {
		return lmsInterestId;
	}

	@JsonProperty("lmsInterestId")
	public void setLmsInterestId(String lmsInterestId) {
		this.lmsInterestId = lmsInterestId;
	}

	@JsonProperty("oISumAssured")
	public String getoISumAssured() {
		return oISumAssured;
	}

	@JsonProperty("oISumAssured")
	public void setoISumAssured(String oISumAssured) {
		this.oISumAssured = oISumAssured;
	}
	
	
	@JsonProperty("quotationId")
	public String getQuotationId() {
		return quotationId;
	}
	@JsonProperty("quotationId")
	public void setQuotationId(String quotationId) {
		this.quotationId = quotationId;
	}

	@Override
	public String toString() {
		return "Item [dateCreated=" + dateCreated + ", dateModified=" + dateModified + ", externalReferenceCode="
				+ externalReferenceCode + ", id=" + id + ", leadStatus=" + leadStatus + ", annualIncome=" + annualIncome
				+ ", occupation=" + occupation + ", gender=" + gender + ", mobileNumber=" + mobileNumber + ", smoker="
				+ smoker + ", countryCode=" + countryCode + ", nRIMobileNumber=" + nRIMobileNumber + ", otherInsurance="
				+ otherInsurance + ", lmsQuoteId=" + lmsQuoteId + ", email=" + email + ", leadId=" + leadId
				+ ", pincode=" + pincode + ", productId=" + productId + ", isNRI=" + isNRI + ", natureOfDuty="
				+ natureOfDuty + ", fullName=" + fullName + ", investmentObjective=" + investmentObjective
				+ ", suitabilityConsent=" + suitabilityConsent + ", dateOfBirth=" + dateOfBirth + ", familyId="
				+ familyId + ", nDNCConsent=" + nDNCConsent + ", riskAppetite=" + riskAppetite
				+ ", educationQualification=" + educationQualification + ", lmsInterestId=" + lmsInterestId
				+ ", oISumAssured=" + oISumAssured + ", quotationId=" + quotationId + "]";
	}

	

}