package in.edelweiss.claim.tracking.modals;

import java.util.Date;

public class ClaimTrackerModel {
	
	private String claimReferenceNo;
	private String policyNo;
	private String nameOfLA;
	private String intimationDate;
	private String decisionDate;
	private String causeOfDeath;
	private String finalDecision;
	private String claimType;
	
	//Default
    private Long c_claimTrackerId;
    private Date modifiedDate;
    private Long objectEntryId;
    private String userName;
    private Date createDate;
    private Integer status;
    private String externalReferenceCode;
    
    // Constructor
    public ClaimTrackerModel() {
        // Default constructor
    }

	public ClaimTrackerModel(String claimReferenceNo, String policyNo, String nameOfLA, String intimationDate,
			String decisionDate, String causeOfDeath, String finalDecision, String claimType, Long c_claimTrackerId,
			Date modifiedDate, Long objectEntryId, String userName, Date createDate, Integer status,
			String externalReferenceCode) {
		this.claimReferenceNo = claimReferenceNo;
		this.policyNo = policyNo;
		this.nameOfLA = nameOfLA;
		this.intimationDate = intimationDate;
		this.decisionDate = decisionDate;
		this.causeOfDeath = causeOfDeath;
		this.finalDecision = finalDecision;
		this.claimType = claimType;
		this.c_claimTrackerId = c_claimTrackerId;
		this.modifiedDate = modifiedDate;
		this.objectEntryId = objectEntryId;
		this.userName = userName;
		this.createDate = createDate;
		this.status = status;
		this.externalReferenceCode = externalReferenceCode;
	}
	
	public String getClaimReferenceNo() {
		return claimReferenceNo;
	}

	public void setClaimReferenceNo(String claimReferenceNo) {
		this.claimReferenceNo = claimReferenceNo;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getNameOfLA() {
		return nameOfLA;
	}

	public void setNameOfLA(String nameOfLA) {
		this.nameOfLA = nameOfLA;
	}

	public String getIntimationDate() {
		return intimationDate;
	}

	public void setIntimationDate(String intimationDate) {
		this.intimationDate = intimationDate;
	}

	public String getDecisionDate() {
		return decisionDate;
	}

	public void setDecisionDate(String decisionDate) {
		this.decisionDate = decisionDate;
	}

	public String getCauseOfDeath() {
		return causeOfDeath;
	}

	public void setCauseOfDeath(String causeOfDeath) {
		this.causeOfDeath = causeOfDeath;
	}

	public String getFinalDecision() {
		return finalDecision;
	}

	public void setFinalDecision(String finalDecision) {
		this.finalDecision = finalDecision;
	}

	public String getClaimType() {
		return claimType;
	}

	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	public Long getC_claimTrackerId() {
		return c_claimTrackerId;
	}

	public void setC_claimTrackerId(Long c_claimTrackerId) {
		this.c_claimTrackerId = c_claimTrackerId;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Long getObjectEntryId() {
		return objectEntryId;
	}

	public void setObjectEntryId(Long objectEntryId) {
		this.objectEntryId = objectEntryId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getExternalReferenceCode() {
		return externalReferenceCode;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		this.externalReferenceCode = externalReferenceCode;
	}
}
