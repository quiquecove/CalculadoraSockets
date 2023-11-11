package com.example.calculadorasocketandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void siguiente1(View view) {
        et = findViewById(R.id.etip);
        Intent i = new Intent(this, MainActivity2.class);
        i.putExtra("ip", String.valueOf(et.getText()));
        startActivity(i);
    }
}