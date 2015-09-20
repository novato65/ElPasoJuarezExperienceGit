package com.solinpromex.elpasojuarezexperience.model;

import java.util.Date;

public class OpinionesHotel {
	private String user_name,opinion,nombre_hotel,imagen_hotel,fecha;
	private int id_opinion_hotel;
	private double valoracion;



	public OpinionesHotel() {
	}

	public OpinionesHotel(String user_name,String opinion, String fecha, String nombre_hotel,String imagen_hotel, int id_opinion_hotel,
                          double valoracion) {

        this.user_name = user_name;
		this.opinion = opinion;
        this.nombre_hotel = nombre_hotel;
        this.id_opinion_hotel = id_opinion_hotel;

        this.valoracion = valoracion;
        this.fecha = fecha;
        this.imagen_hotel = imagen_hotel;
	}

	//STRINGS
	//user_name
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
    //imagen_hotel
    public String getimagen_hotel() {
        return imagen_hotel;
    }

    public void setimagen_hotel_name(String imagen_hotel) {
        this.imagen_hotel = imagen_hotel;
    }
    //opinion
    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    //nombre_hotel
    public String getNombre_hotel() {
        return nombre_hotel;
    }

    public void setNombre_hotel(String nombre_hotel) {
        this.nombre_hotel = nombre_hotel;
    }
    //Fecha
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }




    //INT
    //id_opinion_hotel
	public int getId_opinion_hotel() {
		return id_opinion_hotel;
	}

	public void setId_opinion_hotel(int id_opinion_hotel) {
		this.id_opinion_hotel = id_opinion_hotel;
	}





    //DOUBLE
//cvaloracion
	public double getValoracion() {
		return valoracion;
	}

	public void setValoracion(double valoracion) {
		this.valoracion = valoracion;
	}




}
