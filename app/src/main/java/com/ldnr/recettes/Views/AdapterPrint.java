package com.ldnr.recettes.Views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ldnr.recettes.Beans.Recipe;
import com.ldnr.recettes.Beans.Step;
import com.ldnr.recettes.R;

import java.util.List;

public class AdapterPrint extends RecyclerView.Adapter<ViewHoldersPrint>{
    private List<Step> list;

    //ajouter un constructeur prenant en entrée une liste
    public AdapterPrint(List<Step> list) {
        this.list = list;
    }

    //cette fonction permet de créer les viewHolder
    //et par la même indiquer la vue à inflater (à partir des layout xml)
    @Override
    public ViewHoldersPrint onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_step,viewGroup,false);
        return new ViewHoldersPrint(view);
    }

    @Override
    public void onBindViewHolder(ViewHoldersPrint holder, int position) {
        Step myObject = list.get(position);
        holder.bind(myObject);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
