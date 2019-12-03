package com.example.myapplication3.Shopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication3.R;

import java.util.ArrayList;

public class BrandAdapter extends BaseAdapter {

    // 뷰로 만들어낼 트윗의 목록
    ArrayList<Brand> brandList;


    public BrandAdapter(){
        brandList = new ArrayList<>();
    }


    // 목록의 개수를 요청하는 함수. 리스트의 사이즈를 돌려주도록 작성한다.
    @Override
    public int getCount() {
        return brandList.size();
    }


    @Override
    public Object getItem(int position) {
        return brandList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    // =========================================================================
    // getView
    // 특정 위치에 들어가야 하는 뷰를 생성하여 리스트뷰에 집어넣어주는 함수

    // 매개변수
    // position : 현재 그려서 반환해야 할 뷰의 위치 (리스트에서 몇 번째인지)
    // convertView : 뷰가 담기는 변수. 이미 생성되었던 뷰라면 (ex. 새 트윗이 생겼을 때 기존에 있던 트윗 뷰)
    // 비어있지 않다.
    // parent : 뷰가 생성되어 들어갈 부모 뷰
    // -------------------------------------------------------------------------
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        Context context = parent.getContext();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.brand, parent, false);
        }
        // ------------------------------------------------------------------------


        TextView name = convertView.findViewById(R.id.name);


        // position 번째의 뷰를 만들고 있으므로

        name.setText(brandList.get(position).name);


        // 완성된 뷰를 돌려줍니다.
        return convertView;
    }
    // -------------------------------------------------------------------------
    // getView 함수 종료
    // =========================================================================

    void addItem(Brand brand) {
        brandList.add(0, brand);
        notifyDataSetChanged();
    }

}