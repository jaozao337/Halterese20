package com.solagna.haltere_se20.View;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.solagna.haltere_se20.Adapter.adapterListaAluno;
import com.solagna.haltere_se20.Adapter.adapterListarExercicioTreino;
import com.solagna.haltere_se20.Controller.AlunoController;
import com.solagna.haltere_se20.Controller.ExercicioController;
import com.solagna.haltere_se20.Controller.TreinoController;
import com.solagna.haltere_se20.Helper.RecyclerItemClickListener;
import com.solagna.haltere_se20.Model.Aluno;
import com.solagna.haltere_se20.Model.Exercicio;
import com.solagna.haltere_se20.R;

import java.util.ArrayList;
import java.util.List;

public class CriaExercicioView extends AppCompatActivity {
    private Button btAdicionarExercicio;
    private Chip cardio, muscular;
    private String nome, tipo, descricao;
    private int duracao, peso, repeticoes, series;
    private ExercicioController ec;
    EditText Tnome, Tdescricao, Tduracao, Tpeso, Trepeticoes, Tseries;
    private String titulo;
    /*nome, peso, tipo,  repeticoes,  series, descricao, duracao*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.tela_treinador_cadastra_exercicios);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        btAdicionarExercicio = findViewById(R.id.btCadastrarExercicio);
        setTexts();
        atualizarTitulo();

        criarListeners();
    }

    private void criarListeners() {
        btAdicionarExercicio();
        ec = new ExercicioController(getApplicationContext());
    }

    private void setTexts(){
        Tnome = findViewById(R.id.ptNomeExercicio);
        Tdescricao = findViewById(R.id.ptDescricaoExercicio);
        Tpeso = findViewById(R.id.ptPesoExercicio);
        Tduracao = findViewById(R.id.ptTempoExercicio);
        Tseries = findViewById(R.id.ptSerieExercicio);
        Trepeticoes = findViewById(R.id.ptRepeticoesExercicio);
        cardio= findViewById(R.id.cpTipoMusc);
        muscular= findViewById(R.id.cpTipoCard);
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

        cardio= findViewById(R.id.cpTipoMusc);
        muscular= findViewById(R.id.cpTipoCard);
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

                if (getDados()) {
                    Toast ee = Toast.makeText(getApplicationContext(), "aoba", Toast.LENGTH_SHORT);
                    ee.show();
                    if (ec.cadastrarExercicio( nome,tipo,peso,repeticoes,series,descricao,duracao)) {
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
            //editandoTreino(b);
        }
        if(titulo.equals(getString(R.string.TITULO_ALUNO_VISUALIZA_EXERCICIO))){
            visualizandoExercicio(b);
        }
    }

    private void visualizandoExercicio(Bundle bundle){
        btAdicionarExercicio.setVisibility(View.GONE);
        Tnome.setText(bundle.getString("nome"));
        Tnome.setEnabled(false);
        Tdescricao.setText(bundle.getString("descricao"));
        Tdescricao.setEnabled(false);
        Tpeso.setText(Integer.toString(bundle.getInt("peso")));
        Tpeso.setEnabled(false);
        Tduracao.setText(Integer.toString(bundle.getInt("duracao")));
        Tduracao.setEnabled(false);
        Trepeticoes.setText(Integer.toString(bundle.getInt("repeticao")));
        Trepeticoes.setEnabled(false);
        Tseries.setText(Integer.toString(bundle.getInt("series")));
        Tseries.setEnabled(false);
        String tipo = bundle.getString("tipo");
        if(tipo.equals("Exercício Cardiovascular")){
            cardio.setChecked(true);
            muscular.setChecked(false);
            muscular.setEnabled(false);
            cardio.setEnabled(false);
        }else{
            muscular.setChecked(true);
            cardio.setChecked(false);
            muscular.setEnabled(false);
            cardio.setChecked(false);
        }
    }

    private void editandoExercicio(Bundle bundle){
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

}
