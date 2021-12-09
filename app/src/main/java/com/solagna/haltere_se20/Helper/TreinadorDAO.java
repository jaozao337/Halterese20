package com.solagna.haltere_se20.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.solagna.haltere_se20.Data.DataBase;
import com.solagna.haltere_se20.MainController;
import com.solagna.haltere_se20.Model.Treinador;

import java.util.ArrayList;
import java.util.List;

public class TreinadorDAO implements BaseDAO{
    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public TreinadorDAO(Context context) {
        //DataBase = new DataBase( context );
        escreve = MainController.db.getWritableDatabase();
        le = MainController.db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Object obj) {
        Treinador trnr= (Treinador) obj;
        /* nome,  cpf,  senha,  email ,  salario*/
        ContentValues cv = new ContentValues();
        cv.put("nome", trnr.getNome() );
        cv.put("cpf", trnr.getCpf() );
        cv.put("senha", trnr.getSenha() );
        cv.put("email", trnr.getEmail() );
        cv.put("salario", trnr.getSalario() );

        /*nome, cpf, senha, email, cargaHoraria, observacoes, peso, altura */
        try {
            escreve.insert(DataBase.TABELA_TREINADORES, null, cv );
            Log.i("INFO", "Treinador salvo com sucesso!");
        }catch (Exception e){
            Log.e("INFO", "Erro ao salvar Treinador " + e.getMessage() );
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(Object obj) {
        Treinador trnr= (Treinador) obj;
        /* nome,  cpf,  senha,  email ,  salario*/
        ContentValues cv = new ContentValues();
        cv.put("nome", trnr.getNome() );
        cv.put("cpf", trnr.getCpf() );
        cv.put("senha", trnr.getSenha() );
        cv.put("email", trnr.getEmail() );
        cv.put("salario", trnr.getSalario() );

        try {
            String[] args = {trnr.getCpf().toString()};
            escreve.update(DataBase.TABELA_TREINADORES, cv, "CPF=? ", args);
            Log.i("INFO", "Treinador atualizado com sucesso!");
        }catch (Exception e){
            Log.e("INFO", "Erro ao atualizar Treinador " + e.getMessage() );
            return false;
        }
        return true;
    }

    @Override
    public boolean deletar(Object obj) {
        Treinador trnr= (Treinador) obj;

        try {
            String[] args = {trnr.getCpf().toString()};
            escreve.delete(DataBase.TABELA_TREINADORES, "CPF=?", args );
            Log.i("INFO", "Treinador removido com sucesso!");
        }catch (Exception e){
            Log.e("INFO", "Erro ao remover Treinador " + e.getMessage() );
            return false;
        }
        return true;

    }
    @Override
    public List<Object> listar() { return null;}

    public List<Treinador> listarTreinadores() {
        List<Treinador> treinadores = new ArrayList<>();

        String sql = "SELECT * FROM " + DataBase.TABELA_TREINADORES + " ;";
        Cursor c = le.rawQuery(sql, null);

        while ( c.moveToNext() ){

            Treinador treinador = new Treinador();

            /* nome,  cpf,  senha,  email ,  salario*/
            //Long id = c.getLong( c.getColumnIndex("id") );
            String nome = c.getString( c.getColumnIndex("nome") );
            String cpf = c.getString( c.getColumnIndex("cpf") );
            String senha = c.getString( c.getColumnIndex("senha") );
            String email = c.getString( c.getColumnIndex("email") );
            int salario = Integer.parseInt(c.getString( c.getColumnIndex("salario") ));

            /*/*nome, cpf, senha, email, cargaHoraria, observacoes, peso, altura */
            //treinador.setId( id );
            treinador.setNome( nome );
            treinador.setCpf( cpf );
            treinador.setSenha( senha );
            treinador.setEmail( email );
            treinador.setSalario( salario );

            treinadores.add( treinador );
            Log.i("TreinadorDAO", treinador.getNome() );
        }

        return treinadores;

    }
}
