package bluemobi.iuv.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.model.VipInfo;
import bluemobi.iuv.network.model.VipListModel;
import bluemobi.iuv.network.request.GetVipListRequest;
import bluemobi.iuv.network.response.GetVipListResponse;
import bluemobi.iuv.util.Constants;
import bluemobi.iuv.util.StringUtils;
import bluemobi.iuv.util.WebUtils;
import bluemobi.iuv.view.CircleImageView;
import bluemobi.iuv.view.LoadingPage;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created and Modified by liufy on 2015/8/9.
 * P11_1我的VIP卡包
 * before Created by gaoyn
 */
@ContentView(R.layout.fragment_coupon_receive)
public class MyCouponFragment extends BaseFragment {
    @Bind(R.id.plv_refresh)
    protected PullToRefreshListView plv_refresh;
    private CommonAdapter<VipInfo> adapter;
    private List<VipInfo> dataList = new ArrayList<VipInfo>();

    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity) mContext).setMineBarSytle(getResources().getString(R.string.my_coupon), R.color.common_blue, true);
    }

    @Override
    protected IuwHttpJsonRequest initRequest() {
        GetVipListRequest request = new GetVipListRequest(new Response.Listener<GetVipListResponse>() {
            @Override
            public void onResponse(GetVipListResponse response) {
                if (response != null && response.getStatus() == 0) {
                    if (response.getData().getInfo() != null) {
                        showListData(response.getData());
                    }
                }
            }
        }, (Response.ErrorListener) getActivity());
        request.setCurrentnum(Constants.CURRENTNUMBASE+"");
        request.setCurrentpage("0");
        request.setUserId(IuwApplication.getInstance().getMyUserInfo().getUserId());
        return request;
    }

    @Override
    protected void successViewCompleted(View successView) {
        ButterKnife.bind(this, successView);
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
        GetVipListRequest request = new GetVipListRequest(new Response.Listener<GetVipListResponse>() {
            @Override
            public void onResponse(GetVipListResponse response) {
                if (response != null && response.getStatus() == 0) {
                    plv_refresh.onRefreshComplete();
                    if (response.getData().getInfo() != null) {
                        showListData(response.getData());
                    }
                }
            }
        }, (Response.ErrorListener) getActivity());
        request.setCurrentnum(Constants.CURRENTNUMBASE+"");
        request.setCurrentpage(getCurPage() + "");
        request.setUserId(IuwApplication.getInstance().getMyUserInfo().getUserId());
        WebUtils.doPost(request);
    }

    private void showListData(VipListModel list) {
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
                    adapter = new CommonAdapter<VipInfo>(mContext,
                            dataList, R.layout.lv_coupons) {
                        @Override
                        public void convert(ViewHolder helper, VipInfo item) {
                            TextView receive = helper.getView(R.id.receive);
                            receive.setVisibility(View.GONE);
                            CircleImageView coupons_image = (CircleImageView)helper.getView(R.id.coupons_image);
                            helper.setText(R.id.info, item.getCardDescription());
                            String startTime = StringUtils.isEmpty(item.getCardStartTime())?"":item.getCardStartTime();
                            String endTime = StringUtils.isEmpty(item.getCardEndTime())?"":item.getCardEndTime();
                            if ("null".equals(startTime))
                            {
                                startTime = "";
                            }
                            if ("null".equals(endTime))
                            {
                                endTime = "";
                            }
                            helper.setText(R.id.content,"使用期限" +startTime + "-" + endTime);
                            Glide.with(mContext).load(item.getMembershipCardImgPath()).into(coupons_image);

                        }
                    });

        } else {
            adapter.notifyDataSetChanged();
        }
    }
}
