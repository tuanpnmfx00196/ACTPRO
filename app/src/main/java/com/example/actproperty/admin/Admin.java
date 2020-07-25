package com.example.actproperty.admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.actproperty.R;
import com.example.actproperty.history.History;
import com.example.actproperty.history.HistoryAdapter;
import com.example.actproperty.itemclick_Interface.OnItemClickRecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Admin extends AppCompatActivity implements OnItemClickRecyclerView {
    RecyclerView recyclerHistory;
    ArrayList<History> listHistory;
    HistoryAdapter historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        recyclerHistory = (RecyclerView)findViewById(R.id.recyclerHistory);
        listHistory = new ArrayList<>();
        historyAdapter = new HistoryAdapter(listHistory, Admin.this, this);
        ReadJson("https://sqlandroid2812.000webhostapp.com/gethistory.php");
    }
    private void ReadJson(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0; i<response.length();i++){
                    try{
                        JSONObject jsonObject = response.getJSONObject(i);
                        listHistory.add(new History(
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
                                jsonObject.getInt("Oldid"),
                                jsonObject.getString("Datechange"),
                                jsonObject.getString("Comment"),
                                jsonObject.getString("Userchange")
                        ));
                    }catch(Exception e){
                        Toast.makeText(Admin.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                //TODO
                initView();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        requestQueue.add(jsonArrayRequest);
    }
    private void initView(){
        recyclerHistory = (RecyclerView)findViewById(R.id.recyclerHistory);
        recyclerHistory.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false);
        recyclerHistory.setLayoutManager(layoutManager);
        historyAdapter = new HistoryAdapter(listHistory,getApplicationContext(),this);
        recyclerHistory.setAdapter(historyAdapter);
    }

    @Override
    public void onClick(int position) {
        Toast.makeText(this, listHistory.get(position).getCableId(), Toast.LENGTH_SHORT).show();
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}