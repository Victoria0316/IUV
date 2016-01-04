package bluemobi.iuv.fragment;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bluemobi.iuv.R;
import bluemobi.iuv.activity.MainActivity;
import bluemobi.iuv.annotation.ContentView;
import bluemobi.iuv.fragment.page.HotMarketPage;
import bluemobi.iuv.fragment.page.LargeDepartmentStoresPage;
import bluemobi.iuv.fragment.page.MyCollectGoodsPage;
import bluemobi.iuv.fragment.page.MyCollectStoresPage;
import bluemobi.iuv.view.CustomSpinnerBase;
import bluemobi.iuv.view.CustomSpinnerSingle;
import bluemobi.iuv.view.LoadingPage;
import bluemobi.iuv.view.TextViewWithPopWindow;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liufy on 2015/8/4.
 * P11_2我的收藏
 */
@ContentView(R.layout.fragment_my_collect)
public class MyCollectionFragment extends BaseFragment {

    @Bind(R.id.tv_collect_goods)
    protected TextView tv_collect_goods;

    @Bind(R.id.tv_collect_stores)
    protected TextView tv_collect_stores;

    @Bind(R.id.vp_tab)
    protected ViewPager mViewPager;
    private PagerAdapter mAdapter;

    private MyCollectGoodsPage myCollectGoodsPage;
    private MyCollectStoresPage myCollectStoresPage;

    private List<View> mPages = new ArrayList<View>();

    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity) mContext).setMineBarSytle(getResources().getString(R.string.personal_center_my_collect_title), R.color.common_blue, true);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (((MainActivity) mContext).isHiddChange)
        {
            ((MainActivity) mContext).setMineBarSytle(getResources().getString(R.string.personal_center_my_collect_title), R.color.common_blue, true);
        }

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        isShowLoadPage = false;
        myCollectGoodsPage = new MyCollectGoodsPage(mContext,MyCollectionFragment.this);
        myCollectGoodsPage.loadRequset();
        myCollectStoresPage = new MyCollectStoresPage(mContext,MyCollectionFragment.this);
        mPages.add(myCollectGoodsPage.getRootView());
        mPages.add(myCollectStoresPage.getRootView());

    }

    @Override
    protected void successViewCompleted(View successView) {
        ButterKnife.bind(this, successView);
        mViewPager.setCurrentItem(0);
        tv_collect_goods.setBackgroundResource(R.drawable.tab_selected);
        tv_collect_goods.setTextColor(getResources().getColor(R.color.white));
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                int currentItem = mViewPager.getCurrentItem();
                clearState();
                switch (currentItem) {
                    case 0:
                        tv_collect_goods.setBackgroundResource(R.drawable.tab_selected);
                        tv_collect_goods.setTextColor(getResources().getColor(R.color.white));
                        myCollectGoodsPage.initData();
                        myCollectGoodsPage.loadRequset();
                        break;
                    case 1:
                        tv_collect_stores.setBackgroundResource(R.drawable.tab_selected);
                        tv_collect_stores.setTextColor(getResources().getColor(R.color.white));
                        myCollectStoresPage.initData();
                        myCollectStoresPage.loadRequset();
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


    }


    @OnClick(R.id.tv_collect_goods)
    public void clickGoods()
    {
        mViewPager.setCurrentItem(0);
    }

    @OnClick(R.id.tv_collect_stores)
    public void clickStores()
    {
        mViewPager.setCurrentItem(1);
    }




    public void clearState()
    {
        tv_collect_goods.setBackgroundResource(android.R.color.transparent);
        tv_collect_goods.setTextColor(getResources().getColor(R.color.black_light));
        tv_collect_stores.setBackgroundResource(android.R.color.transparent);
        tv_collect_stores.setTextColor(getResources().getColor(R.color.black_light));

    }


    private  class MyTabPageAdapter extends  PagerAdapter
    {



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
        public void destroyItem(ViewGroup container, int position, Object object)
        {

            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object)
        {
            if (view == object)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

}
