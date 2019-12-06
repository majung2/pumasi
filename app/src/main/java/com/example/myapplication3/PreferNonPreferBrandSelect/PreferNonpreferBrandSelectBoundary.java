package com.example.myapplication3.PreferNonPreferBrandSelect;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.R;
import com.example.myapplication3.MyPage.*;
import com.example.myapplication3.Login.*;

import java.util.ArrayList;

public class PreferNonpreferBrandSelectBoundary extends AppCompatActivity implements LoginController.LoginControlBrandCallback{//선호,비선호 브랜드 리스트 보는 화면
    public static ArrayList<String> preferBrands=new ArrayList<>();//선호 브랜드 리스트
    public static ArrayList<String> nonPreferBrands=new ArrayList<>();// 비선호 브랜드 리스트

    private ArrayList<String> preferBrandslist = new ArrayList<>();
    private ArrayList<String> nonPreferBrandslist = new ArrayList<>();

    private Button changePrefer;
    private Button changeNonPrefer;
    private Button finishRegister;
    private TextView selected_item_textview;
    private TextView selected_item_textview2;
    private ArrayAdapter<String>    adapter;
    private ArrayAdapter<String>    adapter2;
    private ListView listView;
    private ListView listView2;
    private String id;
    private String pw;
    private String name;
    private String sex;
    private Integer age;
    private boolean decision;
    private LoginController controller;
    private ArrayList<String> providersList;
    private String selectedCategory;
    private String selectedOnSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prefer_nonprefer_brand_select);

        //이전 화면으로부터 넘겨받은 유저에 대한 정보들
        Intent intent=getIntent();
        id = intent.getStringExtra("id");
        pw = intent.getStringExtra("pw");
        sex= intent.getStringExtra("sex");
        name = intent.getStringExtra("name");
        age = intent.getIntExtra("age",0);
        if(intent.getStringArrayListExtra("preferlist")!=null){
            preferBrands.addAll(intent.getStringArrayListExtra("preferlist"));}
        if(intent.getStringArrayListExtra("nonpreferlist")!=null){
            nonPreferBrands.addAll(intent.getStringArrayListExtra("nonpreferlist"));}

        System.out.println("선호: "+ preferBrands);
        System.out.println("비선호: "+ nonPreferBrands);


        //버튼 연결
        changePrefer = (Button) findViewById(R.id.preferbrandadd);
        changeNonPrefer = (Button) findViewById(R.id.nonpreferbrandadd);
        finishRegister = (Button) findViewById(R.id.finishButton);


        //컨트롤러 통해 선호, 비선호 브랜드 받아옴
        controller = new LoginController();
        controller.LoginControllerBrand(PreferNonpreferBrandSelectBoundary.this);
        controller.setLoginControlUser(id,pw);

        System.out.println(preferBrands);
        providersList= new ArrayList<>();
        providersList.add("해외명품");
        providersList.add("컨템포러리");
        providersList.add("여성의류");
        providersList.add("남성의류");
        providersList.add("컨템포러리");
        providersList.add("진/캐쥬얼/SPA");
        providersList.add("슈즈/핸드백");
        providersList.add("스포츠/골프/아웃도어");
        providersList.add("잡화");
        providersList.add("아동");
        providersList.add("생활");
        providersList.add("기타");


        //
        if(preferBrands !=null) {
            adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, preferBrands);
            listView = (ListView) findViewById(R.id.selected_1);

            listView.setAdapter(adapter);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//사용자가 삭제를 위해 브랜드를 클릭했을 때

                @Override
                public void onItemClick(AdapterView<?> adapterView,
                                        View view, int position, long id) {


                    if ((preferBrands.size() - 1) < 3) {
                        Toast.makeText(getApplicationContext(), "선호 브랜드는 최소 3개 이상이여야 합니다.", Toast.LENGTH_LONG).show();
                    }

                    // controller.deletBrand((String)adapterView.getItemAtPosition(position),"preferbrand");
                    preferBrands.remove(position); //제거하기

                    adapter.notifyDataSetChanged();
                }
            });
        }
        //

        //
        if(nonPreferBrands !=null) {
            adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,nonPreferBrands);
            listView2 = (ListView) findViewById(R.id.selected_2);

            listView2.setAdapter(adapter2);

            listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {//삭제를 위해 비선호 브랜드를 클릭했을 때

                @Override
                public void onItemClick(AdapterView<?> adapterView,
                                        View view, int position, long id) {

                    if((nonPreferBrands.size()-1)<3){
                        Toast.makeText(getApplicationContext(), "비선호 브랜드는 최소 3개 이상이여야 합니다.", Toast.LENGTH_LONG).show();
                    }
                    //controller.deletBrand((String)adapterView.getItemAtPosition(position),"nonpreferbrand");
                    nonPreferBrands.remove(position);

                    adapter2.notifyDataSetChanged();
                }
            });
        }
        //
    }



    public void onClickAdd(View v) {//사용자가 추가하기 버튼을 눌렀을 경우, alertdialog 창을 띄워 추가할 브랜드를 선택하도록 한다.-선호 브랜드 추가


        final ArrayAdapter<String> adp = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, providersList);

        final Spinner sp = new Spinner(this);
        sp.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedOnSpinner=adapterView.getItemAtPosition(i).toString();
                if(selectedOnSpinner.equals("해외명품")){
                    selectedCategory="c1";
                }
                else if( selectedOnSpinner.equals("컨템포러리")){
                    selectedCategory="c2";
                }
                else if( selectedOnSpinner.equals("여성의류")){
                    selectedCategory="c3";
                }else if( selectedOnSpinner.equals("남성의류")){
                    selectedCategory="c4";
                }
                else if( selectedOnSpinner.equals("진/캐주얼/SPA")){
                    selectedCategory="c5";
                }
                else if( selectedOnSpinner.equals("슈즈/핸드백")){
                    selectedCategory="c6";
                }
                else if( selectedOnSpinner.equals("스포츠/골프/아웃도어")){
                    selectedCategory="c7";
                }
                else if( selectedOnSpinner.equals("잡화")){
                    selectedCategory="c8";
                }
                else if( selectedOnSpinner.equals("아동")){
                    selectedCategory="c9";
                }
                else if( selectedOnSpinner.equals("생활")){
                    selectedCategory="c10";
                } else if( selectedOnSpinner.equals("기타")){
                    selectedCategory="c11";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selectedCategory="c1";
            }
        });
        sp.setAdapter(adp); //스피너로 카테고리 선택

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("카테고리를 선택해주세요.");
        builder.setPositiveButton("선택 완료", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(// 해당 카테고리에 속하는 브랜드 추가 화면으로 전환
                        PreferNonpreferBrandSelectBoundary.this,
                        RegisterAddPreferNonPreferBoundary.class);
                intent.putExtra("id",id);
                intent.putExtra("pw",pw);
                intent.putExtra("age",age);
                intent.putExtra("sex",sex);
                intent.putExtra("name",name);
                intent.putExtra("category",selectedCategory);
                intent.putExtra("prefernonprefer","preferbrand");
                startActivity(intent);

            }
        });

        // Set the negative/no button click listener
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when click the negative button
            }
        });
        builder.setView(sp);
        builder.create().show();
    }

    public void onClickAdd2(View v) {//사용자가 추가하기 버튼을 눌렀을 경우, alertdialog 창을 띄워 추가할 브랜드를 선택하도록 한다.- 비선호 브랜드 추가


        final ArrayAdapter<String> adp = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, providersList);

        final Spinner sp = new Spinner(this);
        sp.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedOnSpinner=adapterView.getItemAtPosition(i).toString();
                if(selectedOnSpinner.equals("해외명품")){
                    selectedCategory="c1";
                }
                else if( selectedOnSpinner.equals("컨템포러리")){
                    selectedCategory="c2";
                }
                else if( selectedOnSpinner.equals("여성의류")){
                    selectedCategory="c3";
                }else if( selectedOnSpinner.equals("남성의류")){
                    selectedCategory="c4";
                }
                else if( selectedOnSpinner.equals("진/캐주얼/SPA")){
                    selectedCategory="c5";
                }
                else if( selectedOnSpinner.equals("슈즈/핸드백")){
                    selectedCategory="c6";
                }
                else if( selectedOnSpinner.equals("스포츠/골프/아웃도어")){
                    selectedCategory="c7";
                }
                else if( selectedOnSpinner.equals("잡화")){
                    selectedCategory="c8";
                }
                else if( selectedOnSpinner.equals("아동")){
                    selectedCategory="c9";
                }
                else if( selectedOnSpinner.equals("생활")){
                    selectedCategory="c10";
                } else if( selectedOnSpinner.equals("기타")){
                    selectedCategory="c11";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selectedCategory="c1";
            }
        });
        sp.setAdapter(adp);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("카테고리를 선택해주세요.");
        builder.setPositiveButton("선택 완료", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(// 비선호 브랜드 추가 화면으로 전환
                        PreferNonpreferBrandSelectBoundary.this,
                        RegisterAddPreferNonPreferBoundary.class);
                intent.putExtra("id",id);
                intent.putExtra("pw",pw);
                intent.putExtra("age",age);
                intent.putExtra("sex",sex);
                intent.putExtra("name",name);
                intent.putExtra("category",selectedCategory);
                intent.putExtra("prefernonprefer","nonpreferbrand");
                startActivity(intent);


            }
        });

        // Set the negative/no button click listener
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when click the negative button
            }
        });
        builder.setView(sp);
        builder.create().show();
    }

    public void onClickFinishRegister(View v) { //사용자가 가입하기 버튼 (finishRegister) 를 눌렀을때 DB로 들어가 회원 정보 저장

        if( name ==null || id ==null || pw ==null  || age==0 || sex == null || preferBrands.size()< 3 || nonPreferBrands.size()<3)
        {
            Toast.makeText(getApplicationContext(), "선호, 비선호 브랜드는 3개 이상이어야 합니다.", Toast.LENGTH_LONG).show();
        }

        else{
            // Login Controller => User (DB) 새로운 유저 추가하기
            System.out.println("새로운 유저 추가하기 - boundary");
            preferBrandslist = preferBrands;
            nonPreferBrandslist = nonPreferBrands;
            controller.register(name,id,pw,sex,age,preferBrandslist,nonPreferBrandslist);

        }
        System.out.println("name:" + name);
        System.out.println("id:" + id);
        System.out.println("pw:" + pw);
        System.out.println("age:" + age);
        System.out.println("sex:" + sex);
        System.out.println("prefer:" + preferBrandslist);
        System.out.println("nonPrefer:" + nonPreferBrandslist);

    }


    @Override
    public void finishDeleteBrand2() {
        Toast.makeText(getApplicationContext(), "해당 브랜드가 삭제되었습니다.", Toast.LENGTH_LONG).show();
    }

}




