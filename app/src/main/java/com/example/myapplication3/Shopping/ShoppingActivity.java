package com.example.myapplication3.Shopping;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication3.Login.MainActivity;
import com.example.myapplication3.MyPage.MyPageSelectMenu;
import com.example.myapplication3.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import static android.content.ContentValues.TAG;


// MainActivity
// 메인 화면의 기능을 정의하는 클래스
public class ShoppingActivity extends AppCompatActivity {

    // ------------------------ 레이아웃 관련 변수 -----------------------------
    // 화면에서 컨트롤할 필요가 있는 요소들을 할당하기 위해 변수를 만들어둔다.

    Button refresh;
    Button end;
    ListView brandListView;
    private String id;
    private String pw;


    // 리스트뷰에 들어갈 내용을 만들어서 전달할 어댑터를 담을 변수
    BrandAdapter brandAdapter;

    // -------------------------------------------------------------------------



    // ---------------------- Firebase 관련 변수 -------------------------------
    // Firebase를 JAVA 코드로 컨트롤하기 위해 변수를 만들어둔다.
    // Firebase Firestore의 컨트롤 권한을 할당하여 사용하기 위한 변수
    FirebaseFirestore db;
    CollectionReference brandRateRef;
    CollectionReference pathRef;
    DocumentReference brandRef;
    // -------------------------------------------------------------------------


    // =========================================================================
    // onCreate
    // 화면이 시작할 때 실행되는 함수
    // -------------------------------------------------------------------------
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
                    ShoppingActivity.this,
                    MyPageSelectMenu.class); // ?ㅼ쓬 ?섏뼱媛??대옒??吏??
            intent.putExtra("id",id);
            intent.putExtra("pw",pw);

            startActivity(intent);
        }
        else if(state==1){
            Toast.makeText(ShoppingActivity.this,"로그아웃되었습니다.",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(// 다음 화면으로 전환
                    ShoppingActivity.this,
                    MainActivity.class);

            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }//옵션메뉴
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        id = intent.getStringExtra("id");
        pw = intent.getStringExtra("pw");

        // --------------------- Firebase 변수 세팅 --------------------------------
        // Firestore와 컬렉션을 준비해둔 변수에 연결한다.
        // Firebase Firestore 컨트롤 권한을 db에 할당
        db = FirebaseFirestore.getInstance();
        pathRef = db.collection("user").document("abc123")
                .collection("path").document("1").collection("brand");
        brandRateRef = db.collection("brandRate");
        brandRef = db.collection("user").document("abc123")
                .collection("path").document("1").collection("brand").document("1");
        // -------------------------------------------------------------------------


        // ------------------------- 화면 요소 세팅 --------------------------------
        setContentView(R.layout.activity_shopping);
        refresh = findViewById(R.id.refresh);
        end = findViewById(R.id.end);
        brandListView = findViewById(R.id.brandList);


        // 어댑터를 생성하여 준비해둔 변수에 연결한다.
        brandAdapter = new BrandAdapter();

        // 리스트뷰에 내용을 책임질 어댑터를 연결한다.
        brandListView.setAdapter(brandAdapter);
        // -------------------------------------------------------------------------


        // ------------------------- 작동 기능 정의 --------------------------------
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingActivity.this, FeedbackActivity.class);
                startActivityForResult(intent, 100);

            }
        });



        pathRef
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Brand brand = new Brand();
                                brand.id = document.getId();
                                brand.name = document.getString("brandname");
                                brand.bought = document.getBoolean("bought");
                                brandAdapter.addItem(brand);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
    // -------------------------------------------------------------------------
    // onCreate 함수 종료
    // =========================================================================
    public void checkBought(String brandId){
        pathRef.document(brandId).update("bought", true);
    }

    public void checkNotBought(String brandId){
        pathRef.document(brandId).update("bought", false);
    }


    // =========================================================================
    // onActivityResult
    // startActivityForResult 함수를 이용해 떠났던 화면으로
    // 결과를 가지고 다시 돌아왔을 때 실행되는 함수
    // (onCreate -> send.setOnClickListener -> startActivityForResult와 이어짐)

    // 매개변수
    // requestCode : startActivityForResult에서 정의한 창구 번호
    // resultCode : 결과의 성공(RESULT_OK), 실패(RESULT_CANCEL) 여부
    // data : 전달된 결과물 소포(Intent)
    // -------------------------------------------------------------------------
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {

            if (resultCode == RESULT_OK) {
                Intent intent = new Intent(ShoppingActivity.this, MyPageSelectMenu.class);
                startActivityForResult(intent, 200);


            }
        }
    }
    // -------------------------------------------------------------------------
    // onActivityResult 함수 종료
    // =========================================================================
}
