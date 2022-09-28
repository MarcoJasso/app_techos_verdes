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
import androidx.fragment.app.Fragment;

import com.example.app_techos_vendes.R;
import com.example.app_techos_vendes.models.CardInfoModel;

import java.util.ArrayList;

public class AdaptadorCardInfo extends ArrayAdapter<CardInfoModel> {

    public AdaptadorCardInfo(@NonNull Context context, @NonNull ArrayList<CardInfoModel> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        CardInfoModel cardInfoModel = getItem(position);

        //convertView = LayoutInflater.from(getContext()).inflate(R.layout.model_card_planta, parent, false);

        convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_informativa,null);

        ImageView imgCard = convertView.findViewById(R.id.img_card_informativa);
        TextView titleCard =  convertView.findViewById(R.id.txt_titulo_card_informativa);
        TextView subTitleCard = convertView.findViewById(R.id.txt_subtitulo_card_informativa);
        TextView contenidoCard = convertView.findViewById(R.id.txt_body_card_informativa);

        imgCard.setImageResource(cardInfoModel.getImagenSource());
        titleCard.setText(cardInfoModel.getTitulo());
        subTitleCard.setText(cardInfoModel.getSubtitulo());
        contenidoCard.setText(cardInfoModel.getContenido());

        //return super.getView(position, convertView, parent);
        return convertView;
    }
}
