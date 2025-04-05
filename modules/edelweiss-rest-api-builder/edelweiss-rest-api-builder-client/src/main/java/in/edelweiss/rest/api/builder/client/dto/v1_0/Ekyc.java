package in.edelweiss.rest.api.builder.client.dto.v1_0;

import in.edelweiss.rest.api.builder.client.dto.v1_0.Data;
import in.edelweiss.rest.api.builder.client.function.UnsafeSupplier;
import in.edelweiss.rest.api.builder.client.serdes.v1_0.EkycSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author pramod.dk
 * @generated
 */
@Generated("")
public class Ekyc implements Cloneable, Serializable {

	public static Ekyc toDTO(String json) {
		return EkycSerDes.toDTO(json);
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public void setData(UnsafeSupplier<Data, Exception> dataUnsafeSupplier) {
		try {
			data = dataUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Data data;

	public String getEkycMessage() {
		return ekycMessage;
	}

	public void setEkycMessage(String ekycMessage) {
		this.ekycMessage = ekycMessage;
	}

	public void setEkycMessage(
		UnsafeSupplier<String, Exception> ekycMessageUnsafeSupplier) {

		try {
			ekycMessage = ekycMessageUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String ekycMessage;

	public String getEkycStatus() {
		return ekycStatus;
	}

	public void setEkycStatus(String ekycStatus) {
		this.ekycStatus = ekycStatus;
	}

	public void setEkycStatus(
		UnsafeSupplier<String, Exception> ekycStatusUnsafeSupplier) {

		try {
			ekycStatus = ekycStatusUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String ekycStatus;

	public String getEkycTimeStamp() {
		return ekycTimeStamp;
	}

	public void setEkycTimeStamp(String ekycTimeStamp) {
		this.ekycTimeStamp = ekycTimeStamp;
	}

	public void setEkycTimeStamp(
		UnsafeSupplier<String, Exception> ekycTimeStampUnsafeSupplier) {

		try {
			ekycTimeStamp = ekycTimeStampUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String ekycTimeStamp;

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public void setTxnId(
		UnsafeSupplier<String, Exception> txnIdUnsafeSupplier) {

		try {
			txnId = txnIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String txnId;

	@Override
	public Ekyc clone() throws CloneNotSupportedException {
		return (Ekyc)super.clone();
	}

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
		return EkycSerDes.toJSON(this);
	}

}