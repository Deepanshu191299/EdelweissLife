package edelweiss.csb.integration.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;



public class CsbIntegrationResponseData {
	
	@JsonProperty("secretKey")
	private String secretKey;
	
	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getEncryptedData() {
		return encryptedData;
	}

	public void setEncryptedData(String encryptedData) {
		this.encryptedData = encryptedData;
	}

	@JsonProperty("encryptedData")
	private String encryptedData;
	
	@JsonProperty("cSBAccountNo")
	private String cSBAccountNo;
	
	@JsonProperty("cSBEmployeeId")
	private String cSBEmployeeId;
	
	@JsonProperty("covid")
	private String covid;
	
	@JsonProperty("occupation")
	private String occupation;
	
	@JsonProperty("clientName")
	private String clientName;
	
	@JsonProperty("neoApplicationNo")
	private String neoApplicationNo;
	
	@JsonProperty("customerAccountNo")
	private String customerAccountNo;
	
	@JsonProperty("emailId")
	private String emailId;
	
	@JsonProperty("riskCoverOpted")
	private String riskCoverOpted;
	
	@JsonProperty("cSBEmployeeName")
	private String cSBEmployeeName;
	
	@JsonProperty("medication")
	private String medication;
	
	@JsonProperty("surgery")
	private String surgery;

	@JsonProperty("branchName")
	private String branchName;
	
	@JsonProperty("nomineeName")
	private String nomineeName;
	
	@JsonProperty("mobileNo")
	private String mobileNo;
	
	@JsonProperty("clientDOB")
	private String clientDOB;
	
	@JsonProperty("branchCode")
	private String branchCode;
	
	@JsonProperty("pinCode")
	private String pinCode;
	
	@JsonProperty("gender")
	private String gender;
	
	@JsonProperty("relation")
	private String relation;
	
	@JsonProperty("premium")
	private String premium;
	
	@JsonProperty("csbIntegrationStatus")
	private String csbIntegrationStatus;
	
	@JsonProperty("transactionId")
	private String transactionId;
	
	
	@JsonProperty("otpTrigerredDate")
	private String otpTrigerredDate;
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("age")
	private String age;
	
	@JsonProperty("transactionDate")
	private String transactionDate;
	
	@JsonProperty("transactionMonth")
	private String transactionMonth;
	
	public String getTransactionMonth() {
		return transactionMonth;
	}

	public void setTransactionMonth(String transactionMonth) {
		this.transactionMonth = transactionMonth;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOtpTrigerredDate() {
		return otpTrigerredDate;
	}

	public void setOtpTrigerredDate(String otpTrigerredDate) {
		this.otpTrigerredDate = otpTrigerredDate;
	}

	

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	

	public String getCsbIntegrationStatus() {
		return csbIntegrationStatus;
	}

	public void setCsbIntegrationStatus(String csbIntegrationStatus) {
		this.csbIntegrationStatus = csbIntegrationStatus;
	}

	public String getPremium() {
		return premium;
	}

	public void setPremium(String premium) {
		this.premium = premium;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getcSBEmployeeId() {
		return cSBEmployeeId;
	}

	public void setcSBEmployeeId(String cSBEmployeeId) {
		this.cSBEmployeeId = cSBEmployeeId;
	}

	public String getCovid() {
		return covid;
	}

	public void setCovid(String covid) {
		this.covid = covid;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getNeoApplicationNo() {
		return neoApplicationNo;
	}

	public void setNeoApplicationNo(String neoApplicationNo) {
		this.neoApplicationNo = neoApplicationNo;
	}

	public String getCustomerAccountNo() {
		return customerAccountNo;
	}

	public void setCustomerAccountNo(String customerAccountNo) {
		this.customerAccountNo = customerAccountNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getRiskCoverOpted() {
		return riskCoverOpted;
	}

	public void setRiskCoverOpted(String riskCoverOpted) {
		this.riskCoverOpted = riskCoverOpted;
	}

	public String getcSBEmployeeName() {
		return cSBEmployeeName;
	}

	public void setcSBEmployeeName(String cSBEmployeeName) {
		this.cSBEmployeeName = cSBEmployeeName;
	}

	public String getMedication() {
		return medication;
	}

	public void setMedication(String medication) {
		this.medication = medication;
	}
	
	public String getSurgery() {
		return surgery;
	}

	public void setSurgery(String surgery) {
		this.surgery = surgery;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getNomineeName() {
		return nomineeName;
	}

	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getClientDOB() {
		return clientDOB;
	}

	public void setClientDOB(String clientDOB) {
		this.clientDOB = clientDOB;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getcSBAccountNo() {
		return cSBAccountNo;
	}

	public void setcSBAccountNo(String cSBAccountNo) {
		this.cSBAccountNo = cSBAccountNo;
	}
	
	
}
