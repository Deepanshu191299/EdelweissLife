package in.edelweiss.claim.tracking.upload.mvc;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadRequest;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.osgi.service.component.annotations.Component;

import in.edelweiss.claim.tracking.constants.ClaimTrackingPortletKeys;
import in.edelweiss.claim.tracking.modals.ClaimTrackerModel;
import in.edelweiss.claim.tracking.modals.ClaimTrackerServiceImpl;

@Component(
		immediate = true, 
		property = {
		"javax.portlet.name=" + ClaimTrackingPortletKeys.CLAIMTRACKING,
		"mvc.command.name=/tracking/upload/excel"
		},
		service = MVCResourceCommand.class)
public class UploadExcelDataResourceCommand extends BaseMVCResourceCommand {
	private static final Log log = LogFactoryUtil.getLog(UploadExcelDataResourceCommand.class);
	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		UploadRequest uploadRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
		File file = uploadRequest.getFile("excelFile");
		JSONObject responseJson = JSONFactoryUtil.createJSONObject();
		JSONObject response = JSONFactoryUtil.createJSONObject();
		boolean status = false;
		String message = StringPool.BLANK;
		
		/*int currentPage = 1;
		int pageSize = 5;
		List<ClaimTrackerModel> entries = ClaimTrackerServiceImpl.getAllClaimTrackerDatas(currentPage, pageSize);
		log.info("entries ::: "+entries.size());
	    for(ClaimTrackerModel entry : entries) {
	    	log.info("ClaimReferenceNo "+entry.getClaimReferenceNo());
	    }*/
	    
		if(!themeDisplay.isSignedIn()) {
			return;
		}
		
		if (file != null && file.exists()) {
			try (FileInputStream fileInputStream = new FileInputStream(file);
                 Workbook workbook = new XSSFWorkbook(fileInputStream)) {

                Sheet sheet = workbook.getSheetAt(0); // Read the first sheet
                Iterator<Row> rowIterator = sheet.iterator();
                
                List<JSONObject> rowDataList = this.rowDataList(rowIterator);
                
                // Skip the first row
                if (!rowDataList.isEmpty()) {
                    rowDataList = rowDataList.subList(1, rowDataList.size());
                }
                
                for (JSONObject rowJson : rowDataList) {
                	String claimReferenceNo = rowJson.getString("col0");
                	String policyNo = rowJson.getString("col1");
                	String nameOfLA = rowJson.getString("col2");
                	String intimationDate = rowJson.getString("col3");
                	String decisionDate = rowJson.getString("col4");
                	String causeOfDeath = rowJson.getString("col5");
                	String finalDecision = rowJson.getString("col6");
                	String claimType = rowJson.getString("col7");

            	    String[] selectedObjectFieldNames = new String[]{"claimReferenceNo"};
            	    List<ClaimTrackerModel> entries = ClaimTrackerServiceImpl.findClaimTrackerDataBySearchTerm(claimReferenceNo, selectedObjectFieldNames);
            	    Map<String, Serializable> claimTrackerInstance = new HashMap<>();
            	    claimTrackerInstance.put("claimReferenceNo", claimReferenceNo);
            	    claimTrackerInstance.put("policyNo", policyNo);
            	    claimTrackerInstance.put("nameOfLA", nameOfLA);
            	    claimTrackerInstance.put("intimationDate", intimationDate);
            	    claimTrackerInstance.put("decisionDate", decisionDate);
            	    claimTrackerInstance.put("causeOfDeath", causeOfDeath);
            	    claimTrackerInstance.put("finalDecision", finalDecision);
            	    claimTrackerInstance.put("claimType", claimType);

            	    for(ClaimTrackerModel entry : entries) {
            	    	if(claimReferenceNo.equals(entry.getClaimReferenceNo())) {
            	    		//log.info("claimReferenceNo exist so updated "+claimReferenceNo);
            	    		ClaimTrackerModel claimTracker = ClaimTrackerServiceImpl.updateClaimTrackerData(entry.getObjectEntryId(), claimTrackerInstance);
            	    	}
            	    }
            	    
            	    if(entries.size() == 0) {
            	    	//log.info("claimReferenceNo not exist so added "+claimReferenceNo);
            	    	ClaimTrackerModel claimTracker = ClaimTrackerServiceImpl.addClaimTrackerData(claimTrackerInstance);
            	    }
                }
                
                status = true;
        		message = "file data successfully uploaded";
                responseJson.put("data", rowDataList);
            } catch (IOException e) {
                log.error("Error processing file: " + e.getMessage(), e);
                status = false;
        		message = "Internal server error";
            }
        }
		
		response.put("data", responseJson);
		response.put("status", status);
        response.put("message", message);
		resourceResponse.setContentType("application/json");
        PrintWriter writer = resourceResponse.getWriter();
        writer.print(response);
	}
	
	private List<JSONObject> rowDataList(Iterator<Row> rowIterator){
		
		List<JSONObject> rowDataList = new ArrayList<>();
        
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            JSONObject rowJson = JSONFactoryUtil.createJSONObject();
            
            int cellIndex = 0;
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                rowJson.put("col" + cellIndex, getCellValue(cell));
                cellIndex++;
            }

            rowDataList.add(rowJson);
        }
        
		return rowDataList;
	}
	
	private String getCellValue(Cell cell) {
	    switch (cell.getCellType()) {
	        case STRING:
	            return cell.getStringCellValue();
	        case NUMERIC:
	            if (DateUtil.isCellDateFormatted(cell)) {
	                return String.valueOf(cell.getDateCellValue());
	            } else {
	                return String.valueOf(cell.getNumericCellValue());
	            }
	        case BOOLEAN:
	            return String.valueOf(cell.getBooleanCellValue());
	        case FORMULA:
	            return cell.getCellFormula();
	        default:
	            return "";
	    }
	}
}
