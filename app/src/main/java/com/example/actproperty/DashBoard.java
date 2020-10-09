package com.example.actproperty;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.actproperty.admin.Admin;
import com.example.actproperty.admin.DashboardAdmin;
import com.example.actproperty.inventory.DashboardInventory;
import com.example.actproperty.inventory.Inventory;
import com.example.actproperty.passport.Passport;
import com.example.actproperty.webact.WebACT;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class DashBoard extends AppCompatActivity {
    Button btnCableId, btnInventory, btnAdmin, btnReport, btnNotify;
    ArrayList<Passport> listUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        listUser = new ArrayList<>();
//        checkPermision(); cancel
        getUser();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Hello " + listUser.get(0).getUser());
        btnAdmin = (Button) findViewById(R.id.btnAdmin);
        btnAdmin.setVisibility(View.VISIBLE);

        btnCableId = (Button) findViewById(R.id.btnCableId);
        btnInventory = (Button) findViewById(R.id.btnInventory);
        btnNotify = (Button)findViewById(R.id.btnNotify);
        btnCableId.setOnClickListener(new View.OnClickListener() {
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
        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoard.this, DashboardAdmin.class);
                intent.putExtra("Account",listUser);
                startActivity(intent);
            }
        });
        btnReport = (Button)findViewById(R.id.btnReport);
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoard.this, WebACT.class);
                startActivity(intent);
            }
        });
    }

    public void toManagement() {
        Intent intent = new Intent(DashBoard.this, Management.class);
        intent.putExtra("Account", listUser);
        startActivity(intent);
    }

    public void toInventory() {
        Intent intent = new Intent(DashBoard.this, DashboardInventory.class);
        intent.putExtra("Account", listUser);
        startActivity(intent);
    }

    public void getUser() {
        Intent intent = getIntent();
        listUser = (ArrayList<Passport>) intent.getSerializableExtra("Account");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
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

    public void ChangePassword() {
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("Change your password!");
        dialog.setContentView(R.layout.change_password);
        final EditText currentPassword = (EditText) dialog.findViewById(R.id.currentPassword);
        final EditText newPassword = (EditText) dialog.findViewById(R.id.newPassword);
        final EditText confirmPassword = (EditText) dialog.findViewById(R.id.confirmPassword);
        Button btnChange = (Button) dialog.findViewById(R.id.btnChange);
        Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listUser.get(0).getPassword().equals(currentPassword.getText().toString())) {
                    if (newPassword.getText().toString().equals(confirmPassword.getText().toString())) {
                        currentPassword.setText("");
                        newPassword.setText("");
                        confirmPassword.setText("");
                        UpdatePassword("https://sqlandroid2812.000webhostapp.com/changepassword.php",
                                confirmPassword.getText().toString());
                        Toast.makeText(DashBoard.this, "Success! Your Password has been changed!",
                                Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                } else {
                    Toast.makeText(DashBoard.this, "Try again, check data input", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }

    private void UpdatePassword(String url, final String newPassword) {
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
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("ID", String.valueOf(listUser.get(0).getId()));
                params.put("NEWPASSWORD", newPassword);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(DashBoard.this, MainActivity.class);
        startActivity(intent);
    }
}

