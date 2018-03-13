package com.zentech.csis18manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.jacksonandroidnetworking.JacksonParserFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

import okhttp3.OkHttpClient;

public class list_delegates extends AppCompatActivity {
    ArrayList<Track_Class> dataModels;
    ListView listView;
    private static Track_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_delegates);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .build();
        AndroidNetworking.initialize(getApplicationContext(),okHttpClient);
        AndroidNetworking.setParserFactory(new JacksonParserFactory());
        listView=(ListView)findViewById(R.id.list1);
        Intent i1 = getIntent() ;
        if(i1.getStringExtra("choice").equals("activedel")){
        AndroidNetworking.get((Home.BASE_URL + "/delegate/listattendtrack"))
                .addQueryParameter("track", i1.getStringExtra("track"))
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
        }
                else{
            TextView tv = (TextView) findViewById(R.id.t_w_l);
            tv.setText("Inactive Delegates");
            AndroidNetworking.get((Home.BASE_URL + "/delegate/listmissingtrack"))
                    .addQueryParameter("track", i1.getStringExtra("track"))
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

        }


    }
}
