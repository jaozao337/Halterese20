package com.solagna.haltere_se20.View;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.solagna.haltere_se20.R;

public class ExercicioView extends AppCompatActivity {
    private Button btAddExercicios;

    private void criarListeners() {

        //botaoExercicios();
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
        setContentView(R.layout.tela_treinador_cadastra_exercicios);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        btAddExercicios = findViewById(R.id.btAdicionarTreino);


        criarListeners();
    }


}
