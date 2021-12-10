package com.solagna.haltere_se20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import com.solagna.haltere_se20.Controller.AlunoController;
import com.solagna.haltere_se20.Controller.TreinoController;
import com.solagna.haltere_se20.Data.DataBase;
import com.solagna.haltere_se20.Model.Treino;
import com.solagna.haltere_se20.View.LoginView;
import com.solagna.haltere_se20.View.PrincipalAlunoView;
import com.solagna.haltere_se20.View.TreinadorView;

public class MainController extends AppCompatActivity {

    public static DataBase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setContentView(R.layout.tela_login);
       // LoginView n= new LoginView();
        Intent intent = new Intent(getApplicationContext(), LoginView.class);
        startActivity(intent);
        db = new DataBase( getApplicationContext() );

        AlunoController alunoController = new AlunoController(getApplicationContext());
        alunoController.cadastrarAluno("Gustavo","123","3","gugu@gug","12","lindo",66000,171);
        alunoController.cadastrarAluno("Jose","3233","3245","53425u@gug","11","fea",54223,154);
        TreinoController treinoController = new TreinoController(getApplicationContext());
        treinoController.cadastrarTreino("TreinoTop",100,"show");
        finish();
    }


}