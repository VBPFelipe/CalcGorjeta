package com.vbpfelipe.calcgraph;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Declara os componentes que serão editados (alterados)
    EditText edtValorConta, edt10Perc, edt15Perc, edt20Perc, edtTotal10P,
            edtTotal15P, edtTotal20P, edtGorjVar, edtValContaVar;

    //declara o informativo da taxa de Gorjeta Variável
    TextView txtGorjetaVaria;

    //Declara a barra de variação de Gorjeta
    SeekBar gorjetaVariavel;

    //Declara o botão para o cálculo da Gorjeta
    Button btnCalculaGorjeta;

    //Variáveis auxiliares
    double valConta = 0.0;
    int perConta = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializando os componentes
        initComponentes();

        //Monitora o botão de cálculo de gorjeta
        btnCalculaGorjeta.setOnClickListener(new View.OnClickListener() {

            //Método do clique no botão
            @Override
            public void onClick(View view) {
                // Captura o clique do botão, se houver
                // Ocorrendo, tentará executar os procedimentos designados para a função.
                try{
                    valConta = Double.parseDouble(edtValorConta.getText().toString());
                    calculaGorjetas();
                    calculaGorjetaVar();
                    // Exibe uma mensagem no app
                    // Opcional, mas posicionado ao fim dos procedimentos,
                    // ao menos informará se o clicar funcionou perfeitamente
                    Toast.makeText(getApplicationContext(), "Calculando gorjetas...",
                            Toast.LENGTH_LONG).show();
                }catch(Exception e){
                    //Caso ocorra algum erro, este será capturado
                    // Informa no LogCat o erro
                    e.printStackTrace();
                    // Exibe uma mensagem de erro no app.
                    Toast.makeText(MainActivity.this, "ERROR: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        // Monitora eventos com a barra variável
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


    //Inicializa os Componentes declarados no começo do código.
    private void initComponentes(){
        btnCalculaGorjeta = (Button) findViewById(R.id.btnCalculaGorjeta);

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

    //Calculo dos diferentes tipos de gorjetas
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

    //Cálculo da Gorjeta Variável
    private void calculaGorjetaVar(){
        txtGorjetaVaria.setText(perConta + "%");

        double gorVar = valConta*(perConta*0.01);
        double valContaVar = valConta + gorVar;

        //Inserir nos objetos
        edtGorjVar.setText(String.format("%.02f", gorVar));
        edtValContaVar.setText(String.format("%.02f", valContaVar));
    }
}
