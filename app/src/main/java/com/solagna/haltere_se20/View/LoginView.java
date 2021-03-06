package com.solagna.haltere_se20.View;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.*;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.installations.FirebaseInstallations;
import com.google.firebase.installations.InstallationTokenResult;
import com.solagna.haltere_se20.Controller.LoginController;
import com.solagna.haltere_se20.Data.DataBase;
import com.solagna.haltere_se20.Helper.AlunoDAO;
import com.solagna.haltere_se20.Model.Aluno;
import com.solagna.haltere_se20.R;

public class LoginView extends AppCompatActivity {
    private Button btLogin, btSouTreinador,btCadastrar;
    private String login, senha;
    private FirebaseAuth mAuth;
    private DatabaseReference bancoDeDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bancoDeDados = FirebaseDatabase.getInstance().getReference().child("Pessoas");
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.tela_login);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        FirebaseInstallations.getInstance().getToken(false).addOnCompleteListener(new OnCompleteListener<InstallationTokenResult>() {
            @Override
            public void onComplete(@NonNull Task<InstallationTokenResult> task) {
                if(!task.isSuccessful()){
                    return;
                }
                String token = task.getResult().getToken();
                System.out.println("the token is: "+ token);

            }
        });

        btLogin = findViewById(R.id.btEntrar);
        btSouTreinador=findViewById(R.id.btEntrarTreinador);
        btCadastrar=findViewById(R.id.btCadastrar);
        criarListeners();
    }

    private void criarListeners(){
        botaoLogin();
        botaoCadastrar();
        botaoEntrarTreinador();
    }
    private void botaoCadastrar(){
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CriaEditaAlunoView.class);
                intent.putExtra("Titulo", getString(R.string.TITULO_ALUNO_CADASTRANDO));
                startActivity(intent);
             //   finish();
            }
        });
    }


private void botaoEntrarTreinador(){
    btSouTreinador.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
           Intent intent = new Intent(getApplicationContext(), TreinadorView.class);
           startActivity(intent);
           finish();
        }
    });
}

    private boolean getCampos(){
        EditText edtLogin = findViewById(R.id.inputNome);
        EditText edtSenha = findViewById(R.id.inputSenha);
         login = edtLogin.getText().toString();
         senha = edtSenha.getText().toString();
         if(login.isEmpty()||senha.isEmpty()){
             Toast.makeText(LoginView.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
             return false;
         }else{
            return true;
         }
    }
    public void botaoLogin(){
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //aqui ele tem que mandar pro controller pra mandar pro back
               if(getCampos()) {
                   mAuth.signInWithEmailAndPassword(login, senha)
                           .addOnCompleteListener(LoginView.this, new OnCompleteListener<AuthResult>() {
                               @Override
                               public void onComplete(@NonNull Task<AuthResult> task) {
                                   if (task.isSuccessful()) {
                                       Query clientePesquisa = bancoDeDados.child("Alunos").orderByChild("email").equalTo(login);
                                       clientePesquisa.addValueEventListener(new ValueEventListener() {
                                           @Override
                                           public void onDataChange(@NonNull DataSnapshot snapshot) {
                                               for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                                   Intent clienteTela = new Intent(getApplicationContext(), PrincipalAlunoView.class);
                                                   clienteTela.putExtra("email", login);
                                                   clienteTela.putExtra("cpf", postSnapshot.getValue(Aluno.class).getCpf());
                                                   clienteTela.putExtra("nome", postSnapshot.getValue(Aluno.class).getNome());
                                                   startActivity(clienteTela);
                                                   Snackbar snackbar = Snackbar.make(view, "Login concluido com sucesso", Snackbar.LENGTH_SHORT);
                                                   snackbar.setBackgroundTint(Color.BLACK);
                                                   snackbar.setTextColor(Color.WHITE);
                                                   snackbar.show();
                                                   finish();
                                               }
                                           }

                                           @Override
                                           public void onCancelled(@NonNull DatabaseError error) {
                                           }
                                       });
                                       Query FuncionarioPesquisa = bancoDeDados.child("Treinadores").orderByChild("email").equalTo(login);
                                       FuncionarioPesquisa.addValueEventListener(new ValueEventListener() {
                                           @Override
                                           public void onDataChange(@NonNull DataSnapshot snapshot) {
                                               for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                                   Intent intent = new Intent(getApplicationContext(), TreinadorView.class);
                                                   startActivity(intent);
                                                   Snackbar snackbar = Snackbar.make(view, "Login concluido com sucesso", Snackbar.LENGTH_SHORT);
                                                   snackbar.setBackgroundTint(Color.BLACK);
                                                   snackbar.setTextColor(Color.WHITE);
                                                   snackbar.show();
                                                   finish();
                                               }
                                           }

                                           @Override
                                           public void onCancelled(@NonNull DatabaseError error) {
                                               Log.i("teste", "teste");
                                           }
                                       });
                                   } else {
                                       String erro;
                                       try {
                                           throw task.getException();
                                       } catch(FirebaseAuthInvalidCredentialsException e) {
                                           erro = "usuario ou senha invalidos";
                                       } catch(Exception e){
                                           erro = "erro desconhecido";
                                       }

                                       Snackbar snackbar = Snackbar.make(view, erro, Snackbar.LENGTH_SHORT);
                                       snackbar.setBackgroundTint(Color.BLACK);
                                       snackbar.setTextColor(Color.WHITE);
                                       snackbar.show();
                                   }
                               }
                           });
               }
            }
        });
    }


    }
