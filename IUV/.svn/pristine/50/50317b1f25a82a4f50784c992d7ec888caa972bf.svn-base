package bluemobi.iuv.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
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
import bluemobi.iuv.entity.PersonalCenterMyInformBean;
import bluemobi.iuv.eventbus.BaseEvent;
import bluemobi.iuv.eventbus.TreasureEvent;
import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.model.TruesureInfo;
import bluemobi.iuv.network.model.TruesureModel;
import bluemobi.iuv.network.model.VipInfo;
import bluemobi.iuv.network.model.VipListModel;
import bluemobi.iuv.network.request.GetTruesureListRequest;
import bluemobi.iuv.network.request.GetVipListRequest;
import bluemobi.iuv.network.response.GetTruesureListResponse;
import bluemobi.iuv.network.response.GetVipListResponse;
import bluemobi.iuv.util.Constants;
import bluemobi.iuv.util.StringUtils;
import bluemobi.iuv.util.UiUtils;
import bluemobi.iuv.util.Utils;
import bluemobi.iuv.util.WebUtils;
import bluemobi.iuv.view.LoadingPage;
import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by liufy on 2015/8/9.
 * * P11_3我的宝藏
 */
@ContentView(R.layout.fragment_my_treasure)
public class MyTreasureFragment extends BaseFragment {

    @Bind(R.id.plv_refresh)
    protected PullToRefreshListView plv_refresh;

    private CommonAdapter<TruesureInfo> mAdapter = null;


    List<TruesureInfo> mListData = new ArrayList<TruesureInfo>();
    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity) mContext).setMineBarSytle(getResources().getString(R.string.personal_center_my_storbhouse_title), R.color.common_blue, true);
    }

    @Override
    protected IuwHttpJsonRequest initRequest() {
        GetTruesureListRequest request = new GetTruesureListRequest(new Response.Listener<GetTruesureListResponse>() {
            @Override
            public void onResponse(GetTruesureListResponse response) {
                if (response != null && response.getStatus() ==0){
                    if (response.getData().getInfo()!=null){
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
        isShowLoadPage = false;
    }


    private void showListData(TruesureModel list) {
        if (list == null) {
            return;
        }
        if (list.getInfo().size() == 0) {
            return;
        }
        if (list.getCurrentpage().equals("0")) {
            mListData.clear();
            mListData.addAll(list.getInfo());
        } else {
            mListData.addAll(list.getInfo());
        }

        if (mAdapter == null) {
            plv_refresh.setAdapter(
                    mAdapter = new CommonAdapter<TruesureInfo>(mContext,
                            mListData, R.layout.item_my_collect) {
                        @Override
                        public void convert(ViewHolder helper, TruesureInfo item) {

                            TextView market_name=helper.getView(R.id.market_name);
                            market_name.setText(item.getShopsName());

                            TextView market_address=helper.getView(R.id.market_address);
                            market_address.setText(item.getShopAddress());

                            TextView phone=helper.getView(R.id.phone);
                            phone.setText("电话：" + item.getShopCellphone());

                            ImageView view = helper.getView(R.id.head);

                            if (StringUtils.isNotEmpty(item.getImgPath()) && !"null".equals(item.getImgPath())) {
                                Glide.with(mContext).load(item.getImgPath()).into(view);
                            } else {
                                view.setBackgroundResource(R.drawable.bztupian1);
                            }
                            view.setTag(item.getId());
                            view.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String id=String.valueOf(v.getTag());//baozang id
                                    TreasureEvent treasureEvent = new TreasureEvent();
                                    treasureEvent.setTreasureID(id);
                                    Utils.moveToFragment(TreasureDetailFragment.class, MyTreasureFragment.this, "TreasureDetailFragment");
                                    EventBus.getDefault().post(treasureEvent);
                                }
                            });
                        }
                    });

        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected boolean getPage(int type) {
        if(!super.getPage(type)){
            return false;
        }
        connectToServer();
        return true;

    }
    private void connectToServer()
    {
        GetTruesureListRequest request = new GetTruesureListRequest(new Response.Listener<GetTruesureListResponse>() {
            @Override
            public void onResponse(GetTruesureListResponse response) {
                if (response != null && response.getStatus() ==0){
                    if (response.getData().getInfo()!=null){
                        showListData(response.getData());
                    }
                }
            }
        }, (Response.ErrorListener) getActivity());
        request.setCurrentnum(Constants.CURRENTNUMBASE+"");
        request.setCurrentpage(getCurPage()+"");
        request.setUserId(IuwApplication.getInstance().getMyUserInfo().getUserId());
        WebUtils.doPost(request);
    }
}
