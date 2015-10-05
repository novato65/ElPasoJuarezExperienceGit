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
import com.solinpromex.elpasojuarezexperience.model.OpinionesHotel;
import com.solinpromex.elpasojuarezexperience.model.OpinionesRte;

import java.util.List;

public class OpinionesRteListAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<OpinionesRte> opinionesrteItems;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public OpinionesRteListAdapter(Activity activity, List<OpinionesRte> opinionesrteItems) {
		this.activity = activity;
		this.opinionesrteItems = opinionesrteItems;
	}

	@Override
	public int getCount() {
		return opinionesrteItems.size();
	}

	@Override
	public Object getItem(int location) {
		return opinionesrteItems.get(location);
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
			convertView = inflater.inflate(R.layout.list_row_opiniones_rte, null);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();
		NetworkImageView thumbNail = (NetworkImageView) convertView
				.findViewById(R.id.thumbnail);
		TextView opiniones = (TextView) convertView.findViewById(R.id.title);
		TextView user_name = (TextView) convertView.findViewById(R.id.rating);
		TextView fecha = (TextView) convertView.findViewById(R.id.genre);
		TextView calificacion = (TextView) convertView.findViewById(R.id.releaseYear);

		// getting movie data for the row
		OpinionesRte m = opinionesrteItems.get(position);

		// thumbnail image
		thumbNail.setImageUrl(m.getimagen_rte(), imageLoader);
		
		// opinion
		opiniones.setText(m.getOpinion());
		
		// username
		user_name.setText("Usuario: " + m.getUser_name());
		
		// fecha

		fecha.setText(m.getFecha());

		//calificacion
		calificacion.setText( String.valueOf(m.getValoracion())+"/10 " );

		return convertView;
	}

}