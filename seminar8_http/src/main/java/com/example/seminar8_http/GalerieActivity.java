package com.example.seminar8_http;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GalerieActivity extends AppCompatActivity {
    private List<ElementGalerie> lista;

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

        List<String> linkuriImagini = new ArrayList<>();
        linkuriImagini.add("https://images.unsplash.com/photo-1555215695-3004980ad54e?w=600");
        linkuriImagini.add("https://images.unsplash.com/photo-1503376780353-7e6692767b70?w=600");
        linkuriImagini.add("https://images.unsplash.com/photo-1606664515524-ed2f786a0bd6?w=600");
        linkuriImagini.add("https://images.unsplash.com/photo-1618843479313-40f8afb4b4d8?w=600");
        linkuriImagini.add("https://images.unsplash.com/photo-1592198084033-aade902d1aae?w=600");

        List<Bitmap> imagini = new ArrayList<>();

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                for (String link : linkuriImagini) {
                    HttpURLConnection con = null;
                    try {
                        URL url = new URL(link);
                        con = (HttpURLConnection) url.openConnection();
                        InputStream is = con.getInputStream();
                        imagini.add(BitmapFactory.decodeStream(is));
                    } catch (Exception e) {
                        e.printStackTrace();
                        imagini.add(null);
                    } finally {
                        if (con != null) {
                            con.disconnect();
                        }
                    }
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        lista = new ArrayList<>();

                        lista.add(new ElementGalerie("BMW M3 Competition", imagini.get(0),
                                "https://en.wikipedia.org/wiki/BMW_M3"));
                        lista.add(new ElementGalerie("Porsche 911 GT3", imagini.get(1),
                                "https://en.wikipedia.org/wiki/Porsche_911_GT3"));
                        lista.add(new ElementGalerie("Audi RS6 Avant", imagini.get(2),
                                "https://en.wikipedia.org/wiki/Audi_RS_6"));
                        lista.add(new ElementGalerie("Mercedes-AMG GT R", imagini.get(3),
                                "https://en.wikipedia.org/wiki/Mercedes-AMG_GT"));
                        lista.add(new ElementGalerie("Ferrari F8 Tributo", imagini.get(4),
                                "https://en.wikipedia.org/wiki/Ferrari_F8"));

                        ListView lv = findViewById(R.id.lvGalerie);
                        GalerieAdapter adapter = new GalerieAdapter(getApplicationContext(), R.layout.item_galerie, lista);
                        lv.setAdapter(adapter);

                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(getApplicationContext(), WebActivity.class);
                                intent.putExtra("link", lista.get(position).getLink());
                                startActivity(intent);
                            }
                        });
                    }
                });
            }
        });
    }
}

