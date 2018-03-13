package com.zentech.csis18manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.gc.materialdesign.views.Button;
import com.gc.materialdesign.views.ButtonFloat;
import com.jacksonandroidnetworking.JacksonParserFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;


public class EnterId extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_id);
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .build();
        AndroidNetworking.initialize(getApplicationContext(),okHttpClient);
        AndroidNetworking.setParserFactory(new JacksonParserFactory());

        ButtonFloat bf = (ButtonFloat) findViewById(R.id.bf_id);
        bf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent i1 = getIntent();
                String choice = i1.getStringExtra("choice");
                EditText ed = (EditText) findViewById(R.id.id_edit);
                if(choice.equals("csis")){

                    AndroidNetworking.get((Home.BASE_URL + "/delegate/regsitermain"))
                            .addQueryParameter("csisid", ed.getText().toString())
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        if(response.getString("statuscode").equals("100")){
                                            Log.e("ERR" , "Data Acquired");
                                            Intent i = new Intent(getApplicationContext() , confirmpage.class);
                                            i.putExtra("result" , "Successfully Registered Delegate");
                                            i.putExtra("name" , response.getString("name"));
                                            startActivity(i);
                                        }
                                        else{
                                            Intent i = new Intent(getApplicationContext() , confirmpage.class);
                                            i.putExtra("result" , "ERROR In Registering Delegate");
                                            i.putExtra("name" ,"ERROR");
                                            startActivity(i);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                @Override
                                public void onError(ANError error) {
                                    Log.e("ERR" , error.toString());
                                    Intent i = new Intent(getApplicationContext() , confirmpage.class);
                                    i.putExtra("result" , "ERROR In Registering Delegate");
                                    i.putExtra("name" ,"ERROR");
                                    startActivity(i);
                                }
                            });



                }
                else if (choice.equals("register")){
                    AndroidNetworking.get((Home.BASE_URL + "/delegate/registertrack"))
                            .addQueryParameter("csisid", ed.getText().toString())
                            .addQueryParameter("track", i1.getStringExtra("track"))
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        if(response.getString("statuscode").equals("100")){
                                            Log.e("ERR" , "Data Acquired");
                                            Intent i = new Intent(getApplicationContext() , confirmpage.class);
                                            i.putExtra("result" , "Successfully Registered for " + i1.getStringExtra("track"));
                                            i.putExtra("name" , response.getString("name"));
                                            startActivity(i);
                                        }
                                        else{
                                            Intent i = new Intent(getApplicationContext() , confirmpage.class);
                                            i.putExtra("result" , "ERROR In Registering Delegate");
                                            i.putExtra("name" ,"ERROR");
                                            startActivity(i);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                @Override
                                public void onError(ANError error) {
                                    Log.e("ERR" , error.toString());
                                    Intent i = new Intent(getApplicationContext() , confirmpage.class);
                                    i.putExtra("result" , "ERROR In Registering Delegate");
                                    i.putExtra("name" ,"ERROR");
                                    startActivity(i);
                                }
                            });



                }
                else if (choice.equals("deregister")) {

                    AndroidNetworking.get((Home.BASE_URL + "/delegate/deregister"))
                            .addQueryParameter("csisid", ed.getText().toString())
                            .addQueryParameter("track", i1.getStringExtra("track"))
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        if(response.getString("statuscode").equals("100")){
                                            Log.e("ERR" , "Data Acquired");
                                            Intent i = new Intent(getApplicationContext() , confirmpage.class);
                                            i.putExtra("result" , "Successfully DE - Registered for " + i1.getStringExtra("track"));
                                            i.putExtra("name" , response.getString("name"));
                                            startActivity(i);

                                        }
                                        else{
                                            Intent i = new Intent(getApplicationContext() , confirmpage.class);
                                            i.putExtra("result" , "ERROR In Registering Delegate");
                                            i.putExtra("name" ,"ERROR");
                                            startActivity(i);

                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                @Override
                                public void onError(ANError error) {
                                    Log.e("ERR" , error.toString());
                                    Intent i = new Intent(getApplicationContext() , confirmpage.class);
                                    i.putExtra("result" , "ERROR In Registering Delegate");
                                    i.putExtra("name" ,"ERROR");
                                    startActivity(i);
                                }
                            });

                }
                else if (choice.equals("atdet")) {
                    Intent i = new Intent(getApplicationContext() , deldet.class);
                    i.putExtra("id" , ed.getText());
                    startActivity(i);
                    finish();
                }



                }
        });


    }

}
