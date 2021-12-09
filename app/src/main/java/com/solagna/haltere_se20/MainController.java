package com.solagna.haltere_se20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


import com.solagna.haltere_se20.Data.DataBase;
import com.solagna.haltere_se20.View.LoginView;
import com.solagna.haltere_se20.View.TreinadorView;

public class MainController extends AppCompatActivity {

    public static DataBase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setContentView(R.layout.tela_login);
        LoginView n= new LoginView();
        Intent intent = new Intent(getApplicationContext(), TreinadorView.class);
        startActivity(intent);
        db = new DataBase( getApplicationContext() );
        finish();
    }


}