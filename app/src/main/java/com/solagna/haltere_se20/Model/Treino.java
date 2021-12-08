package com.solagna.haltere_se20.Model;

public class Treino  {
    String nomeTreino, duracaoTreino, descricaoTreino;

    public Treino(String nomeTreino, String duracaoTreino, String descricaoTreino){

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

    public String getDuracaoTreino() {
        return duracaoTreino;
    }

    public void setDuracaoTreino(String duracaoTreino) {
        this.duracaoTreino = duracaoTreino;
    }

    public String getDescricaoTreino() {
        return descricaoTreino;
    }

    public void setDescricaoTreino(String descricaoTreino) {
        this.descricaoTreino = descricaoTreino;
    }

}
