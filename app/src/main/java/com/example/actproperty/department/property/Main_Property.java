package com.example.actproperty.department.property;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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
import com.example.actproperty.department.noc.CRNOC;
import com.example.actproperty.department.noc.NocDepartment;
import com.example.actproperty.passport.Passport;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main_Property extends AppCompatActivity {
    Button btn_forControl,btn_ioinventory,btn_used; //Button Menu
    Button btn_doisoat, btn_nhapxuatton,btn_history_used; //Button action listener
    LinearLayout layout_doisoat, layout_nhapxuatton, layout_history_used; // 3 layout hide, waiting action
    TextView fromDateHistoryUsed, toDateHistoryUsed, from_dateForControl, to_dateForControl,from_dateIO,to_dateIO; //datePicker
    Spinner spinner_donviquyettoan, spinner_khonhapxuat,spinner_donvisudung;
    ArrayList<Passport> listUser;
    List<String>listLocal;
    ArrayList<CRNOC>listCR, listCrSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_property);
        listUser = new ArrayList<>();
        listLocal = new ArrayList<>();
        listCR = new ArrayList<>();
        listCrSearch = new ArrayList<>();
        Map();
        getListCR("https://sqlandroid2812.000webhostapp.com/getnoc.php");
        getUser();
        CreateListLocal();
        btn_forControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout_doisoat.setVisibility(View.VISIBLE);
                layout_nhapxuatton.setVisibility(View.GONE);
                layout_history_used.setVisibility(View.GONE);
            }
        });
        btn_ioinventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout_doisoat.setVisibility(View.GONE);
                layout_nhapxuatton.setVisibility(View.VISIBLE);
                layout_history_used.setVisibility(View.GONE);
            }
        });
        btn_used.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout_doisoat.setVisibility(View.GONE);
                layout_nhapxuatton.setVisibility(View.GONE);
                layout_history_used.setVisibility(View.VISIBLE);
            }
        });
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
        getLocalForControl();
        btn_doisoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Đối soát quyết toán
                getListCRSearch(from_dateForControl.getText().toString(), to_dateForControl.getText().toString(),
                        spinner_donviquyettoan.getSelectedItem().toString());
                Toast.makeText(Main_Property.this, ""+listCrSearch.size(), Toast.LENGTH_SHORT).show();
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
    private void getLocalForControl(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,listLocal);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner_donviquyettoan.setAdapter(adapter);
        spinner_donviquyettoan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLUE);
                ((TextView) parent.getChildAt(0)).setTextSize(10);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
    private void CreateListLocal() {
        if (listUser.get(0).getProperty() == 1 || listUser.get(0).getAdmin() == 1 ||
                listUser.get(0).getAdmin() == 2) {
            listLocal.add("All");
            listLocal.add("HCM_Bình Chánh");
            listLocal.add("HCM_Bình Tân");
            listLocal.add("HCM_Củ Chi");
            listLocal.add("HCM_Hóc Môn");
            listLocal.add("HCM_Quận 12");
            listLocal.add("HCM_Gò Vấp");
            listLocal.add("Bình Dương");
            listLocal.add("Kiên Giang");
            listLocal.add("Đồng Nai");
            listLocal.add("Trà Vinh");
            listLocal.add("Bến Tre");
            listLocal.add("Long An");
        } else {
            if (listUser.get(0).getHcm_q12() == 1 || listUser.get(0).getHcm_q12() == 2) {
                listLocal.add("HCM_Quận 12");
            }
            if (listUser.get(0).getHcm_bch() == 1 || listUser.get(0).getHcm_bch() == 2) {
                listLocal.add("HCM_Bình Chánh");
            }
            if (listUser.get(0).getHcm_btn() == 1 || listUser.get(0).getHcm_btn() == 2) {
                listLocal.add("HCM_Bình Tân");
            }
            if (listUser.get(0).getHcm_cci() == 1 || listUser.get(0).getHcm_cci() == 2) {
                listLocal.add("HCM_Củ Chi");
            }
            if (listUser.get(0).getHcm_hmn() == 1 || listUser.get(0).getHcm_hmn() == 2) {
                listLocal.add("HCM_Hóc Môn");
            }
            if (listUser.get(0).getHcm_gvp() == 1 || listUser.get(0).getHcm_gvp() == 2) {
                listLocal.add("HCM_Gò Vấp");
            }
            if (listUser.get(0).getBdg() == 1 || listUser.get(0).getBdg() == 2) {
                listLocal.add("Bình Dương");
            }
            if (listUser.get(0).getKgg() == 1 || listUser.get(0).getKgg() == 2) {
                listLocal.add("Kiên Giang");
            }
            if (listUser.get(0).getDni() == 1 || listUser.get(0).getDni() == 2) {
                listLocal.add("Đồng Nai");
            }
            if (listUser.get(0).getLan() == 1 || listUser.get(0).getLan() == 2) {
                listLocal.add("Long An");
            }
            if (listUser.get(0).getTvh() == 1 || listUser.get(0).getTvh() == 2) {
                listLocal.add("Trà Vinh");
            }
            if (listUser.get(0).getBte() == 1 || listUser.get(0).getBte() == 2) {
                listLocal.add("Bến Tre");
            }
        }
    }
    private void getUser(){
        Intent intent = getIntent();
        listUser = (ArrayList<Passport>) intent.getSerializableExtra("Account");
    }
    private void getListCRSearch(String fromTime, String toTime, String local){
        Date from = null;
        Date to = null;
        try {
            from = new SimpleDateFormat("dd/MM/yyyy").parse(fromTime);
        } catch (ParseException e) {
            Toast.makeText(this, "Định dạng thời gian bị sai", Toast.LENGTH_SHORT).show();
        }
        try {
            to = new SimpleDateFormat("dd/MM/yyyy").parse(toTime);
        } catch (ParseException e) {
            Toast.makeText(this, "Định dạng thời gian bị sai", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(Main_Property.this, e.toString(), Toast.LENGTH_SHORT).show();
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
}