package bluemobi.iuv.app;

import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import bluemobi.iuv.R;
import bluemobi.iuv.base.BaseActivity;
import bluemobi.iuv.base.utils.Logger;
import bluemobi.iuv.callback.TitleBarCallBack;
import bluemobi.iuv.network.response.SearchCountyResponse;
import bluemobi.iuv.util.Utils;
import bluemobi.iuv.view.CustomCountySpinnerBase;
import bluemobi.iuv.view.CustomCountySpinnerSingle;


/**
 * Created by liufy on 2015/6/28.
 */
public class TitleBarManager implements View.OnClickListener,CustomCountySpinnerBase.OnCustomSpinnerItemClickListener{

    private  final java.lang.String TAG =this.getClass().getSimpleName() ;
    /**
     * 中间标题
     */
    private TextView tv_title;
    /**
     * 右边标题
     */
    private TextView tv_title_right;
    /**
     * 左边返回按钮
     */
    private ImageView iv_back;

    private BaseActivity activity;

    /**
     * 左边定位图标 地点
     */

    private ImageView iv_left;
    private TextView tv_title_left;


    private ActionBar mSupportActionBar;


    private ImageView iv_right;

    private ImageView iv_to_right;
    /**
     * 右侧图标带文字
     */
    private TextView tv_right;
    /**
     * 右侧左边图标
     */
    private TextView tv_right_left;

    private View il_search_bar;

    /**
     * 搜索bar 搜索框
     */
    private EditText et_search;

    /**
     * 搜索bar 右边按钮
     */
    private ImageView iv_search_icon;

    private ImageView iv_search_right;

    private  RelativeLayout rl_bar;


    private CustomCountySpinnerSingle custom_spinner;

    public final static int NO_TEXT = -1;

    private TitleBarCallBack mListener;

    private int rightType = -1;

    private int mapSytle = -1;

    /**
     * isTreasure true 代表 是P12宝藏的title, false 是检索
     */
    private boolean isTreasure = false;

    /**
     * 初始化 TitleView 中的控件
     *
     * @param activity
     */
    public void init(BaseActivity activity, ActionBar supportActionBar) {
        this.activity = activity;
        mListener = activity;
        initTitle(supportActionBar);
        supportActionBar.setCustomView(R.layout.include_titlebar);
        tv_title = (TextView) activity.findViewById(R.id.tv_title);
        tv_title_right = (TextView) activity.findViewById(R.id.tv_title_right);
        iv_back = (ImageView) activity.findViewById(R.id.iv_back);
        iv_right = (ImageView) activity.findViewById(R.id.iv_right);
        iv_to_right = (ImageView) activity.findViewById(R.id.iv_to_right);
        iv_left  = (ImageView) activity.findViewById(R.id.iv_left);
        tv_title_left  = (TextView) activity.findViewById(R.id.tv_title_left);
        il_search_bar = activity.findViewById(R.id.il_search_bar);

        custom_spinner = (CustomCountySpinnerSingle) activity.findViewById(R.id.custom_spinner);
        et_search = (EditText) activity.findViewById(R.id.et_search);
        iv_search_right = (ImageView) activity.findViewById(R.id.iv_search_right);//右边第一个
        iv_search_icon = (ImageView) activity.findViewById(R.id.iv_search_icon); //右边第二个

        rl_bar = (RelativeLayout) activity.findViewById(R.id.rl_bar);
        iv_left = (ImageView) activity.findViewById(R.id.iv_left);
        tv_title_left = (TextView) activity.findViewById(R.id.tv_title_left);
        tv_right = (TextView) activity.findViewById(R.id.tv_right);
        tv_right_left = (TextView) activity.findViewById(R.id.tv_right_left);
        setListener();
    }


    /**
     * 初始化titlebar
     */
    private void initTitle(ActionBar supportActionBar) {
        this.mSupportActionBar = supportActionBar;
        supportActionBar.setDisplayHomeAsUpEnabled(false);
        supportActionBar.setHomeButtonEnabled(true);
        supportActionBar.setDisplayShowHomeEnabled(false);
        supportActionBar.setDisplayShowCustomEnabled(true);

    }

    public int getBarPaddingleft()
    {
        rl_bar.measure(0, 0);
        int measuredWidth = rl_bar.getMeasuredWidth();
        int i = Utils.getDeviceWidth() - measuredWidth;
        return  i;
    }


    /**
     * 添加监听器
     */
    private void setListener() {
        iv_back.setOnClickListener(this);
        tv_title_right.setOnClickListener(this);
        iv_left.setOnClickListener(this);
        iv_right.setOnClickListener(this);
        il_search_bar.setOnClickListener(this);
        tv_right.setOnClickListener(this);
        tv_right_left.setOnClickListener(this);
        iv_search_icon.setOnClickListener(this);
        iv_search_right.setOnClickListener(this);
        iv_to_right.setOnClickListener(this);
        custom_spinner.setOnCustomClickListener(this);

    }

    public ImageView getSearchIconView()
    {
        return iv_search_icon;
    }

    public EditText getEtView()
    {
        return et_search;
    }

    public TextView getTitleView()
    {
        return tv_title;
    }


    /**
     * 显示最普通的Titlebar 左边按钮，中间文字
     *
     * @param textID     中间的文字
     * @param leftSrcID
     * @param isShowBack 是否显示回退按钮 true 显示
     */
    public void showCommonTitleBar(int textID, int leftSrcID, boolean isShowBack) {
        il_search_bar.setVisibility(View.GONE);
        tv_title.setVisibility(View.VISIBLE);

        if (textID!=0)
        tv_title.setText(textID);
        if (isShowBack) {
            iv_back.setVisibility(View.VISIBLE);
            iv_back.setImageResource(leftSrcID);

        } else {
            iv_back.setVisibility(View.GONE);
        }

        tv_title_right.setVisibility(View.GONE);
    }

    /**
     * Titlebar 左边按钮，文字（定位）
     *
     * @param textID     中间的文字
     * @param leftSrcID
     *
     */
    public void showPositionTitleBar(int positionID, int textID, int leftSrcID) {
        il_search_bar.setVisibility(View.GONE);
        tv_title_left.setVisibility(View.VISIBLE);
        tv_title.setVisibility(View.VISIBLE);
        iv_left.setVisibility(View.VISIBLE);
        tv_title_right.setVisibility(View.INVISIBLE);
        iv_back.setVisibility(View.INVISIBLE);
        tv_title_left.setText(positionID);
        tv_title.setText(textID);
        iv_left.setImageResource(leftSrcID);

    }


    /**
     * 显示最普通的Titlebar 左边按钮，中间文字，右边文字
     */
    public void showTitleTextBar(int textID, int leftSrcID, int rightTextID) {
        iv_right.setVisibility(View.GONE);
        il_search_bar.setVisibility(View.GONE);
        tv_title.setVisibility(View.VISIBLE);
        iv_back.setVisibility(View.VISIBLE);
        tv_title.setText(textID);
        iv_back.setImageResource(leftSrcID);
        tv_title_right.setVisibility(View.VISIBLE);
        tv_title_right.setText(rightTextID);
    }

    /**
     * 右侧两图标 最右侧带文字
     *
     * @param textID         标题
     * @param leftSrcID
     * @param rightText
     * @param rightResID
     * @param rightLeftResID
     */
    public void showTitleTextBar(int textID, int leftSrcID, String rightText, int rightResID, int rightLeftResID) {


        tv_right.setVisibility(View.GONE);
        tv_right_left.setVisibility(View.GONE);

        iv_left.setVisibility(View.GONE);
        il_search_bar.setVisibility(View.GONE);
        iv_to_right.setVisibility(View.GONE);
        iv_search_icon.setVisibility(View.GONE);
        iv_search_right.setVisibility(View.GONE);
        iv_right.setVisibility(View.GONE);

        tv_title.setVisibility(View.VISIBLE);
        iv_back.setVisibility(View.VISIBLE);
        tv_title.setText(textID);
        iv_back.setImageResource(leftSrcID);
        tv_right.setVisibility(View.VISIBLE);
        if (rightResID != NO_TEXT) {
            Drawable leftDrawable = activity.getResources().getDrawable(rightResID);
            leftDrawable.setBounds(0, 0,
                    leftDrawable.getMinimumWidth(),
                    leftDrawable.getMinimumWidth());
            tv_right.setCompoundDrawables(leftDrawable, null, null, null);
        }
        tv_right.setText(rightText);

        tv_right_left.setVisibility(View.VISIBLE);
        tv_right_left.setBackgroundResource(rightLeftResID);
    }


    /**
     * 显示主页标题
     * @param leftSrcID  左边的图片资源
     * @param rightsrcID 右边的图片资源
     */
    public void showHomeTitleBar(int leftSrcID, int rightsrcID) {

        tv_right.setVisibility(View.GONE);
        tv_right_left.setVisibility(View.GONE);

        il_search_bar.setVisibility(View.GONE);
        tv_right_left.setVisibility(View.GONE);
        tv_title_right.setVisibility(View.GONE);
        iv_to_right.setVisibility(View.GONE);

        iv_back.setVisibility(View.GONE);
        tv_title.setVisibility(View.GONE);
        iv_left.setVisibility(View.VISIBLE);
        iv_left.setImageResource(leftSrcID);
        iv_right.setVisibility(View.VISIBLE);
        iv_right.setImageResource(rightsrcID);

    }


    public void showDetailTitleBar(String text,int leftSrcID, int toRightSrcID,int rightsrcID) {

        il_search_bar.setVisibility(View.GONE);
        tv_title.setVisibility(View.VISIBLE);
        tv_title.setText(text);
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(leftSrcID);
        iv_left.setVisibility(View.GONE);
        iv_right.setVisibility(View.VISIBLE);
        iv_to_right.setVisibility(View.VISIBLE);
        iv_to_right.setImageResource(toRightSrcID);
        iv_right.setImageResource(rightsrcID);

    }

    public void showDetailTitleBar(String text,int leftSrcID, int toRightSrcID,int rightsrcID,int mapSytle) {
        this.mapSytle = mapSytle;
        il_search_bar.setVisibility(View.GONE);

        tv_title.setVisibility(View.VISIBLE);
        tv_title.setText(text);
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(leftSrcID);
        iv_left.setVisibility(View.GONE);
        iv_right.setVisibility(View.VISIBLE);
        iv_to_right.setVisibility(View.VISIBLE);
        iv_to_right.setImageResource(toRightSrcID);
        iv_right.setImageResource(rightsrcID);

    }


    /**
     * 显示我的界面
     * @param text
     * @param leftSrcID
     * @param rightsrcID
     */
    public void showLRSrcTitleBar(String text,int leftSrcID, int rightsrcID,boolean isShowBack) {
        il_search_bar.setVisibility(View.GONE);
        tv_right.setVisibility(View.GONE);
        iv_to_right.setVisibility(View.GONE);
        iv_search_right.setVisibility(View.GONE);
        iv_search_icon.setVisibility(View.GONE);
        iv_left.setVisibility(View.GONE);
        tv_right_left.setVisibility(View.GONE);

        tv_title.setVisibility(View.VISIBLE);
        tv_title.setText(text);
        if (isShowBack) {
            iv_back.setVisibility(View.VISIBLE);
            iv_back.setImageResource(leftSrcID);
        } else {
            iv_back.setVisibility(View.GONE);
        }

        iv_right.setVisibility(View.VISIBLE);
        iv_right.setImageResource(rightsrcID);

    }

    /**
     * 显示我的界面
     * @param text
     * @param leftSrcID
     * @param rightsrcID
     */
    public void showLRSrcTitleBar(String text,int leftSrcID, int rightsrcID,boolean isShowBack,int type) {
        this.rightType = type;
        tv_title_right.setVisibility(View.GONE);
        il_search_bar.setVisibility(View.GONE);
        tv_right.setVisibility(View.GONE);
        iv_to_right.setVisibility(View.GONE);
        iv_search_right.setVisibility(View.GONE);
        iv_search_icon.setVisibility(View.GONE);
        iv_left.setVisibility(View.GONE);
        tv_right_left.setVisibility(View.GONE);

        tv_title.setVisibility(View.VISIBLE);
        tv_title.setText(text);
        if (isShowBack) {
            iv_back.setVisibility(View.VISIBLE);
            iv_back.setImageResource(leftSrcID);
        } else {
            iv_back.setVisibility(View.GONE);
        }

        iv_right.setVisibility(View.VISIBLE);
        iv_right.setImageResource(rightsrcID);

    }

    /**
     *  主页的titlebar 带搜索
     *  true 代表P12宝藏页面
     */
    public void showSearchTitleBar(String hint,int rightsrcID,int rightsrcIDTwo,boolean isShowMapIcon) {

        ArrayList<SearchCountyResponse.SearchCountyData> searchCountyDatas = IuwApplication.getInstance().getmCountyData();
        if (searchCountyDatas!=null)
        {
            custom_spinner.setDatas(searchCountyDatas);
        }
        iv_to_right.setVisibility(View.GONE);
        iv_right.setVisibility(View.GONE);
        iv_back.setVisibility(View.GONE);
        tv_title.setVisibility(View.GONE);
        tv_title_right.setVisibility(View.GONE);
        iv_left.setVisibility(View.GONE);
        tv_title_left.setVisibility(View.GONE);
        tv_right.setVisibility(View.GONE);
        tv_right_left.setVisibility(View.GONE);

        iv_search_right.setVisibility(View.VISIBLE);
        iv_search_icon.setVisibility(View.VISIBLE);
        et_search.setHint(hint);
        il_search_bar.setVisibility(View.VISIBLE);


        if (isShowMapIcon)
        {
            isTreasure = true;
            et_search.setOnClickListener(this);
            et_search.setFocusable(false);
            iv_search_right.setVisibility(View.VISIBLE); //第一个图片 true 代码检索按钮
            iv_search_right.setImageResource(rightsrcID);
        }
        else
        {
            isTreasure = false;
            et_search.setFocusable(true);
            il_search_bar.setClickable(false);
            iv_search_right.setVisibility(View.GONE);
        }

        iv_search_icon.setImageResource(rightsrcIDTwo);//第二个 true代表 地图


    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_back:
                onClickBarleft();
                break;
            case R.id.tv_title_right:
                onClickBarRight();
                break;

            case R.id.iv_right:
                onClickImageRight();
                break;
            case R.id.iv_left:
                onClickImageLeft();
                break;
            case R.id.tv_right:
                clickTextRight();
                break;
            case R.id.tv_right_left:
                clickTextRightLeft();
                break;

            case R.id.iv_search_icon:
                if (isTreasure)
                {
                    onClickDetailRightMap();

                }else
                {
                    onClickRightMapSearch();
                }

                break;
            case R.id.iv_search_right:
                if (isTreasure)
                    onClickRightMapSearch();
                break;

            case R.id.iv_to_right:
                onClickDetailRightMap();
                break;

            case R.id.il_search_bar:
            case R.id.et_search:
                onClickSearchBar();
                break;


        }
    }


    /**
     * 点击右边的搜索按钮
     */
    public void onClickSearchBar() {
        mListener.clickSearchBar();
    }

    /**
     * 点击右边的搜索按钮
     */
    public void onClickBarRightSearch() {
        mListener.clickBarRightSearch();
    }

    /**
     * 某地宝藏点击地图按钮
     */
    public void onClickDetailRightMap() {

        if (mapSytle==1)
        {
            mListener.clickRightNaviSearch();
        }
        else {
            mListener.clickDetailRightMap();
        }
    }

    /**
     * 点击右边地图
     */
    public void onClickRightMapSearch() {
        mListener.clickRightMapSearch();
    }

    public void onClickImageRight() {

        if (rightType==0)
        {

        }
        else if (rightType==2) //点评
        {
            mListener.clickCommentRight();
        }
        else
        {
            mListener.clickImageRight();
        }

    }

    public void onClickImageLeft() {

        mListener.clickImageLeft();
    }
    /**
     * 点击右边的bar按钮
     */
    public void onClickBarRight() {
        mListener.clickBarRight();
    }

    /**
     * 点击左边的bar按钮
     */
    public void onClickBarleft() {


        mListener.clickBarleft();
    }




    /**
     * 点击中间搜索的bar按钮
     */
    public void onClickBarSearch() {
        mListener.clickBarSearch();
    }

    /**
     * 点击右边按钮
     *
     */
    public void onClickBarHint() {
        mListener.clickBarHint();
    }



    /**
     * 右侧带文字
     */
    public void clickTextRight() {
        mListener.clickTextRight();
    }

    /**
     * 右侧左边
     */
    public void clickTextRightLeft() {
        mListener.clickTextRightLeft();
    }

    public void setmListener(TitleBarCallBack mListener) {
        this.mListener = mListener;
    }

    @Override
    public void onCustomSpinnerClick(String option) {

    }
}
