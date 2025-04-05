package in.edelweiss.proposal.form.pf.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class MedicalQuestionDetails {

	@JsonProperty("la_details")
	private ArrayList<LaPrSpMedicalQuestionDetails> laDetails;

	@JsonProperty("proposer_details")
	private ArrayList<LaPrSpMedicalQuestionDetails> proposerDetails;

	@JsonProperty("spouse_details")
	private ArrayList<LaPrSpMedicalQuestionDetails> spouseDetails;

	public ArrayList<LaPrSpMedicalQuestionDetails> getLaDetails() {
		return laDetails;
	}

	public void setLaDetails(ArrayList<LaPrSpMedicalQuestionDetails> laDetails) {
		this.laDetails = laDetails;
	}

	public ArrayList<LaPrSpMedicalQuestionDetails> getProposerDetails() {
		return proposerDetails;
	}

	public void setProposerDetails(ArrayList<LaPrSpMedicalQuestionDetails> proposerDetails) {
		this.proposerDetails = proposerDetails;
	}

	public ArrayList<LaPrSpMedicalQuestionDetails> getSpouseDetails() {
		return spouseDetails;
	}

	public void setSpouseDetails(ArrayList<LaPrSpMedicalQuestionDetails> spouseDetails) {
		this.spouseDetails = spouseDetails;
	}

	@Override
	public String toString() {
		return "MedicalQuestionDetails [laDetails=" + laDetails + ", proposerDetails=" + proposerDetails
				+ ", spouseDetails=" + spouseDetails + "]";
	}



}
