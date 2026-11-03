
package com.example.exercitiuseminar4;

import android.content.Intent;
import android.os.Bundle;
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
    private ApartamentAdapter adapter;

    private final int REQUEST_MODIFICARE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_view);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top,
                    systemBars.right, systemBars.bottom);
            return insets;
        });

        lvDatePrimite = findViewById(R.id.lvDatePrimite);

        Intent intentPrimit = getIntent();

        listaApartamente = intentPrimit.getParcelableArrayListExtra("CHEIE_Aprtamente");

        if (listaApartamente == null) {
            listaApartamente = new ArrayList<>();
        }

        adapter = new ApartamentAdapter(this, listaApartamente);
        lvDatePrimite.setAdapter(adapter);

        lvDatePrimite.setOnItemClickListener((parent, view, position, id) -> {

            Apartament apartament = listaApartamente.get(position);

            Intent intent = new Intent(ListViewActivity.this, AddApartamentActivity.class);

            intent.putExtra("APARTAMENT_SELECTAT", apartament);
            intent.putExtra("POZITIE", position);

            startActivityForResult(intent, REQUEST_MODIFICARE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_MODIFICARE && resultCode == RESULT_OK && data != null) {

            int pozitie = data.getIntExtra("POZITIE", -1);

            Apartament apartamentModificat =
                    data.getParcelableExtra("APARTAMENT_MODIFICAT");

            if (pozitie != -1 && apartamentModificat != null) {

                listaApartamente.set(pozitie, apartamentModificat);

                adapter.notifyDataSetChanged();

                Toast.makeText(this, "Apartament modificat!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
