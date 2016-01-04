package bluemobi.iuv.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;

import bluemobi.iuv.R;
import bluemobi.iuv.activity.MainActivity;
import bluemobi.iuv.annotation.ContentView;
import bluemobi.iuv.app.IuwApplication;
import bluemobi.iuv.network.request.AddAdviceRequest;
import bluemobi.iuv.network.response.LoginResponse;
import bluemobi.iuv.util.StringUtils;
import bluemobi.iuv.util.Utils;
import bluemobi.iuv.util.WebUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by gaozq on 2015/8/7.
 *  P11_5我的意见
 */
@ContentView(R.layout.fragment_personal_center_my_opinion)
public class PersonalCenterMyOpinionFragment extends BaseFragment{


    @Bind(R.id.confirm)
    protected Button confirm;

    @Bind(R.id.contact)
    protected EditText contact;

    @Bind(R.id.advice)
    protected EditText advice;
    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity) mContext).setMineBarSytle(getResources().getString(R.string.personal_center_my_opinion_title), R.color.common_blue, true);
    }


    @Override
    public void onHiddenChanged(boolean hidden) {

        super.onHiddenChanged(hidden);
        if (((MainActivity) mContext).isHiddChange)
        {

            ((MainActivity) mContext).setMineBarSytle(getResources().getString(R.string.personal_center_my_opinion_title), R.color.common_blue, true);
        }


    }

    @Override
    protected void successViewCompleted(View successView) {
        ButterKnife.bind(this, successView);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        isShowLoadPage=false;
    }

    @OnClick(R.id.confirm)
    public void commit(){
        if(StringUtils.isEmpty(advice.getText().toString())){
            Toast.makeText(mContext, "意见不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if(StringUtils.isEmpty(contact.getText().toString())){
            Toast.makeText(mContext, "联系方式不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        AddAdviceRequest request = new AddAdviceRequest(
                new Response.Listener<LoginResponse>() {
                    @Override
                    public void onResponse(LoginResponse response) {
                        Utils.closeDialog();
                        if (response != null && response.getStatus() == 0) {
                            Toast.makeText(getActivity(), "提交成功", Toast.LENGTH_SHORT).show();
                            ((MainActivity) mContext).onBackPressed();
                        }
                    }
                }, (Response.ErrorListener) getActivity());
        request.setUserId(IuwApplication.getInstance().getMyUserInfo().getUserId());
        request.setContactWay(contact.getText().toString());
        request.setContent(advice.getText().toString());
        Utils.showProgressDialog(getActivity());
        WebUtils.doPost(request);

    }

}
