package com.solinpromex.elpasojuarezexperience.model;

import java.util.ArrayList;

public class Hotel {
	private String nombre, foto, descripcion,direccion,web,tel_hotel,tel_reservas,zona_hotel;
	private int num_estrellas;
	private double calificacion,latitud,longitud;


	public Hotel() {
	}

	public Hotel(String nombre, String foto, String descripcion, String direccion, String web, String tel_hotel, String tel_reservas, String zona_hotel,
				 double calificacion, double latitud, double longitud, int num_estrellas) {
		this.nombre = nombre;
		this.foto = foto;
		this.descripcion = descripcion;
        this.direccion = direccion;
		this.calificacion = calificacion;
		this.web = web;
		this.tel_hotel = tel_hotel;
		this.tel_reservas = tel_reservas;
		this.latitud = latitud;
		this.longitud = longitud;
		this.num_estrellas = num_estrellas;
        this.zona_hotel = zona_hotel;
	}

	//STRINGS
	//nombre
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	// foto
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	//descripcion
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
	// direccion

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
	//web
    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }
	//tel_hotel
    public String getTel_hotel() {
        return tel_hotel;
    }

    public void setTel_hotel(String tel_hotel) {
        this.tel_hotel = tel_hotel;
    }
	//tel_reservas
    public String getTel_reservas() {
        return tel_reservas;
    }

    public void setTel_reservas(String tel_reservas) {
        this.tel_reservas = tel_reservas;
    }
    //nombre
    public String getZona_hotel() {
        return zona_hotel;
    }

    public void setZona_hotel(String zona_hotel) {
        this.zona_hotel = zona_hotel;
    }


	//INT

	public int getNum_estrellas() {
		return num_estrellas;
	}

	public void setNum_estrellas(int num_estrellas) {
		this.num_estrellas = num_estrellas;
	}

	//DOUBLE
//calificacion
	public double getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(double calificacion) {
		this.calificacion = calificacion;
	}
    //latitud
    public double getLlatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
    //longitud
    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }



}
