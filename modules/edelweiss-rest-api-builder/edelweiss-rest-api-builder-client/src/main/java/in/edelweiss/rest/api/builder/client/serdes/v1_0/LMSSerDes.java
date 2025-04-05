package in.edelweiss.rest.api.builder.client.serdes.v1_0;

import in.edelweiss.rest.api.builder.client.dto.v1_0.LMS;
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
public class LMSSerDes {

	public static LMS toDTO(String json) {
		LMSJSONParser lmsJSONParser = new LMSJSONParser();

		return lmsJSONParser.parseToDTO(json);
	}

	public static LMS[] toDTOs(String json) {
		LMSJSONParser lmsJSONParser = new LMSJSONParser();

		return lmsJSONParser.parseToDTOs(json);
	}

	public static String toJSON(LMS lms) {
		if (lms == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (lms.getInterestSessions() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"interestSessions\": ");

			sb.append(String.valueOf(lms.getInterestSessions()));
		}

		if (lms.getLead() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"lead\": ");

			sb.append(String.valueOf(lms.getLead()));
		}

		if (lms.getQuote() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"quote\": ");

			sb.append(String.valueOf(lms.getQuote()));
		}

		if (lms.getUtm() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"utm\": ");

			sb.append(String.valueOf(lms.getUtm()));
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		LMSJSONParser lmsJSONParser = new LMSJSONParser();

		return lmsJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(LMS lms) {
		if (lms == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (lms.getInterestSessions() == null) {
			map.put("interestSessions", null);
		}
		else {
			map.put(
				"interestSessions", String.valueOf(lms.getInterestSessions()));
		}

		if (lms.getLead() == null) {
			map.put("lead", null);
		}
		else {
			map.put("lead", String.valueOf(lms.getLead()));
		}

		if (lms.getQuote() == null) {
			map.put("quote", null);
		}
		else {
			map.put("quote", String.valueOf(lms.getQuote()));
		}

		if (lms.getUtm() == null) {
			map.put("utm", null);
		}
		else {
			map.put("utm", String.valueOf(lms.getUtm()));
		}

		return map;
	}

	public static class LMSJSONParser extends BaseJSONParser<LMS> {

		@Override
		protected LMS createDTO() {
			return new LMS();
		}

		@Override
		protected LMS[] createDTOArray(int size) {
			return new LMS[size];
		}

		@Override
		protected void setField(
			LMS lms, String jsonParserFieldName, Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "interestSessions")) {
				if (jsonParserFieldValue != null) {
					lms.setInterestSessions(
						InterestSessionsSerDes.toDTO(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "lead")) {
				if (jsonParserFieldValue != null) {
					lms.setLead(LeadSerDes.toDTO((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "quote")) {
				if (jsonParserFieldValue != null) {
					lms.setQuote(
						QuoteSerDes.toDTO((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "utm")) {
				if (jsonParserFieldValue != null) {
					lms.setUtm(UtmSerDes.toDTO((String)jsonParserFieldValue));
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