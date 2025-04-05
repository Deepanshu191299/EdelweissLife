package in.edelweiss.tokio.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompanyDetails {

	@JsonProperty("COMPANY_STATE")
	private Integer companyState;
	
	@JsonProperty("GSTIN")
	private String gstin;
	
	@JsonProperty("GSTIN_Number")
	private Integer gSTINNumber;

	public Integer getCompanyState() {
		return companyState;
	}

	public void setCompanyState(Integer companyState) {
		this.companyState = companyState;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public Integer getgSTINNumber() {
		return gSTINNumber;
	}

	public void setgSTINNumber(Integer gSTINNumber) {
		this.gSTINNumber = gSTINNumber;
	}
	
}
