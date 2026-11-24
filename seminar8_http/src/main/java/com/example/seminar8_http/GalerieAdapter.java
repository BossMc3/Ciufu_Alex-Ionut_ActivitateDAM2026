package com.example.seminar8_http;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GalerieAdapter extends BaseAdapter {
    private Context ctx;
    private int resource_file;
    private List<ElementGalerie> lista;

    public GalerieAdapter(Context ctx, int resource_file, List<ElementGalerie> lista) {
        this.ctx = ctx;
        this.resource_file = resource_file;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return this.lista.size();
    }

    @Override
    public Object getItem(int position) {
        return this.lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(ctx);
            v = inflater.inflate(resource_file, parent, false);
        }

        ElementGalerie element = (ElementGalerie) getItem(position);

        ImageView iv = v.findViewById(R.id.ivImagineItem);
        TextView tv = v.findViewById(R.id.tvDescriereItem);

        if (element.getImagine() != null) {
            iv.setImageBitmap(element.getImagine());
        } else {
            iv.setImageResource(android.R.drawable.ic_delete);
        }
        tv.setText(element.getTextAfisat());

        return v;
    }
}
