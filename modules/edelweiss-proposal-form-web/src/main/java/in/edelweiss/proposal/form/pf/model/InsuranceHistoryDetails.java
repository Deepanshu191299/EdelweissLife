package in.edelweiss.proposal.form.pf.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class InsuranceHistoryDetails {

	@JsonProperty("la_details")
	private LaPrSpInsuranceHistoryDetails laDetails;

	@JsonProperty("proposer_details")
	private LaPrSpInsuranceHistoryDetails proposerDetails;

	@JsonProperty("spouse_details")
	private LaPrSpInsuranceHistoryDetails spouseDetails;

	public LaPrSpInsuranceHistoryDetails getLaDetails() {
		return laDetails;
	}

	public void setLaDetails(LaPrSpInsuranceHistoryDetails laDetails) {
		this.laDetails = laDetails;
	}

	public LaPrSpInsuranceHistoryDetails getProposerDetails() {
		return proposerDetails;
	}

	public void setProposerDetails(LaPrSpInsuranceHistoryDetails proposerDetails) {
		this.proposerDetails = proposerDetails;
	}

	public LaPrSpInsuranceHistoryDetails getSpouseDetails() {
		return spouseDetails;
	}

	public void setSpouseDetails(LaPrSpInsuranceHistoryDetails spouseDetails) {
		this.spouseDetails = spouseDetails;
	}

	@Override
	public String toString() {
		return "InsuranceHistoryDetails [laDetails=" + laDetails + ", proposerDetails=" + proposerDetails
				+ ", spouseDetails=" + spouseDetails + "]";
	}

	public class LaPrSpInsuranceHistoryDetails {

		@JsonProperty("app_insurance_available_yn")
		private String appInsuranceAvailableYn;

		@JsonProperty("app_insurance_name")
		private String appInsuranceName;

		@JsonProperty("app_insurance_reason")
		private String appInsuranceReason;

		@JsonProperty("app_insurance_date")
		private String appInsuranceDate;

		@JsonProperty("app_dis_ci_benefits_yn")
		private String appDisCiBenefitsYn;

		@JsonProperty("app_dis_ci_name")
		private String appDisCiName;

		@JsonProperty("app_dis_ci_reason")
		private String appDisCiReason;

		@JsonProperty("app_dis_ci_date")
		private String appDisCiDate;

		@JsonProperty("details_ins_policy_available_yn")
		private String detailsInsPolicyAvailableYn;

		@JsonProperty("ih_sf_rejected_yn")
		private String ihSfRejectedYn;

		@JsonProperty("ih_sf_rejected_reason")
		private String ihSfRejectedReason;

		@JsonProperty("ih_sf_special_rate_policy_yn")
		private String ihSfSpecialRatePolicyYn;

		@JsonProperty("e_policy_yn")
		private String ePolicyYn;

		@JsonProperty("insurance_history")
		private ArrayList<InsuranceHistory> insuranceHistory;

		public String getAppInsuranceAvailableYn() {
			return appInsuranceAvailableYn;
		}

		public void setAppInsuranceAvailableYn(String appInsuranceAvailableYn) {
			this.appInsuranceAvailableYn = appInsuranceAvailableYn;
		}

		public String getAppInsuranceName() {
			return appInsuranceName;
		}

		public void setAppInsuranceName(String appInsuranceName) {
			this.appInsuranceName = appInsuranceName;
		}

		public String getAppInsuranceReason() {
			return appInsuranceReason;
		}

		public void setAppInsuranceReason(String appInsuranceReason) {
			this.appInsuranceReason = appInsuranceReason;
		}

		public String getAppInsuranceDate() {
			return appInsuranceDate;
		}

		public void setAppInsuranceDate(String appInsuranceDate) {
			this.appInsuranceDate = appInsuranceDate;
		}

		public String getAppDisCiBenefitsYn() {
			return appDisCiBenefitsYn;
		}

		public void setAppDisCiBenefitsYn(String appDisCiBenefitsYn) {
			this.appDisCiBenefitsYn = appDisCiBenefitsYn;
		}

		public String getAppDisCiName() {
			return appDisCiName;
		}

		public void setAppDisCiName(String appDisCiName) {
			this.appDisCiName = appDisCiName;
		}

		public String getAppDisCiReason() {
			return appDisCiReason;
		}

		public void setAppDisCiReason(String appDisCiReason) {
			this.appDisCiReason = appDisCiReason;
		}

		public String getAppDisCiDate() {
			return appDisCiDate;
		}

		public void setAppDisCiDate(String appDisCiDate) {
			this.appDisCiDate = appDisCiDate;
		}

		public String getDetailsInsPolicyAvailableYn() {
			return detailsInsPolicyAvailableYn;
		}

		public void setDetailsInsPolicyAvailableYn(String detailsInsPolicyAvailableYn) {
			this.detailsInsPolicyAvailableYn = detailsInsPolicyAvailableYn;
		}

		public String getIhSfRejectedYn() {
			return ihSfRejectedYn;
		}

		public void setIhSfRejectedYn(String ihSfRejectedYn) {
			this.ihSfRejectedYn = ihSfRejectedYn;
		}

		public String getIhSfRejectedReason() {
			return ihSfRejectedReason;
		}

		public void setIhSfRejectedReason(String ihSfRejectedReason) {
			this.ihSfRejectedReason = ihSfRejectedReason;
		}

		public String getIhSfSpecialRatePolicyYn() {
			return ihSfSpecialRatePolicyYn;
		}

		public void setIhSfSpecialRatePolicyYn(String ihSfSpecialRatePolicyYn) {
			this.ihSfSpecialRatePolicyYn = ihSfSpecialRatePolicyYn;
		}

		public String getePolicyYn() {
			return ePolicyYn;
		}

		public void setePolicyYn(String ePolicyYn) {
			this.ePolicyYn = ePolicyYn;
		}

		public ArrayList<InsuranceHistory> getInsuranceHistory() {
			return insuranceHistory;
		}

		public void setInsuranceHistory(ArrayList<InsuranceHistory> insuranceHistory) {
			this.insuranceHistory = insuranceHistory;
		}

		@Override
		public String toString() {
			return "LaPrSpInsuranceHistoryDetails [appInsuranceAvailableYn=" + appInsuranceAvailableYn
					+ ", appInsuranceName=" + appInsuranceName + ", appInsuranceReason=" + appInsuranceReason
					+ ", appInsuranceDate=" + appInsuranceDate + ", appDisCiBenefitsYn=" + appDisCiBenefitsYn
					+ ", appDisCiName=" + appDisCiName + ", appDisCiReason=" + appDisCiReason + ", appDisCiDate="
					+ appDisCiDate + ", detailsInsPolicyAvailableYn=" + detailsInsPolicyAvailableYn
					+ ", ihSfRejectedYn=" + ihSfRejectedYn + ", ihSfRejectedReason=" + ihSfRejectedReason
					+ ", ihSfSpecialRatePolicyYn=" + ihSfSpecialRatePolicyYn + ", ePolicyYn=" + ePolicyYn
					+ ", insuranceHistory=" + insuranceHistory + "]";
		}

	}
}
