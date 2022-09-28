package com.example.app_techos_vendes.models;

import android.app.Application;

public class AppContainer extends Application {
    private String valorUsuario = "Valor inicial";
    private String hostAPI = "128.0.57.145:5500";

    public String getValorUsuario() {
        return valorUsuario;
    }

    public String getHostAPI() {
        return hostAPI;
    }

    public void setValorUsuario(String valorUsuario) {
        this.valorUsuario = valorUsuario;
    }
}
