package com.example.lab4;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

public class AllListAdapter extends RecyclerView.Adapter<AllListAdapter.ViewHolder> {
    LinkedList<Integer> dataSet;
    OnItemClickListener mOnItemClickListner;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ConstraintLayout mConstraintLayout;
        public ViewHolder(ConstraintLayout v) {
            super(v);
            mConstraintLayout = v;
        }
    }
    public AllListAdapter(LinkedList<Integer> a) {
        dataSet = a;
    }
    @Override
    public AllListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // create a new view
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.all_list_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void setOnItemClickListner(OnItemClickListener a){
        mOnItemClickListner = a;
    }

    @Override
    public void onBindViewHolder(@NonNull final AllListAdapter.ViewHolder viewHolder, int i) {
        i = dataSet.get(i);
        ((TextView)viewHolder.mConstraintLayout.getViewById(R.id.all_list_item_caption)).setText(Item.items[i].name.charAt(0)+"");
        ((TextView)viewHolder.mConstraintLayout.getViewById(R.id.all_list_item_name)).setText(Item.items[i].name);
        if(mOnItemClickListner != null){
            viewHolder.mConstraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListner.onClick(viewHolder.getAdapterPosition());
                }
            });

            viewHolder.mConstraintLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mOnItemClickListner.onLongClick(viewHolder.getAdapterPosition());
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
