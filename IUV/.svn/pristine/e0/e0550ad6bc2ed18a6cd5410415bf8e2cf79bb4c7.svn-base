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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bluemobi.iuv.R;
import bluemobi.iuv.activity.MainActivity;
import bluemobi.iuv.annotation.ContentView;
import bluemobi.iuv.app.IuwApplication;
import bluemobi.iuv.base.utils.Logger;
import bluemobi.iuv.eventbus.BaseEvent;
import bluemobi.iuv.eventbus.CityEvent;
import bluemobi.iuv.eventbus.LargeDeptEvent;
import bluemobi.iuv.eventbus.TopSortEvent;
import bluemobi.iuv.fragment.page.HotMarketPage;
import bluemobi.iuv.fragment.page.LargeDepartmentStoresPage;
import bluemobi.iuv.network.response.TopSortResponse;
import bluemobi.iuv.util.Constants;
import bluemobi.iuv.util.Utils;
import bluemobi.iuv.view.CustomSpinnerBase;
import bluemobi.iuv.view.CustomSpinnerSingle;
import bluemobi.iuv.view.LoadingPage;
import bluemobi.iuv.view.TextViewWithPopWindow;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Created by liufy on 2015/8/4.
 * P8_2商场列表-大型百货
 */
@ContentView(R.layout.fragment_treasure)
public class DetailFragment extends BaseFragment implements CustomSpinnerBase.OnTabClickListener, TextViewWithPopWindow.OnSpinnerTextClickListener,CustomSpinnerBase.OnSnipperClickListener{

    private List<String> dataList;
    private List<String> dataTempList;
    public String sortsType;

    @Bind(R.id.rl_hot_market)
    protected RelativeLayout rl_hot_market;

    @Bind(R.id.rl_large_stores)
    protected RelativeLayout rl_large_stores;

    /**
     * 智能排序
     */
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

    private Map<String, String> sortTypeMap = null;

    private  ImageView childAtOne;

    /**
     * 标题签名
     */
    private String titleName;

    public String mDivisionCode;
    /**
     * divisionType:区划类型：0国家1省份2地市3区县
     */
    public String mDivisionType;
    /**
     * 根据这个ID 查询对应城市区县下的 大型百货热门商场等数据
     */
    public  String mID;

    private LargeDeptEvent mLargeDeptEvent;

    private int currentItem;

    @Bind(R.id.tv_large_stores)
    protected TextView tv_large_stores;

    protected
    @Bind(R.id.hs_scroll)
    HorizontalScrollView hs_scroll;
    protected
    @Bind(R.id.ll_scroll)
    LinearLayout ll_scroll;

    private  ArrayList<TopSortResponse.TopSortData> mTopSortData = null;

    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity) mContext).setDetailBarSytle(titleName);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (((MainActivity) mContext).isHiddChange) {
            ((MainActivity) mContext).setDetailBarSytle(titleName);
        }


    }

    public DetailFragment() {
        EventBus.getDefault().register(this);
    }

    public void onEvent(LargeDeptEvent largeDeptEvent) {
        titleName = largeDeptEvent.getDivisionName();
        this.mLargeDeptEvent = largeDeptEvent;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        isShowLoadPage = false;

        dataTempList = new ArrayList<String>();
        dataTempList.add("收藏最多");
        dataTempList.add("点赞最多");
        dataTempList.add("评论最多");
        dataTempList.add("离我最近");
        sortTypeMap = new HashMap<String, String>();
        sortTypeMap.put("收藏最多", "collectionNum");
        sortTypeMap.put("点赞最多", "praiseNum");
        sortTypeMap.put("评论最多", "commentNum");
        sortTypeMap.put("离我最近", "distance");

        hotMarketPage = new HotMarketPage(mContext, DetailFragment.this);
        largeDepartmentStoresPage = new LargeDepartmentStoresPage(mContext, DetailFragment.this);
        //TODO
        mDivisionCode = mLargeDeptEvent.getDivisionCode();
        mDivisionType = mLargeDeptEvent.getDivisionType();
        mID = mLargeDeptEvent.getId();
        largeDepartmentStoresPage.transData();
        largeDepartmentStoresPage.loadRequset();

        mPages.add(largeDepartmentStoresPage.getRootView());
        mPages.add(hotMarketPage.getRootView());


    }


    @Override
    protected void successViewCompleted(View successView) {
        ButterKnife.bind(this, successView);

        if (IuwApplication.getInstance().dataList==null)
        {
            getHotShopTypes();
        }

        csm_hot_market_spinner.setDatas(IuwApplication.getInstance().dataList);
        csm_hot_market_spinner.setValues(IuwApplication.getInstance().dataCodeList);


        csm_hot_market_spinner.setSnipperClickListener(this);
        csm_hot_market_spinner.setIsBeside(false);
        csm_hot_market_spinner.setShowDownView(rl_hot_market);
        tv_intelligent_sort.setDatas(dataTempList);
        tv_intelligent_sort.setShowDownView(tv_intelligent_sort);
        csm_hot_market_spinner.setOnTabClickListener(this);
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
                        largeDepartmentStoresPage.initData();
                        largeDepartmentStoresPage.loadRequset();
                        childAtOne.setClickable(false);
                        break;
                    case 1:

                        csm_hot_market_spinner.setBackgroundResource(R.drawable.tab_selected);
                        csm_hot_market_spinner.setCusomTextColor(getResources().getColor(R.color.white));
                        isSelect = true;
                        hotMarketPage.transData();
                        hotMarketPage.loadRequset();
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
                    topSortEvent.setDivisionCode(mDivisionCode);
                    topSortEvent.setDivisionType(mDivisionType);
                    topSortEvent.setEventType(Constants.SPEC_STORE_CATE);
                    topSortEvent.setSortType(sortsType);


                    if ("特产店".equals(shopName))
                    {
                        Utils.moveToFragment(SpecialtyStoreCategoryFragment.class, DetailFragment.this, "specialtystorefragment");
                    }
                    else if ("购物街".equals(shopName))
                    {
                        Utils.moveToFragment(ShoppingStreetFragment.class, DetailFragment.this, "shoppingstreetfragment");
                    }
                    else if ("超市".equals(shopName))
                    {
                        Utils.moveToFragment(SupermarketFragment.class, DetailFragment.this, "supermarketfragment");
                    }
                    else
                    {
                        Utils.makeToastAndShow(mContext,"该功能正在建设中...");
                        return;
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
        switch (view.getId())
        {
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
    public void onSpinnerTextClick(int postion) {
        String type = dataTempList.get(postion);
        sortsType = sortTypeMap.get(type);
        if (currentItem==0) {
            largeDepartmentStoresPage.loadRequset();
        } else if (currentItem==1){
            hotMarketPage.loadRequset();
        }

    }

    /**
     *  热门商场下拉选择 数据
     * @param position
     */
    @Override
    public void OnSnipperClick(int position,View v)
    {
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
