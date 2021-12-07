package com.solagna.haltere_se20.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.List;

/*
public class adapterTreinadorAluno extends RecyclerView.Adapter<adapterTreinadorAluno.MyViewHolder> {
    private List<Aluno> listaAlunos;

    public adapterTreinadorAluno(List<Aluno> servicinhos) {
        listaAlunos = servicinhos;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.activity_adapter_treinador_aluno, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Aluno aluno = listaAlunos.get(position);
        holder.nome.setText(aluno.getNome());
        holder.info1.setText(aluno.getPeso() + "/" + aluno.getAltura());
        holder.email.setText(aluno.getEmail());
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
        TextView info1;
        TextView email;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.textNome);
            info1 = itemView.findViewById(R.id.textPesoAltura);
            email = itemView.findViewById(R.id.textEmail);
        }
    }

}*/