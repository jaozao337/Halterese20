package com.solagna.haltere_se20.Controller;

import android.content.Context;
import android.widget.Toast;

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

    public boolean atualizarAluno(String nome, String cpf, String senha, String email, String cargaHoraria, String observacoes, int peso, int altura){
        Aluno aluno = new Aluno(nome, cpf, senha, email, cargaHoraria, observacoes, peso, altura);
        return  alunoDAO.atualizar(aluno);
    }

    public boolean deletarAluno(String nome, String cpf, String senha, String email, String cargaHoraria, String observacoes, int peso, int altura){
        Aluno aluno = new Aluno(nome, cpf, senha, email, cargaHoraria, observacoes, peso, altura);
        return alunoDAO.deletar(aluno);
    }

    public List<Aluno> procurarAlunoNome(String nome){
        List<Aluno> alunos = alunoDAO.buscarAlunoNome(nome);
        return alunos;
    }

    public List<Aluno> listarAlunos(){
        List<Aluno> teste =  alunoDAO.listarAlunos();
        System.out.println("aaaaaaaa" + teste.size());
        return  teste;
    }

}
