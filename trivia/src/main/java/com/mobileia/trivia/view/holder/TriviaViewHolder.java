package com.mobileia.trivia.view.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobileia.recyclerview.holder.BaseViewHolder;
import com.mobileia.trivia.R;
import com.mobileia.trivia.entity.Trivia;

/**
 * Created by matiascamiletti on 4/2/18.
 */

public class TriviaViewHolder extends BaseViewHolder<Trivia> {

    public final TextView dateView;
    public final ImageView imageView;
    public final TextView titleView;

    /**
     * Constructor base
     *
     * @param itemView
     */
    public TriviaViewHolder(View itemView) {
        super(itemView);
        dateView = itemView.findViewById(R.id.text_date);
        imageView = itemView.findViewById(R.id.image);
        titleView = itemView.findViewById(R.id.text_title);
    }

    @Override
    public void bind(Trivia object) {
        // Cargamos el titulo
        titleView.setText(object.title);
        // Cargamos la fecha de finalizacion
        dateView.setText(object.end_date.toString());
        // Verificamos si tiene foto
        if(object.photo != null && object.photo.length() > 0){
            imageView.setVisibility(View.VISIBLE);
        }else{
            imageView.setVisibility(View.GONE);
        }
    }
}
