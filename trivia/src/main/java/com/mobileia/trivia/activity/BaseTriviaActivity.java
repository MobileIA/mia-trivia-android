package com.mobileia.trivia.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.mobileia.recyclerview.MobileiaRecyclerView;
import com.mobileia.trivia.R;
import com.mobileia.trivia.adapter.TriviaAdapter;
import com.mobileia.trivia.entity.Trivia;
import com.mobileia.trivia.rest.TriviaRest;

import java.util.ArrayList;

/**
 * Created by matiascamiletti on 4/2/18.
 */

abstract public class BaseTriviaActivity extends AppCompatActivity implements TriviaRest.OnFetchAllComplete, TriviaAdapter.OnVoteTriviaListener {
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

    @Override
    public void onSuccess(ArrayList<Trivia> trivias) {
        // Asignamos las trivias al adapter
        mAdapter.addAll(trivias);
        // Paramos el loading
        mRecyclerView.stopLoading();
        // Verificar si no esta vacia
        if(trivias.size() == 0){
            mRecyclerView.showEmptyView();
        }
    }

    @Override
    public void onVote(Trivia trivia) {

    }

    /**
     * Funcion que se encarga de llamar al servidor en busca de las trivias
     */
    protected void fetchTrivias(){
        // Iniciamos el loading
        mRecyclerView.startLoading();
        // Llamar al servidor
        new TriviaRest(this).fetchAllCurrent(this);
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
        mAdapter.setOnVoteListener(this);
        // Asignamos el adapter
        mRecyclerView.setAdapter(mAdapter);
        // Asignamos vista vacia
        mRecyclerView.setEmptyView(R.layout.partial_trivia_empty);
        // Inicamos proceso para buscar las trivias
        fetchTrivias();
    }

    /**
     * Metodo para obtener el listado desde al vista
     * @return
     */
    abstract protected MobileiaRecyclerView findRecyclerView();
}
