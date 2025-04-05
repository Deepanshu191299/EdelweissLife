package in.edelweiss.proposal.form.pf.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class ResponseData {

	@JsonProperty("common_data")
	private CommonData commonData;

	@JsonProperty("personal_details")
	private PersonalDetails personalDetails;

	@JsonProperty("communication_details")
	private CommunicationDetails communicationDetails;

	@JsonProperty("employment_details")
	private EmploymentDetails employmentDetails;

	@JsonProperty("nominee_details")
	private ArrayList<NomineeDetails> nomineeDetails;

	@JsonProperty("family_history_details")
	private FamilyHistoryDetails familyHistoryDetails;

	@JsonProperty("family_details")
	private FamilyDetails familyDetails;

	@JsonProperty("medical_question_details")
	private MedicalQuestionDetails medicalQuestionDetails;

	@JsonProperty("insurance_history_details")
	private InsuranceHistoryDetails insuranceHistoryDetails;

	@JsonProperty("life_style_details")
	private LifeStyleDetails lifeStyleDetails;

	@JsonProperty("life_style_details_simple")
	private LifeStyleDetailsSimple lifeStyleDetailsSimple;

	@JsonProperty("product_details")
	private ProductDetails productDetails;

	@JsonProperty("child_product_details")
	private ArrayList<ProductDetails> childProductDetails;

	@JsonProperty("payment_details")
	private PaymentDetails paymentDetails;

	@JsonProperty("covid_question_details")
	private CovidQuestionDetails covidQuestionDetails;

	@JsonProperty("reflex_answer_details")
	private ReflexAnswerDetails reflexAnswerDetails;

	@JsonProperty("Id")
	private String id;

	@JsonProperty("Status")
	private int status;

	@JsonProperty("Code")
	private String code;

	@JsonProperty("Links")
	private String links;

	@JsonProperty("Title")
	private String title;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLinks() {
		return links;
	}

	public void setLinks(String links) {
		this.links = links;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public CommonData getCommonData() {
		return commonData;
	}

	public void setCommonData(CommonData commonData) {
		this.commonData = commonData;
	}

	public PersonalDetails getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(PersonalDetails personalDetails) {
		this.personalDetails = personalDetails;
	}

	public CommunicationDetails getCommunicationDetails() {
		return communicationDetails;
	}

	public void setCommunicationDetails(CommunicationDetails communicationDetails) {
		this.communicationDetails = communicationDetails;
	}

	public EmploymentDetails getEmploymentDetails() {
		return employmentDetails;
	}

	public void setEmploymentDetails(EmploymentDetails employmentDetails) {
		this.employmentDetails = employmentDetails;
	}

	public ArrayList<NomineeDetails> getNomineeDetails() {
		return nomineeDetails;
	}

	public void setNomineeDetails(ArrayList<NomineeDetails> nomineeDetails) {
		this.nomineeDetails = nomineeDetails;
	}

	public FamilyHistoryDetails getFamilyHistoryDetails() {
		return familyHistoryDetails;
	}

	public void setFamilyHistoryDetails(FamilyHistoryDetails familyHistoryDetails) {
		this.familyHistoryDetails = familyHistoryDetails;
	}

	public FamilyDetails getFamilyDetails() {
		return familyDetails;
	}

	public void setFamilyDetails(FamilyDetails familyDetails) {
		this.familyDetails = familyDetails;
	}

	public MedicalQuestionDetails getMedicalQuestionDetails() {
		return medicalQuestionDetails;
	}

	public void setMedicalQuestionDetails(MedicalQuestionDetails medicalQuestionDetails) {
		this.medicalQuestionDetails = medicalQuestionDetails;
	}

	public InsuranceHistoryDetails getInsuranceHistoryDetails() {
		return insuranceHistoryDetails;
	}

	public void setInsuranceHistoryDetails(InsuranceHistoryDetails insuranceHistoryDetails) {
		this.insuranceHistoryDetails = insuranceHistoryDetails;
	}

	public LifeStyleDetails getLifeStyleDetails() {
		return lifeStyleDetails;
	}

	public void setLifeStyleDetails(LifeStyleDetails lifeStyleDetails) {
		this.lifeStyleDetails = lifeStyleDetails;
	}

	public LifeStyleDetailsSimple getLifeStyleDetails_Simple() {
		return lifeStyleDetailsSimple;
	}

	public void setLifeStyleDetails_Simple(LifeStyleDetailsSimple lifeStyleDetails_Simple) {
		this.lifeStyleDetailsSimple = lifeStyleDetails_Simple;
	}

	public ProductDetails getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
	}

	public ArrayList<ProductDetails> getChildProductDetails() {
		return childProductDetails;
	}

	public void setChildProductDetails(ArrayList<ProductDetails> childProductDetails) {
		this.childProductDetails = childProductDetails;
	}

	public PaymentDetails getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(PaymentDetails paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	public CovidQuestionDetails getCovidQuestionDetails() {
		return covidQuestionDetails;
	}

	public void setCovidQuestionDetails(CovidQuestionDetails covidQuestionDetails) {
		this.covidQuestionDetails = covidQuestionDetails;
	}

	public ReflexAnswerDetails getReflexAnswerDetails() {
		return reflexAnswerDetails;
	}

	public void setReflexAnswerDetails(ReflexAnswerDetails reflexAnswerDetails) {
		this.reflexAnswerDetails = reflexAnswerDetails;
	}

	@Override
	public String toString() {
		return "Root [commonData=" + commonData + ", personalDetails=" + personalDetails + ", communicationDetails="
				+ communicationDetails + ", employmentDetails=" + employmentDetails + ", nomineeDetails="
				+ nomineeDetails + ", familyHistoryDetails=" + familyHistoryDetails + ", familyDetails=" + familyDetails
				+ ", medicalQuestionDetails=" + medicalQuestionDetails + ", insuranceHistoryDetails="
				+ insuranceHistoryDetails + ", lifeStyleDetails=" + lifeStyleDetails + ", lifeStyleDetails_Simple="
				+ lifeStyleDetailsSimple + ", productDetails=" + productDetails + ", childProductDetails="
				+ childProductDetails + ", paymentDetails=" + paymentDetails + ", covidQuestionDetails="
				+ covidQuestionDetails + ", reflexAnswerDetails=" + reflexAnswerDetails + "]";
	}

}
