package com.example.actproperty;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.actproperty.department.noc.CRNOC;
import com.example.actproperty.inventory.DashboardInventory;
import com.example.actproperty.inventory.MaterialsInventory;
import com.example.actproperty.itemclick_Interface.OnItemClickRecyclerView;
import com.example.actproperty.passport.Passport;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* pass database cable --->>> |aSGl2|vqrg/EfWh */
public class Management extends AppCompatActivity implements OnItemClickRecyclerView {
    Button btnSearch, checkCR;
    EditText searchLocal, searchCableId;
    ArrayList<MaterialsInventory> listInventory;
    ArrayList<Passport>listUser;
    ArrayList<CableId> listCable;
    ArrayList<CableId> list;
    ArrayList<CableId> listSearch;
    ArrayList<CableId> listShow;
    ArrayList<CRNOC>listCR;
    CableIdAdapter adapter;
    FrameLayout frameContain;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);
        adapter = new CableIdAdapter(listCable,Management.this,this);
        searchLocal = (EditText) findViewById(R.id.searchLocal);
        searchCableId = (EditText) findViewById(R.id.searchCableId);
        listCable = new ArrayList<>();
        listShow = new ArrayList<>();
        listUser = new ArrayList<>();
        listInventory = new ArrayList<>();
        listCR = new ArrayList<>();
        GetAccount();
        getInventory("https://sqlandroid2812.000webhostapp.com/getinventory.php");
        list = new ArrayList<>();
        listSearch = new ArrayList<>();
        frameContain = (FrameLayout) findViewById(R.id.frameContain);
        /*====================== Fragment =========================*/
        Show();
        /*====================== Click Btn Search============================*/
        btnSearch = (Button)findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameContain.setVisibility(View.VISIBLE);
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(frameContain.getApplicationWindowToken(), 0);
                searchCableId.clearFocus();
                searchLocal.clearFocus();
                ReadJsonSeach("https://sqlandroid2812.000webhostapp.com/getdata.php");
                //getInventory("https://sqlandroid2812.000webhostapp.com/getinventory.php");
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                FragmentSearch fragmentSearch = new FragmentSearch();
                fragmentTransaction.replace(R.id.frameContain,fragmentSearch);
                fragmentTransaction.commit();
            }
        });
        searchLocal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    frameContain.setVisibility(View.GONE);
                }
            }
        });
        searchCableId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    frameContain.setVisibility(View.GONE);
                }
            }
        });
        getCR("https://sqlandroid2812.000webhostapp.com/getnoc.php");
    }
    /*=========================== Read Json ===============================*/
    public void ReadJson(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i=0; i<response.length(); i++){
                    try{
                        JSONObject jsonObject = response.getJSONObject(i);
                        listCable.add(new CableId(
                                jsonObject.getInt("ID"),
                                jsonObject.getString("Province"),
                                jsonObject.getString("CableId"),
                                jsonObject.getInt("Hanging4fo"),
                                jsonObject.getInt("Hanging6fo"),
                                jsonObject.getInt("Hanging12fo"),
                                jsonObject.getInt("Hanging24fo"),
                                jsonObject.getInt("Du12fo"),
                                jsonObject.getInt("Odf6fo"),
                                jsonObject.getInt("Odf12fo"),
                                jsonObject.getInt("Odf24fo"),
                                jsonObject.getInt("Odf96fo"),
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
                                jsonObject.getInt("Cr")
                        ));
                    }catch(Exception e){
                        Toast.makeText(Management.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                Permission();
                    adapter.notifyDataSetChanged();
                    checkCR = (Button)findViewById(R.id.checkCR);
                    checkCR.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ArrayList<CableId>listTemp = new ArrayList<>();
                            listTemp.addAll(listShow);
                            GetCheckCR(listTemp);
                            initView();
                        }
                    });
                    initView();
            }
        },
                new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                 Toast.makeText(Management.this, "Lỗi read JSON", Toast.LENGTH_SHORT).show();
                            }
                        }
     );
        requestQueue.add(jsonArrayRequest);
    }

    /*============================Read Json search=====================================*/
    public void ReadJsonSeach(String url){
        list.clear();
        listSearch.clear();
        searchLocal = (EditText)findViewById(R.id.searchLocal);
        searchCableId = (EditText)findViewById(R.id.searchCableId);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i=0; i<response.length(); i++){
                            try{
                                JSONObject jsonObject = response.getJSONObject(i);
                                list.add(new CableId(
                                        jsonObject.getInt("ID"),
                                        jsonObject.getString("Province"),
                                        jsonObject.getString("CableId"),
                                        jsonObject.getInt("Hanging4fo"),
                                        jsonObject.getInt("Hanging6fo"),
                                        jsonObject.getInt("Hanging12fo"),
                                        jsonObject.getInt("Hanging24fo"),
                                        jsonObject.getInt("Du12fo"),
                                        jsonObject.getInt("Odf6fo"),
                                        jsonObject.getInt("Odf12fo"),
                                        jsonObject.getInt("Odf24fo"),
                                        jsonObject.getInt("Odf96fo"),
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
                                        jsonObject.getInt("Cr")
                                ));
                            }catch(Exception e){
                                Toast.makeText(Management.this, "Lỗi read JSON search", Toast.LENGTH_SHORT).show();

                            }
                        }
                        //Module Search ================================>>>>>>>>>>>>>>>>>
                            if(searchLocal.getText().toString().trim().length()>0){
                                if(searchCableId.getText().toString().trim().length()>0){
                                    for(int i=0; i<list.size();i++){
                                        if(list.get(i).getProvince().toLowerCase().contains(
                                                searchLocal.getText().toString().trim().toLowerCase()
                                        )){
                                            if(list.get(i).getCableId().toLowerCase().contains(
                                                    searchCableId.getText().toString().trim().toLowerCase()
                                            )){
                                                listSearch.add(list.get(i));
                                            }
                                        }
                                    }
                                }else{
                                    for(int i=0;i<list.size();i++){
                                        if(list.get(i).getProvince().toLowerCase().contains(
                                                searchLocal.getText().toString().trim().toLowerCase()
                                        )){
                                            listSearch.add(list.get(i));
                                        }
                                    }
                                }
                            }else{
                                for (int i=0; i<list.size(); i++){
                                    if(list.get(i).getCableId().toLowerCase().contains(
                                            searchCableId.getText().toString().trim().toLowerCase()
                                    )){
                                        listSearch.add(list.get(i));
                                    }
                                }
                            }

                        GetSearch(listSearch);
                        checkCR = (Button)findViewById(R.id.checkCR);
                        checkCR.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ArrayList<CableId>listTemp = new ArrayList<>();
                                listTemp.addAll(listShow);
                                GetCheckCR(listTemp);
                                initViewSearch();
                            }
                        });

                        //End search ================================>>>>>>>>>>>>>>
                        adapter.notifyDataSetChanged();
                        initViewSearch();
                        searchLocal.setText("");
                        searchCableId.setText("");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Management.this, "Connection SQL error", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
    /*=========================== INIT RecyclerView Manager ==========================*/
    public void initView(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        CableIdAdapter cableIdAdapter = new CableIdAdapter(listShow, getApplicationContext(),this);
        recyclerView.setAdapter(cableIdAdapter);
    }
    /*======================= INIT RecyclerView Fragment search ====================*/
    public void initViewSearch(){
       RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView1);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        CableIdAdapter cableIdAdapter = new CableIdAdapter(listShow, getApplicationContext(),this);
        recyclerView.setAdapter(cableIdAdapter);
    }

    public void Show(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        ReadJson("https://sqlandroid2812.000webhostapp.com/getdata.php");
        FragmentRecycler fragmentRecycler = new FragmentRecycler();
        fragmentTransaction.add(R.id.frameContain,fragmentRecycler);
        fragmentTransaction.commit();
    }
    public void GetAccount(){
        Intent intent = getIntent();
        listUser = (ArrayList<Passport>) intent.getSerializableExtra("Account");
    }

    public void Permission() {
        if (listUser.get(0).getAdmin() == 1 || listUser.get(0).getAdmin() == 2 || listUser.get(0).getNoc() == 1) {
            for (int i = 0; i < listCable.size(); i++) {
                listShow.add(listCable.get(i));
            }
        } else {
            if (listUser.get(0).getHcm_bch() == 1 || listUser.get(0).getHcm_bch() == 2) {
                for (int i = 0; i < listCable.size(); i++) {
                    if (listCable.get(i).getProvince().equals("HCM_Bình Chánh")) {
                        listShow.add(listCable.get(i));
                    }
                }
            }
            if (listUser.get(0).getHcm_btn() == 1 || listUser.get(0).getHcm_btn() == 2) {
                for (int i = 0; i < listCable.size(); i++) {
                    if (listCable.get(i).getProvince().equals("HCM_Bình Tân")) {
                        listShow.add(listCable.get(i));
                    }
                }
            }
            if (listUser.get(0).getHcm_cci() == 1 || listUser.get(0).getHcm_cci() == 2) {
                for (int i = 0; i < listCable.size(); i++) {
                    if (listCable.get(i).getProvince().equals("HCM_Củ Chi")) {
                        listShow.add(listCable.get(i));
                    }
                }
            }
            if (listUser.get(0).getHcm_gvp() == 1 || listUser.get(0).getHcm_gvp() == 2) {
                for (int i = 0; i < listCable.size(); i++) {
                    if (listCable.get(i).getProvince().equals("HCM_Gò Vấp")) {
                        listShow.add(listCable.get(i));
                    }
                }
            }
            if (listUser.get(0).getHcm_q12() == 1 || listUser.get(0).getHcm_q12() == 2) {
                for (int i = 0; i < listCable.size(); i++) {
                    if (listCable.get(i).getProvince().equals("HCM_Quận 12")) {
                        listShow.add(listCable.get(i));
                    }
                }
            }
            if (listUser.get(0).getHcm_hmn() == 1 || listUser.get(0).getHcm_hmn() == 2) {
                for (int i = 0; i < listCable.size(); i++) {
                    if (listCable.get(i).getProvince().equals("HCM_Hóc Môn")) {
                        listShow.add(listCable.get(i));
                    }
                }
            }
            if (listUser.get(0).getBdg() == 1 || listUser.get(0).getBdg() == 2) {
                for (int i = 0; i < listCable.size(); i++) {
                    if (listCable.get(i).getProvince().equals("Bình Dương")) {
                        listShow.add(listCable.get(i));
                    }
                }
            }
            if (listUser.get(0).getKgg() == 1 || listUser.get(0).getKgg() == 2) {
                for (int i = 0; i < listCable.size(); i++) {
                    if (listCable.get(i).getProvince().equals("Kiên Giang")) {
                        listShow.add(listCable.get(i));
                    }
                }
            }
            if (listUser.get(0).getBte() == 1 || listUser.get(0).getKgg() == 2) {
                for (int i = 0; i < listCable.size(); i++) {
                    if (listCable.get(i).getProvince().equals("Bến Tre")) {
                        listShow.add(listCable.get(i));
                    }
                }
            }
            if (listUser.get(0).getTvh() == 1 || listUser.get(0).getTvh() == 2) {
                for (int i = 0; i < listCable.size(); i++) {
                    if (listCable.get(i).getProvince().equals("Trà Vinh")) {
                        listShow.add(listCable.get(i));
                    }
                }
            }
            if (listUser.get(0).getDni() == 1 || listUser.get(0).getDni() == 2) {
                for (int i = 0; i < listCable.size(); i++) {
                    if (listCable.get(i).getProvince().equals("Đồng Nai")) {
                        listShow.add(listCable.get(i));
                    }
                }
            }
            if (listUser.get(0).getLan() == 1 || listUser.get(0).getLan() == 2) {
                for (int i = 0; i < listCable.size(); i++) {
                    if (listCable.get(i).getProvince().equals("Long An")) {
                        listShow.add(listCable.get(i));
                    }
                }
            }
        }
    }
    private void GetSearch(ArrayList<CableId> arrayList) {
        listShow.clear();
        if (listUser.get(0).getAdmin() == 1 || listUser.get(0).getAdmin() == 2 || listUser.get(0).getNoc() == 1) {
            for (int i = 0; i < arrayList.size(); i++) {
                listShow.add(arrayList.get(i));
            }
        } else {
            if (listUser.get(0).getHcm_bch() == 1) {
                for (int i = 0; i < arrayList.size(); i++) {
                    if (arrayList.get(i).getProvince().equals("HCM_Bình Chánh")) {
                        listShow.add(arrayList.get(i));
                    }
                }
            }
            if (listUser.get(0).getBdg() == 1) {
                for (int i = 0; i < arrayList.size(); i++) {
                    if (arrayList.get(i).getProvince().equals("Bình Dương")) {
                        listShow.add(arrayList.get(i));
                    }
                }
            }
            if (listUser.get(0).getKgg() == 1 || listUser.get(0).getKgg() == 2) {
                for (int i = 0; i < arrayList.size(); i++) {
                    if (arrayList.get(i).getProvince().equals("Kiên Giang")) {
                        listShow.add(arrayList.get(i));
                    }
                }
            }
            if (listUser.get(0).getTvh() == 1 || listUser.get(0).getTvh() == 2) {
                for (int i = 0; i < arrayList.size(); i++) {
                    if (arrayList.get(i).getProvince().equals("Trà Vinh")) {
                        listShow.add(arrayList.get(i));
                    }
                }
            }
            if (listUser.get(0).getBte() == 1 || listUser.get(0).getBte() == 2) {
                for (int i = 0; i < arrayList.size(); i++) {
                    if (arrayList.get(i).getProvince().equals("Bến Tre")) {
                        listShow.add(arrayList.get(i));
                    }
                }
            }
            if (listUser.get(0).getLan() == 1 || listUser.get(0).getLan() == 2) {
                for (int i = 0; i < arrayList.size(); i++) {
                    if (arrayList.get(i).getProvince().equals("Long An")) {
                        listShow.add(arrayList.get(i));
                    }
                }
            }
            if (listUser.get(0).getDni() == 1 || listUser.get(0).getDni() == 2) {
                for (int i = 0; i < arrayList.size(); i++) {
                    if (arrayList.get(i).getProvince().equals("Đồng Nai")) {
                        listShow.add(arrayList.get(i));
                    }
                }
            }
        }
    }
    private void GetCheckCR(ArrayList<CableId>arrayList){
        listShow.clear();
        for(int i=0; i<arrayList.size();i++){
            if(arrayList.get(i).getCr()==1){
                listShow.add(arrayList.get(i));
            }
        }
    }
    @Override
    public void onClick(int position) {
        ShowMoreDetail(position);
    }

    @Override
    public void onClick1(int position) {
        ShowMoreDetail(position);
    }

    @Override
    public void onClick2(int position) {
        //Toast.makeText(this, "Listshow "+listShow.get(position).getId()+", listcable "+listCable.get(position).getId(), Toast.LENGTH_SHORT).show();
        CheckNoc(listShow.get(position).getId(),listShow.get(position).getProvince(),listShow.get(position).getCableId(),listUser.get(0).getUser());

    }

    @Override
    public void onClick3(int position) {
        UpdateCRSQL("https://sqlandroid2812.000webhostapp.com/updatecrdata.php",listShow.get(position).getId(),0);
        int idCR=0;
        for(int i=0; i<listCR.size();i++){
            if(listCR.get(i).getId_origin()==(listShow.get(position).getId())&&listCR.get(i).getStatuscr()==1){
                idCR=i+1;
            }
        }
        UpdateNOC("https://sqlandroid2812.000webhostapp.com/updatenoc.php",idCR,3);
        Intent intent = new Intent(Management.this, DashBoard.class);
        intent.putExtra("Account",listUser);
        startActivity(intent);
    }

    private void ShowMoreDetail(final int position){
        final Dialog dialog = new Dialog(this);
        dialog.setTitle(listShow.get(position).getCableId());
        dialog.setContentView(R.layout.show_more);
        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.97);
        dialog.getWindow().setLayout(width, LinearLayout.LayoutParams.WRAP_CONTENT);
        final TextView cable4fo,cable4fo_origin, cable6fo,cable6fo_origin, cable12fo,cable12fo_origin, cable24fo,cable24fo_origin,
                cable12du, cable12du_origin, odf6, odf6_origin, odf12,odf12_origin, odf24, odf24_origin, odf96, odf96_origin,
                mx6,mx6_origin, mx12,mx12_origin, mx24,mx24_origin, buloongti300, buloongti300_origin, buloongti400, buloongti400_origin,
                poleu8, poleu8_origin,  ironpole6, ironpole6_origin, clamp, clamp_origin, sc_lc5m,sc_lc5m_origin,
                sc_lc10m, sc_lc10m_origin, sc_sc5m, sc_sc5m_origin, dialogTitle, dialogTitle_update;
        final EditText cable4fo_used, cable6fo_used, cable12fo_used, cable24fo_used, cable12du_used, odf6fo_used,
                odf12fo_used, odf24fo_used, odf96fo_used, mx6_used, mx12_used, mx24_used, buloong300_used, buloong400_used,
                clamp_used, poleu8_used, ironpole6_used, sc_lc5_used, sc_lc10_used, sc_sc5_used, commentEdit,
                cable4fo_update, cable6fo_update, cable12fo_update, cable24fo_update, cable12du_update, odf6fo_update,
                odf12fo_update, odf24fo_update, odf96fo_update, mx6_update, mx12_update, mx24_update, buloong300_update, buloong400_update,
                clamp_update, poleu8_update, ironpole6_update, sc_lc5_update, sc_lc10_update, sc_sc5_update;
        LinearLayout row4fo, row4fo_update, row6fo, row6fo_update, row12fo, row12fo_update, row24fo, row24fo_update,
                rowdu12fo,rowdu12fo_update, rowodf6fo, rowodf6fo_update, rowodf12fo, rowodf12fo_update, rowodf24fo, rowodf24fo_update,
                rowodf96fo, rowodf96fo_update, rowmx6, rowmx6_update, rowmx12,rowmx12_update, rowmx24,rowmx24_update, rowbuloongti300,
                rowbuloongti300_update, rowbuloongti400, rowbuloongti400_update, rowpoleu8, rowpoleu8_update, rowironpole6, rowironpole6_update,
                rowclamp,rowclamp_update, rowsc_lc5m, rowsc_lc5m_update, rowsc_lc10m, rowsc_lc10m_update, rowsc_sc5m, rowsc_sc5m_update;
        dialogTitle= (TextView)dialog.findViewById(R.id.dialogTitle);
        dialogTitle_update= (TextView)dialog.findViewById(R.id.dialogTitle_update);
        cable4fo = (TextView)dialog.findViewById(R.id.cable4fo);
        cable4fo_origin = (TextView)dialog.findViewById(R.id.cable4fo_origin);
        cable6fo = (TextView)dialog.findViewById(R.id.cable6fo);
        cable6fo_origin = (TextView)dialog.findViewById(R.id.cable6fo_origin);
        cable12fo = (TextView)dialog.findViewById(R.id.cable12fo);
        cable12fo_origin = (TextView)dialog.findViewById(R.id.cable12fo_origin);
        cable24fo = (TextView)dialog.findViewById(R.id.cable24fo);
        cable24fo_origin = (TextView)dialog.findViewById(R.id.cable24fo_origin);
        cable12du = (TextView)dialog.findViewById(R.id.cable12du);
        cable12du_origin = (TextView)dialog.findViewById(R.id.cable12du_origin);
        odf6 = (TextView)dialog.findViewById(R.id.odf6);
        odf6_origin = (TextView)dialog.findViewById(R.id.odf6_origin);
        odf12 = (TextView)dialog.findViewById(R.id.odf12);
        odf12_origin = (TextView)dialog.findViewById(R.id.odf12_origin);
        odf24 = (TextView)dialog.findViewById(R.id.odf24);
        odf24_origin = (TextView)dialog.findViewById(R.id.odf24_origin);
        odf96 = (TextView)dialog.findViewById(R.id.odf96);
        odf96_origin = (TextView)dialog.findViewById(R.id.odf96_origin);
        mx6 = (TextView)dialog.findViewById(R.id.mx6);
        mx6_origin = (TextView)dialog.findViewById(R.id.mx6_origin);
        mx12 = (TextView)dialog.findViewById(R.id.mx12);
        mx12_origin = (TextView)dialog.findViewById(R.id.mx12_origin);
        mx24 = (TextView)dialog.findViewById(R.id.mx24);
        mx24_origin = (TextView)dialog.findViewById(R.id.mx24_origin);
        buloongti300 = (TextView)dialog.findViewById(R.id.buloongti300);
        buloongti300_origin = (TextView)dialog.findViewById(R.id.buloongti300_origin);
        buloongti400 = (TextView)dialog.findViewById(R.id.buloongti400);
        buloongti400_origin = (TextView)dialog.findViewById(R.id.buloongti400_origin);
        poleu8 = (TextView) dialog.findViewById(R.id.poleu8);
        poleu8_origin = (TextView) dialog.findViewById(R.id.poleu8_origin);
        ironpole6 = (TextView) dialog.findViewById(R.id.ironpole6);
        ironpole6_origin = (TextView) dialog.findViewById(R.id.ironpole6_origin);
        clamp = (TextView)dialog.findViewById(R.id.clamp);
        clamp_origin = (TextView)dialog.findViewById(R.id.clamp_origin);
        sc_lc5m = (TextView)dialog.findViewById(R.id.sc_lc5m);
        sc_lc5m_origin = (TextView)dialog.findViewById(R.id.sc_lc5m_origin);
        sc_lc10m = (TextView)dialog.findViewById(R.id.sc_lc10m);
        sc_lc10m_origin = (TextView)dialog.findViewById(R.id.sc_lc10m_origin);
        sc_sc5m = (TextView)dialog.findViewById(R.id.sc_sc5m);
        sc_sc5m_origin = (TextView)dialog.findViewById(R.id.sc_sc5m_origin);
        final Button btnUpdate = (Button)dialog.findViewById(R.id.btnUpdate);
        final Button btnBack = (Button)dialog.findViewById(R.id.btnBack);
        final Button btnSaveUpdate = (Button)dialog.findViewById(R.id.btnSaveUpdate);
        final Button btnCancelUpdate = (Button)dialog.findViewById(R.id.btnCancelUpdate);
        final LinearLayout showmore = (LinearLayout) dialog.findViewById(R.id.showmore);
        final LinearLayout showUpdate = (LinearLayout) dialog.findViewById(R.id.showUpdate);

        //ROW INVISIBLE
        row4fo = (LinearLayout)dialog.findViewById(R.id.row4fo);
        row4fo_update = (LinearLayout)dialog.findViewById(R.id.row4fo_update);
        row6fo = (LinearLayout)dialog.findViewById(R.id.row6fo);
        row6fo_update = (LinearLayout)dialog.findViewById(R.id.row6fo_update);
        row12fo = (LinearLayout)dialog.findViewById(R.id.row12fo);
        row12fo_update = (LinearLayout)dialog.findViewById(R.id.row12fo_update);
        row24fo = (LinearLayout)dialog.findViewById(R.id.row24fo);
        row24fo_update = (LinearLayout)dialog.findViewById(R.id.row24fo_update);
        rowdu12fo = (LinearLayout)dialog.findViewById(R.id.rowdu12fo);
        rowdu12fo_update = (LinearLayout)dialog.findViewById(R.id.rowdu12fo_update);
        rowodf6fo = (LinearLayout)dialog.findViewById(R.id.rowodf6fo);
        rowodf6fo_update = (LinearLayout)dialog.findViewById(R.id.rowodf6fo_update);
        rowodf12fo = (LinearLayout)dialog.findViewById(R.id.rowodf12fo);
        rowodf12fo_update = (LinearLayout)dialog.findViewById(R.id.rowodf12fo_update);
        rowodf24fo = (LinearLayout)dialog.findViewById(R.id.rowodf24fo);
        rowodf24fo_update = (LinearLayout)dialog.findViewById(R.id.rowodf24fo_update);
        rowodf96fo = (LinearLayout)dialog.findViewById(R.id.rowodf96fo);
        rowodf96fo_update = (LinearLayout)dialog.findViewById(R.id.rowodf96fo_update);
        rowmx6 = (LinearLayout)dialog.findViewById(R.id.rowmx6);
        rowmx6_update = (LinearLayout)dialog.findViewById(R.id.rowmx6_update);
        rowmx12 = (LinearLayout)dialog.findViewById(R.id.rowmx12);
        rowmx12_update = (LinearLayout)dialog.findViewById(R.id.rowmx12_update);
        rowmx24 = (LinearLayout)dialog.findViewById(R.id.rowmx24);
        rowmx24_update = (LinearLayout)dialog.findViewById(R.id.rowmx24_update);
        rowbuloongti300 = (LinearLayout)dialog.findViewById(R.id.rowbuloongti300);
        rowbuloongti300_update = (LinearLayout)dialog.findViewById(R.id.rowbuloongti300_update);
        rowbuloongti400 = (LinearLayout)dialog.findViewById(R.id.rowbuloongti400);
        rowbuloongti400_update = (LinearLayout)dialog.findViewById(R.id.rowbuloongti400_update);
        rowpoleu8 = (LinearLayout)dialog.findViewById(R.id.rowpoleu8);
        rowpoleu8_update = (LinearLayout)dialog.findViewById(R.id.rowpoleu8_update);
        rowironpole6 = (LinearLayout)dialog.findViewById(R.id.rowironpole6);
        rowironpole6_update = (LinearLayout)dialog.findViewById(R.id.rowironpole6_update);
        rowclamp = (LinearLayout)dialog.findViewById(R.id.rowclamp);
        rowclamp_update = (LinearLayout)dialog.findViewById(R.id.rowclamp_update);
        rowsc_lc5m = (LinearLayout)dialog.findViewById(R.id.rowsc_lc5m);
        rowsc_lc5m_update = (LinearLayout)dialog.findViewById(R.id.rowsc_lc5m_update);
        rowsc_lc10m = (LinearLayout)dialog.findViewById(R.id.rowsc_lc10m);
        rowsc_lc10m_update = (LinearLayout)dialog.findViewById(R.id.rowsc_lc10m_update);
        rowsc_sc5m = (LinearLayout)dialog.findViewById(R.id.rowsc_sc5m);
        rowsc_sc5m_update = (LinearLayout)dialog.findViewById(R.id.rowsc_sc5m_update);

        /*======================EDIT CABLE============================*/

        cable4fo_used = (EditText) dialog.findViewById(R.id.cable4fo_used);
        cable6fo_used = (EditText) dialog.findViewById(R.id.cable6fo_used);
        cable12fo_used = (EditText) dialog.findViewById(R.id.cable12fo_used);
        cable24fo_used = (EditText) dialog.findViewById(R.id.cable24fo_used);
        cable12du_used = (EditText) dialog.findViewById(R.id.cable12du_used);
        odf6fo_used = (EditText)dialog.findViewById(R.id.odf6_used);
        odf12fo_used = (EditText)dialog.findViewById(R.id.odf12_used);
        odf24fo_used = (EditText)dialog.findViewById(R.id.cable24fo_used);
        odf96fo_used = (EditText) dialog.findViewById(R.id.odf96_used);
        mx6_used = (EditText) dialog.findViewById(R.id.mx6_used);
        mx12_used = (EditText) dialog.findViewById(R.id.mx12_used);
        mx24_used = (EditText) dialog.findViewById(R.id.mx24_used);
        clamp_used = (EditText) dialog.findViewById(R.id.clamp_used);
        poleu8_used = (EditText) dialog.findViewById(R.id.poleu8_used);
        ironpole6_used = (EditText) dialog.findViewById(R.id.ironpole6_used);
        buloong300_used = (EditText) dialog.findViewById(R.id.buloongti300_used);
        buloong400_used = (EditText) dialog.findViewById(R.id.buloongti400_used);
        sc_lc5_used = (EditText) dialog.findViewById(R.id.sc_lc5m_used);
        sc_lc10_used = (EditText) dialog.findViewById(R.id.sc_lc10m_used);
        sc_sc5_used = (EditText) dialog.findViewById(R.id.sc_sc5m_used);
        commentEdit = (EditText)dialog.findViewById(R.id.commentEdit);

        /*======================EDIT CABLE============================*/

        cable4fo_update = (EditText) dialog.findViewById(R.id.cable4fo_update);
        cable6fo_update = (EditText) dialog.findViewById(R.id.cable6fo_update);
        cable12fo_update = (EditText) dialog.findViewById(R.id.cable12fo_update);
        cable24fo_update = (EditText) dialog.findViewById(R.id.cable24fo_update);
        cable12du_update = (EditText) dialog.findViewById(R.id.cable12du_update);
        odf6fo_update = (EditText)dialog.findViewById(R.id.odf6_update);
        odf12fo_update = (EditText)dialog.findViewById(R.id.odf12_update);
        odf24fo_update = (EditText)dialog.findViewById(R.id.cable24fo_update);
        odf96fo_update = (EditText) dialog.findViewById(R.id.odf96_update);
        mx6_update = (EditText) dialog.findViewById(R.id.mx6_update);
        mx12_update = (EditText) dialog.findViewById(R.id.mx12_update);
        mx24_update = (EditText) dialog.findViewById(R.id.mx24_update);
        clamp_update = (EditText) dialog.findViewById(R.id.clamp_update);
        poleu8_update = (EditText) dialog.findViewById(R.id.poleu8_update);
        ironpole6_update = (EditText) dialog.findViewById(R.id.ironpole6_update);
        buloong300_update = (EditText) dialog.findViewById(R.id.buloongti300_update);
        buloong400_update = (EditText) dialog.findViewById(R.id.buloongti400_update);
        sc_lc5_update = (EditText) dialog.findViewById(R.id.sc_lc5m_update);
        sc_lc10_update = (EditText) dialog.findViewById(R.id.sc_lc10m_update);
        sc_sc5_update = (EditText) dialog.findViewById(R.id.sc_sc5m_update);
        /*======================Get Data Dialog======================*/
            dialogTitle.setText(listShow.get(position).getCableId());
            if(listShow.get(position).getHanging4fo()!=0){
                cable4fo.setText(listShow.get(position).getHanging4fo()+"");
                row4fo.setVisibility(View.VISIBLE);
            } else{
                row4fo.setVisibility(View.GONE);
            }
            if(listShow.get(position).getHanging6fo()!=0){
                cable6fo.setText(listShow.get(position).getHanging6fo()+"");
                row6fo.setVisibility(View.VISIBLE);
            } else{
                row6fo.setVisibility(View.GONE);
            }
        if(listShow.get(position).getHanging12fo()!=0){
            cable12fo.setText(listShow.get(position).getHanging12fo()+"");
            row12fo.setVisibility(View.VISIBLE);
        } else{
            row12fo.setVisibility(View.GONE);
        }
        if(listShow.get(position).getHanging24fo()!=0){
            cable24fo.setText(listShow.get(position).getHanging24fo()+"");
            row24fo.setVisibility(View.VISIBLE);
        } else{
            row24fo.setVisibility(View.GONE);
        }
        if(listShow.get(position).getDu12fo()!=0){
            cable12du.setText(listShow.get(position).getDu12fo()+"");
            rowdu12fo.setVisibility(View.VISIBLE);
        } else{
            rowdu12fo.setVisibility(View.GONE);
        }
        if(listShow.get(position).getOdf6fo()!=0){
            odf6.setText(listShow.get(position).getOdf6fo()+"");
            rowodf6fo.setVisibility(View.VISIBLE);
        } else{
            rowodf6fo.setVisibility(View.GONE);
        }
        if(listShow.get(position).getOdf12fo()!=0){
            odf12.setText(listShow.get(position).getOdf12fo()+"");
            rowodf12fo.setVisibility(View.VISIBLE);
        } else{
            rowodf12fo.setVisibility(View.GONE);
        }
        if(listShow.get(position).getOdf24fo()!=0){
            odf24.setText(listShow.get(position).getOdf24fo()+"");
            rowodf24fo.setVisibility(View.VISIBLE);
        } else{
            rowodf24fo.setVisibility(View.GONE);
        }
        if(listShow.get(position).getOdf96fo()!=0){
            odf96.setText(listShow.get(position).getOdf96fo()+"");
            rowodf96fo.setVisibility(View.VISIBLE);
        } else{
            rowodf24fo.setVisibility(View.GONE);
        }
        if(listShow.get(position).getClosure6fo()!=0){
            mx6.setText(listShow.get(position).getClosure6fo()+"");
            rowmx6.setVisibility(View.VISIBLE);
        } else if(listShow.get(position).getHanging6fo()!=0){
            mx6.setText("0");
            rowmx6.setVisibility(View.VISIBLE);
        } else{
            rowmx6.setVisibility(View.GONE);
        }
        if(listShow.get(position).getClosure12fo()!=0){
            mx12.setText(listShow.get(position).getClosure12fo()+"");
            rowmx12.setVisibility(View.VISIBLE);
        } else if (listShow.get(position).getHanging12fo()!=0){
            mx12.setText("0");
            rowmx12.setVisibility(View.VISIBLE);
        }
        else{
            rowmx12.setVisibility(View.GONE);
        }
        if(listShow.get(position).getClosure24fo()!=0){
            mx24.setText(listShow.get(position).getClosure24fo()+"");
            rowmx24.setVisibility(View.VISIBLE);
        } else if(listShow.get(position).getHanging24fo()!=0){
            mx24.setText("0");
            rowmx24.setVisibility(View.VISIBLE);
        }
        else{
            rowmx24.setVisibility(View.GONE);
        }
        if(listShow.get(position).getBuloong300()!=0){
            buloongti300.setText(listShow.get(position).getBuloong300()+"");
            rowbuloongti300.setVisibility(View.VISIBLE);
        } else{
            rowbuloongti300.setVisibility(View.GONE);
        }
        if(listShow.get(position).getBuloong400()!=0){
            buloongti400.setText(listShow.get(position).getBuloong400()+"");
            rowbuloongti400.setVisibility(View.VISIBLE);
        } else{
            rowbuloongti400.setVisibility(View.GONE);
        }
        if(listShow.get(position).getClamp()!=0){
            clamp.setText(listShow.get(position).getClamp()+"");
            rowclamp.setVisibility(View.VISIBLE);
        } else{
            rowclamp.setVisibility(View.GONE);
        }
        if(listShow.get(position).getPoleu8()!=0){
            poleu8.setText(listShow.get(position).getPoleu8()+"");
            rowpoleu8.setVisibility(View.VISIBLE);
        } else{
            rowpoleu8.setVisibility(View.GONE);
        }
        if(listShow.get(position).getIronpole6()!=0){
            ironpole6.setText(listShow.get(position).getIronpole6()+"");
            rowironpole6.setVisibility(View.VISIBLE);
        } else{
            rowironpole6.setVisibility(View.GONE);
        }
        if(listShow.get(position).getSc_lc5()!=0){
            sc_lc5m.setText(listShow.get(position).getSc_lc5()+"");
            rowsc_lc5m.setVisibility(View.VISIBLE);
        } else{
            rowsc_lc5m.setVisibility(View.GONE);
        }
        if(listShow.get(position).getSc_lc10()!=0){
            sc_lc10m.setText(listShow.get(position).getSc_lc5()+"");
            rowsc_lc10m.setVisibility(View.VISIBLE);
        } else{
            rowsc_lc10m.setVisibility(View.GONE);
        }
        if(listShow.get(position).getSc_sc5()!=0){
            sc_sc5m.setText(listShow.get(position).getSc_sc5()+"");
            rowsc_sc5m.setVisibility(View.VISIBLE);
        } else{
            rowsc_sc5m.setVisibility(View.GONE);
        }

        /*====================== DIALOG UPDATE ========================*/
        if(listShow.get(position).getHanging4fo()!=0){
            cable4fo_origin.setText(listShow.get(position).getHanging4fo()+"");
            cable4fo_update.setText(listShow.get(position).getHanging4fo()+"");
            row4fo_update.setVisibility(View.VISIBLE);
        } else{
            row4fo_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getHanging6fo()!=0){
            cable6fo_origin.setText(listShow.get(position).getHanging6fo()+"");
            cable6fo_update.setText(listShow.get(position).getHanging6fo()+"");
            row6fo_update.setVisibility(View.VISIBLE);
        } else{
            row6fo_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getHanging12fo()!=0){
            cable12fo_origin.setText(listShow.get(position).getHanging12fo()+"");
            cable12fo_update.setText(listShow.get(position).getHanging12fo()+"");
            row12fo_update.setVisibility(View.VISIBLE);
        } else{
            row12fo_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getHanging24fo()!=0){
            cable24fo_origin.setText(listShow.get(position).getHanging24fo()+"");
            cable24fo_update.setText(listShow.get(position).getHanging24fo()+"");
            row24fo_update.setVisibility(View.VISIBLE);
        } else{
            row24fo_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getDu12fo()!=0){
            cable12du_origin.setText(listShow.get(position).getDu12fo()+"");
            cable12du_update.setText(listShow.get(position).getDu12fo()+"");
            rowdu12fo_update.setVisibility(View.VISIBLE);
        } else{
            rowdu12fo_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getOdf6fo()!=0){
            odf6_origin.setText(listShow.get(position).getOdf6fo()+"");
            odf6fo_update.setText(listShow.get(position).getOdf6fo()+"");
            rowodf6fo_update.setVisibility(View.VISIBLE);
        } else{
            rowodf6fo_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getOdf12fo()!=0){
            odf12_origin.setText(listShow.get(position).getOdf12fo()+"");
            odf12fo_update.setText(listShow.get(position).getOdf12fo()+"");
            rowodf12fo_update.setVisibility(View.VISIBLE);
        } else{
            rowodf12fo_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getOdf24fo()!=0){
            odf24_origin.setText(listShow.get(position).getOdf24fo()+"");
            odf24fo_update.setText(listShow.get(position).getOdf24fo()+"");
            rowodf24fo_update.setVisibility(View.VISIBLE);
        } else{
            rowodf24fo_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getOdf96fo()!=0){
            odf96_origin.setText(listShow.get(position).getOdf96fo()+"");
            odf96fo_update.setText(listShow.get(position).getOdf96fo()+"");
            rowodf96fo_update.setVisibility(View.VISIBLE);
        } else{
            rowodf24fo_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getClosure6fo()!=0){
            mx6_origin.setText(listShow.get(position).getClosure6fo()+"");
            mx6_update.setText(listShow.get(position).getClosure6fo()+"");
            rowmx6_update.setVisibility(View.VISIBLE);
        } else if(listShow.get(position).getHanging6fo()!=0){
            mx6_origin.setText("0");
            rowmx6_update.setVisibility(View.VISIBLE);
        }
        else{
            rowmx6_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getClosure12fo()!=0){
            mx12_origin.setText(listShow.get(position).getClosure12fo()+"");
            mx12_update.setText(listShow.get(position).getClosure12fo()+"");
            rowmx12_update.setVisibility(View.VISIBLE);
        } else if(listShow.get(position).getHanging12fo()!=0){
            mx12_origin.setText("0");
            rowmx12_update.setVisibility(View.VISIBLE);
        }
        else{
            rowmx12_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getClosure24fo()!=0){
            mx24_origin.setText(listShow.get(position).getClosure24fo()+"");
            mx24_update.setText(listShow.get(position).getClosure24fo()+"");
            rowmx24_update.setVisibility(View.VISIBLE);
        } else if(listShow.get(position).getHanging24fo()!=0){
            mx24_origin.setText("0");
            rowmx24_update.setVisibility(View.VISIBLE);
        }
        else{
            rowmx24_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getBuloong300()!=0){
            buloongti300_origin.setText(listShow.get(position).getBuloong300()+"");
            buloong300_update.setText(listShow.get(position).getBuloong300()+"");
            rowbuloongti300_update.setVisibility(View.VISIBLE);
        } else{
            rowbuloongti300_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getBuloong400()!=0){
            buloongti400_origin.setText(listShow.get(position).getBuloong400()+"");
            buloong400_update.setText(listShow.get(position).getBuloong400()+"");
            rowbuloongti400_update.setVisibility(View.VISIBLE);
        } else{
            rowbuloongti400_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getClamp()!=0){
            clamp_origin.setText(listShow.get(position).getClamp()+"");
            clamp_update.setText(listShow.get(position).getClamp()+"");
            rowclamp_update.setVisibility(View.VISIBLE);
        } else{
            rowclamp_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getPoleu8()!=0){
            poleu8_origin.setText(listShow.get(position).getPoleu8()+"");
            poleu8_update.setText(listShow.get(position).getPoleu8()+"");
            rowpoleu8_update.setVisibility(View.VISIBLE);
        } else{
            rowpoleu8_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getIronpole6()!=0){
            ironpole6_origin.setText(listShow.get(position).getIronpole6()+"");
            ironpole6_update.setText(listShow.get(position).getIronpole6()+"");
            rowironpole6_update.setVisibility(View.VISIBLE);
        } else{
            rowironpole6_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getSc_lc5()!=0){
            sc_lc5m_origin.setText(listShow.get(position).getSc_lc5()+"");
            sc_lc5_update.setText(listShow.get(position).getSc_lc5()+"");
            rowsc_lc5m_update.setVisibility(View.VISIBLE);
        } else{
            rowsc_lc5m_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getSc_lc10()!=0){
            sc_lc10m_origin.setText(listShow.get(position).getSc_lc10()+"");
            sc_lc10_update.setText(listShow.get(position).getSc_lc10()+"");
            rowsc_lc10m_update.setVisibility(View.VISIBLE);
        } else{
            rowsc_lc10m_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getSc_sc5()!=0){
            sc_sc5m_origin.setText(listShow.get(position).getSc_sc5()+"");
            sc_sc5_update.setText(listShow.get(position).getSc_sc5()+"");
            rowsc_sc5m_update.setVisibility(View.VISIBLE);
        } else{
            rowsc_sc5m_update.setVisibility(View.GONE);
        }
        //Access modifier account
        //BLOCK SET ENABLE BTN UPDATE
        if(listShow.get(position).getCr()==1) {
            if (listUser.get(0).getAdmin() == 1) {
                btnUpdate.setEnabled(true);
            } else if (listUser.get(0).getAdmin() == 2) {
                btnUpdate.setAlpha(.5f);
                btnUpdate.setEnabled(false);
            } else {
                if (listUser.get(0).getBte() == 1 || listUser.get(0).getLan() == 1 || listUser.get(0).getBdg() == 1 ||
                        listUser.get(0).getHcm_bch() == 1 || listUser.get(0).getDni() == 1 || listUser.get(0).getHcm_btn() == 1 ||
                        listUser.get(0).getHcm_cci() == 1 || listUser.get(0).getHcm_gvp() == 1 || listUser.get(0).getHcm_hmn() == 1 ||
                        listUser.get(0).getHcm_q12() == 1 || listUser.get(0).getKgg() == 1 || listUser.get(0).getTvh() == 1) {
                    btnUpdate.setEnabled(true);
                } else {
                    btnUpdate.setAlpha(.5f);
                    btnUpdate.setEnabled(false);
                }
            }
            //END SET ENABLE
        }else{
            btnUpdate.setAlpha(.5f);
            btnUpdate.setEnabled(false);
        }
        dialog.show();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showmore.setVisibility(View.GONE);
                dialogTitle_update.setText(listShow.get(position).getCableId());
                showUpdate.setVisibility(View.VISIBLE);
                String x="";
                for(int i=0;i<listCR.size();i++){
                    if(listCR.get(i).getId_origin()==listShow.get(position).getId()&&listCR.get(i).getStatuscr()==1){
                        x=listCR.get(i).getCommentcr();
                    }
                }
                commentEdit.setText(x);
            }
        });
        btnSaveUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int edt4fo, edt6fo, edt12fo, edt24fo, edt12du, edtodf6fo, edtodf12fo, edtodf24fo, edtodf96fo,
                        edtmx6, edtmx12, edtmx24, edtbl300, edtbl400, edtclamp,edtpoleu8, edtironpole6, edtsclc5, edtsclc10, edtscsc5;
                int add4fo, add6fo, add12fo, add24fo, adddu12, addodf6fo, addodf12fo, addodf24fo, addodf96fo, addclosure6fo, addclosure12fo,
                        addclosure24fo, addbuloong300, addbuloong400, addclamp, addpoleu8, addironpole6, addsc_lc5, addsc_lc10, addsc_sc5;
                int used4fo, used6fo, used12fo, used24fo, useddu12, usedodf6fo, usedodf12fo, usedodf24fo, usedodf96fo, usedclosure6fo, usedclosure12fo,
                        usedclosure24fo, usedbuloong300, usedbuloong400, usedclamp, usedpoleu8, usedironpole6, usedsc_lc5, usedsc_lc10, usedsc_sc5;
                String datechange, comment, userchange;
                if(listShow.get(position).getHanging4fo()!=0){
                    edt4fo = Integer.parseInt(cable4fo_update.getText().toString());
                } else{
                    edt4fo =0;
                }
                if(listShow.get(position).getHanging6fo()!=0){
                    edt6fo = Integer.parseInt(cable6fo_update.getText().toString());
                } else{
                    edt6fo=0;
                }
                if(listShow.get(position).getHanging12fo()!=0){
                    edt12fo = Integer.parseInt(cable12fo_update.getText().toString());
                } else{
                    edt12fo=0;
                }
                if(listShow.get(position).getHanging24fo()!=0){
                    edt24fo = Integer.parseInt(cable24fo_update.getText().toString());
                } else{
                    edt24fo=0;
                }
                if(listShow.get(position).getDu12fo()!=0){
                    edt12du = Integer.parseInt(cable12du_update.getText().toString());
                } else{
                    edt12du=0;
                }
                if(listShow.get(position).getOdf6fo()!=0){
                    edtodf6fo = Integer.parseInt(odf6fo_update.getText().toString());
                } else{
                    edtodf6fo=0;
                }
                if(listShow.get(position).getOdf12fo()!=0){
                    edtodf12fo = Integer.parseInt(odf12fo_update.getText().toString());
                } else{
                    edtodf12fo=0;
                }
                if(listShow.get(position).getOdf24fo()!=0){
                    edtodf24fo = Integer.parseInt(odf24fo_update.getText().toString());
                } else{
                    edtodf24fo=0;
                }
                if(listShow.get(position).getOdf96fo()!=0){
                    edtodf96fo = Integer.parseInt(odf96fo_update.getText().toString());
                } else{
                    edtodf96fo=0;
                }
                if(listShow.get(position).getClosure6fo()!=0){
                    edtmx6 = Integer.parseInt(mx6_update.getText().toString());
                } else{
                    edtmx6=0;
                }
                if(listShow.get(position).getClosure12fo()!=0){
                    edtmx12 = Integer.parseInt(mx12_update.getText().toString());
                } else{
                    edtmx12=0;
                }
                if(listShow.get(position).getClosure24fo()!=0){
                    edtmx24 = Integer.parseInt(mx24_update.getText().toString());
                } else{
                    edtmx24=0;
                }
                if(listShow.get(position).getBuloong300()!=0){
                    edtbl300 = Integer.parseInt(buloong300_update.getText().toString());
                } else{
                    edtbl300=0;
                }
                if(listShow.get(position).getBuloong400()!=0){
                    edtbl400 = Integer.parseInt(buloong400_update.getText().toString());
                } else{
                    edtbl400=0;
                }
                if(listShow.get(position).getClamp()!=0){
                    edtclamp = Integer.parseInt(clamp_update.getText().toString());
                } else{
                    edtclamp=0;
                }
                if(listShow.get(position).getPoleu8()!=0){
                    edtpoleu8 = Integer.parseInt(poleu8_update.getText().toString());
                } else{
                    edtpoleu8=0;
                }
                if(listShow.get(position).getIronpole6()!=0){
                    edtironpole6 = Integer.parseInt(ironpole6_update.getText().toString());
                } else{
                    edtironpole6=0;
                }
                if(listShow.get(position).getSc_lc5()!=0){
                    edtsclc5 = Integer.parseInt(sc_lc5_update.getText().toString());
                } else{
                    edtsclc5=0;
                }
                if(listShow.get(position).getSc_lc10()!=0){
                    edtsclc10 = Integer.parseInt(sc_lc10_update.getText().toString());
                } else{
                    edtsclc10=0;
                }
                if(listShow.get(position).getSc_sc5()!=0){
                    edtscsc5 = Integer.parseInt(sc_sc5_update.getText().toString());
                } else{
                    edtscsc5=0;
                }
                add4fo = listShow.get(position).getHanging4fo();
                add6fo = listShow.get(position).getHanging6fo();
                add12fo = listShow.get(position).getHanging12fo();
                add24fo = listShow.get(position).getHanging24fo();
                adddu12 = listShow.get(position).getDu12fo();
                addodf6fo = listShow.get(position).getOdf6fo();
                addodf12fo = listShow.get(position).getOdf12fo();
                addodf24fo = listShow.get(position).getOdf24fo();
                addodf96fo = listShow.get(position).getOdf96fo();
                addclosure6fo = listShow.get(position).getClosure6fo();
                addclosure12fo = listShow.get(position).getClosure12fo();
                addclosure24fo = listShow.get(position).getClosure24fo();
                addbuloong300 = listShow.get(position).getBuloong300();
                addbuloong400 = listShow.get(position).getBuloong400();
                addclamp = listShow.get(position).getClamp();
                addpoleu8 = listShow.get(position).getPoleu8();
                addironpole6 = listShow.get(position).getIronpole6();
                addsc_lc5 = listShow.get(position).getSc_lc5();
                addsc_lc10 = listShow.get(position).getSc_lc10();
                addsc_sc5 = listShow.get(position).getSc_sc5();
                comment = commentEdit.getText().toString();
                //DATE CHANGE
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
                datechange = sdf.format(new Date());
                userchange = listUser.get(0).getUser().toString();

                //USED MATERIALS
                if(TextUtils.isEmpty(cable4fo_used.getText().toString())){
                    used4fo =0;
                } else{
                    used4fo = Integer.parseInt(cable4fo_used.getText().toString()) ;
                }

                if(TextUtils.isEmpty(cable6fo_used.getText().toString())){
                    used6fo =0;
                } else{
                    used6fo = Integer.parseInt(cable6fo_used.getText().toString());
                }

                if(TextUtils.isEmpty(cable12fo_used.getText().toString())){
                    used12fo =0;
                } else{
                    used12fo = Integer.parseInt(cable12fo_used.getText().toString());
                }

                if(TextUtils.isEmpty(cable24fo_used.getText().toString())){
                    used24fo =0;
                } else{
                    used24fo = Integer.parseInt(cable24fo_used.getText().toString());
                }

                if(TextUtils.isEmpty(cable12du_used.getText().toString())){
                    useddu12 =0;
                } else{
                    useddu12 = Integer.parseInt(cable12du_used.getText().toString());
                }

                if(TextUtils.isEmpty(odf6fo_used.getText().toString())){
                    usedodf6fo =0;
                } else{
                    usedodf6fo = Integer.parseInt(odf6fo_used.getText().toString());
                }

                if(TextUtils.isEmpty(odf12fo_used.getText().toString())){
                    usedodf12fo =0;
                } else{
                    usedodf12fo = Integer.parseInt(odf12fo_used.getText().toString());
                }

                if(TextUtils.isEmpty(odf24fo_used.getText().toString())){
                    usedodf24fo =0;
                } else{
                    usedodf24fo = Integer.parseInt(odf24fo_used.getText().toString());
                }

                if(TextUtils.isEmpty(odf96fo_used.getText().toString())){
                    usedodf96fo =0;
                } else{
                    usedodf96fo = Integer.parseInt(odf96fo_used.getText().toString());
                }

                if(TextUtils.isEmpty(mx6_used.getText().toString())){
                    usedclosure6fo =0;
                } else{
                    usedclosure6fo = Integer.parseInt(mx6_used.getText().toString());
                }

                if(TextUtils.isEmpty(mx12_used.getText().toString())){
                    usedclosure12fo =0;
                } else{
                    usedclosure12fo = Integer.parseInt(mx12_used.getText().toString());
                }

                if(TextUtils.isEmpty(mx24_used.getText().toString())){
                    usedclosure24fo =0;
                } else{
                    usedclosure24fo = Integer.parseInt(mx24_used.getText().toString());
                }

                if(TextUtils.isEmpty(buloong300_used.getText().toString())){
                    usedbuloong300 =0;
                } else{
                    usedbuloong300 = Integer.parseInt(buloong300_used.getText().toString());
                }

                if(TextUtils.isEmpty(buloong400_used.getText().toString())){
                    usedbuloong400 =0;
                } else{
                    usedbuloong400 = Integer.parseInt(buloong400_used.getText().toString());
                }

                if(TextUtils.isEmpty(clamp_used.getText().toString())){
                    usedclamp =0;
                } else{
                    usedclamp = Integer.parseInt(clamp_used.getText().toString());
                }

                if(TextUtils.isEmpty(poleu8_used.getText().toString())){
                    usedpoleu8 =0;
                } else{
                    usedpoleu8 = Integer.parseInt(poleu8_used.getText().toString());
                }

                if(TextUtils.isEmpty(ironpole6_used.getText().toString())){
                    usedironpole6 =0;
                } else{
                    usedironpole6 = Integer.parseInt(ironpole6_used.getText().toString());
                }


                if(TextUtils.isEmpty(sc_lc5_used.getText().toString())){
                    usedsc_lc5 =0;
                } else{
                    usedsc_lc5 = Integer.parseInt(sc_lc5_used.getText().toString());
                }
                if(TextUtils.isEmpty(sc_lc10_used.getText().toString())){
                    usedsc_lc10 =0;
                } else{
                    usedsc_lc10 = Integer.parseInt(sc_lc10_used.getText().toString());
                }

                if(TextUtils.isEmpty(sc_sc5_used.getText().toString())){
                    usedsc_sc5 =0;
                } else{
                    usedsc_sc5 = Integer.parseInt(sc_sc5_used.getText().toString());
                }
                int idCR=0;
                for(int i=0; i<listCR.size();i++){
                    if(listCR.get(i).getId_origin()==listShow.get(position).getId()&&listCR.get(i).getStatuscr()==1){
                        idCR=listCR.get(i).getId();
                    }
                }
                String codecr = "";
                for(int i=0; i<listCR.size();i++){
                    if(listCR.get(i).getId()==idCR){
                        codecr = listCR.get(i).getCodecr().toString();
                    }
                }
                InsertHistory("https://sqlandroid2812.000webhostapp.com/inserthistory.php",position,add4fo,add6fo,
                        add12fo,add24fo,adddu12,addodf6fo,addodf12fo,addodf24fo,addodf96fo,addclosure6fo,addclosure12fo,
                        addclosure24fo,addbuloong300,addbuloong400,addclamp,addpoleu8, addironpole6,addsc_lc5, addsc_lc10, addsc_sc5,
                        datechange,comment,userchange
                        );
                InsertUsedHistory("https://sqlandroid2812.000webhostapp.com/insertused.php",position,codecr,used4fo,used6fo,
                        used12fo,used24fo,useddu12,usedodf6fo,usedodf12fo,usedodf24fo,usedodf96fo,usedclosure6fo,usedclosure12fo,
                        usedclosure24fo,usedbuloong300,usedbuloong400,usedclamp,usedpoleu8, usedironpole6,usedsc_lc5, usedsc_lc10, usedsc_sc5,
                        datechange,comment,userchange
                        );
                UpdateSQL("https://sqlandroid2812.000webhostapp.com/update.php", position,
                        edt4fo,edt6fo,edt12fo,edt24fo,edt12du,edtodf6fo,edtodf12fo,edtodf24fo,
                        edtodf96fo,edtmx6,edtmx12,edtmx24,
                        edtbl300,edtbl400,edtclamp,edtpoleu8,edtironpole6,edtsclc5,
                        edtsclc10, edtscsc5,0
                       );

                UpdateNOC("https://sqlandroid2812.000webhostapp.com/updatenoc.php",idCR,2);
                int idInventory =0;
                if(listShow.get(position).getProvince().equals("Bình Dương")){
                    idInventory=4;
                }else if(listShow.get(position).getProvince().equals("Bến Tre")){
                    idInventory=1;
                }else if(listShow.get(position).getProvince().equals("Trà Vinh")){
                    idInventory=3;
                }else if(listShow.get(position).getProvince().equals("Kiên Giang")){
                    idInventory=5;
                }else {
                    idInventory=2;
                }
                UpdateInventory("https://sqlandroid2812.000webhostapp.com/updateinventory.php",idInventory,used6fo,used12fo,used24fo,
                usedodf6fo,usedodf12fo,usedodf24fo,usedclosure6fo,usedclosure12fo,usedclosure24fo,usedbuloong300,usedbuloong400,usedclamp,
                        usedsc_lc5,usedsc_lc10);

                adapter.notifyDataSetChanged();
                Intent intent = new Intent(Management.this, DashBoard.class);
                intent.putExtra("Account", listUser);
                startActivity(intent);
            }
        });
        btnCancelUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUpdate.setVisibility(View.GONE);
                showmore.setVisibility(View.VISIBLE);
            }
        });
    }

    private void UpdateSQL(String url, final int position, final int hanging4fo, final int hanging6fo,
                           final int hanging12fo , final int hanging24fo, final int du12fo, final int odf6fo, final int odf12fo,
                           final int odf24fo, final int odf96fo, final int closure6fo, final int closure12fo, final int closure24fo,
                           final int buloong300, final int buloong400, final int clamp, final int poleu8, final int ironpole6,
                           final int sc_lc5, final int sc_lc10, final int sc_sc5, final int statuscr
                           ){
         final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Management.this, "Lỗi UpdateSQL", Toast.LENGTH_SHORT).show();
            }
        }
        )
        {
            @Override
            protected Map <String, String> getParams() throws AuthFailureError{
               Map <String, String> params = new HashMap<>();
               params.put("ID",String.valueOf(listShow.get(position).getId()));
               if (hanging4fo > 0){
                   params.put("Hanging4fo", String.valueOf(hanging4fo));
               }else{
                   params.put("Hanging4fo", String.valueOf(0));
               }
               if(hanging6fo > 0){
                   params.put("Hanging6fo", String.valueOf(hanging6fo));
               }else{
                   params.put("Hanging6fo", String.valueOf(0));
               }
                if(hanging12fo > 0){
                    params.put("Hanging12fo", String.valueOf(hanging12fo));
                }else{
                    params.put("Hanging12fo", String.valueOf(0));
                }
                if(hanging24fo > 0){
                    params.put("Hanging24fo", String.valueOf(hanging24fo));
                }else{
                    params.put("Hanging24fo", String.valueOf(0));
                }
                if(du12fo > 0){
                    params.put("Du12fo", String.valueOf(du12fo));
                }else{
                    params.put("Du12fo", String.valueOf(0));
                }
                if(odf6fo > 0){
                    params.put("Odf6fo", String.valueOf(odf6fo));
                }else{
                    params.put("Odf6fo", String.valueOf(0));
                }
                if(odf12fo > 0){
                    params.put("Odf12fo", String.valueOf(odf12fo));
                }else{
                    params.put("Odf12fo", String.valueOf(0));
                }
                if(odf24fo > 0){
                    params.put("Odf24fo", String.valueOf(odf24fo));
                }else{
                    params.put("Odf24fo", String.valueOf(0));
                }
                if(odf96fo > 0){
                    params.put("Odf96fo", String.valueOf(odf96fo));
                }else{
                    params.put("Odf96fo", String.valueOf(0));
                }
                if(closure6fo > 0){
                    params.put("Closure6fo", String.valueOf(closure6fo));
                }else{
                    params.put("Closure6fo", String.valueOf(0));
                }
                if(closure12fo > 0){
                    params.put("Closure12fo", String.valueOf(closure12fo));
                }else{
                    params.put("Closure12fo", String.valueOf(0));
                }
                if(closure24fo > 0){
                    params.put("Closure24fo", String.valueOf(closure24fo));
                }else{
                    params.put("Closure24fo", String.valueOf(0));
                }
                if(buloong300 > 0){
                    params.put("Buloong300", String.valueOf(buloong300));
                }else{
                    params.put("Buloong300", String.valueOf(0));
                }
                if(buloong400 > 0){
                    params.put("Buloong400", String.valueOf(buloong400));
                }else{
                    params.put("Buloong400", String.valueOf(0));
                }
                if(clamp > 0){
                    params.put("Clamp", String.valueOf(clamp));
                }else{
                    params.put("Clamp", String.valueOf(0));
                }
                if(poleu8 > 0){
                    params.put("Poleu8", String.valueOf(poleu8));
                }else{
                    params.put("Poleu8", String.valueOf(0));
                }
                if(ironpole6 > 0){
                    params.put("Ironpole6", String.valueOf(ironpole6));
                }else{
                    params.put("Ironpole6", String.valueOf(0));
                }
                if(sc_lc5 > 0){
                    params.put("Sc_lc5", String.valueOf(sc_lc5));
                }else{
                    params.put("Sc_lc5", String.valueOf(0));
                }
                if(sc_lc10 > 0){
                    params.put("Sc_lc10", String.valueOf(sc_lc10));
                }else{
                    params.put("Sc_lc10", String.valueOf(0));
                }
                if(sc_sc5 >0){
                    params.put("Sc_sc5", String.valueOf(sc_sc5));
                }else{
                    params.put("Sc_sc5", String.valueOf(0));
                }
                    params.put("Statuscr",String.valueOf(statuscr));
               return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void UpdateCRSQL(String url, final int id, final int cr
    ){
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Management.this, "Lỗi Update SQL CR", Toast.LENGTH_SHORT).show();
            }
        }
        )
        {
            @Override
            protected Map <String, String> getParams() throws AuthFailureError{
                Map <String, String> params = new HashMap<>();
                params.put("ID",String.valueOf(id));
                params.put("Cr",String.valueOf(cr));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void UpdateNOC(String url, final int idcr, final int statuscr
    ){
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Management.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        )
        {
            @Override
            protected Map <String, String> getParams() throws AuthFailureError{
                Map <String, String> params = new HashMap<>();
                params.put("Idcr",String.valueOf(idcr));
                params.put("Statuscr",String.valueOf(statuscr));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void InsertHistory(String url, final int position, final int add4fo, final int add6fo,
                               final int add12fo , final int add24fo, final int adddu12, final int addodf6fo, final int addodf12fo,
                               final int addodf24fo, final int addodf96fo, final int addclosure6fo, final int addclosure12fo, final int addclosure24fo,
                               final int addbuloong300, final int addbuloong400, final int addclamp, final int addpoleu8, final int addironpole6,
                               final int addsc_lc5, final int addsc_lc10, final int addsc_sc5, final String datechange, final String comment,
                               final String userchange
                               ){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Management.this, "Insert history", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("Addoldid",String.valueOf(listShow.get(position).getId()));
                params.put("Addcableid",String.valueOf(listShow.get(position).getCableId()));
                params.put("Addprovince",String.valueOf(listShow.get(position).getProvince()));
                if (add4fo > 0){
                    params.put("Addhanging4fo", String.valueOf(add4fo));
                }else{
                    params.put("Addhanging4fo", String.valueOf(0));
                }

                if(add6fo > 0){
                    params.put("Addhanging6fo", String.valueOf(add6fo));
                }else{
                    params.put("Addhanging6fo", String.valueOf(0));
                }

                if(add12fo > 0){
                    params.put("Addhanging12fo", String.valueOf(add12fo));
                }else{
                    params.put("Addhanging12fo", String.valueOf(0));
                }

                if(add24fo > 0){
                    params.put("Addhanging24fo", String.valueOf(add24fo));
                }else{
                    params.put("Addhanging24fo", String.valueOf(0));
                }
                if(adddu12 > 0){
                    params.put("Adddu12fo", String.valueOf(adddu12));
                }else{
                    params.put("Adddu12fo", String.valueOf(0));
                }

                if(addodf6fo > 0){
                    params.put("Addodf6fo", String.valueOf(addodf6fo));
                }else{
                    params.put("Addodf6fo", String.valueOf(0));
                }

                if(addodf12fo > 0){
                    params.put("Addodf12fo", String.valueOf(addodf12fo));
                }else{
                    params.put("Addodf12fo", String.valueOf(0));
                }

                if(addodf24fo > 0){
                    params.put("Addodf24fo", String.valueOf(addodf24fo));
                }else{
                    params.put("Addodf24fo", String.valueOf(0));
                }

                if(addodf96fo > 0){
                    params.put("Addodf96fo", String.valueOf(addodf96fo));
                }else{
                    params.put("Addodf96fo", String.valueOf(0));
                }

                if(addclosure6fo > 0){
                    params.put("Addclosure6fo", String.valueOf(addclosure6fo));
                }else{
                    params.put("Addclosure6fo", String.valueOf(0));
                }

                if(addclosure12fo > 0){
                    params.put("Addclosure12fo", String.valueOf(addclosure12fo));
                }else{
                    params.put("Addclosure12fo", String.valueOf(0));
                }

                if(addclosure24fo > 0){
                    params.put("Addclosure24fo", String.valueOf(addclosure24fo));
                }else{
                    params.put("Addclosure24fo", String.valueOf(0));
                }

                if(addbuloong300 > 0){
                    params.put("Addbuloongti300", String.valueOf(addbuloong300));
                }else{
                    params.put("Addbuloongti300", String.valueOf(0));
                }

                if(addbuloong400 > 0){
                    params.put("Addbuloongti400", String.valueOf(addbuloong400));
                }else{
                    params.put("Addbuloongti400", String.valueOf(0));
                }

                if(addclamp > 0){
                    params.put("Addclamp", String.valueOf(addclamp));
                }else{
                    params.put("Addclamp", String.valueOf(0));
                }

                if(addpoleu8 > 0){
                    params.put("Addpoleu8", String.valueOf(addpoleu8));
                }else{
                    params.put("Addpoleu8", String.valueOf(0));
                }

                if(addironpole6 > 0){
                    params.put("Addironpole6", String.valueOf(addironpole6));
                }else{
                    params.put("Addironpole6", String.valueOf(0));
                }

                if(addsc_lc5 > 0){
                    params.put("Addsc_lc5", String.valueOf(addsc_lc5));
                }else{
                    params.put("Addsc_lc5", String.valueOf(0));
                }

                if(addsc_lc10 > 0){
                    params.put("Addsc_lc10", String.valueOf(addsc_lc10));
                }else{
                    params.put("Addsc_lc10", String.valueOf(0));
                }

                if(addsc_sc5 >0){
                    params.put("Addsc_sc5", String.valueOf(addsc_sc5));
                }else{
                    params.put("Addsc_sc5", String.valueOf(0));
                }
                params.put("Adddatechange",datechange);
                params.put("Addcomment",comment);
                params.put("Adduserchange",userchange);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void InsertUsedHistory(String url, final int position,final String codecr, final int used4fo, final int used6fo,
                               final int used12fo , final int used24fo, final int useddu12, final int usedodf6fo, final int usedodf12fo,
                               final int usedodf24fo, final int usedodf96fo, final int usedclosure6fo, final int usedclosure12fo, final int usedclosure24fo,
                               final int usedbuloong300, final int usedbuloong400, final int usedclamp, final int usedpoleu8, final int usedironpole6,
                               final int usedsc_lc5, final int usedsc_lc10, final int usedsc_sc5, final String datechange, final String comment,
                               final String userchange
    ){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Management.this, "Insert used", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Management.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("Addoldid",String.valueOf(listShow.get(position).getId()));
                params.put("Addcableid",String.valueOf(listShow.get(position).getCableId()));
                params.put("Addprovince",String.valueOf(listShow.get(position).getProvince()));
                params.put("Addcodecr",codecr);
                if (used4fo > 0){
                    params.put("Usedhanging4fo", String.valueOf(used4fo));
                }else{
                    params.put("Usedhanging4fo", String.valueOf(0));
                }

                if(used6fo > 0){
                    params.put("Usedhanging6fo", String.valueOf(used6fo));
                }else{
                    params.put("Usedhanging6fo", String.valueOf(0));
                }

                if(used12fo > 0){
                    params.put("Usedhanging12fo", String.valueOf(used12fo));
                }else{
                    params.put("Usedhanging12fo", String.valueOf(0));
                }

                if(used24fo > 0){
                    params.put("Usedhanging24fo", String.valueOf(used24fo));
                }else{
                    params.put("Usedhanging24fo", String.valueOf(0));
                }
                if(useddu12 > 0){
                    params.put("Useddu12fo", String.valueOf(useddu12));
                }else{
                    params.put("Useddu12fo", String.valueOf(0));
                }

                if(usedodf6fo > 0){
                    params.put("Usedodf6fo", String.valueOf(usedodf6fo));
                }else{
                    params.put("Usedodf6fo", String.valueOf(0));
                }

                if(usedodf12fo > 0){
                    params.put("Usedodf12fo", String.valueOf(usedodf12fo));
                }else{
                    params.put("Usedodf12fo", String.valueOf(0));
                }

                if(usedodf24fo > 0){
                    params.put("Usedodf24fo", String.valueOf(usedodf24fo));
                }else{
                    params.put("Usedodf24fo", String.valueOf(0));
                }

                if(usedodf96fo > 0){
                    params.put("Usedodf96fo", String.valueOf(usedodf96fo));
                }else{
                    params.put("Usedodf96fo", String.valueOf(0));
                }

                if(usedclosure6fo > 0){
                    params.put("Usedclosure6fo", String.valueOf(usedclosure6fo));
                }else{
                    params.put("Usedclosure6fo", String.valueOf(0));
                }

                if(usedclosure12fo > 0){
                    params.put("Usedclosure12fo", String.valueOf(usedclosure12fo));
                }else{
                    params.put("Usedclosure12fo", String.valueOf(0));
                }

                if(usedclosure24fo > 0){
                    params.put("Usedclosure24fo", String.valueOf(usedclosure24fo));
                }else{
                    params.put("Usedclosure24fo", String.valueOf(0));
                }

                if(usedbuloong300 > 0){
                    params.put("Usedbuloongti300", String.valueOf(usedbuloong300));
                }else{
                    params.put("Usedbuloongti300", String.valueOf(0));
                }

                if(usedbuloong400 > 0){
                    params.put("Usedbuloongti400", String.valueOf(usedbuloong400));
                }else{
                    params.put("Usedbuloongti400", String.valueOf(0));
                }

                if(usedclamp > 0){
                    params.put("Usedclamp", String.valueOf(usedclamp));
                }else{
                    params.put("Usedclamp", String.valueOf(0));
                }

                if(usedpoleu8 > 0){
                    params.put("Usedpoleu8", String.valueOf(usedpoleu8));
                }else{
                    params.put("Usedpoleu8", String.valueOf(0));
                }

                if(usedironpole6 > 0){
                    params.put("Usedironpole6", String.valueOf(usedironpole6));
                }else{
                    params.put("Usedironpole6", String.valueOf(0));
                }

                if(usedsc_lc5 > 0){
                    params.put("Usedsc_lc5", String.valueOf(usedsc_lc5));
                }else{
                    params.put("Usedsc_lc5", String.valueOf(0));
                }

                if(usedsc_lc10 > 0){
                    params.put("Usedsc_lc10", String.valueOf(usedsc_lc10));
                }else{
                    params.put("Usedsc_lc10", String.valueOf(0));
                }

                if(usedsc_sc5 >0){
                    params.put("Usedsc_sc5", String.valueOf(usedsc_sc5));
                }else{
                    params.put("Usedsc_sc5", String.valueOf(0));
                }
                params.put("Adddatechange",datechange);
                params.put("Addcomment",comment);
                params.put("Adduserchange",userchange);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    /*=============================== UPDATE INVENTORY ===================================*/
    private void UpdateInventory(String url, final int id, final int hanging6fo,
                           final int hanging12fo , final int hanging24fo, final int odf6fo, final int odf12fo,
                           final int odf24fo, final int closure6fo, final int closure12fo, final int closure24fo,
                           final int buloong300, final int buloong400, final int clamp, final int sc_lc5, final int sc_lc10) {

        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Management.this, "Update Inventory", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Management.this, "Lỗi update Kho "+error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String, String> params = new HashMap<>();
                //Get id inventory
                    params.put("updateID",String.valueOf(id));
                    params.put("updateHanging6fo", String.valueOf(listInventory.get(id-1).getHanging6fo()-hanging6fo));
                    params.put("updateHanging12fo", String.valueOf(listInventory.get(id-1).getHanging12fo()-hanging12fo));
                    params.put("updateHanging24fo", String.valueOf(listInventory.get(id-1).getHanging24fo()-hanging24fo));
                    params.put("updateOdf6fo", String.valueOf(listInventory.get(id-1).getOdf6fo()-odf6fo));
                    params.put("updateOdf12fo", String.valueOf(listInventory.get(id-1).getOdf12fo()-odf12fo));
                    params.put("updateOdf24fo", String.valueOf(listInventory.get(id-1).getOdf24fo()-odf24fo));
                    params.put("updateClosure6fo", String.valueOf(listInventory.get(id-1).getClosure6fo()-closure6fo));
                    params.put("updateClosure12fo", String.valueOf(listInventory.get(id-1).getClosure12fo()-closure12fo));
                    params.put("updateClosure24fo", String.valueOf(listInventory.get(id-1).getClosure24fo()-closure24fo));
                    params.put("updateBuloong300", String.valueOf(listInventory.get(id-1).getBl300()-buloong300));
                    params.put("updateBuloong400", String.valueOf(listInventory.get(id-1).getBl400()-buloong400));
                    params.put("updateClamp", String.valueOf(listInventory.get(id-1).getClamp()-clamp));
                    params.put("updateSc_lc5", String.valueOf(listInventory.get(id-1).getSc_lc5()-sc_lc5));
                    params.put("updateSc_lc10", String.valueOf(listInventory.get(id-1).getSc_lc10()-sc_lc10));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void getInventory(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0; i<response.length(); i++){
                    try{
                        JSONObject jsonObject = response.getJSONObject(i);
                        listInventory.add(new MaterialsInventory(
                                jsonObject.getInt("ID"),
                                jsonObject.getString("Storecode"),
                                jsonObject.getInt("Hanging6fo"),
                                jsonObject.getInt("Hanging12fo"),
                                jsonObject.getInt("Hanging24fo"),
                                jsonObject.getInt("Odf6fo"),
                                jsonObject.getInt("Odf12fo"),
                                jsonObject.getInt("Odf24fo"),
                                jsonObject.getInt("Closure6fo"),
                                jsonObject.getInt("Closure12fo"),
                                jsonObject.getInt("Closure24fo"),
                                jsonObject.getInt("Buloongti300"),
                                jsonObject.getInt("Buloongti400"),
                                jsonObject.getInt("Clamp"),
                                jsonObject.getInt("Sc_lc5"),
                                jsonObject.getInt("Sc_lc10")
                        ));

                    }catch (Exception e){
                        Toast.makeText(Management.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Management.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    /*============================ GET CR NOC=================================*/
    public void getCR(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0; i<response.length(); i++){
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

                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Management.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(jsonArrayRequest);
    }
    private void CheckNoc(int id_origin, String local, String cableidcr, String user){
        Toast.makeText(this, ""+listCable.get(id_origin-1).getId(), Toast.LENGTH_SHORT).show();
        if(listUser.get(0).getNoc()==1 && listCable.get(id_origin-1).getCr() !=1){
            DialogCR(id_origin, local, cableidcr,user);
        } else if (listCable.get(id_origin-1).getCr() ==1){
            Toast.makeText(this, "Mã tuyến này đang có CR chưa hoàn thành!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Bạn không có quyền sử dụng chức năng này!", Toast.LENGTH_SHORT).show();
        }
    }
    private void InsertNOC(String url,final int id_origin, final String local, final String cableid, final String codecr, final String commentcr, final String datetimecr, final String usercreatecr){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Management.this, "Lỗi Insert NOC + "+error, Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map <String, String> getParams() throws AuthFailureError {
            Map <String, String> params = new HashMap<>();
                params.put("Id_origin",String.valueOf(id_origin));
                params.put("Local",local);
                params.put("Cableidcr",cableid);
                params.put("Codecr",codecr);
                params.put("Commentcr",commentcr);
                params.put("Datetimecr",datetimecr);
                params.put("Usercreatecr",usercreatecr);
            return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void DialogCR(final int id_origin, final String local, final String cableid, final String usercreatecr){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.create_cr);
        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.97);
        dialog.getWindow().setLayout(width, LinearLayout.LayoutParams.WRAP_CONTENT);
        final TextView codeCR = (TextView)dialog.findViewById(R.id.codeCR);
        TextView cableidCR = (TextView)dialog.findViewById(R.id.cableidCR);
        TextView dateCR = (TextView)dialog.findViewById(R.id.dateCR);
        final EditText commentCR = (EditText)dialog.findViewById(R.id.commentCR);
        Button btnCreateCR = (Button)dialog.findViewById(R.id.btnCreateCR);
        Button btnCancelCR = (Button)dialog.findViewById(R.id.btnCancelCR);
        cableidCR.setText("Mã tuyến cáp: "+cableid);
        codeCR.setText("CR_PVHKT_NOC_");
        commentCR.setText("["+listCable.get(id_origin).getProvince().toUpperCase()+
                "] liên hệ kho Công ty nhận ... UCTT tuyến "+cableid);
        btnCancelCR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        btnCreateCR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(codeCR.getText().toString().trim().length()<17||
                commentCR.getText().toString().length()<60){
                    Toast.makeText(Management.this, "Vui lòng nhập đầy đủ thông tin mã CR hoặc nội dung CR", Toast.LENGTH_SHORT).show();
                }
                else{
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
                    String dateCreateCR = sdf.format(new Date());
                    InsertNOC("https://sqlandroid2812.000webhostapp.com/insertnoc.php",id_origin,local, cableid,
                            codeCR.getText().toString().trim(),
                            commentCR.getText().toString().trim(),dateCreateCR,usercreatecr);
                    UpdateCRSQL("https://sqlandroid2812.000webhostapp.com/updatecrdata.php",id_origin,1);
                    dialog.cancel();
                }
            }
        });
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Management.this, DashBoard.class);
        intent.putExtra("Account", listUser);
        startActivity(intent);
    }
}
//https://sqlandroid2812.000webhostapp.com/updatenoc.php