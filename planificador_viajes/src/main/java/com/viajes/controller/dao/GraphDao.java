package com.viajes.controller.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Scanner;

import com.google.gson.Gson;
import com.viajes.controller.dao.services.CarreteraServices;
import com.viajes.controller.dao.services.CiudadServices;
import com.viajes.estructures.graph.GraphLabelNotDirect;
import com.viajes.models.Carretera;
import com.viajes.models.Ciudad;

public class GraphDao {
    private GraphLabelNotDirect graph;

    public GraphDao() {
        this.graph = new GraphLabelNotDirect();
    }

    public GraphLabelNotDirect getGraph() throws Exception {
        createGraph();
        return graph;
    }

    public void setGraph(GraphLabelNotDirect graph) {
        this.graph = graph;
    }

    private void addCities() throws Exception {
        CiudadServices cs = new CiudadServices();
        this.graph = new GraphLabelNotDirect(cs.getListAll().getSize(), Ciudad.class);
        for (Object obj : cs.getListAll().toArray()) {
            Ciudad ciudad = (Ciudad) obj;
            this.graph.labelVertex(ciudad.getId(), ciudad);
        }
    }

    private void addRoads() throws Exception {
        CarreteraServices crs = new CarreteraServices();
        CiudadServices cs = new CiudadServices();
        for (Object obj : crs.getListAll().toArray()) {
            Ciudad origen = cs.get(((Carretera) obj).getOrigenId());
            Ciudad destino = cs.get(((Carretera) obj).getDestinoId());
            Double peso = ((Carretera) obj).getDistancia();
            this.graph.addEdgeLabel(origen, destino, peso.floatValue());
        }
    }

    public void createGraph() throws Exception {
        this.addCities();
        this.addRoads();
        this.saveGraph();
    }

    public HashMap toJson() {
        CiudadServices cs = new CiudadServices();
        CarreteraServices crs = new CarreteraServices();

        HashMap<String, Object> grafo = new HashMap<String, Object>();

        Ciudad[] ciudades = (Ciudad[]) cs.getListAll().toArray();
        HashMap<String, Object>[] nodes = new HashMap[ciudades.length];
        for (int i = 0; i < ciudades.length; i++) {
            HashMap<String, Object> node = new HashMap<String, Object>();
            node.put("id", ciudades[i].getId());
            node.put("nombre", ciudades[i].getNombre());
            node.put("lat", ciudades[i].getLatitud());
            node.put("long", ciudades[i].getLongitud());
            nodes[i] = node;
        }
        grafo.put("nodes", nodes);
        
        Carretera[] carreteras = (Carretera[]) crs.getListAll().toArray();
        HashMap<String, Number>[] edges = new HashMap[carreteras.length];
        for (int i = 0; i < carreteras.length; i++) {
            HashMap<String, Number> edge = new HashMap<String, Number>();
            edge.put("from", carreteras[i].getOrigenId());
            edge.put("to", carreteras[i].getDestinoId());
            edge.put("weight", carreteras[i].getDistancia());
            edges[i] = edge;
        }
        grafo.put("edges", edges);
        
        //String json = new Gson().toJson(grafo);
        //System.out.println(json);
        return grafo;
    }

    public void saveGraph () throws Exception {
        try {
            String json = new Gson().toJson(this.toJson());
            FileWriter file = new FileWriter("media/Graph" + ".json");
            file.write(json);
            file.flush();
            file.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public String readGraph() throws Exception {
        File file = new File("media/Graph" + ".json");
        StringBuilder sb = new StringBuilder();
        try (Scanner in = new Scanner(file)) {
            while (in.hasNextLine()) {
                sb.append(in.nextLine());
            }
        } catch (FileNotFoundException e) {
            // TODO: handle exception
        }
        return sb.toString();
    }
}