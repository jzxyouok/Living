package com.living.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.living.R;
import com.living.greendao.model.User;
import com.living.greendao.util.DbUtil;
import com.living.util.LogUtil;
import com.living.util.glide.GlideCircleTransform;
import com.living.util.glide.GlideImageUtil;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 第三个Tab界面显示 Fragment
 * @author tobin
 */
public class Tab3Fragment extends BaseFragment implements View.OnClickListener{
    private View rootView;
    private ImageView iv_header;

    public Tab3Fragment() {

    }

    public void testGreenDao(){
        User user=new User();
        user.setAccount("最会写代码的厨师，最会做饭的程序员");
        user.setHeight(168);

        DbUtil.getUserService().save(user);
        List<User> test = DbUtil.getUserService().queryAll();
        if (test != null && test.size()>0){
            LogUtil.e("User: " + test.get(0).toString() + " //getAccount: " + test.get(0).getAccount());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_tab3, container, false);
            LogUtil.e("tobin Tab3Fragment onCreateView rootView == null");
        }else{
            LogUtil.e("tobin Tab3Fragment onCreateView rootView != null");
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        LogUtil.e("tobin Tab3Fragment onCreateView");
        initView();
        return rootView;
    }

    private void initView(){
        ((TextView) rootView.findViewById(R.id.tv_main_title)).setText("我");
        iv_header = (ImageView)rootView.findViewById(R.id.iv_header);
        iv_header.setOnClickListener(this);
        GlideImageUtil.setPhotoResourceId(getActivity(), GlideCircleTransform.getInstance(getActivity()),R.mipmap.test_header,iv_header);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent result) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_SELECT_PICTURE) {
                final Uri selectedUri = result.getData();
                if (selectedUri != null) {
                    UCrop uCrop = UCrop.of(selectedUri, Uri.fromFile(new File(getActivity().getCacheDir(), "u_crop.png"))).withAspectRatio(1, 1).withMaxResultSize(200,200);
                    uCrop.start(getActivity());
                } else {
                    Toast.makeText(getActivity(), "Cannot retrieve selected image", Toast.LENGTH_SHORT).show();
                }
            } else if (requestCode == UCrop.REQUEST_CROP) {
                final Uri resultUri = UCrop.getOutput(result);
//                iv_header.setImageURI(resultUri);
                GlideImageUtil.setPhotoFast(getActivity(), GlideCircleTransform.getInstance(getActivity()),resultUri.toString(),iv_header,R.mipmap.ic_launcher);
            }
        }
        if (resultCode == UCrop.RESULT_ERROR) {
            Toast.makeText(getActivity(),  "" + UCrop.getError(result).getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private static final int REQUEST_SELECT_PICTURE = 0x01;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_header:
                new SweetAlertDialog(getActivity(), SweetAlertDialog.NORMAL_TYPE)
                    .setTitleText("修改头像！")
                    .setConfirmText("图库")
                    .setCancelText("拍照")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                            Intent intent = new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            intent.addCategory(Intent.CATEGORY_OPENABLE);
                            startActivityForResult(Intent.createChooser(intent, "图片选择"), REQUEST_SELECT_PICTURE);
                        }
                    })
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.cancel();
                            Toast.makeText(getActivity(),"该功能正在开发中",Toast.LENGTH_SHORT).show();
                        }
                    })
                    .show();
                break;
            default:
                break;
        }
    }

}
