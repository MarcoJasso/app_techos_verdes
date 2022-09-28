package com.example.app_techos_vendes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.app_techos_vendes.R;
import com.example.app_techos_vendes.models.CardInfoModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class AdaptadorCardInfoRV extends RecyclerView.Adapter<AdaptadorCardInfoRV.ViewHolder> {

    private ArrayList<CardInfoModel> listItems;
    private LayoutInflater layoutInflater;

    public AdaptadorCardInfoRV(Context context, ArrayList<CardInfoModel> listItems) {
        this.listItems = listItems;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AdaptadorCardInfoRV.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        //View view = layoutInflater.inflate(R.layout.card_informativa, parent, false);

        View view = layoutInflater.from(parent.getContext()).inflate(R.layout.card_informativa, parent, false);
        //View view = layoutInflater.inflate(R.layout.card_informativa, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorCardInfoRV.ViewHolder holder, int position) {

        int img_card = listItems.get(position).getImagenSource();
        String titulo_card =  listItems.get(position).getTitulo();
        String subTitulo_card = listItems.get(position).getSubtitulo();
        String contenido_card = listItems.get(position).getContenido();

        holder.imgCard.setImageResource(img_card);
        holder.titleCard.setText(titulo_card);
        holder.subTitleCard.setText(subTitulo_card);
        holder.contenidoCard.setText(contenido_card);

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imgCard;
        private final TextView titleCard;
        private final TextView subTitleCard;
        private final TextView contenidoCard;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            imgCard = itemView.findViewById(R.id.img_card_informativa);
            titleCard =  itemView.findViewById(R.id.txt_titulo_card_informativa);
            subTitleCard = itemView.findViewById(R.id.txt_subtitulo_card_informativa);
            contenidoCard = itemView.findViewById(R.id.txt_body_card_informativa);


        }

    }
}
