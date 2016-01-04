package bluemobi.iuv.fragment;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import bluemobi.iuv.R;
import bluemobi.iuv.activity.MainActivity;
import bluemobi.iuv.annotation.ContentView;
import bluemobi.iuv.app.IuwApplication;
import bluemobi.iuv.base.BaseActivity;
import bluemobi.iuv.base.utils.Logger;
import bluemobi.iuv.eventbus.BaseEvent;
import bluemobi.iuv.eventbus.CommonTypeEvent;
import bluemobi.iuv.eventbus.MyInfoEvent;
import bluemobi.iuv.network.request.ChooseSexRequest;
import bluemobi.iuv.network.request.UploadPicRequest;
import bluemobi.iuv.network.response.LoginResponse;
import bluemobi.iuv.network.response.UploadPicResponse;
import bluemobi.iuv.util.Base64;
import bluemobi.iuv.util.GlideCircleTransform;
import bluemobi.iuv.util.Utils;
import bluemobi.iuv.util.WebUtils;
import bluemobi.iuv.view.CircleImageView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * 个人信息P11-7
 * Created by wangzhijun on 2015/8/9.
 */
@ContentView(R.layout.fragment_personal_center_my_info)
public class MyInfoFragment extends BaseFragment implements View.OnClickListener{
    @Bind(R.id.rl_avatar)
    protected RelativeLayout rl_avatar;
    @Bind(R.id.rl_gandar)
    protected RelativeLayout rl_gandar;
    @Bind(R.id.rl_nickname)
    protected RelativeLayout rl_nickname;

    private AlertDialog gandarDialog;

    private AlertDialog pickDialog;

    @Bind(R.id.nickname_label)
    protected TextView nickname_label;
    @Bind(R.id.avatar)
    protected CircleImageView avatar;

    @Bind(R.id.gandar_label)
    protected TextView gandar_label;
    private String  headUrl;
    private String  nickName;
    private String  sex;

    public MyInfoFragment()
    {
        EventBus.getDefault().register(this);
    }

    public void onEventMainThread(BaseEvent baseEvent) {
        if (baseEvent instanceof MyInfoEvent) {
            MyInfoEvent my= (MyInfoEvent) baseEvent;
            headUrl= my.getHeadUrl();
            nickName=my.getNickName();
            sex=my.getSex();
        }else if (baseEvent instanceof CommonTypeEvent){
            CommonTypeEvent commonTypeEvent= (CommonTypeEvent) baseEvent;
            int type=commonTypeEvent.getType();
            if (type==1)
            {
                UploadHeadImage();
            }
        }

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Logger.e("MyInfoFragment", "onHiddenChanged");
    }

    @Override
    protected void successViewCompleted(View successView) {
        ButterKnife.bind(this, successView);
        nickname_label.setText(nickName);
        if ("1".equals(sex)){
            gandar_label.setText("男");
        }else if ("2".equals(sex)){
            gandar_label.setText("女");
        }
        RequestManager glideRequest;
        glideRequest = Glide.with(mContext);
        glideRequest.load(headUrl).transform(new GlideCircleTransform(mContext)).into(avatar);

    }
    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity) mContext).setMineBarSytle(getResources().getString(R.string.s_mine), R.color.common_blue, true);
        Logger.e("MyInfoFragment","onStart");
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        isShowLoadPage = false;
    }


    @OnClick(R.id.rl_avatar)
    public void pickAvatar() {
        if(pickDialog == null){
            pickDialog = new AlertDialog.Builder(getActivity())
                    .create();
            view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_pick_pic, null);
            Window window = pickDialog.getWindow();
            window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置
            window.setWindowAnimations(R.style.dialog_bottom_style);  //添加动画

            TextView camera= (TextView) view.findViewById(R.id.camera);
            TextView gallary= (TextView) view.findViewById(R.id.gallary);
            TextView cancel= (TextView) view.findViewById(R.id.cancel);
            camera.setOnClickListener(this);
            gallary.setOnClickListener(this);
            cancel.setOnClickListener(this);
            WindowManager windowManager = getActivity().getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            WindowManager.LayoutParams lp = pickDialog.getWindow().getAttributes();
            lp.width = (int)(display.getWidth()); //设置宽度
            pickDialog.getWindow().setAttributes(lp);
            pickDialog.show();
            pickDialog.setContentView(view);
        }else{
            pickDialog.show();
        }
    }

    @OnClick(R.id.rl_gandar)
    public void pickGardar() {

        if(gandarDialog == null){
            gandarDialog = new AlertDialog.Builder(getActivity())
                    .create();
            view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_gandar, null);
            TextView man= (TextView) view.findViewById(R.id.man);
            TextView woman= (TextView) view.findViewById(R.id.woman);
            man.setOnClickListener(this);
            woman.setOnClickListener(this);
            Window window = gandarDialog.getWindow();
            window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置
            window.setWindowAnimations(R.style.dialog_bottom_style);  //添加动画

            WindowManager windowManager = getActivity().getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            WindowManager.LayoutParams lp = gandarDialog.getWindow().getAttributes();
            lp.width = (int)(display.getWidth()); //设置宽度
            gandarDialog.getWindow().setAttributes(lp);
            gandarDialog.show();
            gandarDialog.setContentView(view);

        }else{
            gandarDialog.show();
        }
    }

    @OnClick(R.id.rl_nickname)
    public void pickNickName() {
        Utils.moveToFragment(UpdateNicknameFragment.class, MyInfoFragment.this, "UpdateNicknameFragment");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.man:
                //性别 1:男，2:女，0为未知
                chooseSex("1");
                break;
            case R.id.woman:
                chooseSex("2");
                break;
            case R.id.camera:
                pickDialog.dismiss();
                ((BaseActivity)mContext).pickImageFromCamera(avatar);
                break;
            case R.id.gallary:
                pickDialog.dismiss();
                ((BaseActivity)mContext).pickImage(avatar);

                break;
            case R.id.cancel:
                pickDialog.dismiss();
                break;
        }
    }

    private void chooseSex(final String sex){
        ChooseSexRequest request = new ChooseSexRequest(new Response.Listener<LoginResponse>() {
            @Override
            public void onResponse(LoginResponse response) {
                Utils.closeDialog();
                gandarDialog.dismiss();
                if (response != null && response.getStatus() ==0){
                    if ("1".equals(sex)){
                        gandar_label.setText("男");
                    }else if ("2".equals(sex)){
                        gandar_label.setText("女");
                    }
                }
            }
        }, (Response.ErrorListener) getActivity());
        request.setUserId(IuwApplication.getInstance().getMyUserInfo().getUserId());
        request.setCustomerGender(sex);
        Utils.showProgressDialog(getActivity());
        WebUtils.doPost(request);
    }

    public  void UploadHeadImage(){
        UploadPicRequest request = new UploadPicRequest(
                new Response.Listener<UploadPicResponse>() {
                    @Override
                    public void onResponse(UploadPicResponse response) {
                        Utils.closeDialog();
                        if (response != null && response.getStatus() == 0) {
                            Toast.makeText(getActivity(), "头像修改成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, (Response.ErrorListener) getActivity());
        request.setUserId(IuwApplication.getInstance().getMyUserInfo().getUserId());
        Bitmap image = ((BitmapDrawable) avatar.getDrawable()).getBitmap();
        request.setPicArray(Base64.encodeBytes(Utils.Bitmap2Bytes(image)));
        Utils.showProgressDialog(getActivity());
        WebUtils.doPost(request);
    }
}
