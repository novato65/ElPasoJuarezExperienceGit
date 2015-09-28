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

public class CustomListAdapterTipoRte extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<TipoRestaurante> tiporteItems;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public CustomListAdapterTipoRte(Activity activity, List<TipoRestaurante> tiporteItems) {
		this.activity = activity;
		this.tiporteItems = tiporteItems;
	}

	@Override
	public int getCount() {
		return tiporteItems.size();
	}

	@Override
	public Object getItem(int location) {
		return tiporteItems.get(location);
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
			convertView = inflater.inflate(R.layout.list_row_tipo, null);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();
		NetworkImageView thumbNail = (NetworkImageView) convertView
				.findViewById(R.id.thumbnail);
		TextView nombre = (TextView) convertView.findViewById(R.id.title);


		// getting movie data for the row
		TipoRestaurante m = tiporteItems.get(position);

		// thumbnail image
		thumbNail.setImageUrl(m.getFoto_tipo(), imageLoader);
		
		// nombre
		nombre.setText(m.getNombre_tipo());
		






		return convertView;
	}

}