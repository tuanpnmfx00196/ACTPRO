package com.example.actproperty.inventory;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import com.example.actproperty.R;
import com.example.actproperty.itemclick_Interface.OnItemClickRecyclerView;
import com.example.actproperty.passport.Passport;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Inventory extends AppCompatActivity implements OnItemClickRecyclerView {
    ArrayList<Passport> listUser;
    String storecode;
    ArrayList<MaterialsInventory> listInventory;
    ArrayList<TempDeliver>listTempDeliver;
    ArrayList<TempDeliver>listTempDeliverPermission;
    LinearLayout deliver;
    TextView makho,inventory6fo, inventory12fo, inventory24fo, inventoryodf6, inventoryodf12,
                inventoryodf24, inventorymx6, inventorymx12, inventorymx24, inventorybl300,
                inventorybl400, inventoryclamp, inventorysclc5, inventorysclc10;
    LinearLayout row6fo_inventory, row12fo_inventory, row24fo_inventory, rowodf6fo_inventory,
            rowodf12fo_inventory, rowodf24fo_inventory, rowmx6fo_inventory, rowmx12fo_inventory,
            rowmx24fo_inventory, rowbl300_inventory, rowbl400_inventory, rowclamp_inventory, rowsclc5_inventory,
    rowsclc10_inventory;
    ListView listDeliver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        listUser = new ArrayList<>();
        listInventory = new ArrayList<>();
        listTempDeliver = new ArrayList<>();
        listTempDeliverPermission = new ArrayList<>();
        getTempDeliver("https://sqlandroid2812.000webhostapp.com/gettempdeliver.php");
        storecode="";
        getAccount();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Hello "+listUser.get(0).getUser());
        Map();
        getInventory("https://sqlandroid2812.000webhostapp.com/getinventory.php");
       }
    public void getAccount(){
        Intent intent = getIntent();
        storecode = intent.getStringExtra("Storecode");
        listUser = (ArrayList<Passport>) intent.getSerializableExtra("Account");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.inventory,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.actJSC:
                Toast.makeText(this, "ACT JSC", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imgUser:
                Toast.makeText(this, "Image User", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item1_inventory:
                Toast.makeText(this, "Item 1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item2_inventory:
                Toast.makeText(this, "Item 2", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void ShowInventory(){
        for(int i=0;i<listInventory.size();i++){
            if(listInventory.get(i).getStoreCode().toString().equals(storecode)){
                if(listInventory.get(i).getHanging6fo()!=0){
                    row6fo_inventory.setVisibility(View.VISIBLE);
                    inventory6fo.setText(listInventory.get(i).getHanging6fo()+"");
                }
                if(listInventory.get(i).getHanging12fo()!=0){
                    row12fo_inventory.setVisibility(View.VISIBLE);
                    inventory12fo.setText(listInventory.get(i).getHanging12fo()+"");
                }
                if(listInventory.get(i).getHanging24fo()!=0){
                    row24fo_inventory.setVisibility(View.VISIBLE);
                    inventory24fo.setText(listInventory.get(i).getHanging24fo()+"");
                }
                if(listInventory.get(i).getOdf6fo()!=0){
                    rowodf6fo_inventory.setVisibility(View.VISIBLE);
                    inventoryodf6.setText(listInventory.get(i).getOdf6fo()+"");
                }
                if(listInventory.get(i).getOdf12fo()!=0){
                    rowodf12fo_inventory.setVisibility(View.VISIBLE);
                    inventoryodf12.setText(listInventory.get(i).getOdf12fo()+"");
                }
                if(listInventory.get(i).getOdf24fo()!=0){
                    rowodf24fo_inventory.setVisibility(View.VISIBLE);
                    inventoryodf24.setText(listInventory.get(i).getOdf24fo()+"");
                }
                if(listInventory.get(i).getClosure6fo()!=0){
                    rowmx6fo_inventory.setVisibility(View.VISIBLE);
                    inventorymx6.setText(listInventory.get(i).getClosure6fo()+"");
                }
                if(listInventory.get(i).getClosure12fo()!=0){
                    rowmx12fo_inventory.setVisibility(View.VISIBLE);
                    inventorymx12.setText(listInventory.get(i).getClosure12fo()+"");
                }
                if(listInventory.get(i).getClosure24fo()!=0){
                    rowmx24fo_inventory.setVisibility(View.VISIBLE);
                    inventorymx24.setText(listInventory.get(i).getClosure24fo()+"");
                }
                if(listInventory.get(i).getBl300()!=0){
                    rowbl300_inventory.setVisibility(View.VISIBLE);
                    inventorybl300.setText(listInventory.get(i).getBl300()+"");
                }
                if(listInventory.get(i).getBl400()!=0){
                    rowbl400_inventory.setVisibility(View.VISIBLE);
                    inventorybl400.setText(listInventory.get(i).getBl400()+"");
                }
                if(listInventory.get(i).getClamp()!=0){
                    rowclamp_inventory.setVisibility(View.VISIBLE);
                    inventoryclamp.setText(listInventory.get(i).getClamp()+"");
                }
                if(listInventory.get(i).getSc_lc5()!=0){
                    rowsclc5_inventory.setVisibility(View.VISIBLE);
                    inventorysclc5.setText(listInventory.get(i).getSc_lc5()+"");
                }
                if(listInventory.get(i).getSc_lc10()!=0){
                    rowsclc10_inventory.setVisibility(View.VISIBLE);
                    inventorysclc10.setText(listInventory.get(i).getSc_lc10()+"");
                }
            }
        }

    }
    private void Map(){
        makho = (TextView)findViewById(R.id.makho);
        inventory6fo = (TextView)findViewById(R.id.inventory6fo);
        inventory12fo = (TextView)findViewById(R.id.inventory12fo);
        inventory24fo = (TextView)findViewById(R.id.inventory24fo);
        inventoryodf6 = (TextView)findViewById(R.id.inventoryodf6fo);
        inventoryodf12 = (TextView)findViewById(R.id.inventoryodf12fo);
        inventoryodf24 = (TextView)findViewById(R.id.inventoryodf24fo);
        inventorymx6 = (TextView)findViewById(R.id.inventorymx6fo);
        inventorymx12 = (TextView)findViewById(R.id.inventorymx12fo);
        inventorymx24 = (TextView)findViewById(R.id.inventorymx24fo);
        inventorybl300 = (TextView)findViewById(R.id.inventorybl300);
        inventorybl400 = (TextView)findViewById(R.id.inventorybl400);
        inventoryclamp = (TextView)findViewById(R.id.inventoryclamp);
        inventorysclc5 = (TextView)findViewById(R.id.inventorysclc5);
        inventorysclc10 = (TextView)findViewById(R.id.inventorysclc10);

        row6fo_inventory = (LinearLayout)findViewById(R.id.row6fo_inventory);
        row12fo_inventory = (LinearLayout)findViewById(R.id.row12fo_inventory);
        row24fo_inventory = (LinearLayout)findViewById(R.id.row24fo_inventory);
        rowodf6fo_inventory = (LinearLayout)findViewById(R.id.rowodf6fo_inventory);
        rowodf12fo_inventory = (LinearLayout)findViewById(R.id.rowodf12fo_inventory);
        rowodf24fo_inventory = (LinearLayout)findViewById(R.id.rowodf24fo_inventory);
        rowmx6fo_inventory = (LinearLayout)findViewById(R.id.rowmx6fo_inventory);
        rowmx12fo_inventory = (LinearLayout)findViewById(R.id.rowmx12fo_inventory);
        rowmx24fo_inventory = (LinearLayout)findViewById(R.id.rowmx24fo_inventory);
        rowbl300_inventory = (LinearLayout)findViewById(R.id.rowbl300_inventory);
        rowbl400_inventory = (LinearLayout)findViewById(R.id.rowbl400_inventory);
        rowclamp_inventory = (LinearLayout)findViewById(R.id.rowclamp_inventory);
        rowsclc5_inventory = (LinearLayout)findViewById(R.id.rowsclc5_inventory);
        rowsclc10_inventory = (LinearLayout)findViewById(R.id.rowsclc10_inventory);
    }
    public void getInventory(String url){
        listInventory = new ArrayList<>();
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
                        Toast.makeText(Inventory.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                ShowInventory();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Inventory.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(jsonArrayRequest);
    }

    /*======================== GET TEMP DELIVER ================================*/
    public void getTempDeliver(String url){
        listTempDeliver = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i=0; i<response.length();i++){
                    try{
                        JSONObject jsonObject = response.getJSONObject(i);
                        listTempDeliver.add(new TempDeliver(
                                jsonObject.getInt("IDdeliver"),
                                jsonObject.getString("Storecodedeliver"),
                                jsonObject.getInt("Hanging6fodeliver"),
                                jsonObject.getInt("Hanging12fodeliver"),
                                jsonObject.getInt("Hanging24fodeliver"),
                                jsonObject.getInt("Odf6fodeliver"),
                                jsonObject.getInt("Odf12fodeliver"),
                                jsonObject.getInt("Odf24fodeliver"),
                                jsonObject.getInt("Closure6fodeliver"),
                                jsonObject.getInt("Closure12fodeliver"),
                                jsonObject.getInt("Closure24fodeliver"),
                                jsonObject.getInt("Buloongti300deliver"),
                                jsonObject.getInt("Buloongti400deliver"),
                                jsonObject.getInt("Clampdeliver"),
                                jsonObject.getInt("Sc_lc5deliver"),
                                jsonObject.getInt("Sc_lc10deliver"),
                                jsonObject.getString("Userdeliver"),
                                jsonObject.getString("Timedeliver"),
                                jsonObject.getString("Commentdeliver"),
                                jsonObject.getInt("Flag")
                        ));
                    }catch(Exception e){
                        Toast.makeText(Inventory.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                showTemp();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Inventory.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(jsonArrayRequest);
    }
    private void showTemp(){
        if(listUser.get(0).getAdmin()==1){
            for(int i=0;i<listTempDeliver.size();i++){
                if(listTempDeliver.get(i).getFlag()==1){
                    listTempDeliverPermission.add(listTempDeliver.get(i));
                }
            }

        } else {
            if (listUser.get(0).getBdg() == 1) {
                for (int i = 0; i < listTempDeliver.size(); i++) {
                    if (listTempDeliver.get(i).getStoreCode().equals("Bình Dương")&&listTempDeliver.get(i).getFlag()==1) {
                        listTempDeliverPermission.add(listTempDeliver.get(i));
                    }
                }
            }
            if (listUser.get(0).getKgg() == 1) {
                for (int i = 0; i < listTempDeliver.size(); i++) {
                    if (listTempDeliver.get(i).getStoreCode().equals("Kiên Giang")&&listTempDeliver.get(i).getFlag()==1) {
                        listTempDeliverPermission.add(listTempDeliver.get(i));
                    }
                }
            }
            if (listUser.get(0).getBte() == 1) {
                for (int i = 0; i < listTempDeliver.size(); i++) {
                    if (listTempDeliver.get(i).getStoreCode().equals("Bến Tre")&&listTempDeliver.get(i).getFlag()==1) {
                        listTempDeliverPermission.add(listTempDeliver.get(i));
                    }
                }
            }
            if (listUser.get(0).getTvh() == 1) {
                for (int i = 0; i < listTempDeliver.size(); i++) {
                    if (listTempDeliver.get(i).getStoreCode().equals("Trà Vinh")&&listTempDeliver.get(i).getFlag()==1) {
                        listTempDeliverPermission.add(listTempDeliver.get(i));
                    }
                }
            }
            if (listUser.get(0).getLan() == 1) {
                for (int i = 0; i < listTempDeliver.size(); i++) {
                    if (listTempDeliver.get(i).getStoreCode().equals("Long An")&&listTempDeliver.get(i).getFlag()==1) {
                        listTempDeliverPermission.add(listTempDeliver.get(i));
                    }
                }
            }
            if (listUser.get(0).getDni() == 1) {
                for (int i = 0; i < listTempDeliver.size(); i++) {
                    if (listTempDeliver.get(i).getStoreCode().equals("Đồng Nai")&&listTempDeliver.get(i).getFlag()==1) {
                        listTempDeliverPermission.add(listTempDeliver.get(i));
                    }
                }
            }
            if (listUser.get(0).getHcm_gvp() == 1) {
                for (int i = 0; i < listTempDeliver.size(); i++) {
                    if (listTempDeliver.get(i).getStoreCode().equals("HCM_Gò Vấp")&&listTempDeliver.get(i).getFlag()==1) {
                        listTempDeliverPermission.add(listTempDeliver.get(i));
                    }
                }
            }
            if (listUser.get(0).getHcm_q12() == 1) {
                for (int i = 0; i < listTempDeliver.size(); i++) {
                    if (listTempDeliver.get(i).getStoreCode().equals("HCM_Quận 12")&&listTempDeliver.get(i).getFlag()==1) {
                        listTempDeliverPermission.add(listTempDeliver.get(i));
                    }
                }
            }
            if (listUser.get(0).getHcm_hmn() == 1) {
                for (int i = 0; i < listTempDeliver.size(); i++) {
                    if (listTempDeliver.get(i).getStoreCode().equals("HCM_Hóc Môn")&&listTempDeliver.get(i).getFlag()==1) {
                        listTempDeliverPermission.add(listTempDeliver.get(i));
                    }
                }
            }
            if (listUser.get(0).getHcm_cci() == 1) {
                for (int i = 0; i < listTempDeliver.size(); i++) {
                    if (listTempDeliver.get(i).getStoreCode().equals("HCM_Củ Chi")&&listTempDeliver.get(i).getFlag()==1) {
                        listTempDeliverPermission.add(listTempDeliver.get(i));
                    }
                }
            }
            if (listUser.get(0).getHcm_btn() == 1) {
                for (int i = 0; i < listTempDeliver.size(); i++) {
                    if (listTempDeliver.get(i).getStoreCode().equals("HCM_Bình Tân")&&listTempDeliver.get(i).getFlag()==1) {
                        listTempDeliverPermission.add(listTempDeliver.get(i));
                    }
                }
            }
            if (listUser.get(0).getHcm_bch() == 1) {
                for (int i = 0; i < listTempDeliver.size(); i++) {
                    if (listTempDeliver.get(i).getStoreCode().equals("HCM_Bình Chánh")&&listTempDeliver.get(i).getFlag()==1) {
                        listTempDeliverPermission.add(listTempDeliver.get(i));
                    }
                }
            }
            /*... Add more code store*/
        }
        listDeliver = (ListView) findViewById(R.id.listDeliver);
        deliver = (LinearLayout)findViewById(R.id.deliver);
        if(listTempDeliverPermission.size()==0){
            deliver.setVisibility(View.GONE);
        } else {
            listDeliver.setVisibility(View.VISIBLE);
            TempDeliverAdapter deliverAdapter = new TempDeliverAdapter(listTempDeliverPermission,
                    this,getApplicationContext());
            listDeliver.setAdapter(deliverAdapter);
        }
    }
    /*===========================================================================================*/
    /*=========================================INTERFACE=========================================*/
    @Override
    public void onClick(int position) {
       switch (listTempDeliverPermission.get(position).getStoreCode()) {
           case "Bến Tre":
               UpdateInventory("https://sqlandroid2812.000webhostapp.com/updateinventory.php",
                       1,
                       listTempDeliverPermission.get(position).getHanging6fo(),
                       listTempDeliverPermission.get(position).getHanging12fo(),
                       listTempDeliverPermission.get(position).getHanging24fo(),
                       listTempDeliverPermission.get(position).getOdf6fo(),
                       listTempDeliverPermission.get(position).getOdf12fo(),
                       listTempDeliverPermission.get(position).getOdf24fo(),
                       listTempDeliverPermission.get(position).getClosure6fo(),
                       listTempDeliverPermission.get(position).getClosure12fo(),
                       listTempDeliverPermission.get(position).getClosure24fo(),
                       listTempDeliverPermission.get(position).getBl300(),
                       listTempDeliverPermission.get(position).getBl400(),
                       listTempDeliverPermission.get(position).getClamp(),
                       listTempDeliverPermission.get(position).getSc_lc5(),
                       listTempDeliverPermission.get(position).getSc_lc10()
               );
               UpdateDeliver("https://sqlandroid2812.000webhostapp.com/updatedeliver.php",
                       listTempDeliverPermission.get(position).getId(),2);
               break;
           case "Long An":
               UpdateInventory("https://sqlandroid2812.000webhostapp.com/updateinventory.php",
                       2,
                       listTempDeliverPermission.get(position).getHanging6fo(),
                       listTempDeliverPermission.get(position).getHanging12fo(),
                       listTempDeliverPermission.get(position).getHanging24fo(),
                       listTempDeliverPermission.get(position).getOdf6fo(),
                       listTempDeliverPermission.get(position).getOdf12fo(),
                       listTempDeliverPermission.get(position).getOdf24fo(),
                       listTempDeliverPermission.get(position).getClosure6fo(),
                       listTempDeliverPermission.get(position).getClosure12fo(),
                       listTempDeliverPermission.get(position).getClosure24fo(),
                       listTempDeliverPermission.get(position).getBl300(),
                       listTempDeliverPermission.get(position).getBl400(),
                       listTempDeliverPermission.get(position).getClamp(),
                       listTempDeliverPermission.get(position).getSc_lc5(),
                       listTempDeliverPermission.get(position).getSc_lc10()
               );
               UpdateDeliver("https://sqlandroid2812.000webhostapp.com/updatedeliver.php",
                       listTempDeliverPermission.get(position).getId(),2);
               break;
           case "Trà Vinh":
               UpdateInventory("https://sqlandroid2812.000webhostapp.com/updateinventory.php",
                       3,
                       listTempDeliverPermission.get(position).getHanging6fo(),
                       listTempDeliverPermission.get(position).getHanging12fo(),
                       listTempDeliverPermission.get(position).getHanging24fo(),
                       listTempDeliverPermission.get(position).getOdf6fo(),
                       listTempDeliverPermission.get(position).getOdf12fo(),
                       listTempDeliverPermission.get(position).getOdf24fo(),
                       listTempDeliverPermission.get(position).getClosure6fo(),
                       listTempDeliverPermission.get(position).getClosure12fo(),
                       listTempDeliverPermission.get(position).getClosure24fo(),
                       listTempDeliverPermission.get(position).getBl300(),
                       listTempDeliverPermission.get(position).getBl400(),
                       listTempDeliverPermission.get(position).getClamp(),
                       listTempDeliverPermission.get(position).getSc_lc5(),
                       listTempDeliverPermission.get(position).getSc_lc10()
               );
               UpdateDeliver("https://sqlandroid2812.000webhostapp.com/updatedeliver.php",
                       listTempDeliverPermission.get(position).getId(),2);
               break;
           case "Bình Dương":
               UpdateInventory("https://sqlandroid2812.000webhostapp.com/updateinventory.php",
                       4,
                       listTempDeliverPermission.get(position).getHanging6fo(),
                       listTempDeliverPermission.get(position).getHanging12fo(),
                       listTempDeliverPermission.get(position).getHanging24fo(),
                       listTempDeliverPermission.get(position).getOdf6fo(),
                       listTempDeliverPermission.get(position).getOdf12fo(),
                       listTempDeliverPermission.get(position).getOdf24fo(),
                       listTempDeliverPermission.get(position).getClosure6fo(),
                       listTempDeliverPermission.get(position).getClosure12fo(),
                       listTempDeliverPermission.get(position).getClosure24fo(),
                       listTempDeliverPermission.get(position).getBl300(),
                       listTempDeliverPermission.get(position).getBl400(),
                       listTempDeliverPermission.get(position).getClamp(),
                       listTempDeliverPermission.get(position).getSc_lc5(),
                       listTempDeliverPermission.get(position).getSc_lc10()
               );
               UpdateDeliver("https://sqlandroid2812.000webhostapp.com/updatedeliver.php",
                       listTempDeliverPermission.get(position).getId(),2);
               break;
           case "Kiên Giang":
               UpdateInventory("https://sqlandroid2812.000webhostapp.com/updateinventory.php",
                       5,
                       listTempDeliverPermission.get(position).getHanging6fo(),
                       listTempDeliverPermission.get(position).getHanging12fo(),
                       listTempDeliverPermission.get(position).getHanging24fo(),
                       listTempDeliverPermission.get(position).getOdf6fo(),
                       listTempDeliverPermission.get(position).getOdf12fo(),
                       listTempDeliverPermission.get(position).getOdf24fo(),
                       listTempDeliverPermission.get(position).getClosure6fo(),
                       listTempDeliverPermission.get(position).getClosure12fo(),
                       listTempDeliverPermission.get(position).getClosure24fo(),
                       listTempDeliverPermission.get(position).getBl300(),
                       listTempDeliverPermission.get(position).getBl400(),
                       listTempDeliverPermission.get(position).getClamp(),
                       listTempDeliverPermission.get(position).getSc_lc5(),
                       listTempDeliverPermission.get(position).getSc_lc10()
               );
               UpdateDeliver("https://sqlandroid2812.000webhostapp.com/updatedeliver.php",
                       listTempDeliverPermission.get(position).getId(),2);
               break;

       }

    }

    @Override
    public void onClick1(int position) {
        UpdateDeliver("https://sqlandroid2812.000webhostapp.com/updatedeliver.php",
                listTempDeliverPermission.get(position).getId(),3);
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
    /*=========================================END INTERFACE=====================================*/


    private void UpdateInventory(String url, final int id, final int hanging6fo,
                                 final int hanging12fo , final int hanging24fo, final int odf6fo, final int odf12fo,
                                 final int odf24fo, final int closure6fo, final int closure12fo, final int closure24fo,
                                 final int buloong300, final int buloong400, final int clamp, final int sc_lc5, final int sc_lc10) {

        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Inventory.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String, String> params = new HashMap<>();
                //Get id inventory
                params.put("updateID",String.valueOf(id));
                params.put("updateHanging6fo", String.valueOf(listInventory.get(id-1).getHanging6fo()+hanging6fo));
                params.put("updateHanging12fo", String.valueOf(listInventory.get(id-1).getHanging12fo()+hanging12fo));
                params.put("updateHanging24fo", String.valueOf(listInventory.get(id-1).getHanging24fo()+hanging24fo));
                params.put("updateOdf6fo", String.valueOf(listInventory.get(id-1).getOdf6fo()+odf6fo));
                params.put("updateOdf12fo", String.valueOf(listInventory.get(id-1).getOdf12fo()+odf12fo));
                params.put("updateOdf24fo", String.valueOf(listInventory.get(id-1).getOdf24fo()+odf24fo));
                params.put("updateClosure6fo", String.valueOf(listInventory.get(id-1).getClosure6fo()+closure6fo));
                params.put("updateClosure12fo", String.valueOf(listInventory.get(id-1).getClosure12fo()+closure12fo));
                params.put("updateClosure24fo", String.valueOf(listInventory.get(id-1).getClosure24fo()+closure24fo));
                params.put("updateBuloong300", String.valueOf(listInventory.get(id-1).getBl300()+buloong300));
                params.put("updateBuloong400", String.valueOf(listInventory.get(id-1).getBl400()+buloong400));
                params.put("updateClamp", String.valueOf(listInventory.get(id-1).getClamp()+clamp));
                params.put("updateSc_lc5", String.valueOf(listInventory.get(id-1).getSc_lc5()+sc_lc5));
                params.put("updateSc_lc10", String.valueOf(listInventory.get(id-1).getSc_lc10()+sc_lc10));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void UpdateDeliver(String url, final int id, final int flag) {

        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Inventory.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String, String> params = new HashMap<>();
                //Get id inventory
                params.put("updateID",String.valueOf(id));
                params.put("updateFlag", String.valueOf(flag));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}