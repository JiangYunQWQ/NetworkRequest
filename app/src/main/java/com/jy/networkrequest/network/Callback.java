package com.jy.networkrequest.network;

import android.util.Log;

public interface Callback {
    void onFinish(String response);
    default void onError(Exception e){
        Log.d("network", "onError: "+e.getMessage());
    }
}
