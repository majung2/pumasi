package com.example.myapplication3.MyPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import com.example.myapplication3.PreviousPathBrand;
import com.example.myapplication3.R;

public class PreviousPathSpecificBoundary extends AppCompatActivity implements MyPageController.PPBrandCallback  {

    private MyPageController controller = new MyPageController();
    private String path = null;
    private String id;
    private String pw;
    private String pathnum;
    private String pathdate;
    ArrayList<PreviousPathBrand> brandlist ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // layout-resource xml
        setContentView(R.layout.previous_path);


        Intent intent=getIntent(); //이전 화면으로부터 넘겨받은 유저에 대한 정보들
        id = intent.getStringExtra("id");
        pw = intent.getStringExtra("pw");
        pathnum = intent.getStringExtra("pathnum");
        pathdate = intent.getStringExtra("pathdate");

        System.out.println(id);
        System.out.println(pathnum);


        Log.d("@@@test", "if path is not 0, then OK!  [path : " + pathnum + " ]");

        controller.PPBrandController(this);
        controller.setMyPageControlUser(id,pw);

        brandlist= new ArrayList<PreviousPathBrand>();
        brandlist= controller.listUpPPBrand(pathnum);

    }

    public void finishPPBrand2(ArrayList<PreviousPathBrand> brandlist){
        System.out.println("specific 바운더리 콜백 성공");
        // layout -lv
        final ListView listview = (ListView) findViewById(R.id.lv);
        final PreviousPathAdapter Adapter1 = new PreviousPathAdapter(this, brandlist, listview);

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

}//end class
