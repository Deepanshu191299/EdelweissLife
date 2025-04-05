package in.edelweiss.proposal.form.pf.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonalDetails {

	@JsonProperty("la_details")
	private LaPrSpPersonalDetails laDetails;

	@JsonProperty("proposer_details")
	private LaPrSpPersonalDetails proposerDetails;

	@JsonProperty("spouse_details")
	private LaPrSpPersonalDetails spouseDetails;

	public LaPrSpPersonalDetails getLaDetails() {
		return laDetails;
	}

	public void setLaDetails(LaPrSpPersonalDetails laDetails) {
		this.laDetails = laDetails;
	}

	public LaPrSpPersonalDetails getProposerDetails() {
		return proposerDetails;
	}

	public void setProposerDetails(LaPrSpPersonalDetails proposerDetails) {
		this.proposerDetails = proposerDetails;
	}

	public LaPrSpPersonalDetails getSpouseDetails() {
		return spouseDetails;
	}

	public void setSpouseDetails(LaPrSpPersonalDetails spouseDetails) {
		this.spouseDetails = spouseDetails;
	}

	@Override
	public String toString() {
		return "PersonalDetails [laDetails=" + laDetails + ", proposerDetails=" + proposerDetails + ", spouseDetails="
				+ spouseDetails + "]";
	}

	public class LaPrSpPersonalDetails {

		@JsonProperty("detail_type_of")
		private int detailTypeOf;

		@JsonProperty("personal_details_id")
		private int personalDetailsId;

		@JsonProperty("lead_id")
		private String leadId;

		@JsonProperty("title_data_id")
		private int titleDataId;

		@JsonProperty("first_name")
		private String firstName;

		@JsonProperty("middle_name")
		private String middleName;

		@JsonProperty("last_name")
		private String lastName;

		@JsonProperty("full_name")
		private String fullName;

		@JsonProperty("is_la_pr_same_yn")
		private String isLaPrSameYn;

		@JsonProperty("la_pr_relation_data_id")
		private String laPrRelationDataId;

		@JsonProperty("dob")
		private String dob;

		@JsonProperty("mobile_number")
		private String mobileNumber;

		@JsonProperty("email_address")
		private String emailAddress;

		@JsonProperty("is_smoker_yn")
		private String isSmokerYn;

		@JsonProperty("gender_data_id")
		private int genderDataId;

		@JsonProperty("marital_status_data_id")
		private int maritalStatusDataId;

		@JsonProperty("pan_number")
		private String panNumber;

		@JsonProperty("aadhar_number")
		private String aadharNumber;

		@JsonProperty("father_name")
		private String fatherName;

		@JsonProperty("mother_name")
		private String motherName;

		@JsonProperty("spouse_name")
		private String spouseName;

		@JsonProperty("nationality_data_id")
		private int nationalityDataId;

		@JsonProperty("nationality_other")
		private String nationalityOther;

		@JsonProperty("age_proof_data_id")
		private int ageProofDataId;

		@JsonProperty("age_proof_other")
		private String ageProofOther;

		@JsonProperty("bank_account_number")
		private String bankAccountNumber;

		@JsonProperty("ifsc_code")
		private String ifscCode;

		@JsonProperty("bank_name")
		private String bankName;

		@JsonProperty("bank_branch_location")
		private String bankBranchLocation;

		@JsonProperty("account_holder_name")
		private String accountHolderName;

		@JsonProperty("qualification_data_id")
		private int qualificationDataId;

		@JsonProperty("qualification_other")
		private String qualificationOther;

		@JsonProperty("highest_qualification")
		private String highestQualification;

		@JsonProperty("course_pursued")
		private String coursePursued;

		@JsonProperty("course_duration")
		private String courseDuration;

		@JsonProperty("course_year")
		private String courseYear;

		@JsonProperty("course_college")
		private String courseCollege;

		@JsonProperty("minor_studying")
		private String minorStudying;

		@JsonProperty("minor_weight")
		private String minorWeight;

		@JsonProperty("vaccination_done_yn")
		private String vaccinationDoneYn;

		@JsonProperty("parents_ins_cover")
		private String parentsInsCover;

		@JsonProperty("parents_annual_income")
		private String parentsAnnualIncome;

		@JsonProperty("siblings_ins_cover")
		private String siblingsInsCover;

		@JsonProperty("politically_exposed_yn")
		private String politicallyExposedYn;

		@JsonProperty("politically_exposed_specify")
		private String politicallyExposedSpecify;

		@JsonProperty("criminal_proceedings_yn")
		private String criminalProceedingsYn;

		@JsonProperty("criminal_proceedings_details")
		private String criminalProceedingDetails;

		@JsonProperty("photo_proof_data_id")
		private int photoProofDataId;

		@JsonProperty("photo_proof_other")
		private String photoProofOther;

		@JsonProperty("address_proof_data_id")
		private int addressProofDataId;

		@JsonProperty("address_proof_other")
		private String addressProofOther;

		@JsonProperty("income_proof_data_id")
		private int incomeProofDataId;

		@JsonProperty("income_proof_other")
		private String incomeProofOther;

		@JsonProperty("policy_categorisation_data_id")
		private String policyCategorisationDataId;

		@JsonProperty("policy_categorisation_other")
		private String policyCategorisationOther;

		@JsonProperty("pr_relationship_data_id")
		private int prRelationshipDataId;

		@JsonProperty("pr_relationship_other")
		private String prRelationshipOther;

		@JsonProperty("tax_residence_declaration_yn")
		private String taxResidenceDeclarationYn;

		@JsonProperty("eia_account_available_yn")
		private String eiaAccountAvailableYn;

		@JsonProperty("eia_account_number")
		private String eiaAccountNumber;

		@JsonProperty("eia_apply_yn")
		private String eiaApplyYn;

		@JsonProperty("eia_repository_data_id")
		private String eiaRepositoryDataId;

		@JsonProperty("family_physician_name")
		private String familyPhysicianName;

		@JsonProperty("family_physician_address_line1")
		private String familyPhysicianAddressLine1;

		@JsonProperty("family_physician_address_line2")
		private String familyPhysicianAddressLine2;

		@JsonProperty("family_physician_contact_number")
		private String familyPhysicianContactNumber;

		@JsonProperty("family_physician_mobile_number")
		private String familyPhysicianMobileNumber;

		@JsonProperty("nri_gst_applicable_yn")
		private String nriGstApplicablYn;

		@JsonProperty("gstin_number")
		private String gstinNumber;
		
		@JsonProperty("abha_number")
		private String abhaNumber;

		public int getDetailTypeOf() {
			return detailTypeOf;
		}

		public void setDetailTypeOf(int detailTypeOf) {
			this.detailTypeOf = detailTypeOf;
		}

		public int getPersonalDetailsId() {
			return personalDetailsId;
		}

		public void setPersonalDetailsId(int personalDetailsId) {
			this.personalDetailsId = personalDetailsId;
		}

		public String getLeadId() {
			return leadId;
		}

		public void setLeadId(String leadId) {
			this.leadId = leadId;
		}

		public int getTitleDataId() {
			return titleDataId;
		}

		public void setTitleDataId(int titleDataId) {
			this.titleDataId = titleDataId;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getMiddleName() {
			return middleName;
		}

		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getFullName() {
			return fullName;
		}

		public void setFullName(String fullName) {
			this.fullName = fullName;
		}

		public String getIsLaPrSameYn() {
			return isLaPrSameYn;
		}

		public void setIsLaPrSameYn(String isLaPrSameYn) {
			this.isLaPrSameYn = isLaPrSameYn;
		}

		public String getLaPrRelationDataId() {
			return laPrRelationDataId;
		}

		public void setLaPrRelationDataId(String laPrRelationDataId) {
			this.laPrRelationDataId = laPrRelationDataId;
		}

		public String getDob() {
			return dob;
		}

		public void setDob(String dob) {
			this.dob = dob;
		}

		public String getMobileNumber() {
			return mobileNumber;
		}

		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}

		public String getEmailAddress() {
			return emailAddress;
		}

		public void setEmailAddress(String emailAddress) {
			this.emailAddress = emailAddress;
		}

		public String getIsSmokerYn() {
			return isSmokerYn;
		}

		public void setIsSmokerYn(String isSmokerYn) {
			this.isSmokerYn = isSmokerYn;
		}

		public int getGenderDataId() {
			return genderDataId;
		}

		public void setGenderDataId(int genderDataId) {
			this.genderDataId = genderDataId;
		}

		public int getMaritalStatusDataId() {
			return maritalStatusDataId;
		}

		public void setMaritalStatusDataId(int maritalStatusDataId) {
			this.maritalStatusDataId = maritalStatusDataId;
		}

		public String getPanNumber() {
			return panNumber;
		}

		public void setPanNumber(String panNumber) {
			this.panNumber = panNumber;
		}

		public String getAadharNumber() {
			return aadharNumber;
		}

		public void setAadharNumber(String aadharNumber) {
			this.aadharNumber = aadharNumber;
		}

		public String getFatherName() {
			return fatherName;
		}

		public void setFatherName(String fatherName) {
			this.fatherName = fatherName;
		}

		public String getMotherName() {
			return motherName;
		}

		public void setMotherName(String motherName) {
			this.motherName = motherName;
		}

		public String getSpouseName() {
			return spouseName;
		}

		public void setSpouseName(String spouseName) {
			this.spouseName = spouseName;
		}

		public int getNationalityDataId() {
			return nationalityDataId;
		}

		public void setNationalityDataId(int nationalityDataId) {
			this.nationalityDataId = nationalityDataId;
		}

		public String getNationalityOther() {
			return nationalityOther;
		}

		public void setNationalityOther(String nationalityOther) {
			this.nationalityOther = nationalityOther;
		}

		public int getAgeProofDataId() {
			return ageProofDataId;
		}

		public void setAgeProofDataId(int ageProofDataId) {
			this.ageProofDataId = ageProofDataId;
		}

		public String getAgeProofOther() {
			return ageProofOther;
		}

		public void setAgeProofOther(String ageProofOther) {
			this.ageProofOther = ageProofOther;
		}

		public String getBankAccountNumber() {
			return bankAccountNumber;
		}

		public void setBankAccountNumber(String bankAccountNumber) {
			this.bankAccountNumber = bankAccountNumber;
		}

		public String getIfscCode() {
			return ifscCode;
		}

		public void setIfscCode(String ifscCode) {
			this.ifscCode = ifscCode;
		}

		public String getBankName() {
			return bankName;
		}

		public void setBankName(String bankName) {
			this.bankName = bankName;
		}

		public String getBankBranchLocation() {
			return bankBranchLocation;
		}

		public void setBankBranchLocation(String bankBranchLocation) {
			this.bankBranchLocation = bankBranchLocation;
		}

		public String getAccountHolderName() {
			return accountHolderName;
		}

		public void setAccountHolderName(String accountHolderName) {
			this.accountHolderName = accountHolderName;
		}

		public int getQualificationDataId() {
			return qualificationDataId;
		}

		public void setQualificationDataId(int qualificationDataId) {
			this.qualificationDataId = qualificationDataId;
		}

		public String getQualificationOther() {
			return qualificationOther;
		}

		public void setQualificationOther(String qualificationOther) {
			this.qualificationOther = qualificationOther;
		}

		public String getHighestQualification() {
			return highestQualification;
		}

		public void setHighestQualification(String highestQualification) {
			this.highestQualification = highestQualification;
		}

		public String getCoursePursued() {
			return coursePursued;
		}

		public void setCoursePursued(String coursePursued) {
			this.coursePursued = coursePursued;
		}

		public String getCourseDuration() {
			return courseDuration;
		}

		public void setCourseDuration(String courseDuration) {
			this.courseDuration = courseDuration;
		}

		public String getCourseYear() {
			return courseYear;
		}

		public void setCourseYear(String courseYear) {
			this.courseYear = courseYear;
		}

		public String getCourseCollege() {
			return courseCollege;
		}

		public void setCourseCollege(String courseCollege) {
			this.courseCollege = courseCollege;
		}

		public String getMinorStudying() {
			return minorStudying;
		}

		public void setMinorStudying(String minorStudying) {
			this.minorStudying = minorStudying;
		}

		public String getMinorWeight() {
			return minorWeight;
		}

		public void setMinorWeight(String minorWeight) {
			this.minorWeight = minorWeight;
		}

		public String getVaccinationDoneYn() {
			return vaccinationDoneYn;
		}

		public void setVaccinationDoneYn(String vaccinationDoneYn) {
			this.vaccinationDoneYn = vaccinationDoneYn;
		}

		public String getParentsInsCover() {
			return parentsInsCover;
		}

		public void setParentsInsCover(String parentsInsCover) {
			this.parentsInsCover = parentsInsCover;
		}

		public String getParentsAnnualIncome() {
			return parentsAnnualIncome;
		}

		public void setParentsAnnualIncome(String parentsAnnualIncome) {
			this.parentsAnnualIncome = parentsAnnualIncome;
		}

		public String getSiblingsInsCover() {
			return siblingsInsCover;
		}

		public void setSiblingsInsCover(String siblingsInsCover) {
			this.siblingsInsCover = siblingsInsCover;
		}

		public String getPoliticallyExposedYn() {
			return politicallyExposedYn;
		}

		public void setPoliticallyExposedYn(String politicallyExposedYn) {
			this.politicallyExposedYn = politicallyExposedYn;
		}

		public String getPoliticallyExposedSpecify() {
			return politicallyExposedSpecify;
		}

		public void setPoliticallyExposedSpecify(String politicallyExposedSpecify) {
			this.politicallyExposedSpecify = politicallyExposedSpecify;
		}

		public String getCriminalProceedingsYn() {
			return criminalProceedingsYn;
		}

		public void setCriminalProceedingsYn(String criminalProceedingsYn) {
			this.criminalProceedingsYn = criminalProceedingsYn;
		}

		public String getCriminalProceedingDetails() {
			return criminalProceedingDetails;
		}

		public void setCriminalProceedingDetails(String criminalProceedingDetails) {
			this.criminalProceedingDetails = criminalProceedingDetails;
		}

		public int getPhotoProofDataId() {
			return photoProofDataId;
		}

		public void setPhotoProofDataId(int photoProofDataId) {
			this.photoProofDataId = photoProofDataId;
		}

		public String getPhotoProofOther() {
			return photoProofOther;
		}

		public void setPhotoProofOther(String photoProofOther) {
			this.photoProofOther = photoProofOther;
		}

		public int getAddressProofDataId() {
			return addressProofDataId;
		}

		public void setAddressProofDataId(int addressProofDataId) {
			this.addressProofDataId = addressProofDataId;
		}

		public String getAddressProofOther() {
			return addressProofOther;
		}

		public void setAddressProofOther(String addressProofOther) {
			this.addressProofOther = addressProofOther;
		}

		public int getIncomeProofDataId() {
			return incomeProofDataId;
		}

		public void setIncomeProofDataId(int incomeProofDataId) {
			this.incomeProofDataId = incomeProofDataId;
		}

		public String getIncomeProofOther() {
			return incomeProofOther;
		}

		public void setIncomeProofOther(String incomeProofOther) {
			this.incomeProofOther = incomeProofOther;
		}

		public String getPolicyCategorisationDataId() {
			return policyCategorisationDataId;
		}

		public void setPolicyCategorisationDataId(String policyCategorisationDataId) {
			this.policyCategorisationDataId = policyCategorisationDataId;
		}

		public String getPolicyCategorisationOther() {
			return policyCategorisationOther;
		}

		public void setPolicyCategorisationOther(String policyCategorisationOther) {
			this.policyCategorisationOther = policyCategorisationOther;
		}

		public int getPrRelationshipDataId() {
			return prRelationshipDataId;
		}

		public void setPrRelationshipDataId(int prRelationshipDataId) {
			this.prRelationshipDataId = prRelationshipDataId;
		}

		public String getPrRelationshipOther() {
			return prRelationshipOther;
		}

		public void setPrRelationshipOther(String prRelationshipOther) {
			this.prRelationshipOther = prRelationshipOther;
		}

		public String getTaxResidenceDeclarationYn() {
			return taxResidenceDeclarationYn;
		}

		public void setTaxResidenceDeclarationYn(String taxResidenceDeclarationYn) {
			this.taxResidenceDeclarationYn = taxResidenceDeclarationYn;
		}

		public String getEiaAccountAvailableYn() {
			return eiaAccountAvailableYn;
		}

		public void setEiaAccountAvailableYn(String eiaAccountAvailableYn) {
			this.eiaAccountAvailableYn = eiaAccountAvailableYn;
		}

		public String getEiaAccountNumber() {
			return eiaAccountNumber;
		}

		public void setEiaAccountNumber(String eiaAccountNumber) {
			this.eiaAccountNumber = eiaAccountNumber;
		}

		public String getEiaApplyYn() {
			return eiaApplyYn;
		}

		public void setEiaApplyYn(String eiaApplyYn) {
			this.eiaApplyYn = eiaApplyYn;
		}

		public String getEiaRepositoryDataId() {
			return eiaRepositoryDataId;
		}

		public void setEiaRepositoryDataId(String eiaRepositoryDataId) {
			this.eiaRepositoryDataId = eiaRepositoryDataId;
		}

		public String getFamilyPhysicianName() {
			return familyPhysicianName;
		}

		public void setFamilyPhysicianName(String familyPhysicianName) {
			this.familyPhysicianName = familyPhysicianName;
		}

		public String getFamilyPhysicianAddressLine1() {
			return familyPhysicianAddressLine1;
		}

		public void setFamilyPhysicianAddressLine1(String familyPhysicianAddressLine1) {
			this.familyPhysicianAddressLine1 = familyPhysicianAddressLine1;
		}

		public String getFamilyPhysicianAddressLine2() {
			return familyPhysicianAddressLine2;
		}

		public void setFamilyPhysicianAddressLine2(String familyPhysicianAddressLine2) {
			this.familyPhysicianAddressLine2 = familyPhysicianAddressLine2;
		}

		public String getFamilyPhysicianContactNumber() {
			return familyPhysicianContactNumber;
		}

		public void setFamilyPhysicianContactNumber(String familyPhysicianContactNumber) {
			this.familyPhysicianContactNumber = familyPhysicianContactNumber;
		}

		public String getFamilyPhysicianMobileNumber() {
			return familyPhysicianMobileNumber;
		}

		public void setFamilyPhysicianMobileNumber(String familyPhysicianMobileNumber) {
			this.familyPhysicianMobileNumber = familyPhysicianMobileNumber;
		}

		public String getNriGstApplicablYn() {
			return nriGstApplicablYn;
		}

		public void setNriGstApplicablYn(String nriGstApplicablYn) {
			this.nriGstApplicablYn = nriGstApplicablYn;
		}

		public String getGstinNumber() {
			return gstinNumber;
		}

		public void setGstinNumber(String gstinNumber) {
			this.gstinNumber = gstinNumber;
		}
		
		public String getAbhaNumber() {
			return abhaNumber;
		}

		public void setAbhaNumber(String abhaNumber) {
			this.abhaNumber = abhaNumber;
		}

		@Override
		public String toString() {
			return "LaPrSpPersonalDetails [detailTypeOf=" + detailTypeOf + ", personalDetailsId=" + personalDetailsId
					+ ", leadId=" + leadId + ", titleDataId=" + titleDataId + ", firstName=" + firstName
					+ ", middleName=" + middleName + ", lastName=" + lastName + ", fullName=" + fullName
					+ ", isLaPrSameYn=" + isLaPrSameYn + ", laPrRelationDataId=" + laPrRelationDataId + ", dob=" + dob
					+ ", mobileNumber=" + mobileNumber + ", emailAddress=" + emailAddress + ", isSmokerYn=" + isSmokerYn
					+ ", genderDataId=" + genderDataId + ", maritalStatusDataId=" + maritalStatusDataId + ", panNumber="
					+ panNumber + ", aadharNumber=" + aadharNumber + ", fatherName=" + fatherName + ", motherName="
					+ motherName + ", spouseName=" + spouseName + ", nationalityDataId=" + nationalityDataId
					+ ", nationalityOther=" + nationalityOther + ", ageProofDataId=" + ageProofDataId
					+ ", ageProofOther=" + ageProofOther + ", bankAccountNumber=" + bankAccountNumber + ", ifscCode="
					+ ifscCode + ", bankName=" + bankName + ", bankBranchLocation=" + bankBranchLocation
					+ ", accountHolderName=" + accountHolderName + ", qualificationDataId=" + qualificationDataId
					+ ", qualificationOther=" + qualificationOther + ", highestQualification=" + highestQualification
					+ ", coursePursued=" + coursePursued + ", courseDuration=" + courseDuration + ", courseYear="
					+ courseYear + ", courseCollege=" + courseCollege + ", minorStudying=" + minorStudying
					+ ", minorWeight=" + minorWeight + ", vaccinationDoneYn=" + vaccinationDoneYn + ", parentsInsCover="
					+ parentsInsCover + ", parentsAnnualIncome=" + parentsAnnualIncome + ", siblingsInsCover="
					+ siblingsInsCover + ", politicallyExposedYn=" + politicallyExposedYn
					+ ", politicallyExposedSpecify=" + politicallyExposedSpecify + ", criminalProceedingsYn="
					+ criminalProceedingsYn + ", criminalProceedingDetails=" + criminalProceedingDetails
					+ ", photoProofDataId=" + photoProofDataId + ", photoProofOther=" + photoProofOther
					+ ", addressProofDataId=" + addressProofDataId + ", addressProofOther=" + addressProofOther
					+ ", incomeProofDataId=" + incomeProofDataId + ", incomeProofOther=" + incomeProofOther
					+ ", policyCategorisationDataId=" + policyCategorisationDataId + ", policyCategorisationOther="
					+ policyCategorisationOther + ", prRelationshipDataId=" + prRelationshipDataId
					+ ", prRelationshipOther=" + prRelationshipOther + ", taxResidenceDeclarationYn="
					+ taxResidenceDeclarationYn + ", eiaAccountAvailableYn=" + eiaAccountAvailableYn
					+ ", eiaAccountNumber=" + eiaAccountNumber + ", eiaApplyYn=" + eiaApplyYn + ", eiaRepositoryDataId="
					+ eiaRepositoryDataId + ", familyPhysicianName=" + familyPhysicianName
					+ ", familyPhysicianAddressLine1=" + familyPhysicianAddressLine1 + ", familyPhysicianAddressLine2="
					+ familyPhysicianAddressLine2 + ", familyPhysicianContactNumber=" + familyPhysicianContactNumber
					+ ", familyPhysicianMobileNumber=" + familyPhysicianMobileNumber + ", nriGstApplicablYn="
					+ nriGstApplicablYn + ", gstinNumber=" + gstinNumber + ", abhaNumber=" + abhaNumber + "]";
		}

	}
}
