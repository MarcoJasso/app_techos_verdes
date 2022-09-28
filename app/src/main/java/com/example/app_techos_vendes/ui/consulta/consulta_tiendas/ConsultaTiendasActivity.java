package com.example.app_techos_vendes.ui.consulta.consulta_tiendas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.app_techos_vendes.R;
import com.example.app_techos_vendes.adapters.AdaptadorCardPlantasRV;
import com.example.app_techos_vendes.adapters.AdaptadorCardTiendasRV;
import com.example.app_techos_vendes.apimodelsresponse.ApiResponseModel;
import com.example.app_techos_vendes.consumeapi.ApiService;
import com.example.app_techos_vendes.databinding.ActivityConsultaTiendasBinding;
import com.example.app_techos_vendes.models.CardPlantasModel;
import com.example.app_techos_vendes.models.CardTiendaModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConsultaTiendasActivity extends AppCompatActivity {

    private ActivityConsultaTiendasBinding binding;
    private ArrayList<CardTiendaModel> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityConsultaTiendasBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        setContentView(root);

        getAllStore(this);
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

    private void getAllStore(Context context){
        ApiService apiService = getRetrofit().create(ApiService.class);

        Call call = apiService.getAllStore();

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

                        CardTiendaModel item = new CardTiendaModel(
                                Integer.parseInt(jsonObject.get("idTienda").toString().replace("\"", "")),
                                jsonObject.get("UrlImagenTienda").toString().replace("\"", ""),
                                jsonObject.get("UrlPaginaTienda").toString().replace("\"", ""),
                                jsonObject.get("Nombretienda").toString().replace("\"", ""),
                                jsonObject.get("DescripcionTienda").toString().replace("\"", ""),
                                jsonObject.get("Direccion").toString().replace("\"", ""),
                                jsonObject.get("telefonoTienda").toString().replace("\"", "")
                        );

                        salida += item.getNombreTienda() + "\n";
                        listItems.add(item);
                    }

                    RecyclerView recyclerView = binding.rvCardTiendas;
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));

                    recyclerView.setAdapter(new AdaptadorCardTiendasRV(context, listItems));

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

    private void showMessageResponse(String message){
        new MaterialAlertDialogBuilder(this)
                .setTitle("Respuesta")
                .setMessage(message)
                .show();
    }

    public void backMainActivity(View view) {
        onBackPressed();
        finish();
    }
}