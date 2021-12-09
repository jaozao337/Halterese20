package com.solagna.haltere_se20.Helper;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.solagna.haltere_se20.Data.DataBase;
import com.solagna.haltere_se20.MainController;
import com.solagna.haltere_se20.Model.Aluno;

import java.util.List;

public class AlunoDAO implements BaseDAO{
    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public AlunoDAO(Context context) {
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
        return false;
    }

    @Override
    public boolean deletar(Object obj) {
        return false;
    }

    @Override
    public List<Object> listar() {
        return null;
    }
}