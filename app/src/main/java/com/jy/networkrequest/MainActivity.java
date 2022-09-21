package com.jy.networkrequest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.jy.networkrequest.network.Callback;
import com.jy.networkrequest.network.JSONUtil;
import com.jy.networkrequest.network.OkHttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        get();
        try {
            post();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void get(){
        OkHttpUtil.onGet(OkHttpUtil.baseUrl + "/prod-api/press/category/list", new Callback() {
            @Override
            public void onFinish(String response) {
                Log.i("parseJson", "onFinish: "+response);
                JSONUtil.parseJson(response);
            }
        });
    }

    private void post() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username","test106");
        jsonObject.put("password","bizideal");
        String str = jsonObject.toString();
        Log.i("TAG", "post: "+str);
        OkHttpUtil.onPost(OkHttpUtil.baseUrl + "/prod-api/api/login",str,new Callback() {
            @Override
            public void onFinish(String response) {
                Log.i("network", "onFinish: "+response);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(response);
                    }
                });
            }
        });
    }
}