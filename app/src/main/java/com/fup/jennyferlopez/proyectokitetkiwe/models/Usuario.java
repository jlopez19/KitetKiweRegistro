package com.fup.jennyferlopez.proyectokitetkiwe.models;

public class Usuario {

    String nomUsuario;
    String contraUsuario;
    String pathImg;
    String sesion;

    public Usuario() {
    }

    public Usuario(String nomUsuario, String contraUsuario, String pathImg, String sesion) {
        this.nomUsuario = nomUsuario;
        this.contraUsuario = contraUsuario;
        this.pathImg = pathImg;
        this.sesion = sesion;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getContraUsuario() {
        return contraUsuario;
    }

    public void setContraUsuario(String contraUsuario) {
        this.contraUsuario = contraUsuario;
    }

    public String getPathImg() {
        return pathImg;
    }

    public void setPathImg(String pathImg) {
        this.pathImg = pathImg;
    }

    public String getSesion() {
        return sesion;
    }

    public void setSesion(String sesion) {
        this.sesion = sesion;
    }
}
