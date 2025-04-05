package in.edelweiss.claim.tracking.components;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import org.osgi.service.component.annotations.Component;

@Component(
    immediate = true,
    property = {
        "key=login.events.post"
    },
    service = LifecycleAction.class
)
public class PostLogin implements LifecycleAction {

    private static final Log _log = LogFactoryUtil.getLog(PostLogin.class);
    private static final String TARGET_ROLE_NAME = "Claim Uploader"; // Change if needed
    private static final String REDIRECT_URL = "/claim/data-upload"; // Change to your target page

    @Override
    public void processLifecycleEvent(LifecycleEvent lifecycleEvent) throws ActionException {
        try {
            ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
            long userId = serviceContext.getUserId();

            if (userId > 0) {
                List<Role> userRoles = RoleLocalServiceUtil.getUserRoles(userId);
                for (Role role : userRoles) {
                    if (TARGET_ROLE_NAME.equals(role.getName())) {
                        // Perform the redirect
                        String portalURL = PortalUtil.getPortalURL(lifecycleEvent.getRequest());
                        lifecycleEvent.getResponse().sendRedirect(portalURL + REDIRECT_URL);
                    }
                }
            }
        } catch (Exception e) {
            _log.error("Error in PostLogin redirection", e);
        }
    }
}
