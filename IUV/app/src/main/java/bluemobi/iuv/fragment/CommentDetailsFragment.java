package bluemobi.iuv.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Response;
import com.bumptech.glide.Glide;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import bluemobi.iuv.R;
import bluemobi.iuv.activity.MainActivity;
import bluemobi.iuv.adapter.CommonAdapter;
import bluemobi.iuv.adapter.ViewHolder;
import bluemobi.iuv.annotation.ContentView;
import bluemobi.iuv.base.utils.Logger;
import bluemobi.iuv.callback.TitleClickCallBack;
import bluemobi.iuv.eventbus.BaseEvent;
import bluemobi.iuv.eventbus.CommentDetailEvent;
import bluemobi.iuv.eventbus.GoodEvent;
import bluemobi.iuv.eventbus.StoreDetailsEvent;
import bluemobi.iuv.eventbus.TreasureEvent;
import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.request.CommentDetailsRequest;
import bluemobi.iuv.network.response.CommentDetailsResponse;
import bluemobi.iuv.network.response.LargeDepartmentStoresResponse;
import bluemobi.iuv.util.Constants;
import bluemobi.iuv.util.StringUtils;
import bluemobi.iuv.util.Utils;
import bluemobi.iuv.util.WebUtils;
import bluemobi.iuv.view.AspectImageView;
import bluemobi.iuv.view.LoadingPage;
import bluemobi.iuv.view.RatioImageView;
import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by gaoyn on 2015/8/5.
 * <p>
 * P8_2_2_1评价详情页面
 */
@ContentView(R.layout.fragment_comment_details)
public class CommentDetailsFragment extends BaseFragment {

    @Bind(R.id.plv_refresh)
    PullToRefreshListView plv_refresh;
    private MyBroadCastReciver mcr;
    private CommonAdapter<CommentDetailsResponse.CommentDetailsInfo> adapter;
    private List<String> lv = new ArrayList<String>();
    private String currentpage;

    private String productId;

    private String sourceType;

    private List<CommentDetailsResponse.CommentDetailsInfo> dataList = new ArrayList<CommentDetailsResponse.CommentDetailsInfo>();

    private  ArrayList<CommentDetailsResponse.ImgListBean> imgListd;

    private RatioImageView[] imageViewArgs = null;

    public CommentDetailsFragment() {
        EventBus.getDefault().register(this);

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (((MainActivity) mContext).isHiddChange) {
            ((MainActivity) mContext).setCommentBarSytle(getResources().getString(R.string.evaluation_details), true, 2);
            Logger.e("aaaaa","onHiddenChanged");
            WebUtils.doPost(initRequest());
        }
    }

    @Override
    public void initEmpteyData() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.lfy.yubroadcast");
        mcr = new MyBroadCastReciver();
        getActivity().registerReceiver(mcr, intentFilter);
    }

    public void onEvent(BaseEvent baseEvent) {
        if (baseEvent instanceof CommentDetailEvent) {
            CommentDetailEvent commentDetailEvent = (CommentDetailEvent) baseEvent;
            productId = commentDetailEvent.getProductID();
            sourceType = commentDetailEvent.getSourceType();

        }
    }


    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity) mContext).setCommentBarSytle(getResources().getString(R.string.evaluation_details), true, 2);
    }




    @Override
    protected IuwHttpJsonRequest initRequest() {

        return commonRequest(0);

    }


    private IuwHttpJsonRequest commonRequest(int startPage)
    {

        CommentDetailsRequest request = new CommentDetailsRequest
                (
                        new Response.Listener<CommentDetailsResponse>() {
                            @Override
                            public void onResponse(CommentDetailsResponse response) {

                                CommentDetailsResponse.CommentDetailsData data = response.data;
                                if (data != null) {
                                    currentpage = data.getCurrentpage();
                                    ArrayList<CommentDetailsResponse.CommentDetailsInfo> info = data.info;
                                    wrapLvData(info);
                                }

                            }
                        }, (Response.ErrorListener) getActivity());

        request.setProductId(productId);
        request.setCurrentnum(Constants.CURRENTNUMBASE + "");
        request.setCurrentpage(startPage + "");
        return request;
    }


    private void wrapLvData(ArrayList<CommentDetailsResponse.CommentDetailsInfo> info) {

        if (info == null) {
            return;
        }
        if (info.size() == 0) {
            return;
        }
        if (currentpage.equals("0")) {
            dataList.clear();
        }
        for (CommentDetailsResponse.CommentDetailsInfo lineDto : info) {
            dataList.add(lineDto);
        }
        imageViewArgs = new RatioImageView[4];
        plv_refresh.setAdapter(
                adapter = new CommonAdapter<CommentDetailsResponse.CommentDetailsInfo>(mContext,
                        dataList, R.layout.lv_evaluation_details) {

                    @Override
                    public void convert(final ViewHolder helper, CommentDetailsResponse.CommentDetailsInfo bean) {

                        helper.setText(R.id.tv_name, bean.userName);
                        helper.setText(R.id.tv_comment_desp, bean.content);
                        RatioImageView iv_pic1 = (RatioImageView) helper.getView(R.id.iv_pic1);
                        RatioImageView iv_pic2 = (RatioImageView) helper.getView(R.id.iv_pic2);
                        RatioImageView iv_pic3 = (RatioImageView) helper.getView(R.id.iv_pic3);
                        iv_pic1.setRatio(1f);
                        iv_pic2.setRatio(1f);
                        iv_pic3.setRatio(1f);
                        if (imgListd != null && imgListd.size() > 0)
                            imgListd.clear();
                        imgListd = bean.imgList;

                        imageViewArgs[0] = iv_pic1;
                        imageViewArgs[1] = iv_pic2;
                        imageViewArgs[2] = iv_pic3;
                        if (imgListd != null) {
                            for (int i = 0; i < imgListd.size(); i++) {
                                Logger.e("tag--imgPath-->", imgListd.get(i).imgPath);
                                Glide.with(mContext).load(imgListd.get(i).imgPath).placeholder(R.drawable.p8_2_2_1_default).into(imageViewArgs[i]);
                            }
                        }


                    }
                });

    }

    @Override
    protected boolean getPage(int type) {
        if (!super.getPage(type))
            return false;
        getDataServer();
        return true;
    }


    public void getDataServer() {
        WebUtils.doPost( commonRequest(getCurPage()));
    }


    @Override
    protected void successViewCompleted(View successView) {
        ButterKnife.bind(this, successView);

        initPullToRefresh(plv_refresh);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    private class MyBroadCastReciver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            CommentDetailEvent commentDetailEvent = new CommentDetailEvent();
            commentDetailEvent.setProductID(productId);
            commentDetailEvent.setSourceType(sourceType);
            Utils.moveToFragment(WriteCommentFragment.class, CommentDetailsFragment.this, "WriteCommentFragment");
            EventBus.getDefault().post(commentDetailEvent);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mcr != null) {
            mContext.unregisterReceiver(mcr);
            mcr = null;
        }
    }
}
