package com.example.myapplication3.Shopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
;
import android.widget.TextView;

import com.example.myapplication3.R;

import java.util.ArrayList;

public class FeedbackAdapter extends BaseAdapter {


    ArrayList<Brand> brandList;
    public FeedbackAdapter(){
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


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Context context = parent.getContext();
        final Brand b = brandList.get(position);

        final BrandViewHolder viewHolder;

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           // convertView = inflater.inflate(R.layout.brand_feedback, parent, false);

            viewHolder = new BrandViewHolder();
            viewHolder.name = convertView.findViewById(R.id.name);
            viewHolder.rating = convertView.findViewById(R.id.rating);
            viewHolder.ratingButton = convertView.findViewById(R.id.ratingButton);
            viewHolder.ratingButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String grade = viewHolder.rating.getText().toString();
                    ((FeedbackActivity)context).checkRating(b.id, grade);
                }
            });

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (BrandViewHolder)convertView.getTag();
        }

        viewHolder.name.setText(b.name);

        return convertView;
    }
    // -------------------------------------------------------------------------
    // getView 함수 종료
    // =========================================================================

    public class BrandViewHolder{
        public TextView name;
        public EditText rating;
        public Button ratingButton;
    }

    void addItem(Brand brand) {
        brandList.add(0, brand);
        notifyDataSetChanged();
    }

}
