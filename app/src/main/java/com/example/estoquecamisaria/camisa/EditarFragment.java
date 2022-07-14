package com.example.estoquecamisaria.camisa;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.estoquecamisaria.R;
import com.example.estoquecamisaria.database.DatabaseHelper;

public class EditarFragment extends Fragment {
    private EditText etTipo;
    private EditText etTamanho;
    private EditText etCor;
    private DatabaseHelper databaseHelper;
    private Camisa c;

    public EditarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.camisa_fragment_editar, container, false);
        etTipo = v.findViewById(R.id.editText_tipo);
        etTamanho = v.findViewById(R.id.editText_tamanho);
        etCor = v.findViewById(R.id.editText_cor);
        Bundle b = getArguments();
        int id_camisa = b.getInt("id");
        databaseHelper = new DatabaseHelper(getActivity());
        c = databaseHelper.getByIdCamisa(id_camisa);
        etTipo.setText(c.getTipo());
        etTamanho.setText(c.getTamanho());
        etCor.setText(c.getCor());
        Button btnEditar = v.findViewById(R.id.button_editar_camisa);
        btnEditar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                editar(id_camisa);
            }
        });
        Button btnExcluir = v.findViewById(R.id.button_excluir_camisa);
        btnExcluir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.certeza_msm);
                builder.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        excluir(id_camisa);
                    }
                });
                builder.setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //nada a fazer
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        return  v;
    }

    private void editar(int id){
        if(etTipo.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Por favor, informar o tipo de camisaria", Toast.LENGTH_LONG).show();
        }else if(etTamanho.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Por favor, informar o tamanho", Toast.LENGTH_LONG).show();
        }else if(etCor.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Por favor, informar a cor", Toast.LENGTH_LONG).show();
        }else{
            c = new Camisa();
            c.setId(id);
            c.setTipo(etTipo.getText().toString());
            c.setTamanho(etTamanho.getText().toString());
            c.setCor(etCor.getText().toString());
            databaseHelper.updateCamisas(c);//método update, que logo chamará a activity responsável por exibir os dados na tela
            Toast.makeText(getActivity(), "Camisa atualizada", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_camisa,new ListarFragment()).commit();
        }
    }

    private void excluir(int id){
        c = new Camisa();
        c.setId(id);
        databaseHelper.deleteCamisas(c);//método delete, que logo chamará a activity responsável por confirmar a deleção
        Toast.makeText(getActivity(), "Camisa excluída", Toast.LENGTH_LONG).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_camisa,new ListarFragment()).commit();
    }
}