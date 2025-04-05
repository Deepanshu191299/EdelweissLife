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
@GraphQLName(description = "Ekyc application.", value = "Ekyc")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "Ekyc")
public class Ekyc implements Serializable {

	public static Ekyc toDTO(String json) {
		return ObjectMapperUtil.readValue(Ekyc.class, json);
	}

	public static Ekyc unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(Ekyc.class, json);
	}

	@Schema(description = "The application's Logo URL.")
	@Valid
	public Data getData() {
		if (_dataSupplier != null) {
			data = _dataSupplier.get();

			_dataSupplier = null;
		}

		return data;
	}

	public void setData(Data data) {
		this.data = data;

		_dataSupplier = null;
	}

	@JsonIgnore
	public void setData(UnsafeSupplier<Data, Exception> dataUnsafeSupplier) {
		_dataSupplier = () -> {
			try {
				return dataUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(description = "The application's Logo URL.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Data data;

	@JsonIgnore
	private Supplier<Data> _dataSupplier;

	@Schema(description = "Ekyc Message.")
	public String getEkycMessage() {
		if (_ekycMessageSupplier != null) {
			ekycMessage = _ekycMessageSupplier.get();

			_ekycMessageSupplier = null;
		}

		return ekycMessage;
	}

	public void setEkycMessage(String ekycMessage) {
		this.ekycMessage = ekycMessage;

		_ekycMessageSupplier = null;
	}

	@JsonIgnore
	public void setEkycMessage(
		UnsafeSupplier<String, Exception> ekycMessageUnsafeSupplier) {

		_ekycMessageSupplier = () -> {
			try {
				return ekycMessageUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(description = "Ekyc Message.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String ekycMessage;

	@JsonIgnore
	private Supplier<String> _ekycMessageSupplier;

	@Schema(description = "Ekyc Status.")
	public String getEkycStatus() {
		if (_ekycStatusSupplier != null) {
			ekycStatus = _ekycStatusSupplier.get();

			_ekycStatusSupplier = null;
		}

		return ekycStatus;
	}

	public void setEkycStatus(String ekycStatus) {
		this.ekycStatus = ekycStatus;

		_ekycStatusSupplier = null;
	}

	@JsonIgnore
	public void setEkycStatus(
		UnsafeSupplier<String, Exception> ekycStatusUnsafeSupplier) {

		_ekycStatusSupplier = () -> {
			try {
				return ekycStatusUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(description = "Ekyc Status.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String ekycStatus;

	@JsonIgnore
	private Supplier<String> _ekycStatusSupplier;

	@Schema(description = "Ekyc Timestamp.")
	public String getEkycTimeStamp() {
		if (_ekycTimeStampSupplier != null) {
			ekycTimeStamp = _ekycTimeStampSupplier.get();

			_ekycTimeStampSupplier = null;
		}

		return ekycTimeStamp;
	}

	public void setEkycTimeStamp(String ekycTimeStamp) {
		this.ekycTimeStamp = ekycTimeStamp;

		_ekycTimeStampSupplier = null;
	}

	@JsonIgnore
	public void setEkycTimeStamp(
		UnsafeSupplier<String, Exception> ekycTimeStampUnsafeSupplier) {

		_ekycTimeStampSupplier = () -> {
			try {
				return ekycTimeStampUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(description = "Ekyc Timestamp.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String ekycTimeStamp;

	@JsonIgnore
	private Supplier<String> _ekycTimeStampSupplier;

	@Schema(description = "Ekyc Transaction ID.")
	public String getTxnId() {
		if (_txnIdSupplier != null) {
			txnId = _txnIdSupplier.get();

			_txnIdSupplier = null;
		}

		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;

		_txnIdSupplier = null;
	}

	@JsonIgnore
	public void setTxnId(
		UnsafeSupplier<String, Exception> txnIdUnsafeSupplier) {

		_txnIdSupplier = () -> {
			try {
				return txnIdUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(description = "Ekyc Transaction ID.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String txnId;

	@JsonIgnore
	private Supplier<String> _txnIdSupplier;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Ekyc)) {
			return false;
		}

		Ekyc ekyc = (Ekyc)object;

		return Objects.equals(toString(), ekyc.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		Data data = getData();

		if (data != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"data\": ");

			sb.append(String.valueOf(data));
		}

		String ekycMessage = getEkycMessage();

		if (ekycMessage != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"ekycMessage\": ");

			sb.append("\"");

			sb.append(_escape(ekycMessage));

			sb.append("\"");
		}

		String ekycStatus = getEkycStatus();

		if (ekycStatus != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"ekycStatus\": ");

			sb.append("\"");

			sb.append(_escape(ekycStatus));

			sb.append("\"");
		}

		String ekycTimeStamp = getEkycTimeStamp();

		if (ekycTimeStamp != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"ekycTimeStamp\": ");

			sb.append("\"");

			sb.append(_escape(ekycTimeStamp));

			sb.append("\"");
		}

		String txnId = getTxnId();

		if (txnId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"txnId\": ");

			sb.append("\"");

			sb.append(_escape(txnId));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "in.edelweiss.rest.api.builder.dto.v1_0.Ekyc",
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