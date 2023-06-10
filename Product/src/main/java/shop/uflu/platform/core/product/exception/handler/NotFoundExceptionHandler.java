package shop.uflu.platform.core.product.exception.handler;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import shop.uflu.platform.core.product.exception.custom.NotFoundException;

@Provider
public class NotFoundExceptionHandler implements ExceptionMapper<NotFoundException> {

	@Override
	public Response toResponse(NotFoundException exception) {
		return Response.status(Status.NOT_FOUND).build();
	}

}
