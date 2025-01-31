package com.viajes.models;

import com.viajes.controller.dao.GraphDao;
import com.viajes.controller.dao.services.CiudadServices;
import com.viajes.controller.dao.services.GraphServices;
import com.viajes.estructures.graph.GraphDirect;
import com.viajes.estructures.graph.GraphLabelNotDirect;
import com.viajes.estructures.graph.GraphNotDirect;

public class prueba {
    public static void main(String[] args) throws Exception {
        CiudadServices ciudadServices = new CiudadServices();
        /*Ciudad ciudad1 = ciudadServices.get(1);
        Ciudad ciudad2 = ciudadServices.get(2);
        Ciudad ciudad3 = ciudadServices.get(3);
        Ciudad ciudad4 = ciudadServices.get(4);
        Ciudad ciudad5 = ciudadServices.get(5);
        Ciudad ciudad6 = ciudadServices.get(6);

        //.out.println(ciudad1);

        GraphLabelNotDirect<Ciudad> graph = new GraphLabelNotDirect<Ciudad>(4, Ciudad.class);
        graph.labelVertex(1, ciudad1);
        graph.labelVertex(2, ciudad2);
        graph.labelVertex(3, ciudad3);
        graph.labelVertex(4, ciudad4);
        graph.addEdgeLabel(ciudad1, ciudad2, 10.0f);
        graph.addEdgeLabel(ciudad1, ciudad3, 5.0f);
        graph.addEdgeLabel(ciudad2, ciudad3, 30.0f);
        graph.addEdgeLabel(ciudad3, ciudad4, 100.0f);

        Integer[] path = graph.minPathFloyd(4, 2);
        for (int i = 0; i < path.length; i++) {
            System.out.println(path[i]);
        }

        Integer[] path2 = graph.BellmanFord(4, 2);
        for (int i = 0; i < path2.length; i++) {
            System.out.println(path2[i]);
        }*/

        GraphServices gs = new GraphServices();
        GraphLabelNotDirect graph = gs.getGraph();
        Integer[] path = graph.minPathFloyd(3, 1);
        System.out.println("Floyd");
        for (int i = 0; i < path.length; i++) {
            System.out.println(path[i]);
        }
        System.out.println("Bellman");
        Integer[] path2 = graph.minPathBellmanFord(3, 1);
        for (int i = 0; i < path2.length; i++) {
            System.out.println(path2[i]);
        }
    }
}
