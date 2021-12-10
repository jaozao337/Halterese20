package com.solagna.haltere_se20.Controller;

import android.content.Context;

import com.solagna.haltere_se20.Helper.AlunoDAO;
import com.solagna.haltere_se20.Model.Aluno;

import java.util.List;

public class AlunoController {


    private AlunoDAO alunoDAO;

    public AlunoController(Context context) {
        alunoDAO = new AlunoDAO(context);
    }


    public boolean cadastrarAluno(String nome, String cpf, String senha, String email, String cargaHoraria, String observacoes, int peso, int altura) {
        Aluno aluno = new Aluno(nome, cpf, senha, email, cargaHoraria, observacoes, peso, altura);
        //cadastra usando o dao
        return alunoDAO.salvar(aluno);

    }

    public List<Aluno> listarAlunos(){
        return  alunoDAO.listarAlunos();
    }

}
