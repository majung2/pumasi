package com.example.myapplication3.Recommend;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.Entity.Brand;
import com.example.myapplication3.Entity.Path;
import com.example.myapplication3.Login.MainActivity;
import com.example.myapplication3.MyPage.MyPageSelectMenu;
import com.example.myapplication3.Pathfinding.PathfindingController;
import com.example.myapplication3.R;
import com.example.myapplication3.Shopping.ShoppingActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class PathSelectBoundary extends AppCompatActivity {
    private String id;
    private String pw;
    private Integer currentX;
    private Integer currentY;
    private ArrayList<String> selectedBrands;
    PathfindingController controller;
    private ArrayList<String> selectedCat;
    private Path path1;
    private Path path2;
    private Brand tmpbrand;
    private Integer cnt =0;
    private Button select;
    private FirebaseFirestore db;
    private CollectionReference BrandRef;
    private CollectionReference brRef;
    private ArrayList<Brand> brandList;
    private ArrayList<String > pathrecom= new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView listView;

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
                    PathSelectBoundary.this,
                    MyPageSelectMenu.class);
            intent.putExtra("id",id);
            intent.putExtra("pw",pw);

            startActivity(intent);
        }
        else if(state==1){
            Toast.makeText(PathSelectBoundary.this,"로그아웃되었습니다.",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(// 다음 화면으로 전환
                    PathSelectBoundary.this,
                    MainActivity.class);

            startActivity(intent);

        }


        return super.onOptionsItemSelected(item);
    }//옵션메뉴

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.path_select);
        selectedCat = new ArrayList<>();
        brandList = new ArrayList<>();
        //이전화면에서 유저정보 받아오기
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        pw = intent.getStringExtra("pw");
        currentX = intent.getIntExtra("x",0);
        currentY = intent.getIntExtra("y",0);
        selectedBrands = intent.getStringArrayListExtra("selectedBrands"); //선택된 브랜드 정보
        System.out.println(selectedBrands.get(0));
        System.out.println(selectedBrands.get(1));
        //임시로 카테고리 설정
        selectedCat.add("해외명품");
        System.out.println(currentX);

        //경로 1, 4 추출
        // controller = new PathfindingController(selectedBrands,currentX,currentY,selectedCat);

        db = FirebaseFirestore.getInstance();

        //선택된 카테고리 만큼
        BrandRef = db.collection("shoppingMall").document("M1").collection("category").document("c1").collection("brand");

        pathrecom.add("추천된 루트입니다.");
        readData(new FirebaseCallback() {
            @Override
            public void onCallback(ArrayList<Brand> recomBrand) {
                cnt++;
                if(cnt==11){
                    controller = new PathfindingController(brandList,currentX,currentY,selectedCat);
                    path1 = controller.getPath1();
                    path2 = controller.getPath2();
                    pathrecom.add(path1.toString());

                    adapter.notifyDataSetChanged();
                }
            }
        });

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,pathrecom);
        listView = (ListView) findViewById(R.id.brandList);

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//사용자가 삭제를 위해 브랜드를 클릭했을 때

            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long id) {

                Toast.makeText(getApplicationContext(), "선호 브랜드는 최소 3개 이상이여야 합니다.", Toast.LENGTH_LONG).show();

                adapter.notifyDataSetChanged();
            }
        });

        int i=0;


    }

    public void onClick1(View v){
        // 디비에 선택한 패스 저장
        System.out.println( ); //현재패스 찍어보기
        Next();
    }

    public void Next(){
        Intent intent = new Intent(// 다음 화면으로 전환
                PathSelectBoundary.this,
                ShoppingActivity.class); //쇼핑 액티비티로 이동
        intent.putExtra("id",id);
        intent.putExtra("pw",pw);

        startActivity(intent);

    }
    public void readData(final FirebaseCallback fb) {
        db = FirebaseFirestore.getInstance();
        BrandRef = db.collection("shoppingMall").document("M1").collection("category");
        BrandRef
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                             //   selectedUser.add(document.getId());
                                String checked = document.getId();
                                // adapter.notifyDataSetChanged();
                                System.out.println(document.getId());
                                brRef = db.collection("shoppingMall").document("M1").collection("category").document(checked).collection("brand");
                                brRef
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                                       // selectedBrand.add(document.getId());
                                                        //   selectedBrand.add(document.get("rate"));
                                                        for(String brand: selectedBrands){
                                                            if(brand.equals(document.get("bName"))){
                                                                tmpbrand = new Brand();
                                                                tmpbrand.setSpotName(document.get("bName").toString());
                                                                tmpbrand.setSpotLocation(Integer.parseInt( document.getData().get("x").toString()),Integer.parseInt(document.getData().get("y").toString()));
                                                                tmpbrand.setSpotFloor(Integer.parseInt(document.getData().get("floor").toString()));
                                                                brandList.add(tmpbrand);
                                                                System.out.println("찾았다 일치");
                                                                System.out.println(tmpbrand.getSpotName());
                                                            }
                                                        }
                                                        adapter.notifyDataSetChanged();
                                                        System.out.println(document.getId());
                                                    }

                                                    fb.onCallback(brandList);

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
public interface FirebaseCallback{
        void onCallback(ArrayList<Brand> recomBrand);
}

}