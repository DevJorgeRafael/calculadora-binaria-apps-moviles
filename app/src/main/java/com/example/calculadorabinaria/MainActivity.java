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

        if ( resultado.startsWith("C") ) {
            lblResultado.setText("0");
            return;
        }

        if (!resultado.isEmpty()) {
            resultado = resultado.substring(0, resultado.length() - 1);
        }

        if (resultado.isEmpty()) {
            resultado = "0";
        }

        lblResultado.setText(resultado);
        chambiarFontsize();
    }


    public void onUnoClick(View v){
        if(lblResultado.getText().equals("0") || lblResultado.getText().equals("Can't Divide by zero")){
            lblResultado.setText("1");
        } else {
            lblResultado.setText( lblResultado.getText() + "1" );
        }
        chambiarFontsize();
    }

    public void onCeroClick(View v){
        if(lblResultado.getText().equals("0") || lblResultado.getText().equals("Can't Divide by zero")){
            lblResultado.setText("0");
        } else {
            lblResultado.setText( lblResultado.getText() + "0" );
        }
        chambiarFontsize();
    }

    public void onSumarClick(View v) {
        if( lblResultado.getText().equals("0") || lblResultado.getText().equals("Can't Divide by zero")) return;

        establecerOperandoConResultado();
        operador = 1;
    }

    public void onRestarClick(View v) {
        if( lblResultado.getText().equals("0") || lblResultado.getText().equals("Can't Divide by zero")) return;

        establecerOperandoConResultado();
        operador = 2;
    }

    public void onMultiplicarClick(View v) {
        if( lblResultado.getText().equals("0") || lblResultado.getText().equals("Can't Divide by zero")) return;

        establecerOperandoConResultado();
        operador = 3;
    }

    public void onDividirClick(View v) {
        if( lblResultado.getText().equals("0") || lblResultado.getText().equals("Can't Divide by zero")) return;

        establecerOperandoConResultado();
        operador = 4;
    }

    public void onIgualClick(View v) {
        String respuesta = obtenerRespuesta();
        Log.d("MiApp", "Respuesta: " + respuesta);

        lblResultado.setText(respuesta);
        chambiarFontsize();
    }

    private String obtenerRespuesta(){
        if( operador == 0 ) return "0";

        int resultado = 0;

        int operando1 = Integer.parseInt( lblOperando.getText().toString(), 2 );
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
        lblOperando.setText( lblResultado.getText() );
        restablecerResultado();
    }
    public void onLimpiarClick(View v) {
        restablecerResultado();
        lblOperando.setText("0");
    }

    private void chambiarFontsize() {
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