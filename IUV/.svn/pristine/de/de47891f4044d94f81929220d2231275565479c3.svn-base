package bluemobi.iuv.view;

import java.util.List;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import bluemobi.iuv.R;

/**
 * 下拉列表
 */
public class CustomSpinnerSingle extends CustomSpinnerBase {
    private TextView tv_spinner;

    private ImageView iv_option;

    public CustomSpinnerSingle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomSpinnerSingle(Context context) {
        this(context, null);
    }

    public CustomSpinnerSingle(Context context, AttributeSet attrs, int defStyle) {
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
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mTabListener != null) {
                    mTabListener.onTabClick(v);
                }

            }
        });

    }

    public void setCusomTextColor(int color) {
        tv_spinner.setTextColor(color);
    }

    @Override
    public void onLvItemClick(int postion) {
        spinnerText = datas.get(postion);
        tv_spinner.setText(spinnerText);
        if (mIsbehside)
            tv_spinner.setText(spinnerText);
        if (mSpinnerListener != null) {
            mSpinnerListener.OnSnipperClick(postion,this);
        }
    }

    @Override
    public void setDatas(List<String> datas) {
        super.setDatas(datas);

    }

    public void swithTabSelect() {

    }

    public void setSpinnerText() {
        if (datas != null && datas.size() > 0)
            tv_spinner.setText(datas.get(0));
    }

    public void setCustomSpinnerText(String text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        tv_spinner.setText(text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_option:
                showDataListDialog(datas, inflate);
                break;

            default:
                break;
        }
    }


}
