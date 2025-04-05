package in.edelweiss.tokio.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class UpdateLMSLeadRequest {

	@JsonProperty("LMSLead")
	private UpdateLead updateLead;

	@JsonProperty("LMSLead")
	public UpdateLead getupdateLead() {
		return updateLead;
	}

	@JsonProperty("LMSLead")
	public void setupdateLead(UpdateLead updateLead) {
		this.updateLead = updateLead;
	}

	@Override
	public String toString() {
		return "UpdateLMSLeadRequest [updateLead=" + updateLead + "]";
	}
}