package com.example.root.godspeed;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    TextView heading;
    EditText userid;
    EditText pass;
    Button sbtBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        heading = (TextView) findViewById(R.id.heading);
        Typeface theCurvyFont = Typeface.createFromAsset(getAssets(),"fonts/GreatVibes-Regular.ttf");
        heading.setTypeface(theCurvyFont);

        userid = (EditText) findViewById(R.id.userid);
        pass = (EditText) findViewById(R.id.pass);
        sbtBtn = (Button) findViewById(R.id.sbtBtn);

        Typeface Raleway = Typeface.createFromAsset(getAssets(),"fonts/Raleway-Regular.ttf");
        userid.setTypeface(Raleway);
        pass.setTypeface(Raleway);
        sbtBtn.setTypeface(Raleway);


        sbtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = userid.getText().toString();
                String password = pass.getText().toString();

                Intent it = new Intent(getApplicationContext(),Landing.class);
                it.putExtra("username",username);
                it.putExtra("password",password);
                startActivity(it);
            }
        });
    }
}
