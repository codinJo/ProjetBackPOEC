package packageDeTravail;


import javax.ejb.EJB;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author javau
 */
@Path("manager")
public class PdfManager {
    
    @Context
    private UriInfo context;
    
    @EJB
    Merge merge;
    
    @GET
    @Path("{id}")
    @Produces("application/xml")
    public Response getXmlWithParams(@PathParam("id") String id, @DefaultValue("all")
            @HeaderParam("name") String name) {
        System.out.println(id);
        System.out.println(name);
        return Response
                .status(Response.Status.OK)
                .entity("<bonjour>Bonjour ENSMA de la part de " + name + "</bonjour>")
                .build();
    }
}
