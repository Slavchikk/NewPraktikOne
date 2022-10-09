package com.example.newpraktikone;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainActivityPhoto extends AppCompatActivity {

    View v;
    Connection connection;
    List<Products> data;
    ListView listView;
    AdapterProduct pAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        getSupportActionBar().setCustomView(R.layout.toolbar_title_layout);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal_200)));
        setContentView(R.layout.activity_main_photo);
        v = findViewById(com.google.android.material.R.id.ghost_view);

        GetTextFromSQL(v);
    }
    public void enterMobile() {
        pAdapter.notifyDataSetInvalidated();
        listView.setAdapter(pAdapter);
    }
    public void GetTextFromSQL(View v) {
        data = new ArrayList<Products>();
        listView = findViewById(R.id.lvData);
        pAdapter = new AdapterProduct(MainActivityPhoto.this, data);
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connectionClass();
            if (connection != null) {
                String query = "Select * From Products";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    Products tempMask = new Products
                            (resultSet.getInt("Kod_id"),
                                    resultSet.getString("Name_product"),
                                    resultSet.getString("Price_Product"),
                                    resultSet.getString("Image")
                            );
                    data.add(tempMask);
                    pAdapter.notifyDataSetInvalidated();
                }
                connection.close();
            } else {
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        enterMobile();

    }
}