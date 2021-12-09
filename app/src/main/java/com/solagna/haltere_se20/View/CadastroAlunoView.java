package com.solagna.haltere_se20.View;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.solagna.haltere_se20.Controller.AlunoController;
import com.solagna.haltere_se20.R;


public class CadastroAlunoView extends AppCompatActivity {
    private Button btAdicionarAluno;
    private String nome, cpf, senha, email, cargaHoraria, observacoes, peso, altura;
private  AlunoController ec;
    public CadastroAlunoView(){

    }

    private void criarListeners() {

        btAdicionarAluno();
    }

    private boolean getDados() {
        nome = findViewById(R.id.ptAddNomeAluno).toString();
        email = findViewById(R.id.ptAddEmailAluno).toString();
        peso = findViewById(R.id.ptAddPesoAluno).toString();
        altura = findViewById(R.id.ptAddAlturaAluno).toString();
        observacoes = findViewById(R.id.ptAddNecessidadesAluno).toString();
        cpf = findViewById(R.id.ptAddCPFAluno).toString();
        cargaHoraria = findViewById(R.id.ptAddCargaHoraria).toString();
        senha = findViewById(R.id.ptAddSenhaAluno).toString();
        if (nome.isEmpty() || email.isEmpty() || peso.isEmpty() || altura.isEmpty() || observacoes.isEmpty() || cargaHoraria.isEmpty() || cpf.isEmpty() || cargaHoraria.isEmpty() || senha.isEmpty()) {
            //toast mandando preencher tudo
            Toast toast = Toast.makeText(getApplicationContext(), "Algum Campo Esta Sem Dados!", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        } else {
            return true;
        }
    }

    private void btAdicionarAluno() {
        btAdicionarAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (getDados()) {
                    if (ec.cadastrarAluno(nome, cpf, senha, email, cargaHoraria, observacoes, peso, altura)) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Salvo com sucesso", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Houve um problema ao salvar", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }


            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.tela_treinador_cadastra_alunos);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        btAdicionarAluno = findViewById(R.id.btAdicionarAluno);

        ec = new AlunoController(getApplicationContext());
        criarListeners();
    }


}