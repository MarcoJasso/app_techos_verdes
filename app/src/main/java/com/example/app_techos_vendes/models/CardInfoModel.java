package com.example.app_techos_vendes.models;

public class CardInfoModel {

    private String Titulo;
    private String Subtitulo;
    private String Contenido;
    private int imagenSource;

    public CardInfoModel(String titulo, String subtitulo, String contenido, int imagenSource) {
        Titulo = titulo;
        Subtitulo = subtitulo;
        Contenido = contenido;
        this.imagenSource = imagenSource;
    }

    public String getTitulo() {
        return Titulo;
    }

    public String getSubtitulo() {
        return Subtitulo;
    }

    public String getContenido() {
        return Contenido;
    }

    public int getImagenSource() {
        return imagenSource;
    }
}
