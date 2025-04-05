package in.edelweisslife.sitemap.component.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.site.manager.SitemapManager;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

@Component(
    immediate = true,
    property = {
        "service.ranking:Integer=100" // This property can be used to give your service higher priority
    },
    service = SitemapManager.class
)
public class CustomSitemap implements SitemapManager {

    private static final Log _log = LogFactoryUtil.getLog(CustomSitemap.class);

    @Override
    public void addURLElement(Element element, String url, UnicodeProperties typeSettingsUnicodeProperties,
                              Date modifiedDate, String canonicalURL, Map<Locale, String> alternateURLs) {
        _log.info("inside the addUrl Element-----");
        Element urlElement = element.addElement("url");
        
        urlElement.addElement("loc").setText(url);
        urlElement.addElement("lastmod").setText(new SimpleDateFormat("yyyy-MM-dd").format(modifiedDate));
        urlElement.addElement("changefreq").setText(typeSettingsUnicodeProperties.getProperty("changefreq", "daily"));
        urlElement.addElement("priority").setText(typeSettingsUnicodeProperties.getProperty("priority", "0.9"));

        if (alternateURLs != null) {
            for (Map.Entry<Locale, String> entry : alternateURLs.entrySet()) {
                Element linkElement = urlElement.addElement("xhtml:link", "http://www.w3.org/1999/xhtml");
                linkElement.addAttribute("rel", "alternate");
                linkElement.addAttribute("hreflang", entry.getKey().toLanguageTag());
                linkElement.addAttribute("href", entry.getValue());
            }
        }
    }

    @Override
    public String encodeXML(String input) {
        _log.info("inside the encodeXML -----------------------------------------");
        if (input == null) {
            return "";
        }
        return input.replaceAll("&", "&amp;")
                    .replaceAll("<", "&lt;")
                    .replaceAll(">", "&gt;")
                    .replaceAll("\"", "&quot;")
                    .replaceAll("'", "&apos;");
    }

    @Override
    public Map<Locale, String> getAlternateURLs(String canonicalURL, ThemeDisplay themeDisplay, Layout layout)
            throws PortalException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getSitemap(long groupId, boolean privateLayout, ThemeDisplay themeDisplay) throws PortalException {
        _log.info("inside the getSitemap method .--------> given group and layout visibility");
        // Generate the sitemap for the given group and layout visibility
        return generateSitemap(themeDisplay);
    }

    @Override
    public String getSitemap(String layoutUuid, long groupId, boolean privateLayout, ThemeDisplay themeDisplay)
            throws PortalException {
        _log.info("inside the getSitemap method .--------> sitemap for the specific layout");
        // Generate the sitemap for the specific layout
        return generateSitemap(themeDisplay);
    }

    private String generateSitemap(ThemeDisplay themeDisplay) {
        _log.info("inside the generateSitemap -----------------------------------------");

        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">");

        // Append static URLs from XML with fixed lastmod, changefreq, and priority
        List<String> xmlSitemapContents = fetchSitemapContentsFromXML("sitemap.xml");

        _log.info("Loaded sitemap contents from XML: " + xmlSitemapContents);

        for (String content : xmlSitemapContents) {
            // Remove xmlns attribute from each <url> element
            content = content.replace(" xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\"", "");
            sb.append(content);
        }

        sb.append("</urlset>");
        return sb.toString();
    }

    // Fetch sitemap contents from an XML file in the classpath
    private List<String> fetchSitemapContentsFromXML(String filePath) {
        _log.info("inside the fetchSitemapContentsFromXML -----------------------------------------");

        List<String> sitemapContents = new ArrayList<>();

        try {
            InputStream xmlFile = getClass().getClassLoader().getResourceAsStream(filePath);
            if (xmlFile == null) {
                throw new RuntimeException("XML file not found in classpath: " + filePath);
            }

            Document document = SAXReaderUtil.read(xmlFile);
            List<Element> urlElements = document.getRootElement().elements("url");

            for (Element urlElement : urlElements) {
                sitemapContents.add(urlElement.asXML());
            }
        } catch (Exception e) {
            _log.error("Error reading XML file", e);
        }

        return sitemapContents;
    }
}
