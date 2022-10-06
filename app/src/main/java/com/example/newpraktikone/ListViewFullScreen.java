package com.example.newpraktikone;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.LauncherActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

public class ListViewFullScreen extends AppCompatActivity {
    View v;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_full_screen);
        v = findViewById(com.google.android.material.R.id.ghost_view);
        GetList(v);
    }


    SimpleAdapter ad;
    public void  GetList(View v)
    {
        ListView listData = (ListView) findViewById(R.id.lvData);
        List<Map<String,String>> MyDataList = null;
        LauncherActivity.ListItem MyData = new LauncherActivity.ListItem();

        ListItem Mydata = new ListItem();
        MyDataList = Mydata.getList();


        String[] fromsql = {"Kod_id","Name_product","Price_product"};
        int[] Tow= {R.id.txtListID,R.id.txtListTitle,R.id.txtListCount};
        ad = new SimpleAdapter(ListViewFullScreen.this,MyDataList,R.layout.list_layout_template,fromsql,Tow);
        listData.setAdapter(ad);
    }
}