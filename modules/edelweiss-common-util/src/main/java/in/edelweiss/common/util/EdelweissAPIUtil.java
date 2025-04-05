package in.edelweiss.common.util;

import com.liferay.list.type.model.ListTypeDefinition;
import com.liferay.list.type.model.ListTypeEntry;
import com.liferay.list.type.service.ListTypeDefinitionLocalServiceUtil;
import com.liferay.list.type.service.ListTypeEntryLocalServiceUtil;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectDefinitionLocalServiceUtil;
import com.liferay.object.service.ObjectEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.StringEntity;

import in.edelweiss.common.contants.EdelweissAPIConstants;
import in.edelweiss.common.contants.ParameterConstants;
import in.edelweiss.system.configurations.ApiURLConfiguration;
import in.edelweiss.system.configurations.EdelweissGSTINOAuthConfiguration;
import in.edelweiss.system.configurations.OAuthCredentialsConfiguration;

/**
 * @author Abhijit AA
 * 
 * The class contains the API Implementation & Some API Related Constants Methods.
 */
public class EdelweissAPIUtil {

	/**
	 * private constructor to hide the implicit public one
	 * 
	 */
	private EdelweissAPIUtil() {

	}

	/**
	 *This method is used to call the all the get APIs.
	 *It will be based on the inputs whether to call which url 
	 *& what authorization & content type is requried.
	 *
	 *@param endpointURL
	 *@param contentType
	 *@param isBearerToken
	 *@param token
	 *@param xApiKey
	 *@param params
	 *
	 *@return Map<String, Object>
	 *
	 *@throws Exception
	 */	
	public static Map<String, Object> callGetAPI(String endpointURL, String contentType, 
			boolean isBearerToken, String token, String xApiKey, Map<String, String> params) throws Exception {

		Map<String, Object> responseMap = new HashMap<>();

		if(Validator.isNotNull(endpointURL) && !endpointURL.isBlank()) {

			CloseableHttpClient httpclient = HttpClients.custom().build();
			
			try {

				HttpGet httpGet = new HttpGet(endpointURL);

				if(Validator.isNotNull(contentType) && !contentType.isBlank()) {
					httpGet.setHeader(HttpHeaders.CONTENT_TYPE, contentType);
				}

				if(Validator.isNotNull(token) && !token.isBlank()) {
					if(isBearerToken) {
						httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
					}else {
						httpGet.setHeader(HttpHeaders.AUTHORIZATION, token);
					}
				}

				if(Validator.isNotNull(xApiKey) && !xApiKey.isBlank()) {
					httpGet.setHeader(EdelweissAPIConstants.X_API_KEY, xApiKey);
				}

				if(Validator.isNotNull(params)) {
					
					URIBuilder uriBuilder = new URIBuilder(httpGet.getURI());
					for(Map.Entry<String, String> entry : params.entrySet()) {
						uriBuilder.addParameter(entry.getKey(), entry.getValue());
					}
					
					httpGet.setURI(uriBuilder.build());
				}
				
				logger.debug("endpointURL - " + endpointURL);
				logger.debug("token - " + token);
				logger.debug("xApiKey - " + xApiKey);
				logger.debug("params - " + params);
				
				CloseableHttpResponse response = httpclient.execute(httpGet);
				int statusCode = 0;
				String content = StringPool.BLANK;
				
				try {
					statusCode = response.getStatusLine().getStatusCode();
					logger.debug("API Status - " + statusCode);
					
					//Get the Response Based on the Status 200
					content = EntityUtils.toString(response.getEntity(), "UTF-8");
				} finally {
					response.close();
				}
				
				responseMap.put("status", statusCode);
				responseMap.put("content", content);

				return responseMap;

			} catch (Exception exception) {
				logger.error("Exception in call get API " + exception.getMessage());
				if (logger.isDebugEnabled()) {
					logger.error(ParameterConstants.EXCEPTION, exception);
				}
			} finally {
				httpclient.close();
			}
		}else {
			throw new Exception("The Endpoint URL is Null or Empty");
		}

		return responseMap;
	}

	/**
	 *This method is used to call the all the Post APIs.
	 *It will be based on the inputs whether to call which url 
	 *& what authorization & content type is requried.
	 *
	 *@param endpointURL
	 *@param contentType
	 *@param isBearerToken
	 *@param token
	 *@param xApiKey
	 *@param entityJSON
	 *
	 *@return Map<String, Object>
	 *
	 *@throws Exception
	 */
	public static Map<String, Object> callPostAPI(String endpointURL, String contentType, 
			boolean isBearerToken, String token, String xApiKey, String entityJSON) throws Exception {

		Map<String, Object> responseMap = new HashMap<>();

		if(Validator.isNotNull(endpointURL) && !endpointURL.isBlank()) {

			CloseableHttpClient httpclient = HttpClients.custom().build();
			
			try {
				
				HttpPost httpPost = new HttpPost(endpointURL);

				if(Validator.isNotNull(contentType) && !contentType.isBlank()) {
					httpPost.setHeader(HttpHeaders.CONTENT_TYPE, contentType);
				}

				if(Validator.isNotNull(token) && !token.isBlank()) {
					if(isBearerToken) {
						httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
					}else {
						httpPost.setHeader(HttpHeaders.AUTHORIZATION, token);
					}
				}

				if(Validator.isNotNull(xApiKey) && !xApiKey.isBlank()) {
					httpPost.setHeader(EdelweissAPIConstants.X_API_KEY, xApiKey);
				}

				if(Validator.isNotNull(entityJSON) && !entityJSON.isBlank()) {
					HttpEntity apiEntity = new ByteArrayEntity(entityJSON.getBytes("UTF-8"));
					httpPost.setEntity(apiEntity);
				}
				logger.debug("endpointURL - " + endpointURL);
				logger.debug("token - " + token);
				logger.debug("xApiKey - " + xApiKey);
				logger.debug("entityJSON - " + entityJSON);
				
				
				CloseableHttpResponse response = httpclient.execute(httpPost);

				int statusCode = 0;
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
				try {
					statusCode = response.getStatusLine().getStatusCode();
					logger.debug("API Status - " + statusCode);
					
					//Get the Response Based on the Status 200
					String content = EntityUtils.toString(response.getEntity(), "UTF-8");
					jsonObject = JSONFactoryUtil.createJSONObject(content);
				} finally {
					response.close();
				}

				responseMap.put("status", statusCode);
				responseMap.put("jsonObject", jsonObject);

				return responseMap;

			} catch (Exception exception) {
				logger.error("Exception in call post API " + exception.getMessage());
				if (logger.isDebugEnabled()) {
					logger.error(ParameterConstants.EXCEPTION, exception);
				}
			} finally {
				httpclient.close();
			}
		}else {
			throw new Exception("The Endpoint URL is Null or Empty");
		}

		return responseMap;
	}

	/**
	 *This method is used to call the all the Put APIs.
	 *It will be based on the inputs whether to call which url 
	 *& what authorization & content type is requried.
	 *
	 *@param endpointURL
	 *@param contentType
	 *@param isBearerToken
	 *@param token
	 *@param xApiKey
	 *@param entityJSON
	 *
	 *@return Map<String, Object>
	 *
	 *@throws Exception
	 */
	public static Map<String, Object> callPutAPI(String endpointURL, String contentType, 
			boolean isBearerToken, String token, String xApiKey, String entityJSON) throws Exception {

		Map<String, Object> responseMap = new HashMap<>();

		if(Validator.isNotNull(endpointURL) && !endpointURL.isBlank()) {
			
			CloseableHttpClient httpclient = HttpClients.custom().build();

			try {

				HttpPut httpPut = new HttpPut(endpointURL);

				if(Validator.isNotNull(contentType) && !contentType.isBlank()) {
					httpPut.setHeader(HttpHeaders.CONTENT_TYPE, contentType);
				}

				if(Validator.isNotNull(token) && !token.isBlank()) {
					if(isBearerToken) {
						httpPut.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
					}else {
						httpPut.setHeader(HttpHeaders.AUTHORIZATION, token);
					}
				}

				if(Validator.isNotNull(xApiKey) && !xApiKey.isBlank()) {
					httpPut.setHeader(EdelweissAPIConstants.X_API_KEY, xApiKey);
				}

				if(Validator.isNotNull(entityJSON) && !entityJSON.isBlank()) {
					HttpEntity apiEntity = new ByteArrayEntity(entityJSON.getBytes("UTF-8"));
					httpPut.setEntity(apiEntity);
				}

				CloseableHttpResponse response = httpclient.execute(httpPut);

				int statusCode = 0;
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
				try {
					statusCode = response.getStatusLine().getStatusCode();
					logger.debug("API Status - " + statusCode);
					
					//Get the Response Based on the Status 200
					String content = EntityUtils.toString(response.getEntity(), "UTF-8");
					jsonObject = JSONFactoryUtil.createJSONObject(content);
				} finally {
					response.close();
				}

				responseMap.put("status", statusCode);
				responseMap.put("jsonObject", jsonObject);

				return responseMap;

			} catch (Exception exception) {
				logger.error("Exception in call put API " + exception.getMessage());
				if (logger.isDebugEnabled()) {
					logger.error(ParameterConstants.EXCEPTION, exception);
				}
			}finally {
				httpclient.close();
			}
		}else {
			throw new Exception("The Endpoint URL is Null or Empty");
		}

		return responseMap;
	}
	
	/**
	 *This method is used to call the all the Post APIs.
	 *It will be based on the inputs whether to call which url 
	 *& what authorization & content type is requried.
	 *
	 *@param endpointURL
	 *@param contentType
	 *@param isBearerToken
	 *@param token
	 *@param xApiKey
	 *@param entityJSON
	 *
	 *@return Map<String, Object>
	 *
	 *@throws Exception
	 */
	public static Map<String, Object> callSMSPostAPI(String endpointURL, String contentType, 
			boolean isBearerToken, String token, String xApiKey, String entityJSON) throws Exception {

		Map<String, Object> responseMap = new HashMap<>();

		if(Validator.isNotNull(endpointURL) && !endpointURL.isBlank()) {

			CloseableHttpClient httpclient = HttpClients.custom().build();
			
			try {

				HttpPost httpPost = new HttpPost(endpointURL);

				if(Validator.isNotNull(contentType) && !contentType.isBlank()) {
					httpPost.setHeader(HttpHeaders.CONTENT_TYPE, contentType);
				}

				if(Validator.isNotNull(token) && !token.isBlank()) {
					if(isBearerToken) {
						httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
					}else {
						httpPost.setHeader(HttpHeaders.AUTHORIZATION, token);
					}
				}

				if(Validator.isNotNull(xApiKey) && !xApiKey.isBlank()) {
					httpPost.setHeader(EdelweissAPIConstants.X_API_KEY, xApiKey);
				}

				if(Validator.isNotNull(entityJSON) && !entityJSON.isBlank()) {
					HttpEntity apiEntity = new ByteArrayEntity(entityJSON.getBytes("UTF-8"));
					httpPost.setEntity(apiEntity);
				}

				CloseableHttpResponse response = httpclient.execute(httpPost);
				
				int statusCode = 0;
				String content = StringPool.BLANK;
				try {
					statusCode = response.getStatusLine().getStatusCode();
					logger.debug("API Status - " + statusCode);
					
					//Get the Response Based on the Status 200
					content = EntityUtils.toString(response.getEntity(), "UTF-8");
				} finally {
					response.close();
				}

				responseMap.put("status", statusCode);
				responseMap.put("content", content);
				
				return responseMap;

			} catch (Exception exception) {
				logger.error("Exception in call sms post API " + exception.getMessage());
				if (logger.isDebugEnabled()) {
					logger.error(ParameterConstants.EXCEPTION, exception);
				}
			}finally {
				httpclient.close();
			}
		}else {
			throw new Exception("The Endpoint URL is Null or Empty");
		}

		return responseMap;
	}

	/**
	 *This method is used to encode the Username & password.
	 *
	 *@param userName
	 *@param password
	 *
	 *@return String
	 * 
	 */
	public static String getEncodedAuthorizationCredentials(String userName, String password) {

		String userNameAndPassword = userName + StringPool.COLON + password;
		String encodedUserNameAndPassword = Base64.getEncoder().
				encodeToString(userNameAndPassword.getBytes());
		return "Basic" + StringPool.SPACE + encodedUserNameAndPassword;
	}

	/**
	 *This method is used to call the Token API and get the Access Token.
	 *
	 *@param isEnach
	 *@return String
	 *	 
	 **/
	public static String getOAuthToken(boolean isEnach) {

		try {

			ApiURLConfiguration apiURLConfiguration = ConfigurationUtil.getApiURLConfiguration();
			OAuthCredentialsConfiguration oAuthCredentialsConfiguration = ConfigurationUtil.getOAuthCredentialsConfiguration();

			String endpointURL = apiURLConfiguration.getOAuthURL();
			String authorizationToken = StringPool.BLANK;
			String username = StringPool.BLANK;
			String password = StringPool.BLANK;
			
			if(isEnach) {
				username = oAuthCredentialsConfiguration.getEnachOAuthUsername();
				password = oAuthCredentialsConfiguration.getEnachOAuthPassword();
			}else {
				username = oAuthCredentialsConfiguration.getOAuthUsername();
				password = oAuthCredentialsConfiguration.getOAuthPassword();
			}

			if(Validator.isNotNull(username) && Validator.isNotNull(password)) {
				authorizationToken = EdelweissAPIUtil.getEncodedAuthorizationCredentials(username, password);
			}
			
			Map<String, Object> responseMap = EdelweissAPIUtil.callPostAPI(endpointURL, 
					EdelweissAPIConstants.APPLICATION_X_WWW_FORM_URLENCODED_CHARSET_UTF_8, 
					false, authorizationToken, StringPool.BLANK, StringPool.BLANK);

			if(Validator.isNotNull(responseMap)) {
				int status = (Integer)responseMap.get("status");
				if(status == 200) {
					JSONObject jsonObject = (JSONObject)responseMap.get("jsonObject");
					if(Validator.isNotNull(jsonObject)) {
						return jsonObject.get(EdelweissAPIConstants.ACCESS_TOKEN).toString();
					}
				}
			}
			return null;
		}catch(Exception exception) {
			logger.error("Error while calling OAuth API : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		return null;
	}
	
	public static String getDocumentType(String documentType) {

		if(documentType.equalsIgnoreCase("DEATH CERTIFICATE")) {
			return "DEATH CERTIFICATE";
		}else if(documentType.equals("Address Proof") 
				|| documentType.equals("Address Proof 1") 
				|| documentType.equals("Address Proof 2")){
			return "COPY OF PHOTO ID AND ADDRESS PROOF OF NOMINEE";
		}else if(documentType.equalsIgnoreCase("CANCELLED CHEQUE")) {
			return "COPY OF PERSONALISED CANCELLED CHEQUE OF NOMINEE";
		}else if(documentType.equalsIgnoreCase("POLICY BOND")) {
			return "ORIGINAL POLICY BOND";
		}else if(documentType.equalsIgnoreCase("DOCTOR'S CERTIFICATE")) {
			return "TREATING DOCTOR'S CERTIFICATE";
		}else if(documentType.equalsIgnoreCase("CAUSE OF DEATH")) {
			return "COPY OF CAUSE OF DEATH CERTIFICATE";
		}else if(documentType.equalsIgnoreCase("POLICE FIR")) {
			return "COPY OF POLICE FIR";
		}else if(documentType.equalsIgnoreCase("POST MORTEM REPORT")) {
			return "POST MORTEM REPORT";
		}else if(documentType.equalsIgnoreCase("PANCHNAMA")) {
			return "PANCHNAMA";
		}else if(documentType.equalsIgnoreCase("VALID ID PROOFS")) {
			return "OTHERS";
		}else if(documentType.equalsIgnoreCase("RECENT PHOTO")) {
			return "OTHERS";
		}else {
			return "OTHER DOCUMENTS";
		}
	}

	public static String getContentType(String documentType) {

		if(documentType.equalsIgnoreCase("DEATH CERTIFICATE") 
				|| documentType.equalsIgnoreCase("CAUSE OF DEATH") 
				|| documentType.equalsIgnoreCase("POST MORTEM REPORT")) {
			return "PROOF OF DEATH";
		}else {
			return "OTHER DOCUMENTS";
		}
	}
	
	public static String getEdelweissDate(String dateStr, String fromFormat, String toFormat, int noOfDaysToAdd) {
		String ldd = "";
		try {
			if (Validator.isNotNull(dateStr)) {
				DateFormat srcDf = new SimpleDateFormat(fromFormat);
				DateFormat destDf = new SimpleDateFormat(toFormat);
				// parse the date string into Date object
				Date date = srcDf.parse(dateStr);
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				cal.add(Calendar.DATE, noOfDaysToAdd);
				// format the date into another format
				ldd = destDf.format(cal.getTime());
				return ldd;
			}
		} catch (Exception exception) {
			logger.error("Exception in date parsing: " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		return ldd;
	}
	
	/**
	 *This method is used to fetch the key & values from the Liferay OOTB Picklist.
	 *
	 *@param companyId
	 *@param pickListExternalReferenceCode
	 *
	 *@return Map<String, String> 
	 */
	public static Map<String, String> getPickListKeyValues(long companyId, String pickListExternalReferenceCode){

		HashMap<String, String> values = new HashMap<>();

		try {
			
			ListTypeDefinition listTypeDefinition = ListTypeDefinitionLocalServiceUtil.fetchListTypeDefinitionByExternalReferenceCode(pickListExternalReferenceCode, companyId);
			List<ListTypeEntry> listTypeEntries = ListTypeEntryLocalServiceUtil.getListTypeEntries(listTypeDefinition.getListTypeDefinitionId());
			
			for(ListTypeEntry listTypeEntry : listTypeEntries) {
				values.put(listTypeEntry.getKey(), listTypeEntry.getName(Locale.ENGLISH));
			}
			
		} catch (Exception exception) {
			logger.error("Error Occurred while Fetching PickList Entries" + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		return values;
	}
	
	/**
	 *This method is used to fetch the key & values from the Liferay OOTB Object.
	 *
	 *@param companyId
	 *@param objectExternalReferenceCode
	 *
	 *@return Map<String, String> 
	 */
	public static Map<String, String> getObjectEntries(ThemeDisplay themeDisplay, String objectExternalReferenceCode){

		HashMap<String, String> values = new HashMap<>();

		try {
			
			ObjectDefinition objectDefinition = ObjectDefinitionLocalServiceUtil.getObjectDefinitionByExternalReferenceCode(objectExternalReferenceCode, themeDisplay.getCompanyId());
			List<ObjectEntry> objectEntries = ObjectEntryLocalServiceUtil.getObjectEntries(0, objectDefinition.getObjectDefinitionId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for(ObjectEntry objectEntry : objectEntries) {
				values.put(String.valueOf(objectEntry.getObjectEntryId()), objectEntry.getValues().get("name").toString());
			}
			
		} catch (Exception exception) {
			logger.error("Error Occurred while Fetching Object Entries" + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		return values;
	}
	
	/**
	 *This method is used to call the all the get APIs.
	 *It will be based on the inputs whether to call which url 
	 *& what authorization & content type is requried.
	 *
	 *@param endpointURL
	 *
	 *@throws Exception
	 */	
	public static String callPJGetAPI(String endpointURL) throws Exception {
		logger.debug("Proposal Journey Get call Start ::::::::::::::: ");
		
		String responseString = StringPool.BLANK;
		if(Validator.isNotNull(endpointURL) && !endpointURL.isBlank()) {
			CloseableHttpClient httpclient = HttpClients.custom().build();
			
			try {
				HttpGet httpGet = new HttpGet(endpointURL);
				httpGet.setHeader(HttpHeaders.CONTENT_TYPE, EdelweissAPIConstants.APPLICATION_JSON);
			
				CloseableHttpResponse response = httpclient.execute(httpGet);
				try {
					
					logger.debug("Proposal Journey Get call Status - " + response.getStatusLine().getStatusCode());
					
					//Get the Response Based on the Status 200
					responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
					
					logger.debug("Proposal Journey  Get call  responseString - " + responseString);
					
				} finally {
					response.close();
				}
				
			} catch (Exception exception) {
				logger.error("Exception in call get API " + exception.getMessage());
				if (logger.isDebugEnabled()) {
					logger.error(ParameterConstants.EXCEPTION, exception);
				}
			} finally {
				httpclient.close();
			}
		}else {
			throw new Exception("The Endpoint URL is Null or Empty");
		}
		logger.info("Proposal Journey Get call End ::::::::::::: ");
		
		return responseString;
	}
	
	/**
	 *This method is used to call the all the Post APIs.
	 *It will be based on the inputs whether to call which url 
	 *& what authorization & content type is requried.
	 *
	 *@param endpointURL
	 *
	 *@throws Exception
	 */
	public static String callPJPostAPI(String endpointURL) throws Exception {
		
		logger.info("Proposal Journey Post call Start :::::::: ");
		String responseString = StringPool.BLANK;
		if(Validator.isNotNull(endpointURL) && !endpointURL.isBlank()) {
			CloseableHttpClient httpclient = HttpClients.custom().build();
			
			try {
				
				HttpPost httpPost = new HttpPost(endpointURL);
				httpPost.setHeader(HttpHeaders.CONTENT_TYPE, EdelweissAPIConstants.APPLICATION_JSON);
				CloseableHttpResponse response = httpclient.execute(httpPost);
				try {
					
					logger.debug("Proposal Journey  Post call  responseString - " + response.getStatusLine().getStatusCode());
					
					//Get the Response Based on the Status 200
					 responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
					 
					 logger.debug("Proposal Journey  Post call  responseString - " + responseString);
					
				} finally {
					response.close();
				}
			} catch (Exception exception) {
				logger.error("Exception in call post API " + exception.getMessage());
				if (logger.isDebugEnabled()) {
					logger.error(ParameterConstants.EXCEPTION, exception);
				}
			} finally {
				httpclient.close();
			}
		}else {
			throw new Exception("The Endpoint URL is Null or Empty");
		}
		logger.info("Proposal Journey Post call End :::::::: ");
		
		return responseString;
	}
	
	/**
	 *This method is used to call the D365 Oauth Token API and get the Access Token.
	 *
	 *
	 *@return String
	 *	 
	 **/
	public static String getD365OAuthToken() {
		logger.info("inside the D365OAuthToken");

		try {
			
			EdelweissGSTINOAuthConfiguration edelweissGSTINOAuthConfiguration = ConfigurationUtil.getEdelweissGSTINOAuthConfiguration();
			//D365OAuthCredentialsConfiguration d365OAuthCredentialsConfiguration = ConfigurationUtil.getEdelweissGSTINOAuthConfiguration();

			
			
			List<NameValuePair> formParams = new ArrayList<NameValuePair>();
	        
	        formParams.add(new BasicNameValuePair("grant_type", edelweissGSTINOAuthConfiguration.getGstinOAuth2GrantType()));
	        formParams.add(new BasicNameValuePair("resource", edelweissGSTINOAuthConfiguration.getGstinOAuth2Resource()));
	        formParams.add(new BasicNameValuePair("username", edelweissGSTINOAuthConfiguration.getGstinOAuth2Username()));
	        formParams.add(new BasicNameValuePair("password", edelweissGSTINOAuthConfiguration.getGstinOAuth2Password()));
	        formParams.add(new BasicNameValuePair("client_id", edelweissGSTINOAuthConfiguration.getGstinOAuth2ClientId()));
			
			Map<String, Object> responseMap = EdelweissAPIUtil.callPostD365OauthAPI(edelweissGSTINOAuthConfiguration.getGstinOAuth2URL(), formParams);	
			logger.info("response Map after the postAPI"+ responseMap);
			if(Validator.isNotNull(responseMap)) {
				int status = (Integer)responseMap.get("status");
				if(status == 200) {
					JSONObject jsonObject = (JSONObject)responseMap.get("jsonObject");
					if(Validator.isNotNull(jsonObject)) {
						logger.info(jsonObject.get(EdelweissAPIConstants.ACCESS_TOKEN).toString());
						return jsonObject.get(EdelweissAPIConstants.ACCESS_TOKEN).toString();
					}
				}
			}
			return null;
		}catch(Exception exception) {
			logger.error("Error while calling OAuth API : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		return null;
	}
	
	public static Map<String, Object> callPostD365OauthAPI(String endpointURL, List<NameValuePair> formParams) throws Exception {
		logger.info("inside the callPostD365OauthAPI");
		Map<String, Object> responseMap = new HashMap<>();
		 String apiUrl = endpointURL;
	       
	        // Create an HttpClient
	       // HttpClient httpClient = HttpClients.createDefault();
	        CloseableHttpClient httpclient = HttpClients.custom().build();

	        // Create an HttpPost request
	        HttpPost httpPost = new HttpPost(apiUrl);

	        
	        // Add any other headers as needed

	        // Set the POST data
	        httpPost.setEntity(new UrlEncodedFormEntity(formParams));

	        // Execute the request and get the response
	       // HttpResponse response = httpClient.execute(httpPost);
	        CloseableHttpResponse response = httpclient.execute(httpPost);
	       
	        int statusCode = 0;
	        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
	        
	        try {
	        	statusCode = response.getStatusLine().getStatusCode();
	        
	        logger.info("status code"+ statusCode);
	        // Read the response content
	        HttpEntity responseEntity = response.getEntity();
	        logger.info(responseEntity);
	        
	        String content = EntityUtils.toString(responseEntity,"UTF-8");
	        jsonObject = JSONFactoryUtil.createJSONObject(content);
	        }finally {
				response.close();
			}
	        responseMap.put("status", statusCode);
	        responseMap.put("jsonObject", jsonObject);
	        logger.info("response Map here:::"+responseMap);
			return responseMap;
								
			
	}
	
	public static String getEKycLinkURLByAPI(JSONObject wrapperApiRequest) throws Exception   {
		String apiUrl = ConfigurationUtil.getProposalFormAPIURLConfiguration().getEKycLinkURL();

        // Create HttpClient instance
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // Create HttpPost instance
        HttpPost httpPost = new HttpPost(apiUrl);

        // Set the content type to application/json
        httpPost.setHeader("Content-Type", "application/json");

        // Set the request body
        httpPost.setEntity(new StringEntity(wrapperApiRequest.toJSONString()));

        // Execute the request and get the response
        CloseableHttpResponse response = httpClient.execute(httpPost);

        // Get the response code
        int statusCode = response.getStatusLine().getStatusCode();
        logger.debug("Response Code: " + statusCode);
        String apiResponse = StringPool.BLANK;
      
		 try {
			    HttpEntity entity = response.getEntity();
			    
			    apiResponse = EntityUtils.toString(entity);
			    logger.debug("*********locationURL: "+apiUrl);
			    logger.debug(" >>>>>>statusCode from post >>>>>>> " + statusCode);
				logger.debug(" >>>>>>httpresponse.getStatusLine() from post >>>>>>> " + response.getStatusLine());
			} finally {
				response.close();
			}	
		 
			logger.debug("HttpAPIClient >>> executePost >>> End Of Invocation :::");
			logger.debug("*********locationURL: "+apiUrl);
			logger.debug("apiResponse : "+apiResponse );
		
		
		return apiResponse;
	
	}
	
	public static String postEkycDetailsApi(JSONObject wrapperApiRequest) throws Exception   {
		String apiUrl = ConfigurationUtil.getProposalFormAPIURLConfiguration().getEkycDetailsPostURL();
		logger.debug("apiUrl: " + apiUrl);
        // Create HttpClient instance
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // Create HttpPost instance
        HttpPost httpPost = new HttpPost(apiUrl);

        // Set the content type to application/json
        httpPost.setHeader("Content-Type", "application/json");

        // Set the request body
        httpPost.setEntity(new StringEntity(wrapperApiRequest.toJSONString()));

        // Execute the request and get the response
        CloseableHttpResponse response = httpClient.execute(httpPost);

        // Get the response code
        int statusCode = response.getStatusLine().getStatusCode();
        logger.debug("Response Code: " + statusCode);
        String apiResponse = StringPool.BLANK;
      
		 try {
			    HttpEntity entity = response.getEntity();
			    
			    apiResponse = EntityUtils.toString(entity);
			    logger.debug("*********locationURL: "+apiUrl);
			    logger.debug(" >>>>>>statusCode from post >>>>>>> " + statusCode);
				logger.debug(" >>>>>>httpresponse.getStatusLine() from post >>>>>>> " + response.getStatusLine());
			} finally {
				response.close();
			}	
		 
			logger.debug("HttpAPIClient >>> executePost >>> End Of Invocation :::");
			logger.debug("*********locationURL: "+apiUrl);
			logger.debug("apiResponse : "+apiResponse );
		
		
		return apiResponse;
	
	}
	
	public static Map<String, Object> validateGstinStateCodeAPI(String gstinStateCodeValidationURL, String accessToken, String stateName) throws Exception, IOException {
		logger.info("inside the validateGSTINSTATECODEapi method");
		Map<String, Object> responseMap = new HashMap<>();
		//EdelweissGSTINOAuthConfiguration edelweissGSTINOAuthConfiguration = ConfigurationUtil.getEdelweissGSTINOAuthConfiguration();
		
		//String endpointURL = edelweissGSTINOAuthConfiguration.getGstinStateCodeAPIValidation();
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			//specify the baseURL
			String baseURL = "https://etliuat1.crm8.dynamics.com/api/data/v9.1/etli_attributemasters";
			
			// Dynamic value based on selected state
            String selectedState = stateName;  // Replace with the actual selected state value
            logger.debug("state name"+ stateName);
			//specify the queryParamter
			String selectParam = "etli_drccode";
            //String filterParam = "etli_attributetype eq 'STATE' and etli_attributetext eq 'KARNATAKA'";
            String filterParam = "etli_attributetype eq 'STATE' and etli_attributetext eq '" + selectedState + "'";
            
            // Encode query parameters
            String encodedSelectParam = java.net.URLEncoder.encode(selectParam, "UTF-8");
            String encodedFilterParam = java.net.URLEncoder.encode(filterParam, "UTF-8");
			
            // Construct the URL with encoded query parameters
            String apiUrl = baseURL + "?$select=" + encodedSelectParam + "&$filter=" + encodedFilterParam;
            logger.info("completeURL::"+ apiUrl);
			
            // Create an HTTP GET request
            HttpGet httpGet = new HttpGet(apiUrl);
            
            // Add headers to the request
            httpGet.addHeader("Authorization", accessToken);
            //   httpGet.addHeader("Cookie", "ARRAffinity=1b32cfe83c24097daad0ae1d3645e29225bbc39fd853cb9594c414c19d9eb79c5ab954b42cfa12ad9f33ddc80a07e15dd633bd57139a65479392e46e5242529408DBF5AB9B0BB882297750619; ReqClientId=855fd542-6a2c-4865-bbb0-9c8785ae02bb; orgId=8ecfc7cb-f580-44ad-b3bb-1f7a80a5e7c3");

             // Execute the request and get the response
            CloseableHttpResponse response = httpClient.execute(httpGet);
            logger.info("response::"+ response);
            
            // Retrieve the response entity
            HttpEntity entity = response.getEntity();
            logger.info("response entity:::"+ response.getEntity());
            
            
            int statusCode = 0;
	        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
	        
	        try {
	        	statusCode = response.getStatusLine().getStatusCode();
	        
	        logger.info("status code"+ statusCode);
	        // Read the response content
	        //HttpEntity responseEntity = response.getEntity();
	      //  logger.info(responseEntity);
	        
	        String content = EntityUtils.toString(entity,"UTF-8");
	        jsonObject = JSONFactoryUtil.createJSONObject(content);
	        }finally {
				response.close();
			}
	       	
	        responseMap.put("status", statusCode);
	        responseMap.put("jsonObject", jsonObject);
	        logger.info("response Map here:::"+responseMap);
	        
			return responseMap;
			}
            
            // Print or process the response content
           /* if (entity != null) {
                String responseBody = EntityUtils.toString(entity);
                System.out.println("responseBody::::"+ responseBody);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } */
 
		
		// TODO Auto-generated method stub
		//return null;
	}
	
	
	
	private static Log logger = LogFactoryUtil.getLog(EdelweissAPIUtil.class);



	
	
	
}
