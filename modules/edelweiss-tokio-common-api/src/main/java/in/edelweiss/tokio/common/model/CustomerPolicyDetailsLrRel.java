package in.edelweiss.tokio.common.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "items" })

public class CustomerPolicyDetailsLrRel {

	@JsonProperty("quotationId")
	private String quotationId;
	@JsonProperty("dateCreated")
	private String dateCreated;
	@JsonProperty("dateModified")
	private String dateModified;
	@JsonProperty("externalReferenceCode")
	private String externalReferenceCode;
	@JsonProperty("id")
	private Integer id;
	@JsonProperty("r_customerPolicyDetailsRel_c_customerEnquiryId")
	private Integer rCustomerPolicyDetailsRelCCustomerEnquiryId;
	@JsonProperty("applicationNumber")
	private String applicationNumber;
	@JsonProperty("policyNumber")
	private String policyNumber;
	@JsonProperty("illustrationURL")
	private String illustrationURL;
	@JsonProperty("r_customerPolicyDetailsRel_c_customerEnquiryERC")
	private String rCustomerPolicyDetailsRelCCustomerEnquiryERC;
	@JsonProperty("applicationId")
	private String applicationId;
	@JsonProperty("leadId")
	private String leadId;
	@JsonProperty("items")
	private List<Item> items;

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
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}

	@JsonProperty("r_customerPolicyDetailsRel_c_customerEnquiryId")
	public Integer getrCustomerPolicyDetailsRelCCustomerEnquiryId() {
		return rCustomerPolicyDetailsRelCCustomerEnquiryId;
	}

	@JsonProperty("r_customerPolicyDetailsRel_c_customerEnquiryId")
	public void setrCustomerPolicyDetailsRelCCustomerEnquiryId(Integer rCustomerPolicyDetailsRelCCustomerEnquiryId) {
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

	@JsonProperty("illustrationURL")
	public String getIllustrationURL() {
		return illustrationURL;
	}

	@JsonProperty("illustrationURL")
	public void setIllustrationURL(String illustrationURL) {
		this.illustrationURL = illustrationURL;
	}

	@JsonProperty("r_customerPolicyDetailsRel_c_customerEnquiryERC")
	public String getrCustomerPolicyDetailsRelCCustomerEnquiryERC() {
		return rCustomerPolicyDetailsRelCCustomerEnquiryERC;
	}

	@JsonProperty("r_customerPolicyDetailsRel_c_customerEnquiryERC")
	public void setrCustomerPolicyDetailsRelCCustomerEnquiryERC(String rCustomerPolicyDetailsRelCCustomerEnquiryERC) {
		this.rCustomerPolicyDetailsRelCCustomerEnquiryERC = rCustomerPolicyDetailsRelCCustomerEnquiryERC;
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

	public String getQuotationId() {
		return quotationId;
	}

	public void setQuotationId(String quotationId) {
		this.quotationId = quotationId;
	}

	@JsonProperty("items")
	public List<Item> getItems() {
		return items;
	}

	@JsonProperty("items")
	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(CustomerPolicyDetailsLrRel.class.getName()).append('@')
				.append(Integer.toHexString(System.identityHashCode(this))).append('[');

		sb.append("items");
		sb.append('=');
		sb.append(((this.items == null) ? "<null>" : this.items));
		sb.append(',');

		if (sb.charAt((sb.length() - 1)) == ',') {
			sb.setCharAt((sb.length() - 1), ']');
		} else {
			sb.append(']');
		}
		return sb.toString();
	}

}