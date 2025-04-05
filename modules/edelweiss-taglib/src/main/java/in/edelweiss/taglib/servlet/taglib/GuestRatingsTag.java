package in.edelweiss.taglib.servlet.taglib;

import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.util.IncludeTag;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import in.edelweiss.taglib.servlet.taglib.internal.servlet.ServletContextUtil;

/**
 * @author krishna
 *
 */
public class GuestRatingsTag extends IncludeTag {

	public String getClassName() {
		return _className;
	}

	public long getClassPK() {
		return _classPK;
	}

	public String getContentTitle() {
		return _contentTitle;
	}

	public void setClassName(String className) {
		_className = className;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public void setContentTitle(String contentTitle) {
		_contentTitle = contentTitle;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		setServletContext(ServletContextUtil.getServletContext());
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_className = null;
		_classPK = 0;
		_contentTitle = null;
		_classNameId = 0;
		_totalScore = 0;

	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) httpServletRequest.getAttribute(WebKeys.THEME_DISPLAY);

			_classNameId = ClassNameLocalServiceUtil.getClassNameId(_className);

			_totalScore = _getRatingsStats(themeDisplay);

			int interval = GetterUtil.getInteger(PropsUtil.get("GUEST_RATING_INTERVAL"), 1500);

			int positiveVotes = (int) Math.round(_totalScore);

			String randomNamespace = PortalUtil.generateRandomKey(getRequest(), "taglib_guest_ratings_");

			randomNamespace += StringPool.UNDERLINE;

			httpServletRequest.setAttribute("edelweiss-ui:ratings:data",
					HashMapBuilder.<String, Object>put("className", _className).put("classPK", _classPK)
							.put("contentTitle", _contentTitle).put("classNameId", _classNameId)
							.put("interval", interval).put("initialPositiveVotes", positiveVotes)
							.put("positiveVotes", positiveVotes).put("randomNamespace", randomNamespace).build());

		} catch (Exception exception) {
			_log.error("Exception in Guest Ratings Tag ...." + exception.getMessage());
		}
	}

	private long _getRatingsStats(ThemeDisplay themeDisplay) {

		if (Math.round(_totalScore) > 0) {
			return _totalScore;
		}

		try {
			String erc = "s_" + _classPK + _classNameId;
			ObjectEntry entry = ObjectEntryLocalServiceUtil.getObjectEntry(erc, themeDisplay.getCompanyId(), 0);

			Map<String, Serializable> map = entry.getValues();

			return (long) map.get("totalScore");

		} catch (Exception e) {

		}

		return 0;

	}

	private static final String _PAGE = "/guest_ratings/page.jsp";

	private static final Log _log = LogFactoryUtil.getLog(GuestRatingsTag.class);

	private String _className;
	private long _classPK;
	private long _classNameId;
	private String _contentTitle;
	private long _totalScore;

}
