package com.example.actproperty.department.noc;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.actproperty.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NocDepartment extends AppCompatActivity {
    TextView timeStartSearchCR, timeToSearchCR;
    Spinner localSearchCR;
    Button btnSearchCR, btnBackCR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noc_department);
        timeStartSearchCR = (TextView)findViewById(R.id.timeStartSearchCR);
        timeToSearchCR = (TextView)findViewById(R.id.timeToSearchCR);
        localSearchCR = (Spinner)findViewById(R.id.localSearchCR);
        btnSearchCR = (Button)findViewById(R.id.btnSearchCR);
        btnBackCR = (Button)findViewById(R.id.btnBackCR);
    }
    private void getStartTimeSearchCR(){
        final Calendar calendar = Calendar.getInstance();
        int _date = calendar.get(Calendar.DATE);
        int _month = calendar.get(Calendar.MONTH);
        int _year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                timeStartSearchCR.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },_year, _month, _date);
        datePickerDialog.show();
    }
    private void getToTimeSearchCR(){
        final Calendar calendar = Calendar.getInstance();
        int _date = calendar.get(Calendar.DATE);
        int _month = calendar.get(Calendar.MONTH);
        int _year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                timeToSearchCR.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },_year, _month, _date);
        datePickerDialog.show();
    }
    private void getLocalSearchCR(){
        List<String> listLocal = new ArrayList<>();
        listLocal.add("All");
        listLocal.add("BTE");
        listLocal.add("LAN");
        listLocal.add("TVH");
        listLocal.add("KGG");
        listLocal.add("BDG");
        listLocal.add("DNI");
        listLocal.add("HCM_BTN");
        listLocal.add("HCM_BCH");
        listLocal.add("HCM_CCI");
        listLocal.add("HCM_TCH");
        listLocal.add("HCM_GDH");
        ArrayAdapter<String> localAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,listLocal);
        localAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        localSearchCR.setAdapter(localAdapter);
        localSearchCR.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                codeStore = spinnerToStore.getSelectedItem().toString();
                tempDeliver.setStoreCode(codeStore);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}