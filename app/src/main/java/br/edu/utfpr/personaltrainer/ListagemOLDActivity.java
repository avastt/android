package br.edu.utfpr.personaltrainer;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListagemOLDActivity extends AppCompatActivity {

    private ListView listViewAlunosOLD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);

        listViewAlunosOLD = findViewById(R.id.listViewAlunos);

        listViewAlunosOLD.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent,
                                            View view, int position, long id) {

                       Aluno aluno = (Aluno) listViewAlunosOLD
                               .getItemAtPosition(position);

                        Toast.makeText(ListagemOLDActivity.this,
                                getString(R.string.foi_clicado) + " " + aluno.getNome(),
                                Toast.LENGTH_SHORT).show();
                    }
                }
        );

        popularLista();
    }

    private void popularLista(){
        String[] nomes = getResources().
                getStringArray(R.array.nomes);
        String[] emails = getResources().
                getStringArray(R.array.emails);
        String[] academias = getResources().
                getStringArray(R.array.academias);
        String[] generos = getResources().
                getStringArray(R.array.generos);

        ArrayList<Aluno> alunos = new ArrayList<>();
        for (int cont = 0; cont < nomes.length; cont++){
            alunos.add(new Aluno(nomes[cont], emails[cont],
                    academias[cont], generos[cont]));
        }

        ArrayAdapter<Aluno> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                alunos);
        listViewAlunosOLD.setAdapter(adapter);
    }

}