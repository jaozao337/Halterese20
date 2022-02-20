package com.solagna.haltere_se20.Helper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.solagna.haltere_se20.Data.DataBase;
import com.solagna.haltere_se20.MainController;
import com.solagna.haltere_se20.Model.Exercicio;
import com.solagna.haltere_se20.Model.Treino;

import java.util.ArrayList;
import java.util.List;

public class TreinoDAO implements BaseDAO{
    private SQLiteDatabase escreve;
    private SQLiteDatabase le;
    private DatabaseReference bancoDeDados;

    public TreinoDAO(Context context) {
        //DataBase = new DataBase( context );
        bancoDeDados = FirebaseDatabase.getInstance().getReference().child("Servicos");
    }

    @Override
    public boolean salvar(Object obj) {
        Treino ex= (Treino) obj;
        if(ex != null) {
            /*nome, peso, tipo,  repeticoes,  series, descricao, duracao*/
            String mGroupId = bancoDeDados.child("Treinos").push().getKey();
            ex.setId(mGroupId);
            bancoDeDados.child("Treinos").child(mGroupId).setValue(ex);
            return true;
        }else{
            return false;
        }
    }

    public boolean validar(Object obj){
        Treino ex= (Treino) obj;
        if(ex != null) {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean atualizar(Object obj) {
        Treino ex= (Treino) obj;
        DatabaseReference treinos = bancoDeDados.child("Treinos");
        Query pesquisaTreinos = treinos.orderByChild("id").equalTo(ex.getId());
        pesquisaTreinos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null){
                    List<Treino> lista= new ArrayList<>();
                    for(DataSnapshot dt: snapshot.getChildren()){
                        Treino a =  dt.getValue(Treino.class);
                        a.setId(dt.getKey());
                        lista.add(a);
                    }
                    treinos.child(lista.get(0).getId()).setValue(ex);
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
        Treino aln= (Treino) obj;
        DatabaseReference treinos= bancoDeDados.child("Treinos");
        Query pesquisaTreinos = treinos.orderByChild("id").equalTo(aln.getId());
        pesquisaTreinos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null){
                    List<Exercicio> listaExercicio= new ArrayList<>();
                    for(DataSnapshot dt: snapshot.getChildren()){
                        Exercicio a = dt.getValue(Exercicio.class);
                        listaExercicio.add(a);
                    }
                    treinos.child(listaExercicio.get(0).getId()).removeValue();
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

    List<Treino> treinoList = new ArrayList<>();

    public List<Treino> listarTreinos() {
        DatabaseReference treinoDB = bancoDeDados.child("Treinos");
        Query pesquisaTreino = treinoDB.orderByChild("id");
        pesquisaTreino.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null){
                    for(DataSnapshot dt: snapshot.getChildren()){
                        Treino a = dt.getValue(Treino.class);
                        treinoList.add(a);
                    }
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return treinoList;
    }

    public List<Treino> buscar(String busca) {
        List<Treino> treinos = new ArrayList<>();

        String sql = "SELECT * FROM " + DataBase.TABELA_TREINOS + " WHERE id LIKE '%" + busca + "%' ;";
        Cursor c = le.rawQuery(sql, null);

        while (c.moveToNext()) {

            Treino treino = new Treino();
            String nomeTreino = c.getString(c.getColumnIndex("nome treino"));
            String descricaoTreino = c.getString(c.getColumnIndex("descricao treino"));
            int duracaoTreino = Integer.parseInt(c.getString(c.getColumnIndex("duracao treino")));

            treino.setNomeTreino(nomeTreino);
            treino.setDescricaoTreino(descricaoTreino);
            treino.setDuracaoTreino(duracaoTreino);

            treinos.add(treino);
            Log.i("TreinoDAO", treino.getNomeTreino());
        }

        return treinos;
    }
}
