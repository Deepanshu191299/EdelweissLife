package in.edelweiss.proposal.form.util;

import com.edelweiss.http.core.api.ETIPCoreAPI;
import com.edelweiss.http.core.exception.ETIPSystemException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.common.util.EdelweissAPIUtil;
import in.edelweiss.proposal.form.pf.model.CommonData;
import in.edelweiss.proposal.form.pf.model.CommunicationDetails;
import in.edelweiss.proposal.form.pf.model.PersonalDetails;
import in.edelweiss.proposal.form.pf.model.ProductDetails;
import in.edelweiss.proposal.form.pf.model.ProposalJourney;
import in.edelweiss.system.configurations.EdelweissLRBasicAuthConfiguration;
import in.edelweiss.tokio.common.util.EdelweissConfigurationUtil;

public class CISHtmlContentReader {

	public String getHtmlByAppId(String appId,JSONObject cusInvestmentRes, ThemeDisplay themeDisplay, ETIPCoreAPI etipCoreAPI) throws Exception {
		
		String resultContent = StringPool.BLANK;
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		
		JSONArray items = cusInvestmentRes.getJSONArray("items");
		JSONObject cusInvestmentObj = items.getJSONObject(0);
		String annualPrem2 = cusInvestmentObj.getString("annualPrem2");
		String policyOption = cusInvestmentObj.getString("policyOption");
		String biJSONAndInputValidationString = cusInvestmentObj.getString("biJSONAndInputValidation");
		String sumassuredMaturity = CISUtil.formatIndianNumber(cusInvestmentObj.getString("totalReturns",StringPool.BLANK));
		int sumAssuredMultiple = 0;
		String data = StringPool.BLANK;
		String paymentCreatedDate = StringPool.BLANK;
		String policyNumer = StringPool.BLANK;
		String installmentPremium = StringPool.BLANK;
		String sumassuredDeath = StringPool.BLANK;
		String sumassuredDeathFsp = StringPool.BLANK;
		String premiumPaymentFreq = StringPool.BLANK;
		String premiumPaymentTerm = StringPool.BLANK;
		String policyTerm = StringPool.BLANK;
		String place = StringPool.BLANK;
		String isCISVerified = StringPool.BLANK;
		String mobileNumber = StringPool.BLANK;
		String emailId = StringPool.BLANK;
		String productName = StringPool.BLANK;
		String paymentStatus = StringPool.BLANK;

		JSONObject biJSONAndInputValidation = JSONFactoryUtil.createJSONObject();
		JSONObject bIJsonFirstIndex = JSONFactoryUtil.createJSONObject();
		JSONArray biInputValidations = JSONFactoryUtil.createJSONArray();
		if(Validator.isNotNull(biJSONAndInputValidationString)) {
			biJSONAndInputValidation = JSONFactoryUtil.createJSONObject(biJSONAndInputValidationString);
			if(biJSONAndInputValidation.has("bIJsonFirstIndex")) {
				bIJsonFirstIndex = biJSONAndInputValidation.getJSONObject("bIJsonFirstIndex");
				sumAssuredMultiple = bIJsonFirstIndex.getInt("SAD_FINAL", 0);
			}
			
			if(biJSONAndInputValidation.has("biInputValidations")) {
				JSONArray allRidersArray = extractRiderDetails(getAllRiders(themeDisplay, etipCoreAPI));
				biInputValidations = biJSONAndInputValidation.getJSONArray("biInputValidations");
				
				_log.info("allRidersArray :: "+allRidersArray);
				_log.info("biInputValidations :: "+biInputValidations);
				data = riderTableData(allRidersArray, biInputValidations);
				
			}
		}

		try {
            paymentCreatedDate = cusInvestmentObj.getString("paymentCreatedDate",StringPool.BLANK);
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = inputFormat.parse(paymentCreatedDate);
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
            paymentCreatedDate = outputFormat.format(date);
        } catch (Exception e) {
        	paymentCreatedDate = StringPool.BLANK;
        }
			    
	    if (Validator.isNotNull(appId)|| !appId.equals(StringPool.BLANK)) {
		    String pfURL = ConfigurationUtil.getProposalFormAPIURLConfiguration().getPFDetailsURL() + "?applicationNumber="+ appId;
			
			String pfResponseCis = EdelweissAPIUtil.callPJGetAPI(pfURL);
			_log.debug("pfResponse under CIS ... "+pfResponseCis);

			ProposalJourney proposalJourney = objectMapper.readValue(pfResponseCis, ProposalJourney.class);
		
			CommonData commonDetails = proposalJourney.getResponseData().getCommonData(); // ProductDetails
			ProductDetails productDetails = proposalJourney.getResponseData().getProductDetails();
			CommunicationDetails communicationDetails = proposalJourney.getResponseData().getCommunicationDetails();
			PersonalDetails personalDetails = proposalJourney.getResponseData().getPersonalDetails();
			
			 if(communicationDetails != null && communicationDetails.getLaDetails().getPaCity() != null ) {
				 place = communicationDetails.getLaDetails().getPaCity() != null ? communicationDetails.getLaDetails().getPaCity() : "";
			 }else {
				 place = cusInvestmentObj.getString("cisPlace",StringPool.BLANK);
			 }
			 if(commonDetails != null && commonDetails.getPolicyNo() != null ) {
				 policyNumer = commonDetails.getPolicyNo() != null ? commonDetails.getPolicyNo() : "";
				 productName = commonDetails.getProductName() != null ? commonDetails.getProductName() : "";
				 paymentStatus = commonDetails.getPaymentStatus() != null ? commonDetails.getPaymentStatus() : "";
			 }
			 if(productDetails != null && productDetails.getTotalPremiumAmount() > 0) {
				 installmentPremium = String.valueOf(productDetails.getTotalPremiumAmount()) != null ? CISUtil.formatIndianNumber(String.valueOf(productDetails.getTotalPremiumAmount())) : "";
			 }
			 if(productDetails != null && productDetails.getSumAssured() > 0) {
				sumassuredDeath = String.valueOf(productDetails.getSumAssured()) != null ? CISUtil.formatIndianNumber(String.valueOf(productDetails.getSumAssured())) : "";
			 }
			 if(productDetails != null && productDetails.getAnnualisedPremium() > 0) {
					sumassuredDeathFsp = String.valueOf(productDetails.getAnnualisedPremium()) != null ? CISUtil.formatIndianNumber(String.valueOf(productDetails.getAnnualisedPremium() * 7)) : "";
			 }
			 if(productDetails != null && productDetails.getPremiuPaymentFrequency() != null ) {
				 premiumPaymentFreq = productDetails.getPremiuPaymentFrequency() != null ? productDetails.getPremiuPaymentFrequency() : "";
			 }
			 if(productDetails != null && productDetails.getPremiumPaymentTerm() > 0 ) {
				 premiumPaymentTerm = String.valueOf(productDetails.getPremiumPaymentTerm()) != null ? String.valueOf(productDetails.getPremiumPaymentTerm()) : "";
			 }
			 if(productDetails != null && productDetails.getPolicyTerm() > 0 ) {
				 policyTerm = String.valueOf(productDetails.getPolicyTerm()) != null ? String.valueOf(productDetails.getPolicyTerm()) : "";
			 }
			 if(personalDetails != null && personalDetails.getLaDetails().getMobileNumber() != null) {
				 mobileNumber = personalDetails.getLaDetails().getMobileNumber() != null ? personalDetails.getLaDetails().getMobileNumber() : "";
			 }
			 if(personalDetails != null && personalDetails.getLaDetails().getEmailAddress() != null) {
				 emailId = personalDetails.getLaDetails().getEmailAddress() != null ? personalDetails.getLaDetails().getEmailAddress() : "";
				 emailId = "/ Email ID "+ emailId;
			 }
			 
			Map<String, String> replaceHolderMap = new HashMap<>();
			replaceHolderMap.put("$[POLICYNO]", policyNumer);
			replaceHolderMap.put("$[INSTALLMENT_PREMIUM]", installmentPremium);
			replaceHolderMap.put("$[SUM_ASSURED_DEATH]", sumassuredDeath);
			replaceHolderMap.put("$[SUM_ASSURED_MATURITY]", sumassuredMaturity);
			replaceHolderMap.put("$[PREMIUM_PAYMENT_FREQUENCY]", premiumPaymentFreq);
			replaceHolderMap.put("$[PREMIUM_PAYMENT_TERM]", premiumPaymentTerm);
			replaceHolderMap.put("$[POLICY_TERM]", policyTerm);
			replaceHolderMap.put("$[PLACE]", place);
			replaceHolderMap.put("$[IS_CISVERIFIED]","");
			replaceHolderMap.put("$[MOBILE_NUMBER]", "");
			replaceHolderMap.put("$[EMAIL_ID]", "");
			replaceHolderMap.put("$[RiderTable]", data);
			replaceHolderMap.put("$[PAYMENT_CREATED_DATE]", paymentCreatedDate);
			
		    if(paymentStatus.equals("Y")){
		        isCISVerified = "Validated through the OTP sent to registered mobile no";
		        replaceHolderMap.put("$[IS_CISVERIFIED]",isCISVerified);
				replaceHolderMap.put("$[MOBILE_NUMBER]", mobileNumber);
				replaceHolderMap.put("$[EMAIL_ID]", emailId);
		    }
			
		    
		    if(productName.equalsIgnoreCase("Premier Guaranteed STAR")) {
		    	_log.debug("CIS for PGS Product:::::::::");
		    	resultContent = CISHtmlContentReader.getArticleByUrlTitle(themeDisplay, "cis-pgs", replaceHolderMap);
		    }else if(productName.equalsIgnoreCase("Flexi-Savings Plan")) {
		    	_log.debug("CIS for FSP Product:::::::::");
		    	replaceHolderMap.put("$[SUM_ASSURED_DEATH_FSP]", sumassuredDeathFsp);
		    	resultContent = CISHtmlContentReader.getArticleByUrlTitle(themeDisplay, "cis-fsp", replaceHolderMap);
		    }else if(productName.equalsIgnoreCase("Guaranteed Income STAR")) {
		    	_log.debug("CIS for GIS Product:::::::::");
		    	resultContent = CISHtmlContentReader.getArticleByUrlTitle(themeDisplay, "cis-gis", replaceHolderMap);
		    }else if(productName.equalsIgnoreCase("Guaranteed Savings STAR")) {
		    	_log.debug("CIS for GSS Product:::::::::");
		    	resultContent = CISHtmlContentReader.getArticleByUrlTitle(themeDisplay, "cis-gss", replaceHolderMap);
		    }else if(productName.equalsIgnoreCase("Saral Jeevan Bima")) {
		    	_log.debug("CIS for SJB Product:::::::::");
		    	resultContent = CISHtmlContentReader.getArticleByUrlTitle(themeDisplay, "cis-sjb", replaceHolderMap);
		    }else if(productName.equalsIgnoreCase("Wealth Plus")) {
		    	_log.debug("CIS for WP Product:::::::::");
		    	resultContent = CISHtmlContentReader.getArticleByUrlTitle(themeDisplay, "cis-wp", replaceHolderMap);
		    }else if(productName.equalsIgnoreCase("Wealth Rise+")) {
		    	_log.debug("CIS for WRP Product:::::::::");
		    	resultContent = CISHtmlContentReader.getArticleByUrlTitle(themeDisplay, "cis-wrp", replaceHolderMap);
		    }else if(productName.equalsIgnoreCase("Zindagi Protect Plus")) {
		    	_log.debug("CIS for ZPP Product:::::::::");
		    	long annualPrem2Num = 0;
		    	if(policyOption.equals("131")) {
		    		annualPrem2Num =Long.parseLong(annualPrem2) * Long.parseLong(premiumPaymentTerm);
		    		replaceHolderMap.put("$[SUM_ASSURED_DEATH_ZPP]", CISUtil.formatIndianNumber(String.valueOf(annualPrem2Num)));
		    	}else {
		    		replaceHolderMap.put("$[SUM_ASSURED_DEATH_ZPP]", String.valueOf(annualPrem2Num));
		    	}
		    	
		    	resultContent = CISHtmlContentReader.getArticleByUrlTitle(themeDisplay, "cis-zpp", replaceHolderMap);
		    }else if (productName.equalsIgnoreCase("Guaranteed Flexi STAR")) {
		        _log.debug("CIS for GFS Product:::::::::");
		        replaceHolderMap.put("$[SUM_ASSURED_DEATH_GFS]", CISUtil.formatIndianNumber(String.valueOf(sumAssuredMultiple)));

		        resultContent = CISHtmlContentReader.getArticleByUrlTitle(themeDisplay, "cis-gfs", replaceHolderMap);
		    }
	    }
	    return resultContent;
	}
	
	public static String getArticleByUrlTitle(ThemeDisplay themeDisplay, String URL, Map<String, String> replaceHolderMap) {
		String resultContent = StringPool.BLANK;
		try {
	        JournalArticle article = JournalArticleLocalServiceUtil.getArticleByUrlTitle(themeDisplay.getScopeGroupId(), URL);
	        if (Validator.isNotNull(article)) {
	        	_log.info("Article found "+URL);
	            String content = article.getContentByLocale(themeDisplay.getLocale().toString());
	            
	            Document doc = SAXReaderUtil.read(content);
		        Element element =  doc.getRootElement();
		        List<Element> elements = element.elements();
		        for(Element elem : elements) {
	                if(elem.attributeValue("field-reference").equalsIgnoreCase("Content")) {
	                	resultContent = elem.element("dynamic-content").getText();
	                }
		        }
                
                // Dynamically replace all placeholders with their values
                for (Map.Entry<String, String> entry : replaceHolderMap.entrySet()) {
                    resultContent = resultContent.replace(entry.getKey(), entry.getValue());
                }
	        }
	    } catch (PortalException e) {
	    	_log.error("Error retrieving article content PortalException "+ e.getMessage());
	    } catch (DocumentException e) {
	    	_log.error("Error retrieving article content DocumentException "+ e.getMessage());
		}
		
		return resultContent;
	}
	
	public String riderTableData(JSONArray riderList, JSONArray CustomerRiderList) {
        StringBuilder tableRows = new StringBuilder();
        long total = 0;
        for (int riderListIndex = 0; riderListIndex < riderList.length(); riderListIndex++) {
        	JSONObject allDefaultRidersJSON = riderList.getJSONObject(riderListIndex);
        	
        	String riderId = allDefaultRidersJSON.getString("riderId");
        	String uin = allDefaultRidersJSON.getString("uin");
        	String title = allDefaultRidersJSON.getString("title");

        	for (int customerRiderIndex = 1; customerRiderIndex < CustomerRiderList.length(); customerRiderIndex++) {
        		
        		JSONObject customerSelectedRidersJSON = CustomerRiderList.getJSONObject(customerRiderIndex);
        		int selectedRiderId = customerSelectedRidersJSON.getInt("ProductId");
        		int riderSumAssured = customerSelectedRidersJSON.getInt("SA");
        		double tax = customerSelectedRidersJSON.getDouble("Tax");
        		int modalPremiumPlusTax = (int) (customerSelectedRidersJSON.getInt("ModalPremium") + Math.round(tax));
        		
        		int pT = customerSelectedRidersJSON.getInt("PT");
        		int pPT = customerSelectedRidersJSON.getInt("PPT");
        		
        		if(riderId.equalsIgnoreCase(String.valueOf(selectedRiderId))) {
        			title = title.contains("(") ? title.substring(0, title.indexOf("(")) : title;
        			String tableRow = "<tr style=\"page-break-inside: avoid; \">"
        	                + "<td style=\"width: 15%; border: 1px solid black; padding: 8px; word-wrap: break-word;\">"
        	                + title + "</td>"
        	                + "<td style=\"width: 15%; border: 1px solid black; padding: 8px; word-wrap: break-word;\">"
        	                + uin + "</td>"
        	                + "<td style=\"width: 20%; border: 1px solid black; padding: 8px; word-wrap: break-word;\">"
        	                + CISUtil.formatIndianNumber(String.valueOf(riderSumAssured)) + "</td>"
        	                + "<td style=\"width: 20%; border: 1px solid black; padding: 8px; word-wrap: break-word;\">"
        	                + CISUtil.formatIndianNumber(String.valueOf(modalPremiumPlusTax)) + "</td>"
        	                + "<td style=\"width: 15%; border: 1px solid black; padding: 8px; word-wrap: break-word;\">"
        	                + String.valueOf(pT) + "</td>"
        	                + "<td style=\"width: 15%; border: 1px solid black; padding: 8px; word-wrap: break-word;\">"
        	                + String.valueOf(pPT) + "</td>"
        	                + "</tr>";
        	            tableRows.append(tableRow);
        	            
        	           total = total +  modalPremiumPlusTax;
        		}
        	}
        }

        String tableRow = "<tr style=\"page-break-inside: avoid; \">"
                + "<td style=\"width: 15%; border: 1px solid black; padding: 8px;\">Total</td>"
                + "<td style=\"width: 15%; border: 1px solid black; padding: 8px;\"></td>"
                + "<td style=\"width: 20%; border: 1px solid black; padding: 8px; word-wrap: break-word;\"></td>"
                + "<td style=\"width: 20%; border: 1px solid black; padding: 8px; word-wrap: break-word;\">"+ CISUtil.formatIndianNumber(String.valueOf(total)) + "</td>"
                + "<td style=\"width: 15%; border: 1px solid black; padding: 8px; word-wrap: break-word;\"></td>"
                + "<td style=\"width: 15%; border: 1px solid black; padding: 8px; word-wrap: break-word;\"></td>"
                + "</tr>";
            tableRows.append(tableRow);
        return tableRows.toString();
    }
	 
	private JSONObject getAllRiders(ThemeDisplay themeDisplay, ETIPCoreAPI etipCoreAPI) {
		JSONObject responseObject = JSONFactoryUtil.createJSONObject();
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
		
		String productRidersURL = themeDisplay.getPortalURL()  + "/o/c/productriders/?page=1&pageSize=20";
		try {
			responseObject = etipCoreAPI.callGetAPI(new HashMap<>(), productRidersURL, false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());
		} catch (ETIPSystemException e) {
			_log.error("EdelweissGenerateBIResourceCommand >> Error :::: "+e.getMessage());
		}
		return responseObject;
	}
		
	private JSONArray extractRiderDetails(JSONObject responseObject) {
	    JSONArray filteredArray = JSONFactoryUtil.createJSONArray();
		
	    if (responseObject.has("items")) {
	        JSONArray items = responseObject.getJSONArray("items");
	        for (int i = 0; i < items.length(); i++) {
	            JSONObject item = items.getJSONObject(i);
	            
	            JSONObject filteredItem = JSONFactoryUtil.createJSONObject();
	            filteredItem.put("title", item.getString("title", ""));
	            filteredItem.put("riderId", item.getString("riderId", ""));
	            filteredItem.put("uin", item.getString("uin", ""));
	            filteredArray.put(filteredItem);
	        }
	    }
	    return filteredArray;
	}
	
	private static final Log _log = LogFactoryUtil.getLog(CISHtmlContentReader.class);
}
