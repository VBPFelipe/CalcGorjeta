package com.vbpfelipe.calcgraph;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText edtValorConta, edt10Perc, edt15Perc, edt20Perc, edtTotal10P,
            edtTotal15P, edtTotal20P, edtGorjVar, edtValContaVar;
    TextView txtGorjetaVaria;
    SeekBar gorjetaVariavel;

    double valConta = 0.0;
    int perConta = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponentes();

        edtValorConta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    valConta = Double.parseDouble(s.toString());
                    calculaGorjetas();
                    calculaGorjetaVar();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        gorjetaVariavel.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                perConta = seekBar.getProgress();
                calculaGorjetaVar();
            }
        });
    }

    private void calculaGorjetas(){

        double gor10 = valConta*0.1;//10%
        double gor15 = valConta*0.15;//15%
        double gor20 = valConta*0.2;//20%

        double vConta10 = valConta+gor10;
        double vConta15 = valConta+gor15;
        double vConta20 = valConta+gor20;

        //Inserir nos objetos
        edt10Perc.setText(String.format("%.02f", gor10));
        edt15Perc.setText(String.format("%.02f", gor15));
        edt20Perc.setText(String.format("%.02f", gor20));

        edtTotal10P.setText(String.format("%.02f", vConta10));
        edtTotal15P.setText(String.format("%.02f", vConta15));
        edtTotal20P.setText(String.format("%.02f", vConta20));
    }

    private void calculaGorjetaVar(){
        txtGorjetaVaria.setText(perConta + "%");

        double gorVar = valConta*(perConta*0.01);
        double valContaVar = valConta + gorVar;

        //Inserir nos objetos
        edtGorjVar.setText(String.format("%.02f", gorVar));
        edtValContaVar.setText(String.format("%.02f", valContaVar));
    }

    private void initComponentes(){
        edtValorConta = (EditText) findViewById(R.id.edtValorConta);

        edt10Perc = (EditText) findViewById(R.id.edt10Perc);
        edt15Perc = (EditText) findViewById(R.id.edt15Perc);
        edt20Perc = (EditText) findViewById(R.id.edt20Perc);

        edtTotal10P = (EditText) findViewById(R.id.edtTotal10P);
        edtTotal15P = (EditText) findViewById(R.id.edtTotal15P);
        edtTotal20P = (EditText) findViewById(R.id.edtTotal20P);

        edtGorjVar = (EditText) findViewById(R.id.edtGorjVar);
        edtValContaVar = (EditText) findViewById(R.id.edtValContaVar);

        txtGorjetaVaria = (TextView) findViewById(R.id.txtGorjetaVaria);

        gorjetaVariavel = (SeekBar) findViewById(R.id.gorjetaVariavel);
    }
}
