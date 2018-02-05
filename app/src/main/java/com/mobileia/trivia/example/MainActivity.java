package com.mobileia.trivia.example;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mobileia.recyclerview.MobileiaRecyclerView;
import com.mobileia.trivia.MobileiaTrivia;
import com.mobileia.trivia.activity.BaseTriviaActivity;
import com.mobileia.trivia.entity.Trivia;
import com.mobileia.trivia.rest.TriviaRest;

import java.util.ArrayList;

public class MainActivity extends BaseTriviaActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // configuracion Trivias
        MobileiaTrivia.init("http://cooperacionprode.mobileia.com/");
        // Obtener las trivias
        /*new TriviaRest(this).fetchAllCurrent(new TriviaRest.OnFetchAllComplete() {
            @Override
            public void onSuccess(ArrayList<Trivia> trivias) {

                for (Trivia t : trivias){
                    System.out.println("Trivia: " + t.title);
                }
            }
        });*/
    }

    @Override
    protected MobileiaRecyclerView findRecyclerView() {
        return findViewById(R.id.list);
    }
}
