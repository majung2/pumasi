package com.example.myapplication3.Recommend;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication3.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendFragment extends Fragment implements OnButttonClick, RecommendBrandController.brandRateController {
    RecyclerView mRecyclerView = null;
    RecyclerView.LayoutManager layoutManager;
    RecommendAdapter mAdapter = null;
    String id;
    String pw;
    boolean[] selectedCategory;
    private RecommendFragment.brNameListListener brListnr;
    private ArrayList<String> brList = new ArrayList();
    private ArrayList<RecommendItem> mList = new ArrayList<>();
    private HashMap<String, HashMap<String, Object>> AllUserMap = new HashMap<>();
    public RecommendFragment() {
        // Required empty public constructor
    }
    public void onClick(String brname, Button btn){
        if(btn.getText().equals("선택")){
            brList.add(brname);
            btn.setText("취소");
        }
        else{
            brList.remove(brname);
            btn.setText("선택");
        }
    }
    public interface brNameListListener{
        void brNameListSet(ArrayList<String> list);
    }


    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof RecommendFragment.brNameListListener){
            brListnr = (RecommendFragment.brNameListListener)context;
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
        View view = inflater.inflate(R.layout.recommend_fragment, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecommendAdapter(mList, this);
        mRecyclerView.setAdapter(mAdapter) ;
        id = getArguments().getString("id");
        pw = getArguments().getString("pw");
        selectedCategory=getArguments().getBooleanArray("selectedCategory");
        RecommendBrandController mController = new RecommendBrandController();
        int tabNumber = getArguments().getInt("tabNumber");
        HashMap<String, String> map = mController.pearsonCheck(id, selectedCategory, AllUserMap);
        callRecommendedItems(tabNumber, map);
        // Inflate the layout for this fragment
        //추천로직 컨트롤러로 추천 리스트생성 => 받아온만큼 체크박스 형식으로 출력.(방법) =>사용자는 원하는 브랜드를 선택 (이부분 어디서 조작할지 모르겠음) =>
        //추천로직 컨트롤러 작동 메소드 => 엔티티클래스에서 디비 불러옴 => 컨트롤러에서 로직 결과 => 엔티티클래스에서 디비 작성
        // => 추천로직 컨트롤러는 리스트뷰에 해당 브랜드들을 추가 => 바운더리에서 화면 보여줌
        brListnr.brNameListSet(brList);

        return view;


    }

    @Override
    public void addAllUserMap2(HashMap<String, HashMap<String, Object>> map) {
        for(String key : map.keySet()) {
            AllUserMap.put(key, map.get(key));
        }
    }

    public void callRecommendedItems(int num, HashMap<String, String> map){
        mList.clear();
        String tmp = "c"+num;
        for(String brName : map.keySet()){
            if(!brName.equals("")){
                if(map.get(brName).equals(tmp)){
                  addItem(brName);
                }
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    public void addItem(String brName) {
        RecommendItem item = new RecommendItem();

        item.setBrName(brName);
        mList.add(item);
    }
}