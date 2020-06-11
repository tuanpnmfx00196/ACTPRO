package com.example.actproperty;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.actproperty.itemclick.OnItemClickRecyclerView;
import com.example.actproperty.passport.Passport;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/* pass database cable --->>> |aSGl2|vqrg/EfWh */
public class Management extends AppCompatActivity implements OnItemClickRecyclerView {
    Button btnSearch;
    EditText searchLocal, searchCableId;
    ArrayList<Passport>listUser;
    ArrayList<CableId> listCable;
    ArrayList<CableId> list;
    ArrayList<CableId> listSearch;
    ArrayList<CableId> listShow;
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
        GetAccount();
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
                ReadJsonSeach("https://sqlandroid2812.000webhostapp.com/getdata.php");
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
                    frameContain.setVisibility(View.INVISIBLE);
                }
            }
        });
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
                                jsonObject.getDouble("Hanging12fo"),
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
                                jsonObject.getInt("Sc_sc5")

                        ));
                    }catch(Exception e){
                        Toast.makeText(Management.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                Permission();
                    adapter.notifyDataSetChanged();
                    initView();
            }
        },
                new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                 Toast.makeText(Management.this, "Connection error", Toast.LENGTH_SHORT).show();
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
                                        jsonObject.getDouble("Hanging12fo"),
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
                                        jsonObject.getInt("Sc_sc5")
                                ));
                            }catch(Exception e){
                                Toast.makeText(Management.this, e.toString(), Toast.LENGTH_SHORT).show();

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
                        Toast.makeText(Management.this, "Connection error", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
    /*=========================== INIT RecyclerView Manager ==========================*/
    public void initView(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
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
    public void Permission(){
        if(listUser.get(0).getAdmin()==1){
            for(int i=0;i<listCable.size();i++){
                listShow.add(listCable.get(i));
            }
        }
        if(listUser.get(0).getHcm_bch()==1){
            for(int i=0;i<listCable.size();i++){
                if(listCable.get(i).getProvince().equals("HCM_Bình Chánh")){
                    listShow.add(listCable.get(i));
                }
            }
        }
        if(listUser.get(0).getBdg()==1){
            for(int i=0;i<listCable.size();i++){
                if(listCable.get(i).getProvince().equals("Bình Dương")){
                    listShow.add(listCable.get(i));
                }
            }
        }
        if(listUser.get(0).getKgg()==1|| listUser.get(0).getKgg()==2){
            for(int i=0;i<listCable.size();i++){
                if(listCable.get(i).getProvince().equals("Kiên Giang")){
                    listShow.add(listCable.get(i));
                }
            }
        }
    }
    private void GetSearch(ArrayList<CableId> arrayList){
        listShow.clear();
        if(listUser.get(0).getAdmin()==1){
            for(int i=0;i<arrayList.size();i++){
                listShow.add(arrayList.get(i));
            }
        }
        if(listUser.get(0).getHcm_bch()==1){
            for(int i=0;i<arrayList.size();i++){
                if(arrayList.get(i).getProvince().equals("HCM_Bình Chánh")){
                    listShow.add(arrayList.get(i));
                }
            }
        }
        if(listUser.get(0).getBdg()==1){
            for(int i=0;i<arrayList.size();i++){
                if(arrayList.get(i).getProvince().equals("Bình Dương")){
                    listShow.add(arrayList.get(i));
                }
            }
        }
        if(listUser.get(0).getKgg()==1|| listUser.get(0).getKgg()==2){
            for(int i=0;i<arrayList.size();i++){
                if(arrayList.get(i).getProvince().equals("Kiên Giang")){
                    listShow.add(arrayList.get(i));
                }
            }
        }
    }

    @Override
    public void onClick(int position) {
        ShowMoreDetail(position);
    }
    private void ShowMoreDetail(int position){
        final Dialog dialog = new Dialog(this);
        dialog.setTitle(listShow.get(position).getCableId());
        dialog.setContentView(R.layout.show_more);
        final TextView cable4fo, cable6fo, cable12fo, cable24fo, cable12du, odf6, odf12, odf24, odf96,
                mx6, mx12, mx24, buloongti300, buloongti400, clamp, sc_lc5m, sc_lc10m;
        LinearLayout row4fo, row6fo, row12fo, row24fo, rowdu12fo, rowodf6fo, rowodf12fo, rowodf24fo, rowodf96fo,
                rowmx6, rowmx12, rowmx24,rowbuloongti300, rowbuloongti400, rowpoleu8, rowironpole6, rowclamp,
                rowsc_lc5m, rowsc_lc10m,rowsc_sc5m;
        cable4fo = (TextView)dialog.findViewById(R.id.cable4fo);
        cable6fo = (TextView)dialog.findViewById(R.id.cable6fo);
        cable12fo = (TextView)dialog.findViewById(R.id.cable12fo);
        cable24fo = (TextView)dialog.findViewById(R.id.cable24fo);
        cable12du = (TextView)dialog.findViewById(R.id.cable12du);
        odf6 = (TextView)dialog.findViewById(R.id.odf6);
        odf12 = (TextView)dialog.findViewById(R.id.odf12);
        odf24 = (TextView)dialog.findViewById(R.id.odf24);
        odf96 = (TextView)dialog.findViewById(R.id.odf96);
        mx6 = (TextView)dialog.findViewById(R.id.mx6);
        mx12 = (TextView)dialog.findViewById(R.id.mx12);
        mx24 = (TextView)dialog.findViewById(R.id.mx24);
        buloongti300 = (TextView)dialog.findViewById(R.id.buloongti300);
        buloongti400 = (TextView)dialog.findViewById(R.id.buloongti400);
        clamp = (TextView)dialog.findViewById(R.id.clamp);
        sc_lc5m = (TextView)dialog.findViewById(R.id.sc_lc5m);
        sc_lc10m = (TextView)dialog.findViewById(R.id.sc_lc10m);
        final Button btnUpdate = (Button)dialog.findViewById(R.id.btnUpdate);
        final Button btnBack = (Button)dialog.findViewById(R.id.btnBack);

        //ROW INVISIBLE
        row4fo = (LinearLayout)dialog.findViewById(R.id.row4fo);
        row6fo = (LinearLayout)dialog.findViewById(R.id.row6fo);
        row12fo = (LinearLayout)dialog.findViewById(R.id.row12fo);
        row24fo = (LinearLayout)dialog.findViewById(R.id.row24fo);
        rowdu12fo = (LinearLayout)dialog.findViewById(R.id.rowdu12fo);
        rowodf6fo = (LinearLayout)dialog.findViewById(R.id.rowodf6fo);
        rowodf12fo = (LinearLayout)dialog.findViewById(R.id.rowodf12fo);
        rowodf24fo = (LinearLayout)dialog.findViewById(R.id.rowodf24fo);
        rowodf96fo = (LinearLayout)dialog.findViewById(R.id.rowodf96fo);
        rowmx6 = (LinearLayout)dialog.findViewById(R.id.rowmx6);
        rowmx12 = (LinearLayout)dialog.findViewById(R.id.rowmx12);
        rowmx24 = (LinearLayout)dialog.findViewById(R.id.rowmx24);
        rowbuloongti300 = (LinearLayout)dialog.findViewById(R.id.rowbuloongti300);
        rowbuloongti400 = (LinearLayout)dialog.findViewById(R.id.rowbuloongti400);
        rowpoleu8 = (LinearLayout)dialog.findViewById(R.id.rowpoleu8);
        rowironpole6 = (LinearLayout)dialog.findViewById(R.id.rowironpole6);
        rowclamp = (LinearLayout)dialog.findViewById(R.id.rowclamp);
        rowsc_lc5m = (LinearLayout)dialog.findViewById(R.id.rowsc_lc5m);
        rowsc_lc10m = (LinearLayout)dialog.findViewById(R.id.rowsc_lc10m);
        rowsc_sc5m = (LinearLayout)dialog.findViewById(R.id.rowsc_sc5m);

        /*======================Get Data Dialog======================*/
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
        if(listShow.get(position).getClosure6fo()!=0){
            mx6.setText(listShow.get(position).getClosure6fo()+"");
            rowmx6.setVisibility(View.VISIBLE);
        } else{
            rowmx6.setVisibility(View.GONE);
        }
        if(listShow.get(position).getClosure12fo()!=0){
            mx12.setText(listShow.get(position).getClosure12fo()+"");
            rowmx12.setVisibility(View.VISIBLE);
        } else{
            rowmx12.setVisibility(View.GONE);
        }
        if(listShow.get(position).getClosure24fo()!=0){
            mx24.setText(listShow.get(position).getClosure24fo()+"");
            rowmx24.setVisibility(View.VISIBLE);
        } else{
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
        dialog.show();
    }
}
