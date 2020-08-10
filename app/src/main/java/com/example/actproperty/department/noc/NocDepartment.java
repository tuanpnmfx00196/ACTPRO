package com.example.actproperty.department.noc;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
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

public class NocDepartment extends AppCompatActivity {
    TextView timeStartSearchCR, timeToSearchCR, localSearchCR;
    Spinner spnLocalSearchCR;
    Button btnSearchCR, btnBackCR;
    ArrayList<CRNOC>listCrSearch, listCR;
    ArrayList<Passport>listUser;
    LinearLayout frameLinearLayoutRecyclerviewListCR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noc_department);
        timeStartSearchCR = (TextView)findViewById(R.id.timeStartSearchCR);
        timeToSearchCR = (TextView)findViewById(R.id.timeToSearchCR);
        spnLocalSearchCR = (Spinner)findViewById(R.id.spnLocalSearchCR);
        btnSearchCR = (Button)findViewById(R.id.btnSearchCR);
        btnBackCR = (Button)findViewById(R.id.btnBackCR);
        localSearchCR = (TextView)findViewById(R.id.localSearchCR);
        listCrSearch = new ArrayList<>();
        frameLinearLayoutRecyclerviewListCR = (LinearLayout)findViewById(R.id.frameLinearLayoutRecyclerviewListCR);
        frameLinearLayoutRecyclerviewListCR.setVisibility(View.GONE);
        listUser = new ArrayList<>();
        getUser();
        listCR = new ArrayList<>();
        getListCR("https://sqlandroid2812.000webhostapp.com/getnoc.php");
        timeStartSearchCR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getStartTimeSearchCR();
            }
        });
        timeToSearchCR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getToTimeSearchCR();
            }
        });
        btnSearchCR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getListCRSearch(timeStartSearchCR.getText().toString(),timeToSearchCR.getText().toString(),
                        localSearchCR.getText().toString());
                Toast.makeText(NocDepartment.this, listCrSearch.size()+"", Toast.LENGTH_SHORT).show();
            }
        });
        getLocalSearchCR();
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
        spnLocalSearchCR.setAdapter(localAdapter);
        spnLocalSearchCR.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                localSearchCR.setText(spnLocalSearchCR.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void getUser() {
        Intent intent = getIntent();
        listUser = (ArrayList<Passport>) intent.getSerializableExtra("Account");
    }
    private void getListCR(String url){
        listCR.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i=0; i<response.length();i++){
                    try{
                        JSONObject jsonObject = response.getJSONObject(i);
                        listCR.add(new CRNOC(
                                jsonObject.getInt("IDcr"),
                                jsonObject.getInt("Idorigin"),
                                jsonObject.getString("Local"),
                                jsonObject.getString("Cableidcr"),
                                jsonObject.getString("Codecr"),
                                jsonObject.getString("Commentcr"),
                                jsonObject.getString("Datetimecr"),
                                jsonObject.getInt("Statuscr")
                        ));
                    }catch(Exception e){
                        Toast.makeText(NocDepartment.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        requestQueue.add(jsonArrayRequest);
    }
    private void getListCRSearch(String fromTime, String toTime, String local){
        Date from = null;
        Date to = null;
        try {
            from = new SimpleDateFormat("dd/MM/yyyy").parse(fromTime);
        } catch (ParseException e) {
            Toast.makeText(this, "Lỗi ngày bắt đầu", Toast.LENGTH_SHORT).show();
        }
        try {
            to = new SimpleDateFormat("dd/MM/yyyy").parse(toTime);
        } catch (ParseException e) {
            Toast.makeText(this, "Lỗi ngày kết thúc", Toast.LENGTH_SHORT).show();
        }
        ArrayList<CRNOC>listCrTemp = new ArrayList<>();
        listCrTemp.addAll(listCR);
        for(int i = 0; i<listCrTemp.size();i++){
            Date dateCR = null;
            try{
                dateCR = new SimpleDateFormat("dd/MM/yyyy").parse(listCrTemp.get(i).getDatetimecr());
            }catch (Exception e){
            }
            if(local.equals("Tất cả")){
                if(dateCR.compareTo(from)>=0 && dateCR.compareTo(to)<=0){
                    listCrSearch.add(listCrTemp.get(i));
                }
            }else{
                if(dateCR.compareTo(from)>=0 && dateCR.compareTo(to)<=0&&listCrTemp.get(i).getLocal().equals(local)){
                    listCrSearch.add(listCrTemp.get(i));
                }
            }
        }

    }
//OK
}