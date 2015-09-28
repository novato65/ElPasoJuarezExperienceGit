package com.solinpromex.elpasojuarezexperience.model;

public class TipoRestaurante {
	private String nombre_tipo, foto_tipo;
    private Integer id_tipo;

//SOLO PAR
	public TipoRestaurante() {
	}

	public TipoRestaurante(String nombre_tipo, String foto_tipo, Integer id_tipo) {

        this.nombre_tipo = nombre_tipo;
        this.foto_tipo = foto_tipo;
        this.id_tipo = id_tipo;

	}

	//STRINGS

	public String getNombre_tipo() {
        return nombre_tipo;
    }

    public void setNombre_tipo(String nombre_tipo) {
        this.nombre_tipo = nombre_tipo;
    }


    public String getFoto_tipo() {
        return foto_tipo;
    }

    public void setFoto_tipo(String foto_tipo) {
        this.foto_tipo = foto_tipo;
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id) {
        this.id_tipo = id_tipo;
    }









}
