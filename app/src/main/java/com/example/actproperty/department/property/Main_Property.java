package com.example.actproperty.department.property;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.actproperty.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main_Property extends AppCompatActivity {
    Button btn_forControl,btn_ioinventory,btn_used; //Button Menu
    Button btn_doisoat, btn_nhapxuatton,btn_history_used; //Button action listener
    LinearLayout layout_doisoat, layout_nhapxuatton, layout_history_used; // 3 layout hide, waiting action
    TextView fromDateHistoryUsed, toDateHistoryUsed, from_dateForControl, to_dateForControl,from_dateIO,to_dateIO; //datePicker
    Spinner spinner_donviquyettoan, spinner_khonhapxuat,spinner_donvisudung;
    String date_forControl, date_forIO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_property);
        Map();
        fromDateHistoryUsed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetFromDateSearchHistoryUsed();
            }
        });
        toDateHistoryUsed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetToDateSearchHistoryUsed();
            }
        });
        from_dateForControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetFromDateForControl();
            }
        });
        to_dateForControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetToDateForControl();
            }
        });
        from_dateIO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetFromDateIO();
            }
        });
        to_dateIO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetToDateIO();
            }
        });
    }
    private void GetFromDateSearchHistoryUsed(){
        final Calendar calendar = Calendar.getInstance();
        int _date = calendar.get(Calendar.DATE);
        int _month = calendar.get(Calendar.MONTH);
        int _year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                fromDateHistoryUsed.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },_year, _month, _date);
        datePickerDialog.show();
    }
    private void GetToDateSearchHistoryUsed(){
        final Calendar calendar = Calendar.getInstance();
        int _date = calendar.get(Calendar.DATE);
        int _month = calendar.get(Calendar.MONTH);
        int _year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                toDateHistoryUsed.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },_year, _month, _date);
        datePickerDialog.show();
    }
    private void GetFromDateForControl(){
        final Calendar calendar = Calendar.getInstance();
        int _date = calendar.get(Calendar.DATE);
        int _month = calendar.get(Calendar.MONTH);
        int _year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                from_dateForControl.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },_year, _month, _date);
        datePickerDialog.show();
    }
    private void GetToDateForControl(){
        final Calendar calendar = Calendar.getInstance();
        int _date = calendar.get(Calendar.DATE);
        int _month = calendar.get(Calendar.MONTH);
        int _year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                to_dateForControl.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },_year, _month, _date);
        datePickerDialog.show();
    }
    private void GetFromDateIO(){
        final Calendar calendar = Calendar.getInstance();
        int _date = calendar.get(Calendar.DATE);
        int _month = calendar.get(Calendar.MONTH);
        int _year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                from_dateIO.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },_year, _month, _date);
        datePickerDialog.show();
    }
    private void GetToDateIO(){
        final Calendar calendar = Calendar.getInstance();
        int _date = calendar.get(Calendar.DATE);
        int _month = calendar.get(Calendar.MONTH);
        int _year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                to_dateIO.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },_year, _month, _date);
        datePickerDialog.show();
    }
    private void Map(){
        btn_forControl= (Button)findViewById(R.id.btn_forControl);
        btn_ioinventory = (Button)findViewById(R.id.btn_ioinventory);
        btn_used = (Button)findViewById(R.id.btn_used);
        btn_doisoat = (Button)findViewById(R.id.btn_doisoat);
        btn_nhapxuatton = (Button)findViewById(R.id.btn_nhapxuatton);
        btn_history_used = (Button)findViewById(R.id.btn_history_used);
        layout_doisoat = (LinearLayout)findViewById(R.id.layout_doisoat);
        layout_nhapxuatton = (LinearLayout)findViewById(R.id.layout_nhapxuatton);
        layout_history_used = (LinearLayout)findViewById(R.id.layout_history_used);
        fromDateHistoryUsed = (TextView)findViewById(R.id.fromDateHistoryUsed);
        toDateHistoryUsed = (TextView)findViewById(R.id.toDateHistoryUsed);
        spinner_donviquyettoan = (Spinner)findViewById(R.id.spinner_donviquyettoan);
        spinner_khonhapxuat = (Spinner)findViewById(R.id.spinner_khonhapxuat);
        spinner_donvisudung = (Spinner)findViewById(R.id.spinner_donvisudung);
        from_dateForControl = (TextView)findViewById(R.id.from_dateForControl);
        to_dateForControl = (TextView)findViewById(R.id.to_dateForControl);
        from_dateIO = (TextView)findViewById(R.id.from_dateIO);
        to_dateIO = (TextView)findViewById(R.id.to_dateIO);
    }
}