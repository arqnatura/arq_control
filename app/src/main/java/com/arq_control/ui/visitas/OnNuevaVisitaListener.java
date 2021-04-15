package com.arq_control.ui.visitas;

public interface OnNuevaVisitaListener {

   public void onVisitaGuardarClickListener(String tituloVisita,
                                            String fecha,
                                            long numeroVisita,
                                            String descripcion);

   public void onVisitaActualizarClickListener(long id,
                                                String tituloVisita,
                                                String fecha,
                                                long numeroVisita,
                                                String descripcion,
                                                String almacenFoto);

}
