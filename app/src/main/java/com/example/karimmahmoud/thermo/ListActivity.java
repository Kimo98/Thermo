package com.example.karimmahmoud.thermo;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity {

   // @Override
    String[]Devices;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ListView List1=(ListView)findViewById(R.id.Devices_List);
        Resources res=getResources();
        Devices=res.getStringArray(R.array.Device_name);
        List1.setAdapter(new ArrayAdapter<String>(this, R.layout.for_listview, Devices));

                List1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent detailList=new Intent(ListActivity.this, DeviceDetail.class);
                        startActivity(detailList);

                    }
                });
    }
}
