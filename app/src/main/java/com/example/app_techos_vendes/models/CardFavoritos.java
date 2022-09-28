package com.example.app_techos_vendes.models;

public class CardFavoritos extends CardPlantasModel{
    private int idFavorito;

    public CardFavoritos(
            int idPlanta,
            String imagenPlanta,
            String nombrePlanta,
            String nombreCientifico,
            String descripcionCorta,
            String luzPlanta,
            String floracionPlanta,
            String sustratoPlanta,
            String temporadaPlanta,
            String frecuenciaPlanta,
            int idFavorito) {
        super(idPlanta, imagenPlanta, nombrePlanta, nombreCientifico, descripcionCorta, luzPlanta, floracionPlanta, sustratoPlanta, temporadaPlanta, frecuenciaPlanta);
        this.idFavorito = idFavorito;
    }

    public int getIdFavorito() {
        return idFavorito;
    }
}
