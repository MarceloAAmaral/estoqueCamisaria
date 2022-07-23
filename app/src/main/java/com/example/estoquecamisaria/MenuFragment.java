package com.example.estoquecamisaria;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


public class MenuFragment extends Fragment {

    public MenuFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.menu_camisas:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, new com.example.estoquecamisaria.camisa.MainFragment()).commit();
/*                Toast.makeText(getActivity(), "Menu Camisa"+item, Toast.LENGTH_LONG).show();*/
                break;
            case R.id.menu_camisetas:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, new com.example.estoquecamisaria.camiseta.MainFragment()).commit();
                /*Toast.makeText(getActivity(), "Menu Camisetas"+item, Toast.LENGTH_LONG).show();*/
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}