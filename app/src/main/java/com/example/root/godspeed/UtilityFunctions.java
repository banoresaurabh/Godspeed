package com.example.root.godspeed;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by root on 19/3/17.
 */
public class UtilityFunctions {

    static int theoryCounter = 0;
    static int totalTheory = 0;

    public static Context context;

    public UtilityFunctions(Context context){
        this.context = context;
    }

    public static String makeHttpRequest(URL url) throws IOException{
        HttpURLConnection httpURLConnection = null;
        String LOG_TAG = "--makeHttpConnection--";
        InputStream inputStream = null;
        String jsonResponse = "";
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
           // httpURLConnection.setConnectTimeout(15000);
            //httpURLConnection.setReadTimeout(15000);
            httpURLConnection.connect();
            Log.d("--AKHIRinhttp--",httpURLConnection.getResponseCode()+"");


            if(httpURLConnection.getResponseCode() == 200){
                inputStream = httpURLConnection.getInputStream();
                jsonResponse = readFromInputStream(inputStream);
            }else{
                Log.e(LOG_TAG,"Error response code" + httpURLConnection.getResponseCode());
            }

        }catch(IOException e){
            Log.e("--AKHIR--","IOException occurred");
            return null;
        }finally {
            if(httpURLConnection != null ){
                httpURLConnection.disconnect();
            }
            if(inputStream != null){
                inputStream.close();
            }
        }
        return jsonResponse;
    }



    public static String readFromInputStream(InputStream inputStream) throws IOException {
        String LOG_TAG = "--readFromInputStream--";
        StringBuilder output = new StringBuilder();
        if(inputStream != null){
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = bufferedReader.readLine();
                while (line != null) {
                    output.append(line);
                    line = bufferedReader.readLine();
                }
            }catch (IOException ex){
                Log.e(LOG_TAG,"IOException occurred" + ex);
                return null;
            }
        }
        return output.toString();
    }

    public static URL makeURL(String urlString){
        URL url = null;
        String LOG_TAG = "--urlString--";
        try {
            url = new URL(urlString);
        }catch (MalformedURLException ex){
            Log.e(LOG_TAG,"MalformedURLException occurred" + ex);
            return null;
        }
        return url;
    }

    public static ArrayList<Report> parseJson(String jsonResponse){

        ArrayList<Report> report = new ArrayList<>();
        double percentage;
        int present,total;
        final String HCTheory = "TH";
        final String HCPrac = "PR";
        final String HCTut = "TUT";

        String subName;
        ArrayList theoryList = new ArrayList();
        ArrayList practicalList = new ArrayList();
        ArrayList tutList = new ArrayList();
        try {
            JSONObject jsonObjectReal = new JSONObject(jsonResponse);
            Iterator itForReal = jsonObjectReal.keys();
            while (itForReal.hasNext()){
                subName =(String) itForReal.next();
                if(subName.trim().equalsIgnoreCase("VERDICT") == false){
                    Log.d("------------", "" + subName);
                    JSONObject jsonForSubject = jsonObjectReal.getJSONObject(subName);
                    Iterator itForType = jsonForSubject.keys();

                    while (itForType.hasNext()) {
                        String type = itForType.next().toString();

                        if (jsonForSubject.has(type)) {
                            JSONObject jsonForType = jsonForSubject.getJSONObject(type);
                            present = jsonForType.getInt("present");
                            total = jsonForType.getInt("total");
                            percentage = (double) jsonForType.get("percentage");
                            Log.d("------------", "" + type);
                            type = type.trim();
                            Log.d("----------", "" + present + " " + total + " " + percentage);
                            if (type.equalsIgnoreCase(HCTheory)) {
                                theoryList.add(present);
                                theoryList.add(total);
                                theoryList.add(percentage);
                                theoryCounter += 45 - total;
                                totalTheory += total;

                            } else if (type.equalsIgnoreCase(HCPrac)) {
                                practicalList.add(present);
                                practicalList.add(total);
                                practicalList.add(percentage);
                            } else if (type.equalsIgnoreCase(HCTut) && !subName.equalsIgnoreCase("VERDICT")) {
                                tutList.add(present);
                                tutList.add(total);
                                tutList.add(percentage);
                            }
                        }
                    }
                    Log.d("--------I am size", "tut " + tutList.size() + "theory " + theoryList.size() + "prac " + practicalList.size());
                    report.add(new Report(subName, theoryList, practicalList, tutList));
                    theoryList.clear();
                    practicalList.clear();
                    tutList.clear();
                }
            }

            JSONObject jsonObjectTemp = jsonObjectReal.getJSONObject("VERDICT");
            subName = "VERDICT";
            JSONObject jsonObjectTempTemp = jsonObjectTemp.getJSONObject("Tutorial");
            tutList.add(jsonObjectTempTemp.getInt("present"));
            tutList.add(jsonObjectTempTemp.getInt("total"));
            tutList.add(jsonObjectTempTemp.getDouble("percentage"));

            jsonObjectTempTemp = jsonObjectTemp.getJSONObject("Theory");
            theoryList.add(jsonObjectTempTemp.getInt("present"));
            theoryList.add(jsonObjectTempTemp.getInt("total"));
            theoryList.add(jsonObjectTempTemp.getDouble("percentage"));

            jsonObjectTempTemp = jsonObjectTemp.getJSONObject("Practical");
            practicalList.add(jsonObjectTempTemp.getInt("present"));
            practicalList.add(jsonObjectTempTemp.getInt("total"));
            practicalList.add(jsonObjectTempTemp.getDouble("percentage"));

            Log.d("--YOO---"," "+tutList.size()+" "+theoryList.size()+" "+practicalList.size());
            report.add(new Report(subName,theoryList,practicalList,tutList));

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("--Exception--","hello");
        }

        return report;
    }
}

