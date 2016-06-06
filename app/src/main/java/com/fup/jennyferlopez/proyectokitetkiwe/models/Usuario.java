package com.fup.jennyferlopez.proyectokitetkiwe.models;

public class Usuario {

    String nomUsuario;
    String contraUsuario;
    String sesion;

    public Usuario() {
    }

    public Usuario(String nomUsuario, String contraUsuario) {
        this.nomUsuario = nomUsuario;
        this.contraUsuario = contraUsuario;
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
}
