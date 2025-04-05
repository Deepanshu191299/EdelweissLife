package in.edelweiss.proposal.form.pf.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FamilyHistory {

	@JsonProperty("fh_row_id")
	private int fhRowId;

	@JsonProperty("family_member_data_id")
	private int familyMemberDataId;

	@JsonProperty("age")
	private int age;

	@JsonProperty("health_status")
	private String healthStatus;

	@JsonProperty("age_on_death")
	private String ageOnDeath;

	@JsonProperty("cause_of_death")
	private String causeOfDeath;

	public int getFhRowId() {
		return fhRowId;
	}

	public void setFhRowId(int fhRowId) {
		this.fhRowId = fhRowId;
	}

	public int getFamilyMemberDataId() {
		return familyMemberDataId;
	}

	public void setFamilyMemberDataId(int familyMemberDataId) {
		this.familyMemberDataId = familyMemberDataId;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getHealthStatus() {
		return healthStatus;
	}

	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
	}

	public String getAgeOnDeath() {
		return ageOnDeath;
	}

	public void setAgeOnDeath(String ageOnDeath) {
		this.ageOnDeath = ageOnDeath;
	}

	public String getCauseOfDeath() {
		return causeOfDeath;
	}

	public void setCauseOfDeath(String causeOfDeath) {
		this.causeOfDeath = causeOfDeath;
	}

	@Override
	public String toString() {
		return "FamilyHistory [fhRowId=" + fhRowId + ", familyMemberDataId=" + familyMemberDataId + ", age=" + age
				+ ", healthStatus=" + healthStatus + ", ageOnDeath=" + ageOnDeath + ", causeOfDeath=" + causeOfDeath
				+ "]";
	}

}
