package bluemobi.iuv.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

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
import bluemobi.iuv.network.model.UserInfo;
import bluemobi.iuv.network.request.LargeDepartmentStoresRequest;
import bluemobi.iuv.network.response.LargeDepartmentStoresResponse;
import bluemobi.iuv.util.Constants;
import bluemobi.iuv.util.SharedPreferencesUtil;
import bluemobi.iuv.util.StringUtils;
import bluemobi.iuv.util.Utils;
import bluemobi.iuv.util.WebUtils;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by liufy on 2015/11/2.
 * P8_6购物街-20151019
 */
@ContentView(R.layout.fragment_shoping_street)
public class ShoppingStreetFragment extends BaseFragment {


    private String currentpage;

    private String divisionCode;
    private String divisionType;
    private String shopsTypeId;
    private String shopsTypeName;
    private String sortType;
    /**
     * 购物街 ID
     */
    private String mainID;
    private CommonAdapter<LargeDepartmentStoresResponse.LargeDepartmentStoresDto> adapter;

    private List<LargeDepartmentStoresResponse.LargeDepartmentStoresDto> dataList = new ArrayList<LargeDepartmentStoresResponse.LargeDepartmentStoresDto>();

    private boolean isDetailFragment = false;

    private List<MarketInfoBean> marketInfoBeans = new ArrayList<MarketInfoBean>();

    public ShoppingStreetFragment() {
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




    @Override
    protected boolean getPage(int type) {
        if (!super.getPage(type)) {
            return false;
        }
        connectToServer();
        return true;

    }

    private void connectToServer() {
        WebUtils.doPost(commonRequest(getCurPage()));
    }


    private IuwHttpJsonRequest commonRequest(int startPage)
    {
        LargeDepartmentStoresRequest request = new LargeDepartmentStoresRequest(
                new Response.Listener<LargeDepartmentStoresResponse>() {
                    @Override
                    public void onResponse(LargeDepartmentStoresResponse response) {
                        Utils.closeDialog();
                        plv_refresh.onRefreshComplete();
                        if (dataList != null && dataList.size() > 0) {
                            dataList.clear();
                        }
                        LargeDepartmentStoresResponse.LargeDepartmentStoresData data = response.data;
                        if (data != null) {
                            ArrayList<LargeDepartmentStoresResponse.LargeDepartmentStoresDto> info = data.info;
                            currentpage = data.getCurrentpage();
                            wrapLvData(info);
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
        if ("2".equals(divisionType)) {
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


    private void wrapLvData(ArrayList<LargeDepartmentStoresResponse.LargeDepartmentStoresDto> info) {
        if (info == null) {
            return;
        }
        if (info.size() == 0) {
            return;
        }
        if (currentpage.equals("0")) {
            dataList.clear();
        }
        for (LargeDepartmentStoresResponse.LargeDepartmentStoresDto lineDto : info) {
            dataList.add(lineDto);
            MarketInfoBean marketInfoBean = new MarketInfoBean();
            marketInfoBean.setShopId(lineDto.id);
            marketInfoBean.setShopLat(lineDto.shopsLatitude);
            marketInfoBean.setShopLon(lineDto.shopsLongitude);
            marketInfoBean.setShopName(lineDto.shopName);
            marketInfoBean.setDistance(lineDto.distance);
            marketInfoBeans.add(marketInfoBean);
        }
        IuwApplication.getInstance().iuwMarketInfoBeans =null;
        IuwApplication.getInstance().iuwMarketInfoBeans = (ArrayList<MarketInfoBean>) marketInfoBeans;

        plv_refresh.setAdapter(
                adapter = new CommonAdapter<LargeDepartmentStoresResponse.LargeDepartmentStoresDto>(mContext,
                        dataList, R.layout.lv_page_market_item) {

                    @Override
                    public void convert(final ViewHolder helper, LargeDepartmentStoresResponse.LargeDepartmentStoresDto bean) {

                        ImageView iv_goods_icon = helper.getView(R.id.iv_goods_icon);
                        Glide.with(mContext).load(bean.shopImgPath).placeholder(R.drawable.p8_2_shop_default).into(iv_goods_icon);
                        ImageView iv_treasure_icon = helper.getView(R.id.iv_treasure_icon);
                        //Glide.with(pageContext).load(dataList.get(0).preciousImgPath).placeholder(R.drawable.p8_2_shop_default).into(iv_treasure_icon);
                        helper.setText(R.id.tv_home_title, bean.shopName == null || "null".equals(bean.shopName) ? "" : bean.shopName);
                        helper.setText(R.id.tv_home_content, bean.preciousName == null || "null".equals(bean.preciousName) ? "" : bean.preciousName);
                        helper.setText(R.id.tv_home_content_detail, bean.preciousDescription == null || "null".equals(bean.preciousDescription) ? "" : bean.preciousDescription);
                        helper.setText(R.id.tv_collect, StringUtils.isEmpty(bean.collectionNum) ? "0" : bean.collectionNum);
                        helper.setText(R.id.tv_praise, StringUtils.isEmpty(bean.praiseNum) ? "0" : bean.praiseNum);
                        helper.setText(R.id.tv_location, StringUtils.isEmpty(bean.distance) ? "0" : bean.distance);
                        if (StringUtils.isEmpty(bean.collectionId) || "null".equals(bean.collectionId)) {
                            helper.setImageResource(R.id.iv_collect, R.drawable.collect_n);

                        } else {
                            helper.setImageResource(R.id.iv_collect, R.drawable.collect_p);
                        }

                        if (StringUtils.isEmpty(bean.praiseId) || "null".equals(bean.praiseId)) {
                            helper.setImageResource(R.id.iv_praise, R.drawable.praise_n);

                        } else {
                            helper.setImageResource(R.id.iv_praise, R.drawable.praise_p);
                        }

                        ImageView iv_collect = helper.getView(R.id.iv_collect);
                        iv_collect.setTag(R.id.iv_collect, bean.id);
                        iv_collect.setTag(R.id.tv_location, dataList.indexOf(bean));
                        iv_collect.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                collect((String) v.getTag(R.id.iv_collect), (Integer) v.getTag(R.id.tv_location), refreshCollect);
                            }
                        });


                        ImageView iv_praise = helper.getView(R.id.iv_praise);
                        iv_praise.setTag(R.id.iv_praise, bean.id);
                        iv_praise.setTag(R.id.tv_home_title, dataList.indexOf(bean));
                        iv_praise.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                givePraise((String) v.getTag(R.id.iv_praise), (Integer) v.getTag(R.id.tv_home_title), refresh);
                            }
                        });

                        iv_treasure_icon.setOnClickListener(new View.OnClickListener()

                                                            {
                                                                @Override
                                                                public void onClick(View v) {
                                                                    ((MainActivity) mContext).isHiddChange = false;
                                                                    ((MainActivity) mContext).selectionTab(2);
                                                                    Utils.moveToFragment(TreasureDetailFragment.class, ShoppingStreetFragment.this, "treasureDetailFragment");
                                                                    TreasureEvent treasureEvent = new TreasureEvent();
                                                                    treasureEvent.setTreasureID(dataList.get(helper.getPosition()).preciousId);
                                                                    EventBus.getDefault().post(treasureEvent);
                                                                }
                                                            }

                        );
                        iv_goods_icon.setOnClickListener(new View.OnClickListener(

                                                         )

                                                         {
                                                             @Override
                                                             public void onClick(View v) {
                                                                 ((MainActivity) mContext).isHiddChange = false;


                                                                     Utils.moveToFragment(MarketInfoFragment.class, ShoppingStreetFragment.this, "MarketInfoFragment");

                                                                 IuwApplication.getInstance().lonlat = null;
                                                                 StoreDetailsEvent storeDetailsEvent = new StoreDetailsEvent();
                                                                 storeDetailsEvent.setShopID(dataList.get(helper.getPosition()).id);
                                                                 String lat = dataList.get(helper.getPosition()).shopsLatitude;
                                                                 String lon = dataList.get(helper.getPosition()).shopsLongitude;
                                                                 storeDetailsEvent.setShopLat(lat);
                                                                 storeDetailsEvent.setShopLon(lon);
                                                                 Lonlat lonlat = new Lonlat();
                                                                 lonlat.setLat(lat);
                                                                 lonlat.setLon(lon);
                                                                 IuwApplication.getInstance().lonlat = lonlat;
                                                                 EventBus.getDefault().post(storeDetailsEvent);
                                                             }
                                                         }

                        );

                    }
                });


    }

     RefreshCollectListener refreshCollect = new RefreshCollectListener() {
        @Override
        public void refreshView(int p) {
            dataList.get(p).collectionId = "1";
            String collectionNum = StringUtils.isEmpty(dataList.get(p).collectionNum)?"0":dataList.get(p).collectionNum;
            dataList.get(p).collectionNum = (String.valueOf(Integer.valueOf(collectionNum) + 1));
            adapter.notifyDataSetChanged();

        }
    };

    RefreshPraiseListener refresh = new RefreshPraiseListener() {
        @Override
        public void refreshView(int p) {
            dataList.get(p).praiseId = "1";
            String praiseNum = StringUtils.isEmpty(dataList.get(p).praiseNum)?"0":dataList.get(p).praiseNum;
            dataList.get(p).praiseNum = (String.valueOf(Integer.valueOf(praiseNum) + 1));
            adapter.notifyDataSetChanged();

        }
    };


}
