package in.edelweiss.payment.receipt.portlet;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import in.edelweiss.common.contants.EdelweissAPIConstants;
import in.edelweiss.common.contants.ParameterConstants;
import in.edelweiss.payment.receipt.constants.PaymentReceiptPortletKeys;
import in.edelweiss.payment.receipt.util.PaymentReceiptUtil;

/**
 * This portlet will call the Customer Policy Data API and 
 * send the data to JSP and render the same.
 * 
 * @author Abhijit AA
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=PaymentReceipt",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/jsp/payment-reciept.jsp",
		"javax.portlet.name=" + PaymentReceiptPortletKeys.PAYMENTRECEIPT,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class PaymentReceiptPortlet extends MVCPortlet {
	/**
	 * This method will call the Customer Policy Data API and 
	 * send the data to JSP and render the same.
	 * 
	 * @param renderRequest
	 * @param renderResponse
	 * @return void
	 */
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		HttpServletRequest request = PortalUtil
				.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
		
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		List<String> policyList = new ArrayList<>();
		String policyStr = StringPool.BLANK;
		
		try {

			JSONObject policyJSON = JSONFactoryUtil.createJSONObject();
			
			if(Validator.isNotNull(request.getSession().getAttribute("policyJSON"))) {
				policyJSON = JSONFactoryUtil
						.createJSONObject(request.getSession().getAttribute("policyJSON").toString());
			}

			if (Validator.isNotNull(policyJSON) && policyJSON.length() > 1) {
				String inputType = policyJSON.getString("inputType");
				String inputValue = policyJSON.getString(inputType);
				String dateOfBirth = policyJSON.getString("dob");
				
				DateFormat originalFormat = new SimpleDateFormat("dd/mm/yyyy");
				DateFormat targetFormat = new SimpleDateFormat("dd-mm-yyyy");
				Date date = originalFormat.parse(dateOfBirth);
				String dateOfBirthFormatted = targetFormat.format(date);

				try {

					if (Validator.isNotNull(inputType) && Validator.isNotNull(inputValue)
							&& Validator.isNotNull(dateOfBirthFormatted)) {

						Map<String, Object> responseMap = PaymentReceiptUtil.customerPolicyData(inputType, inputValue,
								dateOfBirthFormatted, EdelweissAPIConstants.REQUEST_TYPE_RPC);

						if (Validator.isNotNull(responseMap)) {
							int status = (Integer) responseMap.get("status");
							renderRequest.setAttribute("status", status);
							
							if (status == 200) {
								jsonArray = JSONFactoryUtil.createJSONArray(responseMap.get("content").toString());
								for(int i = 0; i < jsonArray.length(); i++){
									String policyNumber = jsonArray.getJSONObject(i).getString("policyNumber");
									policyList.add(policyNumber);
								}
							} else {
								request.getSession(false).removeAttribute("policyJSON");
								jsonObject = JSONFactoryUtil.createJSONObject(responseMap.get("content").toString());
							}
						}
					}
				} catch (Exception exception) {
					logger.error("Exception occured while fetching customer data : " + exception.getMessage());
					if (logger.isDebugEnabled()) {
						logger.error(ParameterConstants.EXCEPTION, exception);
					}
				}
				
				if(Validator.isNotNull(policyList)) {
					policyStr = policyList.toString().replace("[", "").replace("]", "");
				}
				
				renderRequest.setAttribute("policyStr", policyStr);
				renderRequest.setAttribute("customerJSONArray", jsonArray);
				renderRequest.setAttribute("customerJSONObject", jsonObject);
			}
		} catch (Exception exception) {
			logger.error("Exception occured in payment receipt render : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		super.render(renderRequest, renderResponse);
	}

	private Log logger = LogFactoryUtil.getLog(this.getClass());
}