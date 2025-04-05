package in.edelweiss.tokio.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LMSRequest {

	@JsonProperty("LMSLead")
	private Lmslead lmslead;

	public Lmslead getLmslead() {
		return lmslead;
	}

	public void setLmslead(Lmslead lmslead) {
		this.lmslead = lmslead;
	}
	
}
