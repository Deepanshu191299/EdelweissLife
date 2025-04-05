package in.edelweiss.tokio.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class Lmslead {

	@JsonProperty("Phone")
	private String phone;
	
	@JsonProperty("Web_url")
	private String webUrl;
	
	@JsonProperty("Source")
	private String source;
	
	@JsonProperty("Site_Section")
	private String siteSection;
	
	@JsonProperty("Lead_Form_Type")
	private String leadFormType;
	
	@JsonProperty("V_id")
	private String vId;
	
	@JsonProperty("C_id")
	private String cId;
	
	@JsonProperty("Cp_id")
	private String cpId;
	
	@JsonProperty("Name")
	private String name;
	
	@JsonProperty("SpouseName")
	private String spouseName;
	
	@JsonProperty("SpouseDob")
	private String spouseDob;
	
	@JsonProperty("SpouseSmoke")
	private String spouseSmoke;
	
	@JsonProperty("ChildName")
	private String childName;
	
	@JsonProperty("ChildDob")
	private String childDob;
	
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
	
	@JsonProperty("Occupation")
	private String occupation;
	
	@JsonProperty("Education_Qualification")
	private String educationqualification;
	
	@JsonProperty("AnnualIncome")
	private String annualincome;
	
	@JsonProperty("Pincode")
	private String pincode;

	@JsonProperty("Smoke")
	private String smoke;

	@JsonProperty("Spouse")
	private String spouse;
	
	@JsonProperty("Spouse_Occupation")
	private String spouseOccupation;
	
	@JsonProperty("Spouse_Sumassured")
	private String spouseSumassured;
	



	@JsonProperty("Nature_Of_Duty")
	private String natureofduty;
	
	@JsonProperty("Education")
	private String education;
	
	@JsonProperty("Nationality")
	private String nationality;
	
	@JsonProperty("RiskAppetiteSuitability")
	private String riskAppetiteSuitability;
	
	@JsonProperty("AnnualIncomeSuitability")
	private String annualIncomeSuitability;
	
	@JsonProperty("FinancialGoalSuitability")
	private String financialGoalSuitability;
	
	@JsonProperty("OtherInsuranceSuitability")
	private String otherInsuranceSuitability;
	
	@JsonProperty("SumAssuredSuitability")
	private Integer sumAssuredSuitability;
	
	@JsonProperty("Product")
	private String product;
	
	@JsonProperty("Visitor_id")
	private String visitorId;
	
	@JsonProperty("Utm_source")
	private String utmSource;
	
	@JsonProperty("Utm_medium")
	private String utmMedium;
	
	@JsonProperty("Utm_campaign")
	private String utmCampaign;
	
	@JsonProperty("Utm_term")
	private String utmTerm;
	
	@JsonProperty("Gclid")
	private String gclid;
	
	@JsonProperty("Utm_content")
	private String utmContent;
	
	@JsonProperty("Campaign_id")
	private String campaignId;
	
	@JsonProperty("Adgroup_id")
	private String adgroupId;
	
	@JsonProperty("Match_type")
	private String matchType;
	
	@JsonProperty("Device")
	private String device;
	
	@JsonProperty("Ad_position")
	private String adPosition;
	
	@JsonProperty("Location_id")
	private String locationId;
	
	@JsonProperty("Network")
	private String network;
	
	@JsonProperty("Target_name")
	private String targetName;
	
	@JsonProperty("Utm_creative")
	private String utmCreative;
	
	@JsonProperty("UTM_Placement")
	private String uTMPlacement;
	
	@JsonProperty("UserBrowser")
	private String userBrowser;
	
	@JsonProperty("BrowserVersion")
	private String browserVersion;
	
	@JsonProperty("Interest_Sessions")
	private InterestSessions interestSessions;
	
	@JsonProperty("Quote")
	private Quote quote;

	@JsonProperty("Lms_id")
	private String lmsId;

	public String getLmsId() {
		return lmsId;
	}

	public void setLmsId(String lmsId) {
		this.lmsId = lmsId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSiteSection() {
		return siteSection;
	}

	public void setSiteSection(String siteSection) {
		this.siteSection = siteSection;
	}

	public String getLeadFormType() {
		return leadFormType;
	}

	public void setLeadFormType(String leadFormType) {
		this.leadFormType = leadFormType;
	}

	public String getvId() {
		return vId;
	}

	public void setvId(String vId) {
		this.vId = vId;
	}

	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

	public String getCpId() {
		return cpId;
	}

	public void setCpId(String cpId) {
		this.cpId = cpId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getRiskAppetiteSuitability() {
		return riskAppetiteSuitability;
	}

	public void setRiskAppetiteSuitability(String riskAppetiteSuitability) {
		this.riskAppetiteSuitability = riskAppetiteSuitability;
	}

	public String getAnnualIncomeSuitability() {
		return annualIncomeSuitability;
	}

	public void setAnnualIncomeSuitability(String annualIncomeSuitability) {
		this.annualIncomeSuitability = annualIncomeSuitability;
	}

	public String getFinancialGoalSuitability() {
		return financialGoalSuitability;
	}

	public void setFinancialGoalSuitability(String financialGoalSuitability) {
		this.financialGoalSuitability = financialGoalSuitability;
	}

	public String getOtherInsuranceSuitability() {
		return otherInsuranceSuitability;
	}

	public void setOtherInsuranceSuitability(String otherInsuranceSuitability) {
		this.otherInsuranceSuitability = otherInsuranceSuitability;
	}

	public Integer getSumAssuredSuitability() {
		return sumAssuredSuitability;
	}

	public void setSumAssuredSuitability(Integer sumAssuredSuitability) {
		this.sumAssuredSuitability = sumAssuredSuitability;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(String visitorId) {
		this.visitorId = visitorId;
	}

	public String getUtmSource() {
		return utmSource;
	}

	public void setUtmSource(String utmSource) {
		this.utmSource = utmSource;
	}

	public String getUtmMedium() {
		return utmMedium;
	}

	public void setUtmMedium(String utmMedium) {
		this.utmMedium = utmMedium;
	}

	public String getUtmCampaign() {
		return utmCampaign;
	}

	public void setUtmCampaign(String utmCampaign) {
		this.utmCampaign = utmCampaign;
	}

	public String getUtmTerm() {
		return utmTerm;
	}

	public void setUtmTerm(String utmTerm) {
		this.utmTerm = utmTerm;
	}

	public String getGclid() {
		return gclid;
	}

	public void setGclid(String gclid) {
		this.gclid = gclid;
	}

	public String getUtmContent() {
		return utmContent;
	}

	public void setUtmContent(String utmContent) {
		this.utmContent = utmContent;
	}

	public String getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}

	public String getAdgroupId() {
		return adgroupId;
	}

	public void setAdgroupId(String adgroupId) {
		this.adgroupId = adgroupId;
	}

	public String getMatchType() {
		return matchType;
	}

	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getAdPosition() {
		return adPosition;
	}

	public void setAdPosition(String adPosition) {
		this.adPosition = adPosition;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getUtmCreative() {
		return utmCreative;
	}

	public void setUtmCreative(String utmCreative) {
		this.utmCreative = utmCreative;
	}

	public String getuTMPlacement() {
		return uTMPlacement;
	}

	public void setuTMPlacement(String uTMPlacement) {
		this.uTMPlacement = uTMPlacement;
	}

	public String getUserBrowser() {
		return userBrowser;
	}

	public void setUserBrowser(String userBrowser) {
		this.userBrowser = userBrowser;
	}

	public String getBrowserVersion() {
		return browserVersion;
	}

	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
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
	public String getEducationqualification() {
		return educationqualification;
	}

	public void setEducationqualification(String educationqualification) {
		this.educationqualification = educationqualification;
	}

	public String getNatureofduty() {
		return natureofduty;
	}

	public void setNatureofduty(String natureofduty) {
		this.natureofduty = natureofduty;
	}
	
	public String getAnnualincome() {
		return annualincome;
	}

	public void setAnnualincome(String annualincome) {
		this.annualincome = annualincome;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getSmoke() {
		return smoke;
	}

	public void setSmoke(String smoke) {
		this.smoke = smoke;
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

	public String getSpouseSmoke() {
		return spouseSmoke;
	}

	public void setSpouseSmoke(String spouseSmoke) {
		this.spouseSmoke = spouseSmoke;
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
	
}
