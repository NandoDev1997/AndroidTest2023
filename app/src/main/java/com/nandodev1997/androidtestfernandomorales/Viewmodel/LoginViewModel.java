package com.nandodev1997.androidtestfernandomorales.Viewmodel;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    private SharedPreferences sharedPreferences;

    public void init(Context context) {
        sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
    }

    public boolean isLoggedIn() {
        return sharedPreferences.contains("username");
    }

    public void saveUsername(String username) {
        sharedPreferences.edit().putString("username", username).apply();
    }

    public boolean Login(String username) {
        String data = sharedPreferences.getString("username","");
        if(username.equals(data)){
            return true;
        }
        return false;
    }
}