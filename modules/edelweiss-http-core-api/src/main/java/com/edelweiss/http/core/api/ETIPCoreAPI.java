package com.edelweiss.http.core.api;

import com.edelweiss.http.core.exception.ETIPSystemException;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.Map;

/**
 * 
 * @author Ravi Shah
 *
 */
public interface ETIPCoreAPI {
	
	/**
	 * @author Ravi Shah
	 * @param params
	 * @return response of the GET call
	 * @throws ETIPSystemException
	 */
	public JSONObject callGetAPI(Map<String, String> params,String locationURL,boolean isBearerRequired,String userName,String password) throws ETIPSystemException;
	
	/**
	 * @author Ravi Shah
	 * @param params
	 * @return response of the POST call
	 * @throws ETIPSystemException
	 */
	public JSONObject callPostAPI(JSONObject params,String locationURL,boolean isAutorizationRequired,String userName,String password) throws ETIPSystemException;
	
	
	/**
	 * @param locationURL
	 * @param userName
	 * @param password
	 * @return response of the Access Token
	 * @throws ETIPSystemException
	 */
	public String generateOauthToken(String locationURL,String userName, String password) throws ETIPSystemException;

	/**
	 * 
	 * @param params
	 * @param locationURL
	 * @param isAutorizationRequired
	 * @param userName
	 * @param password
	 * @return
	 * @throws ETIPSystemException
	 */
	public JSONObject callPutAPI(JSONObject params, String locationURL, boolean isAutorizationRequired, String userName,
			String password) throws ETIPSystemException;
	
	
	/**
	 * Delete Service
	 * @param params
	 * @param locationURL
	 * @param isAutorizationRequired
	 * @param userName
	 * @param password
	 * @return
	 * @throws ETIPSystemException
	 */
	public JSONObject callDeleteAPI(String locationURL, boolean isAutorizationRequired, String userName, String password) throws ETIPSystemException;

}
