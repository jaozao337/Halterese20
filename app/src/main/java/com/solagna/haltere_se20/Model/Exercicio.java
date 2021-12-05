package com.solagna.haltere_se20.Model;

public class Exercicio {
    private String nome, series,descricao,tempo, repeticoes;

    public Exercicio(String nome, String series, String descricao, String tempo, String repeticoes) {
        this.nome = nome;
        this.series = series;
        this.descricao = descricao;
        this.tempo = tempo;
        this.repeticoes = repeticoes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(String repeticoes) {
        this.repeticoes = repeticoes;
    }
}
