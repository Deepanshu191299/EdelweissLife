package in.edelweiss.proposal.form.pf.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class FamilyHistoryDetails {

	@JsonProperty("la_details")
	private LaPrSpFamilyHistoryDetails laDetails;

	@JsonProperty("proposer_details")
	private LaPrSpFamilyHistoryDetails proposerDetails;

	@JsonProperty("spouse_details")
	private LaPrSpFamilyHistoryDetails spouseDetails;

	public LaPrSpFamilyHistoryDetails getLaDetails() {
		return laDetails;
	}

	public void setLaDetails(LaPrSpFamilyHistoryDetails laDetails) {
		this.laDetails = laDetails;
	}

	public LaPrSpFamilyHistoryDetails getProposerDetails() {
		return proposerDetails;
	}

	public void setProposerDetails(LaPrSpFamilyHistoryDetails proposerDetails) {
		this.proposerDetails = proposerDetails;
	}

	public LaPrSpFamilyHistoryDetails getSpouseDetails() {
		return spouseDetails;
	}

	public void setSpouseDetails(LaPrSpFamilyHistoryDetails spouseDetails) {
		this.spouseDetails = spouseDetails;
	}

	@Override
	public String toString() {
		return "FamilyHistoryDetails [laDetails=" + laDetails + ", proposerDetails=" + proposerDetails
				+ ", spouseDetails=" + spouseDetails + "]";
	}

	public class LaPrSpFamilyHistoryDetails {

		@JsonProperty("fh_any_disease_yn")
		private String fhAnyDiseaseYn;

		@JsonProperty("fh_any_disease_details")
		private String fhAnyDiseaseDetails;

		@JsonProperty("fh_etli_policies_yn")
		private String fhEtliPoliciesYn;

		@JsonProperty("family_history")
		private ArrayList<FamilyHistory> familyHistory;

		public String getFhAnyDiseaseYn() {
			return fhAnyDiseaseYn;
		}

		public void setFhAnyDiseaseYn(String fhAnyDiseaseYn) {
			this.fhAnyDiseaseYn = fhAnyDiseaseYn;
		}

		public String getFhAnyDiseaseDetails() {
			return fhAnyDiseaseDetails;
		}

		public void setFhAnyDiseaseDetails(String fhAnyDiseaseDetails) {
			this.fhAnyDiseaseDetails = fhAnyDiseaseDetails;
		}

		public String getFhEtliPoliciesYn() {
			return fhEtliPoliciesYn;
		}

		public void setFhEtliPoliciesYn(String fhEtliPoliciesYn) {
			this.fhEtliPoliciesYn = fhEtliPoliciesYn;
		}

		public ArrayList<FamilyHistory> getFamilyHistory() {
			return familyHistory;
		}

		public void setFamilyHistory(ArrayList<FamilyHistory> familyHistory) {
			this.familyHistory = familyHistory;
		}

		@Override
		public String toString() {
			return "LaPrSpFamilyDetails [fhAnyDiseaseYn=" + fhAnyDiseaseYn + ", fhAnyDiseaseDetails="
					+ fhAnyDiseaseDetails + ", fhEtliPoliciesYn=" + fhEtliPoliciesYn + ", familyHistory="
					+ familyHistory + "]";
		}

	}

}
