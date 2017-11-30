package com.veryworks.iyeongjun.hkapp.HTTP;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.veryworks.iyeongjun.hkapp.Interface.StarDataInterface;
import com.veryworks.iyeongjun.hkapp.domain.Const;
import com.veryworks.iyeongjun.hkapp.domain.StarsData;
import com.veryworks.iyeongjun.hkapp.domain.StarsItem;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.veryworks.iyeongjun.hkapp.domain.StaticFields.isInitData;
import static com.veryworks.iyeongjun.hkapp.domain.StaticFields.starsData;

/**
 * Created by iyeongjun on 2017. 11. 28..
 */

public class StarDataLoader {
    Retrofit retrofit;
    Context context;
    Gson gson;
    StarDataInterface starDataInterface;

    public StarDataLoader(Context context) {
        this.context = context;
        gson = new Gson();
    }
    public void getStarsData(){
        setRetrofit();
        Call<ResponseBody> result = starDataInterface.getStars();
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String val1 = "{ \"starsItem\":" +response.body().string()+ "}";
                    Log.d("DataLoad",val1);
                    starsData = gson.fromJson(val1,StarsData.class);
                    Log.d("DataLoad",""+starsData.getStarsItem().length);
                    StarsItem[] items = starsData.getStarsItem();
                    for (int i = 0 ;i < items.length ; i++){
                        Log.d("DataLoad",items[i].getPk());
                    }
                    isInitData[1] = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void setRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Const.NETWORK.SERVER_URL)
                .build();
        starDataInterface
                = retrofit.create(StarDataInterface.class);
    }
}
