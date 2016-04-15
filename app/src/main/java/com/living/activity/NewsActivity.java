package com.living.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.living.R;
import com.living.bean.NewsChannelBean;
import com.living.util.LivingNetUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {
    List<NewsChannelBean> NewsChannelBeanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        getChannelNews();
    }

    //新闻频道查询
    private void getChannelNews(){
//        Parameters para = new Parameters();
//        para.put("apikey", ApiStoreSDK.getAppKey());
//        ApiStoreSDK.execute("http://apis.baidu.com/showapi_open_bus/channel_news/channel_news",
//                ApiStoreSDK.GET,
//                para,
//                new ApiCallBack() {
//                    @Override
//                    public void onSuccess(int status, String responseString) {
//                        Log.i("tobin", "onSuccess" + responseString);
//                        jsonResult(responseString);
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.i("tobin", "onComplete");
//
//                    }
//
//                    @Override
//                    public void onError(int status, String responseString, Exception e) {
//                        Log.i("tobin", "onError, status: " + status);
//                        Log.i("tobin", "errMsg: " + (e == null ? "" : e.getMessage()));
//                    }
//                }
//        );

        LivingNetUtils.getChannelNew(new Response.Listener<NewsChannelBean>() {
            @Override
            public void onResponse(NewsChannelBean response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        },null);
    }

    /**
     * 解析返回的json数据
     * @param responseString
     * @return
     */
    private void jsonResult(String responseString) {
        try {
            JSONObject jsonObject = (new JSONObject(responseString)).getJSONObject("showapi_res_body");
            JSONArray jsonArray = jsonObject.getJSONArray("channelList");
            for (int i = 1; i < jsonArray.length(); i++) {
                JSONObject jsonObject2 = (JSONObject) jsonArray.get(i);
                NewsChannelBean newsChannelBean = new NewsChannelBean();
                String channelId = jsonObject2.getString("channelId");
                newsChannelBean.setChannelId(channelId);
                String name = jsonObject2.getString("channelId");
                newsChannelBean.setName(name);
                NewsChannelBeanList.add(newsChannelBean);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
