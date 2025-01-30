package com.viajes.models;

import java.util.Objects;

public class Carretera {
    private Integer id;
    private String nombre;
    private double distancia;
    private double tiempo;
    private String tipoCarretera;
    private Integer origenId;
    private Integer destinoId;

    public Carretera() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getDistancia() {
        return this.distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public double getTiempo() {
        return this.tiempo;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    public String getTipoCarretera() {
        return this.tipoCarretera;
    }

    public void setTipoCarretera(String tipoCarretera) {
        this.tipoCarretera = tipoCarretera;
    }

    public Integer getOrigenId() {
        return this.origenId;
    }

    public void setOrigenId(Integer origenId) {
        this.origenId = origenId;
    }

    public Integer getDestinoId() {
        return this.destinoId;
    }

    public void setDestinoId(Integer destinoId) {
        this.destinoId = destinoId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Carretera carretera = (Carretera) obj;
        return id.equals(carretera.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
