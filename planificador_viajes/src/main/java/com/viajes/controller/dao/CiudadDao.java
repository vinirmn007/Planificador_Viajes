package com.viajes.controller.dao;

import com.viajes.controller.dao.implement.AdapterDao;
import com.viajes.estructures.list.LinkedList;
import com.viajes.models.Ciudad;

public class CiudadDao extends AdapterDao<Ciudad> {
    private Ciudad obj;
    private LinkedList listAll;

    public CiudadDao() {
        super(Ciudad.class);
    }

    //GETTERS Y SETTERS
    public Ciudad getCiudad() {
        if(this.obj == null) {
            this.obj = new Ciudad();
        }
        return this.obj;
    }

    public void setCiudad(Ciudad obj) {
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
