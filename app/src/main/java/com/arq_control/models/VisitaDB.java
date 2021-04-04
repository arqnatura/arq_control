package com.arq_control.models;

import com.arq_control.app.MyApp;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class VisitaDB extends RealmObject {

    public static final String VISITADB_ID = "id";
    public static final String VISITADB_TITULO = "tituloVisita";
    public static final String VISITADB_FECHA = "fecha";
    public static final String VISITADB_NUMEROVISITA = "numeroVisita";
    public static final String VISITADB_DESCRIPCION = "descripcion";
    public static final String VISITADB_ALMACENFOTO = "almacenFoto";

    @PrimaryKey
    private long id;
    @Required
    private String tituloVisita;
    @Required
    private String fecha;
    private long numeroVisita;
    private String descripcion;
    private String almacenFoto;


    public VisitaDB() {
        this.id = MyApp.VisitaID.incrementAndGet();
    }

    public VisitaDB(String tituloVisita, String fecha, long numeroVisita, String descripcion,
                    String almacenFoto) {

        this.id = MyApp.VisitaID.incrementAndGet();
        this.tituloVisita = tituloVisita;
        this.fecha = fecha;
        this.numeroVisita = numeroVisita;
        this.descripcion = descripcion;
        this.almacenFoto = almacenFoto;

    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getTituloVisita() { return tituloVisita; }
    public void setTituloVisita(String titulo) { this.tituloVisita = titulo; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public String getAlmacenFoto() { return almacenFoto; }
    public void setAlmacenFoto(String almacenFoto) { this.almacenFoto = almacenFoto; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public long getNumeroVisita() { return numeroVisita; }
    public void setNumeroVisita(long numeroVisita) { this.numeroVisita = numeroVisita; }

}
