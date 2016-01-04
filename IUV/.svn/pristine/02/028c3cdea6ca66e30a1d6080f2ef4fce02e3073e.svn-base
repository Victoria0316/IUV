package bluemobi.iuv.fragment;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import bluemobi.iuv.R;
import bluemobi.iuv.activity.MainActivity;
import bluemobi.iuv.annotation.ContentView;
import bluemobi.iuv.app.IuwApplication;
import bluemobi.iuv.base.utils.Logger;
import bluemobi.iuv.eventbus.BaseEvent;
import bluemobi.iuv.eventbus.CommentDetailEvent;
import bluemobi.iuv.eventbus.GoodEvent;
import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.request.BrandDetailRequest;
import bluemobi.iuv.network.response.BrandDetailResponse;
import bluemobi.iuv.util.StringUtils;
import bluemobi.iuv.util.Utils;
import bluemobi.iuv.view.AutoScrollViewPager;
import bluemobi.iuv.view.SwitchDotView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * P8-2-2 图片 600 X 588
 * Created by wangzhijun on 2015/8/6.
 * P8_3_2商品详情页面_热门商场-修改
 * P8_2_2商品详情页面_大型百货-修改
 * 商品详情页
 */
@ContentView(R.layout.fragment_brand_detail)
public class BrandDetailFragment extends BaseFragment {
    @Bind(R.id.title)
    protected TextView title;

    @Bind(R.id.desc)
    protected TextView desc;
    /**
     * 赞
     */
    @Bind(R.id.share)
    protected TextView share;
    /**
     * price
     */
    @Bind(R.id.price)
    protected TextView price;
    /**
     * 评价
     */
    @Bind(R.id.toReview)
    protected TextView toReview;
    /**
     * 点评数量
     */
    @Bind(R.id.comment_count)
    protected TextView comment_count;

    @Bind(R.id.guide_swicth_dots)
    protected SwitchDotView mPageSwitchDot;

    @Bind(R.id.flipper)
    protected AutoScrollViewPager autoScrollViewPager;

    private int banderWidth;

    private int banderHeight;
    private ViewpagerAdapter mAdapter;
    private  int size = 0;

    private List<ImageView> mViewList = new ArrayList<ImageView>();

    private  String goodId;

    private String productId;
    /**
     * 点赞数
     */
    private String mPraiseNum;


    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity) mContext).showPriseTextBar(StringUtils.isEmpty(mPraiseNum) ? "" : mPraiseNum);
        Logger.e("tag Brand","onStart");
    }

    public  BrandDetailFragment()
    {
        EventBus.getDefault().register(this);
    }




    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (((MainActivity) mContext).isHiddChange) {
            ((MainActivity) mContext).showPriseTextBar(StringUtils.isEmpty(mPraiseNum)?"":mPraiseNum);
        }
        Logger.e("tag Brand","onHiddenChanged");
    }

    public void onEvent(BaseEvent baseEvent) {
        if (baseEvent instanceof GoodEvent) {
            GoodEvent goodEvent = (GoodEvent) baseEvent;

            goodId = goodEvent.getGoodId();

        }
    }

    @Override
    protected IuwHttpJsonRequest initRequest() {

        BrandDetailRequest request = new BrandDetailRequest
                (
                        new Response.Listener<BrandDetailResponse>() {
                            @Override
                            public void onResponse(BrandDetailResponse response)
                            {
                                BrandDetailResponse.BrandDetailData data = response.data;
                                if (data!=null)
                                {
                                    wrapDetailData(data);
                                }

                            }
                        }, (Response.ErrorListener) getActivity());

        request.setId(goodId);
        if (IuwApplication.getInstance().getMyUserInfo()!=null)
        request.setUserId(IuwApplication.getInstance().getMyUserInfo().getUserId());
        return request;
    }

    private void wrapDetailData(BrandDetailResponse.BrandDetailData data)
    {
        productId = data.id;
        mPraiseNum = data.praiseNum;
        mPraiseNum = 500+"";
        ((MainActivity) mContext).showPriseTextBar(StringUtils.isEmpty(mPraiseNum)?"":mPraiseNum);
        title.setText(data.productName);
        desc.setText(data.description);
        price.setText("¥" + data.customerPrice);
        comment_count.setText(data.commentNum);
        ArrayList<BrandDetailResponse.ImageListDTO> imgList = data.imgList;
        if (imgList==null)
        {
            return;
        }

        autoScrollViewPager.getLayoutParams().width = banderWidth;
        autoScrollViewPager.getLayoutParams().height = banderHeight;
        autoScrollViewPager.setInterval(2000);
        autoScrollViewPager.setCycle(true);
        autoScrollViewPager.startAutoScroll();
        initImages(imgList);
        mAdapter = new ViewpagerAdapter();
        autoScrollViewPager.setAdapter(mAdapter);
        mPageSwitchDot.generateDots(mViewList.size());
        autoScrollViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (mViewList.size() == 1) {
                    return;
                }
                mPageSwitchDot.setCurrentIndex(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }

    @Override
    protected void successViewCompleted(View successView) {
        ButterKnife.bind(this, successView);

    }

    private void initImages(ArrayList<BrandDetailResponse.ImageListDTO> imgListParm) {
        for (int i = 0; i < imgListParm.size(); i++) {
            ImageView imageView = new ImageView(getActivity());
            Glide.with(this).load(imgListParm.get(i).imgPath).placeholder(R.drawable.p8_2_2_goods_detail).into(imageView);
            mViewList.add(imageView);
        }

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        isShowLoadPage = true;
        int deviceWidth = Utils.getDeviceWidth(getActivity());
        banderWidth = (deviceWidth - Utils.dip2px(getActivity(), 24));
        banderHeight = banderWidth * 588 / 600;

    }

    @OnClick(R.id.toReview)
    public void comment() {

        CommentDetailEvent commentDetailEvent = new CommentDetailEvent();
        commentDetailEvent.setProductID(productId);
        commentDetailEvent.setSourceType("1");
        Utils.moveToFragment(CommentDetailsFragment.class, BrandDetailFragment.this, "CommentDetailsFragment");
        EventBus.getDefault().post(commentDetailEvent);
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
//            return super.instantiateItem(container, position);
            container.addView(mViewList.get(position), 0);//添加页卡
            return mViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView(mViewList.get(position));
        }
    }

}
