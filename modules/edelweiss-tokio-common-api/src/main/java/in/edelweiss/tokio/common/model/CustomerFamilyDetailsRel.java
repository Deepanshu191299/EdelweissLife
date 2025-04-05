package in.edelweiss.tokio.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "dateCreated", "dateModified", "externalReferenceCode", "id", "assuranceDob",
	"r_customerFamilyDetailsRel_c_customerEnquiryId", "assuranceFullName", "gender", "assuredRelation", "smoke",
	"leadId", "isLittleChampBenefitOpted","isRisingStarBenefitOpted", "benefitName" })
public class CustomerFamilyDetailsRel {

	@JsonProperty("dateCreated")
	private String dateCreated;

	@JsonProperty("dateModified")
	private String dateModified;

	@JsonProperty("externalReferenceCode")
	private String externalReferenceCode;

	@JsonProperty("id")
	private Long id;

	@JsonProperty("assuranceDob")
	private String assuranceDob;

	@JsonProperty("r_customerFamilyDetailsRel_c_customerEnquiryId")
	private Long rCustomerFamilyDetailsRelCCustomerEnquiryId;

	@JsonProperty("assuranceFullName")
	private String assuranceFullName;

	@JsonProperty("gender")
	private String gender;

	@JsonProperty("assuredRelation")
	private String assuredRelation;

	@JsonProperty("r_customerFamilyDetailsRel_c_customerEnquiryERC")
	private String rCustomerFamilyDetailsRelCCustomerenquiryERC;

	@JsonProperty("smoke")
	private String smoke;

	@JsonProperty("leadId")
	private String leadId;

	@JsonProperty("isLittleChampBenefitOpted")
	private String isLittleChampBenefitOpted;

	@JsonProperty("isRisingStarBenefitOpted")
	private String isRisingStarBenefitOpted;

	@JsonProperty("benefitName")
	private String benefitName;

	@JsonProperty("spouseName")
	private String spouseName;

	@JsonProperty("spouseDateOfBirth")
	private String spouseDateOfBirth;

	@JsonProperty("spouseSmoke")
	private String spouseSmoke;

	@JsonProperty("childDateOfBirth")
	private String childDateOfBirth;

	@JsonProperty("isFamilyFromPolicyOption")
	private String isFamilyFromPolicyOption;
	
	@JsonProperty("occupation")
	private String occupation;
	
	@JsonProperty("totalSumAssured")
	private String totalSumAssured;

	public String getSpouseSmoke() {
		return spouseSmoke;
	}

	public void setSpouseSmoke(String spouseSmoke) {
		this.spouseSmoke = spouseSmoke;
	}

	public String getChildDateOfBirth() {
		return childDateOfBirth;
	}

	public void setChildDateOfBirth(String childDateOfBirth) {
		this.childDateOfBirth = childDateOfBirth;
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

	public String getExternalReferenceCode() {
		return externalReferenceCode;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		this.externalReferenceCode = externalReferenceCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAssuranceDob() {
		return assuranceDob;
	}

	public void setAssuranceDob(String assuranceDob) {
		this.assuranceDob = assuranceDob;
	}

	public Long getrCustomerFamilyDetailsRelCCustomerEnquiryId() {
		return rCustomerFamilyDetailsRelCCustomerEnquiryId;
	}

	public void setrCustomerFamilyDetailsRelCCustomerEnquiryId(Long rCustomerFamilyDetailsRelCCustomerEnquiryId) {
		this.rCustomerFamilyDetailsRelCCustomerEnquiryId = rCustomerFamilyDetailsRelCCustomerEnquiryId;
	}

	public String getAssuranceFullName() {
		return assuranceFullName;
	}

	public void setAssuranceFullName(String assuranceFullName) {
		this.assuranceFullName = assuranceFullName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAssuredRelation() {
		return assuredRelation;
	}

	public void setAssuredRelation(String assuredRelation) {
		this.assuredRelation = assuredRelation;
	}

	public String getrCustomerFamilyDetailsRelCCustomerenquiryERC() {
		return rCustomerFamilyDetailsRelCCustomerenquiryERC;
	}

	public void setrCustomerFamilyDetailsRelCCustomerenquiryERC(String rCustomerFamilyDetailsRelCCustomerenquiryERC) {
		this.rCustomerFamilyDetailsRelCCustomerenquiryERC = rCustomerFamilyDetailsRelCCustomerenquiryERC;
	}

	public String getSmoke() {
		return smoke;
	}

	public void setSmoke(String smoke) {
		this.smoke = smoke;
	}

	public String getLeadId() {
		return leadId;
	}

	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}

	public String getIsLittleChampBenefitOpted() {
		return isLittleChampBenefitOpted;
	}

	public void setIsLittleChampBenefitOpted(String isLittleChampBenefitOpted) {
		this.isLittleChampBenefitOpted = isLittleChampBenefitOpted;
	}

	public String getBenefitName() {
		return benefitName;
	}

	public void setBenefitName(String benefitName) {
		this.benefitName = benefitName;
	}
	public String getIsRisingStarBenefitOpted() {
		return isRisingStarBenefitOpted;
	}

	public void setIsRisingStarBenefitOpted(String isRisingStarBenefitOpted) {
		this.isRisingStarBenefitOpted = isRisingStarBenefitOpted;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public String getSpouseDateOfBirth() {
		return spouseDateOfBirth;
	}

	public void setSpouseDateOfBirth(String spouseDateOfBirth) {
		this.spouseDateOfBirth = spouseDateOfBirth;
	}

	public String getIsFamilyFromPolicyOption() {
		return isFamilyFromPolicyOption;
	}

	public void setIsFamilyFromPolicyOption(String isFamilyFromPolicyOption) {
		this.isFamilyFromPolicyOption = isFamilyFromPolicyOption;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getTotalSumAssured() {
		return totalSumAssured;
	}

	public void setTotalSumAssured(String totalSumAssured) {
		this.totalSumAssured = totalSumAssured;
	}

	@Override
	public String toString() {
		return "CustomerFamilyDetailsRel [dateCreated=" + dateCreated + ", dateModified=" + dateModified
				+ ", externalReferenceCode=" + externalReferenceCode + ", id=" + id + ", assuranceDob=" + assuranceDob
				+ ", rCustomerFamilyDetailsRelCCustomerEnquiryId=" + rCustomerFamilyDetailsRelCCustomerEnquiryId
				+ ", assuranceFullName=" + assuranceFullName + ", gender=" + gender + ", assuredRelation="
				+ assuredRelation + ", rCustomerFamilyDetailsRelCCustomerenquiryERC="
				+ rCustomerFamilyDetailsRelCCustomerenquiryERC + ", smoke=" + smoke + ", leadId=" + leadId
				+ ", isLittleChampBenefitOpted=" + isLittleChampBenefitOpted + ", isRisingStarBenefitOpted="
				+ isRisingStarBenefitOpted + ", benefitName=" + benefitName + ", spouseName=" + spouseName
				+ ", spouseDateOfBirth=" + spouseDateOfBirth + ", spouseSmoke=" + spouseSmoke + ", childDateOfBirth="
				+ childDateOfBirth + ", isFamilyFromPolicyOption=" + isFamilyFromPolicyOption + ", occupation="
				+ occupation + ", totalSumAssured=" + totalSumAssured + "]";
	}
}