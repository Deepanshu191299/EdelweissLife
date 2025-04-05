package com.edelweiss.seo.utility.portlet;

import com.edelweiss.seo.utility.bean.LayoutSeoBean;
import com.edelweiss.seo.utility.constants.EdelweissSeoImportUtilityPortletKeys;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.friendly.url.exception.NoSuchFriendlyURLEntryLocalizationException;
import com.liferay.friendly.url.model.FriendlyURLEntryLocalization;
import com.liferay.friendly.url.service.FriendlyURLEntryLocalServiceUtil;
import com.liferay.journal.exception.NoSuchArticleException;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.layout.seo.service.LayoutSEOEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.NoSuchLayoutException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.osgi.service.component.annotations.Component;

/**
 * @author Anupam Shrivastava
 */

@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=EdelweissSeoImportUtility", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + EdelweissSeoImportUtilityPortletKeys.EDELWEISSSEOIMPORTUTILITY,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class EdelweissSeoImportUtilityPortlet extends MVCPortlet {

	private static int seoCounter = 0;

	public void updateLayoutSeo(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException, EncryptedDocumentException, InvalidFormatException, PortalException {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(Layout.class.getName(), actionRequest);

		UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest((ActionRequest) actionRequest);
		File file = uploadPortletRequest.getFile("uploadMasterData");
		FileInputStream inputStream = null;
		if (file != null) {
			inputStream = new FileInputStream(file);
		}

		LayoutSeoBean lytSeoBean = new LayoutSeoBean();

		Workbook workBook = WorkbookFactory.create(inputStream);
		try {
			if (workBook != null) {
				Sheet sheet = workBook.getSheetAt(0);
				for (Row row : sheet) {
					if (row.getRowNum() != 0) {
						for (Cell cell : row) {
							String columnValue = cell.getRichStringCellValue().toString();
							lytSeoBean = setLayoutSeoBean(cell.getColumnIndex(), columnValue, lytSeoBean);
						}
						updateLayoutSEO(serviceContext, lytSeoBean);
					}
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			workBook.close();
		}

		log.debug("Total no. of friendly url's updated for seo:" + seoCounter);

	}

	private LayoutSeoBean setLayoutSeoBean(int columnIndex, String columnValue, LayoutSeoBean layoutSeoBean) {
		if (columnIndex == 0) {
			layoutSeoBean.setFriendlyURL(columnValue);
		} else if (columnIndex == 1) {
			layoutSeoBean.setSeoTitle(columnValue);
		} else if (columnIndex == 2) {
			layoutSeoBean.setSeoDescription(columnValue);
		} else if (columnIndex == 3) {
			layoutSeoBean.setSeoKeywords(columnValue);
		} else if (columnIndex == 4) {
			layoutSeoBean.setSeoCanonicalUrl(columnValue);
		}
		return layoutSeoBean;
	}

	private static void updateLayoutSEO(ServiceContext serviceContext, LayoutSeoBean layoutSeoBean) {

		try {
			long userId = serviceContext.getUserId();
			long groupId = serviceContext.getScopeGroupId();
			Locale locale = LocaleUtil.fromLanguageId("en_US");

			String[] assetTagNames = null;

			if (!Validator.isBlank(layoutSeoBean.getSeoKeywords()) && layoutSeoBean.getSeoKeywords().contains(",")) {
				assetTagNames = layoutSeoBean.getSeoKeywords().split(",");
			}

			Map<Locale, String> titleMap = new HashMap<Locale, String>();
			titleMap.put(locale, layoutSeoBean.getSeoTitle());

			Map<Locale, String> descriptionMap = new HashMap<Locale, String>();
			descriptionMap.put(locale, layoutSeoBean.getSeoDescription());

			Map<Locale, String> keywordsMap = new HashMap<Locale, String>();
			keywordsMap.put(locale, layoutSeoBean.getSeoKeywords());

			Map<Locale, String> canonicalURLMap = new HashMap<Locale, String>();
			canonicalURLMap.put(locale, layoutSeoBean.getSeoCanonicalUrl());

			if (layoutSeoBean.getFriendlyURL().contains("/b/")) {

				String urlTitle = layoutSeoBean.getFriendlyURL().split("b/")[1];

				long classNameId = ClassNameLocalServiceUtil.getClassNameId(BlogsEntry.class);
				FriendlyURLEntryLocalization friendlyURLEntryLocalization = null;
				BlogsEntry blogsEntry = null;

				try {
					friendlyURLEntryLocalization = FriendlyURLEntryLocalServiceUtil
							.getFriendlyURLEntryLocalization(groupId, classNameId, "en_US", urlTitle);
					long classPK = friendlyURLEntryLocalization.getClassPK();
					blogsEntry = BlogsEntryLocalServiceUtil.getBlogsEntry(classPK);
				} catch (NoSuchFriendlyURLEntryLocalizationException nsfuele) {
					log.error("ERROR! No friendly url exists for the given blog url:" + urlTitle);
				} catch (Exception e) {
					log.error(e.getMessage());
					if(log.isDebugEnabled()) {
						e.printStackTrace();
					}
				}

				if (blogsEntry != null) {

					blogsEntry.setDescription(layoutSeoBean.getSeoDescription());
					BlogsEntryLocalServiceUtil.updateBlogsEntry(blogsEntry);

					AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(BlogsEntry.class.getName(),
							blogsEntry.getEntryId());

					long[] categoryIds = null;
					double priority = 0;

					if (Validator.isNotNull(assetEntry)) {
						categoryIds = assetEntry.getCategoryIds();
						priority = assetEntry.getPriority();
					}

					if (assetTagNames != null) {
						BlogsEntryLocalServiceUtil.updateAsset(userId, blogsEntry, categoryIds, assetTagNames, null,
								priority);
					}

					seoCounter++;
				}

			} else if (layoutSeoBean.getFriendlyURL().contains("/w/")) {
				String urlTitle = layoutSeoBean.getFriendlyURL().split("w/")[1];

				JournalArticle journalArticle = null;

				try {
					journalArticle = JournalArticleLocalServiceUtil.getArticleByUrlTitle(groupId, urlTitle);
				} catch (NoSuchArticleException nsae) {
					log.error("ERROR! No news/press release exists with given url:" + urlTitle);
				}

				if (journalArticle != null) {
					journalArticle.setDescriptionMap(descriptionMap);
					JournalArticleLocalServiceUtil.updateJournalArticle(journalArticle);

					AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(JournalArticle.class.getName(),
							journalArticle.getClassPK());

					long[] categoryIds = null;
					double priority = 0;

					if (Validator.isNotNull(assetEntry)) {
						categoryIds = assetEntry.getCategoryIds();
						priority = assetEntry.getPriority();
					}

					if (assetTagNames != null) {
						JournalArticleLocalServiceUtil.updateAsset(userId, journalArticle, categoryIds, assetTagNames,
								null, priority);
					}

					seoCounter++;

				}

			} else {

				Layout layout = null;
				try {
					layout = LayoutLocalServiceUtil.getLayoutByFriendlyURL(serviceContext.getScopeGroupId(), false,
							layoutSeoBean.getFriendlyURL());
				} catch (NoSuchLayoutException nsle) {
					log.error("ERROR! No Layout exists with given url:" + layoutSeoBean.getFriendlyURL());
				}

				if (Validator.isNotNull(layout)) {
					boolean isPrivateLayout = layout.getPrivateLayout();
					long layoutId = layout.getLayoutId();

					LayoutLocalServiceUtil.updateLayout(groupId, isPrivateLayout, layoutId, layout.getParentLayoutId(),
							layout.getNameMap(), titleMap, descriptionMap, keywordsMap, layout.getRobotsMap(),
							layout.getType(), layout.getHidden(), layout.getFriendlyURLMap(), layout.isIconImage(),
							null, layout.getStyleBookEntryId(), layout.getFaviconFileEntryId(),
							layout.getMasterLayoutPlid(), serviceContext);

					if (Validator.isNotNull(layoutSeoBean.getSeoCanonicalUrl())) {
						LayoutSEOEntryLocalServiceUtil.updateLayoutSEOEntry(userId, groupId, isPrivateLayout, layoutId,
								true, canonicalURLMap, serviceContext);
					}

					seoCounter++;

				}

			}
		} catch (Exception e) {
			log.error("ERROR:: Inside SEO Import Utlity ::");
			log.error(e.getMessage());
			if(log.isDebugEnabled()) {
				e.printStackTrace();
			}
		}

	}

	private static Log log = LogFactoryUtil.getLog(EdelweissSeoImportUtilityPortlet.class);

}