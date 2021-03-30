package com.arq_control.ui.gallery;

import com.arq_control.models.ObraDB;

public interface OnObraInteractionListener {
    public void OnObraClick(ObraDB obraDB);
    public void OnObraEdit(ObraDB mItem);
    public void OnObraEliminar(ObraDB mItem);
}
