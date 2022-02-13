package com.solagna.haltere_se20.Model;

public class Exercicio {

    private String nome,tipo,descricao;
    private int peso, repeticoes, series, duracao;
    private String id;


    public Exercicio(){}

    public Exercicio(String nome, int peso, String tipo, int  repeticoes, int series, String descricao, int duracao) {
        this.nome = nome;
        this.peso = peso;
        this.tipo = tipo;
        this.repeticoes = repeticoes;
        this.series = series;
        this.descricao = descricao;
        this.duracao = duracao;

    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(int repeticoes) {
        this.repeticoes = repeticoes;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
