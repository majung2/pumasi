package com.example.myapplication3.MyPage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.R;

import java.util.ArrayList;

public class PreviousPathBoundary extends AppCompatActivity {

    public MyPageController Pathcontrol;

    ArrayList<String> list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.previous_path);

        // *need modify entitiy

        list = new ArrayList<>();

        for (int i = 1; i < 8; i++) {
            list.add("Path"+ String.valueOf(i));

        }//for end *entitiy

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,list);
        ListView listview = (ListView) findViewById(R.id.lv);


        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long id) {

                // select route name or visited date(?)
                String selectedPath = (String)adapterView.getItemAtPosition(position);



                Pathcontrol = new MyPageController();
                Pathcontrol.loadPath(selectedPath);

                Intent intent = new Intent(PreviousPathBoundary.this,
                        PreviousPathSpecificBoundary.class);
                startActivity(intent);


                adapter.notifyDataSetChanged();
            }
        });



    }
}