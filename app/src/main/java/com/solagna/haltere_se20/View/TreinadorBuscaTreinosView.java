package com.solagna.haltere_se20.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.solagna.haltere_se20.Adapter.adapterListaAluno;
import com.solagna.haltere_se20.Adapter.adapterListaTreino;
import com.solagna.haltere_se20.Controller.AlunoController;
import com.solagna.haltere_se20.Controller.TreinoController;
import com.solagna.haltere_se20.Helper.RecyclerItemClickListener;
import com.solagna.haltere_se20.Model.Aluno;
import com.solagna.haltere_se20.Model.Treino;
import com.solagna.haltere_se20.R;

import java.util.ArrayList;
import java.util.List;

public class  TreinadorBuscaTreinosView extends AppCompatActivity {
    //o recycler view
    private Button btCadastrarTreino;
    private RecyclerView recyclerView;
    private List<Treino> listaTreinos = new ArrayList<Treino>();

    public TreinadorBuscaTreinosView() {

    }

    private  void criarListeners(){
        botaoCadastrar();
    }

    private void botaoCadastrar(){
        btCadastrarTreino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CriaTreinosView.class);
                intent.putExtra("Titulo", getString(R.string.TITULO_TREINADOR_CADASTRANDO));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.tela_treinador_busca_treinos);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        btCadastrarTreino = findViewById(R.id.btTelaAdicionarTreinos);
        criarListeners();
        popular();
        //reciclador();
    }
    protected void popular(){
        //popular a lista de alunos
        //TreinoController treinoController = new TreinoController(getApplicationContext());
        reciclador();
    }
    public void reciclador() {
        //pega os dados e joga pro adp.

        TreinoController treinoController = new TreinoController(getApplicationContext());
        listaTreinos= treinoController.listarTreinos();


        recyclerView = findViewById(R.id.rvListarTreinos);
        adapterListaTreino adaptador = new adapterListaTreino(listaTreinos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(adaptador);


        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        alterarTreino(listaTreinos.get(position));
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        alterarTreino(listaTreinos.get(position));
                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                })
        );
    }

    public void alterarTreino (Treino tr){
        Intent alterarTreino = new Intent(getApplicationContext(), CriaEditaAlunoView.class);
        alterarTreino.putExtra("Titulo", getString(R.string.TITULO_TREINADOR_CRIA_TREINO));
        alterarTreino.putExtra("nomeTreino", tr.getNomeTreino());
        alterarTreino.putExtra("duracaoTreino", tr.getDuracaoTreino());
        alterarTreino.putExtra("descricaoTreino", tr.getDescricaoTreino());

        startActivity(alterarTreino);
    }
}
