package com.appdeveloperblog.keycloak.remoteuserstorageprovider

import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
interface UsersApiService {

    @GET
    @Path("/{userName}")
    fun getUserDetails(@PathParam("userName") userName: String): User

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{userName}/verify-password")
    fun verifyUserPassword(@PathParam("userName") userName: String, password: String): VerifyPasswordResponse
}
