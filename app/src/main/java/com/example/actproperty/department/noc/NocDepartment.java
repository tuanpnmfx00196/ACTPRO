package com.example.actproperty.department.noc;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.actproperty.itemclick_Interface.OnItemClickRecyclerView;
import com.example.actproperty.passport.Passport;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.actproperty.R.id.checkbox;
import static com.example.actproperty.R.id.recyclerViewStatusCr;

public class NocDepartment extends AppCompatActivity implements OnItemClickRecyclerView {
    TextView timeStartSearchCR, timeToSearchCR, localSearchCR, reportCRSearch;
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
        reportCRSearch = (TextView)findViewById(R.id.reportCRSearch);
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
                listCrSearch.clear();
                getListCRSearch(timeStartSearchCR.getText().toString(),timeToSearchCR.getText().toString(),
                        localSearchCR.getText().toString());
                CheckPermissionSearch(listCrSearch);
                if(listCrSearch.size()>0){
                    frameLinearLayoutRecyclerviewListCR.setVisibility(View.VISIBLE);
                    initView();
                    int x=0;
                    int y=0;
                    int z=0;
                    for(int i=0; i<listCrSearch.size();i++){
                        if(listCrSearch.get(i).getStatuscr()==1){
                            y++;
                        } else if(listCrSearch.get(i).getStatuscr()==2){
                            x++;
                        } else if (listCrSearch.get(i).getStatuscr()==3){
                            z++;
                        }
                    }
                    reportCRSearch.setText("Tổng CR là: "+listCrSearch.size()+", trong đó CR đã thực hiện là: "+x+
                            ", CR chưa thực hiện là: "+y+", CR bị từ chối không thực hiện là: "+z);
                }else{
                    frameLinearLayoutRecyclerviewListCR.setVisibility(View.GONE);
                    reportCRSearch.setText("Không có CR phù hợp với các điều kiện tìm kiếm hiện tại");
                }
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
        listLocal.add("HCM_HMN");
        listLocal.add("HCM_Q12");
        listLocal.add("HCM_GVP");
        ArrayAdapter<String> localAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,listLocal);
        localAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnLocalSearchCR.setAdapter(localAdapter);
        spnLocalSearchCR.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (spnLocalSearchCR.getSelectedItem().toString()){
                    case "All":
                        localSearchCR.setText("Tất cả");
                        break;
                    case "BTE":
                        localSearchCR.setText("Bến Tre");
                        break;
                    case "LAN":
                        localSearchCR.setText("Long An");
                        break;
                    case "TVH":
                        localSearchCR.setText("Trà Vinh");
                        break;
                    case "KGG":
                        localSearchCR.setText("Kiên Giang");
                        break;
                    case "BDG":
                        localSearchCR.setText("Bình Dương");
                        break;
                    case "DNI":
                        localSearchCR.setText("Đồng Nai");
                        break;
                    case "HCM_BCH":
                        localSearchCR.setText("HCM_Bình Chánh");
                        break;
                    case "HCM_BTN":
                        localSearchCR.setText("HCM_Bình Tân");
                        break;
                    case "HCM_CCI":
                        localSearchCR.setText("HCM_Củ Chi");
                        break;
                    case "HCM_GVP":
                        localSearchCR.setText("HCM_Gò Vấp");
                        break;
                    case "HCM_HMN":
                        localSearchCR.setText("HCM_Hóc Môn");
                        break;
                    case "HCM_Q12":
                        localSearchCR.setText("HCM_Quận 12");
                        break;
                }
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
    private void CheckPermissionSearch(ArrayList<CRNOC>arrayList){
        ArrayList<CRNOC> listCRTemp = new ArrayList<>();
        if(listUser.get(0).getHcm_btn()==1 || listUser.get(0).getHcm_btn()==2){
            for(int i=0; i<arrayList.size();i++){
                if(arrayList.get(i).getLocal().equals("HCM_Bình Tân")){
                    listCRTemp.add(arrayList.get(i));
                }
            }
        }
        if(listUser.get(0).getBte()==1||listUser.get(0).getBte()==2){
            for(int i=0;i<arrayList.size();i++){
                if(arrayList.get(i).getLocal().equals("Bến Tre")){
                    listCRTemp.add(arrayList.get(i));
                }
            }
        }
        if(listUser.get(0).getKgg()==1||listUser.get(0).getKgg()==2){
            for(int i=0;i<arrayList.size();i++){
                if(arrayList.get(i).getLocal().equals("Kiên Giang")){
                    listCRTemp.add(arrayList.get(i));
                }
            }
        }
        if(listUser.get(0).getTvh()==1||listUser.get(0).getTvh()==2){
            for(int i=0;i<arrayList.size();i++){
                if(arrayList.get(i).getLocal().equals("Trà Vinh")){
                    listCRTemp.add(arrayList.get(i));
                }
            }
        }
        if(listUser.get(0).getDni()==1||listUser.get(0).getDni()==2){
            for(int i=0;i<arrayList.size();i++){
                if(arrayList.get(i).getLocal().equals("Đồng Nai")){
                    listCRTemp.add(arrayList.get(i));
                }
            }
        }
        if(listUser.get(0).getLan()==1||listUser.get(0).getLan()==2){
            for(int i=0;i<arrayList.size();i++){
                if(arrayList.get(i).getLocal().equals("Long An")){
                    listCRTemp.add(arrayList.get(i));
                }
            }
        }
        if(listUser.get(0).getBdg()==1||listUser.get(0).getBdg()==2){
            for(int i=0;i<arrayList.size();i++){
                if(arrayList.get(i).getLocal().equals("Bình Dương")){
                    listCRTemp.add(arrayList.get(i));
                }
            }
        }
        if(listUser.get(0).getHcm_bch()==1||listUser.get(0).getHcm_bch()==2){
            for(int i=0;i<arrayList.size();i++){
                if(arrayList.get(i).getLocal().equals("HCM_Bình Chánh")){
                    listCRTemp.add(arrayList.get(i));
                }
            }
        }
        if(listUser.get(0).getHcm_cci()==1||listUser.get(0).getHcm_cci()==2){
            for(int i=0;i<arrayList.size();i++){
                if(arrayList.get(i).getLocal().equals("HCM_Củ Chi")){
                    listCRTemp.add(arrayList.get(i));
                }
            }
        }
        if(listUser.get(0).getHcm_gvp()==1||listUser.get(0).getHcm_gvp()==2){
            for(int i=0;i<arrayList.size();i++){
                if(arrayList.get(i).getLocal().equals("HCM_Gò Vấp")){
                    listCRTemp.add(arrayList.get(i));
                }
            }
        }
        if(listUser.get(0).getHcm_hmn()==1||listUser.get(0).getHcm_hmn()==2){
            for(int i=0;i<arrayList.size();i++){
                if(arrayList.get(i).getLocal().equals("HCM_Hóc Môn")){
                    listCRTemp.add(arrayList.get(i));
                }
            }
        }
        if(listUser.get(0).getHcm_q12()==1||listUser.get(0).getHcm_q12()==2){
            for(int i=0;i<arrayList.size();i++){
                if(arrayList.get(i).getLocal().equals("HCM_Q12")){
                    listCRTemp.add(arrayList.get(i));
                }
            }
        }
        if(listUser.get(0).getAdmin()==1||listUser.get(0).getNoc()==1||listUser.get(0).getAdmin()==2){
            listCRTemp.addAll(arrayList);
        }
    listCrSearch.clear();
    listCrSearch.addAll(listCRTemp);
    }
    private void initView(){
        RecyclerView recyclerViewListCR = (RecyclerView)findViewById(R.id.recyclerViewListCR);
        recyclerViewListCR.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerViewListCR.setLayoutManager(linearLayoutManager);
        ListCRAdapter adapter = new ListCRAdapter(listCrSearch, getApplicationContext(),this);
        recyclerViewListCR.setAdapter(adapter);
    }

    @Override
    public void onClick(int position) {

    }

    @Override
    public void onClick1(int position) {

    }

    @Override
    public void onClick2(int position) {

    }

    @Override
    public void onClick3(int position) {

    }
}