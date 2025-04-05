package in.edelweiss.rest.api.builder.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.util.ObjectMapperUtil;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

import javax.annotation.Generated;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author pramod.dk
 * @generated
 */
@Generated("")
@GraphQLName(
	description = "Interest Sessions Params.", value = "InterestSessions"
)
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "InterestSessions")
public class InterestSessions implements Serializable {

	public static InterestSessions toDTO(String json) {
		return ObjectMapperUtil.readValue(InterestSessions.class, json);
	}

	public static InterestSessions unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(InterestSessions.class, json);
	}

	@Schema
	public String getLeadFormProductCategory() {
		if (_leadFormProductCategorySupplier != null) {
			leadFormProductCategory = _leadFormProductCategorySupplier.get();

			_leadFormProductCategorySupplier = null;
		}

		return leadFormProductCategory;
	}

	public void setLeadFormProductCategory(String leadFormProductCategory) {
		this.leadFormProductCategory = leadFormProductCategory;

		_leadFormProductCategorySupplier = null;
	}

	@JsonIgnore
	public void setLeadFormProductCategory(
		UnsafeSupplier<String, Exception>
			leadFormProductCategoryUnsafeSupplier) {

		_leadFormProductCategorySupplier = () -> {
			try {
				return leadFormProductCategoryUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String leadFormProductCategory;

	@JsonIgnore
	private Supplier<String> _leadFormProductCategorySupplier;

	@Schema
	public String getLeadFormProductCode() {
		if (_leadFormProductCodeSupplier != null) {
			leadFormProductCode = _leadFormProductCodeSupplier.get();

			_leadFormProductCodeSupplier = null;
		}

		return leadFormProductCode;
	}

	public void setLeadFormProductCode(String leadFormProductCode) {
		this.leadFormProductCode = leadFormProductCode;

		_leadFormProductCodeSupplier = null;
	}

	@JsonIgnore
	public void setLeadFormProductCode(
		UnsafeSupplier<String, Exception> leadFormProductCodeUnsafeSupplier) {

		_leadFormProductCodeSupplier = () -> {
			try {
				return leadFormProductCodeUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String leadFormProductCode;

	@JsonIgnore
	private Supplier<String> _leadFormProductCodeSupplier;

	@Schema
	public String getPersona() {
		if (_personaSupplier != null) {
			persona = _personaSupplier.get();

			_personaSupplier = null;
		}

		return persona;
	}

	public void setPersona(String persona) {
		this.persona = persona;

		_personaSupplier = null;
	}

	@JsonIgnore
	public void setPersona(
		UnsafeSupplier<String, Exception> personaUnsafeSupplier) {

		_personaSupplier = () -> {
			try {
				return personaUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String persona;

	@JsonIgnore
	private Supplier<String> _personaSupplier;

	@Schema
	public String getRecommendedAnnualIncome() {
		if (_recommendedAnnualIncomeSupplier != null) {
			recommendedAnnualIncome = _recommendedAnnualIncomeSupplier.get();

			_recommendedAnnualIncomeSupplier = null;
		}

		return recommendedAnnualIncome;
	}

	public void setRecommendedAnnualIncome(String recommendedAnnualIncome) {
		this.recommendedAnnualIncome = recommendedAnnualIncome;

		_recommendedAnnualIncomeSupplier = null;
	}

	@JsonIgnore
	public void setRecommendedAnnualIncome(
		UnsafeSupplier<String, Exception>
			recommendedAnnualIncomeUnsafeSupplier) {

		_recommendedAnnualIncomeSupplier = () -> {
			try {
				return recommendedAnnualIncomeUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String recommendedAnnualIncome;

	@JsonIgnore
	private Supplier<String> _recommendedAnnualIncomeSupplier;

	@Schema
	public String getRecommendedObjective() {
		if (_recommendedObjectiveSupplier != null) {
			recommendedObjective = _recommendedObjectiveSupplier.get();

			_recommendedObjectiveSupplier = null;
		}

		return recommendedObjective;
	}

	public void setRecommendedObjective(String recommendedObjective) {
		this.recommendedObjective = recommendedObjective;

		_recommendedObjectiveSupplier = null;
	}

	@JsonIgnore
	public void setRecommendedObjective(
		UnsafeSupplier<String, Exception> recommendedObjectiveUnsafeSupplier) {

		_recommendedObjectiveSupplier = () -> {
			try {
				return recommendedObjectiveUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String recommendedObjective;

	@JsonIgnore
	private Supplier<String> _recommendedObjectiveSupplier;

	@Schema
	public String getRecommendedObjectiveCategory() {
		if (_recommendedObjectiveCategorySupplier != null) {
			recommendedObjectiveCategory =
				_recommendedObjectiveCategorySupplier.get();

			_recommendedObjectiveCategorySupplier = null;
		}

		return recommendedObjectiveCategory;
	}

	public void setRecommendedObjectiveCategory(
		String recommendedObjectiveCategory) {

		this.recommendedObjectiveCategory = recommendedObjectiveCategory;

		_recommendedObjectiveCategorySupplier = null;
	}

	@JsonIgnore
	public void setRecommendedObjectiveCategory(
		UnsafeSupplier<String, Exception>
			recommendedObjectiveCategoryUnsafeSupplier) {

		_recommendedObjectiveCategorySupplier = () -> {
			try {
				return recommendedObjectiveCategoryUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String recommendedObjectiveCategory;

	@JsonIgnore
	private Supplier<String> _recommendedObjectiveCategorySupplier;

	@Schema
	public String getRecommendedProduct() {
		if (_recommendedProductSupplier != null) {
			recommendedProduct = _recommendedProductSupplier.get();

			_recommendedProductSupplier = null;
		}

		return recommendedProduct;
	}

	public void setRecommendedProduct(String recommendedProduct) {
		this.recommendedProduct = recommendedProduct;

		_recommendedProductSupplier = null;
	}

	@JsonIgnore
	public void setRecommendedProduct(
		UnsafeSupplier<String, Exception> recommendedProductUnsafeSupplier) {

		_recommendedProductSupplier = () -> {
			try {
				return recommendedProductUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String recommendedProduct;

	@JsonIgnore
	private Supplier<String> _recommendedProductSupplier;

	@Schema
	public String getSiteSection() {
		if (_siteSectionSupplier != null) {
			siteSection = _siteSectionSupplier.get();

			_siteSectionSupplier = null;
		}

		return siteSection;
	}

	public void setSiteSection(String siteSection) {
		this.siteSection = siteSection;

		_siteSectionSupplier = null;
	}

	@JsonIgnore
	public void setSiteSection(
		UnsafeSupplier<String, Exception> siteSectionUnsafeSupplier) {

		_siteSectionSupplier = () -> {
			try {
				return siteSectionUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String siteSection;

	@JsonIgnore
	private Supplier<String> _siteSectionSupplier;

	@Schema
	public String getStage() {
		if (_stageSupplier != null) {
			stage = _stageSupplier.get();

			_stageSupplier = null;
		}

		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;

		_stageSupplier = null;
	}

	@JsonIgnore
	public void setStage(
		UnsafeSupplier<String, Exception> stageUnsafeSupplier) {

		_stageSupplier = () -> {
			try {
				return stageUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String stage;

	@JsonIgnore
	private Supplier<String> _stageSupplier;

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
		StringBundler sb = new StringBundler();

		sb.append("{");

		String leadFormProductCategory = getLeadFormProductCategory();

		if (leadFormProductCategory != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"leadFormProductCategory\": ");

			sb.append("\"");

			sb.append(_escape(leadFormProductCategory));

			sb.append("\"");
		}

		String leadFormProductCode = getLeadFormProductCode();

		if (leadFormProductCode != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"leadFormProductCode\": ");

			sb.append("\"");

			sb.append(_escape(leadFormProductCode));

			sb.append("\"");
		}

		String persona = getPersona();

		if (persona != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"persona\": ");

			sb.append("\"");

			sb.append(_escape(persona));

			sb.append("\"");
		}

		String recommendedAnnualIncome = getRecommendedAnnualIncome();

		if (recommendedAnnualIncome != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"recommendedAnnualIncome\": ");

			sb.append("\"");

			sb.append(_escape(recommendedAnnualIncome));

			sb.append("\"");
		}

		String recommendedObjective = getRecommendedObjective();

		if (recommendedObjective != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"recommendedObjective\": ");

			sb.append("\"");

			sb.append(_escape(recommendedObjective));

			sb.append("\"");
		}

		String recommendedObjectiveCategory = getRecommendedObjectiveCategory();

		if (recommendedObjectiveCategory != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"recommendedObjectiveCategory\": ");

			sb.append("\"");

			sb.append(_escape(recommendedObjectiveCategory));

			sb.append("\"");
		}

		String recommendedProduct = getRecommendedProduct();

		if (recommendedProduct != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"recommendedProduct\": ");

			sb.append("\"");

			sb.append(_escape(recommendedProduct));

			sb.append("\"");
		}

		String siteSection = getSiteSection();

		if (siteSection != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"siteSection\": ");

			sb.append("\"");

			sb.append(_escape(siteSection));

			sb.append("\"");
		}

		String stage = getStage();

		if (stage != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"stage\": ");

			sb.append("\"");

			sb.append(_escape(stage));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "in.edelweiss.rest.api.builder.dto.v1_0.InterestSessions",
		name = "x-class-name"
	)
	public String xClassName;

	private static String _escape(Object object) {
		return StringUtil.replace(
			String.valueOf(object), _JSON_ESCAPE_STRINGS[0],
			_JSON_ESCAPE_STRINGS[1]);
	}

	private static boolean _isArray(Object value) {
		if (value == null) {
			return false;
		}

		Class<?> clazz = value.getClass();

		return clazz.isArray();
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(_escape(entry.getKey()));
			sb.append("\": ");

			Object value = entry.getValue();

			if (_isArray(value)) {
				sb.append("[");

				Object[] valueArray = (Object[])value;

				for (int i = 0; i < valueArray.length; i++) {
					if (valueArray[i] instanceof String) {
						sb.append("\"");
						sb.append(valueArray[i]);
						sb.append("\"");
					}
					else {
						sb.append(valueArray[i]);
					}

					if ((i + 1) < valueArray.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof Map) {
				sb.append(_toJSON((Map<String, ?>)value));
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(value));
				sb.append("\"");
			}
			else {
				sb.append(value);
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

	private static final String[][] _JSON_ESCAPE_STRINGS = {
		{"\\", "\"", "\b", "\f", "\n", "\r", "\t"},
		{"\\\\", "\\\"", "\\b", "\\f", "\\n", "\\r", "\\t"}
	};

	private Map<String, Serializable> _extendedProperties;

}