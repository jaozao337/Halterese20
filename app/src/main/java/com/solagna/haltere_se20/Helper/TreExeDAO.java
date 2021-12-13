package com.solagna.haltere_se20.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.solagna.haltere_se20.Data.DataBase;
import com.solagna.haltere_se20.MainController;
import com.solagna.haltere_se20.Model.Aluno;
import com.solagna.haltere_se20.Model.Treino;
import com.solagna.haltere_se20.Model.TreinoExercicio;

import java.util.ArrayList;
import java.util.List;

public class TreExeDAO implements BaseDAO{
    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public TreExeDAO(Context context) {
        //DataBase = new DataBase( context );
        escreve = MainController.db.getWritableDatabase();
        le = MainController.db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Object obj) {
        TreinoExercicio te= (TreinoExercicio) obj;

        ContentValues cv = new ContentValues();
        cv.put("idTreinoExercicio", te.getTreinoID() + "/" + te.getExercicioID());
        cv.put("idExercicio", te.getExercicioID() );
        cv.put("idTreino", te.getTreinoID() );

        /*nome, cpf, senha, email, cargaHoraria, observacoes, peso, altura */
        try {
            escreve.insert(DataBase.TABELA_ALUNOS, null, cv );
            Log.i("INFO", "Conexão salva com sucesso!");
        }catch (Exception e){
            Log.e("INFO", "Erro ao salvar conexão " + e.getMessage() );
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(Object obj) {
        return false;
    }

    @Override
    public boolean deletar(Object obj) {
        TreinoExercicio te= (TreinoExercicio) obj;
        try {
            String[] args = {te.getTreinoID() + "/" + te.getExercicioID()};
            escreve.delete(DataBase.TABELA_TREINO_EXERCICIOS, "idTreinoExercicio=?", args );
            Log.i("INFO", "Conexão removida com sucesso!");
        }catch (Exception e){
            Log.e("INFO", "Erro ao remover Conexão " + e.getMessage() );
            return false;
        }
        return true;
    }

    @Override
    public List<Object> listar() {
        return null;
    }

    public List<TreinoExercicio> listarTreinoExercicio(){
        List<TreinoExercicio> teLista = new ArrayList<>();

        String sql = "SELECT * FROM " + DataBase.TABELA_TREINO_EXERCICIOS + " ;";
        Cursor c = le.rawQuery(sql, null);

        while ( c.moveToNext() && c!=null ){

            TreinoExercicio te = new TreinoExercicio();

            Integer idExercicio = c.getInt( c.getColumnIndex("idExercicio") );
            Integer idTreino = c.getInt( c.getColumnIndex("idTreino") );
            //String idTreinoExercicio = c.getString( c.getColumnIndex("idTreinoExercicio") );

            te.setExercicioID(idExercicio);
            te.setTreinoID(idTreino);

            teLista.add( te );
            Log.i("TreinoExercicioDao", te.toString() );

        }

        return teLista;
    }

    public List<TreinoExercicio> buscar(String busca) {
        List<TreinoExercicio> teLista = new ArrayList<>();

        String sql = "SELECT * FROM " + DataBase.TABELA_TREINO_EXERCICIOS + " WHERE idTreinoExercicio LIKE '%" + busca + "%' ;";
        Cursor c = le.rawQuery(sql, null);

        while ( c.moveToNext() && c!=null ){
            TreinoExercicio te = new TreinoExercicio();

            Integer idExercicio = c.getInt( c.getColumnIndex("idExercicio") );
            Integer idTreino = c.getInt( c.getColumnIndex("idTreino") );

            te.setExercicioID(idExercicio);
            te.setTreinoID(idTreino);

            teLista.add(te);
            Log.i("TreinoExercicioDAO", teLista.toString());
        }

        return teLista;

    }
}
