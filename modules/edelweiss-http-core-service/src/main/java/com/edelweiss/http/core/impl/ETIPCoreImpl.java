package com.edelweiss.http.core.impl;

import com.edelweiss.http.core.api.ETIPCoreAPI;
import com.edelweiss.http.core.component.service.APIClientFactory;
import com.edelweiss.http.core.exception.ETIPSystemException;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * 
 * @author Ravi Shah
 *
 */
@Component(immediate = true, property = {
}, service = ETIPCoreAPI.class)
public class ETIPCoreImpl implements ETIPCoreAPI {

	@Override
	public JSONObject callGetAPI(Map<String, String> params,String locationURL,boolean isBearerRequired,String userName,String password) throws ETIPSystemException {
		return APIClientFactory.getInstance().executeGet(params, locationURL, isBearerRequired, userName, password);
	}

	@Override
	public JSONObject callPostAPI(JSONObject params,String locationURL,boolean isAutorizationRequired,String userName,String password) throws ETIPSystemException {
		return APIClientFactory.getInstance().executePost(params, locationURL, isAutorizationRequired, userName, password);
	}

	@Override
	public String generateOauthToken(String locationURL,String userName, String password) throws ETIPSystemException {
		return APIClientFactory.getInstance().generateAccessToken(locationURL,userName,password);
	}
	
	@Override
	public JSONObject callPutAPI(JSONObject params, String locationURL, boolean isAutorizationRequired, String userName,
			String password) throws ETIPSystemException {
		
		return APIClientFactory.getInstance().executePut(params, locationURL, isAutorizationRequired, userName, password);
	}

	@Override
	public JSONObject callDeleteAPI(String locationURL, boolean isBearerRequired, String userName, String password) throws ETIPSystemException {
		return APIClientFactory.getInstance().executeDelete(locationURL, isBearerRequired, userName, password);
	}
}
