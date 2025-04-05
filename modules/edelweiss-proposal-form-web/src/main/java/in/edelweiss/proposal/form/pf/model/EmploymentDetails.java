package in.edelweiss.proposal.form.pf.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmploymentDetails {

	@JsonProperty("la_details")
	private LaPrSpEmployementDetails laDetails;

	@JsonProperty("proposer_details")
	private LaPrSpEmployementDetails proposerDetails;

	@JsonProperty("spouse_details")
	private LaPrSpEmployementDetails spouseDetails;

	public LaPrSpEmployementDetails getLaDetails() {
		return laDetails;
	}

	public void setLaDetails(LaPrSpEmployementDetails laDetails) {
		this.laDetails = laDetails;
	}

	public LaPrSpEmployementDetails getProposerDetails() {
		return proposerDetails;
	}

	public void setProposerDetails(LaPrSpEmployementDetails proposerDetails) {
		this.proposerDetails = proposerDetails;
	}

	public LaPrSpEmployementDetails getSpouseDetails() {
		return spouseDetails;
	}

	public void setSpouseDetails(LaPrSpEmployementDetails spouseDetails) {
		this.spouseDetails = spouseDetails;
	}

	@Override
	public String toString() {
		return "EmploymentDetails [laDetails=" + laDetails + ", proposerDetails=" + proposerDetails + ", spouseDetails="
				+ spouseDetails + "]";
	}

	public class LaPrSpEmployementDetails {

		@JsonProperty("employment_details_id")
		private int employmentDetailsId;

		@JsonProperty("emp_row_id")
		private int empRowId;

		@JsonProperty("employment_type_data_id")
		private int employmentTypeDataId;

		@JsonProperty("organisation_type_data_id")
		private int organisationTypeDataId;

		@JsonProperty("organisation_type_other")
		private String organisationTypeOther;

		@JsonProperty("source_income_other_yn")
		private String sourcIncomeOtherYn;

		@JsonProperty("source_income_other_details")
		private String sourceIncomeOtherDetails;

		@JsonProperty("employer_name")
		private String employerName;

		@JsonProperty("employer_address")
		private String employerAddress;

		@JsonProperty("business_nature")
		private String businessNature;

		@JsonProperty("designation_held")
		private String designationHeld;

		@JsonProperty("duty_nature_type_data_id")
		private int dutyNatureTypeDataId;

		@JsonProperty("employ_count_type_data_id")
		private int employCountTypeDataId;

		@JsonProperty("working_duration_year")
		private int workingDurationYear;

		@JsonProperty("working_duration_mnth")
		private int workingDurationMnth;

		@JsonProperty("annual_income")
		private String annualIncome;
		
		public int getEmploymentDetailsId() {
			return employmentDetailsId;
		}

		public void setEmploymentDetailsId(int employmentDetailsId) {
			this.employmentDetailsId = employmentDetailsId;
		}

		public int getEmpRowId() {
			return empRowId;
		}

		public void setEmpRowId(int empRowId) {
			this.empRowId = empRowId;
		}

		public int getEmploymentTypeDataId() {
			return employmentTypeDataId;
		}

		public void setEmploymentTypeDataId(int employmentTypeDataId) {
			this.employmentTypeDataId = employmentTypeDataId;
		}

		public int getOrganisationTypeDataId() {
			return organisationTypeDataId;
		}

		public void setOrganisationTypeDataId(int organisationTypeDataId) {
			this.organisationTypeDataId = organisationTypeDataId;
		}

		public String getOrganisationTypeOther() {
			return organisationTypeOther;
		}

		public void setOrganisationTypeOther(String organisationTypeOther) {
			this.organisationTypeOther = organisationTypeOther;
		}

		public String getSourcIncomeOtherYn() {
			return sourcIncomeOtherYn;
		}

		public void setSourcIncomeOtherYn(String sourcIncomeOtherYn) {
			this.sourcIncomeOtherYn = sourcIncomeOtherYn;
		}

		public String getSourceIncomeOtherDetails() {
			return sourceIncomeOtherDetails;
		}

		public void setSourceIncomeOtherDetails(String sourceIncomeOtherDetails) {
			this.sourceIncomeOtherDetails = sourceIncomeOtherDetails;
		}

		public String getEmployerName() {
			return employerName;
		}

		public void setEmployerName(String employerName) {
			this.employerName = employerName;
		}

		public String getEmployerAddress() {
			return employerAddress;
		}

		public void setEmployerAddress(String employerAddress) {
			this.employerAddress = employerAddress;
		}

		public String getBusinessNature() {
			return businessNature;
		}

		public void setBusinessNature(String businessNature) {
			this.businessNature = businessNature;
		}

		public String getDesignationHeld() {
			return designationHeld;
		}

		public void setDesignationHeld(String designationHeld) {
			this.designationHeld = designationHeld;
		}

		public int getDutyNatureTypeDataId() {
			return dutyNatureTypeDataId;
		}

		public void setDutyNatureTypeDataId(int dutyNatureTypeDataId) {
			this.dutyNatureTypeDataId = dutyNatureTypeDataId;
		}

		public int getEmployCountTypeDataId() {
			return employCountTypeDataId;
		}

		public void setEmployCountTypeDataId(int employCountTypeDataId) {
			this.employCountTypeDataId = employCountTypeDataId;
		}

		public int getWorkingDurationYear() {
			return workingDurationYear;
		}

		public void setWorkingDurationYear(int workingDurationYear) {
			this.workingDurationYear = workingDurationYear;
		}

		public int getWorkingDurationMnth() {
			return workingDurationMnth;
		}

		public void setWorkingDurationMnth(int workingDurationMnth) {
			this.workingDurationMnth = workingDurationMnth;
		}

		public String getAnnualIncome() {
			return annualIncome;
		}

		public void setAnnualIncome(String annualIncome) {
			this.annualIncome = annualIncome;
		}

		@Override
		public String toString() {
			return "LaPrSpEmployementInfo [employmentDetailsId=" + employmentDetailsId + ", empRowId=" + empRowId
					+ ", employmentTypeDataId=" + employmentTypeDataId + ", organisationTypeDataId="
					+ organisationTypeDataId + ", organisationTypeOther=" + organisationTypeOther
					+ ", sourcIncomeOtherYn=" + sourcIncomeOtherYn + ", sourceIncomeOtherDetails="
					+ sourceIncomeOtherDetails + ", employerName=" + employerName + ", employerAddress="
					+ employerAddress + ", businessNature=" + businessNature + ", designationHeld=" + designationHeld
					+ ", dutyNatureTypeDataId=" + dutyNatureTypeDataId + ", employCountTypeDataId="
					+ employCountTypeDataId + ", workingDurationYear=" + workingDurationYear + ", workingDurationMnth="
					+ workingDurationMnth + ", annualIncome=" + annualIncome + "]";
		}

	}

}
