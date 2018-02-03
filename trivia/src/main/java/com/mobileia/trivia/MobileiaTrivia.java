package com.mobileia.trivia;

/**
 * Created by matiascamiletti on 3/2/18.
 */

public class MobileiaTrivia {

    public static String baseUrl;

    /**
     * Inicializa el modulo de trivia con la URL base
     * @param url
     */
    public static void init(String url){
        baseUrl = url;
    }

    /**
     * Devuelve la URL base configurada
     * @return
     */
    public static String getBaseUrl(){
        return baseUrl;
    }
}
