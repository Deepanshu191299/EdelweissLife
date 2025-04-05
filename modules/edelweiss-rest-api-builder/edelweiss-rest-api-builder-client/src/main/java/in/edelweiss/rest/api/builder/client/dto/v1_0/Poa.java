package in.edelweiss.rest.api.builder.client.dto.v1_0;

import in.edelweiss.rest.api.builder.client.function.UnsafeSupplier;
import in.edelweiss.rest.api.builder.client.serdes.v1_0.PoaSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author pramod.dk
 * @generated
 */
@Generated("")
public class Poa implements Cloneable, Serializable {

	public static Poa toDTO(String json) {
		return PoaSerDes.toDTO(json);
	}

	public String getCo() {
		return co;
	}

	public void setCo(String co) {
		this.co = co;
	}

	public void setCo(UnsafeSupplier<String, Exception> coUnsafeSupplier) {
		try {
			co = coUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String co;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCountry(
		UnsafeSupplier<String, Exception> countryUnsafeSupplier) {

		try {
			country = countryUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String country;

	public String getDist() {
		return dist;
	}

	public void setDist(String dist) {
		this.dist = dist;
	}

	public void setDist(UnsafeSupplier<String, Exception> distUnsafeSupplier) {
		try {
			dist = distUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String dist;

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public void setHouse(
		UnsafeSupplier<String, Exception> houseUnsafeSupplier) {

		try {
			house = houseUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String house;

	public String getLm() {
		return lm;
	}

	public void setLm(String lm) {
		this.lm = lm;
	}

	public void setLm(UnsafeSupplier<String, Exception> lmUnsafeSupplier) {
		try {
			lm = lmUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String lm;

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public void setLoc(UnsafeSupplier<String, Exception> locUnsafeSupplier) {
		try {
			loc = locUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String loc;

	public String getPc() {
		return pc;
	}

	public void setPc(String pc) {
		this.pc = pc;
	}

	public void setPc(UnsafeSupplier<String, Exception> pcUnsafeSupplier) {
		try {
			pc = pcUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String pc;

	public String getPo() {
		return po;
	}

	public void setPo(String po) {
		this.po = po;
	}

	public void setPo(UnsafeSupplier<String, Exception> poUnsafeSupplier) {
		try {
			po = poUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String po;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setState(
		UnsafeSupplier<String, Exception> stateUnsafeSupplier) {

		try {
			state = stateUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String state;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setStreet(
		UnsafeSupplier<String, Exception> streetUnsafeSupplier) {

		try {
			street = streetUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String street;

	public String getSubdist() {
		return subdist;
	}

	public void setSubdist(String subdist) {
		this.subdist = subdist;
	}

	public void setSubdist(
		UnsafeSupplier<String, Exception> subdistUnsafeSupplier) {

		try {
			subdist = subdistUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String subdist;

	public String getVtc() {
		return vtc;
	}

	public void setVtc(String vtc) {
		this.vtc = vtc;
	}

	public void setVtc(UnsafeSupplier<String, Exception> vtcUnsafeSupplier) {
		try {
			vtc = vtcUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String vtc;

	@Override
	public Poa clone() throws CloneNotSupportedException {
		return (Poa)super.clone();
	}

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
		return PoaSerDes.toJSON(this);
	}

}