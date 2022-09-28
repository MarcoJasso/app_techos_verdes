package com.example.app_techos_vendes.ui.uifavoritos;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_techos_vendes.MainActivity;
import com.example.app_techos_vendes.R;
import com.example.app_techos_vendes.adapters.AdaptadorCardFavoritos;
import com.example.app_techos_vendes.adapters.AdaptadorCardPlantasRV;
import com.example.app_techos_vendes.apimodelsresponse.ApiResponseModel;
import com.example.app_techos_vendes.consumeapi.ApiService;
import com.example.app_techos_vendes.databinding.ActivityMainBinding;
import com.example.app_techos_vendes.databinding.FragmentFavoritosBinding;
import com.example.app_techos_vendes.databinding.FragmentLoginBinding;
import com.example.app_techos_vendes.models.AppContainer;
import com.example.app_techos_vendes.models.CardFavoritos;
import com.example.app_techos_vendes.models.CardPlantasModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.prefs.Preferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavoritosFragment extends Fragment {

    private ArrayList<CardFavoritos> listItems;
    private FragmentFavoritosBinding binding;
    private ActivityMainBinding Mainbinding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentFavoritosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        getAllFav(getContext());
        return root;
    }

    @NonNull
    @Contract(" -> new")
    private Retrofit getRetrofit() {
        Retrofit retro = new Retrofit.Builder().baseUrl("http://128.0.207.49:5500/")
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().serializeNulls().create()
                ))
                .build();
        return retro;
    }

    private void getAllFav(Context context){
        ApiService apiService = getRetrofit().create(ApiService.class);

        Call call = apiService.getAllFav();

        call.enqueue(new Callback<ApiResponseModel>() {
            @Override
            public void onResponse(Call<ApiResponseModel> call, Response<ApiResponseModel> response) {

                if (response.isSuccessful()) {

                    response.body();

                    listItems = new ArrayList<>();
                    String salida = "";
                    JsonParser parser = new JsonParser();
                    JsonArray jsonArray = parser.parse(response.body().getMessage()).getAsJsonArray();

                    for (JsonElement element : jsonArray) {

                        JsonObject jsonObject = element.getAsJsonObject();

                        CardFavoritos item = new CardFavoritos(
                                Integer.parseInt(jsonObject.get("id_planta").toString().replace("\"", "")),
                                jsonObject.get("url_imagen").toString().replace("\"", ""),
                                jsonObject.get("nombre_col").toString().replace("\"", ""),
                                jsonObject.get("nombre_cient").toString().replace("\"", ""),
                                jsonObject.get("descripcion").toString().replace("\"", ""),
                                jsonObject.get("luz").toString().replace("\"", ""),
                                jsonObject.get("floracion").toString().replace("\"", ""),
                                jsonObject.get("sustrato").toString().replace("\"", ""),
                                "",
                                "",
                                Integer.parseInt(jsonObject.get("id_favorito").toString().replace("\"", ""))
                                );

                        listItems.add(item);
                    }

                    RecyclerView recyclerView = binding.rvCardFavoritos;
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));

                    recyclerView.setAdapter(new AdaptadorCardFavoritos(context, listItems));

                    Toast.makeText(context, "Successful connection", Toast.LENGTH_LONG).show();


                    showMessageResponse(getContext(), ((AppContainer) getContext().getApplicationContext()).getValorUsuario());

                } else {
                    showMessageResponse(context,"Error connection.");
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                showMessageResponse(context,"Error connection: \n" + t.getMessage());
            }
        });


    }

    private void showMessageResponse(Context context, String message){
        new MaterialAlertDialogBuilder(context)
                .setTitle("Respuesta")
                .setMessage(message)
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}