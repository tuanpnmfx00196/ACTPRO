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
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.actproperty.R;
import com.example.actproperty.department.accounting.AccountingDepartment;
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
    LinearLayout layout_doisoat, layout_nhapxuatton, layout_history_used, layoutDetailsControl; // 3 layout hide, waiting action
    TextView fromDateHistoryUsed, toDateHistoryUsed, from_dateForControl, to_dateForControl,from_dateIO,to_dateIO; //datePicker
    Spinner spinner_donviquyettoan, spinner_khonhapxuat;
    ArrayList<Passport> listUser;
    List<String>listLocal;
    ArrayList<CRNOC>listCR, listCrSearch;
    ArrayList<ItemUsed>listItemUsed;
    ArrayList<ItemUsed>listItemUsedTemp, listItemUsedForIdCable;
    TextView generalControl;
    Button btnShowDetailsControl;
    EditText searchForCodeCable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_property);
        listUser = new ArrayList<>();
        listLocal = new ArrayList<>();
        listCR = new ArrayList<>();
        listCrSearch = new ArrayList<>();
        listItemUsed = new ArrayList<>();
        listItemUsedTemp = new ArrayList<>();
        listItemUsedForIdCable = new ArrayList<>();
        Map();
        getListCR("https://sqlandroid2812.000webhostapp.com/getnoc.php");
        getItemUsedTemp("https://sqlandroid2812.000webhostapp.com/getitemused.php");
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
        btn_used.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_doisoat.setVisibility(View.GONE);
                layout_nhapxuatton.setVisibility(View.GONE);
                layout_history_used.setVisibility(View.VISIBLE);
            }
        });
        btn_ioinventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main_Property.this, AccountingDepartment.class);
                intent.putExtra("Account", listUser);
                startActivity(intent);

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
        /*================= GET LOCAL SPINNER ========================*/
        getLocalSpinner();
        getLocalIOInventory();

        btn_doisoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Đối soát quyết toán
                layoutDetailsControl.setVisibility(View.VISIBLE);
                getListCRSearch(from_dateForControl.getText().toString(), to_dateForControl.getText().toString(),
                        spinner_donviquyettoan.getSelectedItem().toString());
                HandlingData();
            }
        });

        /*================== Xuất nhập Tồn=======================*/
        btn_nhapxuatton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_history_used.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnShowDetailsControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main_Property.this, ShowDetailsControl.class);
                intent.putExtra("ListItemUsed",listItemUsed);
                startActivity(intent);

            }
        });
        btn_history_used.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createListItemUsedForIdCable(searchForCodeCable.getText().toString(),fromDateHistoryUsed.getText().toString(),
                        toDateHistoryUsed.getText().toString());
                Intent intent = new Intent(Main_Property.this, History_for_IdCable.class);
                intent.putExtra("listInfoHistory",listItemUsedForIdCable);
                startActivity(intent);
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
        from_dateForControl = (TextView)findViewById(R.id.from_dateForControl);
        to_dateForControl = (TextView)findViewById(R.id.to_dateForControl);
        from_dateIO = (TextView)findViewById(R.id.from_dateIO);
        to_dateIO = (TextView)findViewById(R.id.to_dateIO);
        generalControl = (TextView)findViewById(R.id.generalControl);
        btnShowDetailsControl = (Button)findViewById(R.id.btnShowDetailsControl);
        layoutDetailsControl = (LinearLayout)findViewById(R.id.layoutDetailsControl);
        searchForCodeCable = (EditText)findViewById(R.id.searchForCodeCable);
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
    private void getLocalSpinner(){
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
    private void getLocalIOInventory(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,listLocal);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner_khonhapxuat.setAdapter(adapter);
        spinner_khonhapxuat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        listCrSearch.clear();
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
            if(local.equals("All")){
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
    public void getItemUsedTemp(String url){
        listItemUsedTemp.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++){
                    try{
                        JSONObject jsonObject = response.getJSONObject(i);
                        listItemUsedTemp.add(new ItemUsed(
                                jsonObject.getInt("ID"),
                                jsonObject.getInt("Oldid"),
                                jsonObject.getString("Cableid"),
                                jsonObject.getString("Province"),
                                jsonObject.getString("Codecr"),
                                jsonObject.getInt("Hanging4fo"),
                                jsonObject.getInt("Hanging6fo"),
                                jsonObject.getInt("Hanging12fo"),
                                jsonObject.getInt("Hanging24fo"),
                                jsonObject.getInt("Du12fo"),
                                jsonObject.getInt("Odf6fo"),
                                jsonObject.getInt("Odf12fo"),
                                jsonObject.getInt("Odf24fo"),
                                jsonObject.getInt("Closure6fo"),
                                jsonObject.getInt("Closure12fo"),
                                jsonObject.getInt("Closure24fo"),
                                jsonObject.getInt("Buloong300"),
                                jsonObject.getInt("Buloong400"),
                                jsonObject.getInt("Clamp"),
                                jsonObject.getInt("Poleu8"),
                                jsonObject.getInt("Ironpole6"),
                                jsonObject.getInt("Sc_lc5"),
                                jsonObject.getInt("Sc_lc10"),
                                jsonObject.getInt("Sc_sc5"),
                                jsonObject.getString("Datechange"),
                                jsonObject.getString("Comment"),
                                jsonObject.getString("Userchange")
                        ));
                    }catch (Exception e){
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
    private void HandlingData(){
        listItemUsed.clear();
        for (int i=0; i<listItemUsedTemp.size();i++){
            for(int j=0;j<listCrSearch.size();j++){
                if(listItemUsedTemp.get(i).getCodecr().equals(listCrSearch.get(j).getCodecr())){
                    listItemUsed.add(listItemUsedTemp.get(i));
                }
            }
        }
        int hanging4fo =0;
        int hanging6fo =0;
        int hanging12fo =0;
        int hanging24fo =0;
        int du12fo =0;
        int odf6fo =0;
        int odf12fo=0;
        int odf24fo=0;
        int mx6fo=0;
        int mx12fo=0;
        int mx24fo=0;
        int bl300=0;
        int bl400=0;
        int clamp=0;
        int poleu8=0;
        int ironpole6=0;
        int sc_lc5=0;
        int sc_lc10=0;
        int sc_sc5=0;
        for(int i=0; i<listItemUsed.size();i++){
            hanging4fo +=listItemUsed.get(i).getHanging4fo();
            hanging6fo +=listItemUsed.get(i).getHanging6fo();
            hanging12fo +=listItemUsed.get(i).getHanging12fo();
            hanging24fo +=listItemUsed.get(i).getHanging24fo();
            du12fo +=listItemUsed.get(i).getDu12fo();
            odf6fo +=listItemUsed.get(i).getOdf6fo();
            odf12fo +=listItemUsed.get(i).getOdf12fo();
            odf24fo +=listItemUsed.get(i).getOdf24fo();
            mx6fo +=listItemUsed.get(i).getMx6fo();
            mx12fo+=listItemUsed.get(i).getMx12fo();
            mx24fo+=listItemUsed.get(i).getMx24fo();
            bl300+=listItemUsed.get(i).getBl300();
            bl400+=listItemUsed.get(i).getBl400();
            clamp+=listItemUsed.get(i).getClamp();
            poleu8+=listItemUsed.get(i).getPoleu8();
            ironpole6+=listItemUsed.get(i).getIronpole6();
            sc_lc5+=listItemUsed.get(i).getSc_lc5();
            sc_lc10+=listItemUsed.get(i).getSc_lc10();
            sc_sc5+=listItemUsed.get(i).getSc_sc5();
        }
        String showMessage = "Tổng vật tư quyết toán trong kỳ là: "+"\n";
        if(hanging4fo>0){
            showMessage+="Cáp quang treo 4fo: "+hanging4fo+" mét"+"\n";
        }
        if(hanging6fo>0){
            showMessage+="Cáp quang treo 6fo: "+hanging6fo+" mét"+"\n";
        }
        if(hanging12fo>0){
            showMessage+="Cáp quang treo 12fo: "+hanging12fo+" mét"+"\n";
        }
        if(hanging24fo>0){
            showMessage+="Cáp quang treo 24fo: "+hanging24fo+" mét"+"\n";
        }
        if(du12fo>0){
            showMessage+="Cáp quang luồn cống 212fo: "+du12fo+" mét"+"\n";
        }
        if(odf6fo>0){
            showMessage+="ODF 6fo: "+odf6fo+" bộ"+"\n";
        }
        if(odf12fo>0){
            showMessage+="ODF 12fo: "+odf12fo+" bộ"+"\n";
        }
        if(odf24fo>0){
            showMessage+="ODF 24fo: "+odf24fo+" bộ"+"\n";
        }
        if(mx6fo>0){
            showMessage+="Măng xông 6fo: "+mx6fo+" bộ"+"\n";
        }
        if(mx12fo>0){
            showMessage+="Măng xông 12fo: "+mx12fo+" bộ"+"\n";
        }
        if(mx24fo>0){
            showMessage+="Măng xông 24fo: "+mx24fo+" bộ"+"\n";
        }
        if(bl300>0){
            showMessage+="Buloong ti 300: "+bl300+" bộ"+"\n";
        }
        if(bl400>0){
            showMessage+="Buloong ti 400: "+bl400+" bộ"+"\n";
        }
        if(clamp>0){
            showMessage+="Kẹp cáp 2 rãnh 3 lỗ: "+clamp+" bộ"+"\n";
        }
        if(poleu8>0){
            showMessage+="Cột điện dưới 8m: "+poleu8+" cột"+"\n";
        }
        if(ironpole6>0){
            showMessage+="Cột sắt 6 mét: "+ironpole6+" cột"+"\n";
        }
        if(sc_lc5>0){
            showMessage+="Dây nhảy quang Sc/lc 5m: "+sc_lc5+" sợi"+"\n";
        }
        if(sc_lc10>0){
            showMessage+="Dây nhảy quang Sc/lc 10m: "+sc_lc10+" sợi"+"\n";
        }
        if(sc_sc5>0){
            showMessage+="Dây nhảy quang Sc/sc 5m: "+sc_sc5+" sợi"+"\n";
        }
        generalControl.setText(showMessage);
    }
    private void createListItemUsedForIdCable(String code,String fromTime, String toTime){
        Date from = null;
        Date to = null;
        Date dateUse = null;
        try {
            from = new SimpleDateFormat("dd/MM/yyyy").parse(fromTime);
            to = new SimpleDateFormat("dd/MM/yyyy").parse(toTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        listItemUsedForIdCable.clear();
        for(int i=0;i<listItemUsedTemp.size(); i++){
            try {
                dateUse = new SimpleDateFormat("dd/MM/yyyy").parse(listItemUsedTemp.get(i).getDatechange());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(listItemUsedTemp.get(i).getCableid().toLowerCase().contains(code.toLowerCase())&& dateUse.compareTo(from)>=0
            && dateUse.compareTo(to)<=0){
                listItemUsedForIdCable.add(listItemUsedTemp.get(i));
            }
        }
    }
}