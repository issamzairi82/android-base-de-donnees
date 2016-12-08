package com.example.acer.osadd;

import java.io.Serializable;

/**
 * Created by Acer on 20/11/2016.
 */
public class Fichier_info implements Serializable {

    public String ops ;
    public String logo;
    public String description;
    String nombre_user;
    String nombre_version;
    String nombre_smart  ;


    public Fichier_info(String ops, String logo, String description, String nombre_user, String nombre_version, String nombre_smart) {
        this.ops = ops;
        this.logo = logo;
        this.description = description;
        this.nombre_user = nombre_user;
        this.nombre_version = nombre_version;
        this.nombre_smart = nombre_smart;
    }


    public String getOps() {
        return ops;
    }

    public void setOps(String ops) {
        this.ops = ops;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNombre_user() {
        return nombre_user;
    }

    public void setNombre_user(String nombre_user) {
        this.nombre_user = nombre_user;
    }

    public String getNombre_version() {
        return nombre_version;
    }

    public void setNombre_version(String nombre_version) {
        this.nombre_version = nombre_version;
    }

    public String getNombre_smart() {
        return nombre_smart;
    }

    public void setNombre_smart(String nombre_smart) {
        this.nombre_smart = nombre_smart;
    }
}