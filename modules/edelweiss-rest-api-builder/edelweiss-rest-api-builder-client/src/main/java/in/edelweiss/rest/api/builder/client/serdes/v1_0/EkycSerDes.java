package in.edelweiss.rest.api.builder.client.serdes.v1_0;

import in.edelweiss.rest.api.builder.client.dto.v1_0.Ekyc;
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
public class EkycSerDes {

	public static Ekyc toDTO(String json) {
		EkycJSONParser ekycJSONParser = new EkycJSONParser();

		return ekycJSONParser.parseToDTO(json);
	}

	public static Ekyc[] toDTOs(String json) {
		EkycJSONParser ekycJSONParser = new EkycJSONParser();

		return ekycJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Ekyc ekyc) {
		if (ekyc == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (ekyc.getData() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"data\": ");

			sb.append(String.valueOf(ekyc.getData()));
		}

		if (ekyc.getEkycMessage() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"ekycMessage\": ");

			sb.append("\"");

			sb.append(_escape(ekyc.getEkycMessage()));

			sb.append("\"");
		}

		if (ekyc.getEkycStatus() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"ekycStatus\": ");

			sb.append("\"");

			sb.append(_escape(ekyc.getEkycStatus()));

			sb.append("\"");
		}

		if (ekyc.getEkycTimeStamp() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"ekycTimeStamp\": ");

			sb.append("\"");

			sb.append(_escape(ekyc.getEkycTimeStamp()));

			sb.append("\"");
		}

		if (ekyc.getTxnId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"txnId\": ");

			sb.append("\"");

			sb.append(_escape(ekyc.getTxnId()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		EkycJSONParser ekycJSONParser = new EkycJSONParser();

		return ekycJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Ekyc ekyc) {
		if (ekyc == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (ekyc.getData() == null) {
			map.put("data", null);
		}
		else {
			map.put("data", String.valueOf(ekyc.getData()));
		}

		if (ekyc.getEkycMessage() == null) {
			map.put("ekycMessage", null);
		}
		else {
			map.put("ekycMessage", String.valueOf(ekyc.getEkycMessage()));
		}

		if (ekyc.getEkycStatus() == null) {
			map.put("ekycStatus", null);
		}
		else {
			map.put("ekycStatus", String.valueOf(ekyc.getEkycStatus()));
		}

		if (ekyc.getEkycTimeStamp() == null) {
			map.put("ekycTimeStamp", null);
		}
		else {
			map.put("ekycTimeStamp", String.valueOf(ekyc.getEkycTimeStamp()));
		}

		if (ekyc.getTxnId() == null) {
			map.put("txnId", null);
		}
		else {
			map.put("txnId", String.valueOf(ekyc.getTxnId()));
		}

		return map;
	}

	public static class EkycJSONParser extends BaseJSONParser<Ekyc> {

		@Override
		protected Ekyc createDTO() {
			return new Ekyc();
		}

		@Override
		protected Ekyc[] createDTOArray(int size) {
			return new Ekyc[size];
		}

		@Override
		protected void setField(
			Ekyc ekyc, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "data")) {
				if (jsonParserFieldValue != null) {
					ekyc.setData(
						DataSerDes.toDTO((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "ekycMessage")) {
				if (jsonParserFieldValue != null) {
					ekyc.setEkycMessage((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "ekycStatus")) {
				if (jsonParserFieldValue != null) {
					ekyc.setEkycStatus((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "ekycTimeStamp")) {
				if (jsonParserFieldValue != null) {
					ekyc.setEkycTimeStamp((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "txnId")) {
				if (jsonParserFieldValue != null) {
					ekyc.setTxnId((String)jsonParserFieldValue);
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