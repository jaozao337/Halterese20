package com.solagna.haltere_se20.Controller;

import com.solagna.haltere_se20.Model.Exercicio;

public class ExercicioController {


    public ExercicioController(){

    }


    public void cadastrarExercicio(String nome,String tipo, String peso, String repeticoes, String series, String descricao, String duracao){
        Exercicio exercicio = new Exercicio(nome,peso,tipo,repeticoes,series,descricao,duracao);
    }

}
