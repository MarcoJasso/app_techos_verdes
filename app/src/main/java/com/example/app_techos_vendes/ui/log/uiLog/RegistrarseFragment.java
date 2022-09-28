package com.example.app_techos_vendes.ui.log.uiLog;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.app_techos_vendes.R;
import com.example.app_techos_vendes.adapters.AdaptadorCardPlantasRV;
import com.example.app_techos_vendes.apimodelsresponse.ApiResponseModel;
import com.example.app_techos_vendes.consumeapi.ApiService;
import com.example.app_techos_vendes.databinding.FragmentLoginBinding;
import com.example.app_techos_vendes.databinding.FragmentRegistrarseBinding;
import com.example.app_techos_vendes.models.CardPlantasModel;
import com.example.app_techos_vendes.models.UsuarioModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.jetbrains.annotations.Contract;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrarseFragment extends Fragment implements View.OnClickListener {

    private RegistrarseViewModel mViewModel;
    private FragmentRegistrarseBinding binding;
    private Gson gson = new Gson();

    private EditText edtNombreUsuario;
    private EditText edtNombre;
    private EditText edtApellidos;
    private EditText edtPassword;
    private EditText edtConfPassword;
    private EditText edtCorreo;

    private RadioButton RbtnHombre;
    private RadioButton RbtnMujer;

    private Button btnGuardarRegistro;
    private Button btnCancelarRegistro;

    private RadioGroup radioGroup;

    private View root;

    public static RegistrarseFragment newInstance() {
        return new RegistrarseFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentRegistrarseBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        initComponents();

        return root;
    }

    private void initComponents() {

        btnGuardarRegistro = binding.btnGuardarRegistro;
        btnCancelarRegistro = binding.btnCancelar;

        edtNombreUsuario = binding.edtNombreUsuario;
        edtNombre = binding.edtNombre;
        edtApellidos = binding.edtApellidos;
        edtPassword = binding.edtPasswordR;
        edtConfPassword = binding.edtPasswordRvalidar;
        edtCorreo = binding.edtCorreo;

        RbtnMujer = binding.radioButton2;
        RbtnHombre = binding.radioButton1;

        btnGuardarRegistro.setOnClickListener(this);

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

    private void showMessageResponse(Context context, String message) {
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {

        String data = getDataForm(view);

        if (data != null) {

            Context context = view.getContext();

            addNewUserApi(context, data);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getDataForm(View view) {

        String uNombre = edtNombre.getText().toString();
        String uNombreUsr = edtNombreUsuario.getText().toString();
        String uApellidos = edtApellidos.getText().toString();
        String uPassword = edtPassword.getText().toString();
        String uConfPass = edtConfPassword.getText().toString();
        String uCorreo = edtCorreo.getText().toString();
        String uSexo = "0";

        if (RbtnMujer.isChecked()) {
            uSexo = "1";
        }

        if (!uNombre.equals("")) {
            if (!uNombreUsr.equals("")) {
                if (!uApellidos.equals("")) {
                    if (!uPassword.equals("")) {
                        if (!uConfPass.equals("")) {
                            if (!uCorreo.equals("")) {
                                if (uPassword.equals(uConfPass)) {

                                    try {
                                        UsuarioModel usuarioModel = new UsuarioModel(
                                                uNombreUsr,
                                                uNombre,
                                                uApellidos,
                                                uSexo,
                                                uConfPass,
                                                uCorreo);

                                        return "users/insert/" + Base64.getEncoder().encodeToString(gson.toJson(usuarioModel).getBytes());
                                    } catch (Exception e) {
                                        showMessageResponse(view.getContext(), "Error en el formulario \n" + e.getMessage());
                                    }
                                } else {
                                    showMessageResponse(view.getContext(), "Las contraseñas no coinciden.");
                                }
                            } else {
                                showMessageResponse(view.getContext(), "Es necesario un correo.");
                            }
                        } else {
                            showMessageResponse(view.getContext(), "Es necesario confirmar la contraseña.");
                        }
                    } else {
                        showMessageResponse(view.getContext(), "Es necesario ingresar una contraseña.");
                    }
                } else {
                    showMessageResponse(view.getContext(), "Faltan datos en el campo apellidos.");
                }
            } else {
                showMessageResponse(view.getContext(), "Faltan datos en el campo Nombre.");
            }
        } else {
            showMessageResponse(view.getContext(), "Es necesario un nombre de usuario");
        }
        return null;
    }

    private void clearObjets() {
        edtNombre.setText("");
        edtNombreUsuario.setText("");
        edtApellidos.setText("");
        edtPassword.setText("");
        edtConfPassword.setText("");
        edtCorreo.setText("");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void addNewUserApi(Context context, String item) {

        try {

            ApiService apiService = getRetrofit().create(ApiService.class);

            Call call = apiService.addNewUser(item);


            call.enqueue(new Callback<ApiResponseModel>() {
                @Override
                public void onResponse(Call<ApiResponseModel> call, Response<ApiResponseModel> response) {

                    if (response.isSuccessful()) {

                        response.body();

                        if (response.body().getError() == 1) {

                            showMessageResponse(context,  response.body().getMessage());

                        } else {
                            clearObjets();
                            showMessageResponse(context, response.body().getMessage());
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

}