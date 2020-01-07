package org.jivesoftware.openfire.plugin.rest.exceptions;

import java.util.List;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class RESTExceptionMapper.
 */
@Provider
public class RESTExceptionMapper implements ExceptionMapper<ServiceException> {

    /** The log. */
    private static Logger LOG = LoggerFactory.getLogger(RESTExceptionMapper.class);
    
    /** The headers. */
    @Context
    private HttpHeaders headers;
    

    /**
     * Instantiates a new REST exception mapper.
     */
    public RESTExceptionMapper() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
     */
    public Response toResponse(ServiceException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setResource(exception.getResource());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setException(exception.getException());
        LOG.error(
                exception.getException() + ": " + exception.getMessage() + " with resource "
                        + exception.getResource(), exception.getException());
        
        ResponseBuilder responseBuilder = Response.status(exception.getStatus()).entity(errorResponse);
        List<MediaType> accepts = headers.getAcceptableMediaTypes();

        // If accepts header is given, respect it
        if (accepts.size() == 1 && !accepts.get(0).isWildcardType()) {
            MediaType mediaType = accepts.get(0);
            responseBuilder = responseBuilder.type(mediaType);
        }
        else {
            if (headers.getMediaType() != null) {
                // if accept header is not given, take the content type media type
                responseBuilder = responseBuilder.type(headers.getMediaType());
            } else {
                // if nothing is provided, take XML
                responseBuilder = responseBuilder.type(MediaType.APPLICATION_XML);
            }
        }

        return responseBuilder.build();
    }

}
