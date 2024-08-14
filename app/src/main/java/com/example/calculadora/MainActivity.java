package com.example.calculadora;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //variables a usar
    private EditText txtDisplay;
    private Button[] numberButtons = new Button[10];
    private Button btnAdd, btnSubtract, btnMultiply, btnDivide, btnEquals, btnDel;
    private double val1 = Double.NaN, val2;
    private char ACTION;
    private static final char SUMAR = '+';
    private static final char RESTAR = '-';
    private static final char MULTIPLICAR = '*';
    private static final char DIVIDIR = '/';
    private static final char EQU = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //llamar a la funcion de los IDs
        setupUIViews();

        //darle en onclick a los 9 botones de numeros
        for (int i = 0; i < numberButtons.length; i++) {
            int finalI = i;
            numberButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    txtDisplay.setText(txtDisplay.getText().toString() + finalI);
                }
            });
        }

        //darle en onclick a el boton de sumar
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Operaciones();
                ACTION = SUMAR;
                txtDisplay.setText(txtDisplay.getText().toString() + "+");
            }
        });

        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Operaciones();
                ACTION = RESTAR;
                txtDisplay.setText(txtDisplay.getText().toString() + "-");
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Operaciones();
                ACTION = MULTIPLICAR;
                txtDisplay.setText(txtDisplay.getText().toString() + "*");
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Operaciones();
                ACTION = DIVIDIR;
                txtDisplay.setText(txtDisplay.getText().toString() + "/");
            }
        });

        //darle en onclick a el boton de de igual
        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Operaciones();
                ACTION = EQU;
                txtDisplay.setText(String.valueOf(val1));
                val1 = Double.NaN;
                ACTION = '0';
            }
        });
        //darle en onclick a el boton de borrar
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = txtDisplay.getText().toString();
                if (!text.isEmpty()) {
                    txtDisplay.setText(text.substring(0, text.length() - 1));
                }
            }
        });
        //boton para borrar todo de golpe
        btnDel.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                txtDisplay.setText("");
                return true;
            }
        });

    }
    //asignar los ids a los botones
    private void setupUIViews() {
        txtDisplay = findViewById(R.id.txtDisplay);

        numberButtons[0] = findViewById(R.id.btn0);
        numberButtons[1] = findViewById(R.id.btn1);
        numberButtons[2] = findViewById(R.id.btn2);
        numberButtons[3] = findViewById(R.id.btn3);
        numberButtons[4] = findViewById(R.id.btn4);
        numberButtons[5] = findViewById(R.id.btn5);
        numberButtons[6] = findViewById(R.id.btn6);
        numberButtons[7] = findViewById(R.id.btn7);
        numberButtons[8] = findViewById(R.id.btn8);
        numberButtons[9] = findViewById(R.id.btn9);

        btnAdd = findViewById(R.id.btnAdd);
        btnSubtract = findViewById(R.id.btnSubtract);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivide = findViewById(R.id.btnDivide);
        btnEquals = findViewById(R.id.btnEquals);
        btnDel = findViewById(R.id.btnDel);
    }
    //realizar las operaciones
    private void Operaciones() {
        //que no venga null el val 1
        if (!Double.isNaN(val1)) {
            //partimos el texto
            String[] parts = txtDisplay.getText().toString().split("[+\\-*/]");
            //si tiene mas de 1 parte
            if (parts.length > 1) {
                try {
                    val2 = Double.parseDouble(parts[1]);
                } catch (NumberFormatException e) {
                    txtDisplay.setText("Error");
                    val1 = Double.NaN;
                    ACTION = '0';
                    return;
                }
                //verificacion entre 0
                if (ACTION == DIVIDIR && val2 == 0) {
                    txtDisplay.setText("Error: Div by 0");
                    val1 = Double.NaN;
                    ACTION = '0';
                    return;
                }
                //posibles operaciones
                switch (ACTION) {
                    case SUMAR:
                        val1 = val1 + val2;
                        break;
                    case RESTAR:
                        val1 = val1 - val2;
                        break;
                    case MULTIPLICAR:
                        val1 = val1 * val2;
                        break;
                    case DIVIDIR:
                        val1 = val1 / val2;
                        break;
                    case EQU:
                        break;
                }
            }
            //si todo falla lo cual es imposible en un caso normal mostramos error
        } else {
            try {
                val1 = Double.parseDouble(txtDisplay.getText().toString());
            } catch (NumberFormatException e) {
                txtDisplay.setText("Error");
                val1 = Double.NaN;
                ACTION = '0';
            }
        }
    }

}
