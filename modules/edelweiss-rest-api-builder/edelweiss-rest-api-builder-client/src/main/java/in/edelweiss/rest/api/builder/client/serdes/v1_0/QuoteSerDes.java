package in.edelweiss.rest.api.builder.client.serdes.v1_0;

import in.edelweiss.rest.api.builder.client.dto.v1_0.Quote;
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
public class QuoteSerDes {

	public static Quote toDTO(String json) {
		QuoteJSONParser quoteJSONParser = new QuoteJSONParser();

		return quoteJSONParser.parseToDTO(json);
	}

	public static Quote[] toDTOs(String json) {
		QuoteJSONParser quoteJSONParser = new QuoteJSONParser();

		return quoteJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Quote quote) {
		if (quote == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (quote.getPayoutOptions() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"payoutOptions\": ");

			sb.append("\"");

			sb.append(_escape(quote.getPayoutOptions()));

			sb.append("\"");
		}

		if (quote.getStage() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"stage\": ");

			sb.append("\"");

			sb.append(_escape(quote.getStage()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		QuoteJSONParser quoteJSONParser = new QuoteJSONParser();

		return quoteJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Quote quote) {
		if (quote == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (quote.getPayoutOptions() == null) {
			map.put("payoutOptions", null);
		}
		else {
			map.put("payoutOptions", String.valueOf(quote.getPayoutOptions()));
		}

		if (quote.getStage() == null) {
			map.put("stage", null);
		}
		else {
			map.put("stage", String.valueOf(quote.getStage()));
		}

		return map;
	}

	public static class QuoteJSONParser extends BaseJSONParser<Quote> {

		@Override
		protected Quote createDTO() {
			return new Quote();
		}

		@Override
		protected Quote[] createDTOArray(int size) {
			return new Quote[size];
		}

		@Override
		protected void setField(
			Quote quote, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "payoutOptions")) {
				if (jsonParserFieldValue != null) {
					quote.setPayoutOptions((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "stage")) {
				if (jsonParserFieldValue != null) {
					quote.setStage((String)jsonParserFieldValue);
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