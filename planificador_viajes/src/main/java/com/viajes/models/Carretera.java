package com.viajes.models;

public class Carretera {
    private Integer id;
    private String nombre;
    private double distancia;
    private double tiempo;
    private String tipoCarretera;
    private Integer oigenId;
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
        return this.oigenId;
    }

    public void setOrigenId(Integer oigenId) {
        this.oigenId = oigenId;
    }

    public Integer getDestinoId() {
        return this.destinoId;
    }

    public void setDestinoId(Integer destinoId) {
        this.destinoId = destinoId;
    }
}
