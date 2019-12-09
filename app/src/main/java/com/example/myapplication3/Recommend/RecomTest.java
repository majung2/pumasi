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
import java.util.List;
import java.util.Queue;

public class RecomTest extends AppCompatActivity {
    private ImageView bt_tab1, bt_tab2, bt_tab3, bt_tab4, bt_tab5,
            bt_tab6, bt_tab7, bt_tab8, bt_tab9, bt_tab10, bt_tab11;
    private Button submit, allBrand;
    private boolean[] selectedCategory ;
    private String id;
    private String pw;
    private Integer currentX;
    private Integer currentY;
    private ArrayList<String> brNameList = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ArrayList<String> selectedBrand;
    private  ListView listView;

    FirebaseFirestore db;
    CollectionReference brandRateRef;
    CollectionReference pathRef;
    private ArrayList<String> selectedUser;
    private CollectionReference RateRef;

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
                    RecomTest.this,
                    MyPageSelectMenu.class); // ?ㅼ쓬 ?섏뼱媛??대옒??吏??
            intent.putExtra("id",id);
            intent.putExtra("pw",pw);

            startActivity(intent);
        }
        else if(state==1){
            Toast.makeText(RecomTest.this,"로그아웃되었습니다.",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(// 다음 화면으로 전환
                    RecomTest.this,
                    MainActivity.class);

            startActivity(intent);

        }


        return super.onOptionsItemSelected(item);
    }//옵션메뉴
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brandrecom_test);
        selectedBrand = new ArrayList<>();
        selectedCategory = new boolean[12]; //이전화면에서 셀렉한 카테고리 받아옴
        Intent intent=getIntent();
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
        /*else{
            btnOnClick(bt_tab1, 1, id, pw, selectedCategory);
        }*/
        if(selectedCategory[2]==false){
            bt_tab2.setImageResource(R.drawable.category2_copy);
        }
        /*else{
            btnOnClick(bt_tab2, 2, id, pw, selectedCategory);
        }*/
        if(selectedCategory[3]==false){
            bt_tab3.setImageResource(R.drawable.category3_copy);
        }
        /*else{
            btnOnClick(bt_tab3, 3, id, pw, selectedCategory);
        }*/
        if(selectedCategory[4]==false){
            bt_tab4.setImageResource(R.drawable.category4_copy);
        }
        /*else{
            btnOnClick(bt_tab4, 4, id, pw, selectedCategory);
        }*/
        if(selectedCategory[5]==false){
            bt_tab5.setImageResource(R.drawable.category5_copy);
        }
        /*else{
            btnOnClick(bt_tab5, 5, id, pw, selectedCategory);
        }*/
        if(selectedCategory[6]==false){
            bt_tab6.setImageResource(R.drawable.category6_copy);
        }
        /*else{
            btnOnClick(bt_tab6, 6, id, pw, selectedCategory);
        }*/
        if(selectedCategory[7]==false){
            bt_tab7.setImageResource(R.drawable.category7_copy);
        }
        /*else{
            btnOnClick(bt_tab7, 7, id, pw, selectedCategory);
        }*/
        if(selectedCategory[8]==false){
            bt_tab8.setImageResource(R.drawable.category8_copy);
        }
        /*else{
            btnOnClick(bt_tab8, 8, id, pw, selectedCategory);
        }*/
        if(selectedCategory[9]==false){
            bt_tab9.setImageResource(R.drawable.category9_copy);
        }
        /*else{
            btnOnClick(bt_tab9, 9, id, pw, selectedCategory);
        }*/
        if(selectedCategory[10]==false){
            bt_tab10.setImageResource(R.drawable.category10_copy);
        }
        /*else{
            btnOnClick(bt_tab10, 10, id, pw, selectedCategory);
        }*/
        if(selectedCategory[11]==false){
            bt_tab11.setImageResource(R.drawable.category11_copy);
        }
        /*else{
            btnOnClick(bt_tab11, 11, id, pw, selectedCategory);
        }*/

        submit = (Button) findViewById(R.id.button2);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecomTest.this, PathSelectBoundary.class);
            }
        });


        //fragment (추천 브랜드 리스트) 연결
        //selectedBrand.add("sample");
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
                                String checked = document.getId();
                               // adapter.notifyDataSetChanged();
                                System.out.println(document.getId());
                                RateRef = db.collection("user").document(checked).collection("brandRate");
                                RateRef
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                                       selectedBrand.add(document.getId());
                                                     //   selectedBrand.add(document.get("rate"));
                                                        adapter.notifyDataSetChanged();
                                                        System.out.println(document.getId());
                                                    }
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

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,selectedBrand);
        listView = (ListView) findViewById(R.id.brandlistview);

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//사용자가 삭제를 위해 브랜드를 클릭했을 때

            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long id) {

                Toast.makeText(getApplicationContext(), "선호 브랜드는 최소 3개 이상이여야 합니다.", Toast.LENGTH_LONG).show();

                adapter.notifyDataSetChanged();
            }
        });



    }
    public void changeTab(int num, String id, String pw, boolean[] selectedCategory){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        RecommendFragment fragment = new RecommendFragment();
        Bundle bundle = new Bundle(3);
        bundle.putString("id", id);
        bundle.putString("pw", pw);
        bundle.putInt("tabNumber", num);
        bundle.putBooleanArray("selectedCategory", selectedCategory);
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();

    }
    public void btnOnClick(ImageView btn, final int i, final String id, final String pw, final boolean[] selectedCategory){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTab(i, id, pw, selectedCategory);
            }
        });
    }
    public void brNameListSet(ArrayList<String> list){
        for(int i = 0; i<list.size(); i++){
            brNameList.add(list.get(i));
        }
    }
}
