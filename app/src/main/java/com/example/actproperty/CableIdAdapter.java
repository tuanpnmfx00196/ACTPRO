package com.example.actproperty;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.actproperty.itemclick.OnItemClickRecyclerView;

import java.util.ArrayList;

public class CableIdAdapter extends RecyclerView.Adapter<CableIdAdapter.ViewHolder> {
    ArrayList<CableId> List;
    Context context;
    private OnItemClickRecyclerView clickRecyclerView;
    public CableIdAdapter(ArrayList<CableId> list, Context context, OnItemClickRecyclerView clickRecyclerView) {
        List = list;
        this.context = context;
        this.clickRecyclerView = clickRecyclerView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.custom_recycler,viewGroup,false);
        return new ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.txtLocal.setText(List.get(i).getProvince());
        viewHolder.idRoute.setText(List.get(i).getCableId());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickRecyclerView.onClick(viewHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {
        TextView txtLocal, idRoute;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            txtLocal = (TextView)itemView.findViewById(R.id.txtLocal);
            idRoute = (TextView)itemView.findViewById(R.id.idRoute);
        }
    }

}
