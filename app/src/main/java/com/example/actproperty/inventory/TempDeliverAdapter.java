package com.example.actproperty.inventory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.actproperty.R;

import java.util.ArrayList;

public class TempDeliverAdapter extends BaseAdapter {
    private ArrayList<TempDeliver>arrayList;
    private Context context;
    private LayoutInflater layoutInflater;

    public TempDeliverAdapter(ArrayList<TempDeliver> arrayList) {
        this.arrayList = arrayList;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if(convertView ==null){
            view = View.inflate(parent.getContext(),R.layout.custom_list_deliver, null);
        }else{
            view = convertView;
        }
        TextView storeDeliver = (TextView)view.findViewById(R.id.storeDeliver);
        TextView userDeliver = (TextView)view.findViewById(R.id.userDeliver);
        TextView timeDeliver = (TextView)view.findViewById(R.id.timeDeliver);
        TextView commentDeliver = (TextView)view.findViewById(R.id.commentDeliver);
        TempDeliver tempDeliver = (TempDeliver) getItem(position);
        storeDeliver.setText(tempDeliver.getStoreCode());
        userDeliver.setText("User xuất: "+tempDeliver.getUserDeliver());
        timeDeliver.setText("ngày xuất: "+tempDeliver.getTimeDeliver());
        commentDeliver.setText(tempDeliver.getCommentDeliver());
        return view;
    }
}
