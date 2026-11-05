
package com.example.exercitiuseminar4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ApartamentAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Apartament> listaApartamente;

    public ApartamentAdapter(Context context, ArrayList<Apartament> listaApartamente) {
        this.context = context;
        this.listaApartamente = listaApartamente;
    }

    @Override
    public int getCount() {
        return listaApartamente.size();
    }

    @Override
    public Object getItem(int position) {
        return listaApartamente.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_aprtament, parent, false);
        }

        Apartament apartament = listaApartamente.get(position);

        TextView tvAdresa = convertView.findViewById(R.id.tvAdresa);
        TextView tvPret = convertView.findViewById(R.id.tvPret);
        TextView tvCartier = convertView.findViewById(R.id.tvCartier);
        TextView tvDetalii = convertView.findViewById(R.id.tvDetalii);

        tvAdresa.setText(apartament.getAdresa());
        tvPret.setText("Pret: " + apartament.getPretEuro() + " euro");
        tvCartier.setText("Cartier: " + apartament.getCartier());
        tvDetalii.setText(
                "Parcare: " + (apartament.isAreParcare() ? "Da" : "Nu") +
                        "\nCompartimentare: " + apartament.getTipCompartimentare() +
                        "\nEvaluare stare: " + apartament.getEvaloareStare() +
                        "\nMobilat: " + (apartament.isEsteMobilat() ? "Da" : "Nu")
        );

        return convertView;
    }
}
