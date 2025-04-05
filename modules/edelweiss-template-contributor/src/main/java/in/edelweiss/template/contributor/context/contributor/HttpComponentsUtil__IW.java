package in.edelweiss.template.contributor.context.contributor;

import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HttpComponentsUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author krishna
 *
 */
public class HttpComponentsUtil__IW {

	public static HttpComponentsUtil__IW getInstance() {
		return _instance;
	}

	private HttpComponentsUtil__IW() {

	}

	/**
	 * @param url
	 * @param name
	 * @param value
	 * @return
	 */
	public String addParameter(String url, String name, int value) {
		return HttpComponentsUtil.addParameter(url, name, value);
	}

	/**
	 * @param url
	 * @param name
	 * @return
	 */
	public int getParameter(String url, String name) {

		if (Validator.isNull(url) || Validator.isNull(name)) {
			return 0;
		}

		int pos = url.indexOf(CharPool.QUESTION);

		if (pos == -1) {
			return 0;
		}

		if (url.contains("displayStyle=1")) {
			return 1;
		}

		return 0;
	}

	/**
	 * @param url
	 * @param name
	 * @param value
	 * @return
	 */
	public String addParameter2(String url, String name, int value) {
		String newurl = HttpComponentsUtil.removeParameter(url, name);
		return HttpComponentsUtil.addParameter(newurl, name, value);
	}

	private static HttpComponentsUtil__IW _instance = new HttpComponentsUtil__IW();

	Log log = LogFactoryUtil.getLog(HttpComponentsUtil__IW.class);
}
