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

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.solagna.haltere_se20.Controller.ExercicioController;
import com.solagna.haltere_se20.Controller.TreinoController;
import com.solagna.haltere_se20.R;

public class CriaExercicioView extends AppCompatActivity {
    private Button btAdicionarExercicio;
    private Chip cardio, muscular;
    private String nome, tipo, descricao;
    private int duracao, peso, repeticoes, series;
    private ExercicioController ec;
    EditText Tnome, Tdescricao, Tduracao, Tpeso, Trepeticoes, Tseries;
    private String titulo;
    /*nome, peso, tipo,  repeticoes,  series, descricao, duracao*/

    private void criarListeners() {
        btAdicionarExercicio();
        ec = new ExercicioController(getApplicationContext());
    }


    private boolean getDados() {
        Tnome = findViewById(R.id.ptNomeExercicio);
        nome = Tnome.getText().toString();

        Tdescricao = findViewById(R.id.ptDescricaoExercicio);
        descricao = Tdescricao.getText().toString();

        Tpeso = findViewById(R.id.ptPesoExercicio);
        if (!Tpeso.getText().toString().equals("")) {
            peso = Integer.parseInt(Tpeso.getText().toString());
        }

        Tduracao = findViewById(R.id.ptTempoExercicio);
        if (!Tduracao.getText().toString().equals("")) {
            duracao = Integer.parseInt(Tduracao.getText().toString());
        }

        Tseries = findViewById(R.id.ptSerieExercicio);
        if (!Tseries.getText().toString().equals("")) {
            series = Integer.parseInt(Tseries.getText().toString());
        }

        Trepeticoes = findViewById(R.id.ptRepeticoesExercicio);
        if (!Trepeticoes.getText().toString().equals("")) {
            repeticoes = Integer.parseInt(Trepeticoes.getText().toString());
        }

        cardio= findViewById(R.id.cpTipoCard);
        muscular= findViewById(R.id.cpTipoMusc);
        if(cardio.isChecked()){
            tipo = "Exercício Cardiovascular";
        }else{
            tipo = "Exercício Muscular";
        }

        if (nome.equals("") || descricao.equals("")
                || Tduracao.getText().toString().equals("")
                || Trepeticoes.getText().toString().equals("")
                || Tseries.getText().toString().equals("")
                || Tpeso.getText().toString().equals("")
                || tipo.equals("")) {
            //toast mandando preencher tudo
            Toast toast = Toast.makeText(getApplicationContext(), "Algum Campo Esta Sem Dados!", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        } else {
            return true;
        }
    }

    private void btAdicionarExercicio() {
        btAdicionarExercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast ee = Toast.makeText(getApplicationContext(), "aoba", Toast.LENGTH_SHORT);
                ee.show();
                if (getDados()) {
                    if (ec.cadastrarExercicio( nome,tipo,peso,repeticoes,series,descricao, duracao)) {
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
        //rever urgente
        Bundle b = new Bundle();
        b = getIntent().getExtras();
        titulo = b.getString("Titulo");
        TextView t = findViewById(R.id.tvAdicionarExercicio);
        t.setText(titulo);

        if(titulo.equals(getString(R.string.TITULO_TREINADOR_CRIA_EXERCICIO))){
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
        setContentView(R.layout.tela_treinador_cadastra_exercicios);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        btAdicionarExercicio = findViewById(R.id.btCadastrarExercicio);

        //atualizarTitulo();
        criarListeners();
    }


}
