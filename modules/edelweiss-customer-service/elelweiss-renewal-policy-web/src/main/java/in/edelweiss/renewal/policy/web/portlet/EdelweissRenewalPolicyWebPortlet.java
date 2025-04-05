package in.edelweiss.renewal.policy.web.portlet;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.common.contants.ParameterConstants;
import in.edelweiss.renewal.policy.util.RenewalPolicyApiUtil;
import in.edelweiss.renewal.policy.web.constants.EdelweissRenewalPolicyWebPortletKeys;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.model.MultipleLoginBlock;

/**
 * @author Ravi Prakash
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=EdelweissRenewalPolicyWeb", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/jsp/renewal.jsp",
		"javax.portlet.name=" + EdelweissRenewalPolicyWebPortletKeys.EDELWEISSRENEWALPOLICYWEB,
		"javax.portlet.resource-bundle=content.Language",
"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class EdelweissRenewalPolicyWebPortlet extends MVCPortlet {
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		  
		boolean status = false;
		JSONArray jsonArray = null;
		JSONObject jsonObject=null;
		String customerName = StringPool.BLANK;
		HttpServletRequest request = PortalUtil
				.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));

		try {
			
			JSONObject sessionJsonObject = JSONFactoryUtil.createJSONObject();
			
			if(Validator.isNotNull(request.getSession().getAttribute("policyJSON"))) {
				sessionJsonObject = JSONFactoryUtil
						.createJSONObject(request.getSession().getAttribute("policyJSON").toString());
			}
			
			if (Validator.isNotNull(sessionJsonObject) && sessionJsonObject.length() > 1) {

				String idType = sessionJsonObject.getString("inputType");
				String id = sessionJsonObject.getString(idType);
				String dob = sessionJsonObject.getString("dob");

				try {
					if (Validator.isNotNull(id) && Validator.isNotNull(dob)) {
						Map<String, Object> customerDataResponseMap = RenewalPolicyApiUtil.getCustomerData(id, dob, idType);

						if (Validator.isNotNull(customerDataResponseMap)) {
							int apiStatus = (Integer) customerDataResponseMap.get("status");
							if (apiStatus == 200) {
								jsonArray = JSONFactoryUtil.createJSONArray(customerDataResponseMap.get("content").toString());
								JSONObject customerJSONObject = jsonArray.getJSONObject(0);
								customerName = customerJSONObject.getString("clientName");
								status = true;
							}else {
								request.getSession(false).removeAttribute("policyJSON");
								jsonObject=JSONFactoryUtil.createJSONObject(customerDataResponseMap.get("content").toString());
							}
						}
					}

				} catch (Exception exception) {
					logger.error("Exception occured while fetching customer data : " + exception.getMessage());
					if (logger.isDebugEnabled()) {
						logger.error(ParameterConstants.EXCEPTION, exception);
					}
				}

				logger.debug("customer data object --->" + jsonArray);
				renderRequest.setAttribute("customerName", customerName);
				renderRequest.setAttribute("status", status);
				if(status) {
					renderRequest.setAttribute("customerDataObject", jsonArray);
				}else {
					renderRequest.setAttribute("customerDataObject", jsonObject);
				}				
			}

		} catch (Exception exception) {
			renderRequest.setAttribute("status", status);
			renderRequest.setAttribute("customerDataObject", jsonArray);
			logger.error("erro while fetching customer data " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		super.render(renderRequest, renderResponse);
	}

	private static Log logger = LogFactoryUtil.getLog(EdelweissRenewalPolicyWebPortlet.class);
}