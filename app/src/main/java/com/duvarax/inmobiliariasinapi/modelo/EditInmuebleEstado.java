package com.duvarax.inmobiliariasinapi.modelo;

import java.io.Serializable;
import java.util.Objects;

public class EditInmuebleEstado implements Serializable {

    private int id;
    private Boolean estado;

    public EditInmuebleEstado(int id, Boolean estado) {
        this.id = id;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
