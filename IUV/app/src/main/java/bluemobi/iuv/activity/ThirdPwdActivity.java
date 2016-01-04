package bluemobi.iuv.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;

import bluemobi.iuv.R;
import bluemobi.iuv.annotation.ContentView;
import bluemobi.iuv.app.IuwApplication;
import bluemobi.iuv.app.TitleBarManager;
import bluemobi.iuv.base.BaseActivity;
import bluemobi.iuv.network.request.ForgetPwdRequest;
import bluemobi.iuv.network.request.ThirdPwdRequest;
import bluemobi.iuv.network.response.LoginResponse;
import bluemobi.iuv.network.response.SendRegisterResponse;
import bluemobi.iuv.util.StringUtils;
import bluemobi.iuv.util.Utils;
import bluemobi.iuv.util.WebUtils;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gaoyn on 2015/8/4.
 *
 * 根据用户ID修改密码
 */
@ContentView(R.layout.activity_third_password)
public class ThirdPwdActivity extends BaseActivity implements View.OnClickListener{

    @Bind(R.id.determine)
    Button determine;

    @Bind(R.id.password)
    EditText password;
    @Bind(R.id.again)
    EditText again;
    private         String id_need_pwd;

    @Override
    protected void initBase() {

        TitleBarManager titleBarManager = new TitleBarManager();
        titleBarManager.init(ThirdPwdActivity.this, getSupportActionBar());
        titleBarManager.showCommonTitleBar(R.string.forget_password, R.drawable.common_return, true);
        showLoadingPage(false);
    }

    @Override
    protected void successViewCompleted(View successView) {
        ButterKnife.bind(this, successView);

        id_need_pwd = getIntent().getStringExtra("ID_NEED_PWD");
        determine.setOnClickListener(this);

    }



    @Override
    public void clickBarleft() {
        finish();
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.determine:
                if (checkAll())
                sendCommit();
                break;

        }
    }

    private  void  sendCommit () {

        ThirdPwdRequest request = new ThirdPwdRequest(
                    new Response.Listener<LoginResponse>() {
                        @Override
                        public void onResponse(LoginResponse response) {
                            Utils.closeDialog();
                            if (response != null && response.getStatus() == 0) {
                                IuwApplication.getInstance().setMyUserInfo(response.getData());
                                Utils.moveTo(mContext,MainActivity.class);
                                finish();
                            }
                        }
                    }, this);
            request.setPassword(password.getText().toString());
            request.setId(id_need_pwd);
            Utils.showProgressDialog(this);
            WebUtils.doPost(request);

    }





    private  boolean checkAll(){


        if (StringUtils.isEmpty(password.getText().toString())) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.getText().toString().length() < 8
                || password.getText().toString().length() > 18) {
            Toast.makeText(this, "密码长度为8-18", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (StringUtils.isEmpty(again.getText().toString())) {
            Toast.makeText(this, "请再次输入密码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (again.getText().toString().length() < 8
                || password.getText().toString().length() > 18) {
            Toast.makeText(this, "请再次输入密码长度为8-18", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!(password.getText().toString().equals(again.getText()
                .toString()))) {
            Toast.makeText(this, "密码与请再次输入密码不一致", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
