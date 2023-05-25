package com.duvarax.inmobiliariasinapi.modelo;

import java.io.Serializable;
import java.util.Objects;

public class Contrato implements Serializable {

    private int id;
    private String fechaInicio;
    private String fechaFinalizacion;
    private Double precio;
    private Inquilino inquilino;
    private Inmueble inmueble;
    private Boolean estado;

    public Contrato() {}
    public Contrato(int id, String fechaInicio, String fechaFin, Double precio, Inquilino inquilino, Inmueble inmueble, Boolean estado) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFinalizacion = fechaFin;
        this.precio = precio;
        this.inquilino = inquilino;
        this.inmueble = inmueble;
        this.estado = estado;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFinalizacion;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFinalizacion = fechaFin;
    }

    public Double getprecio() {
        return precio;
    }

    public void setprecio(Double precio) {
        this.precio = precio;
    }


    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
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

    @Override
    public String toString() {
        return "Contrato{" +
                "id=" + id +
                ", fechaInicio='" + fechaInicio + '\'' +
                ", fechaFinalizacion='" + fechaFinalizacion + '\'' +
                ", precio=" + precio +
                ", inquilino=" + inquilino +
                ", inmueble=" + inmueble +
                ", estado=" + estado +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contrato contrato = (Contrato) o;
        return id == contrato.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
