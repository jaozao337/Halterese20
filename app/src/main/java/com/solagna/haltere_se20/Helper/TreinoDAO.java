package com.solagna.haltere_se20.Helper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.solagna.haltere_se20.Data.DataBase;
import com.solagna.haltere_se20.MainController;
import com.solagna.haltere_se20.Model.Treino;

import java.util.ArrayList;
import java.util.List;

public class TreinoDAO implements BaseDAO{
    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public TreinoDAO(Context context) {
        //DataBase = new DataBase( context );
        escreve = MainController.db.getWritableDatabase();
        le = MainController.db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Object obj) {
        Treino tr= (Treino) obj;
        /* nomeTreino,  duracaoTreino,  descricaoTreino*/
        ContentValues cv = new ContentValues();
        cv.put("nome treino", tr.getNomeTreino() );
        cv.put("descricao treino", tr.getDescricaoTreino() );
        cv.put("duracao treino", tr.getDuracaoTreino() );

        try {
            escreve.insert(DataBase.TABELA_TREINOS, null, cv );
            Log.i("INFO", "Treino salvo com sucesso!");
        }catch (Exception e){
            Log.e("INFO", "Erro ao salvar Treino " + e.getMessage() );
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(Object obj) {
        Treino tr= (Treino) obj;
        /* nomeTreino,  duracaoTreino,  descricaoTreino*/
        ContentValues cv = new ContentValues();
        cv.put("nome treino", tr.getNomeTreino() );
        cv.put("descricao treino", tr.getDescricaoTreino() );
        cv.put("duracao treino", tr.getDuracaoTreino() );

        try {
            String[] args = {tr.getNomeTreino().toString()};
            escreve.update(DataBase.TABELA_TREINOS, cv, "Nome=? ", args);
            Log.i("INFO", "Treino atualizado com sucesso!");
        }catch (Exception e){
            Log.e("INFO", "Erro ao atualizar Treino " + e.getMessage() );
            return false;
        }
        return true;
    }

    @Override
    public boolean deletar(Object obj) {
        Treino tr= (Treino) obj;

        try {
            String[] args = {tr.getNomeTreino().toString()};
            escreve.delete(DataBase.TABELA_TREINOS, "Nome=? ", args );
            Log.i("INFO", "Treino removido com sucesso!");
        }catch (Exception e){
            Log.e("INFO", "Erro ao remover Treino " + e.getMessage() );
            return false;
        }
        return true;

    }

    @Override
    public List<Object> listar() {
        return null;
    }


    public List<Treino> listarTreinos() {
        List<Treino> treinos = new ArrayList<>();

        String sql = "SELECT * FROM " + DataBase.TABELA_TREINOS + " ;";
        Cursor c = le.rawQuery(sql, null);

        while (c.moveToNext()) {

            Treino treino = new Treino();
            /* nomeTreino,  duracaoTreino,  descricaoTreino*/
            Long id = c.getLong( c.getColumnIndex("id") );
            String nomeTreino = c.getString(c.getColumnIndex("nome treino"));
            String descricaoTreino = c.getString(c.getColumnIndex("descricao treino"));
            int duracaoTreino = Integer.parseInt(c.getString(c.getColumnIndex("duracao treino")));

            treino.setId( id );
            treino.setNomeTreino(nomeTreino);
            treino.setDescricaoTreino(descricaoTreino);
            treino.setDuracaoTreino(duracaoTreino);

            treinos.add(treino);
            Log.i("TreinoDAO", treino.getNomeTreino());
        }

        return treinos;
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
