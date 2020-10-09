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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.actproperty.R;
import com.example.actproperty.passport.Passport;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AccountingDepartment extends AppCompatActivity {
Button imgBtnDeliver;
Button imgBtnIO,imgBtnShowInventory;
TextView getFromDateIO, getToDateIO;
Spinner spinnerCodeStore;
List<String> listStore;
ArrayList<Passport>listUser;
ArrayList<DeliverObject>listDeliver;
ArrayList<DeliverObject>listDeliverShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounting_department);
        listUser = new ArrayList<>();
        listDeliver = new ArrayList<>();
        listDeliverShow= new ArrayList<>();
        listStore = new ArrayList<>();
        getAccount();
        Map();
        CreateListStore();
        GetDataDeliver("https://sqlandroid2812.000webhostapp.com/gettempdeliver.php");
        imgBtnIO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IOInventory();
            }
        });
        imgBtnDeliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listUser.get(0).getAdmin()!=1 && listUser.get(0).getAdmin()!=2 && listUser.get(0).getAccountant()!=1 ){
                    Toast.makeText(AccountingDepartment.this, "You do not have permission to access!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(AccountingDepartment.this, Deliver.class);
                    intent.putExtra("Account", listUser);
                    startActivity(intent);
                }
            }
        });
    }
    private void Map(){
        imgBtnIO = (Button)findViewById(R.id.imgBtnIO);
        imgBtnShowInventory = (Button)findViewById(R.id.imgBtnShowInventory);
        imgBtnDeliver = (Button)findViewById(R.id.imgBtnDeliver);
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
        getFromDateIO = (TextView)dialog.findViewById(R.id.getFromDateIO);
        getToDateIO = (TextView)dialog.findViewById(R.id.getToDateIO);
        Button btnSearchIO = (Button)dialog.findViewById(R.id.btnSearchIO);
        Button btnBackIO = (Button)dialog.findViewById(R.id.btnBackIO);
        spinnerCodeStore = (Spinner)dialog.findViewById(R.id.spinnerCodeStore);
        ArrayAdapter<String>adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,listStore);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerCodeStore.setAdapter(adapter);
        spinnerCodeStore.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnBackIO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        getFromDateIO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetFromDate();
            }
        });
        getToDateIO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetToDate();
            }
        });
        btnSearchIO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listDeliverShow.clear();
                        listDeliverShow.addAll(ExportDeliver(getFromDateIO.getText().toString(),
                                      getToDateIO.getText().toString(),
                                      spinnerCodeStore.getSelectedItem().toString(),
                                        listDeliver));
                        Intent intent = new Intent(AccountingDepartment.this, ShowDeliverHistory.class);
                        intent.putExtra("listDeliverShow",listDeliverShow);
                        intent.putExtra("dateFrom",getFromDateIO.getText().toString());
                        intent.putExtra("dateTo",getToDateIO.getText().toString());
                        startActivity(intent);
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
    private void GetDataDeliver(String url){
        listDeliver.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++){
                    try{
                        JSONObject jsonObject = response.getJSONObject(i);
                        listDeliver.add(new DeliverObject(
                                jsonObject.getInt("IDdeliver"),
                                jsonObject.getString("Storecodedeliver"),
                                jsonObject.getInt("Hanging6fodeliver"),
                                jsonObject.getInt("Hanging12fodeliver"),
                                jsonObject.getInt("Hanging24fodeliver"),
                                jsonObject.getInt("Odf6fodeliver"),
                                jsonObject.getInt("Odf12fodeliver"),
                                jsonObject.getInt("Odf24fodeliver"),
                                jsonObject.getInt("Closure6fodeliver"),
                                jsonObject.getInt("Closure12fodeliver"),
                                jsonObject.getInt("Closure24fodeliver"),
                                jsonObject.getInt("Buloongti300deliver"),
                                jsonObject.getInt("Buloongti400deliver"),
                                jsonObject.getInt("Clampdeliver"),
                                jsonObject.getInt("Sc_lc5deliver"),
                                jsonObject.getInt("Sc_lc10deliver"),
                                jsonObject.getString("Userdeliver"),
                                jsonObject.getString("Timedeliver"),
                                jsonObject.getString("Commentdeliver"),
                                jsonObject.getInt("Flag")
                        ));
                    }catch(Exception e){
                        Toast.makeText(AccountingDepartment.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AccountingDepartment.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(request);
    }
    private ArrayList<DeliverObject> ExportDeliver(String fromDate, String toDate, String codeStore,
                                                   ArrayList<DeliverObject> arrayList){

        Date from = null;
        Date to = null;
        ArrayList<DeliverObject> listDeliverSearch = new ArrayList<>();
        listDeliverSearch.clear();
        try {
            from = new SimpleDateFormat("dd/MM/yyyy").parse(fromDate);
        } catch (ParseException e) {
            Toast.makeText(this, "Lỗi ngày bắt đầu", Toast.LENGTH_SHORT).show();
        }
        try {
            to = new SimpleDateFormat("dd/MM/yyyy").parse(toDate);
        } catch (ParseException e) {
            Toast.makeText(this, "Lỗi ngày kết thúc", Toast.LENGTH_SHORT).show();
        }
        for(int i=0; i<arrayList.size();i++){
            Date dateDeliver=null;
            try {
                dateDeliver = new SimpleDateFormat("dd/MM/yyyy").parse(listDeliver.get(i).getTimedeliver());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(codeStore.equals("All")){
                if(dateDeliver.compareTo(from)>=0 &&dateDeliver.compareTo(to)<=0){
                    listDeliverSearch.add(arrayList.get(i));
                }
            }else{
                if(listDeliver.get(i).getCodeStore().equals(codeStore)) {
                    if (dateDeliver.compareTo(from) >= 0 && dateDeliver.compareTo(to) <= 0) {
                        listDeliverSearch.add(arrayList.get(i));
                    }
                }
            }
        }
        return listDeliverSearch;
    }
    private void CreateListStore() {
        if (listUser.get(0).getProperty() == 1 || listUser.get(0).getAdmin() == 1 ||
                listUser.get(0).getAdmin() == 2) {
            listStore.add("All");
            listStore.add("HCM_Bình Chánh");
            listStore.add("HCM_Bình Tân");
            listStore.add("HCM_Củ Chi");
            listStore.add("HCM_Hóc Môn");
            listStore.add("HCM_Quận 12");
            listStore.add("HCM_Gò Vấp");
            listStore.add("Bình Dương");
            listStore.add("Kiên Giang");
            listStore.add("Đồng Nai");
            listStore.add("Trà Vinh");
            listStore.add("Bến Tre");
            listStore.add("Long An");
        } else {
            if (listUser.get(0).getHcm_q12() == 1 || listUser.get(0).getHcm_q12() == 2) {
                listStore.add("HCM_Quận 12");
            }
            if (listUser.get(0).getHcm_bch() == 1 || listUser.get(0).getHcm_bch() == 2) {
                listStore.add("HCM_Bình Chánh");
            }
            if (listUser.get(0).getHcm_btn() == 1 || listUser.get(0).getHcm_btn() == 2) {
                listStore.add("HCM_Bình Tân");
            }
            if (listUser.get(0).getHcm_cci() == 1 || listUser.get(0).getHcm_cci() == 2) {
                listStore.add("HCM_Củ Chi");
            }
            if (listUser.get(0).getHcm_hmn() == 1 || listUser.get(0).getHcm_hmn() == 2) {
                listStore.add("HCM_Hóc Môn");
            }
            if (listUser.get(0).getHcm_gvp() == 1 || listUser.get(0).getHcm_gvp() == 2) {
                listStore.add("HCM_Gò Vấp");
            }
            if (listUser.get(0).getBdg() == 1 || listUser.get(0).getBdg() == 2) {
                listStore.add("Bình Dương");
            }
            if (listUser.get(0).getKgg() == 1 || listUser.get(0).getKgg() == 2) {
                listStore.add("Kiên Giang");
            }
            if (listUser.get(0).getDni() == 1 || listUser.get(0).getDni() == 2) {
                listStore.add("Đồng Nai");
            }
            if (listUser.get(0).getLan() == 1 || listUser.get(0).getLan() == 2) {
                listStore.add("Long An");
            }
            if (listUser.get(0).getTvh() == 1 || listUser.get(0).getTvh() == 2) {
                listStore.add("Trà Vinh");
            }
            if (listUser.get(0).getBte() == 1 || listUser.get(0).getBte() == 2) {
                listStore.add("Bến Tre");
            }
        }
    }


}