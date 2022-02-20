package com.solagna.haltere_se20.View;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.solagna.haltere_se20.Controller.TreinadorController;
import com.solagna.haltere_se20.Model.Aluno;
import com.solagna.haltere_se20.Model.Treinador;
import com.solagna.haltere_se20.R;

import java.util.ArrayList;
import java.util.List;

public class CriaEditaTreinadorView extends AppCompatActivity {
    private Button btAdicionarTreinador;
    private String nome, cpf, senha, email;
    private int salario;
    private TreinadorController ec;
    EditText Tnome, Tcpf, Tsenha, Temail, Tsalario;
    private String titulo;
    private FirebaseAuth auth;
    private DatabaseReference bancoDeDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        auth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.tela_treinador_cadastra_treinadores);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        bancoDeDados = FirebaseDatabase.getInstance().getReference().child("Pessoas");
        btAdicionarTreinador = findViewById(R.id.btAdicionarTreinador);
        atualizarTitulo();
        criarListeners();
    }

    private void criarListeners() {

        btAdicionarTreinador();
        ec = new TreinadorController(getApplicationContext());
    }

    private boolean getDados() {
        Tnome = findViewById(R.id.ptAddNomeTreinador);
        nome = Tnome.getText().toString();

        Temail = findViewById(R.id.ptAddEmailTreinador);
        email = Temail.getText().toString();

        Tsalario = findViewById(R.id.ptAddSalarioTreinador);
        if (!Tsalario.getText().toString().equals("")) {
            salario = Integer.parseInt(Tsalario.getText().toString()); }

        Tcpf = findViewById(R.id.ptAddCPFTreinador);
        cpf = Tcpf.getText().toString();

        Tsenha = findViewById(R.id.ptAddSenhaTreinador);
        senha = Tsenha.getText().toString();

        if (nome.equals("") || email.equals("")
                || Tsalario.getText().toString().equals("")
                || cpf.equals("")
                || senha.equals("")) {
            //toast mandando preencher tudo
            Toast toast = Toast.makeText(getApplicationContext(), "Algum Campo Esta Sem Dados!", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        } else {
            return true;
        }
    }

    private void btAdicionarTreinador() {
        btAdicionarTreinador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getDados()) {
                    if (titulo.equals("Adicionar Treinador")) {
                        auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Treinador t = new Treinador(nome, cpf, senha, email, salario);
                                    String mGroupId = bancoDeDados.child("Treinadores").push().getKey();
                                    t.setId(mGroupId);
                                    bancoDeDados.child("Treinadores").child(mGroupId).setValue(t);
                                    Toast toast = Toast.makeText(CriaEditaTreinadorView.this, "Salvo com sucesso", Toast.LENGTH_SHORT);
                                    toast.show();
                                    finish();
                                } else {
                                    String erro;
                                    try {
                                        throw task.getException();
                                    } catch (FirebaseAuthUserCollisionException e) {
                                        erro = "usuario ja registrado";
                                    } catch (FirebaseAuthWeakPasswordException e) {
                                        erro = "senha muito fraca";
                                    } catch (FirebaseAuthInvalidCredentialsException e) {
                                        erro = "email invalido";
                                    } catch (Exception e) {
                                        erro = "erro desconhecido";
                                    }

                                    Snackbar snackbar = Snackbar.make(view, erro, Snackbar.LENGTH_SHORT);
                                    snackbar.setBackgroundTint(Color.BLACK);
                                    snackbar.setTextColor(Color.WHITE);
                                    snackbar.show();
                                    Log.i("adm", "cadastro falhou");
                                }
                            }
                        });
                    } else {
                        DatabaseReference treinadores = bancoDeDados.child("Treinadores");
                        Query pesquisaTreinador = treinadores.orderByChild("cpf").equalTo(cpf);
                        Treinador t = new Treinador(nome, cpf, senha, email, salario);
                        pesquisaTreinador.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.getValue()!=null){
                                    List<Treinador> listaTreinador= new ArrayList<>();
                                    for(DataSnapshot dt: snapshot.getChildren()){
                                        Treinador treinador =  dt.getValue(Treinador.class);
                                        treinador.setId(dt.getKey());
                                        listaTreinador.add(treinador);
                                    }
                                    treinadores.child(listaTreinador.get(0).getId()).setValue(t);
                                    finish();
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }
                }
            }
        });
    }



    private void atualizarTitulo(){
        Bundle b = new Bundle();
        b = getIntent().getExtras();
        titulo = b.getString("titulo");
        TextView t = findViewById(R.id.tvAddTreino);
        t.setText(titulo);

        if(titulo.equals("Alterar Treinador")){
            editandoTreinador(b);
        }
    }

    private void editandoTreinador(Bundle bundle){
        getDados();

        Tnome.setText(bundle.getString("nome"));

        Temail.setText(bundle.getString("email"));
        Temail.setEnabled(false);

        Tsalario.setText(Integer.toString(bundle.getInt("salario")));

        Tcpf.setText(bundle.getString("cpf"));
        Tcpf.setEnabled(false);

        Tsenha.setText(bundle.getString("senha"));
        Tsenha.setEnabled(false);
    }

}
