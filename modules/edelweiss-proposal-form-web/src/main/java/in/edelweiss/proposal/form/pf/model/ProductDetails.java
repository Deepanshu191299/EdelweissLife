package in.edelweiss.proposal.form.pf.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDetails {

	@JsonProperty("product_name")
	private String productName;

	@JsonProperty("application_number")
	private String applicationNumber;

	@JsonProperty("policy_number")
	private String policyNumber;

	@JsonProperty("bi_product_code")
	private String biProductCode;

	@JsonProperty("product_code")
	private String productCode;

	@JsonProperty("bl_login_code")
	private String blLoginCode;

	@JsonProperty("annualised_premium")
	private long annualisedPremium;

	@JsonProperty("total_premium_amount")
	private double totalPremiumAmount;

	@JsonProperty("total_premium_amount_with_discount")
	private double totalPremiumAmountWithDiscount;

	@JsonProperty("sum_assured")
	private long sumAssured;

	@JsonProperty("premium_payment_frequency")
	private String premiuPaymentFrequency;

	@JsonProperty("pdf_path")
	private String pdfPath;

	@JsonProperty("proposal_form_path")
	private String proposalFormPath;

	@JsonProperty("premium_payment_term")
	private long premiumPaymentTerm;

	@JsonProperty("policy_term")
	private long policyTerm;

	@JsonProperty("product_type")
	private String productType;

	@JsonProperty("plan_options")
	private String planOptions;

	@JsonProperty("person_name")
	private String personName;

	@JsonProperty("proposal_form_status")
	private String proposalFormStatus;

	@JsonProperty("proposal_form_complete_date")
	private String proposalFormCompleteDate;

	@JsonProperty("payment_status")
	private String paymentStatus;

	@JsonProperty("payment_complete_date")
	private String paymentCompleteDate;

	@JsonProperty("la_mobile_number")
	private String laMobileNumber;

	@JsonProperty("la_dob")
	private String laDob;

	@JsonProperty("la_email")
	private String laEmail;

	@JsonProperty("la_gender")
	private String laGender;

	@JsonProperty("la_pan")
	private String laPan;

	@JsonProperty("wop_rider")
	private String wopRider;
	
	@JsonProperty("child_benefit")
	private String childBenefit;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getBiProductCode() {
		return biProductCode;
	}

	public void setBiProductCode(String biProductCode) {
		this.biProductCode = biProductCode;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getBlLoginCode() {
		return blLoginCode;
	}

	public void setBlLoginCode(String blLoginCode) {
		this.blLoginCode = blLoginCode;
	}

	public long getAnnualisedPremium() {
		return annualisedPremium;
	}

	public void setAnnualisedPremium(long annualisedPremium) {
		this.annualisedPremium = annualisedPremium;
	}

	public double getTotalPremiumAmount() {
		return totalPremiumAmount;
	}

	public void setTotalPremiumAmount(double totalPremiumAmount) {
		this.totalPremiumAmount = totalPremiumAmount;
	}

	public double getTotalPremiumAmountWithDiscount() {
		return totalPremiumAmountWithDiscount;
	}

	public void setTotalPremiumAmountWithDiscount(double totalPremiumAmountWithDiscount) {
		this.totalPremiumAmountWithDiscount = totalPremiumAmountWithDiscount;
	}

	public long getSumAssured() {
		return sumAssured;
	}

	public void setSumAssured(long sumAssured) {
		this.sumAssured = sumAssured;
	}

	public String getPremiuPaymentFrequency() {
		return premiuPaymentFrequency;
	}

	public void setPremiuPaymentFrequency(String premiuPaymentFrequency) {
		this.premiuPaymentFrequency = premiuPaymentFrequency;
	}

	public String getPdfPath() {
		return pdfPath;
	}

	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}

	public String getProposalFormPath() {
		return proposalFormPath;
	}

	public void setProposalFormPath(String proposalFormPath) {
		this.proposalFormPath = proposalFormPath;
	}

	public long getPremiumPaymentTerm() {
		return premiumPaymentTerm;
	}

	public void setPremiumPaymentTerm(long premiumPaymentTerm) {
		this.premiumPaymentTerm = premiumPaymentTerm;
	}

	public long getPolicyTerm() {
		return policyTerm;
	}

	public void setPolicyTerm(long policyTerm) {
		this.policyTerm = policyTerm;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getPlanOptions() {
		return planOptions;
	}

	public void setPlanOptions(String planOptions) {
		this.planOptions = planOptions;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getProposalFormStatus() {
		return proposalFormStatus;
	}

	public void setProposalFormStatus(String proposalFormStatus) {
		this.proposalFormStatus = proposalFormStatus;
	}

	public String getProposalFormCompleteDate() {
		return proposalFormCompleteDate;
	}

	public void setProposalFormCompleteDate(String proposalFormCompleteDate) {
		this.proposalFormCompleteDate = proposalFormCompleteDate;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentCompleteDate() {
		return paymentCompleteDate;
	}

	public void setPaymentCompleteDate(String paymentCompleteDate) {
		this.paymentCompleteDate = paymentCompleteDate;
	}

	public String getLaMobileNumber() {
		return laMobileNumber;
	}

	public void setLaMobileNumber(String laMobileNumber) {
		this.laMobileNumber = laMobileNumber;
	}

	public String getLaDob() {
		return laDob;
	}

	public void setLaDob(String laDob) {
		this.laDob = laDob;
	}

	public String getLaEmail() {
		return laEmail;
	}

	public void setLaEmail(String laEmail) {
		this.laEmail = laEmail;
	}

	public String getLaGender() {
		return laGender;
	}

	public void setLaGender(String laGender) {
		this.laGender = laGender;
	}

	public String getLaPan() {
		return laPan;
	}

	public void setLaPan(String laPan) {
		this.laPan = laPan;
	}

	public String getWopRider() {
		return wopRider;
	}

	public void setWopRider(String wopRider) {
		this.wopRider = wopRider;
	}

	public String getChildBenefit() {
		return childBenefit;
	}

	public void setChildBenefit(String childBenefit) {
		this.childBenefit = childBenefit;
	}

	@Override
	public String toString() {
		return "ProductDetails [productName=" + productName + ", applicationNumber=" + applicationNumber
				+ ", policyNumber=" + policyNumber + ", biProductCode=" + biProductCode + ", productCode=" + productCode
				+ ", blLoginCode=" + blLoginCode + ", annualisedPremium=" + annualisedPremium + ", totalPremiumAmount="
				+ totalPremiumAmount + ", totalPremiumAmountWithDiscount=" + totalPremiumAmountWithDiscount
				+ ", sumAssured=" + sumAssured + ", premiuPaymentFrequency=" + premiuPaymentFrequency + ", pdfPath="
				+ pdfPath + ", proposalFormPath=" + proposalFormPath + ", premiumPaymentTerm=" + premiumPaymentTerm
				+ ", policyTerm=" + policyTerm + ", productType=" + productType + ", planOptions=" + planOptions
				+ ", personName=" + personName + ", proposalFormStatus=" + proposalFormStatus
				+ ", proposalFormCompleteDate=" + proposalFormCompleteDate + ", paymentStatus=" + paymentStatus
				+ ", paymentCompleteDate=" + paymentCompleteDate + ", laMobileNumber=" + laMobileNumber + ", laDob="
				+ laDob + ", laEmail=" + laEmail + ", laGender=" + laGender + ", laPan=" + laPan + ", wopRider="
				+ wopRider +", childBenefit="+childBenefit+"]";
	}

}
