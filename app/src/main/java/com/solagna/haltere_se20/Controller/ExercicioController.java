package com.solagna.haltere_se20.Controller;

import android.content.Context;

import com.solagna.haltere_se20.Helper.ExercicioDAO;
import com.solagna.haltere_se20.Model.Exercicio;
import com.solagna.haltere_se20.Model.Treino;

import java.util.List;

public class ExercicioController {

    private ExercicioDAO exercicioDAO;

    public ExercicioController(Context context){
        exercicioDAO = new ExercicioDAO(context);
    }

    public ExercicioController() { }

    public boolean cadastrarExercicio(String nome,String tipo, int peso, int repeticoes, int series, String descricao, int duracao){
        Exercicio exercicio = new Exercicio(nome,peso,tipo,repeticoes,series,descricao, duracao);
        //cadastra usando o DAO
        return exercicioDAO.salvar(exercicio);
    }

    public List<Exercicio> listarExercicio(){
        return  exercicioDAO.listarExercicios();
    }

}
