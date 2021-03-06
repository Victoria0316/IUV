package bluemobi.iuv.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import bluemobi.iuv.R;
import bluemobi.iuv.activity.MainActivity;
import bluemobi.iuv.adapter.HotMallAdapter;
import bluemobi.iuv.annotation.ContentView;
import bluemobi.iuv.app.IuwApplication;
import bluemobi.iuv.app.TitleBarManager;
import bluemobi.iuv.base.BaseActivity;
import bluemobi.iuv.entity.HotMallGoods;
import bluemobi.iuv.eventbus.BaseEvent;
import bluemobi.iuv.eventbus.HotShopEvent;
import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.model.GoodInfo;
import bluemobi.iuv.network.model.HotGoods;
import bluemobi.iuv.network.request.CollectShopRequest;
import bluemobi.iuv.network.request.GivePraiseRequest;
import bluemobi.iuv.network.request.QueryThreeHotGoodsRequest;
import bluemobi.iuv.network.response.GivePraiseResponse;
import bluemobi.iuv.network.response.QueryThreeHotGoodsResponse;
import bluemobi.iuv.util.Constants;
import bluemobi.iuv.util.GlideCircleTransform;
import bluemobi.iuv.util.StringUtils;
import bluemobi.iuv.util.Utils;
import bluemobi.iuv.util.WebUtils;
import bluemobi.iuv.view.CircleImageView;
import bluemobi.iuv.view.LoadingPage;
import bluemobi.iuv.view.RatioImageView;
import bluemobi.iuv.view.RoundImageView;
import bluemobi.iuv.view.ScorllListView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * gaoxy P8_3_1商品列表-热门商场-修改
 * p8-3-1
 */
@ContentView(R.layout.fragment_hot_mall)
public class HotMallFragment extends BaseFragment {
    protected View.OnClickListener selectListener;
    protected HotMallAdapter adapter;
    @Bind(R.id.plv_refresh)
    protected ScorllListView list;
    protected boolean zanFlag = false;
    protected boolean soucangFlag = false;

    @Bind(R.id.list_sort)
    protected ImageView sort;

    @Bind(R.id.good_num)
    protected TextView good_num;

    @Bind(R.id.pic)
    protected CircleImageView pic;

    @Bind(R.id.name)
    protected TextView name;

    @Bind(R.id.location_text)
    protected TextView location_text;

    @Bind(R.id.good)
    protected ImageView good;

    @Bind(R.id.shoucang)
    protected ImageView shoucang;

    private HotShopEvent mHotShopEvent;

    private String shopid;

    private List<GoodInfo> HotGoodsList = new ArrayList<GoodInfo>();//remn
    private List<GoodInfo> CommonGoodsList = new ArrayList<GoodInfo>();//putong

    @Bind(R.id.hot_iamge)
    protected RatioImageView hot_iamge;

    @Bind(R.id.left_pic)
    protected RatioImageView left_pic;

    @Bind(R.id.right_pic)
    protected RatioImageView right_pic;

    @Bind(R.id.left_name)
    protected TextView left_name;
    @Bind(R.id.left_price)
    protected TextView left_price;

    @Bind(R.id.right_name)
    protected TextView right_name;
    @Bind(R.id.right_price)
    protected TextView right_price;

    @Bind(R.id.left_view)
    protected View left_view;

    @Bind(R.id.right_view)
    protected View right_view;


    public HotMallFragment() {
        EventBus.getDefault().register(this);
    }

    public void onEvent(BaseEvent baseEvent) {
        mHotShopEvent = (HotShopEvent) baseEvent;
        shopid = mHotShopEvent.getId();
    }

    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity) mContext).setHomeBarSytle(mHotShopEvent.getZone(), true);
    }

    @Override
    protected void successViewCompleted(View successView) {
        ButterKnife.bind(this, successView);
        right_pic.setRatio(0.9237f);
        left_pic.setRatio(0.9237f);
        hot_iamge.setRatio(2.00597f);
        RequestManager glideRequest;
        glideRequest = Glide.with(mContext);
        glideRequest.load(mHotShopEvent.getImagePath()).transform(new GlideCircleTransform(mContext)).into(pic);
        name.setText(mHotShopEvent.getName());
        location_text.setText(mHotShopEvent.getAddress());

        if (StringUtils.isEmpty(mHotShopEvent.getIsCollected()) || "null".equals(mHotShopEvent.getIsCollected())) {
            shoucang.setImageResource(R.drawable.collect_n);
        } else {
            shoucang.setImageResource(R.drawable.collect_p);
        }
        if (StringUtils.isEmpty(mHotShopEvent.getIsPraise()) || "null".equals(mHotShopEvent.getIsPraise())) {
            good.setImageResource(R.drawable.praise_n);
        } else {
            good.setImageResource(R.drawable.praise_p);
        }
        if (StringUtils.isNotEmpty(mHotShopEvent.getPraiseNum()) && !"null".equals(mHotShopEvent.getPraiseNum())) {
            good_num.setText(mHotShopEvent.getPraiseNum());
        } else {
            good_num.setText("0");
        }

        shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectShop((ImageView) v, refreshCollectShop);
            }
        });

        good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                praiseShop((ImageView) v, refreshPraiseShop);
            }
        });

    }

    @Override
    protected IuwHttpJsonRequest initRequest() {
        QueryThreeHotGoodsRequest request = new QueryThreeHotGoodsRequest
                (
                        new Response.Listener<QueryThreeHotGoodsResponse>() {
                            @Override
                            public void onResponse(QueryThreeHotGoodsResponse response) {
                                Utils.closeDialog();
                                if (response != null && response.getStatus() == 0) {
                                    showData(response.getData());
                                }
                            }
                        }, (Response.ErrorListener) getActivity());
        if (IuwApplication.getInstance().getMyUserInfo() != null)
            request.setUserId(IuwApplication.getInstance().getMyUserInfo().getUserId());
        request.setShopsId(shopid);
        request.setCurrentnum("100");
        request.setCurrentpage("0");
        request.setSortType("");
        return request;
    }

    private void showData(HotGoods allDatas) {
        if (allDatas == null) {
            return;
        }
        if (allDatas.getProductsList() != null && allDatas.getProductsList().getInfo() != null && allDatas.getProductsList().getInfo().size() != 0) {
            if (allDatas.getProductsList().getCurrentpage().equals("1")) {
                CommonGoodsList.clear();
                CommonGoodsList.addAll(allDatas.getProductsList().getInfo());
            } else {
                CommonGoodsList.addAll(allDatas.getProductsList().getInfo());
            }
            if (adapter == null) {
                adapter = new HotMallAdapter(mContext, CommonGoodsList, this);
                list.setAdapter(adapter);
            } else {
                adapter.notifyDataSetChanged();
            }
        }

        if (allDatas.getHotList() != null && allDatas.getHotList() != null && allDatas.getHotList().size() != 0) {
            if (allDatas.getHotList().size() == 1) {
                Glide.with(mContext).load(allDatas.getHotList().get(0).getProductImgPath()).placeholder(R.drawable.p8_2_shop_default).into(hot_iamge);
                left_pic.setImageResource(R.drawable.p8_2_shop_default);
                left_name.setText("");
                left_price.setText("");
                right_pic.setImageResource(R.drawable.p8_2_shop_default);
                right_name.setText("");
                right_price.setText("");
                right_view.setVisibility(View.INVISIBLE);
                left_view.setVisibility(View.INVISIBLE);
            } else if (allDatas.getHotList().size() == 2) {
                Glide.with(mContext).load(allDatas.getHotList().get(0).getProductImgPath()).placeholder(R.drawable.p8_2_shop_default).into(hot_iamge);
                Glide.with(mContext).load(allDatas.getHotList().get(1).getProductImgPath()).placeholder(R.drawable.p8_2_shop_default).into(left_pic);
                left_name.setText(allDatas.getHotList().get(1).getProductName());
                left_price.setText("¥ " + allDatas.getHotList().get(1).getCustomerPrice());
                right_pic.setImageResource(R.drawable.p8_2_shop_default);
                right_name.setText("");
                right_price.setText("");
                right_view.setVisibility(View.INVISIBLE);
            } else if (allDatas.getHotList().size() > 2) {
                Glide.with(mContext).load(allDatas.getHotList().get(0).getProductImgPath()).placeholder(R.drawable.p8_2_shop_default).into(hot_iamge);
                Glide.with(mContext).load(allDatas.getHotList().get(1).getProductImgPath()).placeholder(R.drawable.p8_2_shop_default).into(left_pic);
                left_name.setText(allDatas.getHotList().get(1).getProductName());
                left_price.setText("¥ " + allDatas.getHotList().get(1).getCustomerPrice());
                Glide.with(mContext).load(allDatas.getHotList().get(2).getProductImgPath()).placeholder(R.drawable.p8_2_shop_default).into(right_pic);
                right_name.setText(allDatas.getHotList().get(2).getProductName());
                right_price.setText("¥ " + allDatas.getHotList().get(2).getCustomerPrice());
            }
        } else {
            hot_iamge.setImageResource(R.drawable.p8_2_shop_default);
            left_pic.setImageResource(R.drawable.p8_2_shop_default);
            left_name.setText("");
            left_price.setText("");
            right_pic.setImageResource(R.drawable.p8_2_shop_default);
            right_name.setText("");
            right_price.setText("");
            right_view.setVisibility(View.INVISIBLE);
            left_view.setVisibility(View.INVISIBLE);
        }
    }


    public void getDataShops() {

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
        request.setId(shopid);
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
            good_num.setText(String.valueOf(Integer.valueOf(good_num.getText().toString()) + 1));
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
        request.setId(shopid);
        request.setUserId(IuwApplication.getInstance().getMyUserInfo().getUserId());
        Utils.showProgressDialog(mContext);
        WebUtils.doPost(request);
    }

    @OnClick(R.id.hot_iamge)
    public void hotImage() {
        Utils.moveToFragment(BrandDetailFragment.class, HotMallFragment.this, "brandlistfragment");
    }

    @OnClick(R.id.left_right_lay)
    public void leftLay() {
        Utils.moveToFragment(BrandDetailFragment.class, HotMallFragment.this, "brandlistfragment");
    }


    @OnClick(R.id.right_image_lay)
    public void rightLay() {
        Utils.moveToFragment(BrandDetailFragment.class, HotMallFragment.this, "brandlistfragment");
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (((MainActivity) mContext).isHiddChange) {
            ((MainActivity) mContext).setHomeBarSytle(((MainActivity) mContext).titleCity, true);
        }


    }

    @Override
    public void initData(Bundle savedInstanceState) {

        isShowLoadPage = false;
    }


}
