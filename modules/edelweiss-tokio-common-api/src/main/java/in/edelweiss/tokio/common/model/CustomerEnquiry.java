package in.edelweiss.tokio.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerEnquiry {
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("dateCreated")
	private String dateCreated;
	
	@JsonProperty("dateModified")
	private String dateModified;
    
    @JsonProperty("externalReferenceCode")
    private String externalreferencecode;
    
    @JsonProperty("annualIncome")
    private String annualIncome;
    
    @JsonProperty("countryCode")
    private String countryCode;

    @JsonProperty("dateOfBirth")
    private String dateOfBirth;
    
    @JsonProperty("educationQualification")
    private String educationQualification;
    
    @JsonProperty("email")
    private String email;
    
    @JsonProperty("familyId")
    private String familyId;
    
    @JsonProperty("fullName")
    private String fullname;
    
    @JsonProperty("gender")
    private String gender;
    
    @JsonProperty("investmentObjective")
    private String investmentObjective;
    
    @JsonProperty("isNRI")
    private Boolean isNRI;
    
    @JsonProperty("leadId")
    private String leadId;
    
    @JsonProperty("leadStatus")
    private String leadStatus;
    
    @JsonProperty("lmsInterestId")
    private String lmsinterestid;
    
    @JsonProperty("lmsQuoteId")
    private String lmsQuoteId;
   
    @JsonProperty("mobileNumber")
    private String mobilenumber;
    
    @JsonProperty("natureOfDuty")
    private String natureofduty;
    
    @JsonProperty("nDNCConsent")
    private Boolean nDNCConsent;
    
    @JsonProperty("nRIMobileNumber")
    private String nriMobileNumber;
    
    @JsonProperty("occupation")
    private String occupation;
    
    @JsonProperty("oISumAssured")
    private String oISumAssured;
    
    @JsonProperty("otherInsurance")
    private String otherInsurance;
    
    @JsonProperty("pincode")
    private String pincode;
   
    @JsonProperty("productId")
    private String productId;
    
    @JsonProperty("riskAppetite")
    private String riskAppetite;
    
    @JsonProperty("smoker")
    private String smoker;
    
    @JsonProperty("child")
    private String child;
    
    @JsonProperty("married")
    private String married;
    
    @JsonProperty("suitabilityConsent")
    private Boolean suitabilityConsent;
    
    @JsonProperty("customerFamilyDetailsRel")
    private String customerFamilyDetailsRel;
    
    @JsonProperty("customerInvestmentDetailsRel")
    private String customerInvestmentDetailsRel;

    @JsonProperty("customerPolicyDetailsRel")
    private String customerPolicyDetailsrel;

     @JsonProperty("spouseParentName")
     private String spouseParentName;
     
     @JsonProperty("spouseParentOccupation")
     private String spouseParentOccupation;
     
     @JsonProperty("spouseParentSumassured")
     private String spouseParentSumassured;
     
     public String getSpouseParentName() {
    	 return spouseParentName;
     }
     public void setSpouseParentName(String spouseParentName) {
    	 this.spouseParentName = spouseParentName;
     }
     
     public String getSpouseParentOccupation() {
    	 return spouseParentOccupation;
     }
     public void setSpouseParentOccupation(String spouseParentOccupation) {
    	 this.spouseParentOccupation = spouseParentOccupation;
     }
     
     public String getSpousePArentSumassured() {
    	 return spouseParentSumassured;
     }
     public void setSpouseParentSumassured(String spouseParentSumassured) {
    	 this.spouseParentSumassured = spouseParentSumassured;
     }
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDateModified() {
		return dateModified;
	}

	public void setDateModified(String dateModified) {
		this.dateModified = dateModified;
	}

	public String getExternalreferencecode() {
		return externalreferencecode;
	}

	public void setExternalreferencecode(String externalreferencecode) {
		this.externalreferencecode = externalreferencecode;
	}

	public String getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(String annualIncome) {
		this.annualIncome = annualIncome;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEducationQualification() {
		return educationQualification;
	}

	public void setEducationQualification(String educationQualification) {
		this.educationQualification = educationQualification;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFamilyId() {
		return familyId;
	}

	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getInvestmentObjective() {
		return investmentObjective;
	}

	public void setInvestmentObjective(String investmentObjective) {
		this.investmentObjective = investmentObjective;
	}

	public Boolean getIsNRI() {
		return isNRI;
	}

	public void setIsNRI(Boolean isNRI) {
		this.isNRI = isNRI;
	}

	public String getLeadId() {
		return leadId;
	}

	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}

	public String getLeadStatus() {
		return leadStatus;
	}

	public void setLeadStatus(String leadStatus) {
		this.leadStatus = leadStatus;
	}

	public String getLmsinterestid() {
		return lmsinterestid;
	}

	public void setLmsinterestid(String lmsinterestid) {
		this.lmsinterestid = lmsinterestid;
	}

	public String getLmsQuoteId() {
		return lmsQuoteId;
	}

	public void setLmsQuoteId(String lmsQuoteId) {
		this.lmsQuoteId = lmsQuoteId;
	}

	public String getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	public String getNatureofduty() {
		return natureofduty;
	}

	public void setNatureofduty(String natureofduty) {
		this.natureofduty = natureofduty;
	}

	public Boolean getnDNCConsent() {
		return nDNCConsent;
	}

	public void setnDNCConsent(Boolean nDNCConsent) {
		this.nDNCConsent = nDNCConsent;
	}

	public String getNriMobileNumber() {
		return nriMobileNumber;
	}

	public void setNriMobileNumber(String nriMobileNumber) {
		this.nriMobileNumber = nriMobileNumber;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getoISumAssured() {
		return oISumAssured;
	}

	public void setoISumAssured(String oISumAssured) {
		this.oISumAssured = oISumAssured;
	}

	public String getOtherInsurance() {
		return otherInsurance;
	}

	public void setOtherInsurance(String otherInsurance) {
		this.otherInsurance = otherInsurance;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getRiskAppetite() {
		return riskAppetite;
	}

	public void setRiskAppetite(String riskAppetite) {
		this.riskAppetite = riskAppetite;
	}

	public String getSmoker() {
		return smoker;
	}

	public void setSmoker(String smoker) {
		this.smoker = smoker;
	}

	public Boolean getSuitabilityConsent() {
		return suitabilityConsent;
	}

	public void setSuitabilityConsent(Boolean suitabilityConsent) {
		this.suitabilityConsent = suitabilityConsent;
	}

	public String getCustomerFamilyDetailsRel() {
		return customerFamilyDetailsRel;
	}

	public void setCustomerFamilyDetailsRel(String customerFamilyDetailsRel) {
		this.customerFamilyDetailsRel = customerFamilyDetailsRel;
	}

	public String getCustomerInvestmentDetailsRel() {
		return customerInvestmentDetailsRel;
	}

	public void setCustomerInvestmentDetailsRel(String customerInvestmentDetailsRel) {
		this.customerInvestmentDetailsRel = customerInvestmentDetailsRel;
	}

	public String getCustomerPolicyDetailsrel() {
		return customerPolicyDetailsrel;
	}

	public void setCustomerPolicyDetailsrel(String customerPolicyDetailsrel) {
		this.customerPolicyDetailsrel = customerPolicyDetailsrel;
	}
	
	public String getChild() {
		return child;
	}

	public void setChild(String child) {
		this.child = child;
	}

	public String getMarried() {
		return married;
	}

	public void setMarried(String married) {
		this.married = married;
	}

	@Override
	public String toString() {
		return "CustomerEnquiry [id=" + id + ", dateCreated=" + dateCreated + ", dateModified="
				+ dateModified + ", externalreferencecode=" + externalreferencecode + ", annualIncome=" + annualIncome
				+ ", countryCode=" + countryCode + ", dateOfBirth=" + dateOfBirth + ", educationQualification="
				+ educationQualification + ", email=" + email + ", familyId=" + familyId + ", fullname=" + fullname
				+ ", gender=" + gender + ", investmentObjective=" + investmentObjective + ", isNRI=" + isNRI
				+ ", leadId=" + leadId + ", leadStatus=" + leadStatus + ", lmsinterestid=" + lmsinterestid
				+ ", lmsQuoteId=" + lmsQuoteId + ", mobilenumber=" + mobilenumber + ", natureofduty=" + natureofduty
				+ ", nDNCConsent=" + nDNCConsent + ", nriMobileNumber=" + nriMobileNumber + ", occupation=" + occupation
				+ ", oISumAssured=" + oISumAssured + ", otherInsurance=" + otherInsurance + ", pincode=" + pincode
				+ ", productId=" + productId + ", riskAppetite=" + riskAppetite + ", smoker=" + smoker
				+",spouseParentName="+ spouseParentName +",spouseParentOccupation =" + spouseParentOccupation + ",spouseParentSumassured= "+spouseParentSumassured +", suitabilityConsent=" + suitabilityConsent + ", customerFamilyDetailsRel="
				+ customerFamilyDetailsRel + ", customerInvestmentDetailsRel=" + customerInvestmentDetailsRel
				+ ", customerPolicyDetailsrel=" + customerPolicyDetailsrel + "]";
	}
}
