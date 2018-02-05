package com.mobileia.trivia.view.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobileia.recyclerview.holder.BaseViewHolder;
import com.mobileia.trivia.R;
import com.mobileia.trivia.entity.Option;
import com.mobileia.trivia.entity.Trivia;

/**
 * Created by matiascamiletti on 4/2/18.
 */

public class TriviaViewHolder extends BaseViewHolder<Trivia> {

    public final TextView dateView;
    public final ImageView imageView;
    public final TextView titleView;
    public final LinearLayout containerOptions;
    /**
     * Almacena la trivia que se esta viendo
     */
    protected Trivia mTrivia;

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
        containerOptions = itemView.findViewById(R.id.container_options);
    }

    @Override
    public void bind(Trivia object) {
        // Guardamos trivia
        mTrivia = object;
        // Cargamos el titulo
        titleView.setText(object.title);
        // Cargamos la fecha de finalizacion
        if(object.end_date != null){
            dateView.setText(object.end_date.toString());
        }else{
            dateView.setText("");
        }
        // Verificamos si tiene foto
        if(object.photo != null && object.photo.length() > 0){
            imageView.setVisibility(View.VISIBLE);
        }else{
            imageView.setVisibility(View.GONE);
        }
        // Cargar opciones
        loadOptions();
    }

    /**
     * Funcion que se encarga de cargar las opciones de la trivia
     */
    protected void loadOptions(){
        // Limpiamos el contenedor
        containerOptions.removeAllViews();
        // Verificamos si tiene opciones
        if(mTrivia.options == null){
            return;
        }
        // Recorremos las opciones
        for (Option o: mTrivia.options) {
            // Imprimimos opcion
            addOption(o);
        }
    }

    /**
     * Funcion que se encarga de agregar una opcion a la vista
     * @param option
     */
    protected void addOption(Option option){
        // Inflamos vista de la opcion
        View view = LayoutInflater.from(itemView.getContext()).inflate(R.layout.item_option, containerOptions, false);
        // Configuramos titulo
        ((TextView)view.findViewById(R.id.text_title)).setText(option.title);
        // agregamos al layout
        containerOptions.addView(view);
    }
}
