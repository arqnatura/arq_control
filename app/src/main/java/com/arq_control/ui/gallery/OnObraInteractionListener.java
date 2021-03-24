package com.arq_control.ui.gallery;

import com.arq_control.models.ObraDB;

public interface OnObraInteractionListener {
    public void OnObraClick(ObraDB obraDB);

    public void OnObraEdit(ObraDB mItem);

    public void onObraGuardarClickListener(String promotor, String direccion, String telefono,
                                           String titulo, String tipoObra, String referencia,
                                           long visitasPrevistas, String fechaInicio,
                                           String fechaFinal);


    public void onObraActualizarClickListener(long id, String promotor, String direccion,
                                              String telefono, String titulo, String tipoObra,
                                              String referencia, long visitasPrevistas,
                                              String fechaInicio, String fechaFinal);
}
