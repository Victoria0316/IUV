package bluemobi.iuv.app;

import android.app.Application;
import android.os.Environment;
import android.os.Handler;


import java.util.ArrayList;
import java.util.List;

import bluemobi.iuv.entity.Lonlat;
import bluemobi.iuv.entity.MarketInfoBean;
import bluemobi.iuv.network.model.UserInfo;
import bluemobi.iuv.network.response.SearchCountyResponse;
import bluemobi.iuv.network.response.TopSortResponse;
import cn.sharesdk.framework.ShareSDK;


public class IuwApplication extends Application
{
    public static IuwApplication instance;

    private final String SD_PATH = Environment.getExternalStorageDirectory()
            .getAbsolutePath();
    public final String FILE_PATH = SD_PATH + "/IUWLOG/";

    /**
     * 分页查询 往server 传递 时间
     */
    private long pageTime;
    private static int mainTid;
    private static Handler handler;
    public UserInfo myUserInfo ;

    private ArrayList<SearchCountyResponse.SearchCountyData> mCountyData = null;


    /**
     * 宝藏顶层列表数据
     */
    private ArrayList<TopSortResponse.TopSortData> mTopData = null;

    public ArrayList<MarketInfoBean> iuwMarketInfoBeans = null;
    public Lonlat lonlat= null;

    @Override
    public void onCreate()
    {
        super.onCreate();
        init();
        instance = this;
        mainTid = android.os.Process.myTid();
        handler=new Handler();
    }

    private void init()
    {

        VolleyManager.init(this);
    }


    public static IuwApplication getInstance()
    {
        return instance;
    }

    public static int getMainTid() {
        return mainTid;
    }
    public static Handler getHandler() {
        return handler;
    }

    public long getPageTime() {
        return pageTime;
    }

    public void setPageTime(long pageTime) {
        this.pageTime = pageTime;
    }

    public UserInfo getMyUserInfo() {
        return myUserInfo;
    }

    public void setMyUserInfo(UserInfo myUserInfo) {
        this.myUserInfo = myUserInfo;
    }

    public ArrayList<SearchCountyResponse.SearchCountyData> getmCountyData() {
        return mCountyData;
    }

    public void setmCountyData(ArrayList<SearchCountyResponse.SearchCountyData> mCountyData) {
        this.mCountyData = mCountyData;
    }

    public List<String> dataList = new ArrayList<String>();
    public List<String> dataCodeList = new ArrayList<String>();

    public ArrayList<TopSortResponse.TopSortData> getmTopData() {
        return mTopData;
    }

    public void setmTopData(ArrayList<TopSortResponse.TopSortData> mTopData) {
        this.mTopData = mTopData;
    }



}
