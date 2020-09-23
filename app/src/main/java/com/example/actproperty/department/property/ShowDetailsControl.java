package com.example.actproperty.department.property;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.actproperty.Management;
import com.example.actproperty.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShowDetailsControl extends AppCompatActivity {
    ArrayList<ItemUsed>listItemUsed;
    WebView webDetailsControl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details_control);
        listItemUsed = new ArrayList<>();
        pushProvide("https://sqlandroid2812.000webhostapp.com/showdetailscontrol.php");

//        getListItemUsed();
    }
//    private void getListItemUsed(){
//        Intent intent = getIntent();
//        listItemUsed = (ArrayList<ItemUsed>) intent.getSerializableExtra("ListItemUsed");
//    }
//    private void ShowDetails(){
//
//    }
private void pushProvide(String url) {

    final RequestQueue requestQueue = Volley.newRequestQueue(this);
    final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(ShowDetailsControl.this, "Lỗi "+error.toString(), Toast.LENGTH_SHORT).show();
        }
    }
    )
    {
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map <String, String> params = new HashMap<>();
            //Get id inventory
            params.put("provide","Bình Dương");
            return params;
        }
    };
    requestQueue.add(stringRequest);
    showWeb();
}
    private void showWeb(){
        webDetailsControl = (WebView)findViewById(R.id.webDetailsControl);
        WebSettings webSettings = webDetailsControl.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webDetailsControl.loadUrl("https://sqlandroid2812.000webhostapp.com/showdetailscontrol.php");
    }
}