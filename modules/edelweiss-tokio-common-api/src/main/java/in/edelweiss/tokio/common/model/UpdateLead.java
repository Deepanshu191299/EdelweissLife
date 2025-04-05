package in.edelweiss.tokio.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class UpdateLead {
	
	@JsonProperty("LMSId")
	private String lMSId;
	
	@JsonProperty("Name")
	private String name;
	
	@JsonProperty("Phone")
	private String phone;
	
	@JsonProperty("Email")
	private String email;
	
	@JsonProperty("Dob")
	private String dob;
	
	@JsonProperty("Gender")
	private String gender;
	
	@JsonProperty("Married")
	private String married;
	
	@JsonProperty("Tobacco")
	private String tobacco;
	
	@JsonProperty("Income")
	private String income;
	
	@JsonProperty("Lead_Form_Type")
	private String leadFormType;
	
	@JsonProperty("SpouseSmoke")
	private String spouseSmoke;
	
	@JsonProperty("SpouseName")
	private String spouseName;
	
	@JsonProperty("SpouseDob")
	private String spouseDob;
	
	@JsonProperty("ChildName")
	private String childName;
	
	@JsonProperty("ChildDob")
	private String childDob;
	
	@JsonProperty("UTM_Param1")
	private String uTMParam1;
	
	@JsonProperty("UTM_Param2")
	private String uTMParam2;
	
	@JsonProperty("UTM_Param3")
	private String uTMParam3;
	
	@JsonProperty("UTM_Param4")
	private String uTMParam4;
	
	@JsonProperty("UTM_Param5")
	private String uTMParam5;
	
	@JsonProperty("UTM_Param6")
	private String uTMParam6;
	
	@JsonProperty("UTM_Param7")
	private String uTMParam7;
	
	@JsonProperty("UTM_Param8")
	private String uTMParam8;
	
	@JsonProperty("UTM_Param9")
	private String uTMParam9;
	
	@JsonProperty("UTM_Param10")
	private String uTMParam10;
	
	@JsonProperty("RequestURL")
	private String requestURL;
	
	@JsonProperty("Sumassured")
	private String sumassured;
	
	@JsonProperty("Relation")
	private String relation;
	
	@JsonProperty("ParentLeadID")
	private String parentLeadID;
	
	@JsonProperty("LAProposerRelation")
	private String lAProposerRelation;
	
	@JsonProperty("ProposerLARelation")
	private String proposerLARelation;
	
	@JsonProperty("Relationship")
	private String relationship;
	
	@JsonProperty("Total_Sum_Assured")
	private String totalSumAssured;
	
	@JsonProperty("Occupation")
	private String occupation;
	
	@JsonProperty("Education_Qualification")
	private String educationalqualification;
	
	@JsonProperty("Nature_Of_Duty")
	private String natureofduty;
	
	
	
	@JsonProperty("Spouse")
	private String spouse;
	
	@JsonProperty("Spouse_Occupation")
	private String spouseOccupation;
	
	@JsonProperty("Spouse_Sumassured")
	private String spouseSumassured;
	
	
	
	@JsonProperty("PermanentPinCode")
	private String permanentPinCode;
	 
	@JsonProperty("isSmoker")
	private String is_smoke;
	
	
	@JsonProperty("AlternatePhone")
	private String alternatePhone;
	
	@JsonProperty("Interest_Sessions")
	private InterestSessions interestSessions;
	
	@JsonProperty("Quote")
	private Quote quote;
	
	@JsonProperty("C_id")
	private String productVId;
	
	@JsonProperty("Cp_id")
	private String productCPId;
	
	@JsonProperty("V_id")
	private String productCId;

	@JsonProperty("AnnualIncome")
	private String AnnualIncome;
	
	@JsonProperty("Pincode")
	private String pincode;
	
	@JsonProperty("Source")
	private String source;
	
	@JsonProperty("Education")
	private String education;
	
	public String getAnnualIncome() {
		return AnnualIncome;
	}

	public void setAnnualIncome(String annualIncome) {
		AnnualIncome = annualIncome;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	public String getIs_smoke() {
		return is_smoke;
	}

	public void setIs_smoke(String is_smoke) {
		this.is_smoke = is_smoke;
	}


	@JsonProperty("LMSId")
	public String getLMSId() {
		return lMSId;
	}

	public void setLMSId(String lMSId) {
		this.lMSId = lMSId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMarried() {
		return married;
	}

	public void setMarried(String married) {
		this.married = married;
	}

	public String getTobacco() {
		return tobacco;
	}

	public void setTobacco(String tobacco) {
		this.tobacco = tobacco;
	}

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	public String getLeadFormType() {
		return leadFormType;
	}

	public void setLeadFormType(String leadFormType) {
		this.leadFormType = leadFormType;
	}

	public String getSpouseSmoke() {
		return spouseSmoke;
	}

	public void setSpouseSmoke(String spouseSmoke) {
		this.spouseSmoke = spouseSmoke;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public String getSpouseDob() {
		return spouseDob;
	}

	public void setSpouseDob(String spouseDob) {
		this.spouseDob = spouseDob;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public String getChildDob() {
		return childDob;
	}

	public void setChildDob(String childDob) {
		this.childDob = childDob;
	}

	public String getUTMParam1() {
		return uTMParam1;
	}

	public void setUTMParam1(String uTMParam1) {
		this.uTMParam1 = uTMParam1;
	}

	public String getUTMParam2() {
		return uTMParam2;
	}

	public void setUTMParam2(String uTMParam2) {
		this.uTMParam2 = uTMParam2;
	}

	public String getUTMParam3() {
		return uTMParam3;
	}

	public void setUTMParam3(String uTMParam3) {
		this.uTMParam3 = uTMParam3;
	}

	public String getUTMParam4() {
		return uTMParam4;
	}

	public void setUTMParam4(String uTMParam4) {
		this.uTMParam4 = uTMParam4;
	}

	public String getUTMParam5() {
		return uTMParam5;
	}

	public void setUTMParam5(String uTMParam5) {
		this.uTMParam5 = uTMParam5;
	}

	public String getUTMParam6() {
		return uTMParam6;
	}

	public void setUTMParam6(String uTMParam6) {
		this.uTMParam6 = uTMParam6;
	}

	public String getUTMParam7() {
		return uTMParam7;
	}

	public void setUTMParam7(String uTMParam7) {
		this.uTMParam7 = uTMParam7;
	}

	public String getUTMParam8() {
		return uTMParam8;
	}

	public void setUTMParam8(String uTMParam8) {
		this.uTMParam8 = uTMParam8;
	}

	public String getUTMParam9() {
		return uTMParam9;
	}

	public void setUTMParam9(String uTMParam9) {
		this.uTMParam9 = uTMParam9;
	}

	public String getUTMParam10() {
		return uTMParam10;
	}

	public void setUTMParam10(String uTMParam10) {
		this.uTMParam10 = uTMParam10;
	}

	public String getRequestURL() {
		return requestURL;
	}

	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}

	public String getSumassured() {
		return sumassured;
	}

	public void setSumassured(String sumassured) {
		this.sumassured = sumassured;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getParentLeadID() {
		return parentLeadID;
	}

	public void setParentLeadID(String parentLeadID) {
		this.parentLeadID = parentLeadID;
	}

	public String getLAProposerRelation() {
		return lAProposerRelation;
	}

	public void setLAProposerRelation(String lAProposerRelation) {
		this.lAProposerRelation = lAProposerRelation;
	}

	public String getProposerLARelation() {
		return proposerLARelation;
	}

	public void setProposerLARelation(String proposerLARelation) {
		this.proposerLARelation = proposerLARelation;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getTotalSumAssured() {
		return totalSumAssured;
	}

	public void setTotalSumAssured(String totalSumAssured) {
		this.totalSumAssured = totalSumAssured;
	}

	

	public String getPermanentPinCode() {
		return permanentPinCode;
	}

	public void setPermanentPinCode(String permanentPinCode) {
		this.permanentPinCode = permanentPinCode;
	}

	public String getAlternatePhone() {
		return alternatePhone;
	}

	public void setAlternatePhone(String alternatePhone) {
		this.alternatePhone = alternatePhone;
	}

	public InterestSessions getInterestSessions() {
		return interestSessions;
	}

	public void setInterestSessions(InterestSessions interestSessions) {
		this.interestSessions = interestSessions;
	}

	public Quote getQuote() {
		return quote;
	}

	public void setQuote(Quote quote) {
		this.quote = quote;
	}
	

	public String getProductVId() {
		return productVId;
	}

	public void setProductVId(String productVId) {
		this.productVId = productVId;
	}

	public String getProductCPId() {
		return productCPId;
	}

	public void setProductCPId(String productCPId) {
		this.productCPId = productCPId;
	}

	public String getProductCId() {
		return productCId;
	}

	public void setProductCId(String productCId) {
		this.productCId = productCId;
	}
	
	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getEducationalqualification() {
		return educationalqualification;
	}

	public void setEducationalqualification(String educationalqualification) {
		this.educationalqualification = educationalqualification;
	}

	public String getNatureofduty() {
		return natureofduty;
	}

	public void setNatureofduty(String natureofduty) {
		this.natureofduty = natureofduty;
	}	
	
	public String getSpouse() {
		return spouse;
	}

	public void setSpouse(String spouse) {
		this.spouse = spouse;
	}

	public String getSpouseOccupation() {
		return spouseOccupation;
	}

	public void setSpouseOccupation(String spouseOccupation) {
		this.spouseOccupation = spouseOccupation;
	}

	public String getSpouseSumassured() {
		return spouseSumassured;
	}

	public void setSpouseSumassured(String spouseSumassured) {
		this.spouseSumassured = spouseSumassured;
	}
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	public String getlMSId() {
		return lMSId;
	}

	public void setlMSId(String lMSId) {
		this.lMSId = lMSId;
	}

	public String getlAProposerRelation() {
		return lAProposerRelation;
	}

	public void setlAProposerRelation(String lAProposerRelation) {
		this.lAProposerRelation = lAProposerRelation;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	@Override
	public String toString() {
		return "UpdateLead [lMSId=" + lMSId + ", name=" + name + ", phone=" + phone + ", email=" + email + ", dob="
				+ dob + ", gender=" + gender + ", married=" + married + ", tobacco=" + tobacco + ", income=" + income
				+ ", leadFormType=" + leadFormType + ", spouseSmoke=" + spouseSmoke + ", spouseName=" + spouseName
				+ ", spouseDob=" + spouseDob + ", childName=" + childName + ", childDob=" + childDob + ", uTMParam1="
				+ uTMParam1 + ", uTMParam2=" + uTMParam2 + ", uTMParam3=" + uTMParam3 + ", uTMParam4=" + uTMParam4
				+ ", uTMParam5=" + uTMParam5 + ", uTMParam6=" + uTMParam6 + ", uTMParam7=" + uTMParam7 + ", uTMParam8="
				+ uTMParam8 + ", uTMParam9=" + uTMParam9 + ", uTMParam10=" + uTMParam10 + ", requestURL=" + requestURL
				+ ", sumassured=" + sumassured + ", relation=" + relation + ", parentLeadID=" + parentLeadID
				+ ", lAProposerRelation=" + lAProposerRelation + ", proposerLARelation=" + proposerLARelation
				+ ", relationship=" + relationship + ", totalSumAssured=" + totalSumAssured + ", occupation="
				+ occupation + ", educationalqualification=" + educationalqualification + ", natureofduty="
				+ natureofduty + ", spouse=" + spouse + ", spouseOccupation=" + spouseOccupation + ", spouseSumassured="
				+ spouseSumassured + ", permanentPinCode=" + permanentPinCode + ", is_smoke=" + is_smoke
				+ ", alternatePhone=" + alternatePhone + ", interestSessions=" + interestSessions + ", quote=" + quote
				+ ", productVId=" + productVId + ", productCPId=" + productCPId + ", productCId=" + productCId
				+ ", AnnualIncome=" + AnnualIncome + ", pincode=" + pincode + ", source=" + source + ", education="
				+ education + "]";
	}

	
}