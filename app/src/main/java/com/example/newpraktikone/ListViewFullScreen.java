package com.example.newpraktikone;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ListViewFullScreen extends AppCompatActivity {
    View v;
    TextView txtListCount;
    TextView txtListID;
    TextView txtListTitle;
    EditText findName;
    String baz = "";
    Button btnFind;
    public String[] orderBy = {"По умолчанию","По имени продукта", "По цене продукта"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_view_full_screen);
        ArrayAdapter<String> orderByAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,orderBy);
        orderByAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(orderByAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = (String)adapterView.getItemAtPosition(i);

                GetList(v, item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                GetList(v,baz);
            }
        });
        v = findViewById(com.google.android.material.R.id.ghost_view);
        btnFind = (Button) findViewById(R.id.btnFind);
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetList(v,"Поиск");
            }
        });

    }


    SimpleAdapter ad;




    public void  GetList(View v,String str) {

        List<Map<String, String>> MyDataList = null;

        ListView listData = (ListView) findViewById(R.id.lvData);
        findName = (EditText) findViewById(R.id.findName);
        String text;
        if(str == "По имени продукта" )
        {
            ListItemOrderName Mydata = new ListItemOrderName();
            MyDataList = Mydata.getList();
        }
        else if (str == "По цене продукта" )
        {
            ListItemOrderPrice Mydata = new ListItemOrderPrice();
            MyDataList = Mydata.getList();
        }
        else if(str == "Поиск")
        {
            text =  findName.getText().toString();
            ListItemFind Mydata = new ListItemFind(text);
            MyDataList = Mydata.getList();
        }
       else {
            ListItem Mydata = new ListItem();
            MyDataList = Mydata.getList();
        }

        String[] fromsql = {"Kod_id", "Name_product", "Price_product"};
        int[] Tow = {R.id.txtListID, R.id.txtListTitle, R.id.txtListCount};
        ad = new SimpleAdapter(ListViewFullScreen.this, MyDataList, R.layout.list_layout_template, fromsql, Tow);
        listData.setAdapter(ad);
        listData.setClickable(true);
        listData.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                txtListID = view.findViewById(R.id.txtListID);
                String listId =txtListID.getText().toString();
                txtListCount = view.findViewById(R.id.txtListCount);
                String listCount = txtListCount.getText().toString();
                txtListTitle = view.findViewById(R.id.txtListTitle);
                String listTitle = txtListTitle.getText().toString();

                Intent intent = new Intent(ListViewFullScreen.this,setProduct.class);

                intent.putExtra("listId",listId);
                intent.putExtra("listCount",listCount);
                intent.putExtra("listTitle",listTitle);

                startActivity(intent);
            }
        });
    }


}