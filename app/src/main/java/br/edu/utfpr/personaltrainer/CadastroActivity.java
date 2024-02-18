package br.edu.utfpr.personaltrainer;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class CadastroActivity extends AppCompatActivity {

    public static final String NOME = "NOME";
    public static final String EMAIL = "EMAIL";
    public static final String ACADEMIA = "ACADEMIA";
    public static final String GENERO = "GENERO";


    private EditText editTextNome, editTextEmail;
    private CheckBox cb_segunda, cb_terca, cb_quarta, cb_quinta, cb_sexta, cb_sabado, cb_domingo;
    private RadioGroup radioGroupSexo;
    private Spinner spinnerAcademia;

    public static void novoCadastro(AppCompatActivity activity,
                                    ActivityResultLauncher<Intent> launcher){
        Intent intent = new Intent(activity, CadastroActivity.class);

        launcher.launch(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        editTextNome = findViewById(R.id.editTextNome);
        editTextEmail = findViewById(R.id.editTextEmail);

        cb_segunda = findViewById(R.id.checkbox_segunda);
        cb_terca = findViewById(R.id.checkbox_terca);
        cb_quarta = findViewById(R.id.checkbox_quarta);
        cb_quinta = findViewById(R.id.checkbox_quinta);
        cb_sexta = findViewById(R.id.checkbox_sexta);
        cb_sabado = findViewById(R.id.checkbox_sabado);
        cb_domingo = findViewById(R.id.checkbox_domingo);

        radioGroupSexo = findViewById(R.id.radioGroupGenero);
        spinnerAcademia = findViewById(R.id.spinnerAcademia);

        popularSpinner();
    }

    public void limparCampos(View view){
        editTextNome.setText(null);
        editTextEmail.setText(null);
        editTextNome.requestFocus();
        radioGroupSexo.clearCheck();
        cb_segunda.setChecked(false);
        cb_terca.setChecked(false);
        cb_quarta.setChecked(false);
        cb_quinta.setChecked(false);
        cb_sexta.setChecked(false);
        cb_sabado.setChecked(false);
        cb_domingo.setChecked(false);

        makeText(this,
                R.string.campos_limpos,
                LENGTH_SHORT).show();
    }

    private void popularSpinner(){
        ArrayList<String> lista = new ArrayList<>();
        lista.add(getString(R.string.utfprgym));
        lista.add(getString(R.string.utfitpr));
        spinnerAcademia.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                lista));
    }

    public void salvar(View view){
        if(editTextNome.getText().toString().equals("")
                || editTextEmail.getText().toString().equals("")){
            Toast.makeText(this,
                    getString(R.string.campo_text_vazio),
                    LENGTH_SHORT).show();
        } else if((!cb_domingo.isChecked() && !cb_segunda.isChecked() && !cb_terca.isChecked()
                && !cb_quarta.isChecked() && !cb_quinta.isChecked() && !cb_sexta.isChecked()
                && !cb_sabado.isChecked())
                ||
                (radioGroupSexo.getCheckedRadioButtonId() == -1)){
            Toast.makeText(this,
                    getString(R.string.campo_check_box_ou_radio_group_vazio),
                    LENGTH_SHORT).show();
        }

        Intent intent = new Intent();
        intent.putExtra(NOME, editTextNome.getText().toString());
        intent.putExtra(EMAIL, editTextEmail.getText().toString());
        spinnerAcademia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView
                    , int position, long id) {
                String valorEscolhido = parentView.getItemAtPosition(position).toString();

                intent.putExtra(ACADEMIA, valorEscolhido);
                Toast.makeText(getApplicationContext()
                        ,"Valor escolhido: " + valorEscolhido, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        radioGroupSexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButtonMasculino:
                        intent.putExtra(GENERO, R.id.radioButtonMasculino);
                        break;

                    case R.id.radioButtonFeminino:
                        intent.putExtra(GENERO, R.id.radioButtonFeminino);
                        break;
                }
            }
        });

        setResult(Activity.RESULT_OK, intent);
        finish();
    }


}