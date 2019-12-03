package com.example.myapplication3.Shopping;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;

// MainActivity
// 메인 화면의 기능을 정의하는 클래스
public class ShoppingActivity extends AppCompatActivity {

    // ------------------------ 레이아웃 관련 변수 -----------------------------
    // 화면에서 컨트롤할 필요가 있는 요소들을 할당하기 위해 변수를 만들어둔다.

    Button refresh;
    Button end;



    ListView brandListView;

    // 리스트뷰에 들어갈 내용을 만들어서 전달할 어댑터를 담을 변수
    BrandAdapter brandAdapter;

    // -------------------------------------------------------------------------



    // ---------------------- Firebase 관련 변수 -------------------------------
    // Firebase를 JAVA 코드로 컨트롤하기 위해 변수를 만들어둔다.
    // Firebase Firestore의 컨트롤 권한을 할당하여 사용하기 위한 변수
    FirebaseFirestore db;


    CollectionReference brandRef;
    // -------------------------------------------------------------------------



    // =========================================================================
    // onCreate
    // 화면이 시작할 때 실행되는 함수
    // -------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // --------------------- Firebase 변수 세팅 --------------------------------
        // Firestore와 컬렉션을 준비해둔 변수에 연결한다.
        // Firebase Firestore 컨트롤 권한을 db에 할당
        db = FirebaseFirestore.getInstance();
        brandRef = db.collection("brands");
        // -------------------------------------------------------------------------


        // ------------------------- 화면 요소 세팅 --------------------------------
        setContentView(R.layout.activity_main);


        refresh = findViewById(R.id.refresh);
        end = findViewById(R.id.end);
        brandListView = findViewById(R.id.brandList);

        // 어댑터를 생성하여 준비해둔 변수에 연결한다.
        brandAdapter = new BrandAdapter();

        // 리스트뷰에 내용을 책임질 어댑터를 연결한다.
        brandListView.setAdapter(brandAdapter);
        // -------------------------------------------------------------------------


        // ------------------------- 작동 기능 정의 --------------------------------
        // send (새 트윗 버튼)를 눌렀을 때 실행될 기능 정의
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  Intent intent = new Intent(MainActivity.this, FeedbackActivity.class);
               // startActivityForResult(intent, 100);

            }
        });

        // Firestore의 twits 컬렉션에 변경이 있을 때 실행될 기능 정의
        // .orderBy() 절을 추가하여 데이터를 timestamp 기준으로 정렬해서 가져오도록 한다.
        brandRef.orderBy("timestamp").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                // 컬렉션의 모든 Document(문서)를 반복한다.
                // 이 때 queryDocumentSnapshots를 그대로 사용하면 새로 추가된 게 아닌 트윗도 계속 반복 추가되므로
                // .getDocumentChanges를 호출하여 변경된 내역이 있는 데이터만 뽑아내 반복한다.
                for(DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()){

                    // 변경 내역이 ADDED(새로 추가됨) 라면
                    if(doc.getType() == DocumentChange.Type.ADDED){


                        Brand brand = doc.getDocument().toObject(Brand.class);

                        brandAdapter.addItem(brand);

                    }
                }
            }
        });
        // -------------------------------------------------------------------------

    }
    // -------------------------------------------------------------------------
    // onCreate 함수 종료
    // =========================================================================



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

        // 창구번호 100에서 새 트윗 내용을 얻으러 출발하였으므로 다시 100으로 돌아온다.
        // 한 화면에서 갔다올 수 있는 화면이 여러 개일 때 구분하기 위해 사용
        // (현재는 하나뿐이므로 의미는 없음)
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {

            // 결과가 성공이라면
            // : 새 트윗 작성 화면에서 트윗 버튼을 누르면 성공
            // : X (닫기) 버튼을 누르면 실패로 정의
            if (resultCode == RESULT_OK) {

                // 결과 소포에서 message 라는 이름표가 붙은 데이터를 꺼내 msg 변수에 할당한다.
                // String 타입이므로 getStringExtra를 사용한다.
                Float rate = data.getFloatExtra("rate", 0);

                // --------- 내용을 얻었으므로 Firestore에 새로 데이터를 입력한다. ----------
                // 입력하기 위해서 HashMap<String, Object> 타입의 변수를 새로 만들어 채워 넣어야 한다.
                HashMap<String, Object> brand = new HashMap<>();

                // 데이터 형태는 .put() 함수에 "이름", "값"으로 집어넣는다.
                // 이름은 Twit 클래스에 정의된 멤버 변수명과 같아야 가져올 때 자동 변환 시킬 수 있다.
                brand.put("name", "BLACKYAK");
                brand.put("rating", rate);


                // twits 컬렉션에 이 데이터를 추가한다.
                brandRef.add(brand);
                // --------------------------------------------------------------------------
            }
        }
    }
    // -------------------------------------------------------------------------
    // onActivityResult 함수 종료
    // =========================================================================
}
// MainActivity 종료