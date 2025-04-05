package in.edelweiss.tokio.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class InterestSessions {

	@JsonProperty("Recommended_Objective_Category")
	private String recommendedObjectiveCategory;
	
	@JsonProperty("Recommended_Objective")
	private String recommendedObjective;
	
	@JsonProperty("Recommended_Annual_Income")
	private String recommendedAnnualIncome;
	
	@JsonProperty("Recommended_Product")
	private String recommendedProduct;
	
	@JsonProperty("Persona")
	private String persona;
	
	@JsonProperty("Lead_Form_Product_Category")
	private String leadFormProductCategory;
	
	@JsonProperty("Lead_Form_Product_Code")
	private String leadFormProductCode;
	
	@JsonProperty("Stage")
	private String stage;
	
	@JsonProperty("Site_Section")
	private String siteSection;
	
	public String getRecommendedObjectiveCategory() {
		return recommendedObjectiveCategory;
	}
	public void setRecommendedObjectiveCategory(String recommendedObjectiveCategory) {
		this.recommendedObjectiveCategory = recommendedObjectiveCategory;
	}
	public String getRecommendedObjective() {
		return recommendedObjective;
	}
	public void setRecommendedObjective(String recommendedObjective) {
		this.recommendedObjective = recommendedObjective;
	}
	public String getRecommendedAnnualIncome() {
		return recommendedAnnualIncome;
	}
	public void setRecommendedAnnualIncome(String recommendedAnnualIncome) {
		this.recommendedAnnualIncome = recommendedAnnualIncome;
	}
	public String getRecommendedProduct() {
		return recommendedProduct;
	}
	public void setRecommendedProduct(String recommendedProduct) {
		this.recommendedProduct = recommendedProduct;
	}
	public String getPersona() {
		return persona;
	}
	public void setPersona(String persona) {
		this.persona = persona;
	}
	public String getLeadFormProductCategory() {
		return leadFormProductCategory;
	}
	public void setLeadFormProductCategory(String leadFormProductCategory) {
		this.leadFormProductCategory = leadFormProductCategory;
	}
	public String getLeadFormProductCode() {
		return leadFormProductCode;
	}
	public void setLeadFormProductCode(String leadFormProductCode) {
		this.leadFormProductCode = leadFormProductCode;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getSiteSection() {
		return siteSection;
	}
	public void setSiteSection(String siteSection) {
		this.siteSection = siteSection;
	}
	
	@Override
	public String toString() {
		return "InterestSessions [recommendedObjectiveCategory=" + recommendedObjectiveCategory
				+ ", recommendedObjective=" + recommendedObjective + ", recommendedAnnualIncome="
				+ recommendedAnnualIncome + ", recommendedProduct=" + recommendedProduct + ", persona=" + persona
				+ ", leadFormProductCategory=" + leadFormProductCategory + ", leadFormProductCode="
				+ leadFormProductCode + ", stage=" + stage + ", siteSection=" + siteSection + "]";
	}
}
