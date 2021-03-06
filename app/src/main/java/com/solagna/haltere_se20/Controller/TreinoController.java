package com.solagna.haltere_se20.Controller;

import android.content.Context;

import com.solagna.haltere_se20.Helper.AlunoDAO;
import com.solagna.haltere_se20.Helper.TreinoDAO;
import com.solagna.haltere_se20.Model.Aluno;
import com.solagna.haltere_se20.Model.Treino;

import java.util.List;

public class TreinoController {
    private TreinoDAO treinoDAO;

    public TreinoController() { }

    public TreinoController(Context context) {
        treinoDAO = new TreinoDAO(context);
    }

    public boolean cadastrarTreino(String nomeTreino, int duracaoTreino, String descricaoTreino){
        Treino treino = new Treino(nomeTreino,duracaoTreino,descricaoTreino);
        //cadastra usando o DAO
        return treinoDAO.salvar(treino);
    }

    public boolean validarTreino(String nomeTreino, int duracaoTreino, String descricaoTreino){
        Treino treino = new Treino(nomeTreino,duracaoTreino,descricaoTreino);
        return treinoDAO.validar(treino);
    }

    public List<Treino> listarTreinos(){
        return  treinoDAO.listarTreinos();
    }

    public boolean deletarTreino(String nome, int duracao, String descricao, String id){
        Treino treino = new Treino(nome, duracao, descricao);
        treino.setId(id);
        return treinoDAO.deletar(treino);
    }

    public boolean atualizarTreino(String nome, int duracao, String descricao, String id){
        Treino treino = new Treino(nome, duracao, descricao);
        treino.setId(id);
        return treinoDAO.atualizar(treino);
    }
}