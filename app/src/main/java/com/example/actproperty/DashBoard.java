package com.example.actproperty;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.actproperty.inventory.DashboardInventory;
import com.example.actproperty.inventory.Inventory;
import com.example.actproperty.passport.Passport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DashBoard extends AppCompatActivity {
    Button btnCableId, btnInventory, btnAdmin;
    ImageButton btnImgCableId, imgBtnInventory;
    ArrayList<Passport> listUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        listUser = new ArrayList<>();
        getUser();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Hello "+listUser.get(0).getUser());
        btnAdmin = (Button)findViewById(R.id.btnAdmin);
        if(listUser.get(0).getAdmin()==1||listUser.get(0).getAdmin()==2){
            btnAdmin.setVisibility(View.VISIBLE);
        }
        btnCableId = (Button)findViewById(R.id.btnCableId);
        btnInventory = (Button)findViewById(R.id.btnInventory);
        imgBtnInventory = (ImageButton)findViewById(R.id.imgBtnInventory);
        btnImgCableId = (ImageButton)findViewById(R.id.imgBtnCableId);
        btnCableId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toManagement();
            }
        });
        btnImgCableId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toManagement();
            }
        });
        btnInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toInventory();
            }
        });
        imgBtnInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toInventory();
            }
        });
    }
    public void toManagement(){
        Intent intent = new Intent(DashBoard.this, Management.class);
        intent.putExtra("Account",listUser);
        startActivity(intent);
    }
    public void toInventory(){
        Intent intent = new Intent(DashBoard.this, DashboardInventory.class);
        intent.putExtra("Account",listUser);
        startActivity(intent);
    }

    public void getUser(){
        Intent intent = getIntent();
        listUser= (ArrayList<Passport>) intent.getSerializableExtra("Account");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
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
                ChangePassword();
                break;
            case R.id.signout:
                Toast.makeText(this, "Sign out", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void ChangePassword(){
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("Change your password!");
        dialog.setContentView(R.layout.change_password);
        final EditText currentPassword = (EditText) dialog.findViewById(R.id.currentPassword);
        final EditText newPassword = (EditText) dialog.findViewById(R.id.newPassword);
        final EditText confirmPassword = (EditText) dialog.findViewById(R.id.confirmPassword);
        Button btnChange = (Button) dialog.findViewById(R.id.btnChange);
        Button btnCancel = (Button)dialog.findViewById(R.id.btnCancel);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listUser.get(0).getPassword().equals(currentPassword.getText().toString())){
                    if(newPassword.getText().toString().equals(confirmPassword.getText().toString())){
                        currentPassword.setText("");
                        newPassword.setText("");
                        confirmPassword.setText("");
                        UpdatePassword("https://sqlandroid2812.000webhostapp.com/changepassword.php",
                                confirmPassword.getText().toString());
                        Toast.makeText(DashBoard.this, "Success! Your Password has been changed!",
                                Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                }
                else{
                    Toast.makeText(DashBoard.this, "Try again, check data input", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }
    private void UpdatePassword(String url, final String newPassword){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
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
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("ID",String.valueOf(listUser.get(0).getId()));
                params.put("NEWPASSWORD",newPassword);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void Admin(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.admin);
        final Button btnHistory = (Button)dialog.findViewById(R.id.btnHistory);
        final Button btnCR = (Button)dialog.findViewById(R.id.btnCR);
        final Button btnExitAdmin = (Button)dialog.findViewById(R.id.btnExitAdmin);

        dialog.show();
    }
}
