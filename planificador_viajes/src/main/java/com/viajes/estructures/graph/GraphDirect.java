package com.viajes.estructures.graph;

import com.viajes.estructures.exception.OverFlowException;
import com.viajes.estructures.list.LinkedList;

public class GraphDirect extends Graph{
    private Integer nroVertex;
    private Integer nroEdges;
    private LinkedList<Adyacencia> listAdy [];

    public GraphDirect(Integer nroVertex){
        this.nroVertex = nroVertex;
        this.nroEdges = 0;
        this.listAdy = new LinkedList[nroVertex + 1];
        for (int i = 1; i <= nroVertex; i++) {
            this.listAdy[i] = new LinkedList<>();
        }
    }

    @Override
    public Integer nroVertex() {
        return this.nroVertex;
    }

    @Override
    public Integer nroEdges() {
        return this.nroEdges;
    }

    @Override
    public Boolean isEdge(Integer v1, Integer v2) throws Exception{
        Boolean band = false;
        if (v1.intValue() <= nroVertex && v2.intValue() <= nroVertex) {
            LinkedList<Adyacencia> adyList= this.listAdy[v1];
            if (!adyList.isEmpty()) {
                Adyacencia[] matrix = adyList.toArray();
                for (int i = 0; i < matrix.length; i++) {
                    Adyacencia aux = matrix[i];
                    if (aux.getDestino().intValue() == v2.intValue()) {
                        band = true;
                        break;
                    }
                }
            }
        }else{
            throw new OverFlowException("Vertice no existe");
        }
        return band;
    }

    @Override
    public Float weighEdge(Integer v1, Integer v2) throws Exception {
        Float weight = 0.0f;
        if (isEdge(v1, v2)) {
            LinkedList<Adyacencia> listAdy = this.listAdy[v1];
            if (!listAdy.isEmpty()) {
                Adyacencia[] matrix = listAdy.toArray();
                for (int i = 0; i < matrix.length; i++) {
                    Adyacencia aux = matrix[i];
                    if (aux.getDestino().intValue() == v2.intValue()) {
                        weight = aux.getWeight();
                        break;
                    }
                }
            }
        } else {
            throw new Exception("No existe arista");
        }
        return weight;
    }

    @Override
    public void addEdge(Integer v1, Integer v2) throws Exception{
        this.addEdge(v1, v2, Float.NaN);
    }

    @Override
    public void addEdge(Integer v1, Integer v2, Float w) throws Exception {
        if (v1.intValue() <= nroVertex && v2.intValue() <= nroVertex) {
            if (!isEdge(v1, v2)) {
                Adyacencia aux = new Adyacencia(v2, w);
                this.listAdy[v1].add(aux);
                this.nroEdges++;
            } 
        } 
    }

    @Override
    public LinkedList<Adyacencia> adjacents(Integer v){
        return this.listAdy[v];
    }

    public void setNroEdges(Integer nroEdges){
        this.nroEdges = nroEdges;
    }

    /*public void setListAdy(LinkedList<Adyacencia>[] listAdy){
        this.listAdy = listAdy;
    }*/

    public LinkedList<Adyacencia>[] getListAdy(){
        return this.listAdy;
    }
}
