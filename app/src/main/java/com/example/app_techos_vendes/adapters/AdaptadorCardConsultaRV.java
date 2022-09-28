package com.example.app_techos_vendes.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_techos_vendes.R;
import com.example.app_techos_vendes.models.CardConsultasModel;
import com.example.app_techos_vendes.ui.consulta.consulta_planta.ConsultaPlantasActivity;
import com.example.app_techos_vendes.ui.consulta.consulta_tiendas.ConsultaTiendasActivity;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class AdaptadorCardConsultaRV extends RecyclerView.Adapter<AdaptadorCardConsultaRV.ViewHolder> {

    private ArrayList<CardConsultasModel> listItems;
    private LayoutInflater layoutInflater;

    public AdaptadorCardConsultaRV(Context contex, ArrayList<CardConsultasModel> listItems) {
        this.layoutInflater = LayoutInflater.from(contex);
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.from(parent.getContext()).inflate(R.layout.card_menu_consulta, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imgConsulta.setImageResource(this.listItems.get(position).getImageSource());
        holder.tituloConsulta.setText(this.listItems.get(position).getTitulo());
        holder.setPosition(position);
    }

    @Override
    public int getItemCount() {
        return this.listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView imgConsulta;
        private final TextView tituloConsulta;
        private final MaterialCardView cardView;
        private int position;

        @SuppressLint("CutPasteId")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardView_menu_consulta);
            imgConsulta = itemView.findViewById(R.id.img_card_menu_consulta);
            tituloConsulta = itemView.findViewById(R.id.txt_titulo_card_menu_consulta);

            cardView.setOnClickListener(this);

        }

        private void setPosition(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            switch (this.position) {
                case 0:

                    Toast.makeText(context,
                            "Selecciono un elemento [ Plantas ].", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(context, ConsultaPlantasActivity.class);
                    context.startActivity(intent);
                    break;

                case 1:
                    Toast.makeText(context,
                            "Selecciono un elemento: [ Tiendas].", Toast.LENGTH_LONG).show();
                    Intent intentTiendas = new Intent(context, ConsultaTiendasActivity.class);
                    context.startActivity(intentTiendas);
                    break;
            }
        }
    }
}
