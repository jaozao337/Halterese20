package com.solagna.haltere_se20.Controller;

import android.content.Context;
import android.content.Intent;

import com.solagna.haltere_se20.Helper.AlunoDAO;
import com.solagna.haltere_se20.Helper.TreinadorDAO;
import com.solagna.haltere_se20.Model.Aluno;
import com.solagna.haltere_se20.Model.Treinador;
import com.solagna.haltere_se20.Model.Treino;
import com.solagna.haltere_se20.View.TreinadorView;

import java.util.List;

public class TreinadorController {
    private TreinadorDAO treinadorDAO;

    public TreinadorController() { }

    public TreinadorController( Context context){
        treinadorDAO = new TreinadorDAO(context);
    }

    public boolean cadastrarTreinador(String nome, String cpf, String senha, String email , int salario){
        Treinador treinador = new Treinador(nome,cpf,senha,email,salario);
        //cadastra usando o dao
        return treinadorDAO.salvar(treinador);
    }

}
