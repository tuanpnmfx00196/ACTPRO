package com.example.actproperty.department;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.actproperty.R;
import com.example.actproperty.inventory.TempDeliver;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AccountingDepartment extends AppCompatActivity {
ImageButton imgBtnIO, imgBtnShowInventory, imgBtnDeliver;
EditText getFromDateIO, getToDateIO;
Spinner spinnerCodeStore;
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
        imgBtnDeliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Deliver();
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
    private void Deliver(){
        final int[] waiting6fo = new int[1];
        final int waiting12fo;
        final int waiting24fo;
        final int waitingodf6fo;
        final int waitingodf12fo;
        final int waitingodf24fo;
        final int waitingmx6fo;
        final int waitingmx12fo;
        final int waitingmx24fo;
        final int waitingbl300;
        final int waitingbl400;
        final int waitingclamp;
        final int waitingsc_lc5;
        final int waitingsc_lc10;
        final int flag;
        final String[] waitingstore = new String[1];
        String waitinguser;
        String waitingtime;
        String waitingcomment;
        waiting6fo[0] = 0;
        waiting12fo = 0;
        waiting24fo = 0;
        waitingodf6fo = 0;
        waitingodf12fo = 0;
        waitingodf24fo = 0;
        waitingmx6fo = 0;
        waitingmx12fo = 0;
        waitingmx24fo = 0;
        waitingbl300 = 0;
        waitingbl400 = 0;
        waitingclamp = 0;
        waitingsc_lc5 = 0;
        waitingsc_lc10 = 0;
        waitingstore[0] = "";
        Dialog dialog = new Dialog(this);
        dialog.setTitle("Xuất kho");
        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.99);
        dialog.getWindow().setLayout(width, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setContentView(R.layout.dialog_deliver);
        final Spinner spinnerToStore = (Spinner)dialog.findViewById(R.id.spinnerToStore);
        List<String> listToStore = new ArrayList<>();
        listToStore.add("BTE");
        listToStore.add("LAN");
        listToStore.add("TVH");
        listToStore.add("KGG");
        listToStore.add("BDG");
        listToStore.add("DNI");
        listToStore.add("HCM_BTN");
        listToStore.add("HCM_BCH");
        listToStore.add("HCM_CCI");
        listToStore.add("HCM_TCH");
        listToStore.add("HCM_GDH");
        List<String> listItem = new ArrayList<>();
        listItem.add("Cáp quang treo 6Fo");
        listItem.add("Cáp quang treo 12Fo");
        listItem.add("Cáp quang treo 24Fo");
        listItem.add("ODF 6fo");
        listItem.add("ODF 12fo");
        listItem.add("ODF 24fo");
        listItem.add("Măng xông 6fo");
        listItem.add("Măng xông 12fo");
        listItem.add("Măng xông 24fo");
        listItem.add("Buloong ti 300");
        listItem.add("Buloong ti 400");
        listItem.add("Kẹp cáp");
        listItem.add("Dây nhảy Sc/LC 5m");
        listItem.add("Dây nhảy Sc/LC 10m");
        /*======================= CHOICE STORE==========================*/
        ArrayAdapter<String> adapterToStore = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,listToStore);
        adapterToStore.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerToStore.setAdapter(adapterToStore);
        spinnerToStore.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                waitingstore[0] = spinnerCodeStore.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /*======================= ITEM1==========================*/
        final Spinner spinnerItem1 = (Spinner)dialog.findViewById(R.id.spinnerItem1);
        final EditText item1 = (EditText)dialog.findViewById(R.id.item1);
        ArrayAdapter<String> adapterItem1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,listItem);
        adapterItem1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerItem1.setAdapter(adapterItem1);
        spinnerItem1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               ((TextView)parent.getChildAt(0)).setTextSize(10);
                ((TextView)parent.getChildAt(0)).setTextColor(Color.BLUE);
                switch (spinnerItem1.getSelectedItem().toString()){
                    case "Cáp quang treo 6Fo":
                        waiting6fo[0] = Integer.parseInt(item1.getText().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /*======================= ITEM2==========================*/
        final Spinner spinnerItem2 = (Spinner)dialog.findViewById(R.id.spinnerItem2);
        final EditText item2 = (EditText)dialog.findViewById(R.id.item2);
        ArrayAdapter<String> adapterItem2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,listItem);
        adapterItem2.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerItem2.setAdapter(adapterItem1);
        spinnerItem2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AccountingDepartment.this,spinnerItem2.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                ((TextView)parent.getChildAt(0)).setTextSize(10);
                ((TextView)parent.getChildAt(0)).setTextColor(Color.BLUE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /*======================= ITEM3==========================*/
        final Spinner spinnerItem3 = (Spinner)dialog.findViewById(R.id.spinnerItem3);
        final EditText item3 = (EditText)dialog.findViewById(R.id.item3);
        ArrayAdapter<String> adapterItem3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,listItem);
        adapterItem3.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerItem3.setAdapter(adapterItem1);
        spinnerItem3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AccountingDepartment.this,spinnerItem3.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                ((TextView)parent.getChildAt(0)).setTextSize(10);
                ((TextView)parent.getChildAt(0)).setTextColor(Color.BLUE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /*======================= ITEM4=========================*/
        final Spinner spinnerItem4 = (Spinner)dialog.findViewById(R.id.spinnerItem4);
        final EditText item4 = (EditText)dialog.findViewById(R.id.item4);
        ArrayAdapter<String> adapterItem4 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,listItem);
        adapterItem4.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerItem4.setAdapter(adapterItem1);
        spinnerItem4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AccountingDepartment.this,spinnerItem4.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                ((TextView)parent.getChildAt(0)).setTextSize(10);
                ((TextView)parent.getChildAt(0)).setTextColor(Color.BLUE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /*======================= ITEM5==========================*/
        final Spinner spinnerItem5 = (Spinner)dialog.findViewById(R.id.spinnerItem5);
        final EditText item5 = (EditText)dialog.findViewById(R.id.item5);
        ArrayAdapter<String> adapterItem5 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,listItem);
        adapterItem5.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerItem5.setAdapter(adapterItem1);
        spinnerItem5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AccountingDepartment.this,spinnerItem5.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                ((TextView)parent.getChildAt(0)).setTextSize(10);
                ((TextView)parent.getChildAt(0)).setTextColor(Color.BLUE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /*======================= ITEM6==========================*/
        final Spinner spinnerItem6 = (Spinner)dialog.findViewById(R.id.spinnerItem6);
        final EditText item6 = (EditText)dialog.findViewById(R.id.item6);
        ArrayAdapter<String> adapterItem6 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,listItem);
        adapterItem6.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerItem6.setAdapter(adapterItem1);
        spinnerItem6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AccountingDepartment.this,spinnerItem6.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                ((TextView)parent.getChildAt(0)).setTextSize(10);
                ((TextView)parent.getChildAt(0)).setTextColor(Color.BLUE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Button btnDeliver = (Button)dialog.findViewById(R.id.btnDeliver);
        btnDeliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<TempDeliver>listWaiting = new ArrayList<>();

            }
        });
        dialog.show();
    }

}