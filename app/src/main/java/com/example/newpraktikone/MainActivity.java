package com.example.newpraktikone;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity  {

    Connection connection;
    String ConnectionResult = "";

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnDisplayDate = (Button) findViewById(R.id.displayDate);
        Button btnGoToSetWindow = (Button) findViewById(R.id.btnGoToSetWindow);

        btnDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetTextFrommSql(v);
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

    public void GetTextFrommSql(View v)
    {

        TextView ID = findViewById(R.id.txtID);
        TextView Title = findViewById(R.id.txtTitle);
        TextView Count = findViewById(R.id.txtCount);


        try {

            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connectionClass();

            if (connection!=null)
            {
                String query = "SELECT * FROM Products";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);


                while (resultSet.next())
                {

                    ID.setText(ID + resultSet.getString(  1) + "\n");
                    Title.setText(Title + resultSet.getString(  2)+ "\n");
                    Count.setText( Count + resultSet.getString(  3)+ "\n");

                }
                connection.close();
            }
            else
            {
                ConnectionResult="Check Connection";
            }

        }
        catch (Exception ex)
        {

            Log.e("Error: " , ex.getMessage());

       }

    }



}