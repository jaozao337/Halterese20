package com.solagna.haltere_se20.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.solagna.haltere_se20.Adapter.adapterListarExercicioTreino;
import com.solagna.haltere_se20.Controller.TreinoController;
import com.solagna.haltere_se20.Controller.TreinoExercicioController;
import com.solagna.haltere_se20.Helper.RecyclerItemClickListener;
import com.solagna.haltere_se20.Model.Exercicio;
import com.solagna.haltere_se20.Model.Treino;
import com.solagna.haltere_se20.Model.TreinoExercicio;
import com.solagna.haltere_se20.R;

import java.util.ArrayList;
import java.util.List;

public class CriaTreinosView extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Exercicio> listaExercicio = new ArrayList<Exercicio>(), selecionados = new ArrayList<Exercicio>();
    private Button btAdicionarTreino, btTelaExercicio, btDeletarTreino;
    private String nomeTreino, descricaoTreino, y, z;
    private int duracaoTreino = 0, x;
    private TreinoController ec;
    private TreinoExercicioController tec;
    EditText TnomeTreino, TdescricaoTreino, TduracaoTreino;
    private String titulo;
    private DatabaseReference bancoDeDados;
    String[] listaTreExe;
    String id;
    adapterListarExercicioTreino adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bancoDeDados = FirebaseDatabase.getInstance().getReference().child("Servicos");
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.tela_treinador_cadastra_treinos);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setTexts();
        btAdicionarTreino = findViewById(R.id.btAdicionarTreino);
        btTelaExercicio = findViewById(R.id.btTelaAdicionarExercicio);
        btDeletarTreino = findViewById(R.id.btDeletarTreino);
        criarListeners();
        reciclador();
        popular();
        atualizarTitulo();
    }

    private void setTexts(){
        TnomeTreino = findViewById(R.id.ptAddNomeTreino);
        TdescricaoTreino = findViewById(R.id.ptAddDescricaoTreino);
        TduracaoTreino = findViewById(R.id.ptAddDuracaoTreino);
    }

    private void criarListeners() {
        botaoExercicios();
        btAdicionarTreino();

        ec = new TreinoController(getApplicationContext());
    }

    private void popular(){
        DatabaseReference exercicioDB= bancoDeDados.child("Exercicios");
        Query pesquisaExercicio = exercicioDB.orderByChild("id");
        pesquisaExercicio.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null){
                    for(DataSnapshot dt: snapshot.getChildren()){
                        Exercicio a = dt.getValue(Exercicio.class);
                        listaExercicio.add(a);
                    }
                }
                reciclador();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void reciclador() {
        //pega os dados e joga pro adp.
        recyclerView = findViewById(R.id.recyclerView);
        adaptador = new adapterListarExercicioTreino(listaExercicio);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(adaptador);


    }

    public void recicladorListenerView(){
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        abrirExercicio(listaExercicio.get(position));
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        abrirExercicio(listaExercicio.get(position));
                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                })
        );
    }

    public void abrirExercicio(Exercicio e){
        Intent alterarTreino = new Intent(getApplicationContext(), CriaExercicioView.class);
        alterarTreino.putExtra("Titulo", getString(R.string.TITULO_ALUNO_VISUALIZA_EXERCICIO));
        alterarTreino.putExtra("nome", e.getNome());
        alterarTreino.putExtra("id", e.getId());
        alterarTreino.putExtra("descricao", e.getDescricao());
        alterarTreino.putExtra("peso", e.getPeso());
        alterarTreino.putExtra("duracao", e.getDuracao());
        alterarTreino.putExtra("repeticao", e.getRepeticoes());
        alterarTreino.putExtra("series", e.getSeries());
        alterarTreino.putExtra("tipo", e.getTipo());
        startActivity(alterarTreino);
    }

    public void recicladorListenerEdit(){
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        atualizaLista(listaExercicio.get(position));
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        atualizaLista(listaExercicio.get(position));
                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                })
        );
    }

    public void atualizaLista(Exercicio s) {
        if (!selecionados.remove(s)) {
            selecionados.add(s);
        }

        x = 0;
        y = "";
        z = "";
        for (int i = 0; i < selecionados.size(); i++) {
            Exercicio ss = selecionados.get(i);
            if (i + 1 == selecionados.size()) {
                z = z + ss.getId();
                y = y + ss.getNome();

            } else {
                z = z + ss.getId() + ",";
                y = y + ss.getNome() + ",";

            }
        }

        TextView newTreino = findViewById(R.id.tvExerciciosTreino);
        if(y.equals("")){y="Escolha pelo menos um exercicios";}
        newTreino.setText(y);
    }

    private void botaoExercicios() {
        btTelaExercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), CriaExercicioView.class);
                intent.putExtra("Titulo", getString(R.string.TITULO_TREINADOR_CRIA_EXERCICIO));
                startActivity(intent);
            }
        });
    }

    private boolean getDados() {
        nomeTreino = TnomeTreino.getText().toString();
        descricaoTreino = TdescricaoTreino.getText().toString();
        if (!TduracaoTreino.getText().toString().equals("")) {
            duracaoTreino = Integer.parseInt(TduracaoTreino.getText().toString());
        }

        if (nomeTreino.equals("") || descricaoTreino.equals("") || TduracaoTreino.getText().toString().equals("")) {
            //toast mandando preencher tudo
            Toast toast = Toast.makeText(getApplicationContext(), "Algum Campo Esta Sem Dados!", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        } else {
            return true;
        }
    }

    private void btAdicionarTreino() {
        btAdicionarTreino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!titulo.equals(getString(R.string.TITULO_TREINADOR_CRIA_TREINO))) {
                    if (!selecionados.isEmpty() && !TnomeTreino.getText().toString().equals("") && !TduracaoTreino.getText().toString().equals("") && !TdescricaoTreino.getText().toString().equals("")) {
                        if (getDados()) {
                            if (ec.validarTreino(nomeTreino, duracaoTreino, descricaoTreino)) {
                                Treino ex = new Treino(nomeTreino, duracaoTreino, descricaoTreino);
                                if (ex != null) {
                                    /*nome, peso, tipo,  repeticoes,  series, descricao, duracao*/
                                    String mGroupId = bancoDeDados.child("Treinos").push().getKey();
                                    ex.setId(mGroupId);
                                    bancoDeDados.child("Treinos").child(mGroupId).setValue(ex);
                                    criaConexaoExercicioTreino(ex);
                                    Toast toast = Toast.makeText(getApplicationContext(), "Salvo com sucesso", Toast.LENGTH_SHORT);
                                    toast.show();
                                    finish();
                                }
                            } else {
                                Toast toast = Toast.makeText(getApplicationContext(), "Houve um problema ao salvar", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }
                    } else {
                        Toast toast = Toast.makeText(CriaTreinosView.this, "há campos não preenchidos", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                } else {
                    if (!selecionados.isEmpty() && !TnomeTreino.getText().toString().equals("") && !TduracaoTreino.getText().toString().equals("") && !TdescricaoTreino.getText().toString().equals("")) {
                        if (getDados()) {
                            Treino ex = new Treino(nomeTreino, duracaoTreino, descricaoTreino);
                            if (ex != null) {
                                ex.setId(id);
                                bancoDeDados.child("Treinos").child(id).setValue(ex);
                                repopularExercicios();
                                Toast toast = Toast.makeText(getApplicationContext(), "Salvo com sucesso", Toast.LENGTH_SHORT);
                                toast.show();
                                z = "";
                                finish();
                            }
                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), "Houve um problema ao salvar", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    } else {
                        Toast toast = Toast.makeText(CriaTreinosView.this, "há campos não preenchidos", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });
    }

    public void repopularExercicios(){
        listaTreExe = z.split(",");
        for(String s : listaTreExe){
            DatabaseReference DB = bancoDeDados.child("TreinoExercicios");
            Query procuraTreino = DB.orderByChild("treinoID").equalTo(id);
            procuraTreino.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.getValue()!=null){
                        boolean continua = false;
                        for(DataSnapshot dt: snapshot.getChildren()){
                            TreinoExercicio a = dt.getValue(TreinoExercicio.class);
                            if(a.getExercicioID().equals(s)) {
                                continua = true;
                            }
                        }
                        if(!continua){
                            TreinoExercicio ex = new TreinoExercicio(s ,id);
                            String mGroupId = bancoDeDados.child("TreinoExercicios").push().getKey();
                            bancoDeDados.child("TreinoExercicios").child(mGroupId).setValue(ex);
                        }
                        for(DataSnapshot dt: snapshot.getChildren()){
                            boolean deleta = true;
                            for (String s : listaTreExe){
                                TreinoExercicio a = dt.getValue(TreinoExercicio.class);
                                if(a.getExercicioID().equals(s)) {
                                    deleta = false;
                                }
                            }
                            if(deleta){
                                DB.child(dt.getKey()).removeValue();
                            }
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    public void btDeletarTreino(){
        btDeletarTreino.setVisibility(View.VISIBLE);
        btDeletarTreino.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(ec.deletarTreino(nomeTreino, duracaoTreino, descricaoTreino, id)){
                    deletarRelacionamentos(false);
                    Toast toast = Toast.makeText(getApplicationContext(), "Removido com sucesso", Toast.LENGTH_SHORT);
                    toast.show();
                    finish();
                }
            }
        });
    }


    public void deletarRelacionamentos(boolean recriar){
        DatabaseReference DB = bancoDeDados.child("TreinoExercicios");
        Query procuraTreino = DB.orderByChild("treinoID").equalTo(id);
        procuraTreino.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null){
                    for(DataSnapshot dt: snapshot.getChildren()){
                        TreinoExercicio a = dt.getValue(TreinoExercicio.class);
                        DB.child(dt.getKey()).removeValue();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void criaConexaoExercicioTreino(Treino tre){
        listaTreExe = z.split(",");
        String idTreino = tre.getId();
        for (String s : listaTreExe){
            TreinoExercicio ex = new TreinoExercicio(s ,idTreino);
            String mGroupId = bancoDeDados.child("TreinoExercicios").push().getKey();
            bancoDeDados.child("TreinoExercicios").child(mGroupId).setValue(ex);
        }
    }

    private void atualizarTitulo(){
        //TITULO ERRADO AMIGO
        Bundle b = new Bundle();
        b = getIntent().getExtras();
        titulo = b.getString("Titulo");
        TextView t = findViewById(R.id.tvTituloAdicionaTreino);
        t.setText(titulo);

        if(titulo.equals(getString(R.string.TITULO_ALUNO_VISUALIZA_TREINO))){
            recicladorListenerView();
            visualizandoTreino(b);
        }else{
            recicladorListenerEdit();
        }
        if(titulo.equals(getString(R.string.TITULO_TREINADOR_CRIA_TREINO))){
            editandoTreino(b);
        }

    }

    private void editandoTreino(Bundle bundle){
        btDeletarTreino();
        TnomeTreino.setText(bundle.getString("nomeTreino"));
        TdescricaoTreino.setText(bundle.getString("descricaoTreino"));
        TduracaoTreino.setText((Integer.toString(bundle.getInt("duracaoTreino"))));
        id = bundle.getString("id");
        populaExercicioEdit(id);
    }

    private void visualizandoTreino(Bundle bundle){
        btAdicionarTreino.setVisibility(View.GONE);
        btTelaExercicio.setVisibility(View.GONE);
        TnomeTreino.setText(bundle.getString("nomeTreino"));
        TnomeTreino.setEnabled(false);
        TdescricaoTreino.setText(bundle.getString("descricaoTreino"));
        TdescricaoTreino.setEnabled(false);
        TduracaoTreino.setText((Integer.toString(bundle.getInt("duracaoTreino"))));
        TduracaoTreino.setEnabled(false);
        id = bundle.getString("id");
        populaExercicioView(id);
    }

    private void populaExercicioEdit(String id){
        DatabaseReference DB = bancoDeDados.child("TreinoExercicios");
        Query procuraTreino = DB.orderByChild("treinoID").equalTo(id);
        List<String> l = new ArrayList<String>();
        procuraTreino.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null){
                    for(DataSnapshot dt: snapshot.getChildren()){
                        TreinoExercicio a = dt.getValue(TreinoExercicio.class);
                        l.add(a.getExercicioID());
                    }
                }
                populaRecicleEdit(l);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void populaExercicioView(String id){
        DatabaseReference DB = bancoDeDados.child("TreinoExercicios");
        Query procuraTreino = DB.orderByChild("treinoID").equalTo(id);
        List<String> l = new ArrayList<String>();
        procuraTreino.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null){
                    for(DataSnapshot dt: snapshot.getChildren()){
                        TreinoExercicio a = dt.getValue(TreinoExercicio.class);
                        l.add(a.getExercicioID());
                    }
                }
                System.out.println("qqqqqqqqqqqq"+l.size());
                populaRecicleView(l);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void populaRecicleEdit(List<String> l){
        for(String key : l) {
            for (Exercicio e : listaExercicio){
                if(e.getId().equals(key)){
                    atualizaLista(e);
                }
            }
        }
    }

    private void populaRecicleView(List<String> l){
        List<Exercicio> listaTemp = new ArrayList<Exercicio>();
        for(String key : l) {
            for (Exercicio e : listaExercicio){
                if(e.getId().equals(key)){
                    listaTemp.add(e);
                }
            }
        }

        listaExercicio.clear();
        listaExercicio.addAll(listaTemp);
        adaptador = new adapterListarExercicioTreino(listaExercicio);
        recyclerView.setAdapter(adaptador);
    }

}
