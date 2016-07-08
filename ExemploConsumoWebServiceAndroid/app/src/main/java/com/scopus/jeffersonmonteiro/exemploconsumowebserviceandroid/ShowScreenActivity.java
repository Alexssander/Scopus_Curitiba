package com.scopus.jeffersonmonteiro.exemploconsumowebserviceandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import dto.PosicaoOnibusVO;

public class ShowScreenActivity extends AppCompatActivity {

    private TextView txtdataHora;
    private TextView txtOrdem;
    private TextView txtLinha;
    private TextView txtLatitude;
    private TextView txtLongitude;
    private TextView txtVelocidade;
    private TextView txtDirecao;
    private PosicaoOnibusVO posicaoOnibusVO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_screen);
        setUpTextView();

        Intent intent = getIntent();
        posicaoOnibusVO = (PosicaoOnibusVO) intent.getSerializableExtra("onibus");

        txtdataHora.setText(posicaoOnibusVO.getDataHora());
        txtOrdem.setText(posicaoOnibusVO.getOrdem());
        txtLatitude.setText(String.valueOf(posicaoOnibusVO.getLatitude()));
        txtLongitude.setText(String.valueOf(posicaoOnibusVO.getLongitude()));
        txtVelocidade.setText(String.valueOf(posicaoOnibusVO.getVelocidade()));
        txtLinha.setText(posicaoOnibusVO.getLinha());
        txtDirecao.setText(String.valueOf(posicaoOnibusVO.getDirecao()));
    }


    private void setUpTextView() {
        txtdataHora = (TextView) findViewById(R.id.txtDataHora);
        txtOrdem = (TextView) findViewById(R.id.txtOrdemOnibus);
        txtLatitude = (TextView) findViewById(R.id.txtLatitudeOnibus);
        txtLongitude = (TextView) findViewById(R.id.txtLogitudeOnibus);
        txtVelocidade = (TextView) findViewById(R.id.txtVelocidadeOnibus);
        txtLinha = (TextView) findViewById(R.id.txtLinhaOnibus);
        txtDirecao = (TextView) findViewById(R.id.txtDirecaoOnibus);
    }
}
