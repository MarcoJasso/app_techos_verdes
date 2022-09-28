package com.example.app_techos_vendes.ui.consulta.consulta_planta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.app_techos_vendes.R;
import com.example.app_techos_vendes.adapters.AdaptadorCardPlantas;
import com.example.app_techos_vendes.adapters.AdaptadorCardPlantasRV;
import com.example.app_techos_vendes.apimodelsresponse.ApiResponseModel;
import com.example.app_techos_vendes.consumeapi.ApiService;
import com.example.app_techos_vendes.databinding.ActivityConsultaPlantasBinding;
import com.example.app_techos_vendes.models.CardPlantasModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.jetbrains.annotations.Contract;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import kotlinx.coroutines.InterruptibleKt;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConsultaPlantasActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityConsultaPlantasBinding binding;
    private ArrayList<CardPlantasModel> listItems;

    private LinearLayout linearLayoutContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConsultaPlantasBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        setContentView(root);

        getTodasPlantas(this);
    }

    @NonNull
    @Contract(" -> new")
    private Retrofit getRetrofit() {
        Retrofit retro = new Retrofit.Builder().baseUrl("http://128.0.57.145:5500/")
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().serializeNulls().create()
                ))
                .build();
        return retro;
    }

    private void getTodasPlantas(Context context) {


        ApiService apiService = getRetrofit().create(ApiService.class);

        Call call = apiService.getRespuesta();

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

                        CardPlantasModel item = new CardPlantasModel(
                                Integer.parseInt(jsonObject.get("id_planta").toString().replace("\"", "")),
                                jsonObject.get("url_imagen").toString().replace("\"", ""),
                                jsonObject.get("nombre_col").toString().replace("\"", ""),
                                jsonObject.get("nombre_cient").toString().replace("\"", ""),
                                jsonObject.get("descripcion").toString().replace("\"", ""),
                                jsonObject.get("luz").toString().replace("\"", ""),
                                jsonObject.get("floracion").toString().replace("\"", ""),
                                jsonObject.get("sustrato").toString().replace("\"", ""),
                                "",
                                "");

                        listItems.add(item);
                    }

                    showData();
                    RecyclerView recyclerView = binding.rvCardPlantas;
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));

                    recyclerView.setAdapter(new AdaptadorCardPlantasRV(context, listItems));

                    Toast.makeText(context, "Successful connection", Toast.LENGTH_LONG).show();

                } else {
                    showMessageResponse("Error connection.");
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                showMessageResponse("Error connection: \n" + t.getMessage());
            }
        });


    }

    private void showData(){
        binding.viewLoading.setVisibility(View.GONE);
        binding.viewContainer.setVisibility(View.VISIBLE);
    }

    private void showMessageResponse(String message) {
        new MaterialAlertDialogBuilder(this)
                .setTitle("Respuesta")
                .setMessage(message)
                .show();
    }

    public void backMainActivity(View view) {
        onBackPressed();
        finish();
    }

    @Override
    public void onClick(View view) {

    }
}