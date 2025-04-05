package in.edelweiss.proposal.form.company.model;

public class ResponseDatum {
	public int companyID;
	public String companyName;

	public int getCompanyID() {
		return companyID;
	}

	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "ResponseDatum [companyID=" + companyID + ", companyName=" + companyName + "]";
	}

	
}
