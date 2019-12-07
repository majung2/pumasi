package com.example.myapplication3.Recommend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.myapplication3.R;

public class AllBrandList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_brand_list);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        AllBrandListFragment fragment = new AllBrandListFragment();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();

    }
}
