package com.viajes.rest.APIs;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.viajes.controller.dao.services.CiudadServices;
import com.viajes.models.Ciudad;

@Path("ciudades")
public class CiudadAPI {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCiudades() throws Exception {
        HashMap map = new HashMap<>();
        CiudadServices cs = new CiudadServices();

        map.put("msg", "OK");
        map.put("data", cs.getListAll().toArray());

        if (cs.getListAll().isEmpty()) {
            map.put("data", new Object[]{});
        }

        return Response.ok(map).build();
    }

    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveCiudades(HashMap map) {
        HashMap res = new HashMap<>();

        try {
            CiudadServices cs = new CiudadServices();

            cs.getCiudad().setNombre(map.get("nombre").toString());
            cs.getCiudad().setPais(map.get("pais").toString());
            cs.getCiudad().setRegion(map.get("region").toString());
            cs.getCiudad().setClima(map.get("clima").toString());
            cs.getCiudad().setDescripcion(map.get("desc").toString());
            cs.getCiudad().setLatitud(Double.parseDouble(map.get("latitud").toString()));
            cs.getCiudad().setLongitud(Double.parseDouble(map.get("longitud").toString()));
            cs.save();

            res.put("msg", "OK");
            res.put("data", "Ciudad registrada correctamente");

            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("msg", "Error");
            res.put("data", e.toString());

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFamily(@PathParam("id") Integer id) throws Exception {
        HashMap map = new HashMap<>();
        CiudadServices cs = new CiudadServices();
        Ciudad ciudad = cs.get(id);

        if (ciudad == null || ciudad.getId() == null) {
            map.put("msg", "Error");
            map.put("data", "No existe esa ciudad");
            return Response.status(Response.Status.BAD_REQUEST).entity(map).build();
        }

        map.put("msg", "OK");
        map.put("data", ciudad);

        return Response.ok(map).build();
    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCiudades(HashMap map) {
        HashMap res = new HashMap<>();

        try {
            CiudadServices cs = new CiudadServices();

            cs.setCiudad(cs.get(Integer.parseInt(map.get("id").toString())));
            cs.getCiudad().setClima(map.get("clima").toString());
            cs.getCiudad().setDescripcion(map.get("desc").toString());
            cs.update();

            res.put("msg", "OK");
            res.put("data", "Ciudad actualizada correctamente");

            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("msg", "Error");
            res.put("data", e.toString());

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @Path("/delete")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCiudades(HashMap map) {
        HashMap res = new HashMap<>();

        try {
            CiudadServices cs = new CiudadServices();
            
            Integer id = Integer.parseInt(map.get("id").toString());
            Ciudad ciudad = cs.get(id);

            if (ciudad == null || ciudad.getId() == null) {
                res.put("msg", "Error");
                res.put("data", "No existe esa ciudad");
                return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
            }

            cs.setCiudad(ciudad);
            cs.delete();

            res.put("msg", "OK");
            res.put("data", "Ciudad eliminada correctamente");

            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("msg", "Error");
            res.put("data", e.toString());

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }
}
