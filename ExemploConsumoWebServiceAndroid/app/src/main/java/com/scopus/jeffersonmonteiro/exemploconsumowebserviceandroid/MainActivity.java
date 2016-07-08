package com.scopus.jeffersonmonteiro.exemploconsumowebserviceandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

import adapter.ListaPosicaoOnibusAdapter;
import dto.PosicaoOnibusVO;
import service.Service;
import util.Util;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ExemploConsumoWebServiceAndroid";
    public static final String APPLICATION_JSON = "application/json";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String ACCEPT = "Accept";
    public static final int MILLIS = 30000;
    public static final String HTTP = "http";
    public static final int TIMEOUT = 60000;
    private static final int LINHA_DE_ONIBUS = 457;
    private static final String ENDPOINT = "172.16.210.106";
    private static final int PORT = 9090;
    private static final String PATH = "/api/linha";
    private ProgressDialog dialog;

    private Service service;
    private Handler handler;
    private ListView listViewOnibus;
    private ListaPosicaoOnibusAdapter listOnibusAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posicao_onibus_layout);
        service = new Service();
        handler = new Handler();
        new RestTask(ENDPOINT, PATH, LINHA_DE_ONIBUS, PORT).execute();
     }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Classe utilizada para consumir o Web service via REST
    private class RestTask extends AsyncTask<String, Void, String> {

        private String host;
        private String path;
        private Integer number;
        private int port;


        private HttpURLConnection conn;

        public RestTask(String host, String path, int number, int port) {
            this.host = host;
            this.path = path;
            this.number =number;
            this.port = port;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(MainActivity.this, "Exemplo", "Carregando lista, por favor aguarde!!");
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Log.v(TAG,"url- "+host+":"+port+"/"+path+"/"+number);
                URL url = new URL(HTTP, this.host, this.port, this.path + "/" + this.number);
                conn = (HttpURLConnection) url.openConnection();
                configConnection();
                return Util.convertInputStreamToString(conn);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        private void configConnection() {
            conn.setReadTimeout(TIMEOUT);
            conn.setConnectTimeout(TIMEOUT);
            conn.setRequestProperty(CONTENT_TYPE, APPLICATION_JSON);
            conn.setRequestProperty(ACCEPT, APPLICATION_JSON);
            conn.setDoInput(true);
            conn.setAllowUserInteraction(true);
        }

        @Override
        protected void onPostExecute(String s) {
            Log.v(TAG,"json in-"+s);
            try {
                if (s != null) {
                    service.mostraLogResult(s);
                    updateFieldsScreen(s);
                }
            }catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(null);
    }

    //Atualiza o adapter e passa o a posição do objeto para a tela de detalhes
    private void updateFieldsScreen(String value) throws ParseException {
        final ArrayList<PosicaoOnibusVO> posicoesDeOnibus = service.mappingJsonToDtoOnibusGSON(value);

        listViewOnibus = (ListView) findViewById(R.id.lstViewListaOnibus);
        listOnibusAdapter = new ListaPosicaoOnibusAdapter(posicoesDeOnibus, this);
        listViewOnibus.setAdapter(listOnibusAdapter);

        listViewOnibus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(), ShowScreenActivity.class);
                intent.putExtra("onibus", posicoesDeOnibus.get(position));
                ActivityCompat.startActivity(MainActivity.this, intent, null);

            }
        });
        dialog.dismiss();
    }

    //Atualiza os dados via requisão do Web Service
    public void refreshOnClick(View v){
        new RestTask(ENDPOINT, PATH, LINHA_DE_ONIBUS, PORT).execute();
    }

}
