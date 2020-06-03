package com.example.actproperty;

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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/* pass database cable --->>> |aSGl2|vqrg/EfWh */
public class Management extends AppCompatActivity {
    Button btnSearch;
    EditText searchLocal, searchCableId;
    ArrayList<CableId> listCable;
    ArrayList<CableId> listSearch;
    CableIdAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);
        adapter = new CableIdAdapter(listCable,Management.this);
        listCable = new ArrayList<>();
        listSearch = new ArrayList<>();
        ReadJson("https://sqlandroid2812.000webhostapp.com/getdata.php");
        btnSearch = (Button)findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Search();
            }
        });
    }
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
                    adapter.notifyDataSetChanged();
                    initView();
            }
        },
                new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                 Toast.makeText(Management.this, "?????", Toast.LENGTH_SHORT).show();
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
        CableIdAdapter cableIdAdapter = new CableIdAdapter(listCable, getApplicationContext());
        recyclerView.setAdapter(cableIdAdapter);
    }
    /*======================= INIT RecyclerView Fragment search ====================*/
    public void initViewFragment(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        CableIdAdapter cableIdAdapter = new CableIdAdapter(listSearch, getApplicationContext());
        recyclerView.setAdapter(cableIdAdapter);
    }

    /*============================== Search =================================*/
    public void Search(){
        searchLocal = (EditText)findViewById(R.id.searchLocal);
        searchCableId = (EditText)findViewById(R.id.searchCableId);

        initViewFragment();
    }
}
