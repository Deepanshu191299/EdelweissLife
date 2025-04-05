package in.edelweiss.tokio.common.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 *  Liferay Product Rel Response Parsing
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class LiferayResponseMessage {
	
	@JsonProperty("productId")
	private String productId;
	
	@JsonProperty("iNGPlanSubType")
	private String iNGPlanSubType;
	
	@JsonProperty("iNGPlanType")
	private String iNGPlanType;
	
	@JsonProperty("productStatus")
	private String productStatus;
	
	@JsonProperty("productName")
	private String productName;
	
	@JsonProperty("productCategory")
	private String productCategory;
	
	@JsonProperty("productDisplayName")
	private String productDisplayName;
	
	@JsonProperty("iNGProductCode")
	private String iNGProductCode;
	
	@JsonProperty("productPolicyOptionRel")
	private List<ProductPolicyOptionRel> productPolicyOptionRels;
	
	@JsonProperty("productFundManagementRel")
	private List<ProductFundManagementRel> productFundManagementRels;
	
	@JsonProperty("productInvestingForRel")
	private List<ProductInvestingForRel> productInvestingForRels;
	
	@JsonProperty("productPaymentOptionRel")
	private List<ProductPaymentOptionRels> productPaymentOptionRels;
	
	@JsonProperty("productDefaultValueRel")
	private List<ProductDefaultValueRel> productDefaultValueRels;
	
	@JsonProperty("productInvestmentAmountRel")
	private List<ProductInvestmentAmountRel> productInvestmentAmountRels;
	
	@JsonProperty("productPolicyForRel")
	private List<ProductPolicyForRel> productPolicyForRel;
	
	@JsonProperty("brochure")
	private Brochure brochure;
	
	@JsonProperty("productMetaDataRelationship")
	private List<ProductMetaDataRel> productMetaDataRels;
	
	@JsonProperty("familyIncomeBenefitOptions")
	private List<FamilyIncomeBenefitOptions> familyIncomeBenefitOptions;
	
	@JsonProperty("isyPtMapping")
	private List<ProductIsyandPtRelationship> productIsyandPtRelationship;
	
	@JsonProperty("productValidations")
	private List<ProductValidation> productValidations;
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class ProductPolicyOptionRel {
		
		@JsonProperty("policyOptions")
		private List<PolicyOption> policyOptions;

		public List<PolicyOption> getPolicyOptions() {
			return policyOptions;
		}

		public void setPolicyOptions(List<PolicyOption> policyOptions) {
			this.policyOptions = policyOptions;
		}

		@Override
		public String toString() {
			return "ProductPolicyOptionRel [policyOptions=" + policyOptions + "]";
		}
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class ProductFundManagementRel {
		
		@JsonProperty("fundManagementOptions")
		private List<FundManagement> fundManagementOptions;

		public List<FundManagement> getFundManagementOptions() {
			return fundManagementOptions;
		}

		public void setFundManagementOptions(List<FundManagement> fundManagementOptions) {
			this.fundManagementOptions = fundManagementOptions;
		}

		@Override
		public String toString() {
			return "ProductFundManagementRel [fundManagementOptions=" + fundManagementOptions + "]";
		}
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class ProductInvestingForRel {

		@JsonProperty("investingFors")
		private List<InvestingFor> investingFors;

		public List<InvestingFor> getInvestingFors() {
			return investingFors;
		}

		public void setInvestingFors(List<InvestingFor> investingFors) {
			this.investingFors = investingFors;
		}

		@Override
		public String toString() {
			return "ProductInvestingForRel [investingFors=" + investingFors + "]";
		}
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class FamilyIncomeBenefitOptions {

		@JsonProperty("familyIncomeBenefitOptions")
		private List<FamilyIncomeBenefitOption> FamilyIncomeBenefitOptions;

		public List<FamilyIncomeBenefitOption> getFamilyIncomeBenefitOptions() {
			return FamilyIncomeBenefitOptions;
		}

		public void setFamilyIncomeBenefitOptions(List<FamilyIncomeBenefitOption> familyIncomeBenefitOptions) {
			FamilyIncomeBenefitOptions = familyIncomeBenefitOptions;
		}

		@Override
		public String toString() {
			return "FamilyIncomeBenefitOptions []";
		}
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class ProductIsyandPtRelationship {

		@JsonProperty("planOption")
		private PlanOption planOption;
		
		@JsonProperty("policyFor")
		private PolicyFor policyFor;
		
		@JsonProperty("incomeStartYears")
		private List<IncomeStartYear> incomeStartYears;
		
		@JsonProperty("policyTerms")
		private List<PolicyTerm> policyTerms;

		public PlanOption getPlanOption() {
			return planOption;
		}

		public void setPlanOption(PlanOption planOption) {
			this.planOption = planOption;
		}

		public PolicyFor getPolicyFor() {
			return policyFor;
		}

		public void setPolicyFor(PolicyFor policyFor) {
			this.policyFor = policyFor;
		}

		public List<IncomeStartYear> getIncomeStartYears() {
			return incomeStartYears;
		}

		public void setIncomeStartYears(List<IncomeStartYear> incomeStartYears) {
			this.incomeStartYears = incomeStartYears;
		}

		public List<PolicyTerm> getPolicyTerms() {
			return policyTerms;
		}

		public void setPolicyTerms(List<PolicyTerm> policyTerms) {
			this.policyTerms = policyTerms;
		}

		@Override
		public String toString() {
			return "ProductIsyandPtRelationship [planOption=" + planOption + ", policyForData=" + policyFor
					+ ", incomeStartYears=" + incomeStartYears + ", policyTerms=" + policyTerms + "]";
		}
		
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class ProductValidation {

		@JsonProperty("minPT")
		private String minPT;
		
		@JsonProperty("maxPT")
		private String maxPT;
		
		@JsonProperty("minPPT")
		private String minPPT;
		
		@JsonProperty("maxPPT")
		private String maxPPT;
		
		@JsonProperty("minMaturityAge")
		private String minMaturityAge;
		
		@JsonProperty("maxMaturityAge")
		private String maxMaturityAge;
		
		@JsonProperty("minEntryAge")
		private String minEntryAge;
		
		@JsonProperty("maxEntryAge")
		private String maxEntryAge;

		public String getMinPT() {
			return minPT;
		}

		public void setMinPT(String minPT) {
			this.minPT = minPT;
		}

		public String getMaxPT() {
			return maxPT;
		}

		public void setMaxPT(String maxPT) {
			this.maxPT = maxPT;
		}

		public String getMinPPT() {
			return minPPT;
		}

		public void setMinPPT(String minPPT) {
			this.minPPT = minPPT;
		}

		public String getMaxPPT() {
			return maxPPT;
		}

		public void setMaxPPT(String maxPPT) {
			this.maxPPT = maxPPT;
		}

		public String getMinMaturityAge() {
			return minMaturityAge;
		}

		public void setMinMaturityAge(String minMaturityAge) {
			this.minMaturityAge = minMaturityAge;
		}

		public String getMaxMaturityAge() {
			return maxMaturityAge;
		}

		public void setMaxMaturityAge(String maxMaturityAge) {
			this.maxMaturityAge = maxMaturityAge;
		}

		public String getMinEntryAge() {
			return minEntryAge;
		}

		public void setMinEntryAge(String minEntryAge) {
			this.minEntryAge = minEntryAge;
		}

		public String getMaxEntryAge() {
			return maxEntryAge;
		}

		public void setMaxEntryAge(String maxEntryAge) {
			this.maxEntryAge = maxEntryAge;
		}

		@Override
		public String toString() {
			return "{\"minPT\":" + minPT + ", \"maxPT\":" + maxPT + ", \"minPPT\":" + minPPT + ", \"maxPPT\":" + maxPPT
					+ "" + "\"minMaturityAge\":" + minMaturityAge + ", \"maxMaturityAge\":" + maxMaturityAge
					+ ", \"minEntryAge\":" + minEntryAge + ", \"maxEntryAge\":" + maxEntryAge + "}";
		}
		
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class ProductPaymentOptionRels {
		
		@JsonProperty("paymentOptions")
		private List<PaymentOption> paymentOptions;
		
		@JsonProperty("policyOption")
		private PolicyOption policyOption;

		public List<PaymentOption> getPaymentOptions() {
			return paymentOptions;
		}

		public void setPaymentOptions(List<PaymentOption> paymentOptions) {
			this.paymentOptions = paymentOptions;
		}
		
		public PolicyOption getPolicyOption() {
			return policyOption;
		}

		public void setPolicyOption(PolicyOption policyOption) {
			this.policyOption = policyOption;
		}

		@Override
		public String toString() {
			return "ProductPaymentOptionRels [paymentOptions=" + paymentOptions + ", policyOption=" + policyOption
					+ "]";
		}
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class ProductInvestmentAmountRel {

		@JsonProperty("policyOption")
		private PolicyOption policyOption;
		
		@JsonProperty("paymentOption")
		private PaymentOption paymentOption;

		@JsonProperty("minimumInvestmentAmount")
		private String minimumInvestmentAmount;

		@JsonProperty("annualIncome")
		private AnnualIncome annualIncome;
		
		@JsonProperty("minPPT")
		private String minPPT;
		
		@JsonProperty("maxPPT")
		private String maxPPT;
		
		@JsonProperty("minPT")
		private String minPT;
		
		@JsonProperty("maxPT")
		private String maxPT;
		
		public PolicyOption getPolicyOption() {
			return policyOption;
		}

		public void setPolicyOption(PolicyOption policyOption) {
			this.policyOption = policyOption;
		}

		public PaymentOption getPaymentOption() {
			return paymentOption;
		}

		public void setPaymentOption(PaymentOption paymentOption) {
			this.paymentOption = paymentOption;
		}

		public AnnualIncome getAnnualIncome() {
			return annualIncome;
		}

		public void setAnnualIncome(AnnualIncome annualIncome) {
			this.annualIncome = annualIncome;
		}

		public String getMinimumInvestmentAmount() {
			return minimumInvestmentAmount;
		}

		public void setMinimumInvestmentAmount(String minimumInvestmentAmount) {
			this.minimumInvestmentAmount = minimumInvestmentAmount;
		}
		public String getMinPPT() {
			return minPPT;
		}

		public void setMinPPT(String minPPT) {
			this.minPPT = minPPT;
		}

		public String getMaxPPT() {
			return maxPPT;
		}

		public void setMaxPPT(String maxPPT) {
			this.maxPPT = maxPPT;
		}

		public String getMinPT() {
			return minPT;
		}

		public void setMinPT(String minPT) {
			this.minPT = minPT;
		}

		public String getMaxPT() {
			return maxPT;
		}

		public void setMaxPT(String maxPT) {
			this.maxPT = maxPT;
		}

		@Override
		public String toString() {
			return "ProductInvestmentAmountRel [policyOption=" + policyOption + ", paymentOption=" + paymentOption
					+ ", minimumInvestmentAmount=" + minimumInvestmentAmount + ", annualIncome=" + annualIncome
					+ ", minPPT=" + minPPT + ", maxPPT=" + maxPPT + ", minPT=" + minPT + ", maxPT=" + maxPT + "]";
		}
		
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class ProductPolicyForRel {

		@JsonProperty("policyOptions")
		private PolicyOption policyOption;
		
		@JsonProperty("familyIncomeBenefit")
		private FamilyIncomeBenefit familyIncomeBenefit;

		@JsonProperty("policyFor")
		private List<PolicyFor> policyFor;
		
		@JsonProperty("policyTerm")
		private PolicyTerm policyTerm;

		public PolicyOption getPolicyOption() {
			return policyOption;
		}

		public void setPolicyOption(PolicyOption policyOption) {
			this.policyOption = policyOption;
		}

		public FamilyIncomeBenefit getFamilyIncomeBenefit() {
			return familyIncomeBenefit;
		}

		public void setFamilyIncomeBenefit(FamilyIncomeBenefit familyIncomeBenefit) {
			this.familyIncomeBenefit = familyIncomeBenefit;
		}

		public List<PolicyFor> getPolicyFor() {
			return policyFor;
		}

		public void setPolicyFor(List<PolicyFor> policyFor) {
			this.policyFor = policyFor;
		}
		
		public PolicyTerm getPolicyTerm() {
			return policyTerm;
		}

		public void setPolicyTerm(PolicyTerm policyTerm) {
			this.policyTerm = policyTerm;
		}

		@Override
		public String toString() {
			return "{\"policyOption\":" + policyOption + ", \"familyIncomeBenefit\":" + familyIncomeBenefit
					+ ", \"policyFor\":" + policyFor + ", \"policyTerm\":" + policyTerm + "}";
		}
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class AnnualIncome {
		
		@JsonProperty("name")
		private String name;
		
		@JsonProperty("key")
		private String key;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		@Override
		public String toString() {
			return "AnnualIncome [name=" + name + ", annualIncomekey=" + key + "]";
		}
		
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class ProductDefaultValueRel {
		
		//GIF Default Values start
		@JsonProperty("isGFSPolicyTerm")
		private boolean isGFSPolicyTerm;

		public boolean isGFSPolicyTerm() {
			return isGFSPolicyTerm;
		}
		public void setGFSPolicyTerm(boolean isGFSPolicyTerm) {
			this.isGFSPolicyTerm = isGFSPolicyTerm;
		}
		
		@JsonProperty("isGFSIncomePayoutType")
		private boolean isGFSIncomePayoutType;

		@JsonProperty("isGFSPayoutIncreasingPercentage")
		private boolean isGFSPayoutIncreasingPercentage;

		@JsonProperty("isGFSSADMultipleDropdown")
		private boolean isGFSSADMultipleDropdown;

		@JsonProperty("isGFSIncomeDurationApplicable")
		private boolean isGFSIncomeDurationApplicable;

		@JsonProperty("isGFSIncomeStartPoint")
		private boolean isGFSIncomeStartPoint;

		@JsonProperty("isGFSIncomePayoutTime")
		private boolean isGFSIncomePayoutTime;

		@JsonProperty("isGFSPayFor")
		private boolean isGFSPayFor;

		@JsonProperty("isGFSCashBack")
		private boolean isGFSCashBack;
		
		@JsonProperty("isGFSCashBackPercentage")
		private boolean isGFSCashBackPercentage;
			
		public boolean isGFSIncomePayoutType() {
			return isGFSIncomePayoutType;
		}
		public void setGFSIncomePayoutType(boolean isGFSIncomePayoutType) {
			this.isGFSIncomePayoutType = isGFSIncomePayoutType;
		}
		public boolean isGFSPayoutIncreasingPercentage() {
			return isGFSPayoutIncreasingPercentage;
		}
		public void setGFSPayoutIncreasingPercentage(boolean isGFSPayoutIncreasingPercentage) {
			this.isGFSPayoutIncreasingPercentage = isGFSPayoutIncreasingPercentage;
		}
		public boolean isGFSSADMultipleDropdown() {
			return isGFSSADMultipleDropdown;
		}
		public void setGFSSADMultipleDropdown(boolean isGFSSADMultipleDropdown) {
			this.isGFSSADMultipleDropdown = isGFSSADMultipleDropdown;
		}
		public boolean isGFSIncomeDurationApplicable() {
			return isGFSIncomeDurationApplicable;
		}
		public void setGFSIncomeDurationApplicable(boolean isGFSIncomeDurationApplicable) {
			this.isGFSIncomeDurationApplicable = isGFSIncomeDurationApplicable;
		}
		public boolean isGFSIncomeStartPoint() {
			return isGFSIncomeStartPoint;
		}
		public void setGFSIncomeStartPoint(boolean isGFSIncomeStartPoint) {
			this.isGFSIncomeStartPoint = isGFSIncomeStartPoint;
		}
		public boolean isGFSIncomePayoutTime() {
			return isGFSIncomePayoutTime;
		}
		public void setGFSIncomePayoutTime(boolean isGFSIncomePayoutTime) {
			this.isGFSIncomePayoutTime = isGFSIncomePayoutTime;
		}
		public boolean isGFSPayFor() {
			return isGFSPayFor;
		}
		public void setGFSPayFor(boolean isGFSPayFor) {
			this.isGFSPayFor = isGFSPayFor;
		}
		public boolean isGFSCashBack() {
			return isGFSCashBack;
		}
		public void setGFSCashBack(boolean isGFSCashBack) {
			this.isGFSCashBack = isGFSCashBack;
		}
		public boolean isGFSCashBackPercentage() {
			return isGFSCashBackPercentage;
		}
		public void setGFSCashBackPercentage(boolean isGFSCashBackPercentage) {
			this.isGFSCashBackPercentage = isGFSCashBackPercentage;
		}
		//GIF Default Values end

		@JsonProperty("investmentAmount")
		private String investmentAmount;

		@JsonProperty("lifeCover")
		private String lifeCover;
		
		@JsonProperty("projectedReturnsData")
		private String projectedReturnsData;
		
		@JsonProperty("policyOption")
		private PolicyOption policyOption;
		
		@JsonProperty("investingFor")
		private InvestingFor investingFor;
		
		@JsonProperty("policyTerm")
		private String policyTerm;
		
		@JsonProperty("policyFor")
		private String policyFor;
		
		@JsonProperty("paymentOption")
		private PaymentOption paymentOption;
		
		@JsonProperty("fundManagement")
		private FundManagement fundManagement;
		
		@JsonProperty("swp")
		private SWP swp;
		
		@JsonProperty("isEdelweissEmployee")
		private IsEdelweissEmployee isEdelweissEmployee;
		
		@JsonProperty("familyIncomeBenefit")
		private FamilyIncomeBenefit familyIncomeBenefit;
		
		@JsonProperty("incomePayoutFrequency")
		private IncomePayoutFrequency incomePayoutFrequency;
		
		@JsonProperty("payoutOption")
		private PayoutOption payoutOption;
		
		@JsonProperty("incomePeriod")
		private String incomePeriod;
		
		@JsonProperty("incomePercentage")
		private String incomePercentage;
		
		@JsonProperty("premiumBreakBenefit")
		private String premiumBreakBenefit;
		
		@JsonProperty("iBPayoutOption")
		private IBPayoutOption iBPayoutOption;
		
		public IBPayoutOption getiBPayoutOption() {
			return iBPayoutOption;
		}
		public void setiBPayoutOption(IBPayoutOption iBPayoutOption) {
			this.iBPayoutOption = iBPayoutOption;
		}
		public IBPayoutPeriod getiBPayoutPeriod() {
			return iBPayoutPeriod;
		}
		public void setiBPayoutPeriod(IBPayoutPeriod iBPayoutPeriod) {
			this.iBPayoutPeriod = iBPayoutPeriod;
		}
		public boolean isIBPayoutOptionApplicable() {
			return isIBPayoutOptionApplicable;
		}
		public void setIBPayoutOptionApplicable(boolean isIBPayoutOptionApplicable) {
			this.isIBPayoutOptionApplicable = isIBPayoutOptionApplicable;
		}
		public boolean isIBPayoutPeriodApplicable() {
			return isIBPayoutPeriodApplicable;
		}
		public void setIBPayoutPeriodApplicable(boolean isIBPayoutPeriodApplicable) {
			this.isIBPayoutPeriodApplicable = isIBPayoutPeriodApplicable;
		}

		@JsonProperty("iBPayoutPeriod")
		private IBPayoutPeriod iBPayoutPeriod;
		
		@JsonProperty("getIncomeFor")
		private String getIncomeFor;
		
		@JsonProperty("lumpSumBenefit")	
		private LumpSumBenefit lumpSumBenefit;	
			
		@JsonProperty("maturityPayout")	
		private MaturityPayout maturityPayout;
		
		@JsonProperty("gISLumpSumBenefit")
		private GISLumpSumBenefit gISLumpSumBenefit;
		
		public GISLumpSumBenefit getgISLumpSumBenefit() {
			return gISLumpSumBenefit;
		}
		public void setgISLumpSumBenefit(GISLumpSumBenefit gISLumpSumBenefit) {
			this.gISLumpSumBenefit = gISLumpSumBenefit;
		}
		public GISIncomeDuration getgISIncomeDuration() {
			return gISIncomeDuration;
		}
		public void setgISIncomeDuration(GISIncomeDuration gISIncomeDuration) {
			this.gISIncomeDuration = gISIncomeDuration;
		}
		public GISIncomeBenefitPayoutType getgISIncomeBenefitPayoutType() {
			return gISIncomeBenefitPayoutType;
		}
		public void setgISIncomeBenefitPayoutType(GISIncomeBenefitPayoutType gISIncomeBenefitPayoutType) {
			this.gISIncomeBenefitPayoutType = gISIncomeBenefitPayoutType;
		}
		public GISIncomeStartPoint getgISIncomeStartPoint() {
			return gISIncomeStartPoint;
		}
		public void setgISIncomeStartPoint(GISIncomeStartPoint gISIncomeStartPoint) {
			this.gISIncomeStartPoint = gISIncomeStartPoint;
		}
		public boolean isGISLumpSumBenefitApplicable() {
			return isGISLumpSumBenefitApplicable;
		}
		public void setGISLumpSumBenefitApplicable(boolean isGISLumpSumBenefitApplicable) {
			this.isGISLumpSumBenefitApplicable = isGISLumpSumBenefitApplicable;
		}
		public boolean isGISIncomeDurationApplicable() {
			return isGISIncomeDurationApplicable;
		}
		public void setGISIncomeDurationApplicable(boolean isGISIncomeDurationApplicable) {
			this.isGISIncomeDurationApplicable = isGISIncomeDurationApplicable;
		}
		public boolean isGISIncomeBenefitPayoutTypeApplicable() {
			return isGISIncomeBenefitPayoutTypeApplicable;
		}
		public void setGISIncomeBenefitPayoutTypeApplicable(boolean isGISIncomeBenefitPayoutTypeApplicable) {
			this.isGISIncomeBenefitPayoutTypeApplicable = isGISIncomeBenefitPayoutTypeApplicable;
		}
		public boolean isGISIncomeStartPointApplicable() {
			return isGISIncomeStartPointApplicable;
		}
		public void setGISIncomeStartPointApplicable(boolean isGISIncomeStartPointApplicable) {
			this.isGISIncomeStartPointApplicable = isGISIncomeStartPointApplicable;
		}

		@JsonProperty("gISIncomeDuration")
		private GISIncomeDuration gISIncomeDuration;
		
		@JsonProperty("gISIncomeBenefitPayoutType")
		private GISIncomeBenefitPayoutType gISIncomeBenefitPayoutType;
		
		@JsonProperty("gISIncomeStartPoint")
		private GISIncomeStartPoint gISIncomeStartPoint;
		
		@JsonProperty("isEdelweissEmployeeApplicable")
		private boolean isEdelweissEmployeeApplicable;
		
		@JsonProperty("isFamilyIncomeBenefitApplicable")
		private boolean isFamilyIncomeBenefitApplicable;
		
		@JsonProperty("isIncomePayoutFrequencyApplicable")
		private boolean isIncomePayoutFrequencyApplicable;
		
		@JsonProperty("isGetIncomeForApplicable")
		private boolean isGetIncomeForApplicable;
		
		@JsonProperty("isLumpSumBenefitApplicable")	
		private boolean isLumpSumBenefitApplicable;	
			
		@JsonProperty("isMaturityPayoutApplicable")	
		private boolean isMaturityPayoutApplicable;
		
		@JsonProperty("isInvestmentAmountApplicable")
		private boolean isInvestmentAmountApplicable;

		@JsonProperty("isLifeCoverApplicable")
		private boolean isLifeCoverApplicable;

		@JsonProperty("isPolicyOptionApplicable")
		private boolean isPolicyOptionApplicable;
		
		@JsonProperty("isInvestingForApplicable")
		private boolean isInvestingForApplicable;
		
		@JsonProperty("isPolicyTermApplicable")
		private boolean isPolicyTermApplicable;
		
		@JsonProperty("isPolicyForApplicable")
		private boolean isPolicyForApplicable;
		
		@JsonProperty("isPaymentOptionApplicable")
		private boolean isPaymentOptionApplicable;
		
		@JsonProperty("isIncomePeriodApplicable")
		private boolean isIncomePeriodApplicable;
		
		@JsonProperty("isIncomePercentageApplicable")
		private boolean isIncomePercentageApplicable;
		
		@JsonProperty("isPremiumBreakBenefitApplicable")
		private boolean isPremiumBreakBenefitApplicable;
		
		@JsonProperty("isFundManagementApplicable")
		private boolean isFundManagementApplicable;
		
		@JsonProperty("isGISLumpSumBenefitApplicable")
		private boolean isGISLumpSumBenefitApplicable;
		
		@JsonProperty("isGISIncomeDurationApplicable")
		private boolean isGISIncomeDurationApplicable;
		
		@JsonProperty("isGISIncomeBenefitPayoutTypeApplicable")
		private boolean isGISIncomeBenefitPayoutTypeApplicable;
		
		@JsonProperty("isGISIncomeStartPointApplicable")
		private boolean isGISIncomeStartPointApplicable;
		
		@JsonProperty("isSWPApplicable")
		private boolean isSWPApplicable;
		
		@JsonProperty("isIBPayoutOptionApplicable")
		private boolean isIBPayoutOptionApplicable;
		
		@JsonProperty("isIBPayoutPeriodApplicable")
		private boolean isIBPayoutPeriodApplicable;
		
		@JsonProperty("isFundValueToBeWithdrawnApplicable")
		private boolean isFundValueToBeWithdrawnApplicable;
		
		@JsonProperty("fundValueToBeWithdrawn")
		private FundValueToBeWithdrawn fundValueToBeWithdrawn;
		
		@JsonProperty("isPolicyYearFromWhichSWPPayableApllicable")
		private boolean isPolicyYearFromWhichSWPPayableApllicable;
		
		@JsonProperty("policyYearFromWhichSWPPayable")
		private PolicyYearFromWhichSWPPayable policyYearFromWhichSWPPayable;
		
		@JsonProperty("isSWPFrequencyApplicable")
		private boolean isSWPFrequencyApplicable;
		
		@JsonProperty("swpFrequency")
		private SWPFrequency swpFrequency;
		
		@JsonProperty("isPayoutOptionApplicable")
		private boolean isPayoutOptionApplicable;
		
		@JsonProperty("selectedRidersIds")
		private String selectedRidersIds;
		
		@JsonProperty("isIncomeOptionApplicable")
		private boolean isIncomeOptionApplicable;
		
		@JsonProperty("incomeOption")
		private IncomeOption incomeOption;
		
		@JsonProperty("isAIPFamilyBenefitApplicable")
		private boolean isAIPFamilyBenefitApplicable;
		
		@JsonProperty("aipFamilyIncomeBenefit")
		private AIPFamilyIncomeBenefit aipFamilyIncomeBenefit;
		
		@JsonProperty("isMaturityAgeApplicable")
		private boolean isMaturityAgeApplicable;
		
		@JsonProperty("maturityAge")
		private MaturityAge maturityAge;
		
		@JsonProperty("isGuaranteedIncomeApplicable")
		private boolean isGuaranteedIncomeApplicable;
		
		@JsonProperty("guaranteedIncome")
		private GuaranteedIncome guaranteedIncome;
		
		@JsonProperty("isPlanOptionApplicable")
		private boolean isPlanOptionApplicable;
		
		@JsonProperty("planOption")
		private PlanOption planOption;
		
		@JsonProperty("isLifeCoverContinuationBenefitApplicable")
		private boolean isLifeCoverContinuationBenefitApplicable;
		
		@JsonProperty("lifeCoverContinuationBenefit")
		private LifeCoverContinuationBenefit lifeCoverContinuationBenefit;
		
		@JsonProperty("isIncomeStartYearApplicable")
		private boolean isIncomeStartYearApplicable;
		
		@JsonProperty("isGCAPPayoutOptionApplicable")
		private boolean isGCAPPayoutOptionApplicable;
		
		@JsonProperty("gcapPayoutOption")
		private PayoutOption gcapPayoutOption;
		
		@JsonProperty("incomeStartYear")
		private IncomeStartYear incomeStartYear;
		
		public boolean isGCAPPayoutOptionApplicable() {
			return isGCAPPayoutOptionApplicable;
		}
		public void setGCAPPayoutOptionApplicable(boolean isGCAPPayoutOptionApplicable) {
			this.isGCAPPayoutOptionApplicable = isGCAPPayoutOptionApplicable;
		}
		public PayoutOption getGcapPayoutOption() {
			return gcapPayoutOption;
		}
		public void setGcapPayoutOption(PayoutOption gcapPayoutOption) {
			this.gcapPayoutOption = gcapPayoutOption;
		}
		public boolean isIncomeOptionApplicable() {
			return isIncomeOptionApplicable;
		}
		public void setIncomeOptionApplicable(boolean isIncomeOptionApplicable) {
			this.isIncomeOptionApplicable = isIncomeOptionApplicable;
		}
		public IncomeOption getIncomeOption() {
			return incomeOption;
		}
		public void setIncomeOption(IncomeOption incomeOption) {
			this.incomeOption = incomeOption;
		}
		public boolean isAIPFamilyBenefitApplicable() {
			return isAIPFamilyBenefitApplicable;
		}
		public void setAIPFamilyBenefitApplicable(boolean isAIPFamilyBenefitApplicable) {
			this.isAIPFamilyBenefitApplicable = isAIPFamilyBenefitApplicable;
		}
		public AIPFamilyIncomeBenefit getAipFamilyIncomeBenefit() {
			return aipFamilyIncomeBenefit;
		}
		public void setAipFamilyIncomeBenefit(AIPFamilyIncomeBenefit aipFamilyIncomeBenefit) {
			this.aipFamilyIncomeBenefit = aipFamilyIncomeBenefit;
		}
		public boolean isMaturityAgeApplicable() {
			return isMaturityAgeApplicable;
		}
		public void setMaturityAgeApplicable(boolean isMaturityAgeApplicable) {
			this.isMaturityAgeApplicable = isMaturityAgeApplicable;
		}
		public MaturityAge getMaturityAge() {
			return maturityAge;
		}
		public void setMaturityAge(MaturityAge maturityAge) {
			this.maturityAge = maturityAge;
		}
		public boolean isGuaranteedIncomeApplicable() {
			return isGuaranteedIncomeApplicable;
		}
		public void setGuaranteedIncomeApplicable(boolean isGuaranteedIncomeApplicable) {
			this.isGuaranteedIncomeApplicable = isGuaranteedIncomeApplicable;
		}
		public GuaranteedIncome getGuaranteedIncome() {
			return guaranteedIncome;
		}
		public void setGuaranteedIncome(GuaranteedIncome guaranteedIncome) {
			this.guaranteedIncome = guaranteedIncome;
		}
		public String getProjectedReturnsData() {
			return projectedReturnsData;
		}
		public void setProjectedReturnsData(String projectedReturnsData) {
			this.projectedReturnsData = projectedReturnsData;
		}
		public String getInvestmentAmount() {
			return investmentAmount;
		}
		public void setInvestmentAmount(String investmentAmount) {
			this.investmentAmount = investmentAmount;
		}

		public String getLifeCover() {
			return lifeCover;
		}
		public void setLifeCover(String lifeCover) {
			this.lifeCover = lifeCover;
		}

		public PolicyOption getPolicyOption() {
			return policyOption;
		}
		public void setPolicyOption(PolicyOption policyOption) {
			this.policyOption = policyOption;
		}
		public InvestingFor getInvestingFor() {
			return investingFor;
		}
		public void setInvestingFor(InvestingFor investingFor) {
			this.investingFor = investingFor;
		}
		public String getPolicyTerm() {
			return policyTerm;
		}
		public void setPolicyTerm(String policyTerm) {
			this.policyTerm = policyTerm;
		}
		public String getPolicyFor() {
			return policyFor;
		}
		public void setPolicyFor(String policyFor) {
			this.policyFor = policyFor;
		}
		public PaymentOption getPaymentOption() {
			return paymentOption;
		}
		public void setPaymentOption(PaymentOption paymentOption) {
			this.paymentOption = paymentOption;
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
		public FundManagement getFundManagement() {
			return fundManagement;
		}
		public void setFundManagement(FundManagement fundManagement) {
			this.fundManagement = fundManagement;
		}
		public SWP getSwp() {
			return swp;
		}
		public void setSwp(SWP swp) {
			this.swp = swp;
		}
		public IsEdelweissEmployee getIsEdelweissEmployee() {
			return isEdelweissEmployee;
		}
		public void setIsEdelweissEmployee(IsEdelweissEmployee isEdelweissEmployee) {
			this.isEdelweissEmployee = isEdelweissEmployee;
		}
		public FamilyIncomeBenefit getFamilyIncomeBenefit() {
			return familyIncomeBenefit;
		}
		public void setFamilyIncomeBenefit(FamilyIncomeBenefit familyIncomeBenefit) {
			this.familyIncomeBenefit = familyIncomeBenefit;
		}
		public IncomePayoutFrequency getIncomePayoutFrequency() {
			return incomePayoutFrequency;
		}
		public void setIncomePayoutFrequency(IncomePayoutFrequency incomePayoutFrequency) {
			this.incomePayoutFrequency = incomePayoutFrequency;
		}
		public String getGetIncomeFor() {
			return getIncomeFor;
		}
		public LumpSumBenefit getLumpSumBenefit() {	
			return lumpSumBenefit;	
		}	
		public void setLumpSumBenefit(LumpSumBenefit lumpSumBenefit) {	
			this.lumpSumBenefit = lumpSumBenefit;	
		}	
		public MaturityPayout getMaturityPayout() {	
			return maturityPayout;	
		}	
		public void setMaturityPayout(MaturityPayout maturityPayout) {	
			this.maturityPayout = maturityPayout;	
		}
		public void setGetIncomeFor(String getIncomeFor) {
			this.getIncomeFor = getIncomeFor;
		}
		public boolean isInvestmentAmountApplicable() {
			return isInvestmentAmountApplicable;
		}
		public void setInvestmentAmountApplicable(boolean isInvestmentAmountApplicable) {
			this.isInvestmentAmountApplicable = isInvestmentAmountApplicable;
		}

		public boolean isLifeCoverApplicable() {
			return isLifeCoverApplicable;
		}
		
		public boolean isIncomePeriodApplicable() {
			return isIncomePeriodApplicable;
		}
		public boolean isIncomePercentageApplicable() {
			return isIncomePercentageApplicable;
		}
		public boolean isPremiumBreakBenefitApplicable() {
			return isPremiumBreakBenefitApplicable;
		}

		public void setIncomePeriodApplicable(boolean isIncomePeriodApplicable) {
			this.isIncomePeriodApplicable = isIncomePeriodApplicable;
		}
		public void setIncomePercentageApplicable(boolean isIncomePercentageApplicable) {
			this.isIncomePercentageApplicable = isIncomePercentageApplicable;
		}
		public void setPremiumBreakBenefitApplicable(boolean isPremiumBreakBenefitApplicable) {
			this.isPremiumBreakBenefitApplicable = isPremiumBreakBenefitApplicable;
		}


		public void setLifeCoverApplicable(boolean isLifeCoverApplicable) {
			this.isLifeCoverApplicable = isLifeCoverApplicable;
		}

		public boolean isPolicyOptionApplicable() {
			return isPolicyOptionApplicable;
		}
		public void setPolicyOptionApplicable(boolean isPolicyOptionApplicable) {
			this.isPolicyOptionApplicable = isPolicyOptionApplicable;
		}
		public boolean isInvestingForApplicable() {
			return isInvestingForApplicable;
		}
		public void setInvestingForApplicable(boolean isInvestingForApplicable) {
			this.isInvestingForApplicable = isInvestingForApplicable;
		}
		public boolean isPolicyTermApplicable() {
			return isPolicyTermApplicable;
		}
		public void setPolicyTermApplicable(boolean isPolicyTermApplicable) {
			this.isPolicyTermApplicable = isPolicyTermApplicable;
		}
		public boolean isPolicyForApplicable() {
			return isPolicyForApplicable;
		}
		public void setPolicyForApplicable(boolean isPolicyForApplicable) {
			this.isPolicyForApplicable = isPolicyForApplicable;
		}
		public boolean isPaymentOptionApplicable() {
			return isPaymentOptionApplicable;
		}
		public void setPaymentOptionApplicable(boolean isPaymentOptionApplicable) {
			this.isPaymentOptionApplicable = isPaymentOptionApplicable;
		}
		public boolean isFundManagementApplicable() {
			return isFundManagementApplicable;
		}
		public void setFundManagementApplicable(boolean isFundManagementApplicable) {
			this.isFundManagementApplicable = isFundManagementApplicable;
		}
		public boolean isSWPApplicable() {
			return isSWPApplicable;
		}
		public void setSWPApplicable(boolean isSWPApplicable) {
			this.isSWPApplicable = isSWPApplicable;
		}
		public boolean isFundValueToBeWithdrawnApplicable() {
			return isFundValueToBeWithdrawnApplicable;
		}
		public void setFundValueToBeWithdrawnApplicable(boolean isFundValueToBeWithdrawnApplicable) {
			this.isFundValueToBeWithdrawnApplicable = isFundValueToBeWithdrawnApplicable;
		}
		public FundValueToBeWithdrawn getFundValueToBeWithdrawn() {
			return fundValueToBeWithdrawn;
		}
		public void setFundValueToBeWithdrawn(FundValueToBeWithdrawn fundValueToBeWithdrawn) {
			this.fundValueToBeWithdrawn = fundValueToBeWithdrawn;
		}
		public boolean isPolicyYearFromWhichSWPPayableApllicable() {
			return isPolicyYearFromWhichSWPPayableApllicable;
		}
		public void setPolicyYearFromWhichSWPPayableApllicable(boolean isPolicyYearFromWhichSWPPayableApllicable) {
			this.isPolicyYearFromWhichSWPPayableApllicable = isPolicyYearFromWhichSWPPayableApllicable;
		}
		public PolicyYearFromWhichSWPPayable getPolicyYearFromWhichSWPPayable() {
			return policyYearFromWhichSWPPayable;
		}
		public void setPolicyYearFromWhichSWPPayable(PolicyYearFromWhichSWPPayable policyYearFromWhichSWPPayable) {
			this.policyYearFromWhichSWPPayable = policyYearFromWhichSWPPayable;
		}
		public boolean isSWPFrequencyApplicable() {
			return isSWPFrequencyApplicable;
		}
		public void setSWPFrequencyApplicable(boolean isSWPFrequencyApplicable) {
			this.isSWPFrequencyApplicable = isSWPFrequencyApplicable;
		}
		public SWPFrequency getSwpFrequency() {
			return swpFrequency;
		}
		public void setSwpFrequency(SWPFrequency swpFrequency) {
			this.swpFrequency = swpFrequency;
		}
		public boolean isEdelweissEmployeeApplicable() {
			return isEdelweissEmployeeApplicable;
		}
		public boolean isFamilyIncomeBenefitApplicable() {
			return isFamilyIncomeBenefitApplicable;
		}
		public boolean isIncomePayoutFrequencyApplicable() {
			return isIncomePayoutFrequencyApplicable;
		}
		public boolean isGetIncomeForApplicable() {
			return isGetIncomeForApplicable;
		}
		public boolean isLumpSumBenefitApplicable() {	
			return isLumpSumBenefitApplicable;	
		}	
		public boolean isMaturityPayoutApplicable() {	
			return isMaturityPayoutApplicable;	
		}
		public void setEdelweissEmployeeApplicable(boolean isEdelweissEmployeeApplicable) {
			this.isEdelweissEmployeeApplicable = isEdelweissEmployeeApplicable;
		}
		public void setFamilyIncomeBenefitApplicable(boolean isFamilyIncomeBenefitApplicable) {
			this.isFamilyIncomeBenefitApplicable = isFamilyIncomeBenefitApplicable;
		}
		public void setIncomePayoutFrequencyApplicable(boolean isIncomePayoutFrequencyApplicable) {
			this.isIncomePayoutFrequencyApplicable = isIncomePayoutFrequencyApplicable;
		}
		public void setGetIncomeForApplicable(boolean isGetIncomeForApplicable) {
			this.isGetIncomeForApplicable = isGetIncomeForApplicable;
		}
		public void setLumpSumBenefitApplicable(boolean isLumpSumBenefitApplicable) {	
			this.isLumpSumBenefitApplicable = isLumpSumBenefitApplicable;	
		}	
		public void setMaturityPayoutApplicable(boolean isMaturityPayoutApplicable) {	
			this.isMaturityPayoutApplicable = isMaturityPayoutApplicable;
		}
		public boolean isPayoutOptionApplicable() {
			return isPayoutOptionApplicable;
		}
		public void setPayoutOptionApplicable(boolean isPayoutOptionApplicable) {
			this.isPayoutOptionApplicable = isPayoutOptionApplicable;
		}
		
		public PayoutOption getPayoutOption() {
			return payoutOption;
		}
		public void setPayoutOption(PayoutOption payoutOption) {
			this.payoutOption = payoutOption;
		}
		
		public String getSelectedRidersIds() {
			return selectedRidersIds;
		}
		public void setSelectedRidersIds(String selectedRidersIds) {
			this.selectedRidersIds = selectedRidersIds;
		}
		
		public boolean isPlanOptionApplicable() {
			return isPlanOptionApplicable;
		}
		public void setPlanOptionApplicable(boolean isPlanOptionApplicable) {
			this.isPlanOptionApplicable = isPlanOptionApplicable;
		}
		public PlanOption getPlanOption() {
			return planOption;
		}
		public void setPlanOption(PlanOption planOption) {
			this.planOption = planOption;
		}
		public boolean isLifeCoverContinuationBenefitApplicable() {
			return isLifeCoverContinuationBenefitApplicable;
		}
		public void setLifeCoverContinuationBenefitApplicable(boolean isLifeCoverContinuationBenefitApplicable) {
			this.isLifeCoverContinuationBenefitApplicable = isLifeCoverContinuationBenefitApplicable;
		}
		public LifeCoverContinuationBenefit getLifeCoverContinuationBenefit() {
			return lifeCoverContinuationBenefit;
		}
		public void setLifeCoverContinuationBenefit(LifeCoverContinuationBenefit lifeCoverContinuationBenefit) {
			this.lifeCoverContinuationBenefit = lifeCoverContinuationBenefit;
		}
		public boolean isIncomeStartYearApplicable() {
			return isIncomeStartYearApplicable;
		}
		public void setIncomeStartYearApplicable(boolean isIncomeStartYearApplicable) {
			this.isIncomeStartYearApplicable = isIncomeStartYearApplicable;
		}
		public IncomeStartYear getIncomeStartYear() {
			return incomeStartYear;
		}
		public void setIncomeStartYear(IncomeStartYear incomeStartYear) {
			this.incomeStartYear = incomeStartYear;
		}
		@Override
		public String toString() {
			return "ProductDefaultValueRel [isGFSPolicyTerm=" + isGFSPolicyTerm + ", isGFSIncomePayoutType="
					+ isGFSIncomePayoutType + ", isGFSPayoutIncreasingPercentage=" + isGFSPayoutIncreasingPercentage
					+ ", isGFSSADMultipleDropdown=" + isGFSSADMultipleDropdown + ", isGFSIncomeDurationApplicable="
					+ isGFSIncomeDurationApplicable + ", isGFSIncomeStartPoint=" + isGFSIncomeStartPoint
					+ ", isGFSIncomePayoutTime=" + isGFSIncomePayoutTime + ", isGFSPayFor=" + isGFSPayFor
					+ ", isGFSCashBack=" + isGFSCashBack + ", isGFSCashBackPercentage=" + isGFSCashBackPercentage
					+ ", investmentAmount=" + investmentAmount + ", lifeCover=" + lifeCover + ", projectedReturnsData="
					+ projectedReturnsData + ", policyOption=" + policyOption + ", investingFor=" + investingFor
					+ ", policyTerm=" + policyTerm + ", policyFor=" + policyFor + ", paymentOption=" + paymentOption
					+ ", fundManagement=" + fundManagement + ", swp=" + swp + ", isEdelweissEmployee="
					+ isEdelweissEmployee + ", familyIncomeBenefit=" + familyIncomeBenefit + ", incomePayoutFrequency="
					+ incomePayoutFrequency + ", payoutOption=" + payoutOption + ", incomePeriod=" + incomePeriod
					+ ", incomePercentage=" + incomePercentage + ", premiumBreakBenefit=" + premiumBreakBenefit
					+ ", iBPayoutOption=" + iBPayoutOption + ", iBPayoutPeriod=" + iBPayoutPeriod + ", getIncomeFor="
					+ getIncomeFor + ", lumpSumBenefit=" + lumpSumBenefit + ", maturityPayout=" + maturityPayout
					+ ", gISLumpSumBenefit=" + gISLumpSumBenefit + ", gISIncomeDuration=" + gISIncomeDuration
					+ ", gISIncomeBenefitPayoutType=" + gISIncomeBenefitPayoutType + ", gISIncomeStartPoint="
					+ gISIncomeStartPoint + ", isEdelweissEmployeeApplicable=" + isEdelweissEmployeeApplicable
					+ ", isFamilyIncomeBenefitApplicable=" + isFamilyIncomeBenefitApplicable
					+ ", isIncomePayoutFrequencyApplicable=" + isIncomePayoutFrequencyApplicable
					+ ", isGetIncomeForApplicable=" + isGetIncomeForApplicable + ", isLumpSumBenefitApplicable="
					+ isLumpSumBenefitApplicable + ", isMaturityPayoutApplicable=" + isMaturityPayoutApplicable
					+ ", isInvestmentAmountApplicable=" + isInvestmentAmountApplicable + ", isLifeCoverApplicable="
					+ isLifeCoverApplicable + ", isPolicyOptionApplicable=" + isPolicyOptionApplicable
					+ ", isInvestingForApplicable=" + isInvestingForApplicable + ", isPolicyTermApplicable="
					+ isPolicyTermApplicable + ", isPolicyForApplicable=" + isPolicyForApplicable
					+ ", isPaymentOptionApplicable=" + isPaymentOptionApplicable + ", isIncomePeriodApplicable="
					+ isIncomePeriodApplicable + ", isIncomePercentageApplicable=" + isIncomePercentageApplicable
					+ ", isPremiumBreakBenefitApplicable=" + isPremiumBreakBenefitApplicable
					+ ", isFundManagementApplicable=" + isFundManagementApplicable + ", isGISLumpSumBenefitApplicable="
					+ isGISLumpSumBenefitApplicable + ", isGISIncomeDurationApplicable=" + isGISIncomeDurationApplicable
					+ ", isGISIncomeBenefitPayoutTypeApplicable=" + isGISIncomeBenefitPayoutTypeApplicable
					+ ", isGISIncomeStartPointApplicable=" + isGISIncomeStartPointApplicable + ", isSWPApplicable="
					+ isSWPApplicable + ", isIBPayoutOptionApplicable=" + isIBPayoutOptionApplicable
					+ ", isIBPayoutPeriodApplicable=" + isIBPayoutPeriodApplicable
					+ ", isFundValueToBeWithdrawnApplicable=" + isFundValueToBeWithdrawnApplicable
					+ ", fundValueToBeWithdrawn=" + fundValueToBeWithdrawn
					+ ", isPolicyYearFromWhichSWPPayableApllicable=" + isPolicyYearFromWhichSWPPayableApllicable
					+ ", policyYearFromWhichSWPPayable=" + policyYearFromWhichSWPPayable + ", isSWPFrequencyApplicable="
					+ isSWPFrequencyApplicable + ", swpFrequency=" + swpFrequency + ", isPayoutOptionApplicable="
					+ isPayoutOptionApplicable + ", selectedRidersIds=" + selectedRidersIds
					+ ", isIncomeOptionApplicable=" + isIncomeOptionApplicable + ", incomeOption=" + incomeOption
					+ ", isAIPFamilyBenefitApplicable=" + isAIPFamilyBenefitApplicable + ", aipFamilyIncomeBenefit="
					+ aipFamilyIncomeBenefit + ", isMaturityAgeApplicable=" + isMaturityAgeApplicable + ", maturityAge="
					+ maturityAge + ", isGuaranteedIncomeApplicable=" + isGuaranteedIncomeApplicable
					+ ", guaranteedIncome=" + guaranteedIncome + ", isPlanOptionApplicable=" + isPlanOptionApplicable
					+ ", planOption=" + planOption + ", isLifeCoverContinuationBenefitApplicable="
					+ isLifeCoverContinuationBenefitApplicable + ", lifeCoverContinuationBenefit="
					+ lifeCoverContinuationBenefit + ", isIncomeStartYearApplicable=" + isIncomeStartYearApplicable
					+ ", isGCAPPayoutOptionApplicable=" + isGCAPPayoutOptionApplicable + ", gcapPayoutOption="
					+ gcapPayoutOption + ", incomeStartYear=" + incomeStartYear + "]";
		}
		
		
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Brochure {

		@JsonProperty("link")
		private Link link;
		
		@JsonProperty("name")
		private String name;

		@JsonProperty("id")
		private long id;

		public Link getLink() {
			return link;
		}

		public void setLink(Link link) {
			this.link = link;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		@Override
		public String toString() {
			return "Brochure [link=" + link + ", name=" + name + ", id=" + id + "]";
		}
	}
	
	public static class PaymentOption { 
		
		@JsonProperty("name")
		private String name;
		
		@JsonProperty("key")
		private String key;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		
		@Override
		public String toString() {
			return "PaymentOption [name=" + name + ", paymentOptionkey=" + key + "]";
		}
	}
	
	
	public static class PolicyOption { 
		
		@JsonProperty("name")
		private String name;
		
		@JsonProperty("key")
		private String key;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		
		@Override
		public String toString() {
			return "{\"name\":\""+name+"\",\"key\":\""+key+"\"}";
		}
	}
	
	public static class InvestingFor {
		
		@JsonProperty("name")
		private String name;
		
		@JsonProperty("key")
		private String key;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		@Override
		public String toString() {
			return "InvestingFor [name=" + name + ", investingForkey=" + key + "]";
		}
	}
	
	public static class FamilyIncomeBenefitOption {
		
		@JsonProperty("name")
		private String name;
		
		@JsonProperty("key")
		private String key;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		@Override
		public String toString() {
			return "InvestingFor [name=" + name + ", familyIBOptionkey=" + key + "]";
		}
	}
	
	public static class SWP {
		
		@JsonProperty("name")
		private String name;
		
		@JsonProperty("key")
		private String key;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		@Override
		public String toString() {
			return "SWP [name=" + name + ", swpKey=" + key + "]";
		}
	}
	
	public static class IsEdelweissEmployee {
		
		@JsonProperty("name")
		private String name;
		
		@JsonProperty("key")
		private String key;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		@Override
		public String toString() {
			return "IsEdelweissEmployee [name=" + name + ", edelweissEmpKey=" + key + "]";
		}
	}
	
	public static class FundValueToBeWithdrawn {
		@JsonProperty("name")
		private String name;
		
		@JsonProperty("key")
		private String key;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		@Override
		public String toString() {
			return "FundValueToBeWithdrawn [name=" + name + ", fundValueWithdawalkey=" + key + "]";
		}
	}
	
	public static class FamilyIncomeBenefit {
		
		@JsonProperty("name")
		private String name;
		
		@JsonProperty("key")
		private String key;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		
		@Override
		public String toString() {
			return "FamilyIncomeBenefit [name=" + name + ", familyIBKey=" + key + "]";
		}
	}
	
	public static class PolicyYearFromWhichSWPPayable {
		@JsonProperty("name")
		private String name;
		
		@JsonProperty("key")
		private String key;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		
		@Override
		public String toString() {
			return "PolicyYearFromWhichSWPPayable [name=" + name + ", policyYearFWSWPPaykey=" + key + "]";
		}
	}

	public static class PolicyFor {

		@JsonProperty("name")
		private String name;

		@JsonProperty("key")
		private String key;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		@Override
		public String toString() {
			return "{\"name\":\""+name+"\",\"key\":\""+key+"\"}";
		}
	}
	public static class IncomePayoutFrequency {
			
		@JsonProperty("name")
		private String name;
		
		@JsonProperty("key")
		private String key;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		
		@Override
		public String toString() {
			return "IncomePayoutFrequency [name=" + name + ", incomePayoutFreqKey=" + key + "]";
		}
	}
	
	public static class SWPFrequency {
		@JsonProperty("name")
		private String name;
		
		@JsonProperty("key")
		private String key;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		
		@Override
		public String toString() {
			return "SWPFrequency [name=" + name + ", swpFreKey=" + key + "]";
		}
	}
	public static class GetIncomeFor {
		
		@JsonProperty("name")
		private String name;
		
		@JsonProperty("key")
		private String key;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		
		@Override
		public String toString() {
			return "GetIncomeFor [name=" + name + ", getIncomeForKey=" + key + "]";
		}
	}
	
	public static class FundManagement {

		@JsonProperty("name")
		private String name;
		
		@JsonProperty("key")
		private String key;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		
		@Override
		public String toString() {
			return "FundManagement [name=" + name + ", fundManagementKey=" + key + "]";
		}
		
	}
	
	public static class PayoutOption {

		@JsonProperty("name")
		private String name;
		
		@JsonProperty("key")
		private String key;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		
		@Override
		public String toString() {
			return "PayoutOption [name=" + name + ", payoutOptionKey=" + key + "]";
		}
		
	}
	
	public static class IBPayoutOption {

		@JsonProperty("name")
		private String name;
		
		@JsonProperty("key")
		private String key;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		
		@Override
		public String toString() {
			return "IBPayoutOption [name=" + name + ", iBPayoutOptionKey=" + key + "]";
		}
		
	}
	public static class IBPayoutPeriod {

		@JsonProperty("name")
		private String name;
		
		@JsonProperty("key")
		private String key;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		
		@Override
		public String toString() {
			return "IBPayoutPeriod [name=" + name + ", iBPayoutPeriodKey=" + key + "]";
		}
		
	}
	public static class LumpSumBenefit {
			
			@JsonProperty("name")
			private String name;
			
			@JsonProperty("key")
			private String key;
			
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public String getKey() {
				return key;
			}
			public void setKey(String key) {
				this.key = key;
			}
			@Override
			public String toString() {
				return "LumpSumBenefit [name=" + name + ", lumpSumBenefitKey=" + key + "]";
			}
		}
	
	public static class GISLumpSumBenefit {
		
		@JsonProperty("name")
		private String name;
		
		@JsonProperty("key")
		private String key;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		@Override
		public String toString() {
			return "GISLumpSumBenefit [name=" + name + ", gisLBKey=" + key + "]";
		}
	}
	
	public static class GISIncomeBenefitPayoutType {
			
			@JsonProperty("name")
			private String name;
			
			@JsonProperty("key")
			private String key;
			
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public String getKey() {
				return key;
			}
			public void setKey(String key) {
				this.key = key;
			}
			@Override
			public String toString() {
				return "GISIncomeBenefitPayoutType [name=" + name + ", gisIBPTKey=" + key + "]";
			}
		}
	
	public static class GISIncomeDuration {
		
		@JsonProperty("name")
		private String name;
		
		@JsonProperty("key")
		private String key;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		@Override
		public String toString() {
			return "GISIncomeDuration [name=" + name + ", gisIDKey=" + key + "]";
		}
	}
	
	public static class GISIncomeStartPoint {
			
			@JsonProperty("name")
			private String name;
			
			@JsonProperty("key")
			private String key;
			
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public String getKey() {
				return key;
			}
			public void setKey(String key) {
				this.key = key;
			}
			@Override
			public String toString() {
				return "GISIncomeStartPoint [name=" + name + ", gisISPKey=" + key + "]";
			}
		}
	
	public static class MaturityPayout {
		
		@JsonProperty("name")
		private String name;
		
		@JsonProperty("key")
		private String key;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		@Override
		public String toString() {
			return "GetIncomeFor [name=" + name + ", maturityPayoutKey=" + key + "]";
		}
	}
	
	public static class GuaranteedIncome {
		
		@JsonProperty("name")
		private String name;
		
		@JsonProperty("key")
		private String key;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		@Override
		public String toString() {
			return "GuaranteedIncome [name=" + name + ", guaranteedIncomeKey=" + key + "]";
		}
	}
	
	public static class MaturityAge {
		
		@JsonProperty("name")
		private String name;
		
		@JsonProperty("key")
		private String key;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		@Override
		public String toString() {
			return "MaturityAge [name=" + name + ", maturityAgeKey=" + key + "]";
		}
	}
	
	public static class IncomeOption {
		
		@JsonProperty("name")
		private String name;
		
		@JsonProperty("key")
		private String key;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		@Override
		public String toString() {
			return "IncomeOption [name=" + name + ", incomeOptionKey=" + key + "]";
		}
	}
	
	public static class AIPFamilyIncomeBenefit {
		
		@JsonProperty("name")
		private String name;
		
		@JsonProperty("key")
		private String key;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		@Override
		public String toString() {
			return "AIPFamilyIncomeBenefit [name=" + name + ", aipFIBKey=" + key + "]";
		}
	}

	public static class PlanOption {

		@JsonProperty("name")
		private String name;

		@JsonProperty("key")
		private String key;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		@Override
		public String toString() {
			return "PlanOption [name=" + name + ", planOptionKey=" + key + "]";
		}
	}

	public static class LifeCoverContinuationBenefit {

		@JsonProperty("name")
		private String name;

		@JsonProperty("key")
		private String key;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		@Override
		public String toString() {
			return "LifeCoverContinuationBenefit [name=" + name + ", lifeCBKey=" + key + "]";
		}
	}

	public static class IncomeStartYear {

		@JsonProperty("name")
		private String name;

		@JsonProperty("key")
		private String key;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		@Override
		public String toString() {
			return "IncomeStartYear [name=" + name + ", key=" + key + "]";
		}
	}
	
	public static class PolicyTerm {

		@JsonProperty("name")
		private String name;

		@JsonProperty("key")
		private String key;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		@Override
		public String toString() {
			return "{\"name\":\""+name+"\",\"key\":\""+key+"\"}";
		}
	}

	public static class Link {

		@JsonProperty("href")
		private String href;

		@JsonProperty("label")
		private String label;

		public String getHref() {
			return href;
		}

		public void setHref(String href) {
			this.href = href;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		@Override
		public String toString() {
			return "Link [href=" + href + ", label=" + label + "]";
		}
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class ProductMetaDataRel {
		
		@JsonProperty("externalReferenceCode")
		private String externalReferenceCode;

		@JsonProperty("dateCreated")
		private String dateCreated;
		
		@JsonProperty("dateModified")
		private String dateModified;
		
		@JsonProperty("vId")
		private String vId;
		
		@JsonProperty("cpId")
		private String cpId;
		
		@JsonProperty("r_productMetaDataRelationship_c_productsMasterId")
		private Long rProductMetaDataRelCProductsMasterid;
		
		@JsonProperty("campaign")
		private String campaign;
		
		@JsonProperty("channelName")
		private String channelName;
		
		@JsonProperty("r_productMetaDataRelationship_c_productsMasterERC")
		private String rProductMetaDataRelCProductsMasterERC;
		
		@JsonProperty("uRL")
		private String url;
		
		@JsonProperty("cId")
		private String cId;
		
		@JsonProperty("sourceOfTraffic")
		private String sourceOfTraffic;

		public String getExternalReferenceCode() {
			return externalReferenceCode;
		}

		public void setExternalReferenceCode(String externalReferenceCode) {
			this.externalReferenceCode = externalReferenceCode;
		}

		public String getDateCreated() {
			return dateCreated;
		}

		public void setDateCreated(String dateCreated) {
			this.dateCreated = dateCreated;
		}

		public String getDateModified() {
			return dateModified;
		}

		public void setDateModified(String dateModified) {
			this.dateModified = dateModified;
		}

		public String getvId() {
			return vId;
		}

		public void setvId(String vId) {
			this.vId = vId;
		}

		public String getCpId() {
			return cpId;
		}

		public void setCpId(String cpId) {
			this.cpId = cpId;
		}

		public Long getrProductMetaDataRelCProductsMasterid() {
			return rProductMetaDataRelCProductsMasterid;
		}

		public void setrProductMetaDataRelCProductsMasterid(Long rProductMetaDataRelCProductsMasterid) {
			this.rProductMetaDataRelCProductsMasterid = rProductMetaDataRelCProductsMasterid;
		}

		public String getCampaign() {
			return campaign;
		}

		public void setCampaign(String campaign) {
			this.campaign = campaign;
		}

		public String getChannelName() {
			return channelName;
		}

		public void setChannelName(String channelName) {
			this.channelName = channelName;
		}

		public String getrProductMetaDataRelCProductsMasterERC() {
			return rProductMetaDataRelCProductsMasterERC;
		}

		public void setrProductMetaDataRelCProductsMasterERC(String rProductMetaDataRelCProductsMasterERC) {
			this.rProductMetaDataRelCProductsMasterERC = rProductMetaDataRelCProductsMasterERC;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getcId() {
			return cId;
		}

		public void setcId(String cId) {
			this.cId = cId;
		}

		public String getSourceOfTraffic() {
			return sourceOfTraffic;
		}

		public void setSourceOfTraffic(String sourceOfTraffic) {
			this.sourceOfTraffic = sourceOfTraffic;
		}

		@Override
		public String toString() {
			return "ProductMetaDataRel [externalReferenceCode=" + externalReferenceCode + ", dateCreated=" + dateCreated
					+ ", dateModified=" + dateModified + ", vId=" + vId + ", cpId=" + cpId
					+ ", rProductMetaDataRelCProductsMasterid=" + rProductMetaDataRelCProductsMasterid + ", campaign="
					+ campaign + ", channelName=" + channelName + ", rProductMetaDataRelCProductsMasterERC="
					+ rProductMetaDataRelCProductsMasterERC + ", url=" + url + ", cId=" + cId + ", sourceOfTraffic="
					+ sourceOfTraffic + "]";
		}
	}

	public List<ProductPolicyOptionRel> getProductPolicyOptionRels() {
		return productPolicyOptionRels;
	}

	public void setProductPolicyOptionRels(List<ProductPolicyOptionRel> productPolicyOptionRels) {
		this.productPolicyOptionRels = productPolicyOptionRels;
	}

	public List<ProductFundManagementRel> getProductFundManagementRels() {
		return productFundManagementRels;
	}

	public void setProductFundManagementRels(List<ProductFundManagementRel> productFundManagementRels) {
		this.productFundManagementRels = productFundManagementRels;
	}

	public List<ProductInvestingForRel> getProductInvestingForRels() {
		return productInvestingForRels;
	}

	public void setProductInvestingForRels(List<ProductInvestingForRel> productInvestingForRels) {
		this.productInvestingForRels = productInvestingForRels;
	}

	public List<ProductPaymentOptionRels> getProductPaymentOptionRels() {
		return productPaymentOptionRels;
	}

	public void setProductPaymentOptionRels(List<ProductPaymentOptionRels> productPaymentOptionRels) {
		this.productPaymentOptionRels = productPaymentOptionRels;
	}

	public List<ProductDefaultValueRel> getProductDefaultValueRels() {
		return productDefaultValueRels;
	}

	public void setProductDefaultValueRels(List<ProductDefaultValueRel> productDefaultValueRels) {
		this.productDefaultValueRels = productDefaultValueRels;
	}
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getiNGPlanType() {
		return iNGPlanType;
	}

	public void setiNGPlanType(String iNGPlanType) {
		this.iNGPlanType = iNGPlanType;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductDisplayName() {
		return productDisplayName;
	}

	public void setProductDisplayName(String productDisplayName) {
		this.productDisplayName = productDisplayName;
	}

	public String getiNGProductCode() {
		return iNGProductCode;
	}

	public void setiNGProductCode(String iNGProductCode) {
		this.iNGProductCode = iNGProductCode;
	}
	
	public String getiNGPlanSubType() {
		return iNGPlanSubType;
	}

	public void setiNGPlanSubType(String iNGPlanSubType) {
		this.iNGPlanSubType = iNGPlanSubType;
	}
	public List<ProductInvestmentAmountRel> getProductInvestmentAmountRels() {
		return productInvestmentAmountRels;
	}

	public void setProductInvestmentAmountRels(List<ProductInvestmentAmountRel> productInvestmentAmountRels) {
		this.productInvestmentAmountRels = productInvestmentAmountRels;
	}

	public List<ProductPolicyForRel> getProductPolicyForRel() {
		return productPolicyForRel;
	}

	public void setProductPolicyForRel(List<ProductPolicyForRel> productPolicyForRel) {
		this.productPolicyForRel = productPolicyForRel;
	}

	public Brochure getBrochure() {
		return brochure;
	}

	public void setBrochure(Brochure brochure) {
		this.brochure = brochure;
	}

	public List<ProductMetaDataRel> getProductMetaDataRels() {
		return productMetaDataRels;
	}

	public void setProductMetaDataRels(List<ProductMetaDataRel> productMetaDataRels) {
		this.productMetaDataRels = productMetaDataRels;
	}

	public List<FamilyIncomeBenefitOptions> getFamilyIncomeBenefitOptions() {
		return familyIncomeBenefitOptions;
	}

	public void setFamilyIncomeBenefitOptions(List<FamilyIncomeBenefitOptions> familyIncomeBenefitOptions) {
		this.familyIncomeBenefitOptions = familyIncomeBenefitOptions;
	}

	public List<ProductIsyandPtRelationship> getProductIsyandPtRelationship() {
		return productIsyandPtRelationship;
	}

	public void setProductIsyandPtRelationship(List<ProductIsyandPtRelationship> productIsyandPtRelationship) {
		this.productIsyandPtRelationship = productIsyandPtRelationship;
	}

	public List<ProductValidation> getProductValidations() {
		return productValidations;
	}

	public void setProductValidations(List<ProductValidation> productValidations) {
		this.productValidations = productValidations;
	}

	@Override
	public String toString() {
		return "LiferayResponseMessage [productId=" + productId + ", iNGPlanSubType=" + iNGPlanSubType
				+ ", iNGPlanType=" + iNGPlanType + ", productStatus=" + productStatus + ", productName=" + productName
				+ ", productCategory=" + productCategory + ", productDisplayName=" + productDisplayName
				+ ", iNGProductCode=" + iNGProductCode + ", productPolicyOptionRels=" + productPolicyOptionRels
				+ ", productFundManagementRels=" + productFundManagementRels + ", productInvestingForRels="
				+ productInvestingForRels + ", productPaymentOptionRels=" + productPaymentOptionRels
				+ ", productDefaultValueRels=" + productDefaultValueRels + ", productInvestmentAmountRels="
				+ productInvestmentAmountRels + ", productPolicyForRel=" + productPolicyForRel + ", brochure="
				+ brochure + ", productMetaDataRels=" + productMetaDataRels + ", familyIncomeBenefitOptions="
				+ familyIncomeBenefitOptions + ", productIsyandPtRelationship=" + productIsyandPtRelationship
				+ ", productValidations=" + productValidations + "]";
	}
}
