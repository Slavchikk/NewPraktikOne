package com.example.newpraktikone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class setProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_product);
        Button btnGoToMain = (Button) findViewById(R.id.btnGoToMain);


        View.OnClickListener clckGoToMain = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(setProduct.this,MainActivity.class);
                startActivity(intent);
            }
        };
        btnGoToMain.setOnClickListener(clckGoToMain);

    }
}