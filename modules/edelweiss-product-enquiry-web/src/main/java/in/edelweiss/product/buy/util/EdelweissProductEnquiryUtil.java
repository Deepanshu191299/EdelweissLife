package in.edelweiss.product.buy.util;

import static in.edelweiss.tokio.constants.EdelweissObjectConstants.DATE_OF_BIRTH;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.EMAIL;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.FULL_NAME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.GENDER;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.INVESTMENT_OBJECTIVE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.MOBILE_NUMBER;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletRequest;

import in.edelweiss.tokio.common.model.CustomerEnquiry;

public class EdelweissProductEnquiryUtil {
	
	 private EdelweissProductEnquiryUtil() {
		    throw new IllegalStateException("EdelweissProductEnquiryUtil >>> Constructor Invoked >>> Utility class invoked at an illegal or inappropriate time ::: ");
	 }

	private static Log log = LogFactoryUtil.getLog(EdelweissProductEnquiryUtil.class);

	public static Map<String, String> getCustomerEnquiryMap(CustomerEnquiry customerEnquiry) {
		Map<String, String> leadDataMap = new HashMap<>();
		if (Validator.isNotNull(customerEnquiry)) {
			leadDataMap.put(FULL_NAME, customerEnquiry.getFullname());
			leadDataMap.put(DATE_OF_BIRTH, customerEnquiry.getDateOfBirth());
			leadDataMap.put(MOBILE_NUMBER, customerEnquiry.getMobilenumber());
			leadDataMap.put(GENDER, customerEnquiry.getGender());
			leadDataMap.put(EMAIL, customerEnquiry.getEmail());
			leadDataMap.put(INVESTMENT_OBJECTIVE, customerEnquiry.getInvestmentObjective());
			log.debug("EdelweissProductEnquiryUtil >>> getCustomerEnquiryMap >>> Lead Data MAP ::: " + leadDataMap);
		}
		return leadDataMap;
	}

	public static void hideSuccessAndErrorMessage(PortletRequest portletRequest) {
		SessionMessages.add(portletRequest, PortalUtil.getPortletId(portletRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_SUCCESS_MESSAGE);
		SessionMessages.add(portletRequest, PortalUtil.getPortletId(portletRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
	}
}
