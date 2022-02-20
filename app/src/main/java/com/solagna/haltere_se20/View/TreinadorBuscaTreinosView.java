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
    private Button btCadastrarTreino, btBuscarTreino;
    private EditText Tnome;
    private RecyclerView recyclerView;
    private List<Treino> listaTreinos = new ArrayList<Treino>();
    private DatabaseReference bancoDeDados;

    public TreinadorBuscaTreinosView() {

    }

    private  void criarListeners(){
        botaoCadastrar();
        recicladorListener();
        botaoBuscar();
    }

    private void botaoCadastrar(){
        btCadastrarTreino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CriaTreinosView.class);
                intent.putExtra("Titulo", getString(R.string.TITULO_TREINADOR_CADASTRANDO));
                startActivityForResult(intent,0);
            }
        });
    }

    public void botaoBuscar(){
        btBuscarTreino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String busca = Tnome.getText().toString();
                listaTreinos.clear();
                procurarAluno(busca);
            }
        });
    }

    private void procurarAluno(String busca){
        DatabaseReference db= bancoDeDados.child("Treinos");
        Query pesquisaAluno = db.orderByChild("nomeTreino").equalTo(busca);
        pesquisaAluno.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null){
                    for(DataSnapshot dt: snapshot.getChildren()){
                        Treino t =  dt.getValue(Treino.class);
                        listaTreinos.add(t);
                    }
                }
                reciclador();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bancoDeDados = FirebaseDatabase.getInstance().getReference().child("Servicos");
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.tela_treinador_busca_treinos);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        Tnome = findViewById(R.id.ptBuscarExercicio);
        btCadastrarTreino = findViewById(R.id.btTelaAdicionarTreinos);
        btBuscarTreino = findViewById(R.id.btBuscarTreino);
        reciclador();
        criarListeners();
        popular();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        popular();
    }

    protected void popular(){
        listaTreinos.clear();
        DatabaseReference treinoDB = bancoDeDados.child("Treinos");
        Query pesquisaTreino = treinoDB.orderByChild("id");
        pesquisaTreino.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null){
                    for(DataSnapshot dt: snapshot.getChildren()){
                        Treino a = dt.getValue(Treino.class);
                        listaTreinos.add(a);
                        reciclador();
                    }
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    public void reciclador() {
        //pega os dados e joga pro adp.
        recyclerView = findViewById(R.id.rvListarTreinos);
        adapterListaTreino adaptador = new adapterListaTreino(listaTreinos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(adaptador);



    }

    public void recicladorListener(){
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
        Intent alterarTreino = new Intent(getApplicationContext(), CriaTreinosView.class);
        alterarTreino.putExtra("Titulo", getString(R.string.TITULO_TREINADOR_CRIA_TREINO));
        alterarTreino.putExtra("nomeTreino", tr.getNomeTreino());
        alterarTreino.putExtra("duracaoTreino", tr.getDuracaoTreino());
        alterarTreino.putExtra("descricaoTreino", tr.getDescricaoTreino());
        System.out.println("aaaaaaaaaaaaaaaas" + tr.getId());
        alterarTreino.putExtra("id", tr.getId());
        startActivityForResult(alterarTreino,0);
    }
}
