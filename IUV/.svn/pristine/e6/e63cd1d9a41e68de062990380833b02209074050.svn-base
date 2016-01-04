package bluemobi.iuv.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.HashMap;

import bluemobi.iuv.R;
import bluemobi.iuv.activity.MainActivity;
import bluemobi.iuv.annotation.ContentView;
import bluemobi.iuv.app.TitleBarManager;
import bluemobi.iuv.base.BaseActivity;
import bluemobi.iuv.view.LoadingPage;
import butterknife.Bind;
import butterknife.ButterKnife;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * Created by gaozq on 2015/8/7.
 * P11_6邀请好友
 */
@ContentView(R.layout.fragment_personal_center_my_friend)
public class PersonalCenterMyFriendFragment extends BaseFragment implements View.OnClickListener,PlatformActionListener {
    @Bind(R.id.weixin)
    protected ImageView weixin;

    @Bind(R.id.xinlang)
    protected ImageView xinlang;

    @Bind(R.id.qq)
    protected ImageView qq;
    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity) mContext).setMineBarSytle(getResources().getString(R.string.personal_center_my_friend_title), R.color.common_blue, true);
    }

    @Override
    protected void successViewCompleted(View successView) {
        ButterKnife.bind(this, successView);
        weixin.setOnClickListener(this);
        xinlang.setOnClickListener(this);
        qq.setOnClickListener(this);
        ShareSDK.initSDK(getActivity());
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        isShowLoadPage=false;
    }

    @Override
    public void onClick(View v) {
        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setTitle("分享");
        sp.setText("不错,赞一个 ");
        sp.setImageUrl("http://101.200.193.187:8888/Fload/hwg/2015-10-15/7E2E92559DD042BD94F643FED27AF5BC.png?id=7E2E92559DD042BD94F643FED27AF5BC&fn=2015-10-15&fs=png&appkey=hwg");
        sp.setUrl("https://www.baidu.com/");
        switch (v.getId()){
            case R.id.weixin:
                Platform weixin = ShareSDK.getPlatform(Wechat.NAME);
                weixin.setPlatformActionListener(this); // 设置分享事件回调
                sp.setShareType(Platform.SHARE_WEBPAGE);
                weixin.share(sp);
                break;
            case R.id.xinlang:
                sp.setTitleUrl("https://www.baidu.com/");
                Platform xinlang = ShareSDK.getPlatform(SinaWeibo.NAME);
                xinlang.setPlatformActionListener(this); // 设置分享事件回调
                break;
            case R.id.qq:
                sp.setTitleUrl("https://www.baidu.com/");
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                qq.setPlatformActionListener(this); // 设置分享事件回调
                qq.share(sp);
                break;
        }
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        Log.e("share ","share completed");
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        Log.e("share ","onError"+platform.getContext() +  i);
    }

    @Override
    public void onCancel(Platform platform, int i) {
        Log.e("share ","cancel");

    }
}
