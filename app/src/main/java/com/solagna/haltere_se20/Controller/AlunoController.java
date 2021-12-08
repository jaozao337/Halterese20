package com.solagna.haltere_se20.Controller;

import com.solagna.haltere_se20.Model.Aluno;

public class AlunoController {


    public AlunoController(){

    }


    public void cadastrarAluno(String nome, String cpf, String senha, String email, String cargaHoraria, String observacoes, String peso, String altura){
        Aluno aluno = new Aluno(nome,cpf,senha,email,cargaHoraria,observacoes,peso,altura);
    }

}
