package com.example.actproperty.department.noc;

import android.content.Context;
import android.support.annotation.NonNull;
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
import java.util.stream.Stream;

public class GetListCR extends ArrayList<CRNOC> {
    private String url;
    ArrayList<CRNOC>listCR;
    Context context;
    public GetListCR(String url) {
        this.url = url;
    }

    private ArrayList<CRNOC> getListCR(String url){
       listCR = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(context);
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
                        Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
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
       return listCR;
    }

    @NonNull
    @Override
    public Stream<CRNOC> stream() {
        return null;
    }
}
