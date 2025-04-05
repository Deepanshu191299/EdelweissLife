package in.edelweiss.tokio.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerFundAllocationDetailsRel {

    @JsonProperty("dateCreated")
    private String dateCreated;
    
    @JsonProperty("dateModified")
    private String dateModified;
    
    @JsonProperty("externalReferenceCode")
    private String externalReferenceCode;
    
    @JsonProperty("id")
    private Long id;
    
    @JsonProperty("fundDetails")
    private String fundDetails;
    
    @JsonProperty("leadId")
    private String leadId;
    
    @JsonProperty("productCode")
    private String productCode;
    
    @JsonProperty("r_customerFundAllocationDetailsRel_c_customerEnquiryId")
    private Long rCustomerFundAllocationDetailsRelCCustomerEnquiryId;
    
    @JsonProperty("r_customerFundAllocationDetailsRel_c_customerEnquiryERC")
    private String rCustomerFundAllocationDetailsRelCCustomerEnquiryERC;

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDateModified() {
		return dateModified;
	}

	public void setDateModified(String dateModified) {
		this.dateModified = dateModified;
	}

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

	public String getFundDetails() {
		return fundDetails;
	}

	public void setFundDetails(String fundDetails) {
		this.fundDetails = fundDetails;
	}

	public String getLeadId() {
		return leadId;
	}

	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Long getrCustomerFundAllocationDetailsRelCCustomerEnquiryId() {
		return rCustomerFundAllocationDetailsRelCCustomerEnquiryId;
	}

	public void setrCustomerFundAllocationDetailsRelCCustomerEnquiryId(
			Long rCustomerFundAllocationDetailsRelCCustomerEnquiryId) {
		this.rCustomerFundAllocationDetailsRelCCustomerEnquiryId = rCustomerFundAllocationDetailsRelCCustomerEnquiryId;
	}

	public String getrCustomerFundAllocationDetailsRelCCustomerEnquiryERC() {
		return rCustomerFundAllocationDetailsRelCCustomerEnquiryERC;
	}

	public void setrCustomerFundAllocationDetailsRelCCustomerEnquiryERC(
			String rCustomerFundAllocationDetailsRelCCustomerEnquiryERC) {
		this.rCustomerFundAllocationDetailsRelCCustomerEnquiryERC = rCustomerFundAllocationDetailsRelCCustomerEnquiryERC;
	}

	@Override
	public String toString() {
		return "CustomerFundAllocationDetailsRel [dateCreated=" + dateCreated + ", dateModified=" + dateModified
				+ ", externalReferenceCode=" + externalReferenceCode + ", id=" + id + ", fundDetails=" + fundDetails
				+ ", leadId=" + leadId + ", productCode=" + productCode
				+ ", rCustomerFundAllocationDetailsRelCCustomerEnquiryId="
				+ rCustomerFundAllocationDetailsRelCCustomerEnquiryId
				+ ", rCustomerFundAllocationDetailsRelCCustomerEnquiryERC="
				+ rCustomerFundAllocationDetailsRelCCustomerEnquiryERC + "]";
	}
}