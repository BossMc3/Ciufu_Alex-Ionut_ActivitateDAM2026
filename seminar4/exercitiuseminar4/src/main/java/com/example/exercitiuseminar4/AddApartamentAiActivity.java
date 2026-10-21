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

public class AddApartamentAiActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_add_apartament_ai);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent it = getIntent();
        if (it != null && it.getExtras() != null) {
            String titlu = it.getExtras().getString("mesaj");
            if (titlu != null) {
                Toast.makeText(this, titlu, Toast.LENGTH_SHORT).show();
            }
        }

        etAdresa = findViewById(R.id.etAiAdresa);
        etPret = findViewById(R.id.etAiPret);
        spnCartier = findViewById(R.id.spnAiCartier);
        rgCompartimentare = findViewById(R.id.rgAiCompartimentare);
        cbParcare = findViewById(R.id.cbAiParcare);
        swMobilat = findViewById(R.id.swAiMobilat);
        rbStare = findViewById(R.id.rbAiStare);

        String[] cartiere = {"Dorobanți", "Floreasca", "Băneasa", "Titan", "Militari"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cartiere);
        spnCartier.setAdapter(adapter);
    }

    public void intoarceRezultatAi(View view) {
        String adresa = etAdresa.getText().toString().trim();
        String pretStr = etPret.getText().toString().trim();

        if (adresa.isEmpty() || pretStr.isEmpty()) {
            Toast.makeText(this, "Completați adresa și prețul!", Toast.LENGTH_SHORT).show();
            return;
        }

        double pret = Double.parseDouble(pretStr);
        String cartier = spnCartier.getSelectedItem().toString();

        int selectedId = rgCompartimentare.getCheckedRadioButtonId();
        RadioButton rb = findViewById(selectedId);
        String tipCompartimentare = (rb != null) ? rb.getText().toString() : "Decomandat";

        boolean areParcare = cbParcare.isChecked();
        boolean esteMobilat = swMobilat.isChecked();
        float evaluareStare = rbStare.getRating();

        Intent resultIntent = new Intent();
        Bundle bundle = new Bundle();

        bundle.putString("cheie_adresa", adresa);
        bundle.putDouble("cheie_pret", pret);
        bundle.putBoolean("cheie_parcare", areParcare);
        bundle.putString("cheie_compartimentare", tipCompartimentare);
        bundle.putString("cheie_cartier", cartier);
        bundle.putFloat("cheie_evaluare", evaluareStare);
        bundle.putBoolean("cheie_mobilat", esteMobilat);

        resultIntent.putExtras(bundle);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}