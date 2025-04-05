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
@GraphQLName(description = "POA Data.", value = "Poa")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "Poa")
public class Poa implements Serializable {

	public static Poa toDTO(String json) {
		return ObjectMapperUtil.readValue(Poa.class, json);
	}

	public static Poa unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(Poa.class, json);
	}

	@Schema(description = "CO.")
	public String getCo() {
		if (_coSupplier != null) {
			co = _coSupplier.get();

			_coSupplier = null;
		}

		return co;
	}

	public void setCo(String co) {
		this.co = co;

		_coSupplier = null;
	}

	@JsonIgnore
	public void setCo(UnsafeSupplier<String, Exception> coUnsafeSupplier) {
		_coSupplier = () -> {
			try {
				return coUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(description = "CO.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String co;

	@JsonIgnore
	private Supplier<String> _coSupplier;

	@Schema(description = "Country.")
	public String getCountry() {
		if (_countrySupplier != null) {
			country = _countrySupplier.get();

			_countrySupplier = null;
		}

		return country;
	}

	public void setCountry(String country) {
		this.country = country;

		_countrySupplier = null;
	}

	@JsonIgnore
	public void setCountry(
		UnsafeSupplier<String, Exception> countryUnsafeSupplier) {

		_countrySupplier = () -> {
			try {
				return countryUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(description = "Country.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String country;

	@JsonIgnore
	private Supplier<String> _countrySupplier;

	@Schema(description = "District.")
	public String getDist() {
		if (_distSupplier != null) {
			dist = _distSupplier.get();

			_distSupplier = null;
		}

		return dist;
	}

	public void setDist(String dist) {
		this.dist = dist;

		_distSupplier = null;
	}

	@JsonIgnore
	public void setDist(UnsafeSupplier<String, Exception> distUnsafeSupplier) {
		_distSupplier = () -> {
			try {
				return distUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(description = "District.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String dist;

	@JsonIgnore
	private Supplier<String> _distSupplier;

	@Schema(description = "House No.")
	public String getHouse() {
		if (_houseSupplier != null) {
			house = _houseSupplier.get();

			_houseSupplier = null;
		}

		return house;
	}

	public void setHouse(String house) {
		this.house = house;

		_houseSupplier = null;
	}

	@JsonIgnore
	public void setHouse(
		UnsafeSupplier<String, Exception> houseUnsafeSupplier) {

		_houseSupplier = () -> {
			try {
				return houseUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(description = "House No.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String house;

	@JsonIgnore
	private Supplier<String> _houseSupplier;

	@Schema(description = "LM.")
	public String getLm() {
		if (_lmSupplier != null) {
			lm = _lmSupplier.get();

			_lmSupplier = null;
		}

		return lm;
	}

	public void setLm(String lm) {
		this.lm = lm;

		_lmSupplier = null;
	}

	@JsonIgnore
	public void setLm(UnsafeSupplier<String, Exception> lmUnsafeSupplier) {
		_lmSupplier = () -> {
			try {
				return lmUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(description = "LM.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String lm;

	@JsonIgnore
	private Supplier<String> _lmSupplier;

	@Schema(description = "LOC.")
	public String getLoc() {
		if (_locSupplier != null) {
			loc = _locSupplier.get();

			_locSupplier = null;
		}

		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;

		_locSupplier = null;
	}

	@JsonIgnore
	public void setLoc(UnsafeSupplier<String, Exception> locUnsafeSupplier) {
		_locSupplier = () -> {
			try {
				return locUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(description = "LOC.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String loc;

	@JsonIgnore
	private Supplier<String> _locSupplier;

	@Schema(description = "PC.")
	public String getPc() {
		if (_pcSupplier != null) {
			pc = _pcSupplier.get();

			_pcSupplier = null;
		}

		return pc;
	}

	public void setPc(String pc) {
		this.pc = pc;

		_pcSupplier = null;
	}

	@JsonIgnore
	public void setPc(UnsafeSupplier<String, Exception> pcUnsafeSupplier) {
		_pcSupplier = () -> {
			try {
				return pcUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(description = "PC.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String pc;

	@JsonIgnore
	private Supplier<String> _pcSupplier;

	@Schema(description = "PO.")
	public String getPo() {
		if (_poSupplier != null) {
			po = _poSupplier.get();

			_poSupplier = null;
		}

		return po;
	}

	public void setPo(String po) {
		this.po = po;

		_poSupplier = null;
	}

	@JsonIgnore
	public void setPo(UnsafeSupplier<String, Exception> poUnsafeSupplier) {
		_poSupplier = () -> {
			try {
				return poUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(description = "PO.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String po;

	@JsonIgnore
	private Supplier<String> _poSupplier;

	@Schema(description = "State.")
	public String getState() {
		if (_stateSupplier != null) {
			state = _stateSupplier.get();

			_stateSupplier = null;
		}

		return state;
	}

	public void setState(String state) {
		this.state = state;

		_stateSupplier = null;
	}

	@JsonIgnore
	public void setState(
		UnsafeSupplier<String, Exception> stateUnsafeSupplier) {

		_stateSupplier = () -> {
			try {
				return stateUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(description = "State.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String state;

	@JsonIgnore
	private Supplier<String> _stateSupplier;

	@Schema(description = "Street.")
	public String getStreet() {
		if (_streetSupplier != null) {
			street = _streetSupplier.get();

			_streetSupplier = null;
		}

		return street;
	}

	public void setStreet(String street) {
		this.street = street;

		_streetSupplier = null;
	}

	@JsonIgnore
	public void setStreet(
		UnsafeSupplier<String, Exception> streetUnsafeSupplier) {

		_streetSupplier = () -> {
			try {
				return streetUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(description = "Street.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String street;

	@JsonIgnore
	private Supplier<String> _streetSupplier;

	@Schema(description = "Sub District.")
	public String getSubdist() {
		if (_subdistSupplier != null) {
			subdist = _subdistSupplier.get();

			_subdistSupplier = null;
		}

		return subdist;
	}

	public void setSubdist(String subdist) {
		this.subdist = subdist;

		_subdistSupplier = null;
	}

	@JsonIgnore
	public void setSubdist(
		UnsafeSupplier<String, Exception> subdistUnsafeSupplier) {

		_subdistSupplier = () -> {
			try {
				return subdistUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(description = "Sub District.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String subdist;

	@JsonIgnore
	private Supplier<String> _subdistSupplier;

	@Schema(description = "VTC.")
	public String getVtc() {
		if (_vtcSupplier != null) {
			vtc = _vtcSupplier.get();

			_vtcSupplier = null;
		}

		return vtc;
	}

	public void setVtc(String vtc) {
		this.vtc = vtc;

		_vtcSupplier = null;
	}

	@JsonIgnore
	public void setVtc(UnsafeSupplier<String, Exception> vtcUnsafeSupplier) {
		_vtcSupplier = () -> {
			try {
				return vtcUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField(description = "VTC.")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String vtc;

	@JsonIgnore
	private Supplier<String> _vtcSupplier;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Poa)) {
			return false;
		}

		Poa poa = (Poa)object;

		return Objects.equals(toString(), poa.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		String co = getCo();

		if (co != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"co\": ");

			sb.append("\"");

			sb.append(_escape(co));

			sb.append("\"");
		}

		String country = getCountry();

		if (country != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"country\": ");

			sb.append("\"");

			sb.append(_escape(country));

			sb.append("\"");
		}

		String dist = getDist();

		if (dist != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"dist\": ");

			sb.append("\"");

			sb.append(_escape(dist));

			sb.append("\"");
		}

		String house = getHouse();

		if (house != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"house\": ");

			sb.append("\"");

			sb.append(_escape(house));

			sb.append("\"");
		}

		String lm = getLm();

		if (lm != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"lm\": ");

			sb.append("\"");

			sb.append(_escape(lm));

			sb.append("\"");
		}

		String loc = getLoc();

		if (loc != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"loc\": ");

			sb.append("\"");

			sb.append(_escape(loc));

			sb.append("\"");
		}

		String pc = getPc();

		if (pc != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"pc\": ");

			sb.append("\"");

			sb.append(_escape(pc));

			sb.append("\"");
		}

		String po = getPo();

		if (po != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"po\": ");

			sb.append("\"");

			sb.append(_escape(po));

			sb.append("\"");
		}

		String state = getState();

		if (state != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"state\": ");

			sb.append("\"");

			sb.append(_escape(state));

			sb.append("\"");
		}

		String street = getStreet();

		if (street != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"street\": ");

			sb.append("\"");

			sb.append(_escape(street));

			sb.append("\"");
		}

		String subdist = getSubdist();

		if (subdist != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"subdist\": ");

			sb.append("\"");

			sb.append(_escape(subdist));

			sb.append("\"");
		}

		String vtc = getVtc();

		if (vtc != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"vtc\": ");

			sb.append("\"");

			sb.append(_escape(vtc));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "in.edelweiss.rest.api.builder.dto.v1_0.Poa",
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