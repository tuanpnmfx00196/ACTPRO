package com.example.actproperty.department.property;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.actproperty.R;

import java.util.ArrayList;

public class ShowDetailsControl extends AppCompatActivity {
    ArrayList<ItemUsed>listItemUsed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details_control);
        listItemUsed = new ArrayList<>();
        getListItemUsed();
    }
    private void getListItemUsed(){
        Intent intent = getIntent();
        listItemUsed = (ArrayList<ItemUsed>) intent.getSerializableExtra("ListItemUsed");
    }
    private void ShowDetails(){

    }

}