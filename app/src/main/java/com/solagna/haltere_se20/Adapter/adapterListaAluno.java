package com.solagna.haltere_se20.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.solagna.haltere_se20.Model.Aluno;
import com.solagna.haltere_se20.R;

import java.util.List;

public class adapterListaAluno extends RecyclerView.Adapter<adapterListaAluno.MyViewHolder> {

    private List<Aluno> listaAlunos;

    public adapterListaAluno(List<Aluno> alunos) {
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
        holder.observacoes.setText(aluno.getObservacoes());
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
        TextView observacoes;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
             nome = itemView.findViewById(R.id.textNome);
            observacoes = itemView.findViewById(R.id.textRVObservacoesAluno);
        }
    }
}
