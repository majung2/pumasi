package com.example.myapplication3.MyPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.Login.MainActivity;
import com.example.myapplication3.R;
import java.util.ArrayList;

public class PreviousPathBoundary extends AppCompatActivity implements MyPageController.PreviousCallback {

    public MyPageController controller;
    private String id;
    private String pw;
    ArrayList<String> list ;
    @Override
    public boolean onCreateOptionsMenu(Menu menu)//옵션 메뉴바
    {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.mainmenu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)//메뉴 선택
    {
        //   Toast toast = Toast.makeText(getApplicationContext(),"", Toast.LENGTH_LONG);
        int state=3;
        switch(item.getItemId())
        {
            case R.id.menu1:
                state=0;
                break;
            case R.id.menu2:
                state=1;
                break;

        }
        if(state==0){
            Intent intent = new Intent(// 다음 화면으로 전환
                    PreviousPathBoundary.this,
                    MyPageSelectMenu.class); // ?ㅼ쓬 ?섏뼱媛??대옒??吏??
            intent.putExtra("id",id);
            intent.putExtra("pw",pw);

            startActivity(intent);
        }
        else if(state==1){
            Toast.makeText(PreviousPathBoundary.this,"로그아웃되었습니다.",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(// 다음 화면으로 전환
                    PreviousPathBoundary.this,
                    MainActivity.class);

            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }//옵션메뉴
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