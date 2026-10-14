package com.example.studiu_individual;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private TextView resultText;
    private Button addBtn, subBtn, mulBtn, divBtn, eqBtn, clearBtn;
    private Button num1Btn, num2Btn, num3Btn, num4Btn, num5Btn, num6Btn, num7Btn, num8Btn, num9Btn;
    private Button zeroBtn, dotBtn;

    private double num1, num2;
    private boolean isAdd, isSub, isMul, isDiv;

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

        editText = findViewById(R.id.editText2);
        resultText = findViewById(R.id.resultText);
        clearBtn = findViewById(R.id.clearText);

        addBtn = findViewById(R.id.add);
        subBtn = findViewById(R.id.sub);
        mulBtn = findViewById(R.id.mul);
        divBtn = findViewById(R.id.div);
        eqBtn = findViewById(R.id.submit);

        num1Btn = findViewById(R.id.num1);
        num2Btn = findViewById(R.id.num2);
        num3Btn = findViewById(R.id.num3);
        num4Btn = findViewById(R.id.num4);
        num5Btn = findViewById(R.id.num5);
        num6Btn = findViewById(R.id.num6);
        num7Btn = findViewById(R.id.num7);
        num8Btn = findViewById(R.id.num8);
        num9Btn = findViewById(R.id.num9);
        zeroBtn = findViewById(R.id.num0);
        dotBtn = findViewById(R.id.dot);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Validare pentru lungimea textului
                if(editText.getText().length() > 0)
                {
                    //Luam textul din acel edit text , care este ca string si ii facem conversia in double
                    num1 = Double.parseDouble(editText.getText().toString());
                    isAdd = true;

                    //Face ca casuta sa se stearga pentru a putea introduce urmatorul numar !
                    editText.setText("");
                }
            }
        });

        subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editText.getText().length() > 0)
                {

                    num1 = Double.parseDouble(editText.getText().toString());
                    isSub = true;


                    editText.setText("");
                }
            }
        });

        mulBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editText.getText().length() > 0)
                {

                    num1 = Double.parseDouble(editText.getText().toString());
                    isMul = true;


                    editText.setText("");
                }
            }
        });

        divBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editText.getText().length() > 0)
                {

                    num1 = Double.parseDouble(editText.getText().toString());
                    isDiv = true;


                    editText.setText("");
                }
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editText.setText("");
                resultText.setText("0");

                isAdd = false;
                isSub = false;
                isMul = false;
                isDiv = false;
            }
        });

        eqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editText.getText().length() > 0)
                {

                    num2 = Double.parseDouble(editText.getText().toString());

                    if(isAdd)
                    {
                        resultText.setText(String.valueOf(num1 + num2));
                    }

                    // !! De continuat si pentru celelalte operatii !!



                    editText.setText("");
                }
            }
        });

        // !! De facut lisener si pentru butoane pentru a intercepta inputul !!

    }
}

//Studiu de lucru Individual: Aplicatie-Calculator - Nefinalizat