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
import com.solagna.haltere_se20.Controller.TreinadorController;
import com.solagna.haltere_se20.Controller.TreinoController;
import com.solagna.haltere_se20.R;

public class CriaTreinosView extends AppCompatActivity {
    private Button btAdicionarTreino, btTelaExercicio;
    private String nomeTreino, descricaoTreino;
    private int duracaoTreino;
    private TreinoController ec;
    EditText TnomeTreino, TdescricaoTreino, TduracaoTreino;
    private String titulo;

    private void criarListeners() {
        botaoExercicios();
        btAdicionarTreino();

        ec = new TreinoController(getApplicationContext());
    }

    private void botaoExercicios() {
        btTelaExercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), CriaExercicioView.class);
                startActivity(intent);
            }
        });
    }

    private boolean getDados() {
        TnomeTreino = findViewById(R.id.ptAddNomeTreino);
        nomeTreino = TnomeTreino.getText().toString();

        TdescricaoTreino = findViewById(R.id.ptAddDescricaoTreino);
        descricaoTreino = TdescricaoTreino.getText().toString();

        TduracaoTreino = findViewById(R.id.ptAddDuracaoTreino);
        if (!TduracaoTreino.getText().toString().equals("")) {
            duracaoTreino = Integer.parseInt(TduracaoTreino.getText().toString());
        }

        if (nomeTreino.equals("") || descricaoTreino.equals("") || TduracaoTreino.getText().toString().equals("")) {
            //toast mandando preencher tudo
            Toast toast = Toast.makeText(getApplicationContext(), "Algum Campo Esta Sem Dados!", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        } else {
            return true;
        }
    }

    private void btAdicionarTreino() {
        btAdicionarTreino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getDados()) {
                    if (ec.cadastrarTreino( nomeTreino, duracaoTreino, descricaoTreino)) {
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
        //TITULO ERRADO AMIGO
        Bundle b = new Bundle();
        b = getIntent().getExtras();
        titulo = b.getString("Titulo");
        TextView t = findViewById(R.id.tvTituloAdicionaTreino);
        t.setText(titulo);

        if(titulo.equals(getString(R.string.TITULO_TREINADOR_CRIA_TREINO))){
            editandoTreino(b);
        }
    }

    private void editandoTreino(Bundle bundle){
     /* btExcluirAluno.setVisibility(View.VISIBLE);

        EditText nomeTreino=  findViewById(R.id.ptNomeAluno);
        nomeAluno.setText(bundle.getString("nome"));

        EditText email=  findViewById(R.id.ptEmailAluno);
        email.setText(bundle.getString("email"));

        EditText peso=  findViewById(R.id.ptPesoAluno);
        peso.setText(
                (Integer.parseInt(bundle.getString("peso"))));

    */
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.tela_treinador_cadastra_treinos);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        btAdicionarTreino = findViewById(R.id.btAdicionarTreino);
        btTelaExercicio = findViewById(R.id.btTelaAdicionarExercicio);

        atualizarTitulo();


        criarListeners();
    }


}
