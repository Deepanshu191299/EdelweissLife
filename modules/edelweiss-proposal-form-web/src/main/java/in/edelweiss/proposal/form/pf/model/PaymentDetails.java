package in.edelweiss.proposal.form.pf.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentDetails {

	@JsonProperty("transaction_id")
	private String transactionId;

	private double amount;

	@JsonProperty("payment_url")
	private String paymentUrl;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPaymentUrl() {
		return paymentUrl;
	}

	public void setPaymentUrl(String paymentUrl) {
		this.paymentUrl = paymentUrl;
	}

	@Override
	public String toString() {
		return "PaymentDetails [transactionId=" + transactionId + ", amount=" + amount + ", paymentUrl=" + paymentUrl
				+ "]";
	}

}
