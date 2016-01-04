package bluemobi.iuv.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bluemobi.iuv.R;
import bluemobi.iuv.activity.MainActivity;
import bluemobi.iuv.annotation.ContentView;
import bluemobi.iuv.app.IuwApplication;
import bluemobi.iuv.app.TitleBarManager;
import bluemobi.iuv.base.BaseActivity;
import bluemobi.iuv.base.utils.Logger;
import bluemobi.iuv.entity.AddCommentBean;
import bluemobi.iuv.eventbus.BaseEvent;
import bluemobi.iuv.eventbus.CommentDetailEvent;
import bluemobi.iuv.network.request.AddCommentyRequest;
import bluemobi.iuv.network.request.CommentDetailsRequest;
import bluemobi.iuv.network.response.AddCommentyResponse;
import bluemobi.iuv.network.response.CommentDetailsResponse;
import bluemobi.iuv.util.Base64;
import bluemobi.iuv.util.Constants;
import bluemobi.iuv.util.StringUtils;
import bluemobi.iuv.util.Utils;
import bluemobi.iuv.util.WebUtils;
import bluemobi.iuv.view.LoadingPage;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Created by gaoyn on 2015/8/5.
 * <p/>
 * p8-2-2-1-1 写点评
 */

@ContentView(R.layout.fragment_write_evaluation)
public class WriteCommentFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.camera_first)
    ImageView camera_first;

    @Bind(R.id.camera_center)
    ImageView camera_center;

    @Bind(R.id.camera_last)
    ImageView camera_last;

    @Bind(R.id.et_write_commnet)
    EditText et_write_commnet;

    private Map<Integer, ImageView> imageViewMap = null;

    private String productId;

    private AlertDialog pickDialog;

    private int flag = 0;

    private String sourceType;

    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity) mContext).showTitleTextBar();
    }


    public WriteCommentFragment()
    {
        EventBus.getDefault().register(this);
    }


    public void onEvent(BaseEvent baseEvent) {
        if (baseEvent instanceof CommentDetailEvent) {
            CommentDetailEvent commentDetailEvent = (CommentDetailEvent) baseEvent;
            productId = commentDetailEvent.getProductID();
            sourceType = commentDetailEvent.getSourceType();
            Logger.e("tagproductId",productId);

        }
    }

    public void onEventMainThread(String string) {

        if ("clickBarRight".equals(string))
        {
            Bitmap centerImage = null;
            Bitmap lastImage = null;
            String commentDetail = et_write_commnet.getText().toString().trim();
            Bitmap fristImage = ((BitmapDrawable) camera_first.getDrawable()).getBitmap();
            String fristImageBase64 = Base64.encodeBytes(Utils.Bitmap2Bytes(fristImage));
            StringBuffer sb = new StringBuffer();
            sb.append(fristImageBase64);
            if (camera_center.getVisibility() == View.VISIBLE)
            {
                centerImage = ((BitmapDrawable) camera_center.getDrawable()).getBitmap();
                String centerImageBase64 = Base64.encodeBytes(Utils.Bitmap2Bytes(centerImage));
                sb.append(",").append(centerImageBase64);
                if (camera_last.getVisibility()==View.VISIBLE)
                {
                    lastImage = ((BitmapDrawable) camera_last.getDrawable()).getBitmap();
                    String lastImageBase64 = Base64.encodeBytes(Utils.Bitmap2Bytes(lastImage));
                    sb.append(",").append(lastImageBase64);
                }

            }

            if (StringUtils.isEmpty(commentDetail))
            {
                return;
            }

            AddCommentBean bean = new AddCommentBean();
            bean.setUserId(IuwApplication.getInstance().getMyUserInfo().getUserId());
            bean.setProductId(productId);
            bean.setContent(commentDetail);
            bean.setSourceType(sourceType);  //	是	评价类型(1 商品 2 商场 3 宝藏)
            bean.setPicArray(sb.toString());
            bean.setPicType("jpg");
            AddCommentDataServer(bean,fristImage,centerImage,lastImage);
        }



    }


    @Override
    protected void successViewCompleted(View successView) {
        ButterKnife.bind(this, successView);
        imageViewMap = new HashMap<Integer, ImageView>();
        camera_first.setOnClickListener(this);
        camera_center.setOnClickListener(this);
        camera_last.setOnClickListener(this);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        isShowLoadPage = false;

    }


    public void pickAvatar() {
        if (pickDialog == null) {
            pickDialog = new AlertDialog.Builder(getActivity())
                    .create();
            view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_pick_pic, null);
            Window window = pickDialog.getWindow();
            window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置
            window.setWindowAnimations(R.style.dialog_bottom_style);  //添加动画

            TextView camera = (TextView) view.findViewById(R.id.camera);
            TextView gallary = (TextView) view.findViewById(R.id.gallary);
            TextView cancel = (TextView) view.findViewById(R.id.cancel);
            camera.setOnClickListener(this);
            gallary.setOnClickListener(this);
            cancel.setOnClickListener(this);
            WindowManager windowManager = getActivity().getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            WindowManager.LayoutParams lp = pickDialog.getWindow().getAttributes();
            lp.width = (int) (display.getWidth()); //设置宽度
            pickDialog.getWindow().setAttributes(lp);
            pickDialog.show();
            pickDialog.setContentView(view);
        } else {
            pickDialog.show();
        }
    }


/*    @Override
    public void getDataServer(AddCommentBean bean) {
        super.getDataServer(bean);
    }*/

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.camera_first:
                flag = 1;
                imageViewMap.put(flag, camera_first);
                pickAvatar();
                break;
            case R.id.camera_center:
                flag = 2;
                imageViewMap.put(flag, camera_center);
                pickAvatar();
                break;
            case R.id.camera_last:
                flag = 3;
                imageViewMap.put(flag, camera_last);
                pickAvatar();
                break;
            case R.id.camera:
                pickDialog.dismiss();
                ((BaseActivity) mContext).pickImageFromCamera(imageViewMap.get(flag));
                if (flag == 1) {
                    camera_center.setVisibility(View.VISIBLE);
                } else if (flag == 2){
                    camera_last.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.gallary:
                pickDialog.dismiss();
                ((BaseActivity) mContext).pickImage(imageViewMap.get(flag));
                if (flag == 1) {
                    camera_center.setVisibility(View.VISIBLE);
                } else if (flag == 2){
                    camera_last.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.cancel:
                pickDialog.dismiss();
                break;

        }
    }


}
