package com.example.newpraktikone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class setProduct extends AppCompatActivity {

    EditText txtProductSet,txtPriceSet;
    Button btnSetData;
    TextView status;

    PreparedStatement stwt;
    Connection connection;
    Statement stmt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_product);
        Button btnGoToMain = (Button) findViewById(R.id.btnGoToMain);

        txtProductSet = (EditText)findViewById(R.id.txtProductSet);
        txtPriceSet = (EditText)findViewById(R.id.txtPriceSet);

        btnSetData = (Button)findViewById(R.id.btnSetData);
        status = (TextView)findViewById(R.id.status);

        btnSetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new setProduct.setDate().execute("");
            }
        });




        View.OnClickListener clckGoToMain = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(setProduct.this,MainActivity.class);
                startActivity(intent);
            }
        };
        btnGoToMain.setOnClickListener(clckGoToMain);

    }
    public class setDate extends AsyncTask<String, String,String>{

        String z = "";
        Boolean inSuccess = false;


        @Override
        protected void onPreExecute() {
            status.setText("Sending Data to Database");

        }

        @Override
        protected void onPostExecute(String s) {
            status.setText("Registration successuful");
            txtProductSet.setText("");
            txtPriceSet.setText("");
        }

        @Override
        protected String doInBackground(String... strings) {

            try{
                ConnectionHelper connectionHelper = new ConnectionHelper();
                connection = connectionHelper.connectionClass();
                if(connection == null){
                    z = "Check your Internet Connection";
                 }
                else{
                    String sql = "Insert into Products (Name_product,Price_product) values ('"+txtProductSet.getText()+"','"+txtPriceSet.getText()+"')";
                    stmt = connection.createStatement();
                    stmt.executeUpdate(sql);
                }
            }catch (Exception e){
                inSuccess = false;
                z = e.getMessage();

            }

             return z;
        }
    }


}