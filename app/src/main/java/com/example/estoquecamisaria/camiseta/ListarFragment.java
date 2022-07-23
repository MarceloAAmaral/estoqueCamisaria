package com.example.estoquecamisaria.camiseta;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.estoquecamisaria.R;
import com.example.estoquecamisaria.database.DatabaseHelper;


public class ListarFragment extends Fragment {

    public ListarFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.camiseta_fragment_listar, container, false);
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        ListView lv = v.findViewById(R.id.list_view_listar_camisetas);
        databaseHelper.getAllCamisetas(getActivity(), lv);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tvId = view.findViewById(R.id.textViewIdListarCamisetas);
                Bundle b= new Bundle();
                b.putInt("id", Integer.parseInt(tvId.getText().toString()));
                EditarFragment editar = new EditarFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                editar.setArguments(b);
                ft.replace(R.id.frame_camiseta, editar).commit();
            }
        });
        return v;
    }
}