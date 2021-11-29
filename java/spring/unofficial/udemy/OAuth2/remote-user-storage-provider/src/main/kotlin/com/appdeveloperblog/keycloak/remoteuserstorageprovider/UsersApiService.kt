package com.appdeveloperblog.keycloak.remoteuserstorageprovider

import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
interface UsersApiService {

    @GET
    @Path("/{userName}")
    fun getUserDetails(@PathParam("userName") userName: String):User
}
