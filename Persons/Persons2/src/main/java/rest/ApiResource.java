/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import Entity.Person;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import facader.PersonFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import json.JSonConverter;

/**
 * REST Web Service
 *
 * @author MartinLodahl
 */
@Path("person")
public class ApiResource {

    
    EntityManagerFactory emf;
    PersonFacade pf;
    JSonConverter jc;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ApiResource
     */
    public ApiResource() {
        this.jc = new JSonConverter();
        this.emf = Persistence.createEntityManagerFactory("JPAPU");
        this.pf = new PersonFacade(emf);
    }

    /**
     * Retrieves representation of an instance of rest.ApiResource
     *
     * @return an instance of java.lang.String
     */
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPerson(String content, @PathParam("id") int id) {
        //TODO return proper representation object
        //{"quote" : "Quote text"}
        Person p = pf.getPerson(id);
        return jc.getJSONFromPerson(p);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersons(String content) {
        List< Person> p = pf.getPersons();
        return jc.getJSONFromPerson(p);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createPerson(String content) {
        //TODO return proper representation object
        //{"quote" : "Quote text"}
        Person p = jc.getPersonFromJson(content);
        pf.addPerson(p);
    }

    /**
     * PUT method for updating or creating an instance of ApiResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
        Person p = jc.getPersonFromJson(content);
        pf.editPerson(p);
    }
    
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void deletePerson(String content, @PathParam("id") int id) {
        //TODO return proper representation object
        //{"quote" : "Quote text"}
        pf.deletePerson(id);
    }
}
