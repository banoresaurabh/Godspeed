package com.example.root.godspeed;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class Landing extends AppCompatActivity {

    Typeface roboto;
    FrameLayout fl;
    ProgressBar pb;
    TextView errorText;
    RelativeLayout rlInclude;
    String jsonResponse ;

    static int pTotal = 0;
    Toolbar tb;
    UtilityFunctions ob = new UtilityFunctions(this);
    public String THE_BASE_URL = "http://ca27cfd0.ngrok.io/attendance/?";
    DatabaseOperations db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        errorText = (TextView) findViewById(R.id.errorText);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        db = new DatabaseOperations(this);
        //Retrieving creds
        Cursor res = db.getCred();
        pb=(ProgressBar) findViewById(R.id.pb);

        String username = "";
        String password = "";

        while (res.moveToNext()){
            username = res.getString(1);
            password = res.getString(2);
        }

        Toast.makeText(this,""+username+" "+password,Toast.LENGTH_LONG).show();
        fl = (FrameLayout) findViewById(R.id.fl);

        //Again Toolbar
        View v = inflater.inflate(R.layout.info_alert_dialog,null);
        TextView link = (TextView) v.findViewById(R.id.repo);
        link.setMovementMethod(LinkMovementMethod.getInstance());
        tb = (Toolbar) findViewById(R.id.appToolBar);
        setSupportActionBar(tb);

        roboto = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        //Setting typeface to welcome message
        TextView welmsg = (TextView) findViewById(R.id.welMsg);
        welmsg.setTypeface(roboto);

        //Rounded Image Implementation
        ImageView propic = (ImageView) findViewById(R.id.proPic);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.f1);
        RoundedImage roundedImage = new RoundedImage(bitmap);
        propic.setImageDrawable(roundedImage);
        LoadDataFromTheScraper task = new LoadDataFromTheScraper();



        //Toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher);
        getSupportActionBar().setHomeButtonEnabled(true);
        if(isNetworkAvailable())  task.execute(username,password);
        else{
            errorText.setVisibility(View.VISIBLE);
            errorText.setText("Error while connecting to server.Please Check your internet Connection and try again.");
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_info){
            Log.d("--YOOO--","Reached here");

            AlertDialog.Builder alert_d= new AlertDialog.Builder(Landing.this);
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.info_alert_dialog,null);
            alert_d.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            alert_d.setView(view);
            AlertDialog alertDialog = alert_d.create();
            alertDialog.show();

        }
        if(id == R.id.skull){
            Log.d("--YOOO--","Reached here");

            Intent intent = new Intent(getApplicationContext(),Estimate.class);
            intent.putExtra("theoryLectures",UtilityFunctions.totalTheory);
            intent.putExtra("pottaTotal", UtilityFunctions.theoryCounter);
            startActivity(intent);


        }
        if(id == R.id.action_exit){
            db.delete();
            Intent it = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(it);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_landing, menu);
        return super.onCreateOptionsMenu(menu);
    }


    private class LoadDataFromTheScraper extends AsyncTask<String, Void, ArrayList<Report>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<Report> doInBackground(String... strings) {

            ArrayList<Report> report = new ArrayList<>();
            //String jsonResponse = "{\n    \"THEORY OF COMPUTATION \": {\n        \" TUT\": {\n            \"total\": 1,\n            \"percentage\": 100.0,\n            \"present\": 1\n        },\n        \" TH\": {\n            \"total\": 27,\n            \"percentage\": 77.78,\n            \"present\": 21\n        }\n    },\n    \"DESIGN & ANALYSIS OF ALGORITHMS \": {\n        \" PR\": {\n            \"total\": 9,\n            \"percentage\": 100.0,\n            \"present\": 9\n        },\n        \" TUT\": {\n            \"total\": 6,\n            \"percentage\": 83.33,\n            \"present\": 5\n        },\n        \" TH\": {\n            \"total\": 31,\n            \"percentage\": 87.1,\n            \"present\": 27\n        }\n    },\n    \"ADVANCED JAVA \": {\n        \" PR\": {\n            \"total\": 9,\n            \"percentage\": 77.78,\n            \"present\": 7\n        },\n        \" TH\": {\n            \"total\": 33,\n            \"percentage\": 72.73,\n            \"present\": 24\n        }\n    },\n    \"COMPUTER NETWORKS-II \": {\n        \" PR\": {\n            \"total\": 11,\n            \"percentage\": 81.82,\n            \"present\": 9\n        },\n        \" TH\": {\n            \"total\": 37,\n            \"percentage\": 75.68,\n            \"present\": 28\n        }\n    },\n    \"VERDICT\": {\n        \"TUT\": {\n            \"total\": 1,\n            \"percentage\": 100.0,\n            \"present\": 1\n        },\n        \"Tutorial\": {\n            \"total\": 7,\n            \"percentage\": 85.71,\n            \"present\": 6\n        },\n        \"Total\": {\n            \"total\": 237,\n            \"percentage\": 78.48,\n            \"present\": 186\n        },\n        \"Theory\": {\n            \"total\": 182,\n            \"percentage\": 75.27,\n            \"present\": 137\n        },\n        \"Practical\": {\n            \"total\": 48,\n            \"percentage\": 89.58,\n            \"present\": 43\n        }\n    },\n    \"SDL-II MOBILE APPLICATION DEVELOPMENT LAB \": {\n        \" PR\": {\n            \"total\": 11,\n            \"percentage\": 90.91,\n            \"present\": 10\n        },\n        \" TH\": {\n            \"total\": 15,\n            \"percentage\": 53.33,\n            \"present\": 8\n        }\n    },\n    \"SOFTWARE TESTING & QUALITY ASSURANCE \": {\n        \" PR\": {\n            \"total\": 8,\n            \"percentage\": 100.0,\n            \"present\": 8\n        },\n        \" TH\": {\n            \"total\": 39,\n            \"percentage\": 74.36,\n            \"present\": 29\n        }\n    }\n}";
            THE_BASE_URL += "username="+strings[0] + "&password="+strings[1];
            THE_BASE_URL = THE_BASE_URL.replaceAll(" ","%20");
            Log.d("--AKHIR--",""+THE_BASE_URL);
            jsonResponse = "";

            URL url = UtilityFunctions.makeURL(THE_BASE_URL);
            try{
                jsonResponse = UtilityFunctions.makeHttpRequest(url);
                Log.d("--AKHIRKAR--",""+jsonResponse);
            }catch (Exception ex){

//                Toast.makeText(getApplicationContext(),"Some error occurred in doInBackground",Toast.LENGTH_LONG).show();
                return null;
            }
            if(jsonResponse != null){ report = UtilityFunctions.parseJson(jsonResponse);return report;}
            else return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Report> report) {
            super.onPostExecute(report);
            updateUI(report);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        public void updateUI(ArrayList<Report> report) {


            if (report != null) {
                fl.setVisibility(View.VISIBLE);
                pb.setVisibility(View.GONE);
                String subTypeName = "";
                CardGenerator cardGenerator = new CardGenerator();
                Log.e("--UPDATEUI--", " " + report.size());
                cardGenerator.linearLayoutMain = (LinearLayout) findViewById(R.id.LLInner);


                for (int i = 0; i < report.size(); i++) {
                    if (report.get(i).subject.trim().equalsIgnoreCase("VERDICT")) {
                        pTotal = (int) report.get(i).theory.get(0);
                        subTypeName += "( " + report.get(i).theory.get(0) + "/" + report.get(i).theory.get(1) + " )\n";
                        subTypeName += "   Theory";

                        Log.d("--IN LANDING--", " " + report.get(i).theory.size() + " --> " + i);
                        cardGenerator.generateCard(report.get(i).subject, getApplicationContext());
                        cardGenerator.textForType.setText(subTypeName);
                        String textInCenter = "";
                        textInCenter += report.get(i).theory.get(2).toString() + " %";
//                 Integer progress = (Integer) (report.get(i).theory.get(2));
                        cardGenerator.progressBarFor.setProgress(60);
                        cardGenerator.textInTheCenter.setText(textInCenter);

                        textInCenter = "";
                        subTypeName = "";
                        subTypeName += "  ( " + report.get(i).practical.get(0) + "/" + report.get(i).practical.get(1) + " )\n";
                        subTypeName += "  Practical";
                        cardGenerator.textForTypePR.setText(subTypeName);
                        textInCenter += report.get(i).practical.get(2).toString() + " %";
                        //             progress = (Integer) report.get(i).practical.get(2);
                        cardGenerator.progressBarForPR.setProgress(90);
                        cardGenerator.textInTheCenterPR.setText(textInCenter);
                        break;
                    }
                }


                for (int i = 0; i < report.size(); i++) {
                    if (!report.get(i).subject.equalsIgnoreCase("VERDICT")) {
                        if (report.get(i).theory.size() > 0 && report.get(i).practical.size() > 0) {
                            cardGenerator.generateCard(report.get(i).subject, getApplicationContext());
                            String textInCenter = "";
                            textInCenter += report.get(i).theory.get(2).toString() + " %";
//               Integer progress = (Integer) (report.get(i).theory.get(2));
                            cardGenerator.progressBarFor.setProgress(60);
                            cardGenerator.textInTheCenter.setText(textInCenter);
                            subTypeName = "";
                            subTypeName += "( " + report.get(i).theory.get(0) + "/" + report.get(i).theory.get(1) + " )\n";
                            subTypeName += "  Theory";
                            cardGenerator.textForType.setText(subTypeName);


                            textInCenter = "";
                            subTypeName = "";
                            subTypeName += "  ( " + report.get(i).practical.get(0) + "/" + report.get(i).practical.get(1) + " )\n";
                            subTypeName += " Practical";
                            cardGenerator.textForTypePR.setText(subTypeName);
                            textInCenter += report.get(i).practical.get(2).toString() + " %";
                            //             progress = (Integer) report.get(i).practical.get(2);
                            cardGenerator.progressBarForPR.setProgress(90);
                            cardGenerator.textInTheCenterPR.setText(textInCenter);
                        } else {
                            if (report.get(i).theory.size() > 0 && report.get(i).practical.size() == 0) {
                                cardGenerator.generateCardForSingle(report.get(i).subject, getApplicationContext(), "th");
                                String textInCenter = "";
                                textInCenter += report.get(i).theory.get(2).toString() + " %";
//               Integer progress = (Integer) (report.get(i).theory.get(2));
                                cardGenerator.progressBarFor.setProgress(60);
                                cardGenerator.textInTheCenter.setText(textInCenter);
                                subTypeName = "";
                                subTypeName += "( " + report.get(i).theory.get(0) + "/" + report.get(i).theory.get(1) + " )\n";
                                subTypeName += "  Theory";
                                cardGenerator.textForType.setText(subTypeName);

                            } else {
                                cardGenerator.generateCardForSingle(report.get(i).subject, getApplicationContext(), "pr");
                                String textInCenter = "";
                                subTypeName = "";
                                subTypeName += "  ( " + report.get(i).practical.get(0) + "/" + report.get(i).practical.get(1) + " )\n";
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

            }else{
                errorText.setVisibility(View.VISIBLE);
                pb.setVisibility(View.GONE);
                errorText.setText("Some problem occurred while communicating with the caserp.jnec.org, please check you username and password or try again after a while!");
            }
        }
    }
}
