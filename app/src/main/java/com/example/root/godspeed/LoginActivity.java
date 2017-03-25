package com.example.root.godspeed;

import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    TextView heading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        heading = (TextView) findViewById(R.id.heading);
        Typeface theCurvyFont = Typeface.createFromAsset(getAssets(),"fonts/GreatVibes-Regular.ttf");
        heading.setTypeface(theCurvyFont);

        EditText userid = (EditText) findViewById(R.id.userid);
        EditText editText = (EditText) findViewById(R.id.editText);
        Button sbtBtn = (Button) findViewById(R.id.sbtBtn);

        Typeface Raleway = Typeface.createFromAsset(getAssets(),"fonts/Raleway-Regular.ttf");
        userid.setTypeface(Raleway);
        editText.setTypeface(Raleway);
        sbtBtn.setTypeface(Raleway);
    }
}
