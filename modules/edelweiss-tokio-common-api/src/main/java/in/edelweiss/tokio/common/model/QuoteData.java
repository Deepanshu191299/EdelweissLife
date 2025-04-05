package in.edelweiss.tokio.common.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuoteData{
	public QuoteData() {
		
	}
	public QuoteData(String resumeUrl) {
		super();
		this.resumeUrl = resumeUrl;
	}
	
	@JsonProperty("resumeUrl")
	private String resumeUrl;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<>();
	
	@JsonProperty("resumeUrl")
	public String getResumeUrl() {
		return resumeUrl;
	}

	@JsonProperty("resumeUrl")
	public void setResumeUrl(String resumeUrl) {
		this.resumeUrl = resumeUrl;
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
		StringBuilder sb = new StringBuilder();
		sb.append(QuoteData.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
				.append('[');
		sb.append("resumeUrl");
		sb.append('=');
		sb.append(((this.resumeUrl == null) ? "<null>" : this.resumeUrl));
		sb.append(',');
		sb.append("additionalProperties");
		sb.append('=');
		sb.append(((this.additionalProperties == null) ? "<null>" : this.additionalProperties));
		sb.append(',');
		if (sb.charAt((sb.length() - 1)) == ',') {
			sb.setCharAt((sb.length() - 1), ']');
		} else {
			sb.append(']');
		}
		return sb.toString();
	}

}
