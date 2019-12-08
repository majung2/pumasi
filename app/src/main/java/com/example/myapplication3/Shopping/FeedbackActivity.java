package com.example.myapplication3.Shopping;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.Login.MainActivity;
import com.example.myapplication3.MyPage.MyPageSelectMenu;
import com.example.myapplication3.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class FeedbackActivity extends AppCompatActivity {

    //---------------------- 레이아웃 관련 변수 ---------------------------------
    Button enter;
    Button close;
    ListView feedbackListView;
    FeedbackAdapter feedbackAdapter;
    BrandAdapter brandAdapter;


    // ---------------------- Firebase 관련 변수 -------------------------------
    FirebaseFirestore db;
    CollectionReference brandRateRef;
    CollectionReference pathRef;

    //---------------------- 변수 ----------------------------
    String rate;
    String id;
    String pw;
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
                    FeedbackActivity.this,
                    MyPageSelectMenu.class); // ?ㅼ쓬 ?섏뼱媛??대옒??吏??
            intent.putExtra("id",id);
            intent.putExtra("pw",pw);

            startActivity(intent);
        }
        else if(state==1){
            Toast.makeText(FeedbackActivity.this,"로그아웃되었습니다.",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(// 다음 화면으로 전환
                    FeedbackActivity.this,
                    MainActivity.class);

            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }//옵션메뉴
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        // --------------------- 화면 요소 세팅 ----------------------------
        enter = findViewById(R.id.enter);
        close = findViewById(R.id.close);
        feedbackListView = findViewById(R.id.brandList);
        brandAdapter = new BrandAdapter();
        feedbackAdapter = new FeedbackAdapter();
        feedbackListView.setAdapter(feedbackAdapter);

        // --------------------- Firebase 변수 세팅 ----------------------------------------
        db = FirebaseFirestore.getInstance();
        pathRef = db.collection("user").document("abc123")
                .collection("path").document("1").collection("brand");
        brandRateRef = db.collection("user").document("abc123").collection("brandRate");

        // ------------------------- 작동 기능 정의 --------------------------------


        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();

                // 결과를 성공 (내용을 정상적으로 담았음)으로 설정하고 소포를 부친다.
                setResult(RESULT_OK, intent);

                // 화면을 끝낸다.
                finish();
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
                                feedbackAdapter.addItem(brand);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });


        // close (X 버튼)를 눌렀을 때 실행될 기능 정의
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 새로운 Intent (화면 간에 전달할 소포)를 만든다.
                Intent intent = new Intent();

                // 결과를 실패 (내용을 담지 않고 종료)으로 설정하고 소포를 부친다.
                setResult(RESULT_CANCELED, intent);

                // 화면을 끝낸다.
                finish();
            }
        });
        // -------------------------------------------------------------------------
    }
    // -------------------------------------------------------------------------
    // onCreate 함수 종료
    // =========================================================================

    public void checkRating(String brandId, String grade){

        pathRef.document(brandId).update("grade", grade);
    }
}
