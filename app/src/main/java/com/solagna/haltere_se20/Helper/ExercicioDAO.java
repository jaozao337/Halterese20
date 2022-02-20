package com.solagna.haltere_se20.Helper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.solagna.haltere_se20.Data.DataBase;
import com.solagna.haltere_se20.MainController;
import com.solagna.haltere_se20.Model.Aluno;
import com.solagna.haltere_se20.Model.Exercicio;
import com.solagna.haltere_se20.View.CriaEditaAlunoView;

import java.util.ArrayList;
import java.util.List;

public class ExercicioDAO implements BaseDAO{
    private SQLiteDatabase escreve;
    private SQLiteDatabase le;
    private DatabaseReference bancoDeDados;
    private FirebaseAuth auth;

    public ExercicioDAO(Context context) {
        //DataBase = new DataBase( context );
        auth = FirebaseAuth.getInstance();
        bancoDeDados = FirebaseDatabase.getInstance().getReference().child("Servicos");
        escreve = MainController.db.getWritableDatabase();
        le = MainController.db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Object obj) {
        Exercicio ex= (Exercicio) obj;
        if(ex != null) {
            /*nome, peso, tipo,  repeticoes,  series, descricao, duracao*/
            String mGroupId = bancoDeDados.child("Exercicios").push().getKey();
            ex.setId(mGroupId);
            bancoDeDados.child("Exercicios").child(mGroupId).setValue(ex);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean atualizar(Object obj) {
        Exercicio ex= (Exercicio) obj;
        DatabaseReference exercicios = bancoDeDados.child("Exercicios");
        Query pesquisaExercicio = exercicios.orderByChild("id").equalTo(ex.getId());
        pesquisaExercicio.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null){
                    List<Exercicio> lista= new ArrayList<>();
                    for(DataSnapshot dt: snapshot.getChildren()){
                        Exercicio a =  dt.getValue(Exercicio.class);
                        lista.add(a);
                    }
                    exercicios.child(lista.get(0).getId()).setValue(ex);
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
        Exercicio aln= (Exercicio) obj;
        DatabaseReference exercicios= bancoDeDados.child("Exercicios");
        Query pesquisaExercicio = exercicios.orderByChild("id").equalTo(aln.getId());
        pesquisaExercicio.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null){
                    List<Exercicio> listaExercicio= new ArrayList<>();
                    for(DataSnapshot dt: snapshot.getChildren()){
                        Exercicio a =  dt.getValue(Exercicio.class);
                        listaExercicio.add(a);
                    }
                    exercicios.child(listaExercicio.get(0).getId()).removeValue();
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



    public List<Exercicio> listarExercicios() {
        DatabaseReference exercicioDB= bancoDeDados.child("Exercicios");
        Query pesquisaExercicio = exercicioDB.orderByChild("id");
        pesquisaExercicio.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null){
                    for(DataSnapshot dt: snapshot.getChildren()){
                        Exercicio a = dt.getValue(Exercicio.class);
                        exerciciosList.add(a);
                        System.out.println("aaaaaaaa" + exerciciosList.size());
                    }
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return exerciciosList;
    }

    List<Exercicio> exerciciosList = new ArrayList<>();

    public List<Exercicio> buscar(String busca) {
        List<Exercicio> exercicios = new ArrayList<>();

        String sql = "SELECT * FROM " + DataBase.TABELA_EXERCICIOS + " WHERE id LIKE '%" + busca + "%' ;";
        Cursor c = le.rawQuery(sql, null);

        while (c.moveToNext()) {

            Exercicio exercicio = new Exercicio();

            /*nome, peso, tipo,  repeticoes,  series, descricao, duracao*/
            //Long id = c.getLong( c.getColumnIndex("id") );
            String nome = c.getString(c.getColumnIndex("nome"));
            String tipo = c.getString(c.getColumnIndex("tipo"));
            String descricao = c.getString(c.getColumnIndex("descricao"));
            int duracao = Integer.parseInt(c.getString(c.getColumnIndex("duracao")));
            int series = Integer.parseInt(c.getString(c.getColumnIndex("series")));
            int peso = Integer.parseInt(c.getString(c.getColumnIndex("peso")));
            int repeticoes = Integer.parseInt(c.getString(c.getColumnIndex("repeticoes")));

            /*/*nome, cpf, senha, email, cargaHoraria, observacoes, peso, altura */
            //exercicio.setId( id );
            exercicio.setNome(nome);
            exercicio.setTipo(tipo);
            exercicio.setDescricao(descricao);
            exercicio.setDuracao(duracao);
            exercicio.setSeries(series);
            exercicio.setPeso(peso);
            exercicio.setRepeticoes(repeticoes);


            exercicios.add(exercicio);
            Log.i("ExercicioDAO", exercicio.getNome());
        }

        return exercicios;
    }

    }