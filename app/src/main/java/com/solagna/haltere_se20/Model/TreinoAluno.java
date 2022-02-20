package com.solagna.haltere_se20.Model;

public class TreinoAluno {
    private String cpf, treinoID;

    public TreinoAluno() {
    }

    public  TreinoAluno(String treinoID, String cpf) {
        this.cpf = cpf;
        this.treinoID = treinoID;
    }

    public String getTreinoID() {
        return treinoID;
    }

    public void setTreinoID(String treinoID) {
        this.treinoID = treinoID;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "TreinoExercicio{" +
                "idTreino='" + cpf + '\'' +
                ", cpf='" + treinoID + '\'' +
                '}';
    }
}
