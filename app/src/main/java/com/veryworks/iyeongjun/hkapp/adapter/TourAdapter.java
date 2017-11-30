package com.veryworks.iyeongjun.hkapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.veryworks.iyeongjun.hkapp.R;
import com.veryworks.iyeongjun.hkapp.domain.BoardItem;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.veryworks.iyeongjun.hkapp.domain.StaticFields.boardData;

/**
 * Created by iyeongjun on 2017. 11. 29..
 */

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.ViewHolder> {
    private ArrayList<BoardItem> datas;
    Context context;

    public TourAdapter(Context context) {
        this.context = context;
        datas = new ArrayList<>(Arrays.asList(boardData.getBoardItem()));
        for (BoardItem item : boardData.getBoardItem()){
            Log.d("Tour",item.getTitle());
        }
        for(BoardItem item : datas){
            Log.d("Tour",item.getTitle());
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tour, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView2.setText(datas.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView2) TextView textView2;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
