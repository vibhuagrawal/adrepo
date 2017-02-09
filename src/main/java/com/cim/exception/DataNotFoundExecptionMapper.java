package com.cim.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.cim.beans.ErrorMessage;

@Provider
public class DataNotFoundExecptionMapper implements ExceptionMapper<DataNotFoundExecption> {

	@Override
	public Response toResponse(DataNotFoundExecption ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), Status.NOT_FOUND.getStatusCode());
		return Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
	}

}
