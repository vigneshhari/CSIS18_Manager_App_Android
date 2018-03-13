package com.zentech.csis18manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonFloat;

import org.w3c.dom.Text;

public class confirmpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmpage);

        Intent i = getIntent();
        String result = i.getStringExtra("result");
        String name = i.getStringExtra("name");
        TextView t1 = (TextView) findViewById(R.id.title_confirm);
        TextView t2 = (TextView) findViewById(R.id.name_confirm);
        t1.setText(result);
        t2.setText(name);
        ButtonFloat bf = (ButtonFloat) findViewById(R.id.bf_confirm);
        bf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });


    }
}
