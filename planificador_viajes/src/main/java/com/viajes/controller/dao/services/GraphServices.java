package com.viajes.controller.dao.services;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.viajes.controller.dao.GraphDao;
import com.viajes.estructures.graph.GraphLabelNotDirect;
import com.viajes.models.Ciudad;

public class GraphServices {
    GraphDao obj;

    public GraphServices(){
        this.obj = new GraphDao();
    }

    public GraphLabelNotDirect getGraph() throws Exception {
        return this.obj.getGraph();
    }

    public void setGraph(GraphLabelNotDirect graph) {
        this.obj.setGraph(graph);
    }

    public JSONObject graphJson() throws Exception {
        return this.obj.graphJson();
    }

    public void createGraph() throws Exception {
        this.obj.createGraph();
    }

    public Integer[] getMinPath(String algoritmo, Integer origen, Integer destino) throws Exception {
        switch (algoritmo) {
            case "bellman":
                //return this.getGraph().minPathBellman(origen, destino);
            case "floyd":
                return this.getGraph().minPathFloyd(origen, destino);
            default:
                return null;
        }
    }

    public Float getMinWeight(String algoritmo, Integer origen, Integer destino) throws Exception {
        switch (algoritmo) {
            case "bellman":
                //return this.getGraph().minPathBellman(origen, destino);
            case "floyd":
                return this.getGraph().minWeightFloyd(origen, destino);
            default:
                return null;
        }
    }
}
