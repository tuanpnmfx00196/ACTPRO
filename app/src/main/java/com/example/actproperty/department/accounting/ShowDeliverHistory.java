package com.example.actproperty.department.accounting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.actproperty.R;

public class ShowDeliverHistory extends AppCompatActivity {
    TextView statusDateTime;
    RecyclerView listViewDeliverHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_deliver_history);
        Map();

    }
    private void Map(){
        statusDateTime = (TextView)findViewById(R.id.statusDateTime);
        listViewDeliverHistory = (RecyclerView)findViewById(R.id.listViewDeliverHistory);
    }
}