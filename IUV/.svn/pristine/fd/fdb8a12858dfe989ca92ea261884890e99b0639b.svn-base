package bluemobi.iuv.activity;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

import bluemobi.iuv.R;
import bluemobi.iuv.annotation.ContentView;
import bluemobi.iuv.app.IuwApplication;
import bluemobi.iuv.app.TitleBarManager;
import bluemobi.iuv.base.BaseActivity;
import bluemobi.iuv.base.utils.Logger;
import bluemobi.iuv.eventbus.BaseEvent;
import bluemobi.iuv.eventbus.CountyInfoEvent;
import bluemobi.iuv.eventbus.CountySpinnerBean;
import bluemobi.iuv.fragment.HomeMineFragment;
import bluemobi.iuv.fragment.HomePageFragment;
import bluemobi.iuv.fragment.HomeSearchFragment;
import bluemobi.iuv.fragment.HomeTreasureFragment;
import bluemobi.iuv.network.model.HotShopTypeModel;
import bluemobi.iuv.network.request.FindCitiesByCondRequest;
import bluemobi.iuv.network.request.HotShopTypeRequest;
import bluemobi.iuv.network.request.LargerDeptTypeListRequest;
import bluemobi.iuv.network.request.SearchCountyRequest;
import bluemobi.iuv.network.request.TopSortRequest;
import bluemobi.iuv.network.response.HotShopTypeResponse;
import bluemobi.iuv.network.response.SearchCountyResponse;
import bluemobi.iuv.network.response.TopSortResponse;
import bluemobi.iuv.util.Constants;
import bluemobi.iuv.util.SharedPreferencesUtil;
import bluemobi.iuv.util.StringUtils;
import bluemobi.iuv.util.Utils;
import bluemobi.iuv.util.WebUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements View.OnClickListener, AMapLocationListener {

    public String titleCity = "";
    private HomePageFragment homePageFragment;
    private HomeTreasureFragment homeTreasureFragment;
    private HomeSearchFragment homeSearchFragment;
    private HomeMineFragment homeMineFragment;
    @Bind(R.id.rl_home)
    protected View homeLayout;
    @Bind(R.id.rl_treasure)
    protected View treasureLayout;
    @Bind(R.id.rl_search)
    protected View searchLayout;
    @Bind(R.id.rl_mine)
    protected View mineLayout;
    @Bind(R.id.iv_home)
    protected ImageView iv_home;
    @Bind(R.id.iv_treasure)
    protected ImageView iv_treasure;
    @Bind(R.id.iv_search)
    protected ImageView iv_search;
    @Bind(R.id.iv_mine)
    protected ImageView iv_mine;
    @Bind(R.id.tv_home)
    protected TextView tv_home;
    @Bind(R.id.tv_treasure)
    protected TextView tv_treasure;
    @Bind(R.id.tv_search)
    protected TextView tv_search;
    @Bind(R.id.tv_mine)
    protected TextView tv_mine;
    @Bind(R.id.fl_content)
    protected FrameLayout fl_content;
    private FragmentManager fragmentManager;
    private TitleBarManager titleBarManager;
    public boolean isHiddChange = true;
    private boolean isLocationSucceed = false;
    private ArrayList<SearchCountyResponse.SearchCountyData> mData = null;
    private ArrayList<SearchCountyResponse.SearchCountyData> searchData;
    private LocationManagerProxy mLocationManagerProxy;
    /**
     *  是否为首页四个TAB切换，if Yes 则点击BACK可以退出应用，否则不可以退出应用
     */
    public boolean isBaseFragment = false;

    /**
     * 初始化定位
     */
    private void init() {
        mLocationManagerProxy = LocationManagerProxy.getInstance(this);
        mLocationManagerProxy.requestLocationData(
                LocationProviderProxy.AMapNetwork, 2000, 15, this);
        mLocationManagerProxy.setGpsEnable(true);
    }


    @Override
    protected void initBase() {
        showLoadingPage(false);
    }

    @Override
    protected void successViewCompleted(View successView) {
        init();
        ButterKnife.bind(this, successView);
        EventBus.getDefault().register(this);
        getSearchCounty();
        getHotShopTypes();
        getTopSort();
        homeLayout.setOnClickListener(this);
        treasureLayout.setOnClickListener(this);
        searchLayout.setOnClickListener(this);
        mineLayout.setOnClickListener(this);
        fragmentManager = getFragmentManager();
        setTabSelection(0);

        Constants.isSelectHotShop = false;
        Constants.isSelectLargeDept = false;
    }

    /**
     * 获取推荐国家
     */
    private void getSearchCounty() {

        Utils.showProgressDialog(mContext);
        SearchCountyRequest request = new SearchCountyRequest
                (
                        new Response.Listener<SearchCountyResponse>() {
                            @Override
                            public void onResponse(SearchCountyResponse response) {
                                Utils.closeDialog();
                                mData = response.data;
                                if (mData != null && mData.size() > 0) {
                                    IuwApplication.getInstance().setmCountyData(mData);
                                }

                            }
                        }, this);
        WebUtils.doPost(request);

    }


    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index
     */
    private void setTabSelection(int index) {
        finishAllFragment();
        clearSelection();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        titleBarManager = new TitleBarManager();
        titleBarManager.init(this, getSupportActionBar());
        isHiddChange = false;
        switch (index) {
            case 0:
                titleBarManager.showHomeTitleBar(R.drawable.home_page_icon, R.drawable.share);
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.common_blue)));
                iv_home.setImageResource(R.drawable.home_page_p);
                tv_home.setTextColor(getResources().getColor(R.color.common_blue));
                if (homePageFragment == null) {
                    homePageFragment = new HomePageFragment();
                    transaction.add(R.id.fl_content, homePageFragment);
                } else {
                    transaction.show(homePageFragment);
                }

                break;
            case 1:
                if (isLocationSucceed)
                    stopLocation();
                titleBarManager.showSearchTitleBar(getResources().getString(R.string.treasure_hint), R.drawable.search, R.drawable.map, true);
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.common_blue)));
                iv_treasure.setImageResource(R.drawable.home_treasure_p);
                tv_treasure.setTextColor(getResources().getColor(R.color.common_blue));
                if (homeTreasureFragment == null) {
                    homeTreasureFragment = new HomeTreasureFragment();
                    transaction.add(R.id.fl_content, homeTreasureFragment);
                } else {
                    transaction.show(homeTreasureFragment);
                }
                break;
            case 2:
                if (isLocationSucceed)
                    stopLocation();
                titleBarManager.showSearchTitleBar(getResources().getString(R.string.search_hint), R.drawable.map, R.drawable.search, false);
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.common_blue)));
                iv_search.setImageResource(R.drawable.home_search_p);
                tv_search.setTextColor(getResources().getColor(R.color.common_blue));
                if (homeSearchFragment == null) {
                    homeSearchFragment = new HomeSearchFragment();
                    transaction.add(R.id.fl_content, homeSearchFragment);
                } else {
                    transaction.show(homeSearchFragment);
                }
                break;
            case 3:
                if (isLocationSucceed)
                    stopLocation();
                if (shouldGoToLogin()) {
                    return;
                }
                titleBarManager.showLRSrcTitleBar(getResources().getString(R.string.s_mine), R.drawable.common_return, R.drawable.share, false);
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
                iv_mine.setImageResource(R.drawable.home_mine_p);
                tv_mine.setTextColor(getResources().getColor(R.color.common_blue));

                if (homeMineFragment == null) {
                    homeMineFragment = new HomeMineFragment();
                    transaction.add(R.id.fl_content, homeMineFragment);
                } else {
                    transaction.show(homeMineFragment);
                }
                break;
        }
        transaction.commit();
    }


    public void showTitleTextBar() {
        titleBarManager.showTitleTextBar(R.string.write_evaluation, R.drawable.common_return, R.string.submit);
    }

    /**
     * 个人中心的Titlebar
     *
     * @param textID
     * @param barBgColor
     * @param isShow
     */
    public void setMineBarSytle(String textID, int barBgColor, boolean isShow) {
        titleBarManager.showLRSrcTitleBar(textID, R.drawable.common_return, R.drawable.share, isShow);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(barBgColor)));
    }

    public void setHomeBarSytle(String textID, boolean isShow) {
        titleBarManager.showLRSrcTitleBar(textID, R.drawable.common_return, R.drawable.share, isShow);

    }

    //点评
    public void setCommentBarSytle(String textID, boolean isShow, int type) {

        titleBarManager.showLRSrcTitleBar(textID, R.drawable.common_return, R.drawable.comment_icon, isShow, type);


    }

    //点评
    public void setCommentBarSytle(String textID, boolean isShow) {
        titleBarManager.showLRSrcTitleBar(textID, R.drawable.common_return, R.drawable.share, isShow);

    }


    public void setDetailBarSytle(String text) {
        titleBarManager.showDetailTitleBar(text, R.drawable.common_return, R.drawable.map, R.drawable.share);

    }

    /**
     * 赞信息
     */
    //TOOD
    public void showPriseTextBar(String praiseNum) {
        titleBarManager.showTitleTextBar(R.string.good_detail,
                R.drawable.common_return, praiseNum, R.drawable.praise_white,
                R.drawable.collect_white);

    }


    public void setDetailStoreBarSytle(String text) {
        titleBarManager.showDetailTitleBar(text, R.drawable.common_return, R.drawable.navi, R.drawable.share);
    }

    public void setDetailStoreBarNaviSytle(String text, int mapStyle) {
        titleBarManager.showDetailTitleBar(text, R.drawable.common_return, R.drawable.navi, R.drawable.share, mapStyle);
    }

    /**
     * 搜索
     */
    public void setSearchBarSytle(String text) {
        //TODO
        //titleBarManager.showSearchTitleBar(R.string.search_hint, R.drawable.search, R.drawable.map, true);
        titleBarManager.showSearchTitleBar(text, R.drawable.map, R.drawable.search, false);
    }

    /**
     * 和P12宝藏都使用 R.string.search_hint
     */
    public void setSearchTreasureBarSytle(String text) {
        //TODO
        titleBarManager.showSearchTitleBar(text, R.drawable.search, R.drawable.map, true);
    }

    public void setHomeBar() {
        titleBarManager.showHomeTitleBar(R.drawable.home_page_icon, R.drawable.share);
    }

    public void setTitle(String str) {
        titleBarManager.getTitleView().setText(str);
    }

    /**
     * 清除掉所有的选中状态。
     */
    public void clearSelection() {
        iv_home.setImageResource(R.drawable.home_page_n);
        tv_home.setTextColor(getResources().getColor(R.color.common_bottom_title_color));
        iv_treasure.setImageResource(R.drawable.home_treasure_n);
        tv_treasure.setTextColor(getResources().getColor(R.color.common_bottom_title_color));
        iv_search.setImageResource(R.drawable.home_search_n);
        tv_search.setTextColor(getResources().getColor(R.color.common_bottom_title_color));
        iv_mine.setImageResource(R.drawable.home_mine_n);
        tv_mine.setTextColor(getResources().getColor(R.color.common_bottom_title_color));
    }


    public void selectionTab(int type) {
        clearSelection();
        switch (type) {
            case 1:
                iv_home.setImageResource(R.drawable.home_page_p);
                tv_home.setTextColor(getResources().getColor(R.color.common_blue));
                break;

            case 2:
                iv_treasure.setImageResource(R.drawable.home_treasure_p);
                tv_treasure.setTextColor(getResources().getColor(R.color.common_blue));
                break;
            case 3:
                iv_search.setImageResource(R.drawable.home_search_p);
                tv_search.setTextColor(getResources().getColor(R.color.common_blue));
                break;
            case 4:
                iv_mine.setImageResource(R.drawable.home_mine_p);
                tv_mine.setTextColor(getResources().getColor(R.color.common_blue));
                break;


        }
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (homePageFragment != null) {
            transaction.hide(homePageFragment);
        }
        if (homeTreasureFragment != null) {
            transaction.hide(homeTreasureFragment);
        }
        if (homeSearchFragment != null) {
            transaction.hide(homeSearchFragment);
        }
        if (homeMineFragment != null) {
            transaction.hide(homeMineFragment);
        }
    }

    @Override
    public void onBackPressed() {

        if (fragmentManager.getBackStackEntryCount() == 0) {
            finish();
        } else {
            isHiddChange = true;
            fragmentManager.popBackStack();

        }

    }


    public void finishFragment() {
        fragmentManager.popBackStackImmediate();
    }

    public void finishAllFragment() {
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
            fragmentManager.popBackStack();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_home:
                setTabSelection(0);
                break;
            case R.id.rl_treasure:
                setTabSelection(1);
                break;
            case R.id.rl_search:
                setTabSelection(2);
                break;
            case R.id.rl_mine:
                setTabSelection(3);
                break;

        }
    }

    @Override
    public void clickBarRightSearch() {
        titleBarManager.getSearchIconView().setImageResource(R.drawable.share);

    }

    @Override
    public void clickBarleft() {

        if (fragmentManager.getBackStackEntryCount() == 0) {

            finish();
        } else {
            isHiddChange = true;
            fragmentManager.popBackStack();

        }
    }


    /**
     * 搜索按钮
     */
    @Override
    public void clickRightMapSearch() {
        //点击搜索fragment 右边的搜索按钮
        if (StringUtils.isEmpty(countyId)) {
            Utils.makeToastAndShow(mContext, "请您先选择国家");
            return;
        }
        EditText etView = titleBarManager.getEtView();
        String etPlace = etView.getText().toString().trim();
        if (StringUtils.isEmpty(etPlace)) {
            Utils.makeToastAndShow(mContext, "请输入查询的城市");
            return;
        }
        getSearchCityByCode(etPlace);

    }

    /**
     * P8 根据名称查询城市列表
     */
    private void getSearchCityByCode(String str) {

        if (searchData != null && searchData.size() > 0) {
            searchData.clear();
        }
        Utils.showProgressDialog(mContext);
        FindCitiesByCondRequest request = new FindCitiesByCondRequest
                (
                        new Response.Listener<SearchCountyResponse>() {
                            @Override
                            public void onResponse(SearchCountyResponse response) {
                                Utils.closeDialog();
                                if (0 == response.getStatus()) {
                                    searchData = response.data;
                                    if (searchData != null && searchData.size() > 0) {
                                        CountyInfoEvent countyInfoEvent = new CountyInfoEvent();
                                        countyInfoEvent.setEventType(Constants.COUNTYINFOEVENT_TYPE);
                                        countyInfoEvent.setData(searchData);
                                        EventBus.getDefault().post(countyInfoEvent); //跳转到HomeSearchFragment
                                    }
                                }

                                if (2 == response.getStatus()) {
                                    CountyInfoEvent countyInfoEvent = new CountyInfoEvent();
                                    countyInfoEvent.setEventType(Constants.COUNTYINFOEVENT_TYPE);
                                    countyInfoEvent.setData(null);
                                    EventBus.getDefault().post(countyInfoEvent); //跳转到HomeSearchFragment
                                }
                            }
                        }, this);
        request.setDIVISION_NAME(str);
        request.setHandleCustomErr(false);
        request.setId(countyId);
        WebUtils.doPost(request);

    }

    private String countyId;

    /**
     * 获得下拉列表城市ID 从Spinner 列表返回数据
     */
    public void onEventMainThread(BaseEvent baseEvent) {
        if (baseEvent instanceof CountySpinnerBean) {
            CountySpinnerBean countyInfoEvent = (CountySpinnerBean) baseEvent;
            if (countyInfoEvent.getEventType() == Constants.SPINNER_TYPE_LV) {
                this.countyId = countyInfoEvent.getId();
                this.titleCity = countyInfoEvent.getName();
            }

        }
    }

    @Override
    public void clickDetailRightMap() {


        Utils.moveTo(mContext, MapInfoActicity.class);
    }


    @Override
    public void clickCommentRight() {

        Intent intent = new Intent();
        intent.setAction("com.lfy.yubroadcast");
        mContext.sendBroadcast(intent);
        Logger.e("clickCommentRight", "clickCommentRight");


    }

    private AlertDialog dialog;

    @Override
    public void clickImageRight() {
        if (dialog == null) {
            dialog = new AlertDialog.Builder(this)
                    .create();
            View view = LayoutInflater.from(this).inflate(R.layout.dialog_share, null);
            Window window = dialog.getWindow();
            window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置
            window.setWindowAnimations(R.style.dialog_bottom_style);  //添加动画
            dialog.show();
            dialog.setContentView(view);
        } else {
            dialog.show();
        }
    }

    @Override
    public void clickSearchBar() {   //点击搜索按钮跳转到城市
        if (StringUtils.isEmpty(countyId)) {
            Utils.makeToastAndShow(mContext, "请您先选择国家");
            return;
        }

        if (homeTreasureFragment != null) {
            homeTreasureFragment.searchCallBack(countyId, titleCity);
        }
    }

    /**
     * 点击评价中提交按钮
     */
    @Override
    public void clickBarRight() {
        EventBus.getDefault().post("clickBarRight");
    }

    /**
     * 商场详情中的导航按钮
     */
    @Override
    public void clickRightNaviSearch() {
        Utils.moveTo(mContext, MapInfoRouteActicity.class);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        stopLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null && aMapLocation.getAMapException().getErrorCode() == 0) {
            //获取位置信息
            Double geoLat = aMapLocation.getLatitude();
            Double geoLng = aMapLocation.getLongitude();
            isLocationSucceed = true;
            SharedPreferencesUtil.saveToFile(mContext, "geoLat", String.valueOf(geoLat));
            SharedPreferencesUtil.saveToFile(mContext, "geoLng", String.valueOf(geoLng));
            Logger.e("Location--->", geoLat, geoLng);
        }
    }

    private void stopLocation() {
        if (mLocationManagerProxy != null) {
            mLocationManagerProxy.removeUpdates(this);
            mLocationManagerProxy.destory();
        }
        mLocationManagerProxy = null;
    }


    @Override
    protected void onPause() {
        super.onPause();
        stopLocation();
    }


    /**
     * drop
     *
     * @param location
     */
    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private long exitTime;

    public boolean dispatchKeyEvent(KeyEvent event)
    {
        if (isBaseFragment)
        {
            return super.dispatchKeyEvent(event);
        }

        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 3000) {
                Toast.makeText(getApplicationContext(), "再按一次将退出应用",
                        Toast.LENGTH_LONG).show();
                exitTime = System.currentTimeMillis();
            } else {
                IuwApplication.getInstance().setMyUserInfo(null);
                this.finish();
            }
        }
        return true;
    }

    /**
     *初始化 获取热门商场类型
     */
    private void getHotShopTypes() {
        HotShopTypeRequest request = new HotShopTypeRequest(new Response.Listener<HotShopTypeResponse>() {
            @Override
            public void onResponse(HotShopTypeResponse response) {
                if (response != null && response.getStatus() == 0) {
                    if (response.getData() != null) {
                        List<HotShopTypeModel> mHotShopTypeModel = response.getData();
                        IuwApplication.getInstance().dataList.clear();
                        IuwApplication.getInstance().dataCodeList.clear();
                        for (int i = 0; i < mHotShopTypeModel.size(); i++) {
                            IuwApplication.getInstance().dataList.add(mHotShopTypeModel.get(i).getShopsTypeName());
                            IuwApplication.getInstance().dataCodeList.add(mHotShopTypeModel.get(i).getShopsTypeCode());
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        WebUtils.doPost(request);
    }


    /**
     * 获取宝藏页面置顶TOP
     */
    private void getTopSort() {
        TopSortRequest request = new TopSortRequest(new Response.Listener<TopSortResponse>() {
            @Override
            public void onResponse(TopSortResponse response) {
                if (response != null && response.getStatus() == 0) {
                    ArrayList<TopSortResponse.TopSortData> topSortDatas = IuwApplication.getInstance().getmTopData();
                    if (topSortDatas != null)
                        topSortDatas.clear();
                    ArrayList<TopSortResponse.TopSortData> data = response.data;
                    if (data != null && data.size() > 0) {
                        IuwApplication.getInstance().setmTopData(data);

                    }
                }
            }
        }, this);
        WebUtils.doPost(request);
    }
}
