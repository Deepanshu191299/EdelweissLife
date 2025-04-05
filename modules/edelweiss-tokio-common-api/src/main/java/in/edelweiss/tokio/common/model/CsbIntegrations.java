package in.edelweiss.tokio.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CsbIntegrations {
	
	@JsonProperty("externalReferenceCode")
	private String externalReferenceCode;
	
	@JsonProperty("id")
	private Long id;

	@JsonProperty("branchCode")
	private String branchCode;
	
	@JsonProperty("branchName")
	private String branchName;
	
	@JsonProperty("clientDOB")
	private String clientDOB;
	
	@JsonProperty("clientName")
	private String clientName;
	
	@JsonProperty("cSBAccountNo")
	private String cSBAccountNo;
	
	@JsonProperty("cSBEmployeeId")
	private String cSBEmployeeId;
	
	@JsonProperty("cSBEmployeeName")
	private String cSBEmployeeName;
	
	@JsonProperty("customerAccountNo")
	private String customerAccountNo;
	
	@JsonProperty("emailId")
	private String emailId;
	
	@JsonProperty("medication")
	private String medication;
	
	@JsonProperty("mobileNo")
	private String mobileNo;
	
	@JsonProperty("neoApplicationNo")
	private String neoApplicationNo;
	
	@JsonProperty("nomineeName")
	private String nomineeName;
	
	@JsonProperty("csbIntegrationStatus")
	private String csbIntegrationStatus;
	
	@JsonProperty("occupation")
	private String occupation;
	
	@JsonProperty("pinCode")
	private String pinCode;
	
	@JsonProperty("premium")
	private String premium;
	
	@JsonProperty("relation")
	private String relation;
	
	@JsonProperty("transactionId")
	private String transactionId;
	
	@JsonProperty("otpTrigerredDate")
	private String otpTrigerredDate;
	
	@JsonProperty("age")
	private String age;
	
	@JsonProperty("transactionDate")
	private String transactionDate;
	
	@JsonProperty("transactionMonth")
	private String transactionMonth;
}
