
package in.edelweiss.tokio.common.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "status", "errors", "responseData" })
public class RestartJourneyResponse {

	@JsonProperty("status")
	private Boolean status;
	@JsonProperty("errors")
	private List<Object> errors;
	@JsonProperty("responseData")
	private String responseData;

	@JsonProperty("status")
	public Boolean getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(Boolean status) {
		this.status = status;
	}

	@JsonProperty("errors")
	public List<Object> getErrors() {
		return errors;
	}

	@JsonProperty("errors")
	public void setErrors(List<Object> errors) {
		this.errors = errors;
	}

	@JsonProperty("responseData")
	public String getResponseData() {
		return responseData;
	}

	@JsonProperty("responseData")
	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}

	@Override
	public String toString() {
		return "RestartJourneyResponse [status=" + status + ", errors=" + errors + ", responseData=" + responseData
				+ "]";
	}

	

}
