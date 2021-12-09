package com.solagna.haltere_se20.Model;

public class Aluno extends Pessoa{
    private String cargaHoraria, observacoes;
    private int peso, altura;

    public Aluno() {
    }

    public Aluno(String nome, String cpf, String senha, String email, String cargaHoraria, String observacoes, int peso, int altura) {
        super(nome, cpf, senha, email);
        this.cargaHoraria = cargaHoraria;
        this.observacoes = observacoes;
        this.peso = peso;
        this.altura = altura;
    }

    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(String cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
}
