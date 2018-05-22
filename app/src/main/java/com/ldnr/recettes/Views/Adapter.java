package com.ldnr.recettes.Views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ldnr.recettes.Beans.Recipe;
import com.ldnr.recettes.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<ViewHolders>{
    private List<Recipe> list;

    //ajouter un constructeur prenant en entrée une liste
    public Adapter(List<Recipe> list) {
        this.list = list;
    }

    //cette fonction permet de créer les viewHolder
    //et par la même indiquer la vue à inflater (à partir des layout xml)
    @Override
    public ViewHolders onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_cards,viewGroup,false);
        return new ViewHolders(view);
    }

    @Override
    public void onBindViewHolder(ViewHolders holder, int position) {
        Recipe myObject = list.get(position);
        holder.bind(myObject);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
