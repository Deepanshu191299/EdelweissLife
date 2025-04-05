package in.edelweiss.payment.receipt.constants;

/**
 * @author Abhijit AA
 */
public class PaymentReceiptPortletKeys {

	/**
	 * private constructor to hide the implicit public one
	 * 
	 */
	private PaymentReceiptPortletKeys() {

	}
	
	public static final String PAYMENTRECEIPT =
		"in_edelweiss_payment_receipt_PaymentReceiptPortlet";

	public static final String SUBMIT_DOWNLOAD_PAYMENT_RECIEPT_FORM_MVC_ACTION_COMMAND = "/submit/download/payment/form";
	public static final String DOWNLOAD_URL_MVC_RESOURCE_COMMAND = "/download/url";
	public static final String RECEIPT_NUMBERS_MVC_RESOURCE_COMMAND = "/receipt/numbers";
}