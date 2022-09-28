package com.example.app_techos_vendes.ui.log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app_techos_vendes.MainActivity;
import com.example.app_techos_vendes.R;
import com.example.app_techos_vendes.apimodelsresponse.ApiResponseModel;
import com.example.app_techos_vendes.consumeapi.ApiService;
import com.example.app_techos_vendes.databinding.ActivityMainBinding;
import com.example.app_techos_vendes.databinding.ActivityMainLogBinding;
import com.example.app_techos_vendes.databinding.FragmentLoginBinding;
import com.example.app_techos_vendes.databinding.FragmentRegistrarseBinding;
import com.example.app_techos_vendes.models.UsuarioModel;
import com.example.app_techos_vendes.ui.log.uiLog.LoginFragment;
import com.example.app_techos_vendes.ui.log.uiLog.RegistrarseFragment;
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

public class MainLogActivity extends AppCompatActivity {

    private ActivityMainLogBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainLogBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
    }

    public void setOnClick(View view){

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (view.getId()){

            case R.id.btnRegistrar:

                RegistrarseFragment registrarse = new RegistrarseFragment();
                transaction.replace(R.id.contenedor_log, registrarse);
                transaction.commit();

                Toast.makeText(this, "Boton registrarse", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnCancelar:

                LoginFragment loginFragment = new LoginFragment();
                transaction.replace(R.id.contenedor_log, loginFragment);
                transaction.commit();

                Toast.makeText(this, "Se cancelo el registro", Toast.LENGTH_SHORT).show();

                break;

            case R.id.btnGuardarRegistro:

                LoginFragment loginFragment1 = new LoginFragment();
                transaction.replace(R.id.contenedor_log, loginFragment1);
                transaction.commit();

                Toast.makeText(this, "Re gusrdo el registro", Toast.LENGTH_SHORT).show();

                break;
        }
    }


}