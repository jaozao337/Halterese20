package com.solagna.haltere_se20.View;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.solagna.haltere_se20.Controller.TreinadorController;
import com.solagna.haltere_se20.R;

public class CadastroTreinadorView extends AppCompatActivity {

    private Button btAdicionarTreinador;
    private String nome,cpf,senha,email;
    private int salario;

    private void criarListeners() {

        btAdicionarTreinador();
    }
    private void btAdicionarTreinador() {
        btAdicionarTreinador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nome =  findViewById(R.id.ptAddNomeTreinador).toString();
                email = findViewById(R.id.ptAddEmailTreinador).toString();
                cpf =   findViewById(R.id.ptAddCPFTreinador).toString();
                salario = Integer.parseInt(findViewById(R.id.ptAddEmailTreinador).toString());
                senha =   findViewById(R.id.ptAddSenhaTreinador).toString();

                if(nome.isEmpty()||email.isEmpty()||cpf.isEmpty()||salario<0||senha.isEmpty()){
                    //toast mandando preencher tudo
                    Toast toast = Toast.makeText(getApplicationContext(), "Algum Campo Esta Sem Dados!", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    TreinadorController ec = new TreinadorController();
                    ec.cadastrarTreinador(nome,cpf,senha,email,salario);
                }


            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.tela_treinador_cadastra_treinadores);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        btAdicionarTreinador = findViewById(R.id.btAdicionarTreinador);

        criarListeners();
    }
}