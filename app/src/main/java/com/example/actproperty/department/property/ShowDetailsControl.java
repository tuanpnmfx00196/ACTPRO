package com.example.actproperty.department.property;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.actproperty.R;
import java.util.ArrayList;

public class ShowDetailsControl extends AppCompatActivity {
    ArrayList<ItemUsed>listItemUsed;
    TableLayout tblShow;
    TableRow rowTitle;
    TextView stt, CRcontrol, settlement_4fo, settlement_6fo, settlement_12fo, settlement_24fo,
            settlement_du12, settlement_odf6, settlement_odf12,settlement_odf24,
            settlement_mx6, settlement_mx12, settlement_mx24, settlement_bl300, settlement_bl400,
            settlement_clamp, settlement_sc_lc5, settlement_sc_lc10, settlement_sc_sc5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details_control);
        Map();
        listItemUsed = new ArrayList<>();
        getListItemUsed();
        CreateTable();
    }
    private void getListItemUsed(){
        Intent intent = getIntent();
        listItemUsed = (ArrayList<ItemUsed>) intent.getSerializableExtra("ListItemUsed");
    }
    private void Map(){
        tblShow = (TableLayout)findViewById(R.id.tblShow);
        rowTitle = (TableRow)findViewById(R.id.rowTitle);
        stt = (TextView)findViewById(R.id.stt);
        CRcontrol = (TextView)findViewById(R.id.CRcontrol);
        settlement_4fo = (TextView)findViewById(R.id.settlement_4fo);
        settlement_6fo = (TextView)findViewById(R.id.settlement_6fo);
        settlement_12fo = (TextView)findViewById(R.id.settlement_12fo);
        settlement_24fo = (TextView)findViewById(R.id.settlement_24fo);
        settlement_du12 = (TextView)findViewById(R.id.settlement_12du);
        settlement_odf6 = (TextView)findViewById(R.id.settlement_odf6);
        settlement_odf12 = (TextView)findViewById(R.id.settlement_odf12);
        settlement_odf24 = (TextView)findViewById(R.id.settlement_odf24);
        settlement_mx6 = (TextView)findViewById(R.id.settlement_mx6);
        settlement_mx12 = (TextView)findViewById(R.id.settlement_mx12);
        settlement_mx24 = (TextView)findViewById(R.id.settlement_mx24);
        settlement_bl300 = (TextView)findViewById(R.id.settlement_bl300);
        settlement_bl400 = (TextView)findViewById(R.id.settlement_bl400);
        settlement_clamp = (TextView)findViewById(R.id.settlement_clamp);
        settlement_sc_lc5 = (TextView)findViewById(R.id.settlement_sc_lc5);
        settlement_sc_lc10 = (TextView)findViewById(R.id.settlement_sc_lc10);
        settlement_sc_sc5 = (TextView)findViewById(R.id.settlement_sc_sc5);
    }
    private void CreateTable(){
        TableRow tr = new TableRow(this);
        int hanging4fo =0;
        int hanging6fo =0;
        int hanging12fo =0;
        int hanging24fo =0;
        int du12fo =0;
        int odf6fo =0;
        int odf12fo=0;
        int odf24fo=0;
        int mx6fo=0;
        int mx12fo=0;
        int mx24fo=0;
        int bl300=0;
        int bl400=0;
        int clamp=0;
        int sc_lc5=0;
        int sc_lc10=0;
        int sc_sc5=0;
        for(int i=0; i<listItemUsed.size();i++){
            hanging4fo +=listItemUsed.get(i).getHanging4fo();
            hanging6fo +=listItemUsed.get(i).getHanging6fo();
            hanging12fo +=listItemUsed.get(i).getHanging12fo();
            hanging24fo +=listItemUsed.get(i).getHanging24fo();
            du12fo +=listItemUsed.get(i).getDu12fo();
            odf6fo +=listItemUsed.get(i).getOdf6fo();
            odf12fo +=listItemUsed.get(i).getOdf12fo();
            odf24fo +=listItemUsed.get(i).getOdf24fo();
            mx6fo +=listItemUsed.get(i).getMx6fo();
            mx12fo+=listItemUsed.get(i).getMx12fo();
            mx24fo+=listItemUsed.get(i).getMx24fo();
            bl300+=listItemUsed.get(i).getBl300();
            bl400+=listItemUsed.get(i).getBl400();
            clamp+=listItemUsed.get(i).getClamp();
            sc_lc5+=listItemUsed.get(i).getSc_lc5();
            sc_lc10+=listItemUsed.get(i).getSc_lc10();
            sc_sc5+=listItemUsed.get(i).getSc_sc5();

            TextView tvStt = new TextView(this);
            tvStt.setText(""+i+1);
            tvStt.setGravity(Gravity.CENTER);
            tr.addView(tvStt);

            TextView tvCr = new TextView(this);
            tvCr.setText(listItemUsed.get(i).getCodecr());
            tvCr.setGravity(Gravity.CENTER);
            tr.addView(tvCr);

            TextView tv4fo = new TextView(this);
            if(listItemUsed.get(i).getHanging4fo()==0){
                tv4fo.setText("0");
                tv4fo.setVisibility(View.GONE);
                tr.addView(tv4fo);
            }else{
                tv4fo.setText(listItemUsed.get(i).getHanging4fo()+"");
                tv4fo.setGravity(Gravity.CENTER);
                tr.addView(tv4fo);
            }

            TextView tv6fo = new TextView(this);
            if(listItemUsed.get(i).getHanging6fo()==0){
                tv6fo.setText("0");
                tv6fo.setVisibility(View.GONE);
                tr.addView(tv6fo);
            }else{
                tv6fo.setText(listItemUsed.get(i).getHanging6fo()+"");
                tv6fo.setGravity(Gravity.CENTER);
                tr.addView(tv6fo);
            }

            TextView tv12fo = new TextView(this);
            if(listItemUsed.get(i).getHanging12fo()==0){
                tv12fo.setText("0");
                tv12fo.setVisibility(View.GONE);
                tr.addView(tv12fo);
            }else{
                tv12fo.setText(listItemUsed.get(i).getHanging12fo()+"");
                tv12fo.setGravity(Gravity.CENTER);
                tr.addView(tv12fo);
            }

            TextView tv24fo = new TextView(this);
            if(listItemUsed.get(i).getHanging24fo()==0){
                tv24fo.setText("0");
                tv24fo.setVisibility(View.GONE);
                tr.addView(tv24fo);
            }else{
                tv24fo.setText(listItemUsed.get(i).getHanging24fo()+"");
                tv24fo.setGravity(Gravity.CENTER);
                tr.addView(tv24fo);
            }

            TextView tv12du = new TextView(this);
            if(listItemUsed.get(i).getDu12fo()==0){
                tv12du.setText("0");
                tv12du.setVisibility(View.GONE);
                tr.addView(tv12du);
            }else{
                tv12du.setText(listItemUsed.get(i).getDu12fo()+"");
                tv12du.setGravity(Gravity.CENTER);
                tr.addView(tv12du);
            }

            TextView tvOdf6 = new TextView(this);
            if(listItemUsed.get(i).getOdf6fo()==0){
                tvOdf6.setText("0");
                tvOdf6.setVisibility(View.GONE);
                tr.addView(tvOdf6);
            }else{
                tvOdf6.setText(listItemUsed.get(i).getOdf6fo()+"");
                tvOdf6.setGravity(Gravity.CENTER);
                tr.addView(tvOdf6);
            }

            TextView tvOdf12 = new TextView(this);
            if(listItemUsed.get(i).getOdf12fo()==0){
                tvOdf12.setText("0");
                tvOdf12.setVisibility(View.GONE);
                tr.addView(tvOdf12);
            }else{
                tvOdf12.setText(listItemUsed.get(i).getOdf12fo()+"");
                tvOdf12.setGravity(Gravity.CENTER);
                tr.addView(tvOdf12);
            }

            TextView tvOdf24 = new TextView(this);
            if(listItemUsed.get(i).getOdf24fo()==0){
                tvOdf24.setText("0");
                tvOdf24.setVisibility(View.GONE);
                tr.addView(tvOdf24);
            }else{
                tvOdf24.setText(listItemUsed.get(i).getOdf24fo()+"");
                tvOdf24.setGravity(Gravity.CENTER);
                tr.addView(tvOdf24);
            }

            TextView tvMx6 = new TextView(this);
            if(listItemUsed.get(i).getMx6fo()==0){
                tvMx6.setText("0");
                tvMx6.setVisibility(View.GONE);
                tr.addView(tvMx6);
            }else{
                tvMx6.setText(listItemUsed.get(i).getMx6fo()+"");
                tvMx6.setGravity(Gravity.CENTER);
                tr.addView(tvMx6);
            }

            TextView tvMx12 = new TextView(this);
            if(listItemUsed.get(i).getMx12fo()==0){
                tvMx12.setText("0");
                tvMx12.setVisibility(View.GONE);
                tr.addView(tvMx12);
            }else{
                tvMx12.setText(listItemUsed.get(i).getMx12fo()+"");
                tvMx12.setGravity(Gravity.CENTER);
                tr.addView(tvMx12);
            }

            TextView tvMx24 = new TextView(this);
            if(listItemUsed.get(i).getMx24fo()==0){
                tvMx24.setText("0");
                tvMx6.setVisibility(View.GONE);
                tr.addView(tvMx24);
            }else{
                tvMx24.setText(listItemUsed.get(i).getMx24fo()+"");
                tvMx24.setGravity(Gravity.CENTER);
                tr.addView(tvMx24);
            }

            TextView tvBl300 = new TextView(this);
            if(listItemUsed.get(i).getBl300()==0){
                tvBl300.setText("0");
                tvBl300.setVisibility(View.GONE);
                tr.addView(tvBl300);
            }else{
                tvBl300.setText(listItemUsed.get(i).getBl300()+"");
                tvBl300.setGravity(Gravity.CENTER);
                tr.addView(tvBl300);
            }

            TextView tvBl400 = new TextView(this);
            if(listItemUsed.get(i).getBl400()==0){
                tvBl400.setText("0");
                tvBl400.setVisibility(View.GONE);
                tr.addView(tvBl400);
            }else{
                tvBl400.setText(listItemUsed.get(i).getBl400()+"");
                tvBl400.setGravity(Gravity.CENTER);
                tr.addView(tvBl400);
            }

            TextView tvClamp = new TextView(this);
            if(listItemUsed.get(i).getClamp()==0){
                tvClamp.setText("0");
                tvClamp.setVisibility(View.GONE);
                tr.addView(tvClamp);
            }else{
                tvClamp.setText(listItemUsed.get(i).getClamp()+"");
                tvClamp.setGravity(Gravity.CENTER);
                tr.addView(tvClamp);
            }

            TextView tvSc_lc5 = new TextView(this);
            if(listItemUsed.get(i).getSc_lc5()==0){
                tvSc_lc5.setText("0");
                tvSc_lc5.setVisibility(View.GONE);
                tr.addView(tvSc_lc5);
            }else{
                tvSc_lc5.setText(listItemUsed.get(i).getSc_lc5()+"");
                tvSc_lc5.setGravity(Gravity.CENTER);
                tr.addView(tvSc_lc5);
            }

            TextView tvSc_lc10 = new TextView(this);
            if(listItemUsed.get(i).getSc_lc10()==0){
                tvSc_lc10.setText("0");
                tvSc_lc10.setVisibility(View.GONE);
                tr.addView(tvSc_lc10);
            }else{
                tvSc_lc10.setText(listItemUsed.get(i).getSc_lc10()+"");
                tvSc_lc10.setGravity(Gravity.CENTER);
                tr.addView(tvSc_lc10);
            }

            TextView tvSc_sc5 = new TextView(this);
            if(listItemUsed.get(i).getSc_sc5()==0){
                tvSc_sc5.setText("0");
                tvSc_sc5.setVisibility(View.GONE);
                tr.addView(tvSc_sc5);
            }else{
                tvSc_sc5.setText(listItemUsed.get(i).getSc_sc5()+"");
                tvSc_sc5.setGravity(Gravity.CENTER);
                tr.addView(tvSc_sc5);
            }
           tblShow.addView(tr);
        }
        if(hanging4fo==0){
            settlement_4fo.setVisibility(View.GONE);
        }
        if(hanging6fo==0){
            settlement_6fo.setVisibility(View.GONE);
        }
        if(hanging12fo==0){
            settlement_12fo.setVisibility(View.GONE);
        }
        if(hanging24fo==0){
            settlement_24fo.setVisibility(View.GONE);
        }
        if(du12fo==0){
            settlement_du12.setVisibility(View.GONE);
        }
        if(odf6fo==0){
            settlement_odf6.setVisibility(View.GONE);
        }
        if(odf12fo==0){
            settlement_odf12.setVisibility(View.GONE);
        }
        if(odf24fo==0){
            settlement_odf24.setVisibility(View.GONE);
        }
        if(mx6fo==0){
            settlement_mx6.setVisibility(View.GONE);
        }
        if(mx12fo==0){
            settlement_mx12.setVisibility(View.GONE);
        }
        if(mx24fo==0){
            settlement_mx24.setVisibility(View.GONE);
        }
        if(bl300==0){
            settlement_bl300.setVisibility(View.GONE);
        }
        if(bl400==0){
            settlement_bl400.setVisibility(View.GONE);
        }
        if(clamp==0){
            settlement_clamp.setVisibility(View.GONE);
        }
        if(sc_lc5==0){
            settlement_sc_lc5.setVisibility(View.GONE);
        }
        if(sc_lc10==0){
            settlement_sc_lc10.setVisibility(View.GONE);
        }
        if(sc_sc5==0){
            settlement_sc_sc5.setVisibility(View.GONE);
        }

    }
}