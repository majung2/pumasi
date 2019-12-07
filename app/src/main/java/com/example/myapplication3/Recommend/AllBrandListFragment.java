package com.example.myapplication3.Recommend;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication3.Entity.Brand;
import com.example.myapplication3.R;

import java.util.ArrayList;

public class AllBrandListFragment extends Fragment {

    public AllBrandListFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //추천로직 컨트롤러로 추천 리스트생성 => 받아온만큼 체크박스 형식으로 출력.(방법) =>사용자는 원하는 브랜드를 선택 (이부분 어디서 조작할지 모르겠음) =>
        //추천로직 컨트롤러 작동 메소드 => 엔티티클래스에서 디비 불러옴 => 컨트롤러에서 로직 결과 => 엔티티클래스에서 디비 작성
        // => 추천로직 컨트롤러는 리스트뷰에 해당 브랜드들을 추가 => 바운더리에서 화면 보여줌
        RecommendBrandController brController = new RecommendBrandController();
        ArrayList<ArrayList<Brand>> brList = new ArrayList<>();
        brController.getAllbrand();
        brList = brController.getBrList();


        return inflater.inflate(R.layout.all_brand_list_fragment, container, false);


    }

}
