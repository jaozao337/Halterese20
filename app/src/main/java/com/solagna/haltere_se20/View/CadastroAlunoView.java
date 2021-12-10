package com.solagna.haltere_se20.View;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.solagna.haltere_se20.Controller.AlunoController;
import com.solagna.haltere_se20.R;


public class CadastroAlunoView extends AppCompatActivity {
    private Button btAdicionarAluno;
    private String nome, cpf, senha, email, cargaHoraria, observacoes;
    private int peso, altura;
    private  AlunoController ec;
    EditText Tnome, Tcpf, Tsenha, Temail, TcargaHoraria, Tobservacoes, Tpeso, Taltura;
    public CadastroAlunoView(){

    }

    private void criarListeners() {

        btAdicionarAluno();
    }

    private boolean getDados() {
        Tnome = findViewById(R.id.ptAddNomeAluno);
        nome = Tnome.getText().toString();

        Temail = findViewById(R.id.ptAddEmailAluno);
        email = Temail.getText().toString();

        Tpeso = findViewById(R.id.ptAddPesoAluno);
        if(!Tpeso.getText().toString().equals("")) {
            peso = Integer.parseInt(Tpeso.getText().toString());
        }
        Taltura = findViewById(R.id.ptAddAlturaAluno);
        if(!Taltura.getText().toString().equals("")) {
            altura = Integer.parseInt(Taltura.getText().toString());
        }
        Tobservacoes = findViewById(R.id.ptAddNecessidadesAluno);
        observacoes = Tobservacoes.getText().toString();

        Tcpf = findViewById(R.id.ptAddCPFAluno);
        cpf = Tcpf.getText().toString();

        TcargaHoraria = findViewById(R.id.ptAddCargaHoraria);
        cargaHoraria = TcargaHoraria.getText().toString();

        Tsenha = findViewById(R.id.ptAddSenhaAluno);
        senha = Tsenha.getText().toString();

        if (nome.equals("") || email.equals("") || Tpeso.getText().toString().equals("") || Taltura.getText().toString().equals("") || observacoes.equals("") || cargaHoraria.equals("") || cpf.equals("") || cargaHoraria.equals("") || senha.equals("")) {
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
                        finish();
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