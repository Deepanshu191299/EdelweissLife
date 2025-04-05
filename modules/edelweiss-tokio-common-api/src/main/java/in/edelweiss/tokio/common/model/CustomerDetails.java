package in.edelweiss.tokio.common.model;

public class CustomerDetails {

	private LiDetails liDetails;
	private ProposerDetails proposerDetails;
	private AgentDetails agentDetails;
	
	public LiDetails getLiDetails() {
		return liDetails;
	}
	public void setLiDetails(LiDetails liDetails) {
		this.liDetails = liDetails;
	}
	public ProposerDetails getProposerDetails() {
		return proposerDetails;
	}
	public void setProposerDetails(ProposerDetails proposerDetails) {
		this.proposerDetails = proposerDetails;
	}
	public AgentDetails getAgentDetails() {
		return agentDetails;
	}
	public void setAgentDetails(AgentDetails agentDetails) {
		this.agentDetails = agentDetails;
	}
}
