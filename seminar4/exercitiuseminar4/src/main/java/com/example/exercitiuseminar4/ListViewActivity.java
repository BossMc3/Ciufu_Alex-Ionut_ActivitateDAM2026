package com.example.exercitiuseminar4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {

    private ListView lvDatePrimite;
    private ArrayList<Apartament> listaApartamente;
    private ArrayAdapter<Apartament> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lvDatePrimite = findViewById(R.id.lvDatePrimite);

        Intent intentPrimit = getIntent();

        listaApartamente =  (ArrayList<Apartament>) intentPrimit.getSerializableExtra("CHEIE_Aprtamente");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaApartamente);
        lvDatePrimite.setAdapter(adapter);

        lvDatePrimite.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Apartament apartamentSelectat = listaApartamente.get(position);
                Toast.makeText(ListViewActivity.this, apartamentSelectat.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        lvDatePrimite.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Apartament apartamentSters = listaApartamente.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(ListViewActivity.this, "A fost sters: " + apartamentSters.getAdresa(), Toast.LENGTH_SHORT).show();
                return;
            }
        });



    }
}