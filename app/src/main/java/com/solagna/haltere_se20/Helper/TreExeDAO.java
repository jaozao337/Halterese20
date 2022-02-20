package com.solagna.haltere_se20.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.solagna.haltere_se20.Data.DataBase;
import com.solagna.haltere_se20.MainController;
import com.solagna.haltere_se20.Model.Aluno;
import com.solagna.haltere_se20.Model.Exercicio;
import com.solagna.haltere_se20.Model.Treino;
import com.solagna.haltere_se20.Model.TreinoExercicio;

import java.util.ArrayList;
import java.util.List;

public class TreExeDAO implements BaseDAO{
    private SQLiteDatabase escreve;
    private SQLiteDatabase le;
    private DatabaseReference bancoDeDados;

    public TreExeDAO(Context context) {
        //DataBase = new DataBase( context );
        escreve = MainController.db.getWritableDatabase();
        le = MainController.db.getReadableDatabase();
        bancoDeDados = FirebaseDatabase.getInstance().getReference().child("Servicos");
    }

    @Override
    public boolean salvar(Object obj) {
        TreinoExercicio ex= (TreinoExercicio) obj;
        if(ex != null) {
            /*nome, peso, tipo,  repeticoes,  series, descricao, duracao*/
            String mGroupId = bancoDeDados.child("TreinoExercicios").push().getKey();
            bancoDeDados.child("TreinoExercicios").child(mGroupId).setValue(ex);
            return true;
        }else{
            return false;
        }
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
        return null;
    }
}
