package com.solagna.haltere_se20.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import androidx.appcompat.app.*;

import com.solagna.haltere_se20.Controller.LoginController;
import com.solagna.haltere_se20.Helper.AlunoDAO;
import com.solagna.haltere_se20.Model.Aluno;
import com.solagna.haltere_se20.R;

public class LoginView extends AppCompatActivity {
    private Button btLogin, btSouTreinador,btCadastrar;
    private String login, senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.tela_login);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

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
                finish();
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
                LoginController lc = new LoginController(getApplicationContext());
                   if (lc.realizarLoginAluno(login, senha)) {
                       Intent intent = new Intent(getApplicationContext(), PrincipalAlunoView.class);
                       startActivity(intent);
                       Toast.makeText(LoginView.this, "Logou Com Sucesso", Toast.LENGTH_SHORT).show();
                       finish();
                   }else if(lc.realizarLoginTreinador(login, senha)) {
                           Intent intent = new Intent(getApplicationContext(), TreinadorView.class);
                           startActivity(intent);
                           Toast.makeText(LoginView.this, "Logou Com Sucesso", Toast.LENGTH_SHORT).show();
                           finish();
                   }else{
                       Toast.makeText(LoginView.this, "Usuário ou senha inválido", Toast.LENGTH_SHORT).show();
                   }
               }
            }
        });
    }




}
