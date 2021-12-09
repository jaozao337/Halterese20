package com.solagna.haltere_se20.View;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.solagna.haltere_se20.R;

public class TreinosView extends AppCompatActivity {
    private Button btAddTreinos, btAddExercicios;

    private void criarListeners() {
        botaoTreinos();
        botaoExercicios();
    }

    private void botaoTreinos() {
        btAddTreinos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //

            }
        });
    }

    private void botaoExercicios() {
        btAddExercicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.tela_treinador_busca_exercicios);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        btAddExercicios = findViewById(R.id.btExcluirAluno);
       // btAddTreinos = findViewById(R.id.btAddTreino);

        criarListeners();
    }


}