package in.edelweiss.guest.rating.web.portlet.action;

import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectDefinitionLocalServiceUtil;
import com.liferay.object.service.ObjectEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.guest.rating.web.constants.EdelweissGuestRatingWebPortletKeys;

/**
 * @author krishna
 *
 */
@Component(property = { "javax.portlet.name=" + EdelweissGuestRatingWebPortletKeys.EDELWEISSGUESTRATINGWEB,
		"mvc.command.name=/guest_ratings_entry" }, service = MVCResourceCommand.class)
public class UpdateMVCResourceCommand extends BaseMVCResourceCommand {

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {

		_log.debug(" UpdateMVCResourceCommand ..............");

		HttpServletResponse httpServletResponse = _portal.getHttpServletResponse(resourceResponse);
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		try {

			long totalScore = updateRatingStats(resourceRequest);

			resourceResponse.setProperty(ResourceResponse.HTTP_STATUS_CODE,
					String.valueOf(HttpServletResponse.SC_ACCEPTED));

			jsonObject.put("totalScore", totalScore);
			jsonObject.put("status", 200);

		} catch (Exception e) {

			_log.error("Exception in UpdateMVCResourceCommand :::::::",e);
			resourceResponse.setProperty(ResourceResponse.HTTP_STATUS_CODE,
					String.valueOf(HttpServletResponse.SC_UNAUTHORIZED));
			jsonObject.put("status", 200);
		}

		ServletResponseUtil.write(httpServletResponse, jsonObject.toJSONString());

	}

	private long updateRatingStats(ResourceRequest resourceRequest) throws PortalException {

		
		_log.debug(" updateRatingStats....." );
		
		long totalScore = 1;

		ObjectEntry objectEntry = null;

		ServiceContext serviceContext = ServiceContextFactory.getInstance(resourceRequest);

		long companyId = PortalUtil.getCompanyId(resourceRequest);

		ObjectDefinition objectDefinition = ObjectDefinitionLocalServiceUtil
				.fetchObjectDefinitionByExternalReferenceCode("GUEST_RATINGS_STATS", companyId);
		
		_log.debug(" objectDefinition....."+objectDefinition );

		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long classNameId = ParamUtil.getLong(resourceRequest, "classNameId");
		long classPK = ParamUtil.getLong(resourceRequest, "classPK");
		String contentTitle = ParamUtil.getString(resourceRequest, "contentTitle",StringPool.BLANK);

		int score = ParamUtil.getInteger(resourceRequest, "score");

		String erc = "s_" + classPK + classNameId;
		
		_log.debug(" erc....." + erc);

		HashMap<String, Serializable> values = new HashMap<>();
		values.put("externalReferenceCode", erc);
		values.put("classNameId", classNameId);
		values.put("classPK", classPK);
		if(Validator.isNotNull(contentTitle)) {
			values.put("contentTitle", contentTitle);
		}
		
		_log.debug(" score....." + score);

		if (score == 0) {

			values.put("totalScore", totalScore);
			
			ObjectEntryLocalServiceUtil.addObjectEntry(themeDisplay.getDefaultUserId(), 0,
					objectDefinition.getObjectDefinitionId(), values, serviceContext);

		} else if (score == 1) {
			objectEntry = ObjectEntryLocalServiceUtil.getObjectEntry(erc, objectDefinition.getObjectDefinitionId());

			Map<String, Serializable> map = objectEntry.getValues();

			totalScore = (long) map.get("totalScore") + 1;
			values.put("totalScore", totalScore);

			ObjectEntryLocalServiceUtil.updateObjectEntry(themeDisplay.getDefaultUserId(),
					objectEntry.getObjectEntryId(), values, serviceContext);

		}

		_log.debug(" values....." + values);

		return totalScore;

	}

	private static final Log _log = LogFactoryUtil.getLog(UpdateMVCResourceCommand.class);

	@Reference
	private Portal _portal;

}
