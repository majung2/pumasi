package com.example.myapplication3.Shopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication3.R;

import java.util.ArrayList;

public class BrandAdapter extends BaseAdapter {


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

    // -------------------------------------------------------------------------
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        final Context context = parent.getContext();
        final Brand b = brandList.get(position);

        BrandViewHolder viewHolder;

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         //   convertView = inflater.inflate(R.layout.brand_shopping, parent, false);

            viewHolder = new BrandViewHolder();
            viewHolder.name = convertView.findViewById(R.id.name);
            viewHolder.bought = convertView.findViewById(R.id.yes_buy);
            viewHolder.bought.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((ShoppingActivity)context).checkBought(b.id);
                }
            });
            viewHolder.notBought = convertView.findViewById(R.id.no_buy);
            viewHolder.notBought.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((ShoppingActivity)context).checkNotBought(b.id);
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
        public ImageView bought;
        public  ImageView notBought;
    }

    void addItem(Brand brand) {
        brandList.add(0, brand);
        notifyDataSetChanged();
    }

}