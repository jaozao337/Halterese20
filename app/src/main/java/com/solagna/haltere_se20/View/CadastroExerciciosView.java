package com.solagna.haltere_se20.View;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.solagna.haltere_se20.Controller.ExercicioController;
import com.solagna.haltere_se20.R;

public class CadastroExerciciosView extends AppCompatActivity {
    private Button btCadastrarExercicio;
    private String nome,descricao,tipo;
    private int peso,series,repeticoes, duracao;

    private void criarListeners() {
        botaoCadastrarExercicios();
    }

    private void botaoCadastrarExercicios() {
        btCadastrarExercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nome =  findViewById(R.id.ptNomeExercicio).toString();
                descricao =  findViewById(R.id.ptDescricaoExercicio).toString();
                tipo =  findViewById(R.id.ptTipoExercicio).toString();
                peso = Integer.parseInt(findViewById(R.id.ptPesoExercicio).toString());
                repeticoes = Integer.parseInt(findViewById(R.id.ptRepeticoesExercicio).toString());
                series= Integer.parseInt(findViewById(R.id.ptSerieExercicio).toString());
                duracao= Integer.parseInt(findViewById(R.id.ptTempoExercicio).toString());




                if(nome.isEmpty()||peso<0||repeticoes<0||series<0||descricao.isEmpty()||duracao<0){
                    //toast mandando preencher tudo
                    Toast toast = Toast.makeText(getApplicationContext(), "Algum Campo Esta Sem Dados!", Toast.LENGTH_SHORT);
                    toast.show();
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