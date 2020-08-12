package com.example.actproperty.department.noc;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.actproperty.R;
import com.example.actproperty.department.accounting.DeliverObject;
import com.example.actproperty.itemclick_Interface.OnItemClickRecyclerView;

import java.util.ArrayList;

public class ListCRAdapter extends RecyclerView.Adapter<ListCRAdapter.ViewHolder> {
    ArrayList<CRNOC> List;
    Context context;
    private OnItemClickRecyclerView clickRecyclerView;

    public ListCRAdapter(ArrayList<CRNOC> list, Context context, OnItemClickRecyclerView clickRecyclerView) {
        List = list;
        this.context = context;
        this.clickRecyclerView = clickRecyclerView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.custom_recyclerview_listcr,viewGroup,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.recyclerViewCodeCr.setText(List.get(i).getCodecr().toString());
        switch (List.get(i).getStatuscr()){
            case 1:
                viewHolder.recyclerViewStatusCr.setText(List.get(i).getLocal()+"\n"+"Chưa thực hiện");
                break;
            case 2:
                viewHolder.recyclerViewStatusCr.setText(List.get(i).getLocal()+"\n"+"Đã thực hiện");
                break;
            case 3:
                viewHolder.recyclerViewStatusCr.setText(List.get(i).getLocal()+"\n"+"Đã từ chối");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView recyclerViewCodeCr, recyclerViewStatusCr;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerViewCodeCr = (TextView)itemView.findViewById(R.id.recyclerViewCodeCr);
            recyclerViewStatusCr = (TextView)itemView.findViewById(R.id.recyclerViewStatusCr);
        }
    }
}
