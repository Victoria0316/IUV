package bluemobi.iuv.fragment;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import bluemobi.iuv.R;
import bluemobi.iuv.activity.MainActivity;
import bluemobi.iuv.adapter.CommonAdapter;
import bluemobi.iuv.adapter.ViewHolder;
import bluemobi.iuv.annotation.ContentView;
import bluemobi.iuv.base.utils.Logger;
import bluemobi.iuv.eventbus.CountySpinnerBean;
import bluemobi.iuv.eventbus.GoodEvent;
import bluemobi.iuv.eventbus.StoreDetailsEvent;
import bluemobi.iuv.eventbus.TreasureEvent;
import bluemobi.iuv.network.request.BannerImageRequest;
import bluemobi.iuv.network.request.HomeTreasureListRequest;
import bluemobi.iuv.network.request.RecommendCountyRequest;
import bluemobi.iuv.network.response.BannerImageResponse;
import bluemobi.iuv.network.response.HomeTreasureListResponse;
import bluemobi.iuv.network.response.RecommendCountyResponse;
import bluemobi.iuv.util.Utils;
import bluemobi.iuv.util.WebUtils;
import bluemobi.iuv.view.AutoScrollViewPager;
import bluemobi.iuv.view.CircleImageView;
import bluemobi.iuv.view.CustomListView;
import bluemobi.iuv.view.RatioLayout;
import bluemobi.iuv.view.SwitchDotView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Created by liufy on 2015/8/4.
 * P7_首页
 */
@ContentView(R.layout.fragment_home)
public class HomePageFragment extends BaseFragment {

    @Bind(R.id.auto_viewpaper)
    protected AutoScrollViewPager autoScrollViewPager;

    @Bind(R.id.swicth_dots)
    protected SwitchDotView switchDotView;

    @Bind(R.id.lv_treasure)
    protected CustomListView lv_treasure;

    @Bind(R.id.fl_viewpaper)
    protected RatioLayout fl_viewpaper;

    @Bind(R.id.iv_europe)
    protected ImageView iv_europe;

    @Bind(R.id.iv_japan)
    protected ImageView iv_japan;

    @Bind(R.id.iv_australia)
    protected ImageView iv_australia;

    private ImageView iv2;

    private ImageView iv3;

    private ImageView iv1;

    private List<ImageView> mViewList = new ArrayList<ImageView>();

    private ViewpagerAdapter mAdapter;

    private CommonAdapter<HomeTreasureListResponse.HomeTreasureListData> adapter = null;

    private ArrayList<HomeTreasureListResponse.HomeTreasureListData> mData;

    private ArrayList<RecommendCountyResponse.RecommendCountyInfo> recommendData;


    @Override
    protected void successViewCompleted(View successView) {
        ButterKnife.bind(this, successView);
        getBannerImagesFromServer();
        getRecommentCountyFromServer();
        getHomeTreasure();
        autoScrollViewPager.setInterval(2000);
        autoScrollViewPager.setCycle(true);
        autoScrollViewPager.startAutoScroll();
        iv1 = new ImageView(mContext);
        iv2 = new ImageView(mContext);
        iv3 = new ImageView(mContext);
        fl_viewpaper.setRatio(2.13f);
        iv1.setScaleType(ImageView.ScaleType.FIT_XY);
        iv2.setScaleType(ImageView.ScaleType.FIT_XY);
        iv3.setScaleType(ImageView.ScaleType.FIT_XY);
        autoScrollViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (mViewList.size() == 1) {
                    return;
                }
                switchDotView.setCurrentIndex(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        lv_treasure.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((MainActivity)mContext).isBaseFragment = false;
                ((MainActivity) mContext).isHiddChange = false;
                ((MainActivity) mContext).selectionTab(2);
                Utils.moveToFragment(TreasureDetailFragment.class, HomePageFragment.this, "treasureDetailFragment");
                String treasureID = mData.get(position).id;
                TreasureEvent treasureEvent = new TreasureEvent();
                treasureEvent.setTreasureID(treasureID);
                EventBus.getDefault().post(treasureEvent);


            }
        });
    }

    /**
     * 获得宝藏
     */
    private void getHomeTreasure() {

        Utils.showProgressDialog(mContext);
        HomeTreasureListRequest request = new HomeTreasureListRequest
                (
                        new Response.Listener<HomeTreasureListResponse>() {
                            @Override
                            public void onResponse(HomeTreasureListResponse response) {
                                Utils.closeDialog();
                                ArrayList<HomeTreasureListResponse.HomeTreasureListData> data = response.data;
                                wrapLvData(data);

                            }
                        }, (Response.ErrorListener) getActivity());
        WebUtils.doPost(request);

    }

    private void wrapLvData(final ArrayList<HomeTreasureListResponse.HomeTreasureListData> data) {
        if (data == null && data.size() == 0) {
            return;
        }
        this.mData = data;
        if (adapter == null) {
            lv_treasure.setAdapter(adapter = new CommonAdapter<HomeTreasureListResponse.HomeTreasureListData>(mContext, mData, R.layout.lv_treasure_item) {
                @Override
                public void convert(ViewHolder helper, HomeTreasureListResponse.HomeTreasureListData item) {
                    //TODO
                    CircleImageView profile_image = (CircleImageView) helper.getView(R.id.profile_image);
                    Glide.with(mContext).load(item.imgPath).into(profile_image);
                    helper.setText(R.id.tv_home_title, item.preciousName);
                    helper.setText(R.id.tv_home_content, item.preciousDescription);
                    helper.setText(R.id.tv_home_content, item.shopAddress);


                }
            });
        } else {
            adapter.notifyDataSetChanged();
        }


    }

    /**
     * 获取轮播图
     */
    private void getBannerImagesFromServer() {
        BannerImageRequest request = new BannerImageRequest
                (
                        new Response.Listener<BannerImageResponse>() {
                            @Override
                            public void onResponse(BannerImageResponse response) {

                                if (response != null) {

                                    ArrayList<BannerImageResponse.BannerImageInfo> data = response.data;
                                    wrapBannerImage(data);

                                } else {
                                    Toast.makeText(mContext, response.getContent(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, (Response.ErrorListener) getActivity());
        WebUtils.doPost(request);

    }

    /**
     * 获取首页城市
     */
    private void getRecommentCountyFromServer() {
        RecommendCountyRequest request = new RecommendCountyRequest
                (
                        new Response.Listener<RecommendCountyResponse>() {
                            @Override
                            public void onResponse(RecommendCountyResponse response) {

                                recommendData = response.data;
                                wrapRecommendCounty(recommendData);

                            }
                        }, (Response.ErrorListener) getActivity());
        WebUtils.doPost(request);

    }

    /**
     * 推荐国家接口
     *
     * @param data
     */
    private void wrapRecommendCounty(ArrayList<RecommendCountyResponse.RecommendCountyInfo> data) {


    }

    /**
     * 首页轮播图
     *
     * @param data
     */
    private void wrapBannerImage(ArrayList<BannerImageResponse.BannerImageInfo> data) {
        if (data == null || data.size() == 0) {
            return;
        }
        Glide.with(this).load(data.get(0).imagePath).placeholder(R.drawable.home_banner_default).into(iv1);
        Glide.with(this).load(data.get(1).imagePath).placeholder(R.drawable.home_banner_default).into(iv2);
        Glide.with(this).load(data.get(2).imagePath).placeholder(R.drawable.home_banner_default).into(iv3);
        mViewList.add(iv1);
        mViewList.add(iv2);
        mViewList.add(iv3);
        final String attrType = data.get(0).attrType;
        final String id0 = data.get(0).id;
        final String attrType1 = data.get(1).attrType;
        final String id1 = data.get(1).id;
        final String attrType2 = data.get(2).attrType;
        final String id2 = data.get(2).id;
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bannerType(attrType, id0);
            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bannerType(attrType1, id1);
            }
        });
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bannerType(attrType2, id2);
            }
        });
        mAdapter = new ViewpagerAdapter();
        autoScrollViewPager.setAdapter(mAdapter);
        switchDotView.generateDots(mViewList.size());
    }

    /**
     * 点击轮播图跳转不同的页面
     *
     * @param type （1、商场2、商品3、宝藏）;
     */
    private void bannerType(String type, String id) {
        ((MainActivity)mContext).isBaseFragment = false;
        if ("1".equals(type)) {
            Utils.moveToFragment(MarketInfoFragment.class, HomePageFragment.this, "marketinfofragment");
            StoreDetailsEvent storeDetailsEvent = new StoreDetailsEvent();
            storeDetailsEvent.setShopID(id);
            EventBus.getDefault().post(storeDetailsEvent);


        } else if ("2".equals(type)) {
            Utils.moveToFragment(BrandDetailFragment.class, HomePageFragment.this, "branddetailfragment");
            GoodEvent goodEvent = new GoodEvent();
            goodEvent.setGoodId(id);
            EventBus.getDefault().post(goodEvent);
        } else if ("3".equals(type)) {
            Utils.moveToFragment(TreasureDetailFragment.class, HomePageFragment.this, "treasureDetailFragment");
            TreasureEvent treasureEvent = new TreasureEvent();
            treasureEvent.setTreasureID(id);
            EventBus.getDefault().post(treasureEvent);
        }
    }

    @OnClick(R.id.iv_japan)
    public void selectPlace() {


        ((MainActivity)mContext).isBaseFragment = false;
        CountySpinnerBean mCountySpinnerBean = new CountySpinnerBean();
        mCountySpinnerBean.setId(recommendData.get(0).id);
        mCountySpinnerBean.setIsTreasure(false);
        ((MainActivity) mContext).titleCity = "日本";
        ((MainActivity) mContext).clearSelection();
        Utils.moveToFragment(AreaFragment.class, this, "areaFragment");
        EventBus.getDefault().post(mCountySpinnerBean);
    }

    @OnClick(R.id.iv_europe)
    public void clickEurope() {
        ((MainActivity)mContext).isBaseFragment = false;
        CountySpinnerBean mCountySpinnerBean = new CountySpinnerBean();
        if (recommendData.size()==2)
        {
            mCountySpinnerBean.setId(recommendData.get(1).id);
        }
        ((MainActivity) mContext).titleCity = "西班牙";
        ((MainActivity) mContext).clearSelection();
        mCountySpinnerBean.setIsTreasure(false);
        Utils.moveToFragment(AreaFragment.class, this, "areaFragment");
        EventBus.getDefault().post(mCountySpinnerBean);
    }

    @OnClick(R.id.iv_australia)
    public void cliclAustralia() {
        ((MainActivity)mContext).isBaseFragment = false;
        CountySpinnerBean mCountySpinnerBean = new CountySpinnerBean();
        if (recommendData.size()==3)
        {
            mCountySpinnerBean.setId(recommendData.get(2).id);
        }

        ((MainActivity) mContext).titleCity = "澳大利亚";
        ((MainActivity) mContext).clearSelection();
        mCountySpinnerBean.setIsTreasure(false);
        Utils.moveToFragment(AreaFragment.class, this, "areaFragment");
        EventBus.getDefault().post(mCountySpinnerBean);
    }


    @Override
    public void onHiddenChanged(boolean hidden) {

        ((MainActivity)mContext).isBaseFragment = true;
        super.onHiddenChanged(hidden);
        if (((MainActivity) mContext).isHiddChange) {
            ((MainActivity) mContext).setHomeBar();
            ((MainActivity) mContext).selectionTab(1);
        }


    }

    @Override
    public void initData(Bundle savedInstanceState) {
        isShowLoadPage = false;
    }


    class ViewpagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mViewList == null ? 0 : mViewList.size();
        }

        public void destroyItem(View container, int position, Object object) {
            ImageView view = mViewList.get(position % mViewList.size());
            ((AutoScrollViewPager) container).removeView(view);
            view.setImageBitmap(null);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViewList.get(position), 0);//添加页卡
            return mViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViewList.get(position));
        }
    }
}
