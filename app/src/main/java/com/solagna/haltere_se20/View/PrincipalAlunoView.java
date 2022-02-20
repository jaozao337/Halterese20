package com.solagna.haltere_se20.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
import com.solagna.haltere_se20.Adapter.adapterListaTreino;
import com.solagna.haltere_se20.Controller.AlunoController;
import com.solagna.haltere_se20.Controller.TreinoController;
import com.solagna.haltere_se20.Helper.RecyclerItemClickListener;
import com.solagna.haltere_se20.Model.Exercicio;
import com.solagna.haltere_se20.Model.Treinador;
import com.solagna.haltere_se20.Model.Treino;
import com.solagna.haltere_se20.Model.TreinoAluno;
import com.solagna.haltere_se20.R;

import java.util.ArrayList;
import java.util.List;


public class PrincipalAlunoView extends AppCompatActivity {
    private AlunoController alunoController;
    private RecyclerView recyclerView;
    private List<Treino> listaTreinos = new ArrayList<Treino>();
    private List<String> listaId = new ArrayList<>();
    private String nome, email, cpf;
    private DatabaseReference bancoDeDados;
    String[] listaTreAl;
    String idTreino;


        public PrincipalAlunoView() {

        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.tela_aluno_principal);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        bancoDeDados = FirebaseDatabase.getInstance().getReference().child("Servicos");
        this.alunoController = new AlunoController(getApplicationContext());
        atualizarTitulo();
        popular();

    }

    private void popular(){
        DatabaseReference exercicioDB = bancoDeDados.child("AlunoTreino");
        Query pesquisaTreino = exercicioDB.orderByChild("cpf").equalTo(cpf);
        pesquisaTreino.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null){
                    for(DataSnapshot dt: snapshot.getChildren()){
                        TreinoAluno a = dt.getValue(TreinoAluno.class);
                        listaId.add(a.getTreinoID());
                    }
                }
                popularTreinos(listaId);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void popularTreinos(List<String> l){
        DatabaseReference exercicioDB= bancoDeDados.child("Treinos");
        Query pesquisaTreino = exercicioDB.orderByChild("id");
        pesquisaTreino.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null){
                    for(DataSnapshot dt: snapshot.getChildren()){
                        for(String s : l) {
                            Treino a = dt.getValue(Treino.class);
                            if(a.getId().equals(s)) {
                                listaTreinos.add(a);
                            }
                        }
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
        recyclerView = findViewById(R.id.rvCronogramaCompleto);
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
                        atualizaLista(listaTreinos.get(position));
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        atualizaLista(listaTreinos.get(position));
                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                })
        );
    }

    public void atualizaLista(Treino s) {
        Intent alterarTreino = new Intent(getApplicationContext(), CriaTreinosView.class);
        alterarTreino.putExtra("Titulo", getString(R.string.TITULO_ALUNO_VISUALIZA_TREINO));
        alterarTreino.putExtra("nomeTreino", s.getNomeTreino());
        alterarTreino.putExtra("duracaoTreino", s.getDuracaoTreino());
        alterarTreino.putExtra("descricaoTreino", s.getDescricaoTreino());
        alterarTreino.putExtra("id", s.getId());
        startActivity(alterarTreino);
    }

    private void atualizarTitulo(){
        //TITULO ERRADO AMIGO
        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        nome = bundle.getString("nome");
        cpf = bundle.getString("cpf");
        email = bundle.getString("email");
    }


}
