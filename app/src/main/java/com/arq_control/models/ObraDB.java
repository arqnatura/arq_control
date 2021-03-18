package com.arq_control.models;

import com.arq_control.app.MyApp;
import java.util.Date;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ObraDB extends RealmObject {

    @PrimaryKey
    private int id;
    private String promotor;
    private String titulo;
    private String tipoObra;
    private String direccion;
    private Date fechaInicio;
    private Date fechaFinal;
    private String almacenFoto;
    private int numeroVisitas;
    private String referencia;

    public ObraDB() {
        this.id = MyApp.ObraID.incrementAndGet();
    }

    public ObraDB(String promotor, String titulo, String tipoObra, String almacenFoto, int numeroVisitas, String referencia) {
        this.id = MyApp.ObraID.incrementAndGet();
        this.promotor = promotor;
        this.titulo = titulo;
        this.tipoObra = tipoObra;
        this.almacenFoto = almacenFoto;
        this.numeroVisitas = numeroVisitas;
        this.referencia = referencia;
    }

    public ObraDB(String referencia, String promotor, String titulo, String tipoObra,
                  String direccion, Date fechaInicio, Date fechaFinal, String almacenFoto, int numeroVisitas) {
        this.id = MyApp.ObraID.incrementAndGet();
        this.referencia = referencia;
        this.promotor = promotor;
        this.titulo = titulo;
        this.tipoObra = tipoObra;
        this.direccion = direccion;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.almacenFoto = almacenFoto;
        this.numeroVisitas = numeroVisitas;
    }

    public long getId() { return id; }

    public void setId(int id) { this.id = id; }

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

    public Date getFechaInicio() { return fechaInicio; }

    public void setFechaInicio(Date fechaInicio) { this.fechaInicio = fechaInicio; }

    public Date getFechaFinal() { return fechaFinal; }

    public void setFechaFinal(Date fechaFinal) { this.fechaFinal = fechaFinal; }

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
