package in.edelweiss.template.contributor.context.contributor;

import com.liferay.document.library.util.DLURLHelper;
import com.liferay.portal.kernel.template.TemplateContextContributor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author krishna
 */
@Component(immediate = true, property = {
		"type=" + TemplateContextContributor.TYPE_GLOBAL }, service = TemplateContextContributor.class)
public class CustomTemplateContributorTemplateContextContributor implements TemplateContextContributor {

	@Override
	public void prepare(Map<String, Object> contextObjects, HttpServletRequest request) {

		// This contibutor is used in Product List Search Bar to add and remove display
		// style
		contextObjects.put("eHttpComponentsUtil", HttpComponentsUtil__IW.getInstance());
	}

	@Reference
	private DLURLHelper _dlURLHelper;
}