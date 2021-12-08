package com.solagna.haltere_se20.View;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.solagna.haltere_se20.Controller.ExercicioController;
import com.solagna.haltere_se20.R;

public class CadastroExerciciosView extends AppCompatActivity {
    private Button btCadastrarExercicio;
    private String nome,peso,repeticoes,series,descricao,duracao,tipo;

    private void criarListeners() {
        botaoCadastrarExercicios();
    }

    private void botaoCadastrarExercicios() {
        btCadastrarExercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nome =  findViewById(R.id.ptNomeExercicio).toString();
                peso =  findViewById(R.id.ptPesoExercicio).toString();
                repeticoes =  findViewById(R.id.ptRepeticoesExercicio).toString();
                series=  findViewById(R.id.ptSerieExercicio).toString();
                descricao =  findViewById(R.id.ptDescricaoExercicio).toString();
                duracao =   findViewById(R.id.ptTempoExercicio).toString();


                if(nome.isEmpty()||peso.isEmpty()||repeticoes.isEmpty()||series.isEmpty()||descricao.isEmpty()||duracao.isEmpty()){
                    //toast mandando preencher tudo
                }else{
                    ExercicioController ec = new ExercicioController();
                    ec.cadastrarExercicio(nome,tipo, peso,repeticoes,series,descricao,duracao);
                }


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

        btCadastrarExercicio = findViewById(R.id.btCadastrarExercicio);


        criarListeners();
    }


}