package com.example.estoquecamisaria.camiseta;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.example.estoquecamisaria.R;
import com.example.estoquecamisaria.database.DatabaseHelper;

public class AdicionarFragment extends Fragment {
    private EditText etTipo;
    private EditText etTamanho;
    private EditText etCor;
    private EditText etQtde;

    public AdicionarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.camiseta_fragment_adicionar, container, false);
        etTipo = v.findViewById(R.id.editText_tipo);
        etTamanho = v.findViewById(R.id.editText_tamanho);
        etCor = v.findViewById(R.id.editText_cor);
        etQtde = v.findViewById(R.id.editText_qtde);
        Button btnAdicionar = v.findViewById(R.id.button_adicionar_camiseta);
        btnAdicionar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                adicionar();
            }
        });
        return v;
    }

    private void adicionar(){
        if(etTipo.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Por favor, informar o tipo de camisaria", Toast.LENGTH_LONG).show();
        }else if(etTamanho.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Por favor, informar o tamanho", Toast.LENGTH_LONG).show();
        }else if(etCor.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Por favor, informar a cor", Toast.LENGTH_LONG).show();
        }else if(etQtde.getText().toString().equals("")){
            Toast.makeText(getActivity(), "Por favor, informar a qtde", Toast.LENGTH_LONG).show();
        }else{
            DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
            Camiseta e = new Camiseta();
            e.setTipo(etTipo.getText().toString());
            e.setTamanho(etTamanho.getText().toString());
            e.setCor(etCor.getText().toString());
            e.setQtde(etQtde.getText().toString());
            databaseHelper.createCamisetas(e);
            Toast.makeText(getActivity(), "Camiseta salva", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_camiseta,new ListarFragment()).commit();
        }
    }

}