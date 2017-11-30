package com.veryworks.iyeongjun.hkapp.adapter;

import android.content.Context;
import android.location.Location;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.veryworks.iyeongjun.hkapp.Interface.SetBackgroundListFragment;
import com.veryworks.iyeongjun.hkapp.R;
import com.veryworks.iyeongjun.hkapp.domain.HKData;
import com.veryworks.iyeongjun.hkapp.domain.Items;
import com.veryworks.iyeongjun.hkapp.domain.StarsItem;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.veryworks.iyeongjun.hkapp.Util.DataConverter.convertSectionIntToString;
import static com.veryworks.iyeongjun.hkapp.Util.DataConverter.convertSectionStringToInt;
import static com.veryworks.iyeongjun.hkapp.Util.DataConverter.convertTypeIntToString;
import static com.veryworks.iyeongjun.hkapp.Util.DataConverter.convertTypeStringToInt;
import static com.veryworks.iyeongjun.hkapp.Util.UserLocation.currentUserLocation;
import static com.veryworks.iyeongjun.hkapp.domain.StaticFields.starsData;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    Items[] data;
    Context context;
    ArrayList<Items> datas = new ArrayList();
    StarsItem[] mStarsDatas;
    SetBackgroundListFragment setBackInterface;


    public ListAdapter(Context context, SetBackgroundListFragment setBackInterface, HKData hkData) {
        this.context = context;
        this.setBackInterface = setBackInterface;
        data = hkData.getItems();
        datas = new ArrayList<>(Arrays.asList(data));
        mStarsDatas = starsData.getStarsItem();
    }


    public ListAdapter(Context context, SetBackgroundListFragment setBackInterface, HKData hkData, String setting, String type) {
        this.context = context;
        this.setBackInterface = setBackInterface;
        data = hkData.getItems();
        datas = new ArrayList<>(Arrays.asList(data));
        mStarsDatas = starsData.getStarsItem();
        setData(setting, type);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_section, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setPostion(position);
        holder.setTxt(datas.get(position));
        holder.setImage(datas.get(position));
        holder.setTxtStar(datas.get(position), mStarsDatas);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    private void setData(String setting, String content) {
        ArrayList<Items> list = new ArrayList<>();
        for (Items item : datas) {
            if (setting.equals("section")) {
                if (item.getSection().equals(convertSectionStringToInt(content) + ""))
                    list.add(item);

            } else if (setting.equals("type")) {
                if (item.getType().equals(convertTypeStringToInt(content) + "")) list.add(item);
            }
        }
        datas = list;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgListContent) ImageView imgListContent;
        @BindView(R.id.txtStar) TextView txtStar;
        @BindView(R.id.txtTitle) TextView txtTitle;
        @BindView(R.id.txtDescription) TextView txtDescription;
        @BindView(R.id.txtSubScribe) TextView txtSubScribe;
        int posistion;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            txtSetting();
        }

        public void setPostion(int postion) {
            this.posistion = postion;
        }

        public void setTxt(Items items) {
            txtTitle.setText("{fa-tag 80%}"+items.getTitle());

            txtDescription.setText("{fa-map-marker 80%}"+items.getDescription());
            txtSubScribe.setText("{fa-commenting 80%}"+convertSectionIntToString(Integer.parseInt(items.getSection()))
                    + " "
                    + convertTypeIntToString(Integer.parseInt(items.getType())));
        }

        public void setTxtStar(Items item, StarsItem[] starsItems) {
            txtStar.setText(setStars(item, starsItems));
        }

        private void txtSetting() {
//            txtTitle.setTextColor(context.getResources().getColor(R.color.mainColor));
//            txtDescription.setTextColor(context.getResources().getColor(R.color.mainColor));
//            txtStar.setTextColor(context.getResources().getColor(R.color.mainColor));
//            txtSection.setTextColor(context.getResources().getColor(R.color.mGray));
        }

        public void setImage(Items items) {
            Glide
                    .with(context)
                    .load(items.getImage())
                    .into(imgListContent);

//            if (posistion % 2 == 0) setBackInterface.setBackground(items.getImage());
        }

        public String setStars(Items item, StarsItem[] starsItem) {
            String result = "0.0";
            for (int i = 0; i < starsItem.length; i++) {
                if (item.getPk().equals(starsItem[i].getHkpk())) result = starsItem[i].getPoint();

            }
            return result;
        }
        private Location makeLocation(Items item){
            Location location = new Location("Location");
            location.setLatitude(Double.parseDouble(item.getLat()));
            location.setLongitude(Double.parseDouble(item.getLon()));
            location.setAltitude(0.0);
            return location;
        }
        private String getDistance(Location location) {
            double distance = ((int) (currentUserLocation.distanceTo(location))) / (1000.0);
            return distance + "km";
        }
    }
}
