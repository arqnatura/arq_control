package com.arq_control.models;

import com.arq_control.app.MyApp;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class ObraDB extends RealmObject {

    public static final String OBRADB_ID = "id";
    public static final String OBRADB_PROMOTOR = "promotor";
    public static final String OBRADB_DIRECCION = "direccion";
    public static final String OBRADB_TELEFONO = "telefono";
    public static final String OBRADB_TITULO = "titulo";
    public static final String OBRADB_TIPOOBRA = "tipoObra";
    public static final String OBRADB_REFERENCIA = "referencia";
    public static final String OBRADB_FECHAINICIO = "fechaInicio";
    public static final String OBRADB_FECHAFINAL = "fechaFinal";


    @PrimaryKey
    private long id;
    @Required
    private String promotor;
    private String direccion;
    private String telefono;
    @Required
    private String titulo;
    private String tipoObra;
    @Required
    private String referencia;
    @Required
    private String fechaInicio;
    private String fechaFinal;

    public RealmList<VisitaDB> visitas;

    public ObraDB() { this.id = MyApp.ObraID.incrementAndGet(); }

    public ObraDB(String promotor, String direccion, String telefono, String titulo,
                  String tipoObra, String referencia, String fechaInicio, String fechaFinal,
                  RealmList<VisitaDB> visitas) {

        this.id = MyApp.ObraID.incrementAndGet();
        this.promotor = promotor;
        this.direccion = direccion;
        this.telefono = telefono;
        this.titulo = titulo;
        this.tipoObra = tipoObra;
        this.referencia = referencia;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.visitas = visitas;

    }

    // Estandar getters y setters generados por IDE.
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getReferencia() { return referencia; }

    public void setReferencia(String referencia) { this.referencia = referencia; }

    public String getPromotor() { return promotor; }

    public void setPromotor(String promotor) { this.promotor = promotor; }

    public String getTelefono() { return telefono; }

    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getTipoObra() { return tipoObra; }

    public void setTipoObra(String tipoObra) { this.tipoObra = tipoObra; }

    public String getDireccion() { return direccion; }

    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getFechaInicio() { return fechaInicio; }

    public void setFechaInicio(String fechaInicio) { this.fechaInicio = fechaInicio; }

    public String getFechaFinal() { return fechaFinal; }

    public void setFechaFinal(String fechaFinal) { this.fechaFinal = fechaFinal; }

    public RealmList<VisitaDB> getVisitas() { return visitas; }

    public void setVisitas(RealmList<VisitaDB> visitas) { this.visitas = visitas; }

}
