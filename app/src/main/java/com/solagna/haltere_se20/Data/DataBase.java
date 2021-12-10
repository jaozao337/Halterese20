package com.solagna.haltere_se20.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

public class DataBase extends SQLiteOpenHelper {


    public static int VERSION = 1;
    public static String NOME_DB = "DB_Halterese";
    public static String TABELA_ALUNOS = "alunos";
    public static String TABELA_TREINADORES = "treinadores";
    public static String TABELA_EXERCICIOS = "exercicios";
    public static String TABELA_TREINOS = "treinos";

    public DataBase(Context context){

        super(context, NOME_DB, null, VERSION);
    }
    /*nome, cpf, senha, email, cargaHoraria, observacoes, peso, altura */
    private static final String CREATE_TABLE_ALUNOS =
            "CREATE TABLE IF NOT EXISTS " + TABELA_ALUNOS +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "" + " nome TEXT NOT NULL, " +
            "" + " cpf TEXT NOT NULL, " +
            "" + " senha TEXT NOT NULL, " +
            "" + " email TEXT NOT NULL," +
            "" + " cargaHoraria TEXT NOT NULL," +
            "" + " observacoes TEXT NOT NULL," +
            "" + " peso INTEGER NOT NULL," +
            "" + " altura INTEGER NOT NULL); ";
    /*nome, peso, tipo,  repeticoes,  series, descricao, duracao*/
    private static final String CREATE_TABLE_EXERCICIOS =
            "CREATE TABLE IF NOT EXISTS " + TABELA_EXERCICIOS +
                    " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "" + " nome TEXT NOT NULL, " +
                    "" + " peso INTEGER NOT NULL, " +
                    "" + " tipo TEXT NOT NULL, " +
                    "" + " repeticoes INTEGER NOT NULL," +
                    "" + " series INTEGER NOT NULL," +
                    "" + " descricao TEXT NOT NULL," +
                    "" + " duracao INTEGER NOT NULL); ";
    /* nome,  cpf,  senha,  email ,  salario*/
    private static final String CREATE_TABLE_TREINADORES =
            "CREATE TABLE IF NOT EXISTS " + TABELA_TREINADORES +
                    " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "" + " nome TEXT NOT NULL, " +
                    "" + " cpf TEXT NOT NULL, " +
                    "" + " senha TEXT NOT NULL, " +
                    "" + " email TEXT NOT NULL," +
                    "" + " salario INTEGER NOT NULL); ";
    /* nomeTreino,  duracaoTreino,  descricaoTreino*/
    private static final String CREATE_TABLE_TREINOS =
            "CREATE TABLE IF NOT EXISTS " + TABELA_TREINOS +
                    " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "" + " nomeTreino TEXT NOT NULL, " +
                    "" + " duracaoTreino TEXT NOT NULL, " +
                    "" + " descricaoTreino TEXT NOT NULL); ";
    @Override
    public void onCreate(SQLiteDatabase db) {

        String tbAlunos= CREATE_TABLE_ALUNOS;
        String tbTreinadores= CREATE_TABLE_TREINADORES;
        String tbExercicios= CREATE_TABLE_EXERCICIOS;
        String tbTreinos= CREATE_TABLE_TREINOS;

        try {
            db.execSQL( tbAlunos );
            Log.i("INFO DB", "Sucesso ao criar a tabela" );
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao criar a tabela" + e.getMessage() );
        }
        try {
            db.execSQL( tbTreinadores );
            Log.i("INFO DB", "Sucesso ao criar a tabela" );
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao criar a tabela" + e.getMessage() );
        }
        try {
            db.execSQL( tbExercicios );
            Log.i("INFO DB", "Sucesso ao criar a tabela" );
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao criar a tabela" + e.getMessage() );
        }
        try {
            db.execSQL( tbTreinos );
            Log.i("INFO DB", "Sucesso ao criar a tabela" );
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao criar a tabela" + e.getMessage() );
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
        //String sql = "DROP TABLE IF EXISTS " + TABELA_ALUNOS + " ;" ;

        try {
            db.execSQL( "DROP TABLE IF EXISTS " + TABELA_ALUNOS + " ;" );
            onCreate(db);
            Log.i("INFO DB", "Sucesso ao atualizar App" );
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao atualizar App" + e.getMessage() );
        }
        try {
            db.execSQL( "DROP TABLE IF EXISTS " + TABELA_TREINADORES + " ;" );
            onCreate(db);
            Log.i("INFO DB", "Sucesso ao atualizar App" );
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao atualizar App" + e.getMessage() );
        }
        try {
            db.execSQL( "DROP TABLE IF EXISTS " + TABELA_EXERCICIOS + " ;" );
            onCreate(db);
            Log.i("INFO DB", "Sucesso ao atualizar App" );
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao atualizar App" + e.getMessage() );
        }
        try {
            db.execSQL( "DROP TABLE IF EXISTS " + TABELA_TREINOS + " ;" );
            onCreate(db);
            Log.i("INFO DB", "Sucesso ao atualizar App" );
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao atualizar App" + e.getMessage() );
        }

    }
}
