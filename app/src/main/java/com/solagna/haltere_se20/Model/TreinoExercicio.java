package com.solagna.haltere_se20.Model;

public class TreinoExercicio {
    private int exercicioID, treinoID;

    public TreinoExercicio() {
    }

    public  TreinoExercicio(int exercicioID, int treinoID) {
        this.exercicioID = exercicioID;
        this.treinoID = treinoID;
    }

    public int getTreinoID() {
        return treinoID;
    }

    public void setTreinoID(int treinoID) {
        this.treinoID = treinoID;
    }

    public int getExercicioID() {
        return exercicioID;
    }

    public void setExercicioID(int exercicioID) {
        this.exercicioID = exercicioID;
    }

    @Override
    public String toString() {
        return "TreinoExercicio{" +
                "idExercicio='" + exercicioID + '\'' +
                ", idTreino='" + treinoID + '\'' +
                '}';
    }
}
