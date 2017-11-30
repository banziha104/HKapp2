package com.veryworks.iyeongjun.hkapp.HTTP;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.veryworks.iyeongjun.hkapp.Interface.BoardDataInterface;
import com.veryworks.iyeongjun.hkapp.domain.BoardData;
import com.veryworks.iyeongjun.hkapp.domain.BoardItem;
import com.veryworks.iyeongjun.hkapp.domain.Const;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.veryworks.iyeongjun.hkapp.domain.StaticFields.boardData;
import static com.veryworks.iyeongjun.hkapp.domain.StaticFields.isInitData;

/**
 * Created by iyeongjun on 2017. 11. 28..
 */

public class BoardDataLoader {
    Retrofit retrofit;
    Context context;
    Gson gson;
    BoardDataInterface boardDataInterface;
    public BoardDataLoader(Context context) {
        this.context = context;
        gson = new Gson();
    }
    public void getBoardData(){
        setRetrofit();
        Call<ResponseBody> result = boardDataInterface.getBoard();
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String val1 = "{ \"boardItem\":" +response.body().string()+ "}";
                    Log.d("DataLoad",val1);
                    boardData = gson.fromJson(val1,BoardData.class);
                    Log.d("DataLoad",""+boardData.getBoardItem().length);
                    BoardItem[] items = boardData.getBoardItem();
                    for (int i = 0 ;i < items.length ; i++){
                        Log.d("DataLoad",items[i].getTitle());
                    }
                    isInitData[2] = true;
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
        boardDataInterface
                = retrofit.create(BoardDataInterface.class);
    }

}
