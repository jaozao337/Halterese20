package com.solagna.haltere_se20.Controller;

import android.content.Context;

import com.solagna.haltere_se20.Helper.TreExeDAO;
import com.solagna.haltere_se20.Helper.TreinoDAO;
import com.solagna.haltere_se20.Model.Treino;
import com.solagna.haltere_se20.Model.TreinoExercicio;

import java.util.List;

public class TreinoExercicioController {

    private TreExeDAO treExeDAO;

    public TreinoExercicioController() { }

    public TreinoExercicioController(Context context) {
        treExeDAO = new TreExeDAO(context);
    }

    public boolean cadastrarTreinoExercicio(String idTreino, String idExercicio){
        TreinoExercicio treExe = new TreinoExercicio(idTreino, idExercicio);
        //cadastra usando o DAO
        return treExeDAO.salvar(treExe);
    }

    public List<TreinoExercicio> listarTreinos(){
        return  treExeDAO.listarTreinoExercicio();
    }
}
