package com.example.actproperty.inventory;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
}