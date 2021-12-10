package com.solagna.haltere_se20.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.solagna.haltere_se20.Model.Exercicio;
import com.solagna.haltere_se20.R;

import java.util.List;

public class adapterListarExercicioTreino extends RecyclerView.Adapter<adapterListarExercicioTreino.MyViewHolder> {

    private List<Exercicio> listaExercicios;

    public adapterListarExercicioTreino(List<Exercicio> exercicios) {

        listaExercicios = exercicios;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.adapter_treinador_exercicios_treino, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Exercicio exercicio = listaExercicios.get(position);
        holder.nomeExercicio.setText(exercicio.getNome());
        holder.descricaoExercicio.setText(exercicio.getDescricao());
        holder.serieRepeticoesExercicio.setText(exercicio.getSeries()+" / "+exercicio.getRepeticoes());

    }



    @Override
    public int getItemCount() {
        if (listaExercicios == null) {
            return 0;
        } else {
            return listaExercicios.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nomeExercicio;
        TextView descricaoExercicio;
        TextView serieRepeticoesExercicio;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeExercicio = itemView.findViewById(R.id.textNomeDoExercicio);
            serieRepeticoesExercicio = itemView.findViewById(R.id.textSeriesRepeticoes);
            descricaoExercicio = itemView.findViewById(R.id.textDescricao);
        }
    }}

