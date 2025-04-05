package in.edelweiss.tokio.common.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "Application_Number", "LMSQuoteId", "Paid_YN", "Policy_Number", "QuotationID", "Stage", "Status",
		"Tab_Id" })

public class Application {

	@JsonProperty("Application_Number")
	private Long applicationNumber;
	@JsonProperty("LMSQuoteId")
	private String lMSQuoteId;
	@JsonProperty("Paid_YN")
	private String paidYN;
	@JsonProperty("Policy_Number")
	private String policyNumber;
	@JsonProperty("QuotationID")
	private String quotationID;
	@JsonProperty("Stage")
	private String stage;
	@JsonProperty("Status")
	private String status;
	@JsonProperty("Tab_Id")
	private String tabId;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<>();

	@JsonProperty("Application_Number")
	public Long getApplicationNumber() {
		return applicationNumber;
	}

	@JsonProperty("Application_Number")
	public void setApplicationNumber(Long applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	@JsonProperty("LMSQuoteId")
	public String getLMSQuoteId() {
		return lMSQuoteId;
	}

	@JsonProperty("LMSQuoteId")
	public void setLMSQuoteId(String lMSQuoteId) {
		this.lMSQuoteId = lMSQuoteId;
	}

	@JsonProperty("Paid_YN")
	public String getPaidYN() {
		return paidYN;
	}

	@JsonProperty("Paid_YN")
	public void setPaidYN(String paidYN) {
		this.paidYN = paidYN;
	}

	@JsonProperty("Policy_Number")
	public String getPolicyNumber() {
		return policyNumber;
	}

	@JsonProperty("Policy_Number")
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	@JsonProperty("QuotationID")
	public String getQuotationID() {
		return quotationID;
	}

	@JsonProperty("QuotationID")
	public void setQuotationID(String quotationID) {
		this.quotationID = quotationID;
	}

	@JsonProperty("Stage")
	public String getStage() {
		return stage;
	}

	@JsonProperty("Stage")
	public void setStage(String stage) {
		this.stage = stage;
	}

	@JsonProperty("Status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("Status")
	public void setStatus(String status) {
		this.status = status;
	}

	@JsonProperty("Tab_Id")
	public String getTabId() {
		return tabId;
	}

	@JsonProperty("Tab_Id")
	public void setTabId(String tabId) {
		this.tabId = tabId;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return "Application [applicationNumber=" + applicationNumber + ", lMSQuoteId=" + lMSQuoteId + ", paidYN="
				+ paidYN + ", policyNumber=" + policyNumber + ", quotationID=" + quotationID + ", stage=" + stage
				+ ", status=" + status + ", tabId=" + tabId + ", additionalProperties=" + additionalProperties + "]";
	}

	

}
