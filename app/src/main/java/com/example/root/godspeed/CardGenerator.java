package com.example.root.godspeed;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by root on 12/3/17.
 */

public class CardGenerator {
    LinearLayout linearLayoutMain;
    TextView textInTheCenter;
    ProgressBar progressBarBack;
    ProgressBar progressBarFor;
    LinearLayout.LayoutParams params;
    RelativeLayout.LayoutParams rParams;
    TextView textForType;

    TextView textInTheCenterPR;
    ProgressBar progressBarBackPR;
    ProgressBar progressBarForPR;
    TextView textForTypePR;
    public String subTypeName = "";



    public void generateCard(String cardName, Context context){
        //--------------------------------------------Preparing The card--------------------------------------------------------//
        CardView cv1 = new CardView(context);
        cv1.setMaxCardElevation(8);
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 470);
        int margin = 15;
        params.setMargins(16,margin,16,margin);
        cv1.setLayoutParams(params);
        cv1.setContentPadding(2, 1, 2, 1);
        cv1.setCardElevation(9);
        cv1.setCardBackgroundColor(Color.parseColor("#ffffff"));

        //----------------------------------------TextView for Subject name strip------------------------------------------------//
        TextView tv = new TextView(context);
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(params);
        tv.setBackground(context.getResources().getDrawable(R.drawable.yellowgradient));
        tv.setText(cardName);
        tv.setTextSize(18);
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(Color.parseColor("#ffffff"));

        //------------------------------------------The fuckin' progress bars!!--------------------------------------------------//
        LinearLayout layoutForBars = new LinearLayout(context);
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        layoutForBars.setLayoutParams(params);
        layoutForBars.setOrientation(LinearLayout.HORIZONTAL);


        RelativeLayout relativeLayoutForBars = new RelativeLayout(context);
        params = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,1f);
        layoutForBars.addView(relativeLayoutForBars,params);
        rParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        rParams.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);

        progressBarBack = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressBarBack.setLayoutParams(rParams);
        progressBarBack.setIndeterminate(false);
        progressBarBack.setProgress(100);
        progressBarBack.setMax(100);
        progressBarBack.setProgressDrawable(ContextCompat.getDrawable(context,R.drawable.circle_progress_background));


        progressBarFor = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressBarFor.setLayoutParams(rParams);
        progressBarFor.setIndeterminate(false);
        progressBarFor.setRotation(-90);
        progressBarFor.setMax(100);
        progressBarFor.setId(progressBarFor.generateViewId());
        progressBarFor.setProgressDrawable(ContextCompat.getDrawable(context,R.drawable.circle_progress_foreground));


        //-----------------------------------------------TextView for middle text------------------------------------------------------//
        textInTheCenter = new TextView(context);
        textInTheCenter.setId(textInTheCenter.generateViewId());
        rParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        rParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        rParams.addRule(RelativeLayout.CENTER_VERTICAL);
        textInTheCenter.setLayoutParams(rParams);
        textInTheCenter.setTextColor(Color.parseColor("#78909c"));
        textInTheCenter.setTextSize(25);
        textInTheCenter.setText(R.string.centerText);
        //-------------------------------------------------Subject Type text-----------------------------------------------------------//
        textForType = new TextView(context);
        textForType.setId(textForType.generateViewId());
        rParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        rParams.setMargins(0,0,0,5);
        rParams.addRule(RelativeLayout.ALIGN_BOTTOM,progressBarFor.getId());
        rParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        textForType.setLayoutParams(rParams);
        textForType.setTextColor(Color.parseColor("#ff8f00"));
        textForType.setText(R.string.subTypeText);


        //---------------------------------------------progresss bars 2---------------------------------------------------------------//


        RelativeLayout relativeLayoutForBarsPR = new RelativeLayout(context);
        params = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,1f);
        layoutForBars.addView(relativeLayoutForBarsPR,params);
        rParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        rParams.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);

        progressBarBackPR = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressBarBackPR.setLayoutParams(rParams);
        progressBarBackPR.setIndeterminate(false);
        progressBarBackPR.setProgress(100);
        progressBarBackPR.setMax(100);
        progressBarBackPR.setProgressDrawable(ContextCompat.getDrawable(context,R.drawable.circle_progress_background));


        progressBarForPR = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressBarForPR.setLayoutParams(rParams);
        progressBarForPR.setIndeterminate(false);
        progressBarForPR.setRotation(-90);
        progressBarForPR.setMax(100);
        progressBarForPR.setId(progressBarFor.generateViewId());
        progressBarForPR.setProgressDrawable(ContextCompat.getDrawable(context,R.drawable.circle_progress_foreground));


        //-----------------------------------------------TextView for middle text------------------------------------------------------//
        textInTheCenterPR = new TextView(context);
        textInTheCenterPR.setId(textInTheCenter.generateViewId());
        rParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        rParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        rParams.addRule(RelativeLayout.CENTER_VERTICAL);
        textInTheCenterPR.setLayoutParams(rParams);
        textInTheCenterPR.setTextColor(Color.parseColor("#78909c"));
        textInTheCenterPR.setTextSize(25);
        textInTheCenterPR.setText(R.string.centerText);

        textForTypePR = new TextView(context);
        textForTypePR.setId(textForTypePR.generateViewId());
        rParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        textForTypePR.setText(subTypeName);
        rParams.setMargins(0,0,0,5);
        rParams.addRule(RelativeLayout.ALIGN_BOTTOM,progressBarForPR.getId());
        rParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        textForTypePR.setLayoutParams(rParams);
        textForTypePR.setTextColor(Color.parseColor("#ff8f00"));
        textForTypePR.setText(R.string.subTypeText);
        //------------------------------------------------Adding views to parents----------------------------------------------------//
        relativeLayoutForBars.addView(progressBarBack);
        relativeLayoutForBars.addView(progressBarFor);
        relativeLayoutForBars.addView(textInTheCenter);
        relativeLayoutForBars.addView(textForType);

        relativeLayoutForBarsPR.addView(progressBarBackPR);
        relativeLayoutForBarsPR.addView(progressBarForPR);
        relativeLayoutForBarsPR.addView(textInTheCenterPR);
        relativeLayoutForBarsPR.addView(textForTypePR);


        cv1.addView(tv);
        cv1.addView(layoutForBars);
        linearLayoutMain.addView(cv1);

       /* //Test on the cards
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.circle_progress_bar_th);
        Double AJ_theory = 65.15;
        progressBar.setProgress(65);
        TextView proTV = (TextView) findViewById(R.id.AJ_theory);
        proTV.setText(AJ_theory + " %");

        ProgressBar progressBar2 = (ProgressBar) findViewById(R.id.circle_progress_bar2);
        Double AJ_prac = 90.78;
        progressBar2.setProgress(90);
        TextView proTV2 = (TextView) findViewById(R.id.AJ_prac);
        proTV2.setText(AJ_prac + " %");*/
    }












    public void generateCardForSingle(String cardName, Context context,String type){
        textForTypePR = new TextView(context);
        RelativeLayout relativeLayoutForBarsPR;
        RelativeLayout relativeLayoutForBars;
        //--------------------------------------------Preparing The card--------------------------------------------------------//
        CardView cv1 = new CardView(context);
        cv1.setMaxCardElevation(8);
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 470);
        int margin = 15;
        params.setMargins(16,margin,16,margin);
        cv1.setLayoutParams(params);
        cv1.setContentPadding(2, 1, 2, 1);
        cv1.setCardElevation(9);
        cv1.setCardBackgroundColor(Color.parseColor("#ffffff"));

        //----------------------------------------TextView for Subject name strip------------------------------------------------//
        TextView tv = new TextView(context);
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(params);
        tv.setBackground(context.getResources().getDrawable(R.drawable.yellowgradient));
        tv.setText(cardName);
        tv.setTextSize(18);
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(Color.parseColor("#ffffff"));

        //------------------------------------------The fuckin' progress bars!!--------------------------------------------------//
        LinearLayout layoutForBars = new LinearLayout(context);
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        layoutForBars.setLayoutParams(params);
        layoutForBars.setOrientation(LinearLayout.HORIZONTAL);

        if(type.equalsIgnoreCase("th") == true) {
            relativeLayoutForBars = new RelativeLayout(context);
            params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f);
            layoutForBars.addView(relativeLayoutForBars, params);
            rParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            rParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

            progressBarBack = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
            progressBarBack.setLayoutParams(rParams);
            progressBarBack.setIndeterminate(false);
            progressBarBack.setProgress(100);
            progressBarBack.setMax(100);
            progressBarBack.setProgressDrawable(ContextCompat.getDrawable(context, R.drawable.circle_progress_background));


            progressBarFor = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
            progressBarFor.setLayoutParams(rParams);
            progressBarFor.setIndeterminate(false);
            progressBarFor.setRotation(-90);
            progressBarFor.setMax(100);
            progressBarFor.setId(progressBarFor.generateViewId());
            progressBarFor.setProgressDrawable(ContextCompat.getDrawable(context, R.drawable.circle_progress_foreground));


            //-----------------------------------------------TextView for middle text------------------------------------------------------//
            textInTheCenter = new TextView(context);
            textInTheCenter.setId(textInTheCenter.generateViewId());
            rParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            rParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            rParams.addRule(RelativeLayout.CENTER_VERTICAL);
            textInTheCenter.setLayoutParams(rParams);
            textInTheCenter.setTextColor(Color.parseColor("#78909c"));
            textInTheCenter.setTextSize(25);
            textInTheCenter.setText(R.string.centerText);
            //-------------------------------------------------Subject Type text-----------------------------------------------------------//
            textForType = new TextView(context);
            textForType.setId(textForType.generateViewId());
            rParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            rParams.setMargins(0, 0, 0, 5);
            rParams.addRule(RelativeLayout.ALIGN_BOTTOM, progressBarFor.getId());
            rParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            textForType.setLayoutParams(rParams);
            textForType.setTextColor(Color.parseColor("#ff8f00"));
            textForType.setText(R.string.subTypeText);


            relativeLayoutForBarsPR = new RelativeLayout(context);
            params = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,1f);
            layoutForBars.addView(relativeLayoutForBarsPR,params);
            rParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
            rParams.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
            TextView natv = new TextView(context);
            natv.setText("NA");
            natv.setId(natv.generateViewId());
            natv.setTextColor(Color.parseColor("#78909c"));
            rParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            rParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            rParams.addRule(RelativeLayout.CENTER_VERTICAL);
            natv.setLayoutParams(rParams);
            natv.setTextSize(45);

            textForTypePR = new TextView(context);
            rParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            rParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            rParams.setMargins(0,0,0,5);
            rParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            textForTypePR.setLayoutParams(rParams);
            textForTypePR.setTextColor(Color.parseColor("#ff8f00"));
            textForTypePR.setText(R.string.subTypeText);

            textForTypePR.setText("Practical");

            relativeLayoutForBars.addView(progressBarBack);
            relativeLayoutForBars.addView(progressBarFor);
            relativeLayoutForBars.addView(textInTheCenter);
            relativeLayoutForBars.addView(textForType);

            relativeLayoutForBarsPR.addView(natv);
            relativeLayoutForBarsPR.addView(textForTypePR);

            cv1.addView(tv);
            cv1.addView(layoutForBars);
            linearLayoutMain.addView(cv1);
        }

        else

        {
            relativeLayoutForBars = new RelativeLayout(context);
            params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f);
            layoutForBars.addView(relativeLayoutForBars, params);
            rParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            rParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

            TextView natv = new TextView(context);
            natv.setText("NA");
            natv.setTextColor(Color.parseColor("#78909c"));
            rParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            rParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            rParams.addRule(RelativeLayout.CENTER_VERTICAL);
            natv.setLayoutParams(rParams);
            natv.setTextSize(45);


            relativeLayoutForBarsPR = new RelativeLayout(context);
            params = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,1f);
            layoutForBars.addView(relativeLayoutForBarsPR,params);
            rParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
            rParams.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);

            progressBarBackPR = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
            progressBarBackPR.setLayoutParams(rParams);
            progressBarBackPR.setIndeterminate(false);
            progressBarBackPR.setProgress(100);
            progressBarBackPR.setMax(100);
            progressBarBackPR.setProgressDrawable(ContextCompat.getDrawable(context,R.drawable.circle_progress_background));


            progressBarForPR = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
            progressBarForPR.setLayoutParams(rParams);
            progressBarForPR.setIndeterminate(false);
            progressBarForPR.setRotation(-90);
            progressBarForPR.setMax(100);
            progressBarForPR.setId(progressBarFor.generateViewId());
            progressBarForPR.setProgressDrawable(ContextCompat.getDrawable(context,R.drawable.circle_progress_foreground));


            //-----------------------------------------------TextView for middle text------------------------------------------------------//
            textInTheCenterPR = new TextView(context);
            textInTheCenterPR.setId(textInTheCenter.generateViewId());
            rParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
            rParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            rParams.addRule(RelativeLayout.CENTER_VERTICAL);
            textInTheCenterPR.setLayoutParams(rParams);
            textInTheCenterPR.setTextColor(Color.parseColor("#78909c"));
            textInTheCenterPR.setTextSize(25);
            textInTheCenterPR.setText(R.string.centerText);

            textForTypePR = new TextView(context);
            textForTypePR.setId(textForTypePR.generateViewId());
            rParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
            textForTypePR.setText(subTypeName);
            rParams.setMargins(0,0,0,5);
            rParams.addRule(RelativeLayout.ALIGN_BOTTOM,progressBarForPR.getId());
            rParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            textForTypePR.setLayoutParams(rParams);
            textForTypePR.setTextColor(Color.parseColor("#ff8f00"));
            textForTypePR.setText(R.string.subTypeText);

            relativeLayoutForBars.addView(natv);
            relativeLayoutForBars.addView(textForType);

            relativeLayoutForBarsPR.addView(progressBarBackPR);
            relativeLayoutForBarsPR.addView(progressBarForPR);
            relativeLayoutForBarsPR.addView(textInTheCenterPR);
            relativeLayoutForBarsPR.addView(textForTypePR);


            cv1.addView(tv);
            cv1.addView(layoutForBars);
            linearLayoutMain.addView(cv1);

        }


        //---------------------------------------------progresss bars 2---------------------------------------------------------------//



        //------------------------------------------------Adding views to parents----------------------------------------------------//


       /* //Test on the cards
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.circle_progress_bar_th);
        Double AJ_theory = 65.15;
        progressBar.setProgress(65);
        TextView proTV = (TextView) findViewById(R.id.AJ_theory);
        proTV.setText(AJ_theory + " %");

        ProgressBar progressBar2 = (ProgressBar) findViewById(R.id.circle_progress_bar2);
        Double AJ_prac = 90.78;
        progressBar2.setProgress(90);
        TextView proTV2 = (TextView) findViewById(R.id.AJ_prac);
        proTV2.setText(AJ_prac + " %");*/
    }


}
