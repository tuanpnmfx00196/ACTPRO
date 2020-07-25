package com.example.actproperty.department.accounting;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.actproperty.R;
import com.example.actproperty.itemclick_Interface.OnItemClickRecyclerView;

import java.util.ArrayList;

public class DeliverHistoryAdapter extends RecyclerView.Adapter<DeliverHistoryAdapter.ViewHolder> {
    ArrayList<DeliverObject> List;
    Context context;
    private OnItemClickRecyclerView clickRecyclerView;

    public DeliverHistoryAdapter(ArrayList<DeliverObject> list, Context context, OnItemClickRecyclerView clickRecyclerView) {
        List = list;
        this.context = context;
        this.clickRecyclerView = clickRecyclerView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.custom_recyclerview_deliver_history, viewGroup,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.codeDeliver.setText("Mã phiếu xuất: PXK_TCKT_"+List.get(i).getTimedeliver());
        viewHolder.userDeliver.setText("Người xuất: "+List.get(i).getUserdeliver());
        viewHolder.dateDeliver.setText("Ngày xuất: "+List.get(i).getTimedeliver());
        viewHolder.store.setText("Kho nhận: "+List.get(i).getCodeStore());
        if(List.get(i).getFlag()==1){
            viewHolder.statusDeliver.setText("Đang chuyển");
            viewHolder.statusDeliver.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E8AC31")));
        }else if(List.get(i).getFlag()==2){
            viewHolder.statusDeliver.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#31E2E8")));
            viewHolder.statusDeliver.setText("Đã nhập");
        }else{
            viewHolder.statusDeliver.setText("Đã từ chối");
            viewHolder.statusDeliver.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF5722")));
        }
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView codeDeliver, userDeliver, dateDeliver, store, statusDeliver;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            codeDeliver = (TextView)itemView.findViewById(R.id.codeDeliver);
            userDeliver = (TextView)itemView.findViewById(R.id.userDeliver);
            dateDeliver = (TextView)itemView.findViewById(R.id.dateDeliver);
            store = (TextView)itemView.findViewById(R.id.store);
            statusDeliver = (TextView)itemView.findViewById(R.id.statusDeliver);
        }
    }
}
