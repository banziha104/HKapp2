package com.veryworks.iyeongjun.hkapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.veryworks.iyeongjun.hkapp.R;
import com.veryworks.iyeongjun.hkapp.domain.Const;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

import static com.veryworks.iyeongjun.hkapp.domain.StaticDrawble.sectionImages;
import static com.veryworks.iyeongjun.hkapp.domain.StaticDrawble.typeClickImages;
import static com.veryworks.iyeongjun.hkapp.domain.StaticDrawble.typeImages;
import static com.veryworks.iyeongjun.hkapp.domain.StaticFields.isTypeList;

/**
 * Created by iyeongjun on 2017. 11. 25..
 */

/* 타입 리스트 어뎁터 패턴 */
public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.ViewHolder>{

    Context context;

    public TypeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setPostion(position);
        if (isTypeList) {
            holder.btnTypeImage.setImageResource(typeImages[position]);
        } else {
            holder.btnTypeImage.setImageResource(sectionImages[position]);
        }
    }

    @Override
    public int getItemCount() {
        int result = 0;
        if (isTypeList) result = Const.Count.TYPE_LENGTH;
        else result = Const.Count.SECTION_LENGTH;
        return result;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.btnTypeImage) ImageButton btnTypeImage;
        int postion;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setPostion(int postion){this.postion = postion;}

        @OnClick(R.id.btnTypeImage)
        public void btnTypeImageTouched(){
                if (isTypeList){

                }else{

                }
            }
        }
}
