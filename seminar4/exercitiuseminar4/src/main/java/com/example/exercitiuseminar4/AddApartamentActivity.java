package com.example.exercitiuseminar4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddApartamentActivity extends AppCompatActivity {

    private EditText etAdresa, etPret;
    private Spinner spnCartier;
    private RadioGroup rgCompartimentare;
    private CheckBox cbParcare;
    private Switch swMobilat;
    private RatingBar rbStare;

    private int pozitie = -1;
    private Apartament apartamentDeModificat = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_apartament);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etAdresa = findViewById(R.id.etAdresa);
        etPret = findViewById(R.id.etPret);
        spnCartier = findViewById(R.id.spnCartier);
        rgCompartimentare = findViewById(R.id.rgCompartimentare);
        cbParcare = findViewById(R.id.cbParcare);
        swMobilat = findViewById(R.id.swMobilat);
        rbStare = findViewById(R.id.rbStare);

        String[] cartiere = {"Floreasca", "Titan", "Militari", "Centru", "Baneasa"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, cartiere);
        spnCartier.setAdapter(adapter);

        Intent intentPrimit = getIntent();
        if (intentPrimit != null) {
            String mesaj = intentPrimit.getStringExtra("mesaj");
            if (mesaj != null) {
                Toast.makeText(this, mesaj, Toast.LENGTH_SHORT).show();
            }


            if (intentPrimit.hasExtra("APARTAMENT_SELECTAT")) {
                apartamentDeModificat = intentPrimit.getParcelableExtra("APARTAMENT_SELECTAT");
                pozitie = intentPrimit.getIntExtra("POZITIE", -1);

                if (apartamentDeModificat != null) {
                    populeazaCampuri(apartamentDeModificat, adapter);
                }
            }
        }
    }

    private void populeazaCampuri(Apartament ap, ArrayAdapter<String> adapter) {
        etAdresa.setText(ap.getAdresa());
        etPret.setText(String.valueOf(ap.getPretEuro()));
        cbParcare.setChecked(ap.isAreParcare());
        swMobilat.setChecked(ap.isEsteMobilat());
        rbStare.setRating(ap.getEvaloareStare());


        int spinnerPos = adapter.getPosition(ap.getCartier());
        if (spinnerPos >= 0) {
            spnCartier.setSelection(spinnerPos);
        }


        for (int i = 0; i < rgCompartimentare.getChildCount(); i++) {
            View child = rgCompartimentare.getChildAt(i);
            if (child instanceof RadioButton) {
                RadioButton rb = (RadioButton) child;
                if (rb.getText().toString().equalsIgnoreCase(ap.getTipCompartimentare())) {
                    rb.setChecked(true);
                    break;
                }
            }
        }
    }

    public void intoarceRezultat(View view) {
        String adresa = etAdresa.getText().toString().trim();
        String pretStr = etPret.getText().toString().trim();

        if (adresa.isEmpty() || pretStr.isEmpty()) {
            Toast.makeText(this, "Completati adresa si pretul!", Toast.LENGTH_SHORT).show();
            return;
        }

        double pret = Double.parseDouble(pretStr);
        String cartier = spnCartier.getSelectedItem().toString();

        int selectedRadioId = rgCompartimentare.getCheckedRadioButtonId();
        if (selectedRadioId == -1) {
            Toast.makeText(this, "Selectati compartimentarea!", Toast.LENGTH_SHORT).show();
            return;
        }
        RadioButton rb = findViewById(selectedRadioId);
        String tipCompartimentare = rb.getText().toString();

        boolean areParcare = cbParcare.isChecked();
        boolean esteMobilat = swMobilat.isChecked();
        float evaloareStare = rbStare.getRating();

        Intent resultIntent = new Intent();

        if (pozitie != -1 && apartamentDeModificat != null) {

            apartamentDeModificat.setDate(adresa, pret, areParcare, tipCompartimentare, cartier, evaloareStare, esteMobilat);
            resultIntent.putExtra("APARTAMENT_MODIFICAT", apartamentDeModificat);
            resultIntent.putExtra("POZITIE", pozitie);
        } else {

            Bundle bundle = new Bundle();
            bundle.putString("cheie_adresa", adresa);
            bundle.putDouble("cheie_pret", pret);
            bundle.putBoolean("cheie_parcare", areParcare);
            bundle.putString("cheie_compartimentare", tipCompartimentare);
            bundle.putString("cheie_cartier", cartier);
            bundle.putFloat("cheie_evaloare", evaloareStare);
            bundle.putBoolean("cheie_mobilat", esteMobilat);
            resultIntent.putExtras(bundle);
        }

        setResult(RESULT_OK, resultIntent);
        finish();
    }
}