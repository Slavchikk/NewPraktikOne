package com.example.newpraktikone;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
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

    EditText txtProductSet,txtPriceSet,txtIDSet;
    Button btnSetData;
    TextView status;
    Button btnInsertData;
    PreparedStatement stwt;
    Button btnDeleteData;
    Connection connection;
    Statement stmt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        getSupportActionBar().setCustomView(R.layout.toolbar_title_layout);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal_200)));
        setContentView(R.layout.activity_set_product);
        Button btnGoToMain = (Button) findViewById(R.id.btnGoToMain);

        txtProductSet = (EditText)findViewById(R.id.txtProductSet);
        txtPriceSet = (EditText)findViewById(R.id.txtPriceSet);
        txtIDSet = (EditText)findViewById(R.id.txtIDSet);
        btnSetData = (Button)findViewById(R.id.btnSetData);
        btnInsertData = (Button)findViewById(R.id.btnInsertData);
        btnDeleteData = (Button)findViewById(R.id.btnDeleteData);
        status = (TextView)findViewById(R.id.status);


        Intent intent = this.getIntent();
        if(intent!=null)
        {
            String listId = intent.getStringExtra("listId");
            String listCount = intent.getStringExtra("listCount");
            String listTitle = intent.getStringExtra("listTitle");
            txtProductSet.setText(listTitle);
            txtPriceSet.setText(listCount);
            txtIDSet.setText(listId);
        }
        btnDeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new setProduct.deleteDate().execute("");
            }
        });
        btnSetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new setProduct.setDate().execute("");
            }
        });

        btnInsertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { new setProduct.insertDate().execute("");
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
    public class deleteDate extends AsyncTask<String, String,String> {

        String z = "";
        Boolean inSuccess = false;


        @Override
        protected void onPreExecute() {
            status.setText("Отправка...");

        }

        @Override
        protected void onPostExecute(String s) {
            status.setText("Выполнено");
            txtIDSet.setText("");
            txtProductSet.setText("");
            txtPriceSet.setText("");
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                ConnectionHelper connectionHelper = new ConnectionHelper();
                connection = connectionHelper.connectionClass();
                if (connection == null) {
                    z = "Check your Internet Connection";
                } else {
                    String sql = "delete Products  where Kod_id = " + txtIDSet.getText() + ";";
                     stmt = connection.createStatement();
                    stmt.executeUpdate(sql);
                }
            } catch (Exception e) {
                inSuccess = false;
                z = e.getMessage();

            }

            return z;
        }
    }
    public class insertDate extends AsyncTask<String, String,String> {

        String z = "";
        Boolean inSuccess = false;


        @Override
        protected void onPreExecute() {
            status.setText("Отправка...");

        }

        @Override
        protected void onPostExecute(String s) {
            status.setText("Выполнено");
            txtIDSet.setText("");
            txtProductSet.setText("");
            txtPriceSet.setText("");
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                ConnectionHelper connectionHelper = new ConnectionHelper();
                connection = connectionHelper.connectionClass();
                if (connection == null) {
                    z = "Check your Internet Connection";
                } else {
                    String sql = "update Products set Name_product = '" + txtProductSet.getText() + "', Price_product = " + txtPriceSet.getText() + " where Kod_id = " + txtIDSet.getText() + ";";
                    //String sql = "Select into Products (Name_product,Price_product) values ('" + txtProductSet.getText() + "','" + txtPriceSet.getText() + "')";
                    stmt = connection.createStatement();
                    stmt.executeUpdate(sql);
                }
            } catch (Exception e) {
                inSuccess = false;
                z = e.getMessage();

            }

            return z;
        }
    }

        public class setDate extends AsyncTask<String, String, String> {

            String z = "";
            Boolean inSuccess = false;


            @Override
            protected void onPreExecute() {
                status.setText("Отправка...");

            }

            @Override
            protected void onPostExecute(String s) {
                status.setText("Выполнено");
                txtIDSet.setText("");
                txtProductSet.setText("");
                txtPriceSet.setText("");
            }

            @Override
            protected String doInBackground(String... strings) {

                try {
                    ConnectionHelper connectionHelper = new ConnectionHelper();
                    connection = connectionHelper.connectionClass();
                    if (connection == null) {
                        z = "Check your Internet Connection";
                    } else {
                        String sql = "Insert into Products (Kod_id,Name_product,Price_product) values ('" + txtIDSet.getText() + "', '" + txtProductSet.getText() + "','" + txtPriceSet.getText() + "')";
                        stmt = connection.createStatement();
                        stmt.executeUpdate(sql);
                    }
                } catch (Exception e) {
                    inSuccess = false;
                    z = e.getMessage();

                }

                return z;
            }
        }


    }