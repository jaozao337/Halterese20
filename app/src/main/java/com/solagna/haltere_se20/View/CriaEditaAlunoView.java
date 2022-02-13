package com.solagna.haltere_se20.View;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.solagna.haltere_se20.Controller.AlunoController;
import com.solagna.haltere_se20.Model.Aluno;
import com.solagna.haltere_se20.R;


public class CriaEditaAlunoView extends AppCompatActivity {
    private Button btAdicionarAluno,btExcluirAluno;
    private String nome, cpf, senha, email, cargaHoraria, observacoes, titulo;
    private int peso, altura;
    private AlunoController ec;
    EditText Tnome, Tcpf, Tsenha, Temail, TcargaHoraria, Tobservacoes, Tpeso, Taltura;
    private FirebaseAuth auth;
    private DatabaseReference bancoDeDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        auth = FirebaseAuth.getInstance();
        bancoDeDados = FirebaseDatabase.getInstance().getReference().child("Pessoas");
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.tela_cria_edita_aluno);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setTexts();

        btAdicionarAluno = findViewById(R.id.btAdicionarAluno);

        atualizarTitulo();
        criarListeners();
    }

    //caso seja da tela de cadastro
    public CriaEditaAlunoView() {
    }

    private void setTexts(){
        Tnome = findViewById(R.id.ptNomeAluno);
        Temail = findViewById(R.id.ptEmailAluno);
        Tpeso = findViewById(R.id.ptPesoAluno);
        Taltura = findViewById(R.id.ptAlturaAluno);
        Tobservacoes = findViewById(R.id.ptNecessidadesAluno);
        Tcpf = findViewById(R.id.ptCPFAluno);
        TcargaHoraria = findViewById(R.id.ptCargaHoraria);
        Tsenha = findViewById(R.id.ptSenhaAluno);
    }

    private void criarListeners() {

        btAdicionarAluno();
        btExcluirAluno();
        ec = new AlunoController(getApplicationContext());
    }

    private boolean getDados() {

        nome = Tnome.getText().toString();
        email = Temail.getText().toString();
        if (!Tpeso.getText().toString().equals("")) {
            peso = Integer.parseInt(Tpeso.getText().toString());
        }
        if (!Taltura.getText().toString().equals("")) {
            altura = Integer.parseInt(Taltura.getText().toString());
        }
        observacoes = Tobservacoes.getText().toString();
        cpf = Tcpf.getText().toString();
        cargaHoraria = TcargaHoraria.getText().toString();
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
                    if(titulo.equals(getString(R.string.TITULO_TREINADOR_MODIFICANDO))){
                        if (ec.atualizarAluno(nome, cpf, senha, email, cargaHoraria, observacoes, peso, altura)) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Atualizado com sucesso", Toast.LENGTH_SHORT);
                            toast.show();
                            finish();
                        }
                        else {
                            Toast toast = Toast.makeText(getApplicationContext(), "Houve um problema ao atualizar", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }else {
                        if (ec.cadastrarAluno(nome, cpf, senha, email, cargaHoraria, observacoes, peso, altura)) {
                            auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Aluno aln = new Aluno(nome, cpf, senha, email, cargaHoraria, observacoes, peso, altura);
                                        String mGroupId = bancoDeDados.child("Alunos").push().getKey();
                                        aln.setId(mGroupId);
                                        bancoDeDados.child("Alunos").child(mGroupId).setValue(aln);
                                        Toast toast = Toast.makeText(CriaEditaAlunoView.this, "Salvo com sucesso", Toast.LENGTH_SHORT);
                                        toast.show();
                                        finish();
                                    }else{
                                        String erro;
                                        try {
                                            throw task.getException();
                                        }catch(FirebaseAuthUserCollisionException e) {
                                            erro = "usuario ja registrado";
                                        } catch(FirebaseAuthWeakPasswordException e) {
                                            erro = "senha muito fraca";
                                        } catch(FirebaseAuthInvalidCredentialsException e){
                                            erro = "email invalido";
                                        } catch(Exception e){
                                            erro = "erro desconhecido";
                                        }

                                        Snackbar snackbar = Snackbar.make(view, erro, Snackbar.LENGTH_SHORT);
                                        snackbar.setBackgroundTint(Color.BLACK);
                                        snackbar.setTextColor(Color.WHITE);
                                        snackbar.show();
                                        //Toast toast = Toast.makeText(getApplicationContext(), "Algum campo foi preenchido incorretamente", Toast.LENGTH_SHORT);
                                        //toast.show();
                                        Log.i("adm", "cadastro falhou");
                                    }
                                }
                            });

                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), "Houve um problema ao salvar", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                }
            }
        });
    }

    private void btExcluirAluno() {
        btExcluirAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(titulo.equals(getString(R.string.TITULO_TREINADOR_MODIFICANDO))){
                    if (ec.deletarAluno(nome, cpf, senha, email, cargaHoraria, observacoes, peso, altura)) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Atualizado com sucesso", Toast.LENGTH_SHORT);
                        toast.show();
                        finish();
                    }
                    else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Houve um problema ao atualizar", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
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

        Bundle dados = getIntent().getExtras();
        nome = dados.getString("nome");
        email = dados.getString("email");
        cpf = dados.getString("cpf");
        senha = dados.getString("senha");
        cargaHoraria = dados.getString("cargaHoraria");
        observacoes = dados.getString("observacoes");
        peso = Integer.parseInt(dados.getString("peso"));
        altura = Integer.parseInt(dados.getString("altura"));

        //EditText nomeAluno=  findViewById(R.id.ptNomeAluno);
        Tnome.setText(nome);

        //EditText email=  findViewById(R.id.ptEmailAluno);
        Temail.setText(email);
        Temail.setEnabled(false);

        //EditText peso=  findViewById(R.id.ptPesoAluno);
        Tpeso.setText(Integer.toString(peso));

        //EditText altura=  findViewById(R.id.ptAlturaAluno);
        Taltura.setText(Integer.toString(altura));

        //EditText observacoes=  findViewById(R.id.ptNecessidadesAluno);
        Tobservacoes.setText(bundle.getString("observacoes"));

        //EditText cpf=  findViewById(R.id.ptCPFAluno);
        Tcpf.setText(bundle.getString("cpf"));

        //EditText cargah=  findViewById(R.id.ptCargaHoraria);
        TcargaHoraria.setText(bundle.getString("cargaHoraria"));

        //EditText senha=  findViewById(R.id.ptSenhaAluno);
        Tsenha.setText(bundle.getString("senha"));
        Tsenha.setEnabled(false);

    }

}