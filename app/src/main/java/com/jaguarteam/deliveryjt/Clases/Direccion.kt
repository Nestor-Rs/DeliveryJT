package com.jaguarteam.deliveryjt.Clases

import java.io.Serializable

class Direccion constructor(
    nombre:String,
    latitud:String,
    logitud:String
) : Serializable {

    var nombre:String
    var latitud:String
    var logitud:String

    init {
        this.nombre=nombre;
        this.latitud=latitud;
        this.logitud=logitud;
    }
}