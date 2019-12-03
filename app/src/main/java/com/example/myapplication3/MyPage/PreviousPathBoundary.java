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

public class PreviousPathBoundary extends AppCompatActivity implements MyPageController.PreviousCallback {

    public MyPageController controller;
    private String id;
    private String pw;
    ArrayList<String> list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.previous_path);

        //이전 화면으로부터 넘겨받은 유저에 대한 정보들
        Intent intent=getIntent();
        id = intent.getStringExtra("id");
        pw = intent.getStringExtra("pw");

        MyPageController controller = new MyPageController();
        controller.PreviousPathController(this);
        controller.setMyPageControlUser(id,pw);
        list = new ArrayList<>();
        list = controller.listUpPreviousPath();



    }


    public void finishPreviousPath2(ArrayList<String> list){
        System.out.println("previous path 바운더리 콜백 성공");
        System.out.println(list);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,list);
        ListView listview = (ListView) findViewById(R.id.lv);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long id2) {

                // select route name or visited date(?)
                String selectedPath = adapterView.getItemAtPosition(position).toString();
                System.out.println(selectedPath); //선택한 패스 이름

                String[] pathname = selectedPath.split("path");
                String[] pathnumdate = pathname[1].split(" "); // [0]= num [1]= date

                System.out.println(pathnumdate[0]);


                Intent intent = new Intent(PreviousPathBoundary.this,
                        PreviousPathSpecificBoundary.class);
                //
                intent.putExtra("id",id);
                intent.putExtra("pw",pw);
                intent.putExtra("pathnum",pathnumdate[0]);
                intent.putExtra("pathdate",pathnumdate[1]);
                //
                startActivity(intent);


                adapter.notifyDataSetChanged();
            }
        });


    }

}//end class