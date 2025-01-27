package com.viajes.models;

import com.viajes.controller.dao.services.CiudadServices;
import com.viajes.estructures.graph.GraphDirect;
import com.viajes.estructures.graph.GraphLabelNotDirect;
import com.viajes.estructures.graph.GraphNotDirect;

public class prueba {
    public static void main(String[] args) throws Exception {
        CiudadServices ciudadServices = new CiudadServices();
        Ciudad ciudad1 = ciudadServices.get(1);
        Ciudad ciudad2 = ciudadServices.get(2);
        Ciudad ciudad3 = ciudadServices.get(3);

        //.out.println(ciudad1);

        GraphLabelNotDirect<Ciudad> graph = new GraphLabelNotDirect<Ciudad>(3, Ciudad.class);
        graph.labelVertex(1, ciudad1);
        graph.labelVertex(2, ciudad2);
        graph.labelVertex(3, ciudad3);
        graph.addEdgeLabel(ciudad1, ciudad2);
        graph.addEdgeLabel(ciudad1, ciudad3);
        graph.addEdgeLabel(ciudad2, ciudad3);
        
        System.out.println(graph.readGraph());

        //GraphDirect graph2 = new GraphDirect(2);
        //graph2.addEdge(1, 2);
        //System.out.println(graph2);
    }
}
