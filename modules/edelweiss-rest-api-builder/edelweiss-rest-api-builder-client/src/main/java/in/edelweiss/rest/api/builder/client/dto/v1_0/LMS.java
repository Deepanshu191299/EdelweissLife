package in.edelweiss.rest.api.builder.client.dto.v1_0;

import in.edelweiss.rest.api.builder.client.dto.v1_0.InterestSessions;
import in.edelweiss.rest.api.builder.client.dto.v1_0.Lead;
import in.edelweiss.rest.api.builder.client.dto.v1_0.Quote;
import in.edelweiss.rest.api.builder.client.dto.v1_0.Utm;
import in.edelweiss.rest.api.builder.client.function.UnsafeSupplier;
import in.edelweiss.rest.api.builder.client.serdes.v1_0.LMSSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author pramod.dk
 * @generated
 */
@Generated("")
public class LMS implements Cloneable, Serializable {

	public static LMS toDTO(String json) {
		return LMSSerDes.toDTO(json);
	}

	public InterestSessions getInterestSessions() {
		return interestSessions;
	}

	public void setInterestSessions(InterestSessions interestSessions) {
		this.interestSessions = interestSessions;
	}

	public void setInterestSessions(
		UnsafeSupplier<InterestSessions, Exception>
			interestSessionsUnsafeSupplier) {

		try {
			interestSessions = interestSessionsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected InterestSessions interestSessions;

	public Lead getLead() {
		return lead;
	}

	public void setLead(Lead lead) {
		this.lead = lead;
	}

	public void setLead(UnsafeSupplier<Lead, Exception> leadUnsafeSupplier) {
		try {
			lead = leadUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Lead lead;

	public Quote getQuote() {
		return quote;
	}

	public void setQuote(Quote quote) {
		this.quote = quote;
	}

	public void setQuote(UnsafeSupplier<Quote, Exception> quoteUnsafeSupplier) {
		try {
			quote = quoteUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Quote quote;

	public Utm getUtm() {
		return utm;
	}

	public void setUtm(Utm utm) {
		this.utm = utm;
	}

	public void setUtm(UnsafeSupplier<Utm, Exception> utmUnsafeSupplier) {
		try {
			utm = utmUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Utm utm;

	@Override
	public LMS clone() throws CloneNotSupportedException {
		return (LMS)super.clone();
	}

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
		return LMSSerDes.toJSON(this);
	}

}