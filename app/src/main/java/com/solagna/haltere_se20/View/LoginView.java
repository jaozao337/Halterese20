package com.solagna.haltere_se20.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;

import androidx.appcompat.app.*;

import com.solagna.haltere_se20.Controller.LoginController;
import com.solagna.haltere_se20.R;

public class LoginView extends AppCompatActivity {
    private Button btLogin;
    private String login, senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.tela_login);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        btLogin = findViewById(R.id.btEntrar);
        criarListeners();
    }

    private void criarListeners(){
        botaoLogin();
    }

    private boolean getCampos(){
        EditText edtLogin = findViewById(R.id.inputLogin);
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
               if(getCampos()) {
                   if (LoginController.realizarLogin(login, senha)) {
                       Intent intent = new Intent(getApplicationContext(), TreinadorView.class);
                       startActivity(intent);
                       finish();
                   }else {
                       Toast.makeText(LoginView.this, "Usuário ou senha inválido", Toast.LENGTH_SHORT).show();
                   }
               }
            }
        });
    }




}
