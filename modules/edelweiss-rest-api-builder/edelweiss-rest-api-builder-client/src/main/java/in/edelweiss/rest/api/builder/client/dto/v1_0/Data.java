package in.edelweiss.rest.api.builder.client.dto.v1_0;

import in.edelweiss.rest.api.builder.client.dto.v1_0.Poa;
import in.edelweiss.rest.api.builder.client.dto.v1_0.Poi;
import in.edelweiss.rest.api.builder.client.function.UnsafeSupplier;
import in.edelweiss.rest.api.builder.client.serdes.v1_0.DataSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author pramod.dk
 * @generated
 */
@Generated("")
public class Data implements Cloneable, Serializable {

	public static Data toDTO(String json) {
		return DataSerDes.toDTO(json);
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setPhoto(
		UnsafeSupplier<String, Exception> photoUnsafeSupplier) {

		try {
			photo = photoUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String photo;

	public Poa getPoa() {
		return poa;
	}

	public void setPoa(Poa poa) {
		this.poa = poa;
	}

	public void setPoa(UnsafeSupplier<Poa, Exception> poaUnsafeSupplier) {
		try {
			poa = poaUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Poa poa;

	public Poi getPoi() {
		return poi;
	}

	public void setPoi(Poi poi) {
		this.poi = poi;
	}

	public void setPoi(UnsafeSupplier<Poi, Exception> poiUnsafeSupplier) {
		try {
			poi = poiUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Poi poi;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public void setUid(UnsafeSupplier<String, Exception> uidUnsafeSupplier) {
		try {
			uid = uidUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String uid;

	@Override
	public Data clone() throws CloneNotSupportedException {
		return (Data)super.clone();
	}

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
		return DataSerDes.toJSON(this);
	}

}