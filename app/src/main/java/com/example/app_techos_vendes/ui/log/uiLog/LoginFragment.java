package com.example.app_techos_vendes.ui.log.uiLog;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_techos_vendes.MainActivity;
import com.example.app_techos_vendes.R;
import com.example.app_techos_vendes.apimodelsresponse.ApiResponseModel;
import com.example.app_techos_vendes.consumeapi.ApiService;
import com.example.app_techos_vendes.databinding.FragmentInicioBinding;
import com.example.app_techos_vendes.databinding.FragmentLoginBinding;
import com.example.app_techos_vendes.databinding.FragmentRegistrarseBinding;
import com.example.app_techos_vendes.models.AppContainer;
import com.example.app_techos_vendes.models.UsuarioModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.Contract;

import java.util.Base64;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginFragment extends Fragment implements View.OnClickListener {

    private LoginViewModel mViewModel;
    private FragmentLoginBinding binding;

    private EditText edtUsuario;
    private EditText edtPassword;

    private Button btnIniciarSesion;

    private Gson gson = new Gson();

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initiComponents();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initiComponents(){
        edtUsuario = binding.edtUsuario;
        edtPassword = binding.edtPassword;

        btnIniciarSesion = binding.btnIniciarSesion;
        btnIniciarSesion.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getDataForm(View view) {

        String uUsusario = edtUsuario.getText().toString();
        String uPassword = edtPassword.getText().toString();

        if (!uUsusario.equals("")) {
            if (!uPassword.equals("")) {
                try {
                    UsuarioModel usuarioModel = new UsuarioModel(
                            uUsusario,
                            "uNombreUsr",
                            "uApellidos",
                            "uSexo",
                            uPassword,
                            "uCorreo");

                    return "users/get/" + Base64.getEncoder().encodeToString(gson.toJson(usuarioModel).getBytes());

                } catch (Exception e) {
                    showMessageResponse(view.getContext(), "Error en el formulario \n" + e.getMessage());
                }
            } else {
                showMessageResponse(view.getContext(), "Faltan datos en el campo Nombre.");
            }
        } else {
            showMessageResponse(view.getContext(), "Es necesario un nombre de usuario");
        }
        return null;
    }

    private void showMessageResponse(Context context, String message) {
        new MaterialAlertDialogBuilder(context)
                .setTitle("Respuesta")
                .setMessage(message)
                .show();
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void getLogUser(Context context, String item) {

        try {

            ApiService apiService = getRetrofit().create(ApiService.class);

            Call call = apiService.singIn(item);


            call.enqueue(new Callback<ApiResponseModel>() {
                @Override
                public void onResponse(Call<ApiResponseModel> call, Response<ApiResponseModel> response) {

                    if (response.isSuccessful()) {

                        response.body();

                        if (response.body().getError() == 1) {

                            showMessageResponse(context,  response.body().getMessage());

                        } else {
                            //showMessageResponse(context,  response.body().getMessage());

                            Intent intent = new Intent(context, MainActivity.class);

                            UsuarioModel usuarioModel = gson.fromJson(response.body().getMessage(), UsuarioModel.class);

                            ((AppContainer) context.getApplicationContext()).setValorUsuario(usuarioModel.getId_usuario());

                            intent.putExtra("idUserName", usuarioModel.getId_usuario());
                            intent.putExtra("idNombreUsuario", usuarioModel.getNombre());

                            startActivity(intent);
                            getActivity().finish();

                            Toast.makeText(context, "Successful sesion", Toast.LENGTH_SHORT).show();

                        }

                    } else {
                        showMessageResponse(context, "Error connection.");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    showMessageResponse(context, "Error connection: \n" + t.getMessage());
                }
            });


        } catch (Exception e) {
            showMessageResponse(context, e.getMessage());
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        String data = getDataForm(view);

        if (data != null) {

            Context context = view.getContext();

            getLogUser(context, data);

        }
    }
}