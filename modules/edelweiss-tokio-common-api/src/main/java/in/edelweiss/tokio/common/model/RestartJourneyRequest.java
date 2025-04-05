
package in.edelweiss.tokio.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestartJourneyRequest {

	@JsonProperty("Source")
	private String source;
	@JsonProperty("Screen")
	private String screen;
	@JsonProperty("EmailAddress")
	private String emailAddress;
	@JsonProperty("PhoneNumber")
	private String phoneNumber;
	@JsonProperty("ProductCode")
	private String productCode;
	@JsonProperty("leadId")
	private String leadId;
	
	
	
	
	@JsonProperty("QuoteData")
	private String quoteDataStr;
	
	
	public String getQuoteDataStr() {
		return quoteDataStr;
	}

	public void setQuoteDataStr(String quoteDataStr) {
		this.quoteDataStr = quoteDataStr;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	@JsonProperty("ProductName")
	private String productName;

	@JsonProperty("Source")
	public String getSource() {
		return source;
	}

	@JsonProperty("Source")
	public void setSource(String source) {
		this.source = source;
	}

	@JsonProperty("Screen")
	public String getScreen() {
		return screen;
	}

	@JsonProperty("Screen")
	public void setScreen(String screen) {
		this.screen = screen;
	}

	@JsonProperty("EmailAddress")
	public String getEmailAddress() {
		return emailAddress;
	}

	@JsonProperty("EmailAddress")
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@JsonProperty("PhoneNumber")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	@JsonProperty("PhoneNumber")
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@JsonProperty("ProductCode")
	public String getProductCode() {
		return productCode;
	}

	@JsonProperty("ProductCode")
	public void setProductCode(String productCode) {
		this.productCode = productCode;
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
		return "RestartJourneyRequest [source=" + source + ", screen=" + screen + ", emailAddress=" + emailAddress
				+ ", phoneNumber=" + phoneNumber + ", productCode=" + productCode + ", leadId=" + leadId
				+ ", quoteDataStr=" + quoteDataStr + ", productName=" + productName + "]";
	}

	

}
