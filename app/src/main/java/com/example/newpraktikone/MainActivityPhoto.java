package com.example.newpraktikone;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
    List<Mask> data;
    ListView listView;
    AdapterMask pAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        v = findViewById(com.google.android.material.R.id.ghost_view);

        GetTextFromSQL(v);
    }
    public void enterMobile() {
        pAdapter.notifyDataSetInvalidated();
        listView.setAdapter(pAdapter);
    }
    public void GetTextFromSQL(View v) {
        data = new ArrayList<Mask>();
        listView = findViewById(R.id.lvData);
        pAdapter = new AdapterMask(MainActivityPhoto.this, data);
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connectionClass();
            if (connection != null) {
                String query = "Select * From Products";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    Mask tempMask = new Mask
                            (resultSet.getInt("Kod_id"),
                                    resultSet.getString("Name_product"),
                                    Integer.parseInt(resultSet.getString("Price_Product")),
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