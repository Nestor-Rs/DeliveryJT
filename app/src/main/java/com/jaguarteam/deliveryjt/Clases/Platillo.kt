package com.jaguarteam.deliveryjt.Clases

import java.io.Serializable

class Platillo(
    nombre:String,
    descripcion:String,
    imagen:String,
    precio:String
) : Serializable{
    var nombre:String;
    var descripcion:String;
    var imagen:String;
    var precio:String;

    init {
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.imagen=imagen;
        this.precio=precio;
    }
}