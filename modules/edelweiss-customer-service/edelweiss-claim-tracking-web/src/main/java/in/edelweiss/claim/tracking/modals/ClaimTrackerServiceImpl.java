package in.edelweiss.claim.tracking.modals;

import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ClaimTrackerServiceImpl {

static final Log _log = LogFactoryUtil.getLog(ClaimTrackerServiceImpl.class);
	
    public static ClaimTrackerModel addClaimTrackerData(Map<String, Serializable> values) {
        long companyId = ClaimTrackerService.getDefaultCompanyId();
        ObjectDefinition objectDefinition = ClaimTrackerService.fetchObjectDefinition(companyId);
        if (objectDefinition == null) return null;
        ObjectEntry objectEntry = ClaimTrackerService.createObjectEntry(values, objectDefinition);
        return ClaimTrackerService.mapObjectEntryToClaimTrackerModel(objectEntry);
    }
    
    public static ClaimTrackerModel updateClaimTrackerData(long objectEntryId ,Map<String, Serializable> values) {
        long companyId = ClaimTrackerService.getDefaultCompanyId();
        ObjectDefinition objectDefinition = ClaimTrackerService.fetchObjectDefinition(companyId);
        if (objectDefinition == null) return null;
        ObjectEntry objectEntry = ClaimTrackerService.updateObjectEntry(objectEntryId, values, objectDefinition);
        return ClaimTrackerService.mapObjectEntryToClaimTrackerModel(objectEntry);
    }
    
    public static ClaimTrackerModel delteClaimTrackerData(long objectEntryId) {
        long companyId = ClaimTrackerService.getDefaultCompanyId();
        ObjectDefinition objectDefinition = ClaimTrackerService.fetchObjectDefinition(companyId);
        if (objectDefinition == null) return null;
        ObjectEntry objectEntry = ClaimTrackerService.deleteObjectEntry(objectEntryId);
        return ClaimTrackerService.mapObjectEntryToClaimTrackerModel(objectEntry);
    }

    public static List<ClaimTrackerModel> findClaimTrackerDataBySearchTerm(String searchTerm, String[] selectedObjectFieldNames) {
        return ClaimTrackerService.fetchEntries(selectedObjectFieldNames, searchTerm, Integer.MAX_VALUE);
    }

    public static List<ClaimTrackerModel> getAllClaimTrackerDatas() {
        return ClaimTrackerService.fetchEntries(null, null, Integer.MAX_VALUE);
    }
    
    public static List<ClaimTrackerModel> getAllClaimTrackerDatas(int currentPage, int pageSize) {
        return ClaimTrackerService.fetchByPaginatedEntries(null, null, currentPage, pageSize);
    }
}