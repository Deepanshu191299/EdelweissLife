package in.edelweiss.social.bookmark.pinterest.internal;

import com.liferay.portal.kernel.language.Language;
import com.liferay.social.bookmarks.SocialBookmark;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author krishna
 */
@Component(immediate = true, property = { "social.bookmarks.priority:Integer=4",
		"social.bookmarks.type=pinterest" }, service = SocialBookmark.class)
public class PinterestSocialBookmark implements SocialBookmark {

	@Override
	public String getName(Locale locale) {
		return _language.get(locale, "pinterest");
	}

	@Override
	public String getPostURL(String title, String url) {
		return String.format("https://www.pinterest.com/pin/create/button/?media=%s", url);
	}

	@Override
	public void render(String target, String title, String url, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException, ServletException {

		RequestDispatcher requestDispatcher = _servletContext.getRequestDispatcher("/page.jsp");

		requestDispatcher.include(httpServletRequest, httpServletResponse);
	}

	@Reference
	private Language _language;

	@Reference(target = "(osgi.web.symbolicname=in.edelweiss.social.bookmark.pinterest)")
	private ServletContext _servletContext;
}