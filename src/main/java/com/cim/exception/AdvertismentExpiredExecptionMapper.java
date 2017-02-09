package com.cim.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.cim.beans.ErrorMessage;

@Provider
public class AdvertismentExpiredExecptionMapper implements ExceptionMapper<AdvertismentExpiredException> {

	@Override
	public Response toResponse(AdvertismentExpiredException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), Status.EXPECTATION_FAILED.getStatusCode());
		return Response.status(Status.EXPECTATION_FAILED)
				.entity(errorMessage)
				.build();
	}
	
}
