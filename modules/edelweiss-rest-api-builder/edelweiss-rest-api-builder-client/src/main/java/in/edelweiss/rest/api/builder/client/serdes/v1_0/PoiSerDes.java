package in.edelweiss.rest.api.builder.client.serdes.v1_0;

import in.edelweiss.rest.api.builder.client.dto.v1_0.Poi;
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
public class PoiSerDes {

	public static Poi toDTO(String json) {
		PoiJSONParser poiJSONParser = new PoiJSONParser();

		return poiJSONParser.parseToDTO(json);
	}

	public static Poi[] toDTOs(String json) {
		PoiJSONParser poiJSONParser = new PoiJSONParser();

		return poiJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Poi poi) {
		if (poi == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (poi.getDob() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dob\": ");

			sb.append("\"");

			sb.append(_escape(poi.getDob()));

			sb.append("\"");
		}

		if (poi.getGender() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"gender\": ");

			sb.append("\"");

			sb.append(_escape(poi.getGender()));

			sb.append("\"");
		}

		if (poi.getName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(poi.getName()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		PoiJSONParser poiJSONParser = new PoiJSONParser();

		return poiJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Poi poi) {
		if (poi == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (poi.getDob() == null) {
			map.put("dob", null);
		}
		else {
			map.put("dob", String.valueOf(poi.getDob()));
		}

		if (poi.getGender() == null) {
			map.put("gender", null);
		}
		else {
			map.put("gender", String.valueOf(poi.getGender()));
		}

		if (poi.getName() == null) {
			map.put("name", null);
		}
		else {
			map.put("name", String.valueOf(poi.getName()));
		}

		return map;
	}

	public static class PoiJSONParser extends BaseJSONParser<Poi> {

		@Override
		protected Poi createDTO() {
			return new Poi();
		}

		@Override
		protected Poi[] createDTOArray(int size) {
			return new Poi[size];
		}

		@Override
		protected void setField(
			Poi poi, String jsonParserFieldName, Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "dob")) {
				if (jsonParserFieldValue != null) {
					poi.setDob((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "gender")) {
				if (jsonParserFieldValue != null) {
					poi.setGender((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				if (jsonParserFieldValue != null) {
					poi.setName((String)jsonParserFieldValue);
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