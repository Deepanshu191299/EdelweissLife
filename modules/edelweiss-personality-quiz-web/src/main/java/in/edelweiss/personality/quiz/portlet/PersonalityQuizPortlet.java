package in.edelweiss.personality.quiz.portlet;

import com.liferay.list.type.service.ListTypeDefinitionLocalServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.HashMap;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.common.util.EdelweissAPIUtil;
import in.edelweiss.personality.quiz.constants.PersonalityQuizPortletKeys;

/**
 * This Portlet is used to render the personality quiz form with Question & Answers from the picklist.
 * 
 * @author Abhijit AA
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Personality Quiz",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/jsp/personality-quiz.jsp",
		"javax.portlet.name=" + PersonalityQuizPortletKeys.PERSONALITYQUIZ,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class PersonalityQuizPortlet extends MVCPortlet {
	
	/**
	 * This method is used to render the personality quiz form with Question & Answers from the picklist.
	 * 
	 * @param renderRequest
	 * @param renderResponse
	 * 
	 * @return String
	 */
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		String quiz1PickListRefCode = ConfigurationUtil.getOOTBReferenceCodeConfiguration().getQuiz1PickListExternalReferenceCode().trim();
		String quiz2PickListRefCode = ConfigurationUtil.getOOTBReferenceCodeConfiguration().getQuiz2PickListExternalReferenceCode().trim();
		String quiz3PickListRefCode = ConfigurationUtil.getOOTBReferenceCodeConfiguration().getQuiz3PickListExternalReferenceCode().trim();
		String quiz4PickListRefCode = ConfigurationUtil.getOOTBReferenceCodeConfiguration().getQuiz4PickListExternalReferenceCode().trim();
		
		HashMap<String, String> quiz1Options = new HashMap<>(EdelweissAPIUtil.getPickListKeyValues(themeDisplay.getCompanyId(), quiz1PickListRefCode));
		String quiz1 = ListTypeDefinitionLocalServiceUtil.fetchListTypeDefinitionByExternalReferenceCode(quiz1PickListRefCode, themeDisplay.getCompanyId()).getName();
		
		HashMap<String, String> quiz2Options = new HashMap<>(EdelweissAPIUtil.getPickListKeyValues(themeDisplay.getCompanyId(), quiz2PickListRefCode));
		String quiz2 = ListTypeDefinitionLocalServiceUtil.fetchListTypeDefinitionByExternalReferenceCode(quiz2PickListRefCode, themeDisplay.getCompanyId()).getName();
		
		HashMap<String, String> quiz3Options = new HashMap<>(EdelweissAPIUtil.getPickListKeyValues(themeDisplay.getCompanyId(), quiz3PickListRefCode));
		String quiz3 = ListTypeDefinitionLocalServiceUtil.fetchListTypeDefinitionByExternalReferenceCode(quiz3PickListRefCode, themeDisplay.getCompanyId()).getName();
		
		HashMap<String, String> quiz4Options = new HashMap<>(EdelweissAPIUtil.getPickListKeyValues(themeDisplay.getCompanyId(), quiz4PickListRefCode));
		String quiz4 = ListTypeDefinitionLocalServiceUtil.fetchListTypeDefinitionByExternalReferenceCode(quiz4PickListRefCode, themeDisplay.getCompanyId()).getName();
		
		renderRequest.setAttribute("quiz1Name", quiz1);
		renderRequest.setAttribute("quiz1Options", quiz1Options);
		
		renderRequest.setAttribute("quiz2Name", quiz2);
		renderRequest.setAttribute("quiz2Options", quiz2Options);
		
		renderRequest.setAttribute("quiz3Name", quiz3);
		renderRequest.setAttribute("quiz3Options", quiz3Options);
		
		renderRequest.setAttribute("quiz4Name", quiz4);
		renderRequest.setAttribute("quiz4Options", quiz4Options);
		
		super.render(renderRequest, renderResponse);
	}
}