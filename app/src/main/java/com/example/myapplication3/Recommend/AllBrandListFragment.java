package com.example.myapplication3.Recommend;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;

import com.example.myapplication3.Entity.Brand;
import com.example.myapplication3.R;


import java.net.ContentHandler;
import java.util.ArrayList;

public class AllBrandListFragment extends Fragment implements OnButttonClick {

    RecyclerView mRecyclerView = null;
    RecyclerView.LayoutManager layoutManager;
    AllBrListAdapter mAdapter = null;
    ArrayList<AllBrListItem> mList = new ArrayList<>();
    RecommendBrandController brController = new RecommendBrandController();
    ArrayList<ArrayList<Brand>> brList = new ArrayList<>();
    ArrayList<String> brNameList = new ArrayList<>();
    private brNameListListener brListnr;
    public AllBrandListFragment(){

    }
    public void onClick(String brname){
        brNameList.add(brname);
    }
    public interface brNameListListener{
        void brNameListSet(ArrayList<String> list);
    }


    public void onAttach(Context context){
           super.onAttach(context);
           if(context instanceof brNameListListener){
               brListnr = (brNameListListener)context;
           }else{
               throw new RuntimeException(context.toString()+"must implement brNameListListener");
           }

    }
    public void onDetach(){
        super.onDetach();
        brListnr = null;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //추천로직 컨트롤러로 추천 리스트생성 => 받아온만큼 체크박스 형식으로 출력.(방법) =>사용자는 원하는 브랜드를 선택 (이부분 어디서 조작할지 모르겠음) =>
        //추천로직 컨트롤러 작동 메소드 => 엔티티클래스에서 디비 불러옴 => 컨트롤러에서 로직 결과 => 엔티티클래스에서 디비 작성
        // => 추천로직 컨트롤러는 리스트뷰에 해당 브랜드들을 추가 => 바운더리에서 화면 보여줌
        View view = inflater.inflate(R.layout.all_brand_list_fragment, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new AllBrListAdapter(mList, this) ;
        mRecyclerView.setAdapter(mAdapter) ;


        brController.getAllbrand();
        brList = brController.getBrList();
        int tabNumber = getArguments().getInt("tabNumber");
        callAllBrandItem(tabNumber, brList);
        brListnr.brNameListSet(brNameList);

        return view;


    }
    public void callAllBrandItem(int num, ArrayList<ArrayList<Brand>> brList){
        mList.clear();
        for(int i = 0; i<brList.get(num-1).size(); i++) {
            String brName = brList.get(num-1).get(i).getSpotName();
            String priceLevel = brList.get(num-1).get(i).getPriceLevel();
            addItem(brName, priceLevel);
        }
        mAdapter.notifyDataSetChanged();

    }
    public void addItem(String brName, String priceLevel) {
        AllBrListItem item = new AllBrListItem();

        item.setBrName(brName);
        item.setPricelevel(priceLevel);
        mList.add(item);
    }

}
