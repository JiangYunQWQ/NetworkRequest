package com.jy.networkrequest.network;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtil {
    public static void parseJson(String jsonData){
        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            String msg=jsonObject.getString("msg");
            int code=jsonObject.getInt("code");
            if(code!=200){
                Log.i("tag", "parseJson: "+msg);
                return;
            }else{
                JSONArray array = jsonObject.getJSONArray("data");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject jsonObject1 = array.getJSONObject(i);
                    Log.i("data", "parseJson: "+jsonObject1.getString("name"));
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.i("tag", "parseJson: "+e.getMessage());
        }
    }
}
