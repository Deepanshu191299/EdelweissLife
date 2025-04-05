package in.edelweiss.rest.api.builder.internal.graphql.mutation.v1_0;

import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.batch.engine.resource.VulcanBatchEngineImportTaskResource;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;

import in.edelweiss.rest.api.builder.dto.v1_0.Ekyc;
import in.edelweiss.rest.api.builder.dto.v1_0.LMS;
import in.edelweiss.rest.api.builder.resource.v1_0.EkycResource;
import in.edelweiss.rest.api.builder.resource.v1_0.LMSResource;

import java.util.function.BiFunction;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author pramod.dk
 * @generated
 */
@Generated("")
public class Mutation {

	public static void setEkycResourceComponentServiceObjects(
		ComponentServiceObjects<EkycResource>
			ekycResourceComponentServiceObjects) {

		_ekycResourceComponentServiceObjects =
			ekycResourceComponentServiceObjects;
	}

	public static void setLMSResourceComponentServiceObjects(
		ComponentServiceObjects<LMSResource>
			lmsResourceComponentServiceObjects) {

		_lmsResourceComponentServiceObjects =
			lmsResourceComponentServiceObjects;
	}

	@GraphQLField(description = "Adds ekyc details")
	public Response addEkycDetails(@GraphQLName("ekyc") Ekyc ekyc)
		throws Exception {

		return _applyComponentServiceObjects(
			_ekycResourceComponentServiceObjects,
			this::_populateResourceContext,
			ekycResource -> ekycResource.addEkycDetails(ekyc));
	}

	@GraphQLField(description = "Removes the ekycdetails")
	public boolean deletedEkycDetails(
			@GraphQLName("ekycDetailsId") String ekycDetailsId)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_ekycResourceComponentServiceObjects,
			this::_populateResourceContext,
			ekycResource -> ekycResource.deletedEkycDetails(ekycDetailsId));

		return true;
	}

	@GraphQLField(description = "Updates ekyc Details")
	public Ekyc updateEkycDetails(
			@GraphQLName("ekycDetailsId") String ekycDetailsId,
			@GraphQLName("ekyc") Ekyc ekyc)
		throws Exception {

		return _applyComponentServiceObjects(
			_ekycResourceComponentServiceObjects,
			this::_populateResourceContext,
			ekycResource -> ekycResource.updateEkycDetails(
				ekycDetailsId, ekyc));
	}

	@GraphQLField(description = "Adds lead details")
	public Response addLeadDetails(@GraphQLName("lms") LMS lms)
		throws Exception {

		return _applyComponentServiceObjects(
			_lmsResourceComponentServiceObjects, this::_populateResourceContext,
			lmsResource -> lmsResource.addLeadDetails(lms));
	}

	private <T, R, E1 extends Throwable, E2 extends Throwable> R
			_applyComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeFunction<T, R, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			return unsafeFunction.apply(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private <T, E1 extends Throwable, E2 extends Throwable> void
			_applyVoidComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeConsumer<T, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			unsafeFunction.accept(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(EkycResource ekycResource)
		throws Exception {

		ekycResource.setContextAcceptLanguage(_acceptLanguage);
		ekycResource.setContextCompany(_company);
		ekycResource.setContextHttpServletRequest(_httpServletRequest);
		ekycResource.setContextHttpServletResponse(_httpServletResponse);
		ekycResource.setContextUriInfo(_uriInfo);
		ekycResource.setContextUser(_user);
		ekycResource.setGroupLocalService(_groupLocalService);
		ekycResource.setRoleLocalService(_roleLocalService);

		ekycResource.setVulcanBatchEngineImportTaskResource(
			_vulcanBatchEngineImportTaskResource);
	}

	private void _populateResourceContext(LMSResource lmsResource)
		throws Exception {

		lmsResource.setContextAcceptLanguage(_acceptLanguage);
		lmsResource.setContextCompany(_company);
		lmsResource.setContextHttpServletRequest(_httpServletRequest);
		lmsResource.setContextHttpServletResponse(_httpServletResponse);
		lmsResource.setContextUriInfo(_uriInfo);
		lmsResource.setContextUser(_user);
		lmsResource.setGroupLocalService(_groupLocalService);
		lmsResource.setRoleLocalService(_roleLocalService);
	}

	private static ComponentServiceObjects<EkycResource>
		_ekycResourceComponentServiceObjects;
	private static ComponentServiceObjects<LMSResource>
		_lmsResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private com.liferay.portal.kernel.model.Company _company;
	private GroupLocalService _groupLocalService;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private RoleLocalService _roleLocalService;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private UriInfo _uriInfo;
	private com.liferay.portal.kernel.model.User _user;
	private VulcanBatchEngineImportTaskResource
		_vulcanBatchEngineImportTaskResource;

}