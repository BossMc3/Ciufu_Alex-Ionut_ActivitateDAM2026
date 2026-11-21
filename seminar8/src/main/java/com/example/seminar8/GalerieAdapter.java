package com.example.seminar8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class GalerieAdapter extends BaseAdapter {
    private Context context;
    private List<ElementGalerie> listaElemente;

    public GalerieAdapter(Context context, List<ElementGalerie> listaElemente) {
        this.context = context;
        this.listaElemente = listaElemente;
    }

    @Override
    public int getCount() {
        return listaElemente.size();
    }

    @Override
    public Object getItem(int position) {
        return listaElemente.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.item_galerie, parent, false);

        ElementGalerie elementGalerie = listaElemente.get(position);

        ImageView ivImagine = convertView.findViewById(R.id.ivImageItem);
        TextView tvDescriere = convertView.findViewById(R.id.tvDescriereItem);

        tvDescriere.setText(elementGalerie.getDescriere());

        Picasso.get()
                .load(elementGalerie.getUrlImagine())
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_delete)
                .into(ivImagine);

        return convertView;
    }
}
