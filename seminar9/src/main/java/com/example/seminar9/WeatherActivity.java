package com.example.seminar9;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WeatherActivity extends AppCompatActivity {

    private static final String API_KEY = "";

    private EditText etCity;
    private Spinner spinnerDays;
    private Button btnGetWeather;
    private TextView tvResult;

    private ExecutorService executor;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        etCity = findViewById(R.id.etCity);
        spinnerDays = findViewById(R.id.spinnerDays);
        btnGetWeather = findViewById(R.id.btnGetWeather);
        tvResult = findViewById(R.id.tvResult);


        executor = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());


        String[] optiuniZile = {"1 zi", "5 zile"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                optiuniZile
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDays.setAdapter(adapter);

        btnGetWeather.setOnClickListener(v -> {
            String oras = etCity.getText().toString().trim();
            if (oras.isEmpty()) {
                Toast.makeText(this, "Introdu numele unui oras!", Toast.LENGTH_SHORT).show();
                return;
            }


            String selectie = spinnerDays.getSelectedItem().toString();
            String endpointZile = selectie.equals("1 zi") ? "1day" : "5day";

            tvResult.setText("Se incarca datele...");
            obtineCodSiVreme(oras, endpointZile);
        });
    }

    private void obtineCodSiVreme(String oras, String endpointZile) {
        executor.execute(() -> {
            try {

                String orasCodificat = URLEncoder.encode(oras, "UTF-8");
                String linkCitySearch = "https://dataservice.accuweather.com/locations/v1/cities/search?apikey="
                        + API_KEY + "&q=" + orasCodificat;

                String raspunsCityJson = efectueazaCerereHttp(linkCitySearch);

                JSONArray vectorOrase = new JSONArray(raspunsCityJson);
                if (vectorOrase.length() == 0) {
                    handler.post(() -> tvResult.setText("Orasul nu a fost gasit."));
                    return;
                }

                JSONObject primulOras = vectorOrase.getJSONObject(0);
                String cheieOras = primulOras.getString("Key");
                String numeOrasGasit = primulOras.getString("LocalizedName");


                handler.post(() -> Toast.makeText(
                        WeatherActivity.this,
                        "Cod oras (" + numeOrasGasit + "): " + cheieOras,
                        Toast.LENGTH_SHORT
                ).show());

                String linkForecast = "https://dataservice.accuweather.com/forecasts/v1/daily/"
                        + endpointZile + "/" + cheieOras + "?apikey=" + API_KEY + "&metric=true";

                String raspunsForecastJson = efectueazaCerereHttp(linkForecast);

                JSONObject obiectForecast = new JSONObject(raspunsForecastJson);
                JSONArray dailyForecasts = obiectForecast.getJSONArray("DailyForecasts");

                StringBuilder rezultatFinal = new StringBuilder();
                rezultatFinal.append("Oras: ").append(numeOrasGasit)
                        .append(" (Cod: ").append(cheieOras).append(")\n\n");

                for (int i = 0; i < dailyForecasts.length(); i++) {
                    JSONObject zi = dailyForecasts.getJSONObject(i);

                    String data = zi.getString("Date").substring(0, 10);

                    JSONObject temperatura = zi.getJSONObject("Temperature");
                    JSONObject minObj = temperatura.getJSONObject("Minimum");
                    JSONObject maxObj = temperatura.getJSONObject("Maximum");

                    double tempMin = minObj.getDouble("Value");
                    String unitMin = minObj.getString("Unit");

                    double tempMax = maxObj.getDouble("Value");
                    String unitMax = maxObj.getString("Unit");

                    rezultatFinal.append("Data: ").append(data).append("\n")
                            .append("  • Minima: ").append(tempMin).append(" °").append(unitMin).append("\n")
                            .append("  • Maxima: ").append(tempMax).append(" °").append(unitMax).append("\n\n");
                }

                handler.post(() -> tvResult.setText(rezultatFinal.toString()));

            } catch (Exception e) {
                e.printStackTrace();
                handler.post(() -> tvResult.setText("Eroare: " + e.getMessage()));
            }
        });
    }

    private String efectueazaCerereHttp(String linkString) throws Exception {
        URL url = new URL(linkString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String linie;
        while ((linie = reader.readLine()) != null) {
            builder.append(linie);
        }
        reader.close();
        conn.disconnect();

        return builder.toString();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (executor != null) {
            executor.shutdown();
        }
    }
}