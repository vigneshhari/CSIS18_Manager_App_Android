package com.zentech.csis18manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.views.ButtonFloat;

public class Home extends AppCompatActivity {

    public static String BASE_URL = "http://csis18.herokuapp.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButtonFlat regsitermain = (ButtonFlat) findViewById(R.id.regmain);
        regsitermain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 =  new Intent(getApplicationContext() , EnterId.class);
                i1.putExtra("choice" , "csis");
                startActivity(i1);
            }
        });
        ButtonFlat regsitertrack = (ButtonFlat) findViewById(R.id.regtrack);
        regsitertrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 =  new Intent(getApplicationContext() , listtracks.class);
                i1.putExtra("choice" , "register");
                startActivity(i1);
            }
        });
        ButtonFlat deregistertrack = (ButtonFlat) findViewById(R.id.deregtrack);
        deregistertrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 =  new Intent(getApplicationContext() , listtracks.class);
                i1.putExtra("choice" , "deregister");
                startActivity(i1);
            }
        });
        ButtonFlat curtrack = (ButtonFlat) findViewById(R.id.viewcurtrack);
        curtrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 =  new Intent(getApplicationContext() , listtracks.class);
                i1.putExtra("choice" , "activedel");
                startActivity(i1);
            }
        });
        ButtonFlat mistrack = (ButtonFlat) findViewById(R.id.viewmissingtrack);
        mistrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 =  new Intent(getApplicationContext() , listtracks.class);
                i1.putExtra("choice" , "inactivedel");
                startActivity(i1);
            }
        });
        ButtonFlat atdet = (ButtonFlat) findViewById(R.id.viewattendeedet);
        atdet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 =  new Intent(getApplicationContext() , EnterId.class);
                i1.putExtra("choice" , "atdet");
                startActivity(i1);
            }
        });

    }
}
