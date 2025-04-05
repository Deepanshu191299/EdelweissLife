package in.edelweiss.tokio.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MultipleLoginBlock {

	@JsonProperty("externalReferenceCode")
	private String externalReferenceCode;

	@JsonProperty("id")
	private Long id;

	@JsonProperty("createDate")
	private String createDate;

	@JsonProperty("policyNumber")
	private String policyNumber;

	@JsonProperty("ipAddress")
	private String ipAddress;

	@JsonProperty("loggedInDate")
	private String loggedInDate;

	@JsonProperty("loginStatus")
	private String loginStatus;

	@JsonProperty("appName")
	private String appName;
	
	@JsonProperty("loginDate")
	private String loginDate;

	public String getExternalReferenceCode() {
		return externalReferenceCode;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		this.externalReferenceCode = externalReferenceCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getLoggedInDate() {
		return loggedInDate;
	}

	public void setLoggedInDate(String loggedInDate) {
		this.loggedInDate = loggedInDate;
	}
	
	public String getLogInDate() {
		return loginDate;
	}

	public void setLogInDate(String loginDate) {
		this.loginDate = loginDate;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	@Override
	public String toString() {
		return "CustomerInvestmentDetails [externalReferenceCode=" + externalReferenceCode + ", id=" + id
				+ ", createDate=" + createDate + ", policyNumber=" + policyNumber + ", ipAddress="
				+ ipAddress + ", loggedInDate=" + loggedInDate + ", loginStatus=" + loginStatus
				+ ", appName=" + appName + ", loginDate=" + loginDate +"]";
	}

	
}
