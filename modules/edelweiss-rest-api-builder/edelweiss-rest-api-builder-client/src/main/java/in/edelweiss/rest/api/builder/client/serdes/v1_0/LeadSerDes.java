package in.edelweiss.rest.api.builder.client.serdes.v1_0;

import in.edelweiss.rest.api.builder.client.dto.v1_0.Lead;
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
public class LeadSerDes {

	public static Lead toDTO(String json) {
		LeadJSONParser leadJSONParser = new LeadJSONParser();

		return leadJSONParser.parseToDTO(json);
	}

	public static Lead[] toDTOs(String json) {
		LeadJSONParser leadJSONParser = new LeadJSONParser();

		return leadJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Lead lead) {
		if (lead == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (lead.getCampaingId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"campaingId\": ");

			sb.append("\"");

			sb.append(_escape(lead.getCampaingId()));

			sb.append("\"");
		}

		if (lead.getChannelId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"channelId\": ");

			sb.append("\"");

			sb.append(_escape(lead.getChannelId()));

			sb.append("\"");
		}

		if (lead.getDob() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dob\": ");

			sb.append("\"");

			sb.append(_escape(lead.getDob()));

			sb.append("\"");
		}

		if (lead.getEmail() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"email\": ");

			sb.append("\"");

			sb.append(_escape(lead.getEmail()));

			sb.append("\"");
		}

		if (lead.getGender() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"gender\": ");

			sb.append("\"");

			sb.append(_escape(lead.getGender()));

			sb.append("\"");
		}

		if (lead.getLeadFormType() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"leadFormType\": ");

			sb.append("\"");

			sb.append(_escape(lead.getLeadFormType()));

			sb.append("\"");
		}

		if (lead.getName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(lead.getName()));

			sb.append("\"");
		}

		if (lead.getNationality() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"nationality\": ");

			sb.append("\"");

			sb.append(_escape(lead.getNationality()));

			sb.append("\"");
		}

		if (lead.getOccupation() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"occupation\": ");

			sb.append("\"");

			sb.append(_escape(lead.getOccupation()));

			sb.append("\"");
		}

		if (lead.getPhone() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"phone\": ");

			sb.append("\"");

			sb.append(_escape(lead.getPhone()));

			sb.append("\"");
		}

		if (lead.getProduct() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"product\": ");

			sb.append("\"");

			sb.append(_escape(lead.getProduct()));

			sb.append("\"");
		}

		if (lead.getSiteSection() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"siteSection\": ");

			sb.append("\"");

			sb.append(_escape(lead.getSiteSection()));

			sb.append("\"");
		}

		if (lead.getSource() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"source\": ");

			sb.append("\"");

			sb.append(_escape(lead.getSource()));

			sb.append("\"");
		}

		if (lead.getVendorId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"vendorId\": ");

			sb.append("\"");

			sb.append(_escape(lead.getVendorId()));

			sb.append("\"");
		}

		if (lead.getVisitorId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"visitorId\": ");

			sb.append("\"");

			sb.append(_escape(lead.getVisitorId()));

			sb.append("\"");
		}

		if (lead.getWebURL() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"webURL\": ");

			sb.append("\"");

			sb.append(_escape(lead.getWebURL()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		LeadJSONParser leadJSONParser = new LeadJSONParser();

		return leadJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Lead lead) {
		if (lead == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (lead.getCampaingId() == null) {
			map.put("campaingId", null);
		}
		else {
			map.put("campaingId", String.valueOf(lead.getCampaingId()));
		}

		if (lead.getChannelId() == null) {
			map.put("channelId", null);
		}
		else {
			map.put("channelId", String.valueOf(lead.getChannelId()));
		}

		if (lead.getDob() == null) {
			map.put("dob", null);
		}
		else {
			map.put("dob", String.valueOf(lead.getDob()));
		}

		if (lead.getEmail() == null) {
			map.put("email", null);
		}
		else {
			map.put("email", String.valueOf(lead.getEmail()));
		}

		if (lead.getGender() == null) {
			map.put("gender", null);
		}
		else {
			map.put("gender", String.valueOf(lead.getGender()));
		}

		if (lead.getLeadFormType() == null) {
			map.put("leadFormType", null);
		}
		else {
			map.put("leadFormType", String.valueOf(lead.getLeadFormType()));
		}

		if (lead.getName() == null) {
			map.put("name", null);
		}
		else {
			map.put("name", String.valueOf(lead.getName()));
		}

		if (lead.getNationality() == null) {
			map.put("nationality", null);
		}
		else {
			map.put("nationality", String.valueOf(lead.getNationality()));
		}

		if (lead.getOccupation() == null) {
			map.put("occupation", null);
		}
		else {
			map.put("occupation", String.valueOf(lead.getOccupation()));
		}

		if (lead.getPhone() == null) {
			map.put("phone", null);
		}
		else {
			map.put("phone", String.valueOf(lead.getPhone()));
		}

		if (lead.getProduct() == null) {
			map.put("product", null);
		}
		else {
			map.put("product", String.valueOf(lead.getProduct()));
		}

		if (lead.getSiteSection() == null) {
			map.put("siteSection", null);
		}
		else {
			map.put("siteSection", String.valueOf(lead.getSiteSection()));
		}

		if (lead.getSource() == null) {
			map.put("source", null);
		}
		else {
			map.put("source", String.valueOf(lead.getSource()));
		}

		if (lead.getVendorId() == null) {
			map.put("vendorId", null);
		}
		else {
			map.put("vendorId", String.valueOf(lead.getVendorId()));
		}

		if (lead.getVisitorId() == null) {
			map.put("visitorId", null);
		}
		else {
			map.put("visitorId", String.valueOf(lead.getVisitorId()));
		}

		if (lead.getWebURL() == null) {
			map.put("webURL", null);
		}
		else {
			map.put("webURL", String.valueOf(lead.getWebURL()));
		}

		return map;
	}

	public static class LeadJSONParser extends BaseJSONParser<Lead> {

		@Override
		protected Lead createDTO() {
			return new Lead();
		}

		@Override
		protected Lead[] createDTOArray(int size) {
			return new Lead[size];
		}

		@Override
		protected void setField(
			Lead lead, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "campaingId")) {
				if (jsonParserFieldValue != null) {
					lead.setCampaingId((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "channelId")) {
				if (jsonParserFieldValue != null) {
					lead.setChannelId((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "dob")) {
				if (jsonParserFieldValue != null) {
					lead.setDob((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "email")) {
				if (jsonParserFieldValue != null) {
					lead.setEmail((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "gender")) {
				if (jsonParserFieldValue != null) {
					lead.setGender((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "leadFormType")) {
				if (jsonParserFieldValue != null) {
					lead.setLeadFormType((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "name")) {
				if (jsonParserFieldValue != null) {
					lead.setName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "nationality")) {
				if (jsonParserFieldValue != null) {
					lead.setNationality((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "occupation")) {
				if (jsonParserFieldValue != null) {
					lead.setOccupation((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "phone")) {
				if (jsonParserFieldValue != null) {
					lead.setPhone((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "product")) {
				if (jsonParserFieldValue != null) {
					lead.setProduct((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "siteSection")) {
				if (jsonParserFieldValue != null) {
					lead.setSiteSection((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "source")) {
				if (jsonParserFieldValue != null) {
					lead.setSource((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "vendorId")) {
				if (jsonParserFieldValue != null) {
					lead.setVendorId((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "visitorId")) {
				if (jsonParserFieldValue != null) {
					lead.setVisitorId((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "webURL")) {
				if (jsonParserFieldValue != null) {
					lead.setWebURL((String)jsonParserFieldValue);
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