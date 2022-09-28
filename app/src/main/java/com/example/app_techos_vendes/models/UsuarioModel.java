package com.example.app_techos_vendes.models;

public class UsuarioModel {

    private String id_usuario;
    private String nombre;
    private String apellidos;
    private String sexo;
    private String password;
    private String correo;

    public UsuarioModel(String id_usuario, String nombre, String apellidos, String sexo, String password, String correo) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.password = password;
        this.correo = correo;
    }


    public String getId_usuario() {
        return id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getSexo() {
        return sexo;
    }

    public String getPassword() {
        return password;
    }

    public String getCorreo() {
        return correo;
    }
}
