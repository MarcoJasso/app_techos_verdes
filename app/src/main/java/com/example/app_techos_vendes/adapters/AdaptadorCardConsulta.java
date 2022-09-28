package com.example.app_techos_vendes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.app_techos_vendes.R;
import com.example.app_techos_vendes.models.CardConsultasModel;

import java.util.ArrayList;

public class AdaptadorCardConsulta extends ArrayAdapter<CardConsultasModel> {
    public AdaptadorCardConsulta(@NonNull Context context,  @NonNull ArrayList<CardConsultasModel> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        CardConsultasModel cardConsultasModel = getItem(position);

        convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_menu_consulta, null);

        ImageView img_consulta = convertView.findViewById(R.id.img_card_menu_consulta);
        TextView txt_titulo_consulta = convertView.findViewById(R.id.txt_titulo_card_menu_consulta);

        img_consulta.setImageResource(cardConsultasModel.getImageSource());
        txt_titulo_consulta.setText(cardConsultasModel.getTitulo());

        return convertView;
    }
}
