package com.zentech.csis18manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.jacksonandroidnetworking.JacksonParserFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.OkHttpClient;

public class listtracks extends AppCompatActivity {


    ArrayList<Track_Class> dataModels;
    ListView listView;
    private static Track_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listtracks);
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .build();
        AndroidNetworking.initialize(getApplicationContext(),okHttpClient);
        AndroidNetworking.setParserFactory(new JacksonParserFactory());
        listView=(ListView)findViewById(R.id.list);


        AndroidNetworking.get((Home.BASE_URL + "/delegate/listtracks"))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            dataModels= new ArrayList<>();

                            if(response.getString("statuscode").equals("100")){
                                JSONArray data = response.getJSONArray("data");
                                for (int i =0 ; i< data.length() ; i++){
                                    JSONObject temp = data.getJSONObject(i);
                                    dataModels.add(new Track_Class(temp.getString("name")));

                                }
                                adapter= new Track_Adapter(dataModels,getApplicationContext());

                                listView.setAdapter(adapter);
                            }
                            else{
                                Intent i = new Intent(getApplicationContext() , confirmpage.class);
                                i.putExtra("result" , "ERROR In Registering Delegate");
                                i.putExtra("name" ,"ERROR");
                                startActivity(i);
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        Log.e("ERR" , error.toString());

                    }
                });




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Track_Class dataModel= dataModels.get(position);
                Intent i1;
                Intent cur = getIntent();
                if(cur.getStringExtra("choice").equals("activedel") || cur.getStringExtra("choice").equals("inactivedel")  ){
                    i1 =  new Intent(getApplicationContext() , list_delegates.class);

                }
                else {
                    i1 =  new Intent(getApplicationContext() , EnterId.class);
                }
                i1.putExtra("choice", cur.getStringExtra("choice"));
                i1.putExtra("track", dataModel.name);
                startActivity(i1);
                finish();

            }
        });
    }

    }

