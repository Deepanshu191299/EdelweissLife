package in.edelweiss.tokio.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.liferay.petra.string.StringPool;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BIRequest {

	@JsonProperty("productDetails")
	private ProductDetails productDetails;
	
	@JsonProperty("customerDetails")
	private CustomerDetails customerDetails;
	
	@JsonProperty("companyDetails")
	private CompanyDetails companyDetails;

	public BIRequest() {
		
	}
	
	public BIRequest(ProductDetails productDetails, CustomerDetails customerDetails, CompanyDetails companyDetails) {
		this.productDetails = productDetails;
		this.customerDetails = customerDetails;
		this.companyDetails = companyDetails;
	}

	public ProductDetails getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
	}

	public CustomerDetails getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(CustomerDetails customerDetails) {
		this.customerDetails = customerDetails;
	}

	public CompanyDetails getCompanyDetails() {
		return companyDetails;
	}

	public void setCompanyDetails(CompanyDetails companyDetails) {
		this.companyDetails = companyDetails;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class ProductDetails {

		@JsonProperty("product")
		private Product product;
		
		@JsonProperty("comboDetails")
		private ComboDetails comboDetails;
		
		public ProductDetails() {
			
		}
		public ProductDetails(Product product, ComboDetails comboDetails) {
			this.product = product;
			this.comboDetails = comboDetails;
		}
		
		public Product getProduct() {
			return product;
		}
		public void setProduct(Product product) {
			this.product = product;
		}
		public ComboDetails getComboDetails() {
			return comboDetails;
		}
		public void setComboDetails(ComboDetails comboDetails) {
			this.comboDetails = comboDetails;
		}

		@Override
		public String toString() {
			return "ProductDetails [product=" + product + ", comboDetails=" + comboDetails + "]";
		}
	}
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class CustomerDetails {

	    @JsonProperty("liDetails")
	    private LiDetails liDetails;
	    
	    @JsonProperty("proposerDetails")
	    private ProposerDetails proposerDetails;
	    
	    @JsonProperty("agentDetails")
	    private AgentDetails agentDetails;
	    
	    
	    public CustomerDetails() {
		}
	    
		public CustomerDetails(LiDetails liDetails, ProposerDetails proposerDetails, AgentDetails agentDetails) {
			this.liDetails = liDetails;
			this.proposerDetails = proposerDetails;
			this.agentDetails = agentDetails;
		}

		public LiDetails getLiDetails() {
			return liDetails;
		}

		public void setLiDetails(LiDetails liDetails) {
			this.liDetails = liDetails;
		}

		public ProposerDetails getProposerDetails() {
			return proposerDetails;
		}

		public void setProposerDetails(ProposerDetails proposerDetails) {
			this.proposerDetails = proposerDetails;
		}

		public AgentDetails getAgentDetails() {
			return agentDetails;
		}

		public void setAgentDetails(AgentDetails agentDetails) {
			this.agentDetails = agentDetails;
		}

		@Override
		public String toString() {
			return "CustomerDetails [liDetails=" + liDetails + ", proposerDetails=" + proposerDetails
					+ ", agentDetails=" + agentDetails + "]";
		}
	}
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class CompanyDetails {

		@JsonProperty("COMPANY_STATE")
		private int companyState;
		
		@JsonProperty("GSTIN")
		private String gstin;
		
		@JsonProperty("GSTIN_Number")
		private int gstinNumber;
		
		public CompanyDetails() {
		}
		
		public CompanyDetails(int companyState, String gstin, int gstinNumber) {
			this.companyState = companyState;
			this.gstin = gstin;
			this.gstinNumber = gstinNumber;
		}

		public int getCompanyState() {
			return companyState;
		}

		public void setCompanyState(int companyState) {
			this.companyState = companyState;
		}

		public String getGstin() {
			return gstin;
		}

		public void setGstin(String gstin) {
			this.gstin = gstin;
		}

		public int getGstinNumber() {
			return gstinNumber;
		}

		public void setGstinNumber(int gstinNumber) {
			this.gstinNumber = gstinNumber;
		}
		
		@Override
		public String toString() {
			return "CompanyDetails [companyState=" + companyState + ", gstin=" + gstin + ", gstinNumber=" + gstinNumber
					+ "]";
		}
		
	}
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class Product {
		
		@JsonProperty("SA_Multiple")	
		private String saMultiple;
		
		@JsonProperty("EmployeeCode")	
		private String employeeCode = "0";
		
		@JsonProperty("PR_ID")
		private String prId;
		
		@JsonProperty("CashFlow")
		private String cashFlow = "N";
		
		@JsonProperty("INPUT_MODE")
		private String inputMode;
		
		@JsonProperty("PR_PT")
		private String prPt;
		
		@JsonProperty("PR_PPT")
		private String prPpt;
		
		@JsonProperty("PR_ANNPREM")
		private long prAnnPrem;
		
		@JsonProperty("PR_MI")
		private String prMi = "0";
		
		@JsonProperty("PR_SA")
		private String prSa = "0";
		
		@JsonProperty("PR_SAMF")
		private String prSamf;
		
		@JsonProperty("PR_ModalPrem")
		private String prModalPrem = StringPool.BLANK;
		
		@JsonProperty("category")	
		private String category = "1";	
			
		@JsonProperty("PR_CHANNEL")	
		private String prChannel = "1";	
			
		@JsonProperty("REQUESTSOURCE")	
		private String requestSource = "4";	
			
		@JsonProperty("isPdfByte")	
		private String isPdfbyte = "Y";	
			
		@JsonProperty("emr_rate")	
		private String emrRate = "1.00";	
			
		@JsonProperty("flat_rate")	
		private String flatRate = "0";	
			
		@JsonProperty("EmrRatePH")	
		private String emrRateph ="1";	
			
		@JsonProperty("FlatRatePH")	
		private String flatRateph = "0";	
			
		@JsonProperty("PrDeclineDecision1")	
		private String prDeclineDecision1 = "-1";
		
		@JsonProperty("PrDeclineDecisionReason1")
		private String prDeclineDecisionReason1 = "-1";
		
		@JsonProperty("FamilyIncomeBenefitOption")
		private String familyIncomeBenefitOption ;
		
		@JsonProperty("IncomeBenefitPayoutFrequency")
		private String incomeBenefitPayoutFrequency;
		
		@JsonProperty("NriDeclaration")
		private String nriDeclaration ;
		
		@JsonProperty("ZP_NATIONALITY")
		private String zP_NATIONALITY ;
		
		@JsonProperty("fundDetails")
		private FundDetails fundDetails;
		
		@JsonProperty("policyOPtions")
		private PolicyOptions policyOptions;
		
		@JsonProperty("riderDetails")
		private RiderDetails riderDetails;

		public Product() {
			
		}
		

		public Product(String saMultiple, String employeeCode, String prId, String cashFlow, String inputMode,
				String prPt, String prPpt, long prAnnPrem, String prMi, String prSa, String prSamf, String prModalPrem,
				String category, String prChannel, String requestSource, String isPdfbyte, String emrRate,
				String flatRate, String emrRateph, String flatRateph, String prDeclineDecision1,
				String prDeclineDecisionReason1, String familyIncomeBenefitOption, String incomeBenefitPayoutFrequency,
				String nriDeclaration, String zP_NATIONALITY, FundDetails fundDetails, PolicyOptions policyOptions,
				RiderDetails riderDetails) {
			super();
			this.saMultiple = saMultiple;
			this.employeeCode = employeeCode;
			this.prId = prId;
			this.cashFlow = cashFlow;
			this.inputMode = inputMode;
			this.prPt = prPt;
			this.prPpt = prPpt;
			this.prAnnPrem = prAnnPrem;
			this.prMi = prMi;
			this.prSa = prSa;
			this.prSamf = prSamf;
			this.prModalPrem = prModalPrem;
			this.category = category;
			this.prChannel = prChannel;
			this.requestSource = requestSource;
			this.isPdfbyte = isPdfbyte;
			this.emrRate = emrRate;
			this.flatRate = flatRate;
			this.emrRateph = emrRateph;
			this.flatRateph = flatRateph;
			this.prDeclineDecision1 = prDeclineDecision1;
			this.prDeclineDecisionReason1 = prDeclineDecisionReason1;
			this.familyIncomeBenefitOption = familyIncomeBenefitOption;
			this.incomeBenefitPayoutFrequency = incomeBenefitPayoutFrequency;
			this.nriDeclaration = nriDeclaration;
			this.zP_NATIONALITY = zP_NATIONALITY;
			this.fundDetails = fundDetails;
			this.policyOptions = policyOptions;
			this.riderDetails = riderDetails;
		}


		public String getSaMultiple() {
			return saMultiple;
		}
		public void setSaMultiple(String saMultiple) {
			this.saMultiple = saMultiple;
		}
		
		public String getFamilyIncomeBenefitOption() {
			return familyIncomeBenefitOption;
		}
		public void setFamilyIncomeBenefitOption(String familyIncomeBenefitOption) {
			this.familyIncomeBenefitOption = familyIncomeBenefitOption;
		}
		public String getIncomeBenefitPayoutFrequency() {
			return incomeBenefitPayoutFrequency;
		}
		public void setIncomeBenefitPayoutFrequency(String incomeBenefitPayoutFrequency) {
			this.incomeBenefitPayoutFrequency = incomeBenefitPayoutFrequency;
		}
		public String getNriDeclaration() {
			return nriDeclaration;
		}
		public void setNriDeclaration(String nriDeclaration) {
			this.nriDeclaration = nriDeclaration;
		}
		public String getzP_NATIONALITY() {
			return zP_NATIONALITY;
		}
		public void setzP_NATIONALITY(String zP_NATIONALITY) {
			this.zP_NATIONALITY = zP_NATIONALITY;
		}
		public RiderDetails getRiderDetails() {
			return riderDetails;
		}
		public void setRiderDetails(RiderDetails riderDetails) {
			this.riderDetails = riderDetails;
		}
		public String getPrId() {
			return prId;
		}

		public void setPrId(String prId) {
			this.prId = prId;
		}

		public String getCashFlow() {
			return cashFlow;
		}

		public void setCashFlow(String cashFlow) {
			this.cashFlow = cashFlow;
		}

		public String getInputMode() {
			return inputMode;
		}

		public void setInputMode(String inputMode) {
			this.inputMode = inputMode;
		}

		public String getPrPt() {
			return prPt;
		}

		public void setPrPt(String prPt) {
			this.prPt = prPt;
		}

		public String getPrPpt() {
			return prPpt;
		}

		public void setPrPpt(String prPpt) {
			this.prPpt = prPpt;
		}

		public long getPrAnnPrem() {
			return prAnnPrem;
		}

		public void setPrAnnPrem(long prAnnPrem) {
			this.prAnnPrem = prAnnPrem;
		}

		public String getPrMi() {
			return prMi;
		}

		public void setPrMi(String prMi) {
			this.prMi = prMi;
		}

		public String getPrSa() {
			return prSa;
		}

		public void setPrSa(String prSa) {
			this.prSa = prSa;
		}

		public String getPrSamf() {
			return prSamf;
		}

		public void setPrSamf(String prSamf) {
			this.prSamf = prSamf;
		}

		public String getPrModalPrem() {
			return prModalPrem;
		}

		public void setPrModalPrem(String prModalPrem) {
			this.prModalPrem = prModalPrem;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public String getPrChannel() {
			return prChannel;
		}

		public void setPrChannel(String prChannel) {
			this.prChannel = prChannel;
		}

		public String getRequestSource() {
			return requestSource;
		}

		public void setRequestSource(String requestSource) {
			this.requestSource = requestSource;
		}

		public String getIsPdfbyte() {
			return isPdfbyte;
		}

		public void setIsPdfbyte(String isPdfbyte) {
			this.isPdfbyte = isPdfbyte;
		}

		public String getEmrRate() {
			return emrRate;
		}

		public void setEmrRate(String emrRate) {
			this.emrRate = emrRate;
		}

		public String getFlatRate() {
			return flatRate;
		}

		public void setFlatRate(String flatRate) {
			this.flatRate = flatRate;
		}

		public String getEmrRateph() {
			return emrRateph;
		}

		public void setEmrRateph(String emrRateph) {
			this.emrRateph = emrRateph;
		}

		public String getFlatRateph() {
			return flatRateph;
		}

		public void setFlatRateph(String flatRateph) {
			this.flatRateph = flatRateph;
		}

		public String getPrDeclineDecision1() {
			return prDeclineDecision1;
		}

		public void setPrDeclineDecision1(String prDeclineDecision1) {
			this.prDeclineDecision1 = prDeclineDecision1;
		}

		public String getPrDeclineDecisionReason1() {
			return prDeclineDecisionReason1;
		}

		public void setPrDeclineDecisionReason1(String prDeclineDecisionReason1) {
			this.prDeclineDecisionReason1 = prDeclineDecisionReason1;
		}

		public FundDetails getFundDetails() {
			return fundDetails;
		}

		public void setFundDetails(FundDetails fundDetails) {
			this.fundDetails = fundDetails;
		}

		public PolicyOptions getPolicyOptions() {
			return policyOptions;
		}

		public void setPolicyOptions(PolicyOptions policyOptions) {
			this.policyOptions = policyOptions;
		}
		
		public String getEmployeeCode() {
			return employeeCode;
		}
		
		public void setEmployeeCode(String employeeCode) {
			this.employeeCode = employeeCode;
		}
		@Override
		public String toString() {
			return "Product [saMultiple=" + saMultiple + ", employeeCode=" + employeeCode + ", prId=" + prId
					+ ", cashFlow=" + cashFlow + ", inputMode=" + inputMode + ", prPt=" + prPt + ", prPpt=" + prPpt
					+ ", prAnnPrem=" + prAnnPrem + ", prMi=" + prMi + ", prSa=" + prSa + ", prSamf=" + prSamf
					+ ", prModalPrem=" + prModalPrem + ", category=" + category + ", prChannel=" + prChannel
					+ ", requestSource=" + requestSource + ", isPdfbyte=" + isPdfbyte + ", emrRate=" + emrRate
					+ ", flatRate=" + flatRate + ", emrRateph=" + emrRateph + ", flatRateph=" + flatRateph
					+ ", prDeclineDecision1=" + prDeclineDecision1 + ", prDeclineDecisionReason1="
					+ prDeclineDecisionReason1 + ", familyIncomeBenefitOption=" + familyIncomeBenefitOption
					+ ", incomeBenefitPayoutFrequency=" + incomeBenefitPayoutFrequency + ", nriDeclaration="
					+ nriDeclaration + ", zP_NATIONALITY=" + zP_NATIONALITY + ", fundDetails=" + fundDetails
					+ ", policyOptions=" + policyOptions + ", riderDetails=" + riderDetails + "]";
		}
		
	}
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class LiDetails {

	    @JsonProperty("LI_NAME")
	    private String liName;
	    
	    @JsonProperty("LI_ENTRY_AGE")
	    private int liEntryAge;
	    
	    @JsonProperty("LI_DOB")
	    private String liDob;
	    
	    @JsonProperty("LI_GENDER")
	    private String liGender;
	    
	    @JsonProperty("LI_STATE")
	    private String liState;
	    
	    @JsonProperty("LI_MOBILENO")
	    private String liMobileNo;
	    
	    @JsonProperty("LI_EMAILID")
	    private String liEmailId;

	    
	    public LiDetails() {
	    	
		}
	    
		public LiDetails(String liName, int liEntryAge, String liDob, String liGender, String liState,
				String liMobileNo, String liEmailId) {
			this.liName = liName;
			this.liEntryAge = liEntryAge;
			this.liDob = liDob;
			this.liGender = liGender;
			this.liState = liState;
			this.liMobileNo = liMobileNo;
			this.liEmailId = liEmailId;
		}

		public String getLiName() {
			return liName;
		}

		public void setLiName(String liName) {
			this.liName = liName;
		}

		public int getLiEntryAge() {
			return liEntryAge;
		}

		public void setLiEntryAge(int liEntryAge) {
			this.liEntryAge = liEntryAge;
		}

		public String getLiDob() {
			return liDob;
		}

		public void setLiDob(String liDob) {
			this.liDob = liDob;
		}

		public String getLiGender() {
			return liGender;
		}

		public void setLiGender(String liGender) {
			this.liGender = liGender;
		}

		public String getLiState() {
			return liState;
		}

		public void setLiState(String liState) {
			this.liState = liState;
		}

		public String getLiMobileNo() {
			return liMobileNo;
		}

		public void setLiMobileNo(String liMobileNo) {
			this.liMobileNo = liMobileNo;
		}

		public String getLiEmailId() {
			return liEmailId;
		}

		public void setLiEmailId(String liEmailId) {
			this.liEmailId = liEmailId;
		}

		@Override
		public String toString() {
			return "LiDetails [liName=" + liName + ", liEntryAge=" + liEntryAge + ", liDob=" + liDob + ", liGender="
					+ liGender + ", liState=" + liState + ", liMobileNo=" + liMobileNo + ", liEmailId=" + liEmailId
					+ "]";
		}
	}
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class ProposerDetails {

	    @JsonProperty("SameProposer")
	    private String sameProposer;
	    
	    @JsonProperty("PROPOSER_NAME")
	    private String proposerName;
	    
	    @JsonProperty("PROPOSER_AGE")
	    private int proposerAge;
	    
	    @JsonProperty("PROPOSER_DOB")
	    private String proposerDob;
	    
	    @JsonProperty("PROPOSER_GENDER")
	    private String proposerGender;

	    public ProposerDetails() {
		}
	    
		public ProposerDetails(String sameProposer, String proposerName, int proposerAge, String proposerDob,
				String proposerGender) {
			super();
			this.sameProposer = sameProposer;
			this.proposerName = proposerName;
			this.proposerAge = proposerAge;
			this.proposerDob = proposerDob;
			this.proposerGender = proposerGender;
		}

		public String getSameProposer() {
			return sameProposer;
		}

		public void setSameProposer(String sameProposer) {
			this.sameProposer = sameProposer;
		}

		public String getProposerName() {
			return proposerName;
		}

		public void setProposerName(String proposerName) {
			this.proposerName = proposerName;
		}

		public int getProposerAge() {
			return proposerAge;
		}

		public void setProposerAge(int proposerAge) {
			this.proposerAge = proposerAge;
		}

		public String getProposerDob() {
			return proposerDob;
		}

		public void setProposerDob(String proposerDob) {
			this.proposerDob = proposerDob;
		}

		public String getProposerGender() {
			return proposerGender;
		}

		public void setProposerGender(String proposerGender) {
			this.proposerGender = proposerGender;
		}

		@Override
		public String toString() {
			return "ProposerDetails [sameProposer=" + sameProposer + ", proposerName=" + proposerName + ", proposerAge="
					+ proposerAge + ", proposerDob=" + proposerDob + ", proposerGender=" + proposerGender + "]";
		}
	}
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class AgentDetails {

		@JsonProperty("AGENT_ID")
	    private String agentId;
	    
	    @JsonProperty("AgentName")
	    private String agentName;
	    
	    @JsonProperty("AgentLocation")
	    private String agentLocation;
	    
	    public AgentDetails() {
		}
	    
		public AgentDetails(String agentId, String agentName, String agentLocation) {
			this.agentId = agentId;
			this.agentName = agentName;
			this.agentLocation = agentLocation;
		}
		 

		public String getAgentId() {
			return agentId;
		}

		public void setAgentId(String agentId) {
			this.agentId = agentId;
		}

		public String getAgentName() {
			return agentName;
		}

		public void setAgentName(String agentName) {
			this.agentName = agentName;
		}

		public String getAgentLocation() {
			return agentLocation;
		}

		public void setAgentLocation(String agentLocation) {
			this.agentLocation = agentLocation;
		}

		@Override
		public String toString() {
			return "AgentDetails [agentId=" + agentId + ", agentName=" + agentName + ", agentLocation=" + agentLocation
					+ "]";
		}
	}
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class FundDetails {

	    @JsonProperty("FUNDSTRATEGYID")
	    private String fundStrategyId;
	    
	    @JsonProperty("FundValuetobeWithdrawn")
	    private String fundValuetobeWithdrawn;
	    
	    @JsonProperty("EquityLargeCapFund")
	    private String equityLargeCapFund;
	    
	    @JsonProperty("EquityTop250Fund")
	    private String equityTop250Fund;
	    
	    @JsonProperty("BondFund")
	    private String bondFund;
	    
	    @JsonProperty("ManagedFund")
	    private String managedFund;
	    
	    @JsonProperty("EquityMidCapFund")
	    private String equityMidCapFund;
	    
	    @JsonProperty("EquityBlueChipFund")
	    private String equityBlueChipFund;
	    
	    @JsonProperty("GILTFund")
	    private String giltFund;
	    
	    @JsonProperty("LongTermBondFund")
	    private int longTermBondFund;
	    
	    public FundDetails() {
	    	
	    }

		public FundDetails(String fundStrategyId, String fundValuetobeWithdrawn, String equityLargeCapFund,
				String equityTop250Fund, String bondFund, String managedFund, String equityMidCapFund,
				String equityBlueChipFund, String giltFund, int longTermBondFund) {
			this.fundStrategyId = fundStrategyId;
			this.fundValuetobeWithdrawn = fundValuetobeWithdrawn;
			this.equityLargeCapFund = equityLargeCapFund;
			this.equityTop250Fund = equityTop250Fund;
			this.bondFund = bondFund;
			this.managedFund = managedFund;
			this.equityMidCapFund = equityMidCapFund;
			this.equityBlueChipFund = equityBlueChipFund;
			this.giltFund = giltFund;
			this.longTermBondFund = longTermBondFund;
		}

		public String getFundStrategyId() {
			return fundStrategyId;
		}

		public void setFundStrategyId(String fundStrategyId) {
			this.fundStrategyId = fundStrategyId;
		}

		public String getFundValuetobeWithdrawn() {
			return fundValuetobeWithdrawn;
		}

		public void setFundValuetobeWithdrawn(String fundValuetobeWithdrawn) {
			this.fundValuetobeWithdrawn = fundValuetobeWithdrawn;
		}

		public String getEquityLargeCapFund() {
			return equityLargeCapFund;
		}

		public void setEquityLargeCapFund(String equityLargeCapFund) {
			this.equityLargeCapFund = equityLargeCapFund;
		}

		public String getEquityTop250Fund() {
			return equityTop250Fund;
		}

		public void setEquityTop250Fund(String equityTop250Fund) {
			this.equityTop250Fund = equityTop250Fund;
		}

		public String getBondFund() {
			return bondFund;
		}

		public void setBondFund(String bondFund) {
			this.bondFund = bondFund;
		}

		public String getManagedFund() {
			return managedFund;
		}

		public void setManagedFund(String managedFund) {
			this.managedFund = managedFund;
		}

		public String getEquityMidCapFund() {
			return equityMidCapFund;
		}

		public void setEquityMidCapFund(String equityMidCapFund) {
			this.equityMidCapFund = equityMidCapFund;
		}

		public String getEquityBlueChipFund() {
			return equityBlueChipFund;
		}

		public void setEquityBlueChipFund(String equityBlueChipFund) {
			this.equityBlueChipFund = equityBlueChipFund;
		}

		public String getGiltFund() {
			return giltFund;
		}

		public void setGiltFund(String giltFund) {
			this.giltFund = giltFund;
		}

		public int getLongTermBondFund() {
			return longTermBondFund;
		}

		public void setLongTermBondFund(int longTermBondFund) {
			this.longTermBondFund = longTermBondFund;
		}

		@Override
		public String toString() {
			return "FundDetails [fundStrategyId=" + fundStrategyId + ", fundValuetobeWithdrawn="
					+ fundValuetobeWithdrawn + ", equityLargeCapFund=" + equityLargeCapFund + ", equityTop250Fund="
					+ equityTop250Fund + ", bondFund=" + bondFund + ", managedFund=" + managedFund
					+ ", equityMidCapFund=" + equityMidCapFund + ", equityBlueChipFund=" + equityBlueChipFund
					+ ", giltFund=" + giltFund + ", longTermBondFund=" + longTermBondFund + "]";
		}

	}
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class PolicyOptions {

		@JsonProperty("Increasing")
	    private String increasing;
		
		@JsonProperty("IncomeBenefitPayoutStartYear")
	    private String incomeBenefitPayoutStartYear;
		
		@JsonProperty("Cashback")
	    private String cashback;
		
		@JsonProperty("FirstPolicyYearofAnnualisedPremium")
	    private String firstPolicyYearofAnnualisedPremium;
		
		public String getIncreasing() {
			return increasing;
		}



		public void setIncreasing(String increasing) {
			this.increasing = increasing;
		}



		public String getIncomeBenefitPayoutStartYear() {
			return incomeBenefitPayoutStartYear;
		}



		public void setIncomeBenefitPayoutStartYear(String incomeBenefitPayoutStartYear) {
			this.incomeBenefitPayoutStartYear = incomeBenefitPayoutStartYear;
		}



		public String getCashback() {
			return cashback;
		}



		public void setCashback(String cashback) {
			this.cashback = cashback;
		}



		public String getFirstPolicyYearofAnnualisedPremium() {
			return firstPolicyYearofAnnualisedPremium;
		}



		public void setFirstPolicyYearofAnnualisedPremium(String firstPolicyYearofAnnualisedPremium) {
			this.firstPolicyYearofAnnualisedPremium = firstPolicyYearofAnnualisedPremium;
		}



		public String getCashbackPercentAnnualisedPremium() {
			return cashbackPercentAnnualisedPremium;
		}



		public void setCashbackPercentAnnualisedPremium(String cashbackPercentAnnualisedPremium) {
			this.cashbackPercentAnnualisedPremium = cashbackPercentAnnualisedPremium;
		}



		public String getTimingofIncomeFromIncomeStartYear() {
			return timingofIncomeFromIncomeStartYear;
		}



		public void setTimingofIncomeFromIncomeStartYear(String timingofIncomeFromIncomeStartYear) {
			this.timingofIncomeFromIncomeStartYear = timingofIncomeFromIncomeStartYear;
		}



		@JsonProperty("CashbackPercentAnnualisedPremium")
	    private String cashbackPercentAnnualisedPremium;
		
		@JsonProperty("TimingofIncomeFromIncomeStartYear")
	    private String timingofIncomeFromIncomeStartYear;
		
	    @JsonProperty("PlanOption")
	    private String planOption;
	    
	    @JsonProperty("LittleStarBenefit")
	    private String littleStarBenefit;
	    
	    @JsonProperty("SystematicWithdrawalPlan")
	    private String systematicWithdrawalPlan;
	    
	    @JsonProperty("SWPfrequency")
	    private String swpFrequency;
	    
	    @JsonProperty("PolicyYearfromwhichSWPpayable")
	    private String policyYearFromWhichSwpPayable;
	    
	    @JsonProperty("IncomeBenefitPayoutType")
		private String incomeBenefitPayoutType ;
		
		@JsonProperty("LumpsumBenefit")
		private String lumpsumBenefit ;
		
		@JsonProperty("IncomeBenefitPayoutDuration")
		private String incomeBenefitPayoutDuration ;
		
		@JsonProperty("IncomeOption")
	    private String incomeOption;
		
		@JsonProperty("IncomeStartOption")
	    private String incomeStartOption;
		
		@JsonProperty("IncomeDuration")
	    private String incomeDuration;
		
	    public PolicyOptions() {
	    	
	    }

		public PolicyOptions(String increasing, String incomeBenefitPayoutStartYear, String cashback,
				String firstPolicyYearofAnnualisedPremium, String cashbackPercentAnnualisedPremium,
				String timingofIncomeFromIncomeStartYear, String planOption, String littleStarBenefit,
				String systematicWithdrawalPlan, String swpFrequency, String policyYearFromWhichSwpPayable,
				String incomeBenefitPayoutType, String lumpsumBenefit, String incomeBenefitPayoutDuration,
				String incomeOption, String incomeStartOption, String incomeDuration) {
			super();
			this.increasing = increasing;
			this.incomeBenefitPayoutStartYear = incomeBenefitPayoutStartYear;
			this.cashback = cashback;
			this.firstPolicyYearofAnnualisedPremium = firstPolicyYearofAnnualisedPremium;
			this.cashbackPercentAnnualisedPremium = cashbackPercentAnnualisedPremium;
			this.timingofIncomeFromIncomeStartYear = timingofIncomeFromIncomeStartYear;
			this.planOption = planOption;
			this.littleStarBenefit = littleStarBenefit;
			this.systematicWithdrawalPlan = systematicWithdrawalPlan;
			this.swpFrequency = swpFrequency;
			this.policyYearFromWhichSwpPayable = policyYearFromWhichSwpPayable;
			this.incomeBenefitPayoutType = incomeBenefitPayoutType;
			this.lumpsumBenefit = lumpsumBenefit;
			this.incomeBenefitPayoutDuration = incomeBenefitPayoutDuration;
			this.incomeOption = incomeOption;
			this.incomeStartOption = incomeStartOption;
			this.incomeDuration = incomeDuration;
		}



		public String getIncomeOption() {
			return incomeOption;
		}

		public void setIncomeOption(String incomeOption) {
			this.incomeOption = incomeOption;
		}

		public String getIncomeStartOption() {
			return incomeStartOption;
		}

		public void setIncomeStartOption(String incomeStartOption) {
			this.incomeStartOption = incomeStartOption;
		}

		public String getIncomeDuration() {
			return incomeDuration;
		}

		public void setIncomeDuration(String incomeDuration) {
			this.incomeDuration = incomeDuration;
		}
		
		public String getIncomeBenefitPayoutType() {
			return incomeBenefitPayoutType;
		}

		public void setIncomeBenefitPayoutType(String incomeBenefitPayoutType) {
			this.incomeBenefitPayoutType = incomeBenefitPayoutType;
		}

		public String getLumpsumBenefit() {
			return lumpsumBenefit;
		}

		public void setLumpsumBenefit(String lumpsumBenefit) {
			this.lumpsumBenefit = lumpsumBenefit;
		}

		public String getIncomeBenefitPayoutDuration() {
			return incomeBenefitPayoutDuration;
		}

		public void setIncomeBenefitPayoutDuration(String incomeBenefitPayoutDuration) {
			this.incomeBenefitPayoutDuration = incomeBenefitPayoutDuration;
		}

		public String getPlanOption() {
			return planOption;
		}

		public void setPlanOption(String planOption) {
			this.planOption = planOption;
		}

		public String getLittleStarBenefit() {
			return littleStarBenefit;
		}

		public void setLittleStarBenefit(String littleStarBenefit) {
			this.littleStarBenefit = littleStarBenefit;
		}

		public String getSystematicWithdrawalPlan() {
			return systematicWithdrawalPlan;
		}

		public void setSystematicWithdrawalPlan(String systematicWithdrawalPlan) {
			this.systematicWithdrawalPlan = systematicWithdrawalPlan;
		}

		public String getSwpFrequency() {
			return swpFrequency;
		}

		public void setSwpFrequency(String swpFrequency) {
			this.swpFrequency = swpFrequency;
		}

		public String getPolicyYearFromWhichSwpPayable() {
			return policyYearFromWhichSwpPayable;
		}

		public void setPolicyYearFromWhichSwpPayable(String policyYearFromWhichSwpPayable) {
			this.policyYearFromWhichSwpPayable = policyYearFromWhichSwpPayable;
		}



		@Override
		public String toString() {
			return "PolicyOptions [increasing=" + increasing + ", incomeBenefitPayoutStartYear="
					+ incomeBenefitPayoutStartYear + ", cashback=" + cashback + ", firstPolicyYearofAnnualisedPremium="
					+ firstPolicyYearofAnnualisedPremium + ", cashbackPercentAnnualisedPremium="
					+ cashbackPercentAnnualisedPremium + ", timingofIncomeFromIncomeStartYear="
					+ timingofIncomeFromIncomeStartYear + ", planOption=" + planOption + ", littleStarBenefit="
					+ littleStarBenefit + ", systematicWithdrawalPlan=" + systematicWithdrawalPlan + ", swpFrequency="
					+ swpFrequency + ", policyYearFromWhichSwpPayable=" + policyYearFromWhichSwpPayable
					+ ", incomeBenefitPayoutType=" + incomeBenefitPayoutType + ", lumpsumBenefit=" + lumpsumBenefit
					+ ", incomeBenefitPayoutDuration=" + incomeBenefitPayoutDuration + ", incomeOption=" + incomeOption
					+ ", incomeStartOption=" + incomeStartOption + ", incomeDuration=" + incomeDuration + "]";
		}
	}
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class RiderDetails {
		
		@JsonProperty("ADBRider")
	    private String aDBRider;
		
		@JsonProperty("ATPDRider")
	    private String aTPDRider;
		
		@JsonProperty("CriticalIllnessRider")
	    private String criticalIllnessRider;
		
		@JsonProperty("HospitalityCashBenefitRider")
	    private String hospitalityCashBenefitRider;
		
		@JsonProperty("PayorWaiverBenefitRider")
	    private String payorWaiverBenefitRider;
		
		@JsonProperty("PWB")
	    private String pWB;
		
		@JsonProperty("WaiverOfPremiumRider")
	    private String waiverOfPremiumRider;
		
		public RiderDetails() {
			    	
			    }
		public RiderDetails(String aDBRider, String aTPDRider, String criticalIllnessRider,
				String hospitalityCashBenefitRider, String payorWaiverBenefitRider, String pWB,
				String waiverOfPremiumRider) {
			
			this.aDBRider = aDBRider;
			this.aTPDRider = aTPDRider;
			this.criticalIllnessRider = criticalIllnessRider;
			this.hospitalityCashBenefitRider = hospitalityCashBenefitRider;
			this.payorWaiverBenefitRider = payorWaiverBenefitRider;
			this.pWB = pWB;
			this.waiverOfPremiumRider = waiverOfPremiumRider;
		}

		@Override
		public String toString() {
			return "RiderDetails [aDBRider=" + aDBRider + ", aTPDRider=" + aTPDRider + ", criticalIllnessRider="
					+ criticalIllnessRider + ", hospitalityCashBenefitRider=" + hospitalityCashBenefitRider
					+ ", payorWaiverBenefitRider=" + payorWaiverBenefitRider + ", pWB=" + pWB
					+ ", waiverOfPremiumRider=" + waiverOfPremiumRider + "]";
		}
		
		public String getWaiverOfPremiumRider() {
			return waiverOfPremiumRider;
		}
		public void setWaiverOfPremiumRider(String waiverOfPremiumRider) {
			this.waiverOfPremiumRider = waiverOfPremiumRider;
		}
		
		public String getaDBRider() {
			return aDBRider;
		}

		public void setaDBRider(String aDBRider) {
			this.aDBRider = aDBRider;
		}

		public String getaTPDRider() {
			return aTPDRider;
		}

		public void setaTPDRider(String aTPDRider) {
			this.aTPDRider = aTPDRider;
		}

		public String getCriticalIllnessRider() {
			return criticalIllnessRider;
		}

		public void setCriticalIllnessRider(String criticalIllnessRider) {
			this.criticalIllnessRider = criticalIllnessRider;
		}

		public String getHospitalityCashBenefitRider() {
			return hospitalityCashBenefitRider;
		}

		public void setHospitalityCashBenefitRider(String hospitalityCashBenefitRider) {
			this.hospitalityCashBenefitRider = hospitalityCashBenefitRider;
		}

		public String getPayorWaiverBenefitRider() {
			return payorWaiverBenefitRider;
		}

		public void setPayorWaiverBenefitRider(String payorWaiverBenefitRider) {
			this.payorWaiverBenefitRider = payorWaiverBenefitRider;
		}

		public String getpWB() {
			return pWB;
		}

		public void setpWB(String pWB) {
			this.pWB = pWB;
		}
		
	}
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class ComboDetails {

	}

}
