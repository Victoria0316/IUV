package bluemobi.iuv.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.File;
import java.util.List;

import bluemobi.iuv.activity.LoginActivity;
import bluemobi.iuv.activity.MainActivity;
import bluemobi.iuv.annotation.ContentView;
import bluemobi.iuv.app.IuwApplication;
import bluemobi.iuv.base.crop.Crop;
import bluemobi.iuv.base.utils.Logger;
import bluemobi.iuv.callback.TitleBarCallBack;
import bluemobi.iuv.callback.TitleClickCallBack;
import bluemobi.iuv.entity.AddCommentBean;
import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.model.HotShopTypeModel;
import bluemobi.iuv.network.request.AddCommentyRequest;
import bluemobi.iuv.network.request.CollectShopRequest;
import bluemobi.iuv.network.request.GivePraiseRequest;
import bluemobi.iuv.network.request.HotShopTypeRequest;
import bluemobi.iuv.network.request.LargerDeptTypeListRequest;
import bluemobi.iuv.network.response.AddCommentyResponse;
import bluemobi.iuv.network.response.GivePraiseResponse;
import bluemobi.iuv.network.response.HotShopTypeResponse;
import bluemobi.iuv.util.Constants;
import bluemobi.iuv.util.Utils;
import bluemobi.iuv.util.ViewUtils;
import bluemobi.iuv.util.WebUtils;
import bluemobi.iuv.view.LoadingPage;
import de.greenrobot.event.EventBus;

public abstract class BaseFragment extends Fragment implements TitleClickCallBack,PullToRefreshListView.OnRefreshListener2{

    public Context mContext = null;

    public View view = null;

    private LoadingPage loadingPage;

    public PullToRefreshListView plv_refresh = null;

    /**
     * 是否显示Loading 页面 true显示 false 不显示
     */
    public boolean isShowLoadPage = true;

    private View contentView;

    private IuwHttpJsonRequest request;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        initEmpteyData();

    }

    /**
     * 并非要重写的方法，如果在LoadingPage之前处理数据
     */
    public void initEmpteyData() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (loadingPage == null) {
            loadingPage = new LoadingPage(getActivity()){

                @Override
                protected void successViewCompleted(View successView) {
                    BaseFragment.this.successViewCompleted(successView);
                }

                @Override
                public View createSuccessView() {
                    return BaseFragment.this.createSuccessView();
                }

            };
        }else{
            ViewUtils.removeParent(loadingPage);
        }
        request = initRequest();
        invokeRequest();
        return loadingPage;
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



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData(savedInstanceState);
        show();
    }

    protected abstract void successViewCompleted(View successView);

    /**
     * 初始化数据
     * @param savedInstanceState 保存状态数据使用
     */
    public abstract void initData(Bundle savedInstanceState);

    /***
     *  创建成功的界面
     * @return
     */
    public View createSuccessView(){
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        if(Constants.NO_ANNOTATION == getLayoutRes()){
            contentView = createViewCustom(inflater);
        }else{
            View view = inflater.inflate(getLayoutRes(), null);
            contentView = view;
        }
        if(contentView == null ){
            //TODO:

        }
        return contentView;
    }


    public View getCurrentView() {
        return contentView;
    }

    protected View createViewCustom(LayoutInflater inflater){
        return null;
    }


    protected int getLayoutRes(){
        Class<?> handlerType = this.getClass();
        ContentView contentView = handlerType.getAnnotation(ContentView.class);
        if(contentView == null){
            return Constants.NO_ANNOTATION;
        }
        return contentView.value();
    }

    public void show(){
        if(loadingPage!=null){
            loadingPage.show(isShowLoadPage);
        }
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void searchCallBack(String str,String countyName) {

    }

    @Override
    public void commentCallBack() {

    }



    public static final int LOAD_MORE = 1;
    public static final int LOAD_REFRESH = 2;
    public static final int NUMBER_PER_PAGE = Constants.CURRENTNUMBASE;// 每页条数
    protected int curPage = -1;// 当前页
    protected long pageTime = 0;

    //add getPage
    protected boolean getPage(int type) {
        boolean ret = true;
        switch (type) {
            case LOAD_MORE: {
                int count = plv_refresh.getRefreshableView().getAdapter().getCount();
                int i = count-2;
                Log.e("tag-i-->", i + "");
                if (i % NUMBER_PER_PAGE == 0) {
                    curPage = i/ NUMBER_PER_PAGE;
                    Log.e("tag plv ->", "LOAD_MORE");
                } else {
                    ret = false;
                    Log.e("tag plv ELSE->", "LOAD_MORE");
                    plv_refresh.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            plv_refresh.onRefreshComplete();
                            Utils.makeToastAndShow(mContext, "已经没有更多记录", Toast.LENGTH_SHORT);
                        }
                    }, 2000);

                }
            }
            break;
            case LOAD_REFRESH:
                Log.e("tag plv ->","LOAD_REFRESH");
                pageTime = System.currentTimeMillis();
                //IuwApplication.getInstance().setPageTime(pageTime);
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

    @Override
    public void onPullDownToRefresh(PullToRefreshBase pullToRefreshBase)
    {
        Log.e("tag", "onPullDownToRefresh");
        getPage(LOAD_REFRESH);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase pullToRefreshBase)
    {
        Log.e("tag", "onPullUpToRefresh");
        getPage(LOAD_MORE);

    }

    public void initPullToRefresh(PullToRefreshListView pullToRefresh)
    {
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
    private ImageView resultView;
    /** Standard activity result: operation canceled. */
    public static final int RESULT_CANCELED    = 0;
    /** Standard activity result: operation succeeded. */
    public static final int RESULT_OK           = -1;
    /** Start of user-defined activity results. */
    public static final int RESULT_FIRST_USER   = 1;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent result) {
        if (requestCode == Crop.REQUEST_PICK && resultCode == RESULT_OK) {
            beginCrop(result.getData());
        } else if (requestCode == Crop.REQUEST_CROP) {
            handleCrop(resultCode, result);
        } else if (requestCode == Crop.REQUEST_CAMERA && resultCode == RESULT_OK) {
            beginCrop(Crop.outputFileUri);
        }
    }


    private void beginCrop(Uri source) {
        Uri destination = Uri.fromFile(new File(mContext.getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(getActivity());
    }

    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == RESULT_OK) {
            resultView.setImageURI(Crop.getOutput(result));

        } else if (resultCode == Crop.RESULT_ERROR) {
            Toast.makeText(mContext, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public void getImage(ImageView iv) {
        this.resultView = iv;
        CharSequence[] items = {"相册", "相机"};
        new AlertDialog.Builder(getActivity()).setTitle("选择图片来源")
                .setItems(items, new MyDialogClickListener(1)).create()
                .show();
    }

    private class MyDialogClickListener implements
            DialogInterface.OnClickListener {
        private int n;

        MyDialogClickListener(int number) {
            this.n = number;
        }

        public void onClick(DialogInterface dialog, int which) {
            if (which == 0) {
                resultView.setImageDrawable(null);
                Crop.pickImage(getActivity());
            } else {
                resultView.setImageDrawable(null);
                Crop.pickImageFromCamera(getActivity());
            }
        }
    }


    /**
     * 添加评论
     * @param bean
     * @param params
     */
    public void AddCommentDataServer(AddCommentBean bean, final Bitmap... params)
    {
        Utils.showProgressDialog(mContext);
        AddCommentyRequest request = new AddCommentyRequest
                (
                        new Response.Listener<AddCommentyResponse>() {
                            @Override
                            public void onResponse(AddCommentyResponse response) {
                                Utils.closeDialog();
                                Logger.e("tag--params.length->",params.length);

                                for (int i = 0;i<params.length;i++)
                                {

                                    if( params[i] != null && !params[i].isRecycled()){
                                        params[i].recycle();
                                        params[i] = null;

                                    }

                                    System.gc();
                                }

                                ((MainActivity)mContext).onBackPressed();

                            }
                        }, (Response.ErrorListener) getActivity());

        request.setProductId(bean.getProductId());
        request.setUserId(bean.getUserId());
        request.setContent(bean.getContent());
        request.setPicArray(bean.getPicArray());
        request.setPicType(bean.getPicType());
        request.setSourceType(bean.getSourceType());
        WebUtils.doPost(request);
    }



    public void getHotShopTypes(){
        HotShopTypeRequest request = new HotShopTypeRequest(new Response.Listener<HotShopTypeResponse>() {
            @Override
            public void onResponse(HotShopTypeResponse response) {
                if (response != null && response.getStatus() == 0) {
                    if (response.getData() != null) {
                        List<HotShopTypeModel> mHotShopTypeModel = response.getData();
                        IuwApplication.getInstance().dataList.clear();
                        IuwApplication.getInstance().dataCodeList.clear();
                        for (int i = 0; i < mHotShopTypeModel.size(); i++) {
                            IuwApplication.getInstance().dataList.add(mHotShopTypeModel.get(i).getShopsTypeName());
                            IuwApplication.getInstance().dataCodeList.add(mHotShopTypeModel.get(i).getShopsTypeCode());
                        }
                    }
                }
            }
        }, (Response.ErrorListener) getActivity());
        WebUtils.doPost(request);
    }


    public interface RefreshPraiseListener {

        public void refreshView(int pos);
    }

    public void givePraise(String shopId,final int p,final RefreshPraiseListener r){
        if (IuwApplication.getInstance().getMyUserInfo()==null)
        {
            Utils.moveTo(mContext, LoginActivity.class);
            getActivity().finish();
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
                }, (Response.ErrorListener) mContext);
        request.setId(shopId);
        request.setUserId(IuwApplication.getInstance().getMyUserInfo().getUserId());
        Utils.showProgressDialog(mContext);
        WebUtils.doPost(request);
    }
    public interface RefreshCollectListener {

        public void refreshView(int pos);
    }

    public void collect(String shopId,final int p,final RefreshCollectListener r){

        if (IuwApplication.getInstance().getMyUserInfo()==null)
        {
            Utils.moveTo(mContext, LoginActivity.class);
            getActivity().finish();
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
                }, (Response.ErrorListener) mContext);
        request.setId(shopId);
        request.setUserId(IuwApplication.getInstance().getMyUserInfo().getUserId());
        Utils.showProgressDialog(mContext);
        WebUtils.doPost(request);
    }

}


