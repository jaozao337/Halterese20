package com.solagna.haltere_se20.View;

import android.content.Intent;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.solagna.haltere_se20.Adapter.adapterListaAluno;
import com.solagna.haltere_se20.Controller.AlunoController;
import com.solagna.haltere_se20.Helper.RecyclerItemClickListener;
import com.solagna.haltere_se20.Model.Aluno;
import com.solagna.haltere_se20.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TreinadorBuscaAlunosView extends AppCompatActivity {

    //o recycler view
    private Button btCadastrarAluno, btBuscarAluno;
    private RecyclerView recyclerView;
    private List<Aluno> listaAlunos = new ArrayList<Aluno>();
    private adapterListaAluno adaptador;
    private EditText Tnome;
    private DatabaseReference bancoDeDados;

    private  void criarListeners(){
        botaoCadastrar();
        botaoBuscar();
        recicladorListener();
    }

    private void botaoBuscar(){
        btBuscarAluno.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String busca = Tnome.getText().toString();
                AlunoController alunoController = new AlunoController(getApplicationContext());
                listaAlunos.clear();
                procurarAluno(busca);

            }
        });
    }

    private void procurarAluno(String busca){
        listaAlunos.clear();

        DatabaseReference alunosdb= bancoDeDados.child("Alunos");
        Query pesquisaAluno = alunosdb.orderByChild("nome").equalTo(busca);
        pesquisaAluno.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null){
                    for(DataSnapshot dt: snapshot.getChildren()){
                        Aluno a =  dt.getValue(Aluno.class);
                        listaAlunos.add(a);
                    }
                }
                adaptador = new adapterListaAluno(listaAlunos);
                recyclerView.setAdapter(adaptador);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void botaoCadastrar(){
        btCadastrarAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CriaEditaAlunoView.class);
                intent.putExtra("Titulo", getString(R.string.TITULO_TREINADOR_CADASTRANDO));
                startActivityForResult(intent,0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        popular();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bancoDeDados = FirebaseDatabase.getInstance().getReference().child("Pessoas");
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.tela_treinador_busca_alunos);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        btCadastrarAluno = findViewById(R.id.btTelaAdicionarAluno);
        btBuscarAluno = findViewById(R.id.btBuscarAluno);
        Tnome = findViewById(R.id.ptExibirNomeAluno);
        reciclador();
        criarListeners();
        popular();
    }
    protected void popular(){
        listaAlunos.clear();

        DatabaseReference alunosdb= bancoDeDados.child("Alunos");
        Query pesquisaAluno = alunosdb.orderByChild("cpf");
        pesquisaAluno.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null){
                    for(DataSnapshot dt: snapshot.getChildren()){
                        Aluno a =  dt.getValue(Aluno.class);
                        listaAlunos.add(a);
                    }
                }
                reciclador();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void reciclador() {
        //pega os dados e joga pro adp.
        recyclerView = findViewById(R.id.rvListarAlunos);
        adaptador = new adapterListaAluno(listaAlunos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.removeAllViews();
        recyclerView.setAdapter(adaptador);
    }

    public void recicladorListener(){
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
        Intent alteraAluno = new Intent(getApplicationContext(), CriaEditaAlunoView.class);
        alteraAluno.putExtra("Titulo", getString(R.string.TITULO_TREINADOR_MODIFICANDO));
        alteraAluno.putExtra("nome", a.getNome());
        alteraAluno.putExtra("email", a.getEmail());
        alteraAluno.putExtra("cpf", a.getCpf());
        alteraAluno.putExtra("senha", a.getSenha());
        alteraAluno.putExtra("cargaHoraria", a.getCargaHoraria());
        alteraAluno.putExtra("observacoes", a.getObservacoes());
        alteraAluno.putExtra("peso", ""+a.getPeso());
        alteraAluno.putExtra("altura", ""+a.getAltura());
        startActivityForResult(alteraAluno,0);
    }
}
