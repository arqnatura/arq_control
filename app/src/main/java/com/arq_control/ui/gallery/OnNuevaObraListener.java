package com.arq_control.ui.gallery;

public interface OnNuevaObraListener {
    public void onObraGuardarClickListener(String promotor, String direccion, String telefono,
                                           String titulo, String tipoObra, String referencia,
                                           long visitasPrevistas, String fechaInicio,
                                           String fechaFinal);


    public void onObraActualizarClickListener(long idObra, String promotor, String direccion,
                                              String telefono, String titulo, String tipoObra,
                                              String referencia, long visitasPrevistas,
                                              String fechaInicio, String fechaFinal);

}
