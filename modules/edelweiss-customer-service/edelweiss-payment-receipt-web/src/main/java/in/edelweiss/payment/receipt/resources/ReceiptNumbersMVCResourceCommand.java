package in.edelweiss.payment.receipt.resources;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Map;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import in.edelweiss.common.contants.EdelweissAPIConstants;
import in.edelweiss.common.contants.ParameterConstants;
import in.edelweiss.payment.receipt.constants.PaymentReceiptPortletKeys;
import in.edelweiss.payment.receipt.util.PaymentReceiptUtil;

/**
 * 
 * @author Abhijit Ande
 *
 *         This Resource class is used to get the list of Receipt Numbers based on policy Number from the API.
 */

@Component(immediate = true, property = { "javax.portlet.name=" + PaymentReceiptPortletKeys.PAYMENTRECEIPT,
		"mvc.command.name="
				+ PaymentReceiptPortletKeys.RECEIPT_NUMBERS_MVC_RESOURCE_COMMAND }, service = MVCResourceCommand.class)

public class ReceiptNumbersMVCResourceCommand extends BaseMVCResourceCommand {

	/**
	 * This method is used to get the list of Receipt Numbers based on policy Number from the API
	 * 
	 * 
	 * @param resourceRequest
	 * @param resourceResponse
	 * @return void
	 */
	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
		
		JSONObject documenUploadReponse = JSONFactoryUtil.createJSONObject();
		String response = StringPool.BLANK;
		JSONArray receiptList = JSONFactoryUtil.createJSONArray();
		boolean isReceiptListFetched = false;
		
		String policyNumber = ParamUtil.getString(resourceRequest, "policyNumber").trim();
		
		try {

			if (Validator.isNotNull(policyNumber)) {
				
				JSONObject detailsJSON = JSONFactoryUtil.createJSONObject();
				detailsJSON.put("applicationNumber", policyNumber);
				detailsJSON.put("requestType", EdelweissAPIConstants.REQUEST_TYPE_GET_RECEIPT_ID);
				
				Map<String, Object> responseMap = PaymentReceiptUtil.customerServiceRequest(detailsJSON.toString());

				if (Validator.isNotNull(responseMap)) {
					int status = (Integer) responseMap.get("status");
					JSONObject jsonObject = JSONFactoryUtil.createJSONObject(responseMap.get("jsonObject").toString());
					
					if (status == 200) {
						if (Validator.isNotNull(jsonObject) && (jsonObject.has("responseData"))) {
							receiptList = jsonObject.getJSONObject("responseData").getJSONObject("getRcptList").getJSONArray("rcptList");
							isReceiptListFetched = true;
						}
					} else if (Validator.isNotNull(jsonObject) && (jsonObject.has("message"))) {
						response = jsonObject.getString("message");
					}
				}
			}

			documenUploadReponse.put("isReceiptListFetched", isReceiptListFetched);
			documenUploadReponse.put("receiptList", receiptList);
			documenUploadReponse.put("response", response);

		} catch (Exception exception) {
			documenUploadReponse.put("internalError", true);
			logger.error("Error while calling Download URL Resource Command : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		
		resourceResponse.getWriter().write(documenUploadReponse.toString());
	}

	private Log logger = LogFactoryUtil.getLog(this.getClass());
}
