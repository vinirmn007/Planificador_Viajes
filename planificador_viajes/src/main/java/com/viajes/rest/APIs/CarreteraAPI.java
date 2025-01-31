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

import com.viajes.controller.dao.services.CarreteraServices;
import com.viajes.controller.dao.services.CiudadServices;
import com.viajes.controller.dao.services.GraphServices;
import com.viajes.models.Carretera;
import com.viajes.models.Ciudad;

@Path("carreteras")
public class CarreteraAPI {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCarreteras() throws Exception {
        HashMap map = new HashMap<>();
        CarreteraServices cs = new CarreteraServices();

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
    public Response saveCarreteras(HashMap map) {
        HashMap res = new HashMap<>();

        try {
            CarreteraServices cs = new CarreteraServices();
            CiudadServices cds = new CiudadServices();

            if (cds.get(Integer.parseInt(map.get("origen").toString())) == null || cds.get(Integer.parseInt(map.get("destino").toString())) == null) {
                res.put("msg", "Error");
                res.put("data", "No existe alguna de las ciudades");
                return Response.status(Response.Status.BAD_REQUEST).entity(res).build(); 
            }

            cs.getCarretera().setNombre(map.get("nombre").toString());
            cs.getCarretera().setDistancia(Double.parseDouble(map.get("distancia").toString()));
            cs.getCarretera().setTiempo(Double.parseDouble(map.get("tiempo").toString()));
            cs.getCarretera().setTipoCarretera(map.get("tipo").toString());
            cs.getCarretera().setOrigenId(Integer.parseInt(map.get("origen").toString()));
            cs.getCarretera().setDestinoId(Integer.parseInt(map.get("destino").toString()));
            cs.save();
            
            res.put("msg", "OK");
            res.put("data", "Carretera registrada correctamente");

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
        CarreteraServices cs = new CarreteraServices();
        Carretera carretera = cs.get(id);

        if (carretera == null || carretera.getId() == null) {
            map.put("msg", "Error");
            map.put("data", "No existe esa carretera");
            return Response.status(Response.Status.BAD_REQUEST).entity(map).build();
        }

        map.put("msg", "OK");
        map.put("data", carretera);

        return Response.ok(map).build();
    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCarreteras(HashMap map) {
        HashMap res = new HashMap<>();

        try {
            CarreteraServices cs = new CarreteraServices();

            cs.setCarretera(cs.get(Integer.parseInt(map.get("id").toString())));
            cs.getCarretera().setNombre(map.get("nombre").toString());
            cs.getCarretera().setTiempo(Double.parseDouble(map.get("tiempo").toString()));
            cs.getCarretera().setTipoCarretera(map.get("tipo").toString());
            cs.update();

            res.put("msg", "OK");
            res.put("data", "Carretera actualizada correctamente");

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
    public Response deleteCarreteras(HashMap map) {
        HashMap res = new HashMap<>();

        try {
            CarreteraServices cs = new CarreteraServices();
            
            Integer id = Integer.parseInt(map.get("id").toString());
            Carretera carretera = cs.get(id);

            if (carretera == null || carretera.getId() == null) {
                res.put("msg", "Error");
                res.put("data", "No existe esa carretera");
                return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
            }

            cs.setCarretera(carretera);
            cs.delete();

            res.put("msg", "OK");
            res.put("data", "Carretera eliminada correctamente");

            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("msg", "Error");
            res.put("data", e.toString());

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }
}
