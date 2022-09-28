package com.example.app_techos_vendes.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_techos_vendes.R;
import com.example.app_techos_vendes.models.CardPlantasModel;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class AdaptadorCardPlantasRV extends RecyclerView.Adapter<AdaptadorCardPlantasRV.ViewHolder> {

    private LayoutInflater layoutInflater;
    private ArrayList<CardPlantasModel> listItems;

    public AdaptadorCardPlantasRV(Context context, ArrayList<CardPlantasModel> objs) {
        this.layoutInflater = LayoutInflater.from(context);
        this.listItems = objs;
    }

    @NonNull
    @Override
    public AdaptadorCardPlantasRV.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.model_card_planta, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AdaptadorCardPlantasRV.ViewHolder holder, int position) {

        holder.setPosition(position);

        holder.nombrePlanta.setText(listItems.get(position).getNombrePlanta());
        holder.nombreCientifico.setText(listItems.get(position).getNombreCientifico());
        holder.descripcionCorta.setText(listItems.get(position).getDescripcionCorta());

        holder.searchImage(listItems.get(position).getImagenPlanta());

        if (listItems.get(position).getLuzPlanta().equals("1")) {

            holder.descripcionTipoPlanta.setText("Planta de luz");
            holder.icTipoPlantaLuz.setImageResource(R.drawable.ic_wb_sunny_24);

        } else {

            holder.descripcionTipoPlanta.setText("Planta de sombra");
            holder.icTipoPlantaLuz.setImageResource(R.drawable.ic_wb_cloudy_24);

        }
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView imgPlanta;
        private final ImageView icTipoPlantaLuz;

        private final TextView nombrePlanta;
        private final TextView nombreCientifico;
        private final TextView descripcionCorta;
        private final TextView descripcionTipoPlanta;

        private final MaterialCardView cardViewPlanta;
        private final ImageView btnAgregar;

        private int position = 0;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPlanta = itemView.findViewById(R.id.imgPlanta);
            icTipoPlantaLuz = itemView.findViewById(R.id.ic_tipo_planta_luz);

            nombrePlanta = itemView.findViewById(R.id.txt_nombre_planta);
            nombreCientifico = itemView.findViewById(R.id.txt_nombre_cientifico_planta);
            descripcionCorta = itemView.findViewById(R.id.txt_descripcion_planta);
            descripcionTipoPlanta = itemView.findViewById(R.id.txt_tipo_planta_luz);

            cardViewPlanta = itemView.findViewById(R.id.card_lista_plantas);
            btnAgregar = itemView.findViewById(R.id.btn_ic_agregar_favorito);


            cardViewPlanta.setOnClickListener(this);
            btnAgregar.setOnClickListener(this);
        }

        public void setPosition(int positionq) {
            this.position = positionq;
        }

        public void searchImage(String url) {
            Picasso.get().load(url).into(imgPlanta);
        }

        @Override
        public void onClick(View view) {
            int btnAgregar = view.getId();
            if (R.id.btn_ic_agregar_favorito == btnAgregar) {
                onClickAddFavorito(view);
            } else {
                new MaterialAlertDialogBuilder(view.getContext())
                        .setTitle(listItems.get(position).getNombrePlanta())
                        .setMessage("Sustrato: " + listItems.get(position).getSustratoPlanta() + "\n" + "Floración: " + listItems.get(position).getFloracionPlanta())
                        .show();
            }
        }

        public void onClickAddFavorito(View view) {
            btnAgregar.setImageResource(R.drawable.ic_favorite_24);
            Toast.makeText(view.getContext(), "Planta agregada a favoritos. " + listItems.get(position).getIdPlanta(), Toast.LENGTH_SHORT).show();

        }

    }
}
