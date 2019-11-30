package com.example.myapplication3.MyPage;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

    private MyPageController controller;

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


       //컨트롤러 통해 선호, 비선호 브랜드 받아옴
        controller = new MyPageController();
        controller.MyPageControllerBrand(this);
        controller.setMyPageControlUser(id,pw);
        controller.getPreferbrands();
        System.out.println(preferBrands);
        //첫번째 리스트뷰 어댑터에 연결


        //두 번쨰 리스트 뷰 어댑터에 연결
        adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,nonPreferBrands);
        listView2 = (ListView) findViewById(R.id.selected_2);

        listView2.setAdapter(adapter2);

       /* adapter2.add("두번째 리스트");
        adapter2.add("열심히");
        adapter2.add("공부합시다.");
        adapter2.add("홍로이드");
        adapter2.add("리스트뷰");*/

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long id) {

                // select route name or visited date(?)
                String selectedPath = (String)adapterView.getItemAtPosition(position);
                deleteHandler();//브랜드 클릭시 alert dialog 띄움
                adapter2.notifyDataSetChanged();
            }
        });

        //선호 브랜드 추가하기 버튼 클릭한 경우



    }



    public void onClickAdd(View v) {//사용자가 추가하기 버튼을 눌렀을 경우, alertdialog 창을 띄워 추가할 브랜드를 선택하도록 한다.
        AlertDialog.Builder builder = new AlertDialog.Builder(PreferNonpreferBrandBoundary.this);
        // String array for alert dialog multi choice items
        String[] colors = new String[]{
                "Red",
                "Green",
                "Blue",
                "Purple",
                "Olive"
        };

        ArrayList <String> BrandList = new ArrayList<String>();
        controller.getAllBrands();
        // Boolean array for initial selected items
        final boolean[] checkedColors = new boolean[]{
                false, // Red
                true, // Green
                false, // Blue
                true, // Purple
                false // Olive

        };

        // Convert the color array to list
        final List<String> colorsList = Arrays.asList(colors);

        builder.setMultiChoiceItems(colors, checkedColors, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                // Update the current focused item's checked status
                checkedColors[which] = isChecked;

                // Get the current focused item
                String currentItem = colorsList.get(which);

                // Notify the current action
                Toast.makeText(getApplicationContext(),
                        currentItem + " " + isChecked, Toast.LENGTH_SHORT).show();
            }
        });

        // Specify the dialog is not cancelable
        builder.setCancelable(false);

        // Set a title for alert dialog
        builder.setTitle("Your preferred colors?");

        // Set the positive/yes button click listener
        builder.setPositiveButton("선택 완료", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when click positive button

                for (int i = 0; i<checkedColors.length; i++){
                    boolean checked = checkedColors[i];
                    if (checked) {

                    }
                }
            }
        });

        // Set the negative/no button click listener
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when click the negative button
            }
        });


        AlertDialog dialog = builder.create();
        // Display the alert dialog on interface
        dialog.show();
    }




    public void deleteHandler(){//사용자가 브랜드를 클릭했을 때
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("브랜드 삭제").setMessage("선택하신 브랜드를 목록에서 삭제하시겠습니까?");

        builder.setPositiveButton("예", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                Toast.makeText(getApplicationContext(), "OK Click", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                Toast.makeText(getApplicationContext(), "Cancel Click", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void getPrefer2(String brand)
    {

            preferBrands.add(brand);
            System.out.println(brand);
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,preferBrands);
        listView = (ListView) findViewById(R.id.selected_1);

        listView.setAdapter(adapter);
        //listView.setOnItemClickListener(this);

       /* for(int i=0;i<preferBrands.size();i++){
            adapter.add(preferBrands[i]);
        }

        adapter.add("안드로이드");
        adapter.add("열심히");
        adapter.add("공부합시다.");
        adapter.add("홍로이드");
        adapter.add("리스트뷰");*/

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long id) {

                // select route name or visited date(?)
                String selectedPath = (String)adapterView.getItemAtPosition(position);
                deleteHandler();//브랜드 클릭시 alertdialog 띄움

                adapter.notifyDataSetChanged();
            }
        });

        }
    }



