package com.solagna.haltere_se20.View;

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

import com.solagna.haltere_se20.Controller.TreinadorController;
import com.solagna.haltere_se20.Model.Treinador;
import com.solagna.haltere_se20.R;

public class CriaEditaTreinadorView extends AppCompatActivity {
    private Button btAdicionarTreinador;
    private String nome, cpf, senha, email;
    private int salario;
    private TreinadorController ec;
    EditText Tnome, Tcpf, Tsenha, Temail, Tsalario;
    private String titulo;


    private void criarListeners() {

        btAdicionarTreinador();
        ec = new TreinadorController(getApplicationContext());
    }

    private boolean getDados() {
        Tnome = findViewById(R.id.ptAddNomeTreinador);
        nome = Tnome.getText().toString();

        Temail = findViewById(R.id.ptAddEmailTreinador);
        email = Temail.getText().toString();

        Tsalario = findViewById(R.id.ptAddSalarioTreinador);
        if (!Tsalario.getText().toString().equals("")) {
            salario = Integer.parseInt(Tsalario.getText().toString()); }

        Tcpf = findViewById(R.id.ptAddCPFTreinador);
        cpf = Tcpf.getText().toString();

        Tsenha = findViewById(R.id.ptAddSalarioTreinador);
        senha = Tsenha.getText().toString();

        if (nome.equals("") || email.equals("")
                || Tsalario.getText().toString().equals("")
                || cpf.equals("")
                || senha.equals("")) {
            //toast mandando preencher tudo
            Toast toast = Toast.makeText(getApplicationContext(), "Algum Campo Esta Sem Dados!", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        } else {
            return true;
        }
    }

    private void btAdicionarTreinador() {
        btAdicionarTreinador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getDados()) {
                    if (ec.cadastrarTreinador(nome, cpf, senha, email, salario)) {
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


    private void atualizarTitulo(){
        Bundle b = new Bundle();
        b = getIntent().getExtras();
        titulo = b.getString("Titulo");
        TextView t = findViewById(R.id.tituloTelaCadastroAluno);
        t.setText(titulo);

        if(titulo.equals(getString(R.string.TITULO_TREINADOR_MODIFICANDO))){
            //editandoAluno(b);
        }
    }

    private void editandoTreinador(Bundle bundle){

        EditText nomeTreinador=  findViewById(R.id.ptNomeTreinador);
        nomeTreinador.setText(bundle.getString("nome"));

        EditText email=  findViewById(R.id.ptEmailTreinador);
        email.setText(bundle.getString("email"));

        EditText salario=  findViewById(R.id.ptSalarioTreinador);
        salario.setText(
                (Integer.parseInt(bundle.getString("peso"))));

        EditText cpf=  findViewById(R.id.ptCPFTreinador);
        cpf.setText(bundle.getString("cpf"));

        EditText senha=  findViewById(R.id.ptSenhaTreinador);
        senha.setText(bundle.getString("senha"));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.tela_treinador_cadastra_treinadores);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        btAdicionarTreinador = findViewById(R.id.btAdicionarTreinador);

        //atualizarTitulo();
        criarListeners();
    }


}
