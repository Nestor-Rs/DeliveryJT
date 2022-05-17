package com.jaguarteam.deliveryjt.Clases

import java.io.Serializable

class Usuario (
    idUser:String,
    nombre:String,
    apellido:String,
    correo:String,
    telefono:String
):Serializable {
    var idUser:String;
    var nombre:String;
    var apellido:String;
    var correo:String;
    var telefono:String;

    init {
        this.idUser=idUser;
        this.nombre=nombre;
        this.apellido=apellido;
        this.correo=correo;
        this.telefono=telefono;
    }
}