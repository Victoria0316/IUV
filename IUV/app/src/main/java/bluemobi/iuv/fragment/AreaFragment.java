package bluemobi.iuv.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.android.volley.Response;

import java.util.ArrayList;
import java.util.List;

import bluemobi.iuv.R;
import bluemobi.iuv.activity.MainActivity;
import bluemobi.iuv.adapter.CommonAdapter;
import bluemobi.iuv.adapter.ViewHolder;
import bluemobi.iuv.annotation.ContentView;
import bluemobi.iuv.base.utils.Logger;
import bluemobi.iuv.entity.CityBean;
import bluemobi.iuv.eventbus.BaseEvent;
import bluemobi.iuv.eventbus.CityEvent;
import bluemobi.iuv.eventbus.CountySpinnerBean;
import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.model.CityInfo;
import bluemobi.iuv.network.request.GetCitiesByCondRequest;
import bluemobi.iuv.network.response.GetCitiesByCondResponse;
import bluemobi.iuv.network.response.SearchCountyResponse;
import bluemobi.iuv.util.Utils;
import bluemobi.iuv.view.LoadingPage;
import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

@ContentView(R.layout.fragment_area)
public class AreaFragment extends BaseFragment {

    @Bind(R.id.gridview)
    protected GridView mGridView;
    private CommonAdapter<CityInfo> commonAdapter = null;

    private boolean isFromTreasure = false;

    protected  ArrayList<CityInfo> lists= new  ArrayList<CityInfo>();
    private String countryId;

    public AreaFragment()
    {
        EventBus.getDefault().register(this);
    }

    public void onEventMainThread(BaseEvent baseEvent) {

        if (baseEvent instanceof CountySpinnerBean)
        {
            CountySpinnerBean mCountySpinnerBean= (CountySpinnerBean) baseEvent;
            countryId=mCountySpinnerBean.getId();
            isFromTreasure = mCountySpinnerBean.isTreasure();
            Logger.e("AreaFragment--isFromTreasure-->",isFromTreasure);
        }


    }

    @Override
    protected IuwHttpJsonRequest initRequest() {
        GetCitiesByCondRequest request=new GetCitiesByCondRequest
                (
                        new Response.Listener<GetCitiesByCondResponse>() {
                            @Override
                            public void onResponse(GetCitiesByCondResponse response) {
                                Utils.closeDialog();
                                if(response!=null&&response.getStatus()==0) {
                                    lists.clear();
                                    lists.addAll(response.getData().getInfo());
                                    showData();
                                }
                            }
                        }, (Response.ErrorListener) getActivity());
        request.setPid(countryId);
        return request;
    }

    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity) mContext).setHomeBarSytle(((MainActivity) mContext).titleCity, true);
    }

    @Override
    protected void successViewCompleted(View successView) {
        ButterKnife.bind(this, successView);
    }


    @Override
    public void initData(Bundle savedInstanceState) {

        isShowLoadPage = false;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (((MainActivity) mContext).isHiddChange) {

            ((MainActivity) mContext).setHomeBarSytle(((MainActivity) mContext).titleCity, true);
        }

    }


    private void showData(){
        mGridView.setAdapter(commonAdapter = new CommonAdapter<CityInfo>(mContext, lists, R.layout.lv_search_item) {
            @Override
            public void convert(ViewHolder helper, CityInfo item) {
                TextView tv_city =(TextView) helper.getView(R.id.tv_city);
                tv_city.setText(item.getDivisionName());
                if (helper.getPosition() == 0)
                {
                    tv_city.setTextColor(getResources().getColor(R.color.white));
                    tv_city.setBackgroundResource(R.drawable.city_blue);


                } else
                {
                    tv_city.setTextColor(getResources().getColor(R.color.common_blue));
                    tv_city.setBackgroundResource(R.drawable.city_white);

                }

            }
        });
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Utils.moveToFragment(AreaSecondFragment.class, AreaFragment.this, "areaSecondFragment");
                String proId = lists.get(position).getId();
                String proName = lists.get(position).getDivisionName();
                String proDivisionCode =lists.get(position).getDivisionCode();
                String divisionType = lists.get(position).getDivisionType();
                Logger.e("onItemClick==divisionType==>",divisionType);
                CountySpinnerBean baseEvent = new CountySpinnerBean();
                baseEvent.setIsTreasure(isFromTreasure);
                baseEvent.setId(proId);
                baseEvent.setName(proName);
                baseEvent.setDivisionCode(proDivisionCode);
                baseEvent.setDivisionType(divisionType);
                EventBus.getDefault().post(baseEvent);
            }
        });
    }


}