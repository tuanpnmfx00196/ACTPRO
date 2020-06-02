package com.example.actproperty;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class CableIdAdapter extends RecyclerView.Adapter<CableIdAdapter.ViewHolder> {
    ArrayList<CableId> List;
    Context context;
    private ItemClickListener itemClickListener;

    public CableIdAdapter(ArrayList<CableId> list, Context context) {
        List = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.custom_recycler,viewGroup,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.txtLocal.setText(List.get(i).getProvince());
        viewHolder.idRoute.setText(List.get(i).getCableId());
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {
        TextView txtLocal, idRoute;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtLocal = (TextView)itemView.findViewById(R.id.txtLocal);
            idRoute = (TextView)itemView.findViewById(R.id.idRoute);
        }
    }
}
