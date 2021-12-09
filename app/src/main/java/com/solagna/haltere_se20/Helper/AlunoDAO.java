package com.solagna.haltere_se20.Helper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.solagna.haltere_se20.Data.DataBase;
import com.solagna.haltere_se20.MainController;
import com.solagna.haltere_se20.Model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO implements BaseDAO{
    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public AlunoDAO(Context context) {
        //DataBase = new DataBase( context );
        escreve = MainController.db.getWritableDatabase();
        le = MainController.db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Object obj) {
        Aluno aln= (Aluno) obj;

        ContentValues cv = new ContentValues();
        cv.put("nome", aln.getNome() );

        try {
            escreve.insert(DataBase.TABELA_ALUNOS, null, cv );
            Log.i("INFO", "Aluno salvo com sucesso!");
        }catch (Exception e){
            Log.e("INFO", "Erro ao salvar aluno " + e.getMessage() );
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(Object obj) {
        Aluno aln= (Aluno) obj;

        ContentValues cv = new ContentValues();
        cv.put("nome ", aln.getNome() );

        try {
            String[] args = {aln.getCpf().toString()};
            escreve.update(DataBase.TABELA_ALUNOS, cv, "CPF=? ", args);
            Log.i("INFO", "Aluno atualizado com sucesso!");
        }catch (Exception e){
            Log.e("INFO", "Erro ao atualizar aluno " + e.getMessage() );
            return false;
        }
        return true;
    }

    @Override
    public boolean deletar(Object obj) {
        Aluno aln= (Aluno) obj;

        try {
            String[] args = {aln.getCpf().toString()};
            escreve.delete(DataBase.TABELA_ALUNOS, "CPF=?", args );
            Log.i("INFO", "Aluno removida com sucesso!");
        }catch (Exception e){
            Log.e("INFO", "Erro ao remover aluno " + e.getMessage() );
            return false;
        }
    return true;

    }

    @Override
    public List<Object> listar() {
        List<Aluno> alunos = new ArrayList<>();

        String sql = "SELECT * FROM " + DataBase.TABELA_ALUNOS + " ;";
        Cursor c = le.rawQuery(sql, null);
/*
        while ( c.moveToNext() ){

            Aluno aluno = new Aluno();

            Long id = c.getLong( c.getColumnIndex("id") );
            String nomeTarefa = c.getString( c.getColumnIndex("nome") );

            tarefa.setId( id );
            tarefa.setNomeTarefa( nomeTarefa );

            tarefas.add( tarefa );
            Log.i("tarefaDao", tarefa.getNomeTarefa() );
        }

        return tarefas;
      */
        return null;
    }
}