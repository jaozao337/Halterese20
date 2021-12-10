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

import com.solagna.haltere_se20.Adapter.adapterTreinadorAluno;
import com.solagna.haltere_se20.Controller.AlunoController;
import com.solagna.haltere_se20.Helper.RecyclerItemClickListener;
import com.solagna.haltere_se20.Model.Aluno;
import com.solagna.haltere_se20.R;

import java.util.ArrayList;
import java.util.List;

public class TreinadorBuscaAlunosView extends AppCompatActivity {

    //o recycler view
    private Button btCadastrarAluno;
    private RecyclerView recyclerView;
    private List<Aluno> listaAlunos = new ArrayList<Aluno>();

    public TreinadorBuscaAlunosView() {

    }

    private  void criarListeners(){
        botaoCadastrar();
    }
    private void botaoCadastrar(){
        btCadastrarAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CadastroAlunoView.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.tela_treinador_busca_alunos);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        btCadastrarAluno = findViewById(R.id.btTelaAdicionarAluno);
        criarListeners();
    }
    protected void popular(){
        //popular a lista de alunos
        reciclador();
    }
    public void reciclador() {
        //pega os dados e joga pro adp.

        recyclerView = findViewById(R.id.recyclerView);
        adapterTreinadorAluno adaptador = new adapterTreinadorAluno(listaAlunos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(adaptador);


        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        alterarAluno(listaAlunos.get(position));
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        alterarAluno(listaAlunos.get(position));

                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                })
        );
    }

    public void alterarAluno (Aluno a){
        Intent alterarAluno = new Intent(getApplicationContext(), CadastroAlunoView.class);
        alterarAluno.putExtra("nome", a.getNome());
        alterarAluno.putExtra("email", a.getEmail());
        startActivity(alterarAluno);
    }
}
