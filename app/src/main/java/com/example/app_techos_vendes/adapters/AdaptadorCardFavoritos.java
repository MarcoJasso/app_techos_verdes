package com.example.app_techos_vendes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_techos_vendes.R;
import com.example.app_techos_vendes.models.CardFavoritos;
import com.example.app_techos_vendes.models.CardPlantasModel;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdaptadorCardFavoritos extends RecyclerView.Adapter<AdaptadorCardFavoritos.ViewHolder>{

    private ArrayList<CardFavoritos> listItems;
    private LayoutInflater layoutInflater;

    public AdaptadorCardFavoritos(Context context, ArrayList<CardFavoritos> listItems) {
        this.layoutInflater = LayoutInflater.from(context);
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public AdaptadorCardFavoritos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.from(parent.getContext()).inflate(R.layout.model_card_favorito, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorCardFavoritos.ViewHolder holder, int position) {
        holder.setPosition(position);

        holder.nombreFevorito.setText(listItems.get(position).getNombrePlanta());
        holder.descripcionFavorito.setText(listItems.get(position).getDescripcionCorta());

        holder.searchImage(listItems.get(position).getImagenPlanta());
    }

    @Override
    public int getItemCount() {
        return this.listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final CircleImageView imagenFavorito;
        private final MaterialCardView cardView;

        private final TextView nombreFevorito;
        private final TextView descripcionFavorito;

        private final ImageButton btnEliminarFavorito;

        private int position = 0;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imagenFavorito = itemView.findViewById(R.id.img_card_informativa);
            cardView = itemView.findViewById(R.id.cardViewFavorita);

            nombreFevorito = itemView.findViewById(R.id.txt_nombre_planta_fav);
            descripcionFavorito = itemView.findViewById(R.id.txt_descripcion_planta_fav);

            btnEliminarFavorito = itemView.findViewById(R.id.btn_borrar_planta_fav);

            btnEliminarFavorito.setOnClickListener(this);

        }

        public void searchImage(String url){
            Picasso.get().load(url).into(imagenFavorito);
        }
        @Override
        public void onClick(View view) {

        }

        public void setPosition(int position) {
            this.position = position;
        }
    }
}
