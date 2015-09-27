package com.solinpromex.elpasojuarezexperience.model;

public class POI {
	private String nombre_POI, direccion_POI, latitud_POI,longitud_POI,tipo_POI;

//SOLO PAR
	public POI() {
	}

	public POI(String nombre_POI, String direccion_POI, String latitud_POI, String longitud_POI, String tipo_POI ) {

        this.nombre_POI = nombre_POI;
        this.direccion_POI = direccion_POI;
        this.latitud_POI = latitud_POI;
        this.longitud_POI = longitud_POI;
        this.tipo_POI = tipo_POI;

	}

	//STRINGS

	public String getNombre_POI() {
        return nombre_POI;
    }

    public void setNombre_POI(String nombre_POI) {
        this.nombre_POI = nombre_POI;
    }


    public String getDireccion_POI() {
        return direccion_POI;
    }

    public void setDireccion_POI(String direccion_POI) {
        this.direccion_POI = direccion_POI;
    }

    public String getLatitud_POI() {
        return latitud_POI;
    }

    public void setLatitud_POI(String latitud_POI) {
        this.latitud_POI = latitud_POI;
    }


    public String getLongitud_POI() {
        return longitud_POI;
    }

    public void setLongitud_POI(String longitud_POI) {
        this.longitud_POI = longitud_POI;
    }

    public String getTipo_POI() {
        return tipo_POI;
    }

    public void setTipo_POI(String tipo_POI) {
        this.tipo_POI = tipo_POI;
    }








}
