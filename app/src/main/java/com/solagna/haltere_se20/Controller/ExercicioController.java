package com.solagna.haltere_se20.Controller;

import com.solagna.haltere_se20.Model.Exercicio;

public class ExercicioController {


    public ExercicioController(){

    }


    public void cadastrarExercicio(String nome,String tipo, int peso, int repeticoes, int series, String descricao, int duracao){
        Exercicio exercicio = new Exercicio(nome,peso,tipo,repeticoes,series,descricao, duracao);
    }

}
