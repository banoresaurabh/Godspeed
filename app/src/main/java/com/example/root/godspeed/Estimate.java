package com.example.root.godspeed;

import android.graphics.Typeface;
import android.icu.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Estimate extends AppCompatActivity {
    Button btn;
    TextView lectures;
    Typeface raleway;
    TextView preamble;
    TextView res;
    TextView supp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimate);
        btn = (Button) findViewById(R.id.goBtn);
        lectures = (TextView) findViewById(R.id.lectures);
        res = (TextView) findViewById(R.id.res);
        preamble = (TextView) findViewById(R.id.preamble);
        raleway = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Regular.ttf");
        res.setTypeface(raleway);
        preamble.setTypeface(raleway);
        supp= (TextView) findViewById(R.id.supp);
        supp.setTypeface(raleway);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    int x;
                    int lect = Integer.parseInt(lectures.getText().toString());
                    x = getIntent().getIntExtra("theoryLectures",0);
                    int uske = getIntent().getIntExtra("pottaTotal",0);
                    uske -= lect;


                    x = 246;
                    uske = 197 - lect;
                    if(lect < x){
                        double ans = (double) uske/ x * 100;
                        res.setText((Math.round(ans * 100.0)/100.0)+" %");
                        supp.setText("is the approximate attendance you'll be having after bunking "+lect+" lectures.");


                    }else {
                        supp.setText("Please enter a valid number of lectures!!");
                    }

                }catch (Exception ex){
                    Log.d("--in Estimate--","Ener valid number");
                }
            }
        });
    }
}
