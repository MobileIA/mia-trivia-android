package com.mobileia.trivia.rest.service;

import com.mobileia.core.rest.RestBodyCall;
import com.mobileia.trivia.entity.Trivia;

import java.util.ArrayList;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by matiascamiletti on 3/2/18.
 */

public interface TriviaService {

    @FormUrlEncoded
    @POST("api/trivia/list")
    RestBodyCall<ArrayList<Trivia>> fetchAllCurrent(
            @Field("app_id") int app_id,
            @Field("access_token") String access_token
    );
}
