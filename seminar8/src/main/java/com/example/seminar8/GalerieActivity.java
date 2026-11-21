package com.example.seminar8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class GalerieActivity extends AppCompatActivity {
    private ListView lvGalerie;
    private List<ElementGalerie> listaElemente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_galerie);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lvGalerie = findViewById(R.id.lvGalerie);
        listaElemente = new ArrayList<>();

        listaElemente.add(new ElementGalerie(
                "https://images.unsplash.com/photo-1555215695-3004980ad54e?w=600",
                "BMW M3 Competition - Sedan sport de performanta",
                "https://en.wikipedia.org/wiki/BMW_M3"
        ));

        listaElemente.add(new ElementGalerie(
                "https://images.unsplash.com/photo-1503376780353-7e6692767b70?w=600",
                "Porsche 911 GT3 - Motor aspirat natural de 4.0L",
                "https://en.wikipedia.org/wiki/Porsche_911_GT3"
        ));

        listaElemente.add(new ElementGalerie(
                "https://images.unsplash.com/photo-1606664515524-ed2f786a0bd6?w=600",
                "Audi RS6 Avant - Break de familie cu motor V8 biturbo",
                "https://en.wikipedia.org/wiki/Audi_RS_6"
        ));

        listaElemente.add(new ElementGalerie(
                "https://images.unsplash.com/photo-1618843479313-40f8afb4b4d8?w=600",
                "Mercedes-AMG GT R - Coupe conceput pentru circuit",
                "https://en.wikipedia.org/wiki/Mercedes-AMG_GT"
        ));

        listaElemente.add(new ElementGalerie(
                "https://images.unsplash.com/photo-1592198084033-aade902d1aae?w=600",
                "Ferrari F8 Tributo - Supercar italian cu motor central V8",
                "https://en.wikipedia.org/wiki/Ferrari_F8"
        ));

        GalerieAdapter adapter = new GalerieAdapter(this, listaElemente);
        lvGalerie.setAdapter(adapter);

        lvGalerie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ElementGalerie elementSelctat = listaElemente.get(position);

                Intent intent = new Intent(GalerieActivity.this, WebActivity.class);
                intent.putExtra("LINK_WEB", elementSelctat.getUrlPaginaWeb());
                startActivity(intent);
            }
        });

    }
}