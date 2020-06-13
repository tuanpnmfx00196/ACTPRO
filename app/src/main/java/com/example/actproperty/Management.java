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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.actproperty.itemclick.OnItemClickRecyclerView;
import com.example.actproperty.passport.Passport;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    private void ShowMoreDetail(final int position){
        final Dialog dialog = new Dialog(this);
        dialog.setTitle(listShow.get(position).getCableId());
        dialog.setContentView(R.layout.show_more);
        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.97);
        dialog.getWindow().setLayout(width, LinearLayout.LayoutParams.WRAP_CONTENT);
        final TextView cable4fo,cable4fo_update, cable6fo,cable6fo_update, cable12fo,cable12fo_update, cable24fo,cable24fo_update,
                cable12du, cable12du_update, odf6, odf6_update, odf12,odf12_update, odf24, odf24_update, odf96, odf96_update,
                mx6,mx6_update, mx12,mx12_update, mx24,mx24_update, buloongti300, buloongti300_update, buloongti400, buloongti400_update,
                poleu8, poleu8_update,  ironpole6, ironpole6_update, clamp, clamp_update, sc_lc5m,sc_lc5m_update,
                sc_lc10m, sc_lc10m_update, sc_sc5m, sc_sc5m_update, dialogTitle, dialogTitle_update;
        final EditText cable4fo_edit, cable6fo_edit, cable12fo_edit, cable24fo_edit, cable12du_edit, odf6fo_edit,
                odf12fo_edit, odf24fo_edit, odf96fo_edit, mx6_edit, mx12_edit, mx24_edit, buloong300_edit, buloong400_edit,
                clamp_edit, poleu8_edit, ironpole6_edit, sc_lc5_edit, sc_lc10_edit, sc_sc5_edit;
        LinearLayout row4fo, row4fo_update, row6fo, row6fo_update, row12fo, row12fo_update, row24fo, row24fo_update,
                rowdu12fo,rowdu12fo_update, rowodf6fo, rowodf6fo_update, rowodf12fo, rowodf12fo_update, rowodf24fo, rowodf24fo_update,
                rowodf96fo, rowodf96fo_update, rowmx6, rowmx6_update, rowmx12,rowmx12_update, rowmx24,rowmx24_update, rowbuloongti300,
                rowbuloongti300_update, rowbuloongti400, rowbuloongti400_update, rowpoleu8, rowpoleu8_update, rowironpole6, rowironpole6_update,
                rowclamp,rowclamp_update, rowsc_lc5m, rowsc_lc5m_update, rowsc_lc10m, rowsc_lc10m_update, rowsc_sc5m, rowsc_sc5m_update;
        dialogTitle= (TextView)dialog.findViewById(R.id.dialogTitle);
        dialogTitle_update= (TextView)dialog.findViewById(R.id.dialogTitle_update);
        cable4fo = (TextView)dialog.findViewById(R.id.cable4fo);
        cable4fo_update = (TextView)dialog.findViewById(R.id.cable4fo_update);
        cable6fo = (TextView)dialog.findViewById(R.id.cable6fo);
        cable6fo_update = (TextView)dialog.findViewById(R.id.cable6fo_update);
        cable12fo = (TextView)dialog.findViewById(R.id.cable12fo);
        cable12fo_update = (TextView)dialog.findViewById(R.id.cable12fo_update);
        cable24fo = (TextView)dialog.findViewById(R.id.cable24fo);
        cable24fo_update = (TextView)dialog.findViewById(R.id.cable24fo_update);
        cable12du = (TextView)dialog.findViewById(R.id.cable12du);
        cable12du_update = (TextView)dialog.findViewById(R.id.cable12du_update);
        odf6 = (TextView)dialog.findViewById(R.id.odf6);
        odf6_update = (TextView)dialog.findViewById(R.id.odf6_update);
        odf12 = (TextView)dialog.findViewById(R.id.odf12);
        odf12_update = (TextView)dialog.findViewById(R.id.odf12_update);
        odf24 = (TextView)dialog.findViewById(R.id.odf24);
        odf24_update = (TextView)dialog.findViewById(R.id.odf24_update);
        odf96 = (TextView)dialog.findViewById(R.id.odf96);
        odf96_update = (TextView)dialog.findViewById(R.id.odf96_update);
        mx6 = (TextView)dialog.findViewById(R.id.mx6);
        mx6_update = (TextView)dialog.findViewById(R.id.mx6_update);
        mx12 = (TextView)dialog.findViewById(R.id.mx12);
        mx12_update = (TextView)dialog.findViewById(R.id.mx12_update);
        mx24 = (TextView)dialog.findViewById(R.id.mx24);
        mx24_update = (TextView)dialog.findViewById(R.id.mx24_update);
        buloongti300 = (TextView)dialog.findViewById(R.id.buloongti300);
        buloongti300_update = (TextView)dialog.findViewById(R.id.buloongti300_update);
        buloongti400 = (TextView)dialog.findViewById(R.id.buloongti400);
        buloongti400_update = (TextView)dialog.findViewById(R.id.buloongti400_update);
        poleu8 = (TextView) dialog.findViewById(R.id.poleu8);
        poleu8_update = (TextView) dialog.findViewById(R.id.poleu8_update);
        ironpole6 = (TextView) dialog.findViewById(R.id.ironpole6);
        ironpole6_update = (TextView) dialog.findViewById(R.id.ironpole6_update);
        clamp = (TextView)dialog.findViewById(R.id.clamp);
        clamp_update = (TextView)dialog.findViewById(R.id.clamp_update);
        sc_lc5m = (TextView)dialog.findViewById(R.id.sc_lc5m);
        sc_lc5m_update = (TextView)dialog.findViewById(R.id.sc_lc5m_update);
        sc_lc10m = (TextView)dialog.findViewById(R.id.sc_lc10m);
        sc_lc10m_update = (TextView)dialog.findViewById(R.id.sc_lc10m_update);
        sc_sc5m = (TextView)dialog.findViewById(R.id.sc_sc5m);
        sc_sc5m_update = (TextView)dialog.findViewById(R.id.sc_sc5m_update);
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

        cable4fo_edit = (EditText) dialog.findViewById(R.id.cable4fo_edit);
        cable6fo_edit = (EditText) dialog.findViewById(R.id.cable6fo_edit);
        cable12fo_edit = (EditText) dialog.findViewById(R.id.cable12fo_edit);
        cable24fo_edit = (EditText) dialog.findViewById(R.id.cable24fo_edit);
        cable12du_edit = (EditText) dialog.findViewById(R.id.cable12du_edit);
        odf6fo_edit = (EditText)dialog.findViewById(R.id.odf6_edit);
        odf12fo_edit = (EditText)dialog.findViewById(R.id.odf12_edit);
        odf24fo_edit = (EditText)dialog.findViewById(R.id.cable24fo_edit);
        odf96fo_edit = (EditText) dialog.findViewById(R.id.odf96_edit);
        mx6_edit = (EditText) dialog.findViewById(R.id.mx6_edit);
        mx12_edit = (EditText) dialog.findViewById(R.id.mx12_edit);
        mx24_edit = (EditText) dialog.findViewById(R.id.mx24_edit);
        clamp_edit = (EditText) dialog.findViewById(R.id.clamp_edit);
        poleu8_edit = (EditText) dialog.findViewById(R.id.poleu8_edit);
        ironpole6_edit = (EditText) dialog.findViewById(R.id.ironpole6_edit);
        buloong300_edit = (EditText) dialog.findViewById(R.id.buloongti300_edit);
        buloong400_edit = (EditText) dialog.findViewById(R.id.buloongti400_edit);
        sc_lc5_edit = (EditText) dialog.findViewById(R.id.sc_lc5m_edit);
        sc_lc10_edit = (EditText) dialog.findViewById(R.id.sc_lc10m_edit);
        sc_sc5_edit = (EditText) dialog.findViewById(R.id.sc_sc5m_edit);
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
            cable4fo_update.setText(listShow.get(position).getHanging4fo()+"");
            row4fo_update.setVisibility(View.VISIBLE);
        } else{
            row4fo_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getHanging6fo()!=0){
            cable6fo_update.setText(listShow.get(position).getHanging6fo()+"");
            row6fo_update.setVisibility(View.VISIBLE);
        } else{
            row6fo_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getHanging12fo()!=0){
            cable12fo_update.setText(listShow.get(position).getHanging12fo()+"");
            row12fo_update.setVisibility(View.VISIBLE);
        } else{
            row12fo_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getHanging24fo()!=0){
            cable24fo_update.setText(listShow.get(position).getHanging24fo()+"");
            row24fo_update.setVisibility(View.VISIBLE);
        } else{
            row24fo_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getDu12fo()!=0){
            cable12du_update.setText(listShow.get(position).getDu12fo()+"");
            rowdu12fo_update.setVisibility(View.VISIBLE);
        } else{
            rowdu12fo_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getOdf6fo()!=0){
            odf6_update.setText(listShow.get(position).getOdf6fo()+"");
            rowodf6fo_update.setVisibility(View.VISIBLE);
        } else{
            rowodf6fo_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getOdf12fo()!=0){
            odf12_update.setText(listShow.get(position).getOdf12fo()+"");
            rowodf12fo_update.setVisibility(View.VISIBLE);
        } else{
            rowodf12fo_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getOdf24fo()!=0){
            odf24_update.setText(listShow.get(position).getOdf24fo()+"");
            rowodf24fo_update.setVisibility(View.VISIBLE);
        } else{
            rowodf24fo_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getOdf96fo()!=0){
            odf96_update.setText(listShow.get(position).getOdf96fo()+"");
            rowodf96fo_update.setVisibility(View.VISIBLE);
        } else{
            rowodf24fo_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getClosure6fo()!=0){
            mx6_update.setText(listShow.get(position).getClosure6fo()+"");
            rowmx6_update.setVisibility(View.VISIBLE);
        } else{
            rowmx6_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getClosure12fo()!=0){
            mx12_update.setText(listShow.get(position).getClosure12fo()+"");
            rowmx12_update.setVisibility(View.VISIBLE);
        } else{
            rowmx12_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getClosure24fo()!=0){
            mx24_update.setText(listShow.get(position).getClosure24fo()+"");
            rowmx24_update.setVisibility(View.VISIBLE);
        } else{
            rowmx24_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getBuloong300()!=0){
            buloongti300_update.setText(listShow.get(position).getBuloong300()+"");
            rowbuloongti300_update.setVisibility(View.VISIBLE);
        } else{
            rowbuloongti300_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getBuloong400()!=0){
            buloongti400_update.setText(listShow.get(position).getBuloong400()+"");
            rowbuloongti400_update.setVisibility(View.VISIBLE);
        } else{
            rowbuloongti400_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getClamp()!=0){
            clamp_update.setText(listShow.get(position).getClamp()+"");
            rowclamp_update.setVisibility(View.VISIBLE);
        } else{
            rowclamp_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getPoleu8()!=0){
            poleu8_update.setText(listShow.get(position).getPoleu8()+"");
            rowpoleu8_update.setVisibility(View.VISIBLE);
        } else{
            rowpoleu8_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getIronpole6()!=0){
            ironpole6_update.setText(listShow.get(position).getIronpole6()+"");
            rowironpole6_update.setVisibility(View.VISIBLE);
        } else{
            rowironpole6_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getSc_lc5()!=0){
            sc_lc5m_update.setText(listShow.get(position).getSc_lc5()+"");
            rowsc_lc5m_update.setVisibility(View.VISIBLE);
        } else{
            rowsc_lc5m_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getSc_lc10()!=0){
            sc_lc10m_update.setText(listShow.get(position).getSc_lc5()+"");
            rowsc_lc10m_update.setVisibility(View.VISIBLE);
        } else{
            rowsc_lc10m_update.setVisibility(View.GONE);
        }
        if(listShow.get(position).getSc_sc5()!=0){
            sc_sc5m_update.setText(listShow.get(position).getSc_sc5()+"");
            rowsc_sc5m_update.setVisibility(View.VISIBLE);
        } else{
            rowsc_sc5m_update.setVisibility(View.GONE);
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
            }
        });
        btnSaveUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateSQL("https://sqlandroid2812.000webhostapp.com/update.php", position,0,0,372, 0,
                        0,0,2,0,0,0,0,0,0,0,
                        5,0,0,0,0,0

                       );
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
                           final int hanging12fo, final int hanging24fo, final int du12fo, final int odf6fo, final int odf12fo,
                           final int odf24fo, final int odf96fo, final int closure6fo, final int closure12fo, final int closure24fo,
                           final int buloong300, final int buloong400, final int clamp, final int poleu8, final int ironpole6,
                           final int sc_lc5, final int sc_lc10, final int sc_sc5
                           ){
         final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        )
        {
            @Override
            protected Map <String, String> getParams() throws AuthFailureError{
               Map <String, String> params = new HashMap<>();
               params.put("ID",String.valueOf(listShow.get(position).getId()));
               if (hanging4fo !=0){
                   params.put("Hanging4fo", String.valueOf(hanging4fo));
               }else{
                   params.put("Hanging4fo", String.valueOf(0));
               }

               if(hanging6fo !=0){
                   params.put("Hanging6fo", String.valueOf(hanging6fo));
               }else{
                   params.put("Hanging6fo", String.valueOf(0));
               }

                if(hanging12fo !=0){
                    params.put("Hanging12fo", String.valueOf(hanging12fo));
                }else{
                    params.put("Hanging12fo", String.valueOf(0));
                }

                if(hanging24fo !=0){
                    params.put("Hanging24fo", String.valueOf(hanging24fo));
                }else{
                    params.put("Hanging24fo", String.valueOf(0));
                }
                if(du12fo !=0){
                    params.put("Du12fo", String.valueOf(du12fo));
                }else{
                    params.put("Du12fo", String.valueOf(0));
                }

                if(odf6fo !=0){
                    params.put("Odf6fo", String.valueOf(odf6fo));
                }else{
                    params.put("Odf6fo", String.valueOf(0));
                }

                if(odf12fo !=0){
                    params.put("Odf12fo", String.valueOf(odf12fo));
                }else{
                    params.put("Odf12fo", String.valueOf(0));
                }

                if(odf24fo !=0){
                    params.put("Odf24fo", String.valueOf(odf24fo));
                }else{
                    params.put("Odf24fo", String.valueOf(0));
                }

                if(odf96fo !=0){
                    params.put("Odf96fo", String.valueOf(odf96fo));
                }else{
                    params.put("Odf96fo", String.valueOf(0));
                }

                if(closure6fo !=0){
                    params.put("Closure6fo", String.valueOf(closure6fo));
                }else{
                    params.put("Closure6fo", String.valueOf(0));
                }

                if(closure12fo !=0){
                    params.put("Closure12fo", String.valueOf(closure12fo));
                }else{
                    params.put("Closure12fo", String.valueOf(0));
                }

                if(closure24fo !=0){
                    params.put("Closure24fo", String.valueOf(closure24fo));
                }else{
                    params.put("Closure24fo", String.valueOf(0));
                }

                if(buloong300 !=0){
                    params.put("Buloong300", String.valueOf(buloong300));
                }else{
                    params.put("Buloong300", String.valueOf(0));
                }

                if(buloong400 !=0){
                    params.put("Buloong400", String.valueOf(buloong400));
                }else{
                    params.put("Buloong400", String.valueOf(0));
                }

                if(clamp !=0){
                    params.put("Clamp", String.valueOf(clamp));
                }else{
                    params.put("Clamp", String.valueOf(0));
                }

                if(poleu8 !=0){
                    params.put("Poleu8", String.valueOf(poleu8));
                }else{
                    params.put("Poleu8", String.valueOf(0));
                }

                if(ironpole6 !=0){
                    params.put("Ironpole6", String.valueOf(ironpole6));
                }else{
                    params.put("Ironpole6", String.valueOf(0));
                }

                if(sc_lc5 !=0){
                    params.put("Sc_lc5", String.valueOf(sc_lc5));
                }else{
                    params.put("Sc_lc5", String.valueOf(0));
                }

                if(sc_lc10 !=0){
                    params.put("Sc_lc10", String.valueOf(sc_lc10));
                }else{
                    params.put("Sc_lc10", String.valueOf(0));
                }

                if(sc_sc5 !=0){
                    params.put("Sc_sc5", String.valueOf(sc_sc5));
                }else{
                    params.put("Sc_sc5", String.valueOf(0));
                }
               return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}
