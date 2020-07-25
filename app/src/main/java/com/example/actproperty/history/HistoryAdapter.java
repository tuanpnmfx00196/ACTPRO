package com.example.actproperty.history;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.actproperty.R;
import com.example.actproperty.itemclick_Interface.OnItemClickRecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    ArrayList<History>listHistory;
    Context context;
    private OnItemClickRecyclerView clickRecyclerView;

    public HistoryAdapter(ArrayList<History> listHistory, Context context, OnItemClickRecyclerView clickRecyclerView) {
        this.listHistory = listHistory;
        this.context = context;
        this.clickRecyclerView = clickRecyclerView;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.history_recycler,viewGroup,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final HistoryAdapter.ViewHolder viewHolder, int i) {
        viewHolder.local_history.setText(listHistory.get(i).getProvince());
        viewHolder.cableid_history.setText(listHistory.get(i).getCableId());
        viewHolder.userchange.setText("Người đổi "+listHistory.get(i).getUserchange());
        viewHolder.datechange.setText("Thời gian đổi "+listHistory.get(i).getDatechange());
        viewHolder.comment.setText("Lý do: "+listHistory.get(i).getComment());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickRecyclerView.onClick(viewHolder.getAdapterPosition());
            }
        });
    }
    @Override
    public int getItemCount() {
        return listHistory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView local_history, cableid_history, datechange, userchange, comment;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            local_history=(TextView)itemView.findViewById(R.id.local_history);
            cableid_history=(TextView)itemView.findViewById(R.id.cableid_history);
            datechange=(TextView)itemView.findViewById(R.id.datechange);
            userchange=(TextView)itemView.findViewById(R.id.userchange);
            comment=(TextView)itemView.findViewById(R.id.comment);
        }
    }
}
