package in.edelweiss.pdf.generator.utility;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Base64;

import javax.portlet.ResourceResponse;

import org.xhtmlrenderer.pdf.ITextRenderer;

public class PDFUtil {
	
	public static void generatePDFByResource(String htmlContent, ResourceResponse resourceResponse) {
        try {
            resourceResponse.setContentType("application/pdf");
            resourceResponse.setProperty(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=document.pdf");
            ITextRenderer renderer = new ITextRenderer();
            htmlContent = sanitizeHTML(htmlContent);
            String xhtmlContent = wrapInXHTML(htmlContent);
            renderer.setDocumentFromString(xhtmlContent);
            renderer.layout();
            try (OutputStream os = resourceResponse.getPortletOutputStream()) {
                renderer.createPDF(os);
                os.flush();
            }
        } catch (Exception ex) {
            _log.error("Error generating PDF: ", ex);
        }
    }
	
	public static void generatePDFByResource(String htmlContent,String documentName, ResourceResponse resourceResponse) {
        try {
            resourceResponse.setContentType("application/pdf");
            if(documentName.isEmpty()) {
            	documentName = "CIS_Document";
            }
            resourceResponse.setProperty(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+documentName+".pdf");
            ITextRenderer renderer = new ITextRenderer();
            htmlContent = sanitizeHTML(htmlContent);
            String xhtmlContent = wrapInXHTML(htmlContent);
            renderer.setDocumentFromString(xhtmlContent);
            renderer.layout();
            try (OutputStream os = resourceResponse.getPortletOutputStream()) {
                renderer.createPDF(os);
                os.flush();
            }
        } catch (Exception ex) {
            _log.error("Error generating PDF: ", ex);
        }
    }
	
	public static String generatePDFAsBase64String(String htmlContent) {
	    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	    try {
	        ITextRenderer renderer = new ITextRenderer();
	        htmlContent = sanitizeHTML(htmlContent);
            String xhtmlContent = wrapInXHTML(htmlContent);
	        renderer.setDocumentFromString(xhtmlContent);
	        renderer.layout();
	        renderer.createPDF(byteArrayOutputStream);
	    } catch (Exception ex) {
	        _log.error("Error generating PDF: ", ex);
	    }

	    byte[] pdfBytes = byteArrayOutputStream.toByteArray();
	    String base64Pdf = Base64.getEncoder().encodeToString(pdfBytes);
	    return base64Pdf;
	}

	private static String wrapInXHTML(String htmlContent) {
	    StringBuilder xhtml = new StringBuilder();
	    xhtml.append("<!DOCTYPE html>");
	    xhtml.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
	    xhtml.append("<head>");
	    xhtml.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
	    xhtml.append("<title>PDF</title>");
	    xhtml.append("</head>");
	    xhtml.append("<body>");
	    xhtml.append(htmlContent);
	    xhtml.append("</body>");
	    xhtml.append("</html>");
	    return xhtml.toString();
	}
	
	private static String sanitizeHTML(String htmlContent) {
	    // Escape '&' unless it's part of a valid entity reference
	    htmlContent = htmlContent.replaceAll("&(?!(amp|lt|gt|apos|quot|nbsp);)", "&amp;");
	    // Replace self-closing <br> tags with valid XHTML format
	    htmlContent = htmlContent.replaceAll("(?i)<br[^>]*>", "<br />");
	    // Remove malformed attributes
	    htmlContent = htmlContent.replaceAll("(?i)\\s-=\"\"[^>]*", ""); 
	    return htmlContent;
	}

	
	private static Log _log = LogFactoryUtil.getLog(PDFUtil.class);
}
