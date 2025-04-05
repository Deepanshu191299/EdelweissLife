package in.edelweiss.tokio.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AgentDetails {

	@JsonProperty("AGENT_ID")
	private String agentId;
	
	@JsonProperty("AgentLocation")
	private String agentLocation;
	
	@JsonProperty("AgentName")
	private String agentName;

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getAgentLocation() {
		return agentLocation;
	}

	public void setAgentLocation(String agentLocation) {
		this.agentLocation = agentLocation;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

}