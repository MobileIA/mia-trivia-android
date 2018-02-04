package com.mobileia.trivia.adapter;

import com.mobileia.recyclerview.adapter.BuilderAdapter;
import com.mobileia.trivia.R;
import com.mobileia.trivia.view.holder.TriviaViewHolder;

/**
 * Created by matiascamiletti on 4/2/18.
 */

public class TriviaAdapter extends BuilderAdapter {

    public TriviaAdapter(){
        super(R.layout.item_trivia, TriviaViewHolder.class);
    }
}
