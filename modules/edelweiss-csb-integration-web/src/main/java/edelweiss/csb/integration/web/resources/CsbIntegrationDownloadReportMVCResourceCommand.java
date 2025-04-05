package edelweiss.csb.integration.web.resources;

import com.edelweiss.http.core.api.ETIPCoreAPI;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicInteger;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import edelweiss.csb.integration.web.constants.EdelweissCsbIntegrationWebPortletKeys;
import edelweiss.csb.integration.web.model.CsbIntegrationResponseData;
import in.edelweiss.system.configurations.EdelweissLRBasicAuthConfiguration;
import in.edelweiss.tokio.common.util.EdelweissConfigurationUtil;

@Component(
	    immediate = true,
	    property = {
	        "javax.portlet.name="+ EdelweissCsbIntegrationWebPortletKeys.EDELWEISSCSBINTEGRATIONWEB,
	        "mvc.command.name="+EdelweissCsbIntegrationWebPortletKeys.CSB_INTEGRATION_DOWNLOAD_REPORT_MVC_RESOUCE_COMMAND
	    },
	    service = MVCResourceCommand.class
	)
public class CsbIntegrationDownloadReportMVCResourceCommand extends BaseMVCResourceCommand{

	
	private static Log log = LogFactoryUtil.getLog(CsbIntegrationDownloadReportMVCResourceCommand.class);
	
	public static String[] columnNames = { "Sr no","Transaction Date","Transaction Month","CSB Bank Account/Reference number","CSB Employee ID","CSB Employee Name","Application Number","Client Name","Gender","DOB","NeoAppNumber/ClientID","Br Name","OTP Trigerred Date","Br Code","City Pin Code","Profession","Nominee Name","Nominee Relation","Email ID","Contact Number","SA (LAC)","Received Premium","Medication/Hospitalization","Surgery","Covid-19","Status","Age",};
	public static final String COMMA = ",";
	public static final String FILENAME = "CsbIntegrationObjectData.csv";
	public static final String DD_MM_YYYY = "dd/MM/yyyy";

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
		// TODO Auto-generated method stub
		log.info(" **** In Serve Resource Method *****");
		String url = ParamUtil.getString(resourceRequest, "export");
		try {
			if (url.equals("exportCSV")) {		
				StringBundler sb = new StringBundler();
				for (String columnName : columnNames) {
					log.info("columnName---->"+columnName);
					log.info("columns length ---->"+columnName.length());
					sb.append(getCSVFormattedValue(String.valueOf(columnName)));
					sb.append(COMMA);
				}
				sb.setIndex(sb.index() - 1);
				sb.append(CharPool.NEW_LINE);
			
				ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
				
				String csbIntegrationUrl = themeDisplay.getPortalURL() + "/o/c/csbintegrations/";
				log.info("csbIntergration URL::::"+ csbIntegrationUrl);
				
				List<CsbIntegrationResponseData> CsbIntegrationResponseDataList = getAllCsbIntegrationRecords(resourceRequest);
				
				AtomicInteger counter = new AtomicInteger(1); // Initialize the counter
				
				CsbIntegrationResponseDataList.forEach(csbIntegration -> {
					/*
					 * String otpTriggeredDate = null; try { otpTriggeredDate =
					 * getDateToString(csbIntegration); } catch (PortalException e) { // TODO
					 * Auto-generated catch block e.printStackTrace(); }
					 */
					sb.append(getCSVFormattedValue(String.valueOf(counter.getAndIncrement()))); // Add Sr no.
				    sb.append(COMMA);
				    
				    sb.append(getCSVFormattedValue(String.valueOf(csbIntegration.getTransactionDate()))); // Add Sr no.
				    sb.append(COMMA);
				    
				    sb.append(getCSVFormattedValue(String.valueOf(csbIntegration.getTransactionMonth()))); // Add TransactionMonth
				    sb.append(COMMA);
				    
				    sb.append(getCSVFormattedValue(String.valueOf(csbIntegration.getcSBAccountNo()))); // Add csbAccount Number
				    sb.append(COMMA);
				    
				    sb.append(getCSVFormattedValue(String.valueOf(csbIntegration.getcSBEmployeeId()))); // Add csb EmployeeID
				    sb.append(COMMA);
				    
				    sb.append(getCSVFormattedValue(String.valueOf(csbIntegration.getcSBEmployeeName()))); // Add csb EmployeeName
				    sb.append(COMMA);
				    
				    sb.append(getCSVFormattedValue(String.valueOf(csbIntegration.getId()))); // Add application no.
				    sb.append(COMMA);
				    
					sb.append(getCSVFormattedValue(String.valueOf(csbIntegration.getClientName())));
					sb.append(COMMA);
					// gender 
					sb.append(getCSVFormattedValue(String.valueOf(csbIntegration.getGender())));
					sb.append(COMMA);
					
					sb.append(getCSVFormattedValue(String.valueOf(csbIntegration.getClientDOB())));
					sb.append(COMMA);
					
					sb.append(getCSVFormattedValue(String.valueOf(csbIntegration.getNeoApplicationNo())));
					sb.append(COMMA);
					
					sb.append(getCSVFormattedValue(String.valueOf(csbIntegration.getBranchName())));
					sb.append(COMMA);
					
					sb.append(getCSVFormattedValue(String.valueOf(csbIntegration.getOtpTrigerredDate())));
					log.info("otpTriggeredDate----->"+String.valueOf(csbIntegration.getOtpTrigerredDate()));
					sb.append(COMMA);
					
					sb.append(getCSVFormattedValue(String.valueOf(csbIntegration.getBranchCode())));
					sb.append(COMMA);
					
					sb.append(getCSVFormattedValue(String.valueOf(csbIntegration.getPinCode())));
					sb.append(COMMA);
					
					sb.append(getCSVFormattedValue(String.valueOf(csbIntegration.getOccupation())));
					sb.append(COMMA);
					
					sb.append(getCSVFormattedValue(String.valueOf(csbIntegration.getNomineeName())));
					sb.append(COMMA);
					
					sb.append(getCSVFormattedValue(String.valueOf(csbIntegration.getRelation())));
					sb.append(COMMA);
					
					sb.append(getCSVFormattedValue(String.valueOf(csbIntegration.getEmailId())));
					sb.append(COMMA);
					
					sb.append(getCSVFormattedValue(String.valueOf(csbIntegration.getMobileNo())));
					sb.append(COMMA);
					
					sb.append(getCSVFormattedValue(String.valueOf(csbIntegration.getRiskCoverOpted()))); // risk covered opted (sa Lac)
					sb.append(COMMA);
					
					sb.append(getCSVFormattedValue(String.valueOf(csbIntegration.getPremium()))); // received premium
					sb.append(COMMA);
					
					sb.append(getCSVFormattedValue(String.valueOf(csbIntegration.getMedication()))); // medication - section D field 
					sb.append(COMMA);
					
					sb.append(getCSVFormattedValue(String.valueOf(csbIntegration.getSurgery()))); //----Surgery - section D field 
					sb.append(COMMA);
					
					sb.append(getCSVFormattedValue(String.valueOf(csbIntegration.getCovid()))); //----covid - 19
					sb.append(COMMA);
					
					sb.append(getCSVFormattedValue(String.valueOf(csbIntegration.getCsbIntegrationStatus())));
					sb.append(COMMA);
				
					sb.append(getCSVFormattedValue(String.valueOf(csbIntegration.getAge())));
					sb.append(COMMA);
					
					sb.setIndex(sb.index() - 1);
					sb.append(CharPool.NEW_LINE);
				});

				byte[] bytes = sb.toString().getBytes();
				String contentType = ContentTypes.APPLICATION_TEXT;
				PortletResponseUtil.sendFile(resourceRequest, resourceResponse,FILENAME, bytes, contentType);
			}
		} catch (Exception e) {
			log.error("Exception While export CSV file : "+ e.getMessage());
		}
	}

	
	
	
	/*
	 * private String getDateToString(CsbIntegrationResponseData
	 * csbIntegrationResponseData) throws PortalException { Date birthDate =
	 * csbIntegrationResponseData.getOtpTrigerredDate(); SimpleDateFormat formatter
	 * = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); String stringDate =
	 * formatter.format(birthDate); return stringDate; }
	 */
	
	private String getCSVFormattedValue(String value) {
		StringBundler sb = new StringBundler(3);
		sb.append(CharPool.QUOTE);
		sb.append(StringUtil.replace(value, CharPool.QUOTE, StringPool.DOUBLE_QUOTE));
		sb.append(CharPool.QUOTE);
		return sb.toString();
	}

	// get All the records from getApi. 
    public List<CsbIntegrationResponseData> getAllCsbIntegrationRecords(ResourceRequest resourceRequest) {
        List<CsbIntegrationResponseData> allRecords = new ArrayList<>();
        int page = 1;  // Start with the first page
        int pageSize = 20;  // Assuming the page size is 20, adjust if necessary
        int totalPages = 1;  // Initialize to enter the loop
        try {
            EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
            String liferayUserName = edelweissLRBasicAuthConfiguration.getLRUsername();
            String liferayPassword = edelweissLRBasicAuthConfiguration.getLRPassword();
            
            ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
            String csbIntegrationUrl = themeDisplay.getPortalURL() + "/o/c/csbintegrations/";
            
            while (page <= totalPages) {
                String paginatedUrl = csbIntegrationUrl + "?page=" + page + "&pageSize=" + pageSize;
                JSONObject lrCsbResponse = etipCoreApi.callGetAPI(new HashMap<>(), paginatedUrl, false, liferayUserName, liferayPassword);
                
                // call convertItemsToCsbIntegrationList
                List<CsbIntegrationResponseData> pageRecords = convertItemsToCsbIntegrationList(lrCsbResponse);
                allRecords.addAll(pageRecords);
                
                // Assume the total number of pages is included in the response
                if (lrCsbResponse.has("lastPage")) {
                    totalPages = lrCsbResponse.getInt("lastPage");
                } 
                page++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
        return allRecords;
    }
    
	
	// convert items to list 
	public List<CsbIntegrationResponseData> convertItemsToCsbIntegrationList(JSONObject jsonObject) {
	        List<CsbIntegrationResponseData> csbIntegrationResponseDataList = new ArrayList<>();
	         try {
	            // Validate jsonObject and items array
	        	 if (Validator.isNotNull(jsonObject) && jsonObject.has("items") && Validator.isNotNull(jsonObject.getJSONArray("items")) && jsonObject.getJSONArray("items").length() > 0) {
	                JSONArray itemsArray = jsonObject.getJSONArray("items");
	                // Convert each item in the array to a CsbIntegrationResponseData object and add it to the list
	                for (int i = 0; i < itemsArray.length(); i++) {
	                    JSONObject item = itemsArray.getJSONObject(i);
	                    CsbIntegrationResponseData csbIntegrationResponseData = new CsbIntegrationResponseData();
	                    
	                    if (item.has("clientName")) {
	                    	csbIntegrationResponseData.setClientName((item.getString("clientName")));
	                    }
	                    
	                    if (item.has("id")) {
	                    	csbIntegrationResponseData.setId(((item.getString("id"))));
	                    }
	                    
	                    if (item.has("gender")) {
	                    	csbIntegrationResponseData.setGender((item.getString("gender")));
	                    }
	                    if (item.has("clientDOB")) {
	                    	csbIntegrationResponseData.setClientDOB((item.getString("clientDOB")));
	                    }
	                    if (item.has("neoApplicationNo")) {
	                    	csbIntegrationResponseData.setNeoApplicationNo((item.getString("neoApplicationNo")));
	                    }
	                    if (item.has("branchName")) {
	                    	csbIntegrationResponseData.setBranchName((item.getString("branchName")));
	                    	
	                    }
	                    if(item.has("dateCreated")) {
	                    	csbIntegrationResponseData.setOtpTrigerredDate(convertToIST(item.getString("dateCreated")));
	                    }
	                    if (item.has("branchCode")) {
	                    	csbIntegrationResponseData.setBranchCode((item.getString("branchCode")));
	                   }
	                    if (item.has("pinCode")) {
	                    	csbIntegrationResponseData.setPinCode((item.getString("pinCode")));
	                    }
	                   if (item.has("riskCoverOpted")) {
	                    	csbIntegrationResponseData.setRiskCoverOpted((item.getString("riskCoverOpted")));
	                    }
	                    if (item.has("occupation")) {
	                    	csbIntegrationResponseData.setOccupation((item.getString("occupation")));
	                    }
	                    if (item.has("nomineeName")) {
	                    	csbIntegrationResponseData.setNomineeName((item.getString("nomineeName")));
	                    }
	                   if (item.has("relation")) {
	                    	csbIntegrationResponseData.setRelation((item.getString("relation")));
	                   }
	                    if (item.has("emailId")) {
	                    	csbIntegrationResponseData.setEmailId((item.getString("emailId")));
	                    }
	                    if (item.has("mobileNo")) {
	                    	csbIntegrationResponseData.setMobileNo((item.getString("mobileNo")));
	                    }
	                    
	                    if (item.has("premium")) {
	                    	csbIntegrationResponseData.setPremium((item.getString("premium")));
	                    }
	                    
	                    if (item.has("medication")) {
	                    	csbIntegrationResponseData.setMedication((item.getString("medication"))); //-medicaiton
	                    }
	                    
	                    if (item.has("surgery")) {
	                    	csbIntegrationResponseData.setSurgery((item.getString("surgery"))); // surgery
	                    }
	                    
	                    if (item.has("covid")) {
	                    	csbIntegrationResponseData.setCovid((item.getString("covid"))); // covid - 19
	                    }
	                    
	                                             
	                    if (item.has("csbIntegrationStatus")) {
	                    	csbIntegrationResponseData.setCsbIntegrationStatus((item.getString("csbIntegrationStatus")));
	                    }
	                    if (item.has("age")) {
	                    	csbIntegrationResponseData.setAge((item.getString("age")));
	                    }
	                    if (item.has("transactionDate")) {
	                    	csbIntegrationResponseData.setTransactionDate((item.getString("transactionDate")));
	                    }
	                    if (item.has("transactionMonth")) {
	                    	csbIntegrationResponseData.setTransactionMonth((item.getString("transactionMonth")));
	                    }
	                    if (item.has("cSBAccountNo")) {
	                    	csbIntegrationResponseData.setcSBAccountNo((item.getString("cSBAccountNo")));
	                    }
	                    if (item.has("cSBEmployeeId")) {
	                    	csbIntegrationResponseData.setcSBEmployeeId((item.getString("cSBEmployeeId")));
	                    }
	                    if (item.has("cSBEmployeeName")) {
	                    	csbIntegrationResponseData.setcSBEmployeeName((item.getString("cSBEmployeeName")));
	                    }
	                    
	                    
	                    
	                    csbIntegrationResponseDataList.add(csbIntegrationResponseData);
	                }
	            
	        	 }
	        } catch (Exception e) {
	            e.printStackTrace();
	            // Handle the exception as needed
	            log.info("Exception--------"+e.getMessage());
	        }

	        return csbIntegrationResponseDataList;
	    }

	

	public  String convertToIST(String utcDate) throws ParseException {
        // Define the input and output date formats
        SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat istFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a");
        istFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));

        // Parse the UTC date string to a Date object
        Date date = null;
		try {
			date = utcFormat.parse(utcDate);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Format the Date object to the IST date string
        return istFormat.format(date);
    }

	@Reference
	ETIPCoreAPI etipCoreApi;
		
		
	}

