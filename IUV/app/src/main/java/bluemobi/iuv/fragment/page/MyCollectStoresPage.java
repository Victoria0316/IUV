package bluemobi.iuv.fragment.page;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import bluemobi.iuv.R;
import bluemobi.iuv.adapter.CommonAdapter;
import bluemobi.iuv.adapter.ViewHolder;
import bluemobi.iuv.app.IuwApplication;
import bluemobi.iuv.base.utils.Logger;
import bluemobi.iuv.entity.PersonalCenterMyInformBean;
import bluemobi.iuv.eventbus.StoreDetailsEvent;
import bluemobi.iuv.eventbus.TreasureEvent;
import bluemobi.iuv.fragment.BaseFragment;
import bluemobi.iuv.fragment.MarketInfoFragment;
import bluemobi.iuv.fragment.MyCouponFragment;
import bluemobi.iuv.fragment.MyTreasureFragment;
import bluemobi.iuv.fragment.TreasureDetailFragment;
import bluemobi.iuv.fragment.VipCouponDetailFragment;
import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.model.StoreInfo;
import bluemobi.iuv.network.model.StoreModel;
import bluemobi.iuv.network.request.CollectionStoreRequest;
import bluemobi.iuv.network.response.CollectionStoreResponse;
import bluemobi.iuv.util.Constants;
import bluemobi.iuv.util.StringUtils;
import bluemobi.iuv.util.Utils;
import bluemobi.iuv.util.WebUtils;
import de.greenrobot.event.EventBus;

/**
 * Created by liufy on 2015/8/8.
 * P11_2我的收藏 收藏的商场
 */
public class MyCollectStoresPage extends BasePage implements View.OnClickListener{

    private BaseFragment fragment;

    private StoreModel mStoreMoel;
    private List<StoreInfo> dataList = new ArrayList<StoreInfo>();

    public MyCollectStoresPage(Context context,BaseFragment fragment) {
        super(context, fragment);
        this.fragment = fragment;
    }

    private CommonAdapter<StoreInfo> adapter;


    @Override
    public View initView(LayoutInflater inflater) {

        View homeView = inflater.inflate(R.layout.page_collect_market,null);

        plv_refresh = (PullToRefreshListView) homeView.findViewById(R.id.plv_refresh);
        initPullToRefresh(plv_refresh);
        plv_refresh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Utils.moveToFragment(MarketInfoFragment.class, fragment, "MarketInfoFragment");
                StoreDetailsEvent storeDetailsEvent = new StoreDetailsEvent();
                storeDetailsEvent.setShopID(dataList.get(position-1).getId());
                Logger.e("TAG onItemClick---->", dataList.get(position).getShopName());
                Logger.e("TAG onItemClick--position-->",position);
                EventBus.getDefault().post(storeDetailsEvent);
            }
        });

        return homeView;
    }

    @Override
    protected void successViewCompleted(View successView) {

    }


    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_collect_treasure:

                break;
            case R.id.iv_collect_vip:

                break;
        }
    }

    @Override
    protected IuwHttpJsonRequest initRequest() {
        CollectionStoreRequest request = new CollectionStoreRequest(
                new Response.Listener<CollectionStoreResponse>() {
                    @Override
                    public void onResponse(CollectionStoreResponse response) {
                        Utils.closeDialog();
                        plv_refresh.onRefreshComplete();
                        if (response != null && response.getStatus() == 0) {
                            showListData(response.getData());
                        }
                    }
                }, this);
        request.setUserId(IuwApplication.getInstance().getMyUserInfo().getUserId());
        request.setCurrentnum(Constants.CURRENTNUMBASE+"");
        request.setCurrentpage("0");
        return request;
    }
    private void showListData(StoreModel list)
    {
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

        if (adapter == null) {
            plv_refresh.setAdapter(
                    adapter = new CommonAdapter<StoreInfo>(pageContext, dataList, R.layout.item_my_collect_market) {

                        @Override
                        public void convert(ViewHolder helper, StoreInfo bean) {

                            ImageView head = helper.getView(R.id.head);
                            Glide.with(mContext).load(bean.getShopImgPath()).placeholder(R.drawable.collect_goods).into(head);
                            TextView market_name=helper.getView(R.id.market_name);
                            market_name.setText(bean.getShopName());
                            TextView market_address=helper.getView(R.id.market_address);
                            market_address.setText(bean.getShopAddress());
                            TextView phone= helper.getView(R.id.phone);
                            phone.setText("电话："+bean.getShopCellphone());
                            ImageView iv_collect_vip = helper.getView(R.id.iv_collect_vip);
                            ImageView iv_collect_treasure =helper.getView(R.id.iv_collect_treasure);
                            String collectionId = bean.getCollectionId();
                            String praiseId = bean.getPraiseId();
                            iv_collect_vip.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Utils.moveToFragment(VipCouponDetailFragment.class, fragment, "MyCouponFragment");
                                }
                            });
                            iv_collect_treasure.setTag(bean.getId());
                            iv_collect_treasure.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Utils.moveToFragment(TreasureDetailFragment.class, fragment, "MyTreasureFragment");
                                    TreasureEvent treasureEvent = new TreasureEvent();
                                    treasureEvent.setTreasureID((String)v.getTag());
                                    EventBus.getDefault().post(treasureEvent);
                                }
                            });
                        }
                    });

        } else {
            adapter.notifyDataSetChanged();
        }
    }

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
        CollectionStoreRequest request = new CollectionStoreRequest(
                new Response.Listener<CollectionStoreResponse>() {
                    @Override
                    public void onResponse(CollectionStoreResponse response) {
                        Utils.closeDialog();
                        if (response != null && response.getStatus() == 0)
                        {
                            showListData(response.getData());
                        }
                    }
                },this);
        request.setUserId(IuwApplication.getInstance().getMyUserInfo().getUserId());
        request.setCurrentnum(Constants.CURRENTNUMBASE+"");
        request.setCurrentpage(getCurPage() + "");
        WebUtils.doPost(request);
    }
}
