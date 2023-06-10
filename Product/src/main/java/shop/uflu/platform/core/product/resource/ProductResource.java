package shop.uflu.platform.core.product.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import shop.uflu.platform.core.product.exception.custom.NotFoundException;
import shop.uflu.platform.core.product.model.json.ProductTO;
import shop.uflu.platform.core.product.service.api.ProductService;

@Path("/product")
public class ProductResource {
	
    @Inject
    private ProductService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Context HttpHeaders headers, ProductTO product) {
        service.save(product);
        return Response.status(201).build();
    }

    @GET
	@Path("/{providerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@Context HttpHeaders headers, @PathParam(value = "providerId") String providerId) throws NotFoundException {
        List<ProductTO> products = service.findByProvider(providerId);
        return Response.status(200).entity(products).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@Context HttpHeaders headers) throws NotFoundException {
        List<ProductTO> products = service.findAll();
        return Response.status(200).entity(products).build();
    }

}
