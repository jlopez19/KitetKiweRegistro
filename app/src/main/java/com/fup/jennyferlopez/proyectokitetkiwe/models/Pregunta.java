package com.fup.jennyferlopez.proyectokitetkiwe.models;


public class Pregunta {

    private String pregunta, resp1, resp2, resp3, resVer;

    public Pregunta(String pregunta, String resp1, String resp2, String resp3, String resVer) {
        this.pregunta = pregunta;
        this.resp1 = resp1;
        this.resp2 = resp2;
        this.resp3 = resp3;
        this.resVer = resVer;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getResp1() {
        return resp1;
    }

    public void setResp1(String resp1) {
        this.resp1 = resp1;
    }

    public String getResp2() {
        return resp2;
    }

    public void setResp2(String resp2) {
        this.resp2 = resp2;
    }

    public String getResp3() {
        return resp3;
    }

    public void setResp3(String resp3) {
        this.resp3 = resp3;
    }

    public String getResVer() {
        return resVer;
    }

    public void setResVer(String resVer) {
        this.resVer = resVer;
    }
}
