package bluemobi.iuv.activity;

import android.app.Activity;
import android.os.Bundle;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import bluemobi.iuv.util.Constants;
import bluemobi.iuv.util.SharedPreferencesUtil;
import bluemobi.iuv.util.Utils;


/**
 * Created by wangzhijun on 2015/7/17.
 */
public class AppStartActivity extends Activity {

    private boolean firstLoadApp;

    private boolean debug = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //默认为true，加载引导页
        firstLoadApp = Boolean.parseBoolean(SharedPreferencesUtil.
                getFromFileByDefault(this, Constants.FIRSTLOADAPP, "true"));
        process();
    }
    private void process() {
        if(debug) {
            if (firstLoadApp) {//首次加载、跳入引导页面
                Utils.moveTo(this, GuideActivity.class);
            } else {
                Utils.moveTo(this, LauncherActivity.class);
            }
        }else{
            Utils.moveTo(this, LoginActivity.class);
        }
        finish();
    }





}
