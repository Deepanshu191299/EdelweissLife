package in.edelweiss.proposal.form.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.webcache.WebCacheException;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.portal.kernel.webcache.WebCachePoolUtil;

import in.edelweiss.common.util.EdelweissAPIUtil;
import in.edelweiss.proposal.form.company.model.CompanyList;

public class CompanyListCacheItem implements WebCacheItem {

	private static final long _REFRESH_TIME = Time.MINUTE * 1140; // 24 Hours

	public static CompanyList get(String companyListURL) {
		return (CompanyList) WebCachePoolUtil.get(CompanyList.class.getName(),
				new CompanyListCacheItem(companyListURL));
	}

	public CompanyListCacheItem(String companyListURL) {
		_companyListURL = companyListURL;
	}

	@Override
	public CompanyList convert(String key) throws WebCacheException {

		return getCompanyList();
	}

	@Override
	public long getRefreshTime() {
		return _REFRESH_TIME;
	}

	private CompanyList getCompanyList() {

		logger.info(" getCompanyList .........."+_companyListURL);
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			String response = EdelweissAPIUtil.callPJGetAPI(_companyListURL);
			CompanyList companyList = objectMapper.readValue(response, CompanyList.class);

			return companyList;
		} catch (Exception e) {
			logger.error(" Exception while fetching company list " + e);
			CompanyList companyList = new CompanyList();
			companyList.setStatus(false);

			return companyList;
		}

	}

	private static String _companyListURL;
	private static final Log logger = LogFactoryUtil.getLog(CompanyListCacheItem.class);
}
