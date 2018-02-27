package com.mobileia.trivia.view.holder;

import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mobileia.core.helper.DateHelper;
import com.mobileia.recyclerview.holder.BaseViewHolder;
import com.mobileia.trivia.R;
import com.mobileia.trivia.entity.Option;
import com.mobileia.trivia.entity.Trivia;
import com.mobileia.trivia.rest.TriviaRest;

/**
 * Created by matiascamiletti on 4/2/18.
 */

public class TriviaViewHolder extends BaseViewHolder<Trivia> implements View.OnClickListener, TriviaRest.OnVoteComplete {

    public final TextView dateView;
    public final ImageView imageView;
    public final TextView titleView;
    public final LinearLayout containerOptions;
    /**
     * Almacena la trivia que se esta viendo
     */
    protected Trivia mTrivia;
    /**
     * Almacena el Ultimo ID de opcion seleccionado
     */
    protected int mLastOptionId = -1;

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
            dateView.setText("Hasta: " + DateHelper.stringToFormat(object.end_date, "yyyy-MM-dd", "d 'de' MMM 'de' yyyy"));
        }else{
            dateView.setText("");
        }
        // Verificamos si tiene foto
        if(object.photo != null && object.photo.length() > 0){
            Glide.with(imageView).load(object.photo).into(imageView);
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
            // Verificamos si ya tiene votaciona
            if(mTrivia.vote > 0){
                addOptionResult(o);
            }else{
                // Imprimimos opcion
                addOption(o);
            }
        }
    }

    /**
     * Funcion que se encarga de agregar las opciones con sus resultados
     * @param option
     */
    protected void addOptionResult(Option option){
        // Obtenemos recursos
        Resources resources = itemView.getContext().getResources();
        // Inflamos vista de la opcion
        View view = LayoutInflater.from(itemView.getContext()).inflate(R.layout.item_option_result, containerOptions, false);
        // Configuramos titulo
        ((TextView)view.findViewById(R.id.text_title)).setText(option.title);
        // Asignamos los puntos
        ((TextView)view.findViewById(R.id.text_points)).setText(String.valueOf(option.total));
        // Cambiamos el color de fondo
        if(option.is_correct == 1){
            ((CardView)view.findViewById(R.id.card_view)).setCardBackgroundColor(resources.getColor(R.color.option_correct));
        }else if(mTrivia.vote == option.id){
            ((CardView)view.findViewById(R.id.card_view)).setCardBackgroundColor(resources.getColor(R.color.option_incorrect_alpha));
        }else{
            ((CardView)view.findViewById(R.id.card_view)).setCardBackgroundColor(resources.getColor(R.color.option_normal_alpha));
        }
        // agregamos al layout
        containerOptions.addView(view);
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
        // Asignamos el ID de la opcion
        view.setTag(option.id);
        // Asignamos el clik
        view.setOnClickListener(this);
        // agregamos al layout
        containerOptions.addView(view);
    }

    /**
     * Funcion que se ejecuta cuando se hace un click en una opcion
     * @param view
     */
    @Override
    public void onClick(View view) {
        // Obtenemos el ID del option
        mLastOptionId = (int)view.getTag();
        // Realizamos la votacion
        new TriviaRest(itemView.getContext()).vote(mTrivia.id, mLastOptionId, this);
    }

    /**
     * Retorna el valor si se realizo una votacion
     * @param response
     */
    @Override
    public void onSuccess(Boolean response) {
        // Verificamos si se produjo algun error
        if(response == false){
            return;
        }
        // Asignamos que ya se hizo una votacion
        mTrivia.vote = mLastOptionId;
        // Refrescamos el listado
        notifyDataSetChanged();
    }
}
