package com.jy.networkrequest.network;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpUtil {
    public static final String baseUrl="http://218.7.112.123:10001";

    public static void onGet(String url,Callback callback){
        Request request = new Request.Builder().url(url).build();
        onOkHttp(request,callback);
    }
    public static void  onPost(String url, String requestBody,Callback callback){
        MediaType mediaType=MediaType.parse("application/json");
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(mediaType,requestBody))
                .build();
        onOkHttp(request,callback);
    }
    public static void onOkHttp(Request request, Callback callback){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Response response = client.newCall(request).execute();
                    callback.onFinish(response.body().string());
                } catch (Exception e) {
                    e.printStackTrace();
                    callback.onError(e);
                }
            }
        }).start();
    }
}
