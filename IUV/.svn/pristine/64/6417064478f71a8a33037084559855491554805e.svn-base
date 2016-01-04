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
import bluemobi.iuv.network.request.LoginRequest;
import bluemobi.iuv.network.request.UpdatePwdRequest;
import bluemobi.iuv.network.response.LoginResponse;
import bluemobi.iuv.network.response.UpdatePwdResponse;
import bluemobi.iuv.util.StringUtils;
import bluemobi.iuv.util.Utils;
import bluemobi.iuv.util.WebUtils;
import bluemobi.iuv.view.LoadingPage;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gaoyn on 2015/8/5.
 *
 * p5-2 修改密码
 */

@ContentView(R.layout.activity_modify_password)
public class ModifyPasswordActivity extends BaseActivity implements View.OnClickListener{


    @Bind(R.id.determine)
    Button determine;
    @Bind(R.id.phone)
    EditText phone;
    @Bind(R.id.password)
    EditText password;
    @Bind(R.id.new_password)
    EditText new_password;
    @Bind(R.id.again)
    EditText again;


    @Override
    protected void initBase() {

        TitleBarManager titleBarManager = new TitleBarManager();
        titleBarManager.init(ModifyPasswordActivity.this,getSupportActionBar());
        titleBarManager.showCommonTitleBar(R.string.modify_password, R.drawable.common_return, true);
        showLoadingPage(false);

    }

    @Override
    protected void successViewCompleted(View successView) {
        ButterKnife.bind(this, successView);

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
                if(checkInput()){
                    UpdatePwdRequest request = new UpdatePwdRequest(
                            new Response.Listener<UpdatePwdResponse>() {
                                @Override
                                public void onResponse(UpdatePwdResponse response) {
                                    Utils.closeDialog();
                                    if (response != null && response.getStatus() == 0) {
                                        Toast.makeText(ModifyPasswordActivity.this,"修改密码成功",Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                }
                            }, this);
                    request.setUsername(phone.getText().toString());
                    request.setOldPassword(password.getText().toString());
                    request.setNewPassword(new_password.getText().toString());
                    Utils.showProgressDialog(ModifyPasswordActivity.this);
                    WebUtils.doPost(request);
                }
                break;
        }
    }
    private boolean checkInput() {
        if(StringUtils.isEmpty(phone.getText().toString())){
            Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!Utils.checkPhoneNum(phone.getText().toString())){
            Toast.makeText(this,"手机号不合法",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (StringUtils.isEmpty(password.getText().toString())) {
            Toast.makeText(this, "原始密码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.getText().toString().length() < 8 || password.getText().toString().length() > 18) {
            Toast.makeText(this, "原始密码长度为8-18", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (StringUtils.isEmpty(new_password.getText().toString())) {
            Toast.makeText(this, "新密码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (new_password.getText().toString().length() < 8 || new_password.getText().toString().length() > 18) {
            Toast.makeText(this, "新密码长度为8-18", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (StringUtils.isEmpty(again.getText().toString())) {
            Toast.makeText(this, "请再次输入新密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (again.getText().toString().length() < 8 || again.getText().toString().length() > 18) {
            Toast.makeText(this, "再次输入新密码长度为8-18", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!(new_password.getText().toString()).equals(again.getText().toString())) {
            Toast.makeText(this, "新密码不一致", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
