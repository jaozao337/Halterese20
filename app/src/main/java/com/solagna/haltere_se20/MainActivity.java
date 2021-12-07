package com.solagna.haltere_se20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.solagna.haltere_se20.View.LoginView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //aqui a magica
       // setContentView(R.layout.tela_login);
        LoginView n= new LoginView();

    }


}