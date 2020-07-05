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
        final Dialog dialog = new Dialog(this,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
 //       dialog.setTitle("Xuất kho");
//        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.97);
//        int height = (int)(getResources().getDisplayMetrics().heightPixels*0.97);
//        dialog.getWindow().setLayout(width, LinearLayout.LayoutParams.MATCH_PARENT);
//        dialog.getWindow().setLayout(height, LinearLayout.LayoutParams.MATCH_PARENT);
        dialog.setContentView(R.layout.dialog_deliver);
        final Button btnLock = (Button)dialog.findViewById(R.id.btnLock);
        final EditText commentDeliver = (EditText) dialog.findViewById(R.id.commentDeliver);
        final LinearLayout rowLock = (LinearLayout)dialog.findViewById(R.id.rowLock);
        final LinearLayout rowChosen = (LinearLayout)dialog.findViewById(R.id.rowChosen);
        final String[] codeStore = {""};
        final String[] comment ={""};
        final ArrayList<TempItem>listTempItem = new ArrayList<>();
        final Spinner spinnerToStore = (Spinner)dialog.findViewById(R.id.spinnerToStore);
        List<String> listToStore = new ArrayList<>();
        listToStore.add("Mã kho nhận");
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
        listItem.add("Vật tư xuất kho");
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

                btnLock.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rowLock.setVisibility(View.GONE);
                        rowChosen.setVisibility(View.VISIBLE);
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /*======================= GET ITEM==========================*/
        final Spinner spinnerItem1 = (Spinner)dialog.findViewById(R.id.spinnerItem1);
        final EditText item1 = (EditText)dialog.findViewById(R.id.item1);
        final Button btnAddItem = (Button)dialog.findViewById(R.id.btnAddItem);
        ArrayAdapter<String> adapterItem1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,listItem);
        adapterItem1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerItem1.setAdapter(adapterItem1);
        spinnerItem1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               ((TextView)parent.getChildAt(0)).setTextSize(10);
                ((TextView)parent.getChildAt(0)).setTextColor(Color.BLUE);
                btnAddItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int status = 0;
                        for(int i=0;i<listTempItem.size();i++) {
                            if(listTempItem.get(i).getNameItem().equals(spinnerItem1.getSelectedItem().toString())){
                                status++;
                            }
                        }
                        if(status==0) {
                            listTempItem.add(new TempItem(spinnerItem1.getSelectedItem().toString(),
                                    Integer.parseInt(item1.getText().toString())));
                        }else{
                            Toast.makeText(AccountingDepartment.this, "Đã có vật tư này, kiểm tra lại",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Button btnDeliver = (Button)dialog.findViewById(R.id.btnDeliver);
        btnDeliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        dialog.show();
    }

}