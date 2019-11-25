package com.example.myapplication3.MyPage;

import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.myapplication3.R;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication3.Data;

import java.util.ArrayList;

public class BoughtListBoundary extends AppCompatActivity {
    private ArrayList<Data> boughtdata = new ArrayList<Data>();
    private MyPageController controller = new MyPageController();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.bought_list);

        boughtdata = controller.getBought();

        final ListView listview = (ListView) findViewById(R.id.boughtlist);
        final BoughtListAdapter Adapter1 = new BoughtListAdapter(this, boughtdata, listview);

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
