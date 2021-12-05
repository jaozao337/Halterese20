package com.solagna.haltere_se20.Model;

public class Aluno extends Pessoa{
    private String cargaHoraria, observacoes, peso, altura;

    public Aluno() {
    }

    public Aluno(String nome, String cpf, String senha, String email, String cargaHoraria, String observacoes, String peso, String altura) {
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

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }
}
