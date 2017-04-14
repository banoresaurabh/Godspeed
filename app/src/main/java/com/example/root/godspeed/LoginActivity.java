package com.example.root.godspeed;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    TextView heading;
    EditText userid;
    EditText pass;
    Button sbtBtn;
    Intent it;
    DatabaseOperations dbOpr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbOpr = new DatabaseOperations(this);
        Cursor res = dbOpr.getCred();
        it = new Intent(getApplicationContext(),Landing.class);
        if(res.getCount() != 0) startActivity(it);

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

                    if(dbOpr.insert(username,password) == true) startActivity(it);
                    else{
                        Toast.makeText(getApplicationContext(),"Some internal error occurred please try again later",Toast.LENGTH_LONG).show();
                    }
            }
        });
    }
}
