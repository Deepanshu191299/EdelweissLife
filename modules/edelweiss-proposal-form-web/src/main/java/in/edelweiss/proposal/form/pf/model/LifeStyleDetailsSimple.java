package in.edelweiss.proposal.form.pf.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LifeStyleDetailsSimple {

	@JsonProperty("la_details")
	private Object laDetails;

	@JsonProperty("proposer_details")
	private Object proposerDetails;

	@JsonProperty("spouse_details")
	private Object spouseDetails;

	public Object getLaDetails() {
		return laDetails;
	}

	public void setLaDetails(Object laDetails) {
		this.laDetails = laDetails;
	}

	public Object getProposerDetails() {
		return proposerDetails;
	}

	public void setProposerDetails(Object proposerDetails) {
		this.proposerDetails = proposerDetails;
	}

	public Object getSpouseDetails() {
		return spouseDetails;
	}

	public void setSpouseDetails(Object spouseDetails) {
		this.spouseDetails = spouseDetails;
	}

	@Override
	public String toString() {
		return "LifeStyleDetailsSimple [laDetails=" + laDetails + ", proposerDetails=" + proposerDetails
				+ ", spouseDetails=" + spouseDetails + "]";
	}

}
