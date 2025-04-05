package in.edelweiss.rest.api.builder.internal.resource.v1_0;

import com.edelweiss.http.core.api.ETIPCoreAPI;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.rest.api.builder.dto.v1_0.Ekyc;
import in.edelweiss.rest.api.builder.resource.v1_0.EkycResource;
import in.edelweiss.system.configurations.EdelweissLRBasicAuthConfiguration;
import in.edelweiss.tokio.common.util.EdelweissConfigurationUtil;

/**
 * @author pramod.dk
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/ekyc.properties",
	scope = ServiceScope.PROTOTYPE, service = EkycResource.class
)
public class EkycResourceImpl extends BaseEkycResourceImpl {
	
	private static Log log = LogFactoryUtil.getLog(EkycResourceImpl.class);
	
	@Reference
	ETIPCoreAPI etipCoreApi;
	
	@Override
	public Response addEkycDetails(Ekyc ekyc) throws Exception {
		
		JSONObject apiResponse = JSONFactoryUtil.createJSONObject();
		try {
			
			log.info("============= addEkycDetails headless called ==============");
			
			String ekycWrapperURL = ConfigurationUtil.getProposalFormAPIURLConfiguration().getEKycInternalApiURL();
			
			log.info("ekycWrapperURL : "+ekycWrapperURL);
			log.info("ekyc : "+ekyc);
				
			JSONObject request = JSONFactoryUtil.createJSONObject();
			
			request.put("txnId", ekyc.getTxnId());
			request.put("ekycStatus", ekyc.getEkycStatus());
			request.put("ekycMessage", ekyc.getEkycMessage());
			request.put("ekycTimeStamp", ekyc.getEkycTimeStamp());
			request.put("uid", ekyc.getData().getUid());
			request.put("photo", ekyc.getData().getPhoto());
			request.put("dob", ekyc.getData().getPoi().getDob());
			request.put("gender", ekyc.getData().getPoi().getGender());
			request.put("name", ekyc.getData().getPoi().getName());
			request.put("country", ekyc.getData().getPoa().getCountry());
			request.put("dist", ekyc.getData().getPoa().getDist());
			request.put("house", ekyc.getData().getPoa().getHouse());
			request.put("lm", ekyc.getData().getPoa().getLm());
			request.put("loc", ekyc.getData().getPoa().getLoc());
			request.put("pc", ekyc.getData().getPoa().getPc());
			request.put("po", ekyc.getData().getPoa().getPo());
			request.put("state", ekyc.getData().getPoa().getState());
			request.put("subdist", ekyc.getData().getPoa().getSubdist());
			request.put("vtc", ekyc.getData().getPoa().getVtc());
			request.put("co", ekyc.getData().getPoa().getCo());
			request.put("street", ekyc.getData().getPoa().getStreet());

			EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
			
			log.debug("liferayUserName : "+edelweissLRBasicAuthConfiguration.getLRUsername());
			log.debug("liferayPassword : "+edelweissLRBasicAuthConfiguration.getLRPassword());
			
			log.debug("request : "+request);
			
			JSONObject iternalApiResponse = etipCoreApi.callPostAPI(request, ekycWrapperURL, false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());
			
			log.debug("iternalApiResponse : "+iternalApiResponse);
			
			if(null!=iternalApiResponse && iternalApiResponse.keySet().size()!=0) {
	
				if(null!=iternalApiResponse.getJSONObject("status") 
						&& null!= iternalApiResponse.getJSONObject("status").get("label")
						&& iternalApiResponse.getJSONObject("status").getString("label").equalsIgnoreCase("approved")) {
					
					apiResponse.put("status", "success");
					apiResponse.put("message", "");
					apiResponse.put("errorCode", "0");
				}
				
			} else {
				apiResponse.put("status", "failed");
				apiResponse.put("message", "Internal server error");
				apiResponse.put("errorCode", "500");
				
			}
		} catch(Exception e) {
			log.error("Exception Occured : "+e.getMessage());
			log.error(e);
			apiResponse.put("status", "failed");
			apiResponse.put("message", "Internal server error");
			apiResponse.put("errorCode", "500");
		}
		Response.ResponseBuilder responseBuilder = Response.ok();

		log.info("apiResponse : "+apiResponse);
		return responseBuilder.entity(apiResponse).build();
	}
	
}