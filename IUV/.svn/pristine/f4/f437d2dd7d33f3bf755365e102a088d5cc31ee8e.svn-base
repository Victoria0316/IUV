package bluemobi.iuv.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.bumptech.glide.Glide;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import bluemobi.iuv.R;
import bluemobi.iuv.activity.MainActivity;
import bluemobi.iuv.adapter.CommonAdapter;
import bluemobi.iuv.adapter.ViewHolder;
import bluemobi.iuv.annotation.ContentView;
import bluemobi.iuv.app.IuwApplication;
import bluemobi.iuv.app.TitleBarManager;
import bluemobi.iuv.base.BaseActivity;
import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.model.VipInfo;
import bluemobi.iuv.network.model.VipListModel;
import bluemobi.iuv.network.request.GetVipCardRequest;
import bluemobi.iuv.network.request.VipInfoRequest;
import bluemobi.iuv.network.response.GetVipCardResponse;
import bluemobi.iuv.network.response.VipInfoResponse;
import bluemobi.iuv.util.Constants;
import bluemobi.iuv.util.Utils;
import bluemobi.iuv.util.WebUtils;
import bluemobi.iuv.view.LoadingPage;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gaoyn on 2015/8/5.
 *
 * P10_VIP卡详情页面
 */
@ContentView(R.layout.fragment_vip_detail)
public class VipCouponDetailFragment extends BaseFragment {

    @Bind(R.id.plv_refresh)
    PullToRefreshListView plv_refresh;


    private CommonAdapter<VipInfo> adapter;
    private List<VipInfo> dataList = new ArrayList<VipInfo>();

    private AlertDialog dialog;
    private View view;


    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity) mContext).setMineBarSytle(getResources().getString(R.string.coupon_receive), R.color.common_blue, true);
    }


    @Override
    protected IuwHttpJsonRequest initRequest() {
        VipInfoRequest request = new VipInfoRequest(new Response.Listener<VipInfoResponse>() {
            @Override
            public void onResponse(VipInfoResponse response) {
                if (response != null && response.getStatus() ==0){
                    showListData(response.getData());
                }
            }
        }, (Response.ErrorListener) getActivity());
        request.setCurrentpage("0");
        request.setCurrentnum(Constants.CURRENTNUMBASE + "");
        return request;
    }

    @Override
    protected void successViewCompleted(View successView) {
        ButterKnife.bind(this, successView);

        initPullToRefresh(plv_refresh);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        isShowLoadPage=false;
    }


    private void showListData(VipListModel list)
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
                    adapter = new CommonAdapter<VipInfo>(mContext, dataList, R.layout.lv_coupons) {
                        @Override
                        public void convert(ViewHolder helper, VipInfo item) {
                            ImageView coupons_image= helper.getView(R.id.coupons_image);
                            Glide.with(mContext).load(item.getMembershipCardImgPath()).placeholder(R.drawable.collect_goods).into(coupons_image);
                            TextView content=helper.getView(R.id.content);
                            content.setText(item.getCardDescription());

                            TextView receive = helper.getView(R.id.receive);
                            receive.setTag(dataList.get(helper.getPosition()).getId());
                            receive.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    postGetVipCardRequest((String) v.getTag());
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
        VipInfoRequest request = new VipInfoRequest(new Response.Listener<VipInfoResponse>() {
            @Override
            public void onResponse(VipInfoResponse response) {
                Utils.closeDialog();
                plv_refresh.onRefreshComplete();
                if (response != null && response.getStatus() ==0){
                    showListData(response.getData());
                }
            }
        }, (Response.ErrorListener) getActivity());
        request.setCurrentpage("0");
        request.setCurrentnum(Constants.CURRENTNUMBASE + "");
        Utils.showProgressDialog(mContext);
        WebUtils.doPost(request);
    }

    private void postGetVipCardRequest(String id){
        GetVipCardRequest request = new GetVipCardRequest(new Response.Listener<GetVipCardResponse>() {
            @Override
            public void onResponse(GetVipCardResponse response) {
                Utils.closeDialog();
                if (response != null && response.getStatus() ==0){
                    if (dialog == null) {
                        dialog = new AlertDialog.Builder(mContext)
                                .create();
                        view = LayoutInflater.from(mContext).inflate(R.layout.dialog_receive_success, null);
                        TextView tv_sure = (TextView) view.findViewById(R.id.tv_sure);
                        Window window = dialog.getWindow();
                        window.setGravity(Gravity.CENTER);  //此处可以设置dialog显示的位置
                        dialog.show();
                        dialog.setContentView(view);
                        tv_sure.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (dialog != null) {
                                    dialog.dismiss();
                                }
                            }
                        });
                    } else {
                        dialog.show();
                    }
                }
            }
        }, (Response.ErrorListener) getActivity());
        request.setUserId(IuwApplication.getInstance().getMyUserInfo().getUserId());
        request.setMembershipCardId(id);
        Utils.showProgressDialog(mContext);
        WebUtils.doPost(request);
    }

}
