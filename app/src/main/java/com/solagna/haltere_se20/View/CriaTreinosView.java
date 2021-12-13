package com.solagna.haltere_se20.View;

import android.content.Intent;
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

import com.solagna.haltere_se20.Adapter.adapterListarExercicioTreino;
import com.solagna.haltere_se20.Controller.AlunoController;
import com.solagna.haltere_se20.Controller.ExercicioController;
import com.solagna.haltere_se20.Controller.TreinadorController;
import com.solagna.haltere_se20.Controller.TreinoController;
import com.solagna.haltere_se20.Controller.TreinoExercicioController;
import com.solagna.haltere_se20.Helper.RecyclerItemClickListener;
import com.solagna.haltere_se20.Model.Exercicio;
import com.solagna.haltere_se20.Model.Treino;
import com.solagna.haltere_se20.R;

import java.util.ArrayList;
import java.util.List;

public class CriaTreinosView extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Exercicio> listaExercicio = new ArrayList<Exercicio>(), selecionados = new ArrayList<Exercicio>();
    private Button btAdicionarTreino, btTelaExercicio;
    private String nomeTreino, descricaoTreino, y, z;
    private int duracaoTreino, x;
    private TreinoController ec;
    private TreinoExercicioController tec;
    EditText TnomeTreino, TdescricaoTreino, TduracaoTreino;
    private String titulo;

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
        reciclador();
    }

    private void criarListeners() {
        botaoExercicios();
        btAdicionarTreino();

        ec = new TreinoController(getApplicationContext());
    }

    public void reciclador() {
        //pega os dados e joga pro adp.

        ExercicioController exercicioController = new ExercicioController(getApplicationContext());
        listaExercicio = exercicioController.listarExercicio();


        recyclerView = findViewById(R.id.recyclerView);
        adapterListarExercicioTreino adaptador = new adapterListarExercicioTreino(listaExercicio);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(adaptador);


        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        atualizaLista(listaExercicio.get(position));
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        atualizaLista(listaExercicio.get(position));
                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                })
        );
    }

    public void atualizaLista(Exercicio s) {
        if (!selecionados.remove(s)) {
            selecionados.add(s);
        }

        x = 0;
        y = "";
        z = "";
        for (int i = 0; i < selecionados.size(); i++) {
            Exercicio ss = selecionados.get(i);
            if (i + 1 == selecionados.size()) {
                z = z + ss.getId() + ",";
                y = y + ss.getNome();

            } else {
                z = z + ss.getId();
                y = y + ss.getNome() + ",";

            }
        }

        TextView newTreino = findViewById(R.id.tvExerciciosTreino);
        if(y.equals("")){y="Escolha pelo menos um exercicios";}
        newTreino.setText(y);
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
                        //if(criaConexaoExercicioTreino()) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Salvo com sucesso", Toast.LENGTH_SHORT);
                            toast.show();
                            finish();
                        //}
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Houve um problema ao salvar", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });
    }

    private boolean criaConexaoExercicioTreino(){
        String[] listaTreExe = z.split(",");
        List<Treino> listaTreino = new ArrayList<Treino>();
        listaTreino = ec.listarTreinos();
        Long idTreino = 0l;
        for(Treino t:listaTreino){
            if(t.getNomeTreino()==nomeTreino && t.getDuracaoTreino()==duracaoTreino && t.getDescricaoTreino() == descricaoTreino){
                idTreino = t.getId();
            }
        }

        for (String s : listaTreExe){
            return tec.cadastrarTreinoExercicio(Integer.parseInt(""+idTreino), Integer.parseInt(s));
        }
        return false;
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

}
