package br.edu.utfpr.personaltrainer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PrincipalActivity extends AppCompatActivity {

    private ListView listViewAlunos;
    private ArrayList<Aluno> listaAlunos;
    private ArrayAdapter<Aluno> listaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        listViewAlunos = findViewById(R.id.listViewAluno);

        popularLista();
    }

    private void popularLista(){
        listaAlunos = new ArrayList<>();

        listaAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                listaAlunos);

        listViewAlunos.setAdapter(listaAdapter);
    }

    ActivityResultLauncher<Intent> launcherNovoCadastro =
            registerForActivityResult
                    (new ActivityResultContracts.StartActivityForResult(),
                            new ActivityResultCallback<ActivityResult>() {
                                @Override
                                public void onActivityResult(ActivityResult result) {
                                    if (result.getResultCode() == Activity.RESULT_OK){

                                        Intent intent = result.getData();
                                        Bundle bundle = intent.getExtras();

                                        if (bundle != null){
                                            String nome = bundle.getString(
                                                    CadastroActivity.NOME);
                                            String email = bundle.getString(
                                                    CadastroActivity.EMAIL);
                                            String academia = bundle.getString(
                                                    CadastroActivity.ACADEMIA);
                                            String genero = bundle.getString(
                                                    CadastroActivity.GENERO);

                                            Aluno aluno = new Aluno(nome, email,
                                                    academia, genero);

                                            listaAlunos.add(aluno);
                                            listaAdapter.notifyDataSetChanged();
                                        }
                                    }

                                }
                            });

    public void novoCadastro(View view){
        CadastroActivity.novoCadastro(this, launcherNovoCadastro);
    }

    public void sobre(View view){
        SobreActivity.nova(this);
    }
}