package bluemobi.iuv.view;

/**
 * Created by liufy on 2015/6/28.
 */

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import bluemobi.iuv.R;
import bluemobi.iuv.fragment.BaseFragment;
import bluemobi.iuv.network.NetWorkResponseListener;
import bluemobi.iuv.util.UiUtils;
import butterknife.Bind;


/***
 * 创建了自定义帧布局  Loading 页面
 */
public abstract class LoadingPage extends FrameLayout implements NetWorkResponseListener {

    /**
     * 加载默认的状态
     */
    public static final int STATE_UNKOWN = 0;
    /**
     * 加载中状态
     */
    public static final int STATE_LOADING = 1;
    /**
     * 加载出现错误界面
     */
    public static final int STATE_ERROR = 2;
    /**
     * 加载数据为空界面
     */
    public static final int STATE_EMPTY = 3;
    /**
     * 加载成功
     */
    public static final int STATE_SUCCESS = 4;
    /**
     * 初始化默认状态
     */
    public int state = STATE_UNKOWN;
    /**
     * 加载中的界面
     */
    private View loadingView;
    /**
     * 错误界面
     */
    private View errorView;
    /**
     * 空界面
     */
    private View emptyView;
    /**
     * 加载成功的界面
     */
    private View successView;

    public LoadingPage(Context context) {
        super(context);
        init();
    }

    public LoadingPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public LoadingPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        loadingView = createLoadingView(); // 创建了加载中的界面
        if (loadingView != null) {
            this.addView(loadingView, new LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }
        errorView = createErrorView(); // 加载错误界面
        if (errorView != null) {
            this.addView(errorView, new LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }
        emptyView = createEmptyView(); // 加载空的界面
        if (emptyView != null) {
            this.addView(emptyView, new LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }

    }

    @Override
    public void processResponseStatus(String statusCode) {
        if ("0".equals(statusCode)) {
            state = LoadResult.success.getValue();
        } else if ("2".equals(statusCode)) {
            state = LoadResult.empty.getValue();
        } else {
            state = LoadResult.error.getValue();
        }
        mHandler.sendEmptyMessage(0);

    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            showPage(false);
        }
    };

    /**
     * 是否显示Loading界面
     *
     * @param isRelease true 显示 flase 不显示
     */
    public void show(final boolean isRelease) {
        if (state == STATE_ERROR || state == STATE_EMPTY || state == STATE_UNKOWN) {
            state = STATE_LOADING;
        }
        if (!isRelease) { // 传入 FALSE
            state = STATE_SUCCESS;
        }
        showPage(isRelease);

    }

    // 根据不同的状态显示不同的界面
    private void showPage(boolean isShow) {

        if (loadingView != null) {
            loadingView.setVisibility(state == STATE_UNKOWN
                    || state == STATE_LOADING ? View.VISIBLE : View.INVISIBLE);
        }
        if (!isShow) {
            loadingView.setVisibility(View.GONE);
        } else {
            loadingView.setVisibility(View.VISIBLE);
        }

        if (errorView != null) {

            errorView.setVisibility(state == STATE_ERROR ? View.VISIBLE
                    : View.INVISIBLE);
        }
        if (emptyView != null) {

            emptyView.setVisibility(state == STATE_EMPTY ? View.VISIBLE
                    : View.INVISIBLE);
        }
        if (state == STATE_SUCCESS) {

            if (successView == null) {
                successView = createSuccessView();
                successViewCompleted(successView);
                this.addView(successView, new LayoutParams(
                        LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            }
            successView.setVisibility(View.VISIBLE);
        } else {
            if (successView != null) {
                successView.setVisibility(View.INVISIBLE);
            }
        }
    }

    public enum LoadResult {
        error(2), empty(3), success(4);
        int value;

        LoadResult(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

    /**
     * 成功绑定View 方法   @Bind()
     *
     * @param successView
     */
    protected abstract void successViewCompleted(View successView);


    /***
     * 创建成功的界面
     *
     * @return
     */
    public abstract View createSuccessView();

    /* 创建了空的界面 */
    private View createEmptyView() {
        View view = View.inflate(UiUtils.getContext(), R.layout.loadpage_empty,
                null);
        return view;
    }

    /* 创建了错误界面 */
    private View createErrorView() {
        View view = View.inflate(UiUtils.getContext(), R.layout.loadpage_error,
                null);
        Button page_bt = (Button) view.findViewById(R.id.page_bt);
        page_bt.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                show(true);
            }
        });
        return view;
    }

    /* 创建加载中的界面 */
    private View createLoadingView() {
        View view = View.inflate(UiUtils.getContext(),
                R.layout.loadpage_loading, null);
        return view;
    }
}

