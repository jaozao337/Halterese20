package com.solagna.haltere_se20.Adapter;

/*
public class adapterExercicio extends RecyclerView.Adapter<com.example.halterese.adapter.adapterExercicio.MyViewHolder> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_exercicio);
    }



        private List<Treino> listaTreinos;

        public adapterExercicio(List<Treino> servicinhos) {
            listaTreinos = servicinhos;
        }


        @NonNull
        @Override
        public com.example.halterese.adapter.adapterExercicio.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemLista = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.activity_adapter_treinador_treino, parent, false);
            return new com.example.halterese.adapter.adapterExercicio.MyViewHolder(itemLista);
        }

        @Override
        public void onBindViewHolder(@NonNull com.example.halterese.adapter.adapterExercicio.MyViewHolder holder, int position) {
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
    }

}
//*/
