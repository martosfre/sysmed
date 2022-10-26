/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.sysmed.beans.services.rs;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import javax.ws.rs.core.MediaType;
import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import javax.ws.rs.core.UriInfo;

/**
 * Servicio Web para administrar las operaciones de Tipo de Paciente
 *
 * @author martosfre
 */
@Path("/usuario")
@Consumes(value = MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class UserService {

    @Context
    private UriInfo uriInfo;
    
//    @Inject
//    private KeyGenerator keyGenerator;

    @POST
    @Path("/validar")
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response validarUsuario(@QueryParam("usuario") String usuario,
            @QueryParam("clave") String clave) {
        try {
            //Validar usuario con credenciales
            if (usuario != null && usuario.equals("admin") && clave != null
                    & clave.equals("1234")) {
                //Generar token
                String token = null;//generarToken(usuario);
                return Response.ok().header(AUTHORIZATION, "Sysmed" + token).build();
            } else {
                return Response.status(UNAUTHORIZED).build();
            }

        } catch (Exception e) {
            return Response.status(UNAUTHORIZED).build();
        }
    }

//    private String generarToken(String usuario) {
//        Key key = keyGenerator.generateKey();
//        String jwtToken = Jwts.builder()
//                .setSubject(usuario)
//                .setIssuer(uriInfo.getAbsolutePath().toString())
//                .setIssuedAt(new Date())
//                .setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
//                .signWith(key, SignatureAlgorithm.HS512)
//                .compact();
//        return jwtToken;
//    }
    
     private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}
