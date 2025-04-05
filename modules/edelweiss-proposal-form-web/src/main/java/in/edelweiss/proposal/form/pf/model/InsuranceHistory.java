package in.edelweiss.proposal.form.pf.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InsuranceHistory {

	@JsonProperty("ih_row_id")
	private int ihRowId;

	@JsonProperty("policy_number")
	private String policyNumber;

	@JsonProperty("insurance_company_data_id")
	private int insuranceCompanyDataId;

	@JsonProperty("insurance_company_other")
	private String insuranceCompanyOther;

	@JsonProperty("proposal_date")
	private String proposalDate;

	@JsonProperty("policy_issue_year")
	private String policyIssueYear;

	@JsonProperty("policy_issue_date")
	private String policyIssueDate;

	@JsonProperty("sum_assured")
	private long sumAssured;

	@JsonProperty("annualised_premium")
	private long annualisedPremium;

	@JsonProperty("policy_status_data_id")
	private int policyStatusDataId;

	@JsonProperty("policy_status_other")
	private String policyStatusOther;

	@JsonProperty("acceptance_terms")
	private String acceptanceTerms;

	@JsonProperty("reason_for_extra_premium")
	private String reasonForExtraPremium;

	public int getIhRowId() {
		return ihRowId;
	}

	public void setIhRowId(int ihRowId) {
		this.ihRowId = ihRowId;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public int getInsuranceCompanyDataId() {
		return insuranceCompanyDataId;
	}

	public void setInsuranceCompanyDataId(int insuranceCompanyDataId) {
		this.insuranceCompanyDataId = insuranceCompanyDataId;
	}

	public String getInsuranceCompanyOther() {
		return insuranceCompanyOther;
	}

	public void setInsuranceCompanyOther(String insuranceCompanyOther) {
		this.insuranceCompanyOther = insuranceCompanyOther;
	}

	public String getProposalDate() {
		return proposalDate;
	}

	public void setProposalDate(String proposalDate) {
		this.proposalDate = proposalDate;
	}

	public String getPolicyIssueYear() {
		return policyIssueYear;
	}

	public void setPolicyIssueYear(String policyIssueYear) {
		this.policyIssueYear = policyIssueYear;
	}

	public String getPolicyIssueDate() {
		return policyIssueDate;
	}

	public void setPolicyIssueDate(String policyIssueDate) {
		this.policyIssueDate = policyIssueDate;
	}

	public long getSumAssured() {
		return sumAssured;
	}

	public void setSumAssured(long sumAssured) {
		this.sumAssured = sumAssured;
	}

	public long getAnnualisedPremium() {
		return annualisedPremium;
	}

	public void setAnnualisedPremium(long annualisedPremium) {
		this.annualisedPremium = annualisedPremium;
	}

	public int getPolicyStatusDataId() {
		return policyStatusDataId;
	}

	public void setPolicyStatusDataId(int policyStatusDataId) {
		this.policyStatusDataId = policyStatusDataId;
	}

	public String getPolicyStatusOther() {
		return policyStatusOther;
	}

	public void setPolicyStatusOther(String policyStatusOther) {
		this.policyStatusOther = policyStatusOther;
	}

	public String getAcceptanceTerms() {
		return acceptanceTerms;
	}

	public void setAcceptanceTerms(String acceptanceTerms) {
		this.acceptanceTerms = acceptanceTerms;
	}

	public String getReasonForExtraPremium() {
		return reasonForExtraPremium;
	}

	public void setReasonForExtraPremium(String reasonForExtraPremium) {
		this.reasonForExtraPremium = reasonForExtraPremium;
	}

	@Override
	public String toString() {
		return "InsuranceHistory [ihRowId=" + ihRowId + ", policyNumber=" + policyNumber + ", insuranceCompanyDataId="
				+ insuranceCompanyDataId + ", insuranceCompanyOther=" + insuranceCompanyOther + ", proposalDate="
				+ proposalDate + ", policyIssueYear=" + policyIssueYear + ", policyIssueDate=" + policyIssueDate
				+ ", sumAssured=" + sumAssured + ", annualisedPremium=" + annualisedPremium + ", policyStatusDataId="
				+ policyStatusDataId + ", policyStatusOther=" + policyStatusOther + ", acceptanceTerms="
				+ acceptanceTerms + ", reasonForExtraPremium=" + reasonForExtraPremium + "]";
	}

}
