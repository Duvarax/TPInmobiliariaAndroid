package com.duvarax.inmobiliariasinapi.modelo;

public class Usuario {

    private String Email;
    private String Contraseña;

    public Usuario(String email, String contraseña) {
        Email = email;
        Contraseña = contraseña;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }
}
