package com.example.actproperty.department.accounting;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.actproperty.R;
import com.example.actproperty.itemclick_Interface.OnItemClickRecyclerView;

import java.util.ArrayList;

import static com.example.actproperty.R.layout.dialog_detail_deliver;

public class ShowDeliverHistory extends AppCompatActivity implements OnItemClickRecyclerView {
    TextView statusDateTime;
    RecyclerView listViewDeliverHistory;
    ArrayList<DeliverObject> listDeliverShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_deliver_history);
        listDeliverShow = new ArrayList<>();
        Map();
        getListDeliverShow();
        initView();
    }
    private void Map(){
        statusDateTime = (TextView)findViewById(R.id.statusDateTime);
        listViewDeliverHistory = (RecyclerView)findViewById(R.id.listViewDeliverHistory);
    }
    public void initView(){
        listViewDeliverHistory.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        listViewDeliverHistory.setLayoutManager(linearLayoutManager);
        DeliverHistoryAdapter adapter = new DeliverHistoryAdapter(listDeliverShow,getApplicationContext(),this);
        listViewDeliverHistory.setAdapter(adapter);
    }
    private void getListDeliverShow(){
        Intent intent = getIntent();
        listDeliverShow = (ArrayList<DeliverObject>) intent.getSerializableExtra("listDeliverShow");
        statusDateTime.setText("Từ ngày: "+intent.getStringExtra("dateFrom")+" đến ngày: "+
                intent.getStringExtra("dateTo"));
    }

    @Override
    public void onClick(int position) {
        ShowDetailDeliver(position);
    }

    @Override
    public void onClick1(int position) {

    }

    @Override
    public void onClick2(int position) {

    }

    @Override
    public void onClick3(int position) {

    }

    private void ShowDetailDeliver(int position){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(dialog_detail_deliver);
        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.97);
        dialog.getWindow().setLayout(width, LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView title_Code = (TextView)dialog.findViewById(R.id.title_Code);
        TextView details = (TextView)dialog.findViewById(R.id.details);
        Button btnCancelDialogDetailDeliver = (Button)dialog.findViewById(R.id.btnCancelDialogDetailDeliver);
        title_Code.setText("PXK_TCKT_"+listDeliverShow.get(position).getTimedeliver());
        String details_string ="";
                if(listDeliverShow.get(position).getHanging6fo()!=0){
                    details_string += "Cáp quang treo 6fo: "+listDeliverShow.get(position).getHanging6fo()+"\n";
                }
                if(listDeliverShow.get(position).getHanging12fo()!=0){
                details_string += "Cáp quang treo 121fo: "+listDeliverShow.get(position).getHanging12fo()+"\n";
                }
                if(listDeliverShow.get(position).getHanging24fo()!=0){
                    details_string += "Cáp quang treo 24fo: "+listDeliverShow.get(position).getHanging24fo()+"\n";
                }
                if(listDeliverShow.get(position).getOdf6fo()!=0){
                    details_string += "ODF 6fo: "+listDeliverShow.get(position).getOdf6fo()+"\n";
                }
                if(listDeliverShow.get(position).getOdf12fo()!=0){
                    details_string += "ODF 12fo: "+listDeliverShow.get(position).getOdf12fo()+"\n";
                }
                if(listDeliverShow.get(position).getOdf24fo()!=0){
                    details_string += "ODF 24fo: "+listDeliverShow.get(position).getOdf24fo()+"\n";
                }
                if(listDeliverShow.get(position).getMx6fo()!=0){
                    details_string += "Măng xông 6fo: "+listDeliverShow.get(position).getMx6fo()+"\n";
                }
                if(listDeliverShow.get(position).getMx12fo()!=0){
                    details_string += "Măng xông 12fo: "+listDeliverShow.get(position).getMx12fo()+"\n";
                }
                if(listDeliverShow.get(position).getMx24fo()!=0){
                    details_string += "Măng xông 24fo: "+listDeliverShow.get(position).getMx24fo()+"\n";
                }
                if(listDeliverShow.get(position).getBl300()!=0){
                    details_string += "Buloong ti 300: "+listDeliverShow.get(position).getBl300()+"\n";
                }
                if(listDeliverShow.get(position).getBl400()!=0){
                    details_string += "Buloong ti 400: "+listDeliverShow.get(position).getBl400()+"\n";
                }
                if(listDeliverShow.get(position).getClamp()!=0){
                    details_string += "Kẹp 2 rãnh 3 lỗ: "+listDeliverShow.get(position).getClamp()+"\n";
                }
                if(listDeliverShow.get(position).getSc_lc5()!=0){
                    details_string += "Dây nhảy quang SC/LC 5m: "+listDeliverShow.get(position).getSc_lc5()+"\n";
                }
                if(listDeliverShow.get(position).getSc_lc10()!=0){
                    details_string += "Dây nhảy quang SC/LC 10m: "+listDeliverShow.get(position).getSc_lc10()+"\n";
                }
         details.setText(details_string);
         btnCancelDialogDetailDeliver.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 dialog.cancel();
             }
         });
    dialog.show();
    }

}