package in.edelweiss.rest.api.builder.client.dto.v1_0;

import in.edelweiss.rest.api.builder.client.function.UnsafeSupplier;
import in.edelweiss.rest.api.builder.client.serdes.v1_0.InterestSessionsSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author pramod.dk
 * @generated
 */
@Generated("")
public class InterestSessions implements Cloneable, Serializable {

	public static InterestSessions toDTO(String json) {
		return InterestSessionsSerDes.toDTO(json);
	}

	public String getLeadFormProductCategory() {
		return leadFormProductCategory;
	}

	public void setLeadFormProductCategory(String leadFormProductCategory) {
		this.leadFormProductCategory = leadFormProductCategory;
	}

	public void setLeadFormProductCategory(
		UnsafeSupplier<String, Exception>
			leadFormProductCategoryUnsafeSupplier) {

		try {
			leadFormProductCategory =
				leadFormProductCategoryUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String leadFormProductCategory;

	public String getLeadFormProductCode() {
		return leadFormProductCode;
	}

	public void setLeadFormProductCode(String leadFormProductCode) {
		this.leadFormProductCode = leadFormProductCode;
	}

	public void setLeadFormProductCode(
		UnsafeSupplier<String, Exception> leadFormProductCodeUnsafeSupplier) {

		try {
			leadFormProductCode = leadFormProductCodeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String leadFormProductCode;

	public String getPersona() {
		return persona;
	}

	public void setPersona(String persona) {
		this.persona = persona;
	}

	public void setPersona(
		UnsafeSupplier<String, Exception> personaUnsafeSupplier) {

		try {
			persona = personaUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String persona;

	public String getRecommendedAnnualIncome() {
		return recommendedAnnualIncome;
	}

	public void setRecommendedAnnualIncome(String recommendedAnnualIncome) {
		this.recommendedAnnualIncome = recommendedAnnualIncome;
	}

	public void setRecommendedAnnualIncome(
		UnsafeSupplier<String, Exception>
			recommendedAnnualIncomeUnsafeSupplier) {

		try {
			recommendedAnnualIncome =
				recommendedAnnualIncomeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String recommendedAnnualIncome;

	public String getRecommendedObjective() {
		return recommendedObjective;
	}

	public void setRecommendedObjective(String recommendedObjective) {
		this.recommendedObjective = recommendedObjective;
	}

	public void setRecommendedObjective(
		UnsafeSupplier<String, Exception> recommendedObjectiveUnsafeSupplier) {

		try {
			recommendedObjective = recommendedObjectiveUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String recommendedObjective;

	public String getRecommendedObjectiveCategory() {
		return recommendedObjectiveCategory;
	}

	public void setRecommendedObjectiveCategory(
		String recommendedObjectiveCategory) {

		this.recommendedObjectiveCategory = recommendedObjectiveCategory;
	}

	public void setRecommendedObjectiveCategory(
		UnsafeSupplier<String, Exception>
			recommendedObjectiveCategoryUnsafeSupplier) {

		try {
			recommendedObjectiveCategory =
				recommendedObjectiveCategoryUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String recommendedObjectiveCategory;

	public String getRecommendedProduct() {
		return recommendedProduct;
	}

	public void setRecommendedProduct(String recommendedProduct) {
		this.recommendedProduct = recommendedProduct;
	}

	public void setRecommendedProduct(
		UnsafeSupplier<String, Exception> recommendedProductUnsafeSupplier) {

		try {
			recommendedProduct = recommendedProductUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String recommendedProduct;

	public String getSiteSection() {
		return siteSection;
	}

	public void setSiteSection(String siteSection) {
		this.siteSection = siteSection;
	}

	public void setSiteSection(
		UnsafeSupplier<String, Exception> siteSectionUnsafeSupplier) {

		try {
			siteSection = siteSectionUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String siteSection;

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public void setStage(
		UnsafeSupplier<String, Exception> stageUnsafeSupplier) {

		try {
			stage = stageUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String stage;

	@Override
	public InterestSessions clone() throws CloneNotSupportedException {
		return (InterestSessions)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof InterestSessions)) {
			return false;
		}

		InterestSessions interestSessions = (InterestSessions)object;

		return Objects.equals(toString(), interestSessions.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return InterestSessionsSerDes.toJSON(this);
	}

}