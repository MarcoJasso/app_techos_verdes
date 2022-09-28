package com.example.app_techos_vendes.adapters;

import static android.Manifest.permission.CALL_PHONE;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_techos_vendes.R;
import com.example.app_techos_vendes.models.CardTiendaModel;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdaptadorCardTiendasRV extends RecyclerView.Adapter<AdaptadorCardTiendasRV.ViewHolder> {

    private LayoutInflater layoutInflater;
    private ArrayList<CardTiendaModel> listItems;

    public AdaptadorCardTiendasRV(Context context, ArrayList<CardTiendaModel> listItems) {
        this.layoutInflater = LayoutInflater.from(context);
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public AdaptadorCardTiendasRV.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.model_card_tiendas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorCardTiendasRV.ViewHolder holder, int position) {
        holder.setPosition(position);

        holder.txtNombreTienda.setText(listItems.get(position).getNombreTienda());
        holder.txtDescripcionTienda.setText(listItems.get(position).getDescripcionTienda());
        holder.txtDireccionTienda.setText(listItems.get(position).getDireccion());

        holder.searchImage(listItems.get(position).getUrlImagenTienda());

    }

    @Override
    public int getItemCount() {
        return this.listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final CircleImageView imgTienda;
        private final MaterialCardView cardView;

        private final TextView txtNombreTienda;
        private final TextView txtDescripcionTienda;
        private final TextView txtDireccionTienda;

        private final Button btnLlamar;

        private int position = 0;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgTienda = itemView.findViewById(R.id.imgTienda);
            txtNombreTienda = itemView.findViewById(R.id.txt_nombre_tienda);
            txtDescripcionTienda = itemView.findViewById(R.id.txt_descripcion_tienda);
            txtDireccionTienda = itemView.findViewById(R.id.txt_direccionTienda);

            cardView = itemView.findViewById(R.id.card_lista_tiendas);

            btnLlamar = itemView.findViewById(R.id.btnLlamarTienda);

            cardView.setOnClickListener(this);
            btnLlamar.setOnClickListener(this);

        }

        public void setPosition(int position) {
            this.position = position;
        }

        public void searchImage(String url){
            Picasso.get().load(url).into(imgTienda);
        }

        @Override
        public void onClick(View view) {
            int idView = view.getId();

            switch (idView){
                case R.id.btnLlamarTienda:
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + listItems.get(position).getTelefionoTienda()));
                    view.getContext().startActivity(intent);
                    break;
                case R.id.card_lista_tiendas:
                    Uri url = Uri.parse(listItems.get(position).getUrlPaginaTienda());
                    Intent intentWeb = new Intent(Intent.ACTION_VIEW, url);
                    view.getContext().startActivity(intentWeb);
                    break;
            }
        }
    }
}
