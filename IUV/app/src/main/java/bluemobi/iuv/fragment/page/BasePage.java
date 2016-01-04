package bluemobi.iuv.fragment.page;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import bluemobi.iuv.activity.LoginActivity;
import bluemobi.iuv.app.IuwApplication;
import bluemobi.iuv.fragment.BaseFragment;
import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.exception.CustomResponseError;
import bluemobi.iuv.network.exception.TokenInvalid;
import bluemobi.iuv.network.request.CollectShopRequest;
import bluemobi.iuv.network.request.GivePraiseRequest;
import bluemobi.iuv.network.response.GivePraiseResponse;
import bluemobi.iuv.util.Constants;
import bluemobi.iuv.util.Utils;
import bluemobi.iuv.util.ViewUtils;
import bluemobi.iuv.util.WebUtils;
import bluemobi.iuv.view.IuwAlertDialog;
import bluemobi.iuv.view.LoadingPage;

/**
 * liufengyu
 *
 * @author Administrator
 */
public abstract class BasePage implements PullToRefreshListView.OnRefreshListener2,Response.ErrorListener{
    public Context pageContext = null;

    private IuwAlertDialog mDialog;

    public View view = null;
    protected boolean hasLoaded;

    public BaseFragment baseFragment;

    protected LoadingPage loadingPage;

    public boolean isShowLoadPage = true;

    public LayoutInflater inflater;

    public PullToRefreshListView plv_refresh = null;
    public static final int LOAD_MORE = 1;
    public static final int LOAD_REFRESH = 2;
    public int NUMBER_PER_PAGE = Constants.CURRENTNUMBASE;// 每页条数
    public int curPage = -1;// 当前页
    protected long pageTime = 0;
    protected int currentPage = 0;
    private boolean needReload;

    private IuwHttpJsonRequest request;

    public BasePage(Context context, BaseFragment baseFragment) {

        this.pageContext = context;
        this.baseFragment = baseFragment;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        initData();
        loadingPage = (LoadingPage) initBaseView(inflater);
        show();

    }


    /**
     * 初始化请求前传递数据
     */
    public void transData()
    {

    }

    /**
     * 发起Loading页网络请求
     */
    public void loadRequset()
    {
        request = initRequest();
        invokeRequest();
    }

    public void show() {
        if (loadingPage != null) {
            loadingPage.show(isShowLoadPage);
        }
    }

    protected IuwHttpJsonRequest initRequest() {
        return null;
    }

    private void invokeRequest() {
        if(request == null){
            return;
        }else{
            request.setNetWorkResponseListener(loadingPage);
            pageTime = System.currentTimeMillis();
            IuwApplication.getInstance().setPageTime(pageTime);
            WebUtils.doPost(request);
        }
    }

    protected View initBaseView(final LayoutInflater inflater) {
        if (loadingPage == null) {
            loadingPage = new LoadingPage(pageContext) {

                @Override
                protected void successViewCompleted(View successView) {
                    BasePage.this.successViewCompleted(successView);
                }

                @Override
                public View createSuccessView() {
                    return initView(inflater);
                }

            };
        } else {
            ViewUtils.removeParent(loadingPage);
        }
        return loadingPage;
    }


    public abstract void initData();

    public abstract View initView(LayoutInflater inflater);

    protected abstract void successViewCompleted(View successView);

    public View getRootView() {
        return loadingPage;
    }

    public void initPullToRefresh(PullToRefreshListView pullToRefresh) {
        this.plv_refresh = pullToRefresh;
        pullToRefresh.setOnRefreshListener(this);
        pullToRefresh.setMode(PullToRefreshBase.Mode.BOTH);
        ILoadingLayout startLabels = pullToRefresh
                .getLoadingLayoutProxy(true, false);
        startLabels.setPullLabel("下拉刷新...");
        startLabels.setRefreshingLabel("正在载入...");
        startLabels.setReleaseLabel("放开刷新...");
        ILoadingLayout endLabels = pullToRefresh.getLoadingLayoutProxy(
                false, true);
        endLabels.setPullLabel("上拉刷新...");
        endLabels.setRefreshingLabel("正在载入...");
        endLabels.setReleaseLabel("放开刷新...");
    }
    @Override
    public void onPullDownToRefresh(PullToRefreshBase pullToRefreshBase) {
        getPage(LOAD_REFRESH);
    }
    @Override
    public void onPullUpToRefresh(PullToRefreshBase pullToRefreshBase) {
        getPage(LOAD_MORE);
    }
    protected boolean getPage(int type) {
        boolean ret = true;
        switch (type) {
            case LOAD_MORE: {
                int count = plv_refresh.getRefreshableView().getAdapter().getCount();
                int i = count - 2;
                pageTime = System.currentTimeMillis();
                IuwApplication.getInstance().setPageTime(pageTime);
                if (i % NUMBER_PER_PAGE == 0) {
                    curPage = i / NUMBER_PER_PAGE;

                } else {
                    ret = false;
                    plv_refresh.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            plv_refresh.onRefreshComplete();
                            Utils.makeToastAndShow(pageContext, "已经没有更多记录", Toast.LENGTH_SHORT);
                        }
                    }, 2000);
                }
            }
            break;
            case LOAD_REFRESH:
                pageTime = System.currentTimeMillis();
                IuwApplication.getInstance().setPageTime(pageTime);
                curPage = 0;
                break;
        }

        return ret;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public long getPageTime() {
        return pageTime;
    }


    public interface RefreshPraiseListener {

        public void refreshView(int pos);
    }

    public void givePraise(String shopId,final int p,final RefreshPraiseListener r){
        if (IuwApplication.getInstance().getMyUserInfo()==null)
        {
            Utils.moveTo(pageContext, LoginActivity.class);
            this.baseFragment.getActivity().finish();
            return;
        }
        GivePraiseRequest request = new GivePraiseRequest(
                new Response.Listener<GivePraiseResponse>() {
                    @Override
                    public void onResponse(GivePraiseResponse response) {
                        Utils.closeDialog();
                        if (response != null && response.getStatus() == 0) {
                            r.refreshView(p);
                        }
                    }
                }, (Response.ErrorListener) pageContext);
        request.setId(shopId);
        request.setUserId(IuwApplication.getInstance().getMyUserInfo().getUserId());
        Utils.showProgressDialog(pageContext);
        WebUtils.doPost(request);
    }
    public interface RefreshCollectListener {

        public void refreshView(int pos);
    }

    public void collect(String shopId,final int p,final RefreshCollectListener r){
        if (IuwApplication.getInstance().getMyUserInfo()==null)
        {
            Utils.moveTo(pageContext, LoginActivity.class);
            this.baseFragment.getActivity().finish();
            return;
        }
        CollectShopRequest request = new CollectShopRequest(
                new Response.Listener<GivePraiseResponse>() {
                    @Override
                    public void onResponse(GivePraiseResponse response) {
                        Utils.closeDialog();
                        if (response != null && response.getStatus() == 0) {
                            r.refreshView(p);
                        }
                    }
                }, (Response.ErrorListener) pageContext);
        request.setId(shopId);
        request.setUserId(IuwApplication.getInstance().getMyUserInfo().getUserId());
        Utils.showProgressDialog(pageContext);
        WebUtils.doPost(request);
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        requestError(error);
    }

    public void requestError(VolleyError error) {
        if (plv_refresh != null) {
            plv_refresh.onRefreshComplete();
        }
        Utils.closeDialog();
        if (error instanceof TimeoutError) {
            Utils.makeToastAndShow(pageContext, "网络异常,请检查您的网络", Toast.LENGTH_SHORT);
        } else if (error instanceof ParseError) {
            Utils.makeToastAndShow(pageContext, "解析出错", Toast.LENGTH_SHORT);
        } else if (error instanceof TokenInvalid) {
            if (mDialog == null || !mDialog.isShowing()) {
                mDialog = new IuwAlertDialog(pageContext);
                mDialog.setTitle("提示")
                        .setMessage("用户登录已失效,请重新登录")
                        .setNegativeButtonClickListener("取消",
                                new View.OnClickListener() {

                                    @Override
                                    public void onClick(View v) {
                                        mDialog.dismiss();
                                    }
                                })
                        .setPositiveButtonClickListener("确定",
                                new View.OnClickListener() {

                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent();
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.setClass(pageContext,
                                                LoginActivity.class);
                                        intent.putExtra("tokeninvalid", true);
                                        pageContext.startActivity(intent);
                                        mDialog.dismiss();
                                    }
                                });
                mDialog.show();
            }

        } else if (error instanceof CustomResponseError) {
            CustomResponseError crs = (CustomResponseError) error;
            requestCustomErr(crs);
        } else if (error instanceof NoConnectionError) {//无网络连接
            Utils.makeToastAndShow(pageContext, "网络异常,请检查您的网络");
        }
        error.printStackTrace();
    };


    public void requestCustomErr(CustomResponseError err) {
        if (err.isToast()) {
            Utils.makeToastAndShow(pageContext, err.getErrMsg(), Toast.LENGTH_SHORT);
        }
    }


}
