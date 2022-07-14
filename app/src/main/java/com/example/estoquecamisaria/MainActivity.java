package com.example.estoquecamisaria;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.estoquecamisaria.camisa.MainFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /* Método que faz parte do ciclo de vida da activity
        responsavel por carregar layouts e outras operações de inicialização
        Bundle = Classe que guarda objetos na forma de pares chave/valor. Responsável por guardar o estado da Activity quando reiniciada
        savedInstanceState = parametro que contém o estado anterior salvo da atividade */
        super.onCreate(savedInstanceState);//metodo da classe mãe para que a base seja criada
        setContentView(R.layout.activity_main); //responsável por configurar o layout e definir todos os elementos de interface

        if(savedInstanceState == null){
            /*verifica se a Activity será criada pela primeira vez
            getSupportFragmentManager = acessa a classe FragmentManager que permite adicionar, remover uma fragmente no layout da activity dinamicamente
            beginTransaction = usado quando queremos iniciar uma nova transação com a base de dados à qual estamos conectados no momento.
            replace = Retorna um novo objeto contendo a string original com um trecho especificado substituído por outra expressão indicada.
            commit = salvar todas as alterações providas de um comando do tipo DML
            */
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, new MainFragment()).commit();
        }
    }
}
