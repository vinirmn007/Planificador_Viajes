package com.viajes.controller.dao.services;

import com.viajes.controller.dao.CarreteraDao;
import com.viajes.estructures.list.LinkedList;
import com.viajes.models.Carretera;

public class CarreteraServices {
    private CarreteraDao obj;

    public CarreteraServices() {
        this.obj = new CarreteraDao();
    }

    public Carretera getCarretera() {
        return this.obj.getCarretera();
    }

    public void setCarretera(Carretera obj) {
        this.obj.setCarretera(obj);
    }

    public LinkedList getListAll() {
        return this.obj.getListAll();
    }

    public Boolean save() throws Exception {
        return this.obj.save();
    }

    public Boolean update() throws Exception {
        return this.obj.update();
    }

    public Boolean delete() throws Exception {
        return this.obj.delete();
    }

    public Carretera get(Integer id) throws Exception {
        return this.obj.get(id);
    }

    public LinkedList orderByQuickSort(String attribute, Integer type) throws Exception {
        return this.obj.listAll().orderByQuickSort(attribute, type);
    }
}
