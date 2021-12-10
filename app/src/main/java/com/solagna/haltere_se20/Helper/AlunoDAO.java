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
        cv.put("cpf", aln.getCpf() );
        cv.put("senha", aln.getSenha() );
        cv.put("email", aln.getEmail() );
        cv.put("cargaHoraria", aln.getCargaHoraria() );
        cv.put("observacoes", aln.getObservacoes() );
        cv.put("peso", aln.getPeso() );
        cv.put("altura", aln.getAltura() );

        /*nome, cpf, senha, email, cargaHoraria, observacoes, peso, altura */
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
        cv.put("nome", aln.getNome() );
        cv.put("cpf", aln.getCpf() );
        cv.put("senha", aln.getSenha() );
        cv.put("email", aln.getEmail() );
        cv.put("cargaHoraria", aln.getCargaHoraria() );
        cv.put("observacoes", aln.getObservacoes() );
        cv.put("peso", aln.getPeso() );
        cv.put("altura", aln.getAltura() );

        try {
            String[] args = {aln.getCpf().toString()};
            escreve.update(DataBase.TABELA_ALUNOS, cv, "CPF= ", args);
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
            Log.i("INFO", "Aluno removido com sucesso!");
        }catch (Exception e){
            Log.e("INFO", "Erro ao remover aluno " + e.getMessage() );
            return false;
        }
    return true;

    }

    @Override
    public List<Object> listar() {
        return null;
    }


    public List<Aluno> listarAlunos() {
        List<Aluno> alunos = new ArrayList<>();

        String sql = "SELECT * FROM " + DataBase.TABELA_ALUNOS + " ;";
        Cursor c = le.rawQuery(sql, null);

        while ( c.moveToNext() && c!=null ){

            Aluno aluno = new Aluno();

            Integer id = c.getInt( c.getColumnIndex("id") );

            String nome = c.getString( c.getColumnIndex("nome") );
            String cpf = c.getString( c.getColumnIndex("cpf") );
            String senha = c.getString( c.getColumnIndex("senha") );
            String email = c.getString( c.getColumnIndex("email") );
            String cargaHoraria = c.getString( c.getColumnIndex("cargaHoraria") );
            String observacoes = c.getString( c.getColumnIndex("observacoes") );
            int peso = Integer.parseInt(c.getString( c.getColumnIndex("peso") ));
            int altura = Integer.parseInt(c.getString( c.getColumnIndex("altura") ));


            /*/*nome, cpf, senha, email, cargaHoraria, observacoes, peso, altura */
            aluno.setId( id );
            aluno.setNome( nome );
   aluno.setCpf( cpf );
            aluno.setSenha( senha );
            aluno.setEmail( email );
            aluno.setCargaHoraria( cargaHoraria );
            aluno.setObservacoes( observacoes );
            aluno.setPeso(peso);
            aluno.setAltura(altura);

            alunos.add( aluno );
            Log.i("AlunoDAO", aluno.toString() );

        }

        return alunos;

    }
}