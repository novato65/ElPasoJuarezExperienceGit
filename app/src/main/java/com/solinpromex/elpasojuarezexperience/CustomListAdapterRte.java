package com.solinpromex.elpasojuarezexperience;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.solinpromex.elpasojuarezexperience.app.AppController;

import com.solinpromex.elpasojuarezexperience.model.Restaurante;
import com.solinpromex.elpasojuarezexperience.model.TipoRestaurante;

import java.util.List;

public class CustomListAdapterRte extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<Restaurante> rteItems;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public CustomListAdapterRte(Activity activity, List<Restaurante> rteItems) {
		this.activity = activity;
		this.rteItems = rteItems;
	}

	@Override
	public int getCount() {
		return rteItems.size();
	}

	@Override
	public Object getItem(int location) {
		return rteItems.get(location);
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
		TextView tipo_rte = (TextView) convertView.findViewById(R.id.rating);
		TextView zona_rte = (TextView) convertView.findViewById(R.id.genre);
		TextView calificacion = (TextView) convertView.findViewById(R.id.releaseYear);

		// getting movie data for the row
		Restaurante m = rteItems.get(position);

		// thumbnail image
		thumbNail.setImageUrl(m.getFoto(), imageLoader);
		
		// nombre
		nombre.setText(m.getNombre());
		
		// num-estrellas
		tipo_rte.setText("Tipo: " + (m.getTipo_rte()) );


		//calificacion
		calificacion.setText("Valoración: " + String.valueOf(m.getCalificacion()));


		return convertView;
	}

}