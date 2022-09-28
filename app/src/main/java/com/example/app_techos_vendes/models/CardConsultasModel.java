package com.example.app_techos_vendes.models;

public class CardConsultasModel {
    private int ImageSource;
    private String titulo;

    public CardConsultasModel(int imageSource, String titulo) {
        ImageSource = imageSource;
        this.titulo = titulo;
    }

    public int getImageSource() {
        return ImageSource;
    }

    public String getTitulo() {
        return titulo;
    }
}
