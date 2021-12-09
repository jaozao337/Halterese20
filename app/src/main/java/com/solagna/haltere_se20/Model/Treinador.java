package com.solagna.haltere_se20.Model;

public class Treinador extends Pessoa{
    int salario;

    public Treinador() {}

    public Treinador(String nome, String cpf, String senha, String email , int salario){
        super(nome, cpf, senha, email);
        this.salario = salario;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

}
