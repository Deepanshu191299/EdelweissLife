package in.edelweiss.proposal.form.pf.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProposalJourney {

	@JsonProperty("status")
	public boolean status;

	@JsonProperty("errors")
	public ArrayList<String> errors;

	@JsonProperty("response_data")
	public ResponseData responseData;

	@JsonProperty("responseData")
	public ResponseData2 responseData2;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public ArrayList<String> getErrors() {
		return errors;
	}

	public void setErrors(ArrayList<String> errors) {
		this.errors = errors;
	}

	public ResponseData getResponseData() {
		return responseData;
	}

	public void setResponseData(ResponseData responseData) {
		this.responseData = responseData;
	}

	public ResponseData2 getResponseData2() {
		return responseData2;
	}

	public void setResponseData2(ResponseData2 responseData2) {
		this.responseData2 = responseData2;
	}

	@Override
	public String toString() {
		return "ProposalJourney [status=" + status + ", errors=" + errors + ", responseData=" + responseData + "]";
	}

}
