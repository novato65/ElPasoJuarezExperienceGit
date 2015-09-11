package com.solinpromex.elpasojuarezexperience;

import com.solinpromex.elpasojuarezexperience.R;
import com.solinpromex.elpasojuarezexperience.app.AppController;
import com.solinpromex.elpasojuarezexperience.model.Hotel;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class CustomListAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<Hotel> hotelItems;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public CustomListAdapter(Activity activity, List<Hotel> hotelItems) {
		this.activity = activity;
		this.hotelItems = hotelItems;
	}

	@Override
	public int getCount() {
		return hotelItems.size();
	}

	@Override
	public Object getItem(int location) {
		return hotelItems.get(location);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.list_row, null);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();
		NetworkImageView thumbNail = (NetworkImageView) convertView
				.findViewById(R.id.thumbnail);
		TextView nombre = (TextView) convertView.findViewById(R.id.title);
		TextView num_estrellas = (TextView) convertView.findViewById(R.id.rating);
		TextView zona_hotel = (TextView) convertView.findViewById(R.id.genre);
		TextView calificacion = (TextView) convertView.findViewById(R.id.releaseYear);

		// getting movie data for the row
		Hotel m = hotelItems.get(position);

		// thumbnail image
		thumbNail.setImageUrl(m.getFoto(), imageLoader);
		
		// nombre
		nombre.setText(m.getNombre());
		
		// num-estrellas
		num_estrellas.setText("Categoría: " + String.valueOf(m.getNum_estrellas())+ " estrellas");
		
		// direccion

		zona_hotel.setText(m.getZona_hotel());

		//calificacion
		calificacion.setText("Valoración: " + String.valueOf(m.getCalificacion()));

		return convertView;
	}

}