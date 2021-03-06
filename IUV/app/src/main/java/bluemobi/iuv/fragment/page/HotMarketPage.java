package bluemobi.iuv.fragment.page;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import bluemobi.iuv.R;
import bluemobi.iuv.activity.MainActivity;
import bluemobi.iuv.adapter.CommonAdapter;
import bluemobi.iuv.adapter.ViewHolder;
import bluemobi.iuv.app.IuwApplication;
import bluemobi.iuv.entity.Lonlat;
import bluemobi.iuv.entity.MarketInfoBean;
import bluemobi.iuv.eventbus.StoreDetailsEvent;
import bluemobi.iuv.eventbus.TreasureEvent;
import bluemobi.iuv.fragment.BaseFragment;
import bluemobi.iuv.fragment.DetailFragment;
import bluemobi.iuv.fragment.HomeTreasureFragment;
import bluemobi.iuv.fragment.MarketInfoFragment;
import bluemobi.iuv.fragment.TreasureDetailFragment;
import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.model.HotShopInfo;
import bluemobi.iuv.network.model.HotShopListMoedl;
import bluemobi.iuv.network.model.UserInfo;
import bluemobi.iuv.network.request.HotShopListRequest;
import bluemobi.iuv.network.response.HotShopListResponse;
import bluemobi.iuv.network.response.LargeDepartmentStoresResponse;
import bluemobi.iuv.util.Constants;
import bluemobi.iuv.util.SharedPreferencesUtil;
import bluemobi.iuv.util.StringUtils;
import bluemobi.iuv.util.Utils;
import bluemobi.iuv.util.WebUtils;
import bluemobi.iuv.view.CustomSpinnerSingle;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by liufy on 2015/8/8.
 */
public class HotMarketPage extends BasePage {

    private HomeTreasureFragment fragment;

    private DetailFragment mDetailFragment;

    private boolean isDetailFragment = false;

    private CustomSpinnerSingle mySpinner;

    private String mDivisionCode;

    private String mDivisionType;

    private String currentpage;

    private String mID;

    private List<MarketInfoBean> marketInfoBeans = new ArrayList<MarketInfoBean>();
    public CommonAdapter<HotShopInfo> adapter;
    public List<HotShopInfo> dataList =new  ArrayList<HotShopInfo>();


    public HotMarketPage(Context context, BaseFragment fragment) {
        super(context, fragment);
        if (fragment instanceof HomeTreasureFragment)
        {
            this.fragment =(HomeTreasureFragment) fragment;
            isDetailFragment = false;
        }else if (fragment instanceof  DetailFragment){
            this.mDetailFragment =(DetailFragment) fragment;
            isDetailFragment = true;
        }
    }

   public void initData(){

    }

    @Override
    public void transData() {
        if (isDetailFragment)
        {
            mySpinner = mDetailFragment.csm_hot_market_spinner;
            mDivisionCode = mDetailFragment.mDivisionCode;
            mDivisionType = mDetailFragment.mDivisionType;
            mID = mDetailFragment.mID;
        }else
        {
            mySpinner = fragment.csm_hot_market_spinner;
            mDivisionCode = fragment.divisionCode;
            
        }

    }

    @Override
    public View initView(LayoutInflater inflater) {

        View homeView = inflater.inflate(R.layout.page_large_department_stores, null);
        return homeView;
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


    public void showListData(HotShopListMoedl list)
    {
        if (list == null) {
            return;
        }
        if (list.getInfo().size() == 0) {
            return;
        }
        if (list.getCurrentpage().equals("1"))
        {
            dataList.clear();
            dataList.addAll(list.getInfo());
        } else
        {
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
        IuwApplication.getInstance().iuwMarketInfoBeans =null;
        IuwApplication.getInstance().iuwMarketInfoBeans = (ArrayList<MarketInfoBean>) marketInfoBeans;


        if (adapter == null) {
            plv_refresh.setAdapter(
                    adapter = new CommonAdapter<HotShopInfo>(pageContext, dataList, R.layout.lv_page_market_item) {
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
                                                                        Utils.moveToFragment(TreasureDetailFragment.class, fragment, "treasureDetailFragment");
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

                                    if (isDetailFragment) {
                                        Utils.moveToFragment(MarketInfoFragment.class, mDetailFragment, "MarketInfoFragment");
                                    } else {
                                        Utils.moveToFragment(MarketInfoFragment.class, fragment, "MarketInfoFragment");
                                    }
                                    IuwApplication.getInstance().lonlat = null;
                                    StoreDetailsEvent storeDetailsEvent = new StoreDetailsEvent();
                                    storeDetailsEvent.setShopID(dataList.get(helper.getPosition()).getId());
                                    String lat = dataList.get(helper.getPosition()).getShopsLatitude();
                                    String lon = dataList.get(helper.getPosition()).getShopsLongitude();
                                    storeDetailsEvent.setShopLat(lat);
                                    storeDetailsEvent.setShopLon(lon);
                                    Lonlat lonlat = new Lonlat();
                                    storeDetailsEvent.setMarketType(Constants.HOT_MARKET);
                                    storeDetailsEvent.setEventType(Constants.LARGE_HOT_MARKET);
                                    lonlat.setLat(lat);
                                    lonlat.setLon(lon);
                                    IuwApplication.getInstance().lonlat = lonlat;


                                    EventBus.getDefault().post(storeDetailsEvent);
                                }
                            });

                            TextView tv_home_title=helper.getView(R.id.tv_home_title);
                            tv_home_title.setText(bean.getShopName() == null ||"null".equals(bean.getShopName())? "" : bean.getShopName());
                            TextView tv_home_content=helper.getView(R.id.tv_home_content);
                            tv_home_content.setText(bean.getPreciousName() == null ||"null".equals(bean.getPreciousName())? "" : bean.getPreciousName());
                            TextView content=helper.getView(R.id.tv_home_content_detail);
                            content.setText(bean.getPreciousDescription() == null||"null".equals(bean.getPreciousDescription()) ? "" : bean.getPreciousDescription());
                            TextView shoucang=helper.getView(R.id.tv_collect);
                            shoucang.setText(bean.getCollectionNum());

                            TextView zan=helper.getView(R.id.tv_praise);
                            zan.setText(StringUtils.isEmpty(bean.getPraiseNum())?"0":bean.getPraiseNum());
                            TextView juli=helper.getView(R.id.tv_location);
                            juli.setText(bean.getDistance());

                            ImageView iv_collect=helper.getView(R.id.iv_collect);
                            iv_collect.setTag(R.id.iv_collect,bean.getId());
                            iv_collect.setTag(R.id.tv_location,dataList.indexOf(bean));

                            TextView tv_collect = helper.getView(R.id.tv_collect);
                            TextView tv_praise = helper.getView(R.id.tv_praise);
                            if(StringUtils.isEmpty(bean.getCollectionId())||"null".equals(bean.getCollectionId())){
                                iv_collect.setImageResource(R.drawable.collect_n);
                                tv_collect.setTextColor(pageContext.getResources().getColor(R.color.common_gray));

                            }else {
                                iv_collect.setImageResource(R.drawable.collect_p);
                                tv_collect.setTextColor(pageContext.getResources().getColor(R.color.common_blue));
                            }
                            iv_collect.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    collect((String) v.getTag(R.id.iv_collect), (Integer) v.getTag(R.id.tv_location), refreshCollect);
                                }
                            });
                            ImageView iv_praise=helper.getView(R.id.iv_praise);
                            if(StringUtils.isEmpty(bean.getPraiseId())||"null".equals(bean.getPraiseId())){
                                iv_praise.setImageResource(R.drawable.praise_n);
                                tv_praise.setTextColor(pageContext.getResources().getColor(R.color.common_gray));
                            }else {
                                iv_praise.setImageResource(R.drawable.praise_p);
                                tv_praise.setTextColor(pageContext.getResources().getColor(R.color.common_blue));
                            }
                            iv_praise.setTag(R.id.iv_praise,bean.getId());
                            iv_praise.setTag(R.id.tv_home_title, dataList.indexOf(bean));
                            iv_praise.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    givePraise((String) v.getTag(R.id.iv_praise), (Integer) v.getTag(R.id.tv_home_title),refresh);
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
            Toast.makeText(pageContext, "收藏成功", Toast.LENGTH_SHORT).show();

        }
    };

    RefreshPraiseListener refresh = new RefreshPraiseListener() {
        @Override
        public void refreshView(int p) {
            dataList.get(p).setPraiseId("1");
            dataList.get(p).setPraiseNum(String.valueOf(Integer.valueOf(dataList.get(p).getPraiseNum())+1));
            adapter.notifyDataSetChanged();

        }
    };

    @Override
    protected boolean getPage(int type)
    {
        if (!super.getPage(type))
        {
            return false;
        }
        connectToServer();
        return true;

    }

    private void connectToServer()
    {
        Utils.showProgressDialog(pageContext);
        WebUtils.doPost(commonRequest(getCurPage()));
    }


    private IuwHttpJsonRequest commonRequest(int startPage) {

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
                }, this);
        UserInfo userInfo = IuwApplication.getInstance().getMyUserInfo();
        if (userInfo != null) {
            request.setUserId(IuwApplication.getInstance().getMyUserInfo().getUserId());
        }
        String lat = SharedPreferencesUtil.getFromFileByDefault(pageContext, "geoLat", "41.927618");
        String lon = SharedPreferencesUtil.getFromFileByDefault(pageContext, "geoLng", "123.40706");
        request.setLatitude(lat);
        request.setLongitude(lon);
        if ("1".equals(mDivisionType)) {

            request.setShopCityCode(mID == null ? "" : mID);
        } else {
            request.setShopDistrictCode(mID == null ? "" : mID);
        }

        if (Constants.isSelectHotShop) {
            request.setShopsTypeId(mySpinner.getValue());
        } else {
            request.setShopsTypeId("2");
        }
        request.setCurrentnum(Constants.CURRENTNUMBASE + "");
        request.setCurrentpage(startPage + "");

        if (isDetailFragment) {
            request.setSortType(mDetailFragment.sortsType == null ? "" : mDetailFragment.sortsType);
        } else {
            request.setSortType(fragment.sortsType == null ? "" : fragment.sortsType);
        }
        return request;
    }

}
