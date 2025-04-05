
package in.edelweiss.tokio.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "QuotationID", "LeadID", "SRC", "API_Source", "UTM_Source", "PayFirst_YN", "SourceFrom",
		"IPAddress", "Application_Status" })
public class GeneratePolicyNo {

	@JsonProperty("QuotationID")
	private String quotationID;
	@JsonProperty("LeadID")
	private String leadID;
	@JsonProperty("SRC")
	private String src;
	@JsonProperty("API_Source")
	private String aPISource;
	@JsonProperty("UTM_Source")
	private String uTMSource;
	@JsonProperty("PayFirst_YN")
	private String payFirstYN;
	@JsonProperty("SourceFrom")
	private String sourceFrom;
	@JsonProperty("IPAddress")
	private String iPAddress;
	@JsonProperty("Application_Status")
	private String applicationStatus;
	

	@JsonProperty("QuotationID")
	public String getQuotationID() {
		return quotationID;
	}

	@JsonProperty("QuotationID")
	public void setQuotationID(String quotationID) {
		this.quotationID = quotationID;
	}

	@JsonProperty("LeadID")
	public String getLeadID() {
		return leadID;
	}

	@JsonProperty("LeadID")
	public void setLeadID(String leadID) {
		this.leadID = leadID;
	}

	@JsonProperty("SRC")
	public String getSrc() {
		return src;
	}

	@JsonProperty("SRC")
	public void setSrc(String src) {
		this.src = src;
	}

	@JsonProperty("API_Source")
	public String getAPISource() {
		return aPISource;
	}

	@JsonProperty("API_Source")
	public void setAPISource(String aPISource) {
		this.aPISource = aPISource;
	}

	@JsonProperty("UTM_Source")
	public String getUTMSource() {
		return uTMSource;
	}

	@JsonProperty("UTM_Source")
	public void setUTMSource(String uTMSource) {
		this.uTMSource = uTMSource;
	}

	@JsonProperty("PayFirst_YN")
	public String getPayFirstYN() {
		return payFirstYN;
	}

	@JsonProperty("PayFirst_YN")
	public void setPayFirstYN(String payFirstYN) {
		this.payFirstYN = payFirstYN;
	}

	@JsonProperty("SourceFrom")
	public String getSourceFrom() {
		return sourceFrom;
	}

	@JsonProperty("SourceFrom")
	public void setSourceFrom(String sourceFrom) {
		this.sourceFrom = sourceFrom;
	}

	@JsonProperty("IPAddress")
	public String getIPAddress() {
		return iPAddress;
	}

	@JsonProperty("IPAddress")
	public void setIPAddress(String iPAddress) {
		this.iPAddress = iPAddress;
	}

	@JsonProperty("Application_Status")
	public String getApplicationStatus() {
		return applicationStatus;
	}

	@JsonProperty("Application_Status")
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	@Override
	public String toString() {
		return "GeneratePolicyNo [quotationID=" + quotationID + ", leadID=" + leadID + ", src=" + src + ", aPISource="
				+ aPISource + ", uTMSource=" + uTMSource + ", payFirstYN=" + payFirstYN + ", sourceFrom=" + sourceFrom
				+ ", iPAddress=" + iPAddress + ", applicationStatus=" + applicationStatus + "]";
	}

}
