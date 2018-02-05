package com.mobileia.trivia.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.mobileia.recyclerview.MobileiaRecyclerView;
import com.mobileia.trivia.adapter.TriviaAdapter;
import com.mobileia.trivia.entity.Trivia;
import com.mobileia.trivia.rest.TriviaRest;

import java.util.ArrayList;

/**
 * Created by matiascamiletti on 4/2/18.
 */

abstract public class BaseTriviaActivity extends AppCompatActivity {
    /**
     * Almacena la vista del listado
     */
    protected MobileiaRecyclerView mRecyclerView;
    /**
     * Almacena el adapter de las trivias
     */
    protected TriviaAdapter mAdapter;

    @Override
    protected void onResume() {
        super.onResume();
        // Verificamos si ya se inicio la vista
        if(mRecyclerView == null){
            setUpViews();
        }
    }

    /**
     * Funcion que se encarga de llamar al servidor en busca de las trivias
     */
    protected void fetchTrivias(){
        // Iniciamos el loading
        mRecyclerView.startLoading();
        // Llamar al servidor
        new TriviaRest(this).fetchAllCurrent(new TriviaRest.OnFetchAllComplete() {
            @Override
            public void onSuccess(ArrayList<Trivia> trivias) {
                // Asignamos las trivias al adapter
                mAdapter.addAll(trivias);
                // Paramos el loading
                mRecyclerView.stopLoading();
            }
        });
    }

    /**
     * Funcion que se encarga de inicializar la vista
     */
    protected void setUpViews(){
        // Obtenemos vista del listado
        mRecyclerView = findRecyclerView();
        // Seteamos el layout
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Creamos adapter
        mAdapter = new TriviaAdapter();
        // Asignamos el adapter
        mRecyclerView.setAdapter(mAdapter);
        // Inicamos proceso para buscar las trivias
        fetchTrivias();
    }

    /**
     * Metodo para obtener el listado desde al vista
     * @return
     */
    abstract protected MobileiaRecyclerView findRecyclerView();
}
