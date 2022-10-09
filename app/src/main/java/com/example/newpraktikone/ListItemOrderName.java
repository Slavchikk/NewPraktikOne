package com.example.newpraktikone;

import com.example.newpraktikone.ConnectionHelper;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListItemOrderName {
    Connection connect;
    String ConnectionResult="";
    Boolean isSuccess = false;

    public List<Map<String,String>>getList()
    {

        List<Map<String,String>> data = null;
        data = new ArrayList<Map<String,String>>();

        try
        {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connectionClass();
            if(connect!=null)
            {
                String qu = "select *from products order by Name_product";
                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(qu);
                while(resultSet.next())
                {
                    Map<String,String> dtname = new HashMap<String,String>();
                    dtname.put("Kod_id",resultSet.getString("Kod_id"));
                    dtname.put("Name_product",resultSet.getString("Name_product"));
                    dtname.put("Price_product",resultSet.getString("Price_product"));
                    data.add(dtname);
                }
                ConnectionResult = "Success";
                isSuccess = true;
                connect.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  data;
    }
}
