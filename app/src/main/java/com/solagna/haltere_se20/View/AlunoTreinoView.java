package com.solagna.haltere_se20.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.solagna.haltere_se20.Adapter.adapterListaTreino;
import com.solagna.haltere_se20.Adapter.adapterListarExercicioTreino;
import com.solagna.haltere_se20.Controller.ExercicioController;
import com.solagna.haltere_se20.Controller.TreinoController;
import com.solagna.haltere_se20.Controller.TreinoExercicioController;
import com.solagna.haltere_se20.Helper.RecyclerItemClickListener;
import com.solagna.haltere_se20.Model.Exercicio;
import com.solagna.haltere_se20.Model.Treino;
import com.solagna.haltere_se20.Model.TreinoAluno;
import com.solagna.haltere_se20.Model.TreinoExercicio;
import com.solagna.haltere_se20.R;

import java.util.ArrayList;
import java.util.List;

public class AlunoTreinoView extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Treino> listaTreinos = new ArrayList<Treino>(), selecionados = new ArrayList<Treino>();
    private Button btAddTreinoAluno;
    private String nome, cpf, y, z;
    private int x;
    private TreinoController ec;
    private String titulo;
    private DatabaseReference bancoDeDados, alunoDB;
    String[] listaTreAl;
    String idTreino;
    boolean semTreinos = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bancoDeDados = FirebaseDatabase.getInstance().getReference().child("Servicos");
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_aluno_treino_view);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        btAddTreinoAluno = findViewById(R.id.btAdicionarTreinoAluno);
        criarListeners();
        popular();
        atualizarTitulo();
    }

    private void criarListeners() {
        btAddTreinoAluno();
        ec = new TreinoController(getApplicationContext());
    }

    private void popular(){
        DatabaseReference exercicioDB= bancoDeDados.child("Treinos");
        Query pesquisaTreino = exercicioDB.orderByChild("id");
        pesquisaTreino.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null){
                    for(DataSnapshot dt: snapshot.getChildren()){
                        Treino a = dt.getValue(Treino.class);
                        listaTreinos.add(a);
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
        recyclerView = findViewById(R.id.recyclerViewTreinoAluno);
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
        if (!selecionados.remove(s)) {
            selecionados.add(s);
        }

        x = 0;
        y = "";
        z = "";
        for (int i = 0; i < selecionados.size(); i++) {
            Treino ss = selecionados.get(i);
            if (i + 1 == selecionados.size()) {
                z = z + ss.getId();
                y = y + ss.getNomeTreino();

            } else {
                z = z + ss.getId() + ",";
                y = y + ss.getNomeTreino() + ",";

            }
        }

        TextView newTreino = findViewById(R.id.tvTreinoLista);
        if(y.equals("")){y="Escolha pelo menos um treino";}
        newTreino.setText(y);
    }

    private void btAddTreinoAluno() {
        btAddTreinoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(semTreinos){
                    popularTreinos();
                    Toast toast = Toast.makeText(getApplicationContext(), "Salvo com sucesso", Toast.LENGTH_SHORT);
                    toast.show();
                    z = "";
                    finish();
                }else {
                    repopularTreinos();
                    Toast toast = Toast.makeText(getApplicationContext(), "Salvo com sucesso", Toast.LENGTH_SHORT);
                    toast.show();
                    z = "";
                    finish();
                }
            }
        });

    }

    public void popularTreinos(){
        listaTreAl = z.split(",");
        for (String s : listaTreAl){
            TreinoAluno ex = new TreinoAluno(s ,cpf);
            String mGroupId = bancoDeDados.child("AlunoTreino").push().getKey();
            bancoDeDados.child("AlunoTreino").child(mGroupId).setValue(ex);
        }
    }

    public void repopularTreinos(){
        listaTreAl = z.split(",");
        for(String s : listaTreAl){
            DatabaseReference DB = bancoDeDados.child("AlunoTreino");
            Query procuraTreino = DB.orderByChild("cpf").equalTo(cpf);
            procuraTreino.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.getValue()!=null){
                        boolean continua = false;
                        for(DataSnapshot dt: snapshot.getChildren()){
                            TreinoAluno a = dt.getValue(TreinoAluno.class);
                            if(a.getTreinoID().equals(s)) {
                                continua = true;
                            }
                        }
                        if(!continua){
                            TreinoAluno ex = new TreinoAluno(s ,cpf);
                            String mGroupId = bancoDeDados.child("AlunoTreino").push().getKey();
                            bancoDeDados.child("AlunoTreino").child(mGroupId).setValue(ex);
                        }
                        for(DataSnapshot dt: snapshot.getChildren()){
                            boolean deleta = true;
                            for (String s : listaTreAl){
                                TreinoAluno a = dt.getValue(TreinoAluno.class);
                                if(a.getTreinoID().equals(s)) {
                                    deleta = false;
                                }
                            }
                            if(deleta){
                                DB.child(dt.getKey()).removeValue();
                            }
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private void atualizarTitulo(){
        //TITULO ERRADO AMIGO
        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        TextView t = findViewById(R.id.tvNomeAlunoTreino);
        t.setText(bundle.getString("nome"));
        nome = bundle.getString("nome");
        cpf = bundle.getString("cpf");
        populaExercicioEdit(cpf);

    }

    private void populaExercicioEdit(String cpf){
        DatabaseReference DB = bancoDeDados.child("AlunoTreino");
        Query procuraTreino = DB.orderByChild("cpf").equalTo(cpf);
        List<String> l = new ArrayList<String>();
        procuraTreino.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null){
                    for(DataSnapshot dt: snapshot.getChildren()){
                        TreinoAluno a = dt.getValue(TreinoAluno.class);
                        l.add(a.getTreinoID());
                    }
                }
                populaRecicleEdit(l);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void populaRecicleEdit(List<String> l){
        for(String key : l) {
            for (Treino t : listaTreinos){
                if(t.getId().equals(key)){
                    atualizaLista(t);
                }
            }
        }
        if(selecionados.size()==0){
            semTreinos = true;
        }
    }

}