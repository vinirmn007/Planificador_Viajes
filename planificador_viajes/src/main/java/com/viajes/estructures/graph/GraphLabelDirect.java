package com.viajes.estructures.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Scanner;

import com.viajes.estructures.exception.LabelException;
import com.viajes.estructures.list.LinkedList;
import com.viajes.models.Ciudad;

public class GraphLabelDirect<E> extends GraphDirect {
    protected E labels [];
    protected HashMap<E, Integer> dicVertext;
    private Class<E> clazz;

    public GraphLabelDirect(Integer nroVertex, Class<E> clazz) {
        super(nroVertex);
        this.clazz = clazz;
        this.labels = (E[]) Array.newInstance(clazz, nroVertex + 1);
        this.dicVertext = new HashMap<>();
    }

    public GraphLabelDirect(){
        super(0);
    }

    public Boolean isEdgeLabel(E v1, E v2) throws Exception {
        if (islabelsGraph()) {
            return isEdge(getVertexLabel(v1), getVertexLabel(v2));
        } else {
            throw new LabelException("No se han asignado etiquetas a los vertices");
        }
    }

    public void addEdgeLabel(E v1, E v2, Float w) throws Exception {
        if (islabelsGraph()) {
            addEdge(getVertexLabel(v1), getVertexLabel(v2), w);
        } else {
            throw new LabelException("No se han asignado etiquetas a los vertices");
        }
    }

    public void addEdgeLabel(E v1, E v2) throws Exception {
        if (islabelsGraph()) {
            addEdge(getVertexLabel(v1), getVertexLabel(v2), 0.0f);
        } else {
            throw new LabelException("No se han asignado etiquetas a los vertices");
        }
    }

    public LinkedList<Adyacencia> adjacentsLabel(E v) throws Exception {
        if (islabelsGraph()) {
            return adjacents(getVertexLabel(v));
        } else {
            throw new LabelException("No se han asignado etiquetas a los vertices");
        }
    }

    public void labelVertex (Integer vertex, E label) {
        labels[vertex] = label;
        dicVertext.put(label, vertex);
    }

    public Boolean islabelsGraph () {
        Boolean band = true;
        for (int i = 1; i < labels.length; i++) {
            if (labels[i] == null) {
                band = false;
                break;
            }
        }
        return band;
    }

    public Integer getVertexLabel (E label) {
        return dicVertext.get(label);
    }

    public E getLabel (Integer vertex) {
        return labels[vertex];
    }

    public void addEmptyLabels(E label) {
        for (int i = 1; i < labels.length; i++) {
            if (labels[i] == null) {
                labels[i] = label;
            }
        }
    }

    public String toJson() {
        String grafo = "{";
        try {
            grafo += "\"nodes\": [";
            for (int i = 1; i < this.nroVertex()+1; i++) {
                Ciudad ciudad = (Ciudad) getLabel(i);
                grafo += String.format("{\"id\": %d, \"nombre\": \"%s\",", ciudad.getId(), ciudad.getNombre());
                grafo += "\"lat\":" + "\"" + ciudad.getLatitud() + "\"" + ",";
                grafo += "\"long\":" + "\"" + ciudad.getLongitud() + "\"" + "}";
                if (i < this.nroVertex()) {
                    grafo += ",";   
                }
            }
            grafo += "],";
            grafo += "\"edges\":[";
            for (int i = 1; i < this.nroVertex()+1; i++) {
                LinkedList<Adyacencia> listAdj = adjacents(i);
                if (!listAdj.isEmpty()) {
                    Adyacencia[] ady = listAdj.toArray();
                    for (int j = 0; j < ady.length; j++) {
                        grafo += String.format("{\"from\": %d, \"to\": %d, \"weight\": \"%f\"}", i, ady[j].getDestino(), ady[j].getWeight());
                        if (i < this.nroVertex() || j < ady.length-1) {
                            grafo += ",";
                        }
                    }
                }
            }
            grafo += "]";
        } catch (Exception e) {
            //TODO: handle exception
        }
        grafo += "}";
        return grafo;
    }

    public void saveGraph () throws Exception {
        try {
            FileWriter file = new FileWriter("media/Graph" + ".json");
            file.write(this.toJson());
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
