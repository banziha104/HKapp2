package com.veryworks.iyeongjun.hkapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.veryworks.iyeongjun.hkapp.Interface.SetBackgroundListFragment;
import com.veryworks.iyeongjun.hkapp.R;
import com.veryworks.iyeongjun.hkapp.Util.CustomBitmapPool;
import com.veryworks.iyeongjun.hkapp.domain.HKData;
import com.veryworks.iyeongjun.hkapp.domain.Items;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;

/**
 * Created by iyeongjun on 2017. 11. 26..
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    Items[] datas;
    Context context;
    SetBackgroundListFragment setBackInterface;
    public ListAdapter(Context context, SetBackgroundListFragment setBackInterface, HKData hkData) {
        this.context = context;
        this.setBackInterface = setBackInterface;
        datas = hkData.getItems();
    }

    public ListAdapter(Context context, SetBackgroundListFragment setBackInterface,HKData hkData, String setting) {
        this.context = context;
        this.setBackInterface = setBackInterface;
        datas = hkData.getItems();
    }

    public ListAdapter(Context context, SetBackgroundListFragment setBackInterface, HKData hkData, String setting, String type) {
        this.context = context;
        this.setBackInterface = setBackInterface;
        datas = hkData.getItems();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setPostion(position);
        holder.setTxt(datas[position]);
        holder.setImage(datas[position]);
    }

    @Override
    public int getItemCount() {
        return datas.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgListBack) ImageView imgListBack;
        @BindView(R.id.imgListContent) ImageView imgListContent;
        @BindView(R.id.txtTitle) TextView txtTitle;
        @BindView(R.id.txtDescription) TextView txtDescription;

        int posistion;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setPostion(int postion){
            this.posistion = postion;
        }
        public void setTxt(Items items){
            txtTitle.setText(items.getTitle());
            txtDescription.setText(items.getDescription());
        }
        public void setImage(Items items){
            Glide
                    .with(context)
                    .load(items.getImage())
                    .bitmapTransform(new BlurTransformation(context,new CustomBitmapPool(),20))
                    .bitmapTransform(new ColorFilterTransformation(new CustomBitmapPool(), Color.argb(100, 0, 0, 0)))
                    .into(imgListContent);

            if (posistion % 2 == 0) setBackInterface.setBackground(items.getImage());

        }
    }
}
