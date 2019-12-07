package com.example.myapplication3.Recommend;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication3.R;

import java.util.ArrayList;

public class AllBrListAdapter extends RecyclerView.Adapter<AllBrListAdapter.ViewHolder> {
    private ArrayList<AllBrListItem> mData = null ;
    private OnButttonClick mCallback;
    // 생성자에서 데이터 리스트 객체를 전달받음.
    AllBrListAdapter(ArrayList<AllBrListItem> list, OnButttonClick listenr) {
        mData = list ;
        mCallback = listenr;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public AllBrListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.recycler_item, parent, false) ;
        final AllBrListAdapter.ViewHolder vh = new AllBrListAdapter.ViewHolder(view) ;
        vh.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onClick((String)vh.brName.getText());

            }
        });


        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(AllBrListAdapter.ViewHolder holder, int position) {

        AllBrListItem item = mData.get(position) ;

        holder.brName.setText(item.getBrName()) ;
        holder.priceLevel.setText(item.getPricelevel()) ;

    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView brName ;
        TextView priceLevel ;
        Button button;
        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            brName = itemView.findViewById(R.id.brName) ;
            priceLevel = itemView.findViewById(R.id.priceLevel) ;
            button = itemView.findViewById(R.id.button3);

        }
    }
}