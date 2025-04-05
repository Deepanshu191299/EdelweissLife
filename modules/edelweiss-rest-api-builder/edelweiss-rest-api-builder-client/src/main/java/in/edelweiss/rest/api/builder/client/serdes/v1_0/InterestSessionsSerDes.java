package in.edelweiss.rest.api.builder.client.serdes.v1_0;

import in.edelweiss.rest.api.builder.client.dto.v1_0.InterestSessions;
import in.edelweiss.rest.api.builder.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author pramod.dk
 * @generated
 */
@Generated("")
public class InterestSessionsSerDes {

	public static InterestSessions toDTO(String json) {
		InterestSessionsJSONParser interestSessionsJSONParser =
			new InterestSessionsJSONParser();

		return interestSessionsJSONParser.parseToDTO(json);
	}

	public static InterestSessions[] toDTOs(String json) {
		InterestSessionsJSONParser interestSessionsJSONParser =
			new InterestSessionsJSONParser();

		return interestSessionsJSONParser.parseToDTOs(json);
	}

	public static String toJSON(InterestSessions interestSessions) {
		if (interestSessions == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (interestSessions.getLeadFormProductCategory() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"leadFormProductCategory\": ");

			sb.append("\"");

			sb.append(_escape(interestSessions.getLeadFormProductCategory()));

			sb.append("\"");
		}

		if (interestSessions.getLeadFormProductCode() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"leadFormProductCode\": ");

			sb.append("\"");

			sb.append(_escape(interestSessions.getLeadFormProductCode()));

			sb.append("\"");
		}

		if (interestSessions.getPersona() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"persona\": ");

			sb.append("\"");

			sb.append(_escape(interestSessions.getPersona()));

			sb.append("\"");
		}

		if (interestSessions.getRecommendedAnnualIncome() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"recommendedAnnualIncome\": ");

			sb.append("\"");

			sb.append(_escape(interestSessions.getRecommendedAnnualIncome()));

			sb.append("\"");
		}

		if (interestSessions.getRecommendedObjective() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"recommendedObjective\": ");

			sb.append("\"");

			sb.append(_escape(interestSessions.getRecommendedObjective()));

			sb.append("\"");
		}

		if (interestSessions.getRecommendedObjectiveCategory() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"recommendedObjectiveCategory\": ");

			sb.append("\"");

			sb.append(
				_escape(interestSessions.getRecommendedObjectiveCategory()));

			sb.append("\"");
		}

		if (interestSessions.getRecommendedProduct() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"recommendedProduct\": ");

			sb.append("\"");

			sb.append(_escape(interestSessions.getRecommendedProduct()));

			sb.append("\"");
		}

		if (interestSessions.getSiteSection() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"siteSection\": ");

			sb.append("\"");

			sb.append(_escape(interestSessions.getSiteSection()));

			sb.append("\"");
		}

		if (interestSessions.getStage() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"stage\": ");

			sb.append("\"");

			sb.append(_escape(interestSessions.getStage()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		InterestSessionsJSONParser interestSessionsJSONParser =
			new InterestSessionsJSONParser();

		return interestSessionsJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(InterestSessions interestSessions) {
		if (interestSessions == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (interestSessions.getLeadFormProductCategory() == null) {
			map.put("leadFormProductCategory", null);
		}
		else {
			map.put(
				"leadFormProductCategory",
				String.valueOf(interestSessions.getLeadFormProductCategory()));
		}

		if (interestSessions.getLeadFormProductCode() == null) {
			map.put("leadFormProductCode", null);
		}
		else {
			map.put(
				"leadFormProductCode",
				String.valueOf(interestSessions.getLeadFormProductCode()));
		}

		if (interestSessions.getPersona() == null) {
			map.put("persona", null);
		}
		else {
			map.put("persona", String.valueOf(interestSessions.getPersona()));
		}

		if (interestSessions.getRecommendedAnnualIncome() == null) {
			map.put("recommendedAnnualIncome", null);
		}
		else {
			map.put(
				"recommendedAnnualIncome",
				String.valueOf(interestSessions.getRecommendedAnnualIncome()));
		}

		if (interestSessions.getRecommendedObjective() == null) {
			map.put("recommendedObjective", null);
		}
		else {
			map.put(
				"recommendedObjective",
				String.valueOf(interestSessions.getRecommendedObjective()));
		}

		if (interestSessions.getRecommendedObjectiveCategory() == null) {
			map.put("recommendedObjectiveCategory", null);
		}
		else {
			map.put(
				"recommendedObjectiveCategory",
				String.valueOf(
					interestSessions.getRecommendedObjectiveCategory()));
		}

		if (interestSessions.getRecommendedProduct() == null) {
			map.put("recommendedProduct", null);
		}
		else {
			map.put(
				"recommendedProduct",
				String.valueOf(interestSessions.getRecommendedProduct()));
		}

		if (interestSessions.getSiteSection() == null) {
			map.put("siteSection", null);
		}
		else {
			map.put(
				"siteSection",
				String.valueOf(interestSessions.getSiteSection()));
		}

		if (interestSessions.getStage() == null) {
			map.put("stage", null);
		}
		else {
			map.put("stage", String.valueOf(interestSessions.getStage()));
		}

		return map;
	}

	public static class InterestSessionsJSONParser
		extends BaseJSONParser<InterestSessions> {

		@Override
		protected InterestSessions createDTO() {
			return new InterestSessions();
		}

		@Override
		protected InterestSessions[] createDTOArray(int size) {
			return new InterestSessions[size];
		}

		@Override
		protected void setField(
			InterestSessions interestSessions, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(
					jsonParserFieldName, "leadFormProductCategory")) {

				if (jsonParserFieldValue != null) {
					interestSessions.setLeadFormProductCategory(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "leadFormProductCode")) {

				if (jsonParserFieldValue != null) {
					interestSessions.setLeadFormProductCode(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "persona")) {
				if (jsonParserFieldValue != null) {
					interestSessions.setPersona((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "recommendedAnnualIncome")) {

				if (jsonParserFieldValue != null) {
					interestSessions.setRecommendedAnnualIncome(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "recommendedObjective")) {

				if (jsonParserFieldValue != null) {
					interestSessions.setRecommendedObjective(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "recommendedObjectiveCategory")) {

				if (jsonParserFieldValue != null) {
					interestSessions.setRecommendedObjectiveCategory(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "recommendedProduct")) {

				if (jsonParserFieldValue != null) {
					interestSessions.setRecommendedProduct(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "siteSection")) {
				if (jsonParserFieldValue != null) {
					interestSessions.setSiteSection(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "stage")) {
				if (jsonParserFieldValue != null) {
					interestSessions.setStage((String)jsonParserFieldValue);
				}
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		for (String[] strings : BaseJSONParser.JSON_ESCAPE_STRINGS) {
			string = string.replace(strings[0], strings[1]);
		}

		return string;
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
			sb.append(entry.getKey());
			sb.append("\": ");

			Object value = entry.getValue();

			Class<?> valueClass = value.getClass();

			if (value instanceof Map) {
				sb.append(_toJSON((Map)value));
			}
			else if (valueClass.isArray()) {
				Object[] values = (Object[])value;

				sb.append("[");

				for (int i = 0; i < values.length; i++) {
					sb.append("\"");
					sb.append(_escape(values[i]));
					sb.append("\"");

					if ((i + 1) < values.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(entry.getValue()));
				sb.append("\"");
			}
			else {
				sb.append(String.valueOf(entry.getValue()));
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}