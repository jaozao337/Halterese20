package com.solagna.haltere_se20.Adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.RecyclerView;

import com.solagna.haltere_se20.Model.Aluno;
import com.solagna.haltere_se20.R;

import java.util.List;

public class adapterTreinadorAluno extends RecyclerView.Adapter<adapterTreinadorAluno.MyViewHolder> {
    public RecyclerView rvAlunos;
    private List<Aluno> listaAlunos;
    private Button btEditarDados, btEditarTreino;



    public adapterTreinadorAluno(List<Aluno> alunos) {

        listaAlunos = alunos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.adapter_treinador_aluno, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Aluno aluno = listaAlunos.get(position);
        holder.nome.setText(aluno.getNome());
        holder.cpf.setText(aluno.getCpf());
    }

    @Override
    public int getItemCount() {
        if (listaAlunos == null) {
            return 0;
        } else {
            return listaAlunos.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nome;
        TextView cpf;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
             nome = itemView.findViewById(R.id.textNome);
             cpf = itemView.findViewById(R.id.textCpf);
        }
    }
}
