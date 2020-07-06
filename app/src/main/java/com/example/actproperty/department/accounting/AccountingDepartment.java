package com.example.actproperty.department.accounting;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.actproperty.R;
import com.example.actproperty.passport.Passport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AccountingDepartment extends AppCompatActivity {
ImageButton imgBtnIO, imgBtnShowInventory, imgBtnDeliver;
EditText getFromDateIO, getToDateIO;
Spinner spinnerCodeStore;
ArrayList<Passport>listUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounting_department);
        listUser = new ArrayList<>();
        getAccount();
        Map();
        imgBtnIO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IOInventory();
            }
        });
        imgBtnDeliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountingDepartment.this, Deliver.class);
                intent.putExtra("Account", listUser);
                startActivity(intent);
            }
        });
    }
    private void Map(){
        imgBtnIO = (ImageButton)findViewById(R.id.imgBtnIO);
        imgBtnShowInventory = (ImageButton)findViewById(R.id.imgBtnShowInventory);
        imgBtnDeliver = (ImageButton)findViewById(R.id.imgBtnDeliver);
    }
    private void getAccount(){
        Intent intent = getIntent();
        listUser = (ArrayList<Passport>) intent.getSerializableExtra("Account");
    }
    private void IOInventory(){
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("Choose time!");
        dialog.setContentView(R.layout.dialog_ioinventory);
        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.97);
        dialog.getWindow().setLayout(width, LinearLayout.LayoutParams.WRAP_CONTENT);
        getFromDateIO = (EditText)dialog.findViewById(R.id.getFromDateIO);
        getToDateIO = (EditText)dialog.findViewById(R.id.getToDateIO);
        Button btnSearchIO = (Button)dialog.findViewById(R.id.btnSearchIO);
        Button btnBackIO = (Button)dialog.findViewById(R.id.btnBackIO);
        spinnerCodeStore = (Spinner)dialog.findViewById(R.id.spinnerCodeStore);
        List<String> listStore = new ArrayList<>();
        listStore.add("All");
        listStore.add("BTE");
        listStore.add("LAN");
        listStore.add("TVH");
        listStore.add("KGG");
        listStore.add("BDG");
        listStore.add("DNI");
        listStore.add("HCM_BTN");
        listStore.add("HCM_BCH");
        listStore.add("HCM_CCI");
        listStore.add("HCM_TCH");
        listStore.add("HCM_GDH");
        ArrayAdapter<String>adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,listStore);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerCodeStore.setAdapter(adapter);
        spinnerCodeStore.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AccountingDepartment.this,spinnerCodeStore.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
        btnSearchIO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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