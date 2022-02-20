package com.solagna.haltere_se20.Model;

public class TreinoExercicio {
    private String exercicioID, treinoID;

    public TreinoExercicio() {
    }

    public  TreinoExercicio(String exercicioID, String treinoID) {
        this.exercicioID = exercicioID;
        this.treinoID = treinoID;
    }

    public String getTreinoID() {
        return treinoID;
    }

    public void setTreinoID(String treinoID) {
        this.treinoID = treinoID;
    }

    public String getExercicioID() {
        return exercicioID;
    }

    public void setExercicioID(String exercicioID) {
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
