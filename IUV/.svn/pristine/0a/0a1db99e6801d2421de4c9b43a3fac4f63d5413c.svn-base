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
import bluemobi.iuv.fragment.BaseFragment;
import bluemobi.iuv.fragment.BrandDetailFragment;
import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.model.GoodInfo;
import bluemobi.iuv.network.model.GoodsMoel;
import bluemobi.iuv.network.request.CollectionGoodRequest;
import bluemobi.iuv.network.response.CollectionGoodResponse;
import bluemobi.iuv.util.Constants;
import bluemobi.iuv.util.Utils;
import bluemobi.iuv.util.WebUtils;
import bluemobi.iuv.view.LoadingPage;

/**
 * Created by liufy on 2015/8/8.
 * * P11_2我的收藏 收藏的商品
 */
public class MyCollectGoodsPage extends BasePage {

    private BaseFragment fragment;

    private GoodsMoel mGoodsMoel;
    private List<GoodInfo> dataList = new ArrayList<GoodInfo>();

    public MyCollectGoodsPage(Context context, BaseFragment fragment) {
        super(context, fragment);
        this.fragment = fragment;
    }

    private CommonAdapter<GoodInfo> adapter;


    @Override
    public View initView(LayoutInflater inflater) {

        View homeView = inflater.inflate(R.layout.page_collect_goods, null);
        plv_refresh = (PullToRefreshListView) homeView.findViewById(R.id.plv_refresh);
        initPullToRefresh(plv_refresh);
        plv_refresh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Utils.moveToFragment(BrandDetailFragment.class, fragment, "BrandDetailFragment");
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
    protected IuwHttpJsonRequest initRequest() {
        CollectionGoodRequest request = new CollectionGoodRequest(
                new Response.Listener<CollectionGoodResponse>() {
                    @Override
                    public void onResponse(CollectionGoodResponse response) {
                        Utils.closeDialog();
                        if (response != null && response.getStatus() == 0) {
                            showListData(response.getData());
                        }
                    }
                },this);
        request.setUserId(IuwApplication.getInstance().getMyUserInfo().getUserId());
        request.setCurrentnum(Constants.CURRENTNUMBASE+"");
        request.setCurrentpage("0");
        return request;
    }

    private void showListData(GoodsMoel list)
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
                    adapter = new CommonAdapter<GoodInfo>(pageContext, dataList, R.layout.item_my_collect_goods) {
                        @Override
                        public void convert(ViewHolder helper, GoodInfo bean) {
                            ImageView head = helper.getView(R.id.head);
                            TextView goods_name = helper.getView(R.id.goods_name);
                            TextView goods_info = helper.getView(R.id.goods_info);
                            TextView money = helper.getView(R.id.money);
                            goods_name.setText(bean.getShopsName());
                            goods_info.setText(bean.getDescription());
                            money.setText("￥" + bean.getCustomerPrice());
                            Glide.with(mContext).load(bean.getImgPath()).placeholder(R.drawable.collect_goods).into(head);
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
        CollectionGoodRequest request = new CollectionGoodRequest(
                new Response.Listener<CollectionGoodResponse>() {
                    @Override
                    public void onResponse(CollectionGoodResponse response) {
                        Utils.closeDialog();
                        plv_refresh.onRefreshComplete();
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

