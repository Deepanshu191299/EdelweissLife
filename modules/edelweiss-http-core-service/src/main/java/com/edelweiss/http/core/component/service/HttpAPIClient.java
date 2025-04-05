package com.edelweiss.http.core.component.service;

import com.edelweiss.http.core.component.api.APIClient;
import com.edelweiss.http.core.exception.ETIPSystemException;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.http.HttpAuthorizationHeader;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.osgi.service.component.annotations.Component;

import in.edelweiss.system.configurations.CommonConfiguration;
import in.edelweiss.system.configurations.EdelweissLRBasicAuthConfiguration;
import in.edelweiss.tokio.common.util.EdelweissConfigurationUtil;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;

/**
 * @author Ravi
 */
@Component(immediate = true, property = {
}, service = APIClient.class)
public class HttpAPIClient implements APIClient {

	private static final Log _log = LogFactoryUtil.getLog(HttpAPIClient.class);
	private static final String X_API_KEY = "x-api-key";
	
	private String liferayOAuthToken = null;
	private long liferayOAuthTokenExpireIn;
	private static final long TOKEN_REPLACEMENT_IN_MILLISECONDS = (long)2 * 60 * 1000; // Token Replacement before Token expiration time - 2 Minute

	@Override
	public JSONObject executeGet(Map<String, String> params, String locationURL, boolean isBearerRequired,
			String userName, String password) throws ETIPSystemException {
		long startTime = new Date().getTime();
		_log.debug("*********locationURL: "+locationURL);
		
		// Create an HttpClient object
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			// Create an HttpGet object
			HttpGet httpget = new HttpGet(locationURL);
			
			String authorizationStr = userName + ":" + password;
			byte[] authStrEncBytes = Base64.getEncoder().encode(authorizationStr.getBytes());
			String authStrEnc = new String(authStrEncBytes);
			
			URIBuilder uriBuilder = new URIBuilder(httpget.getURI());
			for(Map.Entry<String, String> entry : params.entrySet()) {
				uriBuilder.addParameter(entry.getKey(), entry.getValue());
			}
			httpget.setURI(uriBuilder.build());
			
			 if(isBearerRequired) {
				 _log.debug("Authentication with Provided OAuth Token");
					httpget.setHeader(HttpHeaders.AUTHORIZATION, HttpAuthorizationHeader.SCHEME_BEARER + StringPool.SPACE + password);
					httpget.addHeader(X_API_KEY, userName);
				}
			 else if(Validator.isNotNull(userName) && Validator.isNotNull(password)){
				 _log.debug("Authentication  with Basic Auth");
					httpget.setHeader(HttpHeaders.AUTHORIZATION, HttpAuthorizationHeader.SCHEME_BASIC + StringPool.SPACE + authStrEnc);
			}
			else {
				 _log.debug("Authentication with Liferay OAuth Toekn");
					httpget.setHeader(HttpHeaders.AUTHORIZATION, HttpAuthorizationHeader.SCHEME_BEARER + StringPool.SPACE + generateLRAccessToken());
			}
			 
			// Execute the Get request
			CloseableHttpResponse httpresponse = httpclient.execute(httpget);
			int statusCode = httpresponse.getStatusLine().getStatusCode();
			String apiResponse = "";
			try {
				HttpEntity entity = httpresponse.getEntity();
				apiResponse = EntityUtils.toString(entity);
				_log.debug(" >>>>>>statusCode from get >>>>>>> " + statusCode);
				_log.debug(" >>>>>>httpresponse.getStatusLine() from get >>>>>>> " + httpresponse.getStatusLine());
			} finally {
				httpresponse.close();
			}

			_log.debug(" >>>>>>httpresponse.getStatusLine()>>>>>>> " +apiResponse);
			_log.debug("*********locationURL: "+locationURL);
			_log.debug("**************API Call response time: "+(new Date().getTime() - startTime));
			
			return JSONFactoryUtil.createJSONObject(apiResponse);
		} catch (Exception e) {
			_log.debug("*********locationURL: "+locationURL);
			_log.debug("**************API Call response time: "+(new Date().getTime() - startTime));
			throw new ETIPSystemException(e);
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				_log.error("HttpAPIClient >>> Error while closing  HttpAPIClient  executeGet::: "+e.getMessage());
			}
		}
	}
	
	@Override
	public JSONObject executePost(JSONObject requestParams, String locationURL, boolean isBearerRequired,
			String userName, String password) throws ETIPSystemException {
		
		long startTime = new Date().getTime();
		_log.debug("*********locationURL: "+locationURL);
		_log.debug("*********requestParams: "+requestParams);
		_log.debug("*********XAPI-Key: "+userName);
		_log.debug("*********password: "+password);
		_log.debug("*******isBearerRequired: "+ isBearerRequired);
		_log.debug("HttpAPIClient >>> executePost >>> Invoked :::");
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		try {
			
			HttpPost httpPost = new HttpPost(locationURL);
			 
			 CommonConfiguration commonConfiguration = EdelweissConfigurationUtil.getCommonConfiguration();
				if (Validator.isNotNull(commonConfiguration)
						&& Validator.isNotNull(commonConfiguration.getHttpRequestTimeout())) {
					
					 RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(Integer.parseInt(commonConfiguration.getHttpRequestTimeout()))
						      .build();
					 
					 httpPost.setConfig(requestConfig);
					 
					_log.debug("Request Timeout updated to: " + commonConfiguration.getHttpRequestTimeout());
				}
			
			httpPost.addHeader(HttpHeaders.ACCEPT, ContentTypes.APPLICATION_JSON);
			httpPost.addHeader(HttpHeaders.CONTENT_TYPE, ContentTypes.APPLICATION_JSON);
			
			 if(isBearerRequired) {
				 _log.debug("Authentication with Provided OAuth Token");
				 httpPost.setHeader(HttpHeaders.AUTHORIZATION, HttpAuthorizationHeader.SCHEME_BEARER + StringPool.SPACE + password);
					httpPost.addHeader(X_API_KEY, userName);
					httpPost.setEntity(new StringEntity(requestParams.toString()));
				}
			 else if(Validator.isNotNull(userName) && Validator.isNotNull(password)){
				 _log.debug("Authentication  with Basic Auth");
				 httpPost.setEntity(new StringEntity(requestParams.toString()));
					String authorizationStr = userName + ":" + password;
					byte[] authStrEncBytes = Base64.getEncoder().encode(authorizationStr.getBytes());
					String authStrEnc = new String(authStrEncBytes);
				 httpPost.setHeader(HttpHeaders.AUTHORIZATION, HttpAuthorizationHeader.SCHEME_BASIC + StringPool.SPACE + authStrEnc);
			}
			else {
				 _log.debug("Authentication with Liferay OAuth Toekn");
				 httpPost.setHeader(HttpHeaders.AUTHORIZATION, HttpAuthorizationHeader.SCHEME_BEARER + StringPool.SPACE + generateLRAccessToken());
				 httpPost.setEntity(new StringEntity(requestParams.toString()));
			}
			 
			 
			 CloseableHttpResponse response = httpClient.execute(httpPost);
			 int statusCode = response.getStatusLine().getStatusCode();
			 String apiResponse = StringPool.BLANK;
			 
			 try {
				    HttpEntity entity = response.getEntity();
				    
				    apiResponse = EntityUtils.toString(entity);
				    _log.debug("*********apiResponse: "+apiResponse);
				    _log.debug("*********locationURL: "+locationURL);
				    _log.debug(" >>>>>>statusCode from post >>>>>>> " + statusCode);
					_log.debug(" >>>>>>httpresponse.getStatusLine() from post >>>>>>> " + response.getStatusLine());
				} finally {
					response.close();
				}
				
			 
				_log.debug("HttpAPIClient >>> executePost >>> End Of Invocation :::");
				_log.debug("*********locationURL: "+locationURL);
				_log.debug("**************API Call response time: "+(new Date().getTime() - startTime));

				return JSONFactoryUtil.createJSONObject(apiResponse);
			 
		} catch (IOException | JSONException e1) {
			_log.debug("*********locationURL: "+locationURL);
			_log.debug("**************API Call response time: "+(new Date().getTime() - startTime));
			throw new ETIPSystemException(e1);
		}finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				_log.error("HttpAPIClient >>> Error while closing  HttpAPIClient executePost::: "+e.getMessage());
			}
		}
	}
	@Override
	public String generateAccessToken(String locationURL,String userName,String password) throws ETIPSystemException {
		long startTime = new Date().getTime();
		_log.debug("*********locationURL: "+locationURL);
		Http.Options options = new Http.Options();
		options.addHeader(HttpHeaders.CONTENT_TYPE, ContentTypes.APPLICATION_X_WWW_FORM_URLENCODED);
		options.setPost(true);
		options.setLocation(locationURL);
	    String authStr=userName+":"+password;
	    byte[] authStrEncBytes= Base64.getEncoder().encode(authStr.getBytes());
	    String authStrEnc = new String(authStrEncBytes);
	    options.addHeader(HttpHeaders.AUTHORIZATION, HttpAuthorizationHeader.SCHEME_BASIC + StringPool.SPACE + authStrEnc);
		try {
			String response = HttpUtil.URLtoString(options);
			if(Validator.isNotNull(response)) {
				_log.debug("*********locationURL: "+locationURL);
				_log.debug("**************API Call response time: "+(new Date().getTime() - startTime));
			}
			return response;
		} catch (IOException e) {
			_log.debug("*********locationURL: "+locationURL);
			_log.debug("**************API Call response time: "+(new Date().getTime() - startTime));
			throw new ETIPSystemException(e);
		}
	}
	
	@Override
	public JSONObject executePut(JSONObject requestParams, String locationURL, boolean isAutorizationRequired, String userName,
			String password) throws ETIPSystemException {
		long startTime = new Date().getTime();
		_log.debug("*********locationURL: "+locationURL);
		
		
		CloseableHttpClient httpclient = HttpClients.custom().build();

		try {
			
			//set the location - URL that needs to be invoked
			HttpPut httpPut = new HttpPut(locationURL);
			httpPut.setHeader(HttpHeaders.ACCEPT, ContentTypes.APPLICATION_JSON);
			httpPut.setHeader(HttpHeaders.CONTENT_TYPE, ContentTypes.APPLICATION_JSON);
		
			if (isAutorizationRequired) {
				_log.debug("Authentication with Provided OAuth Token");
				httpPut.setHeader(HttpHeaders.AUTHORIZATION,
						HttpAuthorizationHeader.SCHEME_BEARER + StringPool.SPACE + password);
				httpPut.addHeader(X_API_KEY, userName);
				httpPut.setEntity(new StringEntity(requestParams.toString()));
			} else if (Validator.isNotNull(userName) && Validator.isNotNull(password)) {
				_log.debug("Authentication  with Basic Auth");
				HttpEntity apiEntity = new ByteArrayEntity(requestParams.toString().getBytes("UTF-8"));
				httpPut.setEntity(apiEntity);
				String authorizationStr = userName + ":" + password;
				byte[] authStrEncBytes = Base64.getEncoder().encode(authorizationStr.getBytes());
				String authStrEnc = new String(authStrEncBytes);
				httpPut.setHeader(HttpHeaders.AUTHORIZATION,
						HttpAuthorizationHeader.SCHEME_BASIC + StringPool.SPACE + authStrEnc);
			} else {
				_log.debug("Authentication with Liferay OAuth Toekn");
				httpPut.setHeader(HttpHeaders.AUTHORIZATION,
						HttpAuthorizationHeader.SCHEME_BEARER + StringPool.SPACE + generateLRAccessToken());
				httpPut.setEntity(new StringEntity(requestParams.toString()));
			}
			 
			 
			CloseableHttpResponse response = httpclient.execute(httpPut);
			String responseJSON = StringPool.BLANK;
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			
			try {
				responseJSON = EntityUtils.toString(response.getEntity(), "UTF-8");
				_log.debug("HttpAPIClient >>> executePut >>> Response ::: " + responseJSON);
				jsonObject = JSONFactoryUtil.createJSONObject(responseJSON);
				_log.debug(" >>>>>>statusCode from put >>>>>>> " +  response.getStatusLine().getStatusCode());
				_log.debug(" >>>>>>httpresponse.getStatusLine() from put >>>>>>> " + response.getStatusLine());
			} finally {
				response.close();
			}
			
			_log.debug("**************API Final Parsed response: "+jsonObject);
			_log.debug("*********locationURL: "+locationURL);
			_log.debug("**************API Call response time: "+(new Date().getTime() - startTime));
			
			return jsonObject;
		} catch (IOException | JSONException e) {
			_log.debug("*********locationURL: "+locationURL);
			_log.debug("**************API Call response time: "+(new Date().getTime() - startTime));
			throw new ETIPSystemException(e);
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				_log.error("HttpAPIClient >>> Error while closing   HttpAPIClient executePut::: "+e.getMessage());
			}
		}
	}

	public JSONObject executeDelete(String locationURL, boolean isBearerRequired, String userName, String password) throws ETIPSystemException {
		long startTime = new Date().getTime();
		_log.debug("*********locationURL: "+locationURL);
		_log.debug("HttpAPIClient >>> executeDelete >>> Invoked :::");
		
		CloseableHttpClient httpclient = HttpClients.custom().build();
		
		try {
			//Set the location - URL that needs to be invoked
			HttpDelete httpDelete = new HttpDelete(locationURL);
			httpDelete.setHeader(HttpHeaders.ACCEPT, ContentTypes.APPLICATION_JSON);
			httpDelete.setHeader(HttpHeaders.CONTENT_TYPE, ContentTypes.APPLICATION_JSON);
		
			if (isBearerRequired) {
				_log.debug("Authentication with Provided OAuth Token");
				httpDelete.setHeader(HttpHeaders.AUTHORIZATION,
						HttpAuthorizationHeader.SCHEME_BEARER + StringPool.SPACE + password);
				httpDelete.addHeader(X_API_KEY, userName);
			} else if (Validator.isNotNull(userName) && Validator.isNotNull(password)) {
				_log.debug("Authentication  with Basic Auth");
				String authorizationStr = userName + ":" + password;
				byte[] authStrEncBytes = Base64.getEncoder().encode(authorizationStr.getBytes());
				String authStrEnc = new String(authStrEncBytes);
				httpDelete.setHeader(HttpHeaders.AUTHORIZATION,
						HttpAuthorizationHeader.SCHEME_BASIC + StringPool.SPACE + authStrEnc);
			} else {
				_log.debug("Authentication with Liferay OAuth Toekn");
				httpDelete.setHeader(HttpHeaders.AUTHORIZATION,
						HttpAuthorizationHeader.SCHEME_BEARER + StringPool.SPACE + generateLRAccessToken());
			}

			CloseableHttpResponse response = httpclient.execute(httpDelete);
			String responseJSON = StringPool.BLANK;
			try {
				responseJSON = EntityUtils.toString(response.getEntity(), "UTF-8");
				_log.debug("HttpAPIClient >>> executeDelete >>> Response ::: " + responseJSON);
				_log.debug(" >>>>>>statusCode from delete >>>>>>> " +  response.getStatusLine().getStatusCode());
				_log.debug(" >>>>>>httpresponse.getStatusLine() from delete >>>>>>> " + response.getStatusLine());
			} finally {
				response.close();
			}
			_log.debug("*********locationURL: "+locationURL);
			_log.debug("**************API Call response time: "+(new Date().getTime() - startTime));
			return JSONFactoryUtil.createJSONObject(responseJSON);
		} catch (IOException | JSONException e) {
			_log.debug("*********locationURL: "+locationURL);
			_log.debug("**************API Call response time: "+(new Date().getTime() - startTime));
			throw new ETIPSystemException(e);
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				_log.error("HttpAPIClient >>> Error while closing HttpAPIClient executeDelete ::: "+e.getMessage());
			}
		}
	}

	@Override
	public String generateLRAccessToken() throws ETIPSystemException {
		long startTime = new Date().getTime();
		
		_log.debug("HttpAPIClient >>> generateLRAccessToken >>> Liferay OAuth2 Token :  " + this.liferayOAuthToken);
		_log.debug("HttpAPIClient >>> generateLRAccessToken >>> Current Time in Millisecond : " + System.currentTimeMillis());
		_log.debug("HttpAPIClient >>> generateLRAccessToken >>> Token Expiration Time in Millisecond : " + this.liferayOAuthTokenExpireIn);
		if(!isLiferayOAuthTokenExpired()) {
			
			_log.debug("HttpAPIClient >>> generateLRAccessToken >>> Liferay OAuth token is not expried or null.");
			_log.debug("**************API Call response time: "+(new Date().getTime() - startTime));
			return this.liferayOAuthToken;
		}
		_log.debug("HttpAPIClient >>> generateLRAccessToken >>> Liferay OAuth token is expried or null. Generating a new One");
		EdelweissLRBasicAuthConfiguration commonConfiguration = EdelweissConfigurationUtil
				.getEdelweissLRBasicAuthConfiguration();
		CloseableHttpClient client = HttpClients.createDefault();
		try {
			HttpPost httpPost = new HttpPost(commonConfiguration.getLROauthURL());
			httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("client_id", commonConfiguration.getLRClientId()));
			params.add(new BasicNameValuePair("client_secret", commonConfiguration.getLRClientSecrete()));
			params.add(new BasicNameValuePair("grant_type", "client_credentials"));
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			httpPost.setHeader("Accept", "application/json");
			CloseableHttpResponse response = client.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				try {
					HttpEntity entity = response.getEntity();
					String apiResponse = EntityUtils.toString(entity);
					JSONObject jsonObject = JSONFactoryUtil.createJSONObject(apiResponse);
					_log.debug(" jsonObject......... " + jsonObject);
					
					this.liferayOAuthToken = jsonObject.getString(EdelweissCommonConstants.ACCESS_TOKEN);
					long expireIn = jsonObject.getLong(EdelweissCommonConstants.EXPIRE_IN);
					this.liferayOAuthTokenExpireIn = System.currentTimeMillis() + (expireIn * 1000);
					
					_log.debug("**************API Call response time: "+(new Date().getTime() - startTime));
					return jsonObject.getString(EdelweissCommonConstants.ACCESS_TOKEN);
				} finally {
					response.close();
				}
			} else {
				_log.warn("Invalid response code for getAccessTokenUrl: " + statusCode);
			}
		} catch (IOException | JSONException e) {
			_log.error(" LR Oauth Exception >>>>>>>>>>>>>>>>>>>>>>>> :: ", e);
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				_log.error("HttpAPIClient >>> Error while closing client generateLRAccessToken  ::: "+e.getMessage());
			}
		}
		_log.debug("**************API Call response time: "+(new Date().getTime() - startTime));
		return null;
	}
	
	/**
	 * Checking Liferay OAuth2 Token is expired or not

	 * @return true is token is expired
	 */
	private boolean isLiferayOAuthTokenExpired() {
		if (this.liferayOAuthToken == null || (System.currentTimeMillis() + TOKEN_REPLACEMENT_IN_MILLISECONDS) > this.liferayOAuthTokenExpireIn) {
			_log.debug("EdelweissTokioCommonUtil >>> isQuoteJourneyTokenExpired >>> Quote Journey OAuth2 Token is expired......");
			return true;
		}  else {
			_log.debug("EdelweissTokioCommonUtil >>> isQuoteJourneyTokenExpired >>> Quote Journey OAuth2 Token is already exist.....");
			return false;
		}
	}
}