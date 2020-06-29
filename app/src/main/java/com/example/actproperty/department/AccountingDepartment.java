package com.example.actproperty.department;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.actproperty.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AccountingDepartment extends AppCompatActivity {
ImageButton imgBtnIO, imgBtnShowInventory, imgBtnDeliver;
EditText getFromDateIO, getToDateIO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounting_department);
        Map();
        imgBtnIO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IOInventory();
            }
        });
    }
    private void Map(){
        imgBtnIO = (ImageButton)findViewById(R.id.imgBtnIO);
        imgBtnShowInventory = (ImageButton)findViewById(R.id.imgBtnShowInventory);
        imgBtnDeliver = (ImageButton)findViewById(R.id.imgBtnDeliver);
    }
    private void IOInventory(){
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("Choose time!");
        dialog.setContentView(R.layout.ioinventory);
        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.97);
        dialog.getWindow().setLayout(width, LinearLayout.LayoutParams.WRAP_CONTENT);
        getFromDateIO = (EditText)dialog.findViewById(R.id.getFromDateIO);
        getToDateIO = (EditText)dialog.findViewById(R.id.getToDateIO);
        Button btnSearchIO = (Button)dialog.findViewById(R.id.btnSearchIO);
        Button btnBackIO = (Button)dialog.findViewById(R.id.btnBackIO);
        getFromDateIO.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                GetFromDate();
            }
        });
        getToDateIO.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                GetToDate();
            }
        });
        dialog.show();

    }
    private void GetFromDate(){
        final Calendar calendar = Calendar.getInstance();
        int _date = calendar.get(Calendar.DATE);
        int _month = calendar.get(Calendar.MONTH);
        int _year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                getFromDateIO.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },_year, _month, _date);
        datePickerDialog.show();
    }
    private void GetToDate(){
        final Calendar calendar = Calendar.getInstance();
        int _date = calendar.get(Calendar.DATE);
        int _month = calendar.get(Calendar.MONTH);
        int _year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                getToDateIO.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },_year, _month, _date);
        datePickerDialog.show();
    }
}