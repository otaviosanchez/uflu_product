package shop.uflu.platform.core.product.resource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import shop.uflu.platform.core.product.service.api.HealthCheckService;

@Path("/health")
public class HealthCheckResource {
	
    @Inject
    private HealthCheckService service;

    @GET
    @Path("/check")
    @Produces(MediaType.TEXT_PLAIN)
    public String check() {
        return service.check();
    }

}
