package in.edelweiss.proposal.form.pf.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class FamilyDetails {

	@JsonProperty("la_details")
	private ArrayList<LaPrSpFamilyDetails> laDetails;

	@JsonProperty("proposer_details")
	private ArrayList<LaPrSpFamilyDetails> proposerDetails;

	@JsonProperty("spouse_details")
	private ArrayList<LaPrSpFamilyDetails> spouseDetails;

	public ArrayList<LaPrSpFamilyDetails> getLaDetails() {
		return laDetails;
	}

	public void setLaDetails(ArrayList<LaPrSpFamilyDetails> laDetails) {
		this.laDetails = laDetails;
	}

	public ArrayList<LaPrSpFamilyDetails> getProposerDetails() {
		return proposerDetails;
	}

	public void setProposerDetails(ArrayList<LaPrSpFamilyDetails> proposerDetails) {
		this.proposerDetails = proposerDetails;
	}

	public ArrayList<LaPrSpFamilyDetails> getSpouseDetails() {
		return spouseDetails;
	}

	public void setSpouseDetails(ArrayList<LaPrSpFamilyDetails> spouseDetails) {
		this.spouseDetails = spouseDetails;
	}

	@Override
	public String toString() {
		return "FamilyDetails [laDetails=" + laDetails + ", proposerDetails=" + proposerDetails + ", spouseDetails="
				+ spouseDetails + "]";
	}

	public static class LaPrSpFamilyDetails {

		@JsonProperty("ih_row_id")
		private int ihRowId;

		@JsonProperty("relationship_data_id")
		private int relationshipDataId;

		@JsonProperty("occupation")
		private String occupation;

		@JsonProperty("annual_income")
		private long annualIncome;

		@JsonProperty("total_sum_assured")
		private long totalSumAssured;

		@JsonProperty("can_delete")
		private boolean canDelete;

		public int getIhRowId() {
			return ihRowId;
		}

		public void setIhRowId(int ihRowId) {
			this.ihRowId = ihRowId;
		}

		public int getRelationshipDataId() {
			return relationshipDataId;
		}

		public void setRelationshipDataId(int relationshipDataId) {
			this.relationshipDataId = relationshipDataId;
		}

		public String getOccupation() {
			return occupation;
		}

		public void setOccupation(String occupation) {
			this.occupation = occupation;
		}

		public long getAnnualIncome() {
			return annualIncome;
		}

		public void setAnnualIncome(long annualIncome) {
			this.annualIncome = annualIncome;
		}

		public long getTotalSumAssured() {
			return totalSumAssured;
		}

		public void setTotalSumAssured(long totalSumAssured) {
			this.totalSumAssured = totalSumAssured;
		}

		public boolean isCanDelete() {
			return canDelete;
		}

		public void setCanDelete(boolean canDelete) {
			this.canDelete = canDelete;
		}

		@Override
		public String toString() {
			return "LaPrSpFamilyDetails [ihRowId=" + ihRowId + ", relationshipDataId=" + relationshipDataId
					+ ", occupation=" + occupation + ", annualIncome=" + annualIncome + ", totalSumAssured="
					+ totalSumAssured + ", canDelete=" + canDelete + "]";
		}

	}

}
