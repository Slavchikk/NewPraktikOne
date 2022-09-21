package com.example.newpraktikone;
import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import  java.sql.DriverManager;
import java.sql.Connection;
public class ConnectionHelper {
    String userName, userPassword, ip, port, dateBase;

    @SuppressLint("NewApi")
    public Connection connectionClass()
    {
        ip = "ngknn.ru";
        dateBase = "41PKarzinov";
        userPassword = "12357";
        userName = "31П";
        port = "1433";


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String connectionURL = null;



        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connectionURL = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";" + "databasename=" + dateBase + ";user=" + userName + ";" +  "password=" + userPassword + ";";
            connection = DriverManager.getConnection(connectionURL);
        }
        catch (Exception ex)
        {
            Log.e("Error", ex.getMessage());
        }
        return connection;
    }
}