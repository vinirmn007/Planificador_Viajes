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

    public Integer getVertexLabel (E label) throws Exception {
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

    @Override
    public String toString() {
        String graph = "";
        if (islabelsGraph()) {
            graph += "Vertices: \n";
            for (int i = 1; i < labels.length; i++) {
                graph += i + " -> " + labels[i] + "\n";
            }
            graph += "Aristas:\n";
            for (int i = 1; i < nroVertex() + 1; i++) {
                LinkedList<Adyacencia> listAdj = adjacents(i);
                if (!listAdj.isEmpty()) {
                    Adyacencia[] ady = listAdj.toArray();
                    for (int j = 0; j < ady.length; j++) {
                        graph += i + " -> " + ady[j].getDestino() + " con peso " + ady[j].getWeight() + "\n";
                    }
                }
            }
        } else {
            graph = "No se han asignado etiquetas a los vertices";
        }
        return graph;
    }
}
