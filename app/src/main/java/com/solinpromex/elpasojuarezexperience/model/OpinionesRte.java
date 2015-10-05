package com.solinpromex.elpasojuarezexperience.model;

public class OpinionesRte {
	private String user_name,opinion,nombre_rte,imagen_rte,fecha;
	private int id_opinion_rte;
	private double valoracion;



	public OpinionesRte() {
	}

	public OpinionesRte(String user_name, String opinion, String fecha, String nombre_rte, String imagen_rte, int id_opinion_rte,
                        double valoracion) {

        this.user_name = user_name;
		this.opinion = opinion;
        this.nombre_rte = nombre_rte;
        this.id_opinion_rte = id_opinion_rte;

        this.valoracion = valoracion;
        this.fecha = fecha;
        this.imagen_rte = imagen_rte;
	}

	//STRINGS
	//user_name
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
    //imagen_rte
    public String getimagen_rte() {
        return imagen_rte;
    }

    public void setimagen_rte_name(String imagen_rte) {
        this.imagen_rte = imagen_rte;
    }
    //opinion
    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    //nombre_rte
    public String getNombre_rte() {
        return nombre_rte;
    }

    public void setNombre_rte(String nombre_rte) {
        this.nombre_rte = nombre_rte;
    }
    //Fecha
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }




    //INT
    //id_opinion_rte
	public int getId_opinion_rte() {
		return id_opinion_rte;
	}

	public void setId_opinion_rte(int id_opinion_rte) {
		this.id_opinion_rte = id_opinion_rte;
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
