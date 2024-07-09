package com.univer.store.util;

import com.univer.store.model.response.Notificacion;

public class StoreUtil {
    private StoreUtil(){}

    public static Notificacion retrieveOK(String message){
        Notificacion notificacion = new Notificacion();
        notificacion.setReason("Exitoso");
        notificacion.setLevel("success");
        notificacion.setMessage(message);
        return notificacion;
    }

}
