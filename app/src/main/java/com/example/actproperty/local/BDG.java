package com.example.actproperty.local;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class BDG {
    Context context;
    public void inserBDG(String url){
        this.context = context;
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        )
        {
                    @Override
                protected Map<String,String> getParams()throws AuthFailureError{
                    Map<String, String> params = new HashMap<>();
                    params.put("putCableid","value");
                    params.put("putProvince","Bình Dương");
                    params.put("putHanging4fo","0");
                    params.put("putHanging6fo","value");
                    params.put("putHanging12fo","0");
                    params.put("putHanging24fo","0");
                    params.put("putDu12fo","0");
                    params.put("putOdf6fo","value");
                    params.put("putOdf12fo","0");
                    params.put("putOdf24fo","0");
                    params.put("putClosure6fo","value");
                    params.put("putClosure12fo","0");
                    params.put("putClosure24fo","0");
                    params.put("putBuloong300","value");
                    params.put("putBuloong400","value");
                    params.put("putClamp","value");
                    params.put("putPole8","value");
                    params.put("putIronpole6","value");
                    params.put("putSc.lc5","value");
                    params.put("putSc.lc10","value");
                    return params;
                    }
        };
        requestQueue.add(stringRequest);
    }
}
