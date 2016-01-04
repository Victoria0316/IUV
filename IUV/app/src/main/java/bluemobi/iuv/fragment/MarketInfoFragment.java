package bluemobi.iuv.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.bumptech.glide.Glide;

import bluemobi.iuv.R;
import bluemobi.iuv.activity.MainActivity;
import bluemobi.iuv.annotation.ContentView;
import bluemobi.iuv.app.IuwApplication;
import bluemobi.iuv.base.utils.Logger;
import bluemobi.iuv.eventbus.BaseEvent;
import bluemobi.iuv.eventbus.CommentDetailEvent;
import bluemobi.iuv.eventbus.HotShopEvent;
import bluemobi.iuv.eventbus.ShopEvent;
import bluemobi.iuv.eventbus.StoreDetailsEvent;
import bluemobi.iuv.eventbus.TreasureEvent;
import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.request.CollectShopRequest;
import bluemobi.iuv.network.request.GivePraiseRequest;
import bluemobi.iuv.network.request.StoreDetailsRequest;
import bluemobi.iuv.network.response.GivePraiseResponse;
import bluemobi.iuv.network.response.StoreDetailsResponse;
import bluemobi.iuv.util.Constants;
import bluemobi.iuv.util.StringUtils;
import bluemobi.iuv.util.Utils;
import bluemobi.iuv.util.WebUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * gaoxy
 * P8_4商场详情页面
 */
@ContentView(R.layout.fragment_market_info)
public class MarketInfoFragment extends BaseFragment {

    @Bind(R.id.name)
    protected TextView name;
    @Bind(R.id.pic)
    protected ImageView pic;

    @Bind(R.id.location_text)
    protected TextView location_text;
    @Bind(R.id.tv_shop_detail)
    protected TextView tv_shop_detail;
    @Bind(R.id.worktime_text)
    protected TextView worktime_text;
    @Bind(R.id.net_text)
    protected TextView net_text;
    @Bind(R.id.tel_text)
    protected TextView tel_text;
    @Bind(R.id.activity_text)
    protected TextView activity_text;
    @Bind(R.id.tv_comment_num)
    protected TextView tv_comment_num;
    @Bind(R.id.iv_shop_collect)
    protected ImageView iv_shop_collect;
    @Bind(R.id.iv_shop_praise)
    protected ImageView iv_shop_praise;
    @Bind(R.id.iv_look)
    protected ImageView iv_look;
    @Bind(R.id.tv_comment_text)
    protected TextView tv_comment_text;
    @Bind(R.id.iv_treasure)
    protected ImageView iv_treasure;
    @Bind(R.id.vip)
    protected ImageView vip;
    @Bind(R.id.dianping_pic)
    protected ImageView dianping_pic;

    @Bind(R.id.dianping_lay)
    protected  LinearLayout dianping_lay;

    @Bind(R.id.zan_num)
    protected TextView zan_num;

    WindowManager mWindowManager;
    WindowManager.LayoutParams wmParams;
    LinearLayout mFloatLayout;
    RelativeLayout mFloatView;

    private int mMarketType = 0;

    private String shopID;

    private String treasureId;

    private StoreDetailsResponse.StoreDetailsData data;

    private String zone;

    private String lon;
    private String lat;


    public MarketInfoFragment() {
        EventBus.getDefault().register(this);
    }


    public void onEvent(BaseEvent baseEvent) {
        // TODO: 2015/11/5
        int eventType = baseEvent.getEventType();
        if (baseEvent instanceof StoreDetailsEvent && eventType == Constants.LARGE_HOT_MARKET) {
            StoreDetailsEvent storeDetailsEvent = (StoreDetailsEvent) baseEvent;
            shopID = storeDetailsEvent.getShopID();
            lon = storeDetailsEvent.getShopLon();
            lat = storeDetailsEvent.getShopLat();
            mMarketType = storeDetailsEvent.getMarketType();
            Logger.e("MarketInfoFragment-mMarketType-->", mMarketType);
        } else if (baseEvent instanceof ShopEvent) {
            shopID = ((ShopEvent) baseEvent).getId(); ////MyNotifications  点击通知跳转到 商店详情页面p8-4
            Logger.e("MarketInfoFragment-ShopEvent-->", mMarketType);
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity) mContext).setDetailStoreBarNaviSytle("商场详情", 1);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (((MainActivity) mContext).isHiddChange) {
            ((MainActivity) mContext).setDetailStoreBarNaviSytle("商场详情", 1);

        }


    }


    @Override
    protected IuwHttpJsonRequest initRequest() {
        StoreDetailsRequest request = new StoreDetailsRequest
                (
                        new Response.Listener<StoreDetailsResponse>() {
                            @Override
                            public void onResponse(StoreDetailsResponse response) {
                                Utils.closeDialog();
                                data = response.getData();
                                if (data != null) {
                                    bindData(data);
                                    treasureId = data.preciousId;
                                    zone = data.shopDistrictName;
                                }

                            }
                        }, (Response.ErrorListener) getActivity());
        if (IuwApplication.getInstance().getMyUserInfo() != null)
            request.setUserId(IuwApplication.getInstance().getMyUserInfo().getUserId());
        request.setId(shopID);
        return request;

    }

    private void bindData(StoreDetailsResponse.StoreDetailsData data) {
        name.setText(data.shopName);
        tv_shop_detail.setText(data.shopIntroduce);
        location_text.setText(data.shopAddress);
        worktime_text.setText(data.shopsBusinessHours);
        net_text.setText(data.shopsWebsiteAddress);
        tel_text.setText(data.shopCellphone);
        activity_text.setText(data.shopsPromotionInfo);
        if (StringUtils.isNotEmpty(data.commentNum)) {
            tv_comment_num.setText("(" + data.commentNum + ")");
        } else {
            tv_comment_num.setText("(0)");
        }
        Glide.with(this).load(data.shopImgPath).placeholder(R.drawable.shop_detail_default).into(pic);


        if (IuwApplication.getInstance().getMyUserInfo() != null)
        {
            if (StringUtils.isNotEmpty(data.praiseNum) && !"null".equals(data.praiseNum)) {
                zan_num.setText(data.praiseNum);
                zan_num.setTextColor(getResources().getColor(R.color.common_blue));
            } else {
                zan_num.setText("0");
                zan_num.setTextColor(getResources().getColor(R.color.common_gray));
            }
        }else
        {
            zan_num.setTextColor(getResources().getColor(R.color.common_gray));
            if (StringUtils.isNotEmpty(data.praiseNum) && !"null".equals(data.praiseNum)) {
                zan_num.setText(data.praiseNum);

            } else {
                zan_num.setText("0");
            }
        }

        if (StringUtils.isEmpty(data.collectionId) || "null".equals(data.collectionId)) {
            iv_shop_collect.setImageResource(R.drawable.collect_n);
        } else {
            iv_shop_collect.setImageResource(R.drawable.collect_p);
        }
        if (StringUtils.isEmpty(data.praiseId) || "null".equals(data.praiseId)) {
            iv_shop_praise.setImageResource(R.drawable.praise_n);
        } else {
            iv_shop_praise.setImageResource(R.drawable.praise_p);
        }

        iv_shop_praise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                praiseShop((ImageView) v, refreshPraiseShop);
            }
        });
        iv_shop_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectShop((ImageView) v, refreshCollectShop);
            }
        });
    }

    public interface RefreshCollectShopListener {
        public void refreshView(ImageView mView);
    }

    RefreshCollectShopListener refreshCollectShop = new RefreshCollectShopListener() {
        @Override
        public void refreshView(ImageView mv) {
            mv.setImageResource(R.drawable.collect_p);
            Toast.makeText(mContext, "收藏成功", Toast.LENGTH_SHORT).show();
        }
    };

    private void collectShop(final ImageView collectImage, final RefreshCollectShopListener listener) {
        CollectShopRequest request = new CollectShopRequest(
                new Response.Listener<GivePraiseResponse>() {
                    @Override
                    public void onResponse(GivePraiseResponse response) {
                        Utils.closeDialog();
                        if (response != null && response.getStatus() == 0) {
                            listener.refreshView(collectImage);
                        }
                    }
                }, (Response.ErrorListener) mContext);
        request.setId(shopID);
        request.setUserId(IuwApplication.getInstance().getMyUserInfo().getUserId());
        Utils.showProgressDialog(mContext);
        WebUtils.doPost(request);
    }


    public interface RefreshPraiseShopListener {
        public void refreshView(ImageView mView);
    }

    RefreshPraiseShopListener refreshPraiseShop = new RefreshPraiseShopListener() {
        @Override
        public void refreshView(ImageView mv) {
            mv.setImageResource(R.drawable.praise_p);
            zan_num.setText(String.valueOf(Integer.valueOf(zan_num.getText().toString()) + 1));
        }
    };

    private void praiseShop(final ImageView praiseImage, final RefreshPraiseShopListener listener) {
        GivePraiseRequest request = new GivePraiseRequest(
                new Response.Listener<GivePraiseResponse>() {
                    @Override
                    public void onResponse(GivePraiseResponse response) {
                        Utils.closeDialog();
                        if (response != null && response.getStatus() == 0) {
                            listener.refreshView(praiseImage);
                        }
                    }
                }, (Response.ErrorListener) mContext);
        request.setId(shopID);
        request.setUserId(IuwApplication.getInstance().getMyUserInfo().getUserId());
        Utils.showProgressDialog(mContext);
        WebUtils.doPost(request);
    }

    @OnClick(R.id.iv_look)
    public void look() {
        if (mMarketType == 2) {  //热门商场
            Utils.moveToFragment(HotMallFragment.class, MarketInfoFragment.this, "HotMallFragment");
            HotShopEvent mHotShopEvent = new HotShopEvent();
            mHotShopEvent.setId(shopID);
            mHotShopEvent.setName(data.shopName);
            mHotShopEvent.setAddress(data.shopAddress);
            mHotShopEvent.setImagePath(data.shopImgPath);
            mHotShopEvent.setIsCollected(data.collectionId);
            mHotShopEvent.setIsPraise(data.praiseId);
            mHotShopEvent.setPraiseNum(data.praiseNum);
            mHotShopEvent.setZone(zone);
            Logger.e("TAG mMarketType == HotMallFragment 》》", mMarketType);
            EventBus.getDefault().post(mHotShopEvent);

        } else { //mMarketType == 2 or else
            StoreDetailsEvent storeDetailsEvent = new StoreDetailsEvent();
            storeDetailsEvent.setShopID(shopID);
            storeDetailsEvent.setShopName(data.shopName);
            storeDetailsEvent.setCollectionId(data.collectionId);
            storeDetailsEvent.setCollectionNum(data.collectionNum);
            storeDetailsEvent.setPraiseId(data.praiseId);
            storeDetailsEvent.setPraiseNum(data.praiseNum);
            storeDetailsEvent.setShopDistrictName(data.shopDistrictName);
            Utils.moveToFragment(BrandListFragment.class, MarketInfoFragment.this, "BrandListFragment");
            EventBus.getDefault().post(storeDetailsEvent);
            Logger.e("TAG mMarketType == BrandListFragment 》》", mMarketType);
        }


    }

    @OnClick(R.id.dianping_lay)
    protected void clickComment()
    {
        //TODO
        CommentDetailEvent commentDetailEvent = new CommentDetailEvent();
        commentDetailEvent.setProductID(shopID);
        commentDetailEvent.setSourceType("2");
        Utils.moveToFragment(CommentDetailsFragment.class, MarketInfoFragment.this, "commentdetailsfragment");
        EventBus.getDefault().post(commentDetailEvent);
    }

    @Override
    protected void successViewCompleted(View successView) {
        ButterKnife.bind(this, successView);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
    }

    @OnClick(R.id.dianping_pic)
    public void dianping() {

        Utils.moveToFragment(CommentDetailsFragment.class, MarketInfoFragment.this, "CommentDetailsFragment");
    }

    @OnClick(R.id.iv_treasure)
    public void clickTreasure() {


        Utils.moveToFragment(TreasureDetailFragment.class, MarketInfoFragment.this, "BrandListFragment");
        TreasureEvent treasureEvent = new TreasureEvent();
        treasureEvent.setTreasureID(treasureId);
        EventBus.getDefault().post(treasureEvent);
    }

    @OnClick(R.id.vip)
    public void clickVip() {
        Utils.moveToFragment(VipCouponDetailFragment.class, MarketInfoFragment.this, "VipCouponDetailFragment");
    }

}
