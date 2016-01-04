package bluemobi.iuv.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import bluemobi.iuv.R;
import bluemobi.iuv.activity.MainActivity;
import bluemobi.iuv.adapter.CommonAdapter;
import bluemobi.iuv.adapter.ViewHolder;
import bluemobi.iuv.annotation.ContentView;
import bluemobi.iuv.base.utils.Logger;
import bluemobi.iuv.eventbus.BaseEvent;
import bluemobi.iuv.eventbus.CountyInfoEvent;
import bluemobi.iuv.eventbus.LargeDeptEvent;
import bluemobi.iuv.network.response.SearchCountyResponse;
import bluemobi.iuv.util.Constants;
import bluemobi.iuv.util.Utils;
import bluemobi.iuv.view.PicPopupWindow;
import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by liufy on 2015/8/4.
 * P8_搜索页面 modify
 */
@ContentView(R.layout.fragment_search)
public class HomeSearchFragment extends BaseFragment {

    @Bind(R.id.gridview)
    protected GridView mGridView;
    @Bind(R.id.main)
    protected RelativeLayout rl_main;
    protected PicPopupWindow pw;
    private CommonAdapter<SearchCountyResponse.SearchCountyData> mAdapter;
    private ArrayList<SearchCountyResponse.SearchCountyData> data;
    private android.app.AlertDialog dialog;
    private MyCount myCount = null;
    private int index[] = new int[1];

    public HomeSearchFragment() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        isShowLoadPage = false;
    }

    public void dialogDismass(TextView tv, String temp) {
        int size = temp.length();
        String text = String.format(getResources().getString(R.string.s_disappear), temp);
        index[0] = text.indexOf(temp);
        SpannableStringBuilder style = new SpannableStringBuilder(text);
        style.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.common_blue)), index[0], index[0] + size, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        tv.setText(style);
    }

    class MyCount extends CountDownTimer {
        private TextView mTv;

        public MyCount(long millisInFuture, long countDownInterval, TextView tv) {
            super(millisInFuture, countDownInterval);
            this.mTv = tv;
        }

        @Override
        public void onFinish() {
            if (dialog != null)
                dialog.dismiss();
            dialog = null;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            dialogDismass(mTv, String.valueOf(millisUntilFinished / 1000));
        }
    }

    /**
     * 相应搜索按钮的点击事件
     *
     * @param baseEvent
     */
    public void onEventMainThread(BaseEvent baseEvent) {

        if (baseEvent.getEventType() != Constants.COUNTYINFOEVENT_TYPE) {
            return;
        }
        CountyInfoEvent cityEvent = (CountyInfoEvent) baseEvent;
        data = cityEvent.getData();
        if (data == null || data.size() == 0) {
            searchFailure();
            return;
        }
        mGridView.setAdapter(mAdapter = new CommonAdapter<SearchCountyResponse.SearchCountyData>(mContext, data, R.layout.lv_search_item) {
            @Override
            public void convert(ViewHolder helper, SearchCountyResponse.SearchCountyData item) {
                helper.setText(R.id.tv_city, item.divisionName);
            }
        });

    }

    private void searchFailure() {
        //检索失败 提示
        if (dialog == null) {
            dialog = new android.app.AlertDialog.Builder(mContext)
                    .create();
            view = LayoutInflater.from(mContext).inflate(R.layout.dialog_search_empty, null);
            TextView tv_sure = (TextView) view.findViewById(R.id.tv_sure);
            Window window = dialog.getWindow();
            window.setGravity(Gravity.CENTER);  //此处可以设置dialog显示的位置
            dialog.show();
            dialog.setContentView(view);
            myCount = new MyCount(4000, 1000, tv_sure);
            myCount.start();

        } else {
            dialog.show();
        }

       /* if (myCount != null) {
            myCount.cancel();
        }*/

    }

    @Override
    protected void successViewCompleted(View successView) {
        ButterKnife.bind(this, successView);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((MainActivity)mContext).isBaseFragment = false;
                ((MainActivity) mContext).clearSelection();
                Utils.moveToFragment(DetailFragment.class, HomeSearchFragment.this, "detailFragment");
                LargeDeptEvent largeDeptEvent = new LargeDeptEvent();
                String str = data.get(position).divisionName;
                largeDeptEvent.setDivisionCode(data.get(position).divisionCode);
                largeDeptEvent.setDivisionType(data.get(position).divisionType);
                Logger.e("HomeSearchFragment divisionCode:", data.get(position).divisionCode);
                largeDeptEvent.setDivisionName(str);
                EventBus.getDefault().post(largeDeptEvent);
            }
        });
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        ((MainActivity)mContext).isBaseFragment = true;
        if (((MainActivity) mContext).isHiddChange) {
            ((MainActivity) mContext).setSearchBarSytle(getResources().getString(R.string.search_hint));
        }
    }


}
