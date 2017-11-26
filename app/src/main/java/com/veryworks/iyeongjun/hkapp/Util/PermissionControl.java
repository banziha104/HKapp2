package com.veryworks.iyeongjun.hkapp.Util;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by pc on 6/15/2017.
 */

public class PermissionControl {
    public static final int REQ_FLAG = 1000232412;
    private static String permissions[] = {
            Manifest.permission.INTERNET,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
    };

    public static void checkVersion(Activity activity){
        // 마시멜로 이상버전에서만 런타임 권한체크
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            Log.d("PerMission","m");

            checkPermission(activity);
        }else {

            Log.d("PerMission","e");

            callInit(activity);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static void checkPermission(Activity activity){
        //1 권한체크 - 특정권한이 있는지 시스템에 물어본다
        boolean denied = false;

        Log.d("PerMission","check");

        for(String perm : permissions){
            if(activity.checkSelfPermission(perm) != PackageManager.PERMISSION_GRANTED){
                denied = true;
                break;
            }
        }
        if(denied){
            // 2. 권한이 없으면 사용자에 권한을 달라고 요청

            Log.d("PerMission","req");

            activity.requestPermissions(permissions ,REQ_FLAG);
        }else{
            Log.d("PerMission","reqin");

            callInit(activity);
        }
    }

    public static void onResult(Activity activity, int requestCode, @NonNull int[] grantResults){

        Log.d("PerMission","onResult");

        if(requestCode == REQ_FLAG){
            boolean granted = true;
            for(int grant : grantResults){
                if(grant != PackageManager.PERMISSION_GRANTED){
                    granted = false;
                    Log.d("PerMission","grant");

                    break;
                }
            }
            // 3.1 사용자가 승인을 했음
            if(granted){
                Log.d("PerMission","granted");

                callInit(activity);
            }else{

                Log.d("PerMission","not granted");

                Toast.makeText(activity, "권한요청을 승인하셔야 앱을 사용할 수 있습니다.", Toast.LENGTH_SHORT).show();
                //activity.finish();
            }
        }
    }


    private static void callInit(Activity activity){
        if(activity instanceof CallBack)
            ((CallBack)activity).init();
        else
            throw new RuntimeException("must implement this.CallBack");
    }

    public interface CallBack {
        public void init();
    }
}