package in.edelweiss.proposal.form.pf.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CovidQuestionDetails {

	@JsonProperty("la_details")
	private LaPrSpCovidQuestionDetalis laDetails;

	@JsonProperty("proposer_details")
	private LaPrSpCovidQuestionDetalis proposerDetails;

	@JsonProperty("spouse_details")
	private LaPrSpCovidQuestionDetalis spouseDetails;

	public LaPrSpCovidQuestionDetalis getLaDetails() {
		return laDetails;
	}

	public void setLaDetails(LaPrSpCovidQuestionDetalis laDetails) {
		this.laDetails = laDetails;
	}

	public LaPrSpCovidQuestionDetalis getProposerDetails() {
		return proposerDetails;
	}

	public void setProposerDetails(LaPrSpCovidQuestionDetalis proposerDetails) {
		this.proposerDetails = proposerDetails;
	}

	public LaPrSpCovidQuestionDetalis getSpouseDetails() {
		return spouseDetails;
	}

	public void setSpouseDetails(LaPrSpCovidQuestionDetalis spouseDetails) {
		this.spouseDetails = spouseDetails;
	}

	@Override
	public String toString() {
		return "CovidQuestionDetails [laDetails=" + laDetails + ", proposerDetails=" + proposerDetails
				+ ", spouseDetails=" + spouseDetails + "]";
	}

	public class LaPrSpCovidQuestionDetalis {

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

		public void setTravelTravelAbroadNext3MonthIntendedDuration(
				String travelTravelAbroadNext3MonthIntendedDuration) {
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

		public void setCovidVaccinatedAdverseReactionTreatmentTaken(
				String covidVaccinatedAdverseReactionTreatmentTaken) {
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
			return "LaPrSpCovidQuestionDetalis [txnId=" + txnId + ", closeContactYn=" + closeContactYn
					+ ", closeContactDetails=" + closeContactDetails + ", servingNoticeOfQuarantineYn="
					+ servingNoticeOfQuarantineYn + ", servingNoticeOfQuarantineDetails="
					+ servingNoticeOfQuarantineDetails + ", advisedToTestYn=" + advisedToTestYn
					+ ", advisedToTestDetails=" + advisedToTestDetails + ", testedPositiveYn=" + testedPositiveYn
					+ ", testedPositiveDetails=" + testedPositiveDetails + ", experiencedSymptomsYn="
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
					+ covidComplicationYn + ", covidVaccinatedBoosterDate=" + covidVaccinatedBoosterDate
					+ ", covidDetails=" + covidDetails + ", diagnosisDetails=" + diagnosisDetails + ", diagnosisDate="
					+ diagnosisDate + "]";
		}

	}

}
