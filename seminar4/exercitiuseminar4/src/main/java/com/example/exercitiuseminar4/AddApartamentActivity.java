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

        Intent intent = getIntent();
        String mesaj = intent.getStringExtra("mesaj");
        Toast.makeText(this, mesaj, Toast.LENGTH_SHORT).show();

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
    }

    public void intoarceRezultat(View view)
    {
        String adresa = etAdresa.getText().toString().trim();
        String pretStr = etPret.getText().toString().trim();

        if(adresa.isEmpty() || pretStr.isEmpty())
        {
            Toast.makeText(this, "Completati adresa si pretul!", Toast.LENGTH_SHORT).show();
            return;
        }

        double pret = Double.parseDouble(pretStr);
        String cartier = spnCartier.getSelectedItem().toString();

        int selectedRadioId = rgCompartimentare.getCheckedRadioButtonId();
        RadioButton rb = findViewById(selectedRadioId);
        String tipCompartimentare = rb.getText().toString();

        boolean areParcare = cbParcare.isChecked();
        boolean esteMobilat = swMobilat.isChecked();
        float evaloareStare = rbStare.getRating();

        Intent resultIntent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("cheie_adresa", adresa);
        bundle.putDouble("cheie_pret", pret);
        bundle.putBoolean("cheie_parcare", areParcare);
        bundle.putString("cheie_compartimentare", tipCompartimentare);
        bundle.putString("cheie_cartier", cartier);
        bundle.putFloat("cheie_evaloare", evaloareStare);
        bundle.putBoolean("cheie_mobilat", esteMobilat);

        resultIntent.putExtras(bundle);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}