package com.example.myapplication3.MyPage;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

import com.example.myapplication3.Login.MainActivity;
import com.example.myapplication3.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PreferNonpreferBrandBoundary extends AppCompatActivity implements MyPageController.MyPageControlBrandCallback{//선호,비선호 브랜드 리스트 보는 화면
    private ArrayList<String> preferBrands=new ArrayList<>();//선호 브랜드 리스트
    private ArrayList<String> nonPreferBrands=new ArrayList<>();// 비선호 브랜드 리스트
    private Button changePrefer;
    private Button changeNonPrefer;
    private Button finishNP;
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
    private MyPageController controller;
    private ArrayList<String> providersList;
    private String selectedCategory;
    private String selectedOnSpinner;
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
            /*Intent intent = new Intent(// 다음 화면으로 전환
                    MainActivity.this,
                    MyPageSelectMenu.class); // ?ㅼ쓬 ?섏뼱媛??대옒??吏??
            intent.putExtra("id",currentUser.getId());
            intent.putExtra("pw",currentUser.getPw());

            startActivity(intent);*/
            Toast toast = Toast.makeText(getApplicationContext(),"현재 기능이 마이페이지입니다", Toast.LENGTH_LONG);
            toast.show();
        }
        else if(state==1){
            Toast.makeText(PreferNonpreferBrandBoundary.this,"로그아웃되었습니다.",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(// 다음 화면으로 전환
                    PreferNonpreferBrandBoundary.this,
                    MainActivity.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prefer_nonprefer_brand_info);

        //이전 화면으로부터 넘겨받은 유저에 대한 정보들
        Intent intent=getIntent();
        id = intent.getStringExtra("id");
        pw = intent.getStringExtra("pw");




        //버튼 연결
       changePrefer = (Button) findViewById(R.id.preferbrandadd);
       changeNonPrefer = (Button) findViewById(R.id.nonpreferbrandadd);

       finishNP = (Button) findViewById(R.id.finishNPButton);

       //컨트롤러 통해 선호, 비선호 브랜드 받아옴
        controller = new MyPageController();
        controller.MyPageControllerBrand(this);
        controller.setMyPageControlUser(id,pw);
        controller.getPreferbrands();
        System.out.println(preferBrands);
        controller.getNonPreferbrands();
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
        sp.setAdapter(adp);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("카테고리를 선택해주세요.");
        builder.setPositiveButton("선택 완료", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(// 다음 화면으로 전환
                        PreferNonpreferBrandBoundary.this,
                        AddPreferNonPreferBoundary.class); // 개인정보 수정관련 클래스
                intent.putExtra("id",id);
                intent.putExtra("pw",pw);
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
                Intent intent = new Intent(// 다음 화면으로 전환
                        PreferNonpreferBrandBoundary.this,
                        AddPreferNonPreferBoundary.class); // 개인정보 수정관련 클래스
                intent.putExtra("id",id);
                intent.putExtra("pw",pw);
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

    @Override
    public void getPrefer2(String brand)
    {

            preferBrands.add(brand);
            System.out.println(brand);
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,preferBrands);
        listView = (ListView) findViewById(R.id.selected_1);

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//사용자가 삭제를 위해 브랜드를 클릭했을 때

            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long id) {


                if((preferBrands.size()-1)<3){
                    Toast.makeText(getApplicationContext(), "선호 브랜드는 최소 3개 이상이여야 합니다.", Toast.LENGTH_LONG).show();
                }
                else{
                    controller.deletBrand((String)adapterView.getItemAtPosition(position),"preferbrand");
                    preferBrands.remove(position);
                }
                adapter.notifyDataSetChanged();
            }
        });

        }

    @Override
    public void getNonPrefer2(String brand) {//비선호 브랜드 리스트업
        nonPreferBrands.add(brand);
        System.out.println(brand);
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
                else{
                    controller.deletBrand((String)adapterView.getItemAtPosition(position),"nonpreferbrand");
                    nonPreferBrands.remove(position);
                }
                adapter2.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void finishDeleteBrand2() {
        Toast.makeText(getApplicationContext(), "해당 브랜드가 삭제되었습니다.", Toast.LENGTH_LONG).show();
    }

    public void onClickFinishNP(View v){//finish버튼 클릭시

        Intent intent = new Intent(// 다음 화면으로 전환
                PreferNonpreferBrandBoundary.this,
                MyPageSelectMenu.class); // 선호비선호 브랜드 조회 관련 클래스

        intent.putExtra("id",id);
        intent.putExtra("pw",pw);


        startActivity(intent);
        adapter.notifyDataSetChanged();

    }
}



