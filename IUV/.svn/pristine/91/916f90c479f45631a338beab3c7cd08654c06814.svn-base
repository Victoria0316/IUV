package bluemobi.iuv.fragment;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bluemobi.iuv.R;
import bluemobi.iuv.activity.MainActivity;
import bluemobi.iuv.annotation.ContentView;
import bluemobi.iuv.app.IuwApplication;
import bluemobi.iuv.base.utils.Logger;
import bluemobi.iuv.callback.TitleClickCallBack;
import bluemobi.iuv.eventbus.CountySpinnerBean;
import bluemobi.iuv.eventbus.LargeDeptEvent;
import bluemobi.iuv.eventbus.SpinnerEvent;
import bluemobi.iuv.eventbus.TopSortEvent;
import bluemobi.iuv.fragment.page.HotMarketPage;
import bluemobi.iuv.fragment.page.LargeDepartmentStoresPage;
import bluemobi.iuv.network.model.HotShopTypeModel;
import bluemobi.iuv.network.response.TopSortResponse;
import bluemobi.iuv.util.Constants;
import bluemobi.iuv.util.Utils;
import bluemobi.iuv.view.CustomSpinnerBase;
import bluemobi.iuv.view.CustomSpinnerSingle;
import bluemobi.iuv.view.TextViewWithPopWindow;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Created by liufy on 2015/8/4.
 * P12_宝藏搜索
 */
@ContentView(R.layout.fragment_treasure)
public class HomeTreasureFragment extends BaseFragment implements CustomSpinnerBase.OnTabClickListener, TitleClickCallBack, CustomSpinnerBase.OnSnipperClickListener, TextViewWithPopWindow.OnSpinnerTextClickListener{

    private List<String> dataTempList = new ArrayList<String>();

    public String sortsType;

    private Map<String, String> sortTypeMap = null;

    @Bind(R.id.rl_hot_market)
    protected RelativeLayout rl_hot_market;


    @Bind(R.id.tv_large_stores)
    protected TextView tv_large_stores;

    @Bind(R.id.tv_intelligent_sort)
    protected TextViewWithPopWindow tv_intelligent_sort;

    @Bind(R.id.csm_hot_market_spinner)
    public CustomSpinnerSingle csm_hot_market_spinner;

    @Bind(R.id.vp_tab)
    protected ViewPager mViewPager;
    private PagerAdapter mAdapter;

    private HotMarketPage hotMarketPage;
    private LargeDepartmentStoresPage largeDepartmentStoresPage;

    private List<View> mPages = new ArrayList<View>();

    private boolean isSelect = false;

    private ImageView childAtOne;

    private List<HotShopTypeModel> mHotShopTypeModel;

    private SpinnerEvent mSpinnerEvent;
    /**
     * 标题签名
     */
    private String titleName;

    public String divisionCode;
    private LargeDeptEvent mLargeDeptEvent;
    /**
     * divisionType:区划类型：0国家1省份2地市3区县
     */
    public String mDivisionType;

    private int currentItem;

    protected
    @Bind(R.id.hs_scroll)
    HorizontalScrollView hs_scroll;

    protected
    @Bind(R.id.ll_scroll)
    LinearLayout ll_scroll;

    private  ArrayList<TopSortResponse.TopSortData> mTopSortData = null;

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        ((MainActivity)mContext).isBaseFragment = true;
        if (((MainActivity) mContext).isHiddChange) {
            ((MainActivity) mContext).setSearchTreasureBarSytle(titleName == null ? getString(R.string.treasure_hint) : titleName);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity) mContext).setSearchTreasureBarSytle(titleName == null ? getString(R.string.treasure_hint) : titleName);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        isShowLoadPage = false;
        dataTempList.add("收藏最多");
        dataTempList.add("点赞最多");
        dataTempList.add("评论最多");
        dataTempList.add("离我最近");

        sortTypeMap = new HashMap<String, String>();
        sortTypeMap.put("收藏最多", "collectionNum");
        sortTypeMap.put("点赞最多", "praiseNum");
        sortTypeMap.put("评论最多", "commentNum");
        sortTypeMap.put("离我最近", "distance");
    }

    public HomeTreasureFragment() {
        EventBus.getDefault().register(this);
    }

    public void onEvent(SpinnerEvent sp) {
        this.mSpinnerEvent = sp;
    }

    public void onEvent(LargeDeptEvent largeDeptEvent) {
        titleName = largeDeptEvent.getDivisionName();
        this.mLargeDeptEvent = largeDeptEvent;
        divisionCode = mLargeDeptEvent.getDivisionCode();
        mDivisionType = mLargeDeptEvent.getDivisionType();
    }


    @Override
    protected void successViewCompleted(View successView) {
        ButterKnife.bind(this, successView);
        if (IuwApplication.getInstance().dataList == null) {
            getHotShopTypes();
        }
        csm_hot_market_spinner.setDatas(IuwApplication.getInstance().dataList);
        csm_hot_market_spinner.setValues(IuwApplication.getInstance().dataCodeList);
        hotMarketPage = new HotMarketPage(mContext, HomeTreasureFragment.this);
        largeDepartmentStoresPage = new LargeDepartmentStoresPage(mContext, HomeTreasureFragment.this);
        mPages.add(largeDepartmentStoresPage.getRootView());
        mPages.add(hotMarketPage.getRootView());
        largeDepartmentStoresPage.loadRequset();
        largeDepartmentStoresPage.transData();
        csm_hot_market_spinner.setIsBeside(false);
        csm_hot_market_spinner.setShowDownView(rl_hot_market);
        tv_intelligent_sort.setDatas(dataTempList);
        tv_intelligent_sort.setShowDownView(tv_intelligent_sort);
        csm_hot_market_spinner.setOnTabClickListener(this);
        csm_hot_market_spinner.setSnipperClickListener(this);
        tv_intelligent_sort.setOnSpinnerTextClickListener(this);
        childAtOne = (ImageView) csm_hot_market_spinner.getChildAt(1);
        childAtOne.setClickable(false);
        mViewPager.setCurrentItem(0);
        csm_hot_market_spinner.setCusomTextColor(getResources().getColor(R.color.black_light));
        tv_large_stores.setBackgroundResource(R.drawable.tab_selected);
        tv_large_stores.setTextColor(getResources().getColor(R.color.white));

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                currentItem = mViewPager.getCurrentItem();
                clearState();
                switch (currentItem) {
                    case 0:
                        tv_large_stores.setBackgroundResource(R.drawable.tab_selected);
                        tv_large_stores.setTextColor(getResources().getColor(R.color.white));
                        largeDepartmentStoresPage.transData();
                        largeDepartmentStoresPage.loadRequset();
                        childAtOne.setClickable(false);

                        break;
                    case 1:
                        csm_hot_market_spinner.setBackgroundResource(R.drawable.tab_selected);
                        csm_hot_market_spinner.setCusomTextColor(getResources().getColor(R.color.white));
                        hotMarketPage.transData();
                        hotMarketPage.loadRequset();
                        isSelect = true;
                        childAtOne.setClickable(true);

                        break;

                }

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
        MyTabPageAdapter myTabPageAdapter = new MyTabPageAdapter();
        mViewPager.setAdapter(myTabPageAdapter);

        mTopSortData = IuwApplication.getInstance().getmTopData();
        TextView textView = null;
        for ( int i = 0; i < mTopSortData.size(); i++) {
            textView = new TextView(mContext);
            final String shopName =  mTopSortData.get(i).shopsTypeName;
            final String shopCode =  mTopSortData.get(i).shopsTypeCode;
            final String mainID = mTopSortData.get(i).id;
            textView.setText(mTopSortData.get(i).shopsTypeName);
            textView.setTextColor(getResources().getColor(R.color.common_white));
            textView.setBackgroundResource(R.drawable.scroll_tab_bg);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(10,0,10,0);
            textView.setGravity(Gravity.CENTER);
            ll_scroll.addView(textView, i, lp);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    TopSortEvent topSortEvent = new TopSortEvent();
                    topSortEvent.setId(mainID);
                    topSortEvent.setShopsTypeId(shopCode);
                    topSortEvent.setShopsTypeName(shopName);
                    topSortEvent.setDivisionCode(divisionCode);
                    topSortEvent.setDivisionType(mDivisionType);
                    topSortEvent.setEventType(Constants.SPEC_STORE_CATE);
                    topSortEvent.setSortType(sortsType);


                    if ("特产店".equals(shopName))
                    {
                        Utils.moveToFragment(SpecialtyStoreCategoryFragment.class, HomeTreasureFragment.this, "specialtystorefragment");
                    }
                    else if ("购物街".equals(shopName))
                    {
                        Utils.moveToFragment(ShoppingStreetFragment.class, HomeTreasureFragment.this, "shoppingstreetfragment");
                    }
                    else if ("超市".equals(shopName))
                    {
                        Utils.moveToFragment(SupermarketFragment.class, HomeTreasureFragment.this, "supermarketfragment");
                    }
                    ((MainActivity)mContext).isBaseFragment = false;
                    EventBus.getDefault().post(topSortEvent);
                }
            });

        }

    }


    @OnClick(R.id.tv_large_stores)
    public void clickStores() {
        mViewPager.setCurrentItem(0);
    }


    @Override
    public void onTabClick(View view) {
        switch (view.getId()) {

            case R.id.csm_hot_market_spinner:
                mViewPager.setCurrentItem(1);
                break;
        }
    }

    public void clearState() {
        tv_large_stores.setBackgroundResource(android.R.color.transparent);
        tv_large_stores.setTextColor(getResources().getColor(R.color.black_light));
        csm_hot_market_spinner.setBackgroundResource(android.R.color.transparent);
        csm_hot_market_spinner.setCusomTextColor(getResources().getColor(R.color.black_light));

    }


    @Override
    public void searchCallBack(String countyID, String countyName) {

        CountySpinnerBean mCountySpinnerBean = new CountySpinnerBean();
        mCountySpinnerBean.setId(countyID);
        mCountySpinnerBean.setName(countyName);
        mCountySpinnerBean.setIsTreasure(true);
        Utils.moveToFragment(AreaFragment.class, HomeTreasureFragment.this, "AreaFragment");
        EventBus.getDefault().post(mCountySpinnerBean);
    }

    @Override
    public void commentCallBack() {

    }


    @Override
    public void onSpinnerTextClick(int postion) {
        String type = dataTempList.get(postion);
        sortsType = sortTypeMap.get(type);
        if (currentItem == 0) {
            largeDepartmentStoresPage.loadRequset();
        } else if (currentItem == 1) {
            hotMarketPage.curPage = 0;
            hotMarketPage.loadRequset();
        }
    }


    @Override
    public void OnSnipperClick(int position, View v) {
        if (v.getId() == R.id.csm_hot_market_spinner) {
            Constants.isSelectHotShop = true;
            hotMarketPage.curPage = 0;
            hotMarketPage.loadRequset();
        }

    }




    private class MyTabPageAdapter extends PagerAdapter {


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mPages.get(position));
            return mPages.get(position);

        }

        @Override
        public int getCount() {
            return mPages.size();
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            if (view == object) {
                return true;
            } else {
                return false;
            }
        }
    }

}
