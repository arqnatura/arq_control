package com.arq_control.ui.visitas;

import com.arq_control.models.VisitaDB;

public interface OnVisitaInteractionListener {
    public void OnVisitaClick(VisitaDB visitaDB);
    public void OnVisitaEdit(VisitaDB mItem);
    public void OnVisitaEliminar(VisitaDB mItem);
}
