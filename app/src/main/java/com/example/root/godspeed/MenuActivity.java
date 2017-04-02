package com.example.root.godspeed;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.zip.Inflater;

public class MenuActivity extends AppCompatActivity {
    ListView noAbt;
    String titles[];
    int icons[] = {R.drawable.ic_favorite_black_36dp,R.drawable.ic_exit_to_app_black_36dp};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        noAbt=(ListView) findViewById(R.id.noAbt);
        Resources res = getResources();
        titles = res.getStringArray(R.array.titles);

        ListViewAdaper ob = new ListViewAdaper(this,titles,icons);
        noAbt.setAdapter(ob);
    }

}
class ListViewAdaper extends ArrayAdapter<String>{
    Context context;
    int[] images;
    String[] titles;
    ListViewAdaper(Context context,String[] titles,int[] images){
        super(context,R.layout.single_row,R.id.textView,titles);
        this.context = context;
        this.images = images;
        this.titles = titles;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.single_row,parent,false);

        ImageView icons = (ImageView) view.findViewById(R.id.icon);
        TextView tv = (TextView) view.findViewById(R.id.textView);

        icons.setImageResource(images[position]);
        tv.setText(titles[position]);

        return view;
    }
}


