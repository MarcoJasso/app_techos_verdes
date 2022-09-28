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
import com.example.app_techos_vendes.models.CardPlantasModel;

import java.util.ArrayList;

public class AdaptadorCardPlantas extends ArrayAdapter<CardPlantasModel> {

    public AdaptadorCardPlantas(@NonNull Context context, @NonNull ArrayList<CardPlantasModel> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        CardPlantasModel cardPlantasModel = getItem(position);

        convertView = LayoutInflater.from(getContext()).inflate(R.layout.model_card_planta, null);

        ImageView imageView = convertView.findViewById(R.id.imgPlanta);
        TextView textView = convertView.findViewById(R.id.txt_nombre_planta);

        //imageView.setImageResource(cardPlantasModel.getImagenPlanta());

        textView.setText(cardPlantasModel.getNombrePlanta());

        return convertView;
    }
}
