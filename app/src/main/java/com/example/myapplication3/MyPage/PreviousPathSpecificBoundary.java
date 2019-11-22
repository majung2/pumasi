package com.example.myapplication3.MyPage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import com.example.myapplication3.R;
import com.example.myapplication3.Data;

public class PreviousPathSpecificBoundary extends AppCompatActivity {

    private PreviousPathController pathController = new PreviousPathController();
    private String path = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // layout-resource xml
        setContentView(R.layout.previous_path);

        // layout -lv
        final ListView listview = (ListView) findViewById(R.id.lv);


        path = pathController.getPath();
        Log.d("@@@test", "if path is not 0, then OK!  [path : " + path + " ]");

        // entity ( from DB -> same Path -> load brand)
        final List<Data> brand = new ArrayList<>();

        brand.add(new Data("ABC MART"));
        brand.add(new Data("BEANPOLE"));

        // entity ( from DB -> same route -> load brand)

        final PreviousPathAdapter Adapter1 = new PreviousPathAdapter(this, brand, listview);

        // ListView, adapter connect
        listview.setAdapter(Adapter1);


        listview.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.d("@@@Changed", "mListView.getFirstVisiblePosition()=" + listview.getFirstVisiblePosition());
                Log.d("@@@Changed", "mListView.getLastVisiblePosition()=" + listview.getLastVisiblePosition());
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {


            }


        });

    }
}
