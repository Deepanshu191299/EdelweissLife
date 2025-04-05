package in.edelweiss.proposal.form.pf.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class ReflexAnswerDetails {

	@JsonProperty("la_details")
	private ArrayList<LaDetails> laDetails;

	@JsonProperty("proposer_details")
	private ArrayList<ProposerDetails> proposerDetails;

	@JsonProperty("spouse_details")
	private ArrayList<SpouseDetails> spouseDetails;

	public ArrayList<LaDetails> getLaDetails() {
		return laDetails;
	}

	public void setLaDetails(ArrayList<LaDetails> laDetails) {
		this.laDetails = laDetails;
	}

	public ArrayList<ProposerDetails> getProposerDetails() {
		return proposerDetails;
	}

	public void setProposerDetails(ArrayList<ProposerDetails> proposerDetails) {
		this.proposerDetails = proposerDetails;
	}

	public ArrayList<SpouseDetails> getSpouseDetails() {
		return spouseDetails;
	}

	public void setSpouseDetails(ArrayList<SpouseDetails> spouseDetails) {
		this.spouseDetails = spouseDetails;
	}

	@Override
	public String toString() {
		return "ReflexAnswerDetails [laDetails=" + laDetails + ", proposerDetails=" + proposerDetails
				+ ", spouseDetails=" + spouseDetails + "]";
	}

}
