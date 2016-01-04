package bluemobi.iuv.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.bumptech.glide.Glide;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import bluemobi.iuv.R;
import bluemobi.iuv.activity.MainActivity;
import bluemobi.iuv.adapter.CommonAdapter;
import bluemobi.iuv.adapter.ViewHolder;
import bluemobi.iuv.annotation.ContentView;
import bluemobi.iuv.app.IuwApplication;
import bluemobi.iuv.entity.Lonlat;
import bluemobi.iuv.entity.MarketInfoBean;
import bluemobi.iuv.eventbus.BaseEvent;
import bluemobi.iuv.eventbus.StoreDetailsEvent;
import bluemobi.iuv.eventbus.TopSortEvent;
import bluemobi.iuv.eventbus.TreasureEvent;
import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.model.HotShopInfo;
import bluemobi.iuv.network.model.HotShopListMoedl;
import bluemobi.iuv.network.model.UserInfo;
import bluemobi.iuv.network.request.HotShopListRequest;
import bluemobi.iuv.network.response.HotShopListResponse;
import bluemobi.iuv.util.Constants;
import bluemobi.iuv.util.SharedPreferencesUtil;
import bluemobi.iuv.util.StringUtils;
import bluemobi.iuv.util.Utils;
import bluemobi.iuv.util.WebUtils;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by liufy on 2015/11/2.
 * P8_8特产店-20151019
 P8_8_1-特产店类别-20151019
 */
@ContentView(R.layout.fragment_spec_stores)
public class SupermarketFragment extends BaseFragment {


    private String currentpage;

    private String divisionCode;
    private String divisionType;
    private String shopsTypeId;
    private String shopsTypeName;
    private String sortType;
    private String mainID;
    private List<MarketInfoBean> marketInfoBeans = new ArrayList<MarketInfoBean>();

    public CommonAdapter<HotShopInfo> adapter;

    public List<HotShopInfo> dataList = new ArrayList<HotShopInfo>();

    public SupermarketFragment() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity) mContext).setHomeBarSytle(shopsTypeName, true);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (((MainActivity) mContext).isHiddChange) {
            ((MainActivity) mContext).setHomeBarSytle(shopsTypeName, true);
        }


    }


    public void onEvent(BaseEvent baseEvent) {

        if (baseEvent instanceof TopSortEvent) {
            TopSortEvent topSortEvent = (TopSortEvent) baseEvent;
            divisionCode = topSortEvent.getDivisionCode();
            divisionType = topSortEvent.getDivisionType();
            shopsTypeId = topSortEvent.getShopsTypeId();
            shopsTypeName = topSortEvent.getShopsTypeName();
            sortType = topSortEvent.getSortType();
            mainID = topSortEvent.getId();
        }
    }


    @Override
    protected IuwHttpJsonRequest initRequest() {
        return commonRequest(0);
    }

    @Override
    protected void successViewCompleted(View successView) {
        ButterKnife.bind(this, successView);
        plv_refresh = (PullToRefreshListView) successView.findViewById(R.id.plv_refresh);
        initPullToRefresh(plv_refresh);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    public void showListData(HotShopListMoedl list) {
        if (list == null) {
            return;
        }
        if (list.getInfo().size() == 0) {
            return;
        }
        if (list.getCurrentpage().equals("1")) {
            dataList.clear();
            dataList.addAll(list.getInfo());
        } else {
            dataList.addAll(list.getInfo());

        }


        if (list == null) {
            return;
        }
        if (list.getInfo().size() == 0) {
            return;
        }
        if (currentpage.equals("0")) {
            dataList.clear();
        }
        for (HotShopInfo lineDto : list.getInfo()) {
            dataList.add(lineDto);
            MarketInfoBean marketInfoBean = new MarketInfoBean();
            marketInfoBean.setShopId(lineDto.getId());
            marketInfoBean.setShopLat(lineDto.getShopsLatitude());
            marketInfoBean.setShopLon(lineDto.getShopsLongitude());
            marketInfoBean.setShopName(lineDto.getShopName());
            marketInfoBean.setDistance(lineDto.getDistance());
            marketInfoBeans.add(marketInfoBean);
        }
        IuwApplication.getInstance().iuwMarketInfoBeans = null;
        IuwApplication.getInstance().iuwMarketInfoBeans = (ArrayList<MarketInfoBean>) marketInfoBeans;


        if (adapter == null) {
            plv_refresh.setAdapter(
                    adapter = new CommonAdapter<HotShopInfo>(mContext, dataList, R.layout.lv_page_market_item) {
                        @Override
                        public void convert(final ViewHolder helper, HotShopInfo bean) {
                            ImageView iv_treasure_icon = helper.getView(R.id.iv_treasure_icon);
                            Glide.with(mContext).load(bean.getPreciousImgPath()).placeholder(R.drawable.treasure_item_icon).into(iv_treasure_icon);
                            iv_treasure_icon.setOnClickListener(new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View v) {
                                                                        ((MainActivity) mContext).isHiddChange = false;
                                                                        ((MainActivity) mContext).selectionTab(2);
                                                                        TreasureEvent treasureEvent = new TreasureEvent();
                                                                        treasureEvent.setTreasureID(dataList.get(helper.getPosition()).getId());
                                                                        Utils.moveToFragment(TreasureDetailFragment.class, SupermarketFragment.this, "treasureDetailFragment");
                                                                        EventBus.getDefault().post(treasureEvent);
                                                                    }
                                                                }
                            );
                            ImageView iv_goods_icon = helper.getView(R.id.iv_goods_icon);
                            Glide.with(mContext).load(bean.getShopImgPath()).placeholder(R.drawable.p8_2_shop_default).into(iv_goods_icon);
                            //点击商场
                            iv_goods_icon.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ((MainActivity) mContext).isHiddChange = false;
                                    Utils.moveToFragment(MarketInfoFragment.class, SupermarketFragment.this, "MarketInfoFragment");
                                    IuwApplication.getInstance().lonlat = null;
                                    StoreDetailsEvent storeDetailsEvent = new StoreDetailsEvent();
                                    storeDetailsEvent.setShopID(dataList.get(helper.getPosition()).getId());
                                    String lat = dataList.get(helper.getPosition()).getShopsLatitude();
                                    String lon = dataList.get(helper.getPosition()).getShopsLongitude();
                                    storeDetailsEvent.setShopLat(lat);
                                    storeDetailsEvent.setShopLon(lon);
                                    Lonlat lonlat = new Lonlat();
                                    lonlat.setLat(lat);
                                    lonlat.setLon(lon);
                                    IuwApplication.getInstance().lonlat = lonlat;
                                    EventBus.getDefault().post(storeDetailsEvent);
                                }
                            });

                            TextView tv_home_title = helper.getView(R.id.tv_home_title);
                            tv_home_title.setText(bean.getShopName() == null || "null".equals(bean.getShopName()) ? "" : bean.getShopName());
                            TextView tv_home_content = helper.getView(R.id.tv_home_content);
                            tv_home_content.setText(bean.getPreciousName() == null || "null".equals(bean.getPreciousName()) ? "" : bean.getPreciousName());
                            TextView content = helper.getView(R.id.tv_home_content_detail);
                            content.setText(bean.getPreciousDescription() == null || "null".equals(bean.getPreciousDescription()) ? "" : bean.getPreciousDescription());
                            TextView shoucang = helper.getView(R.id.tv_collect);
                            shoucang.setText(bean.getCollectionNum());

                            TextView zan = helper.getView(R.id.tv_praise);
                            zan.setText(StringUtils.isEmpty(bean.getPraiseNum()) ? "0" : bean.getPraiseNum());
                            TextView juli = helper.getView(R.id.tv_location);
                            juli.setText(bean.getDistance());

                            ImageView iv_collect = helper.getView(R.id.iv_collect);
                            iv_collect.setTag(R.id.iv_collect, bean.getId());
                            iv_collect.setTag(R.id.tv_location, dataList.indexOf(bean));
                            if (StringUtils.isEmpty(bean.getCollectionId()) || "null".equals(bean.getCollectionId())) {
                                iv_collect.setImageResource(R.drawable.collect_n);
                            } else {
                                iv_collect.setImageResource(R.drawable.collect_p);
                            }
                            iv_collect.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    collect((String) v.getTag(R.id.iv_collect), (Integer) v.getTag(R.id.tv_location), refreshCollect);
                                }
                            });
                            ImageView iv_praise = helper.getView(R.id.iv_praise);
                            if (StringUtils.isEmpty(bean.getPraiseId()) || "null".equals(bean.getPraiseId())) {
                                iv_praise.setImageResource(R.drawable.praise_n);
                            } else {
                                iv_praise.setImageResource(R.drawable.praise_p);
                            }
                            iv_praise.setTag(R.id.iv_praise, bean.getId());
                            iv_praise.setTag(R.id.tv_home_title, dataList.indexOf(bean));
                            iv_praise.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    givePraise((String) v.getTag(R.id.iv_praise), (Integer) v.getTag(R.id.tv_home_title), refresh);
                                }
                            });


                        }
                    });

        } else {
            adapter.notifyDataSetChanged();
        }
    }

    RefreshCollectListener refreshCollect = new RefreshCollectListener() {
        @Override
        public void refreshView(int p) {
            dataList.get(p).setCollectionId("1");
            dataList.get(p).setCollectionNum(String.valueOf(Integer.valueOf(dataList.get(p).getCollectionNum()) + 1));
            adapter.notifyDataSetChanged();
            Toast.makeText(mContext, "收藏成功", Toast.LENGTH_SHORT).show();

        }
    };

    RefreshPraiseListener refresh = new RefreshPraiseListener() {
        @Override
        public void refreshView(int p) {
            dataList.get(p).setPraiseId("1");
            dataList.get(p).setPraiseNum(String.valueOf(Integer.valueOf(dataList.get(p).getPraiseNum()) + 1));
            adapter.notifyDataSetChanged();

        }
    };

    @Override
    protected boolean getPage(int type) {
        if (!super.getPage(type)) {
            return false;
        }
        connectToServer();
        return true;

    }

    private void connectToServer() {
        WebUtils.doPost( commonRequest(getCurPage()));
    }


    private IuwHttpJsonRequest commonRequest(int startPage)
    {
        HotShopListRequest request = new HotShopListRequest(
                new Response.Listener<HotShopListResponse>() {
                    @Override
                    public void onResponse(HotShopListResponse response) {
                        Utils.closeDialog();
                        plv_refresh.onRefreshComplete();
                        if (response != null && response.getStatus() == 0) {
                            currentpage = response.getData().getCurrentpage();
                            showListData(response.getData());


                        }
                    }
                }, (Response.ErrorListener) getActivity());
        UserInfo userInfo = IuwApplication.getInstance().getMyUserInfo();
        if (userInfo != null) {
            request.setUserId(IuwApplication.getInstance().getMyUserInfo().getUserId());
        }
        String lat = SharedPreferencesUtil.getFromFileByDefault(mContext, "geoLat", "41.927618");
        String lon = SharedPreferencesUtil.getFromFileByDefault(mContext, "geoLng", "123.40706");
        request.setLatitude(lat);
        request.setLongitude(lon);
        if ("1".equals(divisionType)) {

            request.setShopCityCode(divisionCode == null ? "" : divisionCode);
        } else {
            request.setShopDistrictCode(divisionCode == null ? "" : divisionCode);
        }
        request.setShopsTypeId(mainID);
        request.setCurrentnum(Constants.CURRENTNUMBASE + "");
        request.setCurrentpage(startPage+"");
        request.setSortType(sortType == null ? "" : sortType);
        return request;
    }


}
