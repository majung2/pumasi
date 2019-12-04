package com.example.myapplication3.MyPage;

import android.widget.ArrayAdapter;
import android.content.Context;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication3.R;


public class PreviousPathAdapter extends ArrayAdapter<PreviousPathBrand> {

    private Context context;
    private List mList;
    private ListView mListView;


    class UserViewHolder {
        public TextView brandname;
        public TextView star;
        public TextView category_specific;
    }

    public PreviousPathAdapter(Context context, // 레이아웃 XML 파일을 뷰(view) 객체로 바꾸는데 사용할 컨택스트(Context)
                               List<PreviousPathBrand> list, // ListView에 보여줄 데이터인 data객체 리스트
                               // TextView에 Path 객체의 데이터를 넣어서 ListVIew의 row view로 사용되도록 리턴합니다.
                               ListView listview
    ) {
        super(context, 0, list);
        this.context = context;
        this.mList = list;
        this.mListView = listview;
    }

    @Override
    public View getView(int position,       // ListView에 보여지게 되는 데이터인 path 객체 리스트의 인덱스

                        View convertView,   // 주어진 데이터를 보여주기 위해 사용될 한 줄(row)을 위한 뷰(View)
                        // 값이 null인 경우에만 새로 생성하고 그 외에는 재사용됩니다.

                        ViewGroup parentViewGroup  // XML 레이아웃 파일을 View 객체로 변환하기 위해 사용되는 부모 뷰그룹
    ) {

        View rowView = convertView; // 코드 가독성을 위해서 rowView 변수를 사용합니다.
        UserViewHolder viewHolder;
        String Status;


        // 기존에 생성했던 뷰라면 재사용하고 그렇지 않으면 XML 파일을 새로 view 객체로 변환합니다.
        if (rowView == null) {

            // 레이아웃을 정의한 XML 파일(R.layout.list_item)을 읽어서 계층 구조의 뷰 객체(rowView)로 변환합니다.
            // rowView 객체는 3개의 TextView로 구성되어 있습니다.
            //
            // 다음 한줄로 구현도 가능합니다.
            // rowView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parentViewGroup, false);
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            rowView = layoutInflater.inflate(R.layout.previous_path_specific, parentViewGroup, false);


            // view holder의 구성 요소의 값과 한 줄을 구성하는 레이아웃을 연결함.
            //
            // rowView(=R.layout.list_item)에서 주어진 ID(R.id.textview_list_english)를 가진 뷰를 찾습니다.
            // 찾는 뷰의 타입에 따라 findViewById 리턴 결과를 타입 변환해줘야 합니다.
            viewHolder = new UserViewHolder();
            viewHolder.brandname = (TextView) rowView.findViewById(
                    R.id.BrandName // 한 줄에 대한 레이아웃 파일(R.layout.list_item)의 구성요소,
            );

            viewHolder.star = (TextView) rowView.findViewById(
                    R.id.Star // 한 줄에 대한 레이아웃 파일(R.layout.list_item)의 구성요소,
            );

            viewHolder.category_specific = (TextView) rowView.findViewById(
                    R.id.CategorySpecific // 한 줄에 대한 레이아웃 파일(R.layout.list_item)의 구성요소,
            );

            viewHolder.brandname = (TextView) rowView.findViewById(R.id.BrandName);
            viewHolder.star = (TextView) rowView.findViewById(R.id.Star);
            viewHolder.category_specific = (TextView) rowView.findViewById(R.id.CategorySpecific);

            rowView.setTag(viewHolder);

            Status = "created";
        } else {

            viewHolder = (UserViewHolder) rowView.getTag();

            Status = "reused";
        }


        // 태그 분석을 위한 코드 시작
        String Tag = rowView.getTag().toString();
        int idx = Tag.indexOf("@");
        String tag = Tag.substring(idx + 1);


        //data 객체 리스트의 position 위치에 있는 data객체를 가져옵니다.
        PreviousPathBrand data1 = (PreviousPathBrand) mList.get(position);


        //현재 선택된 data 객체를 화면에 보여주기 위해서 앞에서 미리 찾아 놓은 뷰에 데이터를 집어넣습니다.
        viewHolder.brandname.setText(data1.getBrand_name());
        viewHolder.star.setText("만족도: "+ data1.getGrade().toString());
        viewHolder.category_specific.setText(data1.getBought_Category());
        // 화면에 보여질 뷰를 리턴하여 ListView의 특정 줄로 보여지게 합니다.
        return rowView;
    }
}