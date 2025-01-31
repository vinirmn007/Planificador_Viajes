package com.viajes.models;

import com.viajes.estructures.graph.GraphNotDirect;

public class prueba {
    public static GraphNotDirect createGraph(int n) throws Exception {
        GraphNotDirect graph = new GraphNotDirect(n);

        for (int i = 1; i < n; i++) {
            float w = (float) (Math.random() * 10);
            graph.addEdge(i, i + 1, w); 
        }

        for (int i = 1; i <= n; i++) {
            int randomVertex;
            do {
                randomVertex = (int) (Math.random() * n) + 1;
            } while (randomVertex == i);

            float w = (float) (Math.random() * 10);
            graph.addEdge(i, randomVertex, w);
        }

        return graph;
    }

    public static Double calcTime(GraphNotDirect graph, Integer origin, Integer destino, Integer type)
            throws Exception {
        long start = System.nanoTime();
        switch (type) {
            case 1:
                graph.minPathFloyd(origin, destino);
                break;
            case 2:
                graph.minPathBellmanFord(origin, destino);
                break;
            default:
                break;
        }
        long end = System.nanoTime();
        return (double) (end - start)/1000;
    }

    public static void main(String[] args) throws Exception {
        GraphNotDirect graph = createGraph(10);
        GraphNotDirect graph2 = createGraph(20);
        GraphNotDirect graph3 = createGraph(30);

        System.out.println("Floyd 10: " + calcTime(graph, 1, 9, 1) + " micro seg.");
        System.out.println("Bellman 10: " + calcTime(graph, 1, 9, 2) + " micro seg.");
        System.out.println("Floyd 20: " + calcTime(graph2, 1, 19, 1) + " micro seg.");
        System.out.println("Bellman 20: " + calcTime(graph2, 1, 19, 2) + " micro seg.");
        System.out.println("Floyd 30: " + calcTime(graph3, 1, 29, 1) + " micro seg.");
        System.out.println("Bellman 30: " + calcTime(graph3, 1, 29, 2) + " micro seg.");
    }
}
