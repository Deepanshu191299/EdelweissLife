package in.edelweiss.proposal.form.pf.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LifeStyleDetails {

	@JsonProperty("la_details")
	private LaPrSpLifeStyleDetails laDetails;

	@JsonProperty("proposer_details")
	private LaPrSpLifeStyleDetails proposerDetails;

	@JsonProperty("spouse_details")
	private LaPrSpLifeStyleDetails spouseDetails;

	public LaPrSpLifeStyleDetails getLaDetails() {
		return laDetails;
	}

	public void setLaDetails(LaPrSpLifeStyleDetails laDetails) {
		this.laDetails = laDetails;
	}

	public LaPrSpLifeStyleDetails getProposerDetails() {
		return proposerDetails;
	}

	public void setProposerDetails(LaPrSpLifeStyleDetails proposerDetails) {
		this.proposerDetails = proposerDetails;
	}

	public LaPrSpLifeStyleDetails getSpouseDetails() {
		return spouseDetails;
	}

	public void setSpouseDetails(LaPrSpLifeStyleDetails spouseDetails) {
		this.spouseDetails = spouseDetails;
	}

	@Override
	public String toString() {
		return "LifeStyleDetails [laDetails=" + laDetails + ", proposerDetails=" + proposerDetails + ", spouseDetails="
				+ spouseDetails + "]";
	}

	public class LaPrSpLifeStyleDetails {

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

		@Override
		public String toString() {
			return "LaPrSpLifeStyleDetails [lifeStyleDetailsId=" + lifeStyleDetailsId + ", heightCms=" + heightCms
					+ ", heightFeet=" + heightFeet + ", heightInches=" + heightInches + ", weightKg=" + weightKg
					+ ", weightVariationYn=" + weightVariationYn + ", variationType=" + variationType
					+ ", variationWeight=" + variationWeight + ", weightVariationReasonDataId="
					+ weightVariationReasonDataId + ", qTravelOutsideYn=" + qTravelOutsideYn
					+ ", qTravelOutsideCountryCode=" + qTravelOutsideCountryCode + ", qTravelOutsideCountryOth="
					+ qTravelOutsideCountryOth + ", qPilotCoPilotYn=" + qPilotCoPilotYn + ", qAdventurousHobbiesYn="
					+ qAdventurousHobbiesYn + ", qSkyDivingOrParachutingYn=" + qSkyDivingOrParachutingYn
					+ ", qParaglidingOrHandglidingYn=" + qParaglidingOrHandglidingYn
					+ ", qMountaineeringOrOutdoorRockClimbingYn=" + qMountaineeringOrOutdoorRockClimbingYn
					+ ", qAnyFormOfRacingYn=" + qAnyFormOfRacingYn + ", qAnyOtherHazardousHobbyYn="
					+ qAnyOtherHazardousHobbyYn + ", qAdventurousHobbiesDetails=" + qAdventurousHobbiesDetails
					+ ", qHabitFormingDrugsYn=" + qHabitFormingDrugsYn + ", qHabitFormingDrugsDetails="
					+ qHabitFormingDrugsDetails + ", qConsumeAlcoholYn=" + qConsumeAlcoholYn
					+ ", qConsumeAlcoholBeerQty=" + qConsumeAlcoholBeerQty + ", qConsumeAlcoholBeerSince="
					+ qConsumeAlcoholBeerSince + ", qConsumeAlcoholHardLiquorQty=" + qConsumeAlcoholHardLiquorQty
					+ ", qConsumeAlcoholHardLiquorSince=" + qConsumeAlcoholHardLiquorSince + ", qConsumeAlcoholWineQty="
					+ qConsumeAlcoholWineQty + ", qConsumeAlcoholWineSince=" + qConsumeAlcoholWineSince
					+ ", qConsumeNarcoticsYn=" + qConsumeNarcoticsYn + ", qConsumeNarcoticsQty=" + qConsumeNarcoticsQty
					+ ", qConsumeNarcoticsSince=" + qConsumeNarcoticsSince + ", qSmokeConsumeYn=" + qSmokeConsumeYn
					+ ", qSmokeConsumeCigarettesQty=" + qSmokeConsumeCigarettesQty + ", qSmokeConsumeCigarettesSince="
					+ qSmokeConsumeCigarettesSince + ", qSmokeConsume_BidiQty=" + qSmokeConsume_BidiQty
					+ ", qSmokeConsumeBidiSince=" + qSmokeConsumeBidiSince + ", qSmokeConsumeGutkaQty="
					+ qSmokeConsumeGutkaQty + ", qSmokeConsumeGutkaSince=" + qSmokeConsumeGutkaSince
					+ ", qSmokeConsumePaanQty=" + qSmokeConsumePaanQty + ", q_smoke_consume_paan_since="
					+ q_smoke_consume_paan_since + ", q_smoke_consume_tobacco_pouch_qty="
					+ q_smoke_consume_tobacco_pouch_qty + ", qSmokeConsumeTobaccoPouchSince="
					+ qSmokeConsumeTobaccoPouchSince + ", qSmokeConsumeOthers=" + qSmokeConsumeOthers
					+ ", qSmokeConsumeCigarQty=" + qSmokeConsumeCigarQty + ", qSmokeConsumeCigarSince="
					+ qSmokeConsumeCigarSince + ", qSmokeConsumeJardaQty=" + qSmokeConsumeJardaQty
					+ ", qSmokeConsumeJardaSince=" + qSmokeConsumeJardaSince + ", qStoppedSmokingYn="
					+ qStoppedSmokingYn + ", qStoppedSmokingSince=" + qStoppedSmokingSince + ", qStoppedSmokingReason="
					+ qStoppedSmokingReason + ", qConsultedInfluenzaYn=" + qConsultedInfluenzaYn
					+ ", qConsultedInfluenzaDetails=" + qConsultedInfluenzaDetails + ", qHadEcgXRayYn=" + qHadEcgXRayYn
					+ ", qHadEcgXRayDetails=" + qHadEcgXRayDetails + ", qAdmittedAnyHospitalYn="
					+ qAdmittedAnyHospitalYn + ", qAdmittedAnyHospitalDetails=" + qAdmittedAnyHospitalDetails
					+ ", qTakingAnyMedicationYn=" + qTakingAnyMedicationYn + ", qTakingAnyMedicationDetails="
					+ qTakingAnyMedicationDetails + ", qAnyDisordeHheartYn=" + qAnyDisordeHheartYn
					+ ", qAnyDisorderHeartDetails=" + qAnyDisorderHeartDetails + ", qHistoryHighBloodPressureYn="
					+ qHistoryHighBloodPressureYn + ", qHistoryHighBloodPressureDetails="
					+ qHistoryHighBloodPressureDetails + ", qRespiratoryLungTroubleYn=" + qRespiratoryLungTroubleYn
					+ ", qRespiratoryLungTroubleDetails=" + qRespiratoryLungTroubleDetails + ", qDiabetesUrineYn="
					+ qDiabetesUrineYn + ", qDiabetesUrineDetails=" + qDiabetesUrineDetails
					+ ", qDiseaseKidneysBladderYn=" + qDiseaseKidneysBladderYn + ", qDiseaseKidneysBladderDetails="
					+ qDiseaseKidneysBladderDetails + ", qDisorderDigestiveSystemYn=" + qDisorderDigestiveSystemYn
					+ ", qDisorderDigestiveSystemDetails=" + qDisorderDigestiveSystemDetails
					+ ", qCancerEnlargedGlandYn=" + qCancerEnlargedGlandYn + ", qCancerEnlargedGlandDetails="
					+ qCancerEnlargedGlandDetails + ", qTropicalDiseasesYn=" + qTropicalDiseasesYn
					+ ", qTropicalDiseasesDetails=" + qTropicalDiseasesDetails + ", qThyroidDisorderGoitreYn="
					+ qThyroidDisorderGoitreYn + ", qThyroidDisorderGoitreDetails=" + qThyroidDisorderGoitreDetails
					+ ", qAnaemiaBleedingYn=" + qAnaemiaBleedingYn + ", qAnaemiaBleedingDetails="
					+ qAnaemiaBleedingDetails + ", qNervousNeurologicalDisorderYn=" + qNervousNeurologicalDisorderYn
					+ ", qNervousNeurologicalDisorderDetails=" + qNervousNeurologicalDisorderDetails
					+ ", qEarEyeDisorderYn=" + qEarEyeDisorderYn + ", qEarEyeDisorderDetails=" + qEarEyeDisorderDetails
					+ ", qDisorderMuscleBoneYn=" + qDisorderMuscleBoneYn + ", qDisorderMuscleBoneDetails="
					+ qDisorderMuscleBoneDetails + ", qTestHivAidsYn=" + qTestHivAidsYn + ", qTestHivAidsDetails="
					+ qTestHivAidsDetails + ", qExcessiveAlcoholYn=" + qExcessiveAlcoholYn
					+ ", qExcessiveAlcoholDetails=" + qExcessiveAlcoholDetails + ", qAnyOtherIllnessDisorderYn="
					+ qAnyOtherIllnessDisorderYn + ", qAnyOtherIllnessDisorderDetails="
					+ qAnyOtherIllnessDisorderDetails + ", qDeformityPAbnormalityYn=" + qDeformityPAbnormalityYn
					+ ", qDeformityPAbnormalityDetails=" + qDeformityPAbnormalityDetails
					+ ", qHealthSymptomsAppetiteYn=" + qHealthSymptomsAppetiteYn + ", qHealthSymptomsAppetiteDetails="
					+ qHealthSymptomsAppetiteDetails + ", qPregnantYn=" + qPregnantYn + ", qPregnantWeeks="
					+ qPregnantWeeks + ", qDisorderFemaleOrganYn=" + qDisorderFemaleOrganYn
					+ ", qDisorderFemaleOrganDetails=" + qDisorderFemaleOrganDetails + ", qHospitalizedConditionYn="
					+ qHospitalizedConditionYn + ", qHospitalizationDate=" + qHospitalizationDate
					+ ", qFullyRecoveredMedicationsYn=" + qFullyRecoveredMedicationsYn
					+ ", qFullyRecoveredMedicationsDetails=" + qFullyRecoveredMedicationsDetails
					+ ", qHealthCareCovidPatientsYn=" + qHealthCareCovidPatientsYn + ", qCovidTmDiabetesYn="
					+ qCovidTmDiabetesYn + ", qCovidTmCoronaryYn=" + qCovidTmCoronaryYn + "]";
		}

	}

}
