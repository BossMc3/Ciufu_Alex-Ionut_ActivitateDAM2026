package com.example.exercitiuseminar4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public final int requestCodeAdd = 101;
    public final int requestCodeAddAi = 102;

    private Button btnToLv;

    private TextView tvRezultatObiect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvRezultatObiect = findViewById(R.id.tvRezultatObiect);
        btnToLv = findViewById(R.id.btnToLv);
    }

    public void deschideAdaugareClasic(View view)
    {
        Intent intent = new Intent(getApplicationContext(), AddApartamentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("mesaj", "Deschidere formular clasic");
        intent.putExtras(bundle);

        startActivityForResult(intent, requestCodeAdd);
    }

    public void deschidereAdaugareAi(View view)
    {
        Intent intent = new Intent(getApplicationContext(), AddApartamentAiActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("mesaj", "Deschidere formular modernizat (AI)");
        intent.putExtras(bundle);

        startActivityForResult(intent, requestCodeAdd);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == requestCodeAdd || requestCode == requestCodeAddAi)
        {
            Bundle bundle = data.getExtras();

            String adresa = bundle.getString("cheie_adresa", "");
            double pret = bundle.getDouble("cheie_pret", 0.0);
            boolean areParcare = bundle.getBoolean("cheie_parcare", false);
            String tipCompartimentare = bundle.getString("cheie_compartimentare", "");
            String cartier = bundle.getString("cheie_cartier", "");
            float evaloareStare = bundle.getFloat("cheie_evaloare", 0.0f);
            boolean esteMobilat = bundle.getBoolean("cheie_mobilat", false);

            Apartament apartament = new Apartament(adresa, pret, areParcare, tipCompartimentare, cartier, evaloareStare, esteMobilat);

             tvRezultatObiect.setText(apartament.toString());
             Toast.makeText(this, "Adaugat cu succes: " + apartament.getAdresa(), Toast.LENGTH_SHORT).show();

            ArrayList<String> listaApartamente = new ArrayList<>();
            listaApartamente.add(apartament.toString());


            btnToLv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intet = new Intent(MainActivity.this, ListViewActivity.class);
                    intet.putStringArrayListExtra("CHEIE_Apartamente", listaApartamente);

                    if(Objects.requireNonNull(intet.getExtras()).isEmpty())
                    {

                    }


                    startActivity(intet);
                }
            });


        }
    }
}

