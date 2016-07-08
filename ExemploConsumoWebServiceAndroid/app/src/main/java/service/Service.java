package service;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import dto.PosicaoOnibusVO;

/**
 * Created by JeffersonMonteiro on 05/07/2016.
 */

public class Service {

    public ArrayList<PosicaoOnibusVO> mappingJsonToDtoOnibus(String jsonString)
    {
        // Declara a variável de retorno, que conterá as posições de ônibus.
        ArrayList<PosicaoOnibusVO> listaDePosicoesDeOnibus = new ArrayList<PosicaoOnibusVO>();

        // Declara o DTO que comporá os itens da lista.
        PosicaoOnibusVO posicaoOnibusVO;

        // Transforma a String em JSONObject.
        JSONObject jObj;
        try
        {
            jObj=new JSONObject(jsonString);

            // Extrai do JSONObject os arrays de COLUNAS e POSIÇÕES.
            JSONArray colunas=jObj.getJSONArray("COLUMNS");
            JSONArray posicoesOnibus=jObj.getJSONArray("DATA");

            // Define a variável que guardará uma posição individual de ônibus.
            JSONArray posicaoOnibus;

            // Define variáveis para conversão de String em Date.
            String dateString;
            SimpleDateFormat formatter;

            String direcao="";

            // Itera sobre a String JSON, para extração individual dos dados.
            for (int i=0; i < posicoesOnibus.length(); i++)
            {
                try
                {
                    // Extrai uma posição de ônibus individual.
                    posicaoOnibus=posicoesOnibus.getJSONArray(i);

                    // Instancia o objeto para compor a lista.
                    posicaoOnibusVO = new PosicaoOnibusVO();

                    // Faz o parse de cada campo, transferindo para o DTO que comporá a lista.
                    posicaoOnibusVO.setDataHora(posicaoOnibusVO.getDataHora());
                    posicaoOnibusVO.setOrdem(posicaoOnibus.getString(1));
                    posicaoOnibusVO.setLinha(posicaoOnibus.getString(2).replace(".0", ""));
                    posicaoOnibusVO.setLatitude(Double.valueOf(posicaoOnibus.getString(3)));
                    posicaoOnibusVO.setLongitude(Double.valueOf(posicaoOnibus.getString(4)));
                    posicaoOnibusVO.setVelocidade(Double.parseDouble(posicaoOnibus.getString(5)));
                    direcao=((posicaoOnibus.getString(6)).length() == 0) ? "-1" : posicaoOnibus.getString(6);
                    direcao=direcao.replace(".0", "");
                    posicaoOnibusVO.setDirecao(Integer.parseInt(direcao));

                    // Acrescenta o DTO à lista.
                    listaDePosicoesDeOnibus.add(posicaoOnibusVO);

                }
                catch (Exception e)
                {
                    Log.i("consumidorREST", "Erro 5:" + e.getMessage());
                }
            }
        }
        catch (JSONException f)
        {
            Log.i("consumidorREST", "Erro 6:" + f.getMessage());
        }

        // Retorna a lista de posições de ônibus.
        return listaDePosicoesDeOnibus;
    }

    public ArrayList<PosicaoOnibusVO> mappingJsonToDtoOnibusGSON(String jsonString) throws ParseException {

        ArrayList<PosicaoOnibusVO> listaDePosicoesDeOnibus = new ArrayList<PosicaoOnibusVO>();

        Gson json = new Gson();

        Type collectionType = new TypeToken<List<PosicaoOnibusVO>>() {}.getType();

        List<PosicaoOnibusVO> listPosicoesOnibus = json.fromJson(jsonString, collectionType);

        for (PosicaoOnibusVO posicaoOnibusVO : listPosicoesOnibus) {
            posicaoOnibusVO.setDataHora(posicaoOnibusVO.getDataHora());
            posicaoOnibusVO.setOrdem(posicaoOnibusVO.getOrdem());
            posicaoOnibusVO.setLinha(posicaoOnibusVO.getLinha().replace(".0", ""));
            posicaoOnibusVO.setLatitude(posicaoOnibusVO.getLatitude());
            posicaoOnibusVO.setLongitude(posicaoOnibusVO.getLongitude());
            posicaoOnibusVO.setVelocidade(posicaoOnibusVO.getVelocidade());
            posicaoOnibusVO.setDirecao(posicaoOnibusVO.getDirecao());

            // Acrescenta o DTO à lista.
            listaDePosicoesDeOnibus.add(posicaoOnibusVO);
        }
        // Retorna a lista de posições de ônibus.
        return listaDePosicoesDeOnibus;
    }


    public void mostraLogResult(String resultado) throws ParseException {
        // Chama o método para consumo do webService REST/JSON.
        // Aqui passamos como parâmetro a linha de ônibus 457. O retorno será uma String com o fluxo JSON que tem
        // as posições dos veículos da linha 457, de meia-noite até o momento do consumo.

        String jsonString = resultado;

        // Mostra no LogCat o resultado do consumo.
        Log.i("consumidorREST", jsonString);

        // Executa o parse do JSON, extraindo as posições de ônibus do fluxo JSON.
        ArrayList<PosicaoOnibusVO> posicoesDeOnibus = new ArrayList<PosicaoOnibusVO>();
        //posicoesDeOnibus = this.mappingJsonToDtoOnibus(jsonString);
        posicoesDeOnibus = this.mappingJsonToDtoOnibusGSON(jsonString);

        if (posicoesDeOnibus.size() > 0)
        {
             // Itera sobre a estrutura de dados que agora contém as posições dos ônibus.
            for (PosicaoOnibusVO posicao : posicoesDeOnibus)
            {
                Log.i("consumidorREST", "Data/Hora  : " + posicao.getDataHora()+ "");
                Log.i("consumidorREST", "Veículo    : " + posicao.getOrdem() + "");
                Log.i("consumidorREST", "Linha      : " + posicao.getLinha() + "");
                Log.i("consumidorREST", "Latitude   : " + posicao.getLatitude() + "");
                Log.i("consumidorREST", "Longitude  : " + posicao.getLongitude() + "");
                Log.i("consumidorREST", "Velocidade : " + posicao.getVelocidade() + "");
                Log.i("consumidorREST", "Direção    : " + posicao.getDirecao() + "");
            }
        }
    }
}
