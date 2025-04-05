package in.edelweiss.tokio.common.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "dateCreated", "dateModified", "externalReferenceCode", "id",
		"illustrationURL", "r_customerPolicyDetailsRel_c_customerEnquiryId","r_customerPolicyDetailsRel_c_customerEnquiryERC", "applicationNumber", "policyNumber",
		"applicationId", "leadId","quotationId" })
public class CustomerPolicyDetailsRel {

	
	@JsonProperty("dateCreated")
	private String dateCreated;
	@JsonProperty("dateModified")
	private String dateModified;
	@JsonProperty("externalReferenceCode")
	private String externalReferenceCode;
	@JsonProperty("id")
	private Long id;
	@JsonProperty("illustrationURL")
	private String illustationURL;
	@JsonProperty("r_customerPolicyDetailsRel_c_customerEnquiryId")
	private Long rCustomerPolicyDetailsRelCCustomerEnquiryId;
	@JsonProperty("r_customerPolicyDetailsRel_c_customerEnquiryERC")
	private String rCustomerPolicyDetailsRelCCustomerEnquiryERC;
	@JsonProperty("applicationNumber")
	private String applicationNumber;
	@JsonProperty("policyNumber")
	private String policyNumber;
	@JsonProperty("applicationId")
	private String applicationId;
	@JsonProperty("leadId")
	private String leadId;
	@JsonProperty("quotationId")
	private String quotationId;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public CustomerPolicyDetailsRel() {
	}

	/**
	 *
	 * @param illustationURL
	 * @param rCustomerPolicyDetailsRelCCustomerEnquiryId
	 * @param creator
	 * @param dateCreated
	 * @param applicationNumber
	 * @param dateModified
	 * @param policyNumber
	 * @param id
	 * @param applicationId
	 * @param externalReferenceCode
	 * @param status
	 * @param leadId
	 */
	public CustomerPolicyDetailsRel(String dateCreated, String dateModified,
			String externalReferenceCode, Long id, String illustationURL,
			Long rCustomerPolicyDetailsRelCCustomerEnquiryId, String applicationNumber, String policyNumber,
			String applicationId, String leadId,String creator,String status,String quotationId) {
		super();
		this.dateCreated = dateCreated;
		this.dateModified = dateModified;
		this.externalReferenceCode = externalReferenceCode;
		this.id = id;
		this.illustationURL = illustationURL;
		this.rCustomerPolicyDetailsRelCCustomerEnquiryId = rCustomerPolicyDetailsRelCCustomerEnquiryId;
		this.applicationNumber = applicationNumber;
		this.policyNumber = policyNumber;
		this.applicationId = applicationId;
		this.leadId = leadId;
		this.quotationId= quotationId;
	}

	@JsonProperty("quotationId")
	public String getQuotationId() {
		return quotationId;
	}

	@JsonProperty("quotationId")
	public void setQuotationId(String quotationId) {
		this.quotationId = quotationId;
	}
	
	@JsonProperty("r_customerPolicyDetailsRel_c_customerEnquiryERC")
	public String getrCustomerPolicyDetailsRelCCustomerEnquiryERC() {
		return rCustomerPolicyDetailsRelCCustomerEnquiryERC;
	}

	@JsonProperty("r_customerPolicyDetailsRel_c_customerEnquiryERC")
	public void setrCustomerPolicyDetailsRelCCustomerEnquiryERC(String rCustomerPolicyDetailsRelCCustomerEnquiryERC) {
		this.rCustomerPolicyDetailsRelCCustomerEnquiryERC = rCustomerPolicyDetailsRelCCustomerEnquiryERC;
	}

	@JsonProperty("dateCreated")
	public String getDateCreated() {
		return dateCreated;
	}

	@JsonProperty("dateCreated")
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	@JsonProperty("dateModified")
	public String getDateModified() {
		return dateModified;
	}

	@JsonProperty("dateModified")
	public void setDateModified(String dateModified) {
		this.dateModified = dateModified;
	}

	@JsonProperty("externalReferenceCode")
	public String getExternalReferenceCode() {
		return externalReferenceCode;
	}

	@JsonProperty("externalReferenceCode")
	public void setExternalReferenceCode(String externalReferenceCode) {
		this.externalReferenceCode = externalReferenceCode;
	}

	@JsonProperty("id")
	public Long getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Long id) {
		this.id = id;
	}

	@JsonProperty("illustationURL")
	public String getIllustationURL() {
		return illustationURL;
	}

	@JsonProperty("illustationURL")
	public void setIllustationURL(String illustationURL) {
		this.illustationURL = illustationURL;
	}

	@JsonProperty("r_customerPolicyDetailsRel_c_customerEnquiryId")
	public Long getrCustomerPolicyDetailsRelCCustomerEnquiryId() {
		return rCustomerPolicyDetailsRelCCustomerEnquiryId;
	}

	@JsonProperty("r_customerPolicyDetailsRel_c_customerEnquiryId")
	public void setrCustomerPolicyDetailsRelCCustomerEnquiryId(Long rCustomerPolicyDetailsRelCCustomerEnquiryId) {
		this.rCustomerPolicyDetailsRelCCustomerEnquiryId = rCustomerPolicyDetailsRelCCustomerEnquiryId;
	}

	@JsonProperty("applicationNumber")
	public String getApplicationNumber() {
		return applicationNumber;
	}

	@JsonProperty("applicationNumber")
	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	@JsonProperty("policyNumber")
	public String getPolicyNumber() {
		return policyNumber;
	}

	@JsonProperty("policyNumber")
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	@JsonProperty("applicationId")
	public String getApplicationId() {
		return applicationId;
	}

	@JsonProperty("applicationId")
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	@JsonProperty("leadId")
	public String getLeadId() {
		return leadId;
	}

	@JsonProperty("leadId")
	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}

	@Override
	public String toString() {
		return "CustomerPolicyDetailsRel [dateCreated=" + dateCreated + ", dateModified=" + dateModified
				+ ", externalReferenceCode=" + externalReferenceCode + ", id=" + id + ", illustationURL="
				+ illustationURL + ", rCustomerPolicyDetailsRelCCustomerEnquiryId="
				+ rCustomerPolicyDetailsRelCCustomerEnquiryId + ", rCustomerPolicyDetailsRelCCustomerEnquiryERC="
				+ rCustomerPolicyDetailsRelCCustomerEnquiryERC + ", applicationNumber=" + applicationNumber
				+ ", policyNumber=" + policyNumber + ", applicationId=" + applicationId + ", leadId=" + leadId
				+ ", quotationId=" + quotationId + "]";
	}
}
