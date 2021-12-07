package com.solagna.haltere_se20.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
/*
import com.example.halterese.R;
import com.example.halterese.model.Treino;

import java.util.List;

public class adapterTreinosLista extends RecyclerView.Adapter<com.example.halterese.adapter.adapterTreinosLista.MyViewHolder> {
    private List<Treino> listaTreinos;

    public adapterTreinosLista(List<Treino> servicinhos) {
        listaTreinos = servicinhos;
        }


        @NonNull
        @Override
        public com.example.halterese.adapter.adapterTreinosLista.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemLista = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.activity_adapter_treinador_treino, parent, false);
            return new com.example.halterese.adapter.adapterTreinosLista.MyViewHolder(itemLista);
        }

        @Override
        public void onBindViewHolder(@NonNull com.example.halterese.adapter.adapterTreinosLista.MyViewHolder holder, int position) {
            Treino treino = listaTreinos.get(position);
            holder.nome.setText(treino.getNome());
            holder.info1.setText(treino.getExercicios());
            holder.email.setText(treino.getDescricao());
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
