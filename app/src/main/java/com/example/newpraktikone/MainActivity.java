package com.example.newpraktikone;


import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.content.Intent;
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
        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_main);

        Button btnDisplayDate = (Button) findViewById(R.id.displayDate);
        Button btnGoToSetWindow = (Button) findViewById(R.id.btnGoToSetWindow);

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

       /* SimpleAdapter ad;
    public void  GetList(View v)
    {
        ListView listData = (ListView) findViewById(R.id.listData);
        List<Map<String,String>> MyDataList = null;
        LauncherActivity.ListItem MyData = new LauncherActivity.ListItem();

        ListItem Mydata = new ListItem();
        MyDataList = Mydata.getList();


        String[] fromsql = {"Kod_id","Name_product","Price_product"};
        int[] Tow= {R.id.txtListID,R.id.txtListTitle,R.id.txtListCount};
        ad = new SimpleAdapter(MainActivity.this,MyDataList,R.layout.list_layout_template,fromsql,Tow);
        listData.setAdapter(ad);
    }*/

   /* public void GetTextFrommSql(View v)
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

                    ID.setText( resultSet.getString(  1) );
                    Title.setText( resultSet.getString(  2));
                    Count.setText(  resultSet.getString(  3));

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

    }*/



}