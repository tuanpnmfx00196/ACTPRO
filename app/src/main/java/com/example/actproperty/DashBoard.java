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
import com.example.actproperty.inventory.DashboardInventory;
import com.example.actproperty.inventory.Inventory;
import com.example.actproperty.passport.Passport;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

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
    Button btnCableId, btnInventory, btnAdmin;
    ImageButton btnImgCableId, imgBtnInventory;
    ArrayList<Passport> listUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        listUser = new ArrayList<>();
        checkPermision();
        getUser();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Hello " + listUser.get(0).getUser());
        btnAdmin = (Button) findViewById(R.id.btnAdmin);
        if (listUser.get(0).getAdmin() == 1 || listUser.get(0).getAdmin() == 2) {
            btnAdmin.setVisibility(View.VISIBLE);
        }
        btnCableId = (Button) findViewById(R.id.btnCableId);
        btnInventory = (Button) findViewById(R.id.btnInventory);
        imgBtnInventory = (ImageButton) findViewById(R.id.imgBtnInventory);
        btnImgCableId = (ImageButton) findViewById(R.id.imgBtnCableId);
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
        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Admin();
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

    private void ChangePassword() {
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

    private void Admin() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.admin);
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.97);
        dialog.getWindow().setLayout(width, LinearLayout.LayoutParams.WRAP_CONTENT);
        final Button btnHistory = (Button) dialog.findViewById(R.id.btnHistory);
        final Button exportxls = (Button) dialog.findViewById(R.id.exportxls);
        final Button btnCR = (Button) dialog.findViewById(R.id.btnCR);
        final Button btnExitAdmin = (Button) dialog.findViewById(R.id.btnExitAdmin);
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoard.this, Admin.class);
                startActivity(intent);
            }
        });
        exportxls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFilexxls();
            }
        });
        dialog.show();
    }

    private void saveFilexxls() {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Sheet 1");
        HSSFRow rowA = sheet.createRow(0);
        HSSFCell cellA = rowA.createCell(0);
        cellA.setCellValue(new HSSFRichTextString("Sheet1"));
        FileOutputStream fos = null;
        try {
            String str_path = Environment.getExternalStorageDirectory().getAbsolutePath().toString();
            File file;
            file = new File(str_path, getString(R.string.app_name) + ".xls");
            fos = new FileOutputStream(file);
            workbook.write(fos);
            workbook.close();
            fos.close();
        } catch (IOException e) {
            Toast.makeText(this, "try 1" + e.toString(), Toast.LENGTH_SHORT).show();
        } finally {
            if (fos != null) {
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    Toast.makeText(this, "Finally" + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void checkPermision() {
        if (ContextCompat.checkSelfPermission(DashBoard.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(DashBoard.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }
    }
}
