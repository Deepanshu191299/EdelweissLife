package in.edelweiss.rest.api.builder.internal.graphql.servlet.v1_0;

import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.vulcan.graphql.servlet.ServletData;

import in.edelweiss.rest.api.builder.internal.graphql.mutation.v1_0.Mutation;
import in.edelweiss.rest.api.builder.internal.graphql.query.v1_0.Query;
import in.edelweiss.rest.api.builder.internal.resource.v1_0.EkycResourceImpl;
import in.edelweiss.rest.api.builder.internal.resource.v1_0.LMSResourceImpl;
import in.edelweiss.rest.api.builder.resource.v1_0.EkycResource;
import in.edelweiss.rest.api.builder.resource.v1_0.LMSResource;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceScope;

/**
 * @author pramod.dk
 * @generated
 */
@Component(service = ServletData.class)
@Generated("")
public class ServletDataImpl implements ServletData {

	@Activate
	public void activate(BundleContext bundleContext) {
		Mutation.setEkycResourceComponentServiceObjects(
			_ekycResourceComponentServiceObjects);
		Mutation.setLMSResourceComponentServiceObjects(
			_lmsResourceComponentServiceObjects);

		Query.setEkycResourceComponentServiceObjects(
			_ekycResourceComponentServiceObjects);
	}

	public String getApplicationName() {
		return "EdelweissRestApiBuilder";
	}

	@Override
	public Mutation getMutation() {
		return new Mutation();
	}

	@Override
	public String getPath() {
		return "/edelweiss-rest-api-graphql/v1_0";
	}

	@Override
	public Query getQuery() {
		return new Query();
	}

	public ObjectValuePair<Class<?>, String> getResourceMethodObjectValuePair(
		String methodName, boolean mutation) {

		if (mutation) {
			return _resourceMethodObjectValuePairs.get(
				"mutation#" + methodName);
		}

		return _resourceMethodObjectValuePairs.get("query#" + methodName);
	}

	private static final Map<String, ObjectValuePair<Class<?>, String>>
		_resourceMethodObjectValuePairs =
			new HashMap<String, ObjectValuePair<Class<?>, String>>() {
				{
					put(
						"mutation#addEkycDetails",
						new ObjectValuePair<>(
							EkycResourceImpl.class, "addEkycDetails"));
					put(
						"mutation#deletedEkycDetails",
						new ObjectValuePair<>(
							EkycResourceImpl.class, "deletedEkycDetails"));
					put(
						"mutation#updateEkycDetails",
						new ObjectValuePair<>(
							EkycResourceImpl.class, "updateEkycDetails"));
					put(
						"mutation#addLeadDetails",
						new ObjectValuePair<>(
							LMSResourceImpl.class, "addLeadDetails"));

					put(
						"query#ekycDetails",
						new ObjectValuePair<>(
							EkycResourceImpl.class, "getEkycDetails"));
					put(
						"query#ekycDetailsById",
						new ObjectValuePair<>(
							EkycResourceImpl.class, "getEkycDetailsById"));
				}
			};

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<EkycResource>
		_ekycResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<LMSResource>
		_lmsResourceComponentServiceObjects;

}