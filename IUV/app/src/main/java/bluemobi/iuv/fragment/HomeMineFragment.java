package bluemobi.iuv.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import bluemobi.iuv.R;
import bluemobi.iuv.activity.MainActivity;
import bluemobi.iuv.annotation.ContentView;
import bluemobi.iuv.app.IuwApplication;
import bluemobi.iuv.app.UpdateChecker;
import bluemobi.iuv.base.BaseActivity;
import bluemobi.iuv.base.utils.Logger;
import bluemobi.iuv.eventbus.BaseEvent;
import bluemobi.iuv.eventbus.MyInfoEvent;
import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.model.QueryMyInfo;
import bluemobi.iuv.network.request.QueryMyInfoRequest;
import bluemobi.iuv.network.response.QueryMyInfoResponse;
import bluemobi.iuv.util.GlideCircleTransform;
import bluemobi.iuv.util.Utils;
import bluemobi.iuv.util.WebUtils;
import bluemobi.iuv.view.CircleImageView;
import bluemobi.iuv.view.RatioLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Created by liufy on 2015/8/4.
 * P11_个人中心
 */
@ContentView(R.layout.fragment_mine)
public class HomeMineFragment extends BaseFragment {

    private final static String tag = "HomeMineFragment";

    @Bind(R.id.rl_my_info)
    protected RelativeLayout rl_my_info;


    @Bind(R.id.head)
    protected CircleImageView head;
    @Bind(R.id.name)
    protected TextView name;

    private QueryMyInfo data;


    @Override
    protected IuwHttpJsonRequest initRequest() {
        QueryMyInfoRequest request = new QueryMyInfoRequest(new Response.Listener<QueryMyInfoResponse>() {
            @Override
            public void onResponse(QueryMyInfoResponse response) {
                if(response != null && response.getStatus() == 0){
                    if(response.getData() != null){
                        data=response.getData();
                        RequestManager glideRequest;
                        glideRequest = Glide.with(mContext);

                        glideRequest.load(response.getData().getHeadPicUrl()).into(head);
                        name.setText(response.getData().getNickName());
                    }
                }else{
                }
            }
        }, (Response.ErrorListener) getActivity());
        request.setUserId(IuwApplication.getInstance().getMyUserInfo().getUserId());
        return request;
    }

    @Override
    protected void successViewCompleted(View successView) {
        ButterKnife.bind(this, successView);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        isShowLoadPage = false;
    }



    /**
     * 我的VIP卡包
     */
    @OnClick(R.id.ll_vip)
    public void clickVip() {
        ((MainActivity)mContext).isBaseFragment = false;
        Utils.moveToFragment(MyCouponFragment.class, HomeMineFragment.this, "myCouponFragment");
    }

    /**
     * 我的收藏
     */
    @OnClick(R.id.ll_collect)
    public void clickCollect() {
        ((MainActivity)mContext).isBaseFragment = false;
        Utils.moveToFragment(MyCollectionFragment.class, HomeMineFragment.this, "MyCollectionFragment");
    }

    /**
     * 我的宝藏
     */
    @OnClick(R.id.ll_storehouse)
    public void clickStorehouse() {
        ((MainActivity)mContext).isBaseFragment = false;
        Utils.moveToFragment(MyTreasureFragment.class, HomeMineFragment.this, "myTreasureFragment");
    }

    /**
     * 我的信息
     */
    @OnClick(R.id.rl_my_info)
    public void clickRlMyInfo()
    {
        ((MainActivity) mContext).isHiddChange = true;

        MyInfoEvent myInfoEvent = new MyInfoEvent();
        myInfoEvent.setHeadUrl(data.getHeadPicUrl());
        myInfoEvent.setNickName(data.getNickName());
        myInfoEvent.setSex(data.getCustomerGender());
        Utils.moveToFragment(MyInfoFragment.class, HomeMineFragment.this, "MyInfoFragment");
        EventBus.getDefault().post(myInfoEvent);

    }



    /**
     * 我的通知
     */
    @OnClick(R.id.rl_my_inform)
    public void clickMyInform() {
        ((MainActivity)mContext).isBaseFragment = false;
        ((MainActivity) mContext).isHiddChange = true;
        Utils.moveToFragment(MyNotificationsFragment.class, HomeMineFragment.this, "myNotificationsFragment");
    }

    /**
     * 我的意见
     */
    @OnClick(R.id.rl_my_opinion)
    public void clickMyOpinion() {
        ((MainActivity)mContext).isBaseFragment = false;

        Utils.moveToFragment(PersonalCenterMyOpinionFragment.class, HomeMineFragment.this, "PersonalCenterMyOpinionFragment");
    }

    /**
     * 邀请好友
     */
    @OnClick(R.id.rl_friend)
    public void clickFriend() {
        ((MainActivity)mContext).isBaseFragment = false;
        Utils.moveToFragment(PersonalCenterMyFriendFragment.class, HomeMineFragment.this, "PersonalCenterMyFriendFragment");
    }

    /**
     * 版本检测
     */
    @OnClick(R.id.rl_versions)
    public void clickVersions() {
        Utils.out(tag, "版本检测");
        UpdateChecker.getInstance((BaseActivity)mContext).check(true);
    }



    @Override
    public void onHiddenChanged(boolean hidden) {

        super.onHiddenChanged(hidden);
        ((MainActivity)mContext).isBaseFragment = true;
        if (((MainActivity) mContext).isHiddChange)
        {
            Logger.e("HomeMineFragment  onHiddenChanged","onHiddenChanged");
                    ((MainActivity) mContext).setMineBarSytle(getResources().getString(R.string.s_mine), android.R.color.transparent, false);
            QueryMyInfoRequest request = new QueryMyInfoRequest(new Response.Listener<QueryMyInfoResponse>() {
                @Override
                public void onResponse(QueryMyInfoResponse response) {
                    if(response != null && response.getStatus() == 0){
                        if(response.getData() != null){
                            data=response.getData();
                            RequestManager glideRequest;
                            glideRequest = Glide.with(mContext);

                            glideRequest.load(response.getData().getHeadPicUrl()).into(head);
                            name.setText(response.getData().getNickName());
                        }
                    }else{
                    }
                }
            }, (Response.ErrorListener) getActivity());
            request.setUserId(IuwApplication.getInstance().getMyUserInfo().getUserId());
            WebUtils.doPost(request);
        }


    }

    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity) mContext).setMineBarSytle(getResources().getString(R.string.s_mine), android.R.color.transparent, false);


    }
}
