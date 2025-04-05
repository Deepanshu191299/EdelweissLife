package in.edelweiss.proposal.form.pf.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommonData {

	@JsonProperty("policy_no")
	private String policyNo;

	@JsonProperty("application_number")
	private String applicationNumber;

	@JsonProperty("is_la_pr_same_yn")
	private String isLaPrSameYn;

	@JsonProperty("spouse_exist_yn")
	private String spouseExistYn;

	@JsonProperty("la_pr_relation_data_id")
	private String laPrRelationDataId;

	@JsonProperty("application_stage")
	private int applicationStage;

	@JsonProperty("payment_status")
	private String paymentStatus;

	@JsonProperty("source")
	private String source;

	@JsonProperty("form_type")
	private String formType;

	@JsonProperty("mail_sent")
	private String mailSent;

	@JsonProperty("push_to_lms")
	private String pushToLms;

	@JsonProperty("lead_source")
	private String leadSource;

	@JsonProperty("pivc_status")
	private String pivcStatus;

	@JsonProperty("pivc_url")
	private String pivcUrl;

	@JsonProperty("person_name")
	private String personName;

	@JsonProperty("product_name")
	private String productName;

	@JsonProperty("nri_gst")
	private String nriGst;

	@JsonProperty("show_covid_form_yn")
	private String showCovidFormYn;

	@JsonProperty("covid_la_url")
	private String covidLaUrl;

	@JsonProperty("covid_pr_url")
	private String covidPrUrl;
	
	@JsonProperty("send_copy_sh")
	private String sendCopySh;

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public String getIsLaPrSameYn() {
		return isLaPrSameYn;
	}

	public void setIsLaPrSameYn(String isLaPrSameYn) {
		this.isLaPrSameYn = isLaPrSameYn;
	}

	public String getSpouseExistYn() {
		return spouseExistYn;
	}

	public void setSpouseExistYn(String spouseExistYn) {
		this.spouseExistYn = spouseExistYn;
	}

	public String getLaPrRelationDataId() {
		return laPrRelationDataId;
	}

	public void setLaPrRelationDataId(String laPrRelationDataId) {
		this.laPrRelationDataId = laPrRelationDataId;
	}

	public int getApplicationStage() {
		return applicationStage;
	}

	public void setApplicationStage(int applicationStage) {
		this.applicationStage = applicationStage;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getMailSent() {
		return mailSent;
	}

	public void setMailSent(String mailSent) {
		this.mailSent = mailSent;
	}

	public String getPushToLms() {
		return pushToLms;
	}

	public void setPushToLms(String pushToLms) {
		this.pushToLms = pushToLms;
	}

	public String getLeadSource() {
		return leadSource;
	}

	public void setLeadSource(String leadSource) {
		this.leadSource = leadSource;
	}

	public String getPivcStatus() {
		return pivcStatus;
	}

	public void setPivcStatus(String pivcStatus) {
		this.pivcStatus = pivcStatus;
	}

	public String getPivcUrl() {
		return pivcUrl;
	}

	public void setPivcUrl(String pivcUrl) {
		this.pivcUrl = pivcUrl;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getNriGst() {
		return nriGst;
	}

	public void setNriGst(String nriGst) {
		this.nriGst = nriGst;
	}

	public String getShowCovidFormYn() {
		return showCovidFormYn;
	}

	public void setShowCovidFormYn(String showCovidFormYn) {
		this.showCovidFormYn = showCovidFormYn;
	}

	public String getCovidLaUrl() {
		return covidLaUrl;
	}

	public void setCovidLaUrl(String covidLaUrl) {
		this.covidLaUrl = covidLaUrl;
	}

	public String getCovidPrUrl() {
		return covidPrUrl;
	}

	public void setCovidPrUrl(String covidPrUrl) {
		this.covidPrUrl = covidPrUrl;
	}
	
	public String getSendCopySh() {
		return sendCopySh;
	}
	
	public void setSendCopySh(String sendCopySh) {
		this.sendCopySh = sendCopySh;
	}

	@Override
	public String toString() {
		return "CommonData [policyNo=" + policyNo + ", applicationNumber=" + applicationNumber + ", isLaPrSameYn="
				+ isLaPrSameYn + ", spouseExistYn=" + spouseExistYn + ", laPrRelationDataId=" + laPrRelationDataId
				+ ", applicationStage=" + applicationStage + ", paymentStatus=" + paymentStatus + ", source=" + source
				+ ", formType=" + formType + ", mailSent=" + mailSent + ", pushToLms=" + pushToLms + ", leadSource="
				+ leadSource + ", pivcStatus=" + pivcStatus + ", pivcUrl=" + pivcUrl + ", personName=" + personName
				+ ", productName=" + productName + ", nriGst=" + nriGst + ", showCovidFormYn=" + showCovidFormYn
				+ ", covidLaUrl=" + covidLaUrl + ", covidPrUrl=" + covidPrUrl + ", sendCopySh=" + sendCopySh + "]";
	}

}
