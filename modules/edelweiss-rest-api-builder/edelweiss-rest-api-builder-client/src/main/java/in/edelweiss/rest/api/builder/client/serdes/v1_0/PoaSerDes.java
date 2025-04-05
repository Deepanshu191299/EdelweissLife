package in.edelweiss.rest.api.builder.client.serdes.v1_0;

import in.edelweiss.rest.api.builder.client.dto.v1_0.Poa;
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
public class PoaSerDes {

	public static Poa toDTO(String json) {
		PoaJSONParser poaJSONParser = new PoaJSONParser();

		return poaJSONParser.parseToDTO(json);
	}

	public static Poa[] toDTOs(String json) {
		PoaJSONParser poaJSONParser = new PoaJSONParser();

		return poaJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Poa poa) {
		if (poa == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (poa.getCo() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"co\": ");

			sb.append("\"");

			sb.append(_escape(poa.getCo()));

			sb.append("\"");
		}

		if (poa.getCountry() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"country\": ");

			sb.append("\"");

			sb.append(_escape(poa.getCountry()));

			sb.append("\"");
		}

		if (poa.getDist() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dist\": ");

			sb.append("\"");

			sb.append(_escape(poa.getDist()));

			sb.append("\"");
		}

		if (poa.getHouse() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"house\": ");

			sb.append("\"");

			sb.append(_escape(poa.getHouse()));

			sb.append("\"");
		}

		if (poa.getLm() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"lm\": ");

			sb.append("\"");

			sb.append(_escape(poa.getLm()));

			sb.append("\"");
		}

		if (poa.getLoc() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"loc\": ");

			sb.append("\"");

			sb.append(_escape(poa.getLoc()));

			sb.append("\"");
		}

		if (poa.getPc() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"pc\": ");

			sb.append("\"");

			sb.append(_escape(poa.getPc()));

			sb.append("\"");
		}

		if (poa.getPo() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"po\": ");

			sb.append("\"");

			sb.append(_escape(poa.getPo()));

			sb.append("\"");
		}

		if (poa.getState() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"state\": ");

			sb.append("\"");

			sb.append(_escape(poa.getState()));

			sb.append("\"");
		}

		if (poa.getStreet() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"street\": ");

			sb.append("\"");

			sb.append(_escape(poa.getStreet()));

			sb.append("\"");
		}

		if (poa.getSubdist() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"subdist\": ");

			sb.append("\"");

			sb.append(_escape(poa.getSubdist()));

			sb.append("\"");
		}

		if (poa.getVtc() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"vtc\": ");

			sb.append("\"");

			sb.append(_escape(poa.getVtc()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		PoaJSONParser poaJSONParser = new PoaJSONParser();

		return poaJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Poa poa) {
		if (poa == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (poa.getCo() == null) {
			map.put("co", null);
		}
		else {
			map.put("co", String.valueOf(poa.getCo()));
		}

		if (poa.getCountry() == null) {
			map.put("country", null);
		}
		else {
			map.put("country", String.valueOf(poa.getCountry()));
		}

		if (poa.getDist() == null) {
			map.put("dist", null);
		}
		else {
			map.put("dist", String.valueOf(poa.getDist()));
		}

		if (poa.getHouse() == null) {
			map.put("house", null);
		}
		else {
			map.put("house", String.valueOf(poa.getHouse()));
		}

		if (poa.getLm() == null) {
			map.put("lm", null);
		}
		else {
			map.put("lm", String.valueOf(poa.getLm()));
		}

		if (poa.getLoc() == null) {
			map.put("loc", null);
		}
		else {
			map.put("loc", String.valueOf(poa.getLoc()));
		}

		if (poa.getPc() == null) {
			map.put("pc", null);
		}
		else {
			map.put("pc", String.valueOf(poa.getPc()));
		}

		if (poa.getPo() == null) {
			map.put("po", null);
		}
		else {
			map.put("po", String.valueOf(poa.getPo()));
		}

		if (poa.getState() == null) {
			map.put("state", null);
		}
		else {
			map.put("state", String.valueOf(poa.getState()));
		}

		if (poa.getStreet() == null) {
			map.put("street", null);
		}
		else {
			map.put("street", String.valueOf(poa.getStreet()));
		}

		if (poa.getSubdist() == null) {
			map.put("subdist", null);
		}
		else {
			map.put("subdist", String.valueOf(poa.getSubdist()));
		}

		if (poa.getVtc() == null) {
			map.put("vtc", null);
		}
		else {
			map.put("vtc", String.valueOf(poa.getVtc()));
		}

		return map;
	}

	public static class PoaJSONParser extends BaseJSONParser<Poa> {

		@Override
		protected Poa createDTO() {
			return new Poa();
		}

		@Override
		protected Poa[] createDTOArray(int size) {
			return new Poa[size];
		}

		@Override
		protected void setField(
			Poa poa, String jsonParserFieldName, Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "co")) {
				if (jsonParserFieldValue != null) {
					poa.setCo((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "country")) {
				if (jsonParserFieldValue != null) {
					poa.setCountry((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "dist")) {
				if (jsonParserFieldValue != null) {
					poa.setDist((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "house")) {
				if (jsonParserFieldValue != null) {
					poa.setHouse((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "lm")) {
				if (jsonParserFieldValue != null) {
					poa.setLm((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "loc")) {
				if (jsonParserFieldValue != null) {
					poa.setLoc((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "pc")) {
				if (jsonParserFieldValue != null) {
					poa.setPc((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "po")) {
				if (jsonParserFieldValue != null) {
					poa.setPo((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "state")) {
				if (jsonParserFieldValue != null) {
					poa.setState((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "street")) {
				if (jsonParserFieldValue != null) {
					poa.setStreet((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "subdist")) {
				if (jsonParserFieldValue != null) {
					poa.setSubdist((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "vtc")) {
				if (jsonParserFieldValue != null) {
					poa.setVtc((String)jsonParserFieldValue);
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