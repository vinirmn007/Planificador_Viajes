package com.viajes.controller.dao.services;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.viajes.estructures.graph.GraphLabelNotDirect;
import com.viajes.models.Ciudad;

public class GraphServices {
    GraphLabelNotDirect graph;
    CiudadServices cs = new CiudadServices();

    public GraphServices(){
        this.graph = new GraphLabelNotDirect();
    }

    public GraphLabelNotDirect getGraph() {
        return graph;
    }

    public void setGraph(GraphLabelNotDirect graph) {
        this.graph = graph;
    }

    public void saveCityGraph(Ciudad origen, Ciudad destino, Double peso) throws Exception {
        this.graph = new GraphLabelNotDirect(cs.getListAll().getSize(), Ciudad.class);
        this.graph.labelVertex(origen.getId(), origen);
        this.graph.labelVertex(destino.getId(), destino);
        this.graph.addEmptyLabels(new Ciudad());
        this.graph.addEdgeLabel(origen, destino, peso.floatValue());
        this.graph.saveGraph();
    }

    public JSONObject graphJson() throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(this.graph.readGraph());
        return json;
    }


}
