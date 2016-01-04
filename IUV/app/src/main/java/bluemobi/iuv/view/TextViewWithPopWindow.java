package bluemobi.iuv.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bluemobi.iuv.R;

/**
 * Created by liufy on 2015/8/8.
 */
public class TextViewWithPopWindow extends TextView implements View.OnClickListener,AdapterView.OnItemClickListener
{
    public PopupWindow pw;

    public Context mContext = null;

    public List<String> datas;

    private View downView;

    public TextViewWithPopWindow(Context context) {
        this(context,null);
    }

    public TextViewWithPopWindow(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextViewWithPopWindow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext  = context;
        setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        showDataListDialog(datas,downView);
    }

    public void setShowDownView(View view)
    {
        downView = view;
    }

    public void showDataListDialog(List<String> datas, View view)
    {
        ListView lv = initListView();
        initPopWindow(datas, view, lv, view.getWidth());

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
     */
    public void initPopWindow(List<String> datas, View view,
                              ListView lv, int viewWidth)
    {

        pw = new PopupWindow(lv, viewWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        pw.setFocusable(true);
        pw.setOutsideTouchable(true);
        pw.setBackgroundDrawable(new BitmapDrawable());
        pw.showAsDropDown(view, 0, 0);

    }

    /**
     * 显示下拉数据
     * @param
     */



    public void initPopWindow(List<String> datas, View editView,
                              ListView lv,View arrow)
    {
        int width = editView.getWidth() + arrow.getWidth();
        initPopWindow(datas, editView, lv, width);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int postion,
                            long id)
    {
        if (pw != null && pw.isShowing())
        {
            pw.dismiss();
        }
        if (mSpinnerListener!=null)
        {
            mSpinnerListener.onSpinnerTextClick(postion);
        }

    }

    public OnSpinnerTextClickListener mSpinnerListener;

    public interface OnSpinnerTextClickListener
    {
        public void onSpinnerTextClick(int postion);
    }

    public void setOnSpinnerTextClickListener(
            OnSpinnerTextClickListener listener)
    {
        mSpinnerListener = listener;
    }


    public void setDatas(List<String> datas)
    {
        if (datas == null)
        {
            datas = new ArrayList<String>();
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
        private List<String> datas = new ArrayList<String>();

        public MySpinnerApapter(List<String> datas)
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
            tv_number_item.setText(datas.get(position));
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

}
