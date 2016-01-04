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
import bluemobi.iuv.network.request.ChooseSexRequest;
import bluemobi.iuv.network.request.UpdateNicknameRequest;
import bluemobi.iuv.network.response.LoginResponse;
import bluemobi.iuv.util.StringUtils;
import bluemobi.iuv.util.Utils;
import bluemobi.iuv.util.WebUtils;
import bluemobi.iuv.view.LoadingPage;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by gaoxy on 2015/8/28.
 *  P11_7-1
 */
@ContentView(R.layout.fragment_update_nickname)
public class UpdateNicknameFragment extends BaseFragment{


    @Bind(R.id.nickname)
    protected EditText nickname;
    @Bind(R.id.confirm)
    protected Button confirm;

    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity) mContext).setMineBarSytle(getResources().getString(R.string.update_nickname), R.color.common_blue, true);
    }


    @Override
    public void onHiddenChanged(boolean hidden) {

        super.onHiddenChanged(hidden);
        if (((MainActivity) mContext).isHiddChange)
        {

            ((MainActivity) mContext).setMineBarSytle(getResources().getString(R.string.update_nickname), R.color.common_blue, true);
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
    public void commit() {
        if(StringUtils.isEmpty(nickname.getText().toString())){
            Toast.makeText(mContext,"昵称不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        UpdateNicknameRequest request = new UpdateNicknameRequest(new Response.Listener<LoginResponse>() {
            @Override
            public void onResponse(LoginResponse response) {
                Utils.closeDialog();
                if (response != null && response.getStatus() ==0){
                    Toast.makeText(mContext,"修改成功",Toast.LENGTH_SHORT).show();
                    ((MainActivity)mContext).finishAllFragment();
                }
            }
        }, (Response.ErrorListener) getActivity());
        request.setUserId(IuwApplication.getInstance().getMyUserInfo().getUserId());
        request.setNickName(nickname.getText().toString());
        Utils.showProgressDialog(getActivity());
        WebUtils.doPost(request);
    }
}
