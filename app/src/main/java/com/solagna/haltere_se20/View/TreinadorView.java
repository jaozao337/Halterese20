package com.solagna.haltere_se20.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import androidx.appcompat.app.*;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.solagna.haltere_se20.Adapter.adapterListaAluno;
import com.solagna.haltere_se20.Controller.TreinadorController;
import com.solagna.haltere_se20.Model.Aluno;
import com.solagna.haltere_se20.Model.Treinador;
import com.solagna.haltere_se20.R;

public class TreinadorView extends AppCompatActivity {
    private Button btAlunos, btTreinos, btCadastrarTreinador, btModificarTreinador;
    private TreinadorController treinadorController;
    private FirebaseAuth auth;
    private DatabaseReference bancoDeDados;
    private Treinador treinador;
    public TreinadorView() {
        this.treinadorController = new TreinadorController();
    }


    private void criarListeners() {
        botaoAlunos();
        botaoTreinos();
        botaoCadastrarTreinador();
        botaoModificarTreinador();
    }

    private void botaoAlunos() {
        btAlunos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TreinadorBuscaAlunosView.class);
                startActivity(intent);
            }
        });
    }

    private void botaoTreinos() {
        btTreinos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TreinadorBuscaTreinosView.class);
                startActivity(intent);
            }
        });
    }

    private void botaoCadastrarTreinador() {
        btCadastrarTreinador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CriaEditaTreinadorView.class);
                intent.putExtra("titulo", "Adicionar Treinador");
                startActivity(intent);

            }
        });
    }

    private void botaoModificarTreinador() {
        buscarTreinador(auth.getCurrentUser().getEmail());
        btModificarTreinador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(treinador != null) {
                    Intent intent = new Intent(getApplicationContext(), CriaEditaTreinadorView.class);
                    intent.putExtra("titulo", "Alterar Treinador");
                    intent.putExtra("nome", treinador.getNome());
                    intent.putExtra("cpf", treinador.getCpf());
                    intent.putExtra("senha", treinador.getSenha());
                    intent.putExtra("email", treinador.getEmail());
                    intent.putExtra("salario", treinador.getSalario());
                    startActivity(intent);
                }
            }
        });
    }

    public void buscarTreinador(String email){
        DatabaseReference db= bancoDeDados.child("Treinadores");
        Query pesquisaTreinador = db.orderByChild("email").equalTo(email);
        pesquisaTreinador.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null){
                    for(DataSnapshot dt: snapshot.getChildren()){
                        treinador =  dt.getValue(Treinador.class);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.tela_treinador_principal);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        auth = FirebaseAuth.getInstance();
        btAlunos = findViewById(R.id.btAlunos);
        btTreinos = findViewById(R.id.btTreinos);
        btCadastrarTreinador = findViewById(R.id.btCadastrarNovoTreinador);
        btModificarTreinador = findViewById(R.id.btModificarTreinador);
        bancoDeDados = FirebaseDatabase.getInstance().getReference().child("Pessoas");

        criarListeners();
    }


}
