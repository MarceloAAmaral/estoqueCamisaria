package com.example.estoquecamisaria.camisa;

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



public class AdicionarFragment extends Fragment {
    private EditText etTipo;
    private EditText etTamanho;
    private EditText etCor;


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
        View v = inflater.inflate(R.layout.camisa_fragment_adicionar, container, false);
        etTipo = v.findViewById(R.id.editText_tipo);
        etTamanho = v.findViewById(R.id.editText_tamanho);
        etCor = v.findViewById(R.id.editText_cor);
        Button btnAdicionar = v.findViewById(R.id.button_adicionar_camisa);
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
        }else{
            DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
            Camisa c = new Camisa();
            c.setTipo(etTipo.getText().toString());
            c.setTamanho(etTamanho.getText().toString());
            c.setCor(etCor.getText().toString());
            databaseHelper.createCamisas(c);
            Toast.makeText(getActivity(), "Camisa salva", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_camisa,new ListarFragment()).commit();
        }
    }

}