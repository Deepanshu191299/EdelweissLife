package in.edelweiss.proposal.form.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.webcache.WebCacheException;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.portal.kernel.webcache.WebCachePoolUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.edelweiss.common.util.EdelweissAPIUtil;
import in.edelweiss.proposal.form.dropdown.model.Data;
import in.edelweiss.proposal.form.dropdown.model.DropdownMaster;
import in.edelweiss.proposal.form.dropdown.model.Master;

public class DropdownMasterCacheItem implements WebCacheItem {

	private static final long _REFRESH_TIME = Time.MINUTE * 1140;  // 24 Hours

	public static Map<String, List<Data>> get(String getDataURL) {
		return (Map<String, List<Data>>) WebCachePoolUtil.get(DropdownMaster.class.getName(),
				new DropdownMasterCacheItem(getDataURL));
	}

	public DropdownMasterCacheItem(String getDataURL) {
		_getDataURL = getDataURL;
	}

	@Override
	public Map<String, List<Data>> convert(String key) throws WebCacheException {

		return getDropdownMaster();
	}

	@Override
	public long getRefreshTime() {
		return _REFRESH_TIME;
	}

	private Map<String, List<Data>> getDropdownMaster() {

		logger.info(" getDropdownMaster .........." + _getDataURL);

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			String response = EdelweissAPIUtil.callPJPostAPI(_getDataURL);
			DropdownMaster getData = objectMapper.readValue(response, DropdownMaster.class);
			Map<String, List<Data>> masterMap = getMapFromMaster(getData);
			return masterMap;
		} catch (Exception e) {
			logger.error(" Exception while fetching master dropdown list " + e);

			return null;
		}

	}

	private Map<String, List<Data>> getMapFromMaster(DropdownMaster getData) {
		Map<String, List<Data>> masterMap = new HashMap<>();
		List<Master> masterList = getData.getResponseData().getMaster();

		for (int i = 0; i < masterList.size(); i++) {
			if(masterList.get(i).getType().contains("Job Description")) {
				masterMap.put("Nature of Duty", masterList.get(i).getData());
			}else {
				masterMap.put(masterList.get(i).getType(), masterList.get(i).getData());
			}
		}

		return masterMap;
	}

	private static String _getDataURL;
	private static final Log logger = LogFactoryUtil.getLog(DropdownMasterCacheItem.class);
}
