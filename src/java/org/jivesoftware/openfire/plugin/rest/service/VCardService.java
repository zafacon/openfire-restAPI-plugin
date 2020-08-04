package org.jivesoftware.openfire.plugin.rest.service;

import org.jivesoftware.openfire.plugin.rest.controller.VCardServiceController;
import org.jivesoftware.openfire.plugin.rest.entity.VCardEntity;
import org.jivesoftware.openfire.plugin.rest.exceptions.ServiceException;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("restapi/v1/vcards")
public class VCardService {

    private VCardServiceController plugin;

    @PostConstruct
    public void init() {
        plugin = VCardServiceController.getInstance();
    }

    @POST
    @Path("/{username}")
    public Response createVCard(@PathParam("username") String username, VCardEntity vcardEntity) throws ServiceException {
        plugin.setVCard(username, vcardEntity);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{username}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public VCardEntity getVCard(@PathParam("username") String username) throws ServiceException {
        return plugin.getVCardEntity(username);
    }

    @PUT
    @Path("/{username}")
    public Response updateVCard(@PathParam("username") String username, VCardEntity vcardEntity) throws ServiceException {
        plugin.setVCard(username, vcardEntity);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/{username}")
    public Response deleteVCard(@PathParam("username") String username) throws ServiceException {
        plugin.deleteVCard(username);
        return Response.status(Response.Status.OK).build();
    }
}
