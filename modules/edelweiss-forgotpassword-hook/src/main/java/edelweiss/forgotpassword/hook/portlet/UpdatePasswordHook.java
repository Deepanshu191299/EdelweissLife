package edelweiss.forgotpassword.hook.portlet;

import com.liferay.portal.deploy.hot.CustomJspBag;
import com.liferay.portal.kernel.url.URLContainer;

import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;


/**
 * @author salek
 */
@Component(
	    immediate = true,
	    property = {
	    	"context.id=FRCForgotCustomJSP",
	        "context.name=FRC Update Password JSP",
	    	"service.ranking:Integer=100"
	    }
	)
public class UpdatePasswordHook implements CustomJspBag {
	
	
	@Override
	public String getCustomJspDir() {
	    return "META-INF/jsps/";
	}
	
	@Override
	public List<String> getCustomJsps() {
	    return _customJsps;
	}
	
	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundle = bundleContext.getBundle();

		_customJsps = new ArrayList<>();

		Enumeration<URL> entries = _bundle.findEntries(
			getCustomJspDir(), "*.jsp", true);

		while (entries.hasMoreElements()) {
			URL url = entries.nextElement();

			_customJsps.add(url.getPath());
		}
	}

	
	@Override
	public URLContainer getURLContainer() {
	    return _urlContainer;
	}

	private final URLContainer _urlContainer = new URLContainer() {

	    @Override
	    public URL getResource(String name) {
	        return _bundle.getEntry(name);
	    }

	    @Override
	    public Set<String> getResources(String path) {
	        Set<String> paths = new HashSet<>();

	        for (String entry : _customJsps) {
	            if (entry.startsWith(path)) {
	               paths.add(entry);
	            }
	        }

	        return paths;
	    }

	};
	
	@Override
	public boolean isCustomJspGlobal() {
	    return true;
	}
	private Bundle _bundle;
	private List<String> _customJsps;
}