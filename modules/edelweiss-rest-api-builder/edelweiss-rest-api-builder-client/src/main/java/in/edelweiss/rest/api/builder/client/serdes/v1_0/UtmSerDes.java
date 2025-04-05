package in.edelweiss.rest.api.builder.client.serdes.v1_0;

import in.edelweiss.rest.api.builder.client.dto.v1_0.Utm;
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
public class UtmSerDes {

	public static Utm toDTO(String json) {
		UtmJSONParser utmJSONParser = new UtmJSONParser();

		return utmJSONParser.parseToDTO(json);
	}

	public static Utm[] toDTOs(String json) {
		UtmJSONParser utmJSONParser = new UtmJSONParser();

		return utmJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Utm utm) {
		if (utm == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (utm.getAdgroupId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"adgroupId\": ");

			sb.append("\"");

			sb.append(_escape(utm.getAdgroupId()));

			sb.append("\"");
		}

		if (utm.getCampaignId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"campaignId\": ");

			sb.append("\"");

			sb.append(_escape(utm.getCampaignId()));

			sb.append("\"");
		}

		if (utm.getDevice() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"device\": ");

			sb.append("\"");

			sb.append(_escape(utm.getDevice()));

			sb.append("\"");
		}

		if (utm.getGclid() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"gclid\": ");

			sb.append("\"");

			sb.append(_escape(utm.getGclid()));

			sb.append("\"");
		}

		if (utm.getUtmCampaign() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"utmCampaign\": ");

			sb.append("\"");

			sb.append(_escape(utm.getUtmCampaign()));

			sb.append("\"");
		}

		if (utm.getUtmContent() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"utmContent\": ");

			sb.append("\"");

			sb.append(_escape(utm.getUtmContent()));

			sb.append("\"");
		}

		if (utm.getUtmCreative() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"utmCreative\": ");

			sb.append("\"");

			sb.append(_escape(utm.getUtmCreative()));

			sb.append("\"");
		}

		if (utm.getUtmMedium() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"utmMedium\": ");

			sb.append("\"");

			sb.append(_escape(utm.getUtmMedium()));

			sb.append("\"");
		}

		if (utm.getUtmPlacement() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"utmPlacement\": ");

			sb.append("\"");

			sb.append(_escape(utm.getUtmPlacement()));

			sb.append("\"");
		}

		if (utm.getUtmSource() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"utmSource\": ");

			sb.append("\"");

			sb.append(_escape(utm.getUtmSource()));

			sb.append("\"");
		}

		if (utm.getUtmTerm() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"utmTerm\": ");

			sb.append("\"");

			sb.append(_escape(utm.getUtmTerm()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		UtmJSONParser utmJSONParser = new UtmJSONParser();

		return utmJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Utm utm) {
		if (utm == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (utm.getAdgroupId() == null) {
			map.put("adgroupId", null);
		}
		else {
			map.put("adgroupId", String.valueOf(utm.getAdgroupId()));
		}

		if (utm.getCampaignId() == null) {
			map.put("campaignId", null);
		}
		else {
			map.put("campaignId", String.valueOf(utm.getCampaignId()));
		}

		if (utm.getDevice() == null) {
			map.put("device", null);
		}
		else {
			map.put("device", String.valueOf(utm.getDevice()));
		}

		if (utm.getGclid() == null) {
			map.put("gclid", null);
		}
		else {
			map.put("gclid", String.valueOf(utm.getGclid()));
		}

		if (utm.getUtmCampaign() == null) {
			map.put("utmCampaign", null);
		}
		else {
			map.put("utmCampaign", String.valueOf(utm.getUtmCampaign()));
		}

		if (utm.getUtmContent() == null) {
			map.put("utmContent", null);
		}
		else {
			map.put("utmContent", String.valueOf(utm.getUtmContent()));
		}

		if (utm.getUtmCreative() == null) {
			map.put("utmCreative", null);
		}
		else {
			map.put("utmCreative", String.valueOf(utm.getUtmCreative()));
		}

		if (utm.getUtmMedium() == null) {
			map.put("utmMedium", null);
		}
		else {
			map.put("utmMedium", String.valueOf(utm.getUtmMedium()));
		}

		if (utm.getUtmPlacement() == null) {
			map.put("utmPlacement", null);
		}
		else {
			map.put("utmPlacement", String.valueOf(utm.getUtmPlacement()));
		}

		if (utm.getUtmSource() == null) {
			map.put("utmSource", null);
		}
		else {
			map.put("utmSource", String.valueOf(utm.getUtmSource()));
		}

		if (utm.getUtmTerm() == null) {
			map.put("utmTerm", null);
		}
		else {
			map.put("utmTerm", String.valueOf(utm.getUtmTerm()));
		}

		return map;
	}

	public static class UtmJSONParser extends BaseJSONParser<Utm> {

		@Override
		protected Utm createDTO() {
			return new Utm();
		}

		@Override
		protected Utm[] createDTOArray(int size) {
			return new Utm[size];
		}

		@Override
		protected void setField(
			Utm utm, String jsonParserFieldName, Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "adgroupId")) {
				if (jsonParserFieldValue != null) {
					utm.setAdgroupId((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "campaignId")) {
				if (jsonParserFieldValue != null) {
					utm.setCampaignId((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "device")) {
				if (jsonParserFieldValue != null) {
					utm.setDevice((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "gclid")) {
				if (jsonParserFieldValue != null) {
					utm.setGclid((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "utmCampaign")) {
				if (jsonParserFieldValue != null) {
					utm.setUtmCampaign((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "utmContent")) {
				if (jsonParserFieldValue != null) {
					utm.setUtmContent((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "utmCreative")) {
				if (jsonParserFieldValue != null) {
					utm.setUtmCreative((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "utmMedium")) {
				if (jsonParserFieldValue != null) {
					utm.setUtmMedium((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "utmPlacement")) {
				if (jsonParserFieldValue != null) {
					utm.setUtmPlacement((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "utmSource")) {
				if (jsonParserFieldValue != null) {
					utm.setUtmSource((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "utmTerm")) {
				if (jsonParserFieldValue != null) {
					utm.setUtmTerm((String)jsonParserFieldValue);
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