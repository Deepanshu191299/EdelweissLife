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
@GraphQLName(description = "Quote Params.", value = "Quote")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "Quote")
public class Quote implements Serializable {

	public static Quote toDTO(String json) {
		return ObjectMapperUtil.readValue(Quote.class, json);
	}

	public static Quote unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(Quote.class, json);
	}

	@Schema
	public String getPayoutOptions() {
		if (_payoutOptionsSupplier != null) {
			payoutOptions = _payoutOptionsSupplier.get();

			_payoutOptionsSupplier = null;
		}

		return payoutOptions;
	}

	public void setPayoutOptions(String payoutOptions) {
		this.payoutOptions = payoutOptions;

		_payoutOptionsSupplier = null;
	}

	@JsonIgnore
	public void setPayoutOptions(
		UnsafeSupplier<String, Exception> payoutOptionsUnsafeSupplier) {

		_payoutOptionsSupplier = () -> {
			try {
				return payoutOptionsUnsafeSupplier.get();
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
	protected String payoutOptions;

	@JsonIgnore
	private Supplier<String> _payoutOptionsSupplier;

	@Schema
	public String getStage() {
		if (_stageSupplier != null) {
			stage = _stageSupplier.get();

			_stageSupplier = null;
		}

		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;

		_stageSupplier = null;
	}

	@JsonIgnore
	public void setStage(
		UnsafeSupplier<String, Exception> stageUnsafeSupplier) {

		_stageSupplier = () -> {
			try {
				return stageUnsafeSupplier.get();
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
	protected String stage;

	@JsonIgnore
	private Supplier<String> _stageSupplier;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Quote)) {
			return false;
		}

		Quote quote = (Quote)object;

		return Objects.equals(toString(), quote.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		String payoutOptions = getPayoutOptions();

		if (payoutOptions != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"payoutOptions\": ");

			sb.append("\"");

			sb.append(_escape(payoutOptions));

			sb.append("\"");
		}

		String stage = getStage();

		if (stage != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"stage\": ");

			sb.append("\"");

			sb.append(_escape(stage));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "in.edelweiss.rest.api.builder.dto.v1_0.Quote",
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