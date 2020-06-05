package com.example.actproperty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class DashBoard extends AppCompatActivity {
    Button btnCableId;
    ImageButton btnImgCableId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        btnCableId = (Button)findViewById(R.id.btnCableId);
        btnImgCableId = (ImageButton)findViewById(R.id.imgBtnCableId);
        btnCableId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toManagement();
            }
        });
        btnImgCableId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toManagement();
            }
        });
    }
    public void toManagement(){
        Intent intent = new Intent(DashBoard.this, Management.class);
        startActivity(intent);
    }

}