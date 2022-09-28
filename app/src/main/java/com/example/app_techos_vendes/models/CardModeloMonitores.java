package com.example.app_techos_vendes.models;

public class CardModeloMonitores {

    private String tituloMonitor;
    private String contenidoMonitor;

    public CardModeloMonitores(String tituloMonitor, String contenidoMonitor) {
        this.tituloMonitor = tituloMonitor;
        this.contenidoMonitor = contenidoMonitor;
    }

    public String getTituloMonitor() {
        return tituloMonitor;
    }

    public String getContenidoMonitor() {
        return contenidoMonitor;
    }
}
