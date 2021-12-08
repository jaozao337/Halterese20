package com.solagna.haltere_se20.Controller;

import com.solagna.haltere_se20.Model.Treino;

public class TreinoController {

    public TreinoController(){

    }

    public void cadastrarTreino(String nomeTreino, String duracaoTreino, String descricaoTreino){
        Treino treino = new Treino(nomeTreino,duracaoTreino,descricaoTreino);
    }
}