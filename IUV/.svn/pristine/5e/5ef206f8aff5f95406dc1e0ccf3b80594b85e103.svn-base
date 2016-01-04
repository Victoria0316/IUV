package bluemobi.iuv.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.android.volley.Response;

import java.util.ArrayList;

import bluemobi.iuv.R;
import bluemobi.iuv.activity.MainActivity;
import bluemobi.iuv.adapter.CommonAdapter;
import bluemobi.iuv.adapter.ViewHolder;
import bluemobi.iuv.annotation.ContentView;
import bluemobi.iuv.base.utils.Logger;
import bluemobi.iuv.eventbus.BaseEvent;
import bluemobi.iuv.eventbus.TopSortEvent;
import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.request.SpecStoreCategoryRequest;
import bluemobi.iuv.network.response.TopSortResponse;
import bluemobi.iuv.util.Constants;
import bluemobi.iuv.util.Utils;
import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by liufy on 2015/11/3.
 * P8_8_1-特产店类别-20151019
 */
@ContentView(R.layout.fragment_spec_category)
public class SpecialtyStoreCategoryFragment extends BaseFragment {

    @Bind(R.id.gv_category)
    protected GridView gv_category;

    private CommonAdapter<TopSortResponse.TopSortData> commonAdapter = null;

    private ArrayList<TopSortResponse.TopSortData> mData = null;


    private String mainID;

    private String divisionType;

    private String sortType;

    private String shopsTypeName;

    private String divisionCode;

    public SpecialtyStoreCategoryFragment() {
        EventBus.getDefault().register(this);
    }

    public void onEventMainThread(BaseEvent baseEvent) {

        if (baseEvent instanceof TopSortEvent&&baseEvent.getEventType()==Constants.SPEC_STORE_CATE) {
            TopSortEvent topSortEvent = (TopSortEvent) baseEvent;
            mainID = topSortEvent.getId();
            shopsTypeName = topSortEvent.getShopsTypeName();
            divisionType = topSortEvent.getDivisionType();
            sortType = topSortEvent.getSortType();
            divisionCode = topSortEvent.getDivisionCode(); Logger.e("onEventMainThread--SSCF->", shopsTypeName);
        }

    }

    @Override
    protected IuwHttpJsonRequest initRequest() {
        SpecStoreCategoryRequest request = new SpecStoreCategoryRequest
                (
                        new Response.Listener<TopSortResponse>() {
                            @Override
                            public void onResponse(TopSortResponse response) {
                                Utils.closeDialog();
                                if (response != null && response.getStatus() == 0) {
                                    mData = response.data;
                                    showData();

                                }
                            }
                        }, (Response.ErrorListener) getActivity());
        request.setPid(mainID);
        return request;
    }

    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity) mContext).setHomeBarSytle(shopsTypeName, true);
        Logger.e("onStart-SSCF-->", shopsTypeName);

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (((MainActivity) mContext).isHiddChange) {

            ((MainActivity) mContext).setHomeBarSytle(shopsTypeName, true);
            Logger.e("onHiddenChanged--SSCF->",shopsTypeName);
        }

    }

    @Override
    protected void successViewCompleted(View successView) {
        ButterKnife.bind(this, successView);
    }


    private void showData() {
        gv_category.setAdapter(commonAdapter = new CommonAdapter<TopSortResponse.TopSortData>(mContext, mData, R.layout.lv_search_item) {
            @Override
            public void convert(ViewHolder helper, TopSortResponse.TopSortData item) {
                TextView tv_city = (TextView) helper.getView(R.id.tv_city);
                tv_city.setText(item.shopsTypeName);
                tv_city.setTextColor(getResources().getColor(R.color.common_blue));
                tv_city.setBackgroundResource(R.drawable.city_white);


            }
        });
        gv_category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TopSortEvent topSortEvent = new TopSortEvent();
                topSortEvent.setId(mData.get(position).id);
                topSortEvent.setShopsTypeId(mData.get(position).shopsTypeCode);
                topSortEvent.setShopsTypeName(mData.get(position).shopsTypeName);
                topSortEvent.setDivisionType(divisionType);
                topSortEvent.setDivisionCode(divisionCode);
                topSortEvent.setSortType(sortType);
                Utils.moveToFragment(SpecialtyStoreFragment.class, SpecialtyStoreCategoryFragment.this, "specialtystorefragment");
                EventBus.getDefault().post(topSortEvent);
            }
        });
    }

}
