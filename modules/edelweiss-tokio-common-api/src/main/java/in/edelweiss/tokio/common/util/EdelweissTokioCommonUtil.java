package in.edelweiss.tokio.common.util;

import static in.edelweiss.tokio.constants.EdelweissCommonConstants.BASE_PREMIUM_AMOUNT;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.BASE_PREMIUM_AMOUNT_WO_TAX;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.BI_PDF_PATH;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.BI_QUATATION_NUMBER;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.BOND_FUND;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.CHILD_DOB;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.CHILD_NAME;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.EQUITY_BLUE_CHIP_FUND;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.EQUITY_LARGE_CAP_FUND;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.EQUITY_MID_CAP_FUND;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.EQUITY_TOP_250_FUND;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.GET_STARTED;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.GILT_FUND;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.ING_PLAN_SUB_TYPE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.LEAD_FORM_PRODUCT_CATEGORY;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.LEAD_FORM_TYPE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.LITTLE_CHAMP_BENEFIT;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.LONG_TERM_BOND_FUND;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.MESSAGE_KEY;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.NO;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.PAYOUT_OPTIONS;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.PERSON;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.POLICY_OPTION;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.POLICY_TERM;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.PREMIUM_PAYMENT_FREQUENCY;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.PREMIUM_PAYMENT_TERM;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.PRODUCT_CATEGORY;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.PRODUCT_NAME_QUOTE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.PRODUCT_RELATIONSHIP;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.PROPOSER_LA_RELATION;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.QUOTE_STAGE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.RECCOMENDATION;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.RECOMMENDED_ANNUAL_INCOME;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.RECOMMENDED_OBJECTIVE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.RECOMMENDED_OBJECTIVE_CATEGORY;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.SITE_SECTION;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.STAGE_INTEREST_SESSION;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.SUM_ASSURED;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.SWP_YN;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.TOTAL_PREMIUM_AMOUNT;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.TOTAL_PREMIUM_AMOUNT_WO_TAX;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.*;

import com.edelweiss.http.core.api.ETIPCoreAPI;
import com.edelweiss.http.core.exception.ETIPSystemException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldOptions;
import com.liferay.dynamic.data.mapping.model.DDMFormInstance;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMStructureLayout;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceLocalService;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMStructureLayoutLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMStructureVersionLocalServiceUtil;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.expando.kernel.model.ExpandoValue;
import com.liferay.expando.kernel.service.ExpandoValueLocalService;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.list.type.model.ListTypeDefinition;
import com.liferay.list.type.model.ListTypeEntry;
import com.liferay.list.type.service.ListTypeDefinitionLocalService;
import com.liferay.list.type.service.ListTypeEntryLocalService;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.kernel.cookies.CookiesManagerUtil;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.Serializable;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.system.configurations.CommonConfiguration;
import in.edelweiss.system.configurations.EdelweissBJCagrConfiguration;
import in.edelweiss.system.configurations.EdelweissBJGenerateBIConfiguration;
import in.edelweiss.system.configurations.EdelweissBJProductListConfiguration;
import in.edelweiss.system.configurations.EdelweissBJProductModificationConfiguration;
import in.edelweiss.system.configurations.EdelweissBJRestartJourneyConfiguration;
import in.edelweiss.system.configurations.EdelweissGenerateLeadConfiguration;
import in.edelweiss.system.configurations.EdelweissLRBasicAuthConfiguration;
import in.edelweiss.system.configurations.EdelweissOauth2GenerateTokenConfiguration;
import in.edelweiss.system.configurations.EdelweissRPGenerateBIComboConfiguration;
import in.edelweiss.system.configurations.EdelweissRPGenerateBIConfiguration;
import in.edelweiss.system.configurations.EdelweissUpdateLeadConfiguration;
import in.edelweiss.system.configurations.ProposalFormAPIURLConfiguration;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.model.ApplicationSummaryData;
import in.edelweiss.tokio.common.model.BIRequest;
import in.edelweiss.tokio.common.model.BIRequest.AgentDetails;
import in.edelweiss.tokio.common.model.BIRequest.ComboDetails;
import in.edelweiss.tokio.common.model.BIRequest.CompanyDetails;
import in.edelweiss.tokio.common.model.BIRequest.CustomerDetails;
import in.edelweiss.tokio.common.model.BIRequest.LiDetails;
import in.edelweiss.tokio.common.model.BIRequest.Product;
import in.edelweiss.tokio.common.model.BIRequest.ProductDetails;
import in.edelweiss.tokio.common.model.BIRequest.ProposerDetails;
import in.edelweiss.tokio.common.model.CustomerEnquiry;
import in.edelweiss.tokio.common.model.CustomerFamilyDetailsRel;
import in.edelweiss.tokio.common.model.CustomerFundAllocationDetailsRel;
import in.edelweiss.tokio.common.model.CustomerInvestmentDetails;
import in.edelweiss.tokio.common.model.CustomerInvestmentDetailsRel;
import in.edelweiss.tokio.common.model.CustomerPolicyDetailsRel;
import in.edelweiss.tokio.common.model.CustomizeFeaturesCard;
import in.edelweiss.tokio.common.model.Field;
import in.edelweiss.tokio.common.model.FormView;
import in.edelweiss.tokio.common.model.InterestSessions;
import in.edelweiss.tokio.common.model.LMSRequest;
import in.edelweiss.tokio.common.model.LiferayObjectModel;
import in.edelweiss.tokio.common.model.LiferayResponseMessage;
import in.edelweiss.tokio.common.model.Lmslead;
import in.edelweiss.tokio.common.model.MultipleLoginBlock;
import in.edelweiss.tokio.common.model.Quote;
import in.edelweiss.tokio.common.model.QuoteData;
import in.edelweiss.tokio.common.model.RestartJourneyRequest;
import in.edelweiss.tokio.common.model.UpdateLMSLeadRequest;
import in.edelweiss.tokio.common.model.UpdateLead;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;
import in.edelweiss.tokio.constants.EdelweissObjectConstants;
//import sun.util.resources.provider.NonBaseLocaleDataMetaInfo;

@Component(service = EdelweissTokioCommonApi.class)
public class EdelweissTokioCommonUtil implements EdelweissTokioCommonApi {

	private static Log log = LogFactoryUtil.getLog(EdelweissTokioCommonUtil.class);
	
	@Reference
	private ETIPCoreAPI etipCoreAPI;
	
	@Reference
	private ListTypeDefinitionLocalService listTypeDefinitionLocalService;
	
	@Reference
	private ListTypeEntryLocalService listTypeEntryLocalService;
	
	@Reference
	private ExpandoValueLocalService expandoValueLocalService;
	
	@Reference(unbind = "-")
	private JournalArticleLocalService journalArticleLocalService; 
	
	@Reference(unbind = "-")
	private DDMFormInstanceLocalService ddmFormInstanceLocalService;
	
	/*
	 * Get Liferay Fron View Data (Fields) based on the product name
	 */
	@Override
	public FormView getFormData(PortletRequest renderRequest, String productName) {
		FormView formView = new FormView();
		try {

			DynamicQuery ddmFormInstanceQuery = ddmFormInstanceLocalService.dynamicQuery();
			Criterion formCriterion = null;
			formCriterion = RestrictionsFactoryUtil.like(NAME, "%" + productName + "%");
			ddmFormInstanceQuery.add(formCriterion);
			List<DDMFormInstance> ddmFormInstances = DDMFormInstanceLocalServiceUtil.dynamicQuery(ddmFormInstanceQuery);
			DDMFormInstance ddmFormInstance = null;
			
			for(DDMFormInstance instance : ddmFormInstances) {
				if(instance.getNameCurrentValue().equals(productName)) {
					ddmFormInstance = instance;
				}
			}
			
			if(Validator.isNotNull(ddmFormInstance)) {
				getFormViewData(renderRequest, formView, ddmFormInstance);
			}
			
			
		} catch (PortalException e) {
			log.error("EdelweissTokioCommonUtil >>> getFormData >>> An exception occurred retrieving form data ::: "+e);
		}
		return formView;
	}

	/*
	 * Get the From Data and Validations based on the Form Instance Id
	 */
	@Override
	public FormView getFormData(PortletRequest renderRequest, long formInstanceId) {
		FormView formView = new FormView();
		try {

			DDMFormInstance ddmFormInstance = DDMFormInstanceServiceUtil.getFormInstance(formInstanceId);
			if (Validator.isNull(ddmFormInstance)) {
				return formView;
			}
			renderRequest.setAttribute("ddmFormInstance", ddmFormInstance);

			long structureId = ddmFormInstance.getStructureId();
			long structureVersionId = DDMStructureVersionLocalServiceUtil.getLatestStructureVersion(structureId)
					.getStructureVersionId();
			DDMStructureLayout ddmStructureLayout = DDMStructureLayoutLocalServiceUtil
					.getStructureLayoutByStructureVersionId(structureVersionId);
			JSONObject structureLayoutJSON = JSONFactoryUtil.createJSONObject(ddmStructureLayout.getDefinition());
			JSONArray structurePagesArray = structureLayoutJSON.getJSONArray(PAGES);

			String language = structureLayoutJSON.getString(DEFAULT_LANGUAGE_ID);
			if (Validator.isNotNull(structurePagesArray) && structurePagesArray.length() > 0
					&& Validator.isNotNull(structurePagesArray.getJSONObject(0))) {
				JSONObject structurePages = structurePagesArray.getJSONObject(0);
				formView.setTitle(structurePages.getJSONObject(TITLE).getString(language));
			}

			DDMStructure formStructure = DDMStructureLocalServiceUtil.getDDMStructure(structureId);
			JSONObject structureJSON = JSONFactoryUtil.createJSONObject(formStructure.getDefinition());
			JSONArray structureJSONArray = structureJSON.getJSONArray(FIELDS);
			log.debug(structureJSONArray);
			
			DDMForm ddmForm = formStructure.getDDMForm();
			List<DDMFormField> ddmFormFields = ddmForm.getDDMFormFields();
			log.debug(ddmFormFields);
			
			List<Field> fieldsView = getFormFields(language, structureJSONArray, ddmFormFields);
			formView.setFields(fieldsView);

			Map<String, JSONObject> fieldsValidations = getFormFieldsValidations(language, structureJSONArray);
			renderRequest.setAttribute("fieldsValidations", fieldsValidations);

		} catch (PortalException e) {
			log.error("Exception while getting Form instance with Id: " + formInstanceId);
			log.error(e);
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		return formView;
	}
	
	private Map<String, JSONObject> getFormFieldsValidations(String language, JSONArray structureJSONArray) {
		Map<String, JSONObject> fieldsValidations = new HashMap<String, JSONObject>();

		for (int i = 0; i < structureJSONArray.length(); i++) {

			JSONObject structureObject = structureJSONArray.getJSONObject(i);
			JSONObject fieldValidation = JSONFactoryUtil.createJSONObject();

			JSONObject validationObject = structureObject.getJSONObject("validation");
			if (Validator.isNull(validationObject)
					|| Validator.isNull(validationObject.getJSONObject("expression").getString(NAME))) {
				continue;
			}

			fieldValidation.put(TYPE, validationObject.getJSONObject("expression").getString(NAME));
			fieldValidation.put("parameter", validationObject.getJSONObject("parameter").getString(language));
			fieldValidation.put("errorMessage", validationObject.getJSONObject("errorMessage").getString(language));

			fieldsValidations.put(structureObject.getString(FIELD_REFERENCE), fieldValidation);
		}

		return fieldsValidations;
	}


	/*
	 * Set the Form fields into the given form
	 */
	private void getFormViewData(PortletRequest renderRequest, FormView formView,
			DDMFormInstance formInstacne) throws PortalException {
		 
		if(Validator.isNotNull(formInstacne)) {
			
			long structureId = formInstacne.getStructureId();

			long structureVersionId = DDMStructureVersionLocalServiceUtil.getLatestStructureVersion(structureId)
					.getStructureVersionId();
			DDMStructureLayout ddmStructureLayout = DDMStructureLayoutLocalServiceUtil
					.getStructureLayoutByStructureVersionId(structureVersionId);
			JSONObject structureLayoutJSON = JSONFactoryUtil.createJSONObject(ddmStructureLayout.getDefinition());
			JSONArray structurePagesArray = structureLayoutJSON.getJSONArray(PAGES);
			String language = structureLayoutJSON.getString(DEFAULT_LANGUAGE_ID);
			if (structurePagesArray.length() > 0) {
				for (int i = 0; i < structurePagesArray.length(); i++) {
					JSONObject titlePageObject = structurePagesArray.getJSONObject(i);
					if (Validator.isNotNull(titlePageObject.getJSONObject(TITLE).getString(language))) {
						formView.setTitle(titlePageObject.getJSONObject(TITLE).getString(language));
					}
				}
			}

			DDMStructure formStructure = DDMStructureLocalServiceUtil.getDDMStructure(structureId);
			JSONObject structureJSON = JSONFactoryUtil.createJSONObject(formStructure.getDefinition());
			JSONArray structureJSONArray = structureJSON.getJSONArray(FIELDS);

			List<Field> fieldsView = new ArrayList<>();

			setFieldViewData(renderRequest, formView, language, structureJSONArray, fieldsView);
		}
	}

	/*
	 * Prepare the Form fields with the given Structure JSON Array
	 */
	private List<Field> getFormFields(String language, JSONArray structureJSONArray, List<DDMFormField> ddmFormFields) {

		List<Field> fieldsView = new ArrayList<>();
		for (int i = 0; i < structureJSONArray.length(); i++) {
			JSONObject structureObject = structureJSONArray.getJSONObject(i);

			if (structureObject.getString(TYPE).equalsIgnoreCase(TEXT)
					|| structureObject.getString(TYPE).equalsIgnoreCase(NUMERIC)) {
				Field fields = new Field();

				fields.setFieldReference(structureObject.getString(FIELD_REFERENCE));
				fields.setPlaceHolder(structureObject.getJSONObject(PLACEHOLDER).getString(language));
				fields.setLabel(structureObject.getJSONObject(LABEL).getString(language));
				fields.setHidden(structureObject.getString(HIDE_FIELD));
				fields.setRequired(structureObject.getString(REQUIRED));
				fields.setErrorMessage(structureObject.getJSONObject(REQUIRED_ERROR_MESSAGE).getString(language));
				fields.setType(structureObject.getString(TYPE));
				if (Validator.isNotNull(structureObject.getJSONObject("predefinedValue"))
						&& Validator.isNotNull(structureObject.getJSONObject("predefinedValue").getString(language))) {
					fields.setText(structureObject.getJSONObject("predefinedValue").getString(language));
				}
				fieldsView.add(fields);

			} else if (structureObject.getString(TYPE).equalsIgnoreCase(RADIO)
					|| structureObject.getString(TYPE).equalsIgnoreCase(SELECT)) {
				
				DDMFormField ddmField = ddmFormFields.get(i);

				Field fields = new Field();
				fields.setFieldReference(structureObject.getString(FIELD_REFERENCE));
				fields.setText(structureObject.getJSONArray(OPTIONS).getJSONObject(0).getJSONObject(LABEL)
						.getString(language));
				fields.setLabel(structureObject.getJSONObject(LABEL).getString(language));
				fields.setPlaceHolder(structureObject.getJSONObject(TIP).getString(language));
				fields.setRequired(structureObject.getString(REQUIRED));
				fields.setErrorMessage(structureObject.getJSONObject(REQUIRED_ERROR_MESSAGE).getString(language));
				fields.setType(structureObject.getString(TYPE));

				log.debug(structureObject.getJSONArray(OPTIONS));
			
				DDMFormFieldOptions ddmFormFieldOptions = ddmField.getDDMFormFieldOptions();
				Map<String, LocalizedValue> ddmFmFieldOptions = Validator.isNotNull(ddmFormFieldOptions)
						? ddmFormFieldOptions.getOptions()
						: null;

				if (Validator.isNotNull(ddmFmFieldOptions) && ddmFmFieldOptions.size() > 0) {

					Map<String, String> optionsMap = new LinkedHashMap<>();
					for (Map.Entry<String, LocalizedValue> entry : ddmFmFieldOptions.entrySet()) {
						LocalizedValue localizedValue = entry.getValue();
						String optionValue = localizedValue.getString(Locale.ENGLISH);
						String optionRefValue = ddmFormFieldOptions.getOptionReference(entry.getKey()).charAt(0) == '_'
								? ddmFormFieldOptions.getOptionReference(entry.getKey()).split(StringPool.UNDERLINE)[1]
								: ddmFormFieldOptions.getOptionReference(entry.getKey());
						optionsMap.put(optionRefValue, optionValue);
					}
					fields.setOptionValues(optionsMap);
					log.debug(optionsMap);

					try {
						JSONArray predefinedValueObject = JSONFactoryUtil
								.createJSONArray(ddmField.getPredefinedValue().getString(Locale.ENGLISH));
						if (Validator.isNotNull(predefinedValueObject) && predefinedValueObject.length() > 0) {

							String predefinedValue = ddmFormFieldOptions
									.getOptionReference(predefinedValueObject.getString(0)).charAt(0) == '_'
											? ddmFormFieldOptions.getOptionReference(predefinedValueObject.getString(0))
													.split(StringPool.UNDERLINE)[1]
											: ddmFormFieldOptions
													.getOptionReference(predefinedValueObject.getString(0));
							fields.setText(predefinedValue);

						}
					} catch (JSONException e) {
						log.warn("Unable to get the Predefined value for: " + fields.getFieldReference());
						if (log.isDebugEnabled()) {
							e.printStackTrace();
						}
					}
				}

				fieldsView.add(fields);
			}
		}
		return fieldsView;
	}

	/*
	 * Set Form fields for Product Quote Journy
	 */
	private void setFieldViewData(PortletRequest renderRequest, FormView formView, String language,
			JSONArray structureJSONArray, List<Field> fieldsView) {
		for (int i = 0; i < structureJSONArray.length(); i++) {

			JSONObject structureObject = structureJSONArray.getJSONObject(i);

			if (structureObject.getString(TYPE).equalsIgnoreCase(TEXT)
					|| structureObject.getString(TYPE).equalsIgnoreCase(NUMERIC)) {
				Field fields = new Field();

				fields.setFieldReference(structureObject.getString(FIELD_REFERENCE));
				fields.setPlaceHolder(structureObject.getJSONObject(PLACEHOLDER).getString(language));
				fields.setLabel(structureObject.getJSONObject(LABEL).getString(language));
				fields.setHidden(structureObject.getString(HIDE_FIELD));
				fields.setRequired(structureObject.getString(REQUIRED));
				fields.setErrorMessage(structureObject.getJSONObject(REQUIRED_ERROR_MESSAGE).getString(language));
				fields.setType(structureObject.getString(TYPE));
				fieldsView.add(fields);

				formView.setFields(fieldsView);

			} else if (structureObject.getString(TYPE).equalsIgnoreCase(RADIO)) {
				Field fields = new Field();

				fields.setFieldReference(structureObject.getString(FIELD_REFERENCE));
				fields.setText(structureObject.getJSONArray(OPTIONS).getJSONObject(0).getJSONObject(LABEL)
						.getString(language));
				fields.setLabel(structureObject.getJSONObject(LABEL).getString(language));
				fields.setRequired(structureObject.getString(REQUIRED));
				fields.setErrorMessage(structureObject.getJSONObject(REQUIRED_ERROR_MESSAGE).getString(language));
				fields.setType(structureObject.getString(TYPE));

				fieldsView.add(fields);
				formView.setFields(fieldsView);
			} else if (structureObject.getString(TYPE).equalsIgnoreCase(SELECT)) {
				Field fields = new Field();
				fields.setFieldReference(structureObject.getString(FIELD_REFERENCE));
				fields.setLabel(structureObject.getJSONObject(LABEL).getString(language));
				fields.setRequired(structureObject.getString(REQUIRED));
				fields.setErrorMessage(structureObject.getJSONObject(REQUIRED_ERROR_MESSAGE).getString(language));
				fields.setPlaceHolder(structureObject.getJSONObject(TIP).getString(language));
				fields.setType(structureObject.getString(TYPE));

				if(structureObject.getString(FIELD_REFERENCE).equalsIgnoreCase(GENDER)) {
					ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
					String productCode = StringPool.BLANK;
					if(Validator.isNotNull(renderRequest.getAttribute("productCode"))) {
						productCode = (String)renderRequest.getAttribute("productCode");
					}
					
					Map<String, String> optionValues = getPicklistByExternalReferenceCode(GENDER, themeDisplay.getCompanyId());;					
					if(productCode.equalsIgnoreCase("40039")) {
						log.info("SJB Gender for product code 40039");
						optionValues = getPicklistByExternalReferenceCode(EdelweissCommonConstants.SJB_GENDER, themeDisplay.getCompanyId());
					}
					
					if(productCode.equalsIgnoreCase("40071")) {
						log.info("GFS Gender for product code 40071");
						optionValues = getPicklistByExternalReferenceCode(EdelweissCommonConstants.GFS_GENDER, themeDisplay.getCompanyId());
					}
					
					fields.setOptionValues(optionValues);
				}else if(structureObject.getString(FIELD_REFERENCE).equalsIgnoreCase(ANNUAL_INCOME)) {
					ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
					Map<String, String> optionValues = getPicklistByExternalReferenceCode(EdelweissCommonConstants.YOUR_INCOME_MASTER_PICKLIST_ERC, themeDisplay.getCompanyId());
					fields.setOptionValues(optionValues);
				}
				fieldsView.add(fields);
				formView.setFields(fieldsView);
			}
		}
	}

	
	
	
	/**
	 * Get All Picklist data by externalReferenceCode and company Id
	 * @return picklistDataMap 
	 */
	@Override
	public Map<String, String> getPicklistByExternalReferenceCode(String externalReferenceCode, long companyId) {
		Map<String, String> picklistDataMap = new LinkedHashMap<>();
		try {
			ListTypeDefinition listTypeDefinition = listTypeDefinitionLocalService.getListTypeDefinitionByExternalReferenceCode(externalReferenceCode, companyId);
			if(Validator.isNotNull(listTypeDefinition)) {
				List<ListTypeEntry> listTypeEntries = listTypeEntryLocalService.getListTypeEntries(listTypeDefinition.getListTypeDefinitionId());
				
				for (ListTypeEntry listTypeEntry : listTypeEntries) {
					picklistDataMap.put(listTypeEntry.getKey(), listTypeEntry.getName(Locale.US));
				}
			}
		} catch(Exception e) {
			log.error("Error while fetching Picklist Data for the ExternalReferenceCode : " + externalReferenceCode + " , Error is : " + e);
		}
		
		return picklistDataMap;
	}
	
	@Override
	public String getCustomFieldValue(long companyId, String className, long classPk, String columnName) {
		String data = StringPool.BLANK;
		try {
			ExpandoValue expandoValue = expandoValueLocalService.getValue(companyId, className, ExpandoTableConstants.DEFAULT_TABLE_NAME, columnName, classPk);
			if(Validator.isNotNull(expandoValue)) {
				data = expandoValue.getData();
			}
		} catch (Exception e) {
			log.error("Error while fetching custom field value : " + e);
		}
		return data;
	}
	
	/**
	 * Fetch All Nested Field service response by Product ID
	 * @param serviceURL is the Service URL
	 * @param productID is the Product ID 
	 * @return productRelObjectResponse
	 */
	@Override
	public JSONObject getProductNestedFieldDataByProductId(String serviceURL, String productID) {
		serviceURL = serviceURL.replace(EdelweissCommonConstants.PRODUCT_ID_SERVICE_PARAM, productID);
		log.debug("EdelweissTokioCommonUtil >>> getProductNestedFieldDataByProductId >>> Product Nested Field Service URL ::: " + serviceURL);
		Map<String, String> params = new HashMap<>();
		try {
			EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
			return etipCoreAPI.callGetAPI(params, serviceURL, false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());
		} catch (Exception e) {
			log.error("EdelweissTokioCommonUtil >>> getProductNestedFieldDataByProductId >>> Error while rendering Product Relationship data for Product ID : " + productID + " , Error is : "+ e);
		}
		return null;
	}
	
	@Override
	public BIRequest getBIRequest(ProductDetails productDetails, CustomerDetails customerDetails, CompanyDetails companyDetails) {
		return new BIRequest(productDetails, customerDetails, companyDetails);
	}
	
	/**
	 * Get Product Details for the BIReuest
	 * @param product
	 * @param comboDetails
	 * @return
	 */
	@Override
	public ProductDetails getBIRequestProductDetails(Product product, ComboDetails comboDetails) {
		return new ProductDetails(product, comboDetails);
	}

	/**
	 * Get Customer Details for the BIReuest
	 * @param liDetails
	 * @param proposerDetails
	 * @param agentDetails
	 * @return
	 */
	@Override
	public CustomerDetails getBIRequestCustomerDetails(LiDetails liDetails, ProposerDetails proposerDetails, AgentDetails agentDetails) {
		return new CustomerDetails(liDetails, proposerDetails, agentDetails);
	}
	
	/**
	 * Get LiDetails Details for the BIReuest
	 * @param liName
	 * @param liEntryAge
	 * @param liDob
	 * @param liGender
	 * @param liState
	 * @param liMobileNo
	 * @param liEmailId
	 * @return
	 */
	@Override
	public LiDetails getBIRequestLiDetails(String liName, int liEntryAge, String liDob, String liGender, String liState,
			String liMobileNo, String liEmailId) {
		return new LiDetails(liName, liEntryAge, liDob, liGender, liState, liMobileNo, liEmailId);
	}
	
	/**
	 * Get Proposer Details for the BIReuest
	 * @param sameProposer
	 * @param proposerName
	 * @param proposerAge
	 * @param proposerDob
	 * @param proposerGender
	 * @return proposerDetails
	 */
	@Override
	public ProposerDetails getBIRequestProposerDetails(String sameProposer, String proposerName, int proposerAge, String proposerDob,
			String proposerGender) {
		return new ProposerDetails(sameProposer, proposerName, proposerAge, proposerDob, proposerGender);
	}
	
	/**
	 * Get Company Details for the BIReuest 
	 * @param companyState
	 * @param gstin
	 * @param gstinNumber
	 * @return companyDetails
	 */
	@Override
	public CompanyDetails getBIRequestCompanyDetails(int companyState, String gstin, int gstinNumber) {
		return new CompanyDetails(companyState, gstin, gstinNumber);
	}
	
	/**
	 * Get Agent Details instance for the BIReuest
	 * @param agentId
	 * @param agentName
	 * @param agentLocation
	 * @param fetchDefault
	 * @return
	 */
	@Override
	public AgentDetails getBIRequestAgentDetails(String agentId, String agentName, String agentLocation, boolean fetchDefault) {
		AgentDetails agentDetails = null;
		if(fetchDefault) {
			agentDetails = new AgentDetails("TW9001", StringPool.BLANK, StringPool.BLANK);
		} else {
			agentDetails = new AgentDetails();
			agentDetails.setAgentId(agentId);
			agentDetails.setAgentName(agentName);
			agentDetails.setAgentLocation(agentLocation);
		}
		
		return agentDetails;
	}
	
	
	@Override
	public String getLeadIdFromCookie(PortletRequest portletRequest, String cookieName) {
		String leadId = StringPool.BLANK;
		Cookie[] cookies = portletRequest.getCookies();
		if (Validator.isNotNull(cookies)) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieName)) {
					leadId = cookie.getValue();
				}
			}
		}
		return leadId;
	}
	
	
	

	@Override
	public void createCookie(String name, String values, HttpServletResponse response) throws ETIPSystemException {
		Cookie cookie = new Cookie(name, values);
		cookie.setSecure(false);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	@Override
	public Map<String, String> getEditBasicDetails(PortletRequest portletRequest) throws ETIPSystemException {
		ThemeDisplay themeDisplay = (ThemeDisplay) portletRequest.getAttribute(WebKeys.THEME_DISPLAY);
		// Step 1 : Getting lead value from cookie 
		String cookieLeadValue = getLeadIdFromCookie(portletRequest, "leadId");
	
		// Step 2 : Getting edit form values 
		Map<String, String> mapformMap = new HashMap<>();
		mapformMap.put(FULL_NAME, ParamUtil.getString(portletRequest, "fullName"));
		mapformMap.put(DATE_OF_BIRTH, ParamUtil.getString(portletRequest, "dateOfBirth"));
		mapformMap.put(MOBILE_NUMBER, ParamUtil.getString(portletRequest, "mobileNumber"));
		mapformMap.put(GENDER, ParamUtil.getString(portletRequest, "gender"));
		mapformMap.put(EMAIL, ParamUtil.getString(portletRequest, "email"));
		mapformMap.put(INVESTMENT_OBJECTIVE, ParamUtil.getString(portletRequest, "investmentObjective"));
		mapformMap.put(LEAD_STATUS, "isOpen");
		mapformMap.put(PRODUCT_CODE, getCustomFieldValue(themeDisplay.getCompanyId(), Layout.class.getName(), themeDisplay.getLayout().getPlid(), PRODUCT_CODE));
		mapformMap.put(EXTERNAL_REFERENCE_CODE, cookieLeadValue);
		mapformMap.put(LEAD_ID, cookieLeadValue);
		
		return mapformMap;
	}

	@Override
	public Map<String, String> getFamilyDetails (PortletRequest portletRequest,JSONObject basicDetailsObject) throws ETIPSystemException{
		ThemeDisplay themeDisplay = (ThemeDisplay) portletRequest.getAttribute(WebKeys.THEME_DISPLAY);
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
		String liferayUserName = edelweissLRBasicAuthConfiguration.getLRUsername();
		String liferayPassword = edelweissLRBasicAuthConfiguration.getLRPassword();
		
		String cookieLeadValue = getLeadIdFromCookie(portletRequest, "leadId");
		Map<String, String> familyFormMap = new HashMap<>();
	    familyFormMap.put(ASSURED_RELATION, ParamUtil.getString(portletRequest,"assuredRelation"));
		familyFormMap.put(ASSURED_FULL_NAME, ParamUtil.getString(portletRequest,"assuranceFullName"));
		familyFormMap.put(ASSURED_DATE_OF_BIRTH, ParamUtil.getString(portletRequest,"assuranceDob"));
		familyFormMap.put("gender",familyFormMap.put(ASSURED_RELATION, ParamUtil.getString(portletRequest,"assuredRelation")));
		familyFormMap.put(LEAD_ID, cookieLeadValue);
		familyFormMap.put(RCUSTOMERFAMILYDETAILSRELCCUSTOMERENQUIRYID, basicDetailsObject.getString("id"));
		JSONObject liferayJSON = LiferayObjectModel.createLiferayFamilyJSON(familyFormMap);
		
		if(Validator.isNotNull(basicDetailsObject.getString("familyId"))) {
			String customerFamilyURL = themeDisplay.getPortalURL() + URL_FOR_CUSTOMER_FAMILY_DETAILS+basicDetailsObject.getString("familyId");
			etipCoreAPI.callPutAPI(liferayJSON, customerFamilyURL, false, liferayUserName, liferayPassword);
		}else {
			String customerFamilyURL = themeDisplay.getPortalURL() + URL_FOR_CUSTOMER_FAMILY_DETAILS;
			JSONObject lrFamilyResponse = etipCoreAPI.callPostAPI(liferayJSON, customerFamilyURL, false, liferayUserName, liferayPassword);
			String familyId = lrFamilyResponse.getString("id");
			familyFormMap.put(FAMILY_ID, familyId);
		}
		return familyFormMap;
		
	}
	
	@Override
	public JSONObject updateDetails(PortletRequest portletRequest, Map<String, String> updatedFamilyMap)
			throws ETIPSystemException {

		ThemeDisplay themeDisplay = (ThemeDisplay) portletRequest.getAttribute(WebKeys.THEME_DISPLAY);
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
		String liferayUserName = edelweissLRBasicAuthConfiguration.getLRUsername();
		String liferayPassword = edelweissLRBasicAuthConfiguration.getLRPassword();

		String customerPutFamilyURL = themeDisplay.getPortalURL() + LIFERAY_PRODUCT_ENQUIRY_URL+updatedFamilyMap.get(ID);
		JSONObject liferayFamilyJSON = LiferayObjectModel.createLiferayRequestJSON(updatedFamilyMap);
		JSONObject liferayFamilyResponse = etipCoreAPI.callPutAPI(liferayFamilyJSON, customerPutFamilyURL, false, liferayUserName, liferayPassword);
		log.debug(" EdelweissProductSummaryActionCommand >>> doProcessAction >>> LIFERAY Family Response >>>>>> "
				+ liferayFamilyResponse);
		
		return liferayFamilyResponse;
	}
	
	public JSONObject updateLead(PortletRequest portletRequest,Map<String, String> updateLeadMap, String productName,
			JSONObject metaDataResponse) {
		EdelweissUpdateLeadConfiguration edelweissUpdateLeadConfiguration = EdelweissConfigurationUtil.getEdelweissUpdateLeadConfiguration();
		
		try {
			String authorization = getOAuthToken(edelweissUpdateLeadConfiguration.getOAuth2QJURL(),
					edelweissUpdateLeadConfiguration.getOAuth2QJUsername(),
					edelweissUpdateLeadConfiguration.getOAuth2QJPassword());
			
			Lmslead lmsLeadUpdate = new Lmslead();
			Quote quote = new Quote();
			lmsLeadUpdate.setLmsId(updateLeadMap.get(LEAD_ID));
			lmsLeadUpdate.setcId(metaDataResponse.getString(C_ID));
			lmsLeadUpdate.setCampaignId(StringPool.BLANK);
			lmsLeadUpdate.setCpId(metaDataResponse.getString(CP_ID));
			lmsLeadUpdate.setDevice(StringPool.BLANK);
			lmsLeadUpdate.setDob(updateLeadMap.get(DATE_OF_BIRTH).trim());
			lmsLeadUpdate.setEmail(updateLeadMap.get(EMAIL));
			lmsLeadUpdate.setGender(updateLeadMap.get(GENDER));
			lmsLeadUpdate.setName(updateLeadMap.get(FULL_NAME));
			lmsLeadUpdate.setNationality(INDIAN);
			lmsLeadUpdate.setPhone(updateLeadMap.get(MOBILE_NUMBER));
			lmsLeadUpdate.setProduct(productName);
			lmsLeadUpdate.setSiteSection(LMS_SITE_SECTION);
			lmsLeadUpdate.setSource(EDELWEISS_WEB_SALES);
			lmsLeadUpdate.setUserBrowser(portletRequest.getUserAgent());
			lmsLeadUpdate.setvId(metaDataResponse.getString(V_ID));
			lmsLeadUpdate.setVisitorId(StringPool.BLANK);
			lmsLeadUpdate.setWebUrl(WEB_URL);
			lmsLeadUpdate.setRiskAppetiteSuitability(updateLeadMap.get(RISK_APPETITE));
			lmsLeadUpdate.setOtherInsuranceSuitability(updateLeadMap.get(OTHER_INSURANCE));
			lmsLeadUpdate.setAnnualIncomeSuitability(updateLeadMap.get(ANNUAL_INCOME));
			lmsLeadUpdate.setQuote(quote);
			lmsLeadUpdate.setQuote(quote);
			LMSRequest lmsRequest = new LMSRequest();
			lmsRequest.setLmslead(lmsLeadUpdate);
			
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(lmsRequest);
			
			JSONObject leadJson = JSONFactoryUtil.createJSONObject(jsonString);
			JSONObject responseLeadUpdateJSON = etipCoreAPI.callPostAPI(leadJson, edelweissUpdateLeadConfiguration.getUpdateLeadQJURL(), true, edelweissUpdateLeadConfiguration.getUpdateLeadQJXAPIKEY(), authorization);
			log.debug("EdelweissTokioCommonUtil >>> updateLead >>> lead update response " +responseLeadUpdateJSON );	
			return responseLeadUpdateJSON;
		} catch (JSONException | JsonProcessingException |ETIPSystemException e) {
			log.error("EdelweissProductQuoteUtil >>> updateLead >>> An error occur while getting updating  "+e);
			
		}
		return null;
	}

	/**
	 * Fetch the Recommended Product List form Edelweiss external API
	 * 
	 * @param productId
	 * @return productList
	 */
	@Override
	public JSONArray getRecommendedProductList(String productId, Map<String, String> leadData) {
		JSONArray recommendedProductList = JSONFactoryUtil.createJSONArray();
		String productListUrl = PropsUtil.get(EDELWEISS_PRODUCT_LIST_API_URL);

		try {
			//TODO: TPP - This method is not in use. Update the authorization code when using this method 
			String authorization = getQuoteJourneyOAuthToken();
			String apiKey = "dwV6mBV4S6azK3ZAFLdvY2UjnwZBs1jL7cKCjJaM";

			int age = -1;
			if (Validator.isNotNull(leadData.get(DATE_OF_BIRTH))) {
				String dob = leadData.getOrDefault(DATE_OF_BIRTH, StringPool.BLANK).replace(StringPool.SPACE,
						StringPool.BLANK);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate dobDate = LocalDate.parse(dob, formatter);
				age = Period.between(dobDate, LocalDate.now()).getYears();
			}

			JSONObject requestJson = JSONFactoryUtil.createJSONObject();
			requestJson.put(GOAL_ID, "1");
			requestJson.put(LIFE_ASSURED_AGE, age);
			requestJson.put(LIFE_ASSURED_GENDER, leadData.getOrDefault(GENDER, StringPool.BLANK));
			requestJson.put(IS_SMOKE, leadData.getOrDefault(IS_SMOKE, "0"));
			requestJson.put(MARITAL_STATUS_IS, leadData.getOrDefault(MARITAL_STATUS_IS, StringPool.BLANK).equalsIgnoreCase("yes")?0:1);
			if (!leadData.getOrDefault(MARITAL_STATUS_IS, "0").equalsIgnoreCase("0")) {
				requestJson.put(SPOUSE_AGE, leadData.getOrDefault(SPOUSE_AGE, StringPool.BLANK));
				requestJson.put(SPOUSE_GENDER, leadData.getOrDefault(SPOUSE_GENDER, StringPool.BLANK));
				requestJson.put(IS_SPOUSE_SMOKER, leadData.getOrDefault(IS_SPOUSE_SMOKER, StringPool.BLANK));
			} else {
				requestJson.put(SPOUSE_AGE, -1);
				requestJson.put(SPOUSE_GENDER, -1);
				requestJson.put(IS_SPOUSE_SMOKER, -1);
			}
			requestJson.put(MODEL_INPUT, 1);
			requestJson.put(PROPOSER_AGE, age);
			requestJson.put(PROPOSER_GENDER, leadData.getOrDefault(GENDER, StringPool.BLANK));
			requestJson.put(SAME_PROPOSER, "1");
			requestJson.put(UNDERWRITING, 3);
			requestJson.put(FILTER_PRODUCT_ID, productId);

			log.debug("------Calling External API-----");
			log.debug(requestJson);

			JSONObject responseJSON = etipCoreAPI.callPostAPI(requestJson, productListUrl, true, apiKey, authorization);
			log.debug(responseJSON);
			if (Validator.isNull(responseJSON) || Validator.isNull(responseJSON.getJSONArray("ProdDetails"))) {
				log.error("No Recommended Product data Found");
				return recommendedProductList;
			}

			recommendedProductList = responseJSON.getJSONArray("ProdDetails");
			log.debug(recommendedProductList);

		} catch (JSONException | ETIPSystemException exception) {
			log.error("Error Message -- " + exception);
			if (log.isDebugEnabled()) {
				exception.printStackTrace();
			}
		}

		return recommendedProductList;
	}
	
	@Override
	public Quote getQuoteLMSRequestBody(Map<String, String> updateLMSRquestQuoteMapData) {
		
		Quote quote = new Quote();
		try {
			if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.PRODUCT_CODE_KEY))) {
				quote.setProductCode(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.PRODUCT_CODE_KEY));
			}
			if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(QUOTE_STAGE))) {
				quote.setStage(updateLMSRquestQuoteMapData.get(QUOTE_STAGE));
			}
			updateLMSQuoteCustomizeFormData(updateLMSRquestQuoteMapData, quote);	
			if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(PRODUCT_NAME_QUOTE))) {
				quote.setProductName(updateLMSRquestQuoteMapData.get(PRODUCT_NAME_QUOTE));
			}
			quote.setChildName(updateLMSRquestQuoteMapData.getOrDefault(CHILD_NAME, StringPool.BLANK));
			quote.setChildDOB(lmsDateFormate(updateLMSRquestQuoteMapData.getOrDefault(CHILD_DOB, StringPool.BLANK)));
			
			if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(PREMIUM_PAYMENT_FREQUENCY))) {
				quote.setPremiumPaymentFrequency(updateLMSRquestQuoteMapData.get(PREMIUM_PAYMENT_FREQUENCY));
			}
			
			if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(LITTLE_CHAMP_BENEFIT))) {
				quote.setLittleChampBenefitYN(updateLMSRquestQuoteMapData.get(LITTLE_CHAMP_BENEFIT));
			}
			if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.RISING_START_BENEFIT))) {
				quote.setRisingStarBenefitYN(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.RISING_START_BENEFIT));
			}
			
			if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.IS_LA_PR_SAME_YN))) {
				quote.setiSLAPRSameYN(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.IS_LA_PR_SAME_YN));
			}
			updateQuoteLMSFundData(updateLMSRquestQuoteMapData, quote);
			if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(POLICY_OPTION))) {
				quote.setPolicyOption(updateLMSRquestQuoteMapData.get(POLICY_OPTION));
			}
			if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(BI_PDF_PATH))) {
				quote.setbIPDFPath(updateLMSRquestQuoteMapData.get(BI_PDF_PATH));
			}
			quote.setSwpYn(Validator.isNotNull(updateLMSRquestQuoteMapData.get(SWP_YN)) ? updateLMSRquestQuoteMapData.get(SWP_YN) : NO);
			if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.SWP_PAYABLE))) {
				quote.setsWPPayable(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.SWP_PAYABLE));
			}
			
			if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.FAMILY_INCOME_BENEFIT_YN))) {
				quote.setFamilyIncomeBenefitYN(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.FAMILY_INCOME_BENEFIT_YN));
			}
			
			if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.PLAN_OPTIONS))) {
				quote.setPlanOptions(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.PLAN_OPTIONS));
			}
			
			if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.PAYOUT_OPTIONS))) {
				quote.setPayoutOptions(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.PAYOUT_OPTIONS));
			}
			
			/*
			 * Saral Jeevan Bima
			 */
			if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(LA_SMOKER_YN))) {
				quote.setlASmokerYN(updateLMSRquestQuoteMapData.get(LA_SMOKER_YN));
			}
			
			/*
			 * Zindagi Protect Plus
			 */
			if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.PAYOUT_PERIOD_YEARS))) {
				quote.setPayoutPeriodYears(GetterUtil.getInteger(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.PAYOUT_PERIOD_YEARS),0));
			}
			if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(POLICY_TERM))) {
				quote.setPolicyTerm(GetterUtil.getInteger(updateLMSRquestQuoteMapData.get(POLICY_TERM), 0));
			}
			if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(PREMIUM_PAYMENT_TERM))) {
				quote.setPremiumPaymentTerm(GetterUtil.getInteger(updateLMSRquestQuoteMapData.get(PREMIUM_PAYMENT_TERM)));
			}
			if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(SPOUSE_NAME))) {
				quote.setSpouseName(updateLMSRquestQuoteMapData.get(SPOUSE_NAME));
			}
			if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(SPOUSE_DOB))) {
				quote.setSpouseDOB(updateLMSRquestQuoteMapData.get(SPOUSE_DOB));
			}
			
			
			quote.setAdditionalBenefits(updateLMSRquestQuoteMapData.getOrDefault(EdelweissCommonConstants.ADDITIONAL_BENEFIT, EdelweissCommonConstants.NO_IPS));
			// Preparing LMS Request for Riders
			updateLMSQuoteADBRiders(updateLMSRquestQuoteMapData, quote);
			updateLMSQuoteATPDRiders(updateLMSRquestQuoteMapData, quote);
			updateLMSQuoteWOPRiders(updateLMSRquestQuoteMapData, quote);
			updatePWBQuoteATPDRiders(updateLMSRquestQuoteMapData, quote);
			updateCIQuoteATPDRiders(updateLMSRquestQuoteMapData, quote);
			updateIBQuoteATPDRiders(updateLMSRquestQuoteMapData, quote);
		} catch(Exception e) {
			log.error("EdelweissTokioCommonUtil >>> getQuoteLMSRequestBody >>> An error occurred while getting Quote Details for LMS update " + e);
		}
		return quote;
	}

	/**
	 * ADB Riders LMS update Request
	 * @param updateLMSRquestQuoteMapData
	 * @param quote
	 */
	private void updateLMSQuoteADBRiders(Map<String, String> updateLMSRquestQuoteMapData, Quote quote) {
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.ADB_RIDER_YN))) {
			quote.setaDBRiderYN(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.ADB_RIDER_YN));
		}
		
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.ADB_RIDER_PT))) {
			quote.setaDBRiderPT(GetterUtil.getInteger(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.ADB_RIDER_PT), 0));
		}
		
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.ADB_RIDER_PPT))) {
			quote.setaDBRiderPPT(GetterUtil.getInteger(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.ADB_RIDER_PPT), 0));
		}
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.ADB_RIDER_SA))) {
			quote.setaDBRiderSA(GetterUtil.getLong(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.ADB_RIDER_SA), 0));
		}
		
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.ADB_RIDER_AMOUNT))) {
			quote.setaDBRiderAmount(GetterUtil.getLong(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.ADB_RIDER_AMOUNT), 0));
		}
		
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.ADB_RIDER_AMOUNT_WO_TAX))) {
			quote.setaDBRiderAmountWoTax(GetterUtil.getInteger(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.ADB_RIDER_AMOUNT_WO_TAX), 0));
		}
	}
	
	/**
	 * ATPD Riders LMS update Request
	 * @param updateLMSRquestQuoteMapData
	 * @param quote
	 */
	private void updateLMSQuoteATPDRiders(Map<String, String> updateLMSRquestQuoteMapData, Quote quote) {
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.ATPD_RIDER_YN))) {
			quote.setaTPDRiderYN(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.ATPD_RIDER_YN));
		}
		
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.ATPD_RIDER_PT))) {
			quote.setaTPDRiderPT(GetterUtil.getInteger(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.ATPD_RIDER_PT), 0));
		}
		
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.ATPD_RIDER_PPT))) {
			quote.setaTPDRiderPPT(GetterUtil.getInteger(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.ATPD_RIDER_PPT), 0));
		}
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.ATPD_RIDER_SA))) {
			quote.setaTPDRiderSA(GetterUtil.getLong(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.ATPD_RIDER_SA), 0));
		}
		
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.ATPD_RIDER_AMOUNT))) {
			quote.setaTPDRiderAmount(GetterUtil.getLong(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.ATPD_RIDER_AMOUNT), 0));
		}
		
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.ATPD_RIDER_AMOUNT_WO_TAX))) {
			quote.setaTPDRiderAmountWoTax(GetterUtil.getInteger(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.ATPD_RIDER_AMOUNT_WO_TAX), 0));
		}
	}
	
	
	/**
	 * Waiver of Premium Riders LMS update Request
	 * @param updateLMSRquestQuoteMapData
	 * @param quote
	 */
	private void updateLMSQuoteWOPRiders(Map<String, String> updateLMSRquestQuoteMapData, Quote quote) {
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.WOP_RIDER_YN))) {
			quote.setwOPRiderYN(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.WOP_RIDER_YN));
		}
		
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.WOP_RIDER_PT))) {
			quote.setwOPRiderPT(GetterUtil.getInteger(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.WOP_RIDER_PT), 0));
		}
		
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.WOP_RIDER_PPT))) {
			quote.setwOPRiderPPT(GetterUtil.getInteger(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.WOP_RIDER_PPT), 0));
		}
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.WOP_RIDER_SA))) {
			quote.setwOPRiderSA(GetterUtil.getLong(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.WOP_RIDER_SA), 0));
		}
		
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.WOP_RIDER_AMOUNT))) {
			quote.setwOPRiderAmount(GetterUtil.getLong(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.WOP_RIDER_AMOUNT), 0));
		}
		
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.WOP_RIDER_AMOUNT_WO_TAX))) {
			quote.setwOPRiderAmountWoTax(GetterUtil.getInteger(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.WOP_RIDER_AMOUNT_WO_TAX), 0));
		}
	}
	
	/**
	 * Payor Waiver Benefit Riders LMS update Request
	 * @param updateLMSRquestQuoteMapData
	 * @param quote
	 */
	private void updatePWBQuoteATPDRiders(Map<String, String> updateLMSRquestQuoteMapData, Quote quote) {
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.PWB_RIDER_YN))) {
			quote.setpWBRiderYN(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.PWB_RIDER_YN));
		}
		
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.PWB_RIDER_PT))) {
			quote.setpWBRiderPT(GetterUtil.getInteger(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.PWB_RIDER_PT), 0));
		}
		
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.PWB_RIDER_PPT))) {
			quote.setpWBRiderPPT(GetterUtil.getInteger(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.PWB_RIDER_PPT), 0));
		}
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.PWB_RIDER_SA))) {
			quote.setpWBRiderSA(GetterUtil.getLong(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.PWB_RIDER_SA), 0));
		}
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.PWB_RIDER_AMOUNT))) {
			quote.setpWBRiderAmount(GetterUtil.getLong(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.PWB_RIDER_AMOUNT), 0));
		}
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.PWB_RIDER_AMOUNT_WO_TAX))) {
			quote.setpWBRiderAmountWoTax(GetterUtil.getInteger(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.PWB_RIDER_AMOUNT_WO_TAX), 0));
		}
		
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.PWB_RIDER_ON_DEATH_YN))) {
			quote.setpWbRiderOnDeathYN(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.PWB_RIDER_ON_DEATH_YN));
		}
		
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.PWB_RIDER_CIATPD_YN))) {
			quote.setpWbRiderCIATPDYN(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.PWB_RIDER_CIATPD_YN));
		}
		
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.PWB_RIDER_DEATH_CIATPD))) {
			quote.setpWBRiderDeathCIATPDYN(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.PWB_RIDER_DEATH_CIATPD));
		}
	}
	
	/**
	 * Critical Illness Riders LMS update Request
	 * @param updateLMSRquestQuoteMapData
	 * @param quote
	 */
	private void updateCIQuoteATPDRiders(Map<String, String> updateLMSRquestQuoteMapData, Quote quote) {
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.CI_RIDER_YN))) {
			quote.setcIRiderYN(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.CI_RIDER_YN));
		}
		
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.CI_RIDER_PT))) {
			quote.setcIRiderPT(GetterUtil.getInteger(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.CI_RIDER_PT), 0));
		}
		
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.CI_RIDER_PPT))) {
			quote.setaDBRiderPPT(GetterUtil.getInteger(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.CI_RIDER_PPT), 0));
		}
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.CI_RIDER_SA))) {
			quote.setcIRiderSA(GetterUtil.getLong(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.CI_RIDER_SA), 0));
		}
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.CI_RIDER_AMOUNT))) {
			quote.setcIRiderAmount(GetterUtil.getLong(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.CI_RIDER_AMOUNT), 0));
		}
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.CI_RIDER_AMOUNT_WO_TAX))) {
			quote.setcIRiderAmountWoTax(GetterUtil.getInteger(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.CI_RIDER_AMOUNT_WO_TAX), 0));
		}
	}
	
	/**
	 * Income Benefit Riders LMS update Request
	 * @param updateLMSRquestQuoteMapData
	 * @param quote
	 */
	private void updateIBQuoteATPDRiders(Map<String, String> updateLMSRquestQuoteMapData, Quote quote) {
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.IB_RIDER_YN))) {
			quote.setiBRiderYN(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.IB_RIDER_YN));
		}
		
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.IB_RIDER_PT))) {
			quote.setiBRiderPT(GetterUtil.getInteger(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.IB_RIDER_PT), 0));
		}
		
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.IB_RIDER_PPT))) {
			quote.setiBRiderPPT(GetterUtil.getInteger(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.IB_RIDER_PPT), 0));
		}
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.IB_RIDER_SA))) {
			quote.setiBRiderSA(GetterUtil.getLong(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.IB_RIDER_SA), 0));
		}
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.IB_RIDER_AMOUNT))) {
			quote.setiBRiderAmount(GetterUtil.getLong(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.IB_RIDER_AMOUNT), 0));
		}
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.IB_RIDER_AMOUNT_WO_TAX))) {
			quote.setiBRiderAmountWoTax(GetterUtil.getInteger(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.IB_RIDER_AMOUNT_WO_TAX), 0));
		}
	}
	
	/**
	 * Customize Form Data LMS update request 
	 * @param updateLMSRquestQuoteMapData
	 * @param quote
	 */
	private void updateLMSQuoteCustomizeFormData(Map<String, String> updateLMSRquestQuoteMapData, Quote quote) {
		
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(TOTAL_PREMIUM_AMOUNT))) {
			quote.setTotalPremiumAmount(GetterUtil.getLong(updateLMSRquestQuoteMapData.get(TOTAL_PREMIUM_AMOUNT), 0));
		}
		
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(TOTAL_PREMIUM_AMOUNT_WO_TAX))) {
			quote.setTotalPremiumAmountWoTax(GetterUtil.getLong(updateLMSRquestQuoteMapData.get(TOTAL_PREMIUM_AMOUNT_WO_TAX), 0));
		}
		
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(BASE_PREMIUM_AMOUNT))) {
			quote.setBasePremiumAmount(GetterUtil.getLong(updateLMSRquestQuoteMapData.get(BASE_PREMIUM_AMOUNT), 0));
		}
		
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(BASE_PREMIUM_AMOUNT_WO_TAX))) {
			quote.setBasePremiumAmountWoTax(GetterUtil.getLong(updateLMSRquestQuoteMapData.get(BASE_PREMIUM_AMOUNT_WO_TAX), 0));
		}
		
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(SUM_ASSURED))) {
			quote.setSumAssured(GetterUtil.getLong(updateLMSRquestQuoteMapData.get(SUM_ASSURED), 0));
		}
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(POLICY_TERM))) {
			quote.setPolicyTerm(GetterUtil.getInteger(updateLMSRquestQuoteMapData.get(POLICY_TERM), 0));
		}
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(PREMIUM_PAYMENT_TERM))) {
			quote.setPremiumPaymentTerm(GetterUtil.getInteger(updateLMSRquestQuoteMapData.get(PREMIUM_PAYMENT_TERM)));
		}
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(PAYOUT_OPTIONS))) {
			quote.setPayoutOptions(updateLMSRquestQuoteMapData.get(PAYOUT_OPTIONS));
		}
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(BI_QUATATION_NUMBER))) {
			quote.setbIQuotationNumber(updateLMSRquestQuoteMapData.get(BI_QUATATION_NUMBER));
		}
	}

	/**
	 * Fund Details LMS update request 
	 * @param updateLMSRquestQuoteMapData
	 * @param quote
	 */
	private void updateQuoteLMSFundData(Map<String, String> updateLMSRquestQuoteMapData, Quote quote) {
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EQUITY_LARGE_CAP_FUND))) {
			quote.setEquityLargeCapFund(GetterUtil.getInteger(updateLMSRquestQuoteMapData.get(EQUITY_LARGE_CAP_FUND)));
		}
		
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EQUITY_TOP_250_FUND))) {
			quote.setEquityTop250Fund(GetterUtil.getInteger(updateLMSRquestQuoteMapData.get(EQUITY_TOP_250_FUND)));
		}
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(BOND_FUND))) {
			quote.setBondFund(GetterUtil.getInteger(updateLMSRquestQuoteMapData.get(BOND_FUND)));
		}
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EQUITY_MID_CAP_FUND))) {
			quote.setEquityMidCapFund(GetterUtil.getInteger(updateLMSRquestQuoteMapData.get(EQUITY_MID_CAP_FUND)));
		}
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(GILT_FUND))) {
			quote.setGiltFund(GetterUtil.getInteger(updateLMSRquestQuoteMapData.get(GILT_FUND)));
		}
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.MANAGED_FUND))) {
			quote.setManagedFund(GetterUtil.getInteger(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.MANAGED_FUND)));
		}
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EQUITY_BLUE_CHIP_FUND))) {
			quote.setEquityBluechipFund(GetterUtil.getInteger(updateLMSRquestQuoteMapData.get(EQUITY_BLUE_CHIP_FUND)));
		}
		if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(LONG_TERM_BOND_FUND))) {
			quote.setLongTermBondFund(GetterUtil.getInteger(updateLMSRquestQuoteMapData.get(LONG_TERM_BOND_FUND)));
		}
	}
	
	@Override
	public InterestSessions getLMSRequestInterestSessionsBody(Map<String, String> updateLMSRquestQuoteMapData) {
		InterestSessions interSessions = new InterestSessions();
		try {
			if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(RECOMMENDED_OBJECTIVE_CATEGORY))) {
				interSessions.setRecommendedObjectiveCategory(updateLMSRquestQuoteMapData.getOrDefault(RECOMMENDED_OBJECTIVE_CATEGORY, StringPool.BLANK));
			}
			if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(RECOMMENDED_OBJECTIVE))) {
				interSessions.setRecommendedObjectiveCategory(updateLMSRquestQuoteMapData.getOrDefault(RECOMMENDED_OBJECTIVE, StringPool.BLANK));
				interSessions.setRecommendedObjective(updateLMSRquestQuoteMapData.getOrDefault(RECOMMENDED_OBJECTIVE, StringPool.BLANK));
			}
			if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(RECOMMENDED_ANNUAL_INCOME))) {
				interSessions.setRecommendedAnnualIncome(updateLMSRquestQuoteMapData.getOrDefault(RECOMMENDED_ANNUAL_INCOME, StringPool.BLANK));
			}
			if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(PERSON))) {
				interSessions.setPersona(updateLMSRquestQuoteMapData.getOrDefault(PERSON, StringPool.BLANK));
			}
			if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(LEAD_FORM_PRODUCT_CATEGORY))) {
				interSessions.setLeadFormProductCategory(updateLMSRquestQuoteMapData.getOrDefault(LEAD_FORM_PRODUCT_CATEGORY, StringPool.BLANK));
			}
			if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(EdelweissCommonConstants.PRODUCT_CODE_KEY))) {
				interSessions.setLeadFormProductCode(updateLMSRquestQuoteMapData.getOrDefault(EdelweissCommonConstants.PRODUCT_CODE_KEY, StringPool.BLANK));
			}
			if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(STAGE_INTEREST_SESSION))) {
				interSessions.setStage(updateLMSRquestQuoteMapData.get(STAGE_INTEREST_SESSION));
			}
			if(Validator.isNotNull(updateLMSRquestQuoteMapData.get(SITE_SECTION))) {
				interSessions.setSiteSection(updateLMSRquestQuoteMapData.get(SITE_SECTION));
			}
		} catch(Exception e) {
			log.error("EdelweissTokioCommonUtil >>> getLMSRequestInterestSessionsBody >>> An error occurred while getting Interest Sessions for LMS update " + e);
		}
		return interSessions;
	}

	@Override
	public String getUpdateLMSRequestBody(Map<String, String> updateLMSRequestMapData) {
		
		String lmsUpdateRequestBody = StringPool.BLANK;
		UpdateLead updateLead = new UpdateLead();
		updateLead.setLMSId(updateLMSRequestMapData.get(LEAD_ID));
		updateLead.setName(updateLMSRequestMapData.get(FULL_NAME));
		updateLead.setPhone(updateLMSRequestMapData.get(MOBILE_NUMBER));
		updateLead.setEmail(updateLMSRequestMapData.get(EMAIL));
		//updateLead.setDob(lmsDateFormate(updateLMSRequestMapData.getOrDefault(DATE_OF_BIRTH, StringPool.BLANK)));
		updateLead.setDob(updateLMSRequestMapData.getOrDefault(DATE_OF_BIRTH, StringPool.BLANK));
		updateLead.setGender(updateLMSRequestMapData.get(GENDER));
		
		if(updateLMSRequestMapData.get(GENDER).equalsIgnoreCase("3")) {
			updateLead.setGender("Male");
		} else if(updateLMSRequestMapData.get(GENDER).equalsIgnoreCase("4")) {
			updateLead.setGender("Female");
		} else if(updateLMSRequestMapData.get(GENDER).equalsIgnoreCase("1802")) {
			updateLead.setGender("Transgender");
		}
		
		if(Validator.isNotNull(updateLMSRequestMapData.get(MARRIED))) {
			updateLead.setMarried(updateLMSRequestMapData.getOrDefault(MARRIED, StringPool.BLANK));
		}
		
		if(Validator.isNotNull(updateLMSRequestMapData.get(TOBACCO))) {
			updateLead.setTobacco(updateLMSRequestMapData.getOrDefault(TOBACCO, StringPool.BLANK));
		}
		
		if(Validator.isNotNull(updateLMSRequestMapData.get(INCOME))) {
			updateLead.setIncome(updateLMSRequestMapData.getOrDefault(INCOME, StringPool.BLANK));
		}
		
		updateLead.setLeadFormType(updateLMSRequestMapData.get(LEAD_FORM_TYPE));
		updateLead.setSumassured(updateLMSRequestMapData.getOrDefault(SUM_ASSURED, StringPool.BLANK));
		updateLead.setChildName(updateLMSRequestMapData.getOrDefault(CHILD_NAME, StringPool.BLANK));
		updateLead.setChildDob(lmsDateFormate(updateLMSRequestMapData.getOrDefault(CHILD_DOB, StringPool.BLANK)));
		updateLead.setProposerLARelation(updateLMSRequestMapData.getOrDefault(PROPOSER_LA_RELATION, StringPool.BLANK));
		
		//Setting Source based on URL
		updateLead.setSource(GetterUtil.getString(updateLMSRequestMapData.get(PARTNER_SOURCE), EDELWEISS_WEB_SALES));
		
		updateLead.setSpouseName(updateLMSRequestMapData.getOrDefault(EdelweissCommonConstants.SPOUSE_NAME_KEY, StringPool.BLANK));
		//updateLead.setSpouseDob(lmsDateFormate(updateLMSRequestMapData.getOrDefault(EdelweissCommonConstants.SPOUSE_DOB_KEY, StringPool.BLANK)));
		updateLead.setSpouseDob(updateLMSRequestMapData.getOrDefault(EdelweissCommonConstants.SPOUSE_DOB_KEY, StringPool.BLANK));
		updateLead.setSpouseSmoke(updateLMSRequestMapData.getOrDefault(SPOUSE_SMOKE, StringPool.BLANK));
	
		/*
		 * For saraljeevanbima extrs fields
		 */
		updateLead.setEducationalqualification(updateLMSRequestMapData.get(EDUCATION_QUALIFICATION));
		updateLead.setEducation(updateLMSRequestMapData.get(EDUCATION));
		updateLead.setNatureofduty(updateLMSRequestMapData.get(NATURE_OF_DUTY));
		updateLead.setOccupation(updateLMSRequestMapData.get(OCCUPATION));
		updateLead.setAnnualIncome(updateLMSRequestMapData.get(ANNUAL_INCOME));
		updateLead.setPincode(updateLMSRequestMapData.get(PINCODE));
		updateLead.setPermanentPinCode(updateLMSRequestMapData.get(PERMANENT_PINCODE));
		updateLead.setIs_smoke(updateLMSRequestMapData.get(SMOKER));
		updateLead.setSpouse(updateLMSRequestMapData.get(SPOUSE));
		updateLead.setSpouseOccupation(updateLMSRequestMapData.get(SPOUSE_OCCUPATION));
		updateLead.setSpouseSumassured(updateLMSRequestMapData.get(SPOUSE_SUMASSURED));
		updateLead.setRelation(updateLMSRequestMapData.get(RELATION));
		
		if(Validator.isNotNull(updateLMSRequestMapData.get(EdelweissCommonConstants.ALT_PHONE_NUMBER))) {
			updateLead.setAlternatePhone(updateLMSRequestMapData.getOrDefault(EdelweissCommonConstants.ALT_PHONE_NUMBER, StringPool.BLANK));
		}
		
		if(Validator.isNotNull(updateLMSRequestMapData.get(EdelweissCommonConstants.LA_PROPOSER_RELATION))) {
			updateLead.setLAProposerRelation(updateLMSRequestMapData.getOrDefault(EdelweissCommonConstants.LA_PROPOSER_RELATION, StringPool.BLANK));
		}
		
		if(Validator.isNotNull(updateLMSRequestMapData.get(SUM_ASSURED))) {
			updateLead.setSumassured(updateLMSRequestMapData.get(SUM_ASSURED));
			updateLead.setTotalSumAssured(updateLMSRequestMapData.get(SUM_ASSURED));
		}
		
		if(Validator.isNotNull(updateLMSRequestMapData.get(EdelweissObjectConstants.C_ID))) {
			updateLead.setProductCId(updateLMSRequestMapData.get(EdelweissObjectConstants.C_ID));
		}
		if(Validator.isNotNull(updateLMSRequestMapData.get(EdelweissObjectConstants.V_ID))) {
			updateLead.setProductVId(updateLMSRequestMapData.get(EdelweissObjectConstants.V_ID));
		}
		if(Validator.isNotNull(updateLMSRequestMapData.get(EdelweissObjectConstants.CP_ID))) {
			updateLead.setProductCPId(updateLMSRequestMapData.get(EdelweissObjectConstants.CP_ID));
		}
		
		InterestSessions interSessions = getLMSRequestInterestSessionsBody(updateLMSRequestMapData);
		updateLead.setInterestSessions(interSessions);
		
		Quote quote = getQuoteLMSRequestBody(updateLMSRequestMapData);
		updateLead.setQuote(quote);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		UpdateLMSLeadRequest updateLMSLeadRequest = new UpdateLMSLeadRequest();
		updateLMSLeadRequest.setupdateLead(updateLead);
		
		try {
			lmsUpdateRequestBody = mapper.writeValueAsString(updateLMSLeadRequest);
			log.debug("EdelweissTokioCommonUtil >>> getUpdateLMSRequestBody >>> LMS Request Body  " + lmsUpdateRequestBody);
		} catch (Exception e) {
			log.error("EdelweissTokioCommonUtil >>> getUpdateLMSRequestBody >>> An error occur while getting updating ::: " + e);
		}
		return lmsUpdateRequestBody;
	}
	
	@Override
	public JSONObject updateLMSLead(String requestBody) throws JSONException, ETIPSystemException {
		EdelweissUpdateLeadConfiguration edelweissUpdateLeadConfiguration = EdelweissConfigurationUtil.getEdelweissUpdateLeadConfiguration();
		
		String authorization = getOAuthToken(edelweissUpdateLeadConfiguration.getOAuth2QJURL(),
				edelweissUpdateLeadConfiguration.getOAuth2QJUsername(),
				edelweissUpdateLeadConfiguration.getOAuth2QJPassword());
		log.debug("username : " + edelweissUpdateLeadConfiguration.getOAuth2QJUsername() );	
		log.debug("password : " + edelweissUpdateLeadConfiguration.getOAuth2QJPassword() );	
		log.debug("getUpdateLeadQJXAPIKEY : " + edelweissUpdateLeadConfiguration.getUpdateLeadQJXAPIKEY() );			
		
		JSONObject leadRequestJSON = JSONFactoryUtil.createJSONObject(requestBody);
		JSONObject responseLeadUpdateJSON = etipCoreAPI.callPostAPI(leadRequestJSON, edelweissUpdateLeadConfiguration.getUpdateLeadQJURL(), true, edelweissUpdateLeadConfiguration.getUpdateLeadQJXAPIKEY(), authorization);
		log.debug("EdelweissTokioCommonUtil >>> updateLMSLead >>> lead update response : " + responseLeadUpdateJSON );	
		
		return responseLeadUpdateJSON;
	}
	
	/**
	 * Fetch the given product data with Linked Recommended Plans and Riders
	 * 
	 * @param productId
	 * @return productNestedData
	 */
	@Override
	public JSONObject getProductDataWithLinkedPlansAndRiders(String portalURL, String productId) {
		JSONObject productNestedData = JSONFactoryUtil.createJSONObject();
		String productNestedDataUrl = portalURL
				+ LIFERAY_PRODUCT_MASTER_NEASTED_URL.replace("${productMasterId}", productId)
		+ LIFERAY_PRODUCT_PLANS_RIDERS_URL.replace("${productMasterId}", productId);
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
		String liferayUserName = edelweissLRBasicAuthConfiguration.getLRUsername();
		String liferayPassword = edelweissLRBasicAuthConfiguration.getLRPassword();

		try {
			productNestedData = etipCoreAPI.callGetAPI(new HashMap<>(), productNestedDataUrl, false, liferayUserName,
					liferayPassword);

		} catch (ETIPSystemException etipSystemException) {
			log.error("Unable to fetch the Product benefits List:" + etipSystemException);
			if (log.isDebugEnabled()) {
				etipSystemException.printStackTrace();
			}
		}

		return productNestedData;
	}
	
	/**
	 * Fetch the given product and mapped Riders data
	 * 
	 * @param productId
	 * @return productNestedData
	 */
	@Override
	public JSONObject getProductAndLinkedRidersData(String portalURL, String productId) {
		JSONObject productNestedData = JSONFactoryUtil.createJSONObject();
		String productNestedDataUrl = portalURL + LIFERAY_PRODUCT_RIDERS_URL.replace("${productMasterId}", productId);

		try {
			EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
			productNestedData = etipCoreAPI.callGetAPI(new HashMap<>(), productNestedDataUrl, false,
					edelweissLRBasicAuthConfiguration.getLRUsername(),
					edelweissLRBasicAuthConfiguration.getLRPassword());

		} catch (ETIPSystemException etipSystemException) {
			log.error("Unable to fetch the Product benefits List:" + etipSystemException);
			if (log.isDebugEnabled()) {
				etipSystemException.printStackTrace();
			}
		}

		return productNestedData;
	}
	
	@Override
	public JSONObject getProductAndLinkedDirectRidersData(String portalURL, String productId) {
		JSONObject productNestedData = JSONFactoryUtil.createJSONObject();
		String productNestedDataUrl = portalURL + LIFERAY_DIRECT_PRODUCT_RIDERS_URL.replace("${productMasterId}", productId);

		try {
			EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
			productNestedData = etipCoreAPI.callGetAPI(new HashMap<>(), productNestedDataUrl, false,
					edelweissLRBasicAuthConfiguration.getLRUsername(),
					edelweissLRBasicAuthConfiguration.getLRPassword());

		} catch (ETIPSystemException etipSystemException) {
			log.error("Unable to fetch the Product benefits List:" + etipSystemException);
			if (log.isDebugEnabled()) {
				etipSystemException.printStackTrace();
			}
		}

		return productNestedData;
	}
	
	@Override
	public JSONObject getProductJsonConfigurationByProductId(String portalURL, String productId) {
		JSONObject productConfigJsonRes = JSONFactoryUtil.createJSONObject();
		String productConfigJsonUrl = portalURL + PRODUCT_JSON_CONFIGURATION_SERVICE_URL.replace("${productId}", productId);

		try {
			EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
			productConfigJsonRes = etipCoreAPI.callGetAPI(new HashMap<>(), productConfigJsonUrl, false,
					edelweissLRBasicAuthConfiguration.getLRUsername(),
					edelweissLRBasicAuthConfiguration.getLRPassword());

		} catch (ETIPSystemException etipSystemException) {
			log.error("Unable to fetch the Product benefits List:" + etipSystemException);
			if (log.isDebugEnabled()) {
				etipSystemException.printStackTrace();
			}
		}

		return productConfigJsonRes;
	}
	
	@Override
	public JSONObject getProductRidersData(String portalURL, String riderId) {
		JSONObject response = JSONFactoryUtil.createJSONObject();
		String productRiderUrl = portalURL + LIFERAY_RIDERS_URL.replace("${productRiderId}", riderId);

		try {
			EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
			response = etipCoreAPI.callGetAPI(new HashMap<>(), productRiderUrl, false,
					edelweissLRBasicAuthConfiguration.getLRUsername(),
					edelweissLRBasicAuthConfiguration.getLRPassword());

		} catch (ETIPSystemException etipSystemException) {
			log.error("Unable to fetch the Product benefits List:" + etipSystemException);
			if (log.isDebugEnabled()) {
				etipSystemException.printStackTrace();
			}
		}

		return response;
	}
	
	@Override
	public JSONObject generateLead(Map<String, String>formValues, String productName, JSONObject metaDataResponse, String userAgent) {
		log.debug("calling the generateLead API:::");
		if(Validator.isNull(metaDataResponse))
		{
			log.error("Unable to Generate Lead with No Metadata details");
			return null;
		}
		
		JSONObject responseJSON = JSONFactoryUtil.createJSONObject();
		try {
			EdelweissGenerateLeadConfiguration edelweissGenerateLeadConfiguration = EdelweissConfigurationUtil.getEdelweissGenerateLeadConfiguration(); 
			// Code for external API and Liferay Object
			
			String authorization = getOAuthToken(edelweissGenerateLeadConfiguration.getOAuth2QJURL(), 
					edelweissGenerateLeadConfiguration.getOAuth2QJUsername(), edelweissGenerateLeadConfiguration.getOAuth2QJPassword());
			Lmslead lmsLead = new Lmslead();
			InterestSessions interestSessions = new InterestSessions();
			
			if(Validator.isNotNull(GetterUtil.getString(formValues.get("c_id")))) {
				lmsLead.setcId(GetterUtil.getString(formValues.get("c_id")));
			}else {
			lmsLead.setcId(metaDataResponse.getJSONArray(PRODUCT_RELATIONSHIP).getJSONObject(0).getString(C_ID));
			}	
			
			if(Validator.isNotNull(GetterUtil.getString(formValues.get("cp_id")))) {
				lmsLead.setCpId(GetterUtil.getString(formValues.get("cp_id")));
			}else {
				lmsLead.setCpId(metaDataResponse.getJSONArray(PRODUCT_RELATIONSHIP).getJSONObject(0).getString(CP_ID));
			}	
			
			lmsLead.setCampaignId(StringPool.BLANK);
			lmsLead.setDevice(StringPool.BLANK);
			//lmsLead.setDob(lmsDateFormate(formValues.get(DATE_OF_BIRTH).trim()));
			lmsLead.setDob(formValues.get(DATE_OF_BIRTH));
			lmsLead.setEmail(formValues.get(EMAIL));
			lmsLead.setGender(formValues.get(GENDER));
			
			if(formValues.get(GENDER).equalsIgnoreCase("3")) {
				lmsLead.setGender("Male");
			} else if(formValues.get(GENDER).equalsIgnoreCase("4")) {
				lmsLead.setGender("Female");
			} else if(formValues.get(GENDER).equalsIgnoreCase("1802")) {
				lmsLead.setGender("Transgender");
			}
			
			lmsLead.setName(formValues.get(FULL_NAME));
			lmsLead.setPhone(formValues.get(MOBILE_NUMBER));
			lmsLead.setProduct(productName);
			lmsLead.setSiteSection(metaDataResponse.getString(SITE_SECTION));
			lmsLead.setUserBrowser(userAgent);
			
			if(Validator.isNotNull(GetterUtil.getString(formValues.get("v_id")))) {
				lmsLead.setvId(GetterUtil.getString(formValues.get("v_id")));
			}else {
			lmsLead.setvId(metaDataResponse.getJSONArray(PRODUCT_RELATIONSHIP).getJSONObject(0).getString(V_ID));
			}	

			lmsLead.setVisitorId(StringPool.BLANK);
			lmsLead.setWebUrl(metaDataResponse.getString(WEB_URL));
			lmsLead.setLeadFormType(RECCOMENDATION);
			lmsLead.setNationality(INDIAN);
			lmsLead.setOccupation(formValues.get(OCCUPATION));
			lmsLead.setEducationqualification(formValues.get(EDUCATION_QUALIFICATION));
			lmsLead.setNatureofduty(formValues.get(NATURE_OF_DUTY));
			lmsLead.setAnnualincome(formValues.get(ANNUAL_INCOME));
			lmsLead.setPincode(formValues.get(PINCODE));
			lmsLead.setSpouse(formValues.get(SPOUSE));
			lmsLead.setSpouseOccupation(formValues.get(SPOUSE_OCCUPATION));
			lmsLead.setSpouseSumassured(formValues.get(SPOUSE_SUMASSURED));
			lmsLead.setSmoke(formValues.get(IS_SMOKE));
			
			//Set Partner
			lmsLead.setSource(GetterUtil.getString(formValues.get(PARTNER_SOURCE), metaDataResponse.getJSONArray(PRODUCT_RELATIONSHIP).getJSONObject(0).getString(SOURCE_OF_TRAFFIC)));			
			
			//Set UTM
			lmsLead.setUtmSource(GetterUtil.getString(formValues.get("Utm_source"), metaDataResponse.getJSONArray(PRODUCT_RELATIONSHIP).getJSONObject(0).getString(SOURCE_OF_TRAFFIC)));
			lmsLead.setUtmMedium(GetterUtil.getString(formValues.get("Utm_medium"), StringPool.BLANK));
			lmsLead.setUtmCampaign(GetterUtil.getString(formValues.get("Utm_campaign"), StringPool.BLANK));
			lmsLead.setUtmTerm(GetterUtil.getString(formValues.get("Utm_term"), StringPool.BLANK));
			lmsLead.setUtmContent(GetterUtil.getString(formValues.get("Utm_content"), StringPool.BLANK));
			lmsLead.setuTMPlacement(GetterUtil.getString(formValues.get("Utm_Placement"), StringPool.BLANK));
			lmsLead.setUtmCreative(GetterUtil.getString(formValues.get("Utm_creative"), StringPool.BLANK));
			lmsLead.setAdgroupId(GetterUtil.getString(formValues.get("adgroup_id"), StringPool.BLANK));
			lmsLead.setDevice(GetterUtil.getString(formValues.get("device"), StringPool.BLANK));
			lmsLead.setGclid(GetterUtil.getString(formValues.get("Gclid"), StringPool.BLANK));			
			
			lmsLead.setMarried(formValues.get(MARRIED));
			lmsLead.setTobacco(formValues.get(TOBACCO));
			lmsLead.setIncome(formValues.get(INCOME));
			lmsLead.setSpouseName(formValues.get(EdelweissCommonConstants.SPOUSE_NAME_KEY));
			//lmsLead.setSpouseDob(lmsDateFormate(formValues.get(EdelweissCommonConstants.SPOUSE_DOB_KEY)));
			lmsLead.setSpouseDob(formValues.get(EdelweissCommonConstants.SPOUSE_DOB_KEY));
			lmsLead.setSpouseSmoke(formValues.get(SPOUSE_SMOKE));
			lmsLead.setChildName(formValues.get(CHILD_NAME));
			lmsLead.setChildDob(lmsDateFormate(formValues.get(CHILD_DOB)));
			interestSessions.setLeadFormProductCategory(StringPool.BLANK);
			interestSessions.setLeadFormProductCode(metaDataResponse.getString(PRODUCT_ID));
			interestSessions.setPersona(StringPool.BLANK);
			interestSessions.setRecommendedAnnualIncome(StringPool.BLANK);
			interestSessions.setRecommendedObjective(metaDataResponse.getString(ING_PLAN_SUB_TYPE));
			interestSessions.setRecommendedObjectiveCategory(metaDataResponse.getString(PRODUCT_CATEGORY));
			interestSessions.setRecommendedProduct(productName);
			interestSessions.setSiteSection(metaDataResponse.getString(SITE_SECTION));
			interestSessions.setStage(GET_STARTED);
			lmsLead.setInterestSessions(interestSessions);
			
			Quote quote = new Quote();
			if(Validator.isNotNull(metaDataResponse.getString(PRODUCT_ID))) {
				quote.setProductCode(metaDataResponse.getString(PRODUCT_ID));
			}
			if(Validator.isNotNull(formValues.get(QUOTE_STAGE))) {
				quote.setStage(formValues.get(QUOTE_STAGE));
			}
			if(Validator.isNotNull(productName)) {
				quote.setProductName(productName);
			}
			lmsLead.setQuote(quote);
			
			LMSRequest lmsRequest = new LMSRequest();
			lmsRequest.setLmslead(lmsLead);
			
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(lmsRequest);
			log.debug("EdelweissProductEnquiryUtil >>>  generateLead >>> request Body ::: " + jsonString);
			JSONObject requestJson = JSONFactoryUtil.createJSONObject(jsonString);
			String generateLeadQJURL =edelweissGenerateLeadConfiguration.getGenerateLeadQJURL();
			log.debug("generateLeadQJURL....." + generateLeadQJURL);
			responseJSON = etipCoreAPI.callPostAPI(requestJson, generateLeadQJURL, true, edelweissGenerateLeadConfiguration.getGenerateLeadQJXAPIKEY(), authorization);
			log.debug("EdelweissProductEnquiryUtil >>>  generateLead >>> responseJSON ::: " + responseJSON);
			
		} catch (JSONException | ETIPSystemException e) {
			log.error("EdelweissProductEnquiryUtil >>>  generateLead >>> An exception occurred while updating LMS system :::  " + e);
			if(log.isDebugEnabled()) {
				e.printStackTrace();
			}
		} catch (JsonProcessingException e) {
			log.error("EdelweissProductEnquiryUtil >>>  generateLead >>> An exception occurred while processing JSON :::  " + e);
			if(log.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		
		return responseJSON;
	}	
	
	/**
	 * Update Customer Enquite Based on Lead ID
	 * 
	 * @param customerEnquiryRequest data to update in Customer inquiry
	 * @param leadId to fetch Customer Enquiry by lead Id
	 * @throws ETIPSystemException 
	 */
	@Override
	public CustomerEnquiry updateCustomerEnquiryById(String portalURL, CustomerEnquiry customerEnquiryRequest, Long customerEnquiryId) throws Exception {
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
		log.debug("EdelweissTokioCommonUtil >>> updateCustomerEnquiryById >>> Updating Customer Enquiry By Customer ID : " + customerEnquiryId );
		String updateCustomerEnquireServiceURL = portalURL + PUT_CUSTOMER_DATA;
		updateCustomerEnquireServiceURL = StringUtil.replace(updateCustomerEnquireServiceURL, "{customerEnquiryId}", String.valueOf(customerEnquiryId));
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		String customerEnquiryUpdateStr = mapper.writeValueAsString(customerEnquiryRequest);
		log.debug("EdelweissTokioCommonUtil >>> updateCustomerEnquiryById >>> Customer Enquiry Request Body : " + customerEnquiryUpdateStr);
		
		JSONObject customerEnquiryUpdateRequestJSON = JSONFactoryUtil.createJSONObject(customerEnquiryUpdateStr);
		JSONObject customerEnquiryUpdatedResponseJSON = etipCoreAPI.callPutAPI(customerEnquiryUpdateRequestJSON, updateCustomerEnquireServiceURL, false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());

		CustomerEnquiry customerEnquiry = mapper.readValue(customerEnquiryUpdatedResponseJSON.toString(), CustomerEnquiry.class);
		log.debug("EdelweissDeleteFamilyDetailsResourceCommand >>>> getCustomerEnquiryByLeadId >>>> CustomerEnquiry updated Successfully :  " + customerEnquiry.toString());
		return customerEnquiry;
	}
	
	@Override
	public String getLeadMetaDataURL(String leadId) {
		StringBuilder leadURL = new StringBuilder();
		leadURL.append(LEAD_ID);
		leadURL.append(StringPool.SPACE);
		leadURL.append("eq");
		leadURL.append(StringPool.SPACE);
		leadURL.append("'" + leadId + "'");
		return URLEncoder.encode(leadURL.toString(), StandardCharsets.UTF_8).replace(" ", "%20");
	}
	
	@Override
	public String getMultipleLoginURL(String policyNumber) {
		StringBuilder leadURL = new StringBuilder();
		leadURL.append(POLICY_NUMBER);
		leadURL.append(StringPool.SPACE);
		leadURL.append("eq");
		leadURL.append(StringPool.SPACE);
		leadURL.append("'" + policyNumber + "'");
		return URLEncoder.encode(leadURL.toString(), StandardCharsets.UTF_8).replace(" ", "%20");
	}
	
	@Override
	public CustomerEnquiry getCustomerEnquiryByLeadId(String portalURL, String leadId)  {
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
		CustomerEnquiry customerEnquiry = null;
		try {
			log.debug("EdelweissTokioCommonUtil >>>> getCustomerEnquiryByLeadId >>>> Fetching Customer Enquiry By Lead ID :  " + leadId);
			String parameterURL = getLeadMetaDataURL(leadId);
			String customerInquiryURL = portalURL + LIFERAY_PRODUCT_ENQUIRY_URL + FILTER_QUERY_PARAM + parameterURL;
			JSONObject customerEnquiryResponse = etipCoreAPI.callGetAPI(new HashMap<>(), customerInquiryURL, false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());
			
			if(Validator.isNotNull(customerEnquiryResponse.getJSONArray(ITEMS)) && customerEnquiryResponse.getJSONArray(ITEMS).length() > 0) {
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				
				JSONObject customerEnquityJSON = customerEnquiryResponse.getJSONArray(ITEMS).getJSONObject(0);

				customerEnquiry = mapper.readValue(customerEnquityJSON.toString(), CustomerEnquiry.class);
				log.debug("EdelweissTokioCommonUtil >>>> getCustomerEnquiryByLeadId >>>> CustomerEnquiry Fetched Successfully :  " + customerEnquiry.toString());
			} else {
				log.debug("EdelweissTokioCommonUtil >>> getCustomerEnquiryByLeadId >>> No such entry found with lead ID");
			}
		} catch(Exception e) {
			log.error("EdelweissTokioCommonUtil >>> getCustomerEnquiryByLeadId >>> An error occurred while fetching Customer Enquiry " + e);
		}
		return customerEnquiry;
	}
	
	/**
	 * Add and Update Family Details
	 * 
	 * @param portalURL
	 * @param customerFamilyDetails
	 * @param familyId
	 * @return
	 * @throws JsonProcessingException
	 * @throws ETIPSystemException
	 * @throws JSONException
	 */
	@Override
	public CustomerFamilyDetailsRel updateFamilyDetails(String portalURL, CustomerFamilyDetailsRel customerFamilyDetails, String familyId) throws JsonProcessingException, ETIPSystemException, JSONException {
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
		String liferayUserName = edelweissLRBasicAuthConfiguration.getLRUsername();
		String liferayPassword = edelweissLRBasicAuthConfiguration.getLRPassword();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		String customerFamilyDetailsStr = mapper.writeValueAsString(customerFamilyDetails);
		log.debug("EdelweissTokioCommonUtil >>> updateFamilyDetails >>> Customer Family Details Request Body : " + customerFamilyDetailsStr);
		
		// Attemp to Check if Any Entry Exist in Customer Family Details By Lead ID
		if(Validator.isNull(familyId) && Validator.isNotNull(customerFamilyDetails.getLeadId())) {
			JSONObject familyDetailsResponse = getFamilyDetailsByLeadId(portalURL, customerFamilyDetails.getLeadId());
			if(Validator.isNotNull(familyDetailsResponse)) {
				familyId = familyDetailsResponse.getString(EdelweissObjectConstants.ID);
			}
		}
					
		JSONObject customerFamilyDetailsReqJson = JSONFactoryUtil.createJSONObject(customerFamilyDetailsStr);
		JSONObject familDetailsResponseJSON = null;
		if(Validator.isNotNull(familyId)) {
			String customerFamilyURL = portalURL + URL_FOR_CUSTOMER_FAMILY_DETAILS + familyId;
			familDetailsResponseJSON = etipCoreAPI.callPutAPI(customerFamilyDetailsReqJson, customerFamilyURL, false, liferayUserName, liferayPassword);
			log.debug("EdelweissTokioCommonUtil >>> updateFamilyDetails >>> Customer Family Details updated : " + familDetailsResponseJSON.toString());
		} else {
			String customerFamilyURL = portalURL + URL_FOR_CUSTOMER_FAMILY_DETAILS;
			familDetailsResponseJSON = etipCoreAPI.callPostAPI(customerFamilyDetailsReqJson, customerFamilyURL, false, liferayUserName, liferayPassword);
			log.debug("EdelweissTokioCommonUtil >>> updateFamilyDetails >>> Customer Family Details added : " + familDetailsResponseJSON.toString());
		}
		return mapper.readValue(familDetailsResponseJSON.toString(), CustomerFamilyDetailsRel.class);
	}
	
	/**
	 * Delete Family Details By Family ID
	 */
	@Override
	public Boolean deleteFamilyDetailsById(String portalURL, String familyId) {
		try {
			if(Validator.isNotNull(familyId)) {
				EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
				
				JSONObject familDetailsResponseJSON = etipCoreAPI.callDeleteAPI(portalURL + URL_FOR_CUSTOMER_FAMILY_DETAILS + familyId, false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());
				log.debug("EdelweissTokioCommonUtil >>> updateFamilyDetails >>> Customer Family Details Deleted Response : " + familDetailsResponseJSON.toString());
				return true;
			} else {
				return true;
			}
		} catch(Exception e) {
			log.debug("EdelweissTokioCommonUtil >>> deleteFamilyDetailsById >>> Customer Family Details Deleted : " + e);
			return false;
		}
	}
	
	
	@Override
	public CustomerInvestmentDetails getInvestmentDetailsLeadId(String portalURL, String leadId)  {
		CustomerInvestmentDetails customerInvestmentDetails = null;
		try {
			EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
			log.debug("EdelweissTokioCommonUtil >>>> getInvestmentDetailsLeadId >>>> Fetching Customer Investment Details By Lead ID :  " + leadId);
			String parameterURL = getLeadMetaDataURL(leadId);
			String customerInvestmentDetailsURL = portalURL + CUSTOMER_INVESTMENT_DETAILS_BASE_URL + FILTER_QUERY_PARAM + parameterURL;
			JSONObject customerInvestmentDetailsRes = etipCoreAPI.callGetAPI(new HashMap<>(), customerInvestmentDetailsURL, false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());
			
			if(Validator.isNotNull(customerInvestmentDetailsRes.getJSONArray(ITEMS)) && customerInvestmentDetailsRes.getJSONArray(ITEMS).length() > 0) {
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				
				JSONObject customerInvestmentDetailsJSON = customerInvestmentDetailsRes.getJSONArray(ITEMS).getJSONObject(0);

				customerInvestmentDetails = mapper.readValue(customerInvestmentDetailsJSON.toString(), CustomerInvestmentDetails.class);
				log.debug("EdelweissTokioCommonUtil >>>> getInvestmentDetailsLeadId >>>> Customer Investment Details Fetched Successfully :  " + customerInvestmentDetails.toString());
			} else {
				log.debug("EdelweissTokioCommonUtil >>> getInvestmentDetailsLeadId >>> No such entry found with lead ID");
			}
		} catch(Exception e) {
			log.error("EdelweissTokioCommonUtil >>> getInvestmentDetailsLeadId >>> An error occurred while fetching Customer Investment Details " + e);
		}
		return customerInvestmentDetails;
	}
	
	@Override
	public MultipleLoginBlock getMultipleLoginByPolicyNumber(String portalURL, String policyNumber)  {
		MultipleLoginBlock multipleLoginDetails = null;
		try {
			EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
			log.debug("EdelweissTokioCommonUtil >>>> getMultipleLoginByPolicyNumber >>>> Fetching MultipleLogin Details By proposalNumber :  " + policyNumber);
			String parameterURL = getMultipleLoginURL(policyNumber);
			String multipleLoginDetailsURL = portalURL + MULTIPLE_LOGINS_BLOCK_BASE_URL + FILTER_QUERY_PARAM + parameterURL;
			log.debug("multipleLoginDetailsURL::::::"+ multipleLoginDetailsURL);
			JSONObject multipleLoginDetailsRes = etipCoreAPI.callGetAPI(new HashMap<>(), multipleLoginDetailsURL, false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());
			log.debug("multipleLoginDetailsRes:::::"+multipleLoginDetailsRes);
			if(Validator.isNotNull(multipleLoginDetailsRes.getJSONArray(ITEMS)) && multipleLoginDetailsRes.getJSONArray(ITEMS).length() > 0) {
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				JSONObject multipleLoginDetailsResponse = multipleLoginDetailsRes.getJSONArray(ITEMS).getJSONObject(0);
				multipleLoginDetails = mapper.readValue(multipleLoginDetailsResponse.toString(), MultipleLoginBlock.class);
				log.debug("EdelweissTokioCommonUtil >>> getMultipleLoginByPolicyNumber >>> multipleLoginDetailsResponse Successfully : " + multipleLoginDetailsResponse);
				
			} else {
				log.debug("EdelweissTokioCommonUtil >>> getMultipleLoginByPolicyNumber >>> No such entry found with proposalNumber");
			}
		} catch(Exception e) {
			log.error("EdelweissTokioCommonUtil >>> getMultipleLoginByPolicyNumber >>> An error occurred while fetching multipleLoginDetailsResponse " + e);
		}
		return multipleLoginDetails;
	}
	
	@Override
	public JSONObject getCustomerInvestmentDetailsLeadId(String portalURL, String leadId)  {
		JSONObject customerInvestmentDetailsJSON = null;
		try {
			EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
			log.debug("EdelweissTokioCommonUtil >>>> getCustomerInvestmentDetailsLeadId >>>> Fetching Customer Investment Details By Lead ID :  " + leadId);
			String parameterURL = getLeadMetaDataURL(leadId);
			String customerInvestmentDetailsURL = portalURL + CUSTOMER_INVESTMENT_DETAILS_BASE_URL + FILTER_QUERY_PARAM + parameterURL;
			JSONObject customerInvestmentDetailsRes = etipCoreAPI.callGetAPI(new HashMap<>(), customerInvestmentDetailsURL, false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());
			
			if(Validator.isNotNull(customerInvestmentDetailsRes.getJSONArray(ITEMS)) && customerInvestmentDetailsRes.getJSONArray(ITEMS).length() > 0) {
				customerInvestmentDetailsJSON = customerInvestmentDetailsRes.getJSONArray(ITEMS).getJSONObject(0);
				log.debug("EdelweissTokioCommonUtil >>>> getCustomerInvestmentDetailsLeadId >>>> Customer Investment Details Fetched Successfully :  " + customerInvestmentDetailsJSON.toString());
			} else {
				log.debug("EdelweissTokioCommonUtil >>> getCustomerInvestmentDetailsLeadId >>> No such entry found with lead ID  : " + leadId);
			}
		} catch(Exception e) {
			log.error("EdelweissTokioCommonUtil >>> getCustomerInvestmentDetailsLeadId >>> An error occurred while fetching Customer Investment Details " + e);
		}
		return customerInvestmentDetailsJSON;
	}
	
	@Override
	public JSONObject getFamilyDetailsByLeadId(String portalURL, String leadId)  {
		try {
			EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
			log.debug("EdelweissTokioCommonUtil >>>> getFamilyDetailsByLeadId >>>> Fetching Customer Family Details By Lead ID :  " + leadId);
			String parameterURL = getLeadMetaDataURL(leadId);
			String customerFamilyDetailsURL = portalURL + URL_FOR_CUSTOMER_FAMILY_DETAILS + FILTER_QUERY_PARAM + parameterURL;
			JSONObject customerFamilyDetailsRes = etipCoreAPI.callGetAPI(new HashMap<>(), customerFamilyDetailsURL, false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());
			
			if(Validator.isNotNull(customerFamilyDetailsRes.getJSONArray(ITEMS)) && customerFamilyDetailsRes.getJSONArray(ITEMS).length() > 0) {
				JSONObject familyDetailsResponse = customerFamilyDetailsRes.getJSONArray(ITEMS).getJSONObject(0);
				log.debug("EdelweissTokioCommonUtil >>> getFamilyDetailsByLeadId >>> Family Details Fetched Successfully : " + familyDetailsResponse);
				return familyDetailsResponse;
			} else {
				log.debug("EdelweissTokioCommonUtil >>> getFamilyDetailsByLeadId >>> No such entry found with lead ID for Family Details");
			}
		} catch(Exception e) {
			log.error("EdelweissTokioCommonUtil >>> getFamilyDetailsByLeadId >>> An error occurred while fetching Customer Family Details " + e);
		}
		return null;
	}
	
	@Override
	public CustomerInvestmentDetails updateInvestmentDetailsById(String portalURL, CustomerInvestmentDetails customerInvestmentDetails, Long customerInvestmentDetailId) throws Exception {
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
		String liferayUserName = edelweissLRBasicAuthConfiguration.getLRUsername();
		String liferayPassword = edelweissLRBasicAuthConfiguration.getLRPassword();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		String customerInvestmentDetailsReqStr = mapper.writeValueAsString(customerInvestmentDetails);
		
		log.debug("EdelweissTokioCommonUtil >>> updateCustomerEnquiryById >>> Customer InvestmentDetail Request Body : " + customerInvestmentDetailsReqStr);
		
		// Attemp to Check if Any Entry Exist in Customer Investment Details By Lead ID
		if(Validator.isNull(customerInvestmentDetailId) && Validator.isNotNull(customerInvestmentDetails.getLeadId())) {
			CustomerInvestmentDetails cusInvestmentDetails = getInvestmentDetailsLeadId(portalURL, customerInvestmentDetails.getLeadId());
			if(Validator.isNotNull(cusInvestmentDetails)) {
				customerInvestmentDetailId = cusInvestmentDetails.getId();
			}
		}
				
		JSONObject customerInvestmentDetailsReqJSON = JSONFactoryUtil.createJSONObject(customerInvestmentDetailsReqStr);
		JSONObject customerInvestmentDetailsResponseJson = null;
		if(Validator.isNotNull(customerInvestmentDetailId)) {
			log.debug("EdelweissTokioCommonUtil >>> updateInvestmentDetailsById >>> Updating Customer Investment Details By Customer Investment Detail Id  : " + customerInvestmentDetailId );
			String updateInvestmentDetailsServiceURL = portalURL + PUT_CUSTOMER_INVESTMENT_DETAILS_URL;
			updateInvestmentDetailsServiceURL = StringUtil.replace(updateInvestmentDetailsServiceURL, CUSTOMER_INVESTMENT_DETAILS_ID_PARAM, String.valueOf(customerInvestmentDetailId));
			
			customerInvestmentDetailsResponseJson = etipCoreAPI.callPutAPI(customerInvestmentDetailsReqJSON, updateInvestmentDetailsServiceURL, false, liferayUserName, liferayPassword);
			log.debug("EdelweissTokioCommonUtil >>> updateInvestmentDetailsById >>> Customer Investment Details updated : " + customerInvestmentDetailsResponseJson.toString());
		} else {
			log.debug("EdelweissTokioCommonUtil >>> updateInvestmentDetailsById >>> Adding Customer Investment Details");
			String postInvestmentDetailsURL = portalURL + CUSTOMER_INVESTMENT_DETAILS_BASE_URL;
			customerInvestmentDetailsResponseJson = etipCoreAPI.callPostAPI(customerInvestmentDetailsReqJSON, postInvestmentDetailsURL, false, liferayUserName, liferayPassword);
			log.debug("EdelweissTokioCommonUtil >>> updateInvestmentDetailsById >>> Customer Investment Details added : " + customerInvestmentDetailsResponseJson.toString());
		}
	
		CustomerInvestmentDetails customerInvestmentDetailsResponse = mapper.readValue(customerInvestmentDetailsResponseJson.toString(), CustomerInvestmentDetails.class);
		log.debug("EdelweissTokioCommonUtil >>>> updateInvestmentDetailsById >>>> Customer Investment Details updated Successfully :  " + customerInvestmentDetailsResponse.toString());
		return customerInvestmentDetailsResponse;
	}
	
	@Override
	public MultipleLoginBlock updateMultipleLoginBlock(String portalURL, MultipleLoginBlock multipleLoginBlock,
			Long multipleLoginBlockId) throws Exception {
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
		String liferayUserName = edelweissLRBasicAuthConfiguration.getLRUsername();
		String liferayPassword = edelweissLRBasicAuthConfiguration.getLRPassword();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String multipleLoginBlockReqStr = mapper.writeValueAsString(multipleLoginBlock);
		log.debug("EdelweissTokioCommonUtil >>> updateMultipleLoginBlock >>> MultipleLoginBlock Request Body : " + multipleLoginBlockReqStr);
		
		MultipleLoginBlock mulLoginBlock=getMultipleLoginByPolicyNumber(portalURL, multipleLoginBlock.getPolicyNumber());
		if(Validator.isNotNull(mulLoginBlock)) {
			multipleLoginBlockId = mulLoginBlock.getId();
		}
		JSONObject multipleLoginBlockResponseJson=null;
		JSONObject multipleLoginBlockReqJSON = JSONFactoryUtil.createJSONObject(multipleLoginBlockReqStr);
		if(Validator.isNotNull(multipleLoginBlockId)) {
			log.debug("EdelweissTokioCommonUtil >>> updateMultipleLoginBlock >>> Updating Customer Multiple Login Details By PolicyNumber  : " + multipleLoginBlockId );
			String updateMultipleLoginBlockServiceURL = portalURL + PUT_MULTIPLE_LOGINS_DETAILS_URL;
			updateMultipleLoginBlockServiceURL = StringUtil.replace(updateMultipleLoginBlockServiceURL, MULTIPLE_LOGINS_DETAILS_ID_PARAM, String.valueOf(multipleLoginBlockId));
			
			multipleLoginBlockResponseJson = etipCoreAPI.callPutAPI(multipleLoginBlockReqJSON, updateMultipleLoginBlockServiceURL, false, liferayUserName, liferayPassword);
			log.debug("EdelweissTokioCommonUtil >>> updateMultipleLoginBlock >>> MultipleLoginBlock Details updated : " + multipleLoginBlockResponseJson.toString());

		}else {
			String postMultipleLoginBlockURL = portalURL + MULTIPLE_LOGINS_BLOCK_BASE_URL;
			multipleLoginBlockResponseJson = etipCoreAPI.callPostAPI(multipleLoginBlockReqJSON, postMultipleLoginBlockURL, false, liferayUserName, liferayPassword);
			log.debug("EdelweissTokioCommonUtil >>> updateMultipleLoginBlock >>> MultipleLoginBlock Details added : " + multipleLoginBlockResponseJson.toString());
		}
		MultipleLoginBlock multipleLoginBlockResponse = mapper.readValue(multipleLoginBlockResponseJson.toString(), MultipleLoginBlock.class);
		log.debug("EdelweissTokioCommonUtil >>>> updateMultipleLoginBlock >>>> multipleLoginBlock Details updated Successfully :  " + multipleLoginBlockResponse.toString());
		
		return multipleLoginBlockResponse;
	}
	
	@Override
	public CustomerPolicyDetailsRel updatePolicyDetailsById(String portalURL, CustomerPolicyDetailsRel customerPolicyDetailsRel, Long customerPolicyDetailsId) throws Exception {
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
		String liferayUserName = edelweissLRBasicAuthConfiguration.getLRUsername();
		String liferayPassword = edelweissLRBasicAuthConfiguration.getLRPassword();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		String customerPolicyDetailsReqStr = mapper.writeValueAsString(customerPolicyDetailsRel);
		
		log.debug("EdelweissTokioCommonUtil >>> updatePolicyDetailsById >>> Customer Policy Details Request Body : " + customerPolicyDetailsReqStr);
		
		// Attemp to Check if Any Entry Exist in Customer Policy Details By Lead ID
		if(Validator.isNull(customerPolicyDetailsId) && Validator.isNotNull(customerPolicyDetailsRel.getLeadId())) {
			JSONObject policyDetailsResponse = getPolicyDetailsByLeadId(portalURL, customerPolicyDetailsRel.getLeadId());
			if(Validator.isNotNull(policyDetailsResponse)) {
				customerPolicyDetailsId = policyDetailsResponse.getLong(EdelweissObjectConstants.ID);
			}
		}
		
		JSONObject customerPolicyDetailsReqJSON = JSONFactoryUtil.createJSONObject(customerPolicyDetailsReqStr);
		JSONObject customerPolicyDetailsResponseJson = null;
		if(Validator.isNotNull(customerPolicyDetailsId)) {
			log.debug("EdelweissTokioCommonUtil >>> updatePolicyDetailsById >>> Updating Customer Policy Details By Id  : " + customerPolicyDetailsId );
			String updatePolicyDetailsServiceURL = portalURL + PUT_CUSTOMER_POLICY_DETAILS_URL;
			updatePolicyDetailsServiceURL = StringUtil.replace(updatePolicyDetailsServiceURL, CUSTOMER_POLICY_DETAILS_ID_PARAM, String.valueOf(customerPolicyDetailsId));
			
			customerPolicyDetailsResponseJson = etipCoreAPI.callPutAPI(customerPolicyDetailsReqJSON, updatePolicyDetailsServiceURL, false, liferayUserName, liferayPassword);
			log.debug("EdelweissTokioCommonUtil >>> updatePolicyDetailsById >>> Customer Customer Policy Details updated : " + customerPolicyDetailsResponseJson.toString());
		} else {
			log.debug("EdelweissTokioCommonUtil >>> updatePolicyDetailsById >>> Adding Customer Policy Details");
			String postPolicyDetailsURL = portalURL + CUSTOMER_POLICY_DETAILS_BASE_URL;
			customerPolicyDetailsResponseJson = etipCoreAPI.callPostAPI(customerPolicyDetailsReqJSON, postPolicyDetailsURL, false, liferayUserName, liferayPassword);
			log.debug("EdelweissTokioCommonUtil >>> updatePolicyDetailsById >>> Customer Policy Details added : " + customerPolicyDetailsResponseJson.toString());
		}
	
		CustomerPolicyDetailsRel customerPolicyDetailsResponse = mapper.readValue(customerPolicyDetailsResponseJson.toString(), CustomerPolicyDetailsRel.class);
		log.debug("EdelweissTokioCommonUtil >>>> updatePolicyDetailsById >>>> Customer Policy Details updated Successfully :  " + customerPolicyDetailsResponse.toString());
		return customerPolicyDetailsResponse;
	}
	
	@Override
	public JSONObject getPolicyDetailsByLeadId(String portalURL, String leadId)  {
		try {
			EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
			log.debug("EdelweissTokioCommonUtil >>>> getPolicyDetailsByLeadId >>>> Fetching Customer Policy Details By Lead ID :  " + leadId);
			String parameterURL = getLeadMetaDataURL(leadId);
			String customerPolicyDetailsURL = portalURL + CUSTOMER_POLICY_DETAILS_BASE_URL + FILTER_QUERY_PARAM + parameterURL;
			JSONObject customerPolicyDetailsRes = etipCoreAPI.callGetAPI(new HashMap<>(), customerPolicyDetailsURL, false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());
			
			if(Validator.isNotNull(customerPolicyDetailsRes.getJSONArray(ITEMS)) && customerPolicyDetailsRes.getJSONArray(ITEMS).length() > 0) {
				JSONObject policyDetailsResponse = customerPolicyDetailsRes.getJSONArray(ITEMS).getJSONObject(0);
				log.debug("EdelweissTokioCommonUtil >>> getPolicyDetailsByLeadId >>> Policy Details Fetched Successfully : " + policyDetailsResponse);
				return policyDetailsResponse;
			} else {
				log.debug("EdelweissTokioCommonUtil >>> getPolicyDetailsByLeadId >>> No such entry found with lead ID for Policy Details");
			}
		} catch(Exception e) {
			log.error("EdelweissTokioCommonUtil >>> getPolicyDetailsByLeadId >>> An error occurred while fetching Customer Policy Details " + e);
		}
		return null;
	}
	
	@Override
	public CustomerFundAllocationDetailsRel updateFundAllocationDetailsById(String portalURL, CustomerFundAllocationDetailsRel customerFundAllocationDetails, Long customerFundAllocationDetailsId) throws Exception {
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
		String liferayUserName = edelweissLRBasicAuthConfiguration.getLRUsername();
		String liferayPassword = edelweissLRBasicAuthConfiguration.getLRPassword();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		String fundAllocationDetailsStr = mapper.writeValueAsString(customerFundAllocationDetails);
		
		log.debug("EdelweissTokioCommonUtil >>> updateFundAllocationDetailsById >>> Customer Fund Allocation Details Request Body : " + fundAllocationDetailsStr);
		
		// Attemp to Check if Any Entry Exist in Customer Fund Allocation Details By Lead ID
		if(Validator.isNull(customerFundAllocationDetailsId) && Validator.isNotNull(customerFundAllocationDetails.getLeadId())) {
			CustomerFundAllocationDetailsRel customerFundAllocationDetailsRel = getFundAllocationDetailsByLeadId(portalURL, customerFundAllocationDetails.getLeadId());
			if(Validator.isNotNull(customerFundAllocationDetailsRel)) {
				customerFundAllocationDetailsId = customerFundAllocationDetailsRel.getId();
			}
		}
		
		JSONObject fundAllocationDetailsReqJSON = JSONFactoryUtil.createJSONObject(fundAllocationDetailsStr);
		JSONObject fundAllocationDetailsResJSON = null;
		if(Validator.isNotNull(customerFundAllocationDetailsId)) {
			log.debug("EdelweissTokioCommonUtil >>> updateFundAllocationDetailsById >>> Updating Customer Fund Allocation Details By Id  : " + customerFundAllocationDetailsId);
			String updateFundAllocationSeriveURL = portalURL + PUT_CUSTOMER_FUND_ALLOCATION_DETAILS_URL;
			updateFundAllocationSeriveURL = StringUtil.replace(updateFundAllocationSeriveURL, CUSTOMER_FUND_ALLOCATION_DETAILS_ID_PARAM, String.valueOf(customerFundAllocationDetailsId));
			
			fundAllocationDetailsResJSON = etipCoreAPI.callPutAPI(fundAllocationDetailsReqJSON, updateFundAllocationSeriveURL, false, liferayUserName, liferayPassword);
			log.debug("EdelweissTokioCommonUtil >>> updateFundAllocationDetailsById >>> Customer Fund Allocation Details updated : " + fundAllocationDetailsResJSON.toString());
		} else {
			log.debug("EdelweissTokioCommonUtil >>> updateFundAllocationDetailsById >>> Adding Customer Fund Allocation Detail");
			String postFundAllocationDetailsURL = portalURL + CUSTOMER_FUND_ALLOCATION_DETAILS_BASE_URL;
			fundAllocationDetailsResJSON = etipCoreAPI.callPostAPI(fundAllocationDetailsReqJSON, postFundAllocationDetailsURL, false, liferayUserName, liferayPassword);
			log.debug("EdelweissTokioCommonUtil >>> updateFundAllocationDetailsById >>> Customer Fund Allocation Details added : " + fundAllocationDetailsResJSON.toString());
		}
	
		CustomerFundAllocationDetailsRel customerFundAllocationDetailsRel = mapper.readValue(fundAllocationDetailsResJSON.toString(), CustomerFundAllocationDetailsRel.class);
		log.debug("EdelweissTokioCommonUtil >>>> updateFundAllocationDetailsById >>>> CustomerEnquiry updated Successfully :  " + customerFundAllocationDetailsRel.toString());
		return customerFundAllocationDetailsRel;
	}
	
	@Override
	public CustomerFundAllocationDetailsRel getFundAllocationDetailsByLeadId(String portalURL, String leadId)  {
		CustomerFundAllocationDetailsRel customerFundAllocationDetailsRel = null;
		try {
			EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
			
			log.debug("EdelweissTokioCommonUtil >>>> getFundAllocationDetailsByLeadId >>>> Fetching Customer Fund Allocation Details By Lead ID :  " + leadId);
			String parameterURL = getLeadMetaDataURL(leadId);
			String fundAllocationFetchSeriveURL = portalURL + CUSTOMER_FUND_ALLOCATION_DETAILS_BASE_URL + FILTER_QUERY_PARAM + parameterURL;
			JSONObject fundAllocationResJSON = etipCoreAPI.callGetAPI(new HashMap<>(), fundAllocationFetchSeriveURL, false, edelweissLRBasicAuthConfiguration.getLRUsername(),edelweissLRBasicAuthConfiguration.getLRPassword());
			
			if(Validator.isNotNull(fundAllocationResJSON) && Validator.isNotNull(fundAllocationResJSON.getJSONArray(ITEMS)) && fundAllocationResJSON.getJSONArray(ITEMS).length() > 0) {
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				
				JSONObject fundAllocationDetailsJSON = fundAllocationResJSON.getJSONArray(ITEMS).getJSONObject(0);

				customerFundAllocationDetailsRel = mapper.readValue(fundAllocationDetailsJSON.toString(), CustomerFundAllocationDetailsRel.class);
				log.debug("EdelweissTokioCommonUtil >>>> getFundAllocationDetailsByLeadId >>>> Customer Fund Allocation Details Fetched Successfully :  " + fundAllocationDetailsJSON.toString());
			} else {
				log.debug("EdelweissTokioCommonUtil >>>> getFundAllocationDetailsByLeadId >>>> No Fund Allocation Details Found By Lead Id : " + leadId);
			}
		} catch(Exception e) {
			log.error("EdelweissTokioCommonUtil >>> getFundAllocationDetailsByLeadId >>> An error occurred while fetching Customer Fund Allocation Details " + e);
		}
		return customerFundAllocationDetailsRel;
	}
	
	@Override
	public JSONObject getCAGRData() throws JSONException, ETIPSystemException  {
		EdelweissBJCagrConfiguration edelweissBJCagrConfiguration  = EdelweissConfigurationUtil.getEdelweissBJCagrConfiguration();
		
		log.debug("EdelweissTokioCommonUtil >>>> getCAGRData >>>> Customize Fund Data loading from CAGR Detail");
		String authToken = getOAuthToken(edelweissBJCagrConfiguration.getOAuth2QJURL(),
				edelweissBJCagrConfiguration.getOAuth2QJUsername(),
				edelweissBJCagrConfiguration.getOAuth2QJPassword());
		
		Map<String, String> params = new HashMap<>();
		JSONObject response = etipCoreAPI.callGetAPI(params, edelweissBJCagrConfiguration.getBJCagrURL(), true, edelweissBJCagrConfiguration.getBJCagrXAPIKEY(), authToken);
		log.debug("EdelweissTokioCommonUtil >>> getCAGRData >>> CAGR Details Response : " + response.toString());
		return response;
	}
	
	
	@Override
	public JSONObject invokeRetailParnerGenerateBI(String biRequestJsonString) throws JSONException, ETIPSystemException {
		EdelweissRPGenerateBIConfiguration edelweissRPGenerateBIConfiguration  = EdelweissConfigurationUtil.getEdelweissRPGenerateBIConfiguration();

		String authToken = getOAuthToken(edelweissRPGenerateBIConfiguration.getOAuth2QJURL(),
				edelweissRPGenerateBIConfiguration.getOAuth2QJUsername(),
				edelweissRPGenerateBIConfiguration.getOAuth2QJPassword());

		JSONObject biRequestJSONObject = JSONFactoryUtil.createJSONObject(biRequestJsonString);
		JSONObject biResponseJSONObject = etipCoreAPI.callPostAPI(biRequestJSONObject, edelweissRPGenerateBIConfiguration.getRPGenerateBIURL(), true, edelweissRPGenerateBIConfiguration.getRPGenerateBIXAPIKEY(), authToken);
		if(log.isDebugEnabled()) {
			log.debug("EdelweissTokioCommonUtil >>> invokeRetailParnerGenerateBI >>> BIResponse : " + biResponseJSONObject.toJSONString());
		}
		return biResponseJSONObject;
	}
	
	@Override
	public JSONObject invokeBuyJourneyGenerateBi(String biRequestJsonString)throws JSONException, ETIPSystemException{
		EdelweissBJGenerateBIConfiguration edelweissBJGenerateBIConfiguration = EdelweissConfigurationUtil.getEdelweissBJGenerateBIConfiguration();
		String authToken = getOAuthToken(edelweissBJGenerateBIConfiguration.getOAuth2QJURL(),
				edelweissBJGenerateBIConfiguration.getOAuth2QJUsername(),
				edelweissBJGenerateBIConfiguration.getOAuth2QJPassword());
		
		JSONObject biRequestJSONObject = JSONFactoryUtil.createJSONObject(biRequestJsonString);
		JSONObject biResponseJSONObject = etipCoreAPI.callPostAPI(biRequestJSONObject, edelweissBJGenerateBIConfiguration.getBJGenerateBIURL(), true, edelweissBJGenerateBIConfiguration.getBJGenerateBIXAPIKEY(), authToken);
		if(log.isDebugEnabled()) {
			log.debug("EdelweissTokioCommonUtil >>> invokeRetailParnerGenerateBI >>> BIResponse : " + biResponseJSONObject.toJSONString());
		}
		return biResponseJSONObject;
	}
	
	@Override
	public JSONObject invokeComboGenerateBi(String biRequestJsonString)throws JSONException, ETIPSystemException{
		
		EdelweissRPGenerateBIComboConfiguration edelweissRPGenerateBIComboConfiguration = EdelweissConfigurationUtil.getEdelweissRPGenerateBIComboConfiguration();
		String authToken = getQJComboOAuthToken(edelweissRPGenerateBIComboConfiguration.getOAuth2QJURL(), edelweissRPGenerateBIComboConfiguration.getOAuth2QJUsername(), edelweissRPGenerateBIComboConfiguration.getOAuth2QJPassword());
		
		JSONObject biRequestJSONObject = JSONFactoryUtil.createJSONObject(biRequestJsonString);
		
		log.debug("biRequestJSONObject"+biRequestJSONObject.toString());
		log.debug("authToken"+authToken);
		log.debug("api key"+edelweissRPGenerateBIComboConfiguration.getRPGenerateBIComboXAPIKEY());
		log.debug("url"+edelweissRPGenerateBIComboConfiguration.getRPGenerateBIComboURL());		
		
		JSONObject biResponseJSONObject = etipCoreAPI.callPostAPI(biRequestJSONObject, edelweissRPGenerateBIComboConfiguration.getRPGenerateBIComboURL(), true, edelweissRPGenerateBIComboConfiguration.getRPGenerateBIComboXAPIKEY(), authToken);
		if(log.isDebugEnabled()) {
			log.debug("EdelweissTokioCommonUtil >>> invokeComboGenerateBi >>> BIResponse : " + biResponseJSONObject.toJSONString());
		}
		return biResponseJSONObject;
	}
	
	@Override
	public String getQJComboOAuthToken(String url, String userName, String pwd)
			throws JSONException, ETIPSystemException {
			log.debug("EdelweissTokioCommonUtil >>> getQJComboOAuthToken >>> Generating OAuth2 New Access Token for Combo API....");
		if (isQuoteJourneyComboTokenExpired()) {
			JSONObject authorizations = JSONFactoryUtil
					.createJSONObject(etipCoreAPI.generateOauthToken(url, userName, pwd));

			long expireIn = authorizations.getLong(EdelweissCommonConstants.EXPIRE_IN);
			qjComboAuthTokenTokenExpireIn = System.currentTimeMillis() + (expireIn * 1000);
			log.debug(
					"EdelweissTokioCommonUtil >>> getQJComboOAuthToken >>> This Quote Journey Access Token will expire in Millisecond : "
							+ qjComboAuthTokenTokenExpireIn);
			qjComboAuthToken = authorizations.getString(EdelweissCommonConstants.ACCESS_TOKEN);
		}
		if(log.isDebugEnabled()) {
			log.debug("EdelweissTokioCommonUtil >>> getQJComboOAuthToken >>> Quote Journey OAuth2 Token :  " + this.qjComboAuthToken);
			log.debug("EdelweissTokioCommonUtil >>> getQJComboOAuthToken >>> Current Time in Millisecond : " + System.currentTimeMillis());
			log.debug("EdelweissTokioCommonUtil >>> getQJComboOAuthToken >>> Token Expiration Time in Millisecond : " + this.qjComboAuthTokenTokenExpireIn);
		}
		return this.qjComboAuthToken;
	}
	
	@Override
	public String getQuoteJourneyOAuthToken() throws JSONException, ETIPSystemException {
		if(isQuoteJourneyTokenExpired()) {
			log.debug("EdelweissTokioCommonUtil >>> getQuoteJourneyOAuthToken >>> Generating OAuth2 New Access Token for Quote Journey....");
			EdelweissOauth2GenerateTokenConfiguration edelweissOauth2GenerateTokenConfiguration  = EdelweissConfigurationUtil.getEdelweissOauth2GenerateTokenConfiguration();
			JSONObject authorizations = JSONFactoryUtil.createJSONObject(etipCoreAPI.generateOauthToken(edelweissOauth2GenerateTokenConfiguration.getOAuth2QJURL(), edelweissOauth2GenerateTokenConfiguration.getOAuth2QJUsername(), edelweissOauth2GenerateTokenConfiguration.getOAuth2QJPassword()));
			
			long expireIn = authorizations.getLong(EdelweissCommonConstants.EXPIRE_IN);
			quoteJourneyTokenExpireIn = System.currentTimeMillis() + (expireIn * 1000); 
			log.debug("EdelweissTokioCommonUtil >>> getQuoteJourneyOAuthToken >>> This Quote Journey Access Token will expire in Millisecond : " + quoteJourneyTokenExpireIn);
			quoteJourneyAuthToken = authorizations.getString(EdelweissCommonConstants.ACCESS_TOKEN);
		} 
		if(log.isDebugEnabled()) {
			log.debug("EdelweissTokioCommonUtil >>> getQuoteJourneyOAuthToken >>> Quote Journey OAuth2 Token :  " + this.quoteJourneyAuthToken);
			log.debug("EdelweissTokioCommonUtil >>> getQuoteJourneyOAuthToken >>> Current Time in Millisecond : " + System.currentTimeMillis());
			log.debug("EdelweissTokioCommonUtil >>> getQuoteJourneyOAuthToken >>> Token Expiration Time in Millisecond : " + this.quoteJourneyTokenExpireIn);
		}
		return this.quoteJourneyAuthToken;
	}
	
	@Override
	public String getOAuthToken(String url, String username, String password) throws JSONException, ETIPSystemException {
		
		if(Validator.isNull(url) || Validator.isNull(username) || Validator.isNull(password)) {
			return StringPool.BLANK;
		}
		
		JSONObject authorizations = JSONFactoryUtil.createJSONObject(etipCoreAPI.generateOauthToken(url, username, password));
		if(Validator.isNotNull(authorizations)) {
			return authorizations.getString(EdelweissCommonConstants.ACCESS_TOKEN);
		}
		log.error("Unable to generate toke. URL: "+url+", useranme: "+username);
		return StringPool.BLANK;
	}
	
	/**
	 * Checking Quote Journey OAuth2 Token is expired or not

	 * @return true is token is expired
	 */
	private boolean isQuoteJourneyTokenExpired() {
		if (this.quoteJourneyAuthToken == null || (System.currentTimeMillis() + TOKEN_REPLACEMENT_IN_MILLISECONDS) > this.quoteJourneyTokenExpireIn) {
			log.debug("EdelweissTokioCommonUtil >>> isQuoteJourneyTokenExpired >>> Quote Journey OAuth2 Token is expired......");
			return true;
		}  else {
			log.debug("EdelweissTokioCommonUtil >>> isQuoteJourneyTokenExpired >>> Quote Journey OAuth2 Token is already exist.....");
			return false;
		}
	}
	
	/**
	 * Checking Quote Journey Combo OAuth2 Token is expired or not

	 * @return true is token is expired
	 */
	private boolean isQuoteJourneyComboTokenExpired() {
		if (this.qjComboAuthToken == null || (System.currentTimeMillis() + TOKEN_REPLACEMENT_IN_MILLISECONDS) > this.qjComboAuthTokenTokenExpireIn) {
			log.debug("EdelweissTokioCommonUtil >>> isQuoteJourneyComboTokenExpired >>> Quote Journey OAuth2 Token is expired......");
			return true;
		}  else {
			log.debug("EdelweissTokioCommonUtil >>> isQuoteJourneyComboTokenExpired >>> Quote Journey OAuth2 Token is already exist.....");
			return false;
		}
	}
	
	// restart journey code 
		@Override
		public JSONObject getRestartJourneyData(Map<String, String> restartJourneyDataMap){
			EdelweissBJRestartJourneyConfiguration edelweissBJRestartJourneyConfiguration = EdelweissConfigurationUtil.getEdelweissBJRestartJourneyConfiguration();
			JSONObject restartJourneyResponse = JSONFactoryUtil.createJSONObject();
			try {
				String authorization = getOAuthToken(edelweissBJRestartJourneyConfiguration.getOAuth2QJURL(),
						edelweissBJRestartJourneyConfiguration.getOAuth2QJUsername(),
						edelweissBJRestartJourneyConfiguration.getOAuth2QJPassword());
				String restartJourneyBody = getRestartJouneryRequestBody(restartJourneyDataMap); 
				log.debug(">>> restartJourneyBody is :" + restartJourneyBody);
				restartJourneyResponse = etipCoreAPI.callPostAPI(JSONFactoryUtil.createJSONObject(restartJourneyBody), edelweissBJRestartJourneyConfiguration.getBJRestartJourneyURL(), true, edelweissBJRestartJourneyConfiguration.getBJRestartJourneyXAPIKEY(), authorization);
				log.debug("EdelweissTokioCommonUtil >>>  getRestartJourneyData >>> responseJSON ::: " + restartJourneyResponse);
			}   catch (JSONException | ETIPSystemException e) {
				log.error("EdelweissTokioCommonUtil >>>  getRestartJourneyData >>> An exception occurred while calling restart journey :::  " + e);
			} catch (JsonProcessingException e) {
				log.error("EdelweissTokioCommonUtil >>>  getRestartJourneyData >>> An exception occurred while processing JSON :::  " + e);
			}
			return restartJourneyResponse;
		}
		/**
		 * @author naitik.datta
		 * @param restartJourneyMap
		 * @return requestbody for restart journey API call
		 * @throws JsonProcessingException
		 */
		private String getRestartJouneryRequestBody(Map<String, String> restartJourneyMap) throws JsonProcessingException {
			ObjectMapper objectMapper= new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			 
		
			 RestartJourneyRequest restartJourneyRequest = new RestartJourneyRequest();
			QuoteData quoteData = new QuoteData();
			quoteData.setResumeUrl(restartJourneyMap.get(RESUME_URL));
			objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			restartJourneyRequest.setSource(SOURCE);
			restartJourneyRequest.setScreen(SCREEN);
			restartJourneyRequest.setEmailAddress(restartJourneyMap.get(EMAIL));
			restartJourneyRequest.setPhoneNumber(restartJourneyMap.get(MOBILE_NUMBER));
			restartJourneyRequest.setProductCode(restartJourneyMap.get(PRODUCT_CODE));
			restartJourneyRequest.setProductName(restartJourneyMap.get(PRODUCT_NAME_QUOTE));
			restartJourneyRequest.setQuoteDataStr(objectMapper.writeValueAsString(quoteData));
			return objectMapper.writeValueAsString(restartJourneyRequest);
			
		}
		public JSONObject updateCustomerDropoutDetails(String portalUrl ,Map<String,String> restartJourneyMap, String customerDropoutId) throws ETIPSystemException {
			EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
			String customerDropoutDetailsPutURL = portalUrl + CUSTOMER_DROPOUT_DETAILS_PUT_URL;
			customerDropoutDetailsPutURL = StringUtil.replace(customerDropoutDetailsPutURL, "{customerDropOutId}", customerDropoutId);
		    JSONObject liferayCustomerDropoutJSON = JSONFactoryUtil.createJSONObject(restartJourneyMap);
			JSONObject lrCustomerDropoutResponse = etipCoreAPI.callPutAPI(liferayCustomerDropoutJSON, customerDropoutDetailsPutURL, false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());
		    log.debug(" EdelweissTokioCommonUtil >>>> updateCustomerDropoutDetails >>>>>Customer drop out response " + lrCustomerDropoutResponse);
			return lrCustomerDropoutResponse;
			
		}
		
		@Override
		public void renderBasicDetails(PortletRequest portletRequest) {
			
			EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
			String liferayUserName = edelweissLRBasicAuthConfiguration.getLRUsername();
			String liferayPassword = edelweissLRBasicAuthConfiguration.getLRPassword();
			ThemeDisplay themeDisplay = (ThemeDisplay) portletRequest.getAttribute(WebKeys.THEME_DISPLAY);
			try {
			
				String cookieLeadId = getLeadIdFromCookie(portletRequest, LEAD_ID);
				if(Validator.isNotNull(cookieLeadId)) {

					String parameterURL = getLeadMetaDataURL(cookieLeadId);
					
					String urlToGetBasicDetails = themeDisplay.getPortalURL()+ LIFERAY_PRODUCT_ENQUIRY_URL + FILTER_QUERY_PARAM +parameterURL;
					
					log.debug("EdelweissTokioCommonUtil >>>> url to get basic details >>>>> "+urlToGetBasicDetails);
					
					JSONObject basicDetailsObject = etipCoreAPI.callGetAPI(new  HashMap<>(), urlToGetBasicDetails, false, liferayUserName, liferayPassword);
					
					
					if(Validator.isNull(basicDetailsObject) || Validator.isNull(basicDetailsObject.getJSONArray(ITEMS)) || basicDetailsObject.getJSONArray(ITEMS).length()<=-0 )
					{
						log.warn("Customer Basic Details no found: "+cookieLeadId);
						return;
					}

					basicDetailsObject = basicDetailsObject.getJSONArray(ITEMS).getJSONObject(0);
					log.debug("EdelweissTokioCommonUtil >>>> json response >>>>> "+basicDetailsObject);
					
					// Nested Code Starts
					String customerEnquiryAPIURL = themeDisplay.getPortalURL() + MessageFormat.format(EdelweissObjectConstants.GET_CUSTOMER_ENQUIRY_RELATIONAL_API + EdelweissObjectConstants.CUSTOMER_ENQUIRY_FAMILY_POLICY_FUND_REL_PARAM, basicDetailsObject.getString("id"));
					log.debug("EdelweissTokioCommonUtil >>> locationURL ::: " + customerEnquiryAPIURL);
					
					JSONObject apiResponseObject = etipCoreAPI.callGetAPI(new HashMap<>(), customerEnquiryAPIURL, false, liferayUserName, liferayPassword);
					
					ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
					ApplicationSummaryData basicDetailsData =  mapper.readValue(apiResponseObject.toJSONString(), ApplicationSummaryData.class);
					
					portletRequest.setAttribute("summaryData", basicDetailsData);
					log.debug("EdelweissTokioCommonUtil >>> Basic Details ::: "+basicDetailsData);
					
					Map<String, String> basicDetailsMap = new HashMap<>();
					basicDetailsMap.put(FULL_NAME, basicDetailsObject.getString(FULL_NAME));
					basicDetailsMap.put(DATE_OF_BIRTH, basicDetailsObject.getString(DATE_OF_BIRTH));
					basicDetailsMap.put(MOBILE_NUMBER, basicDetailsObject.getString(MOBILE_NUMBER));
					basicDetailsMap.put(INDIA_STD, "+91 ");
					basicDetailsMap.put(GENDER, basicDetailsObject.getString(GENDER));
					basicDetailsMap.put(EMAIL, basicDetailsObject.getString(EMAIL));
					basicDetailsMap.put(INVESTMENT_OBJECTIVE, basicDetailsObject.getString(INVESTMENT_OBJECTIVE));
					basicDetailsMap.put(EdelweissCommonConstants.BASIC_INVESTING_FOR, EdelweissObjectConstants.MYSELF);
					basicDetailsMap.put(EdelweissObjectConstants.ISNRI, basicDetailsObject.getString(EdelweissObjectConstants.ISNRI));
					basicDetailsMap.put(EdelweissObjectConstants.NRI_MOBILE_NUMBER, basicDetailsObject.getString(EdelweissObjectConstants.NRI_MOBILE_NUMBER));
					basicDetailsMap.put("age", calculateAge(basicDetailsObject.getString(DATE_OF_BIRTH)));
					basicDetailsMap.put(ANNUAL_INCOME, basicDetailsObject.getString(ANNUAL_INCOME));
					basicDetailsMap.put(PINCODE, basicDetailsObject.getString(PINCODE));
					basicDetailsMap.put("smokerDetails", showSmoke(basicDetailsObject.getString(SMOKER)));
					basicDetailsMap.put("genderValue", showGender(basicDetailsObject.getString(GENDER)));
					basicDetailsMap.put(OCCUPATION, basicDetailsObject.getString(OCCUPATION));
					basicDetailsMap.put(EDUCATION_QUALIFICATION, basicDetailsObject.getString(EDUCATION_QUALIFICATION));
					basicDetailsMap.put(NATURE_OF_DUTY, basicDetailsObject.getString(NATURE_OF_DUTY));
					basicDetailsMap.put(SPOUSE, basicDetailsObject.getString(SPOUSE));
					basicDetailsMap.put(SPOUSE_OCCUPATION, basicDetailsObject.getString(SPOUSE_OCCUPATION));
					basicDetailsMap.put(SPOUSE_SUMASSURED, basicDetailsObject.getString(SPOUSE_SUMASSURED));
					basicDetailsMap.put(SMOKER, basicDetailsObject.getString(SMOKER));
					basicDetailsMap.put(MARITAL_STATUS_IS, basicDetailsObject.getString(MARITAL_STATUS_IS));
					basicDetailsMap.put(SPOUSE_NAME, basicDetailsObject.getString(SPOUSE_NAME));
					basicDetailsMap.put(SPOUSE_AGE, basicDetailsObject.getString(SPOUSE_AGE));
					basicDetailsMap.put(SPOUSE_SMOKE, basicDetailsObject.getString(SPOUSE_SMOKE));
					basicDetailsMap.put(CHILD_DOB, basicDetailsObject.getString(CHILD_DOB));
					renderBasicFamilyDetails(portletRequest, basicDetailsData, basicDetailsMap);
					
					Map<String, String> investmentObjectiveMap = getPicklistByExternalReferenceCode(INVESTMENT_OBJECTIVE, themeDisplay.getCompanyId());
					portletRequest.setAttribute("picklistOptionValues", investmentObjectiveMap);
					portletRequest.setAttribute("basicDetailsMap", basicDetailsMap);
					portletRequest.setAttribute(EdelweissCommonConstants.CUSTOMER_ENQUIRY_ID, basicDetailsObject.getString(ID));
					
					portletRequest.setAttribute("cookieLeadId", cookieLeadId);
					
					//Check Partner
					if(GetterUtil.getBoolean(basicDetailsObject.getBoolean("isPartner"), false)) {
						portletRequest.setAttribute(PARTNER_COOKIE, true);
					}
				}
			}catch(ETIPSystemException e) {
				log.error("EdelweissProductCustomizeRenderCommand >>> render invoked >>> An error occurred getting the customer details ::: " + e);
			} catch (JsonMappingException e) {
				log.error("EdelweissProductCustomizeRenderCommand >>> render invoked >>> An error occurred mapping the JSON to Object Mapper ::: " + e);
			} catch (JsonProcessingException e) {
				log.error("EdelweissProductCustomizeRenderCommand >>> render invoked >>> An error occurred processing the JSON to Object Mapper ::: " + e);
			}
			
		}
		
		private String calculateAge(String dobString) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					LocalDate dob = LocalDate.parse(dobString,formatter); 
					LocalDate curDate = LocalDate.now();
					return Integer.toString(Period.between(dob, curDate).getYears());
				}
		// method for saral jeevan bima
		private String showSmoke(String smoker) {
			if(smoker.equals("1")) {
				return "Smoker";
			}
			else {
				return "Non-Smoker";

			}
		}
		// method for saral jeevan bima
		private String showGender(String gender) {
			if(gender.equals("3")) {
				return "Male";
			}
			else if(gender.equals("4")) {
				return "Female";
			}
			else {
				return "Transgender";
			}
		}
		/**
		 * {@link #renderBasicDetails(PortletRequest)}
		 * @param portletRequest
		 * @param basicDetailsData
		 * @param basicDetailsMap
		 */
		private void renderBasicFamilyDetails(PortletRequest portletRequest, ApplicationSummaryData basicDetailsData,
				Map<String, String> basicDetailsMap) {
			if (Validator.isNotNull(basicDetailsData)
					&& Validator.isNotNull(basicDetailsData.getCustomerFamilyDetailsRel())
					&& !basicDetailsData.getCustomerFamilyDetailsRel().isEmpty()) {
				CustomerFamilyDetailsRel customerFamilyDetailsRel = basicDetailsData.getCustomerFamilyDetailsRel().get(0);
				
				basicDetailsMap.put(ASSURED_FULL_NAME, customerFamilyDetailsRel.getAssuranceFullName());
				basicDetailsMap.put(ASSURED_DATE_OF_BIRTH, customerFamilyDetailsRel.getAssuranceDob());
				basicDetailsMap.put(EdelweissCommonConstants.IS_LITTLE_CHAMP_OPTED, customerFamilyDetailsRel.getIsLittleChampBenefitOpted());
				basicDetailsMap.put(EdelweissCommonConstants.IS_RISING_STAR_OPTED, String.valueOf(customerFamilyDetailsRel.getIsRisingStarBenefitOpted()));
				basicDetailsMap.put(ASSURED_RELATION, customerFamilyDetailsRel.getAssuredRelation());
				portletRequest.setAttribute("customerFamilyDetailsRel", customerFamilyDetailsRel);
				
				portletRequest.setAttribute(EdelweissCommonConstants.CUSTOMER_FAMILY_DETAILS_ID, customerFamilyDetailsRel.getId());
			}
			
			if(Validator.isNotNull(basicDetailsData.getCustomerInvestmentDetailsRel()) && !basicDetailsData.getCustomerInvestmentDetailsRel().isEmpty()) {
				CustomerInvestmentDetailsRel customerPolicyDetailsRel = basicDetailsData.getCustomerInvestmentDetailsRel().get(0);
				basicDetailsMap.put(EdelweissCommonConstants.BASIC_INVESTING_FOR, customerPolicyDetailsRel.getInvestingFor());
			}
			
			if(Validator.isNotNull(basicDetailsData.getCustomerPolicyDetailsRel()) && !basicDetailsData.getCustomerPolicyDetailsRel().isEmpty()) {
				CustomerPolicyDetailsRel customerPolicyDetailsRel = basicDetailsData.getCustomerPolicyDetailsRel().get(0);
				portletRequest.setAttribute(EdelweissCommonConstants.CUSTOMER_POLICY_DETAILS_ID, customerPolicyDetailsRel.getId());
			}
			
			if(Validator.isNotNull(basicDetailsData.getCustomerFundAllocationDetailsRel()) && !basicDetailsData.getCustomerFundAllocationDetailsRel().isEmpty()) {
				CustomerFundAllocationDetailsRel customerFundAllocationDetails = basicDetailsData.getCustomerFundAllocationDetailsRel().get(0);
				portletRequest.setAttribute(EdelweissCommonConstants.CUSTOMER_FUND_ALLOCATION_DETAILS_ID, customerFundAllocationDetails.getId());
			}
		}
		

	@Override
	public String getMetaDataURLByParam(String fieldName, String fieldValue) {
		StringBuilder leadURL = new StringBuilder();
		leadURL.append(fieldName);
		leadURL.append(StringPool.SPACE);
		leadURL.append("eq");
		leadURL.append(StringPool.SPACE);
		leadURL.append("'" + fieldValue + "'");
		return URLEncoder.encode(leadURL.toString(), StandardCharsets.UTF_8).replace(" ", "%20");
	}
		
	/**
	 * Fetching Product Master Details By Product Code
	 * 
	 */
	@Override
	public JSONObject getProductMasterByProductCode(String portalURL, String productCode) {
		try {
			EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
			log.debug("EdelweissTokioCommonUtil >>>> getProductMasterByProductCode >>>> Fetching Product Master Detail by Product Code :  " + productCode);
			String parameterURL = getMetaDataURLByParam(PRODUCT_ID, productCode);
			String productMasterServiceURL = portalURL + EdelweissObjectConstants.PRODUCT_MASTER_BASE_URL + FILTER_QUERY_PARAM + parameterURL;
			JSONObject productMasterResponse = etipCoreAPI.callGetAPI(new HashMap<>(), productMasterServiceURL, false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());
			
			if(Validator.isNotNull(productMasterResponse.getJSONArray(ITEMS)) && productMasterResponse.getJSONArray(ITEMS).length() > 0) {
				JSONObject productMasterData = productMasterResponse.getJSONArray(ITEMS).getJSONObject(0);
				log.debug("EdelweissTokioCommonUtil >>> getProductMasterByProductCode >>> Product Master Fetched Successfully : " + productMasterData);
				return productMasterData;
			} else {
				log.debug("EdelweissTokioCommonUtil >>> getProductMasterByProductCode >>> No such entry found with Product Code : " + productCode);
			}
		} catch(Exception e) {
			log.error("EdelweissTokioCommonUtil >>> getProductMasterByProductCode >>> An error occurred while fetching Product Master Details " + e);
		}
		return null;
	}

	@Override
	public LiferayResponseMessage getProductMasterBeanByProductCode(String portalURL, String productCode) {
		try {
			JSONObject productMasterResponseJSON = getProductMasterByProductCode(portalURL, productCode);
			
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			LiferayResponseMessage liferayResponseMessage = objectMapper.readValue(productMasterResponseJSON.toString(), LiferayResponseMessage.class);
			log.debug("EdelweissTokioCommonUtil >>> getProductMasterBeanByProductCode >>> Product Master Relation Response " + liferayResponseMessage.toString());
			return liferayResponseMessage;
		} catch(Exception e) {
			log.error("EdelweissTokioCommonUtil >>> getProductMasterBeanByProductCode >>> An error occurred while fetching Product Master Details " + e);
		}
		return null;
	}
	
	@Override
	public CustomerFamilyDetailsRel getRequestCustomerFamilyDetails(Map<String, String> requestParametersMap, String leadId) {
		CustomerFamilyDetailsRel customerFamilyDetails = new CustomerFamilyDetailsRel();
		customerFamilyDetails.setAssuranceFullName(requestParametersMap.get(EdelweissCommonConstants.ASSURED_FULL_NAME));
		customerFamilyDetails.setAssuranceDob(requestParametersMap.get(EdelweissCommonConstants.ASSURED_DATE_OF_BIRTH));
		customerFamilyDetails.setAssuredRelation(requestParametersMap.get(EdelweissCommonConstants.ASSURED_RELATION));
		customerFamilyDetails.setSpouseName(requestParametersMap.get(EdelweissObjectConstants.FAMILY_SPOUSE_NAME));
		customerFamilyDetails.setSpouseDateOfBirth(requestParametersMap.get(EdelweissObjectConstants.FAMILY_SPOUSE_DOB));
		customerFamilyDetails.setSpouseSmoke(requestParametersMap.get(EdelweissObjectConstants.FAMILY_SPOUSE_SMOKE));
		customerFamilyDetails.setChildDateOfBirth(requestParametersMap.get(EdelweissObjectConstants.FAMILY_CHILD_DOB));
		customerFamilyDetails.setLeadId(leadId);
		customerFamilyDetails.setrCustomerFamilyDetailsRelCCustomerEnquiryId(Long.valueOf(requestParametersMap.get(EdelweissCommonConstants.CUSTOMER_ENQUIRY_ID)));
		boolean removeLittleChamp = GetterUtil.getBoolean(requestParametersMap.get(EdelweissCommonConstants.REMOVE_LITTLE_CHAMP), false);
		
		if(Validator.isNotNull(removeLittleChamp) && removeLittleChamp) {
			customerFamilyDetails.setIsLittleChampBenefitOpted(EdelweissCommonConstants.NO_IPS);
		}
		
		boolean removeRisingStar = GetterUtil.getBoolean(requestParametersMap.get(EdelweissCommonConstants.REMOVE_RISING_STAR), false);
		
		if(Validator.isNotNull(removeRisingStar) && removeRisingStar) {
			customerFamilyDetails.setIsRisingStarBenefitOpted(EdelweissCommonConstants.NO_IPS);
		}
		
		if(Validator.isNotNull(requestParametersMap.get(EdelweissObjectConstants.MARITAL_STATUS_IS))) {
			customerFamilyDetails.setSpouseName(requestParametersMap.get(EdelweissObjectConstants.SPOUSE_NAME));
			customerFamilyDetails.setSpouseDateOfBirth(requestParametersMap.get(EdelweissObjectConstants.SPOUSE_DOB));
			customerFamilyDetails.setSpouseSmoke(requestParametersMap.get(EdelweissObjectConstants.SPOUSE_SMOKE));
		}else {
			customerFamilyDetails.setSpouseName(StringPool.BLANK);
			customerFamilyDetails.setSpouseDateOfBirth(StringPool.BLANK);
			customerFamilyDetails.setSpouseSmoke(StringPool.BLANK);
		}
		
		if(Validator.isNotNull(requestParametersMap.get(EdelweissObjectConstants.CHILD_NAME))) {
			customerFamilyDetails.setChildDateOfBirth(requestParametersMap.get(EdelweissObjectConstants.CHILD_DOB));
		}else {
			customerFamilyDetails.setChildDateOfBirth(StringPool.BLANK);
		}

		customerFamilyDetails.setOccupation(requestParametersMap.getOrDefault(EdelweissObjectConstants.FAMILY_OCCUPATION, StringPool.BLANK));
		customerFamilyDetails.setTotalSumAssured(requestParametersMap.getOrDefault(EdelweissObjectConstants.FAMILY_TOTAL_SUM_ASSURED, StringPool.BLANK));
		
		log.debug("EdelweissProductCustomizeHelper >>> getRequestCustomerFamilyDetails >>> Customer Family Details ::: " + customerFamilyDetails.toString());
		return customerFamilyDetails;
	}
	
	/**
	 * Get Customer Policy Details Request Bean for Saving to Liferay Object
	 * 
	 * @param requestParametersMap
	 * @param leadId
	 * @return customerPolicyDetailsRel
	 */
	@Override
	public CustomerPolicyDetailsRel getRequestPolicyDetails(Map<String, String> requestParametersMap, String leadId) {
		CustomerPolicyDetailsRel customerPolicyDetailsRel = new CustomerPolicyDetailsRel();
		customerPolicyDetailsRel.setrCustomerPolicyDetailsRelCCustomerEnquiryId(Long.valueOf(requestParametersMap.getOrDefault(EdelweissCommonConstants.CUSTOMER_ENQUIRY_ID,"0")));
		customerPolicyDetailsRel.setIllustationURL(requestParametersMap.get(EdelweissCommonConstants.ILLUSTRATION_URL));
		if(Validator.isNotNull(requestParametersMap.get(EdelweissObjectConstants.QUOTATION_ID))) {
			customerPolicyDetailsRel.setQuotationId(requestParametersMap.get(EdelweissObjectConstants.QUOTATION_ID));
		}
		customerPolicyDetailsRel.setLeadId(leadId);
		
		log.debug("EdelweissProductCustomizeHelper >>> getRequestPolicyDetails >>> Customer Policy Details ::: " + customerPolicyDetailsRel.toString());
		return customerPolicyDetailsRel;
	}
	
	/**
	 * Product List Service
	 */
	@Override
	public JSONObject invokeProductList(String productListRequestStr) throws JSONException, ETIPSystemException {
		EdelweissBJProductListConfiguration edelweissBJProductListConfiguration  = EdelweissConfigurationUtil.getEdelweissBJProductListConfiguration();

		EdelweissBJProductListConfiguration productListConfiguration  = EdelweissConfigurationUtil.getEdelweissBJProductListConfiguration();

		String authToken = getOAuthToken(productListConfiguration.getOAuth2QJURL(),
				productListConfiguration.getOAuth2QJUsername(),
				productListConfiguration.getOAuth2QJPassword());

		JSONObject productListRequestJSONObject = JSONFactoryUtil.createJSONObject(productListRequestStr);
		JSONObject productListResponseJSONObject = etipCoreAPI.callPostAPI(productListRequestJSONObject, edelweissBJProductListConfiguration.getBJProductListURL(), true, edelweissBJProductListConfiguration.getBJProductListXAPIKEY(), authToken);
		if(log.isDebugEnabled()) {
			log.debug("EdelweissTokioCommonUtil >>> invokeProductList >>> Product List Response : " + productListResponseJSONObject.toJSONString());
		}
		return productListResponseJSONObject;
	}
	
	/**
	 * Product Modification Service
	 */
	@Override
	public JSONObject invokeProductModification(String productModificationRequestStr) throws JSONException, ETIPSystemException {
		EdelweissBJProductModificationConfiguration edelweissBJProductModificationConfiguration  = EdelweissConfigurationUtil.getEdelweissBJProductModificationConfiguration();

		String authToken = getOAuthToken(edelweissBJProductModificationConfiguration.getOAuth2QJURL(),
				edelweissBJProductModificationConfiguration.getOAuth2QJUsername(),
				edelweissBJProductModificationConfiguration.getOAuth2QJPassword());

		JSONObject productModificationRequestJSONObject = JSONFactoryUtil.createJSONObject(productModificationRequestStr);
		JSONObject productModificationResponseJSONObject = etipCoreAPI.callPostAPI(productModificationRequestJSONObject, edelweissBJProductModificationConfiguration.getBJProductModificationURL(), true, edelweissBJProductModificationConfiguration.getBJProductModificationXAPIKEY(), authToken);
		if(log.isDebugEnabled()) {
			log.debug("EdelweissTokioCommonUtil >>> invokeProductModification >>> Product Modification Response : " + productModificationResponseJSONObject.toJSONString());
		}
		return productModificationResponseJSONObject;
	}
	
	
	@Override
	public CustomizeFeaturesCard getCustomizeFeatureCardContentByURLTitle(long grpupId, String articleURLTitle) {
		CustomizeFeaturesCard customizeFeaturesCard = new CustomizeFeaturesCard();
		try {
			Locale locale = Locale.getDefault();
			JournalArticle journalArticle = journalArticleLocalService.getArticleByUrlTitle(grpupId, articleURLTitle);
			JSONArray cfcNestedJSONArray = JSONFactoryUtil.createJSONArray();
			JSONObject customizeCardsJSONObj = JSONFactoryUtil.createJSONObject();
			
			if(Validator.isNotNull(journalArticle)) {
				getStructureFieldAndValues(locale, journalArticle, cfcNestedJSONArray, customizeCardsJSONObj);
				
				if(cfcNestedJSONArray.length() > 0) {
					customizeCardsJSONObj.put(EdelweissCommonConstants.CFC_NESTED_DATA, cfcNestedJSONArray);
				}
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
				customizeFeaturesCard = objectMapper.readValue(customizeCardsJSONObj.toString(), CustomizeFeaturesCard.class);
				log.debug("EdelweissTokioCommonUtil >>> getCustomizeFeatureCardContentByURLTitle >>> Customize Features Card ::: " + customizeFeaturesCard);
			}
			
		} catch(Exception e) {
			log.error("EdelweissTokioCommonUtil >>> getCustomizeFeatureCardContentByURLTitle >>> Error Occurred while fetching Customize Feature Content : " + e);
		}
		return customizeFeaturesCard;
	}


	/**
	 * Get Structure Field and Values and update in JSON Object
	 * @param locale
	 * @param journalArticle
	 * @param cfcNestedJSONArray
	 * @param customizeCardsJSONObj
	 * @throws JSONException
	 */
	private void getStructureFieldAndValues(Locale locale, JournalArticle journalArticle, JSONArray cfcNestedJSONArray,
			JSONObject customizeCardsJSONObj) throws JSONException {
		DDMFormValues ddmFormValues = journalArticle.getDDMFormValues();
		for (DDMFormFieldValue ddmFormFieldValue : ddmFormValues.getDDMFormFieldValues()) {
			JSONObject nestedCustomizeCardJSONObj = JSONFactoryUtil.createJSONObject();
			if(!ddmFormFieldValue.getNestedDDMFormFieldValues().isEmpty()) {
				for (DDMFormFieldValue nestedDDMFormFieldValue : ddmFormFieldValue.getNestedDDMFormFieldValues()) {
					if(nestedDDMFormFieldValue.getFieldReference().equalsIgnoreCase(EdelweissCommonConstants.IMAGE_FIELD)) {
						nestedCustomizeCardJSONObj.put(nestedDDMFormFieldValue.getFieldReference(), JSONFactoryUtil.createJSONObject(nestedDDMFormFieldValue.getValue().getString(locale)));
					} else {
						nestedCustomizeCardJSONObj.put(nestedDDMFormFieldValue.getFieldReference(), nestedDDMFormFieldValue.getValue().getString(locale));
					}
				}
				cfcNestedJSONArray.put(nestedCustomizeCardJSONObj);
			} else {
				customizeCardsJSONObj.put(ddmFormFieldValue.getFieldReference(), ddmFormFieldValue.getValue().getString(locale));
			}
		}
	}
	
	@Override
	public CustomerEnquiry updateCustomerEnquiryLMSResponse(String portalURL, JSONObject leadUpdateResponse, String customerEnquiryId) {
		try {
			if(Validator.isNotNull(customerEnquiryId) && Validator.isNotNull(leadUpdateResponse) && leadUpdateResponse.getBoolean(EdelweissObjectConstants.STATUS)) {
				log.debug("EdelweissTokioCommonUtil >>> updateCustomerEnquiryLMSResponse >>> Lead Response updating to Customer Enquiry");
				JSONObject responseData = leadUpdateResponse.getJSONObject(RESPONSE_DATA);
				CustomerEnquiry customerEnquiryRequest = new CustomerEnquiry();
				customerEnquiryRequest.setLmsQuoteId(responseData.getString(LMS_QUOTE_JOURNEY_ID));
				customerEnquiryRequest.setLmsinterestid(responseData.getString(LMS_QUOTE_INTEREST_ID));
				customerEnquiryRequest.setLeadStatus(responseData.getString(EdelweissCommonConstants.LEAD_STATUS_RES_PARAM));
				
				try {
					if(leadUpdateResponse.has("isZP") && leadUpdateResponse.getBoolean("isZP")) {
						customerEnquiryRequest.setAnnualIncome(leadUpdateResponse.getString("zp_" + EdelweissObjectConstants.INCOME));
						customerEnquiryRequest.setEducationQualification(leadUpdateResponse.getString("zp_" + EdelweissObjectConstants.EDUCATION_QUALIFICATION));
						customerEnquiryRequest.setOccupation(leadUpdateResponse.getString("zp_" + EdelweissObjectConstants.OCCUPATION));
					}
				} catch (Exception e) {
                      log.error(" Error while updating Customer enquire for ZP >>>>>>>>>>>>>>>",e);
				}
				
				return updateCustomerEnquiryById(portalURL, customerEnquiryRequest, GetterUtil.getLong(customerEnquiryId));
			}
		} catch(Exception e) {
			log.error("EdelweissTokioCommonUtil >>> updateCustomerEnquiryLMSResponse >>> Error while updating LMS Response Data to Customer Enquiry : " + e);
		}
		return null;
	}
	
	private String quoteJourneyAuthToken = null; 
	private long quoteJourneyTokenExpireIn;
	private static final long TOKEN_REPLACEMENT_IN_MILLISECONDS = (long)10 * 60 * 1000; // Token Replacement before Token expiration time - 10 Minute
	
	private String qjComboAuthToken = null; 
	private long qjComboAuthTokenTokenExpireIn;

	@Override
	public JSONObject validate(PortletRequest portletRequest) {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		
		String name = ParamUtil.getString(portletRequest, EdelweissObjectConstants.FULL_NAME);
		String gender = ParamUtil.getString(portletRequest, EdelweissObjectConstants.GENDER);
		String dob = ParamUtil.getString(portletRequest, EdelweissObjectConstants.DATE_OF_BIRTH);
		String mobileNumber = ParamUtil.getString(portletRequest, EdelweissObjectConstants.MOBILE_NUMBER);
		String email = ParamUtil.getString(portletRequest, EdelweissObjectConstants.EMAIL);
		String investmentObjective = ParamUtil.getString(portletRequest, EdelweissObjectConstants.INVESTMENT_OBJECTIVE);
		String annualIncome = ParamUtil.getString(portletRequest, EdelweissObjectConstants.ANNUAL_INCOME);
		String riskAppetite = ParamUtil.getString(portletRequest, EdelweissObjectConstants.RISK_APPETITE);
		
		log.debug(" name .."+name);
		log.debug(" gender .."+gender);
		log.debug(" dob .."+dob);
		log.debug(" mobileNumber .."+mobileNumber);
		log.debug(" email .."+email);
		log.debug(" investmentObjective .."+investmentObjective);		
		log.debug(" annualIncome .."+annualIncome);
		
		CommonConfiguration commonConfiguration = EdelweissConfigurationUtil.getCommonConfiguration();
		
		if (commonConfiguration.validateLMSData()) {
			log.debug(" LMS form validation enabled ..");
			if (!Validator.isBlank(name) && !Validator.isName(name)) {
				jsonObject.put(MESSAGE_KEY, "Invalid Name");
			} else if (!Validator.isBlank(gender) && !Validator.isChar(gender)) {
				jsonObject.put(MESSAGE_KEY, "Invalid Gender");
			} else if (!Validator.isBlank(dob) && !validateDate(dob)) {
				jsonObject.put(MESSAGE_KEY, "Invalid Date Of Birth");
			} else if (!Validator.isBlank(email) && !Validator.isEmailAddress(email)) {
				jsonObject.put(MESSAGE_KEY, "Invalid Email Address");
			} else if (!Validator.isBlank(mobileNumber)
					&& (!Validator.isNumber(mobileNumber) || mobileNumber.length() > 10)) {
				jsonObject.put(MESSAGE_KEY, "Invalid Mobile Number");
			} else if (!Validator.isBlank(annualIncome) && !Validator.isAlphanumericName(annualIncome)) {
				jsonObject.put(MESSAGE_KEY, "Invalid Annual Income");
			} else if (!Validator.isBlank(riskAppetite) && !Validator.isChar(riskAppetite)) {
				jsonObject.put(MESSAGE_KEY, "Invalid Risk Appetite");
			}
		}
			
		log.debug(" jsonObject .."+jsonObject);
		
		return jsonObject;
	}
	
	private boolean validateDate(String dob) {		 
		Pattern pattern = Pattern.compile(EdelweissCommonConstants.DATE_REGEX);
		Matcher matcher = pattern.matcher(dob);
		 
		return matcher.matches();
	}

	@Override
	public void updateCustomerQuoteStage(Map<String, Serializable> values, long customerEnquiryId,ServiceContext serviceContext) {
		try {
			log.debug("customerEnquiryId >>>>>>>>>>>>>>>>> "+customerEnquiryId);
			ObjectEntry objectEntry = ObjectEntryLocalServiceUtil.getObjectEntry(customerEnquiryId);
			ObjectEntryLocalServiceUtil.updateObjectEntry(objectEntry.getUserId(), objectEntry.getObjectEntryId(), values, serviceContext);
			
		} catch (PortalException e) {
			log.error(" Unable to update customer stage >>> "+e.getMessage());
		}
		
	}
	
	@Override
	public JSONObject getPartnerData(PortletRequest portletRequest,String productId,ThemeDisplay themeDisplay,String partnerSource) {

		log.debug(" EdelweissTokioCommonUtil >>> getPartnerData >>>");

		JSONObject response = JSONFactoryUtil.createJSONObject();
		response.put("isValidPartner", false);

		if(Validator.isNotNull(partnerSource) && !partnerSource.isBlank()) {
			try {
				StringBuilder partnerURL = new StringBuilder();
				partnerURL.append(EdelweissObjectConstants.PARTNER_SOURCE);
				partnerURL.append(StringPool.SPACE);
				partnerURL.append("eq");
				partnerURL.append(StringPool.SPACE);
				partnerURL.append("'" + partnerSource + "'");
				partnerURL.append(StringPool.SPACE);
				partnerURL.append("and");
				partnerURL.append(StringPool.SPACE);
				partnerURL.append("r_partners_c_productsMasterId");
				partnerURL.append(StringPool.SPACE);
				partnerURL.append("eq");
				partnerURL.append(StringPool.SPACE);
				partnerURL.append("'" + productId + "'");
				 String partnerSourceURL = themeDisplay.getPortalURL() + "/o/c/partnerredirections/?fields=partnerSource,channel,category,agentId,r_partners_c_productsMasterId&filter=" + URLEncoder.encode(partnerURL.toString(), StandardCharsets.UTF_8).replace(" ", "%20");
				 log.debug(" EdelweissTokioCommonUtil  getPartnerData >>> partnerSourceURL ::" + partnerSourceURL);
				 response = etipCoreAPI.callGetAPI(new HashMap<>(), partnerSourceURL, false, null, null);
			     log.debug(" EdelweissTokioCommonUtil  getPartnerData >>> response ::" + response);
			     log.debug(" EdelweissTokioCommonUtil  getPartnerData >>> partnerSource ::" + partnerSource);
			     log.debug(" EdelweissTokioCommonUtil  getPartnerData >>> productId ::" + productId);
				 if(Validator.isNotNull(response) && response.getInt("totalCount") > 0) {
			    	 response = response.getJSONArray("items").getJSONObject(0);
			    	if(response.getString(EdelweissObjectConstants.PARTNER_SOURCE).equals(partnerSource) && response.getString("r_partners_c_productsMasterId").equals(productId)) {
			    		response.put("isValidPartner", true);
			    	}


			    }

			} catch (ETIPSystemException e) {
				log.error("EdelweissTokioCommonUtil  >>> Exception while reading Partner data :: "+e);
			}
		}
		return response;
	}


	@Override
	public Map<String, String> addUTMCampaignParams(PortletRequest portletRequest, Map<String, String> requestObject) {

		   String cp_id = ParamUtil.getString(portletRequest, "cp_id", StringPool.BLANK);
		   String v_id = ParamUtil.getString(portletRequest, "v_id", StringPool.BLANK);
		   String c_id = ParamUtil.getString(portletRequest, "c_id", StringPool.BLANK);
	       String utm_source =   ParamUtil.getString(portletRequest, "utm_source", EDELWEISS_WEB_SALES);
	       String utm_term =  ParamUtil.getString(portletRequest, "utm_term", StringPool.BLANK);
	       String utm_creative =  ParamUtil.getString(portletRequest, "utm_creative", StringPool.BLANK);
	       String utm_campaign =  ParamUtil.getString(portletRequest, "utm_campaign", StringPool.BLANK);
	       String utm_device =  ParamUtil.getString(portletRequest, "utm_device", StringPool.BLANK);
	       String utm_content =  ParamUtil.getString(portletRequest, "utm_content", StringPool.BLANK); 
	       String utm_adgroup =  ParamUtil.getString(portletRequest, "utm_adgroup", StringPool.BLANK);
	       String utm_placement =  ParamUtil.getString(portletRequest, "utm_placement", StringPool.BLANK);
	       String utm_medium =  ParamUtil.getString(portletRequest, "utm_medium", StringPool.BLANK);
	       String gclid =  ParamUtil.getString(portletRequest, "gclid", StringPool.BLANK);


	       log.debug(" cp_id >>>>>>>>> "+cp_id);
	       log.debug(" v_id >>>>>>>>> "+v_id);
	       log.debug(" c_id >>>>>>>>> "+c_id);
	       log.debug(" utm_source >>>>>>>>> "+utm_source);
	       log.debug(" utm_term >>>>>>>>> "+utm_term);
	       log.debug(" utm_creative >>>>>>>>> "+utm_creative);
	       log.debug(" utm_campaign >>>>>>>>> "+utm_campaign);
	       log.debug(" utm_device >>>>>>>>> "+utm_device);
	       log.debug(" utm_content >>>>>>>>> "+utm_content);
	       log.debug(" utm_adgroup >>>>>>>>> "+utm_adgroup);
	       log.debug(" utm_placement >>>>>>>>> "+utm_placement);
	       log.debug(" utm_medium >>>>>>>>> "+utm_medium);
	       log.debug(" gclid >>>>>>>>> "+gclid);

	       if(Validator.isNotNull(cp_id)) {
	    	   requestObject.put("cp_id", cp_id);
	       }
	       
	       if(Validator.isNotNull(v_id)) {
	    	   requestObject.put("v_id", v_id);
	       }
	       
	       if(Validator.isNotNull(c_id)) {
	    	   requestObject.put("c_id", c_id);
	       }
	       requestObject.put("Utm_source", utm_source);
	       requestObject.put("Utm_medium", utm_medium);
	       requestObject.put("Utm_campaign", utm_campaign);
	       requestObject.put("Utm_term", utm_term);
	       requestObject.put("Utm_content", utm_content);
	       requestObject.put("Utm_Placement", utm_placement);
	       requestObject.put("Utm_creative", utm_creative);
	       requestObject.put("adgroup_id", utm_adgroup);
	       requestObject.put("device", utm_device);
	       requestObject.put("Gclid", gclid);

	       log.debug(" EdelweissTokioCommonUtil >>> updateCustomerEnquiryLMSResponse >>> requestObject :: "+requestObject);

			return requestObject;
	}

	@Override
	public JSONObject setPartner(JSONObject customerEnquire, JSONObject partnerData, PortletResponse portletResponse) {
		if(Validator.isNotNull(partnerData) && partnerData.has("isValidPartner") && partnerData.getBoolean("isValidPartner")) {
			customerEnquire.put(EdelweissObjectConstants.PARTNER_SOURCE, partnerData.getString(EdelweissObjectConstants.PARTNER_SOURCE));
			customerEnquire.put("isPartner", true);
		}else {
			customerEnquire.put(EdelweissObjectConstants.PARTNER_SOURCE, StringPool.BLANK);
			customerEnquire.put("isPartner", false);
		}

		return customerEnquire;
	}

	@Override
	public void setPartnerCookie(boolean isPartner, PortletRequest portletRequest, PortletResponse portletResponse) {

		try {
			log.debug(" Partner cookie started ....");
			HttpServletResponse response = PortalUtil.getHttpServletResponse(portletResponse);
			
			if(isPartner) {
				Cookie cookie = new Cookie(PARTNER_COOKIE, "true");
				cookie.setSecure(true);
				cookie.setPath("/");
				response.addCookie(cookie);
			}else {
				String isptnrValue = CookiesManagerUtil.getCookieValue(PARTNER_COOKIE, PortalUtil.getHttpServletRequest(portletRequest));
				if(Validator.isNotNull(isptnrValue) && isptnrValue.equals("true")) {
					Cookie cookie = new Cookie(PARTNER_COOKIE, "true");
					cookie.setSecure(true);
					cookie.setPath("/");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
				
			}
			
			log.debug(" Partner cookie Ended ....");
		} catch (Exception e) {
			log.debug(" EdelweissTokioCommonUtil >>> updateCustomerEnquiryLMSResponse >>> Exception while setting partner cookieeeeeeee :: "+e.getMessage());
		}
		
	}
	
	@Override
	public JSONObject getPartnerDataByLeadId(PortletRequest portletRequest) {

		JSONObject partnerData = JSONFactoryUtil.createJSONObject();
		partnerData.put("isValidPartner", false);

		try {

			String isptnr = CookiesManagerUtil.getCookieValue(EdelweissObjectConstants.PARTNER_COOKIE,
					PortalUtil.getHttpServletRequest(portletRequest));

			if (Validator.isNotNull(isptnr) && isptnr.equals("true")) {
				
				log.debug("EdelweissTokioCommonUtil >>> getPartnerDataByLeadId >>> is partner :: " + isptnr);

				ThemeDisplay themeDisplay = (ThemeDisplay) portletRequest.getAttribute(WebKeys.THEME_DISPLAY);
				String leadId = getLeadIdFromCookie(portletRequest, LEAD_ID);
				String productId = getCustomFieldValue(themeDisplay.getCompanyId(), Layout.class.getName(),
						themeDisplay.getPlid(), EdelweissCommonConstants.PAGE_CUSTOM_FIELD_PRODUCT_ID);
				String parameterURL = getLeadMetaDataURL(leadId);
				String customerInquiryURL = themeDisplay.getPortalURL() + LIFERAY_PRODUCT_ENQUIRY_URL
						+ FILTER_QUERY_PARAM + parameterURL + "&fields=partnerSource";
				
				log.debug("EdelweissTokioCommonUtil >>> getPartnerDataByLeadId >>> customerInquiryURL :: " + customerInquiryURL);
				
				JSONObject response = etipCoreAPI.callGetAPI(new HashMap<>(), customerInquiryURL, false, null, null);
				
				if (Validator.isNotNull(response) && response.getInt("totalCount") > 0) {
					
					response = response.getJSONArray("items").getJSONObject(0);
					
					log.debug("EdelweissTokioCommonUtil >>> getPartnerDataByLeadId >>> customerEnqu data :: " + response);

					if (Validator.isNotNull(response) && Validator.isNotNull(response.getString("partnerSource"))
							&& !response.getString("partnerSource").isBlank()) {

						partnerData = getPartnerData(portletRequest, productId, themeDisplay,
								response.getString("partnerSource"));
						log.debug("EdelweissTokioCommonUtil >>> getPartnerDataByLeadId >>> partner data :: " + partnerData);
					}

				}

			}
		} catch (ETIPSystemException e) {
			log.debug(
					" EdelweissTokioCommonUtil >>> updateCustomerEnquiryLMSResponse >>> Exception while getCEByLeadId :: "
							+ e.getMessage());
		}

		return partnerData;
	}
	
	/*
	 * Update Date formate from dd/MM/yyyy to MM/dd/yyyy
	 */
	@Override
	public String lmsDateFormate(String date) {
		log.debug("lmsDateFormate:::" + date);
		String formatedDate = date;
		
		try {
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date inputDate = inputDateFormat.parse(date);
            log.debug("inputDate"+ inputDate);

            SimpleDateFormat outputDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            formatedDate = outputDateFormat.format(inputDate);
            log.debug("output Date::"+ formatedDate);

        } catch (Exception e) {
            log.debug("Error parsing the date: " + e.getMessage());
        }
		
		return formatedDate;
	}
	
	@Override
	public String getEKycLinkURLByAPI(String requestJsonString)throws JSONException, ETIPSystemException{
		log.debug("inside getEKycLinkURLByAPI");

		ProposalFormAPIURLConfiguration proposalFormAPIURLConfiguration = EdelweissConfigurationUtil.getProposalFormAPIURLConfiguration();
		log.debug("getEKycLinkURL"+proposalFormAPIURLConfiguration.getEKycLinkURL());
		log.debug("getEKycLinkXAPIKEY"+proposalFormAPIURLConfiguration.getEKycLinkXAPIKEY());
		
		String authToken = getOAuthToken(proposalFormAPIURLConfiguration.getEKycOAuthURL(),
				proposalFormAPIURLConfiguration.getEKycLinkClientID(),
				proposalFormAPIURLConfiguration.getEKycLinkSecretKet());
		
		JSONObject requestJSONObject = JSONFactoryUtil.createJSONObject(requestJsonString);
		JSONObject responseJSONObject = etipCoreAPI.callPostAPI(requestJSONObject, proposalFormAPIURLConfiguration.getEKycLinkURL(), true, proposalFormAPIURLConfiguration.getEKycLinkXAPIKEY(), authToken);
		if(log.isDebugEnabled()) {
			log.debug("EdelweissTokioCommonUtil >>> getEKycLinkURLByAPI >>> BIResponse : " + responseJSONObject.toJSONString());
		}
		return responseJSONObject.toString();
	}
}