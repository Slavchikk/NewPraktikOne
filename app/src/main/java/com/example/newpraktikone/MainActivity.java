package com.example.newpraktikone;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;

import android.app.LauncherActivity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;
import java.sql.Connection;

public class MainActivity extends AppCompatActivity  {

    Connection connection;
    String ConnectionResult = "";

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        getSupportActionBar().setCustomView(R.layout.toolbar_title_layout);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal_200)));
        setContentView(R.layout.activity_main);

        Button btnDisplayDate = (Button) findViewById(R.id.displayDate);
        Button btnGoToSetWindow = (Button) findViewById(R.id.btnGoToSetWindow);
        Button btnDisplayPhoto = (Button) findViewById(R.id.btnDisplayPhoto);
        btnDisplayPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this,MainActivityPhoto.class);
                startActivity(intent2);
            }
        });
        btnDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ListViewFullScreen viewList = new ListViewFullScreen();
                Intent intent1 = new Intent(MainActivity.this,ListViewFullScreen.class);
                startActivity(intent1);

            }
        });

        View.OnClickListener clckGoToSetWindow = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,setProduct.class);
                startActivity(intent);
            }
        };



        btnGoToSetWindow.setOnClickListener(clckGoToSetWindow);

    }




}