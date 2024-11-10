package com.example.calculadorabinaria;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Variables de botones
    private Button btnBorrar;
    private Button btnUno;
    private Button btnCero;
    private Button btnSumar;
    private Button btnRestar;
    private Button btnMultiplicar;
    private Button btnDividir;
    private Button btnIgual;

    // Variables de los textview
    private TextView lblResultado;
    private TextView lblOperando;

    //variable local para operar
    private int operador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Inicializar los botones por id
        btnBorrar = findViewById(R.id.btnBorrar);
        btnUno = findViewById(R.id.btnUno);
        btnCero = findViewById(R.id.btnCero);
        btnSumar = findViewById(R.id.btnSumar);
        btnRestar = findViewById(R.id.btnRestar);
        btnMultiplicar = findViewById(R.id.btnMultiplicar);
        btnDividir = findViewById(R.id.btnDividir);
        btnIgual = findViewById(R.id.btnIgual);

        lblResultado = findViewById(R.id.lblResultado);
        lblOperando = findViewById(R.id.lblOperando);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //Métodos para realizar las operaciones
    public void onBorrarClick(View v) {
        String resultado = lblResultado.getText().toString();

        if ( resultado.startsWith("C") || resultado.isEmpty() ) {
            lblResultado.setText("0");
            cambiarFontsize();
            return;
        }

        if (!resultado.isEmpty()) {
            resultado = resultado.substring(0, resultado.length() - 1);
        }

        if (resultado.isEmpty()) {
            resultado = "0";
        }

        lblResultado.setText(resultado);
        cambiarFontsize();
    }


    public void onUnoClick(View v){
        if(lblResultado.getText().equals("0") || lblResultado.getText().equals("Can't Divide by zero")){
            lblResultado.setText("1");
        } else {
            lblResultado.setText( lblResultado.getText() + "1" );
        }
        cambiarFontsize();
    }

    public void onCeroClick(View v){
        if(lblResultado.getText().equals("0") || lblResultado.getText().equals("Can't Divide by zero")){
            lblResultado.setText("0");
        } else {
            lblResultado.setText( lblResultado.getText() + "0" );
        }
        cambiarFontsize();
    }

    public void onSumarClick(View v) {
        if( lblResultado.getText().equals("0") || lblResultado.getText().equals("Can't Divide by zero")) return;

        this.operador = 1;
        establecerOperandoConResultado();
    }

    public void onRestarClick(View v) {
        if( lblResultado.getText().equals("0") || lblResultado.getText().equals("Can't Divide by zero")) return;

        this.operador = 2;
        establecerOperandoConResultado();
    }

    public void onMultiplicarClick(View v) {
        if( lblResultado.getText().equals("0") || lblResultado.getText().equals("Can't Divide by zero")) return;

        this.operador = 3;
        establecerOperandoConResultado();
    }

    public void onDividirClick(View v) {
        if( lblResultado.getText().equals("0") || lblResultado.getText().equals("Can't Divide by zero")) return;

        this.operador = 4;
        establecerOperandoConResultado();
    }

    public void onIgualClick(View v) {
        String respuesta = obtenerRespuesta();

        lblResultado.setText(respuesta);
        cambiarFontsize();
    }

    private String obtenerRespuesta(){
        if( operador == 0 ) return "0";

        int resultado = 0;
        String stringOperando1 = lblOperando.getText().toString().substring(0, lblOperando.getText().length() - 1);

        int operando1 = Integer.parseInt( stringOperando1, 2 );
        if ( lblResultado.getText().equals("Can't Divide by zero") ) return "Can't Divide by zero";
        int operando2 = Integer.parseInt( lblResultado.getText().toString(), 2 );

        if( operador == 1 ) resultado = operando1 + operando2;
        else if( operador == 2 ) resultado = operando1 - operando2;
        else if( operador == 3 ) resultado = operando1 * operando2;
        else if( operador == 4 ) {
            if ( operando2 == 0 ) return "Can't Divide by zero";
            else resultado = operando1 / operando2;

        }

        return Integer.toBinaryString(resultado);
    }



    private void restablecerResultado(){
        lblResultado.setText("0");
    }

    private void establecerOperandoConResultado() {
        String stringOperador = "";
        switch ( this.operador ) {
            case 1:
                stringOperador = "+";
                break;
            case 2:
                stringOperador = "-";
                break;
            case 3:
                stringOperador = "*";
                break;
            case 4:
                stringOperador = "/";
                break;
        }

        lblOperando.setText( lblResultado.getText() + stringOperador );
        restablecerResultado();
    }
    public void onLimpiarClick(View v) {
        restablecerResultado();
        lblOperando.setText("0");
    }

    private void cambiarFontsize() {
        int lenghtResultado = lblResultado.getText().length();
        if ( lenghtResultado > 16 ) {
            lblResultado.setTextSize(24);
        }
        else if ( lenghtResultado > 8 ) {
            lblResultado.setTextSize(32);
        }
        else if (lenghtResultado > 5 ) {
            lblResultado.setTextSize(64);
        } else {
            lblResultado.setTextSize(96);
        }
    }
}