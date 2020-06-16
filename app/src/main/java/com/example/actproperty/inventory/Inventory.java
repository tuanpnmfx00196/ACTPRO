package com.example.actproperty.inventory;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.actproperty.R;
import com.example.actproperty.passport.Passport;

import java.util.ArrayList;

public class Inventory extends AppCompatActivity {
    ArrayList<Passport> listUser;
    TextView makho,inventory6fo, inventory12fo, inventory24fo, inventoryodf6, inventoryodf12,
                inventoryodf24, inventorymx6, inventorymx12, inventorymx24, inventorybl300,
                inventorybl400, inventoryclamp;
    LinearLayout row6fo_inventory, row12fo_inventory, row24fo_inventory, rowodf6fo_inventory,
            rowodf12fo_inventory, rowodf24fo_inventory, rowmx6fo_inventory, rowmx12fo_inventory,
            rowmx24fo_inventory, rowbl300_inventory, rowbl400_inventory, rowclamp_inventory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        listUser = new ArrayList<>();
        getAccount();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Hello "+listUser.get(0).getUser());
        Map();
       }
    public void getAccount(){
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
            case R.id.item1_inventory:
                Toast.makeText(this, "Item 1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item2_inventory:
                Toast.makeText(this, "Item 2", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void DataInventory(int in6fo, int in12fo, int in24fo, int inodf6, int inodf12,
                              int inodf24, int inmx6, int inmx12, int inmx24, int inbl300,
                              int inbl400, int inclamp){


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
    }
}