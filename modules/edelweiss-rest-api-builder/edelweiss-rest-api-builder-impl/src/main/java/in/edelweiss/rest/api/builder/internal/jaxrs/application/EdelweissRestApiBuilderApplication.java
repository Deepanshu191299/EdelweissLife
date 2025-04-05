package in.edelweiss.rest.api.builder.internal.jaxrs.application;

import javax.annotation.Generated;

import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;

/**
 * @author pramod.dk
 * @generated
 */
@Component(
	property = {
		"liferay.jackson=false",
		"osgi.jaxrs.application.base=/edelweiss-rest-api",
		"osgi.jaxrs.extension.select=(osgi.jaxrs.name=Liferay.Vulcan)",
		"osgi.jaxrs.name=EdelweissRestApiBuilder"
	},
	service = Application.class
)
@Generated("")
public class EdelweissRestApiBuilderApplication extends Application {
}