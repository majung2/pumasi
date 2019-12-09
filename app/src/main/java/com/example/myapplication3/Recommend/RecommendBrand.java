package com.example.myapplication3.Recommend;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication3.Login.MainActivity;
import com.example.myapplication3.MyPage.MyPageSelectMenu;
import com.example.myapplication3.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

public class RecommendBrand extends AppCompatActivity {
    private ImageView bt_tab1, bt_tab2, bt_tab3, bt_tab4, bt_tab5,
            bt_tab6, bt_tab7, bt_tab8, bt_tab9, bt_tab10, bt_tab11;
    private Button submit;
    private boolean[] selectedCategory ;
    private String id;
    private String pw;
    private Integer currentX;
    private Integer currentY;
    private ArrayList<String> brNameList = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ArrayList<String> selectedBrand;
    private  ListView listView;

    private Integer cntcallback = 0;
    FirebaseFirestore db;
    CollectionReference brandRateRef;
    CollectionReference pathRef;
    private ArrayList<String> selectedUser;
    private CollectionReference RateRef;
    private HashMap<String, HashMap<String, Object>> AllUserMap = new HashMap<>();
    //이전화면에서 받아오기
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
                    RecommendBrand.this,
                    MyPageSelectMenu.class); // ?ㅼ쓬 ?섏뼱媛??대옒??吏??
            intent.putExtra("id",id);
            intent.putExtra("pw",pw);

            startActivity(intent);
        }
        else if(state==1){
            Toast.makeText(RecommendBrand.this,"로그아웃되었습니다.",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(// 다음 화면으로 전환
                    RecommendBrand.this,
                    MainActivity.class);

            startActivity(intent);

        }


        return super.onOptionsItemSelected(item);
    }//옵션메뉴
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brandrecommend);
        selectedBrand = new ArrayList<>();
        selectedCategory = new boolean[12]; //이전화면에서 셀렉한 카테고리 받아옴
        Intent intent=getIntent();
        cntcallback=0;
        selectedCategory = intent.getBooleanArrayExtra("selected");
        id = intent.getStringExtra("id");
        pw = intent.getStringExtra("pw");
        currentX = intent.getIntExtra("X",0);
        currentY = intent.getIntExtra("Y",0);
        listView = (ListView) findViewById(R.id.brandlistview);
        for(int i = 1; i<12;i++) {System.out.println(selectedCategory[i]);}
        bt_tab1 = (ImageView) findViewById(R.id.ImageView1);
        bt_tab2 = (ImageView) findViewById(R.id.ImageView2);
        bt_tab3 = (ImageView) findViewById(R.id.ImageView3);
        bt_tab4 = (ImageView) findViewById(R.id.ImageView4);
        bt_tab5 = (ImageView) findViewById(R.id.ImageView5);
        bt_tab6 = (ImageView) findViewById(R.id.ImageView6);
        bt_tab7 = (ImageView)findViewById(R.id.ImageView7);
        bt_tab8 = (ImageView)findViewById(R.id.ImageView8);
        bt_tab9 = (ImageView)findViewById(R.id.ImageView9);
        bt_tab10 = (ImageView)findViewById(R.id.ImageView10);
        bt_tab11 = (ImageView)findViewById(R.id.ImageView11);


        // 받아온 카테고리의 이미지만 띄움
        if(selectedCategory[1]==false){
            bt_tab1.setImageResource(R.drawable.category1_copy);
        }
        if(selectedCategory[2]==false){
            bt_tab2.setImageResource(R.drawable.category2_copy);
        }
        if(selectedCategory[3]==false){
            bt_tab3.setImageResource(R.drawable.category3_copy);
        }
        if(selectedCategory[4]==false){
            bt_tab4.setImageResource(R.drawable.category4_copy);
        }
        if(selectedCategory[5]==false){
            bt_tab5.setImageResource(R.drawable.category5_copy);
        }
        if(selectedCategory[6]==false){
            bt_tab6.setImageResource(R.drawable.category6_copy);
        }
        if(selectedCategory[7]==false){
            bt_tab7.setImageResource(R.drawable.category7_copy);
        }
        if(selectedCategory[8]==false){
            bt_tab8.setImageResource(R.drawable.category8_copy);
        }
        if(selectedCategory[9]==false){
            bt_tab9.setImageResource(R.drawable.category9_copy);
        }
        if(selectedCategory[10]==false){
            bt_tab10.setImageResource(R.drawable.category10_copy);
        }
        if(selectedCategory[11]==false){
            bt_tab11.setImageResource(R.drawable.category11_copy);
        }


        ReadData(new firebaseCallback() {
            @Override
            public void setUserMap(String str, HashMap<String, Object> map) {
                AllUserMap.put(str, map);
                cntcallback++;
                System.out.println(cntcallback);
                if(cntcallback==4){
                    RecommendBrandController mController = new RecommendBrandController();
                    ArrayList<String> tmpList = new ArrayList<>();
                    tmpList.addAll(0, mController.pearsonCheck(id, AllUserMap));
                    for(int i = 0; i<tmpList.size(); i++){
                        if(!selectedBrand.contains(tmpList.get(i)))
                            selectedBrand.add(tmpList.get(i));
                    }
                    System.out.println("추천브랜드: "+ selectedBrand);
                    adapter.notifyDataSetChanged();
                }

            }
        });
        //fragment (추천 브랜드 리스트) 연결


        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,selectedBrand);


        listView = (ListView) findViewById(R.id.brandlistview);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//사용자가 삭제를 위해 브랜드를 클릭했을 때

            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long id) {

                Toast.makeText(getApplicationContext(), "선호 브랜드는 최소 3개 이상이여야 합니다.", Toast.LENGTH_LONG).show();
                brNameList.add(((TextView)view).getText().toString());
                adapter.notifyDataSetChanged();
            }
        });
        final ArrayList<String> catList = new ArrayList<>();
        for(int i = 1; i<selectedCategory.length; i++){
            if(selectedCategory[i]==true){
                catList.add("c"+i);
            }
        }

        submit = (Button) findViewById(R.id.button2);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecommendBrand.this, PathSelectBoundary.class);
                intent.putStringArrayListExtra("brNameList", brNameList);
                intent.putStringArrayListExtra("catList", catList);
                intent.putExtra("id",id);
                intent.putExtra("pw",pw);
                intent.putExtra("X",currentX);
                intent.putExtra("Y",currentY);
                startActivity(intent);
            }
        });
    }
    public void ReadData(final firebaseCallback fb){
        db = FirebaseFirestore.getInstance();
        pathRef = db.collection("user");
        selectedUser = new ArrayList<>();
        int i=0;
        pathRef
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                selectedUser.add(document.getId());
                                final String checked = document.getId();
                                // adapter.notifyDataSetChanged();
                                System.out.println(document.getId());
                                RateRef = db.collection("user").document(checked).collection("brandRate");
                                RateRef
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    HashMap<String, Object> tmpMap = new HashMap<>();
                                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                                        String brand = document.getId();//브랜드명
                                                        tmpMap.put(brand, document.get("rate")); //브랜드명, 레이트값
                                                        System.out.println("244번째 줄" + document.getId());
                                                    }
                                                    fb.setUserMap(checked, tmpMap);//유저이름 (브랜드명,레이트값)
                                                } else {
                                                    //  Log.d(TAG, "Error getting documents: ", task.getException());
                                                }
                                            }
                                        });

                            }
                        } else {
                            //  Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
    public interface firebaseCallback{
        void setUserMap(String str , HashMap<String, Object> map);
    }

}
