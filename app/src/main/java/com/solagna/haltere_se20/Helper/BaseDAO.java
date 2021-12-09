package com.solagna.haltere_se20.Helper;

import com.solagna.haltere_se20.Model.Aluno;

import java.util.List;

public interface BaseDAO {
    public boolean salvar(Object obj);
    public boolean atualizar(Object obj);
    public boolean deletar(Object obj);
    public List<Object> listar();
    //public List<Aluno> listarAluno();
}
