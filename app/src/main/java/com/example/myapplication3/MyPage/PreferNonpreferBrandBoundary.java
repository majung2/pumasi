package com.example.myapplication3.MyPage;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PreferNonpreferBrandBoundary extends AppCompatActivity {//선호,비선호 브랜드 리스트 보는 화면
    private ArrayList<String> preferBrands=new ArrayList<>();//선호 브랜드 리스트
    private ArrayList<String> nonPreferBrands=new ArrayList<>();// 비선호 브랜드 리스트
    private Button changePrefer;
    private Button changeNonPrefer;
    private TextView selected_item_textview;
    private TextView selected_item_textview2;
    private ArrayAdapter<String>    adapter;
    private ArrayAdapter<String>    adapter2;
    private ListView listView;
    private ListView listView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prefer_nonprefer_brand_info);

        //버튼 연결
       changePrefer = (Button) findViewById(R.id.preferbrandadd);
       changeNonPrefer = (Button) findViewById(R.id.nonpreferbrandadd);

        //리스트뷰 확인 위해 임의로 리스트에 데이터 추가, 디비 읽어들이는 과정 끝나면 최종 수정 예정
        preferBrands.add("AbC MART");
        preferBrands.add("NEANPOLE");
        nonPreferBrands.add("BLACKYARK");
        nonPreferBrands.add("ADIDAS");

        //첫번째 리스트뷰 어댑터에 연결
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
        listView = (ListView) findViewById(R.id.selected_1);

        listView.setAdapter(adapter);
        //listView.setOnItemClickListener(this);


        adapter.add("안드로이드");
        adapter.add("열심히");
        adapter.add("공부합시다.");
        adapter.add("홍로이드");
        adapter.add("리스트뷰");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long id) {

                // select route name or visited date(?)
                String selectedPath = (String)adapterView.getItemAtPosition(position);
                deleteHandler();//브랜드 클릭시 alertdialog 띄움

                adapter.notifyDataSetChanged();
            }
        });

        //두 번쨰 리스트 뷰 어댑터에 연결
        adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
        listView2 = (ListView) findViewById(R.id.selected_2);

        listView2.setAdapter(adapter2);

        adapter2.add("두번째 리스트");
        adapter2.add("열심히");
        adapter2.add("공부합시다.");
        adapter2.add("홍로이드");
        adapter2.add("리스트뷰");

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long id) {

                // select route name or visited date(?)
                String selectedPath = (String)adapterView.getItemAtPosition(position);
                deleteHandler();//브랜드 클릭시 alertdialog 띄움
                adapter2.notifyDataSetChanged();
            }
        });



    }


    public void onClickChangeBrand(View v){//사용자가 선호브랜드 또는 비선호 브랜드 수정하기 버튼을 눌렀을 때-> 선호비선호 브랜드 수정 화면으로 전환 + 사용자의 선호/비선호 브랜드 리스트 가져가기
        Intent intent = new Intent(// 다음 화면으로 전환
                PreferNonpreferBrandBoundary.this,
                ChangePreferNonPreferBoundary.class); //
        startActivity(intent);
    }
    public void deleteHandler(){//사용자가 브랜드를 클릭했을 때
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("브랜드 삭제").setMessage("선택하신 브랜드를 목록에서 삭제하시겠습니까?");

        builder.setPositiveButton("예", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                Toast.makeText(getApplicationContext(), "OK Click", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                Toast.makeText(getApplicationContext(), "Cancel Click", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
