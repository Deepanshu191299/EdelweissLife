package in.edelweiss.proposal.form.pf.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LaPrSpMedicalQuestionDetails {

	@JsonProperty("application_number")
	private int applicationNumber;

	@JsonProperty("med_q_details_row_id")
	private int medQDetailsRowId;

	@JsonProperty("disease_name")
	private String diseaseName;

	@JsonProperty("diagnosis_date")
	private String diagnosisDate;

	@JsonProperty("treatment_details")
	private String treatmentDetails;

	@JsonProperty("question_no")
	private String questionNo;

	@JsonProperty("dosage_details")
	private String dosageDetails;

	@JsonProperty("doctor_name")
	private String doctorName;

	@JsonProperty("further_test_date")
	private String furtherTestDate;

	@JsonProperty("any_complications")
	private String anyComplications;

	@JsonProperty("additional_remarks")
	private String additionalRemarks;

	
	public int getApplicationNumber() {
		return applicationNumber;
	}


	public void setApplicationNumber(int applicationNumber) {
		this.applicationNumber = applicationNumber;
	}


	public int getMedQDetailsRowId() {
		return medQDetailsRowId;
	}


	public void setMedQDetailsRowId(int medQDetailsRowId) {
		this.medQDetailsRowId = medQDetailsRowId;
	}


	public String getDiseaseName() {
		return diseaseName;
	}


	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}


	public String getDiagnosisDate() {
		return diagnosisDate;
	}


	public void setDiagnosisDate(String diagnosisDate) {
		this.diagnosisDate = diagnosisDate;
	}


	public String getTreatmentDetails() {
		return treatmentDetails;
	}


	public void setTreatmentDetails(String treatmentDetails) {
		this.treatmentDetails = treatmentDetails;
	}


	public String getQuestionNo() {
		return questionNo;
	}


	public void setQuestionNo(String questionNo) {
		this.questionNo = questionNo;
	}


	public String getDosageDetails() {
		return dosageDetails;
	}


	public void setDosageDetails(String dosageDetails) {
		this.dosageDetails = dosageDetails;
	}


	public String getDoctorName() {
		return doctorName;
	}


	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}


	public String getFurtherTestDate() {
		return furtherTestDate;
	}


	public void setFurtherTestDate(String furtherTestDate) {
		this.furtherTestDate = furtherTestDate;
	}


	public String getAnyComplications() {
		return anyComplications;
	}


	public void setAnyComplications(String anyComplications) {
		this.anyComplications = anyComplications;
	}


	public String getAdditionalRemarks() {
		return additionalRemarks;
	}


	public void setAdditionalRemarks(String additionalRemarks) {
		this.additionalRemarks = additionalRemarks;
	}


	@Override
	public String toString() {
		return "LaPrSpMedicalQuestionDetails [applicationNumber=" + applicationNumber + ", medQDetailsRowId="
				+ medQDetailsRowId + ", diseaseName=" + diseaseName + ", diagnosisDate=" + diagnosisDate
				+ ", treatmentDetails=" + treatmentDetails + ", questionNo=" + questionNo + ", dosageDetails="
				+ dosageDetails + ", doctorName=" + doctorName + ", furtherTestDate=" + furtherTestDate
				+ ", anyComplications=" + anyComplications + ", additionalRemarks=" + additionalRemarks + "]";
	}

}
