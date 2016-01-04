package bluemobi.iuv.activity;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;

import bluemobi.iuv.R;
import bluemobi.iuv.annotation.ContentView;
import bluemobi.iuv.app.TitleBarManager;
import bluemobi.iuv.base.BaseActivity;
import bluemobi.iuv.network.request.ConfirmCodeRequest;
import bluemobi.iuv.network.request.GetCodeRequest;
import bluemobi.iuv.network.request.SendRegisterRequest;
import bluemobi.iuv.network.response.ConfirmCodeResponse;
import bluemobi.iuv.network.response.GetCodeResponse;
import bluemobi.iuv.network.response.SendRegisterResponse;
import bluemobi.iuv.util.StringUtils;
import bluemobi.iuv.util.Utils;
import bluemobi.iuv.util.WebUtils;
import bluemobi.iuv.view.LoadingPage;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gaoyn on 2015/8/5.
 * <p/>
 * p6 注册
 */

@ContentView(R.layout.activity_registered)
public class RegisteredActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.Registered)
    Button Registered;
    @Bind(R.id.getid)
    TextView getCode;
    @Bind(R.id.phone)
    EditText phone;
    @Bind(R.id.yanzhengma)
    EditText yanzhengma;
    @Bind(R.id.nickname)
    EditText nickname;
    @Bind(R.id.password)
    EditText password;
    @Bind(R.id.password_two)
    EditText password_two;

    @Override
    protected void initBase() {

        TitleBarManager titleBarManager = new TitleBarManager();
        titleBarManager.init(RegisteredActivity.this,getSupportActionBar());
        titleBarManager.showCommonTitleBar(R.string.Registered_title, R.drawable.common_return, true);
        showLoadingPage(false);

    }

    @Override
    protected void successViewCompleted(View successView) {
        ButterKnife.bind(this, successView);

        Registered.setOnClickListener(this);
        getCode.setOnClickListener(this);
        myHandler = new MyHandler();

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.Registered:
                if (checkAll()){
                    ConfirmCodeRequest request = new ConfirmCodeRequest(
                            new Response.Listener<ConfirmCodeResponse>() {
                                @Override
                                public void onResponse(ConfirmCodeResponse response) {
                                    if (response != null && response.getStatus() == 0) {
                                        sendCommit();
                                    }
                                }
                            }, this);
                    request.setCellphone(phone.getText().toString());
                    request.setType("register");
                    request.setValidationCode(yanzhengma.getText().toString());
                    WebUtils.doPost(request);
                }
                break;
            case R.id.getid :
               if( checkPhone()){
                   new MyThread().start();
                   GetCodeRequest request = new GetCodeRequest(
                           new Response.Listener<GetCodeResponse>() {
                               @Override
                               public void onResponse(GetCodeResponse response) {
                               }
                           }, this);
                   request.setCellphone(phone.getText().toString());
                   request.setType("register");
                   WebUtils.doPost(request);
               }
                break;
        }
    }

    private  void  sendCommit (){
        SendRegisterRequest request = new SendRegisterRequest(
                new Response.Listener<SendRegisterResponse>() {
                    @Override
                    public void onResponse(SendRegisterResponse response) {
                        Utils.closeDialog();
                        if (response != null && response.getStatus() == 0) {
                            Toast.makeText(mContext, "注册成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }, this);
        request.setNickName(nickname.getText().toString());
        request.setPassword(password.getText().toString());
        request.setUserName(phone.getText().toString());
        Utils.showProgressDialog(this);
        WebUtils.doPost(request);
    }
    private  boolean checkAll(){
        if (!checkPhone()){
            return  false;
        }
        if (StringUtils.isEmpty(yanzhengma.getText().toString())){
            Toast.makeText(this,"验证码不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (StringUtils.isEmpty(nickname.getText().toString())){
            Toast.makeText(this,"昵称不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (StringUtils.isEmpty(password.getText().toString())) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.getText().toString().length() < 8
                || password.getText().toString().length() > 18) {
            Toast.makeText(this, "密码长度为8-18", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (StringUtils.isEmpty(password_two.getText().toString())) {
            Toast.makeText(this, "请再次输入密码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password_two.getText().toString().length() < 8
                || password.getText().toString().length() > 18) {
            Toast.makeText(this, "请再次输入密码长度为8-18", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!(password.getText().toString().equals(password_two.getText()
                .toString()))) {
            Toast.makeText(this, "密码与请再次输入密码不一致", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private  boolean checkPhone(){
        if(StringUtils.isEmpty(phone.getText().toString())){
            Toast.makeText(this,"手机号不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!Utils.checkPhoneNum(phone.getText().toString())){
            Toast.makeText(this,"手机号不合法",Toast.LENGTH_SHORT).show();
            return false;
        }
        return  true;
    }

    public class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub

            if (-1 < Integer.parseInt("" + msg.what)) {
                getCode.setEnabled(false);
                getCode.setText("" + msg.what);
            } else {
                getCode.setEnabled(true);
                getCode.setText("重新发送");
            }
            super.handleMessage(msg);
        }
    }
    /**
     * 默认60秒倒计时
     */
    private int begin = 60;

    /**
     * 倒计时标识 MyHandler
     */
    MyHandler myHandler;

    public class MyThread extends Thread {
        public void run() {
            for (int i = begin; i >= -1; i--) {

                Message msg = new Message();

                msg.what = i;
                myHandler.sendMessage(msg);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
