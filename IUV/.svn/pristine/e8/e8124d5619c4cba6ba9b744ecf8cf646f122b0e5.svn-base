package bluemobi.iuv.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.android.volley.Response;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import bluemobi.iuv.R;
import bluemobi.iuv.activity.MainActivity;
import bluemobi.iuv.adapter.CommonAdapter;
import bluemobi.iuv.adapter.ViewHolder;
import bluemobi.iuv.annotation.ContentView;
import bluemobi.iuv.base.utils.Logger;
import bluemobi.iuv.eventbus.ShopEvent;
import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.model.NotifyInfo;
import bluemobi.iuv.network.model.NotifyModel;
import bluemobi.iuv.network.request.GetMyNotifyRequest;
import bluemobi.iuv.network.response.GetMyNotifyResponse;
import bluemobi.iuv.util.Constants;
import bluemobi.iuv.util.Utils;
import bluemobi.iuv.util.WebUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by gaozq on 2015/8/6.
 * modify by liufy on 2015/8/9.
 */
@ContentView(R.layout.fragment_my_notifyications)
public class MyNotificationsFragment extends BaseFragment {

    @Bind(R.id.plv_refresh)
    protected PullToRefreshListView plv_refresh;

    private CommonAdapter<NotifyInfo> adapter ;

    private List<NotifyInfo> dataList = new ArrayList<NotifyInfo>();

    @Override
    public void initData(Bundle savedInstanceState) {

        isShowLoadPage=false;
    }

    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity) mContext).setMineBarSytle(getResources().getString(R.string.personal_center_title), R.color.common_blue, true);
    }


    @Override
    public void onHiddenChanged(boolean hidden) {

        super.onHiddenChanged(hidden);
        if (((MainActivity) mContext).isHiddChange)
        {
            ((MainActivity) mContext).setMineBarSytle(getResources().getString(R.string.personal_center_title), R.color.common_blue, true);
        }
    }

    @Override
    protected void successViewCompleted(View successView) {

        ButterKnife.bind(this, successView);
        initPullToRefresh(plv_refresh);
        plv_refresh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String idd = dataList.get(position-1).getId();
                ShopEvent mShopEvent = new ShopEvent();
                mShopEvent.setId(idd);
                Logger.e("TAG=====>",idd);
                Utils.moveToFragment(MarketInfoFragment.class, MyNotificationsFragment.this, "MarketInfoFragment");
                EventBus.getDefault().post(mShopEvent);
                //点击通知跳转到 商店详情页面p8-4
            }
        });

    }

    @Override
    protected IuwHttpJsonRequest initRequest() {
        GetMyNotifyRequest request = new GetMyNotifyRequest(new Response.Listener<GetMyNotifyResponse>() {
            @Override
            public void onResponse(GetMyNotifyResponse response) {
                if(response != null && response.getStatus() == 0){
                    if(response.getData() != null){
                        showListData(response.getData());
                    }
                }else{
                }
            }
        }, (Response.ErrorListener) getActivity());
        request.setCurrentpage("0");
        request.setCurrentnum(Constants.CURRENTNUMBASE+"");
        return request;
    }
    private void showListData(NotifyModel list) {
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
            plv_refresh.setAdapter(adapter = new CommonAdapter<NotifyInfo>(mContext, dataList, R.layout.item_my_inform) {
                @Override
                public void convert(ViewHolder helper, NotifyInfo item) {

                    TextView content=helper.getView(R.id.content);
                    content.setText(item.getNoticeInfo());
                    TextView market_name=helper.getView(R.id.market_name);
                    market_name.setText(item.getShopsName());
                }
            });

        } else {
            adapter.notifyDataSetChanged();
        }
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
        GetMyNotifyRequest request = new GetMyNotifyRequest(new Response.Listener<GetMyNotifyResponse>() {
            @Override
            public void onResponse(GetMyNotifyResponse response) {
                plv_refresh.onRefreshComplete();
                if(response != null && response.getStatus() == 0){
                    if(response.getData() != null){
                        showListData(response.getData());
                    }
                }else{
                }
            }
        }, (Response.ErrorListener) getActivity());
        request.setCurrentpage(getCurPage()+"");
        request.setCurrentnum(Constants.CURRENTNUMBASE+"");
        WebUtils.doPost(request);
    }


}
