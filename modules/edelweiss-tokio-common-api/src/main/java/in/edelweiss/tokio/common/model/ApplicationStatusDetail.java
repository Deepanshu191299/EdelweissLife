package in.edelweiss.tokio.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "Application_Status", "Application_Substatus", "Application_Page_URL", "Disable_AllFields_YN",
		"Is_Combo_YN", "CallThirdPartyApplicationStatusAPI_YN", "Lead_Source" })
public class ApplicationStatusDetail {

	@JsonProperty("Application_Status")
	private String applicationStatus;
	@JsonProperty("Application_Substatus")
	private String applicationSubstatus;
	@JsonProperty("Application_Page_URL")
	private String applicationPageURL;
	@JsonProperty("Disable_AllFields_YN")
	private String disableAllFieldsYN;
	@JsonProperty("Is_Combo_YN")
	private String isComboYN;
	@JsonProperty("CallThirdPartyApplicationStatusAPI_YN")
	private String callThirdPartyApplicationStatusAPIYN;
	@JsonProperty("Lead_Source")
	private String leadSource;

	@JsonProperty("Application_Status")
	public String getApplicationStatus() {
		return applicationStatus;
	}

	@JsonProperty("Application_Status")
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	@JsonProperty("Application_Substatus")
	public String getApplicationSubstatus() {
		return applicationSubstatus;
	}

	@JsonProperty("Application_Substatus")
	public void setApplicationSubstatus(String applicationSubstatus) {
		this.applicationSubstatus = applicationSubstatus;
	}

	@JsonProperty("Application_Page_URL")
	public String getApplicationPageURL() {
		return applicationPageURL;
	}

	@JsonProperty("Application_Page_URL")
	public void setApplicationPageURL(String applicationPageURL) {
		this.applicationPageURL = applicationPageURL;
	}

	@JsonProperty("Disable_AllFields_YN")
	public String getDisableAllFieldsYN() {
		return disableAllFieldsYN;
	}

	@JsonProperty("Disable_AllFields_YN")
	public void setDisableAllFieldsYN(String disableAllFieldsYN) {
		this.disableAllFieldsYN = disableAllFieldsYN;
	}

	@JsonProperty("Is_Combo_YN")
	public String getIsComboYN() {
		return isComboYN;
	}

	@JsonProperty("Is_Combo_YN")
	public void setIsComboYN(String isComboYN) {
		this.isComboYN = isComboYN;
	}

	@JsonProperty("CallThirdPartyApplicationStatusAPI_YN")
	public String getCallThirdPartyApplicationStatusAPIYN() {
		return callThirdPartyApplicationStatusAPIYN;
	}

	@JsonProperty("CallThirdPartyApplicationStatusAPI_YN")
	public void setCallThirdPartyApplicationStatusAPIYN(String callThirdPartyApplicationStatusAPIYN) {
		this.callThirdPartyApplicationStatusAPIYN = callThirdPartyApplicationStatusAPIYN;
	}

	@JsonProperty("Lead_Source")
	public String getLeadSource() {
		return leadSource;
	}

	@JsonProperty("Lead_Source")
	public void setLeadSource(String leadSource) {
		this.leadSource = leadSource;
	}

	@Override
	public String toString() {
		return "ApplicationStatusDetail [applicationStatus=" + applicationStatus + ", applicationSubstatus="
				+ applicationSubstatus + ", applicationPageURL=" + applicationPageURL + ", disableAllFieldsYN="
				+ disableAllFieldsYN + ", isComboYN=" + isComboYN + ", callThirdPartyApplicationStatusAPIYN="
				+ callThirdPartyApplicationStatusAPIYN + ", leadSource=" + leadSource + "]";
	}

	

}