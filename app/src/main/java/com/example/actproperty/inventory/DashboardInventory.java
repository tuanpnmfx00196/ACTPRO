package com.example.actproperty.inventory;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.actproperty.R;
import com.example.actproperty.passport.Passport;

import java.util.ArrayList;

public class DashboardInventory extends AppCompatActivity {
    Button btnBCH, btnBTN, btnCCI, btnTCH, btnGDH, btnDNI, btnLAN, btnBTE, btnTVH, btnKGG, btnBDG, btnACT;
    ArrayList<Passport>listUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_inventory);
        listUser = new ArrayList<>();
        getAccount();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Hello "+listUser.get(0).getUser());
        MapButtonDashInventory();
        checkPermision(listUser);
        btnACT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toInventory(listUser,"Kho ACT");
            }
        });
        btnBCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toInventory(listUser,"HCM_Bình Chánh");
            }
        });
        btnBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toInventory(listUser,"HCM_Bình Tân");
            }
        });
        btnCCI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toInventory(listUser,"HCM_Củ Chi");
            }
        });
        btnTCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toInventory(listUser,"HCM_Hóc Môn");
            }
        });
        btnGDH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toInventory(listUser,"HCM_Gò Vấp");
            }
        });
        btnBDG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toInventory(listUser,"Bình Dương");
            }
        });
        btnKGG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toInventory(listUser,"Kiên Giang");
            }
        });
        btnBTE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toInventory(listUser,"Bến Tre");
            }
        });
        btnTVH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toInventory(listUser,"Trà Vinh");
            }
        });
        btnDNI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toInventory(listUser,"Đồng Nai");
            }
        });
        btnLAN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toInventory(listUser,"Long An");
            }
        });
    }
    private void MapButtonDashInventory(){
        btnACT = (Button)findViewById(R.id.btnACT);
        btnBTN = (Button)findViewById(R.id.btnBTN);
        btnBCH = (Button)findViewById(R.id.btnBCH);
        btnCCI = (Button)findViewById(R.id.btnCCI);
        btnGDH = (Button)findViewById(R.id.btnGDH);
        btnTCH = (Button)findViewById(R.id.btnTCH);
        btnBDG = (Button)findViewById(R.id.btnBDG);
        btnDNI = (Button)findViewById(R.id.btnDNI);
        btnLAN = (Button)findViewById(R.id.btnLAN);
        btnBTE = (Button)findViewById(R.id.btnBTE);
        btnTVH = (Button)findViewById(R.id.btnTVH);
        btnKGG = (Button)findViewById(R.id.btnKGG);
    }
    private void getAccount(){
        Intent intent = getIntent();
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
            case R.id.changePassword:
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
    private void checkPermision(ArrayList<Passport> listUser){
        if(listUser.get(0).getAdmin()==1|| listUser.get(0).getAdmin()==2){
            btnACT.setVisibility(View.VISIBLE);
            btnBCH.setVisibility(View.VISIBLE);
            btnBTN.setVisibility(View.VISIBLE);
            btnCCI.setVisibility(View.VISIBLE);
            btnTCH.setVisibility(View.VISIBLE);
            btnGDH.setVisibility(View.VISIBLE);
            btnLAN.setVisibility(View.VISIBLE);
            btnDNI.setVisibility(View.VISIBLE);
            btnBDG.setVisibility(View.VISIBLE);
            btnKGG.setVisibility(View.VISIBLE);
            btnBTE.setVisibility(View.VISIBLE);
            btnTVH.setVisibility(View.VISIBLE);
        } else{
            if(listUser.get(0).getBdg()==1||listUser.get(0).getBdg()==2){
                btnBDG.setVisibility(View.VISIBLE);
            }
            if(listUser.get(0).getBte()==1||listUser.get(0).getBte()==2){
                btnBTE.setVisibility(View.VISIBLE);
            }
            if(listUser.get(0).getKgg()==1||listUser.get(0).getKgg()==2){
                btnKGG.setVisibility(View.VISIBLE);
            }
            if(listUser.get(0).getTvh()==1||listUser.get(0).getTvh()==2){
                btnTVH.setVisibility(View.VISIBLE);
            }
            if(listUser.get(0).getLan()==1||listUser.get(0).getLan()==2){
                btnLAN.setVisibility(View.VISIBLE);
            }
            if(listUser.get(0).getDni()==1||listUser.get(0).getDni()==2){
                btnDNI.setVisibility(View.VISIBLE);
            }
            if(listUser.get(0).getHcm_bch()==1||listUser.get(0).getHcm_bch()==2){
                btnBCH.setVisibility(View.VISIBLE);
            }
            if(listUser.get(0).getHcm_btn()==1||listUser.get(0).getHcm_btn()==2){
                btnBTN.setVisibility(View.VISIBLE);
            }
            if(listUser.get(0).getHcm_cci()==1||listUser.get(0).getHcm_cci()==2){
                btnCCI.setVisibility(View.VISIBLE);
            }
            if(listUser.get(0).getHcm_hmn()==1||listUser.get(0).getHcm_hmn()==2){
                btnTCH.setVisibility(View.VISIBLE);
            }
            if(listUser.get(0).getHcm_q12()==1||listUser.get(0).getHcm_q12()==2){
                btnTCH.setVisibility(View.VISIBLE);
            }
            if(listUser.get(0).getHcm_gvp()==1||listUser.get(0).getHcm_gvp()==2){
                btnGDH.setVisibility(View.VISIBLE);
            }

        }
    }
    private void toInventory(ArrayList<Passport>listUser, String storecode){
        Intent intent = new Intent(DashboardInventory.this, Inventory.class);
        intent.putExtra("Account",listUser);
        intent.putExtra("Storecode",storecode);
        startActivity(intent);
    }
}