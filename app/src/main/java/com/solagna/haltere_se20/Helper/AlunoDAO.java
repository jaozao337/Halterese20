package com.solagna.haltere_se20.Helper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.solagna.haltere_se20.Data.DataBase;
import com.solagna.haltere_se20.MainController;
import com.solagna.haltere_se20.Model.Aluno;
import com.solagna.haltere_se20.View.CriaEditaAlunoView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class AlunoDAO implements BaseDAO{
    private SQLiteDatabase escreve;
    private SQLiteDatabase le;
    private DatabaseReference bancoDeDados;
    private FirebaseAuth auth;
    private Context contextt;

    public AlunoDAO(Context context) {
        auth = FirebaseAuth.getInstance();
        bancoDeDados = FirebaseDatabase.getInstance().getReference().child("Pessoas");
        //DataBase = new DataBase( context );
        contextt = context;
    }

    @Override
    public boolean salvar(Object obj) {
        Aluno aln= (Aluno) obj;
        if(aln != null){
            return true;
        }else{
            return false;
        }
    }

    private void tira(String id){
        DatabaseReference aluno =bancoDeDados.child(id);
        aluno.removeValue();
        /*
        y.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.i("DATABASE","deletou o id: "+id);
            }
        });*/
    }

    @Override
    public boolean atualizar(Object obj) {
        Aluno aln= (Aluno) obj;
        DatabaseReference alunos= bancoDeDados.child("Alunos");
        Query pesquisaAluno = alunos.orderByChild("cpf").equalTo(aln.getCpf());
        pesquisaAluno.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null){
                    List<Aluno> listaAlunos= new ArrayList<>();
                    for(DataSnapshot dt: snapshot.getChildren()){
                        Aluno a =  dt.getValue(Aluno.class);
                        a.setId(dt.getKey());
                        listaAlunos.add(a);
                    }
                    alunos.child(listaAlunos.get(0).getId()).setValue(aln);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return true;
    }

    @Override
    public boolean deletar(Object obj) {

        Aluno aln= (Aluno) obj;
        DatabaseReference alunos= bancoDeDados.child("Alunos");
        Query pesquisaAluno = alunos.orderByChild("cpf").equalTo(aln.getCpf());
        pesquisaAluno.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null){
                    List<Aluno> listaAlunos= new ArrayList<>();
                    for(DataSnapshot dt: snapshot.getChildren()){
                       Aluno a =  dt.getValue(Aluno.class);
                     listaAlunos.add(a);
                    }
                  alunos.child(listaAlunos.get(0).getId()).removeValue();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
return true;
    }

    @Override
    public List<Object> listar() {
        return null;
    }


    public List<Aluno> listarAlunos() {
        alunos.clear();

        DatabaseReference alunosdb= bancoDeDados.child("Alunos");
        Query pesquisaAluno = alunosdb.orderByChild("cpf");
        pesquisaAluno.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null){
                    for(DataSnapshot dt: snapshot.getChildren()){
                        Aluno a =  dt.getValue(Aluno.class);
                        alunos.add(a);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return alunos;

    }

    public List<Aluno> buscarAlunoNome(String nomeBusca) {
    alunos.clear();

        DatabaseReference alunosdb= bancoDeDados.child("Alunos");
        Query pesquisaAluno = alunosdb.orderByChild("nome").equalTo(nomeBusca);
        pesquisaAluno.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null){
                    for(DataSnapshot dt: snapshot.getChildren()){
                        Aluno a =  dt.getValue(Aluno.class);
                        alunos.add(a);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return alunos;
    }

    private List<Aluno> alunos = new ArrayList<>();


    public Aluno validarLoginAluno(String login, String senha) {

    alunos.clear();
        DatabaseReference alunosdb= bancoDeDados.child("Alunos");
        Query pesquisaAluno = alunosdb.orderByChild("email").equalTo(login);
        pesquisaAluno.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null){
                    for(DataSnapshot dt: snapshot.getChildren()){
                        Aluno a =  dt.getValue(Aluno.class);
                        if(a.getSenha().equals(senha)){
                            alunos.add(a);

                        }
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
      //  Log.i("DATABASE", alunos.get(0).toString());
        if(alunos.size()!=0){
            Log.i("DATABASE", alunos.size()+" <= size ");
            return alunos.get(0);
        }else{
            return null;
        }
    }


}