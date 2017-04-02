package com.example.root.godspeed;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;
import java.util.ArrayList;

public class Landing extends AppCompatActivity {

    Typeface roboto;
    Toolbar tb;
    UtilityFunctions ob = new UtilityFunctions(this);
    public final String THE_BASE_URL = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);


        tb = (Toolbar) findViewById(R.id.appToolBar);
        setSupportActionBar(tb);

        roboto = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Regular.ttf");
        //Setting typeface to welcome message
        TextView welmsg = (TextView) findViewById(R.id.welMsg);
        welmsg.setTypeface(roboto);

        //Rounded Image Implementation
        ImageView propic = (ImageView) findViewById(R.id.proPic);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.f1);
        RoundedImage roundedImage = new RoundedImage(bitmap);
        propic.setImageDrawable(roundedImage);
        LoadDataFromTheScraper task = new LoadDataFromTheScraper();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        getSupportActionBar().setHomeButtonEnabled(true);
        task.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_landing, menu);
        return super.onCreateOptionsMenu(menu);
    }



   private class LoadDataFromTheScraper extends AsyncTask<String,Void,ArrayList<Report>>{

        @Override
        protected ArrayList<Report> doInBackground(String... strings) {
            ArrayList<Report> report = new ArrayList<>();
            String jsonResponse = "{\n    \"THEORY OF COMPUTATION \": {\n        \" TUT\": {\n            \"total\": 1,\n            \"percentage\": 100.0,\n            \"present\": 1\n        },\n        \" TH\": {\n            \"total\": 27,\n            \"percentage\": 77.78,\n            \"present\": 21\n        }\n    },\n    \"DESIGN & ANALYSIS OF ALGORITHMS \": {\n        \" PR\": {\n            \"total\": 9,\n            \"percentage\": 100.0,\n            \"present\": 9\n        },\n        \" TUT\": {\n            \"total\": 6,\n            \"percentage\": 83.33,\n            \"present\": 5\n        },\n        \" TH\": {\n            \"total\": 31,\n            \"percentage\": 87.1,\n            \"present\": 27\n        }\n    },\n    \"ADVANCED JAVA \": {\n        \" PR\": {\n            \"total\": 9,\n            \"percentage\": 77.78,\n            \"present\": 7\n        },\n        \" TH\": {\n            \"total\": 33,\n            \"percentage\": 72.73,\n            \"present\": 24\n        }\n    },\n    \"COMPUTER NETWORKS-II \": {\n        \" PR\": {\n            \"total\": 11,\n            \"percentage\": 81.82,\n            \"present\": 9\n        },\n        \" TH\": {\n            \"total\": 37,\n            \"percentage\": 75.68,\n            \"present\": 28\n        }\n    },\n    \"VERDICT\": {\n        \"TUT\": {\n            \"total\": 1,\n            \"percentage\": 100.0,\n            \"present\": 1\n        },\n        \"Tutorial\": {\n            \"total\": 7,\n            \"percentage\": 85.71,\n            \"present\": 6\n        },\n        \"Total\": {\n            \"total\": 237,\n            \"percentage\": 78.48,\n            \"present\": 186\n        },\n        \"Theory\": {\n            \"total\": 182,\n            \"percentage\": 75.27,\n            \"present\": 137\n        },\n        \"Practical\": {\n            \"total\": 48,\n            \"percentage\": 89.58,\n            \"present\": 43\n        }\n    },\n    \"SDL-II MOBILE APPLICATION DEVELOPMENT LAB \": {\n        \" PR\": {\n            \"total\": 11,\n            \"percentage\": 90.91,\n            \"present\": 10\n        },\n        \" TH\": {\n            \"total\": 15,\n            \"percentage\": 53.33,\n            \"present\": 8\n        }\n    },\n    \"SOFTWARE TESTING & QUALITY ASSURANCE \": {\n        \" PR\": {\n            \"total\": 8,\n            \"percentage\": 100.0,\n            \"present\": 8\n        },\n        \" TH\": {\n            \"total\": 39,\n            \"percentage\": 74.36,\n            \"present\": 29\n        }\n    }\n}";
           /*
            URL url = UtilityFunctions.makeURL(THE_BASE_URL);
            try{
                jsonResponse = UtilityFunctions.makeHttpRequest(url);
            }catch (Exception ex){
                Toast.makeText(getApplicationContext(),"Some error occurred in doInBackground",Toast.LENGTH_LONG).show();
                return null;
            }*/
            report = UtilityFunctions.parseJson(jsonResponse);
            return report;
        }
       @Override
       protected void onPostExecute(ArrayList<Report> report) {
           super.onPostExecute(report);
           if(report == null) return ;
           updateUI(report);
       }

       public void updateUI(ArrayList<Report> report){
           String subTypeName = "";
           CardGenerator cardGenerator = new CardGenerator();
           Log.e("--UPDATEUI--"," "+report.size());
           cardGenerator.linearLayoutMain = (LinearLayout) findViewById(R.id.LLInner);



        for (int i = 0; i < report.size(); i++){
            if(report.get(i).subject.trim().equalsIgnoreCase("VERDICT")){
                subTypeName += "( "+report.get(i).theory.get(0) +"/" +report.get(i).theory.get(1) + " )\n";
                subTypeName += "   Theory";

                Log.d("--IN LANDING--"," "+report.get(i).theory.size() + " --> "+ i);
                cardGenerator.generateCard(report.get(i).subject,getApplicationContext());
                cardGenerator.textForType.setText(subTypeName);
                String textInCenter = "";
                textInCenter += report.get(i).theory.get(2).toString() + " %";
//                 Integer progress = (Integer) (report.get(i).theory.get(2));
                cardGenerator.progressBarFor.setProgress(60);
                cardGenerator.textInTheCenter.setText(textInCenter);

                textInCenter = "";
                subTypeName = "";
                subTypeName += "  ( "+report.get(i).practical.get(0) +"/" +report.get(i).practical.get(1) + " )\n";
                subTypeName += "  Practical";
                cardGenerator.textForTypePR.setText(subTypeName);
                textInCenter += report.get(i).practical.get(2).toString() + " %";
                //             progress = (Integer) report.get(i).practical.get(2);
                cardGenerator.progressBarForPR.setProgress(90);
                cardGenerator.textInTheCenterPR.setText(textInCenter);
                break;
            }
        }





           for(int i = 0; i < report.size(); i++) {
               if (!report.get(i).subject.equalsIgnoreCase("VERDICT")) {
                   if (report.get(i).theory.size() > 0 && report.get(i).practical.size() > 0) {
                       cardGenerator.generateCard(report.get(i).subject, getApplicationContext());
                       String textInCenter = "";
                       textInCenter += report.get(i).theory.get(2).toString() + " %";
//               Integer progress = (Integer) (report.get(i).theory.get(2));
                       cardGenerator.progressBarFor.setProgress(60);
                       cardGenerator.textInTheCenter.setText(textInCenter);
                       subTypeName = "";
                       subTypeName += "( "+report.get(i).theory.get(0) +"/" +report.get(i).theory.get(1) + " )\n";
                       subTypeName += "  Theory";
                       cardGenerator.textForType.setText(subTypeName);


                       textInCenter = "";
                       subTypeName = "";
                       subTypeName += "  ( "+report.get(i).practical.get(0) +"/" +report.get(i).practical.get(1) + " )\n";
                       subTypeName += " Practical";
                       cardGenerator.textForTypePR.setText(subTypeName);
                       textInCenter += report.get(i).practical.get(2).toString() + " %";
                       //             progress = (Integer) report.get(i).practical.get(2);
                       cardGenerator.progressBarForPR.setProgress(90);
                       cardGenerator.textInTheCenterPR.setText(textInCenter);
                   }else {
                       if(report.get(i).theory.size() > 0 && report.get(i).practical.size() == 0){
                           cardGenerator.generateCardForSingle(report.get(i).subject, getApplicationContext(),"th");
                           String textInCenter = "";
                           textInCenter += report.get(i).theory.get(2).toString() + " %";
//               Integer progress = (Integer) (report.get(i).theory.get(2));
                           cardGenerator.progressBarFor.setProgress(60);
                           cardGenerator.textInTheCenter.setText(textInCenter);
                           subTypeName = "";
                           subTypeName += "( "+report.get(i).theory.get(0) +"/" +report.get(i).theory.get(1) + " )\n";
                           subTypeName += "  Theory";
                           cardGenerator.textForType.setText(subTypeName);

                       }else{
                           cardGenerator.generateCardForSingle(report.get(i).subject, getApplicationContext(),"pr");
                           String textInCenter = "";
                           subTypeName = "";
                           subTypeName += "  ( "+report.get(i).practical.get(0) +"/" +report.get(i).practical.get(1) + " )\n";
                           subTypeName += " Practical";
                           cardGenerator.textForTypePR.setText(subTypeName);
                           textInCenter += report.get(i).practical.get(2).toString() + " %";
                           //             progress = (Integer) report.get(i).practical.get(2);
                           cardGenerator.progressBarForPR.setProgress(90);
                           cardGenerator.textInTheCenterPR.setText(textInCenter);
                           cardGenerator.textForType.setText("Theory");
                       }
                   }
               }
           }

       }
   }
}
