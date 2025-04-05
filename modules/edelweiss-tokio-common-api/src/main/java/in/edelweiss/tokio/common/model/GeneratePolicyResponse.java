package in.edelweiss.tokio.common.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "QuotationID", "Application_Number", "Policy_Number", "Status", "Form_Type", "Name",
		"Product_Name", "Journey_URL", "PRG_LeadID", "IsPolicyNumberValid_YN", "BI_ProductCode", "Form_Type_Name",
		"Soln_BI_Product_Code", "Soln_Product_Name", "lstQuotationApplicationDetails", "ApplicationStatusDetail",
		"WrapperRequestTime", "WrapperResponseTime", "LoginCode", "Authentication", "ErrorCode", "ErrorMessage",
		"SuccessMessage", "ActualErrorCode", "ActualErrorMessage", "AddOutput1", "AddOutput2", "AddOutput3",
		"AddOutput4", "AddOutput5" })
public class GeneratePolicyResponse {

	@JsonProperty("QuotationID")
	private String quotationID;
	@JsonProperty("Application_Number")
	private String applicationNumber;
	@JsonProperty("Policy_Number")
	private String policyNumber;
	@JsonProperty("Status")
	private String status;
	@JsonProperty("Form_Type")
	private String formType;
	@JsonProperty("Name")
	private String name;
	@JsonProperty("Product_Name")
	private String productName;
	@JsonProperty("Journey_URL")
	private String journeyURL;
	@JsonProperty("PRG_LeadID")
	private String pRGLeadID;
	@JsonProperty("IsPolicyNumberValid_YN")
	private String isPolicyNumberValidYN;
	@JsonProperty("BI_ProductCode")
	private String bIProductCode;
	@JsonProperty("Form_Type_Name")
	private String formTypeName;
	@JsonProperty("Soln_BI_Product_Code")
	private Object solnBIProductCode;
	@JsonProperty("Soln_Product_Name")
	private Object solnProductName;
	@JsonProperty("lstQuotationApplicationDetails")
	private List<Object> lstQuotationApplicationDetails;
	@JsonProperty("ApplicationStatusDetail")
	private ApplicationStatusDetail applicationStatusDetail;
	@JsonProperty("WrapperRequestTime")
	private String wrapperRequestTime;
	@JsonProperty("WrapperResponseTime")
	private String wrapperResponseTime;
	@JsonProperty("LoginCode")
	private String loginCode;
	@JsonProperty("Authentication")
	private String authentication;
	@JsonProperty("ErrorCode")
	private String errorCode;
	@JsonProperty("ErrorMessage")
	private String errorMessage;
	@JsonProperty("SuccessMessage")
	private String successMessage;
	@JsonProperty("ActualErrorCode")
	private String actualErrorCode;
	@JsonProperty("ActualErrorMessage")
	private String actualErrorMessage;
	@JsonProperty("AddOutput1")
	private String addOutput1;
	@JsonProperty("AddOutput2")
	private String addOutput2;
	@JsonProperty("AddOutput3")
	private String addOutput3;
	@JsonProperty("AddOutput4")
	private String addOutput4;
	@JsonProperty("AddOutput5")
	private String addOutput5;

	@JsonProperty("QuotationID")
	public String getQuotationID() {
		return quotationID;
	}

	@JsonProperty("QuotationID")
	public void setQuotationID(String quotationID) {
		this.quotationID = quotationID;
	}

	@JsonProperty("Application_Number")
	public String getApplicationNumber() {
		return applicationNumber;
	}

	@JsonProperty("Application_Number")
	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	@JsonProperty("Policy_Number")
	public String getPolicyNumber() {
		return policyNumber;
	}

	@JsonProperty("Policy_Number")
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	@JsonProperty("Status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("Status")
	public void setStatus(String status) {
		this.status = status;
	}

	@JsonProperty("Form_Type")
	public String getFormType() {
		return formType;
	}

	@JsonProperty("Form_Type")
	public void setFormType(String formType) {
		this.formType = formType;
	}

	@JsonProperty("Name")
	public String getName() {
		return name;
	}

	@JsonProperty("Name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("Product_Name")
	public String getProductName() {
		return productName;
	}

	@JsonProperty("Product_Name")
	public void setProductName(String productName) {
		this.productName = productName;
	}

	@JsonProperty("Journey_URL")
	public String getJourneyURL() {
		return journeyURL;
	}

	@JsonProperty("Journey_URL")
	public void setJourneyURL(String journeyURL) {
		this.journeyURL = journeyURL;
	}

	@JsonProperty("PRG_LeadID")
	public String getPRGLeadID() {
		return pRGLeadID;
	}

	@JsonProperty("PRG_LeadID")
	public void setPRGLeadID(String pRGLeadID) {
		this.pRGLeadID = pRGLeadID;
	}

	@JsonProperty("IsPolicyNumberValid_YN")
	public String getIsPolicyNumberValidYN() {
		return isPolicyNumberValidYN;
	}

	@JsonProperty("IsPolicyNumberValid_YN")
	public void setIsPolicyNumberValidYN(String isPolicyNumberValidYN) {
		this.isPolicyNumberValidYN = isPolicyNumberValidYN;
	}

	@JsonProperty("BI_ProductCode")
	public String getBIProductCode() {
		return bIProductCode;
	}

	@JsonProperty("BI_ProductCode")
	public void setBIProductCode(String bIProductCode) {
		this.bIProductCode = bIProductCode;
	}

	@JsonProperty("Form_Type_Name")
	public String getFormTypeName() {
		return formTypeName;
	}

	@JsonProperty("Form_Type_Name")
	public void setFormTypeName(String formTypeName) {
		this.formTypeName = formTypeName;
	}

	@JsonProperty("Soln_BI_Product_Code")
	public Object getSolnBIProductCode() {
		return solnBIProductCode;
	}

	@JsonProperty("Soln_BI_Product_Code")
	public void setSolnBIProductCode(Object solnBIProductCode) {
		this.solnBIProductCode = solnBIProductCode;
	}

	@JsonProperty("Soln_Product_Name")
	public Object getSolnProductName() {
		return solnProductName;
	}

	@JsonProperty("Soln_Product_Name")
	public void setSolnProductName(Object solnProductName) {
		this.solnProductName = solnProductName;
	}

	@JsonProperty("lstQuotationApplicationDetails")
	public List<Object> getLstQuotationApplicationDetails() {
		return lstQuotationApplicationDetails;
	}

	@JsonProperty("lstQuotationApplicationDetails")
	public void setLstQuotationApplicationDetails(List<Object> lstQuotationApplicationDetails) {
		this.lstQuotationApplicationDetails = lstQuotationApplicationDetails;
	}

	@JsonProperty("ApplicationStatusDetail")
	public ApplicationStatusDetail getApplicationStatusDetail() {
		return applicationStatusDetail;
	}

	@JsonProperty("ApplicationStatusDetail")
	public void setApplicationStatusDetail(ApplicationStatusDetail applicationStatusDetail) {
		this.applicationStatusDetail = applicationStatusDetail;
	}

	@JsonProperty("WrapperRequestTime")
	public String getWrapperRequestTime() {
		return wrapperRequestTime;
	}

	@JsonProperty("WrapperRequestTime")
	public void setWrapperRequestTime(String wrapperRequestTime) {
		this.wrapperRequestTime = wrapperRequestTime;
	}

	@JsonProperty("WrapperResponseTime")
	public String getWrapperResponseTime() {
		return wrapperResponseTime;
	}

	@JsonProperty("WrapperResponseTime")
	public void setWrapperResponseTime(String wrapperResponseTime) {
		this.wrapperResponseTime = wrapperResponseTime;
	}

	@JsonProperty("LoginCode")
	public String getLoginCode() {
		return loginCode;
	}

	@JsonProperty("LoginCode")
	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	@JsonProperty("Authentication")
	public String getAuthentication() {
		return authentication;
	}

	@JsonProperty("Authentication")
	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}

	@JsonProperty("ErrorCode")
	public String getErrorCode() {
		return errorCode;
	}

	@JsonProperty("ErrorCode")
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	@JsonProperty("ErrorMessage")
	public String getErrorMessage() {
		return errorMessage;
	}

	@JsonProperty("ErrorMessage")
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@JsonProperty("SuccessMessage")
	public String getSuccessMessage() {
		return successMessage;
	}

	@JsonProperty("SuccessMessage")
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	@JsonProperty("ActualErrorCode")
	public String getActualErrorCode() {
		return actualErrorCode;
	}

	@JsonProperty("ActualErrorCode")
	public void setActualErrorCode(String actualErrorCode) {
		this.actualErrorCode = actualErrorCode;
	}

	@JsonProperty("ActualErrorMessage")
	public String getActualErrorMessage() {
		return actualErrorMessage;
	}

	@JsonProperty("ActualErrorMessage")
	public void setActualErrorMessage(String actualErrorMessage) {
		this.actualErrorMessage = actualErrorMessage;
	}

	@JsonProperty("AddOutput1")
	public String getAddOutput1() {
		return addOutput1;
	}

	@JsonProperty("AddOutput1")
	public void setAddOutput1(String addOutput1) {
		this.addOutput1 = addOutput1;
	}

	@JsonProperty("AddOutput2")
	public String getAddOutput2() {
		return addOutput2;
	}

	@JsonProperty("AddOutput2")
	public void setAddOutput2(String addOutput2) {
		this.addOutput2 = addOutput2;
	}

	@JsonProperty("AddOutput3")
	public String getAddOutput3() {
		return addOutput3;
	}

	@JsonProperty("AddOutput3")
	public void setAddOutput3(String addOutput3) {
		this.addOutput3 = addOutput3;
	}

	@JsonProperty("AddOutput4")
	public String getAddOutput4() {
		return addOutput4;
	}

	@JsonProperty("AddOutput4")
	public void setAddOutput4(String addOutput4) {
		this.addOutput4 = addOutput4;
	}

	@JsonProperty("AddOutput5")
	public String getAddOutput5() {
		return addOutput5;
	}

	@JsonProperty("AddOutput5")
	public void setAddOutput5(String addOutput5) {
		this.addOutput5 = addOutput5;
	}

	@Override
	public String toString() {
		return "GeneratePolicyResponse [quotationID=" + quotationID + ", applicationNumber=" + applicationNumber
				+ ", policyNumber=" + policyNumber + ", status=" + status + ", formType=" + formType + ", name=" + name
				+ ", productName=" + productName + ", journeyURL=" + journeyURL + ", pRGLeadID=" + pRGLeadID
				+ ", isPolicyNumberValidYN=" + isPolicyNumberValidYN + ", bIProductCode=" + bIProductCode
				+ ", formTypeName=" + formTypeName + ", solnBIProductCode=" + solnBIProductCode + ", solnProductName="
				+ solnProductName + ", lstQuotationApplicationDetails=" + lstQuotationApplicationDetails
				+ ", applicationStatusDetail=" + applicationStatusDetail + ", wrapperRequestTime=" + wrapperRequestTime
				+ ", wrapperResponseTime=" + wrapperResponseTime + ", loginCode=" + loginCode + ", authentication="
				+ authentication + ", errorCode=" + errorCode + ", errorMessage=" + errorMessage + ", successMessage="
				+ successMessage + ", actualErrorCode=" + actualErrorCode + ", actualErrorMessage=" + actualErrorMessage
				+ ", addOutput1=" + addOutput1 + ", addOutput2=" + addOutput2 + ", addOutput3=" + addOutput3
				+ ", addOutput4=" + addOutput4 + ", addOutput5=" + addOutput5 + "]";
	}

	

}