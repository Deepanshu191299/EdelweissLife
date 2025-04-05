package in.edelweiss.tokio.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "dateCreated", "dateModified", "externalReferenceCode", "id",
		"incomePayoutFrequency", "payoutPeriod", "policyOption", "policyTerm",
		"r_customerInvestmentDetailsRel_c_customerEnquiryId", "lumpsumBenefits", "paymentFrequency", "projectedReturnsData",
		"incomeStartPoint", "riderId", "incomeFor", "payoutOption", "rate", "incomeDuration", "payoutFrequency",
		"investingFor", "familyIncomeBenefits", "investmentStrategy", "leadId", "incomeType", "investmentAmount",
		"premiumPayingTerm", "systematicWithdrawalPlan", "totalReturns", "productCode", "eTEmployee",
		"incomeStartYear", "fundValuetobeWithdrawn", "policyYearFromWhichSWPPayable", "swpFrequency","maturityAge","incomeOption","guaranteedIncomeType" })
public class CustomerInvestmentDetailsRel {

	@JsonProperty("biJSONAndInputValidation")
	private String biJSONAndInputValidation;
	public String getBiJSONAndInputValidation() {
		return biJSONAndInputValidation;
	}

	public void setBiJSONAndInputValidation(String biJSONAndInputValidation) {
		this.biJSONAndInputValidation = biJSONAndInputValidation;
	}

	@JsonProperty("edelweissEmployee")
	private String edelweissEmployee;
	@JsonProperty("incomePayoutTime")
	private String incomePayoutTime;
	@JsonProperty("sumAssuredMultiple")
	private String sumAssuredMultiple;
	
	@JsonProperty("incomePayoutType")
	private String incomePayoutType;
	@JsonProperty("payoutIncreasingPercentage")
	private String payoutIncreasingPercentage;
	
	public String getIncomePayoutType() {
		return incomePayoutType;
	}

	public void setIncomePayoutType(String incomePayoutType) {
		this.incomePayoutType = incomePayoutType;
	}

	public String getPayoutIncreasingPercentage() {
		return payoutIncreasingPercentage;
	}

	public void setPayoutIncreasingPercentage(String payoutIncreasingPercentage) {
		this.payoutIncreasingPercentage = payoutIncreasingPercentage;
	}

	public String getEdelweissEmployee() {
		return edelweissEmployee;
	}

	public void setEdelweissEmployee(String edelweissEmployee) {
		this.edelweissEmployee = edelweissEmployee;
	}

	public String getIncomePayoutTime() {
		return incomePayoutTime;
	}

	public void setIncomePayoutTime(String incomePayoutTime) {
		this.incomePayoutTime = incomePayoutTime;
	}

	public String getSumAssuredMultiple() {
		return sumAssuredMultiple;
	}

	public void setSumAssuredMultiple(String sumAssuredMultiple) {
		this.sumAssuredMultiple = sumAssuredMultiple;
	}

	@JsonProperty("dateCreated")
	private String dateCreated;
	@JsonProperty("dateModified")
	private String dateModified;
	@JsonProperty("externalReferenceCode")
	private String externalReferenceCode;
	@JsonProperty("id")
	private Integer id;
	@JsonProperty("incomePayoutFrequency")
	private String incomePayoutFrequency;
	@JsonProperty("payoutPeriod")
	private String payoutPeriod;
	@JsonProperty("policyOption")
	private String policyOption;
	@JsonProperty("policyTerm")
	private String policyTerm;
	@JsonProperty("r_customerInvestmentDetailsRel_c_customerEnquiryId")
	private Integer rCustomerInvestmentDetailsRelCCustomerEnquiryId;
	@JsonProperty("lumpSumBenefits")
	private String lumpSumBenefits;
	@JsonProperty("paymentFrequency")
	private String paymentFrequency;
	@JsonProperty("projectedReturnsData")
	private String projectedReturnsData;
	@JsonProperty("incomeStartPoint")
	private String incomeStartPoint;
	@JsonProperty("riderId")
	private String riderId;
	@JsonProperty("incomeFor")
	private String incomeFor;
	@JsonProperty("payoutOption")
	private String payoutOption;
	@JsonProperty("rate")
	private String rate;
	@JsonProperty("incomeDuration")
	private String incomeDuration;
	@JsonProperty("payoutFrequency")
	private String payoutFrequency;
	@JsonProperty("investingFor")
	private String investingFor;
	@JsonProperty("familyIncomeBenefits")
	private String familyIncomeBenefits;
	@JsonProperty("investmentStrategy")
	private String investmentStrategy;
	@JsonProperty("leadId")
	private String leadId;
	@JsonProperty("incomeType")
	private String incomeType;
	@JsonProperty("investmentAmount")
	private String investmentAmount;
	@JsonProperty("premiumPayingTerm")
	private String premiumPayingTerm;
	@JsonProperty("systematicWithdrawalPlan")
	private String systematicWithdrawalPlan;
	@JsonProperty("totalReturns")
	private String totalReturns;
	@JsonProperty("productCode")
	private String productCode;
	@JsonProperty("eTEmployee")
	private Boolean eTEmployee;
	@JsonProperty("incomeStartYear")
	private String incomeStartYear;
	
	@JsonProperty("fundValuetobeWithdrawn")
	private String fundValuetobeWithdrawn;
	
	@JsonProperty("policyYearFromWhichSWPPayable")
	private String policyYearFromWhichSWPPayable;
	
	@JsonProperty("swpFrequency")
	private String swpFrequency;
	
	@JsonProperty("basePremiumAmount")
	private String basePremiumAmount;
	
	@JsonProperty("premiumAmount")
	private String premiumAmount;
	
	@JsonProperty("maturityAge")
	private String maturityAge;
	
	@JsonProperty("incomeOption")
	private String incomeOption;
	
	@JsonProperty("guaranteedIncomeType")
	private String guaranteedIncomeType;
	
	@JsonProperty("gst")
	private String gst;
	
	@JsonProperty("lifeCover")
	private String lifeCover;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public CustomerInvestmentDetailsRel() {
	}

	/**
	 *
	 * @param incomePayoutFrequency
	 * @param payoutPeriod
	 * @param policyOption
	 * @param policyTerm
	 * @param lumpsumBenefits
	 * @param paymentFrequency
	 * @param projectedReturnsData
	 * @param externalReferenceCode
	 * @param incomeStartPoint
	 * @param riderId
	 * @param dateCreated
	 * @param incomeFor
	 * @param payoutOption
	 * @param rate
	 * @param incomeDuration
	 * @param payoutFrequency
	 * @param id
	 * @param investingFor
	 * @param familyIncomeBenefits
	 * @param investmentStrategy
	 * @param leadId
	 * @param creator
	 * @param incomeType
	 * @param investmentAmount
	 * @param rCustomerInvestmentDetailsRelCCustomerEnquiryId
	 * @param premiumPayingTerm
	 * @param systematicWithdrawalPlan
	 * @param dateModified
	 * @param totalReturns
	 * @param productCode
	 * @param eTEmployee
	 * @param incomeStartYear
	 * @param status
	 */
	

	@JsonProperty("dateCreated")
	public String getDateCreated() {
		return dateCreated;
	}
	
	public CustomerInvestmentDetailsRel(String edelweissEmployee, String incomePayoutTime, String sumAssuredMultiple,
			String incomePayoutType, String payoutIncreasingPercentage, String dateCreated, String dateModified,
			String externalReferenceCode, Integer id, String incomePayoutFrequency, String payoutPeriod,
			String policyOption, String policyTerm, Integer rCustomerInvestmentDetailsRelCCustomerEnquiryId,
			String lumpSumBenefits, String paymentFrequency, String projectedReturnsData, String incomeStartPoint,
			String riderId, String incomeFor, String payoutOption, String rate, String incomeDuration,
			String payoutFrequency, String investingFor, String familyIncomeBenefits, String investmentStrategy,
			String leadId, String incomeType, String investmentAmount, String premiumPayingTerm,
			String systematicWithdrawalPlan, String totalReturns, String productCode, Boolean eTEmployee,
			String incomeStartYear, String fundValuetobeWithdrawn, String policyYearFromWhichSWPPayable,
			String swpFrequency, String basePremiumAmount, String premiumAmount, String maturityAge,
			String incomeOption, String guaranteedIncomeType, String gst, String lifeCover) {
		super();
		this.edelweissEmployee = edelweissEmployee;
		this.incomePayoutTime = incomePayoutTime;
		this.sumAssuredMultiple = sumAssuredMultiple;
		this.incomePayoutType = incomePayoutType;
		this.payoutIncreasingPercentage = payoutIncreasingPercentage;
		this.dateCreated = dateCreated;
		this.dateModified = dateModified;
		this.externalReferenceCode = externalReferenceCode;
		this.id = id;
		this.incomePayoutFrequency = incomePayoutFrequency;
		this.payoutPeriod = payoutPeriod;
		this.policyOption = policyOption;
		this.policyTerm = policyTerm;
		this.rCustomerInvestmentDetailsRelCCustomerEnquiryId = rCustomerInvestmentDetailsRelCCustomerEnquiryId;
		this.lumpSumBenefits = lumpSumBenefits;
		this.paymentFrequency = paymentFrequency;
		this.projectedReturnsData = projectedReturnsData;
		this.incomeStartPoint = incomeStartPoint;
		this.riderId = riderId;
		this.incomeFor = incomeFor;
		this.payoutOption = payoutOption;
		this.rate = rate;
		this.incomeDuration = incomeDuration;
		this.payoutFrequency = payoutFrequency;
		this.investingFor = investingFor;
		this.familyIncomeBenefits = familyIncomeBenefits;
		this.investmentStrategy = investmentStrategy;
		this.leadId = leadId;
		this.incomeType = incomeType;
		this.investmentAmount = investmentAmount;
		this.premiumPayingTerm = premiumPayingTerm;
		this.systematicWithdrawalPlan = systematicWithdrawalPlan;
		this.totalReturns = totalReturns;
		this.productCode = productCode;
		this.eTEmployee = eTEmployee;
		this.incomeStartYear = incomeStartYear;
		this.fundValuetobeWithdrawn = fundValuetobeWithdrawn;
		this.policyYearFromWhichSWPPayable = policyYearFromWhichSWPPayable;
		this.swpFrequency = swpFrequency;
		this.basePremiumAmount = basePremiumAmount;
		this.premiumAmount = premiumAmount;
		this.maturityAge = maturityAge;
		this.incomeOption = incomeOption;
		this.guaranteedIncomeType = guaranteedIncomeType;
		this.gst = gst;
		this.lifeCover = lifeCover;
	}

	@JsonProperty("dateCreated")
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	@JsonProperty("dateModified")
	public String getDateModified() {
		return dateModified;
	}

	@JsonProperty("dateModified")
	public void setDateModified(String dateModified) {
		this.dateModified = dateModified;
	}

	@JsonProperty("externalReferenceCode")
	public String getExternalReferenceCode() {
		return externalReferenceCode;
	}

	@JsonProperty("externalReferenceCode")
	public void setExternalReferenceCode(String externalReferenceCode) {
		this.externalReferenceCode = externalReferenceCode;
	}

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}


	@JsonProperty("incomePayoutFrequency")
	public String getIncomePayoutFrequency() {
		return incomePayoutFrequency;
	}

	@JsonProperty("incomePayoutFrequency")
	public void setIncomePayoutFrequency(String incomePayoutFrequency) {
		this.incomePayoutFrequency = incomePayoutFrequency;
	}

	@JsonProperty("payoutPeriod")
	public String getPayoutPeriod() {
		return payoutPeriod;
	}

	@JsonProperty("payoutPeriod")
	public void setPayoutPeriod(String payoutPeriod) {
		this.payoutPeriod = payoutPeriod;
	}

	@JsonProperty("policyOption")
	public String getPolicyOption() {
		return policyOption;
	}

	@JsonProperty("policyOption")
	public void setPolicyOption(String policyOption) {
		this.policyOption = policyOption;
	}

	@JsonProperty("policyTerm")
	public String getPolicyTerm() {
		return policyTerm;
	}

	@JsonProperty("policyTerm")
	public void setPolicyTerm(String policyTerm) {
		this.policyTerm = policyTerm;
	}

	@JsonProperty("r_customerInvestmentDetailsRel_c_customerEnquiryId")
	public Integer getrCustomerInvestmentDetailsRelCCustomerEnquiryId() {
		return rCustomerInvestmentDetailsRelCCustomerEnquiryId;
	}

	@JsonProperty("r_customerInvestmentDetailsRel_c_customerEnquiryId")
	public void setrCustomerInvestmentDetailsRelCCustomerEnquiryId(
			Integer rCustomerInvestmentDetailsRelCCustomerEnquiryId) {
		this.rCustomerInvestmentDetailsRelCCustomerEnquiryId = rCustomerInvestmentDetailsRelCCustomerEnquiryId;
	}

	@JsonProperty("lumpsumBenefits")
	public String getLumpsumBenefits() {
		return lumpSumBenefits;
	}

	@JsonProperty("lumpsumBenefits")
	public void setLumpsumBenefits(String lumpsumBenefits) {
		this.lumpSumBenefits = lumpsumBenefits;
	}

	@JsonProperty("paymentFrequency")
	public String getPaymentFrequency() {
		return paymentFrequency;
	}

	@JsonProperty("paymentFrequency")
	public void setPaymentFrequency(String paymentFrequency) {
		this.paymentFrequency = paymentFrequency;
	}


	@JsonProperty("incomeStartPoint")
	public String getIncomeStartPoint() {
		return incomeStartPoint;
	}

	@JsonProperty("incomeStartPoint")
	public void setIncomeStartPoint(String incomeStartPoint) {
		this.incomeStartPoint = incomeStartPoint;
	}

	public String getRiderId() {
		return riderId;
	}

	public void setRiderId(String riderId) {
		this.riderId = riderId;
	}

	@JsonProperty("incomeFor")
	public String getIncomeFor() {
		return incomeFor;
	}

	@JsonProperty("incomeFor")
	public void setIncomeFor(String incomeFor) {
		this.incomeFor = incomeFor;
	}

	@JsonProperty("payoutOption")
	public String getPayoutOption() {
		return payoutOption;
	}

	@JsonProperty("payoutOption")
	public void setPayoutOption(String payoutOption) {
		this.payoutOption = payoutOption;
	}

	@JsonProperty("rate")
	public String getRate() {
		return rate;
	}

	@JsonProperty("rate")
	public void setRate(String rate) {
		this.rate = rate;
	}

	@JsonProperty("incomeDuration")
	public String getIncomeDuration() {
		return incomeDuration;
	}

	@JsonProperty("incomeDuration")
	public void setIncomeDuration(String incomeDuration) {
		this.incomeDuration = incomeDuration;
	}

	@JsonProperty("payoutFrequency")
	public String getPayoutFrequency() {
		return payoutFrequency;
	}

	@JsonProperty("payoutFrequency")
	public void setPayoutFrequency(String payoutFrequency) {
		this.payoutFrequency = payoutFrequency;
	}

	@JsonProperty("investingFor")
	public String getInvestingFor() {
		return investingFor;
	}

	@JsonProperty("investingFor")
	public void setInvestingFor(String investingFor) {
		this.investingFor = investingFor;
	}

	@JsonProperty("familyIncomeBenefits")
	public String getFamilyIncomeBenefits() {
		return familyIncomeBenefits;
	}

	@JsonProperty("familyIncomeBenefits")
	public void setFamilyIncomeBenefits(String familyIncomeBenefits) {
		this.familyIncomeBenefits = familyIncomeBenefits;
	}

	@JsonProperty("investmentStrategy")
	public String getInvestmentStrategy() {
		return investmentStrategy;
	}

	@JsonProperty("investmentStrategy")
	public void setInvestmentStrategy(String investmentStrategy) {
		this.investmentStrategy = investmentStrategy;
	}

	@JsonProperty("leadId")
	public String getLeadId() {
		return leadId;
	}

	@JsonProperty("leadId")
	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}

	@JsonProperty("incomeType")
	public String getIncomeType() {
		return incomeType;
	}

	@JsonProperty("incomeType")
	public void setIncomeType(String incomeType) {
		this.incomeType = incomeType;
	}

	@JsonProperty("investmentAmount")
	public String getInvestmentAmount() {
		return investmentAmount;
	}

	@JsonProperty("investmentAmount")
	public void setInvestmentAmount(String investmentAmount) {
		this.investmentAmount = investmentAmount;
	}

	@JsonProperty("premiumPayingTerm")
	public String getPremiumPayingTerm() {
		return premiumPayingTerm;
	}

	@JsonProperty("premiumPayingTerm")
	public void setPremiumPayingTerm(String premiumPayingTerm) {
		this.premiumPayingTerm = premiumPayingTerm;
	}

	@JsonProperty("systematicWithdrawalPlan")
	public String getSystematicWithdrawalPlan() {
		return systematicWithdrawalPlan;
	}

	@JsonProperty("systematicWithdrawalPlan")
	public void setSystematicWithdrawalPlan(String systematicWithdrawalPlan) {
		this.systematicWithdrawalPlan = systematicWithdrawalPlan;
	}

	@JsonProperty("productCode")
	public String getProductCode() {
		return productCode;
	}

	@JsonProperty("productCode")
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	@JsonProperty("eTEmployee")
	public Boolean geteTEmployee() {
		return eTEmployee;
	}

	@JsonProperty("eTEmployee")
	public void seteTEmployee(Boolean eTEmployee) {
		this.eTEmployee = eTEmployee;
	}

	@JsonProperty("incomeStartYear")
	public String getIncomeStartYear() {
		return incomeStartYear;
	}

	@JsonProperty("incomeStartYear")
	public void setIncomeStartYear(String incomeStartYear) {
		this.incomeStartYear = incomeStartYear;
	}

	@JsonProperty("projectedReturnsData")
	public String getProjectedReturnsData() {
		return projectedReturnsData;
	}
	
	@JsonProperty("projectedReturnsData")
	public void setProjectedReturnsData(String projectedReturnsData) {
		this.projectedReturnsData = projectedReturnsData;
	}

	@JsonProperty("totalReturns")
	public String getTotalReturns() {
		return totalReturns;
	}

	@JsonProperty("totalReturns")
	public void setTotalReturns(String totalReturns) {
		this.totalReturns = totalReturns;
	}

	public String getFundValuetobeWithdrawn() {
		return fundValuetobeWithdrawn;
	}

	public void setFundValuetobeWithdrawn(String fundValuetobeWithdrawn) {
		this.fundValuetobeWithdrawn = fundValuetobeWithdrawn;
	}

	public String getPolicyYearFromWhichSWPPayable() {
		return policyYearFromWhichSWPPayable;
	}

	public void setPolicyYearFromWhichSWPPayable(String policyYearFromWhichSWPPayable) {
		this.policyYearFromWhichSWPPayable = policyYearFromWhichSWPPayable;
	}

	public String getSwpFrequency() {
		return swpFrequency;
	}

	public void setSwpFrequency(String swpFrequency) {
		this.swpFrequency = swpFrequency;
	}

	public String getBasePremiumAmount() {
		return basePremiumAmount;
	}

	public void setBasePremiumAmount(String basePremiumAmount) {
		this.basePremiumAmount = basePremiumAmount;
	}

	public String getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(String premiumAmount) {
		this.premiumAmount = premiumAmount;
	}
	
	public String getMaturityAge() {
		return maturityAge;
	}

	public void setMaturityAge(String maturityAge) {
		this.maturityAge = maturityAge;
	}

	public String getIncomeOption() {
		return incomeOption;
	}

	public void setIncomeOption(String incomeOption) {
		this.incomeOption = incomeOption;
	}

	public String getGuaranteedIncomeType() {
		return guaranteedIncomeType;
	}

	public void setGuaranteedIncomeType(String guaranteedIncomeType) {
		this.guaranteedIncomeType = guaranteedIncomeType;
	}

	public String getLumpSumBenefits() {
		return lumpSumBenefits;
	}

	public void setLumpSumBenefits(String lumpSumBenefits) {
		this.lumpSumBenefits = lumpSumBenefits;
	}

	public String getGst() {
		return gst;
	}

	public void setGst(String gst) {
		this.gst = gst;
	}

	public String getLifeCover() {
		return lifeCover;
	}

	public void setLifeCover(String lifeCover) {
		this.lifeCover = lifeCover;
	}

	@Override
	public String toString() {
		return "CustomerInvestmentDetailsRel [biJSONAndInputValidation=" + biJSONAndInputValidation
				+ ", edelweissEmployee=" + edelweissEmployee + ", incomePayoutTime=" + incomePayoutTime
				+ ", sumAssuredMultiple=" + sumAssuredMultiple + ", incomePayoutType=" + incomePayoutType
				+ ", payoutIncreasingPercentage=" + payoutIncreasingPercentage + ", dateCreated=" + dateCreated
				+ ", dateModified=" + dateModified + ", externalReferenceCode=" + externalReferenceCode + ", id=" + id
				+ ", incomePayoutFrequency=" + incomePayoutFrequency + ", payoutPeriod=" + payoutPeriod
				+ ", policyOption=" + policyOption + ", policyTerm=" + policyTerm
				+ ", rCustomerInvestmentDetailsRelCCustomerEnquiryId=" + rCustomerInvestmentDetailsRelCCustomerEnquiryId
				+ ", lumpSumBenefits=" + lumpSumBenefits + ", paymentFrequency=" + paymentFrequency
				+ ", projectedReturnsData=" + projectedReturnsData + ", incomeStartPoint=" + incomeStartPoint
				+ ", riderId=" + riderId + ", incomeFor=" + incomeFor + ", payoutOption=" + payoutOption + ", rate="
				+ rate + ", incomeDuration=" + incomeDuration + ", payoutFrequency=" + payoutFrequency
				+ ", investingFor=" + investingFor + ", familyIncomeBenefits=" + familyIncomeBenefits
				+ ", investmentStrategy=" + investmentStrategy + ", leadId=" + leadId + ", incomeType=" + incomeType
				+ ", investmentAmount=" + investmentAmount + ", premiumPayingTerm=" + premiumPayingTerm
				+ ", systematicWithdrawalPlan=" + systematicWithdrawalPlan + ", totalReturns=" + totalReturns
				+ ", productCode=" + productCode + ", eTEmployee=" + eTEmployee + ", incomeStartYear=" + incomeStartYear
				+ ", fundValuetobeWithdrawn=" + fundValuetobeWithdrawn + ", policyYearFromWhichSWPPayable="
				+ policyYearFromWhichSWPPayable + ", swpFrequency=" + swpFrequency + ", basePremiumAmount="
				+ basePremiumAmount + ", premiumAmount=" + premiumAmount + ", maturityAge=" + maturityAge
				+ ", incomeOption=" + incomeOption + ", guaranteedIncomeType=" + guaranteedIncomeType + ", gst=" + gst
				+ ", lifeCover=" + lifeCover + "]";
	}
}
