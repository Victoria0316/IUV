package bluemobi.iuv.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;

import bluemobi.iuv.R;
import bluemobi.iuv.activity.MainActivity;
import bluemobi.iuv.base.BaseActivity;
import bluemobi.iuv.network.request.CheckVersionRequest;
import bluemobi.iuv.network.response.CheckVersionResponse;
import bluemobi.iuv.service.UpdateService;
import bluemobi.iuv.util.Utils;
import bluemobi.iuv.util.WebUtils;
import bluemobi.iuv.view.CustomDialog;
import bluemobi.iuv.view.IuwAlertDialog;

public class UpdateChecker {
	private static BaseActivity mActivity;

	private static int localVersion = 0;
	
	private CustomDialog dialog;

	private UpdateChecker(BaseActivity activity) {
		mActivity = activity;
		try {
			localVersion = mActivity.getPackageManager().getPackageInfo(
					mActivity.getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

	}

	private static UpdateChecker instance;

	public static UpdateChecker getInstance(BaseActivity activity) {
		if (instance == null) {
			instance = new UpdateChecker(activity);
		}
		mActivity = activity;
		return instance;
	}

	/**
	 * 是否提示检测中
	 * 
	 * @param showLoading
	 */
	//baseactivity
	public void check(final boolean showLoading) {
		CheckVersionRequest request = new CheckVersionRequest(
				new Response.Listener<CheckVersionResponse>() {

					@Override
					public void onResponse(final CheckVersionResponse response) {
						if (showLoading) {
							Utils.closeDialog();
						}
						if (response != null && response.getStatus() == 0) {
							float serverVersion = response.getData().getVcode();
							Log.e("aaaaa",serverVersion+"");
							if (serverVersion > localVersion) {
								 boolean force =  false;
								if("3".equals(response.getData().getReleaseStatus())){
									force=true;
								}
								final boolean isForce=force;
								String negative = !force? "取消":"退出";
								final IuwAlertDialog dialog = new IuwAlertDialog(
										mActivity);
								dialog.setTitle("更新提示")
										.setMessage(
												response.getData().getContent())
										.setNegativeButtonClickListener(negative,
												new View.OnClickListener() {

													@Override
													public void onClick(View v) {
														dialog.dismiss();
														if (isForce) {
															if (mActivity instanceof MainActivity) {
																IuwApplication.getInstance().myUserInfo = null;
																mActivity.finish();
															}
														}
													}
												})
										.setPositiveButtonClickListener("更新",
												new View.OnClickListener() {

													@Override
													public void onClick(View v) {
														Intent updateIntent = new Intent(
																mActivity,
																UpdateService.class);
														updateIntent.putExtra("downLoadUrl",
																 response.getData().getFilepath());
														Log.e("update app", response.getData().getFilepath());
														updateIntent.putExtra("ifForce", isForce);
														mActivity
																.startService(updateIntent);
														dialog.dismiss();
													}
												}).show();
//										.show();
							} else {
								if(showLoading){
									CustomDialog.Builder customBuilder = new CustomDialog.Builder(mActivity);
									customBuilder
											.setTitle("提示")
											.setMessage("当前版本已为最新")
											.setLineGONE(View.GONE)
											.setNegativeButton("确定",
													new DialogInterface.OnClickListener() {
														@Override
														public void onClick(DialogInterface dialog,
																int which) {
															dialog.dismiss();
														}
													});
									dialog = customBuilder.create();
									dialog.show();
								}
							}
						} else {// TODO:
							if(!showLoading){
								return;
							}
							Utils.makeToastAndShow(
									mActivity,
									response == null ? mActivity.getString(R.string.global_unknown_err) : response
											.getContent(), Toast.LENGTH_SHORT);
						}
					}
				}, mActivity);
		if (showLoading) {
			Utils.showProgressDialog(mActivity);
		}else{
			request.setHandleCustomErr(false);
		}
		WebUtils.doPost(request);
	}

}
