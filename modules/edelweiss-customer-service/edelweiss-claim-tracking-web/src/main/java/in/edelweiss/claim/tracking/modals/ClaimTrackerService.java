package in.edelweiss.claim.tracking.modals;

import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectDefinitionLocalServiceUtil;
import com.liferay.object.service.ObjectEntryLocalServiceUtil;
import com.liferay.petra.sql.dsl.query.sort.OrderByExpression;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ClaimTrackerService {
	static final Log _log = LogFactoryUtil.getLog(ClaimTrackerService.class);
	static final String OBJECT_DEFINITION_NAME = "C_ClaimTracker";
	
	static ObjectEntry createObjectEntry(Map<String, Serializable> values, ObjectDefinition objectDefinition) {
		try {
            User defaultUser = UserLocalServiceUtil.getDefaultUser(objectDefinition.getCompanyId());
            ServiceContext serviceContext = new ServiceContext();

            return ObjectEntryLocalServiceUtil.addObjectEntry(
                defaultUser.getUserId(),
                0,
                objectDefinition.getObjectDefinitionId(),
                values,
                serviceContext
            );
        } catch (PortalException e) {
            throw new RuntimeException("Error creating ObjectEntry", e);
        }
	}
	
	static ObjectEntry updateObjectEntry(long objectEntryId, Map<String, Serializable> values, ObjectDefinition objectDefinition) {
		try {
            User defaultUser = UserLocalServiceUtil.getDefaultUser(objectDefinition.getCompanyId());
            ServiceContext serviceContext = new ServiceContext();
            
            return ObjectEntryLocalServiceUtil.updateObjectEntry(
            		defaultUser.getUserId(),
            		objectEntryId,
            		values,
            		serviceContext
            );
        } catch (PortalException e) {
            throw new RuntimeException("Error Update ObjectEntry", e);
        }
	}
	
	static ObjectEntry deleteObjectEntry(long objectEntryId) {
		try {            
            return ObjectEntryLocalServiceUtil.deleteObjectEntry(objectEntryId);
        } catch (PortalException e) {
            throw new RuntimeException("Error Update ObjectEntry", e);
        }
	}
	
	static List<ClaimTrackerModel> fetchEntries(String[] selectedObjectFieldNames, String searchTerm, int limit) {
		List<ClaimTrackerModel> entries = new ArrayList<>();
        long companyId = getDefaultCompanyId();
        ObjectDefinition objectDefinition = fetchObjectDefinition(companyId);

        if (objectDefinition == null) return entries;

        try {
            List<Map<String, Serializable>> objectEntries = ObjectEntryLocalServiceUtil.getValuesList(
                    0, companyId, 0, objectDefinition.getObjectDefinitionId(),
                    selectedObjectFieldNames, null, searchTerm, 0, limit, new OrderByExpression[0]
            );
            
            for (Map<String, Serializable> entry : objectEntries) {
            	entries.add(mapEntryToClaimTrackerModel(entry));
            }
        } catch (PortalException e) {
            _log.error("Error fetching object entries: " + e.getMessage(), e);
        }

        return entries;
	}
	
	static List<ClaimTrackerModel> fetchByPaginatedEntries(String[] selectedObjectFieldNames, String searchTerm, int currentPage, int pageSize) {
	    List<ClaimTrackerModel> entries = new ArrayList<>();
	    long companyId = getDefaultCompanyId();
	    ObjectDefinition objectDefinition = fetchObjectDefinition(companyId);

	    if (objectDefinition == null) return entries;

	    try {
	        int start = (currentPage - 1) * pageSize;
	        int end = start + pageSize;

	        // Fetch total record count for pagination handling
	        int totalRecords = ObjectEntryLocalServiceUtil.getValuesListCount(
	            0, companyId, 0, objectDefinition.getObjectDefinitionId(), null, searchTerm
	        );

	        // Fetch paginated results
	        List<Map<String, Serializable>> objectEntries = ObjectEntryLocalServiceUtil.getValuesList(
	            0, companyId, 0, objectDefinition.getObjectDefinitionId(),
	            selectedObjectFieldNames, null, searchTerm, start, end, new OrderByExpression[0]
	        );

	        for (Map<String, Serializable> entry : objectEntries) {
	            entries.add(mapEntryToClaimTrackerModel(entry));
	        }

	        // Optional: Log pagination details
	        _log.info("Fetched " + entries.size() + " records out of " + totalRecords + " total records.");

	    } catch (PortalException e) {
	        _log.error("Error fetching object entries: " + e.getMessage(), e);
	    }

	    return entries;
	}

	
	static ClaimTrackerModel mapObjectEntryToClaimTrackerModel(ObjectEntry objectEntry) {
        ClaimTrackerModel claimTrackerEntry = new ClaimTrackerModel();
        
        claimTrackerEntry.setClaimReferenceNo((String) objectEntry.getValues().get("claimReferenceNo"));
        claimTrackerEntry.setPolicyNo((String) objectEntry.getValues().get("policyNo"));
        claimTrackerEntry.setNameOfLA((String) objectEntry.getValues().get("nameOfLA"));
        claimTrackerEntry.setIntimationDate((String) objectEntry.getValues().get("intimationDate"));
        claimTrackerEntry.setDecisionDate((String) objectEntry.getValues().get("decisionDate"));
        claimTrackerEntry.setCauseOfDeath((String) objectEntry.getValues().get("causeOfDeath"));
        claimTrackerEntry.setFinalDecision((String) objectEntry.getValues().get("finalDecision"));
        claimTrackerEntry.setClaimType((String) objectEntry.getValues().get("claimType"));
        
        //Default fields
        claimTrackerEntry.setObjectEntryId(objectEntry.getObjectEntryId());
        claimTrackerEntry.setExternalReferenceCode(objectEntry.getExternalReferenceCode());
        claimTrackerEntry.setCreateDate(objectEntry.getCreateDate());
        claimTrackerEntry.setModifiedDate(objectEntry.getModifiedDate());
        claimTrackerEntry.setStatus(objectEntry.getStatus());
        claimTrackerEntry.setUserName(objectEntry.getUserName());
        return claimTrackerEntry;
    }
	
	static ClaimTrackerModel mapEntryToClaimTrackerModel(Map<String, Serializable> entry) {
        ClaimTrackerModel claimTrackerEntry = new ClaimTrackerModel();
        
        claimTrackerEntry.setClaimReferenceNo((String) entry.get("claimReferenceNo"));
        claimTrackerEntry.setPolicyNo((String) entry.get("policyNo"));
        claimTrackerEntry.setNameOfLA((String) entry.get("nameOfLA"));
        claimTrackerEntry.setIntimationDate((String) entry.get("intimationDate"));
        claimTrackerEntry.setDecisionDate((String) entry.get("decisionDate"));
        claimTrackerEntry.setCauseOfDeath((String) entry.get("causeOfDeath"));
        claimTrackerEntry.setFinalDecision((String) entry.get("finalDecision"));
        claimTrackerEntry.setClaimType((String) entry.get("claimType"));
        
        //Default fields
        claimTrackerEntry.setC_claimTrackerId((Long) entry.get("c_claimTrackerId"));
        claimTrackerEntry.setObjectEntryId((Long) entry.get("objectEntryId"));
        claimTrackerEntry.setUserName((String) entry.get("userName"));
        claimTrackerEntry.setCreateDate((Date) entry.get("createDate"));
        claimTrackerEntry.setModifiedDate((Date) entry.get("modifiedDate"));
        claimTrackerEntry.setExternalReferenceCode((String) entry.get("externalReferenceCode"));
        claimTrackerEntry.setStatus((Integer) entry.get("status"));
        return claimTrackerEntry;
    }
	
	static ObjectDefinition fetchObjectDefinition(long companyId) {
        ObjectDefinition objectDefinition = ObjectDefinitionLocalServiceUtil.fetchObjectDefinition(companyId, OBJECT_DEFINITION_NAME);
        if (objectDefinition == null) {
            _log.error("Object Definition not found.");
        }
        return objectDefinition;
    }

	static long getDefaultCompanyId() {
        try {
            Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
            return company.getCompanyId();
        } catch (Exception e) {
            _log.error("Exception in getting company Id: " + e.getMessage());
            return 0;
        }
    }

	static User getDefaultUser() {
        try {
            return UserLocalServiceUtil.getDefaultUser(getDefaultCompanyId());
        } catch (Exception e) {
            _log.error("Exception in getting default user: " + e.getMessage());
            return null;
        }
    }
}
