package com.example.myapplication3.Recommend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myapplication3.R;

import java.util.ArrayList;

public class AllBrandList extends AppCompatActivity
implements AllBrandListFragment.brNameListListener{
    private ImageView bt_tab1, bt_tab2, bt_tab3, bt_tab4, bt_tab5,
            bt_tab6, bt_tab7, bt_tab8, bt_tab9, bt_tab10, bt_tab11;
    ArrayList<String> brNameList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_brand_list);
        bt_tab1 = (ImageView) findViewById(R.id.ImageView1);
        bt_tab2 = (ImageView) findViewById(R.id.ImageView2);
        bt_tab3 = (ImageView) findViewById(R.id.ImageView3);
        bt_tab4 = (ImageView) findViewById(R.id.ImageView4);
        bt_tab5 = (ImageView) findViewById(R.id.ImageView5);
        bt_tab6 = (ImageView) findViewById(R.id.ImageView6);
        bt_tab7 = (ImageView) findViewById(R.id.ImageView7);
        bt_tab8 = (ImageView) findViewById(R.id.ImageView8);
        bt_tab9 = (ImageView) findViewById(R.id.ImageView9);
        bt_tab10 = (ImageView) findViewById(R.id.ImageView10);
        bt_tab11 = (ImageView) findViewById(R.id.ImageView11);
        RecommendBrandController brandController = new RecommendBrandController();
        brandController.getAllbrand();
        btnOnClick(bt_tab1, 1); btnOnClick(bt_tab2, 2); btnOnClick(bt_tab3, 3); btnOnClick(bt_tab4, 4); btnOnClick(bt_tab5, 5);
        btnOnClick(bt_tab6, 6); btnOnClick(bt_tab7, 7); btnOnClick(bt_tab8, 8); btnOnClick(bt_tab9, 9);
        btnOnClick(bt_tab10, 10); btnOnClick(bt_tab11, 11);

    }

    public void changeTab(int num){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        AllBrandListFragment fragment = new AllBrandListFragment();
        Bundle bundle = new Bundle(1);
        bundle.putInt("tabNumber", num);
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();

    }
    public void btnOnClick(ImageView btn, final int i){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTab(i);
            }
        });
    }
    @Override
    public void brNameListSet(ArrayList<String> list){
        for(int i = 0; i<list.size(); i++){
            brNameList.add(list.get(i));
        }
    }
    public void findBrand(String str){

    }
}
