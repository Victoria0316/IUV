package bluemobi.iuv.base;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.File;

import bluemobi.iuv.activity.LoginActivity;
import bluemobi.iuv.annotation.ContentView;
import bluemobi.iuv.app.IuwActivityManager;
import bluemobi.iuv.app.IuwApplication;
import bluemobi.iuv.base.crop.Crop;
import bluemobi.iuv.callback.TitleBarCallBack;
import bluemobi.iuv.entity.AddCommentBean;
import bluemobi.iuv.eventbus.CommonTypeEvent;
import bluemobi.iuv.network.IuwHttpJsonRequest;
import bluemobi.iuv.network.exception.CustomResponseError;
import bluemobi.iuv.network.exception.TokenInvalid;
import bluemobi.iuv.network.request.AddCommentyRequest;
import bluemobi.iuv.network.response.AddCommentyResponse;
import bluemobi.iuv.util.Constants;
import bluemobi.iuv.util.Utils;
import bluemobi.iuv.util.ViewUtils;
import bluemobi.iuv.util.WebUtils;
import bluemobi.iuv.view.IuwAlertDialog;
import bluemobi.iuv.view.LoadingPage;
import de.greenrobot.event.EventBus;


public abstract class BaseActivity extends AppCompatActivity implements TitleBarCallBack, Response.ErrorListener{

	public Context mContext = null;
	/**
	 * 加载Loading页面
	*/
	private LoadingPage loadingPage;
	public ImageView resultView;
	protected View contentView;
	private IuwAlertDialog mDialog;
	private IuwHttpJsonRequest request;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mContext = this;
		LayoutInflater inflater = LayoutInflater.from(mContext);
		request = initRequest();
		invokeRequest();
		initView(inflater);
		initBase();

	}


	protected IuwHttpJsonRequest initRequest() {
		return null;
	}

	private void invokeRequest() {
		if (request == null) {
			return;
		} else {
			request.setNetWorkResponseListener(loadingPage);
			WebUtils.doPost(request);
		}
	}

	/**
	 * 初始化基础数据
	 */
	protected abstract void initBase();

	/**
	 * 初始化loadingpage
	 */
	protected  void initView( final LayoutInflater inflater)
	{

		if (loadingPage == null)
		{
			loadingPage = new LoadingPage(mContext)
			{

				@Override
				protected void successViewCompleted(View successView) {
					BaseActivity.this.successViewCompleted(successView);
				}

				@Override
				public View createSuccessView()
				{
					return BaseActivity.this.createSuccessView();
				}

			};
		}
		else
		{
			ViewUtils.removeParent(loadingPage);
		}
		setContentView(loadingPage);

	}

	protected abstract void successViewCompleted(View successView);

	/**
	 *  显示加载界面
	 */
	public void showLoadingPage(boolean isShowLoad)
	{
		if(loadingPage!=null)
		{
			loadingPage.show(isShowLoad);
		}
	}


	/***
	 *  创建成功的界面 通过inflate加载界面
	 * @return
	 */
	public  View createSuccessView(){
		LayoutInflater inflater = LayoutInflater.from(this);
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

	protected void myFinish() {
		IuwActivityManager.getInstance().popOneActivity(this);
	}

	protected void finishAll() {
		IuwActivityManager.getInstance().finishAllActivity();
	}

	@Override
	protected void onDestroy() {
		IuwActivityManager.getInstance().popOneActivity(this);
		super.onDestroy();
	}

	/**
	 * 点击bar 右边按钮
	 */
	@Override
	public void clickBarRight() {
	}

	/**
	 * 点击bar左边按钮
	 */
	@Override
	public void clickBarleft()
	{
		this.finish();
	}

	/**
	 * 点击bar搜索按钮
	 */
	@Override
	public void clickBarSearch() {

	}


	/**
	 * 点击bar搜索按钮
	 */
	@Override
	public void clickBarHint() {

	}

	@Override
	public void clickImageRight() {

	}

	@Override
	public void clickImageLeft() {

	}

	/**
	 * 右侧带文字
	 */
	public void clickTextRight(){

	}

	/**
	 * 右侧左边
	 */
	public void clickTextRightLeft(){

	}

	@Override
	public void clickBarRightSearch()
	{

	}

	@Override
	public void clickRightMapSearch() {

	}
	@Override
	public void clickDetailRightMap() {

	}

	@Override
	public void clickSearchBar() {

	}

	@Override
	public void clickCommentRight() {

	}

	@Override
	public void clickRightNaviSearch() {

	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent result) {
		if (requestCode == Crop.REQUEST_PICK && resultCode == RESULT_OK) {
			beginCrop(result.getData());
		} else if (requestCode == Crop.REQUEST_CROP) {
			handleCrop(resultCode, result);
		} else if (requestCode == Crop.REQUEST_CAMERA && resultCode == RESULT_OK) {
			beginCrop(Crop.outputFileUri);
		}
	}

	private void beginCrop(Uri source) {
		Uri destination = Uri.fromFile(new File(getCacheDir(), "cropped"));
		Crop.of(source, destination).asSquare().start(this);
	}

	private void handleCrop(int resultCode, Intent result) {
		if (resultCode == RESULT_OK) {
			resultView.setImageURI(Crop.getOutput(result));
			CommonTypeEvent commonTypeEvent = new CommonTypeEvent();
			commonTypeEvent.setType(Constants.UPLOAD_IMAGE);
			EventBus.getDefault().post(commonTypeEvent);
		} else if (resultCode == Crop.RESULT_ERROR) {
			Toast.makeText(this, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
		}
	}


	public void getImage(ImageView iv) {
		this.resultView = iv;
		CharSequence[] items = {"相册", "相机"};
		new AlertDialog.Builder(this).setTitle("选择图片来源")
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
				Crop.pickImage(BaseActivity.this);
			} else {
				resultView.setImageDrawable(null);
				Crop.pickImageFromCamera(BaseActivity.this);
			}
		}
	}
	public void pickImage(ImageView iv){
		this.resultView = iv;
		resultView.setImageDrawable(null);
		Crop.pickImage(BaseActivity.this);
	}
	public void pickImageFromCamera(ImageView iv){
		this.resultView = iv;
		resultView.setImageDrawable(null);
		Crop.pickImageFromCamera(BaseActivity.this);
	}


	@Override
	public void onErrorResponse(VolleyError error) {
		requestError(error);
	}

	public void requestError(VolleyError error) {

		Utils.closeDialog();
		if (error instanceof TimeoutError) {
			Utils.makeToastAndShow(this, "网络异常,请检查您的网络", Toast.LENGTH_SHORT);
		} else if (error instanceof ParseError) {
			Utils.makeToastAndShow(this, "解析出错", Toast.LENGTH_SHORT);
		} else if (error instanceof TokenInvalid) {
			if (mDialog == null || !mDialog.isShowing()) {
				mDialog = new IuwAlertDialog(this);
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
										intent.setClass(getBaseContext(),
												LoginActivity.class);
										intent.putExtra("tokeninvalid", true);
										getBaseContext().startActivity(intent);
										mDialog.dismiss();
									}
								});
				mDialog.show();
			}

		} else if (error instanceof CustomResponseError) {
			CustomResponseError crs = (CustomResponseError) error;
			requestCustomErr(crs);
		} else if (error instanceof NoConnectionError) {//无网络连接
			Utils.makeToastAndShow(this, "网络异常,请检查您的网络");
		}
		error.printStackTrace();
	};


	public void requestCustomErr(CustomResponseError err) {
		if (err.isToast()) {
			Utils.makeToastAndShow(this, err.getErrMsg(), Toast.LENGTH_SHORT);
		}
	}



	public void addCommentToServer(AddCommentBean bean)
	{
		AddCommentyRequest request = new AddCommentyRequest
				(
						new Response.Listener<AddCommentyResponse>() {
							@Override
							public void onResponse(AddCommentyResponse response) {


							}
						}, this);

		request.setProductId(bean.getProductId());
		request.setUserId(bean.getUserId());
		request.setContent(bean.getContent());
		request.setPicArray(bean.getPicArray());
		request.setPicType(bean.getPicType());
		request.setSourceType(bean.getSourceType());
		WebUtils.doPost(request);
	}

	/**
	 * 登录后的操作
	 * @return  true 需要登录 false 不需要登录
	 */
	public boolean shouldGoToLogin()
	{
		if (IuwApplication.getInstance().getMyUserInfo()==null)
		{
			Utils.moveTo(mContext,LoginActivity.class);
			return  true;
		}
		else
		{
			return false;
		}
	}

}
