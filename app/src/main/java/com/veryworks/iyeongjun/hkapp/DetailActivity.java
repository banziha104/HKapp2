package com.veryworks.iyeongjun.hkapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.veryworks.iyeongjun.hkapp.domain.Items;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.veryworks.iyeongjun.hkapp.domain.StaticFields.hkDatas;

public class DetailActivity extends CustomFontAcitivity implements OnMapReadyCallback {

    @BindView(R.id.deBtnBack) ImageButton deBtnBack;
    @BindView(R.id.detailImage) ImageView detailImage;
    @BindView(R.id.detxtTitle) TextView detxtTitle;
    @BindView(R.id.deTxtStars) TextView deTxtStars;
    @BindView(R.id.deTxtSub) TextView deTxtSub;
    @BindView(R.id.detxtContents) TextView detxtContents;
    @BindView(R.id.deMap) SupportMapFragment deMap;
    Items items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, //상태바 제거
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        deMap.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Intent intent = getIntent();
        String pk = intent.getStringExtra("pk");
        Items[] items = hkDatas.getItems();
        for(){
            
        }
    }
}
