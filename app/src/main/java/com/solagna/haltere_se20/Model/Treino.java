package com.solagna.haltere_se20.Model;

public class Treino  {
    private String nomeTreino, descricaoTreino;
    private int duracaoTreino;

    public Treino() {}

    public Treino(String nomeTreino, int duracaoTreino, String descricaoTreino){

        this.nomeTreino = nomeTreino;
        this.duracaoTreino = duracaoTreino;
        this.descricaoTreino = descricaoTreino;
    }

    public String getNomeTreino() {
        return nomeTreino;
    }

    public void setNomeTreino(String nomeTreino) {
        this.nomeTreino = nomeTreino;
    }

    public int getDuracaoTreino() {
        return duracaoTreino;
    }

    public void setDuracaoTreino(int duracaoTreino) {
        this.duracaoTreino = duracaoTreino;
    }

    public String getDescricaoTreino() {
        return descricaoTreino;
    }

    public void setDescricaoTreino(String descricaoTreino) {
        this.descricaoTreino = descricaoTreino;
    }

}
