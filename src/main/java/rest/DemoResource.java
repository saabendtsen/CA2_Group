package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.UserDTO;
import entities.User;

import java.io.IOException;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import facades.UserFacade;
import org.eclipse.persistence.annotations.CompositeMember;
import utils.EMF_Creator;
import utils.HttpUtils;

@Path("info")
public class DemoResource {
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    @Context
    private UriInfo context;
    private UserFacade facade = UserFacade.getUserFacade(EMF);
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();


    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello anonymous\"}";
    }

    //Just to verify if the database is setup
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String allUsers() {

        EntityManager em = EMF.createEntityManager();
        try {
            TypedQuery<User> query = em.createQuery ("select u from User u",entities.User.class);
            List<User> users = query.getResultList();
            return "[" + users.size() + "]";
        } finally {
            em.close();
        }
    }
    @GET
    @Path("randomuser")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandomUser() throws IOException {
        String catFact = HttpUtils.fetchData("https://randomuser.me/api/");
        return catFact;
    }
    @GET
    @Path("crypto")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCrypto() throws IOException {
        String catFact = HttpUtils.fetchData("https://api.coindesk.com/v1/bpi/currentprice.json");
        return catFact;
    }

    @GET
    @Path("catfact")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCatFacts() throws IOException {
        String catFact = HttpUtils.fetchData("https://catfact.ninja/fact");
        return catFact;
    }

    String thisuserrole = "";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user")
    @RolesAllowed("user")
    public String getFromUser() {
        String thisuser = securityContext.getUserPrincipal().getName();
        if (securityContext.isUserInRole("user")) {
            thisuserrole = "user";
        }
        return "{\"msg\": \"Hello: " + thisuser +  "   -   Role: " + thisuserrole +"\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin")
    @RolesAllowed("admin")
    public String getFromAdmin() {
        String thisuser = securityContext.getUserPrincipal().getName();
        if (securityContext.isUserInRole("admin")) {
            thisuserrole = "admin";
        }
        return "{\"msg\": \"Hello: " + thisuser +  "   -   Role: " + thisuserrole +"\"}";
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin/createUser")
    @RolesAllowed("admin")
    @Consumes(MediaType.APPLICATION_JSON)
    public String createUser(String newUser) {
        String thisuser = securityContext.getUserPrincipal().getName();
        UserDTO userDTO = gson.fromJson(newUser, UserDTO.class);
        userDTO = facade.createUser(userDTO);

        return gson.toJson(userDTO);
    }

    @DELETE
    @Path("admin/deleteUser/{name}")
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String deleteUser(@PathParam("name")String name){
        UserDTO userDTO = facade.deleteUser(name);

        return "Deleted user " + gson.toJson(userDTO);
    }
}