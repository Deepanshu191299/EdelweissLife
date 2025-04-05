package in.edelweiss.rest.api.builder.client.dto.v1_0;

import in.edelweiss.rest.api.builder.client.function.UnsafeSupplier;
import in.edelweiss.rest.api.builder.client.serdes.v1_0.QuoteSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author pramod.dk
 * @generated
 */
@Generated("")
public class Quote implements Cloneable, Serializable {

	public static Quote toDTO(String json) {
		return QuoteSerDes.toDTO(json);
	}

	public String getPayoutOptions() {
		return payoutOptions;
	}

	public void setPayoutOptions(String payoutOptions) {
		this.payoutOptions = payoutOptions;
	}

	public void setPayoutOptions(
		UnsafeSupplier<String, Exception> payoutOptionsUnsafeSupplier) {

		try {
			payoutOptions = payoutOptionsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String payoutOptions;

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public void setStage(
		UnsafeSupplier<String, Exception> stageUnsafeSupplier) {

		try {
			stage = stageUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String stage;

	@Override
	public Quote clone() throws CloneNotSupportedException {
		return (Quote)super.clone();
	}

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
		return QuoteSerDes.toJSON(this);
	}

}