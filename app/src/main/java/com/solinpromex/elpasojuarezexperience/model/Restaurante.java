package com.solinpromex.elpasojuarezexperience.model;

public class Restaurante {
	private String nombre, foto, descripcion,direccion,web,tel_rte,tel_reservas,tipo_rte,facebook,twitter;
	private int  id;
	private double calificacion,latitud,longitud;

//SOLO PAR
	public Restaurante() {
	}

	public Restaurante(String nombre, String facebook, String twitter, String foto, String descripcion, String direccion, String web, String tel_hotel, String tel_reservas, String tipo_rte,
                       double calificacion, double latitud, double longitud,  int id) {
        this.id = id;
        this.nombre = nombre;
		this.foto = foto;
		this.descripcion = descripcion;
        this.direccion = direccion;
		this.calificacion = calificacion;
		this.web = web;
		this.tel_rte = tel_hotel;
		this.tel_reservas = tel_reservas;
		this.latitud = latitud;
		this.longitud = longitud;
        this.tipo_rte = tipo_rte;
        this.facebook = facebook;
        this.twitter =  twitter;
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
    public String getTel_rte() {
        return tel_rte;
    }

    public void setTel_rte(String tel_rte) {
        this.tel_rte = tel_rte;
    }
	//tel_reservas
    public String getTel_reservas() {
        return tel_reservas;
    }

    public void setTel_reservas(String tel_reservas) {
        this.tel_reservas = tel_reservas;
    }
    //nombre
    public String getTipo_rte() {
        return tipo_rte;
    }

    public void setTipo_rte(String tipo_rte) {
        this.tipo_rte = tipo_rte;
    }
    //facebook
    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }
    //facebook
    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }


	//INT


    //id_hotel
    public int getId_rte() {
        return id;
    }

    public void setId_rte(int id) {
        this.id = id;
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
    public double getLatitud() {
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
