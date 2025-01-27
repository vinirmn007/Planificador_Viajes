package com.viajes.controller.dao;

import com.viajes.controller.dao.implement.AdapterDao;
import com.viajes.estructures.list.LinkedList;
import com.viajes.models.Carretera;

public class CarreteraDao extends AdapterDao<Carretera> {
    private Carretera obj;
    private LinkedList listAll;

    public CarreteraDao() {
        super(Carretera.class);
    }

    //GETTERS Y SETTERS
    public Carretera getCarretera() {
        if(this.obj == null) {
            this.obj = new Carretera();
        }
        return this.obj;
    }

    public void setCarretera(Carretera obj) {
        this.obj = obj;
    }

    public LinkedList getListAll() {
        if (listAll == null) {
            this.listAll = listAll();
        }
        return this.listAll;
    }

    //OPERACIONES
    public Boolean save() throws Exception{
        Integer id = getListAll().getSize() +1;
        try {
            obj.setId(id);
            this.persist(this.obj);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean update() throws Exception {
        try {
            this.merge(this.obj, this.obj.getId());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean delete() throws Exception {
        try {
            this.delete(this.obj.getId());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
