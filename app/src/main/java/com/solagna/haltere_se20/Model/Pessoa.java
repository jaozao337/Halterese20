package com.solagna.haltere_se20.Model;

public abstract class Pessoa {
   private String nome, cpf, senha, email;
private int id;
    public Pessoa(){

    }

    public Pessoa(String nome, String cpf, String senha, String email){
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", senha='" + senha + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                '}';
    }

    public void setId(Integer id) {
        this.id=id;
    }
}
