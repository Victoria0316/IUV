package bluemobi.iuv.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import bluemobi.iuv.R;
import bluemobi.iuv.eventbus.CountySpinnerBean;
import bluemobi.iuv.network.response.SearchCountyResponse;
import bluemobi.iuv.util.Constants;
import de.greenrobot.event.EventBus;

/**
 * 下拉列表
 */
public class CustomCountySpinnerSingle extends CustomCountySpinnerBase {
    private TextView tv_spinner;

    private ImageView iv_option;


    public CustomCountySpinnerSingle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomCountySpinnerSingle(Context context) {
        this(context, null);
    }

    public CustomCountySpinnerSingle(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    public String getText() {
        return tv_spinner.getText().toString().trim();
    }

    @Override
    public void initViews(Context context, AttributeSet attrs, int defStyle) {
        inflate = LayoutInflater.from(context).inflate(R.layout.spinner_item,
                this);
        tv_spinner = (TextView) findViewById(R.id.tv_spinner);
        iv_option = (ImageView) findViewById(R.id.iv_option);
        setBackgroundDrawable(background);
        tv_spinner.setText(lableText);
        iv_option.setOnClickListener(this);
        tv_spinner.setOnClickListener(this);

    }

    public void setCusomTextColor(int color) {
        tv_spinner.setTextColor(color);
    }

    @Override
    public void onLvItemClick(int postion) {
        //TODO
        spinnerText = datas.get(postion).divisionName;
        CountySpinnerBean mCountySpinnerBean= new CountySpinnerBean();
        mCountySpinnerBean.setEventType(Constants.SPINNER_TYPE_LV);
        mCountySpinnerBean.setName(datas.get(postion).divisionName);
        mCountySpinnerBean.setId(datas.get(postion).id);
        EventBus.getDefault().post(mCountySpinnerBean);

        if (mIsbehside)
            tv_spinner.setText(spinnerText);
    }

    @Override
    public void setDatas(List<SearchCountyResponse.SearchCountyData> datas) {
        super.setDatas(datas);

    }

    public void swithTabSelect()
    {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_option:
                showDataListDialog(datas, inflate);
                break;
            case R.id.tv_spinner:
                if (mTabListener!=null)
                {
                    mTabListener.onTabClick();
                }
                break;

            default:
                break;
        }
    }




}
