package com.univer.store.model.response;

public class ProductoResponse {
    private String name;
    private Notificacion notificacion;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Notificacion getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(Notificacion notificacion) {
        this.notificacion = notificacion;
    }
}
