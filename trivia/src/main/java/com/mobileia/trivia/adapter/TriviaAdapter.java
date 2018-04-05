package com.mobileia.trivia.adapter;

import com.mobileia.recyclerview.adapter.BuilderAdapter;
import com.mobileia.trivia.R;
import com.mobileia.trivia.entity.Trivia;
import com.mobileia.trivia.view.holder.TriviaViewHolder;

/**
 * Created by matiascamiletti on 4/2/18.
 */

public class TriviaAdapter extends BuilderAdapter {

    protected OnVoteTriviaListener mListener;

    public TriviaAdapter(){
        super(R.layout.item_trivia, TriviaViewHolder.class);
    }

    public void finishVote(Trivia trivia){
        if(mListener != null){
            mListener.onVote(trivia);
        }
    }

    public void setOnVoteListener(OnVoteTriviaListener listener){
        mListener = listener;
    }

    public interface OnVoteTriviaListener{
        void onVote(Trivia trivia);
    }
}
