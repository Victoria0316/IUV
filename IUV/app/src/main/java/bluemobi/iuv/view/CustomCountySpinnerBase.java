package bluemobi.iuv.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bluemobi.iuv.R;
import bluemobi.iuv.network.response.SearchCountyResponse;

/**
 * 
 * @author liufy
 *
 */
public abstract class CustomCountySpinnerBase extends RelativeLayout implements
        View.OnClickListener, OnItemClickListener
{

    public View inflate;

    public PopupWindow pw;

    public Context mContext = null;


    /**
     * 下拉数据
     */
    public List<SearchCountyResponse.SearchCountyData> datas;

    /**
     * spinner 上显示的数据
     */
    public String spinnerText;

    /**
     * 输入项 提示
     */
    public CharSequence hintText;

    /**
     *  输入项标签
     */
    public CharSequence lableText;

    /**
     * ViewGroup 背景
     */
    public Drawable background;

    public boolean mIsbehside = true;


    private View mShowView;


    public OnCustomSpinnerItemClickListener mListener;

    public interface OnCustomSpinnerItemClickListener
    {
        public void onCustomSpinnerClick(String option);
    }

    public void setOnCustomClickListener(
            OnCustomSpinnerItemClickListener listener)
    {
        mListener = listener;
    }

    public CustomCountySpinnerBase(Context context)
    {
        this(context, null);
    }

    public CustomCountySpinnerBase(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public CustomCountySpinnerBase(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.SpinnerText, 0, defStyle);
        hintText = a.getText(R.styleable.SpinnerText_android_hint);
        lableText = a.getText(R.styleable.SpinnerText_android_text);
        background = a.getDrawable(R.styleable.SpinnerText_android_background);
        a.recycle();
        initViews(context, attrs, defStyle);
    }

    /**
     * 初始化 View
     * @param context  上下文
     * @param attrs    属性 
     * @param defStyle 默认
     */
    public abstract void initViews(Context context, AttributeSet attrs,
            int defStyle);

    /**
     *  下拉选择ListView 单击事件
     * @param postion
     */
    public abstract void onLvItemClick(int postion);


    public void showDataListDialog(List<SearchCountyResponse.SearchCountyData> datas, View view,View arrow)
    {
        ListView lv = initListView();
        initPopWindow(datas, view, lv, arrow);

    }
    
    
    /**
     * 显示下拉数据
     * @param
     */
    public void showDataListDialog(List<SearchCountyResponse.SearchCountyData> datas, View view)
    {
        ListView lv = initListView();
        initPopWindow(datas, view, lv, view.getWidth(),mIsbehside);

    }

    public void setIsBeside(boolean isbeside)
    {
        mIsbehside =isbeside ;
    }

    /**
     * 初始化下拉ListView
     * @return
     */
    private ListView initListView()
    {
        ListView lv = new ListView(mContext);
        lv.setVerticalScrollBarEnabled(false);
        lv.setDivider(null);
        lv.setDividerHeight(0);
        lv.setCacheColorHint(000000);
        lv.setOnItemClickListener(this);
        if (datas != null)
        {
            MySpinnerApapter mAdapter = new MySpinnerApapter(datas);
            lv.setAdapter(mAdapter);
        }

        return lv;
    }

    /**
     * 初始化PopWindow
     * @param datas  数据
     * @param view   哪个控件下面显示
     * @param lv     下拉显示为listview
     * @param viewWidth popwindow 宽度
     * @param isBeside  是否靠边
     */
    public void initPopWindow(List<SearchCountyResponse.SearchCountyData> datas, View view,
            ListView lv, int viewWidth,boolean isBeside)
    {
        int besidelen ;
        if (isBeside)
        {
            besidelen = 50;
        }else
        {
            besidelen = 0;
        }


        pw = new PopupWindow(lv, viewWidth+besidelen, ViewGroup.LayoutParams.WRAP_CONTENT);
        pw.setFocusable(true);
        pw.setOutsideTouchable(true);
        pw.setBackgroundDrawable(new BitmapDrawable());
        if (isBeside)
        {
            pw.showAsDropDown(view, -besidelen, 0);
        }
        else
        {
            pw.showAsDropDown(mShowView, 0, 0);
        }

    }

    public void setShowDownView(View downView)
    {
          this.mShowView = downView;

    }


    public void initPopWindow(List<SearchCountyResponse.SearchCountyData> datas, View editView,
             ListView lv,View arrow)
    {
        int width = editView.getWidth() + arrow.getWidth();
        initPopWindow(datas, editView, lv, width,mIsbehside);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int postion,
            long id)
    {
        onLvItemClick(postion);
        if (pw != null && pw.isShowing())
        {
            pw.dismiss();
        }
    }




    public String getSpinnerText()
    {
        return spinnerText != null ? spinnerText : "";
    }

    public void setDatas(List<SearchCountyResponse.SearchCountyData> datas)
    {
        if (datas == null)
        {
            datas = new ArrayList<SearchCountyResponse.SearchCountyData>();
        }
        this.datas = datas;

    }

    /**
     * 下拉选择框适配器
     * @author liufy
     *
     */
    public class MySpinnerApapter extends BaseAdapter
    {
        private List<SearchCountyResponse.SearchCountyData> datas = new ArrayList<SearchCountyResponse.SearchCountyData>();

        public MySpinnerApapter(List<SearchCountyResponse.SearchCountyData> datas)
        {
            this.datas = datas;
        }

        @Override
        public int getCount()
        {
            return datas.size();
        }

        @Override
        public View getView(final int position, View convertView,
                ViewGroup parent)
        {
            View view = null;
            if (convertView == null)
            {
                view = View.inflate(mContext, R.layout.spinner_option_item,
                        null);
            }
            else
            {
                view = convertView;
            }
            TextView tv_number_item = (TextView) view
                    .findViewById(R.id.tv_number_item);
            tv_number_item.setText(datas.get(position).divisionName);
            LinearLayout ll_item = (LinearLayout) view.findViewById(R.id.ll_item);
            if (position == getCount()-1)
            {
                ll_item.setBackgroundResource(R.drawable.lv_item_no_line_bg);
            }
            else
            {ll_item.setBackgroundResource(R.drawable.lv_item_with_line_bg);

            }
            return view;
        }

        @Override
        public Object getItem(int position)
        {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }
    }




    public OnTabClickListener mTabListener;

    public interface OnTabClickListener
    {
        public void onTabClick();
    }

    public void setOnTabClickListener(
            OnTabClickListener listener)
    {
        mTabListener = listener;
    }
}
