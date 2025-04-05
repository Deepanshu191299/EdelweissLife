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
@GraphQLName(description = "UTM Params.", value = "Utm")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "Utm")
public class Utm implements Serializable {

	public static Utm toDTO(String json) {
		return ObjectMapperUtil.readValue(Utm.class, json);
	}

	public static Utm unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(Utm.class, json);
	}

	@Schema
	public String getAdgroupId() {
		if (_adgroupIdSupplier != null) {
			adgroupId = _adgroupIdSupplier.get();

			_adgroupIdSupplier = null;
		}

		return adgroupId;
	}

	public void setAdgroupId(String adgroupId) {
		this.adgroupId = adgroupId;

		_adgroupIdSupplier = null;
	}

	@JsonIgnore
	public void setAdgroupId(
		UnsafeSupplier<String, Exception> adgroupIdUnsafeSupplier) {

		_adgroupIdSupplier = () -> {
			try {
				return adgroupIdUnsafeSupplier.get();
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
	protected String adgroupId;

	@JsonIgnore
	private Supplier<String> _adgroupIdSupplier;

	@Schema
	public String getCampaignId() {
		if (_campaignIdSupplier != null) {
			campaignId = _campaignIdSupplier.get();

			_campaignIdSupplier = null;
		}

		return campaignId;
	}

	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;

		_campaignIdSupplier = null;
	}

	@JsonIgnore
	public void setCampaignId(
		UnsafeSupplier<String, Exception> campaignIdUnsafeSupplier) {

		_campaignIdSupplier = () -> {
			try {
				return campaignIdUnsafeSupplier.get();
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
	protected String campaignId;

	@JsonIgnore
	private Supplier<String> _campaignIdSupplier;

	@Schema
	public String getDevice() {
		if (_deviceSupplier != null) {
			device = _deviceSupplier.get();

			_deviceSupplier = null;
		}

		return device;
	}

	public void setDevice(String device) {
		this.device = device;

		_deviceSupplier = null;
	}

	@JsonIgnore
	public void setDevice(
		UnsafeSupplier<String, Exception> deviceUnsafeSupplier) {

		_deviceSupplier = () -> {
			try {
				return deviceUnsafeSupplier.get();
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
	protected String device;

	@JsonIgnore
	private Supplier<String> _deviceSupplier;

	@Schema
	public String getGclid() {
		if (_gclidSupplier != null) {
			gclid = _gclidSupplier.get();

			_gclidSupplier = null;
		}

		return gclid;
	}

	public void setGclid(String gclid) {
		this.gclid = gclid;

		_gclidSupplier = null;
	}

	@JsonIgnore
	public void setGclid(
		UnsafeSupplier<String, Exception> gclidUnsafeSupplier) {

		_gclidSupplier = () -> {
			try {
				return gclidUnsafeSupplier.get();
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
	protected String gclid;

	@JsonIgnore
	private Supplier<String> _gclidSupplier;

	@Schema
	public String getUtmCampaign() {
		if (_utmCampaignSupplier != null) {
			utmCampaign = _utmCampaignSupplier.get();

			_utmCampaignSupplier = null;
		}

		return utmCampaign;
	}

	public void setUtmCampaign(String utmCampaign) {
		this.utmCampaign = utmCampaign;

		_utmCampaignSupplier = null;
	}

	@JsonIgnore
	public void setUtmCampaign(
		UnsafeSupplier<String, Exception> utmCampaignUnsafeSupplier) {

		_utmCampaignSupplier = () -> {
			try {
				return utmCampaignUnsafeSupplier.get();
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
	protected String utmCampaign;

	@JsonIgnore
	private Supplier<String> _utmCampaignSupplier;

	@Schema
	public String getUtmContent() {
		if (_utmContentSupplier != null) {
			utmContent = _utmContentSupplier.get();

			_utmContentSupplier = null;
		}

		return utmContent;
	}

	public void setUtmContent(String utmContent) {
		this.utmContent = utmContent;

		_utmContentSupplier = null;
	}

	@JsonIgnore
	public void setUtmContent(
		UnsafeSupplier<String, Exception> utmContentUnsafeSupplier) {

		_utmContentSupplier = () -> {
			try {
				return utmContentUnsafeSupplier.get();
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
	protected String utmContent;

	@JsonIgnore
	private Supplier<String> _utmContentSupplier;

	@Schema
	public String getUtmCreative() {
		if (_utmCreativeSupplier != null) {
			utmCreative = _utmCreativeSupplier.get();

			_utmCreativeSupplier = null;
		}

		return utmCreative;
	}

	public void setUtmCreative(String utmCreative) {
		this.utmCreative = utmCreative;

		_utmCreativeSupplier = null;
	}

	@JsonIgnore
	public void setUtmCreative(
		UnsafeSupplier<String, Exception> utmCreativeUnsafeSupplier) {

		_utmCreativeSupplier = () -> {
			try {
				return utmCreativeUnsafeSupplier.get();
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
	protected String utmCreative;

	@JsonIgnore
	private Supplier<String> _utmCreativeSupplier;

	@Schema
	public String getUtmMedium() {
		if (_utmMediumSupplier != null) {
			utmMedium = _utmMediumSupplier.get();

			_utmMediumSupplier = null;
		}

		return utmMedium;
	}

	public void setUtmMedium(String utmMedium) {
		this.utmMedium = utmMedium;

		_utmMediumSupplier = null;
	}

	@JsonIgnore
	public void setUtmMedium(
		UnsafeSupplier<String, Exception> utmMediumUnsafeSupplier) {

		_utmMediumSupplier = () -> {
			try {
				return utmMediumUnsafeSupplier.get();
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
	protected String utmMedium;

	@JsonIgnore
	private Supplier<String> _utmMediumSupplier;

	@Schema
	public String getUtmPlacement() {
		if (_utmPlacementSupplier != null) {
			utmPlacement = _utmPlacementSupplier.get();

			_utmPlacementSupplier = null;
		}

		return utmPlacement;
	}

	public void setUtmPlacement(String utmPlacement) {
		this.utmPlacement = utmPlacement;

		_utmPlacementSupplier = null;
	}

	@JsonIgnore
	public void setUtmPlacement(
		UnsafeSupplier<String, Exception> utmPlacementUnsafeSupplier) {

		_utmPlacementSupplier = () -> {
			try {
				return utmPlacementUnsafeSupplier.get();
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
	protected String utmPlacement;

	@JsonIgnore
	private Supplier<String> _utmPlacementSupplier;

	@Schema
	public String getUtmSource() {
		if (_utmSourceSupplier != null) {
			utmSource = _utmSourceSupplier.get();

			_utmSourceSupplier = null;
		}

		return utmSource;
	}

	public void setUtmSource(String utmSource) {
		this.utmSource = utmSource;

		_utmSourceSupplier = null;
	}

	@JsonIgnore
	public void setUtmSource(
		UnsafeSupplier<String, Exception> utmSourceUnsafeSupplier) {

		_utmSourceSupplier = () -> {
			try {
				return utmSourceUnsafeSupplier.get();
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
	protected String utmSource;

	@JsonIgnore
	private Supplier<String> _utmSourceSupplier;

	@Schema
	public String getUtmTerm() {
		if (_utmTermSupplier != null) {
			utmTerm = _utmTermSupplier.get();

			_utmTermSupplier = null;
		}

		return utmTerm;
	}

	public void setUtmTerm(String utmTerm) {
		this.utmTerm = utmTerm;

		_utmTermSupplier = null;
	}

	@JsonIgnore
	public void setUtmTerm(
		UnsafeSupplier<String, Exception> utmTermUnsafeSupplier) {

		_utmTermSupplier = () -> {
			try {
				return utmTermUnsafeSupplier.get();
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
	protected String utmTerm;

	@JsonIgnore
	private Supplier<String> _utmTermSupplier;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Utm)) {
			return false;
		}

		Utm utm = (Utm)object;

		return Objects.equals(toString(), utm.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		String adgroupId = getAdgroupId();

		if (adgroupId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"adgroupId\": ");

			sb.append("\"");

			sb.append(_escape(adgroupId));

			sb.append("\"");
		}

		String campaignId = getCampaignId();

		if (campaignId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"campaignId\": ");

			sb.append("\"");

			sb.append(_escape(campaignId));

			sb.append("\"");
		}

		String device = getDevice();

		if (device != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"device\": ");

			sb.append("\"");

			sb.append(_escape(device));

			sb.append("\"");
		}

		String gclid = getGclid();

		if (gclid != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"gclid\": ");

			sb.append("\"");

			sb.append(_escape(gclid));

			sb.append("\"");
		}

		String utmCampaign = getUtmCampaign();

		if (utmCampaign != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"utmCampaign\": ");

			sb.append("\"");

			sb.append(_escape(utmCampaign));

			sb.append("\"");
		}

		String utmContent = getUtmContent();

		if (utmContent != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"utmContent\": ");

			sb.append("\"");

			sb.append(_escape(utmContent));

			sb.append("\"");
		}

		String utmCreative = getUtmCreative();

		if (utmCreative != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"utmCreative\": ");

			sb.append("\"");

			sb.append(_escape(utmCreative));

			sb.append("\"");
		}

		String utmMedium = getUtmMedium();

		if (utmMedium != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"utmMedium\": ");

			sb.append("\"");

			sb.append(_escape(utmMedium));

			sb.append("\"");
		}

		String utmPlacement = getUtmPlacement();

		if (utmPlacement != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"utmPlacement\": ");

			sb.append("\"");

			sb.append(_escape(utmPlacement));

			sb.append("\"");
		}

		String utmSource = getUtmSource();

		if (utmSource != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"utmSource\": ");

			sb.append("\"");

			sb.append(_escape(utmSource));

			sb.append("\"");
		}

		String utmTerm = getUtmTerm();

		if (utmTerm != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"utmTerm\": ");

			sb.append("\"");

			sb.append(_escape(utmTerm));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "in.edelweiss.rest.api.builder.dto.v1_0.Utm",
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