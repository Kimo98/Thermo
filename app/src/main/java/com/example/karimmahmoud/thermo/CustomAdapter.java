package com.example.karimmahmoud.thermo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;

import java.util.ArrayList;


public class CustomAdapter extends BaseAdapter {
    ArrayList<SingleRow> List;
Context context;
    public CustomAdapter(Context c) {
        context=c;
      List = new ArrayList<SingleRow>();
        String[] Languages=context.getResources().getStringArray(R.array.Languages);
        int[]images={R.drawable.sql, R.drawable.java, R.drawable.js, R.drawable.c1,R.drawable.python,R.drawable.c2,R.drawable.php };
        for(int i=0; i<6;i++) {
            List.add(new SingleRow(Languages[i], images[i]));
        }
    }

    // exposed to an array
    @Override
    public int getCount() { //works in only 1 array:
        return List.size();
    }

    @Override
    public Object getItem(int position) {
        return List.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=inflater.inflate(R.layout.custom, parent, false); //reference to the contrained layout
        ImageView imageView= row.findViewById(R.id.imageView);
        TextView ListTextView=row.findViewById(R.id.ListTextView);
        SingleRow S=List.get(position);
        imageView.setImageResource(S.image);
        ListTextView.setText(S.Language);

        return row;
    }
}
