package com.nandodev1997.androidtestfernandomorales;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.nandodev1997.androidtestfernandomorales.databinding.FragmentLoginBinding;
import com.nandodev1997.androidtestfernandomorales.Viewmodel.LoginViewModel;

public class LoginFragment extends Fragment {
    NavController navController;
    FragmentLoginBinding binding;
    AlertDialog.Builder alertDialog;
    LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(binding.getRoot());


        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModel.init(getActivity());

        binding.btnInitSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValidInput()) {
                    checkLogin();
                }
            }
        });
    }

    private void checkLogin() {
        if (!loginViewModel.isLoggedIn()) {
            loginViewModel.saveUsername( binding.inputUsername.getText().toString());
            alertDialog = new AlertDialog.Builder(getActivity());
            alertDialog.setTitle("Atencion");
            alertDialog.setMessage("El usuario se ha agregado");
            alertDialog.setPositiveButton("Aceptar", (dialogInterface, i) -> {
                dialogInterface.cancel();
            });
            alertDialog.show();
        } else {
            if(loginViewModel.Login(binding.inputUsername.getText().toString())){
                navController.navigate(R.id.homeFragment);
                Toast.makeText(getActivity(), "Inicio exitoso", Toast.LENGTH_SHORT).show();
            }else{
                alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setTitle("Atencion");
                alertDialog.setMessage("El usuario no corresponde con el ingresado anteriormente");
                alertDialog.setPositiveButton("Aceptar", (dialogInterface, i) -> {
                    dialogInterface.cancel();
                });
                alertDialog.show();
            }
        }
    }

    private boolean isValidInput() {
        binding.layoutInputUsername.setError(null);
        if(binding.inputUsername.getText().toString() == "" || binding.inputUsername.getText().toString().isEmpty()){
            binding.layoutInputUsername.setError("El usuario es requerido");
            return false;
        }else {
            return true;
        }
    }
}