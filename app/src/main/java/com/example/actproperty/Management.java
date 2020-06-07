package com.example.actproperty;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.actproperty.passport.Passport;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* pass database cable --->>> |aSGl2|vqrg/EfWh */
public class Management extends AppCompatActivity {
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
        adapter = new CableIdAdapter(listCable,Management.this);
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
                //Search();
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
                                jsonObject.getString("CableId")
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
                                        jsonObject.getString("CableId")
                                ));
                            }catch(Exception e){
                                Toast.makeText(Management.this, e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        //Module Search ================================>>>>>>>>>>>>>>>>>
                        for(int i=0; i<list.size();i++){
                          if(searchLocal.getText().toString().trim() !=""){
                              if(list.get(i).getProvince().toLowerCase().contains(
                                      searchLocal.getText().toString().trim().toLowerCase()
                              )){
                                  if(searchCableId.getText().toString().trim()==""){
                                      listSearch.add(list.get(i));
                                  } else{
                                      if(list.get(i).getCableId().trim().toLowerCase().contains(
                                              searchCableId.getText().toString().trim().toLowerCase()
                                      )){
                                          listSearch.add(list.get(i));
                                      }
                                  }
                              }
                          } else{
                              if(searchCableId.getText().toString().trim()!=""){
                                  if(list.get(i).getCableId().trim().toLowerCase().contains(
                                     searchCableId.getText().toString().trim().toLowerCase())){
                                      listSearch.add(list.get(i));
                                  }else{
                                      Toast.makeText(Management.this, "No found", Toast.LENGTH_SHORT).show();
                                  }
                              }
                          }

                        }
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
        CableIdAdapter cableIdAdapter = new CableIdAdapter(listShow, getApplicationContext());
        recyclerView.setAdapter(cableIdAdapter);
    }
    /*======================= INIT RecyclerView Fragment search ====================*/
    public void initViewSearch(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView1);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        CableIdAdapter cableIdAdapter = new CableIdAdapter(listSearch, getApplicationContext());
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
}
