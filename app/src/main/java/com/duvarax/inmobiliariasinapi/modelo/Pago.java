package com.duvarax.inmobiliariasinapi.modelo;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Pago implements Serializable {

    private int id;
    private int identificadorPago;
    private Contrato contrato;
    private Double importe;
    private String fechaPago;
    private int contratoId;

    public Pago() {}

    public Pago(int id, int identificadorPago, Contrato contrato, Double importe, String fechaPago, int contratoId) {
        this.id = id;
        this.identificadorPago = identificadorPago;
        this.contrato = contrato;
        this.importe = importe;
        this.fechaPago = fechaPago;
        this.contratoId = contratoId;
    }

    public Pago(int id, int identificadorPago, Double importe, String fechaPago, int contratoId) {
        this.id = id;
        identificadorPago = identificadorPago;
        this.importe = importe;
        this.fechaPago = fechaPago;
        this.contratoId = contratoId;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public int getidentificadorPago() {
        return identificadorPago;
    }

    public void setidentificadorPago(int identificadorPago) {
        this.identificadorPago = identificadorPago;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public String getfechaPago() {
        return fechaPago;
    }

    public void setfechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public int getcontratoId() {
        return contratoId;
    }

    public void setcontratoId(int contratoId) {
        this.contratoId = contratoId;
    }

    @NonNull
    @Override
    public String toString() {
        return id + " " + identificadorPago + " " + importe + " " + fechaPago;
    }
}
