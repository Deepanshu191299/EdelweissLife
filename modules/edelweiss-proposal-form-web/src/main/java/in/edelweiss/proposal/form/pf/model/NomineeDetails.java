package in.edelweiss.proposal.form.pf.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NomineeDetails {

	@JsonProperty("nominee_details_id")
	private String nomineeDetailsId;

	@JsonProperty("nomine_row_id")
	private int nomineRowId;

	@JsonProperty("nominee_name")
	private String nomineeName;

	@JsonProperty("nominee_dob")
	private String nomineeDob;

	@JsonProperty("nominee_gender_data_id")
	private int nomineeGenderDataId;

	@JsonProperty("nominee_percentage")
	private int nomineePercentage;

	@JsonProperty("nominee_la_relationship_data_id")
	private int nomineeLaRelationshipDataId;

	@JsonProperty("appointee_name")
	private String appointeeName;

	@JsonProperty("appointee_dob")
	private String appointeeDob;

	@JsonProperty("appointee_nominee_relationship_data_id")
	private String appointeeNomineeRelationshipDataId;

	@JsonProperty("appointee_gender_data_id")
	private int appointeeGenderDataId;

	@JsonProperty("created_by")
	private String createdBy;

	public String getNomineeDetailsId() {
		return nomineeDetailsId;
	}

	public void setNomineeDetailsId(String nomineeDetailsId) {
		this.nomineeDetailsId = nomineeDetailsId;
	}

	public int getNomineRowId() {
		return nomineRowId;
	}

	public void setNomineRowId(int nomineRowId) {
		this.nomineRowId = nomineRowId;
	}

	public String getNomineeName() {
		return nomineeName;
	}

	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}

	public String getNomineeDob() {
		return nomineeDob;
	}

	public void setNomineeDob(String nomineeDob) {
		this.nomineeDob = nomineeDob;
	}

	public int getNomineeGenderDataId() {
		return nomineeGenderDataId;
	}

	public void setNomineeGenderDataId(int nomineeGenderDataId) {
		this.nomineeGenderDataId = nomineeGenderDataId;
	}

	public int getNomineePercentage() {
		return nomineePercentage;
	}

	public void setNomineePercentage(int nomineePercentage) {
		this.nomineePercentage = nomineePercentage;
	}

	public int getNomineeLaRelationshipDataId() {
		return nomineeLaRelationshipDataId;
	}

	public void setNomineeLaRelationshipDataId(int nomineeLaRelationshipDataId) {
		this.nomineeLaRelationshipDataId = nomineeLaRelationshipDataId;
	}

	public String getAppointeeName() {
		return appointeeName;
	}

	public void setAppointeeName(String appointeeName) {
		this.appointeeName = appointeeName;
	}

	public String getAppointeeDob() {
		return appointeeDob;
	}

	public void setAppointeeDob(String appointeeDob) {
		this.appointeeDob = appointeeDob;
	}

	public String getAppointeeNomineeRelationshipDataId() {
		return appointeeNomineeRelationshipDataId;
	}

	public void setAppointeeNomineeRelationshipDataId(String appointeeNomineeRelationshipDataId) {
		this.appointeeNomineeRelationshipDataId = appointeeNomineeRelationshipDataId;
	}

	public int getAppointeeGenderDataId() {
		return appointeeGenderDataId;
	}

	public void setAppointeeGenderDataId(int appointeeGenderDataId) {
		this.appointeeGenderDataId = appointeeGenderDataId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public String toString() {
		return "NomineeDetail [nomineeDetailsId=" + nomineeDetailsId + ", nomineRowId=" + nomineRowId + ", nomineeName="
				+ nomineeName + ", nomineeDob=" + nomineeDob + ", nomineeGenderDataId=" + nomineeGenderDataId
				+ ", nomineePercentage=" + nomineePercentage + ", nomineeLaRelationshipDataId="
				+ nomineeLaRelationshipDataId + ", appointeeName=" + appointeeName + ", appointeeDob=" + appointeeDob
				+ ", appointeeNomineeRelationshipDataId=" + appointeeNomineeRelationshipDataId
				+ ", appointeeGenderDataId=" + appointeeGenderDataId + ", createdBy=" + createdBy + "]";
	}

}
