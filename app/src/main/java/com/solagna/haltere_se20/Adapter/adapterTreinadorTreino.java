package com.solagna.haltere_se20.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
/*
import com.example.halterese.R;
import com.example.halterese.model.Exercicio;

import java.util.List;

public class adapterTreinadorTreino extends RecyclerView.Adapter<adapterTreinadorTreino.MyViewHolder> {
    private List<Exercicio> listaTreinos;

    public adapterTreinadorTreino(List<Exercicio> servicinhos) {
        listaTreinos = servicinhos;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.activity_adapter_treinador_treino, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Exercicio exercicio = listaTreinos.get(position);
        holder.nome.setText(exercicio.getNome());
        holder.info1.setText("series:" + exercicio.getSeries() + "/rep:" + exercicio.getRepeticoes());
        holder.email.setText(exercicio.getDescricao() + ", durante" + exercicio.getTempo());
    }

    @Override
    public int getItemCount() {
        if (listaTreinos == null) {
            return 0;
        } else {
            return listaTreinos.size();
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nome;
        TextView info1;
        TextView email;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.textNomeExercicio);
            info1 = itemView.findViewById(R.id.textSeriesRepeticoes);
            email = itemView.findViewById(R.id.textDescricao);
        }
    }


}*/