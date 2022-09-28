package com.example.app_techos_vendes.models;

public class CardTiendaModel {

    private int idTienda;
    private String UrlImagenTienda;
    private String UrlPaginaTienda;
    private String NombreTienda;
    private String DescripcionTienda;
    private String Direccion;
    private String telefionoTienda;

    public CardTiendaModel(int idTienda, String urlImagenTienda, String urlPaginaTienda, String nombreTienda, String descripcionTienda, String direccion, String telefionoTienda) {
        this.idTienda = idTienda;
        UrlImagenTienda = urlImagenTienda;
        UrlPaginaTienda = urlPaginaTienda;
        NombreTienda = nombreTienda;
        DescripcionTienda = descripcionTienda;
        Direccion = direccion;
        this.telefionoTienda = telefionoTienda;
    }

    public int getIdTienda() {
        return idTienda;
    }

    public String getUrlImagenTienda() {
        return UrlImagenTienda;
    }

    public String getUrlPaginaTienda() {
        return UrlPaginaTienda;
    }

    public String getNombreTienda() {
        return NombreTienda;
    }

    public String getDescripcionTienda() {
        return DescripcionTienda;
    }

    public String getDireccion() {
        return Direccion;
    }

    public String getTelefionoTienda() {
        return telefionoTienda;
    }
}
