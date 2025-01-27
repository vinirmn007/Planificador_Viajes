package com.viajes.controller.dao.services;

import com.viajes.controller.dao.CiudadDao;
import com.viajes.estructures.list.LinkedList;
import com.viajes.models.Ciudad;

public class CiudadServices {
    private CiudadDao obj;

    public CiudadServices() {
        this.obj = new CiudadDao();
    }

    public Ciudad getCiudad() {
        return this.obj.getCiudad();
    }

    public void setCiudad(Ciudad obj) {
        this.obj.setCiudad(obj);
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

    public Ciudad get(Integer id) throws Exception {
        return this.obj.get(id);
    }

    public LinkedList orderByQuickSort(String attribute, Integer type) throws Exception {
        return this.obj.listAll().orderByQuickSort(attribute, type);
    }
}
