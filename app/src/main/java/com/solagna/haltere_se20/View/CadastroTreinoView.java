package com.solagna.haltere_se20.View;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.solagna.haltere_se20.Controller.TreinoController;
import com.solagna.haltere_se20.R;

public class CadastroTreinoView extends AppCompatActivity{

        private Button btAdicionarTreino;
        private String nomeTreino,descricaoTreino;
        private int duracaoTreino;
        private void criarListeners() {

            btAdicionarTreino();
        }
        private void btAdicionarTreino() {
            btAdicionarTreino.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    nomeTreino =  findViewById(R.id.ptAddNomeTreino).toString();
                    duracaoTreino = Integer.parseInt(findViewById(R.id.ptAddDuracaoTreino).toString());
                    descricaoTreino =   findViewById(R.id.ptAddDescricaoTreino).toString();



                    if(nomeTreino.isEmpty()||duracaoTreino<0||descricaoTreino.isEmpty()){
                        //toast mandando preencher tudo
                        Toast toast = Toast.makeText(getApplicationContext(), "Algum Campo Esta Sem Dados!", Toast.LENGTH_SHORT);
                        toast.show();
                    }else{
                        TreinoController ec = new TreinoController();
                        ec.cadastrarTreino(nomeTreino,duracaoTreino,descricaoTreino);
                    }


                }
            });
        }



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.tela_treinador_cadastra_treinos);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            btAdicionarTreino = findViewById(R.id.btAdicionarTreino);

            criarListeners();
        }
    }

