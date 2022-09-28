package com.example.app_techos_vendes.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_techos_vendes.R;
import com.example.app_techos_vendes.adapters.AdaptadorCardInfo;
import com.example.app_techos_vendes.adapters.AdaptadorCardInfoRV;
import com.example.app_techos_vendes.databinding.FragmentInicioBinding;
import com.example.app_techos_vendes.models.CardInfoModel;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private FragmentInicioBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentInicioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ArrayList<CardInfoModel> listItems = new ArrayList<>();

        listItems.add(new CardInfoModel("Techos verdes", "La importacia de ser ecologicos.", getString(R.string.content_body_card_1), R.drawable.img_card_inf_1));
        listItems.add(new CardInfoModel("Vegetación", "Tipo de plantas para techos verdes.", getString(R.string.content_body_card_1), R.drawable.img_card_inf_2));
        listItems.add(new CardInfoModel("Huerto", "Tecnicas para hacer tu huerto.", getString(R.string.content_body_card_1), R.drawable.fondo_huerto));

        RecyclerView recyclerViewCardInformativa = (RecyclerView) binding.rvCardInformativa;
        recyclerViewCardInformativa.setHasFixedSize(true);
        recyclerViewCardInformativa.setLayoutManager( new LinearLayoutManager(getContext()));

        recyclerViewCardInformativa.setAdapter(new AdaptadorCardInfoRV(getContext(), listItems));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}