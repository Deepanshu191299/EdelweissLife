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

import javax.validation.Valid;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author pramod.dk
 * @generated
 */
@Generated("")
@GraphQLName(description = "The application's Logo URL.", value = "Data")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "Data")
public class Data implements Serializable {

	public static Data toDTO(String json) {
		return ObjectMapperUtil.readValue(Data.class, json);
	}

	public static Data unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(Data.class, json);
	}

	@Schema(description = "photo.")
	public String getPhoto() {
		if (_photoSupplier != null) {
			photo = _photoSupplier.get();

			_photoSupplier = null;
		}

		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;

		_photoSupplier = null;
	}

	@JsonIgnore
	public void setPhoto(
		UnsafeSupplier<String, Exception> photoUnsafeSupplier) {

		_photoSupplier = () -> {
			try {
				return photoUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(description = "photo.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String photo;

	@JsonIgnore
	private Supplier<String> _photoSupplier;

	@Schema(description = "POA Data.")
	@Valid
	public Poa getPoa() {
		if (_poaSupplier != null) {
			poa = _poaSupplier.get();

			_poaSupplier = null;
		}

		return poa;
	}

	public void setPoa(Poa poa) {
		this.poa = poa;

		_poaSupplier = null;
	}

	@JsonIgnore
	public void setPoa(UnsafeSupplier<Poa, Exception> poaUnsafeSupplier) {
		_poaSupplier = () -> {
			try {
				return poaUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(description = "POA Data.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Poa poa;

	@JsonIgnore
	private Supplier<Poa> _poaSupplier;

	@Schema(description = "photo.")
	@Valid
	public Poi getPoi() {
		if (_poiSupplier != null) {
			poi = _poiSupplier.get();

			_poiSupplier = null;
		}

		return poi;
	}

	public void setPoi(Poi poi) {
		this.poi = poi;

		_poiSupplier = null;
	}

	@JsonIgnore
	public void setPoi(UnsafeSupplier<Poi, Exception> poiUnsafeSupplier) {
		_poiSupplier = () -> {
			try {
				return poiUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(description = "photo.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Poi poi;

	@JsonIgnore
	private Supplier<Poi> _poiSupplier;

	@Schema(description = "uid.")
	public String getUid() {
		if (_uidSupplier != null) {
			uid = _uidSupplier.get();

			_uidSupplier = null;
		}

		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;

		_uidSupplier = null;
	}

	@JsonIgnore
	public void setUid(UnsafeSupplier<String, Exception> uidUnsafeSupplier) {
		_uidSupplier = () -> {
			try {
				return uidUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(description = "uid.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String uid;

	@JsonIgnore
	private Supplier<String> _uidSupplier;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Data)) {
			return false;
		}

		Data data = (Data)object;

		return Objects.equals(toString(), data.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		String photo = getPhoto();

		if (photo != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"photo\": ");

			sb.append("\"");

			sb.append(_escape(photo));

			sb.append("\"");
		}

		Poa poa = getPoa();

		if (poa != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"poa\": ");

			sb.append(String.valueOf(poa));
		}

		Poi poi = getPoi();

		if (poi != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"poi\": ");

			sb.append(String.valueOf(poi));
		}

		String uid = getUid();

		if (uid != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"uid\": ");

			sb.append("\"");

			sb.append(_escape(uid));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "in.edelweiss.rest.api.builder.dto.v1_0.Data",
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