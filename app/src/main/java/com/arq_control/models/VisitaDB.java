package com.arq_control.models;

import com.arq_control.app.MyApp;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class VisitaDB extends RealmObject {
    @PrimaryKey
    private int id;
    private String referencia;
    private String titulo;
    private Date fecha;
    private String almacenFoto;
    private String descripcion;
    private int numeroVisita;

    public VisitaDB() {
        this.id = MyApp.VisitaID.incrementAndGet();
    }

    public VisitaDB(String referencia, String titulo, Date fecha, String almacenFoto, String descripcion, int numeroVisita) {
        this.id = MyApp.VisitaID.incrementAndGet();
        this.referencia = referencia;
        this.titulo = titulo;
        this.fecha = fecha;
        this.almacenFoto = almacenFoto;
        this.descripcion = descripcion;
        this.numeroVisita = numeroVisita;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getReferencia() { return referencia; }

    public void setReferencia(String referencia) { this.referencia = referencia; }

    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) { this.titulo = titulo; }

    public Date getFecha() { return fecha; }

    public void setFecha(Date fecha) { this.fecha = fecha; }

    public String getAlmacenFoto() { return almacenFoto; }

    public void setAlmacenFoto(String almacenFoto) { this.almacenFoto = almacenFoto; }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getNumeroVisita() { return numeroVisita; }

    public void setNumeroVisita(int numeroVisita) { this.numeroVisita = numeroVisita; }
}
