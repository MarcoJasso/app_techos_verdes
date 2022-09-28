package com.example.app_techos_vendes.ui.consulta;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_techos_vendes.R;
import com.example.app_techos_vendes.adapters.AdaptadorCardConsulta;
import com.example.app_techos_vendes.adapters.AdaptadorCardConsultaRV;
import com.example.app_techos_vendes.adapters.AdaptadorCardInfoRV;
import com.example.app_techos_vendes.databinding.FragmentConsultaBinding;
import com.example.app_techos_vendes.models.CardConsultasModel;
import com.example.app_techos_vendes.ui.consulta.consulta_planta.ConsultaPlantasActivity;

import java.util.ArrayList;

public class ConsultaFragment extends Fragment {

    private FragmentConsultaBinding binding;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentConsultaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ArrayList<CardConsultasModel> listItems = new ArrayList<>();

        listItems.add(new CardConsultasModel(R.drawable.img_list_model_4, "Plantas"));
        listItems.add(new CardConsultasModel(R.drawable.img_list_model_3, "Tiendas"));

        recyclerView = binding.rvMenuConsulta;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(new AdaptadorCardConsultaRV(getContext(), listItems));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}