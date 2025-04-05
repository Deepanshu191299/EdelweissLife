package in.edelweiss.premium.paid.certificate.portlet;

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
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import in.edelweiss.common.contants.EdelweissAPIConstants;
import in.edelweiss.common.contants.ParameterConstants;
import in.edelweiss.premium.paid.certificate.constants.PremiumPaidCertificatePortletKeys;
import in.edelweiss.premium.paid.certificate.util.PremiumPaidCertificateUtil;

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
				"javax.portlet.display-name=PremiumPaidCertificate",
				"javax.portlet.init-param.template-path=/",
				"javax.portlet.init-param.view-template=/jsp/premium-paid-certificate.jsp",
				"javax.portlet.name=" + PremiumPaidCertificatePortletKeys.PREMIUMPAIDCERTIFICATE,
				"javax.portlet.resource-bundle=content.Language",
				"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
		)
public class PremiumPaidCertificatePortlet extends MVCPortlet {

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

						Map<String, Object> responseMap = PremiumPaidCertificateUtil.customerPolicyData(inputType, inputValue,
								dateOfBirthFormatted, EdelweissAPIConstants.REQUEST_TYPE_PPC);

						if (Validator.isNotNull(responseMap)) {
							int status = (Integer) responseMap.get("status");
							renderRequest.setAttribute("status", status);

							if (status == 200) {
								jsonArray = JSONFactoryUtil.createJSONArray(responseMap.get("content").toString());
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

				Map<String, String> financialYears = generateFinancialYearDropdown();

				renderRequest.setAttribute("financialYears", financialYears);
				renderRequest.setAttribute("customerJSONArray", jsonArray);
				renderRequest.setAttribute("customerJSONObject", jsonObject);
			}
		} catch (Exception exception) {
			logger.error("Exception occured in premium paid render : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		super.render(renderRequest, renderResponse);
	}

	public Map<String, String> generateFinancialYearDropdown() {

		TreeMap<String, String> financialYears = new TreeMap<>(Collections.reverseOrder());
		String financialYearKey = StringPool.BLANK;

		Date today = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);

		if ((calendar.get(Calendar.MONTH) + 1) <= 3) {
			for(int i = 1; i < 6; i++) {
				financialYearKey = (calendar.get(Calendar.YEAR) - i) + "-" + (calendar.get(Calendar.YEAR) - (i - 1));
				financialYears.put(financialYearKey, financialYearKey.substring(0, 5) + financialYearKey.substring(7, 9));
			}
		} else {
			for(int i = 4; i >= 0 ; i--) {
				financialYearKey = (calendar.get(Calendar.YEAR) - i) + "-" + (calendar.get(Calendar.YEAR) - (i - 1));
				financialYears.put(financialYearKey, financialYearKey.substring(0, 5) + financialYearKey.substring(7, 9));
			}
		}
		return financialYears;
	}

	private Log logger = LogFactoryUtil.getLog(this.getClass());
}