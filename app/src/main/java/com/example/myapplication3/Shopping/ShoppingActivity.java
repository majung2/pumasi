package com.example.myapplication3.Shopping;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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

        /*pathRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                                                @Override
                                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                    Map<String, Object> visited = new HashMap<>();
                                                    visited.put("visited", true);

                                                    for (QueryDocumentSnapshot doc : task.getResult()) {
                                                        System.out.println(doc.getId());
                                                        System.out.println(doc.getData().get("brandname").toString());
                                                    }
                                                }
                                            });*/

       brandRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Brand brand = documentSnapshot.toObject(Brand.class);
                brandAdapter.addItem(brand);

            }
        });





       /* pathRef.orderBy("timestamp").addSnapshotListener(new EventListener<QuerySnapshot>() {
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
        });*/
    }
    // -------------------------------------------------------------------------
    // onCreate 함수 종료
    // =========================================================================
    public void checkBought(String brandId, Map<String, Boolean> bought){
        bought = new HashMap<>();
        bought.put(brandId, true);
        pathRef.document("brand").update("bought", bought);
    }

    public void checkNotBought(String brandId, Map<String, Boolean> bought){
        bought = new HashMap<>();
        bought.put(brandId, false);
        pathRef.document("brand").update("bought", bought);
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

        // 창구번호 100에서 새 트윗 내용을 얻으러 출발하였으므로 다시 100으로 돌아온다.
        // 한 화면에서 갔다올 수 있는 화면이 여러 개일 때 구분하기 위해 사용
        // (현재는 하나뿐이므로 의미는 없음)
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {

            if (resultCode == RESULT_OK) {

                Float rate = data.getFloatExtra("rate", 0);
                brandRateRef.document("brandRate").update("rate", rate); //id별 따로 저장해야함.

                // --------------------------------------------------------------------------
            }
        }
    }
    // -------------------------------------------------------------------------
    // onActivityResult 함수 종료
    // =========================================================================
}
// MainActivity 종료