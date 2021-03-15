package com.arq_control.ui.gallery;

public class Obra {
    private String referencia;
    private String promotor;
    private String titulo;
    private String tipoObra;
    private String direccion;
    private String almacenFoto;
    private int numeroVisitas;

    public Obra() {
    }

    public Obra(String promotor, String titulo, String tipoObra, String almacenFoto, int numeroVisitas) {
        this.promotor = promotor;
        this.titulo = titulo;
        this.tipoObra = tipoObra;
        this.almacenFoto = almacenFoto;
        this.numeroVisitas = numeroVisitas;
    }

    public Obra(String referencia, String promotor, String titulo, String tipoObra, String direccion, String almacenFoto, int nuemeroVisitas) {
        this.referencia = referencia;
        this.promotor = promotor;
        this.titulo = titulo;
        this.tipoObra = tipoObra;
        this.direccion = direccion;
        this.almacenFoto = almacenFoto;
        this.numeroVisitas = nuemeroVisitas;
    }

    public String getReferencia() { return referencia; }

    public void setReferencia(String referencia) { this.referencia = referencia; }

    public String getPromotor() { return promotor; }

    public void setPromotor(String promotor) { this.promotor = promotor; }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipoObra() {
        return tipoObra;
    }

    public void setTipoObra(String tipoObra) {
        this.tipoObra = tipoObra;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getAlmacenFoto() {
        return almacenFoto;
    }

    public void setAlmacenFoto(String almacenFoto) {
        this.almacenFoto = almacenFoto;
    }

    public int getNumeroVisitas() {
        return numeroVisitas;
    }

    public void setNumeroVisitas(int numeroVisitas) {
        this.numeroVisitas = numeroVisitas;
    }
}
