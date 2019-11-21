package com.example.myapplication3.Login;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.myapplication3.Entity.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class LoginController {
    private String name;
    private String id;
    private String pw;
    private String sex;
    private Integer age;
    public  User currentUser;//로그인 후에 유저 정보를 담는 객체
    private FirebaseFirestore db;
    private boolean exists;
    public void LoginController() {//초기화
        this.name=null;
        this.id=null;
        this.pw=null;
        this.sex=null;
        this.age=null;
        this.exists=false;


    }

    public void register(String UserName, String UserId, String UserPW,  String UserSex, Integer UserAge) {//회원가입시 입력된 정보를 유저 클래스로 넘김(유저 클래스에서 최종 데이터 저장)
    this.name=UserName;
    this.id=UserId;
    this.pw = UserPW;
    this.sex = UserSex;
    this.age= UserAge;
    this.exists=false;
        final String TAG = "컨트롤러: ";
        Log.d(TAG,"완성");
    currentUser = new User();
    //currentUser.register(name,id,pw,sex,age);

    }
    public boolean login(String currentid, String currentpassword){//로그인(디비에서 읽어오는 코드 추가할 예정)
        Log.d("결과:", "돌아감");
        exists=false;
        id=currentid;
        pw=currentpassword;
        db = FirebaseFirestore.getInstance();
        DocumentReference ref = db.collection("user").document(currentid);
        ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task< DocumentSnapshot > task) {//해당 아이디의 유저를 찾은 경우
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    if(pw.equals(doc.get("password"))){//해당 아이디, 비밀번호가 일치하는 유저를 찾은 경우
                        exists=true;
                        name =  doc.get("name").toString();
                        sex = doc.get("sex").toString();
                        pw= doc.get("password").toString();
                        age = Integer.parseInt(doc.get("age").toString());
                        System.out.println("찾음");
                        System.out.println(" "+id+name+sex+pw+age);

                        //유저가 존재하므로 currentUser 객체를 만들어 디비에서 읽어온 정보들을 담아놓아야한다. 코딩 필요- 유저 객체를 만든 후-> 로그인 메소드 콜 하면 됨

                    }


                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("없음");
                    }
                });

        return exists;
    }

}