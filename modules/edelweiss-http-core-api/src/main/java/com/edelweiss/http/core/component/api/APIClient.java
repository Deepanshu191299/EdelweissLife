package com.edelweiss.http.core.component.api;

import com.edelweiss.http.core.exception.ETIPSystemException;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.Map;

/**
 * @author Ravi Shah
 */
public interface APIClient {
	
	
	/**
	 * @param params
	 * @param locationURL
	 * @param isBearerRequired
	 * @param userName
	 * @param password
	 * @return response of the GET call
	 * @throws ETIPSystemException
	 */
	public JSONObject executeGet(Map<String, String> params,String locationURL,boolean isBearerRequired,String userName,String password) throws ETIPSystemException;
	
	
	/**
	 * @param params
	 * @param locationURL
	 * @param isAutorizationRequired
	 * @param userName
	 * @param password
	 * @return response of the POST call
	 * @throws ETIPSystemException
	 */
	public JSONObject executePost(JSONObject params,String locationURL,boolean isAutorizationRequired,String userName,String password) throws ETIPSystemException;
	
	
	
	/**
	 * @param locationURL
	 * @param userName
	 * @param password
	 * @return response of the Access Token
	 * @throws ETIPSystemException
	 */
	public String generateAccessToken(String locationURL,String userName,String password) throws ETIPSystemException;


	/**
	 * 
	 * @param requestParams
	 * @param locationURL
	 * @param isAutorizationRequired
	 * @param userName
	 * @param password
	 * @return
	 * @throws ETIPSystemException
	 */
	public JSONObject executePut(JSONObject requestParams, String locationURL, boolean isAutorizationRequired, String userName,
			String password) throws ETIPSystemException;
	
	
	/**
	 * Delete Service by Id
	 * @param locationURL
	 * @param isBearerRequired
	 * @param userName
	 * @param password
	 * @return
	 * @throws ETIPSystemException
	 */
	public JSONObject executeDelete(String locationURL, boolean isBearerRequired, String userName, String password) throws ETIPSystemException;
	
	/**
	 * @param locationURL
	 * @param userName
	 * @param password
	 * @return response of the Access Token
	 * @throws ETIPSystemException
	 */
	public String generateLRAccessToken() throws ETIPSystemException;
	
}