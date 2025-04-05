package in.edelweiss.rest.api.builder.internal.graphql.query.v1_0;

import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.pagination.Page;

import in.edelweiss.rest.api.builder.dto.v1_0.Ekyc;
import in.edelweiss.rest.api.builder.resource.v1_0.EkycResource;

import java.util.Map;
import java.util.function.BiFunction;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author pramod.dk
 * @generated
 */
@Generated("")
public class Query {

	public static void setEkycResourceComponentServiceObjects(
		ComponentServiceObjects<EkycResource>
			ekycResourceComponentServiceObjects) {

		_ekycResourceComponentServiceObjects =
			ekycResourceComponentServiceObjects;
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {ekycDetails{items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Get ekycDetails")
	public EkycPage ekycDetails() throws Exception {
		return _applyComponentServiceObjects(
			_ekycResourceComponentServiceObjects,
			this::_populateResourceContext,
			ekycResource -> new EkycPage(ekycResource.getEkycDetails()));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {ekycDetailsById(ekycDetailsId: ___){txnId, ekycStatus, ekycMessage, ekycTimeStamp, data}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the app")
	public Ekyc ekycDetailsById(
			@GraphQLName("ekycDetailsId") String ekycDetailsId)
		throws Exception {

		return _applyComponentServiceObjects(
			_ekycResourceComponentServiceObjects,
			this::_populateResourceContext,
			ekycResource -> ekycResource.getEkycDetailsById(ekycDetailsId));
	}

	@GraphQLName("EkycPage")
	public class EkycPage {

		public EkycPage(Page ekycPage) {
			actions = ekycPage.getActions();

			items = ekycPage.getItems();
			lastPage = ekycPage.getLastPage();
			page = ekycPage.getPage();
			pageSize = ekycPage.getPageSize();
			totalCount = ekycPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map<String, String>> actions;

		@GraphQLField
		protected java.util.Collection<Ekyc> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

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
	}

	private static ComponentServiceObjects<EkycResource>
		_ekycResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private com.liferay.portal.kernel.model.Company _company;
	private BiFunction<Object, String, Filter> _filterBiFunction;
	private GroupLocalService _groupLocalService;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private RoleLocalService _roleLocalService;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private UriInfo _uriInfo;
	private com.liferay.portal.kernel.model.User _user;

}