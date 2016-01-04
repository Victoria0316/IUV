package bluemobi.iuv.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

import bluemobi.iuv.R;
import bluemobi.iuv.app.IuwApplication;
import bluemobi.iuv.network.model.HotShopTypeModel;
import bluemobi.iuv.network.request.HotShopTypeRequest;
import bluemobi.iuv.network.request.LargerDeptTypeListRequest;
import bluemobi.iuv.network.response.HotShopTypeResponse;
import bluemobi.iuv.util.Utils;
import bluemobi.iuv.util.WebUtils;

/**
 * 启动页面
 * Created by wangzhijun on 2015/7/17.
 */
public class LauncherActivity extends Activity{
    private String cityVersion;
    private  String hotCityStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final RelativeLayout rl = new RelativeLayout(this);
        rl.setBackgroundResource(R.drawable.launcher);
        setContentView(rl);
        new CountDownTimer(3000, 1000) {
            public void onTick(long millisUntilFinished) {
            }
            public void onFinish() {
                Utils.moveTo(LauncherActivity.this, MainActivity.class);
                finish();
            }
        }.start();
    }





}
