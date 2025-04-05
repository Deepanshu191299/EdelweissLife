package in.edelweiss.common.mvc.blogs.action;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.blogs.constants.BlogsPortletKeys;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.view.count.ViewCountManagerUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(property = { "javax.portlet.name=" + BlogsPortletKeys.BLOGS_ADMIN, "mvc.command.name=/blogs/edit_entry",
		"service.ranking:Integer=100" }, service = MVCActionCommand.class)
public class BlogsViewsCountMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		mvcActionCommand.processAction(actionRequest, actionResponse);

		try {
			String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

			int initialViewsCount = ParamUtil.getInteger(actionRequest, "initialViewsCount", 0);
			long entryId = ParamUtil.getLong(actionRequest, "entryId");

			if (cmd.equals(Constants.UPDATE)) {

				long classNameId = ClassNameLocalServiceUtil.getClassNameId(AssetEntry.class);
				long companyId = PortalUtil.getCompanyId(actionRequest);

				AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(BlogsEntry.class.getName(), entryId);

				int finalCount = 0;

				long currentCount = ViewCountManagerUtil.getViewCount(companyId, classNameId, assetEntry.getEntryId());

				if (currentCount < initialViewsCount) {
					finalCount = (int) (initialViewsCount - currentCount);
					ViewCountManagerUtil.incrementViewCount(companyId, classNameId, assetEntry.getEntryId(),
							finalCount);
				}

			}
		} catch (Exception e) {

			System.err.println("Exception while setting initial views for blogs" + e.getMessage());
		}
	}

	@Reference(target = "(component.name=com.liferay.blogs.web.internal.portlet.action.EditEntryMVCActionCommand)")
	protected MVCActionCommand mvcActionCommand;

}