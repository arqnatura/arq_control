package com.arq_control.ui.gallery;

public interface OnNuevaObraListener {
    public void onObraGuardarClickListener(String promotor, String direccion, String telefono,
                                           String titulo, String tipoObra, String referencia,
                                           String fechaInicio, String fechaFinal);


    public void onObraActualizarClickListener(long id, String promotor, String direccion,
                                              String telefono, String titulo, String tipoObra,
                                              String referencia, String fechaInicio,
                                              String fechaFinal);

}
