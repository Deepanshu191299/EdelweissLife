package in.edelweiss.tokio.common.model;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "Application", "LMSId", "Phone" })
public class LMSApplication {

	@JsonProperty("Application")
	private Application application;
	@JsonProperty("LMSId")
	private String lMSId;
	@JsonProperty("Phone")
	private String phone;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<>();

	@JsonProperty("Application")
	public Application getApplication() {
		return application;
	}

	@JsonProperty("Application")
	public void setApplication(Application application) {
		this.application = application;
	}

	@JsonProperty("LMSId")
	public String getLMSId() {
		return lMSId;
	}

	@JsonProperty("LMSId")
	public void setLMSId(String lMSId) {
		this.lMSId = lMSId;
	}

	@JsonProperty("Phone")
	public String getPhone() {
		return phone;
	}

	@JsonProperty("Phone")
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return "LMSApplication [application=" + application + ", lMSId=" + lMSId + ", phone=" + phone
				+ ", additionalProperties=" + additionalProperties + "]";
	}

	
}
