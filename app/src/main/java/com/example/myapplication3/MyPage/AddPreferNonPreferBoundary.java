package com.example.myapplication3.MyPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication3.R;

import java.util.ArrayList;

public class AddPreferNonPreferBoundary extends AppCompatActivity implements MyPageController.AddBrandCallback,MyPageController.userBrancCallback{
    String id;
    String pw;
    String selectedCategory;
    MyPageController controller;
    ArrayList<String> selectedBrands;
    private ArrayAdapter<String> adapter;
    private String preferNon;
    private ListView listView;
    MyPageController controller2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_prefer_nonprefer);
        Intent intent=getIntent();
        id = intent.getStringExtra("id");
        pw = intent.getStringExtra("pw");
        selectedCategory = intent.getStringExtra("category");
        preferNon = intent.getStringExtra("prefernonprefer");
        controller = new MyPageController();
        controller.MyPageControllerBrand(this);
        controller.setAddControlUser(id,pw);
        controller.getCatBrands(selectedCategory);

        controller2 = new MyPageController();
        controller2.MyPageAddController(this);
        controller2.setAddControlUser(id,pw);

        selectedBrands= new ArrayList<>();
    }

    @Override
    public void finishFindBrands2(String bName) {
        selectedBrands.add(bName);
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,selectedBrands);
        listView = (ListView) findViewById(R.id.brand_list);

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//사용자가 추가를 위해 브랜드를 클릭했을 때

            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long id) {
                //controller.setAddBrandUser(AddPreferNonPreferBoundary.this);

               controller2.addBrand((String)adapterView.getItemAtPosition(position),preferNon);

                adapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void finishAddBrand2() {
        Toast.makeText(getApplicationContext(), "해당 브랜드를 추가하였습니다.", Toast.LENGTH_LONG).show();
    }
}
