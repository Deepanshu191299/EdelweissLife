package in.edelweiss.tokio.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerInvestmentDetails {

	@JsonProperty("incomePayoutTime")
	private String incomePayoutTime;
	
	@JsonProperty("incomePayoutType")
	private String incomePayoutType;
	
	@JsonProperty("payoutIncreasingPercentage")
	private String payoutIncreasingPercentage;
	
	@JsonProperty("annualPrem2")
	private String annualPrem2;
	
	
	public String getAnnualPrem2() {
		return annualPrem2;
	}

	public void setAnnualPrem2(String annualPrem2) {
		this.annualPrem2 = annualPrem2;
	}

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

	@JsonProperty("sumAssuredMultiple")
	private String sumAssuredMultiple;
	
	@JsonProperty("externalReferenceCode")
	private String externalReferenceCode;

	@JsonProperty("id")
	private Long id;

	@JsonProperty("createDate")
	private String createDate;

	@JsonProperty("edelweissEmployee")
	private String edelweissEmployee;

	@JsonProperty("familyIncomeBenefits")
	private String familyIncomeBenefits;

	@JsonProperty("incomeDuration")
	private String incomeDuration;

	@JsonProperty("incomeFor")
	private String incomeFor;

	@JsonProperty("incomePayoutFrequency")
	private String incomePayoutFrequency;

	@JsonProperty("incomeStartPoint")
	private String incomeStartPoint;

	@JsonProperty("incomeStartYear")
	private String incomeStartYear;

	@JsonProperty("incomeType")
	private String incomeType;

	@JsonProperty("investingFor")
	private String investingFor;

	@JsonProperty("investmentAmount")
	private String investmentAmount;

	@JsonProperty("investmentStrategy")
	private String investmentStrategy;

	@JsonProperty("leadId")
	private String leadId;

	@JsonProperty("lumpSumBenefits")
	private String lumpSumBenefits;

	@JsonProperty("modifiedDate")
	private String modifiedDate;

	@JsonProperty("paymentFrequency")
	private String paymentFrequency;

	@JsonProperty("payoutOption")
	private String payoutOption;

	@JsonProperty("payoutPeriod")
	private String payoutPeriod;

	@JsonProperty("policyOption")
	private String policyOption;

	@JsonProperty("policyTerm")
	private String policyTerm;

	@JsonProperty("premiumPayingTerm")
	private String premiumPayingTerm;

	@JsonProperty("productId")
	private String productId;

	@JsonProperty("rate")
	private String rate;

	@JsonProperty("riderId")
	private String riderId;

	@JsonProperty("systematicWithdrawalPlan")
	private String systematicWithdrawalPlan;

	@JsonProperty("totalReturns")
	private String totalReturns;

	@JsonProperty("projectedReturnsData")
	private String projectedReturnsData;

	@JsonProperty("r_customerInvestmentDetailsRel_c_customerEnquiryId")
	private String rCustomerInvestmentDetailsRelCCustomerEnquiryId;

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

	@JsonProperty("planOption")
	private String planOption;

	@JsonProperty("lifeCoverContinuationBenefit")
	private String lifeCoverContinuationBenefit;

	@JsonProperty("lifeCover")
	private String lifeCover;

	@JsonProperty("incomePeriod")
	private String incomePeriod;

	@JsonProperty("incomePercentage")
	private String incomePercentage;

	@JsonProperty("premiumBreakBenefit")
	private String premiumBreakBenefit;
	
	@JsonProperty("gst")
	private String gst;

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

	public String getExternalReferenceCode() {
		return externalReferenceCode;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		this.externalReferenceCode = externalReferenceCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getEdelweissEmployee() {
		return edelweissEmployee;
	}

	public void setEdelweissEmployee(String edelweissEmployee) {
		this.edelweissEmployee = edelweissEmployee;
	}

	public String getFamilyIncomeBenefits() {
		return familyIncomeBenefits;
	}

	public void setFamilyIncomeBenefits(String familyIncomeBenefits) {
		this.familyIncomeBenefits = familyIncomeBenefits;
	}

	public String getIncomeDuration() {
		return incomeDuration;
	}

	public void setIncomeDuration(String incomeDuration) {
		this.incomeDuration = incomeDuration;
	}

	public String getIncomeFor() {
		return incomeFor;
	}

	public void setIncomeFor(String incomeFor) {
		this.incomeFor = incomeFor;
	}

	public String getIncomePayoutFrequency() {
		return incomePayoutFrequency;
	}

	public void setIncomePayoutFrequency(String incomePayoutFrequency) {
		this.incomePayoutFrequency = incomePayoutFrequency;
	}

	public String getIncomeStartPoint() {
		return incomeStartPoint;
	}

	public void setIncomeStartPoint(String incomeStartPoint) {
		this.incomeStartPoint = incomeStartPoint;
	}

	public String getIncomeStartYear() {
		return incomeStartYear;
	}

	public void setIncomeStartYear(String incomeStartYear) {
		this.incomeStartYear = incomeStartYear;
	}

	public String getIncomeType() {
		return incomeType;
	}

	public void setIncomeType(String incomeType) {
		this.incomeType = incomeType;
	}

	public String getInvestingFor() {
		return investingFor;
	}

	public void setInvestingFor(String investingFor) {
		this.investingFor = investingFor;
	}

	public String getInvestmentAmount() {
		return investmentAmount;
	}

	public void setInvestmentAmount(String investmentAmount) {
		this.investmentAmount = investmentAmount;
	}

	public String getInvestmentStrategy() {
		return investmentStrategy;
	}

	public void setInvestmentStrategy(String investmentStrategy) {
		this.investmentStrategy = investmentStrategy;
	}

	public String getLeadId() {
		return leadId;
	}

	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}

	public String getLumpSumBenefits() {
		return lumpSumBenefits;
	}

	public void setLumpSumBenefits(String lumpSumBenefits) {
		this.lumpSumBenefits = lumpSumBenefits;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getPaymentFrequency() {
		return paymentFrequency;
	}

	public void setPaymentFrequency(String paymentFrequency) {
		this.paymentFrequency = paymentFrequency;
	}

	public String getPayoutOption() {
		return payoutOption;
	}

	public void setPayoutOption(String payoutOption) {
		this.payoutOption = payoutOption;
	}

	public String getPayoutPeriod() {
		return payoutPeriod;
	}

	public void setPayoutPeriod(String payoutPeriod) {
		this.payoutPeriod = payoutPeriod;
	}

	public String getPolicyOption() {
		return policyOption;
	}

	public void setPolicyOption(String policyOption) {
		this.policyOption = policyOption;
	}

	public String getPolicyTerm() {
		return policyTerm;
	}

	public void setPolicyTerm(String policyTerm) {
		this.policyTerm = policyTerm;
	}

	public String getPremiumPayingTerm() {
		return premiumPayingTerm;
	}

	public void setPremiumPayingTerm(String premiumPayingTerm) {
		this.premiumPayingTerm = premiumPayingTerm;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getRiderId() {
		return riderId;
	}

	public void setRiderId(String riderId) {
		this.riderId = riderId;
	}

	public String getSystematicWithdrawalPlan() {
		return systematicWithdrawalPlan;
	}

	public void setSystematicWithdrawalPlan(String systematicWithdrawalPlan) {
		this.systematicWithdrawalPlan = systematicWithdrawalPlan;
	}

	public String getTotalReturns() {
		return totalReturns;
	}

	public void setTotalReturns(String totalReturns) {
		this.totalReturns = totalReturns;
	}

	public String getProjectedReturnsData() {
		return projectedReturnsData;
	}

	public void setProjectedReturnsData(String projectedReturnsData) {
		this.projectedReturnsData = projectedReturnsData;
	}

	public String getrCustomerInvestmentDetailsRelCCustomerEnquiryId() {
		return rCustomerInvestmentDetailsRelCCustomerEnquiryId;
	}

	public void setrCustomerInvestmentDetailsRelCCustomerEnquiryId(
			String rCustomerInvestmentDetailsRelCCustomerEnquiryId) {
		this.rCustomerInvestmentDetailsRelCCustomerEnquiryId = rCustomerInvestmentDetailsRelCCustomerEnquiryId;
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

	public String getPlanOption() {
		return planOption;
	}

	public void setPlanOption(String planOption) {
		this.planOption = planOption;
	}

	public String getLifeCoverContinuationBenefit() {
		return lifeCoverContinuationBenefit;
	}

	public void setLifeCoverContinuationBenefit(String lifeCoverContinuationBenefit) {
		this.lifeCoverContinuationBenefit = lifeCoverContinuationBenefit;
	}

	public String getLifeCover() {
		return lifeCover;
	}

	public void setLifeCover(String lifeCover) {
		this.lifeCover = lifeCover;
	}

	public String getIncomePeriod() {
		return incomePeriod;
	}

	public void setIncomePeriod(String incomePeriod) {
		this.incomePeriod = incomePeriod;
	}

	public String getIncomePercentage() {
		return incomePercentage;
	}

	public void setIncomePercentage(String incomePercentage) {
		this.incomePercentage = incomePercentage;
	}

	public String getPremiumBreakBenefit() {
		return premiumBreakBenefit;
	}

	public void setPremiumBreakBenefit(String premiumBreakBenefit) {
		this.premiumBreakBenefit = premiumBreakBenefit;
	}

	public String getGst() {
		return gst;
	}

	public void setGst(String gst) {
		this.gst = gst;
	}

	@Override
	public String toString() {
		return "CustomerInvestmentDetails [incomePayoutTime=" + incomePayoutTime + ", incomePayoutType="
				+ incomePayoutType + ", payoutIncreasingPercentage=" + payoutIncreasingPercentage + ", annualPrem2="
				+ annualPrem2 + ", sumAssuredMultiple=" + sumAssuredMultiple + ", externalReferenceCode="
				+ externalReferenceCode + ", id=" + id + ", createDate=" + createDate + ", edelweissEmployee="
				+ edelweissEmployee + ", familyIncomeBenefits=" + familyIncomeBenefits + ", incomeDuration="
				+ incomeDuration + ", incomeFor=" + incomeFor + ", incomePayoutFrequency=" + incomePayoutFrequency
				+ ", incomeStartPoint=" + incomeStartPoint + ", incomeStartYear=" + incomeStartYear + ", incomeType="
				+ incomeType + ", investingFor=" + investingFor + ", investmentAmount=" + investmentAmount
				+ ", investmentStrategy=" + investmentStrategy + ", leadId=" + leadId + ", lumpSumBenefits="
				+ lumpSumBenefits + ", modifiedDate=" + modifiedDate + ", paymentFrequency=" + paymentFrequency
				+ ", payoutOption=" + payoutOption + ", payoutPeriod=" + payoutPeriod + ", policyOption=" + policyOption
				+ ", policyTerm=" + policyTerm + ", premiumPayingTerm=" + premiumPayingTerm + ", productId=" + productId
				+ ", rate=" + rate + ", riderId=" + riderId + ", systematicWithdrawalPlan=" + systematicWithdrawalPlan
				+ ", totalReturns=" + totalReturns + ", projectedReturnsData=" + projectedReturnsData
				+ ", rCustomerInvestmentDetailsRelCCustomerEnquiryId=" + rCustomerInvestmentDetailsRelCCustomerEnquiryId
				+ ", fundValuetobeWithdrawn=" + fundValuetobeWithdrawn + ", policyYearFromWhichSWPPayable="
				+ policyYearFromWhichSWPPayable + ", swpFrequency=" + swpFrequency + ", basePremiumAmount="
				+ basePremiumAmount + ", premiumAmount=" + premiumAmount + ", maturityAge=" + maturityAge
				+ ", incomeOption=" + incomeOption + ", guaranteedIncomeType=" + guaranteedIncomeType + ", planOption="
				+ planOption + ", lifeCoverContinuationBenefit=" + lifeCoverContinuationBenefit + ", lifeCover="
				+ lifeCover + ", incomePeriod=" + incomePeriod + ", incomePercentage=" + incomePercentage
				+ ", premiumBreakBenefit=" + premiumBreakBenefit + ", gst=" + gst + "]";
	}

	
}
