package com.example.app_techos_vendes.models;

public class CardPlantasModel {

    private int idPlanta;

    private String ImagenPlanta;

    private String NombrePlanta;
    private String NombreCientifico;
    private String DescripcionCorta;
    private String luzPlanta;
    private String floracionPlanta;
    private String SustratoPlanta;
    private String TemporadaPlanta;
    private String FrecuenciaPlanta;

    public CardPlantasModel() {
    }

    public CardPlantasModel(int idPlanta, String imagenPlanta, String nombrePlanta, String nombreCientifico, String descripcionCorta, String luzPlanta, String floracionPlanta, String sustratoPlanta, String temporadaPlanta, String frecuenciaPlanta) {
        this.idPlanta = idPlanta;
        ImagenPlanta = imagenPlanta;
        NombrePlanta = nombrePlanta;
        NombreCientifico = nombreCientifico;
        DescripcionCorta = descripcionCorta;
        this.luzPlanta = luzPlanta;
        this.floracionPlanta = floracionPlanta;
        SustratoPlanta = sustratoPlanta;
        TemporadaPlanta = temporadaPlanta;
        FrecuenciaPlanta = frecuenciaPlanta;
    }

    public int getIdPlanta() {
        return idPlanta;
    }

    public String getImagenPlanta() {
        return ImagenPlanta;
    }

    public String getNombrePlanta() {
        return NombrePlanta;
    }

    public String getNombreCientifico() {
        return NombreCientifico;
    }

    public String getDescripcionCorta() {
        return DescripcionCorta;
    }

    public String getLuzPlanta() {
        return luzPlanta;
    }

    public String getFloracionPlanta() {
        return floracionPlanta;
    }

    public String getSustratoPlanta() {
        return SustratoPlanta;
    }

    public String getTemporadaPlanta() {
        return TemporadaPlanta;
    }

    public String getFrecuenciaPlanta() {
        return FrecuenciaPlanta;
    }

}
