package com.solagna.haltere_se20.Controller;

import android.content.Intent;

import com.solagna.haltere_se20.Model.Aluno;
import com.solagna.haltere_se20.Model.Treinador;
import com.solagna.haltere_se20.View.TreinadorView;

public class TreinadorController {

    public TreinadorController(){

    }

    public void cadastrarTreinador(String nome, String cpf, String senha, String email , int salario){
        Treinador treinador = new Treinador(nome,cpf,senha,email,salario);
    }



}
