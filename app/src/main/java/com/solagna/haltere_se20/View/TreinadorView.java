package com.solagna.haltere_se20.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.*;
import android.widget.*;

import androidx.appcompat.app.*;

import com.solagna.haltere_se20.Controller.TreinadorController;
import com.solagna.haltere_se20.R;

public class TreinadorView extends AppCompatActivity {
    private Button btAlunos, btTreinos, btCadastrarTreinador, btModificarTreinador;
    private TreinadorController treinadorController;

    public TreinadorView() {
        this.treinadorController = new TreinadorController();
    }


    private void criarListeners() {
        botaoAlunos();
        botaoTreinos();
        botaoCadastrarTreinador();
        botaoModificarTreinador();
    }

    private void botaoAlunos() {
        btAlunos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //

            }
        });
    }

    private void botaoTreinos() {
        btTreinos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //

            }
        });
    }

    private void botaoCadastrarTreinador() {
        btCadastrarTreinador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //

            }
        });
    }

    private void botaoModificarTreinador() {
        btModificarTreinador.setOnClickListener(new View.OnClickListener() {
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
        setContentView(R.layout.tela_principal_treinador);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        btAlunos = findViewById(R.id.btAlunos);
        btTreinos = findViewById(R.id.btTreinos);
        btCadastrarTreinador = findViewById(R.id.btCadastrarNovoTreinador);
        btModificarTreinador = findViewById(R.id.btModificarTreinador);


        criarListeners();
    }


}
