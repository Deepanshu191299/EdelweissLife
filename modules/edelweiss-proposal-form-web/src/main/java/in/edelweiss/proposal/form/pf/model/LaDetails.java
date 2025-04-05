package in.edelweiss.proposal.form.pf.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class LaDetails {

	@JsonProperty("ih_row_id")
	private int ihRowId;

	@JsonProperty("relationship_data_id")
	private int relationshipDataId;

	@JsonProperty("occupation")
	private String occupation;

	@JsonProperty("annual_income")
	private long annualIncome;

	@JsonProperty("total_sum_assured")
	private long totalSumAssured;

	@JsonProperty("can_delete")
	private boolean canDelete;

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

	@JsonProperty("communication_details_id")
	private int communicationDetailsId;

	@JsonProperty("ca_address_line1")
	private String caAddressLine1;

	@JsonProperty("ca_address_line2")
	private String caAddressLine2;

	@JsonProperty("ca_address_line3")
	private String caAddressLine3;

	@JsonProperty("ca_address_line4")
	private String caAddressLine4;

	@JsonProperty("ca_city")
	private String caCity;

	@JsonProperty("ca_state")
	private String caState;

	@JsonProperty("ca_pincode")
	private String caPincode;

	@JsonProperty("is_ca_pa_same_yn")
	private String isCaPaSameYn;

	@JsonProperty("pa_address_line1")
	private String paAddressLine1;

	@JsonProperty("pa_address_line2")
	private String paAddressLine2;

	@JsonProperty("pa_address_line3")
	private String paAddressLine3;

	@JsonProperty("pa_address_line4")
	private String paAddressLine4;

	@JsonProperty("pa_city")
	private String paCity;

	@JsonProperty("pa_state")
	private String paState;

	@JsonProperty("pa_pincode")
	private String paPincode;

	@JsonProperty("correspondance_add")
	private String correspondanceAdd;

	@JsonProperty("phone_number_office_std")
	private String phoneNumberOfficeStd;

	@JsonProperty("phone_number_office")
	private String phoneNumberOffice;

	@JsonProperty("phone_number_home_std")
	private String phoneNumberHomeStd;

	@JsonProperty("phone_number_home")
	private String phoneNumberHome;

	@JsonProperty("email_id")
	private String emailId;

	@JsonProperty("facebook_id")
	private String facebookId;

	@JsonProperty("linked_in_id")
	private String linkedInId;

	@JsonProperty("corporate_email_id")
	private String corporateEmailId;

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

	@JsonProperty("fh_any_disease_yn")
	private String fhAnyDiseaseYn;

	@JsonProperty("fh_any_disease_details")
	private String fhAnyDiseaseDetails;

	@JsonProperty("fh_etli_policies_yn")
	private String fhEtliPoliciesYn;

	@JsonProperty("family_history")
	private ArrayList<FamilyHistory> familyHistory;

	@JsonProperty("app_insurance_available_yn")
	private String appInsuranceAvailableYn;

	@JsonProperty("app_insurance_name")
	private String appInsuranceName;

	@JsonProperty("app_insurance_reason")
	private String appInsuranceReason;

	@JsonProperty("app_insurance_date")
	private String appInsuranceDate;

	@JsonProperty("app_dis_ci_benefits_yn")
	private String appDisCiBenefitsYn;

	@JsonProperty("app_dis_ci_name")
	private String appDisCiName;

	@JsonProperty("app_dis_ci_reason")
	private String appDisCiReason;

	@JsonProperty("app_dis_ci_date")
	private String appDisCiDate;

	@JsonProperty("details_ins_policy_available_yn")
	private String detailsInsPolicyAvailableYn;

	@JsonProperty("ih_sf_rejected_yn")
	private String ihSfRejectedYn;

	@JsonProperty("ih_sf_rejected_reason")
	private String ihSfRejectedReason;

	@JsonProperty("ih_sf_special_rate_policy_yn")
	private String ihSfSpecialRatePolicyYn;

	@JsonProperty("e_policy_yn")
	private String ePolicyYn;

	@JsonProperty("insurance_history")
	private ArrayList<InsuranceHistory> insuranceHistory;

	@JsonProperty("life_style_details_id")
	private String lifeStyleDetailsId;

	@JsonProperty("height_cms")
	private String heightCms;

	@JsonProperty("height_feet")
	private int heightFeet;

	@JsonProperty("height_inches")
	private int heightInches;

	@JsonProperty("weight_kg")
	private String weightKg;

	@JsonProperty("weight_variation_yn")
	private String weightVariationYn;

	@JsonProperty("variation_type")
	private String variationType;

	@JsonProperty("variation_weight")
	private int variationWeight;

	@JsonProperty("weight_variation_reason_data_id")
	private int weightVariationReasonDataId;

	@JsonProperty("q_travel_outside_yn")
	private String qTravelOutsideYn;

	@JsonProperty("q_travel_outside_country_code")
	private String qTravelOutsideCountryCode;

	@JsonProperty("q_travel_outside_country_oth")
	private String qTravelOutsideCountryOth;

	@JsonProperty("q_pilot_co_pilot_yn")
	private String qPilotCoPilotYn;

	@JsonProperty("q_adventurous_hobbies_yn")
	private String qAdventurousHobbiesYn;

	@JsonProperty("q_sky_diving_or_parachuting_yn")
	private String qSkyDivingOrParachutingYn;

	@JsonProperty("q_paragliding_or_handgliding_yn")
	private String qParaglidingOrHandglidingYn;

	@JsonProperty("q_mountaineering_or_outdoor_rock_climbing_yn")
	private String qMountaineeringOrOutdoorRockClimbingYn;

	@JsonProperty("q_any_form_of_racing_yn")
	private String qAnyFormOfRacingYn;

	@JsonProperty("q_any_other_hazardous_hobby_yn")
	private String qAnyOtherHazardousHobbyYn;

	@JsonProperty("q_adventurous_hobbies_details")
	private String qAdventurousHobbiesDetails;

	@JsonProperty("q_habit_forming_drugs_yn")
	private String qHabitFormingDrugsYn;

	@JsonProperty("q_habit_forming_drugs_details")
	private String qHabitFormingDrugsDetails;

	@JsonProperty("q_consume_alcohol_yn")
	private String qConsumeAlcoholYn;

	@JsonProperty("q_consume_alcohol_beer_qty")
	private String qConsumeAlcoholBeerQty;

	@JsonProperty("q_consume_alcohol_beer_since")
	private String qConsumeAlcoholBeerSince;

	@JsonProperty("q_consume_alcohol_hard_liquor_qty")
	private String qConsumeAlcoholHardLiquorQty;

	@JsonProperty("q_consume_alcohol_hard_liquor_since")
	private String qConsumeAlcoholHardLiquorSince;

	@JsonProperty("q_consume_alcohol_wine_qty")
	private String qConsumeAlcoholWineQty;

	@JsonProperty("q_consume_alcohol_wine_since")
	private String qConsumeAlcoholWineSince;

	@JsonProperty("q_consume_narcotics_yn")
	private String qConsumeNarcoticsYn;

	@JsonProperty("q_consume_narcotics_qty")
	private String qConsumeNarcoticsQty;

	@JsonProperty("q_consume_narcotics_since")
	private String qConsumeNarcoticsSince;

	@JsonProperty("q_smoke_consume_yn")
	private String qSmokeConsumeYn;

	@JsonProperty("q_smoke_consume_cigarettes_qty")
	private String qSmokeConsumeCigarettesQty;

	@JsonProperty("q_smoke_consume_cigarettes_since")
	private String qSmokeConsumeCigarettesSince;

	@JsonProperty("q_smoke_consume_bidi_qty")
	private String qSmokeConsume_BidiQty;

	@JsonProperty("q_smoke_consume_bidi_since")
	private String qSmokeConsumeBidiSince;

	@JsonProperty("q_smoke_consume_gutka_qty")
	private String qSmokeConsumeGutkaQty;

	@JsonProperty("q_smoke_consume_gutka_since")
	private String qSmokeConsumeGutkaSince;

	@JsonProperty("q_smoke_consume_paan_qty")
	private String qSmokeConsumePaanQty;

	@JsonProperty("q_smoke_consume_paan_since")
	private String q_smoke_consume_paan_since;

	@JsonProperty("q_smoke_consume_tobacco_pouch_qty")
	private String q_smoke_consume_tobacco_pouch_qty;

	@JsonProperty("q_smoke_consume_tobacco_pouch_since")
	private String qSmokeConsumeTobaccoPouchSince;

	@JsonProperty("q_smoke_consume_others")
	private String qSmokeConsumeOthers;

	@JsonProperty("q_smoke_consume_cigar_qty")
	private String qSmokeConsumeCigarQty;

	@JsonProperty("q_smoke_consume_cigar_since")
	private String qSmokeConsumeCigarSince;

	@JsonProperty("q_smoke_consume_jarda_qty")
	private String qSmokeConsumeJardaQty;

	@JsonProperty("q_smoke_consume_jarda_since")
	private String qSmokeConsumeJardaSince;

	@JsonProperty("q_stopped_smoking_yn")
	private String qStoppedSmokingYn;

	@JsonProperty("q_stopped_smoking_since")
	private String qStoppedSmokingSince;

	@JsonProperty("q_stopped_smoking_reason")
	private String qStoppedSmokingReason;

	@JsonProperty("q_consulted_influenza_yn")
	private String qConsultedInfluenzaYn;

	@JsonProperty("q_consulted_influenza_details")
	private String qConsultedInfluenzaDetails;

	@JsonProperty("q_had_ecg_x_ray_yn")
	private String qHadEcgXRayYn;

	@JsonProperty("q_had_ecg_x_ray_details")
	private String qHadEcgXRayDetails;

	@JsonProperty("q_admitted_any_hospital_yn")
	private String qAdmittedAnyHospitalYn;

	@JsonProperty("q_admitted_any_hospital_details")
	private String qAdmittedAnyHospitalDetails;

	@JsonProperty("q_taking_any_medication_yn")
	private String qTakingAnyMedicationYn;

	@JsonProperty("q_taking_any_medication_details")
	private String qTakingAnyMedicationDetails;

	@JsonProperty("q_any_disorder_heart_yn")
	private String qAnyDisordeHheartYn;

	@JsonProperty("q_any_disorder_heart_details")
	private String qAnyDisorderHeartDetails;

	@JsonProperty("q_history_high_blood_pressure_yn")
	private String qHistoryHighBloodPressureYn;

	@JsonProperty("q_history_high_blood_pressure_details")
	private String qHistoryHighBloodPressureDetails;

	@JsonProperty("q_respiratory_lung_trouble_yn")
	private String qRespiratoryLungTroubleYn;

	@JsonProperty("q_respiratory_lung_trouble_details")
	private String qRespiratoryLungTroubleDetails;

	@JsonProperty("q_diabetes_urine_yn")
	private String qDiabetesUrineYn;

	@JsonProperty("q_diabetes_urine_details")
	private String qDiabetesUrineDetails;

	@JsonProperty("q_disease_kidneys_bladder_yn")
	private String qDiseaseKidneysBladderYn;

	@JsonProperty("q_disease_kidneys_bladder_details")
	private String qDiseaseKidneysBladderDetails;

	@JsonProperty("q_disorder_digestive_system_yn")
	private String qDisorderDigestiveSystemYn;

	@JsonProperty("q_disorder_digestive_system_details")
	private String qDisorderDigestiveSystemDetails;

	@JsonProperty("q_cancer_enlarged_gland_yn")
	private String qCancerEnlargedGlandYn;

	@JsonProperty("q_cancer_enlarged_gland_details")
	private String qCancerEnlargedGlandDetails;

	@JsonProperty("q_tropical_diseases_yn")
	private String qTropicalDiseasesYn;

	@JsonProperty("q_tropical_diseases_details")
	private String qTropicalDiseasesDetails;

	@JsonProperty("q_thyroid_disorder_goitre_yn")
	private String qThyroidDisorderGoitreYn;

	@JsonProperty("q_thyroid_disorder_goitre_details")
	private String qThyroidDisorderGoitreDetails;

	@JsonProperty("q_anaemia_bleeding_yn")
	private String qAnaemiaBleedingYn;

	@JsonProperty("q_anaemia_bleeding_details")
	private String qAnaemiaBleedingDetails;

	@JsonProperty("q_nervous_neurological_disorder_yn")
	private String qNervousNeurologicalDisorderYn;

	@JsonProperty("q_nervous_neurological_disorder_details")
	private String qNervousNeurologicalDisorderDetails;

	@JsonProperty("q_ear_eye_disorder_yn")
	private String qEarEyeDisorderYn;

	@JsonProperty("q_ear_eye_disorder_details")
	private String qEarEyeDisorderDetails;

	@JsonProperty("q_disorder_muscle_bone_yn")
	private String qDisorderMuscleBoneYn;

	@JsonProperty("q_disorder_muscle_bone_details")
	private String qDisorderMuscleBoneDetails;

	@JsonProperty("q_test_hiv_aids_yn")
	private String qTestHivAidsYn;

	@JsonProperty("q_test_hiv_aids_details")
	private String qTestHivAidsDetails;

	@JsonProperty("q_excessive_alcohol_yn")
	private String qExcessiveAlcoholYn;

	@JsonProperty("q_excessive_alcohol_details")
	private String qExcessiveAlcoholDetails;

	@JsonProperty("q_any_other_illness_disorder_yn")
	private String qAnyOtherIllnessDisorderYn;

	@JsonProperty("q_any_other_illness_disorder_details")
	private String qAnyOtherIllnessDisorderDetails;

	@JsonProperty("q_deformity_p_abnormality_yn")
	private String qDeformityPAbnormalityYn;

	@JsonProperty("q_deformity_p_abnormality_details")
	private String qDeformityPAbnormalityDetails;

	@JsonProperty("q_health_symptoms_appetite_yn")
	private String qHealthSymptomsAppetiteYn;

	@JsonProperty("q_health_symptoms_appetite_details")
	private String qHealthSymptomsAppetiteDetails;

	@JsonProperty("q_pregnant_yn")
	private String qPregnantYn;

	@JsonProperty("q_pregnant_weeks")
	private String qPregnantWeeks;

	@JsonProperty("q_disorder_female_organ_yn")
	private String qDisorderFemaleOrganYn;

	@JsonProperty("q_disorder_female_organ_details")
	private String qDisorderFemaleOrganDetails;

	@JsonProperty("q_hospitalized_condition_yn")
	private String qHospitalizedConditionYn;

	@JsonProperty("q_hospitalization_date")
	private String qHospitalizationDate;

	@JsonProperty("q_fully_recovered_medications_yn")
	private String qFullyRecoveredMedicationsYn;

	@JsonProperty("q_fully_recovered_medications_details")
	private String qFullyRecoveredMedicationsDetails;

	@JsonProperty("q_health_care_covid_patients_yn")
	private String qHealthCareCovidPatientsYn;

	@JsonProperty("q_covid_tm_diabetes_yn")
	private String qCovidTmDiabetesYn;

	@JsonProperty("q_covid_tm_coronary_yn")
	private String qCovidTmCoronaryYn;

	@JsonProperty("txn_id")
	private String txnId;

	@JsonProperty("close_contact_yn")
	private String closeContactYn;

	@JsonProperty("close_contact_details")
	private String closeContactDetails;

	@JsonProperty("serving_notice_of_quarantine_yn")
	private String servingNoticeOfQuarantineYn;

	@JsonProperty("serving_notice_of_quarantine_details")
	private String servingNoticeOfQuarantineDetails;

	@JsonProperty("advised_to_test_yn")
	private String advisedToTestYn;

	@JsonProperty("advised_to_test_details")
	private String advisedToTestDetails;

	@JsonProperty("tested_positive_yn")
	private String testedPositiveYn;

	@JsonProperty("tested_positive_details")
	private String testedPositiveDetails;

	@JsonProperty("experienced_symptoms_yn")
	private String experiencedSymptomsYn;

	@JsonProperty("experienced_symptoms_details")
	private String experiencedSymptomsDetails;

	@JsonProperty("healthcare_professionals_yn")
	private String healthcareProfessionalsYn;

	@JsonProperty("healthcare_professionals_details")
	private String healthcareProfessionalsDetails;

	@JsonProperty("travel_is_nri_yn")
	private String travelIsNriYn;

	@JsonProperty("travel_nri_country")
	private String travelNriCountry;

	@JsonProperty("travel_nri_city")
	private String travelNriCity;

	@JsonProperty("travel_nri_date_of_travel")
	private String travelNriDateOfTravel;

	@JsonProperty("travel_nri_intended_duration")
	private String travelNriIntendedDuration;

	@JsonProperty("travel_travel_abroad_past14_days_yn")
	private String travelTravelAbroadPast14DaysYn;

	@JsonProperty("travel_travel_abroad_past14_days_country")
	private String travelTravelAbroadPast14DaysCountry;

	@JsonProperty("travel_travel_abroad_past14_days_city")
	private String travelTravelAbroadPast14DaysCity;

	@JsonProperty("travel_travel_abroad_past14_days_date_arrived")
	private String travelTravelAbroadPast14DaysDateArrived;

	@JsonProperty("travel_travel_abroad_past14_days_date_departed")
	private String travelTravelAbroadPast14DaysDateDeparted;

	@JsonProperty("travel_travel_abroad_next3_month_yn")
	private String travelTravelAbroadNext3MonthYn;

	@JsonProperty("travel_travel_abroad_next3_month_country")
	private String travelTravelAbroadNext3MonthCountry;

	@JsonProperty("travel_travel_abroad_next3_month_city")
	private String travelTravelAbroadNext3MonthCity;

	@JsonProperty("travel_travel_abroad_next3_month_date_of_travel")
	private String travelTravelAbroadNext3MonthDateOfTravel;

	@JsonProperty("travel_travel_abroad_next3_month_intended_duration")
	private String travelTravelAbroadNext3MonthIntendedDuration;

	@JsonProperty("covid_vaccinated_yn")
	private String covidVaccinatedYn;

	@JsonProperty("covid_vaccinated_first_dose_date")
	private String covidVaccinatedFirstDoseDate;

	@JsonProperty("covid_vaccinated_second_dose_date")
	private String covidVaccinatedSecondDoseDate;

	@JsonProperty("covid_vaccinated_name")
	private String covidVaccinatedName;

	@JsonProperty("covid_vaccinated_adverse_reaction_yn")
	private String covidVaccinatedAdverseReactionYn;

	@JsonProperty("covid_vaccinated_adverse_reaction_treatment_taken")
	private String covidVaccinatedAdverseReactionTreatmentTaken;

	@JsonProperty("complete_recovery_date")
	private String completeRecoveryDate;

	@JsonProperty("covid_form_path")
	private String covidFormPath;

	@JsonProperty("daily_duties_details")
	private String dailyDutiesDetails;

	@JsonProperty("hospitalized_home_quarantine")
	private String hospitalizedHomeQuarantine;

	@JsonProperty("covid_complication_yn")
	private String covidComplicationYn;

	@JsonProperty("covid_vaccinated_booster_date")
	private String covidVaccinatedBoosterDate;

	@JsonProperty("covid_details")
	private String covidDetails;

	@JsonProperty("diagnosis_details")
	private String diagnosisDetails;

	@JsonProperty("diagnosis_date")
	private String diagnosisDate;

	public int getIhRowId() {
		return ihRowId;
	}

	public void setIhRowId(int ihRowId) {
		this.ihRowId = ihRowId;
	}

	public int getRelationshipDataId() {
		return relationshipDataId;
	}

	public void setRelationshipDataId(int relationshipDataId) {
		this.relationshipDataId = relationshipDataId;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public long getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(long annualIncome) {
		this.annualIncome = annualIncome;
	}

	public long getTotalSumAssured() {
		return totalSumAssured;
	}

	public void setTotalSumAssured(long totalSumAssured) {
		this.totalSumAssured = totalSumAssured;
	}

	public boolean isCanDelete() {
		return canDelete;
	}

	public void setCanDelete(boolean canDelete) {
		this.canDelete = canDelete;
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

	public int getCommunicationDetailsId() {
		return communicationDetailsId;
	}

	public void setCommunicationDetailsId(int communicationDetailsId) {
		this.communicationDetailsId = communicationDetailsId;
	}

	public String getCaAddressLine1() {
		return caAddressLine1;
	}

	public void setCaAddressLine1(String caAddressLine1) {
		this.caAddressLine1 = caAddressLine1;
	}

	public String getCaAddressLine2() {
		return caAddressLine2;
	}

	public void setCaAddressLine2(String caAddressLine2) {
		this.caAddressLine2 = caAddressLine2;
	}

	public String getCaAddressLine3() {
		return caAddressLine3;
	}

	public void setCaAddressLine3(String caAddressLine3) {
		this.caAddressLine3 = caAddressLine3;
	}

	public String getCaAddressLine4() {
		return caAddressLine4;
	}

	public void setCaAddressLine4(String caAddressLine4) {
		this.caAddressLine4 = caAddressLine4;
	}

	public String getCaCity() {
		return caCity;
	}

	public void setCaCity(String caCity) {
		this.caCity = caCity;
	}

	public String getCaState() {
		return caState;
	}

	public void setCaState(String caState) {
		this.caState = caState;
	}

	public String getCaPincode() {
		return caPincode;
	}

	public void setCaPincode(String caPincode) {
		this.caPincode = caPincode;
	}

	public String getIsCaPaSameYn() {
		return isCaPaSameYn;
	}

	public void setIsCaPaSameYn(String isCaPaSameYn) {
		this.isCaPaSameYn = isCaPaSameYn;
	}

	public String getPaAddressLine1() {
		return paAddressLine1;
	}

	public void setPaAddressLine1(String paAddressLine1) {
		this.paAddressLine1 = paAddressLine1;
	}

	public String getPaAddressLine2() {
		return paAddressLine2;
	}

	public void setPaAddressLine2(String paAddressLine2) {
		this.paAddressLine2 = paAddressLine2;
	}

	public String getPaAddressLine3() {
		return paAddressLine3;
	}

	public void setPaAddressLine3(String paAddressLine3) {
		this.paAddressLine3 = paAddressLine3;
	}

	public String getPaAddressLine4() {
		return paAddressLine4;
	}

	public void setPaAddressLine4(String paAddressLine4) {
		this.paAddressLine4 = paAddressLine4;
	}

	public String getPaCity() {
		return paCity;
	}

	public void setPaCity(String paCity) {
		this.paCity = paCity;
	}

	public String getPaState() {
		return paState;
	}

	public void setPaState(String paState) {
		this.paState = paState;
	}

	public String getPaPincode() {
		return paPincode;
	}

	public void setPaPincode(String paPincode) {
		this.paPincode = paPincode;
	}

	public String getCorrespondanceAdd() {
		return correspondanceAdd;
	}

	public void setCorrespondanceAdd(String correspondanceAdd) {
		this.correspondanceAdd = correspondanceAdd;
	}

	public String getPhoneNumberOfficeStd() {
		return phoneNumberOfficeStd;
	}

	public void setPhoneNumberOfficeStd(String phoneNumberOfficeStd) {
		this.phoneNumberOfficeStd = phoneNumberOfficeStd;
	}

	public String getPhoneNumberOffice() {
		return phoneNumberOffice;
	}

	public void setPhoneNumberOffice(String phoneNumberOffice) {
		this.phoneNumberOffice = phoneNumberOffice;
	}

	public String getPhoneNumberHomeStd() {
		return phoneNumberHomeStd;
	}

	public void setPhoneNumberHomeStd(String phoneNumberHomeStd) {
		this.phoneNumberHomeStd = phoneNumberHomeStd;
	}

	public String getPhoneNumberHome() {
		return phoneNumberHome;
	}

	public void setPhoneNumberHome(String phoneNumberHome) {
		this.phoneNumberHome = phoneNumberHome;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getLinkedInId() {
		return linkedInId;
	}

	public void setLinkedInId(String linkedInId) {
		this.linkedInId = linkedInId;
	}

	public String getCorporateEmailId() {
		return corporateEmailId;
	}

	public void setCorporateEmailId(String corporateEmailId) {
		this.corporateEmailId = corporateEmailId;
	}

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

	public String getFhAnyDiseaseYn() {
		return fhAnyDiseaseYn;
	}

	public void setFhAnyDiseaseYn(String fhAnyDiseaseYn) {
		this.fhAnyDiseaseYn = fhAnyDiseaseYn;
	}

	public String getFhAnyDiseaseDetails() {
		return fhAnyDiseaseDetails;
	}

	public void setFhAnyDiseaseDetails(String fhAnyDiseaseDetails) {
		this.fhAnyDiseaseDetails = fhAnyDiseaseDetails;
	}

	public String getFhEtliPoliciesYn() {
		return fhEtliPoliciesYn;
	}

	public void setFhEtliPoliciesYn(String fhEtliPoliciesYn) {
		this.fhEtliPoliciesYn = fhEtliPoliciesYn;
	}

	public ArrayList<FamilyHistory> getFamilyHistory() {
		return familyHistory;
	}

	public void setFamilyHistory(ArrayList<FamilyHistory> familyHistory) {
		this.familyHistory = familyHistory;
	}

	public String getAppInsuranceAvailableYn() {
		return appInsuranceAvailableYn;
	}

	public void setAppInsuranceAvailableYn(String appInsuranceAvailableYn) {
		this.appInsuranceAvailableYn = appInsuranceAvailableYn;
	}

	public String getAppInsuranceName() {
		return appInsuranceName;
	}

	public void setAppInsuranceName(String appInsuranceName) {
		this.appInsuranceName = appInsuranceName;
	}

	public String getAppInsuranceReason() {
		return appInsuranceReason;
	}

	public void setAppInsuranceReason(String appInsuranceReason) {
		this.appInsuranceReason = appInsuranceReason;
	}

	public String getAppInsuranceDate() {
		return appInsuranceDate;
	}

	public void setAppInsuranceDate(String appInsuranceDate) {
		this.appInsuranceDate = appInsuranceDate;
	}

	public String getAppDisCiBenefitsYn() {
		return appDisCiBenefitsYn;
	}

	public void setAppDisCiBenefitsYn(String appDisCiBenefitsYn) {
		this.appDisCiBenefitsYn = appDisCiBenefitsYn;
	}

	public String getAppDisCiName() {
		return appDisCiName;
	}

	public void setAppDisCiName(String appDisCiName) {
		this.appDisCiName = appDisCiName;
	}

	public String getAppDisCiReason() {
		return appDisCiReason;
	}

	public void setAppDisCiReason(String appDisCiReason) {
		this.appDisCiReason = appDisCiReason;
	}

	public String getAppDisCiDate() {
		return appDisCiDate;
	}

	public void setAppDisCiDate(String appDisCiDate) {
		this.appDisCiDate = appDisCiDate;
	}

	public String getDetailsInsPolicyAvailableYn() {
		return detailsInsPolicyAvailableYn;
	}

	public void setDetailsInsPolicyAvailableYn(String detailsInsPolicyAvailableYn) {
		this.detailsInsPolicyAvailableYn = detailsInsPolicyAvailableYn;
	}

	public String getIhSfRejectedYn() {
		return ihSfRejectedYn;
	}

	public void setIhSfRejectedYn(String ihSfRejectedYn) {
		this.ihSfRejectedYn = ihSfRejectedYn;
	}

	public String getIhSfRejectedReason() {
		return ihSfRejectedReason;
	}

	public void setIhSfRejectedReason(String ihSfRejectedReason) {
		this.ihSfRejectedReason = ihSfRejectedReason;
	}

	public String getIhSfSpecialRatePolicyYn() {
		return ihSfSpecialRatePolicyYn;
	}

	public void setIhSfSpecialRatePolicyYn(String ihSfSpecialRatePolicyYn) {
		this.ihSfSpecialRatePolicyYn = ihSfSpecialRatePolicyYn;
	}

	public String getePolicyYn() {
		return ePolicyYn;
	}

	public void setePolicyYn(String ePolicyYn) {
		this.ePolicyYn = ePolicyYn;
	}

	public ArrayList<InsuranceHistory> getInsuranceHistory() {
		return insuranceHistory;
	}

	public void setInsuranceHistory(ArrayList<InsuranceHistory> insuranceHistory) {
		this.insuranceHistory = insuranceHistory;
	}

	public String getLifeStyleDetailsId() {
		return lifeStyleDetailsId;
	}

	public void setLifeStyleDetailsId(String lifeStyleDetailsId) {
		this.lifeStyleDetailsId = lifeStyleDetailsId;
	}

	public String getHeightCms() {
		return heightCms;
	}

	public void setHeightCms(String heightCms) {
		this.heightCms = heightCms;
	}

	public int getHeightFeet() {
		return heightFeet;
	}

	public void setHeightFeet(int heightFeet) {
		this.heightFeet = heightFeet;
	}

	public int getHeightInches() {
		return heightInches;
	}

	public void setHeightInches(int heightInches) {
		this.heightInches = heightInches;
	}

	public String getWeightKg() {
		return weightKg;
	}

	public void setWeightKg(String weightKg) {
		this.weightKg = weightKg;
	}

	public String getWeightVariationYn() {
		return weightVariationYn;
	}

	public void setWeightVariationYn(String weightVariationYn) {
		this.weightVariationYn = weightVariationYn;
	}

	public String getVariationType() {
		return variationType;
	}

	public void setVariationType(String variationType) {
		this.variationType = variationType;
	}

	public int getVariationWeight() {
		return variationWeight;
	}

	public void setVariationWeight(int variationWeight) {
		this.variationWeight = variationWeight;
	}

	public int getWeightVariationReasonDataId() {
		return weightVariationReasonDataId;
	}

	public void setWeightVariationReasonDataId(int weightVariationReasonDataId) {
		this.weightVariationReasonDataId = weightVariationReasonDataId;
	}

	public String getqTravelOutsideYn() {
		return qTravelOutsideYn;
	}

	public void setqTravelOutsideYn(String qTravelOutsideYn) {
		this.qTravelOutsideYn = qTravelOutsideYn;
	}

	public String getqTravelOutsideCountryCode() {
		return qTravelOutsideCountryCode;
	}

	public void setqTravelOutsideCountryCode(String qTravelOutsideCountryCode) {
		this.qTravelOutsideCountryCode = qTravelOutsideCountryCode;
	}

	public String getqTravelOutsideCountryOth() {
		return qTravelOutsideCountryOth;
	}

	public void setqTravelOutsideCountryOth(String qTravelOutsideCountryOth) {
		this.qTravelOutsideCountryOth = qTravelOutsideCountryOth;
	}

	public String getqPilotCoPilotYn() {
		return qPilotCoPilotYn;
	}

	public void setqPilotCoPilotYn(String qPilotCoPilotYn) {
		this.qPilotCoPilotYn = qPilotCoPilotYn;
	}

	public String getqAdventurousHobbiesYn() {
		return qAdventurousHobbiesYn;
	}

	public void setqAdventurousHobbiesYn(String qAdventurousHobbiesYn) {
		this.qAdventurousHobbiesYn = qAdventurousHobbiesYn;
	}

	public String getqSkyDivingOrParachutingYn() {
		return qSkyDivingOrParachutingYn;
	}

	public void setqSkyDivingOrParachutingYn(String qSkyDivingOrParachutingYn) {
		this.qSkyDivingOrParachutingYn = qSkyDivingOrParachutingYn;
	}

	public String getqParaglidingOrHandglidingYn() {
		return qParaglidingOrHandglidingYn;
	}

	public void setqParaglidingOrHandglidingYn(String qParaglidingOrHandglidingYn) {
		this.qParaglidingOrHandglidingYn = qParaglidingOrHandglidingYn;
	}

	public String getqMountaineeringOrOutdoorRockClimbingYn() {
		return qMountaineeringOrOutdoorRockClimbingYn;
	}

	public void setqMountaineeringOrOutdoorRockClimbingYn(String qMountaineeringOrOutdoorRockClimbingYn) {
		this.qMountaineeringOrOutdoorRockClimbingYn = qMountaineeringOrOutdoorRockClimbingYn;
	}

	public String getqAnyFormOfRacingYn() {
		return qAnyFormOfRacingYn;
	}

	public void setqAnyFormOfRacingYn(String qAnyFormOfRacingYn) {
		this.qAnyFormOfRacingYn = qAnyFormOfRacingYn;
	}

	public String getqAnyOtherHazardousHobbyYn() {
		return qAnyOtherHazardousHobbyYn;
	}

	public void setqAnyOtherHazardousHobbyYn(String qAnyOtherHazardousHobbyYn) {
		this.qAnyOtherHazardousHobbyYn = qAnyOtherHazardousHobbyYn;
	}

	public String getqAdventurousHobbiesDetails() {
		return qAdventurousHobbiesDetails;
	}

	public void setqAdventurousHobbiesDetails(String qAdventurousHobbiesDetails) {
		this.qAdventurousHobbiesDetails = qAdventurousHobbiesDetails;
	}

	public String getqHabitFormingDrugsYn() {
		return qHabitFormingDrugsYn;
	}

	public void setqHabitFormingDrugsYn(String qHabitFormingDrugsYn) {
		this.qHabitFormingDrugsYn = qHabitFormingDrugsYn;
	}

	public String getqHabitFormingDrugsDetails() {
		return qHabitFormingDrugsDetails;
	}

	public void setqHabitFormingDrugsDetails(String qHabitFormingDrugsDetails) {
		this.qHabitFormingDrugsDetails = qHabitFormingDrugsDetails;
	}

	public String getqConsumeAlcoholYn() {
		return qConsumeAlcoholYn;
	}

	public void setqConsumeAlcoholYn(String qConsumeAlcoholYn) {
		this.qConsumeAlcoholYn = qConsumeAlcoholYn;
	}

	public String getqConsumeAlcoholBeerQty() {
		return qConsumeAlcoholBeerQty;
	}

	public void setqConsumeAlcoholBeerQty(String qConsumeAlcoholBeerQty) {
		this.qConsumeAlcoholBeerQty = qConsumeAlcoholBeerQty;
	}

	public String getqConsumeAlcoholBeerSince() {
		return qConsumeAlcoholBeerSince;
	}

	public void setqConsumeAlcoholBeerSince(String qConsumeAlcoholBeerSince) {
		this.qConsumeAlcoholBeerSince = qConsumeAlcoholBeerSince;
	}

	public String getqConsumeAlcoholHardLiquorQty() {
		return qConsumeAlcoholHardLiquorQty;
	}

	public void setqConsumeAlcoholHardLiquorQty(String qConsumeAlcoholHardLiquorQty) {
		this.qConsumeAlcoholHardLiquorQty = qConsumeAlcoholHardLiquorQty;
	}

	public String getqConsumeAlcoholHardLiquorSince() {
		return qConsumeAlcoholHardLiquorSince;
	}

	public void setqConsumeAlcoholHardLiquorSince(String qConsumeAlcoholHardLiquorSince) {
		this.qConsumeAlcoholHardLiquorSince = qConsumeAlcoholHardLiquorSince;
	}

	public String getqConsumeAlcoholWineQty() {
		return qConsumeAlcoholWineQty;
	}

	public void setqConsumeAlcoholWineQty(String qConsumeAlcoholWineQty) {
		this.qConsumeAlcoholWineQty = qConsumeAlcoholWineQty;
	}

	public String getqConsumeAlcoholWineSince() {
		return qConsumeAlcoholWineSince;
	}

	public void setqConsumeAlcoholWineSince(String qConsumeAlcoholWineSince) {
		this.qConsumeAlcoholWineSince = qConsumeAlcoholWineSince;
	}

	public String getqConsumeNarcoticsYn() {
		return qConsumeNarcoticsYn;
	}

	public void setqConsumeNarcoticsYn(String qConsumeNarcoticsYn) {
		this.qConsumeNarcoticsYn = qConsumeNarcoticsYn;
	}

	public String getqConsumeNarcoticsQty() {
		return qConsumeNarcoticsQty;
	}

	public void setqConsumeNarcoticsQty(String qConsumeNarcoticsQty) {
		this.qConsumeNarcoticsQty = qConsumeNarcoticsQty;
	}

	public String getqConsumeNarcoticsSince() {
		return qConsumeNarcoticsSince;
	}

	public void setqConsumeNarcoticsSince(String qConsumeNarcoticsSince) {
		this.qConsumeNarcoticsSince = qConsumeNarcoticsSince;
	}

	public String getqSmokeConsumeYn() {
		return qSmokeConsumeYn;
	}

	public void setqSmokeConsumeYn(String qSmokeConsumeYn) {
		this.qSmokeConsumeYn = qSmokeConsumeYn;
	}

	public String getqSmokeConsumeCigarettesQty() {
		return qSmokeConsumeCigarettesQty;
	}

	public void setqSmokeConsumeCigarettesQty(String qSmokeConsumeCigarettesQty) {
		this.qSmokeConsumeCigarettesQty = qSmokeConsumeCigarettesQty;
	}

	public String getqSmokeConsumeCigarettesSince() {
		return qSmokeConsumeCigarettesSince;
	}

	public void setqSmokeConsumeCigarettesSince(String qSmokeConsumeCigarettesSince) {
		this.qSmokeConsumeCigarettesSince = qSmokeConsumeCigarettesSince;
	}

	public String getqSmokeConsume_BidiQty() {
		return qSmokeConsume_BidiQty;
	}

	public void setqSmokeConsume_BidiQty(String qSmokeConsume_BidiQty) {
		this.qSmokeConsume_BidiQty = qSmokeConsume_BidiQty;
	}

	public String getqSmokeConsumeBidiSince() {
		return qSmokeConsumeBidiSince;
	}

	public void setqSmokeConsumeBidiSince(String qSmokeConsumeBidiSince) {
		this.qSmokeConsumeBidiSince = qSmokeConsumeBidiSince;
	}

	public String getqSmokeConsumeGutkaQty() {
		return qSmokeConsumeGutkaQty;
	}

	public void setqSmokeConsumeGutkaQty(String qSmokeConsumeGutkaQty) {
		this.qSmokeConsumeGutkaQty = qSmokeConsumeGutkaQty;
	}

	public String getqSmokeConsumeGutkaSince() {
		return qSmokeConsumeGutkaSince;
	}

	public void setqSmokeConsumeGutkaSince(String qSmokeConsumeGutkaSince) {
		this.qSmokeConsumeGutkaSince = qSmokeConsumeGutkaSince;
	}

	public String getqSmokeConsumePaanQty() {
		return qSmokeConsumePaanQty;
	}

	public void setqSmokeConsumePaanQty(String qSmokeConsumePaanQty) {
		this.qSmokeConsumePaanQty = qSmokeConsumePaanQty;
	}

	public String getQ_smoke_consume_paan_since() {
		return q_smoke_consume_paan_since;
	}

	public void setQ_smoke_consume_paan_since(String q_smoke_consume_paan_since) {
		this.q_smoke_consume_paan_since = q_smoke_consume_paan_since;
	}

	public String getQ_smoke_consume_tobacco_pouch_qty() {
		return q_smoke_consume_tobacco_pouch_qty;
	}

	public void setQ_smoke_consume_tobacco_pouch_qty(String q_smoke_consume_tobacco_pouch_qty) {
		this.q_smoke_consume_tobacco_pouch_qty = q_smoke_consume_tobacco_pouch_qty;
	}

	public String getqSmokeConsumeTobaccoPouchSince() {
		return qSmokeConsumeTobaccoPouchSince;
	}

	public void setqSmokeConsumeTobaccoPouchSince(String qSmokeConsumeTobaccoPouchSince) {
		this.qSmokeConsumeTobaccoPouchSince = qSmokeConsumeTobaccoPouchSince;
	}

	public String getqSmokeConsumeOthers() {
		return qSmokeConsumeOthers;
	}

	public void setqSmokeConsumeOthers(String qSmokeConsumeOthers) {
		this.qSmokeConsumeOthers = qSmokeConsumeOthers;
	}

	public String getqSmokeConsumeCigarQty() {
		return qSmokeConsumeCigarQty;
	}

	public void setqSmokeConsumeCigarQty(String qSmokeConsumeCigarQty) {
		this.qSmokeConsumeCigarQty = qSmokeConsumeCigarQty;
	}

	public String getqSmokeConsumeCigarSince() {
		return qSmokeConsumeCigarSince;
	}

	public void setqSmokeConsumeCigarSince(String qSmokeConsumeCigarSince) {
		this.qSmokeConsumeCigarSince = qSmokeConsumeCigarSince;
	}

	public String getqSmokeConsumeJardaQty() {
		return qSmokeConsumeJardaQty;
	}

	public void setqSmokeConsumeJardaQty(String qSmokeConsumeJardaQty) {
		this.qSmokeConsumeJardaQty = qSmokeConsumeJardaQty;
	}

	public String getqSmokeConsumeJardaSince() {
		return qSmokeConsumeJardaSince;
	}

	public void setqSmokeConsumeJardaSince(String qSmokeConsumeJardaSince) {
		this.qSmokeConsumeJardaSince = qSmokeConsumeJardaSince;
	}

	public String getqStoppedSmokingYn() {
		return qStoppedSmokingYn;
	}

	public void setqStoppedSmokingYn(String qStoppedSmokingYn) {
		this.qStoppedSmokingYn = qStoppedSmokingYn;
	}

	public String getqStoppedSmokingSince() {
		return qStoppedSmokingSince;
	}

	public void setqStoppedSmokingSince(String qStoppedSmokingSince) {
		this.qStoppedSmokingSince = qStoppedSmokingSince;
	}

	public String getqStoppedSmokingReason() {
		return qStoppedSmokingReason;
	}

	public void setqStoppedSmokingReason(String qStoppedSmokingReason) {
		this.qStoppedSmokingReason = qStoppedSmokingReason;
	}

	public String getqConsultedInfluenzaYn() {
		return qConsultedInfluenzaYn;
	}

	public void setqConsultedInfluenzaYn(String qConsultedInfluenzaYn) {
		this.qConsultedInfluenzaYn = qConsultedInfluenzaYn;
	}

	public String getqConsultedInfluenzaDetails() {
		return qConsultedInfluenzaDetails;
	}

	public void setqConsultedInfluenzaDetails(String qConsultedInfluenzaDetails) {
		this.qConsultedInfluenzaDetails = qConsultedInfluenzaDetails;
	}

	public String getqHadEcgXRayYn() {
		return qHadEcgXRayYn;
	}

	public void setqHadEcgXRayYn(String qHadEcgXRayYn) {
		this.qHadEcgXRayYn = qHadEcgXRayYn;
	}

	public String getqHadEcgXRayDetails() {
		return qHadEcgXRayDetails;
	}

	public void setqHadEcgXRayDetails(String qHadEcgXRayDetails) {
		this.qHadEcgXRayDetails = qHadEcgXRayDetails;
	}

	public String getqAdmittedAnyHospitalYn() {
		return qAdmittedAnyHospitalYn;
	}

	public void setqAdmittedAnyHospitalYn(String qAdmittedAnyHospitalYn) {
		this.qAdmittedAnyHospitalYn = qAdmittedAnyHospitalYn;
	}

	public String getqAdmittedAnyHospitalDetails() {
		return qAdmittedAnyHospitalDetails;
	}

	public void setqAdmittedAnyHospitalDetails(String qAdmittedAnyHospitalDetails) {
		this.qAdmittedAnyHospitalDetails = qAdmittedAnyHospitalDetails;
	}

	public String getqTakingAnyMedicationYn() {
		return qTakingAnyMedicationYn;
	}

	public void setqTakingAnyMedicationYn(String qTakingAnyMedicationYn) {
		this.qTakingAnyMedicationYn = qTakingAnyMedicationYn;
	}

	public String getqTakingAnyMedicationDetails() {
		return qTakingAnyMedicationDetails;
	}

	public void setqTakingAnyMedicationDetails(String qTakingAnyMedicationDetails) {
		this.qTakingAnyMedicationDetails = qTakingAnyMedicationDetails;
	}

	public String getqAnyDisordeHheartYn() {
		return qAnyDisordeHheartYn;
	}

	public void setqAnyDisordeHheartYn(String qAnyDisordeHheartYn) {
		this.qAnyDisordeHheartYn = qAnyDisordeHheartYn;
	}

	public String getqAnyDisorderHeartDetails() {
		return qAnyDisorderHeartDetails;
	}

	public void setqAnyDisorderHeartDetails(String qAnyDisorderHeartDetails) {
		this.qAnyDisorderHeartDetails = qAnyDisorderHeartDetails;
	}

	public String getqHistoryHighBloodPressureYn() {
		return qHistoryHighBloodPressureYn;
	}

	public void setqHistoryHighBloodPressureYn(String qHistoryHighBloodPressureYn) {
		this.qHistoryHighBloodPressureYn = qHistoryHighBloodPressureYn;
	}

	public String getqHistoryHighBloodPressureDetails() {
		return qHistoryHighBloodPressureDetails;
	}

	public void setqHistoryHighBloodPressureDetails(String qHistoryHighBloodPressureDetails) {
		this.qHistoryHighBloodPressureDetails = qHistoryHighBloodPressureDetails;
	}

	public String getqRespiratoryLungTroubleYn() {
		return qRespiratoryLungTroubleYn;
	}

	public void setqRespiratoryLungTroubleYn(String qRespiratoryLungTroubleYn) {
		this.qRespiratoryLungTroubleYn = qRespiratoryLungTroubleYn;
	}

	public String getqRespiratoryLungTroubleDetails() {
		return qRespiratoryLungTroubleDetails;
	}

	public void setqRespiratoryLungTroubleDetails(String qRespiratoryLungTroubleDetails) {
		this.qRespiratoryLungTroubleDetails = qRespiratoryLungTroubleDetails;
	}

	public String getqDiabetesUrineYn() {
		return qDiabetesUrineYn;
	}

	public void setqDiabetesUrineYn(String qDiabetesUrineYn) {
		this.qDiabetesUrineYn = qDiabetesUrineYn;
	}

	public String getqDiabetesUrineDetails() {
		return qDiabetesUrineDetails;
	}

	public void setqDiabetesUrineDetails(String qDiabetesUrineDetails) {
		this.qDiabetesUrineDetails = qDiabetesUrineDetails;
	}

	public String getqDiseaseKidneysBladderYn() {
		return qDiseaseKidneysBladderYn;
	}

	public void setqDiseaseKidneysBladderYn(String qDiseaseKidneysBladderYn) {
		this.qDiseaseKidneysBladderYn = qDiseaseKidneysBladderYn;
	}

	public String getqDiseaseKidneysBladderDetails() {
		return qDiseaseKidneysBladderDetails;
	}

	public void setqDiseaseKidneysBladderDetails(String qDiseaseKidneysBladderDetails) {
		this.qDiseaseKidneysBladderDetails = qDiseaseKidneysBladderDetails;
	}

	public String getqDisorderDigestiveSystemYn() {
		return qDisorderDigestiveSystemYn;
	}

	public void setqDisorderDigestiveSystemYn(String qDisorderDigestiveSystemYn) {
		this.qDisorderDigestiveSystemYn = qDisorderDigestiveSystemYn;
	}

	public String getqDisorderDigestiveSystemDetails() {
		return qDisorderDigestiveSystemDetails;
	}

	public void setqDisorderDigestiveSystemDetails(String qDisorderDigestiveSystemDetails) {
		this.qDisorderDigestiveSystemDetails = qDisorderDigestiveSystemDetails;
	}

	public String getqCancerEnlargedGlandYn() {
		return qCancerEnlargedGlandYn;
	}

	public void setqCancerEnlargedGlandYn(String qCancerEnlargedGlandYn) {
		this.qCancerEnlargedGlandYn = qCancerEnlargedGlandYn;
	}

	public String getqCancerEnlargedGlandDetails() {
		return qCancerEnlargedGlandDetails;
	}

	public void setqCancerEnlargedGlandDetails(String qCancerEnlargedGlandDetails) {
		this.qCancerEnlargedGlandDetails = qCancerEnlargedGlandDetails;
	}

	public String getqTropicalDiseasesYn() {
		return qTropicalDiseasesYn;
	}

	public void setqTropicalDiseasesYn(String qTropicalDiseasesYn) {
		this.qTropicalDiseasesYn = qTropicalDiseasesYn;
	}

	public String getqTropicalDiseasesDetails() {
		return qTropicalDiseasesDetails;
	}

	public void setqTropicalDiseasesDetails(String qTropicalDiseasesDetails) {
		this.qTropicalDiseasesDetails = qTropicalDiseasesDetails;
	}

	public String getqThyroidDisorderGoitreYn() {
		return qThyroidDisorderGoitreYn;
	}

	public void setqThyroidDisorderGoitreYn(String qThyroidDisorderGoitreYn) {
		this.qThyroidDisorderGoitreYn = qThyroidDisorderGoitreYn;
	}

	public String getqThyroidDisorderGoitreDetails() {
		return qThyroidDisorderGoitreDetails;
	}

	public void setqThyroidDisorderGoitreDetails(String qThyroidDisorderGoitreDetails) {
		this.qThyroidDisorderGoitreDetails = qThyroidDisorderGoitreDetails;
	}

	public String getqAnaemiaBleedingYn() {
		return qAnaemiaBleedingYn;
	}

	public void setqAnaemiaBleedingYn(String qAnaemiaBleedingYn) {
		this.qAnaemiaBleedingYn = qAnaemiaBleedingYn;
	}

	public String getqAnaemiaBleedingDetails() {
		return qAnaemiaBleedingDetails;
	}

	public void setqAnaemiaBleedingDetails(String qAnaemiaBleedingDetails) {
		this.qAnaemiaBleedingDetails = qAnaemiaBleedingDetails;
	}

	public String getqNervousNeurologicalDisorderYn() {
		return qNervousNeurologicalDisorderYn;
	}

	public void setqNervousNeurologicalDisorderYn(String qNervousNeurologicalDisorderYn) {
		this.qNervousNeurologicalDisorderYn = qNervousNeurologicalDisorderYn;
	}

	public String getqNervousNeurologicalDisorderDetails() {
		return qNervousNeurologicalDisorderDetails;
	}

	public void setqNervousNeurologicalDisorderDetails(String qNervousNeurologicalDisorderDetails) {
		this.qNervousNeurologicalDisorderDetails = qNervousNeurologicalDisorderDetails;
	}

	public String getqEarEyeDisorderYn() {
		return qEarEyeDisorderYn;
	}

	public void setqEarEyeDisorderYn(String qEarEyeDisorderYn) {
		this.qEarEyeDisorderYn = qEarEyeDisorderYn;
	}

	public String getqEarEyeDisorderDetails() {
		return qEarEyeDisorderDetails;
	}

	public void setqEarEyeDisorderDetails(String qEarEyeDisorderDetails) {
		this.qEarEyeDisorderDetails = qEarEyeDisorderDetails;
	}

	public String getqDisorderMuscleBoneYn() {
		return qDisorderMuscleBoneYn;
	}

	public void setqDisorderMuscleBoneYn(String qDisorderMuscleBoneYn) {
		this.qDisorderMuscleBoneYn = qDisorderMuscleBoneYn;
	}

	public String getqDisorderMuscleBoneDetails() {
		return qDisorderMuscleBoneDetails;
	}

	public void setqDisorderMuscleBoneDetails(String qDisorderMuscleBoneDetails) {
		this.qDisorderMuscleBoneDetails = qDisorderMuscleBoneDetails;
	}

	public String getqTestHivAidsYn() {
		return qTestHivAidsYn;
	}

	public void setqTestHivAidsYn(String qTestHivAidsYn) {
		this.qTestHivAidsYn = qTestHivAidsYn;
	}

	public String getqTestHivAidsDetails() {
		return qTestHivAidsDetails;
	}

	public void setqTestHivAidsDetails(String qTestHivAidsDetails) {
		this.qTestHivAidsDetails = qTestHivAidsDetails;
	}

	public String getqExcessiveAlcoholYn() {
		return qExcessiveAlcoholYn;
	}

	public void setqExcessiveAlcoholYn(String qExcessiveAlcoholYn) {
		this.qExcessiveAlcoholYn = qExcessiveAlcoholYn;
	}

	public String getqExcessiveAlcoholDetails() {
		return qExcessiveAlcoholDetails;
	}

	public void setqExcessiveAlcoholDetails(String qExcessiveAlcoholDetails) {
		this.qExcessiveAlcoholDetails = qExcessiveAlcoholDetails;
	}

	public String getqAnyOtherIllnessDisorderYn() {
		return qAnyOtherIllnessDisorderYn;
	}

	public void setqAnyOtherIllnessDisorderYn(String qAnyOtherIllnessDisorderYn) {
		this.qAnyOtherIllnessDisorderYn = qAnyOtherIllnessDisorderYn;
	}

	public String getqAnyOtherIllnessDisorderDetails() {
		return qAnyOtherIllnessDisorderDetails;
	}

	public void setqAnyOtherIllnessDisorderDetails(String qAnyOtherIllnessDisorderDetails) {
		this.qAnyOtherIllnessDisorderDetails = qAnyOtherIllnessDisorderDetails;
	}

	public String getqDeformityPAbnormalityYn() {
		return qDeformityPAbnormalityYn;
	}

	public void setqDeformityPAbnormalityYn(String qDeformityPAbnormalityYn) {
		this.qDeformityPAbnormalityYn = qDeformityPAbnormalityYn;
	}

	public String getqDeformityPAbnormalityDetails() {
		return qDeformityPAbnormalityDetails;
	}

	public void setqDeformityPAbnormalityDetails(String qDeformityPAbnormalityDetails) {
		this.qDeformityPAbnormalityDetails = qDeformityPAbnormalityDetails;
	}

	public String getqHealthSymptomsAppetiteYn() {
		return qHealthSymptomsAppetiteYn;
	}

	public void setqHealthSymptomsAppetiteYn(String qHealthSymptomsAppetiteYn) {
		this.qHealthSymptomsAppetiteYn = qHealthSymptomsAppetiteYn;
	}

	public String getqHealthSymptomsAppetiteDetails() {
		return qHealthSymptomsAppetiteDetails;
	}

	public void setqHealthSymptomsAppetiteDetails(String qHealthSymptomsAppetiteDetails) {
		this.qHealthSymptomsAppetiteDetails = qHealthSymptomsAppetiteDetails;
	}

	public String getqPregnantYn() {
		return qPregnantYn;
	}

	public void setqPregnantYn(String qPregnantYn) {
		this.qPregnantYn = qPregnantYn;
	}

	public String getqPregnantWeeks() {
		return qPregnantWeeks;
	}

	public void setqPregnantWeeks(String qPregnantWeeks) {
		this.qPregnantWeeks = qPregnantWeeks;
	}

	public String getqDisorderFemaleOrganYn() {
		return qDisorderFemaleOrganYn;
	}

	public void setqDisorderFemaleOrganYn(String qDisorderFemaleOrganYn) {
		this.qDisorderFemaleOrganYn = qDisorderFemaleOrganYn;
	}

	public String getqDisorderFemaleOrganDetails() {
		return qDisorderFemaleOrganDetails;
	}

	public void setqDisorderFemaleOrganDetails(String qDisorderFemaleOrganDetails) {
		this.qDisorderFemaleOrganDetails = qDisorderFemaleOrganDetails;
	}

	public String getqHospitalizedConditionYn() {
		return qHospitalizedConditionYn;
	}

	public void setqHospitalizedConditionYn(String qHospitalizedConditionYn) {
		this.qHospitalizedConditionYn = qHospitalizedConditionYn;
	}

	public String getqHospitalizationDate() {
		return qHospitalizationDate;
	}

	public void setqHospitalizationDate(String qHospitalizationDate) {
		this.qHospitalizationDate = qHospitalizationDate;
	}

	public String getqFullyRecoveredMedicationsYn() {
		return qFullyRecoveredMedicationsYn;
	}

	public void setqFullyRecoveredMedicationsYn(String qFullyRecoveredMedicationsYn) {
		this.qFullyRecoveredMedicationsYn = qFullyRecoveredMedicationsYn;
	}

	public String getqFullyRecoveredMedicationsDetails() {
		return qFullyRecoveredMedicationsDetails;
	}

	public void setqFullyRecoveredMedicationsDetails(String qFullyRecoveredMedicationsDetails) {
		this.qFullyRecoveredMedicationsDetails = qFullyRecoveredMedicationsDetails;
	}

	public String getqHealthCareCovidPatientsYn() {
		return qHealthCareCovidPatientsYn;
	}

	public void setqHealthCareCovidPatientsYn(String qHealthCareCovidPatientsYn) {
		this.qHealthCareCovidPatientsYn = qHealthCareCovidPatientsYn;
	}

	public String getqCovidTmDiabetesYn() {
		return qCovidTmDiabetesYn;
	}

	public void setqCovidTmDiabetesYn(String qCovidTmDiabetesYn) {
		this.qCovidTmDiabetesYn = qCovidTmDiabetesYn;
	}

	public String getqCovidTmCoronaryYn() {
		return qCovidTmCoronaryYn;
	}

	public void setqCovidTmCoronaryYn(String qCovidTmCoronaryYn) {
		this.qCovidTmCoronaryYn = qCovidTmCoronaryYn;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public String getCloseContactYn() {
		return closeContactYn;
	}

	public void setCloseContactYn(String closeContactYn) {
		this.closeContactYn = closeContactYn;
	}

	public String getCloseContactDetails() {
		return closeContactDetails;
	}

	public void setCloseContactDetails(String closeContactDetails) {
		this.closeContactDetails = closeContactDetails;
	}

	public String getServingNoticeOfQuarantineYn() {
		return servingNoticeOfQuarantineYn;
	}

	public void setServingNoticeOfQuarantineYn(String servingNoticeOfQuarantineYn) {
		this.servingNoticeOfQuarantineYn = servingNoticeOfQuarantineYn;
	}

	public String getServingNoticeOfQuarantineDetails() {
		return servingNoticeOfQuarantineDetails;
	}

	public void setServingNoticeOfQuarantineDetails(String servingNoticeOfQuarantineDetails) {
		this.servingNoticeOfQuarantineDetails = servingNoticeOfQuarantineDetails;
	}

	public String getAdvisedToTestYn() {
		return advisedToTestYn;
	}

	public void setAdvisedToTestYn(String advisedToTestYn) {
		this.advisedToTestYn = advisedToTestYn;
	}

	public String getAdvisedToTestDetails() {
		return advisedToTestDetails;
	}

	public void setAdvisedToTestDetails(String advisedToTestDetails) {
		this.advisedToTestDetails = advisedToTestDetails;
	}

	public String getTestedPositiveYn() {
		return testedPositiveYn;
	}

	public void setTestedPositiveYn(String testedPositiveYn) {
		this.testedPositiveYn = testedPositiveYn;
	}

	public String getTestedPositiveDetails() {
		return testedPositiveDetails;
	}

	public void setTestedPositiveDetails(String testedPositiveDetails) {
		this.testedPositiveDetails = testedPositiveDetails;
	}

	public String getExperiencedSymptomsYn() {
		return experiencedSymptomsYn;
	}

	public void setExperiencedSymptomsYn(String experiencedSymptomsYn) {
		this.experiencedSymptomsYn = experiencedSymptomsYn;
	}

	public String getExperiencedSymptomsDetails() {
		return experiencedSymptomsDetails;
	}

	public void setExperiencedSymptomsDetails(String experiencedSymptomsDetails) {
		this.experiencedSymptomsDetails = experiencedSymptomsDetails;
	}

	public String getHealthcareProfessionalsYn() {
		return healthcareProfessionalsYn;
	}

	public void setHealthcareProfessionalsYn(String healthcareProfessionalsYn) {
		this.healthcareProfessionalsYn = healthcareProfessionalsYn;
	}

	public String getHealthcareProfessionalsDetails() {
		return healthcareProfessionalsDetails;
	}

	public void setHealthcareProfessionalsDetails(String healthcareProfessionalsDetails) {
		this.healthcareProfessionalsDetails = healthcareProfessionalsDetails;
	}

	public String getTravelIsNriYn() {
		return travelIsNriYn;
	}

	public void setTravelIsNriYn(String travelIsNriYn) {
		this.travelIsNriYn = travelIsNriYn;
	}

	public String getTravelNriCountry() {
		return travelNriCountry;
	}

	public void setTravelNriCountry(String travelNriCountry) {
		this.travelNriCountry = travelNriCountry;
	}

	public String getTravelNriCity() {
		return travelNriCity;
	}

	public void setTravelNriCity(String travelNriCity) {
		this.travelNriCity = travelNriCity;
	}

	public String getTravelNriDateOfTravel() {
		return travelNriDateOfTravel;
	}

	public void setTravelNriDateOfTravel(String travelNriDateOfTravel) {
		this.travelNriDateOfTravel = travelNriDateOfTravel;
	}

	public String getTravelNriIntendedDuration() {
		return travelNriIntendedDuration;
	}

	public void setTravelNriIntendedDuration(String travelNriIntendedDuration) {
		this.travelNriIntendedDuration = travelNriIntendedDuration;
	}

	public String getTravelTravelAbroadPast14DaysYn() {
		return travelTravelAbroadPast14DaysYn;
	}

	public void setTravelTravelAbroadPast14DaysYn(String travelTravelAbroadPast14DaysYn) {
		this.travelTravelAbroadPast14DaysYn = travelTravelAbroadPast14DaysYn;
	}

	public String getTravelTravelAbroadPast14DaysCountry() {
		return travelTravelAbroadPast14DaysCountry;
	}

	public void setTravelTravelAbroadPast14DaysCountry(String travelTravelAbroadPast14DaysCountry) {
		this.travelTravelAbroadPast14DaysCountry = travelTravelAbroadPast14DaysCountry;
	}

	public String getTravelTravelAbroadPast14DaysCity() {
		return travelTravelAbroadPast14DaysCity;
	}

	public void setTravelTravelAbroadPast14DaysCity(String travelTravelAbroadPast14DaysCity) {
		this.travelTravelAbroadPast14DaysCity = travelTravelAbroadPast14DaysCity;
	}

	public String getTravelTravelAbroadPast14DaysDateArrived() {
		return travelTravelAbroadPast14DaysDateArrived;
	}

	public void setTravelTravelAbroadPast14DaysDateArrived(String travelTravelAbroadPast14DaysDateArrived) {
		this.travelTravelAbroadPast14DaysDateArrived = travelTravelAbroadPast14DaysDateArrived;
	}

	public String getTravelTravelAbroadPast14DaysDateDeparted() {
		return travelTravelAbroadPast14DaysDateDeparted;
	}

	public void setTravelTravelAbroadPast14DaysDateDeparted(String travelTravelAbroadPast14DaysDateDeparted) {
		this.travelTravelAbroadPast14DaysDateDeparted = travelTravelAbroadPast14DaysDateDeparted;
	}

	public String getTravelTravelAbroadNext3MonthYn() {
		return travelTravelAbroadNext3MonthYn;
	}

	public void setTravelTravelAbroadNext3MonthYn(String travelTravelAbroadNext3MonthYn) {
		this.travelTravelAbroadNext3MonthYn = travelTravelAbroadNext3MonthYn;
	}

	public String getTravelTravelAbroadNext3MonthCountry() {
		return travelTravelAbroadNext3MonthCountry;
	}

	public void setTravelTravelAbroadNext3MonthCountry(String travelTravelAbroadNext3MonthCountry) {
		this.travelTravelAbroadNext3MonthCountry = travelTravelAbroadNext3MonthCountry;
	}

	public String getTravelTravelAbroadNext3MonthCity() {
		return travelTravelAbroadNext3MonthCity;
	}

	public void setTravelTravelAbroadNext3MonthCity(String travelTravelAbroadNext3MonthCity) {
		this.travelTravelAbroadNext3MonthCity = travelTravelAbroadNext3MonthCity;
	}

	public String getTravelTravelAbroadNext3MonthDateOfTravel() {
		return travelTravelAbroadNext3MonthDateOfTravel;
	}

	public void setTravelTravelAbroadNext3MonthDateOfTravel(String travelTravelAbroadNext3MonthDateOfTravel) {
		this.travelTravelAbroadNext3MonthDateOfTravel = travelTravelAbroadNext3MonthDateOfTravel;
	}

	public String getTravelTravelAbroadNext3MonthIntendedDuration() {
		return travelTravelAbroadNext3MonthIntendedDuration;
	}

	public void setTravelTravelAbroadNext3MonthIntendedDuration(String travelTravelAbroadNext3MonthIntendedDuration) {
		this.travelTravelAbroadNext3MonthIntendedDuration = travelTravelAbroadNext3MonthIntendedDuration;
	}

	public String getCovidVaccinatedYn() {
		return covidVaccinatedYn;
	}

	public void setCovidVaccinatedYn(String covidVaccinatedYn) {
		this.covidVaccinatedYn = covidVaccinatedYn;
	}

	public String getCovidVaccinatedFirstDoseDate() {
		return covidVaccinatedFirstDoseDate;
	}

	public void setCovidVaccinatedFirstDoseDate(String covidVaccinatedFirstDoseDate) {
		this.covidVaccinatedFirstDoseDate = covidVaccinatedFirstDoseDate;
	}

	public String getCovidVaccinatedSecondDoseDate() {
		return covidVaccinatedSecondDoseDate;
	}

	public void setCovidVaccinatedSecondDoseDate(String covidVaccinatedSecondDoseDate) {
		this.covidVaccinatedSecondDoseDate = covidVaccinatedSecondDoseDate;
	}

	public String getCovidVaccinatedName() {
		return covidVaccinatedName;
	}

	public void setCovidVaccinatedName(String covidVaccinatedName) {
		this.covidVaccinatedName = covidVaccinatedName;
	}

	public String getCovidVaccinatedAdverseReactionYn() {
		return covidVaccinatedAdverseReactionYn;
	}

	public void setCovidVaccinatedAdverseReactionYn(String covidVaccinatedAdverseReactionYn) {
		this.covidVaccinatedAdverseReactionYn = covidVaccinatedAdverseReactionYn;
	}

	public String getCovidVaccinatedAdverseReactionTreatmentTaken() {
		return covidVaccinatedAdverseReactionTreatmentTaken;
	}

	public void setCovidVaccinatedAdverseReactionTreatmentTaken(String covidVaccinatedAdverseReactionTreatmentTaken) {
		this.covidVaccinatedAdverseReactionTreatmentTaken = covidVaccinatedAdverseReactionTreatmentTaken;
	}

	public String getCompleteRecoveryDate() {
		return completeRecoveryDate;
	}

	public void setCompleteRecoveryDate(String completeRecoveryDate) {
		this.completeRecoveryDate = completeRecoveryDate;
	}

	public String getCovidFormPath() {
		return covidFormPath;
	}

	public void setCovidFormPath(String covidFormPath) {
		this.covidFormPath = covidFormPath;
	}

	public String getDailyDutiesDetails() {
		return dailyDutiesDetails;
	}

	public void setDailyDutiesDetails(String dailyDutiesDetails) {
		this.dailyDutiesDetails = dailyDutiesDetails;
	}

	public String getHospitalizedHomeQuarantine() {
		return hospitalizedHomeQuarantine;
	}

	public void setHospitalizedHomeQuarantine(String hospitalizedHomeQuarantine) {
		this.hospitalizedHomeQuarantine = hospitalizedHomeQuarantine;
	}

	public String getCovidComplicationYn() {
		return covidComplicationYn;
	}

	public void setCovidComplicationYn(String covidComplicationYn) {
		this.covidComplicationYn = covidComplicationYn;
	}

	public String getCovidVaccinatedBoosterDate() {
		return covidVaccinatedBoosterDate;
	}

	public void setCovidVaccinatedBoosterDate(String covidVaccinatedBoosterDate) {
		this.covidVaccinatedBoosterDate = covidVaccinatedBoosterDate;
	}

	public String getCovidDetails() {
		return covidDetails;
	}

	public void setCovidDetails(String covidDetails) {
		this.covidDetails = covidDetails;
	}

	public String getDiagnosisDetails() {
		return diagnosisDetails;
	}

	public void setDiagnosisDetails(String diagnosisDetails) {
		this.diagnosisDetails = diagnosisDetails;
	}

	public String getDiagnosisDate() {
		return diagnosisDate;
	}

	public void setDiagnosisDate(String diagnosisDate) {
		this.diagnosisDate = diagnosisDate;
	}

	@Override
	public String toString() {
		return "LaDetails [ihRowId=" + ihRowId + ", relationshipDataId=" + relationshipDataId + ", occupation="
				+ occupation + ", annualIncome=" + annualIncome + ", totalSumAssured=" + totalSumAssured
				+ ", canDelete=" + canDelete + ", personalDetailsId=" + personalDetailsId + ", leadId=" + leadId
				+ ", titleDataId=" + titleDataId + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", fullName=" + fullName + ", isLaPrSameYn=" + isLaPrSameYn
				+ ", laPrRelationDataId=" + laPrRelationDataId + ", dob=" + dob + ", mobileNumber=" + mobileNumber
				+ ", emailAddress=" + emailAddress + ", isSmokerYn=" + isSmokerYn + ", genderDataId=" + genderDataId
				+ ", maritalStatusDataId=" + maritalStatusDataId + ", panNumber=" + panNumber + ", aadharNumber="
				+ aadharNumber + ", fatherName=" + fatherName + ", motherName=" + motherName + ", spouseName="
				+ spouseName + ", nationalityDataId=" + nationalityDataId + ", nationalityOther=" + nationalityOther
				+ ", ageProofDataId=" + ageProofDataId + ", ageProofOther=" + ageProofOther + ", bankAccountNumber="
				+ bankAccountNumber + ", ifscCode=" + ifscCode + ", bankName=" + bankName + ", bankBranchLocation="
				+ bankBranchLocation + ", accountHolderName=" + accountHolderName + ", qualificationDataId="
				+ qualificationDataId + ", qualificationOther=" + qualificationOther + ", highestQualification="
				+ highestQualification + ", coursePursued=" + coursePursued + ", courseDuration=" + courseDuration
				+ ", courseYear=" + courseYear + ", courseCollege=" + courseCollege + ", minorStudying=" + minorStudying
				+ ", minorWeight=" + minorWeight + ", vaccinationDoneYn=" + vaccinationDoneYn + ", parentsInsCover="
				+ parentsInsCover + ", parentsAnnualIncome=" + parentsAnnualIncome + ", siblingsInsCover="
				+ siblingsInsCover + ", politicallyExposedYn=" + politicallyExposedYn + ", politicallyExposedSpecify="
				+ politicallyExposedSpecify + ", criminalProceedingsYn=" + criminalProceedingsYn
				+ ", criminalProceedingDetails=" + criminalProceedingDetails + ", photoProofDataId=" + photoProofDataId
				+ ", photoProofOther=" + photoProofOther + ", addressProofDataId=" + addressProofDataId
				+ ", addressProofOther=" + addressProofOther + ", incomeProofDataId=" + incomeProofDataId
				+ ", incomeProofOther=" + incomeProofOther + ", policyCategorisationDataId="
				+ policyCategorisationDataId + ", policyCategorisationOther=" + policyCategorisationOther
				+ ", prRelationshipDataId=" + prRelationshipDataId + ", prRelationshipOther=" + prRelationshipOther
				+ ", taxResidenceDeclarationYn=" + taxResidenceDeclarationYn + ", eiaAccountAvailableYn="
				+ eiaAccountAvailableYn + ", eiaAccountNumber=" + eiaAccountNumber + ", eiaApplyYn=" + eiaApplyYn
				+ ", eiaRepositoryDataId=" + eiaRepositoryDataId + ", familyPhysicianName=" + familyPhysicianName
				+ ", familyPhysicianAddressLine1=" + familyPhysicianAddressLine1 + ", familyPhysicianAddressLine2="
				+ familyPhysicianAddressLine2 + ", familyPhysicianContactNumber=" + familyPhysicianContactNumber
				+ ", familyPhysicianMobileNumber=" + familyPhysicianMobileNumber + ", nriGstApplicablYn="
				+ nriGstApplicablYn + ", gstinNumber=" + gstinNumber + ", communicationDetailsId="
				+ communicationDetailsId + ", caAddressLine1=" + caAddressLine1 + ", caAddressLine2=" + caAddressLine2
				+ ", caAddressLine3=" + caAddressLine3 + ", caAddressLine4=" + caAddressLine4 + ", caCity=" + caCity
				+ ", caState=" + caState + ", caPincode=" + caPincode + ", isCaPaSameYn=" + isCaPaSameYn
				+ ", paAddressLine1=" + paAddressLine1 + ", paAddressLine2=" + paAddressLine2 + ", paAddressLine3="
				+ paAddressLine3 + ", paAddressLine4=" + paAddressLine4 + ", paCity=" + paCity + ", paState=" + paState
				+ ", paPincode=" + paPincode + ", correspondanceAdd=" + correspondanceAdd + ", phoneNumberOfficeStd="
				+ phoneNumberOfficeStd + ", phoneNumberOffice=" + phoneNumberOffice + ", phoneNumberHomeStd="
				+ phoneNumberHomeStd + ", phoneNumberHome=" + phoneNumberHome + ", emailId=" + emailId + ", facebookId="
				+ facebookId + ", linkedInId=" + linkedInId + ", corporateEmailId=" + corporateEmailId
				+ ", employmentDetailsId=" + employmentDetailsId + ", empRowId=" + empRowId + ", employmentTypeDataId="
				+ employmentTypeDataId + ", organisationTypeDataId=" + organisationTypeDataId
				+ ", organisationTypeOther=" + organisationTypeOther + ", sourcIncomeOtherYn=" + sourcIncomeOtherYn
				+ ", sourceIncomeOtherDetails=" + sourceIncomeOtherDetails + ", employerName=" + employerName
				+ ", employerAddress=" + employerAddress + ", businessNature=" + businessNature + ", designationHeld="
				+ designationHeld + ", dutyNatureTypeDataId=" + dutyNatureTypeDataId + ", employCountTypeDataId="
				+ employCountTypeDataId + ", workingDurationYear=" + workingDurationYear + ", workingDurationMnth="
				+ workingDurationMnth + ", fhAnyDiseaseYn=" + fhAnyDiseaseYn + ", fhAnyDiseaseDetails="
				+ fhAnyDiseaseDetails + ", fhEtliPoliciesYn=" + fhEtliPoliciesYn + ", familyHistory=" + familyHistory
				+ ", appInsuranceAvailableYn=" + appInsuranceAvailableYn + ", appInsuranceName=" + appInsuranceName
				+ ", appInsuranceReason=" + appInsuranceReason + ", appInsuranceDate=" + appInsuranceDate
				+ ", appDisCiBenefitsYn=" + appDisCiBenefitsYn + ", appDisCiName=" + appDisCiName + ", appDisCiReason="
				+ appDisCiReason + ", appDisCiDate=" + appDisCiDate + ", detailsInsPolicyAvailableYn="
				+ detailsInsPolicyAvailableYn + ", ihSfRejectedYn=" + ihSfRejectedYn + ", ihSfRejectedReason="
				+ ihSfRejectedReason + ", ihSfSpecialRatePolicyYn=" + ihSfSpecialRatePolicyYn + ", ePolicyYn="
				+ ePolicyYn + ", insuranceHistory=" + insuranceHistory + ", lifeStyleDetailsId=" + lifeStyleDetailsId
				+ ", heightCms=" + heightCms + ", heightFeet=" + heightFeet + ", heightInches=" + heightInches
				+ ", weightKg=" + weightKg + ", weightVariationYn=" + weightVariationYn + ", variationType="
				+ variationType + ", variationWeight=" + variationWeight + ", weightVariationReasonDataId="
				+ weightVariationReasonDataId + ", qTravelOutsideYn=" + qTravelOutsideYn
				+ ", qTravelOutsideCountryCode=" + qTravelOutsideCountryCode + ", qTravelOutsideCountryOth="
				+ qTravelOutsideCountryOth + ", qPilotCoPilotYn=" + qPilotCoPilotYn + ", qAdventurousHobbiesYn="
				+ qAdventurousHobbiesYn + ", qSkyDivingOrParachutingYn=" + qSkyDivingOrParachutingYn
				+ ", qParaglidingOrHandglidingYn=" + qParaglidingOrHandglidingYn
				+ ", qMountaineeringOrOutdoorRockClimbingYn=" + qMountaineeringOrOutdoorRockClimbingYn
				+ ", qAnyFormOfRacingYn=" + qAnyFormOfRacingYn + ", qAnyOtherHazardousHobbyYn="
				+ qAnyOtherHazardousHobbyYn + ", qAdventurousHobbiesDetails=" + qAdventurousHobbiesDetails
				+ ", qHabitFormingDrugsYn=" + qHabitFormingDrugsYn + ", qHabitFormingDrugsDetails="
				+ qHabitFormingDrugsDetails + ", qConsumeAlcoholYn=" + qConsumeAlcoholYn + ", qConsumeAlcoholBeerQty="
				+ qConsumeAlcoholBeerQty + ", qConsumeAlcoholBeerSince=" + qConsumeAlcoholBeerSince
				+ ", qConsumeAlcoholHardLiquorQty=" + qConsumeAlcoholHardLiquorQty + ", qConsumeAlcoholHardLiquorSince="
				+ qConsumeAlcoholHardLiquorSince + ", qConsumeAlcoholWineQty=" + qConsumeAlcoholWineQty
				+ ", qConsumeAlcoholWineSince=" + qConsumeAlcoholWineSince + ", qConsumeNarcoticsYn="
				+ qConsumeNarcoticsYn + ", qConsumeNarcoticsQty=" + qConsumeNarcoticsQty + ", qConsumeNarcoticsSince="
				+ qConsumeNarcoticsSince + ", qSmokeConsumeYn=" + qSmokeConsumeYn + ", qSmokeConsumeCigarettesQty="
				+ qSmokeConsumeCigarettesQty + ", qSmokeConsumeCigarettesSince=" + qSmokeConsumeCigarettesSince
				+ ", qSmokeConsume_BidiQty=" + qSmokeConsume_BidiQty + ", qSmokeConsumeBidiSince="
				+ qSmokeConsumeBidiSince + ", qSmokeConsumeGutkaQty=" + qSmokeConsumeGutkaQty
				+ ", qSmokeConsumeGutkaSince=" + qSmokeConsumeGutkaSince + ", qSmokeConsumePaanQty="
				+ qSmokeConsumePaanQty + ", q_smoke_consume_paan_since=" + q_smoke_consume_paan_since
				+ ", q_smoke_consume_tobacco_pouch_qty=" + q_smoke_consume_tobacco_pouch_qty
				+ ", qSmokeConsumeTobaccoPouchSince=" + qSmokeConsumeTobaccoPouchSince + ", qSmokeConsumeOthers="
				+ qSmokeConsumeOthers + ", qSmokeConsumeCigarQty=" + qSmokeConsumeCigarQty
				+ ", qSmokeConsumeCigarSince=" + qSmokeConsumeCigarSince + ", qSmokeConsumeJardaQty="
				+ qSmokeConsumeJardaQty + ", qSmokeConsumeJardaSince=" + qSmokeConsumeJardaSince
				+ ", qStoppedSmokingYn=" + qStoppedSmokingYn + ", qStoppedSmokingSince=" + qStoppedSmokingSince
				+ ", qStoppedSmokingReason=" + qStoppedSmokingReason + ", qConsultedInfluenzaYn="
				+ qConsultedInfluenzaYn + ", qConsultedInfluenzaDetails=" + qConsultedInfluenzaDetails
				+ ", qHadEcgXRayYn=" + qHadEcgXRayYn + ", qHadEcgXRayDetails=" + qHadEcgXRayDetails
				+ ", qAdmittedAnyHospitalYn=" + qAdmittedAnyHospitalYn + ", qAdmittedAnyHospitalDetails="
				+ qAdmittedAnyHospitalDetails + ", qTakingAnyMedicationYn=" + qTakingAnyMedicationYn
				+ ", qTakingAnyMedicationDetails=" + qTakingAnyMedicationDetails + ", qAnyDisordeHheartYn="
				+ qAnyDisordeHheartYn + ", qAnyDisorderHeartDetails=" + qAnyDisorderHeartDetails
				+ ", qHistoryHighBloodPressureYn=" + qHistoryHighBloodPressureYn + ", qHistoryHighBloodPressureDetails="
				+ qHistoryHighBloodPressureDetails + ", qRespiratoryLungTroubleYn=" + qRespiratoryLungTroubleYn
				+ ", qRespiratoryLungTroubleDetails=" + qRespiratoryLungTroubleDetails + ", qDiabetesUrineYn="
				+ qDiabetesUrineYn + ", qDiabetesUrineDetails=" + qDiabetesUrineDetails + ", qDiseaseKidneysBladderYn="
				+ qDiseaseKidneysBladderYn + ", qDiseaseKidneysBladderDetails=" + qDiseaseKidneysBladderDetails
				+ ", qDisorderDigestiveSystemYn=" + qDisorderDigestiveSystemYn + ", qDisorderDigestiveSystemDetails="
				+ qDisorderDigestiveSystemDetails + ", qCancerEnlargedGlandYn=" + qCancerEnlargedGlandYn
				+ ", qCancerEnlargedGlandDetails=" + qCancerEnlargedGlandDetails + ", qTropicalDiseasesYn="
				+ qTropicalDiseasesYn + ", qTropicalDiseasesDetails=" + qTropicalDiseasesDetails
				+ ", qThyroidDisorderGoitreYn=" + qThyroidDisorderGoitreYn + ", qThyroidDisorderGoitreDetails="
				+ qThyroidDisorderGoitreDetails + ", qAnaemiaBleedingYn=" + qAnaemiaBleedingYn
				+ ", qAnaemiaBleedingDetails=" + qAnaemiaBleedingDetails + ", qNervousNeurologicalDisorderYn="
				+ qNervousNeurologicalDisorderYn + ", qNervousNeurologicalDisorderDetails="
				+ qNervousNeurologicalDisorderDetails + ", qEarEyeDisorderYn=" + qEarEyeDisorderYn
				+ ", qEarEyeDisorderDetails=" + qEarEyeDisorderDetails + ", qDisorderMuscleBoneYn="
				+ qDisorderMuscleBoneYn + ", qDisorderMuscleBoneDetails=" + qDisorderMuscleBoneDetails
				+ ", qTestHivAidsYn=" + qTestHivAidsYn + ", qTestHivAidsDetails=" + qTestHivAidsDetails
				+ ", qExcessiveAlcoholYn=" + qExcessiveAlcoholYn + ", qExcessiveAlcoholDetails="
				+ qExcessiveAlcoholDetails + ", qAnyOtherIllnessDisorderYn=" + qAnyOtherIllnessDisorderYn
				+ ", qAnyOtherIllnessDisorderDetails=" + qAnyOtherIllnessDisorderDetails + ", qDeformityPAbnormalityYn="
				+ qDeformityPAbnormalityYn + ", qDeformityPAbnormalityDetails=" + qDeformityPAbnormalityDetails
				+ ", qHealthSymptomsAppetiteYn=" + qHealthSymptomsAppetiteYn + ", qHealthSymptomsAppetiteDetails="
				+ qHealthSymptomsAppetiteDetails + ", qPregnantYn=" + qPregnantYn + ", qPregnantWeeks=" + qPregnantWeeks
				+ ", qDisorderFemaleOrganYn=" + qDisorderFemaleOrganYn + ", qDisorderFemaleOrganDetails="
				+ qDisorderFemaleOrganDetails + ", qHospitalizedConditionYn=" + qHospitalizedConditionYn
				+ ", qHospitalizationDate=" + qHospitalizationDate + ", qFullyRecoveredMedicationsYn="
				+ qFullyRecoveredMedicationsYn + ", qFullyRecoveredMedicationsDetails="
				+ qFullyRecoveredMedicationsDetails + ", qHealthCareCovidPatientsYn=" + qHealthCareCovidPatientsYn
				+ ", qCovidTmDiabetesYn=" + qCovidTmDiabetesYn + ", qCovidTmCoronaryYn=" + qCovidTmCoronaryYn
				+ ", txnId=" + txnId + ", closeContactYn=" + closeContactYn + ", closeContactDetails="
				+ closeContactDetails + ", servingNoticeOfQuarantineYn=" + servingNoticeOfQuarantineYn
				+ ", servingNoticeOfQuarantineDetails=" + servingNoticeOfQuarantineDetails + ", advisedToTestYn="
				+ advisedToTestYn + ", advisedToTestDetails=" + advisedToTestDetails + ", testedPositiveYn="
				+ testedPositiveYn + ", testedPositiveDetails=" + testedPositiveDetails + ", experiencedSymptomsYn="
				+ experiencedSymptomsYn + ", experiencedSymptomsDetails=" + experiencedSymptomsDetails
				+ ", healthcareProfessionalsYn=" + healthcareProfessionalsYn + ", healthcareProfessionalsDetails="
				+ healthcareProfessionalsDetails + ", travelIsNriYn=" + travelIsNriYn + ", travelNriCountry="
				+ travelNriCountry + ", travelNriCity=" + travelNriCity + ", travelNriDateOfTravel="
				+ travelNriDateOfTravel + ", travelNriIntendedDuration=" + travelNriIntendedDuration
				+ ", travelTravelAbroadPast14DaysYn=" + travelTravelAbroadPast14DaysYn
				+ ", travelTravelAbroadPast14DaysCountry=" + travelTravelAbroadPast14DaysCountry
				+ ", travelTravelAbroadPast14DaysCity=" + travelTravelAbroadPast14DaysCity
				+ ", travelTravelAbroadPast14DaysDateArrived=" + travelTravelAbroadPast14DaysDateArrived
				+ ", travelTravelAbroadPast14DaysDateDeparted=" + travelTravelAbroadPast14DaysDateDeparted
				+ ", travelTravelAbroadNext3MonthYn=" + travelTravelAbroadNext3MonthYn
				+ ", travelTravelAbroadNext3MonthCountry=" + travelTravelAbroadNext3MonthCountry
				+ ", travelTravelAbroadNext3MonthCity=" + travelTravelAbroadNext3MonthCity
				+ ", travelTravelAbroadNext3MonthDateOfTravel=" + travelTravelAbroadNext3MonthDateOfTravel
				+ ", travelTravelAbroadNext3MonthIntendedDuration=" + travelTravelAbroadNext3MonthIntendedDuration
				+ ", covidVaccinatedYn=" + covidVaccinatedYn + ", covidVaccinatedFirstDoseDate="
				+ covidVaccinatedFirstDoseDate + ", covidVaccinatedSecondDoseDate=" + covidVaccinatedSecondDoseDate
				+ ", covidVaccinatedName=" + covidVaccinatedName + ", covidVaccinatedAdverseReactionYn="
				+ covidVaccinatedAdverseReactionYn + ", covidVaccinatedAdverseReactionTreatmentTaken="
				+ covidVaccinatedAdverseReactionTreatmentTaken + ", completeRecoveryDate=" + completeRecoveryDate
				+ ", covidFormPath=" + covidFormPath + ", dailyDutiesDetails=" + dailyDutiesDetails
				+ ", hospitalizedHomeQuarantine=" + hospitalizedHomeQuarantine + ", covidComplicationYn="
				+ covidComplicationYn + ", covidVaccinatedBoosterDate=" + covidVaccinatedBoosterDate + ", covidDetails="
				+ covidDetails + ", diagnosisDetails=" + diagnosisDetails + ", diagnosisDate=" + diagnosisDate + "]";
	}

}
