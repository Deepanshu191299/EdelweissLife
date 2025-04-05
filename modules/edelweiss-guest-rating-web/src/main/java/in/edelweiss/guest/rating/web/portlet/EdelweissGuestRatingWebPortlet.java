package in.edelweiss.guest.rating.web.portlet;

import com.edelweiss.http.core.api.ETIPCoreAPI;
import com.edelweiss.http.core.exception.ETIPSystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.HashMap;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.guest.rating.web.constants.EdelweissGuestRatingWebPortletKeys;
import in.edelweiss.system.configurations.EdelweissLRBasicAuthConfiguration;
import in.edelweiss.tokio.common.util.EdelweissConfigurationUtil;

/**
 * @author krishna
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=EdelweissGuestRatingWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.expiration-cache=0",
		"com.liferay.portlet.use-default-template=true",
		"com.liferay.portlet.add-default-resource=true",
		"javax.portlet.init-param.add-process-action-success-action=false",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + EdelweissGuestRatingWebPortletKeys.EDELWEISSGUESTRATINGWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=guest,power-user,user"
	},
	service = Portlet.class
)
public class EdelweissGuestRatingWebPortlet extends MVCPortlet {
	
	private static final Log logger = LogFactoryUtil.getLog(EdelweissGuestRatingWebPortlet.class);
	
	@Reference(unbind = "-")
	private ETIPCoreAPI etipCoreAPI;
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		
		logger.debug("Inside render method");
		
		
		
		
		getGuestRatings(renderRequest);
		
		logger.debug("after");
		super.render(renderRequest, renderResponse);
		
	}
	
	
private JSONObject getGuestRatings(RenderRequest renderRequest) {
		
	
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		JSONObject guestRatingsJson =  JSONFactoryUtil.createJSONObject();
		
		long classNameId = ClassNameLocalServiceUtil.getClassNameId(Layout.class);
		long classPK = themeDisplay.getLayout().getPlid();
		
		
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
		try {
			
			logger.debug("themeDisplay PortalURL : "+themeDisplay.getPortalURL());
			
			String erc = "s_"+classPK+classNameId;
			
			logger.debug("filter : "+erc);

			guestRatingsJson = etipCoreAPI.callGetAPI(new HashMap<>(),
					"https://uat.edelweisslife.in"
							+ EdelweissGuestRatingWebPortletKeys.GUEST_RATINGS_FILTER+erc,
					false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());
			
			
			/*guestRatingsJson = etipCoreAPI.callGetAPI(new HashMap<>(),
					themeDisplay.getPortalURL()
							+ EdelweissGuestRatingWebPortletKeys.GUEST_RATINGS_FILTER+filter,
					false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());*/
			 
			logger.debug("guestRatingsJson : "+guestRatingsJson);
			
			if(null!=guestRatingsJson && guestRatingsJson.length()!=0) {
				JSONArray guestRatingsJsonArray = guestRatingsJson.getJSONArray("items");
				
				logger.debug("guestRatingsJsonArray : "+guestRatingsJsonArray);
				
									
			}
		
			
		} catch (ETIPSystemException e) {
			logger.error(e.getMessage());
			if(logger.isDebugEnabled()) {
				e.printStackTrace();
			}
			guestRatingsJson = JSONFactoryUtil.createJSONObject();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			if(logger.isDebugEnabled()) {
				e.printStackTrace();
			}
			guestRatingsJson = JSONFactoryUtil.createJSONObject();
			
		}
		
		logger.debug("guestRatingsJson : "+guestRatingsJson);
		return guestRatingsJson;
		
	}
}