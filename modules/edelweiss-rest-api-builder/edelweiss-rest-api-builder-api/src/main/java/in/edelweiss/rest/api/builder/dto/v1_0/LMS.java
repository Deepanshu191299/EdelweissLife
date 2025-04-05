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
@GraphQLName(description = "LMS application.", value = "LMS")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "LMS")
public class LMS implements Serializable {

	public static LMS toDTO(String json) {
		return ObjectMapperUtil.readValue(LMS.class, json);
	}

	public static LMS unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(LMS.class, json);
	}

	@Schema(description = "Interest Sessions Params.")
	@Valid
	public InterestSessions getInterestSessions() {
		if (_interestSessionsSupplier != null) {
			interestSessions = _interestSessionsSupplier.get();

			_interestSessionsSupplier = null;
		}

		return interestSessions;
	}

	public void setInterestSessions(InterestSessions interestSessions) {
		this.interestSessions = interestSessions;

		_interestSessionsSupplier = null;
	}

	@JsonIgnore
	public void setInterestSessions(
		UnsafeSupplier<InterestSessions, Exception>
			interestSessionsUnsafeSupplier) {

		_interestSessionsSupplier = () -> {
			try {
				return interestSessionsUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(description = "Interest Sessions Params.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected InterestSessions interestSessions;

	@JsonIgnore
	private Supplier<InterestSessions> _interestSessionsSupplier;

	@Schema(description = "Lead Params.")
	@Valid
	public Lead getLead() {
		if (_leadSupplier != null) {
			lead = _leadSupplier.get();

			_leadSupplier = null;
		}

		return lead;
	}

	public void setLead(Lead lead) {
		this.lead = lead;

		_leadSupplier = null;
	}

	@JsonIgnore
	public void setLead(UnsafeSupplier<Lead, Exception> leadUnsafeSupplier) {
		_leadSupplier = () -> {
			try {
				return leadUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(description = "Lead Params.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Lead lead;

	@JsonIgnore
	private Supplier<Lead> _leadSupplier;

	@Schema(description = "Quote Params.")
	@Valid
	public Quote getQuote() {
		if (_quoteSupplier != null) {
			quote = _quoteSupplier.get();

			_quoteSupplier = null;
		}

		return quote;
	}

	public void setQuote(Quote quote) {
		this.quote = quote;

		_quoteSupplier = null;
	}

	@JsonIgnore
	public void setQuote(UnsafeSupplier<Quote, Exception> quoteUnsafeSupplier) {
		_quoteSupplier = () -> {
			try {
				return quoteUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(description = "Quote Params.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Quote quote;

	@JsonIgnore
	private Supplier<Quote> _quoteSupplier;

	@Schema(description = "UTM Params.")
	@Valid
	public Utm getUtm() {
		if (_utmSupplier != null) {
			utm = _utmSupplier.get();

			_utmSupplier = null;
		}

		return utm;
	}

	public void setUtm(Utm utm) {
		this.utm = utm;

		_utmSupplier = null;
	}

	@JsonIgnore
	public void setUtm(UnsafeSupplier<Utm, Exception> utmUnsafeSupplier) {
		_utmSupplier = () -> {
			try {
				return utmUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(description = "UTM Params.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Utm utm;

	@JsonIgnore
	private Supplier<Utm> _utmSupplier;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof LMS)) {
			return false;
		}

		LMS lms = (LMS)object;

		return Objects.equals(toString(), lms.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		InterestSessions interestSessions = getInterestSessions();

		if (interestSessions != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"interestSessions\": ");

			sb.append(String.valueOf(interestSessions));
		}

		Lead lead = getLead();

		if (lead != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"lead\": ");

			sb.append(String.valueOf(lead));
		}

		Quote quote = getQuote();

		if (quote != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"quote\": ");

			sb.append(String.valueOf(quote));
		}

		Utm utm = getUtm();

		if (utm != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"utm\": ");

			sb.append(String.valueOf(utm));
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "in.edelweiss.rest.api.builder.dto.v1_0.LMS",
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