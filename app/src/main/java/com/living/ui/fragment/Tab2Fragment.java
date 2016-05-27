package com.living.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.living.R;
import com.living.ui.activity.TuLingChatActivity;
import com.living.util.LogUtil;

public class Tab2Fragment extends BaseFragment implements View.OnClickListener{
    private View rootView;//缓存Fragment view

    LinearLayout ll_chat,ll_mood;
    TextView tv_content, tv_time;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        LogUtil.e("Tab2Fragment onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.e("Tab2Fragment onCreate");
    }

    private void initView(){
        ((TextView) rootView.findViewById(R.id.tv_main_title)).setText("发现");
        ll_chat = (LinearLayout) rootView.findViewById(R.id.ll_chat);
        tv_content = (TextView) rootView.findViewById(R.id.tv_content);
        tv_time = (TextView) rootView.findViewById(R.id.tv_time);
        ll_chat.setOnClickListener(this);

        ll_mood = (LinearLayout) rootView.findViewById(R.id.ll_mood);
        ll_mood.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(rootView == null){
            rootView=inflater.inflate(R.layout.fragment_tab2, null);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }

        initView();
        return rootView;
    }

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.ll_chat:
                startActivity(new Intent(getActivity(), TuLingChatActivity.class));
                break;
            case R.id.ll_mood:
                Toast.makeText(getActivity(),"该功能计划中.....",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.e("Tab2Fragment onPause");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.e("Tab2Fragment onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.e("Tab2Fragment onStop");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.e("Tab2Fragment onResume");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.e("Tab2Fragment onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtil.e("Tab2Fragment onDetach");
    }
}
