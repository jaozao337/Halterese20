package com.solagna.haltere_se20.Helper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.solagna.haltere_se20.Data.DataBase;
import com.solagna.haltere_se20.MainController;
import com.solagna.haltere_se20.Model.Exercicio;

import java.util.ArrayList;
import java.util.List;

public class ExercicioDAO implements BaseDAO{
    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public ExercicioDAO(Context context) {
        //DataBase = new DataBase( context );
        escreve = MainController.db.getWritableDatabase();
        le = MainController.db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Object obj) {
        Exercicio ex= (Exercicio) obj;
        /*nome, peso, tipo,  repeticoes,  series, descricao, duracao*/
        ContentValues cv = new ContentValues();
        cv.put("nome", ex.getNome() );
        cv.put("peso", ex.getPeso() );
        cv.put("tipo", ex.getTipo() );
        cv.put("repeticoes", ex.getRepeticoes() );
        cv.put("series", ex.getSeries() );
        cv.put("descricao", ex.getDescricao() );
        cv.put("duracao", ex.getDuracao() );


        /*nome, cpf, senha, email, cargaHoraria, observacoes, peso, altura */
        try {
            escreve.insert(DataBase.TABELA_EXERCICIOS, null, cv );
            Log.i("INFO", "Exercicio salvo com sucesso!");
        }catch (Exception e){
            Log.e("INFO", "Erro ao salvar Exercicio " + e.getMessage() );
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(Object obj) {
        Exercicio ex= (Exercicio) obj;
        /*nome, peso, tipo,  repeticoes,  series, descricao, duracao*/
        ContentValues cv = new ContentValues();
        cv.put("nome", ex.getNome() );
        cv.put("peso", ex.getPeso() );
        cv.put("tipo", ex.getTipo() );
        cv.put("repeticoes", ex.getRepeticoes() );
        cv.put("series", ex.getSeries() );
        cv.put("descricao", ex.getDescricao() );
        cv.put("duracao", ex.getDuracao() );

        try {
            String[] args = {ex.getNome().toString()};
            escreve.update(DataBase.TABELA_EXERCICIOS, cv, "Nome=? ", args);
            Log.i("INFO", "Exercicio atualizado com sucesso!");
        }catch (Exception e){
            Log.e("INFO", "Erro ao atualizar Exercicio " + e.getMessage() );
            return false;
        }
        return true;
    }

    @Override
    public boolean deletar(Object obj) {
        Exercicio ex= (Exercicio) obj;

        try {
            String[] args = {ex.getNome().toString()};
            escreve.delete(DataBase.TABELA_EXERCICIOS, "Nome=? ", args );
            Log.i("INFO", "Exercicio removido com sucesso!");
        }catch (Exception e){
            Log.e("INFO", "Erro ao remover Exercicio " + e.getMessage() );
            return false;
        }
        return true;

    }

    @Override
    public List<Object> listar() {
        return null;
    }


    public List<Exercicio> listarExercicios() {
        List<Exercicio> exercicios = new ArrayList<>();

        String sql = "SELECT * FROM " + DataBase.TABELA_EXERCICIOS + " ;";
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