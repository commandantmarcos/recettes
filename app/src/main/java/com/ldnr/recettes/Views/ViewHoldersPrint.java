package com.ldnr.recettes.Views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ldnr.recettes.Beans.Recipe;
import com.ldnr.recettes.Beans.Step;
import com.ldnr.recettes.R;
import com.squareup.picasso.Picasso;


public class ViewHoldersPrint extends RecyclerView.ViewHolder {

    private TextView step_descr;
    private TextView step_num;
    private ImageView step_pic;

    public ViewHoldersPrint(View itemView) {
        super(itemView);

        /*step_descr = itemView.findViewById(R.id.p_step_description);
        step_num = itemView.findViewById(R.id.p_step_title);
        step_pic = itemView.findViewById(R.id.p_step_picture);*/
    }

    //puis ajouter une fonction pour remplir la cellule en fonction d'un MyObject
    public void bind(Step step){
        step_descr.setText(step.getStep_description());
        step_num.setText("ETAPE n° : " + Integer.toString(step.getStep_num()));

        // Ici nous utilisons Picasso binder l'image depuis une URL à notre imageView.
        Picasso.with(step_pic.getContext()).load(step.getUrl_step()).centerCrop().fit().into(step_pic);

    }
}
