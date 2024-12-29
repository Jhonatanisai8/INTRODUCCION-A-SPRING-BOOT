package com.jhonatan.clase01Spring.domain;

public class Cliente { //clase pojo
    //atributos
    private int idCliente;
    private String nombre;
    private String nombreUsuario;
    private String password;

    //contructor
    public Cliente(int idCliente, String nombre, String nombreUsuario, String password) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }

    //getters y setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

