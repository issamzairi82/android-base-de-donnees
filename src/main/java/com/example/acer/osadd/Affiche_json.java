package com.example.acer.osadd;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Affiche_json extends AppCompatActivity {

    TextView Textviewops;
    TextView Textviewlogo;
    TextView TextviewDesc;
    TextView Textviewnbusers;
    TextView Textviewnbvers;
    TextView Textviewnbsmart;
    String id;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_json);




        RequestHandlerjson os = (RequestHandlerjson) getIntent().getExtras().get("ops");

        Textviewops=(TextView)findViewById(R.id.textViewops);
        TextviewDesc= (TextView) findViewById(R.id.textViewdesc);
        Textviewnbusers=(TextView)findViewById(R.id.textViewnbusers);
        Textviewnbvers=(TextView)findViewById(R.id.textViewnbvers);
        Textviewnbsmart=(TextView)findViewById(R.id.textViewnbsmart);

        Intent intent = getIntent();
        //getIntent().getExtras().get("os");
        id = (String) intent.getExtras().get(Configjson.KEY_OPS);
        RequestHandlerjson rh=new RequestHandlerjson();
        String res= rh.sendGetRequestParam(Configjson.URL_GET_ALL,id);

        try {
            JSONObject jsonObject = new JSONObject(res);
            JSONArray result = jsonObject.getJSONArray(Configjson.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String logo = c.getString(Configjson.KEY_LOGO);
            String desc = c.getString(Configjson.KEY_DESC);
            String users = c.getString(Configjson.KEY_NB_USERS);
            String version = c.getString(Configjson.KEY_NB_VERS);
            String smart = c.getString(Configjson.KEY_NB_SMART);


            Textviewops.setText(logo);
            TextviewDesc.setText(desc);
            Textviewnbusers.setText(users);
            Textviewnbvers.setText(version);
            Textviewnbsmart.setText(smart);

        } catch (JSONException e) {
            e.printStackTrace();
        }







        //getresp();
        //showInfo(id);
    }

    private void getresp(){
        class Getresp extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Affiche_json.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showInfo(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandlerjson rh = new RequestHandlerjson();
                 s = rh.sendGetRequestParam(Configjson.URL_GET_ALL,id);
                return s;
            }
        }
        Getresp ge = new Getresp();
        ge.execute();
    }

    private void showInfo(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Configjson.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String logo = c.getString(Configjson.KEY_LOGO);
            String desc = c.getString(Configjson.KEY_DESC);
            String users = c.getString(Configjson.KEY_NB_USERS);
            String version = c.getString(Configjson.KEY_NB_VERS);
            String smart = c.getString(Configjson.KEY_NB_SMART);


            Textviewops.setText(logo);
            TextviewDesc.setText(desc);
            Textviewnbusers.setText(users);
            Textviewnbvers.setText(version);
            Textviewnbsmart.setText(smart);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}
