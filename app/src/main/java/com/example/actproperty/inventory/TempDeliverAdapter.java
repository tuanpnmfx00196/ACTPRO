package com.example.actproperty.inventory;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.actproperty.R;
import com.example.actproperty.itemclick_Interface.OnItemClickRecyclerView;

import java.util.ArrayList;

public class TempDeliverAdapter extends BaseAdapter {
    private ArrayList<TempDeliver>arrayList;
    private OnItemClickRecyclerView clickRecyclerView;
    Context context;

    public TempDeliverAdapter(ArrayList<TempDeliver> arrayList, OnItemClickRecyclerView clickRecyclerView, Context context) {
        this.arrayList = arrayList;
        this.clickRecyclerView = clickRecyclerView;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        if(convertView ==null){
            view = View.inflate(parent.getContext(),R.layout.custom_list_deliver, null);
        }else{
            view = convertView;
        }
        TextView txtTimeDeliver = (TextView)view.findViewById(R.id.txtTimeDeliver);
        TextView txtCodeDeliver = (TextView)view.findViewById(R.id.txtCodeDeliver);
        TextView codeStoreDeliver = (TextView)view.findViewById(R.id.codeStoreDeliver);
        TextView txtUserDeliver = (TextView)view.findViewById(R.id.txtUserDeliver);
        TextView detailsDeliver = (TextView)view.findViewById(R.id.detailsDeliver);
        TempDeliver tempDeliver = (TempDeliver) getItem(position);
        txtTimeDeliver.setText(tempDeliver.getTimeDeliver());
        txtCodeDeliver.setText("Mã phiếu xuất : "+tempDeliver.getId());
        codeStoreDeliver.setText("Mã kho nhập: "+tempDeliver.getStoreCode());
        txtUserDeliver.setText(tempDeliver.getUserDeliver());
        detailsDeliver.setText(tempDeliver.getCommentDeliver());
        Button btnAcceptDeliver = (Button)view.findViewById(R.id.btnAcceptDeliver);
        Button btnIgnoreDeliver = (Button)view.findViewById(R.id.btnIgnoreDeliver);
        btnAcceptDeliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickRecyclerView.onClick(position);
            }
        });
        btnIgnoreDeliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickRecyclerView.onClick1(position);
            }
        });
        return view;
    }
}
