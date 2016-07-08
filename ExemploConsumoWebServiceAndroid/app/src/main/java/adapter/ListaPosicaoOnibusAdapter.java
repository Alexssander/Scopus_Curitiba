package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.scopus.jeffersonmonteiro.exemploconsumowebserviceandroid.R;

import java.util.List;

import dto.PosicaoOnibusVO;

/**
 * Created by JeffersonMonteiro on 06/07/2016.
 */

public class ListaPosicaoOnibusAdapter extends BaseAdapter {

    private List<PosicaoOnibusVO> listaPosicaoOnibus;
    private Context context;

    public ListaPosicaoOnibusAdapter(List<PosicaoOnibusVO> listaPosicaoOnibus, Context context) {
        this.listaPosicaoOnibus = listaPosicaoOnibus;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaPosicaoOnibus.size();
    }

    @Override
    public Object getItem(int position) {
        return listaPosicaoOnibus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_onibus_layout_adapter, parent, false);
        TextView txtListViewAdapter  = (TextView) view.findViewById(R.id.txtValuesOnibus);

        txtListViewAdapter.setText("Data/Hora: "+ listaPosicaoOnibus.get(position).getDataHora().toString()
                + " || Ordem: " + listaPosicaoOnibus.get(position).getOrdem()
                + " || Linha: " + listaPosicaoOnibus.get(position).getLinha()
                + " || Latitude : " + String.valueOf(listaPosicaoOnibus.get(position).getLatitude())
                + " || Longitude: " + String.valueOf(listaPosicaoOnibus.get(position).getLongitude())
                + " || Velocidade: " + String.valueOf(listaPosicaoOnibus.get(position).getVelocidade())
                + " || Direção: " + String.valueOf(listaPosicaoOnibus.get(position).getDirecao()));

        ImageView imgBabyListViewAdapter = (ImageView) view.findViewById(R.id.imgListaOnibus);
        imgBabyListViewAdapter.setImageResource(R.drawable.latitude);

        return view;
    }
}
