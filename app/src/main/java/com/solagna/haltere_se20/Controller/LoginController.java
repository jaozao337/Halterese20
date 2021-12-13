package com.solagna.haltere_se20.Controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.IconCompat;

import com.solagna.haltere_se20.Data.DataBase;
import com.solagna.haltere_se20.Helper.AlunoDAO;
import com.solagna.haltere_se20.Helper.TreinadorDAO;
import com.solagna.haltere_se20.MainController;
import com.solagna.haltere_se20.Model.Aluno;
import com.solagna.haltere_se20.Model.Treinador;
import com.solagna.haltere_se20.View.LoginView;

public class LoginController {

    private AlunoDAO ad;
    private TreinadorDAO td;

    public LoginController(Context ex){
        ad = new AlunoDAO(ex);
        td = new TreinadorDAO(ex);
    }
    //cria o dao

    public boolean realizarLoginAluno(String login, String senha){
        Aluno aluno = ad.validarLoginAluno(login, senha);
        // Se a variável usuario for diferente de null, significa que consultou no SQLite e esse usuário existe, então:
        if (aluno != null) {
            Log.i("info","logou");
            return true;
        } else {
            Log.i("info","n logou");
            return false; }
    }
    public boolean realizarLoginTreinador(String login, String senha){
        Treinador treinador = td.validarLoginTreinador(login, senha);
        // Se a variável usuario for diferente de null, significa que consultou no SQLite e esse usuário existe, então:
        if (treinador != null) {
            Log.i("info","logou");
            return true;
        } else {
            Log.i("info","n logou");
            return false; }
    }




    /*
    public static Boolean autenticaAluno(String login, String senha){
        SQLiteDatabase le;
        le = MainController.db.getReadableDatabase();
        //String sql = "SELECT * FROM " + DataBase.TABELA_ALUNOS + " WHERE  email = " + "'" + aluno.getEmail() + "'" ;
        String sql = "SELECT * FROM " + DataBase.TABELA_ALUNOS +
                " WHERE email LIKE " + login + " ;";
        Cursor c = le.rawQuery(sql, null);


        while(c.moveToNext()){
            if(aluno.getEmail().equals(c.getString(c.getColumnIndex("email")))){
                if(aluno.getSenha().equals(c.getString(c.getColumnIndex("senha")))){
                        Log.i("INFO", "LOGOU"); // Info
                    return true;
                }
            }
        }
        Log.i("INFO", "NAO LOGOU"); // Info
        return false;
    }
    */
}
