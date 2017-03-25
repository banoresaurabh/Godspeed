package com.example.root.godspeed;

import java.util.ArrayList;

/**
 * Created by root on 19/3/17.
 */
public class Report {
    String subject;

    public final int SIZE_OF_LIST = 3;

    public final ArrayList theory;


    public final ArrayList practical;


    public final ArrayList tutorial;

    public Report(String subjectName,ArrayList theory, ArrayList practical,ArrayList tutorial){
        this.theory = new ArrayList(theory);
        this.practical = new ArrayList(practical);
        this.tutorial = new ArrayList(tutorial);
        this.subject = subjectName;
    }

}
