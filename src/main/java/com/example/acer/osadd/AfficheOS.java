package com.example.acer.osadd;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class AfficheOS extends AppCompatActivity {

    TextView ops1;
    ImageView logo1;
    TextView descrip;
    TextView nombre_user;
    TextView nombre_version;
    TextView nombre_smart;
    Fichier_info os;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_os);

        os = (Fichier_info) getIntent().getExtras().get("os");
        ops1=(TextView) findViewById(R.id.textView);
        ops1.setText(os.getOps().toString());


        descrip = (TextView) findViewById(R.id.textView9);
        descrip.setText(os.getDescription());

        nombre_user= (TextView) findViewById(R.id.textView4);
        nombre_user.setText(" "+os.getNombre_user());

        nombre_version = (TextView) findViewById(R.id.textView6);
        nombre_version.setText(" "+os.getNombre_version());

        nombre_smart = (TextView) findViewById(R.id.textView8);
        nombre_smart.setText(" "+os.getNombre_smart());
    }
}
