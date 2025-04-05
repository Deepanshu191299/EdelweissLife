package in.edelweiss.tokio.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;

@JsonInclude(Include.NON_NULL)
public class Quote {

	@JsonProperty("Product_Code")
	private String productCode;
	
	@JsonProperty("Stage")
	private String stage;
	
	@JsonProperty("BIQuotationNumber")
	private String bIQuotationNumber;
	
	@JsonProperty("BI_PDF_Path")
	private String bIPDFPath;
	
	@JsonProperty("ProductName")
	private String productName;
	
	@JsonProperty("ProductType")
	private String productType;
	
	@JsonProperty("IS_LA_PR_Same_YN")
	private String iSLAPRSameYN;
	
	@JsonProperty("LA_Smoker_YN")
	private String lASmokerYN;
	
	@JsonProperty("PR_Smoker_YN")
	private String pRSmokerYN;
	
	@JsonProperty("TotalPremiumAmount")
	private long totalPremiumAmount;
	
	@JsonProperty("TotalPremiumAmountWoTax")
	private long totalPremiumAmountWoTax;
	
	@JsonProperty("BasePremiumAmount")
	private long basePremiumAmount;
	
	@JsonProperty("BasePremiumAmountWoTax")
	private long basePremiumAmountWoTax;
	
	@JsonProperty("AllRiderPremium")
	private long allRiderPremium;
	
	@JsonProperty("AllRiderPremiumWoTax")
	private long allRiderPremiumWoTax;
	
	@JsonProperty("SumAssured")
	private long sumAssured;
	
	@JsonProperty("CIRider_YN")
	private String cIRiderYN;
	
	@JsonProperty("CIRiderPT")
	private int cIRiderPT;
	
	@JsonProperty("CIRiderPPT")
	private int cIRiderPPT;
	
	@JsonProperty("CIRiderSA")
	private long cIRiderSA;
	
	@JsonProperty("CIRiderAmount")
	private long cIRiderAmount;
	
	@JsonProperty("CIRiderAmountWoTax")
	private int cIRiderAmountWoTax;
	
	@JsonProperty("ADBRider_YN")
	private String aDBRiderYN;
	
	@JsonProperty("ADBRiderPT")
	private int aDBRiderPT;
	
	@JsonProperty("ADBRiderPPT")
	private int aDBRiderPPT;
	
	@JsonProperty("ADBRiderSA")
	private long aDBRiderSA;
	
	@JsonProperty("ADBRiderAmount")
	private long aDBRiderAmount;
	
	@JsonProperty("ADBRiderAmountWoTax")
	private int aDBRiderAmountWoTax;
	
	@JsonProperty("ATPDRider_YN")
	private String aTPDRiderYN;
	
	@JsonProperty("ATPDRiderPT")
	private int aTPDRiderPT;
	
	@JsonProperty("ATPDRiderPPT")
	private int aTPDRiderPPT;
	
	@JsonProperty("ATPDRiderSA")
	private long aTPDRiderSA;
	
	@JsonProperty("ATPDRiderAmount")
	private long aTPDRiderAmount;
	
	@JsonProperty("ATPDRiderAmountWoTax")
	private int aTPDRiderAmountWoTax;
	
	@JsonProperty("HCBRider_YN")
	private String hCBRiderYN;
	
	@JsonProperty("HCBRiderPT")
	private int hCBRiderPT;
	
	@JsonProperty("HCBRiderPPT")
	private int hCBRiderPPT;
	
	@JsonProperty("HCBRiderSA")
	private int hCBRiderSA;
	
	@JsonProperty("HCBRiderAmount")
	private int hCBRiderAmount;
	
	@JsonProperty("HCBRiderAmountWoTax")
	private int hCBRiderAmountWoTax;
	
	@JsonProperty("IBRider_YN")
	private String iBRiderYN;
	
	@JsonProperty("IBRiderPT")
	private int iBRiderPT;
	
	@JsonProperty("IBRiderPPT")
	private int iBRiderPPT;
	
	@JsonProperty("IBRiderSA")
	private long iBRiderSA;
	
	@JsonProperty("IBRiderAmount")
	private long iBRiderAmount;
	
	@JsonProperty("IBRiderAmountWoTax")
	private int iBRiderAmountWoTax;
	
	@JsonProperty("TermRider_YN")
	private String termRiderYN;
	
	@JsonProperty("TermRiderPT")
	private int termRiderPT;
	
	@JsonProperty("TermRiderPPT")
	private int termRiderPPT;
	
	@JsonProperty("TermRiderSA")
	private int termRiderSA;
	
	@JsonProperty("TermRiderAmount")
	private int termRiderAmount;
	
	@JsonProperty("TermRiderAmountWoTax")
	private int termRiderAmountWoTax;
	
	@JsonProperty("WOPRider_YN")
	private String wOPRiderYN;
	
	@JsonProperty("WOPRiderPT")
	private int wOPRiderPT;
	
	@JsonProperty("WOPRiderPPT")
	private int wOPRiderPPT;
	
	@JsonProperty("WOPRiderSA")
	private long wOPRiderSA;
	
	@JsonProperty("WOPRiderAmount")
	private long wOPRiderAmount;
	
	@JsonProperty("WOPRiderAmountWoTax")
	private int wOPRiderAmountWoTax;
	
	@JsonProperty("WOPBenefitYN")
	private String wOPBenefitYN;
	
	@JsonProperty("WOPBenefitAmount")
	private int wOPBenefitAmount;
	
	@JsonProperty("WOPBenefitAmountWoTax")
	private int wOPBenefitAmountWoTax;
	
	@JsonProperty("PWBRider_YN")
	private String pWBRiderYN;
	
	@JsonProperty("PWBRiderPT")
	private int pWBRiderPT;
	
	@JsonProperty("PWBRiderPPT")
	private int pWBRiderPPT;
	
	@JsonProperty("PWBRiderSA")
	private long pWBRiderSA;
	
	@JsonProperty("PWBRiderAmount")
	private long pWBRiderAmount;
	
	@JsonProperty("PWBRiderAmountWoTax")
	private int pWBRiderAmountWoTax;
	
	@JsonProperty("PWbRiderOnDeath_YN")
	private String pWbRiderOnDeathYN;
	
	@JsonProperty("PWbRiderCIATPD_YN")
	private String pWbRiderCIATPDYN;
	
	@JsonProperty("PWBRiderDeathCIATPD_YN")
	private String pWBRiderDeathCIATPDYN;
	
	@JsonProperty("PremiumPaymentFrequency")
	private String premiumPaymentFrequency;
	
	@JsonProperty("PolicyTerm")
	private int policyTerm;
	
	@JsonProperty("PremiumPaymentTerm")
	private int premiumPaymentTerm;
	
	@JsonProperty("PremiumPaymentTermOption")
	private String premiumPaymentTermOption;
	
	@JsonProperty("DeathBenefitOption")
	private String deathBenefitOption;
	
	@JsonProperty("PayoutOptions")
	private String payoutOptions;
	
	@JsonProperty("ClaimOption")
	private String claimOption;
	
	@JsonProperty("PlanOptions")
	private String planOptions;
	
	@JsonProperty("Additional_Benefits")
	private String additionalBenefits;
	
	@JsonProperty("ABTopupBenefits_YN")
	private String aBTopupBenefitsYN;
	
	@JsonProperty("ABTopupRate")
	private int aBTopupRate;
	
	@JsonProperty("ABBetterHalfBenefit_YN")
	private String aBBetterHalfBenefitYN;
	
	@JsonProperty("AB_Better_Half_Amount")
	private int aBBetterHalfAmount;
	
	@JsonProperty("AB_Better_Half_AmountWoTax")
	private int aBBetterHalfAmountWoTax;
	
	@JsonProperty("LumpsumProportion")
	private int lumpsumProportion;
	
	@JsonProperty("MonthlyIncomeOptions")
	private String monthlyIncomeOptions;
	
	@JsonProperty("MonthlyIncomeOptionsMonths")
	private int monthlyIncomeOptionsMonths;
	
	@JsonProperty("OpportunityPayout")
	private String opportunityPayout;
	
	@JsonProperty("MaturityBenefitOption")
	private String maturityBenefitOption;
	
	@JsonProperty("PayoutPeriodYears")
	private int payoutPeriodYears;
	
	@JsonProperty("PayoutFrequency")
	private String payoutFrequency;
	
	@JsonProperty("AnnuityOptions")
	private String annuityOptions;
	
	@JsonProperty("RiskStrategyOptedFor")
	private String riskStrategyOptedFor;
	
	@JsonProperty("PolicyOption")
	private String policyOption;
	
	@JsonProperty("LittleChampBenefit_YN")
	private String littleChampBenefitYN;
	
	@JsonProperty("STP_YN")
	private String stpYn;
	
	@JsonProperty("STPType")
	private String sTPType;
	
	@JsonProperty("SWP_YN")
	private String swpYn;
	
	@JsonProperty("SWPPayable")
	private String sWPPayable;
	
	@JsonProperty("SWPWithdrawalPerAnnum")
	private String sWPWithdrawalPerAnnum;
	
	@JsonProperty("InvestmentStrategy")
	private String investmentStrategy;
	
	@JsonProperty("RisingStarBenefit_YN")
	private String risingStarBenefitYN;
	
	@JsonProperty("GuaranteedIncomeType")
	private String guaranteedIncomeType;
	
	@JsonProperty("FamilyIncomeBenefit_YN")
	private String familyIncomeBenefitYN;
	
	@JsonProperty("PaidUpAdditionsBenefit_YN")
	private String paidUpAdditionsBenefitYN;
	
	@JsonProperty("EquityLargeCapFund")
	private int equityLargeCapFund;
	
	@JsonProperty("EquityTop250Fund")
	private int equityTop250Fund;
	
	@JsonProperty("BondFund")
	private int bondFund;
	
	@JsonProperty("MoneyMaketFund")
	private int moneyMaketFund;
	
	@JsonProperty("PebasedFund")
	private int pebasedFund;
	
	@JsonProperty("ManagedFund")
	private int managedFund;
	
	@JsonProperty("EquityMidCapFund")
	private int equityMidCapFund;
	
	@JsonProperty("GiltFund")
	private int giltFund;
	
	@JsonProperty("EquityBluechipFund")
	private int equityBluechipFund;
	
	@JsonProperty("LongTermBondFund")
	private int longTermBondFund;
	
	@JsonProperty("Child_Name")
	private String childName;
	
	@JsonProperty("Child_DOB")
	private String childDOB;
	
	@JsonProperty("Spouse_Name")
	private String spouseName;
	
	@JsonProperty("Spouse_DOB")
	private String spouseDOB;
	
	@JsonProperty("SpouseSmoke_YN")
	private String spouseSmokeYN;
	
	@JsonProperty("Combo_YN")
	private String comboYN;
	
	@JsonProperty("ComboData")
	private List<ComboData> comboData;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getbIQuotationNumber() {
		return bIQuotationNumber;
	}

	public void setbIQuotationNumber(String bIQuotationNumber) {
		this.bIQuotationNumber = bIQuotationNumber;
	}

	public String getbIPDFPath() {
		return bIPDFPath;
	}

	public void setbIPDFPath(String bIPDFPath) {
		this.bIPDFPath = bIPDFPath;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getiSLAPRSameYN() {
		return iSLAPRSameYN;
	}

	public void setiSLAPRSameYN(String iSLAPRSameYN) {
		this.iSLAPRSameYN = iSLAPRSameYN;
	}

	public String getlASmokerYN() {
		return lASmokerYN;
	}

	public void setlASmokerYN(String lASmokerYN) {
		this.lASmokerYN = lASmokerYN;
	}

	public String getpRSmokerYN() {
		return pRSmokerYN;
	}

	public void setpRSmokerYN(String pRSmokerYN) {
		this.pRSmokerYN = pRSmokerYN;
	}

	public long getTotalPremiumAmount() {
		return totalPremiumAmount;
	}

	public void setTotalPremiumAmount(long totalPremiumAmount) {
		this.totalPremiumAmount = totalPremiumAmount;
	}

	public long getTotalPremiumAmountWoTax() {
		return totalPremiumAmountWoTax;
	}

	public void setTotalPremiumAmountWoTax(long totalPremiumAmountWoTax) {
		this.totalPremiumAmountWoTax = totalPremiumAmountWoTax;
	}

	public long getBasePremiumAmount() {
		return basePremiumAmount;
	}

	public void setBasePremiumAmount(long basePremiumAmount) {
		this.basePremiumAmount = basePremiumAmount;
	}

	public long getBasePremiumAmountWoTax() {
		return basePremiumAmountWoTax;
	}

	public void setBasePremiumAmountWoTax(long basePremiumAmountWoTax) {
		this.basePremiumAmountWoTax = basePremiumAmountWoTax;
	}

	public long getAllRiderPremium() {
		return allRiderPremium;
	}

	public void setAllRiderPremium(long allRiderPremium) {
		this.allRiderPremium = allRiderPremium;
	}

	public long getAllRiderPremiumWoTax() {
		return allRiderPremiumWoTax;
	}

	public void setAllRiderPremiumWoTax(long allRiderPremiumWoTax) {
		this.allRiderPremiumWoTax = allRiderPremiumWoTax;
	}

	public long getSumAssured() {
		return sumAssured;
	}

	public void setSumAssured(long sumAssured) {
		this.sumAssured = sumAssured;
	}

	public String getcIRiderYN() {
		return cIRiderYN;
	}

	public void setcIRiderYN(String cIRiderYN) {
		this.cIRiderYN = cIRiderYN;
	}

	public int getcIRiderPT() {
		return cIRiderPT;
	}

	public void setcIRiderPT(int cIRiderPT) {
		this.cIRiderPT = cIRiderPT;
	}

	public int getcIRiderPPT() {
		return cIRiderPPT;
	}

	public void setcIRiderPPT(int cIRiderPPT) {
		this.cIRiderPPT = cIRiderPPT;
	}

	public long getcIRiderSA() {
		return cIRiderSA;
	}

	public void setcIRiderSA(long cIRiderSA) {
		this.cIRiderSA = cIRiderSA;
	}

	public long getcIRiderAmount() {
		return cIRiderAmount;
	}

	public void setcIRiderAmount(long cIRiderAmount) {
		this.cIRiderAmount = cIRiderAmount;
	}

	public int getcIRiderAmountWoTax() {
		return cIRiderAmountWoTax;
	}

	public void setcIRiderAmountWoTax(int cIRiderAmountWoTax) {
		this.cIRiderAmountWoTax = cIRiderAmountWoTax;
	}

	public String getaDBRiderYN() {
		return aDBRiderYN;
	}

	public void setaDBRiderYN(String aDBRiderYN) {
		this.aDBRiderYN = aDBRiderYN;
	}

	public int getaDBRiderPT() {
		return aDBRiderPT;
	}

	public void setaDBRiderPT(int aDBRiderPT) {
		this.aDBRiderPT = aDBRiderPT;
	}

	public int getaDBRiderPPT() {
		return aDBRiderPPT;
	}

	public void setaDBRiderPPT(int aDBRiderPPT) {
		this.aDBRiderPPT = aDBRiderPPT;
	}

	public long getaDBRiderSA() {
		return aDBRiderSA;
	}

	public void setaDBRiderSA(long aDBRiderSA) {
		this.aDBRiderSA = aDBRiderSA;
	}

	public long getaDBRiderAmount() {
		return aDBRiderAmount;
	}

	public void setaDBRiderAmount(long aDBRiderAmount) {
		this.aDBRiderAmount = aDBRiderAmount;
	}

	public int getaDBRiderAmountWoTax() {
		return aDBRiderAmountWoTax;
	}

	public void setaDBRiderAmountWoTax(int aDBRiderAmountWoTax) {
		this.aDBRiderAmountWoTax = aDBRiderAmountWoTax;
	}

	public String getaTPDRiderYN() {
		return aTPDRiderYN;
	}

	public void setaTPDRiderYN(String aTPDRiderYN) {
		this.aTPDRiderYN = aTPDRiderYN;
	}

	public int getaTPDRiderPT() {
		return aTPDRiderPT;
	}

	public void setaTPDRiderPT(int aTPDRiderPT) {
		this.aTPDRiderPT = aTPDRiderPT;
	}

	public int getaTPDRiderPPT() {
		return aTPDRiderPPT;
	}

	public void setaTPDRiderPPT(int aTPDRiderPPT) {
		this.aTPDRiderPPT = aTPDRiderPPT;
	}

	public long getaTPDRiderSA() {
		return aTPDRiderSA;
	}

	public void setaTPDRiderSA(long aTPDRiderSA) {
		this.aTPDRiderSA = aTPDRiderSA;
	}

	public long getaTPDRiderAmount() {
		return aTPDRiderAmount;
	}

	public void setaTPDRiderAmount(long aTPDRiderAmount) {
		this.aTPDRiderAmount = aTPDRiderAmount;
	}

	public int getaTPDRiderAmountWoTax() {
		return aTPDRiderAmountWoTax;
	}

	public void setaTPDRiderAmountWoTax(int aTPDRiderAmountWoTax) {
		this.aTPDRiderAmountWoTax = aTPDRiderAmountWoTax;
	}

	public String gethCBRiderYN() {
		return hCBRiderYN;
	}

	public void sethCBRiderYN(String hCBRiderYN) {
		this.hCBRiderYN = hCBRiderYN;
	}

	public int gethCBRiderPT() {
		return hCBRiderPT;
	}

	public void sethCBRiderPT(int hCBRiderPT) {
		this.hCBRiderPT = hCBRiderPT;
	}

	public int gethCBRiderPPT() {
		return hCBRiderPPT;
	}

	public void sethCBRiderPPT(int hCBRiderPPT) {
		this.hCBRiderPPT = hCBRiderPPT;
	}

	public int gethCBRiderSA() {
		return hCBRiderSA;
	}

	public void sethCBRiderSA(int hCBRiderSA) {
		this.hCBRiderSA = hCBRiderSA;
	}

	public int gethCBRiderAmount() {
		return hCBRiderAmount;
	}

	public void sethCBRiderAmount(int hCBRiderAmount) {
		this.hCBRiderAmount = hCBRiderAmount;
	}

	public int gethCBRiderAmountWoTax() {
		return hCBRiderAmountWoTax;
	}

	public void sethCBRiderAmountWoTax(int hCBRiderAmountWoTax) {
		this.hCBRiderAmountWoTax = hCBRiderAmountWoTax;
	}

	public String getiBRiderYN() {
		return iBRiderYN;
	}

	public void setiBRiderYN(String iBRiderYN) {
		this.iBRiderYN = iBRiderYN;
	}

	public int getiBRiderPT() {
		return iBRiderPT;
	}

	public void setiBRiderPT(int iBRiderPT) {
		this.iBRiderPT = iBRiderPT;
	}

	public int getiBRiderPPT() {
		return iBRiderPPT;
	}

	public void setiBRiderPPT(int iBRiderPPT) {
		this.iBRiderPPT = iBRiderPPT;
	}

	public long getiBRiderSA() {
		return iBRiderSA;
	}

	public void setiBRiderSA(long iBRiderSA) {
		this.iBRiderSA = iBRiderSA;
	}

	public long getiBRiderAmount() {
		return iBRiderAmount;
	}

	public void setiBRiderAmount(long iBRiderAmount) {
		this.iBRiderAmount = iBRiderAmount;
	}

	public int getiBRiderAmountWoTax() {
		return iBRiderAmountWoTax;
	}

	public void setiBRiderAmountWoTax(int iBRiderAmountWoTax) {
		this.iBRiderAmountWoTax = iBRiderAmountWoTax;
	}

	public String getTermRiderYN() {
		return termRiderYN;
	}

	public void setTermRiderYN(String termRiderYN) {
		this.termRiderYN = termRiderYN;
	}

	public int getTermRiderPT() {
		return termRiderPT;
	}

	public void setTermRiderPT(int termRiderPT) {
		this.termRiderPT = termRiderPT;
	}

	public int getTermRiderPPT() {
		return termRiderPPT;
	}

	public void setTermRiderPPT(int termRiderPPT) {
		this.termRiderPPT = termRiderPPT;
	}

	public int getTermRiderSA() {
		return termRiderSA;
	}

	public void setTermRiderSA(int termRiderSA) {
		this.termRiderSA = termRiderSA;
	}

	public int getTermRiderAmount() {
		return termRiderAmount;
	}

	public void setTermRiderAmount(int termRiderAmount) {
		this.termRiderAmount = termRiderAmount;
	}

	public int getTermRiderAmountWoTax() {
		return termRiderAmountWoTax;
	}

	public void setTermRiderAmountWoTax(int termRiderAmountWoTax) {
		this.termRiderAmountWoTax = termRiderAmountWoTax;
	}

	public String getwOPRiderYN() {
		return wOPRiderYN;
	}

	public void setwOPRiderYN(String wOPRiderYN) {
		this.wOPRiderYN = wOPRiderYN;
	}

	public int getwOPRiderPT() {
		return wOPRiderPT;
	}

	public void setwOPRiderPT(int wOPRiderPT) {
		this.wOPRiderPT = wOPRiderPT;
	}

	public int getwOPRiderPPT() {
		return wOPRiderPPT;
	}

	public void setwOPRiderPPT(int wOPRiderPPT) {
		this.wOPRiderPPT = wOPRiderPPT;
	}

	public long getwOPRiderSA() {
		return wOPRiderSA;
	}

	public void setwOPRiderSA(long wOPRiderSA) {
		this.wOPRiderSA = wOPRiderSA;
	}

	public long getwOPRiderAmount() {
		return wOPRiderAmount;
	}

	public void setwOPRiderAmount(long wOPRiderAmount) {
		this.wOPRiderAmount = wOPRiderAmount;
	}

	public int getwOPRiderAmountWoTax() {
		return wOPRiderAmountWoTax;
	}

	public void setwOPRiderAmountWoTax(int wOPRiderAmountWoTax) {
		this.wOPRiderAmountWoTax = wOPRiderAmountWoTax;
	}

	public String getwOPBenefitYN() {
		return wOPBenefitYN;
	}

	public void setwOPBenefitYN(String wOPBenefitYN) {
		this.wOPBenefitYN = wOPBenefitYN;
	}

	public int getwOPBenefitAmount() {
		return wOPBenefitAmount;
	}

	public void setwOPBenefitAmount(int wOPBenefitAmount) {
		this.wOPBenefitAmount = wOPBenefitAmount;
	}

	public int getwOPBenefitAmountWoTax() {
		return wOPBenefitAmountWoTax;
	}

	public void setwOPBenefitAmountWoTax(int wOPBenefitAmountWoTax) {
		this.wOPBenefitAmountWoTax = wOPBenefitAmountWoTax;
	}

	public String getpWBRiderYN() {
		return pWBRiderYN;
	}

	public void setpWBRiderYN(String pWBRiderYN) {
		this.pWBRiderYN = pWBRiderYN;
	}

	public int getpWBRiderPT() {
		return pWBRiderPT;
	}

	public void setpWBRiderPT(int pWBRiderPT) {
		this.pWBRiderPT = pWBRiderPT;
	}

	public int getpWBRiderPPT() {
		return pWBRiderPPT;
	}

	public void setpWBRiderPPT(int pWBRiderPPT) {
		this.pWBRiderPPT = pWBRiderPPT;
	}

	public long getpWBRiderSA() {
		return pWBRiderSA;
	}

	public void setpWBRiderSA(long pWBRiderSA) {
		this.pWBRiderSA = pWBRiderSA;
	}

	public long getpWBRiderAmount() {
		return pWBRiderAmount;
	}

	public void setpWBRiderAmount(long pWBRiderAmount) {
		this.pWBRiderAmount = pWBRiderAmount;
	}

	public int getpWBRiderAmountWoTax() {
		return pWBRiderAmountWoTax;
	}

	public void setpWBRiderAmountWoTax(int pWBRiderAmountWoTax) {
		this.pWBRiderAmountWoTax = pWBRiderAmountWoTax;
	}

	public String getpWbRiderOnDeathYN() {
		return pWbRiderOnDeathYN;
	}

	public void setpWbRiderOnDeathYN(String pWbRiderOnDeathYN) {
		this.pWbRiderOnDeathYN = pWbRiderOnDeathYN;
	}

	public String getpWbRiderCIATPDYN() {
		return pWbRiderCIATPDYN;
	}

	public void setpWbRiderCIATPDYN(String pWbRiderCIATPDYN) {
		this.pWbRiderCIATPDYN = pWbRiderCIATPDYN;
	}

	public String getpWBRiderDeathCIATPDYN() {
		return pWBRiderDeathCIATPDYN;
	}

	public void setpWBRiderDeathCIATPDYN(String pWBRiderDeathCIATPDYN) {
		this.pWBRiderDeathCIATPDYN = pWBRiderDeathCIATPDYN;
	}

	public String getPremiumPaymentFrequency() {
		return premiumPaymentFrequency;
	}

	public void setPremiumPaymentFrequency(String premiumPaymentFrequency) {
		this.premiumPaymentFrequency = premiumPaymentFrequency;
	}

	public int getPolicyTerm() {
		return policyTerm;
	}

	public void setPolicyTerm(int policyTerm) {
		this.policyTerm = policyTerm;
	}

	public int getPremiumPaymentTerm() {
		return premiumPaymentTerm;
	}

	public void setPremiumPaymentTerm(int premiumPaymentTerm) {
		this.premiumPaymentTerm = premiumPaymentTerm;
	}

	public String getPremiumPaymentTermOption() {
		return premiumPaymentTermOption;
	}

	public void setPremiumPaymentTermOption(String premiumPaymentTermOption) {
		this.premiumPaymentTermOption = premiumPaymentTermOption;
	}

	public String getDeathBenefitOption() {
		return deathBenefitOption;
	}

	public void setDeathBenefitOption(String deathBenefitOption) {
		this.deathBenefitOption = deathBenefitOption;
	}

	public String getPayoutOptions() {
		return payoutOptions;
	}

	public void setPayoutOptions(String payoutOptions) {
		this.payoutOptions = payoutOptions;
	}

	public String getClaimOption() {
		return claimOption;
	}

	public void setClaimOption(String claimOption) {
		this.claimOption = claimOption;
	}

	public String getPlanOptions() {
		return planOptions;
	}

	public void setPlanOptions(String planOptions) {
		this.planOptions = planOptions;
	}

	public String getAdditionalBenefits() {
		return additionalBenefits;
	}

	public void setAdditionalBenefits(String additionalBenefits) {
		this.additionalBenefits = additionalBenefits;
	}

	public String getaBTopupBenefitsYN() {
		return aBTopupBenefitsYN;
	}

	public void setaBTopupBenefitsYN(String aBTopupBenefitsYN) {
		this.aBTopupBenefitsYN = aBTopupBenefitsYN;
	}

	public int getaBTopupRate() {
		return aBTopupRate;
	}

	public void setaBTopupRate(int aBTopupRate) {
		this.aBTopupRate = aBTopupRate;
	}

	public String getaBBetterHalfBenefitYN() {
		return aBBetterHalfBenefitYN;
	}

	public void setaBBetterHalfBenefitYN(String aBBetterHalfBenefitYN) {
		this.aBBetterHalfBenefitYN = aBBetterHalfBenefitYN;
	}

	public int getaBBetterHalfAmount() {
		return aBBetterHalfAmount;
	}

	public void setaBBetterHalfAmount(int aBBetterHalfAmount) {
		this.aBBetterHalfAmount = aBBetterHalfAmount;
	}

	public int getaBBetterHalfAmountWoTax() {
		return aBBetterHalfAmountWoTax;
	}

	public void setaBBetterHalfAmountWoTax(int aBBetterHalfAmountWoTax) {
		this.aBBetterHalfAmountWoTax = aBBetterHalfAmountWoTax;
	}

	public int getLumpsumProportion() {
		return lumpsumProportion;
	}

	public void setLumpsumProportion(int lumpsumProportion) {
		this.lumpsumProportion = lumpsumProportion;
	}

	public String getMonthlyIncomeOptions() {
		return monthlyIncomeOptions;
	}

	public void setMonthlyIncomeOptions(String monthlyIncomeOptions) {
		this.monthlyIncomeOptions = monthlyIncomeOptions;
	}

	public int getMonthlyIncomeOptionsMonths() {
		return monthlyIncomeOptionsMonths;
	}

	public void setMonthlyIncomeOptionsMonths(int monthlyIncomeOptionsMonths) {
		this.monthlyIncomeOptionsMonths = monthlyIncomeOptionsMonths;
	}

	public String getOpportunityPayout() {
		return opportunityPayout;
	}

	public void setOpportunityPayout(String opportunityPayout) {
		this.opportunityPayout = opportunityPayout;
	}

	public String getMaturityBenefitOption() {
		return maturityBenefitOption;
	}

	public void setMaturityBenefitOption(String maturityBenefitOption) {
		this.maturityBenefitOption = maturityBenefitOption;
	}

	public int getPayoutPeriodYears() {
		return payoutPeriodYears;
	}

	public void setPayoutPeriodYears(int payoutPeriodYears) {
		this.payoutPeriodYears = payoutPeriodYears;
	}

	public String getPayoutFrequency() {
		return payoutFrequency;
	}

	public void setPayoutFrequency(String payoutFrequency) {
		this.payoutFrequency = payoutFrequency;
	}

	public String getAnnuityOptions() {
		return annuityOptions;
	}

	public void setAnnuityOptions(String annuityOptions) {
		this.annuityOptions = annuityOptions;
	}

	public String getRiskStrategyOptedFor() {
		return riskStrategyOptedFor;
	}

	public void setRiskStrategyOptedFor(String riskStrategyOptedFor) {
		this.riskStrategyOptedFor = riskStrategyOptedFor;
	}

	public String getPolicyOption() {
		return policyOption;
	}

	public void setPolicyOption(String policyOption) {
		this.policyOption = policyOption;
	}

	public String getLittleChampBenefitYN() {
		return littleChampBenefitYN;
	}

	public void setLittleChampBenefitYN(String littleChampBenefitYN) {
		this.littleChampBenefitYN = littleChampBenefitYN;
	}

	public String getStpYn() {
		return stpYn;
	}

	public void setStpYn(String stpYn) {
		this.stpYn = stpYn;
	}

	public String getsTPType() {
		return sTPType;
	}

	public void setsTPType(String sTPType) {
		this.sTPType = sTPType;
	}

	public String getSwpYn() {
		return swpYn;
	}

	public void setSwpYn(String swpYn) {
		this.swpYn = swpYn;
	}

	public String getsWPPayable() {
		return sWPPayable;
	}

	public void setsWPPayable(String sWPPayable) {
		this.sWPPayable = sWPPayable;
	}

	public String getsWPWithdrawalPerAnnum() {
		return sWPWithdrawalPerAnnum;
	}

	public void setsWPWithdrawalPerAnnum(String sWPWithdrawalPerAnnum) {
		this.sWPWithdrawalPerAnnum = sWPWithdrawalPerAnnum;
	}

	public String getInvestmentStrategy() {
		return investmentStrategy;
	}

	public void setInvestmentStrategy(String investmentStrategy) {
		this.investmentStrategy = investmentStrategy;
	}

	public String getRisingStarBenefitYN() {
		return risingStarBenefitYN;
	}

	public void setRisingStarBenefitYN(String risingStarBenefitYN) {
		this.risingStarBenefitYN = risingStarBenefitYN;
	}

	public String getGuaranteedIncomeType() {
		return guaranteedIncomeType;
	}

	public void setGuaranteedIncomeType(String guaranteedIncomeType) {
		this.guaranteedIncomeType = guaranteedIncomeType;
	}

	public String getFamilyIncomeBenefitYN() {
		return familyIncomeBenefitYN;
	}

	public void setFamilyIncomeBenefitYN(String familyIncomeBenefitYN) {
		this.familyIncomeBenefitYN = familyIncomeBenefitYN;
	}

	public String getPaidUpAdditionsBenefitYN() {
		return paidUpAdditionsBenefitYN;
	}

	public void setPaidUpAdditionsBenefitYN(String paidUpAdditionsBenefitYN) {
		this.paidUpAdditionsBenefitYN = paidUpAdditionsBenefitYN;
	}

	public int getEquityLargeCapFund() {
		return equityLargeCapFund;
	}

	public void setEquityLargeCapFund(int equityLargeCapFund) {
		this.equityLargeCapFund = equityLargeCapFund;
	}

	public int getEquityTop250Fund() {
		return equityTop250Fund;
	}

	public void setEquityTop250Fund(int equityTop250Fund) {
		this.equityTop250Fund = equityTop250Fund;
	}

	public int getBondFund() {
		return bondFund;
	}

	public void setBondFund(int bondFund) {
		this.bondFund = bondFund;
	}

	public int getMoneyMaketFund() {
		return moneyMaketFund;
	}

	public void setMoneyMaketFund(int moneyMaketFund) {
		this.moneyMaketFund = moneyMaketFund;
	}

	public int getPebasedFund() {
		return pebasedFund;
	}

	public void setPebasedFund(int pebasedFund) {
		this.pebasedFund = pebasedFund;
	}

	public int getManagedFund() {
		return managedFund;
	}

	public void setManagedFund(int managedFund) {
		this.managedFund = managedFund;
	}

	public int getEquityMidCapFund() {
		return equityMidCapFund;
	}

	public void setEquityMidCapFund(int equityMidCapFund) {
		this.equityMidCapFund = equityMidCapFund;
	}

	public int getGiltFund() {
		return giltFund;
	}

	public void setGiltFund(int giltFund) {
		this.giltFund = giltFund;
	}

	public int getEquityBluechipFund() {
		return equityBluechipFund;
	}

	public void setEquityBluechipFund(int equityBluechipFund) {
		this.equityBluechipFund = equityBluechipFund;
	}

	public int getLongTermBondFund() {
		return longTermBondFund;
	}

	public void setLongTermBondFund(int longTermBondFund) {
		this.longTermBondFund = longTermBondFund;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public String getChildDOB() {
		return childDOB;
	}

	public void setChildDOB(String childDOB) {
		this.childDOB = childDOB;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public String getSpouseDOB() {
		return spouseDOB;
	}

	public void setSpouseDOB(String spouseDOB) {
		this.spouseDOB = spouseDOB;
	}

	public String getSpouseSmokeYN() {
		return spouseSmokeYN;
	}

	public void setSpouseSmokeYN(String spouseSmokeYN) {
		this.spouseSmokeYN = spouseSmokeYN;
	}

	public String getComboYN() {
		return comboYN;
	}

	public void setComboYN(String comboYN) {
		this.comboYN = comboYN;
	}

	public List<ComboData> getComboData() {
		return comboData;
	}

	public void setComboData(List<ComboData> comboData) {
		this.comboData = comboData;
	}
		
}
