package com.jaguarteam.deliveryjt.Clases

import java.io.Serializable

class Restaurantes (
    idRestaurante:String,
    Nombre:String,
    ubicacion:String,
    logo:String
):Serializable {

    var idRestaurante:String;
    var Nombre:String;
    var ubicacion:String;
    var logo:String;

    init {
        this.idRestaurante=idRestaurante;
        this.Nombre=Nombre;
        this.ubicacion=ubicacion;
        this.logo=logo;
    }
}