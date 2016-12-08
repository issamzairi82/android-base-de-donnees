package com.example.acer.osadd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ADD_OS extends AppCompatActivity {

    Button button ;
    Button button1 ;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__os);
        button =(Button)  findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                editText = (EditText) findViewById(R.id.editText);
                Intent intent = new Intent();
                intent.putExtra("item",editText.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

    }
}
