package com.solinpromex.elpasojuarezexperience.model;

import java.util.ArrayList;

public class Hotel {
	private String title, thumbnailUrl, descripcion,direccion,web,tel_hotel,tel_reservas;
	private int year,num_estrellas;
	private double rating,latitud,longitud;
	private ArrayList<String> genre;

	public Hotel() {
	}

	public Hotel(String name, String thumbnailUrl, int year, double rating,
				 ArrayList<String> genre) {
		this.title = name;
		this.thumbnailUrl = thumbnailUrl;
		this.year = year;
		this.rating = rating;
		this.genre = genre;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String name) {
		this.title = name;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public ArrayList<String> getGenre() {
		return genre;
	}

	public void setGenre(ArrayList<String> genre) {
		this.genre = genre;
	}

}
