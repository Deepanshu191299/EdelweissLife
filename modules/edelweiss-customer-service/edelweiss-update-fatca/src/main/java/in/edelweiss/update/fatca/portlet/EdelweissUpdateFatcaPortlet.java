package in.edelweiss.update.fatca.portlet;

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

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.common.contants.ParameterConstants;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.model.MultipleLoginBlock;
import in.edelweiss.update.fatca.constants.EdelweissUpdateFatcaPortletKeys;

/**
 * @author krishna
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=EdelweissUpdateFatca", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + EdelweissUpdateFatcaPortletKeys.EDELWEISSUPDATEFATCA,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class EdelweissUpdateFatcaPortlet extends MVCPortlet {
	@Reference
	private EdelweissTokioCommonApi edelweissTokioCommonApi;
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		try {
			HttpServletRequest request = PortalUtil
					.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
			 HttpSession session = request.getSession(); 
			 
				
				String currDateRenew = new Date().toString();
				renderRequest.setAttribute("uniqueDateObj", currDateRenew);
				ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
				 MultipleLoginBlock multipleLoginBlock = new MultipleLoginBlock();
				 String inputValSess = (String) session.getAttribute("inputValueRenewal");
				 log.debug("inputTypeValue in renewal policy::::::"+ inputValSess);
				  multipleLoginBlock.setPolicyNumber(inputValSess);
				  multipleLoginBlock.setAppName(null);
				  multipleLoginBlock.setLogInDate(currDateRenew);
				  if(Validator.isNotNull(inputValSess)&&!inputValSess.isEmpty()) {
				  MultipleLoginBlock multipleLoginBlockResponse;
				try {
					multipleLoginBlockResponse = edelweissTokioCommonApi.updateMultipleLoginBlock(themeDisplay.getPortalURL(),  multipleLoginBlock, null);
					log.debug("multipleLoginBlockResponse..."+multipleLoginBlockResponse);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}

			JSONObject policyJSON = JSONFactoryUtil.createJSONObject();
			if(Validator.isNotNull(request.getSession().getAttribute("policyJSON"))) {
				policyJSON = JSONFactoryUtil
						.createJSONObject(request.getSession().getAttribute("policyJSON").toString());
			}
			
			if (Validator.isNotNull(policyJSON) && policyJSON.length() > 1) {

				log.debug(" policyJSON......." + policyJSON);
				String inputType = policyJSON.getString("inputType");
				String inputValue = policyJSON.getString(inputType);
				String dateOfBirth = policyJSON.getString("dob");

				request.setAttribute(inputType, inputValue);
				request.setAttribute("dob", dateOfBirth);

			}

		} catch (Exception exception) {
			log.error(" Policy Details are null in session ...." + exception.getMessage());
			if (log.isDebugEnabled()) {
				log.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		super.render(renderRequest, renderResponse);
	}

	private static Log log = LogFactoryUtil.getLog(EdelweissUpdateFatcaPortlet.class);
}