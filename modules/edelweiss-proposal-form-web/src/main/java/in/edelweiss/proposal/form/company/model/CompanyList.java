package in.edelweiss.proposal.form.company.model;

import java.util.ArrayList;

public class CompanyList {

	public boolean status;
	public ArrayList<String> errors;
	public ArrayList<ResponseDatum> responseData;

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

	public ArrayList<ResponseDatum> getResponseData() {
		return responseData;
	}

	public void setResponseData(ArrayList<ResponseDatum> responseData) {
		this.responseData = responseData;
	}

	@Override
	public String toString() {
		return "CompanyList [status=" + status + ", errors=" + errors + ", responseData=" + responseData + "]";
	}

	
}
