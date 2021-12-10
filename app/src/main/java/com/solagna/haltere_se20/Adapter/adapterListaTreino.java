package com.solagna.haltere_se20.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.solagna.haltere_se20.Model.Aluno;
import com.solagna.haltere_se20.Model.Treino;
import com.solagna.haltere_se20.R;

import java.util.List;

public class adapterListaTreino extends RecyclerView.Adapter<adapterListaTreino.MyViewHolder> {

    private List<Treino> listaTreinos;

    public adapterListaTreino(List<Treino> treinos) {

        listaTreinos = treinos;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.adapter_treinador_treinos, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Treino treino = listaTreinos.get(position);
        holder.nomeTreino.setText(treino.getNomeTreino());
        holder.descricaoTreino.setText(treino.getDescricaoTreino());
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
        TextView nomeTreino;
        TextView descricaoTreino;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeTreino = itemView.findViewById(R.id.textNomeTreinoADP);
            descricaoTreino = itemView.findViewById(R.id.textDescricaoDoTreinoADP);
        }
    }

}
