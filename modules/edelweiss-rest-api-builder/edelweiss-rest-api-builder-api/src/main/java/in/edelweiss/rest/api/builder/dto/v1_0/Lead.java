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
@GraphQLName(description = "Lead Params.", value = "Lead")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "Lead")
public class Lead implements Serializable {

	public static Lead toDTO(String json) {
		return ObjectMapperUtil.readValue(Lead.class, json);
	}

	public static Lead unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(Lead.class, json);
	}

	@Schema
	public String getCampaingId() {
		if (_campaingIdSupplier != null) {
			campaingId = _campaingIdSupplier.get();

			_campaingIdSupplier = null;
		}

		return campaingId;
	}

	public void setCampaingId(String campaingId) {
		this.campaingId = campaingId;

		_campaingIdSupplier = null;
	}

	@JsonIgnore
	public void setCampaingId(
		UnsafeSupplier<String, Exception> campaingIdUnsafeSupplier) {

		_campaingIdSupplier = () -> {
			try {
				return campaingIdUnsafeSupplier.get();
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
	protected String campaingId;

	@JsonIgnore
	private Supplier<String> _campaingIdSupplier;

	@Schema
	public String getChannelId() {
		if (_channelIdSupplier != null) {
			channelId = _channelIdSupplier.get();

			_channelIdSupplier = null;
		}

		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;

		_channelIdSupplier = null;
	}

	@JsonIgnore
	public void setChannelId(
		UnsafeSupplier<String, Exception> channelIdUnsafeSupplier) {

		_channelIdSupplier = () -> {
			try {
				return channelIdUnsafeSupplier.get();
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
	protected String channelId;

	@JsonIgnore
	private Supplier<String> _channelIdSupplier;

	@Schema
	public String getDob() {
		if (_dobSupplier != null) {
			dob = _dobSupplier.get();

			_dobSupplier = null;
		}

		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;

		_dobSupplier = null;
	}

	@JsonIgnore
	public void setDob(UnsafeSupplier<String, Exception> dobUnsafeSupplier) {
		_dobSupplier = () -> {
			try {
				return dobUnsafeSupplier.get();
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
	protected String dob;

	@JsonIgnore
	private Supplier<String> _dobSupplier;

	@Schema
	public String getEmail() {
		if (_emailSupplier != null) {
			email = _emailSupplier.get();

			_emailSupplier = null;
		}

		return email;
	}

	public void setEmail(String email) {
		this.email = email;

		_emailSupplier = null;
	}

	@JsonIgnore
	public void setEmail(
		UnsafeSupplier<String, Exception> emailUnsafeSupplier) {

		_emailSupplier = () -> {
			try {
				return emailUnsafeSupplier.get();
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
	protected String email;

	@JsonIgnore
	private Supplier<String> _emailSupplier;

	@Schema
	public String getGender() {
		if (_genderSupplier != null) {
			gender = _genderSupplier.get();

			_genderSupplier = null;
		}

		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;

		_genderSupplier = null;
	}

	@JsonIgnore
	public void setGender(
		UnsafeSupplier<String, Exception> genderUnsafeSupplier) {

		_genderSupplier = () -> {
			try {
				return genderUnsafeSupplier.get();
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
	protected String gender;

	@JsonIgnore
	private Supplier<String> _genderSupplier;

	@Schema
	public String getLeadFormType() {
		if (_leadFormTypeSupplier != null) {
			leadFormType = _leadFormTypeSupplier.get();

			_leadFormTypeSupplier = null;
		}

		return leadFormType;
	}

	public void setLeadFormType(String leadFormType) {
		this.leadFormType = leadFormType;

		_leadFormTypeSupplier = null;
	}

	@JsonIgnore
	public void setLeadFormType(
		UnsafeSupplier<String, Exception> leadFormTypeUnsafeSupplier) {

		_leadFormTypeSupplier = () -> {
			try {
				return leadFormTypeUnsafeSupplier.get();
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
	protected String leadFormType;

	@JsonIgnore
	private Supplier<String> _leadFormTypeSupplier;

	@Schema
	public String getName() {
		if (_nameSupplier != null) {
			name = _nameSupplier.get();

			_nameSupplier = null;
		}

		return name;
	}

	public void setName(String name) {
		this.name = name;

		_nameSupplier = null;
	}

	@JsonIgnore
	public void setName(UnsafeSupplier<String, Exception> nameUnsafeSupplier) {
		_nameSupplier = () -> {
			try {
				return nameUnsafeSupplier.get();
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
	protected String name;

	@JsonIgnore
	private Supplier<String> _nameSupplier;

	@Schema
	public String getNationality() {
		if (_nationalitySupplier != null) {
			nationality = _nationalitySupplier.get();

			_nationalitySupplier = null;
		}

		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;

		_nationalitySupplier = null;
	}

	@JsonIgnore
	public void setNationality(
		UnsafeSupplier<String, Exception> nationalityUnsafeSupplier) {

		_nationalitySupplier = () -> {
			try {
				return nationalityUnsafeSupplier.get();
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
	protected String nationality;

	@JsonIgnore
	private Supplier<String> _nationalitySupplier;

	@Schema
	public String getOccupation() {
		if (_occupationSupplier != null) {
			occupation = _occupationSupplier.get();

			_occupationSupplier = null;
		}

		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;

		_occupationSupplier = null;
	}

	@JsonIgnore
	public void setOccupation(
		UnsafeSupplier<String, Exception> occupationUnsafeSupplier) {

		_occupationSupplier = () -> {
			try {
				return occupationUnsafeSupplier.get();
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
	protected String occupation;

	@JsonIgnore
	private Supplier<String> _occupationSupplier;

	@Schema
	public String getPhone() {
		if (_phoneSupplier != null) {
			phone = _phoneSupplier.get();

			_phoneSupplier = null;
		}

		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;

		_phoneSupplier = null;
	}

	@JsonIgnore
	public void setPhone(
		UnsafeSupplier<String, Exception> phoneUnsafeSupplier) {

		_phoneSupplier = () -> {
			try {
				return phoneUnsafeSupplier.get();
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
	protected String phone;

	@JsonIgnore
	private Supplier<String> _phoneSupplier;

	@Schema
	public String getProduct() {
		if (_productSupplier != null) {
			product = _productSupplier.get();

			_productSupplier = null;
		}

		return product;
	}

	public void setProduct(String product) {
		this.product = product;

		_productSupplier = null;
	}

	@JsonIgnore
	public void setProduct(
		UnsafeSupplier<String, Exception> productUnsafeSupplier) {

		_productSupplier = () -> {
			try {
				return productUnsafeSupplier.get();
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
	protected String product;

	@JsonIgnore
	private Supplier<String> _productSupplier;

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
	public String getSource() {
		if (_sourceSupplier != null) {
			source = _sourceSupplier.get();

			_sourceSupplier = null;
		}

		return source;
	}

	public void setSource(String source) {
		this.source = source;

		_sourceSupplier = null;
	}

	@JsonIgnore
	public void setSource(
		UnsafeSupplier<String, Exception> sourceUnsafeSupplier) {

		_sourceSupplier = () -> {
			try {
				return sourceUnsafeSupplier.get();
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
	protected String source;

	@JsonIgnore
	private Supplier<String> _sourceSupplier;

	@Schema
	public String getVendorId() {
		if (_vendorIdSupplier != null) {
			vendorId = _vendorIdSupplier.get();

			_vendorIdSupplier = null;
		}

		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;

		_vendorIdSupplier = null;
	}

	@JsonIgnore
	public void setVendorId(
		UnsafeSupplier<String, Exception> vendorIdUnsafeSupplier) {

		_vendorIdSupplier = () -> {
			try {
				return vendorIdUnsafeSupplier.get();
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
	protected String vendorId;

	@JsonIgnore
	private Supplier<String> _vendorIdSupplier;

	@Schema
	public String getVisitorId() {
		if (_visitorIdSupplier != null) {
			visitorId = _visitorIdSupplier.get();

			_visitorIdSupplier = null;
		}

		return visitorId;
	}

	public void setVisitorId(String visitorId) {
		this.visitorId = visitorId;

		_visitorIdSupplier = null;
	}

	@JsonIgnore
	public void setVisitorId(
		UnsafeSupplier<String, Exception> visitorIdUnsafeSupplier) {

		_visitorIdSupplier = () -> {
			try {
				return visitorIdUnsafeSupplier.get();
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
	protected String visitorId;

	@JsonIgnore
	private Supplier<String> _visitorIdSupplier;

	@Schema
	public String getWebURL() {
		if (_webURLSupplier != null) {
			webURL = _webURLSupplier.get();

			_webURLSupplier = null;
		}

		return webURL;
	}

	public void setWebURL(String webURL) {
		this.webURL = webURL;

		_webURLSupplier = null;
	}

	@JsonIgnore
	public void setWebURL(
		UnsafeSupplier<String, Exception> webURLUnsafeSupplier) {

		_webURLSupplier = () -> {
			try {
				return webURLUnsafeSupplier.get();
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
	protected String webURL;

	@JsonIgnore
	private Supplier<String> _webURLSupplier;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Lead)) {
			return false;
		}

		Lead lead = (Lead)object;

		return Objects.equals(toString(), lead.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		String campaingId = getCampaingId();

		if (campaingId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"campaingId\": ");

			sb.append("\"");

			sb.append(_escape(campaingId));

			sb.append("\"");
		}

		String channelId = getChannelId();

		if (channelId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"channelId\": ");

			sb.append("\"");

			sb.append(_escape(channelId));

			sb.append("\"");
		}

		String dob = getDob();

		if (dob != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dob\": ");

			sb.append("\"");

			sb.append(_escape(dob));

			sb.append("\"");
		}

		String email = getEmail();

		if (email != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"email\": ");

			sb.append("\"");

			sb.append(_escape(email));

			sb.append("\"");
		}

		String gender = getGender();

		if (gender != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"gender\": ");

			sb.append("\"");

			sb.append(_escape(gender));

			sb.append("\"");
		}

		String leadFormType = getLeadFormType();

		if (leadFormType != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"leadFormType\": ");

			sb.append("\"");

			sb.append(_escape(leadFormType));

			sb.append("\"");
		}

		String name = getName();

		if (name != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"name\": ");

			sb.append("\"");

			sb.append(_escape(name));

			sb.append("\"");
		}

		String nationality = getNationality();

		if (nationality != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"nationality\": ");

			sb.append("\"");

			sb.append(_escape(nationality));

			sb.append("\"");
		}

		String occupation = getOccupation();

		if (occupation != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"occupation\": ");

			sb.append("\"");

			sb.append(_escape(occupation));

			sb.append("\"");
		}

		String phone = getPhone();

		if (phone != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"phone\": ");

			sb.append("\"");

			sb.append(_escape(phone));

			sb.append("\"");
		}

		String product = getProduct();

		if (product != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"product\": ");

			sb.append("\"");

			sb.append(_escape(product));

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

		String source = getSource();

		if (source != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"source\": ");

			sb.append("\"");

			sb.append(_escape(source));

			sb.append("\"");
		}

		String vendorId = getVendorId();

		if (vendorId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"vendorId\": ");

			sb.append("\"");

			sb.append(_escape(vendorId));

			sb.append("\"");
		}

		String visitorId = getVisitorId();

		if (visitorId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"visitorId\": ");

			sb.append("\"");

			sb.append(_escape(visitorId));

			sb.append("\"");
		}

		String webURL = getWebURL();

		if (webURL != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"webURL\": ");

			sb.append("\"");

			sb.append(_escape(webURL));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "in.edelweiss.rest.api.builder.dto.v1_0.Lead",
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