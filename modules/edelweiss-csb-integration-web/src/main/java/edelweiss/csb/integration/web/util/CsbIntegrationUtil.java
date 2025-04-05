package edelweiss.csb.integration.web.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.http.HttpAuthorizationHeader;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.ContentTypes;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import edelweiss.csb.integration.web.model.CsbIntegrationResponseData;


public class CsbIntegrationUtil {
	
	public static JSONObject sendCsbOtp(String ApplicationNumber,String Name, String Resend , String Usedfor, String Mobileno, String EmailAddress, String DocURL, String locationURL ) {
        
        Map<String, String> mapformMap = new HashMap<>();
        mapformMap.put("ApplicationNumber", ApplicationNumber);
        mapformMap.put("Name", Name);
        mapformMap.put("Resend", Resend);
        mapformMap.put("Usedfor", Usedfor);
        mapformMap.put("Mobileno", Mobileno);
        mapformMap.put("EmailAddress", EmailAddress);
        mapformMap.put("DocURL", DocURL);
        
        JSONObject requestJson = JSONFactoryUtil.createJSONObject(mapformMap);
        _log.info("RequestBody::::::::"+ requestJson);
        _log.info("docURL::::::::"+ DocURL);
        _log.info("EndPoint URL:::"+ locationURL);
        
CloseableHttpClient httpClient = HttpClients.createDefault();
JSONObject apiResponse = JSONFactoryUtil.createJSONObject();
		try {
			
			HttpPost httpPost = new HttpPost(locationURL);
			
			httpPost.addHeader(HttpHeaders.ACCEPT, ContentTypes.APPLICATION_JSON);
			httpPost.addHeader(HttpHeaders.CONTENT_TYPE, ContentTypes.APPLICATION_JSON);
			
			 
			 httpPost.setHeader(HttpHeaders.AUTHORIZATION, HttpAuthorizationHeader.SCHEME_BEARER + StringPool.SPACE );
				 httpPost.setEntity(new StringEntity(requestJson.toString()));
			
			 
			 
			 CloseableHttpResponse response = httpClient.execute(httpPost);
			 int statusCode = response.getStatusLine().getStatusCode();
			 _log.info("status on SendOTP::::"+ statusCode);
			 String apiResponse1 = StringPool.BLANK;
			 
			 try {
				    HttpEntity entity = response.getEntity();
				    
				    apiResponse1 = EntityUtils.toString(entity);
				    _log.debug("*********apiResponse: "+apiResponse);
				    _log.debug("*********locationURL: "+locationURL);
				    _log.debug(" >>>>>>statusCode from post >>>>>>> " + statusCode);
					_log.debug(" >>>>>>httpresponse.getStatusLine() from post >>>>>>> " + response.getStatusLine());
				} finally {
					response.close();
				}
				
			 
				_log.debug("HttpAPIClient >>> executePost >>> End Of Invocation :::");
				_log.debug("*********locationURL: "+locationURL);
				

				return JSONFactoryUtil.createJSONObject(apiResponse1);
			 
		} catch (IOException | JSONException e1) {
			_log.debug("*********locationURL: "+locationURL);
			
			
		}finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				_log.error("HttpAPIClient >>> Error while closing  HttpAPIClient executePost::: "+e.getMessage());
			}
		}
		return apiResponse;
	}
	
	
	
	
	private static final Log _log = LogFactoryUtil.getLog(CsbIntegrationUtil.class);
	private static final String X_API_KEY = "x-api-key";




	/*public static JSONObject generateTinyUrl(String previewURL, String tinyURLEndpoint, boolean isBearerRequired,String apiKey, String userName, String password) {
		// TODO Auto-generated method stub
			long startTime = new Date().getTime();
			_log.debug("*********previewURL: "+previewURL);
			_log.debug("*********tinyURLEndpoint: "+previewURL);
			_log.debug("*********apikey: "+apiKey);
			_log.debug("*********userName: "+userName);
			_log.debug("*********password: "+password);
			
			CloseableHttpClient httpclient = HttpClients.custom().build();
			
			try {
				//Set the location - URL that needs to be invoked
				HttpDelete httpDelete = new HttpDelete(tinyURLEndpoint);
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
		}*/
	// setCsbIntegrationValues 

    public static void setCsbIntegrationValues(Map<String, Serializable> values, CsbIntegrationResponseData csbIntegrationResponseData) {
        csbIntegrationResponseData.setcSBAccountNo((String) values.get("cSBAccountNo"));
        csbIntegrationResponseData.setGender((String) values.get("gender"));
        csbIntegrationResponseData.setClientDOB((String) values.get("clientDOB"));
        csbIntegrationResponseData.setNeoApplicationNo((String) values.get("neoApplicationNo"));
        csbIntegrationResponseData.setBranchName((String) values.get("branchName"));
        csbIntegrationResponseData.setBranchCode((String) values.get("branchCode"));
        csbIntegrationResponseData.setPinCode((String) values.get("pinCode"));
        csbIntegrationResponseData.setRiskCoverOpted((String) values.get("riskCoverOpted"));
        csbIntegrationResponseData.setOccupation((String) values.get("occupation"));
        csbIntegrationResponseData.setNomineeName((String) values.get("nomineeName"));
        csbIntegrationResponseData.setRelation((String) values.get("relation"));
        csbIntegrationResponseData.setEmailId((String) values.get("emailId"));
        csbIntegrationResponseData.setMobileNo((String) values.get("mobileNo"));
        csbIntegrationResponseData.setPremium((String) values.get("premium"));
    }
}
