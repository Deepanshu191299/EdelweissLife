/*
 * package in.edelweiss.register.enach.resources;
 * 
 * import com.liferay.petra.string.StringPool; import
 * com.liferay.portal.kernel.json.JSONFactoryUtil; import
 * com.liferay.portal.kernel.json.JSONObject; import
 * com.liferay.portal.kernel.log.Log; import
 * com.liferay.portal.kernel.log.LogFactoryUtil; import
 * com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand; import
 * com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand; import
 * com.liferay.portal.kernel.theme.ThemeDisplay; import
 * com.liferay.portal.kernel.util.ParamUtil; import
 * com.liferay.portal.kernel.util.Validator; import
 * com.liferay.portal.kernel.util.WebKeys;
 * 
 * import java.io.PrintWriter;
 * 
 * import javax.portlet.ResourceRequest; import javax.portlet.ResourceResponse;
 * 
 * import org.osgi.service.component.annotations.Component; import
 * org.osgi.service.component.annotations.Reference;
 * 
 * import in.edelweiss.register.enach.constants.RegisterEnachPortletKeys; import
 * in.edelweiss.tokio.common.api.EdelweissTokioCommonApi; import
 * in.edelweiss.tokio.common.model.MultipleLoginBlock;
 * 
 * @Component(immediate = true, property = { "javax.portlet.name=" +
 * RegisterEnachPortletKeys.REGISTERENACH,
 * "mvc.command.name=/get/multipleLoginData" }, service =
 * MVCResourceCommand.class) public class EdelweissRegisterResourceEnach extends
 * BaseMVCResourceCommand{
 * 
 * @Reference private EdelweissTokioCommonApi edelweissTokioCommonApi; private
 * static Log logger =
 * LogFactoryUtil.getLog(EdelweissRegisterResourceEnach.class);
 * 
 * @Override protected void doServeResource(ResourceRequest resourceRequest,
 * ResourceResponse resourceResponse) throws Exception { // TODO Auto-generated
 * method stub logger.debug("inside doserver resource::::::"); String
 * loginCreateDate=StringPool.BLANK; JSONObject responseObj =
 * JSONFactoryUtil.createJSONObject(); try { ThemeDisplay themeDisplay =
 * (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY); String
 * portalURL = themeDisplay.getPortalURL(); String
 * policyNumber=ParamUtil.getString(resourceRequest, "policyNumber");
 * logger.debug("policyNumber...."+policyNumber);
 * if(Validator.isNotNull(policyNumber) && !policyNumber.isEmpty()) {
 * MultipleLoginBlock multipleLoginBlockResponse =
 * edelweissTokioCommonApi.getMultipleLoginByPolicyNumber(portalURL,
 * policyNumber);
 * logger.debug("multipleLoginBlockResponse...."+multipleLoginBlockResponse);
 * loginCreateDate=multipleLoginBlockResponse.getLogInDate();
 * logger.debug("loginCreateDate...."+loginCreateDate);
 * responseObj.put("loginCreateDate", loginCreateDate); } } catch (Exception e)
 * { e.printStackTrace(); } finally { PrintWriter writer =
 * resourceResponse.getWriter(); writer.write(responseObj.toString());
 * writer.close(); } } }
 */