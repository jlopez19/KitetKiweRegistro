package com.fup.jennyferlopez.proyectokitetkiwe.models;

/**
 * Created by Jennyfer Lopez on 04/03/2016.
 */
public class Puntos {

    int id_usuario_r;
    int puntos;

    public Puntos(int id_usuario_r, int puntos) {
        this.id_usuario_r = id_usuario_r;
        this.puntos = puntos;
    }

    public Puntos() {
    }


    public int getId_usuario_r() {
        return id_usuario_r;
    }

    public void setId_usuario_r(int id_usuario_r) {
        this.id_usuario_r = id_usuario_r;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
