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

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
		"mvc.command.name=/guest_page_ratings_entry" }, service = MVCResourceCommand.class)
public class UpdatePageRatingsMVCResourceCommand extends BaseMVCResourceCommand {

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {

		_log.debug(" UpdateMVCResourceCommand ..............");
		try {
			updateRatingStats(resourceRequest, resourceResponse);
		} catch (Exception e) {
			_log.error("Exception in UpdateMVCResourceCommand :::::::", e);
		}
	}

	private void updateRatingStats(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws PortalException, IOException {

		
		_log.debug(" updateRatingStats....." );
		
		double totalScore = 0.0;

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

		_log.debug(" classNameId....." + classNameId);
		_log.debug(" classPK....." + classPK);
		_log.debug(" contentTitle....." + contentTitle);
		
		
		double score = ParamUtil.getDouble(resourceRequest, "score",0.0);

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

		_log.debug(" objectEntry....." + objectEntry);
			
		try {
			objectEntry = ObjectEntryLocalServiceUtil.getObjectEntry(erc, objectDefinition.getObjectDefinitionId());
		} catch (Exception e) {
			_log.error(" Exception Occured : ....." + e);
			objectEntry=null;
		}
		
		

		if(Validator.isNotNull(objectEntry)) {
			
			Map<String, Serializable> map = objectEntry.getValues();

			totalScore = (double) map.get("totalScoreDecimal") + score;
			long totalEntries = (long) map.get("totalEntries") + 1;
			
			BigDecimal bd = BigDecimal.valueOf(totalScore/totalEntries);
			bd = bd.setScale(1, RoundingMode.HALF_UP);
			
			values.put("totalScoreDecimal", totalScore);
			values.put("totalEntries", totalEntries);
			values.put("averageScore", bd.doubleValue());

			objectEntry = ObjectEntryLocalServiceUtil.updateObjectEntry(themeDisplay.getDefaultUserId(),
					objectEntry.getObjectEntryId(), values, serviceContext);

		} else {
			
			values.put("totalScoreDecimal", score);
			values.put("totalEntries", 1);
			values.put("averageScore", score/1);
			
			objectEntry =  ObjectEntryLocalServiceUtil.addObjectEntry(themeDisplay.getDefaultUserId(), 0,
					objectDefinition.getObjectDefinitionId(), values, serviceContext);
		}
		
		
		_log.debug(" objectEntry after update....." + objectEntry);
		
		JSONObject updatedRating = JSONFactoryUtil.createJSONObject();
		
		if(Validator.isNotNull(objectEntry)) {
			updatedRating.put("totalEntries", objectEntry.getValues().get("totalEntries"));
			updatedRating.put("averageScore", objectEntry.getValues().get("averageScore"));
		}
		
		
	

		_log.debug(" values....." + values);
		
		resourceResponse.getWriter().write(updatedRating.toJSONString());

	}

	private static final Log _log = LogFactoryUtil.getLog(UpdatePageRatingsMVCResourceCommand.class);

	@Reference
	private Portal _portal;

}
