package com.mobileia.trivia.rest;

import android.content.Context;

import com.mobileia.authentication.MobileiaAuth;
import com.mobileia.core.Mobileia;
import com.mobileia.core.rest.RestBody;
import com.mobileia.core.rest.RestBodyCall;
import com.mobileia.core.rest.RestBuilder;
import com.mobileia.trivia.MobileiaTrivia;
import com.mobileia.trivia.entity.Trivia;
import com.mobileia.trivia.rest.service.TriviaService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by matiascamiletti on 3/2/18.
 */

public class TriviaRest extends RestBuilder {
    /**
     * Almacena el contexto
     */
    protected Context mContext;

    /**
     * Constructor
     * @param context
     */
    public TriviaRest(Context context){
        super();
        mContext = context;
    }

    /**
     * Servicio para obtener las trivias disponibles
     * @param callback
     */
    public void fetchAllCurrent(final OnFetchAllComplete callback){
        // Creamos el servicio
        TriviaService service = createService(TriviaService.class);
        // Generamos la call
        RestBodyCall<ArrayList<Trivia>> call = service.fetchAllCurrent(Mobileia.getInstance().getAppId(), getAccessToken());
        // Ejecutamos call
        call.enqueue(new Callback<RestBody<ArrayList<Trivia>>>() {
            @Override
            public void onResponse(Call<RestBody<ArrayList<Trivia>>> call, Response<RestBody<ArrayList<Trivia>>> response) {
                // Verificar si la respuesta fue incorrecta
                if (!response.isSuccessful() || !response.body().success) {
                    //callback.onError(response.body().error);
                    return;
                }
                // Enviamos las trivias
                callback.onSuccess(response.body().response);
            }

            @Override
            public void onFailure(Call<RestBody<ArrayList<Trivia>>> call, Throwable t) {

            }
        });
    }

    /**
     * Devuelve le AccessToken del usuario logueado
     * @return
     */
    public String getAccessToken(){
        //return "3be8b617e076b96b2b0fa6369b6c72ed84318d72";
        return MobileiaAuth.getInstance(mContext).getCurrentUser().getAccessToken();
    }

    /**
     * Metodo que devuelve la URL base
     * @return
     */
    @Override
    public String getBaseUrl() {
        return MobileiaTrivia.getBaseUrl();
    }

    /**
     * Interface para obtener las trivias
     */
    public interface OnFetchAllComplete{
        void onSuccess(ArrayList<Trivia> trivias);
    }
}
