package com.example.myapplication3.PreferNonPreferBrandSelect;

import android.os.Bundle;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;

import com.example.myapplication3.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {


    public Fragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //엔티티에서 브랜드 가져옴 => 해당 카테고리의 브랜드 체크박스 리스트생성 => 사용자는 선호/비선호 브랜드를 선택 =>
        //선호/비선호 디비에 저장

        return inflater.inflate(R.layout.fragment_fragment1, container, false);


    }

}