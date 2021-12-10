package com.solagna.haltere_se20.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.solagna.haltere_se20.Controller.AlunoController;
import com.solagna.haltere_se20.R;


public class CriaEditaAlunoView extends AppCompatActivity {
    private Button btAdicionarAluno,btExcluirAluno;
    private String nome, cpf, senha, email, cargaHoraria, observacoes;
    private int peso, altura;
    private AlunoController ec;
    EditText Tnome, Tcpf, Tsenha, Temail, TcargaHoraria, Tobservacoes, Tpeso, Taltura;
    private String titulo;

    //caso seja da tela de cadastro
    public CriaEditaAlunoView() {
    }



    private void criarListeners() {

        btAdicionarAluno();
        btExcluirAluno();
        ec = new AlunoController(getApplicationContext());
    }

    private boolean getDados() {
        Tnome = findViewById(R.id.ptNomeAluno);
        nome = Tnome.getText().toString();

        Temail = findViewById(R.id.ptEmailAluno);
        email = Temail.getText().toString();

        Tpeso = findViewById(R.id.ptPesoAluno);
        if (!Tpeso.getText().toString().equals("")) {
            peso = Integer.parseInt(Tpeso.getText().toString());
        }
        Taltura = findViewById(R.id.ptAlturaAluno);
        if (!Taltura.getText().toString().equals("")) {
            altura = Integer.parseInt(Taltura.getText().toString());
        }
        Tobservacoes = findViewById(R.id.ptNecessidadesAluno);
        observacoes = Tobservacoes.getText().toString();

        Tcpf = findViewById(R.id.ptCPFAluno);
        cpf = Tcpf.getText().toString();

        TcargaHoraria = findViewById(R.id.ptCargaHoraria);
        cargaHoraria = TcargaHoraria.getText().toString();

        Tsenha = findViewById(R.id.ptSenhaAluno);
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

    private void btExcluirAluno() {
        btExcluirAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //exclui
            }
        });
    }
    private void atualizarTitulo(){
        Bundle b = new Bundle();
        b = getIntent().getExtras();
        titulo = b.getString("Titulo");
        TextView t = findViewById(R.id.tituloTelaCadastroAluno);
        t.setText(titulo);
        btExcluirAluno = findViewById(R.id.btExcluirAluno);
        if(titulo.equals(getString(R.string.TITULO_TREINADOR_MODIFICANDO))){
           editandoAluno(b);
        }
    }

    private void editandoAluno(Bundle bundle){
        btExcluirAluno.setVisibility(View.VISIBLE);

        EditText nomeAluno=  findViewById(R.id.ptNomeAluno);
       nomeAluno.setText(bundle.getString("nome"));

        EditText email=  findViewById(R.id.ptEmailAluno);
        email.setText(bundle.getString("email"));

        EditText peso=  findViewById(R.id.ptPesoAluno);
        peso.setText(
                (Integer.parseInt(bundle.getString("peso"))));

        EditText altura=  findViewById(R.id.ptAlturaAluno);
        altura.setText(
                (Integer.parseInt(bundle.getString("altura"))));

        EditText observacoes=  findViewById(R.id.ptNecessidadesAluno);
        observacoes.setText(bundle.getString("observacoes"));

        EditText cpf=  findViewById(R.id.ptCPFAluno);
        cpf.setText(bundle.getString("cpf"));

        EditText cargah=  findViewById(R.id.ptCargaHoraria);
        cargah.setText(bundle.getString("cargaHoraria"));

        EditText senha=  findViewById(R.id.ptSenhaAluno);
        senha.setText(bundle.getString("senha"));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.tela_cria_edita_aluno);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        btAdicionarAluno = findViewById(R.id.btAdicionarAluno);

       atualizarTitulo();


        criarListeners();
    }


}