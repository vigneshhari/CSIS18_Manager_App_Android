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

public class deldet extends AppCompatActivity {
    ArrayList<Det_Class> dataModels;
    ListView listView;
    private static Det_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deldet);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .build();
        AndroidNetworking.initialize(getApplicationContext(),okHttpClient);
        AndroidNetworking.setParserFactory(new JacksonParserFactory());
        listView=(ListView)findViewById(R.id.list2);
        Intent i1 = getIntent() ;

            AndroidNetworking.get((Home.BASE_URL + "/delegate/info"))
                    .addQueryParameter("csisid", i1.getStringExtra("id"))
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                TextView tv = (TextView) findViewById(R.id.name_det);
                                dataModels= new ArrayList<>();
                                tv.setText(response.getString("name"));
                                if(response.getString("statuscode").equals("100")){
                                    JSONArray data = response.getJSONArray("track");
                                    for (int i =0 ; i< data.length() ; i++){
                                        JSONObject temp = data.getJSONObject(i);
                                        dataModels.add(new Det_Class(temp.getString("name") , temp.getString("hour") ));

                                    }
                                    adapter= new Det_Adapter(dataModels,getApplicationContext());

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
