package in.edelweiss.rest.api.builder.client.serdes.v1_0;

import in.edelweiss.rest.api.builder.client.dto.v1_0.Data;
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
public class DataSerDes {

	public static Data toDTO(String json) {
		DataJSONParser dataJSONParser = new DataJSONParser();

		return dataJSONParser.parseToDTO(json);
	}

	public static Data[] toDTOs(String json) {
		DataJSONParser dataJSONParser = new DataJSONParser();

		return dataJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Data data) {
		if (data == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (data.getPhoto() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"photo\": ");

			sb.append("\"");

			sb.append(_escape(data.getPhoto()));

			sb.append("\"");
		}

		if (data.getPoa() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"poa\": ");

			sb.append(String.valueOf(data.getPoa()));
		}

		if (data.getPoi() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"poi\": ");

			sb.append(String.valueOf(data.getPoi()));
		}

		if (data.getUid() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"uid\": ");

			sb.append("\"");

			sb.append(_escape(data.getUid()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		DataJSONParser dataJSONParser = new DataJSONParser();

		return dataJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Data data) {
		if (data == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (data.getPhoto() == null) {
			map.put("photo", null);
		}
		else {
			map.put("photo", String.valueOf(data.getPhoto()));
		}

		if (data.getPoa() == null) {
			map.put("poa", null);
		}
		else {
			map.put("poa", String.valueOf(data.getPoa()));
		}

		if (data.getPoi() == null) {
			map.put("poi", null);
		}
		else {
			map.put("poi", String.valueOf(data.getPoi()));
		}

		if (data.getUid() == null) {
			map.put("uid", null);
		}
		else {
			map.put("uid", String.valueOf(data.getUid()));
		}

		return map;
	}

	public static class DataJSONParser extends BaseJSONParser<Data> {

		@Override
		protected Data createDTO() {
			return new Data();
		}

		@Override
		protected Data[] createDTOArray(int size) {
			return new Data[size];
		}

		@Override
		protected void setField(
			Data data, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "photo")) {
				if (jsonParserFieldValue != null) {
					data.setPhoto((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "poa")) {
				if (jsonParserFieldValue != null) {
					data.setPoa(PoaSerDes.toDTO((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "poi")) {
				if (jsonParserFieldValue != null) {
					data.setPoi(PoiSerDes.toDTO((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "uid")) {
				if (jsonParserFieldValue != null) {
					data.setUid((String)jsonParserFieldValue);
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